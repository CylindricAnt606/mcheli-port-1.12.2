/*    */ package mcheli.mcheli.throwable;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.throwable.MCH_EntityThrowable;
/*    */ import mcheli.throwable.MCH_ThrowableInfo;
/*    */ import mcheli.wrapper.W_Item;
/*    */ import net.minecraft.block.BlockDispenser;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_ItemThrowable extends W_Item {
/*    */   public MCH_ItemThrowable(int par1) {
/* 16 */     super(par1);
/* 17 */     func_77625_d(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void registerDispenseBehavior(Item item) {
/* 22 */     BlockDispenser.field_149943_a.func_82595_a(item, new MCH_ItemThrowableDispenseBehavior());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/* 27 */     player.func_71008_a(itemStack, func_77626_a(itemStack));
/* 28 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77615_a(ItemStack itemStack, World world, EntityPlayer player, int par4) {
/* 33 */     if (itemStack != null && itemStack.field_77994_a > 0) {
/*    */       
/* 35 */       MCH_ThrowableInfo info = MCH_ThrowableInfoManager.get(itemStack.func_77973_b());
/*    */       
/* 37 */       if (info != null) {
/*    */         
/* 39 */         if (!player.field_71075_bZ.field_75098_d) {
/*    */           
/* 41 */           itemStack.field_77994_a--;
/*    */           
/* 43 */           if (itemStack.field_77994_a <= 0)
/*    */           {
/* 45 */             player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
/*    */           }
/*    */         } 
/*    */         
/* 49 */         world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*    */         
/* 51 */         if (!world.field_72995_K) {
/*    */           
/* 53 */           float acceleration = 1.0F;
/* 54 */           par4 = itemStack.func_77988_m() - par4;
/* 55 */           if (par4 <= 35) {
/*    */             
/* 57 */             if (par4 < 5) par4 = 5; 
/* 58 */             acceleration = par4 / 25.0F;
/*    */           } 
/*    */           
/* 61 */           MCH_Lib.DbgLog(world, "MCH_ItemThrowable.onPlayerStoppedUsing(%d)", new Object[] { Integer.valueOf(par4) });
/*    */           
/* 63 */           MCH_EntityThrowable entity = new MCH_EntityThrowable(world, (EntityLivingBase)player, acceleration);
/* 64 */           entity.setInfo(info);
/* 65 */           world.func_72838_d((Entity)entity);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_77626_a(ItemStack par1ItemStack) {
/* 73 */     return 72000;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/* 78 */     return EnumAction.bow;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_ItemThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */