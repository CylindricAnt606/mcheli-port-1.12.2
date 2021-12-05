/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ 
/*    */ public class MCH_HudItemLineStipple
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String pat;
/*    */   private final String fac;
/*    */   private final String[] pos;
/*    */   
/*    */   public MCH_HudItemLineStipple(int fileLine, String[] position) {
/* 14 */     super(fileLine);
/* 15 */     this.pat = position[0];
/* 16 */     this.fac = position[1];
/* 17 */     this.pos = new String[position.length - 2];
/* 18 */     for (int i = 0; i < position.length - 2; i++)
/*    */     {
/* 20 */       this.pos[i] = toFormula(position[2 + i]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 27 */     double[] lines = new double[this.pos.length];
/* 28 */     for (int i = 0; i < lines.length; i += 2) {
/*    */       
/* 30 */       this; lines[i + 0] = centerX + calc(this.pos[i + 0]);
/* 31 */       this; lines[i + 1] = centerY + calc(this.pos[i + 1]);
/*    */     } 
/* 33 */     drawLineStipple(lines, colorSetting, (int)calc(this.fac), (int)calc(this.pat));
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemLineStipple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */