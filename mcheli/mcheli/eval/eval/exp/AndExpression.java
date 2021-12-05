/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ public class AndExpression
/*    */   extends Col2OpeExpression
/*    */ {
/*    */   public AndExpression() {
/* 12 */     setOperator("&&");
/*    */   }
/*    */   
/*    */   protected AndExpression(mcheli.eval.eval.exp.AndExpression from, ShareExpValue s) {
/* 16 */     super((Col2Expression)from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.AndExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/* 24 */     long val = this.expl.evalLong();
/* 25 */     if (val == 0L)
/* 26 */       return val; 
/* 27 */     return this.expr.evalLong();
/*    */   }
/*    */   
/*    */   public double evalDouble() {
/* 31 */     double val = this.expl.evalDouble();
/* 32 */     if (val == 0.0D)
/* 33 */       return val; 
/* 34 */     return this.expr.evalDouble();
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 38 */     Object val = this.expl.evalObject();
/* 39 */     if (!this.share.oper.bool(val)) {
/* 40 */       return val;
/*    */     }
/* 42 */     return this.expr.evalObject();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\AndExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */