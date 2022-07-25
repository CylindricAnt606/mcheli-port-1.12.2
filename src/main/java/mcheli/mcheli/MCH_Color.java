/*    */ package mcheli.mcheli;
/*    */ 
/*    */ 
/*    */ public class MCH_Color
/*    */ {
/*    */   public float a;
/*    */   public float r;
/*    */   public float g;
/*    */   public float b;
/*    */   
/*    */   public MCH_Color(float aa, float rr, float gg, float bb) {
/* 12 */     this.a = round(aa);
/* 13 */     this.r = round(rr);
/* 14 */     this.g = round(gg);
/* 15 */     this.b = round(bb);
/*    */   }
/*    */   
/*    */   public MCH_Color(float rr, float gg, float bb) {
/* 19 */     this(1.0F, rr, gg, bb);
/*    */   }
/*    */   
/*    */   public MCH_Color() {
/* 23 */     this(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float round(float f) {
/* 28 */     return (f < 0.0F) ? 0.0F : ((f > 1.0F) ? 1.0F : f);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Color.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */