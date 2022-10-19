/*    */ package mcheli.mcheli.throwable;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.throwable.MCH_EntityThrowable;
/*    */ import mcheli.throwable.MCH_ThrowableInfo;
/*    */ import mcheli.throwable.MCH_ThrowableInfoManager;
/*    */ import mcheli.wrapper.W_BlockDispenser;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class MCH_ItemThrowableDispenseBehavior extends BehaviorDefaultDispenseItem {
/*    */   public ItemStack func_82487_b(IBlockSource bs, ItemStack itemStack) {
/* 14 */     EnumFacing enumfacing = W_BlockDispenser.getFacing(bs.func_82620_h());
/* 15 */     double x = bs.func_82615_a() + enumfacing.func_82601_c() * 2.0D;
/* 16 */     double y = bs.func_82617_b() + enumfacing.func_96559_d() * 2.0D;
/* 17 */     double z = bs.func_82616_c() + enumfacing.func_82599_e() * 2.0D;
/*    */     
/* 19 */     if (itemStack.func_77973_b() instanceof mcheli.throwable.MCH_ItemThrowable) {
/*    */       
/* 21 */       MCH_ThrowableInfo info = MCH_ThrowableInfoManager.get(itemStack.func_77973_b());
/*    */       
/* 23 */       if (info != null) {
/*    */         
/* 25 */         bs.func_82618_k().func_72980_b(x, y, z, "random.bow", 0.5F, 0.4F / ((bs.func_82618_k()).field_73012_v.nextFloat() * 0.4F + 0.8F), false);
/*    */         
/* 27 */         if (!(bs.func_82618_k()).field_72995_K) {
/*    */           
/* 29 */           MCH_Lib.DbgLog(bs.func_82618_k(), "MCH_ItemThrowableDispenseBehavior.dispenseStack(%s)", new Object[] { info.name });
/*    */           
/* 31 */           MCH_EntityThrowable entity = new MCH_EntityThrowable(bs.func_82618_k(), x, y, z);
/* 32 */           entity.field_70159_w = enumfacing.func_82601_c() * info.dispenseAcceleration;
/* 33 */           entity.field_70181_x = enumfacing.func_96559_d() * info.dispenseAcceleration;
/* 34 */           entity.field_70179_y = enumfacing.func_82599_e() * info.dispenseAcceleration;
/* 35 */           entity.setInfo(info);
/* 36 */           bs.func_82618_k().func_72838_d((Entity)entity);
/*    */           
/* 38 */           itemStack.func_77979_a(1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_ItemThrowableDispenseBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */