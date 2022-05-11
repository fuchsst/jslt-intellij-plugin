// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsltFunctionCall extends PsiElement {

  @NotNull
  List<JsltExpr> getExprList();

  @NotNull
  JsltFunctionName getFunctionName();

  @Nullable
  String getImportAlias();

  //WARNING: setImportAlias(...) is skipped
  //matching setImportAlias(JsltFunctionCall, ...)
  //methods are not found in JsltPsiImplUtil

  @Nullable
  String getName();

  //WARNING: setName(...) is skipped
  //matching setName(JsltFunctionCall, ...)
  //methods are not found in JsltPsiImplUtil

}
