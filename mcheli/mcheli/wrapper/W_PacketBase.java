/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import com.google.common.io.ByteStreams;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ public class W_PacketBase
/*    */   implements IMessage
/*    */ {
/*    */   ByteArrayDataInput data;
/*    */   
/*    */   public byte[] createData() {
/* 14 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 21 */     byte[] dst = new byte[(buf.array()).length - 1];
/* 22 */     buf.getBytes(0, dst);
/* 23 */     this.data = ByteStreams.newDataInput(dst);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 27 */     buf.writeBytes(createData());
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_PacketBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */