/*    */ package mcheli.mcheli.throwable;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.throwable.MCH_EntityThrowable;
/*    */ import mcheli.throwable.MCH_ThrowableInfo;
/*    */ import mcheli.wrapper.W_Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
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
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderThrowable
/*    */   extends W_Render
/*    */ {
/*    */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/* 32 */     MCH_EntityThrowable throwable = (MCH_EntityThrowable)entity;
/* 33 */     MCH_ThrowableInfo info = throwable.getInfo();
/* 34 */     if (info == null) {
/*    */       return;
/*    */     }
/* 37 */     GL11.glPushMatrix();
/*    */     
/* 39 */     GL11.glTranslated(posX, posY, posZ);
/*    */ 
/*    */     
/* 42 */     GL11.glRotatef(entity.field_70177_z, 0.0F, -1.0F, 0.0F);
/* 43 */     GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
/*    */     
/* 45 */     setCommonRenderParam(true, entity.func_70070_b(tickTime));
/*    */     
/* 47 */     if (info.model != null) {
/*    */       
/* 49 */       bindTexture("textures/throwable/" + info.name + ".png");
/*    */       
/* 51 */       info.model.renderAll();
/*    */     } 
/*    */     
/* 54 */     restoreCommonRenderParam();
/*    */     
/* 56 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 61 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_RenderThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */