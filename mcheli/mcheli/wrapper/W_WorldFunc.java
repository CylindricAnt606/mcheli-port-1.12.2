/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_MOD;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class W_WorldFunc
/*     */ {
/*     */   public static void DEF_playSoundEffect(World w, double x, double y, double z, String name, float volume, float pitch) {
/*  16 */     w.func_72908_a(x, y, z, name, volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void MOD_playSoundEffect(World w, double x, double y, double z, String name, float volume, float pitch) {
/*  25 */     DEF_playSoundEffect(w, x, y, z, W_MOD.DOMAIN + ":" + name, volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void playSoundAtEntity(Entity e, String name, float volume, float pitch) {
/*  36 */     e.field_70170_p.func_72956_a(e, name, volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void MOD_playSoundAtEntity(Entity e, String name, float volume, float pitch) {
/*  43 */     playSoundAtEntity(e, W_MOD.DOMAIN + ":" + name, volume, pitch);
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
/*     */   public static int getBlockId(World w, int x, int y, int z) {
/*  55 */     return Block.func_149682_b(w.func_147439_a(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Block getBlock(World w, int x, int y, int z) {
/*  62 */     return w.func_147439_a(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Material getBlockMaterial(World w, int x, int y, int z) {
/*  71 */     return w.func_147439_a(x, y, z).func_149688_o();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockWater(World w, int x, int y, int z) {
/*  78 */     return isEqualBlock(w, x, y, z, W_Block.getWater());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEqualBlock(World w, int x, int y, int z, Block block) {
/*  84 */     return Block.func_149680_a(w.func_147439_a(x, y, z), block);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition clip(World w, Vec3 par1Vec3, Vec3 par2Vec3) {
/*  95 */     return w.func_72933_a(par1Vec3, par2Vec3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition clip(World w, Vec3 par1Vec3, Vec3 par2Vec3, boolean b) {
/* 100 */     return w.func_72901_a(par1Vec3, par2Vec3, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MovingObjectPosition clip(World w, Vec3 par1Vec3, Vec3 par2Vec3, boolean b1, boolean b2, boolean b3) {
/* 109 */     return w.func_147447_a(par1Vec3, par2Vec3, b1, b2, b3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean setBlock(World w, int a, int b, int c, Block d) {
/* 119 */     return w.func_147449_b(a, b, c, d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBlock(World w, int x, int y, int z, Block b, int i, int j) {
/* 126 */     w.func_147465_d(x, y, z, b, i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean destroyBlock(World w, int x, int y, int z, boolean par4) {
/* 134 */     return w.func_147480_a(x, y, z, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 getWorldVec3(World w, double x, double y, double z) {
/* 142 */     return Vec3.func_72443_a(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 getWorldVec3EntityPos(Entity e) {
/* 149 */     return getWorldVec3(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_WorldFunc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */