/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class MCH_DamageFactor
/*    */ {
/*  9 */   private HashMap<Class, Float> map = new HashMap<Class<?>, Float>();
/*    */ 
/*    */   
/*    */   public void clear() {
/* 13 */     this.map.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(Class c, float value) {
/* 18 */     this.map.put(c, Float.valueOf(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDamageFactor(Class c) {
/* 23 */     if (this.map.containsKey(c))
/*    */     {
/* 25 */       return ((Float)this.map.get(c)).floatValue();
/*    */     }
/* 27 */     return 1.0F;
/*    */   }
/*    */   
/*    */   public float getDamageFactor(Entity e) {
/* 31 */     return (e != null) ? getDamageFactor(e.getClass()) : 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_DamageFactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */