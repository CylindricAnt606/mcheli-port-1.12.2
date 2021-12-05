/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class PlusExpression
/*    */   extends Col2Expression
/*    */ {
/*    */   public PlusExpression() {
/* 12 */     setOperator("+");
/*    */   }
/*    */   
/*    */   protected PlusExpression(mcheli.eval.eval.exp.PlusExpression from, ShareExpValue s) {
/* 16 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.PlusExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long vl, long vr) {
/* 24 */     return vl + vr;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double vl, double vr) {
/* 28 */     return vl + vr;
/*    */   }
/*    */   
/*    */   protected Object operateObject(Object vl, Object vr) {
/* 32 */     return this.share.oper.plus(vl, vr);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\PlusExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */