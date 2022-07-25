package mcheli.mcheli.eval.eval.srch;

import mcheli.eval.eval.exp.AbstractExpression;
import mcheli.eval.eval.exp.Col1Expression;
import mcheli.eval.eval.exp.Col2Expression;
import mcheli.eval.eval.exp.Col3Expression;
import mcheli.eval.eval.exp.FunctionExpression;
import mcheli.eval.eval.exp.WordExpression;

public interface Search {
  boolean end();
  
  void search(AbstractExpression paramAbstractExpression);
  
  void search0(WordExpression paramWordExpression);
  
  boolean search1_begin(Col1Expression paramCol1Expression);
  
  void search1_end(Col1Expression paramCol1Expression);
  
  boolean search2_begin(Col2Expression paramCol2Expression);
  
  boolean search2_2(Col2Expression paramCol2Expression);
  
  void search2_end(Col2Expression paramCol2Expression);
  
  boolean search3_begin(Col3Expression paramCol3Expression);
  
  boolean search3_2(Col3Expression paramCol3Expression);
  
  boolean search3_3(Col3Expression paramCol3Expression);
  
  void search3_end(Col3Expression paramCol3Expression);
  
  boolean searchFunc_begin(FunctionExpression paramFunctionExpression);
  
  boolean searchFunc_2(FunctionExpression paramFunctionExpression);
  
  void searchFunc_end(FunctionExpression paramFunctionExpression);
}


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\srch\Search.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */