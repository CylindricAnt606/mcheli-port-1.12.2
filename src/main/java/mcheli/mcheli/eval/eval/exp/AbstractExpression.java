/* Ported to 1.12.2 and Fixed by super_craft_alex */

 package mcheli.mcheli.eval.eval.exp;

 import java.util.List;
 import mcheli.mcheli.eval.eval.EvalException;
 import mcheli.mcheli.eval.eval.exp.ShareExpValue;

 public abstract class AbstractExpression
 {
   public static final int TRUE = 1;
   public static final int FALSE = 0;

   protected final boolean isTrue(boolean lng) {
     if (lng) {
       return (evalLong() != 0L);
     }
     return (evalDouble() != 0.0D);
   }
   protected String string = null;
   protected int pos = -1;

   private String ope1;

   private String ope2;

   public ShareExpValue share;
   protected int prio;

   protected AbstractExpression() {}

   protected AbstractExpression(mcheli.mcheli.eval.eval.exp.AbstractExpression from, ShareExpValue s) {
     this.string = from.string;
     this.pos = from.pos;
     this.prio = from.prio;
     if (s != null) {
       this.share = s;
     } else {
       this.share = from.share;
     }
     this.ope1 = from.ope1;
     this.ope2 = from.ope2;
   }
   public abstract mcheli.mcheli.eval.eval.exp.AbstractExpression dup(ShareExpValue paramShareExpValue);
   public final String getOperator() {
     return this.ope1;
   }
   public final String getEndOperator() {
     return this.ope2;
   }
   public final void setOperator(String ope) {
     this.ope1 = ope;
   }
   public final void setEndOperator(String ope) {
     this.ope2 = ope;
   }
   protected String getWord() {
     return getOperator();
   }
   protected void setWord(String word) {
     throw new EvalException(2001, word, this.string, this.pos, null);
   }
   protected abstract int getCols();
   protected final void setPos(String string, int pos) {
     this.string = string;
     this.pos = pos;
   }
   protected abstract int getFirstPos();
   public final void setPriority(int prio) {
     this.prio = prio;
   }
   protected final int getPriority() {
     return this.prio;
   }
   protected void let(Object val, int pos) {
     throw new EvalException(2004, toString(), this.string, pos, null);
   }
   protected void let(long val, int pos) {
     let(new Long(val), pos);
   }
   protected void let(double val, int pos) {
     let(new Double(val), pos);
   }
   protected Object getVariable() {
     String word = toString();
     throw new EvalException(2002, word, this.string, this.pos, null);
   }
   protected void evalArgsLong(List<Long> args) {
     args.add(new Long(evalLong()));
   }
   protected void evalArgsDouble(List<Double> args) {
     args.add(new Double(evalDouble()));
   }
   protected void evalArgsObject(List<Object> args) {
     args.add(evalObject());
   }
   public abstract long evalLong();
   public abstract double evalDouble();
   public abstract Object evalObject();
   protected abstract void search();
   protected abstract mcheli.mcheli.eval.eval.exp.AbstractExpression replace();
   protected abstract mcheli.mcheli.eval.eval.exp.AbstractExpression replaceVar();
   public abstract boolean equals(Object paramObject);
   public abstract int hashCode();

   public boolean same(mcheli.mcheli.eval.eval.exp.AbstractExpression exp) {
     return (same(getOperator(), exp.getOperator()) && same(getEndOperator(), exp.getEndOperator()) && equals(exp));
   }



   private static boolean same(String str1, String str2) {
     if (str1 == null) {
       return (str2 == null);
     }
     return str1.equals(str2);
   }

   public abstract void dump(int paramInt);

   public abstract String toString();
 }