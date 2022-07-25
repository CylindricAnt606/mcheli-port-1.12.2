 package mcheli.mcheli.helicopter;

 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_KeyName;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.aircraft.MCH_AircraftCommonGui;
 import mcheli.mcheli.aircraft.MCH_AircraftInfo;
 import mcheli.mcheli.aircraft.MCH_EntityAircraft;
 import mcheli.mcheli.helicopter.MCH_EntityHeli;
 import mcheli.mcheli.helicopter.MCH_HeliInfo;
 import net.minecraft.client.Minecraft;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraftforge.fml.relauncher.Side;
 import net.minecraftforge.fml.relauncher.SideOnly;
 import org.lwjgl.input.Keyboard;

 //TODO: complete mess (bytecode)

 @SideOnly(Side.CLIENT)
 public class MCH_GuiHeli
   extends MCH_AircraftCommonGui
 {
   public MCH_GuiHeli(Minecraft minecraft) {
     super(minecraft);
   }


   public boolean isDrawGui(EntityPlayer player) {
     return MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player) instanceof MCH_EntityHeli;
   }

   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
     // Byte code:
     //   0: aload_1
     //   1: invokestatic getAircraft_RiddenOrControl : (Lnet/minecraft/entity/Entity;)Lmcheli/aircraft/MCH_EntityAircraft;
     //   4: astore_3
     //   5: aload_3
     //   6: instanceof mcheli/helicopter/MCH_EntityHeli
     //   9: ifeq -> 19
     //   12: aload_3
     //   13: invokevirtual isDestroyed : ()Z
     //   16: ifeq -> 20
     //   19: return
     //   20: aload_3
     //   21: checkcast mcheli/helicopter/MCH_EntityHeli
     //   24: astore #4
     //   26: aload_3
     //   27: aload_1
     //   28: invokevirtual getSeatIdByEntity : (Lnet/minecraft/entity/Entity;)I
     //   31: istore #5
     //   33: aload_0
     //   34: pop
     //   35: getstatic mcheli/helicopter/MCH_GuiHeli.scaleFactor : I
     //   38: i2f
     //   39: invokestatic glLineWidth : (F)V
     //   42: aload #4
     //   44: aload_1
     //   45: invokevirtual getCameraMode : (Lnet/minecraft/entity/player/EntityPlayer;)I
     //   48: iconst_1
     //   49: if_icmpne -> 56
     //   52: aload_0
     //   53: invokevirtual drawNightVisionNoise : ()V
     //   56: iload_2
     //   57: ifeq -> 73
     //   60: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
     //   63: pop
     //   64: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
     //   67: getfield prmBool : Z
     //   70: ifeq -> 105
     //   73: iload #5
     //   75: ifne -> 97
     //   78: aload #4
     //   80: aload_1
     //   81: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
     //   84: ifeq -> 97
     //   87: aload_0
     //   88: aload_3
     //   89: aload_1
     //   90: iconst_1
     //   91: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
     //   94: goto -> 105
     //   97: aload_0
     //   98: aload_3
     //   99: aload_1
     //   100: iload #5
     //   102: invokevirtual drawHud : (Lmcheli/aircraft/MCH_EntityAircraft;Lnet/minecraft/entity/player/EntityPlayer;I)V
     //   105: aload_0
     //   106: aload #4
     //   108: invokevirtual drawDebugtInfo : (Lmcheli/aircraft/MCH_EntityAircraft;)V
     //   111: aload #4
     //   113: aload_1
     //   114: invokevirtual getIsGunnerMode : (Lnet/minecraft/entity/Entity;)Z
     //   117: ifne -> 159
     //   120: iload_2
     //   121: ifeq -> 137
     //   124: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
     //   127: pop
     //   128: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
     //   131: getfield prmBool : Z
     //   134: ifeq -> 146
     //   137: aload_0
     //   138: aload #4
     //   140: aload_1
     //   141: iload #5
     //   143: invokevirtual drawKeyBind : (Lmcheli/helicopter/MCH_EntityHeli;Lnet/minecraft/entity/player/EntityPlayer;I)V
     //   146: aload_0
     //   147: aload #4
     //   149: ldc -14101432
     //   151: iload #5
     //   153: invokevirtual drawHitBullet : (Lmcheli/aircraft/MCH_EntityAircraft;II)V
     //   156: goto -> 227
     //   159: iload_2
     //   160: ifeq -> 176
     //   163: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
     //   166: pop
     //   167: getstatic mcheli/MCH_Config.DisplayHUDThirdPerson : Lmcheli/MCH_ConfigPrm;
     //   170: getfield prmBool : Z
     //   173: ifeq -> 217
     //   176: aload #4
     //   178: invokevirtual getTVMissile : ()Lmcheli/weapon/MCH_EntityTvMissile;
     //   181: astore #6
     //   183: aload #4
     //   185: aload_1
     //   186: invokevirtual isMissileCameraMode : (Lnet/minecraft/entity/Entity;)Z
     //   189: ifne -> 204
     //   192: aload_0
     //   193: aload #4
     //   195: aload_1
     //   196: iload #5
     //   198: invokevirtual drawKeyBind : (Lmcheli/helicopter/MCH_EntityHeli;Lnet/minecraft/entity/player/EntityPlayer;I)V
     //   201: goto -> 217
     //   204: aload #6
     //   206: ifnull -> 217
     //   209: aload_0
     //   210: aload #4
     //   212: aload #6
     //   214: invokevirtual drawTvMissileNoise : (Lmcheli/aircraft/MCH_EntityAircraft;Lmcheli/weapon/MCH_EntityTvMissile;)V
     //   217: aload_0
     //   218: aload #4
     //   220: ldc -805306369
     //   222: iload #5
     //   224: invokevirtual drawHitBullet : (Lmcheli/aircraft/MCH_EntityAircraft;II)V
     //   227: return
     // Line number table:
     //   Java source line number -> byte code offset
     //   #35	-> 0
     //   #37	-> 5
     //   #39	-> 19
     //   #42	-> 20
     //   #43	-> 26
     //   #45	-> 33
     //   #48	-> 42
     //   #50	-> 52
     //   #53	-> 56
     //   #55	-> 73
     //   #57	-> 87
     //   #61	-> 97
     //   #65	-> 105
     //   #68	-> 111
     //   #70	-> 120
     //   #73	-> 137
     //   #76	-> 146
     //   #81	-> 159
     //   #83	-> 176
     //   #86	-> 183
     //   #89	-> 192
     //   #92	-> 204
     //   #94	-> 209
     //   #98	-> 217
     //   #100	-> 227
     // Local variable table:
     //   start	length	slot	name	descriptor
     //   183	34	6	tvmissile	Lmcheli/weapon/MCH_EntityTvMissile;
     //   0	228	0	this	Lmcheli/helicopter/MCH_GuiHeli;
     //   0	228	1	player	Lnet/minecraft/entity/player/EntityPlayer;
     //   0	228	2	isThirdPersonView	Z
     //   5	223	3	ac	Lmcheli/aircraft/MCH_EntityAircraft;
     //   26	202	4	heli	Lmcheli/helicopter/MCH_EntityHeli;
     //   33	195	5	seatID	I
   }

   public void drawKeyBind(MCH_EntityHeli heli, EntityPlayer player, int seatID) {
     if (MCH_Config.HideKeybind.prmBool)
       return;
     MCH_HeliInfo info = heli.getHeliInfo();
     if (info == null) {
       return;
     }

     int colorActive = -1342177281;
     int colorInactive = -1349546097;
     int RX = this.centerX + 120;
     int LX = this.centerX - 200;

     drawKeyBind((MCH_EntityAircraft)heli, (MCH_AircraftInfo)info, player, seatID, RX, LX, colorActive, colorInactive);

     if (seatID == 0 && info.isEnableGunnerMode) if (!Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {

         int c = heli.isHoveringMode() ? colorInactive : colorActive;
         String msg = (heli.getIsGunnerMode((Entity)player) ? "Normal" : "Gunner") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchMode.prmInt);

         drawString(msg, RX, this.centerY - 70, c);
       }

     if (seatID > 0 && heli.canSwitchGunnerModeOtherSeat(player)) {

       String msg = (heli.getIsGunnerMode((Entity)player) ? "Normal" : "Camera") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchMode.prmInt);

       drawString(msg, RX, this.centerY - 40, colorActive);
     }

     if (seatID == 0) if (!Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {
         int c = heli.getIsGunnerMode((Entity)player) ? colorInactive : colorActive;
         String msg = (heli.getIsGunnerMode((Entity)player) ? "Normal" : "Hovering") + " : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwitchHovering.prmInt);

         drawString(msg, RX, this.centerY - 60, c);
       }

     if (seatID == 0)
     {
       if (heli.getTowChainEntity() != null && !(heli.getTowChainEntity()).field_70128_L) {
         String msg = "Drop  : " + MCH_KeyName.getDescOrName(MCH_Config.KeyExtra.prmInt);
         drawString(msg, RX, this.centerY - 30, colorActive);
       }
       else if (info.isEnableFoldBlade && MCH_Lib.getBlockIdY(heli.field_70170_p, heli.field_70165_t, heli.field_70163_u, heli.field_70161_v, 1, -2, true) > 0 && heli.getCurrentThrottle() <= 0.01D) {

         String msg = "FoldBlade  : " + MCH_KeyName.getDescOrName(MCH_Config.KeyExtra.prmInt);
         drawString(msg, RX, this.centerY - 30, colorActive);
       }
     }

     if ((heli.getIsGunnerMode((Entity)player) || heli.isUAV()) && info.cameraZoom > 1) {
       String msg = "Zoom : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
       drawString(msg, LX, this.centerY - 80, colorActive);
     }
     else if (seatID == 0) {

       if (heli.canFoldHatch() || heli.canUnfoldHatch()) {

         String msg = "OpenHatch : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
         drawString(msg, LX, this.centerY - 80, colorActive);
       }
     }
   }
 }