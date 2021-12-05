/*    */ package mcheli.mcheli.debug;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.debug.MCH_ModelTest;
/*    */ import mcheli.wrapper.W_Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderTest
/*    */   extends W_Render
/*    */ {
/*    */   protected MCH_ModelTest model;
/*    */   private float offsetX;
/*    */   private float offsetY;
/*    */   private float offsetZ;
/*    */   private String textureName;
/*    */   
/*    */   public MCH_RenderTest(float x, float y, float z, String texture_name) {
/* 25 */     this.offsetX = x;
/* 26 */     this.offsetY = y;
/* 27 */     this.offsetZ = z;
/* 28 */     this.textureName = texture_name;
/* 29 */     this.model = new MCH_ModelTest();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76986_a(Entity e, double posX, double posY, double posZ, float par8, float par9) {
/*    */     float prevYaw;
/* 40 */     if (!MCH_Config.TestMode.prmBool)
/*    */       return; 
/* 42 */     GL11.glPushMatrix();
/* 43 */     GL11.glTranslated(posX + this.offsetX, posY + this.offsetY, posZ + this.offsetZ);
/*    */     
/* 45 */     GL11.glScalef(e.field_70130_N, e.field_70131_O, e.field_70130_N);
/*    */     
/* 47 */     GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
/*    */ 
/*    */ 
/*    */     
/* 51 */     if (e.field_70177_z - e.field_70126_B < -180.0F) { prevYaw = e.field_70126_B - 360.0F; }
/* 52 */     else if (e.field_70126_B - e.field_70177_z < -180.0F) { prevYaw = e.field_70126_B + 360.0F; }
/* 53 */     else { prevYaw = e.field_70126_B; }
/*    */     
/* 55 */     float yaw = -(prevYaw + (e.field_70177_z - prevYaw) * par9) - 180.0F;
/* 56 */     float pitch = -(e.field_70127_C + (e.field_70125_A - e.field_70127_C) * par9);
/* 57 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 58 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*    */     
/* 60 */     bindTexture("textures/" + this.textureName + ".png");
/* 61 */     this.model.renderModel(0.0D, 0.0D, 0.1F);
/* 62 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 67 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\debug\MCH_RenderTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */