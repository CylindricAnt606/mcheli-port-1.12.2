/*    */ package mcheli.mcheli.gui;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class MCH_ConfigGuiContainer
/*    */   extends Container
/*    */ {
/*    */   public final EntityPlayer player;
/*    */   
/*    */   public MCH_ConfigGuiContainer(EntityPlayer player) {
/* 13 */     this.player = player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75142_b() {
/* 19 */     super.func_75142_b();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75145_c(EntityPlayer player) {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/* 31 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_ConfigGuiContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */