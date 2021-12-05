/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DecBeforeExpression
/*    */   extends Col1Expression
/*    */ {
/*    */   public DecBeforeExpression() {
/* 13 */     setOperator("--");
/*    */   }
/*    */   
/*    */   protected DecBeforeExpression(mcheli.eval.eval.exp.DecBeforeExpression from, ShareExpValue s) {
/* 17 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.DecBeforeExpression(this, s);
/*    */   }
/*    */   
/*    */   protected long operateLong(long val) {
/* 25 */     val--;
/* 26 */     this.exp.let(val, this.pos);
/* 27 */     return val;
/*    */   }
/*    */   
/*    */   protected double operateDouble(double val) {
/* 31 */     val--;
/* 32 */     this.exp.let(val, this.pos);
/* 33 */     return val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 37 */     Object val = this.exp.evalObject();
/* 38 */     val = this.share.oper.inc(val, -1);
/* 39 */     this.exp.let(val, this.pos);
/* 40 */     return val;
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 44 */     this.exp = this.exp.replaceVar();
/* 45 */     return this.share.repl.replaceVar1(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\DecBeforeExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */