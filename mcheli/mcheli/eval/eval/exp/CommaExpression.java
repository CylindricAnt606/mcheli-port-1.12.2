/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ public class CommaExpression
/*    */   extends Col2OpeExpression
/*    */ {
/*    */   public CommaExpression() {
/* 12 */     setOperator(",");
/*    */   }
/*    */   
/*    */   protected CommaExpression(mcheli.eval.eval.exp.CommaExpression from, ShareExpValue s) {
/* 16 */     super((Col2Expression)from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.CommaExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/* 24 */     this.expl.evalLong();
/* 25 */     return this.expr.evalLong();
/*    */   }
/*    */   
/*    */   public double evalDouble() {
/* 29 */     this.expl.evalDouble();
/* 30 */     return this.expr.evalDouble();
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 34 */     this.expl.evalObject();
/* 35 */     return this.expr.evalObject();
/*    */   }
/*    */   
/*    */   protected String toStringLeftSpace() {
/* 39 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\CommaExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */