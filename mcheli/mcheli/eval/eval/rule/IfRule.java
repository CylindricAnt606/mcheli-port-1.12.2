/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col3Expression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IfRule
/*    */   extends AbstractRule
/*    */ {
/*    */   public AbstractExpression cond;
/*    */   
/*    */   public IfRule(ShareRuleValue share) {
/* 20 */     super(share);
/*    */   } protected AbstractExpression parse(Lex lex) {
/*    */     String ope;
/*    */     int pos;
/* 24 */     AbstractExpression x = this.nextRule.parse(lex);
/* 25 */     switch (lex.getType()) {
/*    */       case 2147483634:
/* 27 */         ope = lex.getOperator();
/* 28 */         pos = lex.getPos();
/* 29 */         if (isMyOperator(ope) && 
/* 30 */           lex.isOperator(this.cond.getOperator())) {
/* 31 */           x = parseCond(lex, x, ope, pos);
/*    */         }
/*    */         
/* 34 */         return x;
/*    */     } 
/* 36 */     return x;
/*    */   }
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
/*    */   protected AbstractExpression parseCond(Lex lex, AbstractExpression x, String ope, int pos) {
/* 51 */     AbstractExpression y = parse(lex.next());
/* 52 */     if (!lex.isOperator(this.cond.getEndOperator())) {
/* 53 */       throw new EvalException(1001, new String[] { this.cond.getEndOperator() }, lex);
/*    */     }
/*    */     
/* 56 */     AbstractExpression z = parse(lex.next());
/* 57 */     x = Col3Expression.create(newExpression(ope, lex.getShare()), lex.getString(), pos, x, y, z);
/*    */     
/* 59 */     return x;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\IfRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */