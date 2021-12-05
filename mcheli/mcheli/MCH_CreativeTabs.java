/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_CreativeTabs
/*     */   extends CreativeTabs
/*     */ {
/*     */   private List<Item> iconItems;
/*     */   private Item lastItem;
/*     */   private int currentIconIndex;
/*     */   private int switchItemWait;
/*  24 */   private Item fixedItem = null;
/*     */ 
/*     */   
/*     */   public MCH_CreativeTabs(String label) {
/*  28 */     super(label);
/*  29 */     this.iconItems = new ArrayList<Item>();
/*  30 */     this.currentIconIndex = 0;
/*  31 */     this.switchItemWait = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFixedIconItem(String itemName) {
/*  36 */     if (itemName.indexOf(':') >= 0) {
/*     */       
/*  38 */       this.fixedItem = W_Item.getItemByName(itemName);
/*  39 */       if (this.fixedItem != null) this.fixedItem.func_111206_d(itemName);
/*     */     
/*     */     } else {
/*     */       
/*  43 */       this.fixedItem = W_Item.getItemByName("mcheli:" + itemName);
/*  44 */       if (this.fixedItem != null) this.fixedItem.func_111206_d("mcheli:" + itemName);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item func_78016_d() {
/*  51 */     if (this.iconItems.size() <= 0) return null; 
/*  52 */     this.currentIconIndex = (this.currentIconIndex + 1) % this.iconItems.size();
/*  53 */     return this.iconItems.get(this.currentIconIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_151244_d() {
/*  58 */     if (this.fixedItem != null)
/*     */     {
/*  60 */       return new ItemStack(this.fixedItem, 1, 0);
/*     */     }
/*     */     
/*  63 */     if (this.switchItemWait > 0) {
/*     */       
/*  65 */       this.switchItemWait--;
/*     */     }
/*     */     else {
/*     */       
/*  69 */       this.lastItem = func_78016_d();
/*  70 */       this.switchItemWait = 60;
/*     */     } 
/*     */     
/*  73 */     if (this.lastItem == null)
/*     */     {
/*  75 */       this.lastItem = W_Item.getItemByName("iron_block");
/*     */     }
/*     */     
/*  78 */     return new ItemStack(this.lastItem, 1, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_78018_a(List<?> list) {
/*  84 */     super.func_78018_a(list);
/*     */ 
/*     */     
/*  87 */     Object object = new Object(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     Collections.sort(list, (Comparator<?>)object);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addIconItem(Item i) {
/* 110 */     if (i != null) this.iconItems.add(i);
/*     */   
/*     */   }
/*     */   
/*     */   public String func_78024_c() {
/* 115 */     return "MC Heli";
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_CreativeTabs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */