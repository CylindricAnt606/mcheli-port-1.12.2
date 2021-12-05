/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_GuiButton
/*    */   extends GuiButton
/*    */ {
/* 14 */   public List<String> hoverStringList = null;
/*    */ 
/*    */   
/*    */   public W_GuiButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
/* 18 */     super(par1, par2, par3, par4, par5, par6Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addHoverString(String s) {
/* 23 */     if (this.hoverStringList == null)
/*    */     {
/* 25 */       this.hoverStringList = new ArrayList<String>();
/*    */     }
/*    */     
/* 28 */     this.hoverStringList.add(s);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isVisible() {
/* 34 */     return this.field_146125_m;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVisible(boolean b) {
/* 42 */     this.field_146125_m = b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setVisible(GuiButton button, boolean b) {
/* 49 */     button.field_146125_m = b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enableBlend() {
/* 57 */     GL11.glEnable(3042);
/* 58 */     OpenGlHelper.func_148821_a(770, 771, 1, 0);
/* 59 */     GL11.glBlendFunc(770, 771);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOnMouseOver() {
/* 68 */     return this.field_146123_n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOnMouseOver(boolean b) {
/* 76 */     this.field_146123_n = b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWidth() {
/* 83 */     return this.field_146120_f;
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 87 */     return this.field_146121_g;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_GuiButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */