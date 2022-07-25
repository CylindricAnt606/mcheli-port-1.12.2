/*    */ package mcheli.mcheli.gui;
/*    */ 
/*    */ import mcheli.wrapper.W_GuiButton;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ public class MCH_GuiOnOffButton
/*    */   extends W_GuiButton
/*    */ {
/*    */   private boolean statOnOff;
/*    */   private final String dispOnOffString;
/*    */   
/*    */   public MCH_GuiOnOffButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
/* 14 */     super(par1, par2, par3, par4, par5, "");
/* 15 */     this.dispOnOffString = par6Str;
/* 16 */     setOnOff(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOnOff(boolean b) {
/* 21 */     this.statOnOff = b;
/* 22 */     this.field_146126_j = this.dispOnOffString + (getOnOff() ? "ON" : "OFF");
/*    */   }
/*    */   
/*    */   public boolean getOnOff() {
/* 26 */     return this.statOnOff;
/*    */   }
/*    */   
/*    */   public void switchOnOff() {
/* 30 */     setOnOff(!getOnOff());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int x, int y) {
/* 35 */     if (super.func_146116_c(mc, x, y)) {
/*    */       
/* 37 */       switchOnOff();
/* 38 */       return true;
/*    */     } 
/* 40 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiOnOffButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */