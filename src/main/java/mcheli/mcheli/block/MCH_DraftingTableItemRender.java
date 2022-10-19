/*    */ package mcheli.mcheli.block;
/*    */ 
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_DraftingTableItemRender
/*    */   implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/* 16 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*    */       
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/*    */       case 4:
/* 22 */         return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/*    */     float INV_SIZE;
/* 39 */     GL11.glPushMatrix();
/* 40 */     W_McClient.MOD_bindTexture("textures/blocks/drafting_table.png");
/*    */     
/* 42 */     GL11.glEnable(32826);
/*    */ 
/*    */     
/* 45 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*    */       
/*    */       case 1:
/* 48 */         GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/* 49 */         GL11.glScalef(1.5F, 1.5F, 1.5F);
/*    */         break;
/*    */       
/*    */       case 4:
/* 53 */         INV_SIZE = 0.75F;
/* 54 */         GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/* 55 */         GL11.glScalef(0.75F, 0.75F, 0.75F);
/*    */         break;
/*    */       
/*    */       case 2:
/* 59 */         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
/* 60 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/*    */         break;
/*    */       
/*    */       case 3:
/* 64 */         GL11.glTranslatef(0.75F, 0.0F, 0.0F);
/* 65 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 66 */         GL11.glRotatef(90.0F, 0.0F, -1.0F, 0.0F);
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 73 */     MCH_ModelManager.render("blocks", "drafting_table");
/*    */ 
/*    */     
/* 76 */     GL11.glPopMatrix();
/*    */     
/* 78 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 79 */     GL11.glEnable(3042);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableItemRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */