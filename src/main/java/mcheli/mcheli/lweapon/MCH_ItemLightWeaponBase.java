/*    */ package mcheli.mcheli.lweapon;
/*    */ 
/*    */ import mcheli.lweapon.MCH_ItemLightWeaponBullet;
/*    */ import mcheli.wrapper.W_Item;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_ItemLightWeaponBase
/*    */   extends W_Item
/*    */ {
/*    */   public final MCH_ItemLightWeaponBullet bullet;
/*    */   
/*    */   public MCH_ItemLightWeaponBase(int par1, MCH_ItemLightWeaponBullet bullet) {
/* 19 */     super(par1);
/* 20 */     func_77656_e(10);
/* 21 */     func_77625_d(1);
/* 22 */     this.bullet = bullet;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getName(ItemStack itemStack) {
/* 27 */     if (itemStack != null && itemStack.func_77973_b() instanceof mcheli.lweapon.MCH_ItemLightWeaponBase) {
/*    */       
/* 29 */       String name = itemStack.func_77977_a();
/* 30 */       int li = name.lastIndexOf(":");
/* 31 */       if (li >= 0)
/*    */       {
/* 33 */         name = name.substring(li + 1);
/*    */       }
/* 35 */       return name;
/*    */     } 
/* 37 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isHeld(EntityPlayer player) {
/* 42 */     ItemStack is = (player != null) ? player.func_70694_bm() : null;
/* 43 */     if (is != null && is.func_77973_b() instanceof mcheli.lweapon.MCH_ItemLightWeaponBase)
/*    */     {
/*    */ 
/*    */       
/* 47 */       return (player.func_71057_bx() > 10);
/*    */     }
/*    */     
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 55 */     PotionEffect pe = player.func_70660_b(Potion.field_76439_r);
/* 56 */     if (pe != null && pe.func_76459_b() < 220)
/*    */     {
/* 58 */       player.func_70690_d(new PotionEffect(Potion.field_76439_r.func_76396_c(), 250, 0, false));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/* 69 */     return EnumAction.bow;
/*    */   }
/*    */   
/*    */   public int func_77626_a(ItemStack par1ItemStack) {
/* 73 */     return 72000;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 77 */     if (par1ItemStack != null)
/*    */     {
/* 79 */       par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/*    */     }
/* 81 */     return par1ItemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_ItemLightWeaponBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */