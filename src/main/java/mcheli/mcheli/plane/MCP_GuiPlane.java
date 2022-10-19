/*     */ package mcheli.mcheli.plane;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.aircraft.MCH_AircraftCommonGui;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCP_GuiPlane
/*     */   extends MCH_AircraftCommonGui
/*     */ {
/*     */   public MCP_GuiPlane(Minecraft minecraft) {
/*  24 */     super(minecraft);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  29 */     return MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player) instanceof MCP_EntityPlane;
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
/*     */     //   6: instanceof mcheli/plane/MCP_EntityPlane
/*     */     //   9: ifeq -> 19
/*     */     //   12: aload_3
/*     */     //   13: invokevirtual isDestroyed : ()Z
/*     */     //   16: ifeq -> 20
/*     */     //   19: return
/*     */     //   20: aload_3
/*     */     //   21: checkcast mcheli/plane/MCP_EntityPlane
/*     */     //   24: astore #4
/*     */     //   26: aload_3
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual getSeatIdByEntity : (Lnet/minecraft/entity/Entity;)I
/*     */     //   31: istore #5
/*     */     //   33: aload_0
/*     */     //   34: pop
/*     */     //   35: getstatic mcheli/plane/MCP_GuiPlane.scaleFactor : I
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
/*     */     //   70: ifeq -> 105
/*     */     //   73: iload #5
/*     */     //   75: ifne -> 97
/*     */     //   78: aload #4
/*     */     //   80: aload_1
/*     */     //   81: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
/*     */     //   84: ifeq -> 97
/*     */     //   87: aload_0
/*     */     //   88: aload_3
/*     */     //   89: aload_1
/*     */     //   90: iconst_1
/*     */     //   91: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   94: goto -> 105
/*     */     //   97: aload_0
/*     */     //   98: aload_3
/*     */     //   99: aload_1
/*     */     //   100: iload #5
/*     */     //   102: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   105: aload_0
/*     */     //   106: aload #4
/*     */     //   108: invokevirtual drawDebugtInfo : (Lmcheli/aircraft/MCH_EntityAircraft;)V
/*     */     //   111: iload_2
/*     */     //   112: ifeq -> 128
/*     */     //   115: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   118: pop
/*     */     //   119: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
/*     */     //   122: getfield prmBool : Z
/*     */     //   125: ifeq -> 176
/*     */     //   128: aload #4
/*     */     //   130: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   133: ifnull -> 167
/*     */     //   136: aload #4
/*     */     //   138: aload_1
/*     */     //   139: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
/*     */     //   142: ifne -> 153
/*     */     //   145: aload #4
/*     */     //   147: invokevirtual isUAV : ()Z
/*     */     //   150: ifeq -> 167
/*     */     //   153: aload_0
/*     */     //   154: aload #4
/*     */     //   156: aload #4
/*     */     //   158: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   161: invokevirtual drawTvMissileNoise : (Lmcheli/aircraft/MCH_EntityAircraft;Lmcheli/weapon/MCH_EntityTvMissile;)V
/*     */     //   164: goto -> 176
/*     */     //   167: aload_0
/*     */     //   168: aload #4
/*     */     //   170: aload_1
/*     */     //   171: iload #5
/*     */     //   173: invokevirtual drawKeybind : (Lmcheli/plane/MCP_EntityPlane;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   176: aload_0
/*     */     //   177: aload #4
/*     */     //   179: ldc -14101432
/*     */     //   181: iload #5
/*     */     //   183: invokevirtual drawHitBullet : (Lmcheli/aircraft/MCH_EntityAircraft;II)V
/*     */     //   186: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #34	-> 0
/*     */     //   #36	-> 5
/*     */     //   #38	-> 19
/*     */     //   #41	-> 20
/*     */     //   #43	-> 26
/*     */     //   #45	-> 33
/*     */     //   #48	-> 42
/*     */     //   #50	-> 52
/*     */     //   #53	-> 56
/*     */     //   #55	-> 73
/*     */     //   #57	-> 87
/*     */     //   #61	-> 97
/*     */     //   #65	-> 105
/*     */     //   #67	-> 111
/*     */     //   #69	-> 128
/*     */     //   #71	-> 153
/*     */     //   #76	-> 167
/*     */     //   #81	-> 176
/*     */     //   #82	-> 186
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	187	0	this	Lmcheli/plane/MCP_GuiPlane;
/*     */     //   0	187	1	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	187	2	isThirdPersonView	Z
/*     */     //   5	182	3	ac	Lmcheli/aircraft/MCH_EntityAircraft;
/*     */     //   26	161	4	plane	Lmcheli/plane/MCP_EntityPlane;
/*     */     //   33	154	5	seatID	I
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawKeybind(MCP_EntityPlane plane, EntityPlayer player, int seatID) {
/*  86 */     if (MCH_Config.HideKeybind.prmBool)
/*  87 */       return;  MCP_PlaneInfo info = plane.getPlaneInfo();
/*  88 */     if (info == null) {
/*     */       return;
/*     */     }
/*     */     
/*  92 */     int colorActive = -1342177281;
/*  93 */     int colorInactive = -1349546097;
/*  94 */     int RX = this.centerX + 120;
/*  95 */     int LX = this.centerX - 200;
/*     */     
/*  97 */     drawKeyBind((MCH_EntityAircraft)plane, (MCH_AircraftInfo)info, player, seatID, RX, LX, colorActive, colorInactive);
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (seatID == 0 && info.isEnableGunnerMode) if (!Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {
/*     */         
/* 103 */         int c = plane.isHoveringMode() ? colorInactive : colorActive;
/* 104 */         String msg = (plane.getIsGunnerMode((Entity)player) ? "Normal" : "Gunner") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchMode.prmInt);
/*     */         
/* 106 */         drawString(msg, RX, this.centerY - 70, c);
/*     */       } 
/*     */     
/* 109 */     if (seatID > 0 && plane.canSwitchGunnerModeOtherSeat(player)) {
/*     */       
/* 111 */       String msg = (plane.getIsGunnerMode((Entity)player) ? "Normal" : "Camera") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchMode.prmInt);
/*     */       
/* 113 */       drawString(msg, RX, this.centerY - 40, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 117 */     if (seatID == 0 && info.isEnableVtol) if (!Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {
/*     */         
/* 119 */         int stat = plane.getVtolMode();
/* 120 */         if (stat != 1) {
/*     */           
/* 122 */           String msg = ((stat == 0) ? "VTOL : " : "Normal : ") + MCH_KeyName.getDescOrName(MCH_Config.KeyExtra.prmInt);
/* 123 */           drawString(msg, RX, this.centerY - 60, colorActive);
/*     */         } 
/*     */       } 
/*     */ 
/*     */     
/* 128 */     if (plane.canEjectSeat((Entity)player)) {
/*     */       
/* 130 */       String msg = "Eject seat: " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchHovering.prmInt);
/* 131 */       drawString(msg, RX, this.centerY - 30, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 135 */     if (plane.getIsGunnerMode((Entity)player) && info.cameraZoom > 1) {
/*     */ 
/*     */       
/* 138 */       String msg = "Zoom : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 139 */       drawString(msg, LX, this.centerY - 80, colorActive);
/*     */     }
/* 141 */     else if (seatID == 0) {
/*     */ 
/*     */       
/* 144 */       if (plane.canFoldWing() || plane.canUnfoldWing()) {
/*     */         
/* 146 */         String msg = "FoldWing : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 147 */         drawString(msg, LX, this.centerY - 80, colorActive);
/*     */       
/*     */       }
/* 150 */       else if (plane.canFoldHatch() || plane.canUnfoldHatch()) {
/*     */         
/* 152 */         String msg = "OpenHatch : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 153 */         drawString(msg, LX, this.centerY - 80, colorActive);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_GuiPlane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */