/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemConditional
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final boolean isEndif;
/*    */   private final String conditional;
/*    */   
/*    */   public MCH_HudItemConditional(int fileLine, boolean isEndif, String conditional) {
/* 12 */     super(fileLine);
/* 13 */     this.isEndif = isEndif;
/* 14 */     this.conditional = conditional;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canExecute() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 25 */     if (!this.isEndif) {
/*    */       
/* 27 */       this.parent.isIfFalse = (calc(this.conditional) == 0.0D);
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 32 */       this.parent.isIfFalse = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemConditional.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */