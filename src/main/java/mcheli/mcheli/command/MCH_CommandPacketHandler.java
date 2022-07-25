/*    */ package mcheli.mcheli.command;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.MCH_MOD;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.command.MCH_PacketCommandSave;
/*    */ import mcheli.command.MCH_PacketTitle;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class MCH_CommandPacketHandler
/*    */ {
/*    */   public static void onPacketTitle(EntityPlayer player, ByteArrayDataInput data) {
/* 14 */     if (player == null || !player.field_70170_p.field_72995_K)
/*    */       return; 
/* 16 */     MCH_PacketTitle req = new MCH_PacketTitle();
/* 17 */     req.readData(data);
/*    */     
/* 19 */     MCH_MOD.proxy.printChatMessage(req.chatComponent, req.showTime, req.position);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void onPacketSave(EntityPlayer player, ByteArrayDataInput data) {
/* 25 */     if (player == null || player.field_70170_p.field_72995_K)
/*    */       return; 
/* 27 */     MCH_PacketCommandSave req = new MCH_PacketCommandSave();
/* 28 */     req.readData(data);
/*    */     
/* 30 */     MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)player);
/* 31 */     if (ac != null)
/*    */     {
/* 33 */       ac.setCommand(req.str, player);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\command\MCH_CommandPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */