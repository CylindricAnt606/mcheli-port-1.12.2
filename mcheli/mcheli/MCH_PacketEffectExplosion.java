/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_PacketEffectExplosion
/*    */   extends MCH_Packet
/*    */ {
/* 15 */   ExplosionParam prm = new ExplosionParam(this);
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
/*    */   public int getMessageID() {
/* 30 */     return 268437520;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 38 */       this.prm.posX = data.readDouble();
/* 39 */       this.prm.posY = data.readDouble();
/* 40 */       this.prm.posZ = data.readDouble();
/* 41 */       this.prm.size = data.readFloat();
/* 42 */       this.prm.exploderID = data.readInt();
/* 43 */       this.prm.inWater = (data.readByte() != 0);
/*    */     }
/* 45 */     catch (Exception e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 56 */       dos.writeDouble(this.prm.posX);
/* 57 */       dos.writeDouble(this.prm.posY);
/* 58 */       dos.writeDouble(this.prm.posZ);
/* 59 */       dos.writeFloat(this.prm.size);
/* 60 */       dos.writeInt(this.prm.exploderID);
/* 61 */       dos.writeByte(this.prm.inWater ? 1 : 0);
/*    */     }
/* 63 */     catch (IOException e) {
/*    */       
/* 65 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static ExplosionParam create() {
/* 71 */     return (new mcheli.MCH_PacketEffectExplosion()).aaa();
/*    */   }
/*    */   private ExplosionParam aaa() {
/* 74 */     return new ExplosionParam(this);
/*    */   }
/*    */   
/*    */   public static void send(ExplosionParam param) {
/* 78 */     if (param != null) {
/*    */       
/* 80 */       mcheli.MCH_PacketEffectExplosion s = new mcheli.MCH_PacketEffectExplosion();
/* 81 */       s.prm = param;
/* 82 */       W_Network.sendToAllPlayers((W_PacketBase)s);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_PacketEffectExplosion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */