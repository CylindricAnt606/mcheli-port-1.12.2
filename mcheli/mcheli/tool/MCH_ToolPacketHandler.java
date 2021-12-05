/*    */ package mcheli.mcheli.tool;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.multiplay.MCH_Multiplay;
/*    */ import mcheli.multiplay.MCH_PacketIndSpotEntity;
/*    */ import mcheli.wrapper.W_WorldFunc;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class MCH_ToolPacketHandler
/*    */ {
/*    */   public static void onPacket_IndSpotEntity(EntityPlayer player, ByteArrayDataInput data) {
/* 17 */     if (player.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 23 */     MCH_PacketIndSpotEntity pc = new MCH_PacketIndSpotEntity();
/* 24 */     pc.readData(data);
/*    */     
/* 26 */     ItemStack itemStack = player.func_70694_bm();
/* 27 */     if (itemStack != null && itemStack.func_77973_b() instanceof mcheli.tool.rangefinder.MCH_ItemRangeFinder)
/*    */     {
/*    */       
/* 30 */       if (pc.targetFilter == 0) {
/*    */         
/* 32 */         if (MCH_Multiplay.markPoint(player, player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v))
/*    */         {
/* 34 */           W_WorldFunc.MOD_playSoundAtEntity((Entity)player, "pi", 1.0F, 1.0F);
/*    */         }
/*    */         else
/*    */         {
/* 38 */           W_WorldFunc.MOD_playSoundAtEntity((Entity)player, "ng", 1.0F, 1.0F);
/*    */         }
/*    */       
/*    */       }
/* 42 */       else if (itemStack.func_77960_j() < itemStack.func_77958_k()) {
/*    */         
/* 44 */         if (MCH_Config.RangeFinderConsume.prmBool)
/*    */         {
/* 46 */           itemStack.func_77972_a(1, (EntityLivingBase)player);
/*    */         }
/*    */         
/* 49 */         int time = ((pc.targetFilter & 0xFC) == 0) ? 60 : MCH_Config.RangeFinderSpotTime.prmInt;
/* 50 */         if (MCH_Multiplay.spotEntity(player, null, player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, pc.targetFilter, MCH_Config.RangeFinderSpotDist.prmInt, time, 20.0F)) {
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 55 */           W_WorldFunc.MOD_playSoundAtEntity((Entity)player, "pi", 1.0F, 1.0F);
/*    */         }
/*    */         else {
/*    */           
/* 59 */           W_WorldFunc.MOD_playSoundAtEntity((Entity)player, "ng", 1.0F, 1.0F);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\MCH_ToolPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */