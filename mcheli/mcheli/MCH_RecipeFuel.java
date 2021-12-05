/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import mcheli.MCH_MOD;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_RecipeFuel
/*     */   implements IRecipe
/*     */ {
/*     */   public boolean func_77569_a(InventoryCrafting inv, World var2) {
/*  19 */     int jcnt = 0;
/*  20 */     int ccnt = 0;
/*  21 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/*     */       
/*  23 */       ItemStack is = inv.func_70301_a(i);
/*  24 */       if (is != null)
/*     */       {
/*  26 */         if (is.func_77973_b() instanceof mcheli.aircraft.MCH_ItemFuel) {
/*     */ 
/*     */           
/*  29 */           if (is.func_77960_j() == 0)
/*     */           {
/*  31 */             return false;
/*     */           }
/*     */           
/*  34 */           jcnt++;
/*     */ 
/*     */           
/*  37 */           if (jcnt > 1)
/*     */           {
/*  39 */             return false;
/*     */           }
/*     */         }
/*  42 */         else if (is.func_77973_b() instanceof net.minecraft.item.ItemCoal && is.field_77994_a > 0) {
/*     */           
/*  44 */           ccnt++;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  49 */           return false;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  54 */     return (jcnt == 1 && ccnt > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77572_b(InventoryCrafting inv) {
/*  60 */     ItemStack output = new ItemStack((Item)MCH_MOD.itemFuel); int i;
/*  61 */     for (i = 0; i < inv.func_70302_i_(); i++) {
/*     */       
/*  63 */       ItemStack is = inv.func_70301_a(i);
/*  64 */       if (is != null && is.func_77973_b() instanceof mcheli.aircraft.MCH_ItemFuel) {
/*     */         
/*  66 */         output.func_77964_b(is.func_77960_j());
/*     */         break;
/*     */       } 
/*     */     } 
/*  70 */     for (i = 0; i < inv.func_70302_i_(); i++) {
/*     */       
/*  72 */       ItemStack is = inv.func_70301_a(i);
/*  73 */       if (is != null && is.func_77973_b() instanceof net.minecraft.item.ItemCoal) {
/*     */         
/*  75 */         int sp = 100;
/*  76 */         if (is.func_77960_j() == 1)
/*     */         {
/*  78 */           sp = 75;
/*     */         }
/*  80 */         if (output.func_77960_j() > sp) {
/*     */           
/*  82 */           output.func_77964_b(output.func_77960_j() - sp);
/*     */         }
/*     */         else {
/*     */           
/*  86 */           output.func_77964_b(0);
/*     */         } 
/*     */       } 
/*     */     } 
/*  90 */     return output;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77570_a() {
/*  96 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77571_b() {
/* 102 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_RecipeFuel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */