/*    */ package mcheli.mcheli.gui;
/*    */ 
/*    */ import mcheli.MCH_ConfigPrm;
/*    */ import mcheli.MCH_KeyName;
/*    */ import mcheli.gui.MCH_GuiListItem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public class MCH_GuiListItemKeyBind
/*    */   extends MCH_GuiListItem
/*    */ {
/*    */   public String displayString;
/*    */   public GuiButton button;
/*    */   public GuiButton buttonReset;
/*    */   public int keycode;
/*    */   public final int defaultKeycode;
/*    */   public MCH_ConfigPrm config;
/*    */   public GuiButton lastPushButton;
/*    */   
/*    */   public MCH_GuiListItemKeyBind(int id, int idReset, int posX, String dispStr, MCH_ConfigPrm prm) {
/* 21 */     this.displayString = dispStr;
/* 22 */     this.defaultKeycode = prm.prmIntDefault;
/* 23 */     this.button = new GuiButton(id, posX + 160, 0, 70, 20, "");
/* 24 */     this.buttonReset = new GuiButton(idReset, posX + 240, 0, 40, 20, "Reset");
/* 25 */     this.config = prm;
/* 26 */     this.lastPushButton = null;
/*    */     
/* 28 */     setKeycode(prm.prmInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(int x, int y) {
/* 33 */     this.button.func_146118_a(x, y);
/* 34 */     this.buttonReset.func_146118_a(x, y);
/*    */   }
/*    */   
/*    */   public boolean mousePressed(Minecraft mc, int x, int y) {
/* 38 */     if (this.button.func_146116_c(mc, x, y)) {
/*    */       
/* 40 */       this.lastPushButton = this.button;
/*    */       
/* 42 */       return true;
/*    */     } 
/* 44 */     if (this.buttonReset.func_146116_c(mc, x, y)) {
/*    */       
/* 46 */       this.lastPushButton = this.buttonReset;
/*    */       
/* 48 */       return true;
/*    */     } 
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public void draw(Minecraft mc, int mouseX, int mouseY, int posX, int posY) {
/* 54 */     int y = 6;
/* 55 */     this.button.func_73731_b(mc.field_71466_p, this.displayString, posX + 10, posY + y, -1);
/* 56 */     this.button.field_146129_i = posY;
/* 57 */     this.button.func_146112_a(mc, mouseX, mouseY);
/* 58 */     this.buttonReset.field_146124_l = (this.keycode != this.defaultKeycode);
/* 59 */     this.buttonReset.field_146129_i = posY;
/* 60 */     this.buttonReset.func_146112_a(mc, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyKeycode() {
/* 65 */     this.config.setPrm(this.keycode);
/*    */   }
/*    */   
/*    */   public void resetKeycode() {
/* 69 */     setKeycode(this.defaultKeycode);
/*    */   }
/*    */   
/*    */   public void setKeycode(int k) {
/* 73 */     if (k != 0 && !MCH_KeyName.getDescOrName(k).isEmpty()) {
/*    */       
/* 75 */       this.keycode = k;
/* 76 */       this.button.field_146126_j = MCH_KeyName.getDescOrName(k);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiListItemKeyBind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */