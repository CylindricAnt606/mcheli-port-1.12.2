/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.MCH_Camera;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_HudItemCameraRot
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String drawPosX;
/*    */   private final String drawPosY;
/*    */   
/*    */   public MCH_HudItemCameraRot(int fileLine, String posx, String posy) {
/* 17 */     super(fileLine);
/* 18 */     this.drawPosX = toFormula(posx);
/* 19 */     this.drawPosY = toFormula(posy);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 25 */     drawCommonGunnerCamera((Entity)ac, ac.camera, colorSetting, centerX + calc(this.drawPosX), centerY + calc(this.drawPosY));
/*    */   }
/*    */ 
/*    */   
/*    */   private void drawCommonGunnerCamera(Entity ac, MCH_Camera camera, int color, double posX, double posY) {
/* 30 */     if (camera == null)
/*    */       return; 
/* 32 */     double centerX = posX;
/* 33 */     double centerY = posY;
/*    */ 
/*    */     
/* 36 */     int WW = 20;
/* 37 */     int WH = 10;
/* 38 */     int LW = 1;
/* 39 */     double[] line = { centerX - 21.0D, centerY - 11.0D, centerX + 21.0D, centerY - 11.0D, centerX + 21.0D, centerY + 11.0D, centerX - 21.0D, centerY + 11.0D };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     drawLine(line, color, 2);
/*    */ 
/*    */     
/* 48 */     line = new double[] { centerX - 21.0D, centerY, centerX, centerY, centerX + 21.0D, centerY, centerX, centerY, centerX, centerY - 11.0D, centerX, centerY, centerX, centerY + 11.0D, centerX, centerY };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     drawLineStipple(line, color, 1, 52428);
/*    */     
/* 56 */     float pitch = camera.rotationPitch;
/* 57 */     if (pitch < -30.0F) pitch = -30.0F; 
/* 58 */     if (pitch > 70.0F) pitch = 70.0F; 
/* 59 */     pitch -= 20.0F;
/* 60 */     pitch = (float)(pitch * 0.16D);
/*    */     
/* 62 */     float heliYaw = ac.field_70126_B + (ac.field_70177_z - ac.field_70126_B) / 2.0F;
/* 63 */     float cameraYaw = camera.prevRotationYaw + (camera.rotationYaw - camera.prevRotationYaw) / 2.0F;
/*    */     
/* 65 */     float yaw = (float)MCH_Lib.getRotateDiff(ac.field_70177_z, camera.rotationYaw);
/* 66 */     yaw *= 2.0F;
/* 67 */     if (yaw < -50.0F) yaw = -50.0F; 
/* 68 */     if (yaw > 50.0F) yaw = 50.0F; 
/* 69 */     yaw = (float)(yaw * 0.34D);
/*    */ 
/*    */     
/* 72 */     line = new double[] { centerX + yaw - 3.0D, centerY + pitch - 2.0D, centerX + yaw + 3.0D, centerY + pitch - 2.0D, centerX + yaw + 3.0D, centerY + pitch + 2.0D, centerX + yaw - 3.0D, centerY + pitch + 2.0D };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 78 */     drawLine(line, color, 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemCameraRot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */