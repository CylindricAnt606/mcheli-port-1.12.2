/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public abstract class W_GuiContainer
/*    */   extends GuiContainer
/*    */ {
/*    */   public W_GuiContainer(Container par1Container) {
/* 14 */     super(par1Container);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawItemStack(ItemStack item, int x, int y) {
/* 19 */     if (item == null)
/* 20 */       return;  if (item.func_77973_b() == null)
/* 21 */       return;  FontRenderer font = item.func_77973_b().getFontRenderer(item);
/* 22 */     if (font == null) font = this.field_146289_q; 
/* 23 */     GL11.glEnable(2929);
/* 24 */     GL11.glEnable(2896);
/* 25 */     field_146296_j.func_82406_b(font, this.field_146297_k.func_110434_K(), item, x, y);
/* 26 */     field_146296_j.func_94148_a(font, this.field_146297_k.func_110434_K(), item, x, y, null);
/* 27 */     this.field_73735_i = 0.0F;
/* 28 */     field_146296_j.field_77023_b = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawString(String s, int x, int y, int color) {
/* 34 */     func_73731_b(this.field_146289_q, s, x, y, color);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawCenteredString(String s, int x, int y, int color) {
/* 43 */     func_73732_a(this.field_146289_q, s, x, y, color);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getStringWidth(String s) {
/* 51 */     return this.field_146289_q.func_78256_a(s);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_GuiContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */