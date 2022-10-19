/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import java.util.List;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FuncArgExpression
/*    */   extends Col2OpeExpression
/*    */ {
/*    */   public FuncArgExpression() {
/* 16 */     setOperator(",");
/*    */   }
/*    */   
/*    */   protected FuncArgExpression(mcheli.eval.eval.exp.FuncArgExpression from, ShareExpValue s) {
/* 20 */     super((Col2Expression)from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 24 */     return (AbstractExpression)new mcheli.eval.eval.exp.FuncArgExpression(this, s);
/*    */   }
/*    */   
/*    */   protected void evalArgsLong(List args) {
/* 28 */     this.expl.evalArgsLong(args);
/* 29 */     this.expr.evalArgsLong(args);
/*    */   }
/*    */   
/*    */   protected void evalArgsDouble(List args) {
/* 33 */     this.expl.evalArgsDouble(args);
/* 34 */     this.expr.evalArgsDouble(args);
/*    */   }
/*    */   
/*    */   protected void evalArgsObject(List args) {
/* 38 */     this.expl.evalArgsObject(args);
/* 39 */     this.expr.evalArgsObject(args);
/*    */   }
/*    */   
/*    */   protected String toStringLeftSpace() {
/* 43 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\FuncArgExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */