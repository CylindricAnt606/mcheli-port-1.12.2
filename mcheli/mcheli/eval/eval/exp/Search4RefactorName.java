/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.FunctionExpression;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.ref.Refactor;
/*    */ import mcheli.eval.eval.srch.SearchAdapter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Search4RefactorName
/*    */   extends SearchAdapter
/*    */ {
/*    */   protected Refactor ref;
/*    */   
/*    */   Search4RefactorName(Refactor ref) {
/* 19 */     this.ref = ref;
/*    */   }
/*    */   
/*    */   public void search0(WordExpression exp) {
/* 23 */     if (exp instanceof mcheli.eval.eval.exp.VariableExpression) {
/* 24 */       String name = this.ref.getNewName(null, exp.getWord());
/* 25 */       if (name != null) {
/* 26 */         exp.setWord(name);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean search2_2(Col2Expression exp) {
/* 32 */     if (exp instanceof mcheli.eval.eval.exp.FieldExpression) {
/* 33 */       AbstractExpression exp1 = exp.expl;
/* 34 */       Object obj = exp1.getVariable();
/* 35 */       if (obj == null) {
/* 36 */         throw new EvalException(2104, toString(), exp1.string, exp1.pos, null);
/*    */       }
/*    */       
/* 39 */       AbstractExpression exp2 = exp.expr;
/* 40 */       String name = this.ref.getNewName(obj, exp2.getWord());
/* 41 */       if (name != null) {
/* 42 */         exp2.setWord(name);
/*    */       }
/* 44 */       return true;
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public boolean searchFunc_2(FunctionExpression exp) {
/* 50 */     Object obj = null;
/* 51 */     if (exp.target != null) {
/* 52 */       obj = exp.target.getVariable();
/*    */     }
/* 54 */     String name = this.ref.getNewFuncName(obj, exp.name);
/* 55 */     if (name != null) {
/* 56 */       exp.name = name;
/*    */     }
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Search4RefactorName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */