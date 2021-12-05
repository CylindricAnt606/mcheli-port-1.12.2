/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.NumberExpression;
/*    */ import mcheli.eval.eval.exp.OptimizeObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OptimizeLong
/*    */   extends OptimizeObject
/*    */ {
/*    */   protected boolean isTrue(AbstractExpression x) {
/* 13 */     return (x.evalLong() != 0L);
/*    */   }
/*    */   
/*    */   protected AbstractExpression toConst(AbstractExpression exp) {
/*    */     try {
/* 18 */       long val = exp.evalLong();
/* 19 */       return (AbstractExpression)NumberExpression.create(exp, Long.toString(val));
/* 20 */     } catch (Exception e) {
/* 21 */       return exp;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\OptimizeLong.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */