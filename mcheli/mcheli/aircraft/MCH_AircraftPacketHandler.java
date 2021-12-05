/*     */ package mcheli.mcheli.aircraft;
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketIndNotifyAmmoNum;
/*     */ import mcheli.aircraft.MCH_PacketIndReload;
/*     */ import mcheli.aircraft.MCH_PacketIndRotation;
/*     */ import mcheli.aircraft.MCH_PacketNotifyAmmoNum;
/*     */ import mcheli.aircraft.MCH_PacketNotifyClientSetting;
/*     */ import mcheli.aircraft.MCH_PacketNotifyOnMountEntity;
/*     */ import mcheli.aircraft.MCH_PacketNotifyTVMissileEntity;
/*     */ import mcheli.aircraft.MCH_PacketNotifyWeaponID;
/*     */ import mcheli.aircraft.MCH_PacketSeatListRequest;
/*     */ import mcheli.aircraft.MCH_PacketSeatListResponse;
/*     */ import mcheli.aircraft.MCH_PacketSeatPlayerControl;
/*     */ import mcheli.aircraft.MCH_PacketStatusResponse;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_AircraftPacketHandler {
/*     */   public static void onPacketIndRotation(EntityPlayer player, ByteArrayDataInput data) {
/*  24 */     if (player == null || player.field_70170_p.field_72995_K)
/*     */       return; 
/*  26 */     MCH_PacketIndRotation req = new MCH_PacketIndRotation();
/*  27 */     req.readData(data);
/*     */ 
/*     */     
/*  30 */     if (req.entityID_Ac <= 0)
/*  31 */       return;  Entity e = player.field_70170_p.func_73045_a(req.entityID_Ac);
/*  32 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/*  34 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/*  35 */       ac.setRotRoll(req.roll);
/*  36 */       if (req.rollRev) {
/*     */         
/*  38 */         MCH_Lib.DbgLog(ac.field_70170_p, "onPacketIndRotation Error:req.rollRev y=%.2f, p=%.2f, r=%.2f", new Object[] { Float.valueOf(req.yaw), Float.valueOf(req.pitch), Float.valueOf(req.roll) });
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  43 */         if (ac.getRiddenByEntity() != null) {
/*     */           
/*  45 */           (ac.getRiddenByEntity()).field_70177_z = req.yaw;
/*  46 */           (ac.getRiddenByEntity()).field_70126_B = req.yaw;
/*     */         } 
/*     */         
/*  49 */         for (int sid = 0; sid < ac.getSeatNum(); sid++) {
/*     */           
/*  51 */           Entity entity = ac.getEntityBySeatId(1 + sid);
/*  52 */           if (entity != null)
/*     */           {
/*  54 */             entity.field_70177_z += (entity.field_70177_z <= 0.0F) ? 180.0F : -180.0F;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  59 */       ac.setRotYaw(req.yaw);
/*  60 */       ac.setRotPitch(req.pitch);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketOnMountEntity(EntityPlayer player, ByteArrayDataInput data) {
/*  67 */     if (player == null || !player.field_70170_p.field_72995_K)
/*     */       return; 
/*  69 */     MCH_PacketNotifyOnMountEntity req = new MCH_PacketNotifyOnMountEntity();
/*  70 */     req.readData(data);
/*     */     
/*  72 */     MCH_Lib.DbgLog(player.field_70170_p, "onPacketOnMountEntity.rcv:%d, %d, %d, %d", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)player)), Integer.valueOf(req.entityID_Ac), Integer.valueOf(req.entityID_rider), Integer.valueOf(req.seatID) });
/*     */ 
/*     */ 
/*     */     
/*  76 */     if (req.entityID_Ac <= 0)
/*  77 */       return;  if (req.entityID_rider <= 0)
/*  78 */       return;  if (req.seatID < 0)
/*  79 */       return;  Entity e = player.field_70170_p.func_73045_a(req.entityID_Ac);
/*  80 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/*  82 */       MCH_Lib.DbgLog(player.field_70170_p, "onPacketOnMountEntity:" + W_Entity.getEntityId((Entity)player), new Object[0]);
/*     */       
/*  84 */       Entity rider = player.field_70170_p.func_73045_a(req.entityID_rider);
/*  85 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyAmmoNum(EntityPlayer player, ByteArrayDataInput data) {
/*  92 */     if (player == null || !player.field_70170_p.field_72995_K)
/*     */       return; 
/*  94 */     MCH_PacketNotifyAmmoNum status = new MCH_PacketNotifyAmmoNum();
/*  95 */     status.readData(data);
/*     */ 
/*     */     
/*  98 */     if (status.entityID_Ac <= 0)
/*  99 */       return;  Entity e = player.field_70170_p.func_73045_a(status.entityID_Ac);
/* 100 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/* 102 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/* 103 */       String msg = "onPacketNotifyAmmoNum:";
/* 104 */       msg = msg + ((ac.getAcInfo() != null) ? (ac.getAcInfo()).displayName : "null") + ":";
/*     */       
/* 106 */       if (status.all) {
/*     */         
/* 108 */         msg = msg + "All=true, Num=" + status.num;
/* 109 */         for (int i = 0; i < ac.getWeaponNum() && i < status.num; i++) {
/*     */           
/* 111 */           ac.getWeapon(i).setAmmoNum(status.ammo[i]);
/* 112 */           ac.getWeapon(i).setRestAllAmmoNum(status.restAmmo[i]);
/* 113 */           msg = msg + ", [" + status.ammo[i] + "/" + status.restAmmo[i] + "]";
/*     */         } 
/*     */         
/* 116 */         MCH_Lib.DbgLog(e.field_70170_p, msg, new Object[0]);
/*     */ 
/*     */       
/*     */       }
/* 120 */       else if (status.weaponID < ac.getWeaponNum()) {
/*     */         
/* 122 */         msg = msg + "All=false, WeaponID=" + status.weaponID + ", " + status.ammo[0] + ", " + status.restAmmo[0];
/* 123 */         ac.getWeapon(status.weaponID).setAmmoNum(status.ammo[0]);
/* 124 */         ac.getWeapon(status.weaponID).setRestAllAmmoNum(status.restAmmo[0]);
/*     */         
/* 126 */         MCH_Lib.DbgLog(e.field_70170_p, msg, new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/* 130 */         MCH_Lib.DbgLog(e.field_70170_p, "Error:" + status.weaponID, new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketStatusRequest(EntityPlayer player, ByteArrayDataInput data) {
/* 140 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/* 142 */     MCH_PacketStatusRequest req = new MCH_PacketStatusRequest();
/* 143 */     req.readData(data);
/*     */     
/* 145 */     if (req.entityID_AC <= 0)
/* 146 */       return;  Entity e = player.field_70170_p.func_73045_a(req.entityID_AC);
/* 147 */     if (e instanceof MCH_EntityAircraft)
/*     */     {
/* 149 */       MCH_PacketStatusResponse.sendStatus((MCH_EntityAircraft)e, player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketIndNotifyAmmoNum(EntityPlayer player, ByteArrayDataInput data) {
/* 157 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/* 159 */     MCH_PacketIndNotifyAmmoNum req = new MCH_PacketIndNotifyAmmoNum();
/* 160 */     req.readData(data);
/*     */     
/* 162 */     if (req.entityID_Ac <= 0)
/* 163 */       return;  Entity e = player.field_70170_p.func_73045_a(req.entityID_Ac);
/* 164 */     if (e instanceof MCH_EntityAircraft)
/*     */     {
/* 166 */       if (req.weaponID >= 0) {
/*     */         
/* 168 */         MCH_PacketNotifyAmmoNum.sendAmmoNum((MCH_EntityAircraft)e, player, req.weaponID);
/*     */       }
/*     */       else {
/*     */         
/* 172 */         MCH_PacketNotifyAmmoNum.sendAllAmmoNum((MCH_EntityAircraft)e, player);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketIndReload(EntityPlayer player, ByteArrayDataInput data) {
/* 181 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/* 183 */     MCH_PacketIndReload ind = new MCH_PacketIndReload();
/* 184 */     ind.readData(data);
/*     */     
/* 186 */     if (ind.entityID_Ac <= 0)
/* 187 */       return;  Entity e = player.field_70170_p.func_73045_a(ind.entityID_Ac);
/* 188 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/* 190 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/* 191 */       MCH_Lib.DbgLog(e.field_70170_p, "onPacketIndReload :%s", new Object[] { (ac.getAcInfo()).displayName });
/* 192 */       ac.supplyAmmo(ind.weaponID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketStatusResponse(EntityPlayer player, ByteArrayDataInput data) {
/* 200 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/* 202 */     MCH_PacketStatusResponse status = new MCH_PacketStatusResponse();
/* 203 */     status.readData(data);
/*     */     
/* 205 */     String msg = "onPacketStatusResponse:";
/*     */     
/* 207 */     if (status.entityID_AC <= 0)
/*     */       return; 
/* 209 */     msg = msg + "EID=" + status.entityID_AC + ":";
/*     */     
/* 211 */     Entity e = player.field_70170_p.func_73045_a(status.entityID_AC);
/* 212 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/* 214 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/* 215 */       if (status.seatNum > 0 && status.weaponIDs != null && status.weaponIDs.length == status.seatNum) {
/*     */ 
/*     */ 
/*     */         
/* 219 */         msg = msg + "seatNum=" + status.seatNum + ":";
/* 220 */         for (int i = 0; i < status.seatNum; i++)
/*     */         {
/* 222 */           ac.updateWeaponID(i, status.weaponIDs[i]);
/* 223 */           msg = msg + "[" + i + "," + status.weaponIDs[i] + "]";
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 228 */         msg = msg + "Error seatNum=" + status.seatNum;
/*     */       } 
/*     */     } 
/* 231 */     MCH_Lib.DbgLog(true, msg, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyWeaponID(EntityPlayer player, ByteArrayDataInput data) {
/* 238 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/* 240 */     MCH_PacketNotifyWeaponID status = new MCH_PacketNotifyWeaponID();
/* 241 */     status.readData(data);
/*     */     
/* 243 */     if (status.entityID_Ac <= 0)
/*     */       return; 
/* 245 */     Entity e = player.field_70170_p.func_73045_a(status.entityID_Ac);
/* 246 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/* 248 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/* 249 */       if (ac.isValidSeatID(status.seatID)) {
/*     */         
/* 251 */         ac.getWeapon(status.weaponID).setAmmoNum(status.ammo);
/* 252 */         ac.getWeapon(status.weaponID).setRestAllAmmoNum(status.restAmmo);
/* 253 */         MCH_Lib.DbgLog(true, "onPacketNotifyWeaponID:WeaponID=%d (%d / %d)", new Object[] { Integer.valueOf(status.weaponID), Short.valueOf(status.ammo), Short.valueOf(status.restAmmo) });
/*     */ 
/*     */         
/* 256 */         if (W_Lib.isClientPlayer(ac.getEntityBySeatId(status.seatID))) {
/*     */ 
/*     */           
/* 259 */           MCH_Lib.DbgLog(true, "onPacketNotifyWeaponID:#discard:SeatID=%d, WeaponID=%d", new Object[] { Integer.valueOf(status.seatID), Integer.valueOf(status.weaponID) });
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 265 */           MCH_Lib.DbgLog(true, "onPacketNotifyWeaponID:SeatID=%d, WeaponID=%d", new Object[] { Integer.valueOf(status.seatID), Integer.valueOf(status.weaponID) });
/*     */           
/* 267 */           ac.updateWeaponID(status.seatID, status.weaponID);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyHitBullet(EntityPlayer player, ByteArrayDataInput data) {
/* 277 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/* 279 */     MCH_PacketNotifyHitBullet status = new MCH_PacketNotifyHitBullet();
/* 280 */     status.readData(data);
/*     */     
/* 282 */     if (status.entityID_Ac <= 0) {
/*     */       
/* 284 */       MCH_MOD.proxy.hitBullet();
/*     */     }
/*     */     else {
/*     */       
/* 288 */       Entity e = player.field_70170_p.func_73045_a(status.entityID_Ac);
/* 289 */       if (e instanceof MCH_EntityAircraft)
/*     */       {
/* 291 */         ((MCH_EntityAircraft)e).hitBullet();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketSeatListRequest(EntityPlayer player, ByteArrayDataInput data) {
/* 300 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/* 302 */     MCH_PacketSeatListRequest req = new MCH_PacketSeatListRequest();
/* 303 */     req.readData(data);
/*     */     
/* 305 */     if (req.entityID_AC <= 0)
/* 306 */       return;  Entity e = player.field_70170_p.func_73045_a(req.entityID_AC);
/* 307 */     if (e instanceof MCH_EntityAircraft)
/*     */     {
/* 309 */       MCH_PacketSeatListResponse.sendSeatList((MCH_EntityAircraft)e, player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketNotifyTVMissileEntity(EntityPlayer player, ByteArrayDataInput data) {
/* 316 */     if (player.field_70170_p.field_72995_K) {
/*     */       
/* 318 */       MCH_PacketNotifyTVMissileEntity packet = new MCH_PacketNotifyTVMissileEntity();
/* 319 */       packet.readData(data);
/*     */       
/* 321 */       if (packet.entityID_Ac <= 0)
/* 322 */         return;  if (packet.entityID_TVMissile <= 0)
/*     */         return; 
/* 324 */       Entity e = player.field_70170_p.func_73045_a(packet.entityID_Ac);
/* 325 */       if (e == null || !(e instanceof MCH_EntityAircraft))
/* 326 */         return;  MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/*     */       
/* 328 */       e = player.field_70170_p.func_73045_a(packet.entityID_TVMissile);
/* 329 */       if (e == null || !(e instanceof MCH_EntityTvMissile))
/*     */         return; 
/* 331 */       ((MCH_EntityTvMissile)e).shootingEntity = (Entity)player;
/*     */       
/* 333 */       ac.setTVMissile((MCH_EntityTvMissile)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacketSeatListResponse(EntityPlayer player, ByteArrayDataInput data) {
/* 345 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/* 347 */     MCH_PacketSeatListResponse seatList = new MCH_PacketSeatListResponse();
/* 348 */     seatList.readData(data);
/*     */     
/* 350 */     if (seatList.entityID_AC <= 0)
/*     */       return; 
/* 352 */     Entity e = player.field_70170_p.func_73045_a(seatList.entityID_AC);
/* 353 */     if (e instanceof MCH_EntityAircraft) {
/*     */       
/* 355 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)e;
/* 356 */       if (seatList.seatNum > 0 && seatList.seatNum == (ac.getSeats()).length && seatList.seatEntityID != null && seatList.seatEntityID.length == seatList.seatNum)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 361 */         for (int i = 0; i < seatList.seatNum; i++) {
/*     */           
/* 363 */           Entity entity = player.field_70170_p.func_73045_a(seatList.seatEntityID[i]);
/* 364 */           if (entity instanceof MCH_EntitySeat) {
/*     */             
/* 366 */             MCH_EntitySeat seat = (MCH_EntitySeat)entity;
/* 367 */             seat.seatID = i;
/* 368 */             seat.setParent(ac);
/* 369 */             seat.parentUniqueID = ac.getCommonUniqueId();
/* 370 */             ac.setSeat(i, seat);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/* 381 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 387 */     MCH_EntityAircraft ac = null;
/*     */     
/* 389 */     if (player.field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/* 391 */       MCH_EntitySeat seat = (MCH_EntitySeat)player.field_70154_o;
/* 392 */       ac = seat.getParent();
/*     */     }
/*     */     else {
/*     */       
/* 396 */       ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player);
/*     */     } 
/*     */     
/* 399 */     if (ac == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 404 */     MCH_PacketSeatPlayerControl pc = new MCH_PacketSeatPlayerControl();
/* 405 */     pc.readData(data);
/*     */ 
/*     */     
/* 408 */     if (pc.isUnmount)
/*     */     
/* 410 */     { ac.unmountEntityFromSeat((Entity)player); }
/*     */     
/* 412 */     else if (pc.switchSeat > 0)
/*     */     
/* 414 */     { if (pc.switchSeat == 3) {
/*     */         
/* 416 */         player.func_70078_a(null);
/* 417 */         ac.keepOnRideRotation = true;
/* 418 */         ac.interactFirst(player, true);
/*     */       } 
/* 420 */       if (pc.switchSeat == 1) ac.switchNextSeat((Entity)player); 
/* 421 */       if (pc.switchSeat == 2) ac.switchPrevSeat((Entity)player);
/*     */       
/*     */        }
/*     */     
/* 425 */     else if (pc.parachuting) { ac.unmount((Entity)player); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacket_ClientSetting(EntityPlayer player, ByteArrayDataInput data) {
/* 433 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 439 */     MCH_PacketNotifyClientSetting pc = new MCH_PacketNotifyClientSetting();
/* 440 */     pc.readData(data);
/*     */     
/* 442 */     MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player);
/*     */     
/* 444 */     if (ac != null) {
/*     */       
/* 446 */       int sid = ac.getSeatIdByEntity((Entity)player);
/* 447 */       if (sid == 0) {
/*     */         
/* 449 */         ac.cs_dismountAll = pc.dismountAll;
/* 450 */         ac.cs_heliAutoThrottleDown = pc.heliAutoThrottleDown;
/* 451 */         ac.cs_planeAutoThrottleDown = pc.planeAutoThrottleDown;
/* 452 */         ac.cs_tankAutoThrottleDown = pc.tankAutoThrottleDown;
/*     */       } 
/* 454 */       ac.camera.setShaderSupport(sid, Boolean.valueOf(pc.shaderSupport));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void onPacketNotifyInfoReloaded(EntityPlayer player, ByteArrayDataInput data) {
/*     */     MCH_EntityAircraft ac;
/* 460 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 466 */     MCH_PacketNotifyInfoReloaded pc = new MCH_PacketNotifyInfoReloaded();
/* 467 */     pc.readData(data);
/*     */     
/* 469 */     switch (pc.type) {
/*     */       
/*     */       case 0:
/* 472 */         ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player);
/* 473 */         if (ac != null && ac.getAcInfo() != null) {
/*     */           
/* 475 */           String name = (ac.getAcInfo()).name;
/* 476 */           for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
/*     */             
/* 478 */             List<MCH_EntityAircraft> list = world.field_72996_f;
/* 479 */             for (int i = 0; i < list.size(); i++) {
/*     */               
/* 481 */               if (list.get(i) instanceof MCH_EntityAircraft) {
/*     */                 
/* 483 */                 ac = list.get(i);
/* 484 */                 if (ac.getAcInfo() != null && (ac.getAcInfo()).name.equals(name)) {
/*     */ 
/*     */                   
/* 487 */                   ac.changeType(name);
/* 488 */                   ac.createSeats(UUID.randomUUID().toString());
/* 489 */                   ac.onAcInfoReloaded();
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 497 */         MCH_WeaponInfoManager.reload();
/* 498 */         for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
/*     */           
/* 500 */           List<MCH_EntityAircraft> list = world.field_72996_f;
/* 501 */           for (int i = 0; i < list.size(); i++) {
/*     */             
/* 503 */             if (list.get(i) instanceof MCH_EntityAircraft) {
/*     */               
/* 505 */               ac = list.get(i);
/* 506 */               if (ac.getAcInfo() != null) {
/*     */ 
/*     */                 
/* 509 */                 ac.changeType((ac.getAcInfo()).name);
/* 510 */                 ac.createSeats(UUID.randomUUID().toString());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */