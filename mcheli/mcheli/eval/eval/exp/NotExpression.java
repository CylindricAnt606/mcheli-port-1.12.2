/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class NotExpression
/*    */   extends Col1Expression
/*    */ {
/*    */   public NotExpression() {
/* 12 */     setOperator("!");
/*    */   }
/*    */   
/*    */   protected NotExpression(mcheli.eval.eval.exp.NotExpression from, ShareExpValue s) {
/* 16 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.NotExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long val) {
/* 24 */     return (val == 0L) ? 1L : 0L;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double val) {
/* 28 */     return (val == 0.0D) ? 1.0D : 0.0D;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 32 */     return this.share.oper.not(this.exp.evalObject());
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\NotExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */