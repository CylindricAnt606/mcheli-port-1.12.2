/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.client.audio.MovingSound;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Sound
/*    */   extends MovingSound
/*    */ {
/*    */   protected W_Sound(ResourceLocation r, float volume, float pitch, double x, double y, double z) {
/* 15 */     super(r);
/* 16 */     setVolumeAndPitch(volume, pitch);
/* 17 */     setPosition(x, y, z);
/*    */   }
/*    */   
/*    */   protected W_Sound(ResourceLocation r, float volume, float pitch) {
/* 21 */     super(r);
/* 22 */     setVolumeAndPitch(volume, pitch);
/* 23 */     Entity entity = W_McClient.getRenderEntity();
/* 24 */     if (entity != null)
/*    */     {
/* 26 */       setPosition(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRepeat(boolean b) {
/* 32 */     this.field_147659_g = b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSoundParam(Entity e, float v, float p) {
/* 37 */     setPosition(e);
/* 38 */     setVolumeAndPitch(v, p);
/*    */   }
/* 40 */   public void setVolumeAndPitch(float v, float p) { setVolume(v); setPitch(p); }
/* 41 */   public void setVolume(float v) { this.field_147662_b = v; } public void setPitch(float p) {
/* 42 */     this.field_147663_c = p;
/*    */   }
/*    */   
/*    */   public void setPosition(double x, double y, double z) {
/* 46 */     this.field_147660_d = (float)x;
/* 47 */     this.field_147661_e = (float)y;
/* 48 */     this.field_147658_f = (float)z;
/*    */   }
/*    */   
/*    */   public void setPosition(Entity e) {
/* 52 */     setPosition(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*    */   }
/*    */   
/*    */   public void func_73660_a() {}
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Sound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */