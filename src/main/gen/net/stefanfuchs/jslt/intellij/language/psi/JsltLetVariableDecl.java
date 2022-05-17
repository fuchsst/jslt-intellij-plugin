// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltLetVariableDecl extends JsltLetVariableDeclElement {

  boolean isReferenceTo(@NotNull PsiElement otherElement);

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String newAlias);

  @Nullable
  PsiElement getNameIdentifier();

}
