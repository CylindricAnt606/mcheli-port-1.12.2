/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemExit
/*    */   extends MCH_HudItem
/*    */ {
/*    */   public MCH_HudItemExit(int fileLine) {
/*  9 */     super(fileLine);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 15 */     this.parent.exit = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemExit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */