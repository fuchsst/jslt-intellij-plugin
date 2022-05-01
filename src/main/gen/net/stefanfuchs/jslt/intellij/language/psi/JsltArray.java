// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JsltArray extends PsiElement {

  @Nullable
  JsltArrayElem getArrayElem();

  @NotNull
  List<JsltExpr> getExprList();

  @NotNull
  List<JsltLetAssignment> getLetAssignmentList();

  @NotNull
  ItemPresentation getPresentation();

}
