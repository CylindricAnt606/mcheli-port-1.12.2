/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import java.util.List;
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.Expression;
/*    */ import mcheli.eval.eval.Rule;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.eval.lex.LexFactory;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
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
/*    */ public class ShareRuleValue
/*    */   extends Rule
/*    */ {
/*    */   public AbstractRule topRule;
/*    */   public AbstractRule funcArgRule;
/*    */   public LexFactory lexFactory;
/* 41 */   protected List[] opeList = new List[4];
/*    */   
/*    */   public AbstractExpression paren;
/*    */ 
/*    */   
/*    */   public Expression parse(String str) {
/* 47 */     if (str == null)
/* 48 */       return null; 
/* 49 */     if (str.trim().length() <= 0) {
/* 50 */       return (Expression)new EmptyExpression(this);
/*    */     }
/*    */     
/* 53 */     ShareExpValue exp = new ShareExpValue();
/* 54 */     AbstractExpression x = parse(str, exp);
/*    */     
/* 56 */     exp.setAbstractExpression(x);
/* 57 */     return (Expression)exp;
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
/*    */   public AbstractExpression parse(String str, ShareExpValue exp) {
/* 71 */     if (str == null) {
/* 72 */       return null;
/*    */     }
/* 74 */     Lex lex = this.lexFactory.create(str, this.opeList, this, exp);
/* 75 */     lex.check();
/*    */     
/* 77 */     AbstractExpression x = this.topRule.parse(lex);
/* 78 */     if (lex.getType() != Integer.MAX_VALUE) {
/* 79 */       throw new EvalException(1005, lex);
/*    */     }
/* 81 */     return x;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\ShareRuleValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */