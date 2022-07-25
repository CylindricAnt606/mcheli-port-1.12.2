/*     */ package mcheli.mcheli.eval.eval.rule;
/*     */ 
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.Col1Expression;
/*     */ import mcheli.eval.eval.exp.Col2Expression;
/*     */ import mcheli.eval.eval.exp.FunctionExpression;
/*     */ import mcheli.eval.eval.lex.Lex;
/*     */ import mcheli.eval.eval.rule.AbstractRule;
/*     */ import mcheli.eval.eval.rule.ShareRuleValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Col1AfterRule
/*     */   extends AbstractRule
/*     */ {
/*     */   public AbstractExpression func;
/*     */   public AbstractExpression array;
/*     */   public AbstractExpression field;
/*     */   
/*     */   public Col1AfterRule(ShareRuleValue share) {
/*  27 */     super(share);
/*     */   }
/*     */   
/*     */   public AbstractExpression parse(Lex lex) {
/*  31 */     AbstractExpression x = this.nextRule.parse(lex); while (true) {
/*     */       String ope; int pos;
/*  33 */       switch (lex.getType()) {
/*     */         case 2147483634:
/*  35 */           ope = lex.getOperator();
/*  36 */           pos = lex.getPos();
/*  37 */           if (isMyOperator(ope)) {
/*  38 */             if (lex.isOperator(this.func.getOperator())) {
/*  39 */               x = parseFunc(lex, x);
/*     */               continue;
/*     */             } 
/*  42 */             if (lex.isOperator(this.array.getOperator())) {
/*  43 */               x = parseArray(lex, x, ope, pos);
/*     */               continue;
/*     */             } 
/*  46 */             if (lex.isOperator(this.field.getOperator())) {
/*  47 */               x = parseField(lex, x, ope, pos);
/*     */               continue;
/*     */             } 
/*  50 */             x = Col1Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x);
/*     */ 
/*     */             
/*  53 */             lex.next();
/*     */             continue;
/*     */           } 
/*  56 */           return x;
/*     */       }  break;
/*  58 */     }  return x;
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
/*     */   protected AbstractExpression parseFunc(Lex lex, AbstractExpression x) {
/*  71 */     AbstractExpression a = null;
/*  72 */     lex.next();
/*  73 */     if (!lex.isOperator(this.func.getEndOperator())) {
/*  74 */       a = this.share.funcArgRule.parse(lex);
/*  75 */       if (!lex.isOperator(this.func.getEndOperator())) {
/*  76 */         throw new EvalException(1001, new String[] { this.func.getEndOperator() }, lex);
/*     */       }
/*     */     } 
/*     */     
/*  80 */     lex.next();
/*  81 */     x = FunctionExpression.create(x, a, this.prio, lex.getShare());
/*  82 */     return x;
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
/*     */   protected AbstractExpression parseArray(Lex lex, AbstractExpression x, String ope, int pos) {
/*  96 */     AbstractExpression y = this.share.topRule.parse(lex.next());
/*  97 */     if (!lex.isOperator(this.array.getEndOperator())) {
/*  98 */       throw new EvalException(1001, new String[] { this.array.getEndOperator() }, lex);
/*     */     }
/*     */     
/* 101 */     lex.next();
/* 102 */     x = Col2Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x, y);
/*     */     
/* 104 */     return x;
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
/*     */   protected AbstractExpression parseField(Lex lex, AbstractExpression x, String ope, int pos) {
/* 118 */     AbstractExpression y = this.nextRule.parse(lex.next());
/* 119 */     x = Col2Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x, y);
/*     */     
/* 121 */     return x;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\Col1AfterRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */