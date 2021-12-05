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
/*    */ public class Col2Rule
/*    */   extends AbstractRule
/*    */ {
/*    */   public Col2Rule(ShareRuleValue share) {
/* 15 */     super(share);
/*    */   }
/*    */   
/*    */   protected AbstractExpression parse(Lex lex) {
/* 19 */     AbstractExpression x = this.nextRule.parse(lex); while (true) {
/*    */       String ope;
/* 21 */       switch (lex.getType()) {
/*    */         case 2147483634:
/* 23 */           ope = lex.getOperator();
/* 24 */           if (isMyOperator(ope)) {
/* 25 */             int pos = lex.getPos();
/* 26 */             AbstractExpression y = this.nextRule.parse(lex.next());
/* 27 */             x = Col2Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x, y);
/*    */             
/*    */             continue;
/*    */           } 
/*    */           
/* 32 */           return x;
/*    */       }  break;
/* 34 */     }  return x;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\Col2Rule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */