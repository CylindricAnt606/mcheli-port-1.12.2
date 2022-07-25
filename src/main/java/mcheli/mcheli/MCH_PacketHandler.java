/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import mcheli.MCH_CommonPacketHandler;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_AircraftPacketHandler;
/*     */ import mcheli.block.MCH_DraftingTablePacketHandler;
/*     */ import mcheli.command.MCH_CommandPacketHandler;
/*     */ import mcheli.gltd.MCH_GLTDPacketHandler;
/*     */ import mcheli.helicopter.MCH_HeliPacketHandler;
/*     */ import mcheli.lweapon.MCH_LightWeaponPacketHandler;
/*     */ import mcheli.multiplay.MCH_MultiplayPacketHandler;
/*     */ import mcheli.plane.MCP_PlanePacketHandler;
/*     */ import mcheli.tank.MCH_TankPacketHandler;
/*     */ import mcheli.tool.MCH_ToolPacketHandler;
/*     */ import mcheli.uav.MCH_UavPacketHandler;
/*     */ import mcheli.vehicle.MCH_VehiclePacketHandler;
/*     */ import mcheli.wrapper.W_PacketHandler;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ public class MCH_PacketHandler
/*     */   extends W_PacketHandler
/*     */ {
/*     */   public void onPacket(ByteArrayDataInput data, EntityPlayer entityPlayer) {
/*  26 */     int msgid = getMessageId(data);
/*  27 */     switch (msgid) {
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/*  32 */         MCH_Lib.DbgLog(entityPlayer.field_70170_p, "MCH_PacketHandler.onPacket invalid MSGID=0x%X(%d)", new Object[] { Integer.valueOf(msgid), Integer.valueOf(msgid) });
/*     */         return;
/*     */ 
/*     */       
/*     */       case 268437520:
/*  37 */         MCH_CommonPacketHandler.onPacketEffectExplosion(entityPlayer, data);
/*     */         return;
/*     */       case 536872992:
/*  40 */         MCH_CommonPacketHandler.onPacketIndOpenScreen(entityPlayer, data);
/*     */         return;
/*     */       case 268437568:
/*  43 */         MCH_CommonPacketHandler.onPacketNotifyServerSettings(entityPlayer, data);
/*     */         return;
/*     */       case 536873984:
/*  46 */         MCH_CommonPacketHandler.onPacketNotifyLock(entityPlayer, data);
/*     */         return;
/*     */       
/*     */       case 536873088:
/*  50 */         MCH_MultiplayPacketHandler.onPacket_Command(entityPlayer, data);
/*     */         return;
/*     */       case 268437761:
/*  53 */         MCH_MultiplayPacketHandler.onPacket_NotifySpotedEntity(entityPlayer, data);
/*     */         return;
/*     */       case 268437762:
/*  56 */         MCH_MultiplayPacketHandler.onPacket_NotifyMarkPoint(entityPlayer, data);
/*     */         return;
/*     */       case 536873472:
/*  59 */         MCH_MultiplayPacketHandler.onPacket_LargeData(entityPlayer, data);
/*     */         return;
/*     */       case 536873473:
/*  62 */         MCH_MultiplayPacketHandler.onPacket_ModList(entityPlayer, data);
/*     */         return;
/*     */       case 268438032:
/*  65 */         MCH_MultiplayPacketHandler.onPacket_IndClient(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 268438272:
/*  70 */         MCH_CommandPacketHandler.onPacketTitle(entityPlayer, data);
/*     */         return;
/*     */       case 536873729:
/*  73 */         MCH_CommandPacketHandler.onPacketSave(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536873216:
/*  78 */         MCH_ToolPacketHandler.onPacket_IndSpotEntity(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536879120:
/*  83 */         MCH_HeliPacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536875104:
/*  88 */         MCH_AircraftPacketHandler.onPacketStatusRequest(entityPlayer, data);
/*     */         return;
/*     */       case 268439649:
/*  91 */         MCH_AircraftPacketHandler.onPacketStatusResponse(entityPlayer, data);
/*     */         return;
/*     */       case 536875024:
/*  94 */         MCH_AircraftPacketHandler.onPacketSeatListRequest(entityPlayer, data);
/*     */         return;
/*     */       case 268439569:
/*  97 */         MCH_AircraftPacketHandler.onPacketSeatListResponse(entityPlayer, data);
/*     */         return;
/*     */       case 536875040:
/* 100 */         MCH_AircraftPacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */       case 268439600:
/* 103 */         MCH_AircraftPacketHandler.onPacketNotifyTVMissileEntity(entityPlayer, data);
/*     */         return;
/*     */       case 536875072:
/* 106 */         MCH_AircraftPacketHandler.onPacket_ClientSetting(entityPlayer, data);
/*     */         return;
/*     */       case 268439632:
/* 109 */         MCH_AircraftPacketHandler.onPacketOnMountEntity(entityPlayer, data);
/*     */         return;
/*     */       case 268439601:
/* 112 */         MCH_AircraftPacketHandler.onPacketNotifyWeaponID(entityPlayer, data);
/*     */         return;
/*     */       case 268439602:
/* 115 */         MCH_AircraftPacketHandler.onPacketNotifyHitBullet(entityPlayer, data);
/*     */         return;
/*     */       case 536875059:
/* 118 */         MCH_AircraftPacketHandler.onPacketIndReload(entityPlayer, data);
/*     */         return;
/*     */       case 536875062:
/* 121 */         MCH_AircraftPacketHandler.onPacketIndRotation(entityPlayer, data);
/*     */         return;
/*     */       case 536875063:
/* 124 */         MCH_AircraftPacketHandler.onPacketNotifyInfoReloaded(entityPlayer, data);
/*     */         return;
/*     */       case 268439604:
/* 127 */         MCH_AircraftPacketHandler.onPacketNotifyAmmoNum(entityPlayer, data);
/*     */         return;
/*     */       case 536875061:
/* 130 */         MCH_AircraftPacketHandler.onPacketIndNotifyAmmoNum(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536887312:
/* 135 */         MCH_GLTDPacketHandler.onPacket_GLTDPlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536903696:
/* 140 */         MCP_PlanePacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 537919504:
/* 145 */         MCH_TankPacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 536936464:
/* 150 */         MCH_LightWeaponPacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 537002000:
/* 155 */         MCH_VehiclePacketHandler.onPacket_PlayerControl(entityPlayer, data);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 537133072:
/* 160 */         MCH_UavPacketHandler.onPacketUavStatus(entityPlayer, data);
/*     */         return;
/*     */       case 537395216:
/*     */         break;
/*     */     } 
/* 165 */     MCH_DraftingTablePacketHandler.onPacketCreate(entityPlayer, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getMessageId(ByteArrayDataInput data) {
/*     */     try {
/* 176 */       return data.readInt();
/*     */     }
/* 178 */     catch (Exception e) {
/*     */       
/* 180 */       e.printStackTrace();
/*     */ 
/*     */       
/* 183 */       return 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_PacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */