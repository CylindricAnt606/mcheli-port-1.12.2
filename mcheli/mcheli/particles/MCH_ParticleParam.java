/*    */ package mcheli.mcheli.particles;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_ParticleParam
/*    */ {
/*    */   public final World world;
/*    */   public final String name;
/*    */   public double posX;
/*    */   public double posY;
/*    */   public double posZ;
/* 12 */   public double motionX = 0.0D;
/* 13 */   public double motionY = 0.0D;
/* 14 */   public double motionZ = 0.0D;
/* 15 */   public float size = 1.0F;
/* 16 */   public float a = 1.0F;
/* 17 */   public float r = 1.0F;
/* 18 */   public float g = 1.0F;
/* 19 */   public float b = 1.0F;
/*    */   public boolean isEffectWind = false;
/* 21 */   public int age = 0;
/*    */   public boolean diffusible = false;
/*    */   public boolean toWhite = false;
/* 24 */   public float gravity = 0.0F;
/* 25 */   public float motionYUpAge = 2.0F;
/*    */ 
/*    */   
/*    */   public MCH_ParticleParam(World w, String name, double x, double y, double z) {
/* 29 */     this.world = w;
/* 30 */     this.name = name;
/* 31 */     this.posX = x;
/* 32 */     this.posY = y;
/* 33 */     this.posZ = z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_ParticleParam(World w, String name, double x, double y, double z, double mx, double my, double mz, float size) {
/* 40 */     this(w, name, x, y, z);
/* 41 */     this.motionX = mx;
/* 42 */     this.motionY = my;
/* 43 */     this.motionZ = mz;
/* 44 */     this.size = size;
/*    */   }
/*    */   
/*    */   public void setColor(float a, float r, float g, float b) {
/* 48 */     this.a = a;
/* 49 */     this.r = r;
/* 50 */     this.g = g;
/* 51 */     this.b = b;
/*    */   }
/*    */   
/*    */   public void setMotion(double x, double y, double z) {
/* 55 */     this.motionX = x;
/* 56 */     this.motionY = y;
/* 57 */     this.motionZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_ParticleParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */