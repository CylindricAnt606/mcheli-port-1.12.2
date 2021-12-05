/*    */ package mcheli.mcheli.gltd;
/*    */ 
/*    */ import mcheli.gltd.MCH_RenderGLTD;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_ItemGLTDRender
/*    */   implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/* 16 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.ENTITY);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/* 26 */     return (type == IItemRenderer.ItemRenderType.ENTITY);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/* 32 */     GL11.glPushMatrix();
/*    */     
/* 34 */     GL11.glEnable(2884);
/*    */     
/* 36 */     W_McClient.MOD_bindTexture("textures/gltd.png");
/*    */     
/* 38 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*    */       
/*    */       case 1:
/* 41 */         GL11.glEnable(32826);
/* 42 */         GL11.glEnable(2903);
/* 43 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 44 */         MCH_RenderGLTD.model.renderAll();
/* 45 */         GL11.glDisable(32826);
/*    */         break;
/*    */       case 2:
/* 48 */         GL11.glEnable(32826);
/* 49 */         GL11.glEnable(2903);
/* 50 */         GL11.glTranslatef(0.0F, 0.005F, -0.165F);
/* 51 */         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
/* 52 */         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/* 53 */         MCH_RenderGLTD.model.renderAll();
/* 54 */         GL11.glDisable(32826);
/*    */         break;
/*    */       case 3:
/* 57 */         GL11.glEnable(32826);
/* 58 */         GL11.glEnable(2903);
/* 59 */         GL11.glTranslatef(0.3F, 0.5F, -0.5F);
/* 60 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 61 */         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 62 */         GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/* 63 */         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/* 64 */         MCH_RenderGLTD.model.renderAll();
/* 65 */         GL11.glDisable(32826);
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 73 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_ItemGLTDRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */