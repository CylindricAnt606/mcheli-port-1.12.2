/* Ported to 1.12.2 and Fixed by super_craft_alex */

 package mcheli.mcheli.eval.eval.exp;

 import mcheli.mcheli.eval.eval.exp.AbstractExpression;
 import mcheli.mcheli.eval.eval.exp.Col2Expression;
 import mcheli.mcheli.eval.eval.exp.Col2OpeExpression;
 import mcheli.mcheli.eval.eval.exp.ShareExpValue;

 public class AndExpression extends Col2OpeExpression {
   public AndExpression() {
     setOperator("&&");
   }

   protected AndExpression(mcheli.mcheli.eval.eval.exp.AndExpression from, ShareExpValue s) {
     super((Col2Expression)from, s);
   }

   public AbstractExpression dup(ShareExpValue s) {
     return (AbstractExpression)new mcheli.mcheli.eval.eval.exp.AndExpression(this, s);
   }

   public long evalLong() {
     long val = this.expl.evalLong();
     if (val == 0L)
       return val;
     return this.expr.evalLong();
   }

   public double evalDouble() {
     double val = this.expl.evalDouble();
     if (val == 0.0D)
       return val;
     return this.expr.evalDouble();
   }

   public Object evalObject() {
     Object val = this.expl.evalObject();
     if (!this.share.oper.bool(val)) {
       return val;
     }
     return this.expr.evalObject();
   }
 }