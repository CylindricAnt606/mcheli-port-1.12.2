/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Network
/*    */ {
/* 20 */   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("MCHeli_CH");
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToServer(W_PacketBase pkt) {
/* 25 */     INSTANCE.sendToServer((IMessage)pkt);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(W_PacketBase pkt, EntityPlayer player) {
/* 32 */     if (player instanceof EntityPlayerMP) INSTANCE.sendTo((IMessage)pkt, (EntityPlayerMP)player);
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToAllAround(W_PacketBase pkt, Entity sender, double renge) {
/* 39 */     NetworkRegistry.TargetPoint t = new NetworkRegistry.TargetPoint(sender.field_71093_bK, sender.field_70165_t, sender.field_70163_u, sender.field_70161_v, renge);
/* 40 */     INSTANCE.sendToAllAround((IMessage)pkt, t);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendToAllPlayers(W_PacketBase pkt) {
/* 48 */     INSTANCE.sendToAll((IMessage)pkt);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Network.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */