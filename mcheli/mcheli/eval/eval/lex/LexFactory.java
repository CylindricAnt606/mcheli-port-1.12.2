/*    */ package mcheli.mcheli.eval.eval.lex;
/*    */ 
/*    */ import java.util.List;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ import mcheli.eval.eval.lex.Lex;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LexFactory
/*    */ {
/*    */   public Lex create(String str, List[] opeList, ShareRuleValue share, ShareExpValue exp) {
/* 32 */     return new Lex(str, opeList, share.paren, exp);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\lex\LexFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */