/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignRule
/*    */   extends AbstractRule
/*    */ {
/*    */   public SignRule(ShareRuleValue share) {
/* 18 */     super(share);
/*    */   }
/*    */   public AbstractExpression parse(Lex lex) {
/*    */     String ope;
/* 22 */     switch (lex.getType()) {
/*    */       case 2147483634:
/* 24 */         ope = lex.getOperator();
/* 25 */         if (isMyOperator(ope)) {
/* 26 */           int pos = lex.getPos();
/* 27 */           return Col1Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, parse(lex.next()));
/*    */         } 
/*    */ 
/*    */         
/* 31 */         return this.nextRule.parse(lex);
/*    */     } 
/* 33 */     return this.nextRule.parse(lex);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\SignRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */