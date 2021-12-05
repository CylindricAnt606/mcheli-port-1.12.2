/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.MCH_Vector2;
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ 
/*    */ public class MCH_HudItemRadar
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String rot;
/*    */   private final String left;
/*    */   private final String top;
/*    */   private final String width;
/*    */   private final String height;
/*    */   private final boolean isEntityRadar;
/*    */   
/*    */   public MCH_HudItemRadar(int fileLine, boolean isEntityRadar, String rot, String left, String top, String width, String height) {
/* 19 */     super(fileLine);
/* 20 */     this.isEntityRadar = isEntityRadar;
/* 21 */     this.rot = toFormula(rot);
/* 22 */     this.left = toFormula(left);
/* 23 */     this.top = toFormula(top);
/* 24 */     this.width = toFormula(width);
/* 25 */     this.height = toFormula(height);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 31 */     if (this.isEntityRadar) {
/*    */       
/* 33 */       if (EntityList != null && EntityList.size() > 0)
/*    */       {
/* 35 */         drawEntityList(EntityList, (float)calc(this.rot), centerX + calc(this.left), centerY + calc(this.top), calc(this.width), calc(this.height));
/*    */ 
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 41 */     else if (EnemyList != null && EnemyList.size() > 0) {
/*    */       
/* 43 */       drawEntityList(EnemyList, (float)calc(this.rot), centerX + calc(this.left), centerY + calc(this.top), calc(this.width), calc(this.height));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawEntityList(ArrayList<MCH_Vector2> src, float r, double left, double top, double w, double h) {
/* 51 */     double w1 = -w / 2.0D;
/* 52 */     double w2 = w / 2.0D;
/* 53 */     double h1 = -h / 2.0D;
/* 54 */     double h2 = h / 2.0D;
/* 55 */     double w_factor = w / 64.0D;
/* 56 */     double h_factor = h / 64.0D;
/* 57 */     double[] list = new double[src.size() * 2];
/* 58 */     int idx = 0;
/* 59 */     for (MCH_Vector2 v : src) {
/*    */       
/* 61 */       list[idx + 0] = v.x / 2.0D * w_factor;
/* 62 */       list[idx + 1] = v.y / 2.0D * h_factor;
/* 63 */       idx += 2;
/*    */     } 
/* 65 */     MCH_Lib.rotatePoints(list, r);
/* 66 */     ArrayList<Double> drawList = new ArrayList<Double>();
/* 67 */     for (int i = 0; i + 1 < list.length; i += 2) {
/*    */       
/* 69 */       if (list[i + 0] > w1 && list[i + 0] < w2 && list[i + 1] > h1 && list[i + 1] < h2) {
/*    */         
/* 71 */         drawList.add(Double.valueOf(list[i + 0] + left + w / 2.0D));
/* 72 */         drawList.add(Double.valueOf(list[i + 1] + top + h / 2.0D));
/*    */       } 
/*    */     } 
/* 75 */     this; drawPoints(drawList, colorSetting, scaleFactor * 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemRadar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */