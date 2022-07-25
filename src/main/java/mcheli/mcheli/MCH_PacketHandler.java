 package mcheli.mcheli;

 import com.google.common.io.ByteArrayDataInput;
 import mcheli.mcheli.MCH_CommonPacketHandler;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.aircraft.MCH_AircraftPacketHandler;
 import mcheli.mcheli.block.MCH_DraftingTablePacketHandler;
 import mcheli.mcheli.command.MCH_CommandPacketHandler;
 import mcheli.mcheli.gltd.MCH_GLTDPacketHandler;
 import mcheli.mcheli.helicopter.MCH_HeliPacketHandler;
 import mcheli.mcheli.lweapon.MCH_LightWeaponPacketHandler;
 import mcheli.mcheli.multiplay.MCH_MultiplayPacketHandler;
 import mcheli.mcheli.plane.MCP_PlanePacketHandler;
 import mcheli.mcheli.tank.MCH_TankPacketHandler;
 import mcheli.mcheli.tool.MCH_ToolPacketHandler;
 import mcheli.mcheli.uav.MCH_UavPacketHandler;
 import mcheli.mcheli.vehicle.MCH_VehiclePacketHandler;
 import mcheli.mcheli.wrapper.W_PacketHandler;
 import net.minecraft.entity.player.EntityPlayer;


 public class MCH_PacketHandler
   extends W_PacketHandler
 {
   public void onPacket(ByteArrayDataInput data, EntityPlayer entityPlayer) {
     int msgid = getMessageId(data);
     switch (msgid) {



       default:
         MCH_Lib.DbgLog(entityPlayer.world, "MCH_PacketHandler.onPacket invalid MSGID=0x%X(%d)", new Object[] { Integer.valueOf(msgid), Integer.valueOf(msgid) });
         return;


       case 268437520:
         MCH_CommonPacketHandler.onPacketEffectExplosion(entityPlayer, data);
         return;
       case 536872992:
         MCH_CommonPacketHandler.onPacketIndOpenScreen(entityPlayer, data);
         return;
       case 268437568:
         MCH_CommonPacketHandler.onPacketNotifyServerSettings(entityPlayer, data);
         return;
       case 536873984:
         MCH_CommonPacketHandler.onPacketNotifyLock(entityPlayer, data);
         return;

       case 536873088:
         MCH_MultiplayPacketHandler.onPacket_Command(entityPlayer, data);
         return;
       case 268437761:
         MCH_MultiplayPacketHandler.onPacket_NotifySpotedEntity(entityPlayer, data);
         return;
       case 268437762:
         MCH_MultiplayPacketHandler.onPacket_NotifyMarkPoint(entityPlayer, data);
         return;
       case 536873472:
         MCH_MultiplayPacketHandler.onPacket_LargeData(entityPlayer, data);
         return;
       case 536873473:
         MCH_MultiplayPacketHandler.onPacket_ModList(entityPlayer, data);
         return;
       case 268438032:
         MCH_MultiplayPacketHandler.onPacket_IndClient(entityPlayer, data);
         return;


       case 268438272:
         MCH_CommandPacketHandler.onPacketTitle(entityPlayer, data);
         return;
       case 536873729:
         MCH_CommandPacketHandler.onPacketSave(entityPlayer, data);
         return;


       case 536873216:
         MCH_ToolPacketHandler.onPacket_IndSpotEntity(entityPlayer, data);
         return;


       case 536879120:
         MCH_HeliPacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;


       case 536875104:
         MCH_AircraftPacketHandler.onPacketStatusRequest(entityPlayer, data);
         return;
       case 268439649:
         MCH_AircraftPacketHandler.onPacketStatusResponse(entityPlayer, data);
         return;
       case 536875024:
         MCH_AircraftPacketHandler.onPacketSeatListRequest(entityPlayer, data);
         return;
       case 268439569:
         MCH_AircraftPacketHandler.onPacketSeatListResponse(entityPlayer, data);
         return;
       case 536875040:
         MCH_AircraftPacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;
       case 268439600:
         MCH_AircraftPacketHandler.onPacketNotifyTVMissileEntity(entityPlayer, data);
         return;
       case 536875072:
         MCH_AircraftPacketHandler.onPacket_ClientSetting(entityPlayer, data);
         return;
       case 268439632:
         MCH_AircraftPacketHandler.onPacketOnMountEntity(entityPlayer, data);
         return;
       case 268439601:
         MCH_AircraftPacketHandler.onPacketNotifyWeaponID(entityPlayer, data);
         return;
       case 268439602:
         MCH_AircraftPacketHandler.onPacketNotifyHitBullet(entityPlayer, data);
         return;
       case 536875059:
         MCH_AircraftPacketHandler.onPacketIndReload(entityPlayer, data);
         return;
       case 536875062:
         MCH_AircraftPacketHandler.onPacketIndRotation(entityPlayer, data);
         return;
       case 536875063:
         MCH_AircraftPacketHandler.onPacketNotifyInfoReloaded(entityPlayer, data);
         return;
       case 268439604:
         MCH_AircraftPacketHandler.onPacketNotifyAmmoNum(entityPlayer, data);
         return;
       case 536875061:
         MCH_AircraftPacketHandler.onPacketIndNotifyAmmoNum(entityPlayer, data);
         return;


       case 536887312:
         MCH_GLTDPacketHandler.onPacket_GLTDPlayerControl(entityPlayer, data);
         return;


       case 536903696:
         MCP_PlanePacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;


       case 537919504:
         MCH_TankPacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;


       case 536936464:
         MCH_LightWeaponPacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;


       case 537002000:
         MCH_VehiclePacketHandler.onPacket_PlayerControl(entityPlayer, data);
         return;


       case 537133072:
         MCH_UavPacketHandler.onPacketUavStatus(entityPlayer, data);
         return;
       case 537395216:
         break;
     }
     MCH_DraftingTablePacketHandler.onPacketCreate(entityPlayer, data);
   }







   protected int getMessageId(ByteArrayDataInput data) {
     try {
       return data.readInt();
     }
     catch (Exception e) {

       e.printStackTrace();


       return 0;
     }
   }
 }
