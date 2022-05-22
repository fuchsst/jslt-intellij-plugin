// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltFunctionDeclNameDecl extends JsltFunctionDeclNameDeclElement {

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String newName);

  @Nullable
  PsiElement getNameIdentifier();

  boolean isReferenceTo(@NotNull PsiElement otherElement);

}
