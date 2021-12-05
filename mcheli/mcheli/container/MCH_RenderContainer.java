/*    */ package mcheli.mcheli.container;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.aircraft.MCH_RenderAircraft;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderContainer
/*    */   extends W_Render
/*    */ {
/* 24 */   public static final Random rand = new Random();
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
/*    */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/* 39 */     if (MCH_RenderAircraft.shouldSkipRender(entity)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 44 */     GL11.glPushMatrix();
/*    */     
/* 46 */     GL11.glEnable(2884);
/*    */     
/* 48 */     GL11.glTranslated(posX, posY - 0.2D, posZ);
/*    */ 
/*    */     
/* 51 */     float yaw = MCH_Lib.smoothRot(entity.field_70177_z, entity.field_70126_B, tickTime);
/* 52 */     float pitch = MCH_Lib.smoothRot(entity.field_70125_A, entity.field_70127_C, tickTime);
/* 53 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/* 54 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*    */ 
/*    */     
/* 57 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*    */ 
/*    */     
/* 60 */     bindTexture("textures/container.png");
/*    */     
/* 62 */     MCH_ModelManager.render("container");
/*    */     
/* 64 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 69 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\container\MCH_RenderContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */