/*    */ package mcheli.mcheli.tool;
/*    */ 
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.tool.MCH_ItemWrench;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_ItemRenderWrench
/*    */   implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/* 17 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/* 24 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/*    */     int useFrame;
/* 30 */     GL11.glPushMatrix();
/*    */     
/* 32 */     W_McClient.MOD_bindTexture("textures/wrench.png");
/*    */     
/* 34 */     float size = 1.0F;
/*    */     
/* 36 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*    */       
/*    */       case 1:
/* 39 */         size = 2.2F;
/* 40 */         GL11.glScalef(size, size, size);
/* 41 */         GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
/* 42 */         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
/* 43 */         GL11.glTranslatef(0.1F, 0.5F, -0.1F);
/*    */         break;
/*    */       case 2:
/* 46 */         useFrame = MCH_ItemWrench.getUseAnimCount(item) - 8;
/* 47 */         if (useFrame < 0) useFrame = -useFrame; 
/* 48 */         size = 2.2F;
/*    */         
/* 50 */         if (data.length >= 2 && data[1] instanceof EntityPlayer) {
/*    */           
/* 52 */           EntityPlayer player = (EntityPlayer)data[1];
/*    */           
/* 54 */           if (player.func_71052_bv() > 0) {
/*    */             
/* 56 */             float x = 0.8567F;
/* 57 */             float y = -0.0298F;
/* 58 */             float z = 0.0F;
/* 59 */             GL11.glTranslatef(-x, -y, -z);
/* 60 */             GL11.glRotatef((useFrame + 20), 1.0F, 0.0F, 0.0F);
/* 61 */             GL11.glTranslatef(x, y, z);
/*    */           } 
/*    */         } 
/*    */         
/* 65 */         GL11.glScalef(size, size, size);
/* 66 */         GL11.glRotatef(-200.0F, 0.0F, 1.0F, 0.0F);
/* 67 */         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
/* 68 */         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 69 */         GL11.glTranslatef(-0.2F, 0.5F, -0.1F);
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 75 */     MCH_ModelManager.render("wrench");
/* 76 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\MCH_ItemRenderWrench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */