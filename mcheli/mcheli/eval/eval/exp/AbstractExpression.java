/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractExpression
/*     */ {
/*     */   public static final int TRUE = 1;
/*     */   public static final int FALSE = 0;
/*     */   
/*     */   protected final boolean isTrue(boolean lng) {
/*  26 */     if (lng) {
/*  27 */       return (evalLong() != 0L);
/*     */     }
/*  29 */     return (evalDouble() != 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   protected String string = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected int pos = -1;
/*     */   
/*     */   private String ope1;
/*     */   
/*     */   private String ope2;
/*     */   
/*     */   public ShareExpValue share;
/*     */   protected int prio;
/*     */   
/*     */   protected AbstractExpression() {}
/*     */   
/*     */   protected AbstractExpression(mcheli.eval.eval.exp.AbstractExpression from, ShareExpValue s) {
/*  59 */     this.string = from.string;
/*  60 */     this.pos = from.pos;
/*  61 */     this.prio = from.prio;
/*  62 */     if (s != null) {
/*  63 */       this.share = s;
/*     */     } else {
/*  65 */       this.share = from.share;
/*     */     } 
/*  67 */     this.ope1 = from.ope1;
/*  68 */     this.ope2 = from.ope2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract mcheli.eval.eval.exp.AbstractExpression dup(ShareExpValue paramShareExpValue);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getOperator() {
/*  93 */     return this.ope1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getEndOperator() {
/* 106 */     return this.ope2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setOperator(String ope) {
/* 117 */     this.ope1 = ope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setEndOperator(String ope) {
/* 128 */     this.ope2 = ope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getWord() {
/* 137 */     return getOperator();
/*     */   }
/*     */   
/*     */   protected void setWord(String word) {
/* 141 */     throw new EvalException(2001, word, this.string, this.pos, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getCols();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setPos(String string, int pos) {
/* 165 */     this.string = string;
/* 166 */     this.pos = pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getFirstPos();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setPriority(int prio) {
/* 190 */     this.prio = prio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getPriority() {
/* 200 */     return this.prio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void let(Object val, int pos) {
/* 219 */     throw new EvalException(2004, toString(), this.string, pos, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void let(long val, int pos) {
/* 227 */     let(new Long(val), pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void let(double val, int pos) {
/* 234 */     let(new Double(val), pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getVariable() {
/* 253 */     String word = toString();
/* 254 */     throw new EvalException(2002, word, this.string, this.pos, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void evalArgsLong(List<Long> args) {
/* 268 */     args.add(new Long(evalLong()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void evalArgsDouble(List<Double> args) {
/* 281 */     args.add(new Double(evalDouble()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void evalArgsObject(List<Object> args) {
/* 295 */     args.add(evalObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract long evalLong();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double evalDouble();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object evalObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void search();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract mcheli.eval.eval.exp.AbstractExpression replace();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract mcheli.eval.eval.exp.AbstractExpression replaceVar();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean equals(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int hashCode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean same(mcheli.eval.eval.exp.AbstractExpression exp) {
/* 395 */     return (same(getOperator(), exp.getOperator()) && same(getEndOperator(), exp.getEndOperator()) && equals(exp));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean same(String str1, String str2) {
/* 401 */     if (str1 == null) {
/* 402 */       return (str2 == null);
/*     */     }
/* 404 */     return str1.equals(str2);
/*     */   }
/*     */   
/*     */   public abstract void dump(int paramInt);
/*     */   
/*     */   public abstract String toString();
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\AbstractExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */