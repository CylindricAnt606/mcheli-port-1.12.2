/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParenExpression
/*    */   extends Col1Expression
/*    */ {
/*    */   public ParenExpression() {
/* 13 */     setOperator("(");
/* 14 */     setEndOperator(")");
/*    */   }
/*    */   
/*    */   protected ParenExpression(mcheli.eval.eval.exp.ParenExpression from, ShareExpValue s) {
/* 18 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 22 */     return (AbstractExpression)new mcheli.eval.eval.exp.ParenExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long val) {
/* 26 */     return val;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double val) {
/* 30 */     return val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 34 */     return this.exp.evalObject();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 38 */     if (this.exp == null) {
/* 39 */       return "";
/*    */     }
/* 41 */     return getOperator() + this.exp.toString() + getEndOperator();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\ParenExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */