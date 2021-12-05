/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import mcheli.wrapper.W_Blocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class W_Block
/*    */   extends Block
/*    */ {
/*    */   protected W_Block(Material p_i45394_1_) {
/* 13 */     super(p_i45394_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Block getBlockFromName(String name) {
/* 20 */     return Block.func_149684_b(name);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Block getSnowLayer() {
/* 52 */     return W_Blocks.field_150431_aC;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isNull(Block block) {
/* 60 */     return (block == null || block == W_Blocks.field_150350_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isEqual(int blockId, Block block) {
/* 68 */     return Block.func_149680_a(Block.func_149729_e(blockId), block);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isEqual(Block block1, Block block2) {
/* 76 */     return Block.func_149680_a(block1, block2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Block getWater() {
/* 84 */     return W_Blocks.field_150355_j;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Block getBlockById(int i) {
/* 92 */     return Block.func_149729_e(i);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Block.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */