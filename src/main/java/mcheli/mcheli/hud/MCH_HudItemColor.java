/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemColor
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String updateColor;
/*    */   
/*    */   public MCH_HudItemColor(int fileLine, String newColor) {
/* 11 */     super(fileLine);
/* 12 */     this.updateColor = newColor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static mcheli.hud.MCH_HudItemColor createByParams(int fileLine, String[] prm) {
/* 18 */     if (prm.length == 1)
/*    */     {
/* 20 */       return new mcheli.hud.MCH_HudItemColor(fileLine, toFormula(prm[0]));
/*    */     }
/* 22 */     if (prm.length == 4)
/*    */     {
/* 24 */       return new mcheli.hud.MCH_HudItemColor(fileLine, "((" + toFormula(prm[0]) + ")<<24)|" + "((" + toFormula(prm[1]) + ")<<16)|" + "((" + toFormula(prm[2]) + ")<<8 )|" + "((" + toFormula(prm[3]) + ")<<0 )");
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 36 */     double d = calc(this.updateColor);
/* 37 */     long l = (long)d;
/* 38 */     MCH_HudItem.colorSetting = (int)l;
/* 39 */     updateVarMapItem("color", getColor());
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */