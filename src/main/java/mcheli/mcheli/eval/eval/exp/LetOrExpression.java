/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.BitOrExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ public class LetOrExpression
/*    */   extends BitOrExpression
/*    */ {
/*    */   public LetOrExpression() {
/* 12 */     setOperator("|=");
/*    */   }
/*    */   
/*    */   protected LetOrExpression(mcheli.eval.eval.exp.LetOrExpression from, ShareExpValue s) {
/* 16 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 20 */     return (AbstractExpression)new mcheli.eval.eval.exp.LetOrExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/* 24 */     long val = super.evalLong();
/* 25 */     this.expl.let(val, this.pos);
/* 26 */     return val;
/*    */   }
/*    */   
/*    */   public double evalDouble() {
/* 30 */     double val = super.evalDouble();
/* 31 */     this.expl.let(val, this.pos);
/* 32 */     return val;
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/* 36 */     Object val = super.evalObject();
/* 37 */     this.expl.let(val, this.pos);
/* 38 */     return val;
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 42 */     this.expl = this.expl.replaceVar();
/* 43 */     this.expr = this.expr.replace();
/* 44 */     return this.share.repl.replaceLet((Col2Expression)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\LetOrExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */