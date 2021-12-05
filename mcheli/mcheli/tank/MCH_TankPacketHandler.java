/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_TankPacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_TankPacketHandler
/*     */ {
/*     */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/*  16 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  22 */     MCH_TankPacketPlayerControl pc = new MCH_TankPacketPlayerControl();
/*  23 */     pc.readData(data);
/*     */     
/*  25 */     boolean isPilot = true;
/*     */     
/*  27 */     MCH_EntityTank tank = null;
/*  28 */     if (player.field_70154_o instanceof MCH_EntityTank) {
/*     */       
/*  30 */       tank = (MCH_EntityTank)player.field_70154_o;
/*     */     }
/*  32 */     else if (player.field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/*  34 */       if (((MCH_EntitySeat)player.field_70154_o).getParent() instanceof MCH_EntityTank)
/*     */       {
/*  36 */         tank = (MCH_EntityTank)((MCH_EntitySeat)player.field_70154_o).getParent();
/*     */       }
/*     */     }
/*  39 */     else if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*     */       
/*  41 */       MCH_EntityUavStation uavStation = (MCH_EntityUavStation)player.field_70154_o;
/*  42 */       if (uavStation.getControlAircract() instanceof MCH_EntityTank)
/*     */       {
/*  44 */         tank = (MCH_EntityTank)uavStation.getControlAircract();
/*     */       }
/*     */     } 
/*     */     
/*  48 */     if (tank == null)
/*     */       return; 
/*  50 */     MCH_EntityTank mCH_EntityTank1 = tank;
/*     */ 
/*     */     
/*  53 */     if (pc.isUnmount == 1) {
/*     */       
/*  55 */       mCH_EntityTank1.unmountEntity();
/*     */     }
/*  57 */     else if (pc.isUnmount == 2) {
/*     */       
/*  59 */       mCH_EntityTank1.unmountCrew();
/*     */     }
/*  61 */     else if (pc.ejectSeat) {
/*     */       
/*  63 */       mCH_EntityTank1.ejectSeat((Entity)player);
/*     */     }
/*     */     else {
/*     */       
/*  67 */       if (pc.switchMode == 0) mCH_EntityTank1.switchGunnerMode(false); 
/*  68 */       if (pc.switchMode == 1) mCH_EntityTank1.switchGunnerMode(true); 
/*  69 */       if (pc.switchMode == 2) mCH_EntityTank1.switchHoveringMode(false); 
/*  70 */       if (pc.switchMode == 3) mCH_EntityTank1.switchHoveringMode(true);
/*     */       
/*  72 */       if (pc.switchSearchLight) mCH_EntityTank1.setSearchLight(!mCH_EntityTank1.isSearchLightON()); 
/*  73 */       if (pc.switchCameraMode > 0) mCH_EntityTank1.switchCameraMode(player, pc.switchCameraMode - 1); 
/*  74 */       if (pc.switchWeapon >= 0) mCH_EntityTank1.switchWeapon((Entity)player, pc.switchWeapon); 
/*  75 */       if (pc.useWeapon) {
/*     */         
/*  77 */         MCH_WeaponParam prm = new MCH_WeaponParam();
/*  78 */         prm.entity = (Entity)tank;
/*  79 */         prm.user = (Entity)player;
/*  80 */         prm.setPosAndRot(pc.useWeaponPosX, pc.useWeaponPosY, pc.useWeaponPosZ, 0.0F, 0.0F);
/*  81 */         prm.option1 = pc.useWeaponOption1;
/*  82 */         prm.option2 = pc.useWeaponOption2;
/*  83 */         mCH_EntityTank1.useCurrentWeapon(prm);
/*     */       } 
/*     */       
/*  86 */       if (mCH_EntityTank1.isPilot((Entity)player)) {
/*     */         
/*  88 */         ((MCH_EntityAircraft)mCH_EntityTank1).throttleUp = pc.throttleUp;
/*  89 */         ((MCH_EntityAircraft)mCH_EntityTank1).throttleDown = pc.throttleDown;
/*  90 */         double dx = ((MCH_EntityAircraft)mCH_EntityTank1).field_70165_t - ((MCH_EntityAircraft)mCH_EntityTank1).field_70169_q;
/*  91 */         double dz = ((MCH_EntityAircraft)mCH_EntityTank1).field_70161_v - ((MCH_EntityAircraft)mCH_EntityTank1).field_70166_s;
/*  92 */         double dist = dx * dx + dz * dz;
/*  93 */         if (pc.useBrake && mCH_EntityTank1.getCurrentThrottle() <= 0.03D && dist < 0.01D) {
/*     */           
/*  95 */           ((MCH_EntityAircraft)mCH_EntityTank1).moveLeft = false;
/*  96 */           ((MCH_EntityAircraft)mCH_EntityTank1).moveRight = false;
/*     */         } 
/*  98 */         mCH_EntityTank1.setBrake(pc.useBrake);
/*     */       } 
/*     */       
/* 101 */       if (pc.useFlareType > 0) mCH_EntityTank1.useFlare(pc.useFlareType); 
/* 102 */       if (pc.openGui) mCH_EntityTank1.openGui(player); 
/* 103 */       if (pc.switchHatch > 0)
/*     */       {
/* 105 */         if (mCH_EntityTank1.getAcInfo().haveHatch())
/*     */         {
/* 107 */           mCH_EntityTank1.foldHatch((pc.switchHatch == 2));
/*     */         }
/*     */       }
/* 110 */       if (pc.switchFreeLook > 0) mCH_EntityTank1.switchFreeLookMode((pc.switchFreeLook == 1)); 
/* 111 */       if (pc.switchGear == 1) mCH_EntityTank1.foldLandingGear(); 
/* 112 */       if (pc.switchGear == 2) mCH_EntityTank1.unfoldLandingGear(); 
/* 113 */       if (pc.putDownRack == 1) mCH_EntityTank1.mountEntityToRack(); 
/* 114 */       if (pc.putDownRack == 2) mCH_EntityTank1.unmountEntityFromRack(); 
/* 115 */       if (pc.putDownRack == 3) mCH_EntityTank1.rideRack(); 
/* 116 */       if (pc.isUnmount == 3) mCH_EntityTank1.unmountAircraft(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_TankPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */