/*    */ package mcheli.mcheli.block;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.MCH_Packet;
/*    */ import mcheli.wrapper.W_Item;
/*    */ import mcheli.wrapper.W_Network;
/*    */ import mcheli.wrapper.W_PacketBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_DraftingTableCreatePacket
/*    */   extends MCH_Packet
/*    */ {
/*    */   public Item outputItem;
/* 28 */   public Map<Item, Integer> map = new HashMap<Item, Integer>();
/*    */   
/*    */   public int getMessageID() {
/* 31 */     return 537395216;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readData(ByteArrayDataInput data) {
/*    */     try {
/* 39 */       this.outputItem = W_Item.getItemByName(data.readUTF());
/*    */       
/* 41 */       int size = data.readByte();
/* 42 */       for (int i = 0; i < size; i++)
/*    */       {
/* 44 */         String s = data.readUTF();
/* 45 */         int num = data.readByte();
/* 46 */         Item item = W_Item.getItemByName(s);
/* 47 */         if (item != null)
/*    */         {
/* 49 */           this.map.put(item, Integer.valueOf(0 + num));
/*    */         }
/*    */       }
/*    */     
/* 53 */     } catch (Exception e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeData(DataOutputStream dos) {
/*    */     try {
/* 64 */       dos.writeUTF(getItemName(this.outputItem));
/*    */       
/* 66 */       dos.writeByte(this.map.size());
/* 67 */       for (Item key : this.map.keySet())
/*    */       {
/* 69 */         dos.writeUTF(getItemName(key));
/* 70 */         dos.writeByte(((Integer)this.map.get(key)).byteValue());
/*    */       }
/*    */     
/* 73 */     } catch (IOException e) {
/*    */       
/* 75 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getItemName(Item item) {
/* 80 */     return W_Item.getNameForItem(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void send(IRecipe recipe) {
/* 85 */     if (recipe != null) {
/*    */       
/* 87 */       mcheli.block.MCH_DraftingTableCreatePacket s = new mcheli.block.MCH_DraftingTableCreatePacket();
/* 88 */       s.outputItem = (recipe.func_77571_b() != null) ? recipe.func_77571_b().func_77973_b() : null;
/* 89 */       if (s.outputItem != null) {
/*    */         
/* 91 */         s.map = MCH_Lib.getItemMapFromRecipe(recipe);
/* 92 */         W_Network.sendToServer((W_PacketBase)s);
/*    */       } 
/*    */       
/* 95 */       MCH_Lib.DbgLog(true, "MCH_DraftingTableCreatePacket.send outputItem = " + s.outputItem, new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableCreatePacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */