/*    */ package mcheli.mcheli.tool.rangefinder;
/*    */ 
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.tool.rangefinder.MCH_ItemRangeFinder;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_ItemRenderRangeFinder
/*    */   implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/* 18 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.ENTITY);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/* 26 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.ENTITY);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/* 34 */     GL11.glPushMatrix();
/*    */     
/* 36 */     W_McClient.MOD_bindTexture("textures/rangefinder.png");
/*    */     
/* 38 */     float size = 1.0F;
/*    */     
/* 40 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*    */       
/*    */       case 1:
/* 43 */         size = 2.2F;
/* 44 */         GL11.glScalef(size, size, size);
/* 45 */         GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
/* 46 */         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
/* 47 */         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
/* 48 */         GL11.glTranslatef(0.0F, 0.0F, -0.0F);
/* 49 */         MCH_ModelManager.render("rangefinder");
/*    */         break;
/*    */       case 2:
/* 52 */         size = 2.2F;
/* 53 */         GL11.glScalef(size, size, size);
/* 54 */         GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
/* 55 */         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
/* 56 */         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
/* 57 */         if ((Minecraft.func_71410_x()).field_71439_g.func_71057_bx() > 0) {
/*    */           
/* 59 */           GL11.glTranslatef(0.4F, -0.35F, -0.3F);
/*    */         }
/*    */         else {
/*    */           
/* 63 */           GL11.glTranslatef(0.2F, -0.35F, -0.3F);
/*    */         } 
/* 65 */         MCH_ModelManager.render("rangefinder");
/*    */         break;
/*    */       case 3:
/* 68 */         if (!MCH_ItemRangeFinder.isUsingScope((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g)) {
/*    */           
/* 70 */           size = 2.2F;
/* 71 */           GL11.glScalef(size, size, size);
/* 72 */           GL11.glRotatef(-210.0F, 0.0F, 1.0F, 0.0F);
/* 73 */           GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/* 74 */           GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
/* 75 */           GL11.glTranslatef(0.06F, 0.53F, -0.1F);
/* 76 */           MCH_ModelManager.render("rangefinder");
/*    */         } 
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 83 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\rangefinder\MCH_ItemRenderRangeFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */