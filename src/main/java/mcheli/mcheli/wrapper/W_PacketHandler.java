/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.wrapper.IPacketHandler;
/*    */ import mcheli.wrapper.W_NetworkRegistry;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import mcheli.wrapper.W_PacketDummy;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_PacketHandler
/*    */   implements IPacketHandler, IMessageHandler<W_PacketBase, W_PacketDummy>
/*    */ {
/*    */   public void onPacket(ByteArrayDataInput data, EntityPlayer player) {}
/*    */   
/*    */   public W_PacketDummy onMessage(W_PacketBase message, MessageContext ctx) {
/*    */     try {
/* 35 */       if (message.data != null)
/*    */       {
/* 37 */         if (ctx.side.isClient())
/*    */         {
/* 39 */           if (MCH_Lib.getClientPlayer() != null)
/*    */           {
/* 41 */             W_NetworkRegistry.packetHandler.onPacket(message.data, (EntityPlayer)MCH_Lib.getClientPlayer());
/*    */           }
/*    */         }
/*    */         else
/*    */         {
/* 46 */           W_NetworkRegistry.packetHandler.onPacket(message.data, (EntityPlayer)(ctx.getServerHandler()).field_147369_b);
/*    */         }
/*    */       
/*    */       }
/* 50 */     } catch (Exception e) {
/*    */       
/* 52 */       e.printStackTrace();
/*    */     } 
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_PacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */