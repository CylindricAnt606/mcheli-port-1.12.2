/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class GreaterEqualExpression
/*    */   extends Col2Expression
/*    */ {
/*    */   public GreaterEqualExpression() {
/* 12 */     setOperator(">=");
/*    */   }
/*    */ 
/*    */   
/*    */   protected GreaterEqualExpression(mcheli.eval.eval.exp.GreaterEqualExpression from, ShareExpValue s) {
/* 17 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.GreaterEqualExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long vl, long vr) {
/* 25 */     return (vl >= vr) ? 1L : 0L;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double vl, double vr) {
/* 29 */     return (vl >= vr) ? 1.0D : 0.0D;
/*    */   }
/*    */   
/*    */   protected Object operateObject(Object vl, Object vr) {
/* 33 */     return this.share.oper.greaterEqual(vl, vr);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\GreaterEqualExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */