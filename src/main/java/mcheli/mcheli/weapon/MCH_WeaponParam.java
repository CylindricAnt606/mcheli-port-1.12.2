/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
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
/*    */ public class MCH_WeaponParam
/*    */ {
/* 21 */   public Entity entity = null;
/* 22 */   public Entity user = null;
/* 23 */   public double posX = 0.0D;
/* 24 */   public double posY = 0.0D;
/* 25 */   public double posZ = 0.0D;
/* 26 */   public float rotYaw = 0.0F;
/* 27 */   public float rotPitch = 0.0F;
/* 28 */   public float rotRoll = 0.0F;
/* 29 */   public int option1 = 0;
/* 30 */   public int option2 = 0;
/*    */   public boolean isInfinity = false;
/*    */   public boolean isTurret = false;
/*    */   public boolean result;
/*    */   public boolean reload;
/*    */   
/*    */   public void setPosAndRot(double x, double y, double z, float yaw, float pitch) {
/* 37 */     setPosition(x, y, z);
/* 38 */     setRotation(yaw, pitch);
/*    */   }
/*    */   
/*    */   public void setPosition(double x, double y, double z) {
/* 42 */     this.posX = x;
/* 43 */     this.posY = y;
/* 44 */     this.posZ = z;
/*    */   }
/*    */   
/*    */   public void setRotation(float y, float p) {
/* 48 */     this.rotYaw = y;
/* 49 */     this.rotPitch = p;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */