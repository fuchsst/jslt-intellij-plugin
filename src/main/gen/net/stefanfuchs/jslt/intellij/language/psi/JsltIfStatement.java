// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltIfStatement extends PsiElement {

  @Nullable
  JsltElseBranch getElseBranch();

  @NotNull
  List<JsltExpr> getExprList();

  @NotNull
  List<JsltLetAssignment> getLetAssignmentList();

}
