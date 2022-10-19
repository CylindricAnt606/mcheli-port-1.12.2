/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.network.IGuiHandler;
/*    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import mcheli.wrapper.W_PacketHandler;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class W_NetworkRegistry
/*    */ {
/*    */   public static W_PacketHandler packetHandler;
/*    */   
/*    */   public static void registerChannel(W_PacketHandler handler, String name) {
/* 17 */     packetHandler = handler;
/* 18 */     W_Network.INSTANCE.registerMessage(W_PacketHandler.class, W_PacketBase.class, 0, Side.SERVER);
/* 19 */     W_Network.INSTANCE.registerMessage(W_PacketHandler.class, W_PacketBase.class, 0, Side.CLIENT);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void handlePacket(EntityPlayer player, byte[] data) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void registerGuiHandler(Object mod, IGuiHandler handler) {
/* 34 */     NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_NetworkRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */