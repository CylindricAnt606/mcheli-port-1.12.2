/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.CharExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.NumberExpression;
/*    */ import mcheli.eval.eval.exp.StringExpression;
/*    */ import mcheli.eval.eval.exp.VariableExpression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
/*    */ 
/*    */ public class PrimaryRule
/*    */   extends AbstractRule {
/*    */   public PrimaryRule(ShareRuleValue share) {
/* 17 */     super(share); } public final AbstractExpression parse(Lex lex) { AbstractExpression n; AbstractExpression w;
/*    */     AbstractExpression s;
/*    */     AbstractExpression c;
/*    */     String ope;
/*    */     int pos;
/* 22 */     switch (lex.getType()) {
/*    */       case 2147483633:
/* 24 */         n = NumberExpression.create(lex, this.prio);
/* 25 */         lex.next();
/* 26 */         return n;
/*    */       case 2147483632:
/* 28 */         w = VariableExpression.create(lex, this.prio);
/* 29 */         lex.next();
/* 30 */         return w;
/*    */       case 2147483635:
/* 32 */         s = StringExpression.create(lex, this.prio);
/* 33 */         lex.next();
/* 34 */         return s;
/*    */       case 2147483636:
/* 36 */         c = CharExpression.create(lex, this.prio);
/* 37 */         lex.next();
/* 38 */         return c;
/*    */       case 2147483634:
/* 40 */         ope = lex.getOperator();
/* 41 */         pos = lex.getPos();
/* 42 */         if (isMyOperator(ope)) {
/* 43 */           if (ope.equals(this.share.paren.getOperator())) {
/* 44 */             return parseParen(lex, ope, pos);
/*    */           }
/* 46 */           return Col1Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, parse(lex.next()));
/*    */         } 
/*    */ 
/*    */         
/* 50 */         throw new EvalException(1002, lex);
/*    */       case 2147483647:
/* 52 */         throw new EvalException(1004, lex);
/*    */     } 
/* 54 */     throw new EvalException(1003, lex); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractExpression parseParen(Lex lex, String ope, int pos) {
/* 68 */     AbstractExpression s = this.share.topRule.parse(lex.next());
/* 69 */     if (!lex.isOperator(this.share.paren.getEndOperator()))
/*    */     {
/* 71 */       throw new EvalException(1001, new String[] { this.share.paren.getEndOperator() }, lex);
/*    */     }
/*    */     
/* 74 */     lex.next();
/* 75 */     return Col1Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\PrimaryRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */