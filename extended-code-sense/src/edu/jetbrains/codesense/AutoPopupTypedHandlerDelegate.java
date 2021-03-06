package edu.jetbrains.codesense;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.DotAutoLookupHandler;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import edu.jetbrains.options.BeanManager;
import edu.jetbrains.util.Debug;
import edu.jetbrains.util.Util;

import java.util.concurrent.atomic.AtomicLong;

public class AutoPopupTypedHandlerDelegate extends TypedHandlerDelegate {

    final AtomicLong scheduledOrderNumber = new AtomicLong(0);

    public AutoPopupTypedHandlerDelegate() {
    }

    public Result checkAutoPopup(char charTyped, Project project, Editor editor, PsiFile file) {
        Debug.out("check auto popup: ["+charTyped+"]");
        if (editor != null && (editor.isDisposed() || editor.isViewer())) {
            return Result.CONTINUE;
        }
        final boolean isInWordAutoActivation = BeanManager.storedBean().isInWordAutoActivation();
        final boolean isOutOfWordAutoActivation = BeanManager.storedBean().isOutOfWordAutoActivation();
        boolean isAuto = false;
        if ( isInWordAutoActivation || isOutOfWordAutoActivation ) {
            final boolean isWord = Character.isJavaIdentifierPart(charTyped);
            if (isWord) {
               isAuto = isInWordAutoActivation
                    && isIdentifierOrKeywordInEditorContext(charTyped, editor);
            } else {
               isAuto = isOutOfWordAutoActivation
                    && isAfterConfiguredTrigger(charTyped, editor);
            }
      }
      if ( isAuto ) {
          Debug.out("***** scheduled.");
          schedulePopupImpl(project, editor, file);
          return Result.STOP;
      }
      return Result.CONTINUE;
    }

    protected /* 4junit */ void schedulePopupImpl(final Project project, final Editor editor, final PsiFile file) {
        final Runnable request = new Runnable(){
            private final long myOrderNumber = scheduledOrderNumber.incrementAndGet();
            public void run(){
              if (scheduledOrderNumber.get() > myOrderNumber) {
                  Debug.out("superseded by a later request. noop.");
                  return;
              }
            if (project.isDisposed()) { return; }
            if (editor.isDisposed()) { return; }
            //if (editor.isViewer()) { return; }
            /* not sure why all editors are to be committed. */
            //Debug.out("committed all editors.");
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            //Debug.out("invoking the completion...");
            new DotAutoLookupHandler().invoke(project, editor, file);
            //Debug.out("done.");
          }
        };
        int delay = getDelay();
        AutoPopupController controller = AutoPopupController.getInstance(project);
        controller.invokeAutoPopupRunnable( request, delay );
    }

    static int getDelay() {
        String delayString = BeanManager.storedBean().getAutoActivationDelay();
        Integer delay = Util.getInt(delayString);
        return delay;
    }

    protected boolean isIdentifierOrKeywordInEditorContext(char charTyped, Editor editor) {
        final int typedOffset = editor.getCaretModel().getOffset();
        final CharSequence seq = editor.getDocument().getCharsSequence();
        return isIdentifierInEditorContext(charTyped, seq, typedOffset);        
    }

    static boolean isIdentifierInEditorContext(char charTyped, CharSequence seq, int typedOffset) {
        int stopperOffset = typedOffset;
        while (true) {
            stopperOffset--;
            if (stopperOffset < 0) {
                break; // start of the file is also a stopper
            }
            char c = seq.charAt(stopperOffset);
            if (!Character.isJavaIdentifierPart(c)) {
                break; // stopper found
            }
        }
        final char expectedStartOfIdentifier;
        if (stopperOffset == typedOffset - 1) {
            expectedStartOfIdentifier = charTyped;
        } else {
            expectedStartOfIdentifier = seq.charAt(stopperOffset + 1);
        }
        boolean result = Character.isJavaIdentifierStart(expectedStartOfIdentifier);
        return result;
    }

    protected boolean isAfterConfiguredTrigger(char charTyped, Editor editor) {
        final int typedOffset = editor.getCaretModel().getOffset();
        final CharSequence seq = editor.getDocument().getCharsSequence();
        return isAfterConfiguredTrigger(charTyped, seq, typedOffset);
    }

    public static boolean isBlank(char c) {
        return (c <= ' ')
                || c == '\u00A0'
                || c == '\u2007'
                || c == '\u202F'
                || Character.isWhitespace(c);
    }

    static boolean isAfterConfiguredTrigger(char charTyped, CharSequence seq, final int typedOffset) {
        int stopperOffset = typedOffset;
        char stopper = 0;
        while (true) {
            if (stopperOffset == typedOffset) {
                stopper = charTyped;
            } else {
                if (stopperOffset < 0) {
                    stopper = 0;
                    break; // start of the file is also a stopper
                }
                stopper = seq.charAt(stopperOffset);
            }
            stopperOffset--;
            if (!isBlank(stopper)) {
                break; // stopper found
            }
        }
        Debug.out("stopper char = ["+stopper+"]");
        if (stopper == 0) {
            return false;
        } else {
            String activationCharacters = BeanManager.storedBean().getOutOfWordActivationCharacters();
            boolean result = activationCharacters.indexOf(stopper) >= 0;
            Debug.out("is after trigger: ["+result+"]");
            return result;
        }
    }

}
