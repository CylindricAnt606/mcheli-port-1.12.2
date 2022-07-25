/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.wrapper.W_Reflection;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.shader.ShaderGroup;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_EntityRenderer
/*    */ {
/*    */   public static void setItemRenderer(Minecraft mc, ItemRenderer ir) {
/* 26 */     W_Reflection.setItemRenderer(ir);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isShaderSupport() {
/* 34 */     if (OpenGlHelper.field_148824_g) if (!MCH_Config.DisableShader.prmBool);  return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void activateShader(String n) {
/* 41 */     activateShader(new ResourceLocation("mcheli", "shaders/post/" + n + ".json"));
/*    */   }
/*    */   
/*    */   public static void activateShader(ResourceLocation r) {
/* 45 */     Minecraft mc = Minecraft.func_71410_x();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 51 */       mc.field_71460_t.field_147707_d = new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), r);
/*    */ 
/*    */       
/* 54 */       mc.field_71460_t.field_147707_d.func_148026_a(mc.field_71443_c, mc.field_71440_d);
/*    */     }
/* 56 */     catch (IOException ioexception) {
/*    */       
/* 58 */       ioexception.printStackTrace();
/*    */     }
/* 60 */     catch (JsonSyntaxException jsonsyntaxexception) {
/*    */       
/* 62 */       MCH_Lib.Log("Failed to load shader: " + r, new Object[0]);
/* 63 */       jsonsyntaxexception.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void deactivateShader() {
/* 73 */     (Minecraft.func_71410_x()).field_71460_t.func_147703_b();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void renderEntityWithPosYaw(RenderManager rm, Entity par1Entity, double par2, double par4, double par6, float par8, float par9, boolean b) {
/* 82 */     rm.func_147939_a(par1Entity, par2, par4, par6, par8, par9, b);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_EntityRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */