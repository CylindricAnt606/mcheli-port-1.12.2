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
/*    */ 
/*    */ public class MathFunction
/*    */   implements Function
/*    */ {
/*    */   public long evalLong(Object object, String name, Long[] args) throws Throwable {
/* 22 */     Class[] types = new Class[args.length];
/* 23 */     for (int i = 0; i < types.length; i++) {
/* 24 */       types[i] = long.class;
/*    */     }
/*    */     
/* 27 */     Method m = Math.class.getMethod(name, types);
/* 28 */     Object ret = m.invoke(null, (Object[])args);
/*    */     
/* 30 */     return ((Long)ret).longValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble(Object object, String name, Double[] args) throws Throwable {
/* 35 */     Class[] types = new Class[args.length];
/* 36 */     for (int i = 0; i < types.length; i++) {
/* 37 */       types[i] = double.class;
/*    */     }
/*    */     
/* 40 */     Method m = Math.class.getMethod(name, types);
/* 41 */     Object ret = m.invoke(null, (Object[])args);
/*    */     
/* 43 */     return ((Double)ret).doubleValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject(Object object, String name, Object[] args) throws Throwable {
/* 48 */     Class[] types = new Class[args.length];
/* 49 */     for (int i = 0; i < types.length; i++) {
/* 50 */       Class<?> c = args[i].getClass();
/* 51 */       if (Double.class.isAssignableFrom(c)) {
/* 52 */         c = double.class;
/* 53 */       } else if (Float.class.isAssignableFrom(c)) {
/* 54 */         c = float.class;
/* 55 */       } else if (Integer.class.isAssignableFrom(c)) {
/* 56 */         c = int.class;
/* 57 */       } else if (Number.class.isAssignableFrom(c)) {
/* 58 */         c = long.class;
/*    */       } 
/* 60 */       types[i] = c;
/*    */     } 
/*    */     
/* 63 */     Method m = Math.class.getMethod(name, types);
/* 64 */     return m.invoke(null, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\func\MathFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */