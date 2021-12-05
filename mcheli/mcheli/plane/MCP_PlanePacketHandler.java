/*     */ package mcheli.mcheli.plane;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_PlanePacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCP_PlanePacketHandler
/*     */ {
/*     */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/*  16 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  22 */     MCP_PlanePacketPlayerControl pc = new MCP_PlanePacketPlayerControl();
/*  23 */     pc.readData(data);
/*     */     
/*  25 */     boolean isPilot = true;
/*     */     
/*  27 */     MCP_EntityPlane plane = null;
/*  28 */     if (player.field_70154_o instanceof MCP_EntityPlane) {
/*     */       
/*  30 */       plane = (MCP_EntityPlane)player.field_70154_o;
/*     */     }
/*  32 */     else if (player.field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/*  34 */       if (((MCH_EntitySeat)player.field_70154_o).getParent() instanceof MCP_EntityPlane)
/*     */       {
/*  36 */         plane = (MCP_EntityPlane)((MCH_EntitySeat)player.field_70154_o).getParent();
/*     */       }
/*     */     }
/*  39 */     else if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*     */       
/*  41 */       MCH_EntityUavStation uavStation = (MCH_EntityUavStation)player.field_70154_o;
/*  42 */       if (uavStation.getControlAircract() instanceof MCP_EntityPlane)
/*     */       {
/*  44 */         plane = (MCP_EntityPlane)uavStation.getControlAircract();
/*     */       }
/*     */     } 
/*     */     
/*  48 */     if (plane == null)
/*     */       return; 
/*  50 */     MCP_EntityPlane mCP_EntityPlane1 = plane;
/*     */ 
/*     */     
/*  53 */     if (pc.isUnmount == 1) {
/*     */       
/*  55 */       mCP_EntityPlane1.unmountEntity();
/*     */     }
/*  57 */     else if (pc.isUnmount == 2) {
/*     */       
/*  59 */       mCP_EntityPlane1.unmountCrew();
/*     */     }
/*  61 */     else if (pc.ejectSeat) {
/*     */       
/*  63 */       mCP_EntityPlane1.ejectSeat((Entity)player);
/*     */     }
/*     */     else {
/*     */       
/*  67 */       if (pc.switchVtol == 0) plane.swithVtolMode(false); 
/*  68 */       if (pc.switchVtol == 1) plane.swithVtolMode(true); 
/*  69 */       if (pc.switchMode == 0) mCP_EntityPlane1.switchGunnerMode(false); 
/*  70 */       if (pc.switchMode == 1) mCP_EntityPlane1.switchGunnerMode(true); 
/*  71 */       if (pc.switchMode == 2) mCP_EntityPlane1.switchHoveringMode(false); 
/*  72 */       if (pc.switchMode == 3) mCP_EntityPlane1.switchHoveringMode(true);
/*     */       
/*  74 */       if (pc.switchSearchLight) mCP_EntityPlane1.setSearchLight(!mCP_EntityPlane1.isSearchLightON()); 
/*  75 */       if (pc.switchCameraMode > 0) mCP_EntityPlane1.switchCameraMode(player, pc.switchCameraMode - 1); 
/*  76 */       if (pc.switchWeapon >= 0) mCP_EntityPlane1.switchWeapon((Entity)player, pc.switchWeapon); 
/*  77 */       if (pc.useWeapon) {
/*     */         
/*  79 */         MCH_WeaponParam prm = new MCH_WeaponParam();
/*  80 */         prm.entity = (Entity)plane;
/*  81 */         prm.user = (Entity)player;
/*  82 */         prm.setPosAndRot(pc.useWeaponPosX, pc.useWeaponPosY, pc.useWeaponPosZ, 0.0F, 0.0F);
/*  83 */         prm.option1 = pc.useWeaponOption1;
/*  84 */         prm.option2 = pc.useWeaponOption2;
/*  85 */         mCP_EntityPlane1.useCurrentWeapon(prm);
/*     */       } 
/*     */       
/*  88 */       if (mCP_EntityPlane1.isPilot((Entity)player)) {
/*     */         
/*  90 */         ((MCH_EntityAircraft)mCP_EntityPlane1).throttleUp = pc.throttleUp;
/*  91 */         ((MCH_EntityAircraft)mCP_EntityPlane1).throttleDown = pc.throttleDown;
/*  92 */         ((MCH_EntityAircraft)mCP_EntityPlane1).moveLeft = pc.moveLeft;
/*  93 */         ((MCH_EntityAircraft)mCP_EntityPlane1).moveRight = pc.moveRight;
/*     */       } 
/*     */       
/*  96 */       if (pc.useFlareType > 0) mCP_EntityPlane1.useFlare(pc.useFlareType); 
/*  97 */       if (pc.openGui) mCP_EntityPlane1.openGui(player); 
/*  98 */       if (pc.switchHatch > 0)
/*     */       {
/* 100 */         if (mCP_EntityPlane1.getAcInfo().haveHatch()) {
/*     */           
/* 102 */           mCP_EntityPlane1.foldHatch((pc.switchHatch == 2));
/*     */         }
/*     */         else {
/*     */           
/* 106 */           plane.foldWing((pc.switchHatch == 2));
/*     */         } 
/*     */       }
/* 109 */       if (pc.switchFreeLook > 0) mCP_EntityPlane1.switchFreeLookMode((pc.switchFreeLook == 1)); 
/* 110 */       if (pc.switchGear == 1) mCP_EntityPlane1.foldLandingGear(); 
/* 111 */       if (pc.switchGear == 2) mCP_EntityPlane1.unfoldLandingGear(); 
/* 112 */       if (pc.putDownRack == 1) mCP_EntityPlane1.mountEntityToRack(); 
/* 113 */       if (pc.putDownRack == 2) mCP_EntityPlane1.unmountEntityFromRack(); 
/* 114 */       if (pc.putDownRack == 3) mCP_EntityPlane1.rideRack(); 
/* 115 */       if (pc.isUnmount == 3) mCP_EntityPlane1.unmountAircraft(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_PlanePacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */