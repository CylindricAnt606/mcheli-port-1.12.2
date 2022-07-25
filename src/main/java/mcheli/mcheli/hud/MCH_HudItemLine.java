/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemLine
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String[] pos;
/*    */   
/*    */   public MCH_HudItemLine(int fileLine, String[] position) {
/* 11 */     super(fileLine);
/* 12 */     this.pos = new String[position.length];
/* 13 */     for (int i = 0; i < position.length; i++)
/*    */     {
/* 15 */       this.pos[i] = position[i].toLowerCase();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 22 */     double[] lines = new double[this.pos.length];
/* 23 */     for (int i = 0; i < lines.length; i += 2) {
/*    */       
/* 25 */       this; lines[i + 0] = centerX + calc(this.pos[i + 0]);
/* 26 */       this; lines[i + 1] = centerY + calc(this.pos[i + 1]);
/*    */     } 
/* 28 */     drawLine(lines, colorSetting, 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */