/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class SignMinusExpression
/*    */   extends Col1Expression
/*    */ {
/*    */   public SignMinusExpression() {
/* 12 */     setOperator("-");
/*    */   }
/*    */   
/*    */   protected SignMinusExpression(mcheli.eval.eval.exp.SignMinusExpression from, ShareExpValue s) {
/* 16 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.SignMinusExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long val) {
/* 24 */     return -val;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double val) {
/* 28 */     return -val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 32 */     return this.share.oper.signMinus(this.exp.evalObject());
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\SignMinusExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */