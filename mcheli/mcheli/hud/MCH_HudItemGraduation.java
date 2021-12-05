/*     */ package mcheli.mcheli.hud;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.hud.MCH_HudItem;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class MCH_HudItemGraduation
/*     */   extends MCH_HudItem
/*     */ {
/*     */   private final String drawRot;
/*     */   private final String drawRoll;
/*     */   private final String drawPosX;
/*     */   private final String drawPosY;
/*     */   private final int type;
/*     */   
/*     */   public MCH_HudItemGraduation(int fileLine, int type, String rot, String roll, String posx, String posy) {
/*  18 */     super(fileLine);
/*  19 */     this.drawRot = toFormula(rot);
/*  20 */     this.drawRoll = toFormula(roll);
/*  21 */     this.drawPosX = toFormula(posx);
/*  22 */     this.drawPosY = toFormula(posy);
/*  23 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/*  29 */     GL11.glPushMatrix();
/*     */     
/*  31 */     int x = (int)(centerX + calc(this.drawPosX));
/*  32 */     int y = (int)(centerY + calc(this.drawPosY));
/*     */     
/*  34 */     GL11.glTranslated(x, y, 0.0D);
/*  35 */     GL11.glRotatef((float)calc(this.drawRoll), 0.0F, 0.0F, 1.0F);
/*  36 */     GL11.glTranslated(-x, -y, 0.0D);
/*     */     
/*  38 */     if (this.type == 0) {
/*     */       
/*  40 */       drawCommonGraduationYaw(calc(this.drawRot), colorSetting, x, y);
/*     */     }
/*  42 */     else if (this.type == 1) {
/*     */       
/*  44 */       drawCommonGraduationPitch1(calc(this.drawRot), colorSetting, x, y);
/*     */     }
/*  46 */     else if (this.type == 2 || this.type == 3) {
/*     */       
/*  48 */       drawCommonGraduationPitch2(calc(this.drawRot), colorSetting, x, y);
/*     */     } 
/*     */     
/*  51 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCommonGraduationPitch2(double playerPitch, int color, int posX, int posY) {
/*  56 */     playerPitch = -playerPitch;
/*     */ 
/*     */     
/*  59 */     int pitch_n = (int)playerPitch / 5 * 5;
/*     */ 
/*     */     
/*  62 */     double[] line = new double[8];
/*     */     
/*  64 */     int start = (this.type == 2) ? 0 : 1;
/*  65 */     int end = (this.type == 2) ? 5 : 4;
/*  66 */     int INT = (this.type == 2) ? 1 : 2;
/*     */     
/*  68 */     for (int i = start; i < end; i++) {
/*     */       
/*  70 */       int pitch = -(-pitch_n - 10 + i * 5);
/*  71 */       double p_rest = playerPitch % 5.0D;
/*     */       
/*  73 */       int XO = 50;
/*  74 */       int XI = 30;
/*  75 */       int x = (pitch != 0) ? 50 : 100;
/*  76 */       int y = posY + (int)((-60 * INT) + p_rest * 6.0D * INT + (i * 30 * INT));
/*     */ 
/*     */       
/*  79 */       line[0] = (posX - x);
/*  80 */       line[1] = (y + ((pitch == 0) ? 0 : ((pitch > 0) ? 2 : -2)));
/*  81 */       line[2] = (posX - 50);
/*  82 */       line[3] = y;
/*  83 */       line[4] = (posX + x);
/*  84 */       line[5] = line[1];
/*  85 */       line[6] = (posX + 50);
/*  86 */       line[7] = y;
/*  87 */       drawLine(line, color);
/*     */ 
/*     */       
/*  90 */       line[0] = (posX - 50);
/*  91 */       line[1] = y;
/*  92 */       line[2] = (posX - 30);
/*  93 */       line[3] = y;
/*  94 */       line[4] = (posX + 50);
/*  95 */       line[5] = y;
/*  96 */       line[6] = (posX + 30);
/*  97 */       line[7] = y;
/*  98 */       if (pitch >= 0) {
/*     */         
/* 100 */         drawLine(line, color);
/*     */       }
/*     */       else {
/*     */         
/* 104 */         drawLineStipple(line, color, 1, 52428);
/*     */       } 
/*     */       
/* 107 */       if (pitch != 0) {
/*     */         
/* 109 */         drawCenteredString("" + pitch, posX - 50 - 10, y - 4, color);
/* 110 */         drawCenteredString("" + pitch, posX + 50 + 10, y - 4, color);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCommonGraduationPitch1(double playerPitch, int color, int posX, int posY) {
/* 118 */     int pitch = (int)playerPitch % 360;
/* 119 */     int INVY = 10;
/*     */ 
/*     */     
/* 122 */     int y = (int)(playerPitch * 10.0D % 10.0D);
/* 123 */     if (y < 0) y += 10;
/*     */     
/* 125 */     int GW = 100;
/* 126 */     int posX_L = posX - 100;
/* 127 */     int posX_R = posX + 100;
/* 128 */     int linePosY = posY;
/* 129 */     posY -= 80;
/*     */ 
/*     */     
/* 132 */     double[] line = new double[144];
/* 133 */     int p = (playerPitch >= 0.0D || y == 0) ? (pitch - 8) : (pitch - 9);
/* 134 */     for (int i = 0; i < line.length / 8; i++, p++) {
/*     */       
/* 136 */       int olx = (p % 3 == 0) ? 15 : 5;
/* 137 */       int ilx = 0;
/*     */ 
/*     */       
/* 140 */       line[i * 8 + 0] = (posX_L - olx);
/* 141 */       line[i * 8 + 1] = (posY + i * 10 - y);
/* 142 */       line[i * 8 + 2] = (posX_L + ilx);
/* 143 */       line[i * 8 + 3] = (posY + i * 10 - y);
/*     */       
/* 145 */       line[i * 8 + 4] = (posX_R + olx);
/* 146 */       line[i * 8 + 5] = (posY + i * 10 - y);
/* 147 */       line[i * 8 + 6] = (posX_R - ilx);
/* 148 */       line[i * 8 + 7] = (posY + i * 10 - y);
/*     */     } 
/* 150 */     drawLine(line, color);
/*     */ 
/*     */     
/* 153 */     double[] verticalLine = { (posX_L - 25), (linePosY - 90), posX_L, (linePosY - 90), posX_L, (linePosY + 90), (posX_L - 25), (linePosY + 90) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     drawLine(verticalLine, color, 3);
/* 160 */     verticalLine = new double[] { (posX_R + 25), (linePosY - 90), posX_R, (linePosY - 90), posX_R, (linePosY + 90), (posX_R + 25), (linePosY + 90) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     drawLine(verticalLine, color, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCommonGraduationYaw(double playerYaw, int color, int posX, int posY) {
/* 171 */     double yaw = MCH_Lib.getRotate360(playerYaw);
/*     */     
/* 173 */     int INVX = 10;
/* 174 */     posX -= 90;
/* 175 */     double[] line = new double[76];
/*     */ 
/*     */     
/* 178 */     int x = (int)(yaw * 10.0D) % 10;
/*     */ 
/*     */     
/* 181 */     int y = (int)yaw - 9;
/* 182 */     for (int i = 0; i < line.length / 4; i++, y++) {
/*     */       
/* 184 */       int azPosX = posX + i * 10 - x;
/* 185 */       line[i * 4 + 0] = azPosX;
/* 186 */       line[i * 4 + 1] = posY;
/* 187 */       line[i * 4 + 2] = azPosX;
/* 188 */       line[i * 4 + 3] = (posY + ((y % 45 == 0) ? 15 : ((y % 3 == 0) ? 10 : 5)));
/* 189 */       if (y % 45 == 0) {
/*     */         
/* 191 */         drawCenteredString(MCH_Lib.getAzimuthStr8(y), azPosX, posY - 10, -65536);
/*     */       }
/* 193 */       else if (y % 3 == 0) {
/*     */         
/* 195 */         int rot = y + 180;
/* 196 */         if (rot < 0) rot += 360; 
/* 197 */         if (rot > 360) rot -= 360; 
/* 198 */         drawCenteredString(String.format("%d", new Object[] { Integer.valueOf(rot) }), azPosX, posY - 10, color);
/*     */       } 
/*     */     } 
/* 201 */     drawLine(line, color);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemGraduation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */