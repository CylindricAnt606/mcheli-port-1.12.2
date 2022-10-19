/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_ItemAircraft;
/*    */ import mcheli.wrapper.W_BlockDispenser;
/*    */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class MCH_ItemAircraftDispenseBehavior
/*    */   extends BehaviorDefaultDispenseItem {
/*    */   public ItemStack func_82487_b(IBlockSource bs, ItemStack itemStack) {
/* 16 */     EnumFacing enumfacing = W_BlockDispenser.getFacing(bs.func_82620_h());
/* 17 */     double x = bs.func_82615_a() + enumfacing.func_82601_c() * 2.0D;
/* 18 */     double y = bs.func_82617_b() + enumfacing.func_96559_d() * 2.0D;
/* 19 */     double z = bs.func_82616_c() + enumfacing.func_82599_e() * 2.0D;
/*    */     
/* 21 */     if (itemStack.func_77973_b() instanceof MCH_ItemAircraft) {
/*    */       
/* 23 */       MCH_EntityAircraft ac = ((MCH_ItemAircraft)itemStack.func_77973_b()).onTileClick(itemStack, bs.func_82618_k(), 0.0F, (int)x, (int)y, (int)z);
/*    */ 
/*    */       
/* 26 */       if (ac != null)
/*    */       {
/* 28 */         if (!ac.isUAV()) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 34 */           if (!(bs.func_82618_k()).field_72995_K) {
/*    */             
/* 36 */             ac.getAcDataFromItem(itemStack);
/* 37 */             bs.func_82618_k().func_72838_d((Entity)ac);
/*    */           } 
/*    */ 
/*    */           
/* 41 */           itemStack.func_77979_a(1);
/* 42 */           MCH_Lib.DbgLog(bs.func_82618_k(), "dispenseStack:x=%.1f,y=%.1f,z=%.1f;dir=%s:item=" + itemStack.func_82833_r(), new Object[] { Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), enumfacing.toString() });
/*    */         } 
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 49 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_ItemAircraftDispenseBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */