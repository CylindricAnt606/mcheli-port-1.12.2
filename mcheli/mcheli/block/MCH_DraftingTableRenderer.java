/*    */ package mcheli.mcheli.block;
/*    */ 
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_DraftingTableRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   public void func_147500_a(TileEntity tile, double posX, double posY, double posZ, float var8) {
/* 19 */     GL11.glPushMatrix();
/*    */     
/* 21 */     GL11.glEnable(2884);
/*    */     
/* 23 */     GL11.glTranslated(posX + 0.5D, posY, posZ + 0.5D);
/*    */     
/* 25 */     float yaw = (-tile.func_145832_p() * 45 + 180);
/* 26 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 27 */     RenderHelper.func_74519_b();
/*    */ 
/*    */     
/* 30 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*    */     
/* 32 */     GL11.glEnable(3042);
/* 33 */     int srcBlend = GL11.glGetInteger(3041);
/* 34 */     int dstBlend = GL11.glGetInteger(3040);
/* 35 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 37 */     if (MCH_Config.SmoothShading.prmBool)
/*    */     {
/* 39 */       GL11.glShadeModel(7425);
/*    */     }
/*    */ 
/*    */     
/* 43 */     W_McClient.MOD_bindTexture("textures/blocks/drafting_table.png");
/* 44 */     MCH_ModelManager.render("blocks", "drafting_table");
/*    */ 
/*    */     
/* 47 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 48 */     GL11.glDisable(3042);
/* 49 */     GL11.glShadeModel(7424);
/*    */     
/* 51 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */