/*     */ package mcheli.mcheli;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_DamageFactor;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_PacketEffectExplosion;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_ChunkPosition;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_Explosion extends Explosion {
/*  28 */   public final int field_77289_h = 16;
/*     */   public World world;
/*  30 */   private static Random explosionRNG = new Random();
/*  31 */   public Map field_77288_k = new HashMap<Object, Object>();
/*     */   public boolean isDestroyBlock;
/*     */   public int countSetFireEntity;
/*     */   public boolean isPlaySound;
/*     */   public boolean isInWater;
/*     */   ExplosionResult result;
/*     */   public EntityPlayer explodedPlayer;
/*     */   public float explosionSizeBlock;
/*  39 */   public MCH_DamageFactor damageFactor = null;
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
/*     */   public MCH_Explosion(World par1World, Entity exploder, Entity player, double x, double y, double z, float size) {
/*  53 */     super(par1World, exploder, x, y, z, size);
/*  54 */     this.world = par1World;
/*  55 */     this.isDestroyBlock = false;
/*  56 */     this.explosionSizeBlock = size;
/*  57 */     this.countSetFireEntity = 0;
/*  58 */     this.isPlaySound = true;
/*  59 */     this.isInWater = false;
/*  60 */     this.result = null;
/*  61 */     this.explodedPlayer = (player instanceof EntityPlayer) ? (EntityPlayer)player : null;
/*     */   }
/*     */   public boolean isRemote() {
/*  64 */     return this.world.field_72995_K;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77278_a() {
/*  69 */     HashSet<ChunkPosition> hashset = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     int i = 0; getClass(); for (; i < 16; i++) {
/*     */       
/*  79 */       int m = 0; getClass(); for (; m < 16; m++) {
/*     */         
/*  81 */         int n = 0; getClass(); for (; n < 16; n++) {
/*     */           
/*  83 */           getClass(); getClass(); getClass(); if (i == 0 || i == 16 - 1 || m == 0 || m == 16 - 1 || n == 0 || n == 16 - 1) {
/*     */             
/*  85 */             getClass(); double d3 = (i / (16.0F - 1.0F) * 2.0F - 1.0F);
/*  86 */             getClass(); double d4 = (m / (16.0F - 1.0F) * 2.0F - 1.0F);
/*  87 */             getClass(); double d5 = (n / (16.0F - 1.0F) * 2.0F - 1.0F);
/*  88 */             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*  89 */             d3 /= d6;
/*  90 */             d4 /= d6;
/*  91 */             d5 /= d6;
/*  92 */             float f1 = this.explosionSizeBlock * (0.7F + this.world.field_73012_v.nextFloat() * 0.6F);
/*  93 */             double d0 = this.field_77284_b;
/*  94 */             double d1 = this.field_77285_c;
/*  95 */             double d2 = this.field_77282_d;
/*     */             
/*  97 */             for (float f2 = 0.3F; f1 > 0.0F; f1 -= 0.22500001F) {
/*     */               
/*  99 */               int l = MathHelper.func_76128_c(d0);
/* 100 */               int i1 = MathHelper.func_76128_c(d1);
/* 101 */               int j1 = MathHelper.func_76128_c(d2);
/* 102 */               int k1 = W_WorldFunc.getBlockId(this.world, l, i1, j1);
/*     */               
/* 104 */               if (k1 > 0) {
/*     */                 float f3;
/* 106 */                 Block block = W_WorldFunc.getBlock(this.world, l, i1, j1);
/*     */                 
/* 108 */                 if (this.field_77283_e != null) {
/*     */                   
/* 110 */                   f3 = W_Entity.getBlockExplosionResistance(this.field_77283_e, this, this.world, l, i1, j1, block);
/*     */                 }
/*     */                 else {
/*     */                   
/* 114 */                   f3 = block.getExplosionResistance(this.field_77283_e, this.world, l, i1, j1, this.field_77284_b, this.field_77285_c, this.field_77282_d);
/*     */                 } 
/*     */                 
/* 117 */                 if (this.isInWater)
/*     */                 {
/* 119 */                   f3 *= this.world.field_73012_v.nextFloat() * 0.2F + 0.2F;
/*     */                 }
/*     */                 
/* 122 */                 f1 -= (f3 + 0.3F) * 0.3F;
/*     */               } 
/*     */               
/* 125 */               if (f1 > 0.0F && (this.field_77283_e == null || W_Entity.shouldExplodeBlock(this.field_77283_e, this, this.world, l, i1, j1, k1, f1)))
/*     */               {
/* 127 */                 hashset.add(new ChunkPosition(l, i1, j1));
/*     */               }
/*     */               
/* 130 */               d0 += d3 * 0.30000001192092896D;
/* 131 */               d1 += d4 * 0.30000001192092896D;
/* 132 */               d2 += d5 * 0.30000001192092896D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     float f = this.field_77280_f;
/*     */     
/* 141 */     this.field_77281_g.addAll(hashset);
/* 142 */     this.field_77280_f *= 2.0F;
/* 143 */     i = MathHelper.func_76128_c(this.field_77284_b - this.field_77280_f - 1.0D);
/* 144 */     int j = MathHelper.func_76128_c(this.field_77284_b + this.field_77280_f + 1.0D);
/* 145 */     int k = MathHelper.func_76128_c(this.field_77285_c - this.field_77280_f - 1.0D);
/* 146 */     int l1 = MathHelper.func_76128_c(this.field_77285_c + this.field_77280_f + 1.0D);
/* 147 */     int i2 = MathHelper.func_76128_c(this.field_77282_d - this.field_77280_f - 1.0D);
/* 148 */     int j2 = MathHelper.func_76128_c(this.field_77282_d + this.field_77280_f + 1.0D);
/* 149 */     List<Entity> list = this.world.func_72839_b(this.field_77283_e, W_AxisAlignedBB.getAABB(i, k, i2, j, l1, j2));
/* 150 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(this.world, this.field_77284_b, this.field_77285_c, this.field_77282_d);
/*     */ 
/*     */     
/* 153 */     this.field_77283_e = (Entity)this.explodedPlayer;
/*     */     
/* 155 */     for (int k2 = 0; k2 < list.size(); k2++) {
/*     */       
/* 157 */       Entity entity = list.get(k2);
/* 158 */       double d7 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / this.field_77280_f;
/*     */       
/* 160 */       if (d7 <= 1.0D) {
/*     */         
/* 162 */         double d0 = entity.field_70165_t - this.field_77284_b;
/* 163 */         double d1 = entity.field_70163_u + entity.func_70047_e() - this.field_77285_c;
/* 164 */         double d2 = entity.field_70161_v - this.field_77282_d;
/* 165 */         double d8 = MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
/*     */         
/* 167 */         if (d8 != 0.0D) {
/*     */           
/* 169 */           d0 /= d8;
/* 170 */           d1 /= d8;
/* 171 */           d2 /= d8;
/* 172 */           double d9 = getBlockDensity(vec3, entity.field_70121_D);
/* 173 */           double d10 = (1.0D - d7) * d9;
/* 174 */           float damage = (int)((d10 * d10 + d10) / 2.0D * 8.0D * this.field_77280_f + 1.0D);
/* 175 */           if (damage > 0.0F && this.result != null)
/*     */           {
/* 177 */             if (!(entity instanceof net.minecraft.entity.item.EntityItem) && !(entity instanceof net.minecraft.entity.item.EntityExpBottle) && !(entity instanceof net.minecraft.entity.item.EntityXPOrb) && !W_Entity.isEntityFallingBlock(entity))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 184 */               if (entity instanceof MCH_EntityBaseBullet && this.field_77283_e instanceof EntityPlayer) {
/*     */ 
/*     */                 
/* 187 */                 if (!W_Entity.isEqual(((MCH_EntityBaseBullet)entity).shootingEntity, this.field_77283_e))
/*     */                 {
/* 189 */                   this.result.hitEntity = true;
/* 190 */                   MCH_Lib.DbgLog(this.world, "MCH_Explosion.doExplosionA:Damage=%.1f:HitEntityBullet=" + entity.getClass(), new Object[] { Float.valueOf(damage) });
/*     */                 }
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 196 */                 MCH_Lib.DbgLog(this.world, "MCH_Explosion.doExplosionA:Damage=%.1f:HitEntity=" + entity.getClass(), new Object[] { Float.valueOf(damage) });
/* 197 */                 this.result.hitEntity = true;
/*     */               } 
/*     */             }
/*     */           }
/*     */           
/* 202 */           MCH_Lib.applyEntityHurtResistantTimeConfig(entity);
/*     */           
/* 204 */           DamageSource ds = DamageSource.func_94539_a(this);
/* 205 */           damage = MCH_Config.applyDamageVsEntity(entity, ds, damage);
/* 206 */           damage *= (this.damageFactor != null) ? this.damageFactor.getDamageFactor(entity) : 1.0F;
/* 207 */           W_Entity.attackEntityFrom(entity, ds, damage);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 212 */           double d11 = EnchantmentProtection.func_92092_a(entity, d10);
/* 213 */           if (!(entity instanceof MCH_EntityBaseBullet)) {
/*     */             
/* 215 */             entity.field_70159_w += d0 * d11 * 0.4D;
/* 216 */             entity.field_70181_x += d1 * d11 * 0.1D;
/* 217 */             entity.field_70179_y += d2 * d11 * 0.4D;
/*     */           } 
/*     */           
/* 220 */           if (entity instanceof EntityPlayer)
/*     */           {
/* 222 */             this.field_77288_k.put((EntityPlayer)entity, W_WorldFunc.getWorldVec3(this.world, d0 * d10, d1 * d10, d2 * d10));
/*     */           }
/*     */           
/* 225 */           if (damage > 0.0F && this.countSetFireEntity > 0) {
/*     */             
/* 227 */             double fireFactor = 1.0D - d8 / this.field_77280_f;
/* 228 */             if (fireFactor > 0.0D)
/*     */             {
/* 230 */               entity.func_70015_d((int)(fireFactor * this.countSetFireEntity));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     this.field_77280_f = f;
/*     */   }
/*     */ 
/*     */   
/*     */   private double getBlockDensity(Vec3 vec3, AxisAlignedBB p_72842_2_) {
/* 242 */     double d0 = 1.0D / ((p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * 2.0D + 1.0D);
/* 243 */     double d1 = 1.0D / ((p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * 2.0D + 1.0D);
/* 244 */     double d2 = 1.0D / ((p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * 2.0D + 1.0D);
/*     */     
/* 246 */     if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
/*     */       
/* 248 */       int i = 0;
/* 249 */       int j = 0;
/*     */       float f;
/* 251 */       for (f = 0.0F; f <= 1.0F; f = (float)(f + d0)) {
/*     */         float f1;
/* 253 */         for (f1 = 0.0F; f1 <= 1.0F; f1 = (float)(f1 + d1)) {
/*     */           float f2;
/* 255 */           for (f2 = 0.0F; f2 <= 1.0F; f2 = (float)(f2 + d2)) {
/*     */             
/* 257 */             double d3 = p_72842_2_.field_72340_a + (p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * f;
/* 258 */             double d4 = p_72842_2_.field_72338_b + (p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * f1;
/* 259 */             double d5 = p_72842_2_.field_72339_c + (p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * f2;
/*     */             
/* 261 */             if (this.world.func_147447_a(Vec3.func_72443_a(d3, d4, d5), vec3, false, true, false) == null)
/*     */             {
/* 263 */               i++;
/*     */             }
/*     */             
/* 266 */             j++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 271 */       return (i / j);
/*     */     } 
/*     */ 
/*     */     
/* 275 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77279_a(boolean par1) {
/* 282 */     if (this.isPlaySound)
/*     */     {
/* 284 */       W_WorldFunc.DEF_playSoundEffect(this.world, this.field_77284_b, this.field_77285_c, this.field_77282_d, "random.explode", 4.0F, (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
/*     */     }
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
/* 297 */     if (this.field_82755_b) {
/*     */       
/* 299 */       Iterator<ChunkPosition> iterator = this.field_77281_g.iterator();
/*     */       
/* 301 */       while (iterator.hasNext()) {
/*     */         
/* 303 */         ChunkPosition chunkposition = iterator.next();
/* 304 */         int i = W_ChunkPosition.getChunkPosX(chunkposition);
/* 305 */         int j = W_ChunkPosition.getChunkPosY(chunkposition);
/* 306 */         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
/* 307 */         int l = W_WorldFunc.getBlockId(this.world, i, j, k);
/*     */         
/* 309 */         if (l > 0 && this.isDestroyBlock && this.explosionSizeBlock > 0.0F) if (MCH_Config.Explosion_DestroyBlock.prmBool) {
/*     */             
/* 311 */             Block block = W_Block.getBlockById(l);
/*     */             
/* 313 */             if (block.func_149659_a(this))
/*     */             {
/* 315 */               block.func_149690_a(this.world, i, j, k, this.world.func_72805_g(i, j, k), 1.0F / this.explosionSizeBlock, 0);
/*     */             }
/*     */             
/* 318 */             block.onBlockExploded(this.world, i, j, k, this);
/*     */           } 
/*     */       
/*     */       } 
/*     */     } 
/* 323 */     if (this.field_77286_a) if (MCH_Config.Explosion_FlamingBlock.prmBool) {
/*     */         
/* 325 */         Iterator<ChunkPosition> iterator = this.field_77281_g.iterator();
/*     */         
/* 327 */         while (iterator.hasNext()) {
/*     */           
/* 329 */           ChunkPosition chunkposition = iterator.next();
/* 330 */           int i = W_ChunkPosition.getChunkPosX(chunkposition);
/* 331 */           int j = W_ChunkPosition.getChunkPosY(chunkposition);
/* 332 */           int k = W_ChunkPosition.getChunkPosZ(chunkposition);
/* 333 */           int l = W_WorldFunc.getBlockId(this.world, i, j, k);
/* 334 */           Block b = W_WorldFunc.getBlock(this.world, i, j - 1, k);
/*     */           
/* 336 */           if (l == 0 && b != null && b.func_149662_c()) { this; if (explosionRNG.nextInt(3) == 0)
/*     */             {
/* 338 */               W_WorldFunc.setBlock(this.world, i, j, k, (Block)W_Blocks.field_150480_ab);
/*     */             } }
/*     */         
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   public ExplosionResult newExplosionResult() {
/* 346 */     return new ExplosionResult(this);
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
/*     */   public static ExplosionResult newExplosion(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity) {
/* 377 */     return newExplosion(w, entityExploded, player, x, y, z, size, sizeBlock, playSound, isSmoking, isFlaming, isDestroyBlock, countSetFireEntity, null);
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
/*     */   public static ExplosionResult newExplosion(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity, MCH_DamageFactor df) {
/* 392 */     if (w.field_72995_K) return null;
/*     */     
/* 394 */     mcheli.MCH_Explosion exp = new mcheli.MCH_Explosion(w, entityExploded, player, x, y, z, size);
/*     */     
/* 396 */     exp.field_82755_b = w.func_82736_K().func_82766_b("mobGriefing");
/* 397 */     exp.field_77286_a = isFlaming;
/* 398 */     exp.isDestroyBlock = isDestroyBlock;
/* 399 */     exp.explosionSizeBlock = sizeBlock;
/* 400 */     exp.countSetFireEntity = countSetFireEntity;
/* 401 */     exp.isPlaySound = playSound;
/* 402 */     exp.isInWater = false;
/* 403 */     exp.result = exp.newExplosionResult();
/* 404 */     exp.damageFactor = df;
/*     */     
/* 406 */     exp.func_77278_a();
/* 407 */     exp.func_77279_a(true);
/*     */     
/* 409 */     MCH_PacketEffectExplosion.ExplosionParam param = MCH_PacketEffectExplosion.create();
/* 410 */     param.exploderID = W_Entity.getEntityId(entityExploded);
/* 411 */     param.posX = x;
/* 412 */     param.posY = y;
/* 413 */     param.posZ = z;
/* 414 */     param.size = size;
/* 415 */     param.inWater = false;
/* 416 */     MCH_PacketEffectExplosion.send(param);
/*     */     
/* 418 */     return exp.result;
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
/*     */   public static ExplosionResult newExplosionInWater(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity, MCH_DamageFactor df) {
/* 435 */     if (w.field_72995_K) return null;
/*     */     
/* 437 */     mcheli.MCH_Explosion exp = new mcheli.MCH_Explosion(w, entityExploded, player, x, y, z, size);
/*     */     
/* 439 */     exp.field_82755_b = w.func_82736_K().func_82766_b("mobGriefing");
/* 440 */     exp.field_77286_a = isFlaming;
/* 441 */     exp.isDestroyBlock = isDestroyBlock;
/* 442 */     exp.explosionSizeBlock = sizeBlock;
/* 443 */     exp.countSetFireEntity = countSetFireEntity;
/* 444 */     exp.isPlaySound = playSound;
/* 445 */     exp.isInWater = true;
/* 446 */     exp.result = exp.newExplosionResult();
/* 447 */     exp.damageFactor = df;
/*     */     
/* 449 */     exp.func_77278_a();
/* 450 */     exp.func_77279_a(true);
/*     */     
/* 452 */     MCH_PacketEffectExplosion.ExplosionParam param = MCH_PacketEffectExplosion.create();
/* 453 */     param.exploderID = W_Entity.getEntityId(entityExploded);
/* 454 */     param.posX = x;
/* 455 */     param.posY = y;
/* 456 */     param.posZ = z;
/* 457 */     param.size = size;
/* 458 */     param.inWater = true;
/* 459 */     MCH_PacketEffectExplosion.send(param);
/*     */     
/* 461 */     return exp.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void playExplosionSound(World w, double x, double y, double z) {
/* 466 */     Random rand = new Random();
/* 467 */     W_WorldFunc.DEF_playSoundEffect(w, x, y, z, "random.explode", 4.0F, (1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F) * 0.7F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void effectExplosion(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
/* 475 */     List<ChunkPosition> affectedBlockPositions = new ArrayList();
/* 476 */     int field_77289_h = 16;
/*     */     
/* 478 */     float f = explosionSize;
/* 479 */     HashSet<ChunkPosition> hashset = new HashSet();
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */     
/* 487 */     for (i = 0; i < 16; i++) {
/*     */       
/* 489 */       for (int j = 0; j < 16; j++) {
/*     */         
/* 491 */         for (int k = 0; k < 16; k++) {
/*     */           
/* 493 */           if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
/*     */             
/* 495 */             double d3 = (i / 15.0F * 2.0F - 1.0F);
/* 496 */             double d4 = (j / 15.0F * 2.0F - 1.0F);
/* 497 */             double d5 = (k / 15.0F * 2.0F - 1.0F);
/* 498 */             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/* 499 */             d3 /= d6;
/* 500 */             d4 /= d6;
/* 501 */             d5 /= d6;
/* 502 */             float f1 = explosionSize * (0.7F + world.field_73012_v.nextFloat() * 0.6F);
/* 503 */             double d0 = explosionX;
/* 504 */             double d1 = explosionY;
/* 505 */             double d2 = explosionZ;
/*     */             
/* 507 */             for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
/*     */               
/* 509 */               int l = MathHelper.func_76128_c(d0);
/* 510 */               int i1 = MathHelper.func_76128_c(d1);
/* 511 */               int j1 = MathHelper.func_76128_c(d2);
/* 512 */               int k1 = W_WorldFunc.getBlockId(world, l, i1, j1);
/*     */               
/* 514 */               if (k1 > 0) {
/*     */                 
/* 516 */                 Block block = W_Block.getBlockById(k1);
/* 517 */                 float f3 = block.getExplosionResistance(exploder, world, l, i1, j1, explosionX, explosionY, explosionZ);
/* 518 */                 f1 -= (f3 + 0.3F) * f2;
/*     */               } 
/*     */               
/* 521 */               if (f1 > 0.0F)
/*     */               {
/* 523 */                 hashset.add(new ChunkPosition(l, i1, j1));
/*     */               }
/*     */               
/* 526 */               d0 += d3 * f2;
/* 527 */               d1 += d4 * f2;
/* 528 */               d2 += d5 * f2;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 535 */     affectedBlockPositions.addAll(hashset);
/*     */ 
/*     */ 
/*     */     
/* 539 */     if (explosionSize >= 2.0F && isSmoking) {
/*     */       
/* 541 */       MCH_ParticlesUtil.DEF_spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 547 */       MCH_ParticlesUtil.DEF_spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 556 */     if (isSmoking) {
/*     */       
/* 558 */       Iterator<ChunkPosition> iterator = affectedBlockPositions.iterator();
/*     */       
/* 560 */       int cnt = 0;
/* 561 */       int flareCnt = (int)explosionSize;
/* 562 */       while (iterator.hasNext()) {
/*     */         
/* 564 */         ChunkPosition chunkposition = iterator.next();
/* 565 */         i = W_ChunkPosition.getChunkPosX(chunkposition);
/* 566 */         int j = W_ChunkPosition.getChunkPosY(chunkposition);
/* 567 */         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
/* 568 */         int l = W_WorldFunc.getBlockId(world, i, j, k);
/*     */         
/* 570 */         cnt++;
/*     */ 
/*     */         
/* 573 */         double d0 = (i + world.field_73012_v.nextFloat());
/* 574 */         double d1 = (j + world.field_73012_v.nextFloat());
/* 575 */         double d2 = (k + world.field_73012_v.nextFloat());
/* 576 */         double mx = d0 - explosionX;
/* 577 */         double my = d1 - explosionY;
/* 578 */         double mz = d2 - explosionZ;
/* 579 */         double d6 = MathHelper.func_76133_a(mx * mx + my * my + mz * mz);
/* 580 */         mx /= d6;
/* 581 */         my /= d6;
/* 582 */         mz /= d6;
/* 583 */         double d7 = 0.5D / (d6 / explosionSize + 0.1D);
/* 584 */         d7 *= (world.field_73012_v.nextFloat() * world.field_73012_v.nextFloat() + 0.3F);
/* 585 */         mx *= d7 * 0.5D;
/* 586 */         my *= d7 * 0.5D;
/* 587 */         mz *= d7 * 0.5D;
/*     */         
/* 589 */         double px = (d0 + explosionX * 1.0D) / 2.0D;
/* 590 */         double py = (d1 + explosionY * 1.0D) / 2.0D;
/* 591 */         double pz = (d2 + explosionZ * 1.0D) / 2.0D;
/*     */         
/* 593 */         double r = Math.PI * world.field_73012_v.nextInt(360) / 180.0D;
/* 594 */         if (explosionSize >= 4.0F && flareCnt > 0) {
/*     */           
/* 596 */           double a = Math.min((explosionSize / 12.0F), 0.6D) * (0.5F + world.field_73012_v.nextFloat() * 0.5F);
/* 597 */           world.func_72838_d((Entity)new MCH_EntityFlare(world, px, py + 2.0D, pz, Math.sin(r) * a, (1.0D + my / 5.0D) * a, Math.cos(r) * a, 2.0F, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 605 */           flareCnt--;
/*     */         } 
/*     */         
/* 608 */         if (cnt % 4 == 0) {
/*     */           
/* 610 */           float bdf = Math.min(explosionSize / 3.0F, 2.0F) * (0.5F + world.field_73012_v.nextFloat() * 0.5F);
/* 611 */           boolean ret = MCH_ParticlesUtil.spawnParticleTileDust(world, (int)(px + 0.5D), (int)(py - 0.5D), (int)(pz + 0.5D), px, py + 1.0D, pz, Math.sin(r) * bdf, 0.5D + my / 5.0D * bdf, Math.cos(r) * bdf, Math.min(explosionSize / 2.0F, 3.0F) * (0.5F + world.field_73012_v.nextFloat() * 0.5F));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 622 */         int es = (int)((explosionSize >= 4.0F) ? explosionSize : 4.0F);
/* 623 */         if (explosionSize <= 1.0F || cnt % es == 0) {
/*     */           
/* 625 */           if (world.field_73012_v.nextBoolean()) {
/*     */             
/* 627 */             my *= 3.0D;
/* 628 */             mx *= 0.1D;
/* 629 */             mz *= 0.1D;
/*     */           }
/*     */           else {
/*     */             
/* 633 */             my *= 0.2D;
/* 634 */             mx *= 3.0D;
/* 635 */             mz *= 3.0D;
/*     */           } 
/*     */ 
/*     */           
/* 639 */           MCH_ParticleParam prm = new MCH_ParticleParam(world, "explode", px, py, pz, mx, my, mz, (explosionSize < 8.0F) ? ((explosionSize < 2.0F) ? 2.0F : (explosionSize * 2.0F)) : 16.0F);
/*     */ 
/*     */ 
/*     */           
/* 643 */           prm.r = prm.g = prm.b = 0.3F + world.field_73012_v.nextFloat() * 0.4F;
/* 644 */           prm.r += 0.1F;
/* 645 */           prm.g += 0.05F;
/* 646 */           prm.b += 0.0F;
/* 647 */           prm.age = 10 + world.field_73012_v.nextInt(30);
/* 648 */           prm.age = (int)(prm.age * ((explosionSize < 6.0F) ? explosionSize : 6.0F));
/* 649 */           prm.age = prm.age * 2 / 3;
/* 650 */           prm.diffusible = true;
/*     */           
/* 652 */           MCH_ParticlesUtil.spawnParticle(prm);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void DEF_effectExplosion(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
/* 666 */     List<ChunkPosition> affectedBlockPositions = new ArrayList();
/* 667 */     int field_77289_h = 16;
/*     */     
/* 669 */     float f = explosionSize;
/* 670 */     HashSet<ChunkPosition> hashset = new HashSet();
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */     
/* 678 */     for (i = 0; i < 16; i++) {
/*     */       
/* 680 */       for (int j = 0; j < 16; j++) {
/*     */         
/* 682 */         for (int k = 0; k < 16; k++) {
/*     */           
/* 684 */           if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
/*     */             
/* 686 */             double d3 = (i / 15.0F * 2.0F - 1.0F);
/* 687 */             double d4 = (j / 15.0F * 2.0F - 1.0F);
/* 688 */             double d5 = (k / 15.0F * 2.0F - 1.0F);
/* 689 */             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/* 690 */             d3 /= d6;
/* 691 */             d4 /= d6;
/* 692 */             d5 /= d6;
/* 693 */             float f1 = explosionSize * (0.7F + world.field_73012_v.nextFloat() * 0.6F);
/* 694 */             double d0 = explosionX;
/* 695 */             double d1 = explosionY;
/* 696 */             double d2 = explosionZ;
/*     */             
/* 698 */             for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
/*     */               
/* 700 */               int l = MathHelper.func_76128_c(d0);
/* 701 */               int i1 = MathHelper.func_76128_c(d1);
/* 702 */               int j1 = MathHelper.func_76128_c(d2);
/* 703 */               int k1 = W_WorldFunc.getBlockId(world, l, i1, j1);
/*     */               
/* 705 */               if (k1 > 0) {
/*     */                 
/* 707 */                 Block block = W_Block.getBlockById(k1);
/* 708 */                 float f3 = block.getExplosionResistance(exploder, world, l, i1, j1, explosionX, explosionY, explosionZ);
/* 709 */                 f1 -= (f3 + 0.3F) * f2;
/*     */               } 
/*     */               
/* 712 */               if (f1 > 0.0F)
/*     */               {
/* 714 */                 hashset.add(new ChunkPosition(l, i1, j1));
/*     */               }
/*     */               
/* 717 */               d0 += d3 * f2;
/* 718 */               d1 += d4 * f2;
/* 719 */               d2 += d5 * f2;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 726 */     affectedBlockPositions.addAll(hashset);
/*     */ 
/*     */ 
/*     */     
/* 730 */     if (explosionSize >= 2.0F && isSmoking) {
/*     */       
/* 732 */       MCH_ParticlesUtil.DEF_spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
/*     */     }
/*     */     else {
/*     */       
/* 736 */       MCH_ParticlesUtil.DEF_spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 743 */     if (isSmoking) {
/*     */       
/* 745 */       Iterator<ChunkPosition> iterator = affectedBlockPositions.iterator();
/*     */       
/* 747 */       while (iterator.hasNext()) {
/*     */         
/* 749 */         ChunkPosition chunkposition = iterator.next();
/* 750 */         i = W_ChunkPosition.getChunkPosX(chunkposition);
/* 751 */         int j = W_ChunkPosition.getChunkPosY(chunkposition);
/* 752 */         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
/* 753 */         int l = W_WorldFunc.getBlockId(world, i, j, k);
/*     */ 
/*     */ 
/*     */         
/* 757 */         double d0 = (i + world.field_73012_v.nextFloat());
/* 758 */         double d1 = (j + world.field_73012_v.nextFloat());
/* 759 */         double d2 = (k + world.field_73012_v.nextFloat());
/* 760 */         double d3 = d0 - explosionX;
/* 761 */         double d4 = d1 - explosionY;
/* 762 */         double d5 = d2 - explosionZ;
/* 763 */         double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 764 */         d3 /= d6;
/* 765 */         d4 /= d6;
/* 766 */         d5 /= d6;
/* 767 */         double d7 = 0.5D / (d6 / explosionSize + 0.1D);
/* 768 */         d7 *= (world.field_73012_v.nextFloat() * world.field_73012_v.nextFloat() + 0.3F);
/* 769 */         d3 *= d7;
/* 770 */         d4 *= d7;
/* 771 */         d5 *= d7;
/* 772 */         MCH_ParticlesUtil.DEF_spawnParticle("explode", (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3, d4, d5, 10.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 778 */         MCH_ParticlesUtil.DEF_spawnParticle("smoke", d0, d1, d2, d3, d4, d5, 10.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void effectExplosionInWater(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
/* 788 */     if (explosionSize <= 0.0F)
/* 789 */       return;  int range = (int)(explosionSize + 0.5D) / 1;
/* 790 */     int ex = (int)(explosionX + 0.5D);
/* 791 */     int ey = (int)(explosionY + 0.5D);
/* 792 */     int ez = (int)(explosionZ + 0.5D);
/*     */ 
/*     */ 
/*     */     
/* 796 */     for (int y = -range; y <= range; y++) {
/*     */       
/* 798 */       if (ey + y >= 1)
/*     */       {
/* 800 */         for (int x = -range; x <= range; x++) {
/*     */           
/* 802 */           for (int z = -range; z <= range; z++) {
/*     */             
/* 804 */             int d = x * x + y * y + z * z;
/* 805 */             if (d < range * range)
/*     */             {
/* 807 */               if (W_Block.func_149680_a(W_WorldFunc.getBlock(world, ex + x, ey + y, ez + z), W_Block.getWater())) {
/*     */                 
/* 809 */                 int n = explosionRNG.nextInt(2);
/* 810 */                 for (int i = 0; i < n; i++) {
/*     */                   
/* 812 */                   MCH_ParticleParam prm = new MCH_ParticleParam(world, "splash", (ex + x), (ey + y), (ez + z), x / range * (explosionRNG.nextFloat() - 0.2D), 1.0D - Math.sqrt((x * x + z * z)) / range + explosionRNG.nextFloat() * 0.4D * range * 0.4D, z / range * (explosionRNG.nextFloat() - 0.2D), (explosionRNG.nextInt(range) * 3 + range));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 818 */                   MCH_ParticlesUtil.spawnParticle(prm);
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Explosion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */