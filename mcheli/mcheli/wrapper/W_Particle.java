/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Particle
/*    */ {
/*    */   public static String getParticleTileCrackName(World w, int blockX, int blockY, int blockZ) {
/* 14 */     Block block = w.func_147439_a(blockX, blockY, blockZ);
/* 15 */     if (block.func_149688_o() != Material.field_151579_a)
/*    */     {
/* 17 */       return "blockcrack_" + Block.func_149682_b(block) + "_" + w.func_72805_g(blockX, blockY, blockZ);
/*    */     }
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
/* 32 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getParticleTileDustName(World w, int blockX, int blockY, int blockZ) {
/* 39 */     Block block = w.func_147439_a(blockX, blockY, blockZ);
/* 40 */     if (block.func_149688_o() != Material.field_151579_a)
/*    */     {
/* 42 */       return "blockdust_" + Block.func_149682_b(block) + "_" + w.func_72805_g(blockX, blockY, blockZ);
/*    */     }
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
/* 57 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Particle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */