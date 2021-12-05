/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.aircraft.MCH_AircraftCommonGui;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiTank
/*     */   extends MCH_AircraftCommonGui
/*     */ {
/*     */   public MCH_GuiTank(Minecraft minecraft) {
/*  22 */     super(minecraft);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  27 */     return MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player) instanceof MCH_EntityTank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokestatic getAircraft_RiddenOrControl : (Lnet/minecraft/entity/Entity;)Lmcheli/aircraft/MCH_EntityAircraft;
/*     */     //   4: astore_3
/*     */     //   5: aload_3
/*     */     //   6: instanceof mcheli/tank/MCH_EntityTank
/*     */     //   9: ifeq -> 19
/*     */     //   12: aload_3
/*     */     //   13: invokevirtual isDestroyed : ()Z
/*     */     //   16: ifeq -> 20
/*     */     //   19: return
/*     */     //   20: aload_3
/*     */     //   21: checkcast mcheli/tank/MCH_EntityTank
/*     */     //   24: astore #4
/*     */     //   26: aload_3
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual getSeatIdByEntity : (Lnet/minecraft/entity/Entity;)I
/*     */     //   31: istore #5
/*     */     //   33: aload_0
/*     */     //   34: pop
/*     */     //   35: getstatic mcheli/tank/MCH_GuiTank.scaleFactor : I
/*     */     //   38: i2f
/*     */     //   39: invokestatic glLineWidth : (F)V
/*     */     //   42: aload #4
/*     */     //   44: aload_1
/*     */     //   45: invokevirtual getCameraMode : (Lnet/minecraft/entity/player/EntityPlayer;)I
/*     */     //   48: iconst_1
/*     */     //   49: if_icmpne -> 56
/*     */     //   52: aload_0
/*     */     //   53: invokevirtual drawNightVisionNoise : ()V
/*     */     //   56: iload_2
/*     */     //   57: ifeq -> 73
/*     */     //   60: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   63: pop
/*     */     //   64: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
/*     */     //   67: getfield prmBool : Z
/*     */     //   70: ifeq -> 81
/*     */     //   73: aload_0
/*     */     //   74: aload_3
/*     */     //   75: aload_1
/*     */     //   76: iload #5
/*     */     //   78: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   81: aload_0
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual drawDebugtInfo : (Lmcheli/tank/MCH_EntityTank;)V
/*     */     //   87: iload_2
/*     */     //   88: ifeq -> 104
/*     */     //   91: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   94: pop
/*     */     //   95: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
/*     */     //   98: getfield prmBool : Z
/*     */     //   101: ifeq -> 152
/*     */     //   104: aload #4
/*     */     //   106: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   109: ifnull -> 143
/*     */     //   112: aload #4
/*     */     //   114: aload_1
/*     */     //   115: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
/*     */     //   118: ifne -> 129
/*     */     //   121: aload #4
/*     */     //   123: invokevirtual isUAV : ()Z
/*     */     //   126: ifeq -> 143
/*     */     //   129: aload_0
/*     */     //   130: aload #4
/*     */     //   132: aload #4
/*     */     //   134: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   137: invokevirtual drawTvMissileNoise : (Lmcheli/aircraft/MCH_EntityAircraft;Lmcheli/weapon/MCH_EntityTvMissile;)V
/*     */     //   140: goto -> 152
/*     */     //   143: aload_0
/*     */     //   144: aload #4
/*     */     //   146: aload_1
/*     */     //   147: iload #5
/*     */     //   149: invokevirtual drawKeybind : (Lmcheli/tank/MCH_EntityTank;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   152: aload_0
/*     */     //   153: aload #4
/*     */     //   155: ldc -14101432
/*     */     //   157: iload #5
/*     */     //   159: invokevirtual drawHitBullet : (Lmcheli/aircraft/MCH_EntityAircraft;II)V
/*     */     //   162: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #32	-> 0
/*     */     //   #34	-> 5
/*     */     //   #36	-> 19
/*     */     //   #39	-> 20
/*     */     //   #41	-> 26
/*     */     //   #43	-> 33
/*     */     //   #46	-> 42
/*     */     //   #48	-> 52
/*     */     //   #51	-> 56
/*     */     //   #53	-> 73
/*     */     //   #56	-> 81
/*     */     //   #58	-> 87
/*     */     //   #60	-> 104
/*     */     //   #62	-> 129
/*     */     //   #67	-> 143
/*     */     //   #72	-> 152
/*     */     //   #73	-> 162
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	163	0	this	Lmcheli/tank/MCH_GuiTank;
/*     */     //   0	163	1	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	163	2	isThirdPersonView	Z
/*     */     //   5	158	3	ac	Lmcheli/aircraft/MCH_EntityAircraft;
/*     */     //   26	137	4	tank	Lmcheli/tank/MCH_EntityTank;
/*     */     //   33	130	5	seatID	I
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDebugtInfo(MCH_EntityTank ac) {
/*  77 */     if (MCH_Config.DebugLog) {
/*     */       
/*  79 */       int LX = this.centerX - 100;
/*     */ 
/*     */       
/*  82 */       drawDebugtInfo((MCH_EntityAircraft)ac);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawKeybind(MCH_EntityTank tank, EntityPlayer player, int seatID) {
/*  88 */     if (MCH_Config.HideKeybind.prmBool)
/*  89 */       return;  MCH_TankInfo info = tank.getTankInfo();
/*  90 */     if (info == null) {
/*     */       return;
/*     */     }
/*     */     
/*  94 */     int colorActive = -1342177281;
/*  95 */     int colorInactive = -1349546097;
/*  96 */     int RX = this.centerX + 120;
/*  97 */     int LX = this.centerX - 200;
/*     */     
/*  99 */     drawKeyBind((MCH_EntityAircraft)tank, (MCH_AircraftInfo)info, player, seatID, RX, LX, colorActive, colorInactive);
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (seatID == 0 && tank.hasBrake()) {
/*     */       
/* 105 */       String msg = "Brake : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchHovering.prmInt);
/* 106 */       drawString(msg, RX, this.centerY - 30, colorActive);
/*     */     } 
/*     */     
/* 109 */     if (seatID > 0 && tank.canSwitchGunnerModeOtherSeat(player)) {
/*     */       
/* 111 */       String msg = (tank.getIsGunnerMode((Entity)player) ? "Normal" : "Camera") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchMode.prmInt);
/*     */       
/* 113 */       drawString(msg, RX, this.centerY - 40, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 117 */     if (tank.getIsGunnerMode((Entity)player) && info.cameraZoom > 1) {
/*     */ 
/*     */       
/* 120 */       String msg = "Zoom : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 121 */       drawString(msg, LX, this.centerY - 80, colorActive);
/*     */     }
/* 123 */     else if (seatID == 0) {
/*     */ 
/*     */       
/* 126 */       if (tank.canFoldHatch() || tank.canUnfoldHatch()) {
/*     */         
/* 128 */         String msg = "OpenHatch : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 129 */         drawString(msg, LX, this.centerY - 80, colorActive);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_GuiTank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */