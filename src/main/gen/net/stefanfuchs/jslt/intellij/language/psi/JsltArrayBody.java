// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltArrayBody extends PsiElement {

  @Nullable
  JsltArrayElements getArrayElements();

  @Nullable
  JsltArrayFor getArrayFor();

  @NotNull
  List<JsltExpr> getExpressions();

}
