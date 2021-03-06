package edu.jetbrains.completionWithLiveTemplates;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.command.impl.DummyProject;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: ivan
 * Date: 11.07.2010
 * Time: 20:29:13
 * To change this template use File | Settings | File Templates.
 */
public class MockPsiElement implements PsiElement {

    public String text;
    public Project project = DummyProject.getInstance();
    public PsiFile psiFile ;

    @NotNull
    public Project getProject() throws PsiInvalidElementAccessException {
        return project;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Language getLanguage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiManager getManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public PsiElement[] getChildren() {
        return new PsiElement[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getParent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getFirstChild() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getLastChild() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getNextSibling() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getPrevSibling() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
        return psiFile;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public TextRange getTextRange() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getStartOffsetInParent() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTextLength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement findElementAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference findReferenceAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTextOffset() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getText() {
        return text;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public char[] textToCharArray() {
        return new char[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getNavigationElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getOriginalElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean textMatches(@NotNull CharSequence text) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean textMatches(@NotNull PsiElement element) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean textContains(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void acceptChildren(@NotNull PsiElementVisitor visitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement copy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addBefore(@NotNull PsiElement element, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addAfter(@NotNull PsiElement element, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkAdd(@NotNull PsiElement element) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeBefore(@NotNull PsiElement first, @NotNull PsiElement last, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeAfter(PsiElement first, PsiElement last, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkDelete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteChildRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isWritable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference getReference() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public PsiReference[] getReferences() {
        return new PsiReference[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getCopyableUserData(Key<T> key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putCopyableUserData(Key<T> key, T value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isPhysical() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public GlobalSearchScope getResolveScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public SearchScope getUseScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ASTNode getNode() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEquivalentTo(PsiElement another) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Icon getIcon(int flags) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getUserData(@NotNull Key<T> key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
