/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.MCH_MOD;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_RecipeReloadRangeFinder
/*    */   implements IRecipe
/*    */ {
/*    */   public boolean func_77569_a(InventoryCrafting inv, World var2) {
/* 19 */     int jcnt = 0;
/* 20 */     int ccnt = 0;
/* 21 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/*    */       
/* 23 */       ItemStack is = inv.func_70301_a(i);
/* 24 */       if (is != null)
/*    */       {
/* 26 */         if (is.func_77973_b() instanceof mcheli.tool.rangefinder.MCH_ItemRangeFinder) {
/*    */ 
/*    */           
/* 29 */           if (is.func_77960_j() == 0)
/*    */           {
/* 31 */             return false;
/*    */           }
/*    */           
/* 34 */           jcnt++;
/*    */ 
/*    */           
/* 37 */           if (jcnt > 1)
/*    */           {
/* 39 */             return false;
/*    */           }
/*    */         }
/* 42 */         else if (is.func_77973_b() instanceof net.minecraft.item.ItemRedstone && is.field_77994_a > 0) {
/*    */           
/* 44 */           ccnt++;
/*    */ 
/*    */           
/* 47 */           if (ccnt > 1)
/*    */           {
/* 49 */             return false;
/*    */           
/*    */           }
/*    */         }
/*    */         else {
/*    */           
/* 55 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 60 */     return (jcnt == 1 && ccnt > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv) {
/* 66 */     ItemStack output = new ItemStack((Item)MCH_MOD.itemRangeFinder);
/* 67 */     return output;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_77570_a() {
/* 73 */     return 9;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77571_b() {
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_RecipeReloadRangeFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */