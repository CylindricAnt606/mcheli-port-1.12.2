/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.PowerExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ public class LetPowerExpression
/*    */   extends PowerExpression
/*    */ {
/*    */   public LetPowerExpression() {
/* 13 */     setOperator("**=");
/*    */   }
/*    */   
/*    */   protected LetPowerExpression(mcheli.eval.eval.exp.LetPowerExpression from, ShareExpValue s) {
/* 17 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 21 */     return (AbstractExpression)new mcheli.eval.eval.exp.LetPowerExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/* 25 */     long val = super.evalLong();
/* 26 */     this.expl.let(val, this.pos);
/* 27 */     return val;
/*    */   }
/*    */   
/*    */   public double evalDouble() {
/* 31 */     double val = super.evalDouble();
/* 32 */     this.expl.let(val, this.pos);
/* 33 */     return val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 37 */     Object val = super.evalObject();
/* 38 */     this.expl.let(val, this.pos);
/* 39 */     return val;
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 43 */     this.expl = this.expl.replaceVar();
/* 44 */     this.expr = this.expr.replace();
/* 45 */     return this.share.repl.replaceLet((Col2Expression)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\LetPowerExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */