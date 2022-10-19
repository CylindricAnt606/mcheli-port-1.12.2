/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col3Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class IfExpression
/*    */   extends Col3Expression
/*    */ {
/*    */   public IfExpression() {
/* 12 */     setOperator("?");
/* 13 */     setEndOperator(":");
/*    */   }
/*    */   
/*    */   protected IfExpression(mcheli.eval.eval.exp.IfExpression from, ShareExpValue s) {
/* 17 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.IfExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/* 25 */     if (this.exp1.evalLong() != 0L) {
/* 26 */       return this.exp2.evalLong();
/*    */     }
/* 28 */     return this.exp3.evalLong();
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble() {
/* 33 */     if (this.exp1.evalDouble() != 0.0D) {
/* 34 */       return this.exp2.evalDouble();
/*    */     }
/* 36 */     return this.exp3.evalDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject() {
/* 41 */     if (this.share.oper.bool(this.exp1.evalObject())) {
/* 42 */       return this.exp2.evalObject();
/*    */     }
/* 44 */     return this.exp3.evalObject();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\IfExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */