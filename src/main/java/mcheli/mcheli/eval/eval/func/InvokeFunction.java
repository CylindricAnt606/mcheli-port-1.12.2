/*    */ package mcheli.mcheli.eval.eval.func;
/*    */ 
/*    */ import java.lang.reflect.Method;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvokeFunction
/*    */   implements Function
/*    */ {
/*    */   public long evalLong(Object object, String name, Long[] args) throws Throwable {
/* 21 */     if (object == null) {
/* 22 */       return 0L;
/*    */     }
/*    */     
/* 25 */     Object r = callMethod(object, name, (Object[])args);
/* 26 */     return ((Number)r).longValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble(Object object, String name, Double[] args) throws Throwable {
/* 31 */     if (object == null) {
/* 32 */       return 0.0D;
/*    */     }
/*    */     
/* 35 */     Object r = callMethod(object, name, (Object[])args);
/* 36 */     return ((Number)r).doubleValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject(Object object, String name, Object[] args) throws Throwable {
/* 41 */     if (object == null) {
/* 42 */       return null;
/*    */     }
/*    */     
/* 45 */     return callMethod(object, name, args);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Object callMethod(Object obj, String name, Object[] args) throws Exception {
/* 50 */     Class<?> c = obj.getClass();
/* 51 */     Class[] types = new Class[args.length];
/* 52 */     for (int i = 0; i < types.length; i++) {
/* 53 */       types[i] = args[i].getClass();
/*    */     }
/* 55 */     Method m = c.getMethod(name, types);
/* 56 */     return m.invoke(obj, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\func\InvokeFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */