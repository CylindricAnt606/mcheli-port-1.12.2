/*    */ package mcheli.mcheli.flare;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.flare.MCH_ModelFlare;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderFlare
/*    */   extends W_Render
/*    */ {
/* 20 */   protected MCH_ModelFlare model = new MCH_ModelFlare();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float yaw, float partialTickTime) {
/* 31 */     GL11.glPushMatrix();
/*    */     
/* 33 */     GL11.glEnable(2884);
/* 34 */     double x = entity.field_70169_q + entity.field_70159_w * partialTickTime;
/* 35 */     double y = entity.field_70167_r + entity.field_70181_x * partialTickTime;
/* 36 */     double z = entity.field_70166_s + entity.field_70179_y * partialTickTime;
/* 37 */     GL11.glTranslated(posX, posY, posZ);
/* 38 */     GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 39 */     GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
/* 40 */     GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 42 */     GL11.glColor4f(1.0F, 1.0F, 0.5F, 1.0F);
/*    */     
/* 44 */     bindTexture("textures/flare.png");
/* 45 */     this.model.renderModel(0.0D, 0.0D, 0.0625F);
/*    */     
/* 47 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 52 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\flare\MCH_RenderFlare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */