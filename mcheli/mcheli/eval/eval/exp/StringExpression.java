/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ import mcheli.eval.eval.exp.WordExpression;
/*     */ import mcheli.eval.eval.lex.Lex;
/*     */ import mcheli.eval.util.CharUtil;
/*     */ import mcheli.eval.util.NumberUtil;
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
/*     */ public class StringExpression
/*     */   extends WordExpression
/*     */ {
/*     */   public static AbstractExpression create(Lex lex, int prio) {
/*  26 */     String str = lex.getWord();
/*  27 */     str = CharUtil.escapeString(str, 1, str.length() - 2);
/*  28 */     mcheli.eval.eval.exp.StringExpression stringExpression = new mcheli.eval.eval.exp.StringExpression(str);
/*  29 */     stringExpression.setPos(lex.getString(), lex.getPos());
/*  30 */     stringExpression.setPriority(prio);
/*  31 */     ((AbstractExpression)stringExpression).share = lex.getShare();
/*  32 */     return (AbstractExpression)stringExpression;
/*     */   }
/*     */   
/*     */   public StringExpression(String str) {
/*  36 */     super(str);
/*  37 */     setOperator("\"");
/*  38 */     setEndOperator("\"");
/*     */   }
/*     */   
/*     */   protected StringExpression(mcheli.eval.eval.exp.StringExpression from, ShareExpValue s) {
/*  42 */     super(from, s);
/*     */   }
/*     */   
/*     */   public AbstractExpression dup(ShareExpValue s) {
/*  46 */     return (AbstractExpression)new mcheli.eval.eval.exp.StringExpression(this, s);
/*     */   }
/*     */   
/*     */   public static mcheli.eval.eval.exp.StringExpression create(AbstractExpression from, String word) {
/*  50 */     mcheli.eval.eval.exp.StringExpression n = new mcheli.eval.eval.exp.StringExpression(word);
/*  51 */     n.string = from.string;
/*  52 */     n.pos = from.pos;
/*  53 */     n.prio = from.prio;
/*  54 */     n.share = from.share;
/*  55 */     return n;
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*     */     try {
/*  60 */       return NumberUtil.parseLong(this.word);
/*  61 */     } catch (Exception e) {
/*     */       
/*     */       try {
/*  64 */         return Long.parseLong(this.word);
/*  65 */       } catch (Exception exception) {
/*     */         
/*     */         try {
/*  68 */           return (long)Double.parseDouble(this.word);
/*  69 */         } catch (Exception exception1) {
/*  70 */           throw new EvalException(2003, this.word, this.string, this.pos, exception1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public double evalDouble() {
/*     */     try {
/*  77 */       return Double.parseDouble(this.word);
/*  78 */     } catch (Exception e) {
/*     */       try {
/*  80 */         return NumberUtil.parseLong(this.word);
/*  81 */       } catch (Exception e2) {
/*     */         
/*  83 */         throw new EvalException(2003, this.word, this.string, this.pos, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object evalObject() {
/*  89 */     return this.word;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  93 */     if (obj instanceof mcheli.eval.eval.exp.StringExpression) {
/*  94 */       mcheli.eval.eval.exp.StringExpression e = (mcheli.eval.eval.exp.StringExpression)obj;
/*  95 */       return this.word.equals(e.word);
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 101 */     return this.word.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 105 */     StringBuffer sb = new StringBuffer();
/* 106 */     sb.append(getOperator());
/* 107 */     sb.append(this.word);
/* 108 */     sb.append(getEndOperator());
/* 109 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\StringExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */