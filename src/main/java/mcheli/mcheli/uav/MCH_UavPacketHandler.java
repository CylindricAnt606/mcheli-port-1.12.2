/*    */ package mcheli.mcheli.uav;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.uav.MCH_EntityUavStation;
/*    */ import mcheli.uav.MCH_UavPacketStatus;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_UavPacketHandler
/*    */ {
/*    */   public static void onPacketUavStatus(EntityPlayer player, ByteArrayDataInput data) {
/* 14 */     if (!player.field_70170_p.field_72995_K) {
/*    */       
/* 16 */       MCH_UavPacketStatus status = new MCH_UavPacketStatus();
/* 17 */       status.readData(data);
/*    */       
/* 19 */       if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*    */         
/* 21 */         ((MCH_EntityUavStation)player.field_70154_o).setUavPosition(status.posUavX, status.posUavY, status.posUavZ);
/*    */ 
/*    */         
/* 24 */         if (status.continueControl)
/*    */         {
/* 26 */           ((MCH_EntityUavStation)player.field_70154_o).controlLastAircraft((Entity)player);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_UavPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */