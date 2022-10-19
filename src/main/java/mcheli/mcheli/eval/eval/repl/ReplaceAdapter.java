/*    */ package mcheli.mcheli.eval.eval.repl;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.Col3Expression;
/*    */ import mcheli.eval.eval.exp.FunctionExpression;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.repl.Replace;
/*    */ 
/*    */ public class ReplaceAdapter
/*    */   implements Replace {
/*    */   public AbstractExpression replace0(WordExpression exp) {
/* 15 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace1(Col1Expression exp) {
/* 19 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace2(Col2Expression exp) {
/* 23 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace2(Col2OpeExpression exp) {
/* 27 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace3(Col3Expression exp) {
/* 31 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar0(WordExpression exp) {
/* 35 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar1(Col1Expression exp) {
/* 39 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar2(Col2Expression exp) {
/* 43 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar2(Col2OpeExpression exp) {
/* 47 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar3(Col3Expression exp) {
/* 51 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceFunc(FunctionExpression exp) {
/* 55 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceLet(Col2Expression exp) {
/* 59 */     return (AbstractExpression)exp;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\repl\ReplaceAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */