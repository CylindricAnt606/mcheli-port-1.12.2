/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ import mcheli.eval.eval.exp.WordExpression;
/*     */ import mcheli.eval.eval.lex.Lex;
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
/*     */ public class VariableExpression
/*     */   extends WordExpression
/*     */ {
/*     */   public static AbstractExpression create(Lex lex, int prio) {
/*  25 */     mcheli.eval.eval.exp.VariableExpression variableExpression = new mcheli.eval.eval.exp.VariableExpression(lex.getWord());
/*  26 */     variableExpression.setPos(lex.getString(), lex.getPos());
/*  27 */     variableExpression.setPriority(prio);
/*  28 */     ((AbstractExpression)variableExpression).share = lex.getShare();
/*  29 */     return (AbstractExpression)variableExpression;
/*     */   }
/*     */   
/*     */   public VariableExpression(String str) {
/*  33 */     super(str);
/*     */   }
/*     */   
/*     */   protected VariableExpression(mcheli.eval.eval.exp.VariableExpression from, ShareExpValue s) {
/*  37 */     super(from, s);
/*     */   }
/*     */   
/*     */   public AbstractExpression dup(ShareExpValue s) {
/*  41 */     return (AbstractExpression)new mcheli.eval.eval.exp.VariableExpression(this, s);
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*     */     try {
/*  46 */       return this.share.var.evalLong(getVarValue());
/*  47 */     } catch (EvalException e) {
/*  48 */       throw e;
/*  49 */     } catch (Exception e) {
/*  50 */       throw new EvalException(2003, this.word, this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double evalDouble() {
/*     */     try {
/*  57 */       return this.share.var.evalDouble(getVarValue());
/*  58 */     } catch (EvalException e) {
/*  59 */       throw e;
/*  60 */     } catch (Exception e) {
/*  61 */       throw new EvalException(2003, this.word, this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object evalObject() {
/*  67 */     return getVarValue();
/*     */   }
/*     */   
/*     */   protected void let(Object val, int pos) {
/*  71 */     String name = getWord();
/*     */     try {
/*  73 */       this.share.var.setValue(name, val);
/*  74 */     } catch (EvalException e) {
/*  75 */       throw e;
/*  76 */     } catch (Exception e) {
/*  77 */       throw new EvalException(2102, name, this.string, pos, e);
/*     */     } 
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
/*     */   private Object getVarValue() {
/*     */     Object val;
/*  91 */     String word = getWord();
/*     */ 
/*     */     
/*     */     try {
/*  95 */       val = this.share.var.getObject(word);
/*  96 */     } catch (EvalException e) {
/*  97 */       throw e;
/*  98 */     } catch (Exception e) {
/*  99 */       throw new EvalException(2101, word, this.string, this.pos, e);
/*     */     } 
/*     */     
/* 102 */     if (val == null) {
/* 103 */       throw new EvalException(2103, word, this.string, this.pos, null);
/*     */     }
/*     */     
/* 106 */     return val;
/*     */   }
/*     */   
/*     */   protected Object getVariable() {
/*     */     try {
/* 111 */       return this.share.var.getObject(this.word);
/* 112 */     } catch (EvalException e) {
/* 113 */       throw e;
/* 114 */     } catch (Exception e) {
/* 115 */       throw new EvalException(2002, this.word, this.string, this.pos, null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\VariableExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */