/*    */ package mcheli.mcheli.eval.eval.srch;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col3Expression;
/*    */ import mcheli.eval.eval.exp.FunctionExpression;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.srch.Search;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SearchAdapter
/*    */   implements Search
/*    */ {
/*    */   protected boolean end = false;
/*    */   
/*    */   public boolean end() {
/* 20 */     return this.end;
/*    */   }
/*    */   
/*    */   protected void setEnd() {
/* 24 */     this.end = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void search(AbstractExpression exp) {}
/*    */ 
/*    */   
/*    */   public void search0(WordExpression exp) {}
/*    */   
/*    */   public boolean search1_begin(Col1Expression exp) {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void search1_end(Col1Expression exp) {}
/*    */   
/*    */   public boolean search2_begin(Col2Expression exp) {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public boolean search2_2(Col2Expression exp) {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void search2_end(Col2Expression exp) {}
/*    */   
/*    */   public boolean search3_begin(Col3Expression exp) {
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public boolean search3_2(Col3Expression exp3) {
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public boolean search3_3(Col3Expression exp) {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void search3_end(Col3Expression exp) {}
/*    */   
/*    */   public boolean searchFunc_begin(FunctionExpression exp) {
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public boolean searchFunc_2(FunctionExpression exp) {
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public void searchFunc_end(FunctionExpression exp) {}
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\srch\SearchAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */