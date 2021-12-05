/*    */ package mcheli.mcheli.eval.eval.func;
/*    */ 
/*    */ import mcheli.eval.eval.func.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VoidFunction
/*    */   implements Function
/*    */ {
/*    */   public long evalLong(Object object, String name, Long[] args) throws Throwable {
/* 17 */     System.out.println(object + "." + name + "関数が呼ばれた(long)");
/* 18 */     for (int i = 0; i < args.length; i++) {
/* 19 */       System.out.println("arg[" + i + "] " + args[i]);
/*    */     }
/*    */     
/* 22 */     return 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble(Object object, String name, Double[] args) throws Throwable {
/* 27 */     System.out.println(object + "." + name + "関数が呼ばれた(double)");
/* 28 */     for (int i = 0; i < args.length; i++) {
/* 29 */       System.out.println("arg[" + i + "] " + args[i]);
/*    */     }
/*    */     
/* 32 */     return 0.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject(Object object, String name, Object[] args) throws Throwable {
/* 37 */     System.out.println(object + "." + name + "関数が呼ばれた(Object)");
/* 38 */     for (int i = 0; i < args.length; i++) {
/* 39 */       System.out.println("arg[" + i + "] " + args[i]);
/*    */     }
/*    */     
/* 42 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\func\VoidFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */