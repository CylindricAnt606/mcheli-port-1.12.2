/*    */ package mcheli.mcheli.block;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.block.MCH_DraftingTableCreatePacket;
/*    */ import mcheli.block.MCH_DraftingTableGuiContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class MCH_DraftingTablePacketHandler
/*    */ {
/*    */   public static void onPacketCreate(EntityPlayer player, ByteArrayDataInput data) {
/* 13 */     if (!player.field_70170_p.field_72995_K) {
/*    */       
/* 15 */       MCH_DraftingTableCreatePacket packet = new MCH_DraftingTableCreatePacket();
/* 16 */       packet.readData(data);
/*    */       
/* 18 */       boolean openScreen = player.field_71070_bA instanceof MCH_DraftingTableGuiContainer;
/*    */       
/* 20 */       MCH_Lib.DbgLog(false, "MCH_DraftingTablePacketHandler.onPacketCreate : " + openScreen, new Object[0]);
/*    */       
/* 22 */       if (openScreen)
/*    */       {
/* 24 */         ((MCH_DraftingTableGuiContainer)player.field_71070_bA).createRecipeItem(packet.outputItem, packet.map);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTablePacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */