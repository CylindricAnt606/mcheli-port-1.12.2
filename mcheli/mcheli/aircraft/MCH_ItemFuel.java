/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import mcheli.wrapper.W_Item;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_ItemFuel
/*    */   extends W_Item
/*    */ {
/*    */   public MCH_ItemFuel(int itemID) {
/* 15 */     super(itemID);
/* 16 */     func_77656_e(600);
/* 17 */     func_77625_d(1);
/* 18 */     setNoRepair();
/* 19 */     func_77664_n();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
/* 24 */     int damage = stack.func_77960_j();
/* 25 */     if (!world.field_72995_K && stack.func_77951_h() && !player.field_71075_bZ.field_75098_d) {
/*    */ 
/*    */       
/* 28 */       refuel(stack, player, 1);
/* 29 */       refuel(stack, player, 0);
/*    */     } 
/* 31 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   private void refuel(ItemStack stack, EntityPlayer player, int coalType) {
/* 36 */     ItemStack[] list = player.field_71071_by.field_70462_a;
/* 37 */     for (int i = 0; i < list.length; i++) {
/*    */       
/* 39 */       ItemStack is = list[i];
/* 40 */       if (is != null && is.func_77973_b() instanceof net.minecraft.item.ItemCoal && is.func_77960_j() == coalType) {
/*    */         
/* 42 */         for (int j = 0; is.field_77994_a > 0 && stack.func_77951_h() && j < 64; j++) {
/*    */           
/* 44 */           int damage = stack.func_77960_j() - ((coalType == 1) ? 75 : 100);
/* 45 */           if (damage < 0) damage = 0; 
/* 46 */           stack.func_77964_b(damage);
/* 47 */           is.field_77994_a--;
/*    */         } 
/*    */         
/* 50 */         if (is.field_77994_a <= 0) list[i] = null; 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_ItemFuel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */