/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.Col2Expression;
/*     */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldExpression
/*     */   extends Col2OpeExpression
/*     */ {
/*     */   public FieldExpression() {
/*  18 */     setOperator(".");
/*     */   }
/*     */   
/*     */   protected FieldExpression(mcheli.eval.eval.exp.FieldExpression from, ShareExpValue s) {
/*  22 */     super((Col2Expression)from, s);
/*     */   }
/*     */   
/*     */   public AbstractExpression dup(ShareExpValue s) {
/*  26 */     return (AbstractExpression)new mcheli.eval.eval.exp.FieldExpression(this, s);
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*     */     try {
/*  31 */       return this.share.var.evalLong(getVariable());
/*  32 */     } catch (EvalException e) {
/*  33 */       throw e;
/*  34 */     } catch (Exception e) {
/*  35 */       throw new EvalException(2003, toString(), this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double evalDouble() {
/*     */     try {
/*  42 */       return this.share.var.evalDouble(getVariable());
/*  43 */     } catch (EvalException e) {
/*  44 */       throw e;
/*  45 */     } catch (Exception e) {
/*  46 */       throw new EvalException(2003, toString(), this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object evalObject() {
/*  52 */     return getVariable();
/*     */   }
/*     */   
/*     */   protected Object getVariable() {
/*  56 */     Object obj = this.expl.getVariable();
/*  57 */     if (obj == null) {
/*  58 */       throw new EvalException(2104, this.expl.toString(), this.string, this.pos, null);
/*     */     }
/*     */     
/*  61 */     String word = this.expr.getWord();
/*     */     try {
/*  63 */       return this.share.var.getObject(obj, word);
/*  64 */     } catch (EvalException e) {
/*  65 */       throw e;
/*  66 */     } catch (Exception e) {
/*  67 */       throw new EvalException(2301, toString(), this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void let(Object val, int pos) {
/*  73 */     Object obj = this.expl.getVariable();
/*  74 */     if (obj == null) {
/*  75 */       throw new EvalException(2104, this.expl.toString(), this.string, pos, null);
/*     */     }
/*     */     
/*  78 */     String word = this.expr.getWord();
/*     */     try {
/*  80 */       this.share.var.setValue(obj, word, val);
/*  81 */     } catch (EvalException e) {
/*  82 */       throw e;
/*  83 */     } catch (Exception e) {
/*  84 */       throw new EvalException(2302, toString(), this.string, pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected AbstractExpression replace() {
/*  90 */     this.expl = this.expl.replaceVar();
/*     */     
/*  92 */     return this.share.repl.replace2(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replaceVar() {
/*  96 */     this.expl = this.expl.replaceVar();
/*     */     
/*  98 */     return this.share.repl.replaceVar2(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 102 */     StringBuffer sb = new StringBuffer();
/* 103 */     sb.append(this.expl.toString());
/* 104 */     sb.append('.');
/* 105 */     sb.append(this.expr.toString());
/* 106 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\FieldExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */