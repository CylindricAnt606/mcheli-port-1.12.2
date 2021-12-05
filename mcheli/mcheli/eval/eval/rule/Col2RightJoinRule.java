/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Col2RightJoinRule
/*    */   extends AbstractRule
/*    */ {
/*    */   public Col2RightJoinRule(ShareRuleValue share) {
/* 15 */     super(share);
/*    */   }
/*    */   protected AbstractExpression parse(Lex lex) {
/*    */     String ope;
/* 19 */     AbstractExpression x = this.nextRule.parse(lex);
/* 20 */     switch (lex.getType()) {
/*    */       case 2147483634:
/* 22 */         ope = lex.getOperator();
/* 23 */         if (isMyOperator(ope)) {
/* 24 */           int pos = lex.getPos();
/* 25 */           AbstractExpression y = parse(lex.next());
/* 26 */           x = Col2Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x, y);
/*    */         } 
/*    */         
/* 29 */         return x;
/*    */     } 
/* 31 */     return x;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\Col2RightJoinRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */