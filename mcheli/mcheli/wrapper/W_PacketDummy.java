package mcheli.mcheli.wrapper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class W_PacketDummy implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
}


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_PacketDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */