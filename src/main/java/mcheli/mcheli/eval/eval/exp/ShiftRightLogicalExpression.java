/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class ShiftRightLogicalExpression
/*    */   extends Col2Expression
/*    */ {
/*    */   public ShiftRightLogicalExpression() {
/* 12 */     setOperator(">>>");
/*    */   }
/*    */ 
/*    */   
/*    */   protected ShiftRightLogicalExpression(mcheli.eval.eval.exp.ShiftRightLogicalExpression from, ShareExpValue s) {
/* 17 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.ShiftRightLogicalExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long vl, long vr) {
/* 25 */     return vl >>> (int)vr;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double vl, double vr) {
/* 29 */     if (vl < 0.0D)
/* 30 */       vl = -vl; 
/* 31 */     return vl / Math.pow(2.0D, vr);
/*    */   }
/*    */   
/*    */   protected Object operateObject(Object vl, Object vr) {
/* 35 */     return this.share.oper.shiftRightLogical(vl, vr);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\ShiftRightLogicalExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */