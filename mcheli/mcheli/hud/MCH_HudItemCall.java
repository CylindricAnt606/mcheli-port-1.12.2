/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_Hud;
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ import mcheli.hud.MCH_HudManager;
/*    */ 
/*    */ public class MCH_HudItemCall extends MCH_HudItem {
/*    */   private final String hudName;
/*    */   
/*    */   public MCH_HudItemCall(int fileLine, String name) {
/* 11 */     super(fileLine);
/* 12 */     this.hudName = name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 18 */     MCH_Hud hud = MCH_HudManager.get(this.hudName);
/* 19 */     if (hud != null)
/*    */     {
/* 21 */       hud.drawItems();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */