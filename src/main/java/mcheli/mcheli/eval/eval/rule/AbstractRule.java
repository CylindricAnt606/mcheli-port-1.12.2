/*     */ package mcheli.mcheli.eval.eval.rule;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ import mcheli.eval.eval.lex.Lex;
/*     */ import mcheli.eval.eval.rule.ShareRuleValue;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractRule
/*     */ {
/*     */   public mcheli.eval.eval.rule.AbstractRule nextRule;
/*     */   protected ShareRuleValue share;
/*     */   private final Map opes;
/*     */   public int prio;
/*     */   
/*     */   public AbstractRule(ShareRuleValue share) {
/*  61 */     this.opes = new HashMap<Object, Object>();
/*     */     this.share = share;
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
/*     */   public final void addOperator(String ope, AbstractExpression exp) {
/*  75 */     this.opes.put(ope, exp);
/*     */     
/*  77 */     addLexOperator(ope);
/*     */   } public final void addExpression(AbstractExpression exp) {
/*     */     if (exp == null)
/*     */       return; 
/*     */     String ope = exp.getOperator();
/*     */     addOperator(ope, exp);
/*     */     addLexOperator(exp.getEndOperator());
/*     */     if (exp instanceof mcheli.eval.eval.exp.ParenExpression)
/*     */       this.share.paren = exp; 
/*     */   } public final String[] getOperators() {
/*  87 */     List list = new ArrayList();
/*  88 */     for (Iterator i = this.opes.keySet().iterator(); i.hasNext();) {
/*  89 */       list.add(i.next());
/*     */     }
/*  91 */     return (String[])list.toArray((Object[])new String[list.size()]);
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
/*     */   public final void addLexOperator(String ope) {
/* 107 */     if (ope == null)
/*     */       return; 
/* 109 */     int n = ope.length() - 1;
/* 110 */     if (this.share.opeList[n] == null)
/* 111 */       this.share.opeList[n] = new ArrayList(); 
/* 112 */     this.share.opeList[n].add(ope);
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
/*     */   protected final boolean isMyOperator(String ope) {
/* 127 */     return this.opes.containsKey(ope);
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
/*     */   protected final AbstractExpression newExpression(String ope, ShareExpValue share) {
/*     */     try {
/* 144 */       AbstractExpression org = (AbstractExpression)this.opes.get(ope);
/* 145 */       AbstractExpression n = org.dup(share);
/* 146 */       n.setPriority(this.prio);
/* 147 */       n.share = share;
/* 148 */       return n;
/* 149 */     } catch (RuntimeException e) {
/* 150 */       throw e;
/* 151 */     } catch (Exception e) {
/* 152 */       throw new RuntimeException(e);
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
/*     */ 
/*     */   
/*     */   public final void initPriority(int prio) {
/* 167 */     this.prio = prio;
/*     */     
/* 169 */     if (this.nextRule != null)
/* 170 */       this.nextRule.initPriority(prio + 1); 
/*     */   }
/*     */   
/*     */   protected abstract AbstractExpression parse(Lex paramLex);
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\AbstractRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */