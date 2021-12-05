/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.MCH_Queue;
/*    */ 
/*    */ public class MCH_LowPassFilterFloat {
/*    */   private MCH_Queue<Float> filter;
/*    */   
/*    */   public MCH_LowPassFilterFloat(int filterLength) {
/*  9 */     this.filter = new MCH_Queue(filterLength, Float.valueOf(0.0F));
/*    */   }
/*    */   
/* 12 */   public void clear() { this.filter.clear(Float.valueOf(0.0F)); } public void put(float t) {
/* 13 */     this.filter.put(Float.valueOf(t));
/*    */   }
/*    */   public float getAvg() {
/* 16 */     float f = 0.0F;
/*    */     
/* 18 */     for (int i = 0; i < this.filter.size(); i++)
/*    */     {
/* 20 */       f += ((Float)this.filter.get(i)).floatValue();
/*    */     }
/*    */ 
/*    */     
/* 24 */     return f / this.filter.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_LowPassFilterFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */