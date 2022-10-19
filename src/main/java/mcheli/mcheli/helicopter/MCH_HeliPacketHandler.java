/*     */ package mcheli.mcheli.helicopter;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import mcheli.MCH_Achievement;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.chain.MCH_EntityChain;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliPacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_HeliPacketHandler
/*     */ {
/*     */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/*  21 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  27 */     MCH_HeliPacketPlayerControl pc = new MCH_HeliPacketPlayerControl();
/*  28 */     pc.readData(data);
/*     */     
/*  30 */     MCH_EntityHeli heli = null;
/*  31 */     if (player.field_70154_o instanceof MCH_EntityHeli) {
/*     */       
/*  33 */       heli = (MCH_EntityHeli)player.field_70154_o;
/*     */     }
/*  35 */     else if (player.field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/*  37 */       if (((MCH_EntitySeat)player.field_70154_o).getParent() instanceof MCH_EntityHeli)
/*     */       {
/*  39 */         heli = (MCH_EntityHeli)((MCH_EntitySeat)player.field_70154_o).getParent();
/*     */       }
/*     */     }
/*  42 */     else if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*     */       
/*  44 */       MCH_EntityUavStation uavStation = (MCH_EntityUavStation)player.field_70154_o;
/*  45 */       if (uavStation.getControlAircract() instanceof MCH_EntityHeli)
/*     */       {
/*  47 */         heli = (MCH_EntityHeli)uavStation.getControlAircract();
/*     */       }
/*     */     } 
/*     */     
/*  51 */     if (heli == null)
/*  52 */       return;  MCH_EntityHeli mCH_EntityHeli1 = heli;
/*     */ 
/*     */     
/*  55 */     if (pc.isUnmount == 1) {
/*     */       
/*  57 */       mCH_EntityHeli1.unmountEntity();
/*     */     }
/*  59 */     else if (pc.isUnmount == 2) {
/*     */       
/*  61 */       mCH_EntityHeli1.unmountCrew();
/*     */     }
/*     */     else {
/*     */       
/*  65 */       if (pc.switchFold == 0) heli.setFoldBladeStat((byte)3); 
/*  66 */       if (pc.switchFold == 1) heli.setFoldBladeStat((byte)1); 
/*  67 */       if (pc.switchMode == 0) mCH_EntityHeli1.switchGunnerMode(false); 
/*  68 */       if (pc.switchMode == 1) mCH_EntityHeli1.switchGunnerMode(true); 
/*  69 */       if (pc.switchMode == 2) mCH_EntityHeli1.switchHoveringMode(false); 
/*  70 */       if (pc.switchMode == 3) mCH_EntityHeli1.switchHoveringMode(true);
/*     */       
/*  72 */       if (pc.switchSearchLight) mCH_EntityHeli1.setSearchLight(!mCH_EntityHeli1.isSearchLightON()); 
/*  73 */       if (pc.switchCameraMode > 0) mCH_EntityHeli1.switchCameraMode(player, pc.switchCameraMode - 1); 
/*  74 */       if (pc.switchWeapon >= 0) mCH_EntityHeli1.switchWeapon((Entity)player, pc.switchWeapon); 
/*  75 */       if (pc.useWeapon) {
/*     */         
/*  77 */         MCH_WeaponParam prm = new MCH_WeaponParam();
/*  78 */         prm.entity = (Entity)mCH_EntityHeli1;
/*  79 */         prm.user = (Entity)player;
/*  80 */         prm.setPosAndRot(pc.useWeaponPosX, pc.useWeaponPosY, pc.useWeaponPosZ, 0.0F, 0.0F);
/*  81 */         prm.option1 = pc.useWeaponOption1;
/*  82 */         prm.option2 = pc.useWeaponOption2;
/*  83 */         mCH_EntityHeli1.useCurrentWeapon(prm);
/*     */       } 
/*     */       
/*  86 */       if (mCH_EntityHeli1.isPilot((Entity)player)) {
/*     */         
/*  88 */         ((MCH_EntityAircraft)mCH_EntityHeli1).throttleUp = pc.throttleUp;
/*  89 */         ((MCH_EntityAircraft)mCH_EntityHeli1).throttleDown = pc.throttleDown;
/*  90 */         ((MCH_EntityAircraft)mCH_EntityHeli1).moveLeft = pc.moveLeft;
/*  91 */         ((MCH_EntityAircraft)mCH_EntityHeli1).moveRight = pc.moveRight;
/*     */       } 
/*     */       
/*  94 */       if (pc.useFlareType > 0) mCH_EntityHeli1.useFlare(pc.useFlareType);
/*     */ 
/*     */       
/*  97 */       if (pc.unhitchChainId >= 0) {
/*     */         
/*  99 */         Entity e = player.field_70170_p.func_73045_a(pc.unhitchChainId);
/* 100 */         if (e instanceof MCH_EntityChain) {
/*     */           
/* 102 */           if (((MCH_EntityChain)e).towedEntity instanceof mcheli.container.MCH_EntityContainer)
/*     */           {
/* 104 */             if (MCH_Lib.getBlockIdY((Entity)heli, 3, -20) == 0)
/*     */             {
/* 106 */               MCH_Achievement.addStat((Entity)player, MCH_Achievement.reliefSupplies, 1);
/*     */             }
/*     */           }
/* 109 */           e.func_70106_y();
/*     */         } 
/*     */       } 
/*     */       
/* 113 */       if (pc.openGui) mCH_EntityHeli1.openGui(player); 
/* 114 */       if (pc.switchHatch > 0) mCH_EntityHeli1.foldHatch((pc.switchHatch == 2)); 
/* 115 */       if (pc.switchFreeLook > 0) mCH_EntityHeli1.switchFreeLookMode((pc.switchFreeLook == 1)); 
/* 116 */       if (pc.switchGear == 1) mCH_EntityHeli1.foldLandingGear(); 
/* 117 */       if (pc.switchGear == 2) mCH_EntityHeli1.unfoldLandingGear(); 
/* 118 */       if (pc.putDownRack == 1) mCH_EntityHeli1.mountEntityToRack(); 
/* 119 */       if (pc.putDownRack == 2) mCH_EntityHeli1.unmountEntityFromRack(); 
/* 120 */       if (pc.putDownRack == 3) mCH_EntityHeli1.rideRack(); 
/* 121 */       if (pc.isUnmount == 3) mCH_EntityHeli1.unmountAircraft(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_HeliPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */