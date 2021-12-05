/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemRect
/*    */   extends MCH_HudItem {
/*    */   private final String left;
/*    */   private final String top;
/*    */   private final String width;
/*    */   private final String height;
/*    */   
/*    */   public MCH_HudItemRect(int fileLine, String left, String top, String width, String height) {
/* 13 */     super(fileLine);
/* 14 */     this.left = toFormula(left);
/* 15 */     this.top = toFormula(top);
/* 16 */     this.width = toFormula(width);
/* 17 */     this.height = toFormula(height);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 24 */     this; double x2 = centerX + calc(this.left);
/* 25 */     this; double y2 = centerY + calc(this.top);
/* 26 */     double x1 = x2 + (int)calc(this.width);
/* 27 */     double y1 = y2 + (int)calc(this.height);
/* 28 */     drawRect(x1, y1, x2, y2, colorSetting);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemRect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */