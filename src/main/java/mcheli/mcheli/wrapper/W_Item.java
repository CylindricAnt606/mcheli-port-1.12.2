/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import mcheli.wrapper.W_MOD;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.Item;
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
/*     */ public class W_Item
/*     */   extends Item
/*     */ {
/*     */   public W_Item(int par1) {}
/*     */   
/*     */   public W_Item() {}
/*     */   
/*     */   public static int getIdFromItem(Item i) {
/*  28 */     return (i == null) ? 0 : field_150901_e.func_148757_b(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item setTexture(String par1Str) {
/*  36 */     func_111206_d(W_MOD.DOMAIN + ":" + par1Str);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Item getItemById(int i) {
/*  48 */     return Item.func_150899_d(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Item getItemByName(String nm) {
/*  57 */     if (nm.indexOf(':') < 0)
/*     */     {
/*  59 */       nm = "minecraft:" + nm;
/*     */     }
/*  61 */     return (Item)Item.field_150901_e.func_82594_a(nm);
/*     */   }
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
/*     */   public static String getNameForItem(Item item) {
/*  96 */     return Item.field_150901_e.func_148750_c(item);
/*     */   }
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
/*     */   public static Item getItemFromBlock(Block block) {
/* 113 */     return Item.func_150898_a(block);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Item.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */