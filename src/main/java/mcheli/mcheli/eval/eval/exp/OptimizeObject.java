/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.CharExpression;
/*     */ import mcheli.eval.eval.exp.Col1Expression;
/*     */ import mcheli.eval.eval.exp.Col2Expression;
/*     */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*     */ import mcheli.eval.eval.exp.Col3Expression;
/*     */ import mcheli.eval.eval.exp.NumberExpression;
/*     */ import mcheli.eval.eval.exp.StringExpression;
/*     */ import mcheli.eval.eval.exp.WordExpression;
/*     */ import mcheli.eval.eval.repl.ReplaceAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OptimizeObject
/*     */   extends ReplaceAdapter
/*     */ {
/*     */   protected boolean isConst(AbstractExpression x) {
/*  22 */     return (x instanceof NumberExpression || x instanceof StringExpression || x instanceof CharExpression);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isTrue(AbstractExpression x) {
/*  33 */     return (x.evalLong() != 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractExpression toConst(AbstractExpression exp) {
/*     */     try {
/*  44 */       Object val = exp.evalObject();
/*  45 */       if (val instanceof String) {
/*  46 */         return (AbstractExpression)StringExpression.create(exp, (String)val);
/*     */       }
/*  48 */       if (val instanceof Character) {
/*  49 */         return (AbstractExpression)CharExpression.create(exp, val.toString());
/*     */       }
/*  51 */       return (AbstractExpression)NumberExpression.create(exp, val.toString());
/*  52 */     } catch (Exception e) {
/*  53 */       return exp;
/*     */     } 
/*     */   }
/*     */   
/*     */   public AbstractExpression replace0(WordExpression exp) {
/*  58 */     if (exp instanceof mcheli.eval.eval.exp.VariableExpression) {
/*  59 */       return toConst((AbstractExpression)exp);
/*     */     }
/*  61 */     return (AbstractExpression)exp;
/*     */   }
/*     */   
/*     */   public AbstractExpression replace1(Col1Expression exp) {
/*  65 */     if (exp instanceof mcheli.eval.eval.exp.ParenExpression) {
/*  66 */       return exp.exp;
/*     */     }
/*  68 */     if (exp instanceof mcheli.eval.eval.exp.SignPlusExpression) {
/*  69 */       return exp.exp;
/*     */     }
/*  71 */     if (isConst(exp.exp)) {
/*  72 */       return toConst((AbstractExpression)exp);
/*     */     }
/*  74 */     return (AbstractExpression)exp;
/*     */   }
/*     */   
/*     */   public AbstractExpression replace2(Col2Expression exp) {
/*  78 */     boolean const_l = isConst(exp.expl);
/*  79 */     boolean const_r = isConst(exp.expr);
/*  80 */     if (const_l && const_r) {
/*  81 */       return toConst((AbstractExpression)exp);
/*     */     }
/*     */     
/*  84 */     return (AbstractExpression)exp;
/*     */   }
/*     */   
/*     */   public AbstractExpression replace2(Col2OpeExpression exp) {
/*  88 */     if (exp instanceof mcheli.eval.eval.exp.ArrayExpression) {
/*  89 */       if (isConst(exp.expr)) {
/*  90 */         return toConst((AbstractExpression)exp);
/*     */       }
/*  92 */       return (AbstractExpression)exp;
/*     */     } 
/*  94 */     if (exp instanceof mcheli.eval.eval.exp.FieldExpression) {
/*  95 */       return toConst((AbstractExpression)exp);
/*     */     }
/*     */     
/*  98 */     boolean const_l = isConst(exp.expl);
/*  99 */     if (exp instanceof mcheli.eval.eval.exp.AndExpression) {
/* 100 */       if (const_l) {
/* 101 */         if (isTrue(exp.expl)) {
/* 102 */           return exp.expr;
/*     */         }
/* 104 */         return exp.expl;
/*     */       } 
/*     */       
/* 107 */       return (AbstractExpression)exp;
/*     */     } 
/* 109 */     if (exp instanceof mcheli.eval.eval.exp.OrExpression) {
/* 110 */       if (const_l) {
/* 111 */         if (isTrue(exp.expl)) {
/* 112 */           return exp.expl;
/*     */         }
/* 114 */         return exp.expr;
/*     */       } 
/*     */       
/* 117 */       return (AbstractExpression)exp;
/*     */     } 
/* 119 */     if (exp instanceof mcheli.eval.eval.exp.CommaExpression) {
/* 120 */       if (const_l) {
/* 121 */         return exp.expr;
/*     */       }
/* 123 */       return (AbstractExpression)exp;
/*     */     } 
/*     */     
/* 126 */     return (AbstractExpression)exp;
/*     */   }
/*     */   
/*     */   public AbstractExpression replace3(Col3Expression exp) {
/* 130 */     if (isConst(exp.exp1)) {
/* 131 */       if (isTrue(exp.exp1)) {
/* 132 */         return exp.exp2;
/*     */       }
/* 134 */       return exp.exp3;
/*     */     } 
/*     */     
/* 137 */     return (AbstractExpression)exp;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\OptimizeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */