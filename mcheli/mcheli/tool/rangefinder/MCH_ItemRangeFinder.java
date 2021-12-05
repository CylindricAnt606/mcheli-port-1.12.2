/*     */ package mcheli.mcheli.tool.rangefinder;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.multiplay.MCH_PacketIndSpotEntity;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ItemRangeFinder extends W_Item {
/*  19 */   public static int rangeFinderUseCooldown = 0;
/*     */   public static boolean continueUsingItem = false;
/*  21 */   public static float zoom = 2.0F;
/*     */   
/*  23 */   public static int mode = 0;
/*     */ 
/*     */   
/*     */   public MCH_ItemRangeFinder(int itemId) {
/*  27 */     super(itemId);
/*  28 */     this.field_77777_bU = 1;
/*  29 */     func_77656_e(10);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canUse(EntityPlayer player) {
/*  34 */     if (player == null) return false; 
/*  35 */     if (player.field_70170_p == null) return false;
/*     */     
/*  37 */     if (player.func_71045_bC() == null) return false; 
/*  38 */     if (!(player.func_71045_bC().func_77973_b() instanceof mcheli.tool.rangefinder.MCH_ItemRangeFinder)) return false;
/*     */     
/*  40 */     if (player.field_70154_o instanceof MCH_EntityAircraft) return false; 
/*  41 */     if (player.field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/*  43 */       MCH_EntityAircraft ac = ((MCH_EntitySeat)player.field_70154_o).getParent();
/*  44 */       if (ac != null && (ac.getIsGunnerMode((Entity)player) || ac.getWeaponIDBySeatID(ac.getSeatIdByEntity((Entity)player)) >= 0))
/*     */       {
/*  46 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isUsingScope(EntityPlayer player) {
/*  55 */     return (player.func_71057_bx() > 8 || continueUsingItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onStartUseItem() {
/*  62 */     zoom = 2.0F;
/*  63 */     W_Reflection.setCameraZoom(2.0F);
/*  64 */     continueUsingItem = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onStopUseItem() {
/*  72 */     W_Reflection.restoreCameraZoom();
/*  73 */     continueUsingItem = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void spotEntity(EntityPlayer player, ItemStack itemStack) {
/*  80 */     if (player != null && player.field_70170_p.field_72995_K)
/*     */     {
/*  82 */       if (rangeFinderUseCooldown == 0 && player.func_71057_bx() > 8)
/*     */       {
/*  84 */         if (mode == 2) {
/*     */           
/*  86 */           rangeFinderUseCooldown = 60;
/*  87 */           MCH_PacketIndSpotEntity.send((EntityLivingBase)player, 0);
/*     */         }
/*  89 */         else if (itemStack.func_77960_j() < itemStack.func_77958_k()) {
/*     */           
/*  91 */           rangeFinderUseCooldown = 60;
/*  92 */           MCH_PacketIndSpotEntity.send((EntityLivingBase)player, (mode == 0) ? 60 : 3);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  97 */           W_McClient.MOD_playSoundFX("ng", 1.0F, 1.0F);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
/* 105 */     if (p_77615_2_.field_72995_K)
/*     */     {
/* 107 */       onStopUseItem();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
/* 113 */     return p_77654_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d() {
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack itemStack) {
/* 131 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack itemStack) {
/* 139 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/* 148 */     if (canUse(player))
/*     */     {
/* 150 */       player.func_71008_a(itemStack, func_77626_a(itemStack));
/*     */     }
/* 152 */     return itemStack;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\rangefinder\MCH_ItemRangeFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */