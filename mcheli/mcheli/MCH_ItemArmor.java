/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.MCH_TEST_ModelBiped;
/*    */ import mcheli.wrapper.W_ItemArmor;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class MCH_ItemArmor
/*    */   extends W_ItemArmor
/*    */ {
/*    */   public static final String HELMET_TEXTURE = "mcheli:textures/helicopters/ah-64.png";
/*    */   public static final String CHESTPLATE_TEXTURE = "mcheli:textures/armor/plate.png";
/*    */   public static final String LEGGINGS_TEXTURE = "mcheli:textures/armor/leg.png";
/*    */   public static final String BOOTS_TEXTURE = "mcheli:textures/armor/boots.png";
/*    */   
/*    */   public MCH_ItemArmor(int par1, int par3, int par4) {
/* 21 */     super(par1, par3, par4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
/* 33 */     if (slot == 0) {
/* 34 */       return "mcheli:textures/helicopters/ah-64.png";
/*    */     }
/* 36 */     if (slot == 1) {
/* 37 */       return "mcheli:textures/armor/plate.png";
/*    */     }
/* 39 */     if (slot == 2) {
/* 40 */       return "mcheli:textures/armor/leg.png";
/*    */     }
/* 42 */     if (slot == 3) {
/* 43 */       return "mcheli:textures/armor/boots.png";
/*    */     }
/* 45 */     return "none";
/*    */   }
/*    */   
/* 48 */   public static MCH_TEST_ModelBiped model = null;
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
/* 53 */     if (model == null)
/*    */     {
/* 55 */       model = new MCH_TEST_ModelBiped();
/*    */     }
/* 57 */     if (armorSlot == 0)
/*    */     {
/* 59 */       return (ModelBiped)model;
/*    */     }
/* 61 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ItemArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */