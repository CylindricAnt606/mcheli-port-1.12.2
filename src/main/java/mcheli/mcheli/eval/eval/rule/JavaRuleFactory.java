/*    */ package mcheli.mcheli.eval.eval.rule;
/*    */ 
/*    */ import mcheli.eval.eval.ExpRuleFactory;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.rule.AbstractRule;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
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
/*    */ public class JavaRuleFactory
/*    */   extends ExpRuleFactory
/*    */ {
/*    */   private static mcheli.eval.eval.rule.JavaRuleFactory me;
/*    */   
/*    */   public static ExpRuleFactory getInstance() {
/* 29 */     if (me == null) {
/* 30 */       me = new mcheli.eval.eval.rule.JavaRuleFactory();
/*    */     }
/* 32 */     return me;
/*    */   }
/*    */   
/*    */   protected AbstractRule createCommaRule(ShareRuleValue share) {
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   protected AbstractRule createPowerRule(ShareRuleValue share) {
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   protected AbstractExpression createLetPowerExpression() {
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\rule\JavaRuleFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */