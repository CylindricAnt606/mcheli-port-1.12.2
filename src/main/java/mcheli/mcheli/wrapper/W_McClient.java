/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import mcheli.wrapper.W_MOD;
/*    */ import mcheli.wrapper.W_Sound;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_McClient
/*    */ {
/*    */   public static void DEF_playSoundFX(String name, float volume, float pitch) {
/* 18 */     Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)new W_Sound(new ResourceLocation(name), volume, pitch));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void MOD_playSoundFX(String name, float volume, float pitch) {
/* 28 */     DEF_playSoundFX(W_MOD.DOMAIN + ":" + name, volume, pitch);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addSound(String name) {
/* 38 */     Minecraft mc = Minecraft.func_71410_x();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void DEF_bindTexture(String tex) {
/* 49 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(new ResourceLocation(tex));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void MOD_bindTexture(String tex) {
/* 58 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(new ResourceLocation(W_MOD.DOMAIN, tex));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isGamePaused() {
/* 67 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 69 */     return mc.func_147113_T();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Entity getRenderEntity() {
/* 76 */     return (Entity)(Minecraft.func_71410_x()).field_71451_h;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setRenderEntity(EntityLivingBase entity) {
/* 81 */     (Minecraft.func_71410_x()).field_71451_h = entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_McClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */