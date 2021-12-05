/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1AfterExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class DecAfterExpression
/*    */   extends Col1AfterExpression
/*    */ {
/*    */   public DecAfterExpression() {
/* 13 */     setOperator("--");
/*    */   }
/*    */   
/*    */   protected DecAfterExpression(mcheli.eval.eval.exp.DecAfterExpression from, ShareExpValue s) {
/* 17 */     super((Col1Expression)from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.DecAfterExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long val) {
/* 25 */     this.exp.let(val - 1L, this.pos);
/* 26 */     return val;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double val) {
/* 30 */     this.exp.let(val - 1.0D, this.pos);
/* 31 */     return val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 35 */     Object val = this.exp.evalObject();
/* 36 */     this.exp.let(this.share.oper.inc(val, -1), this.pos);
/* 37 */     return val;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\DecAfterExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */