/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ public class W_MovingObjectPosition
/*    */ {
/*    */   public static boolean isHitTypeEntity(MovingObjectPosition m) {
/* 10 */     if (m == null) return false;
/*    */     
/* 12 */     return (m.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isHitTypeTile(MovingObjectPosition m) {
/* 18 */     if (m == null) return false;
/*    */     
/* 20 */     return (m.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK);
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
/*    */   public static MovingObjectPosition newMOP(int p1, int p2, int p3, int p4, Vec3 p5, boolean p6) {
/* 34 */     return new MovingObjectPosition(p1, p2, p3, p4, p5, p6);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_MovingObjectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */