/*     */ package mcheli.mcheli.vehicle;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.aircraft.MCH_AircraftCommonGui;
/*     */ import mcheli.vehicle.MCH_EntityVehicle;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.weapon.MCH_WeaponSet;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiVehicle extends MCH_AircraftCommonGui {
/*     */   static final int COLOR1 = -14066;
/*     */   
/*     */   public MCH_GuiVehicle(Minecraft minecraft) {
/*  19 */     super(minecraft);
/*     */   }
/*     */   static final int COLOR2 = -2161656;
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  24 */     return (player.field_70154_o != null && player.field_70154_o instanceof MCH_EntityVehicle);
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
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: getfield field_70154_o : Lnet/minecraft/entity/Entity;
/*     */     //   4: ifnull -> 17
/*     */     //   7: aload_1
/*     */     //   8: getfield field_70154_o : Lnet/minecraft/entity/Entity;
/*     */     //   11: instanceof mcheli/vehicle/MCH_EntityVehicle
/*     */     //   14: ifne -> 18
/*     */     //   17: return
/*     */     //   18: aload_1
/*     */     //   19: getfield field_70154_o : Lnet/minecraft/entity/Entity;
/*     */     //   22: checkcast mcheli/vehicle/MCH_EntityVehicle
/*     */     //   25: astore_3
/*     */     //   26: aload_3
/*     */     //   27: invokevirtual isDestroyed : ()Z
/*     */     //   30: ifeq -> 34
/*     */     //   33: return
/*     */     //   34: aload_3
/*     */     //   35: aload_1
/*     */     //   36: invokevirtual getSeatIdByEntity : (Lnet/minecraft/entity/Entity;)I
/*     */     //   39: istore #4
/*     */     //   41: aload_0
/*     */     //   42: pop
/*     */     //   43: getstatic mcheli/vehicle/MCH_GuiVehicle.scaleFactor : I
/*     */     //   46: i2f
/*     */     //   47: invokestatic glLineWidth : (F)V
/*     */     //   50: aload_3
/*     */     //   51: aload_1
/*     */     //   52: invokevirtual getCameraMode : (Lnet/minecraft/entity/player/EntityPlayer;)I
/*     */     //   55: iconst_1
/*     */     //   56: if_icmpne -> 63
/*     */     //   59: aload_0
/*     */     //   60: invokevirtual drawNightVisionNoise : ()V
/*     */     //   63: aload_3
/*     */     //   64: aload_1
/*     */     //   65: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
/*     */     //   68: ifeq -> 87
/*     */     //   71: aload_3
/*     */     //   72: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   75: ifnull -> 87
/*     */     //   78: aload_0
/*     */     //   79: aload_3
/*     */     //   80: aload_3
/*     */     //   81: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
/*     */     //   84: invokevirtual drawTvMissileNoise : (Lmcheli/aircraft/MCH_EntityAircraft;Lmcheli/weapon/MCH_EntityTvMissile;)V
/*     */     //   87: aload_0
/*     */     //   88: aload_3
/*     */     //   89: invokevirtual drawDebugtInfo : (Lmcheli/aircraft/MCH_EntityAircraft;)V
/*     */     //   92: iload_2
/*     */     //   93: ifeq -> 109
/*     */     //   96: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   99: pop
/*     */     //   100: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
/*     */     //   103: getfield prmBool : Z
/*     */     //   106: ifeq -> 123
/*     */     //   109: aload_0
/*     */     //   110: aload_3
/*     */     //   111: aload_1
/*     */     //   112: iload #4
/*     */     //   114: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
/*     */     //   117: aload_0
/*     */     //   118: aload_3
/*     */     //   119: aload_1
/*     */     //   120: invokevirtual drawKeyBind : (Lmcheli/vehicle/MCH_EntityVehicle;Lnet/minecraft/entity/player/EntityPlayer;)V
/*     */     //   123: aload_0
/*     */     //   124: aload_3
/*     */     //   125: sipush #-14066
/*     */     //   128: iload #4
/*     */     //   130: invokevirtual drawHitBullet : (Lmcheli/aircraft/MCH_EntityAircraft;II)V
/*     */     //   133: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #33	-> 0
/*     */     //   #35	-> 17
/*     */     //   #38	-> 18
/*     */     //   #39	-> 26
/*     */     //   #40	-> 34
/*     */     //   #42	-> 41
/*     */     //   #45	-> 50
/*     */     //   #47	-> 59
/*     */     //   #49	-> 63
/*     */     //   #51	-> 78
/*     */     //   #53	-> 87
/*     */     //   #55	-> 92
/*     */     //   #57	-> 109
/*     */     //   #58	-> 117
/*     */     //   #62	-> 123
/*     */     //   #63	-> 133
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	134	0	this	Lmcheli/vehicle/MCH_GuiVehicle;
/*     */     //   0	134	1	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	134	2	isThirdPersonView	Z
/*     */     //   26	108	3	vehicle	Lmcheli/vehicle/MCH_EntityVehicle;
/*     */     //   41	93	4	seatID	I
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
/*     */   public void drawKeyBind(MCH_EntityVehicle vehicle, EntityPlayer player) {
/*  67 */     if (MCH_Config.HideKeybind.prmBool)
/*     */       return; 
/*  69 */     MCH_VehicleInfo info = vehicle.getVehicleInfo();
/*  70 */     if (info == null) {
/*     */       return;
/*     */     }
/*     */     
/*  74 */     int colorActive = -1342177281;
/*  75 */     int colorInactive = -1349546097;
/*  76 */     int RX = this.centerX + 120;
/*  77 */     int LX = this.centerX - 200;
/*     */ 
/*     */     
/*  80 */     if (vehicle.haveFlare()) {
/*     */ 
/*     */       
/*  83 */       int c = vehicle.isFlarePreparation() ? colorInactive : colorActive;
/*  84 */       String str = "Flare : " + MCH_KeyName.getDescOrName(MCH_Config.KeyFlare.prmInt);
/*  85 */       drawString(str, RX, this.centerY - 50, c);
/*     */     } 
/*     */ 
/*     */     
/*  89 */     if (vehicle.func_70302_i_() > 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (vehicle.getTowChainEntity() != null && !(vehicle.getTowChainEntity()).field_70128_L) {
/*     */ 
/*     */       
/*  98 */       String str = "Drop  : " + MCH_KeyName.getDescOrName(MCH_Config.KeyExtra.prmInt);
/*  99 */       drawString(str, RX, this.centerY - 30, colorActive);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (vehicle.camera.getCameraZoom() > 1.0F) {
/*     */ 
/*     */       
/* 107 */       String str = "Zoom : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 108 */       drawString(str, LX, this.centerY - 80, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 112 */     MCH_WeaponSet ws = vehicle.getCurrentWeapon((Entity)player);
/* 113 */     if (vehicle.getWeaponNum() > 1) {
/*     */       
/* 115 */       String str = "Weapon : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchWeapon2.prmInt);
/* 116 */       drawString(str, LX, this.centerY - 70, colorActive);
/*     */     } 
/* 118 */     if ((ws.getCurrentWeapon()).numMode > 0) {
/*     */       
/* 120 */       String str = "WeaponMode : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwWeaponMode.prmInt);
/* 121 */       drawString(str, LX, this.centerY - 60, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (info.isEnableNightVision) {
/*     */       
/* 127 */       String str = "CameraMode : " + MCH_KeyName.getDescOrName(MCH_Config.KeyCameraMode.prmInt);
/* 128 */       drawString(str, LX, this.centerY - 50, colorActive);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     String msg = "Dismount all : LShift";
/* 133 */     drawString(msg, LX, this.centerY - 40, colorActive);
/*     */     
/* 135 */     if (vehicle.getSeatNum() >= 2) {
/*     */ 
/*     */       
/* 138 */       msg = "Dismount : " + MCH_KeyName.getDescOrName(MCH_Config.KeyUnmount.prmInt);
/* 139 */       drawString(msg, LX, this.centerY - 30, colorActive);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_GuiVehicle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */