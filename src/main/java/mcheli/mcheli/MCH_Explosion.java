 package mcheli.mcheli;
 import java.util.*;

 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_DamageFactor;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.MCH_PacketEffectExplosion;
 import mcheli.mcheli.particles.MCH_ParticleParam;
 import mcheli.mcheli.particles.MCH_ParticlesUtil;
 import mcheli.mcheli.wrapper.W_Block;
 import mcheli.mcheli.wrapper.W_ChunkPosition;
 import mcheli.mcheli.wrapper.W_Entity;
 import mcheli.mcheli.wrapper.W_WorldFunc;
 import net.minecraft.block.Block;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraft.util.DamageSource;
 import net.minecraft.world.Explosion;
 import net.minecraft.world.World;

 //TODO: RECODE

 public class MCH_Explosion extends Explosion {
   public final int field_77289_h = 16;
   public World world;
   private static Random explosionRNG = new Random();
   public Map field_77288_k = new HashMap<Object, Object>();
   public boolean isDestroyBlock;
   public int countSetFireEntity;
   public boolean isPlaySound;
   public boolean isInWater;
   ExplosionResult result;
   public EntityPlayer explodedPlayer;
   public float explosionSizeBlock;
   public MCH_DamageFactor damageFactor = null;
   
   public MCH_Explosion(World par1World, Entity exploder, Entity player, double x, double y, double z, float size) {
     super(par1World, exploder, x, y, z, size);
     this.world = par1World;
     this.isDestroyBlock = false;
     this.explosionSizeBlock = size;
     this.countSetFireEntity = 0;
     this.isPlaySound = true;
     this.isInWater = false;
     this.result = null;
     this.explodedPlayer = (player instanceof EntityPlayer) ? (EntityPlayer)player : null;
   }
   public boolean isRemote() {
     return this.world.field_72995_K;
   }
 
   public void func_77278_a() {
     HashSet<ChunkPosition> hashset = new HashSet();


     int i = 0; getClass(); for (; i < 16; i++) {
       
       int m = 0; getClass(); for (; m < 16; m++) {
         
         int n = 0; getClass(); for (; n < 16; n++) {
           
           getClass(); getClass(); getClass(); if (i == 0 || i == 16 - 1 || m == 0 || m == 16 - 1 || n == 0 || n == 16 - 1) {
             
             getClass(); double d3 = (i / (16.0F - 1.0F) * 2.0F - 1.0F);
             getClass(); double d4 = (m / (16.0F - 1.0F) * 2.0F - 1.0F);
             getClass(); double d5 = (n / (16.0F - 1.0F) * 2.0F - 1.0F);
             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
             d3 /= d6;
             d4 /= d6;
             d5 /= d6;
             float f1 = this.explosionSizeBlock * (0.7F + this.world.field_73012_v.nextFloat() * 0.6F);
             double d0 = this.field_77284_b;
             double d1 = this.field_77285_c;
             double d2 = this.field_77282_d;
             
             for (float f2 = 0.3F; f1 > 0.0F; f1 -= 0.22500001F) {
               
               int l = MathHelper.func_76128_c(d0);
               int i1 = MathHelper.func_76128_c(d1);
               int j1 = MathHelper.func_76128_c(d2);
               int k1 = W_WorldFunc.getBlockId(this.world, l, i1, j1);
               
               if (k1 > 0) {
                 float f3;
                 Block block = W_WorldFunc.getBlock(this.world, l, i1, j1);
                 
                 if (this.field_77283_e != null) {
                   
                   f3 = W_Entity.getBlockExplosionResistance(this.field_77283_e, this, this.world, l, i1, j1, block);
                 }
                 else {
                   
                   f3 = block.getExplosionResistance(this.field_77283_e, this.world, l, i1, j1, this.field_77284_b, this.field_77285_c, this.field_77282_d);
                 } 
                 
                 if (this.isInWater)
                 {
                   f3 *= this.world.field_73012_v.nextFloat() * 0.2F + 0.2F;
                 }
                 
                 f1 -= (f3 + 0.3F) * 0.3F;
               } 
               
               if (f1 > 0.0F && (this.field_77283_e == null || W_Entity.shouldExplodeBlock(this.field_77283_e, this, this.world, l, i1, j1, k1, f1)))
               {
                 hashset.add(new ChunkPosition(l, i1, j1));
               }
               
               d0 += d3 * 0.30000001192092896D;
               d1 += d4 * 0.30000001192092896D;
               d2 += d5 * 0.30000001192092896D;
             } 
           } 
         } 
       } 
     } 
     
     float f = this.field_77280_f;
     
     this.field_77281_g.addAll(hashset);
     this.field_77280_f *= 2.0F;
     i = MathHelper.func_76128_c(this.field_77284_b - this.field_77280_f - 1.0D);
     int j = MathHelper.func_76128_c(this.field_77284_b + this.field_77280_f + 1.0D);
     int k = MathHelper.func_76128_c(this.field_77285_c - this.field_77280_f - 1.0D);
     int l1 = MathHelper.func_76128_c(this.field_77285_c + this.field_77280_f + 1.0D);
     int i2 = MathHelper.func_76128_c(this.field_77282_d - this.field_77280_f - 1.0D);
     int j2 = MathHelper.func_76128_c(this.field_77282_d + this.field_77280_f + 1.0D);
     List<Entity> list = this.world.func_72839_b(this.field_77283_e, W_AxisAlignedBB.getAABB(i, k, i2, j, l1, j2));
     Vec3 vec3 = W_WorldFunc.getWorldVec3(this.world, this.field_77284_b, this.field_77285_c, this.field_77282_d);
 
     
     this.field_77283_e = (Entity)this.explodedPlayer;
     
     for (int k2 = 0; k2 < list.size(); k2++) {
       
       Entity entity = list.get(k2);
       double d7 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / this.field_77280_f;
       
       if (d7 <= 1.0D) {
         
         double d0 = entity.field_70165_t - this.field_77284_b;
         double d1 = entity.field_70163_u + entity.func_70047_e() - this.field_77285_c;
         double d2 = entity.field_70161_v - this.field_77282_d;
         double d8 = MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
         
         if (d8 != 0.0D) {
           
           d0 /= d8;
           d1 /= d8;
           d2 /= d8;
           double d9 = getBlockDensity(vec3, entity.field_70121_D);
           double d10 = (1.0D - d7) * d9;
           float damage = (int)((d10 * d10 + d10) / 2.0D * 8.0D * this.field_77280_f + 1.0D);
           if (damage > 0.0F && this.result != null)
           {
             if (!(entity instanceof net.minecraft.entity.item.EntityItem) && !(entity instanceof net.minecraft.entity.item.EntityExpBottle) && !(entity instanceof net.minecraft.entity.item.EntityXPOrb) && !W_Entity.isEntityFallingBlock(entity))
             {
          
               if (entity instanceof MCH_EntityBaseBullet && this.field_77283_e instanceof EntityPlayer) {
 
                 
                 if (!W_Entity.isEqual(((MCH_EntityBaseBullet)entity).shootingEntity, this.field_77283_e))
                 {
                   this.result.hitEntity = true;
                   MCH_Lib.DbgLog(this.world, "MCH_Explosion.doExplosionA:Damage=%.1f:HitEntityBullet=" + entity.getClass(), new Object[] { Float.valueOf(damage) });
                 }
               
               }
               else {
                 
                 MCH_Lib.DbgLog(this.world, "MCH_Explosion.doExplosionA:Damage=%.1f:HitEntity=" + entity.getClass(), new Object[] { Float.valueOf(damage) });
                 this.result.hitEntity = true;
               } 
             }
           }
           
           MCH_Lib.applyEntityHurtResistantTimeConfig(entity);
           
           DamageSource ds = DamageSource.func_94539_a(this);
           damage = MCH_Config.applyDamageVsEntity(entity, ds, damage);
           damage *= (this.damageFactor != null) ? this.damageFactor.getDamageFactor(entity) : 1.0F;
           W_Entity.attackEntityFrom(entity, ds, damage);
 
 
 
           
           double d11 = EnchantmentProtection.func_92092_a(entity, d10);
           if (!(entity instanceof MCH_EntityBaseBullet)) {
             
             entity.field_70159_w += d0 * d11 * 0.4D;
             entity.field_70181_x += d1 * d11 * 0.1D;
             entity.field_70179_y += d2 * d11 * 0.4D;
           } 
           
           if (entity instanceof EntityPlayer)
           {
             this.field_77288_k.put((EntityPlayer)entity, W_WorldFunc.getWorldVec3(this.world, d0 * d10, d1 * d10, d2 * d10));
           }
           
           if (damage > 0.0F && this.countSetFireEntity > 0) {
             
             double fireFactor = 1.0D - d8 / this.field_77280_f;
             if (fireFactor > 0.0D)
             {
               entity.func_70015_d((int)(fireFactor * this.countSetFireEntity));
             }
           } 
         } 
       } 
     } 
     
     this.field_77280_f = f;
   }
 
   
   private double getBlockDensity(Vec3 vec3, AxisAlignedBB p_72842_2_) {
     double d0 = 1.0D / ((p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * 2.0D + 1.0D);
     double d1 = 1.0D / ((p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * 2.0D + 1.0D);
     double d2 = 1.0D / ((p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * 2.0D + 1.0D);
     
     if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
       
       int i = 0;
       int j = 0;
       float f;
       for (f = 0.0F; f <= 1.0F; f = (float)(f + d0)) {
         float f1;
         for (f1 = 0.0F; f1 <= 1.0F; f1 = (float)(f1 + d1)) {
           float f2;
           for (f2 = 0.0F; f2 <= 1.0F; f2 = (float)(f2 + d2)) {
             
             double d3 = p_72842_2_.field_72340_a + (p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * f;
             double d4 = p_72842_2_.field_72338_b + (p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * f1;
             double d5 = p_72842_2_.field_72339_c + (p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * f2;
             
             if (this.world.func_147447_a(Vec3.func_72443_a(d3, d4, d5), vec3, false, true, false) == null)
             {
               i++;
             }
             
             j++;
           } 
         } 
       } 
       
       return (i / j);
     } 
 
     
     return 0.0D;
   }
 
 
 
   
   public void func_77279_a(boolean par1) {
     if (this.isPlaySound)
     {
       W_WorldFunc.DEF_playSoundEffect(this.world, this.field_77284_b, this.field_77285_c, this.field_77282_d, "random.explode", 4.0F, (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
     }
 
 
 
 
 
 

     if (this.field_82755_b) {
       
       Iterator<ChunkPosition> iterator = this.field_77281_g.iterator();
       
       while (iterator.hasNext()) {
         
         ChunkPosition chunkposition = iterator.next();
         int i = W_ChunkPosition.getChunkPosX(chunkposition);
         int j = W_ChunkPosition.getChunkPosY(chunkposition);
         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
         int l = W_WorldFunc.getBlockId(this.world, i, j, k);
         
         if (l > 0 && this.isDestroyBlock && this.explosionSizeBlock > 0.0F) if (MCH_Config.Explosion_DestroyBlock.prmBool) {
             
             Block block = W_Block.getBlockById(l);
             
             if (block.func_149659_a(this))
             {
               block.func_149690_a(this.world, i, j, k, this.world.func_72805_g(i, j, k), 1.0F / this.explosionSizeBlock, 0);
             }
             
             block.onBlockExploded(this.world, i, j, k, this);
           } 
       
       } 
     } 
     if (this.field_77286_a) if (MCH_Config.Explosion_FlamingBlock.prmBool) {
         
         Iterator<ChunkPosition> iterator = this.field_77281_g.iterator();
         
         while (iterator.hasNext()) {
           
           ChunkPosition chunkposition = iterator.next();
           int i = W_ChunkPosition.getChunkPosX(chunkposition);
           int j = W_ChunkPosition.getChunkPosY(chunkposition);
           int k = W_ChunkPosition.getChunkPosZ(chunkposition);
           int l = W_WorldFunc.getBlockId(this.world, i, j, k);
           Block b = W_WorldFunc.getBlock(this.world, i, j - 1, k);
           
           if (l == 0 && b != null && b.func_149662_c()) { this; if (explosionRNG.nextInt(3) == 0)
             {
               W_WorldFunc.setBlock(this.world, i, j, k, (Block)W_Blocks.field_150480_ab);
             } }
         
         } 
       }  
   }
   
   public ExplosionResult newExplosionResult() {
     return new ExplosionResult(this);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static ExplosionResult newExplosion(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity) {
     return newExplosion(w, entityExploded, player, x, y, z, size, sizeBlock, playSound, isSmoking, isFlaming, isDestroyBlock, countSetFireEntity, null);
   }
 
 
 
 
 
 
 
 
 
 
 
   
   public static ExplosionResult newExplosion(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity, MCH_DamageFactor df) {
     if (w.field_72995_K) return null;
     
     mcheli.MCH_Explosion exp = new mcheli.MCH_Explosion(w, entityExploded, player, x, y, z, size);
     
     exp.field_82755_b = w.func_82736_K().func_82766_b("mobGriefing");
     exp.field_77286_a = isFlaming;
     exp.isDestroyBlock = isDestroyBlock;
     exp.explosionSizeBlock = sizeBlock;
     exp.countSetFireEntity = countSetFireEntity;
     exp.isPlaySound = playSound;
     exp.isInWater = false;
     exp.result = exp.newExplosionResult();
     exp.damageFactor = df;
     
     exp.func_77278_a();
     exp.func_77279_a(true);
     
     MCH_PacketEffectExplosion.ExplosionParam param = MCH_PacketEffectExplosion.create();
     param.exploderID = W_Entity.getEntityId(entityExploded);
     param.posX = x;
     param.posY = y;
     param.posZ = z;
     param.size = size;
     param.inWater = false;
     MCH_PacketEffectExplosion.send(param);
     
     return exp.result;
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static ExplosionResult newExplosionInWater(World w, Entity entityExploded, Entity player, double x, double y, double z, float size, float sizeBlock, boolean playSound, boolean isSmoking, boolean isFlaming, boolean isDestroyBlock, int countSetFireEntity, MCH_DamageFactor df) {
     if (w.field_72995_K) return null;
     
     mcheli.MCH_Explosion exp = new mcheli.MCH_Explosion(w, entityExploded, player, x, y, z, size);
     
     exp.field_82755_b = w.func_82736_K().func_82766_b("mobGriefing");
     exp.field_77286_a = isFlaming;
     exp.isDestroyBlock = isDestroyBlock;
     exp.explosionSizeBlock = sizeBlock;
     exp.countSetFireEntity = countSetFireEntity;
     exp.isPlaySound = playSound;
     exp.isInWater = true;
     exp.result = exp.newExplosionResult();
     exp.damageFactor = df;
     
     exp.func_77278_a();
     exp.func_77279_a(true);
     
     MCH_PacketEffectExplosion.ExplosionParam param = MCH_PacketEffectExplosion.create();
     param.exploderID = W_Entity.getEntityId(entityExploded);
     param.posX = x;
     param.posY = y;
     param.posZ = z;
     param.size = size;
     param.inWater = true;
     MCH_PacketEffectExplosion.send(param);
     
     return exp.result;
   }
 
   
   public static void playExplosionSound(World w, double x, double y, double z) {
     Random rand = new Random();
     W_WorldFunc.DEF_playSoundEffect(w, x, y, z, "random.explode", 4.0F, (1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F) * 0.7F);
   }
 
 
 
 
   
   public static void effectExplosion(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
     List<ChunkPosition> affectedBlockPositions = new ArrayList();
     int field_77289_h = 16;
     
     float f = explosionSize;
     HashSet<ChunkPosition> hashset = new HashSet();
 
 
     
     int i;
 
 
     
     for (i = 0; i < 16; i++) {
       
       for (int j = 0; j < 16; j++) {
         
         for (int k = 0; k < 16; k++) {
           
           if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
             
             double d3 = (i / 15.0F * 2.0F - 1.0F);
             double d4 = (j / 15.0F * 2.0F - 1.0F);
             double d5 = (k / 15.0F * 2.0F - 1.0F);
             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
             d3 /= d6;
             d4 /= d6;
             d5 /= d6;
             float f1 = explosionSize * (0.7F + world.field_73012_v.nextFloat() * 0.6F);
             double d0 = explosionX;
             double d1 = explosionY;
             double d2 = explosionZ;
             
             for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
               
               int l = MathHelper.func_76128_c(d0);
               int i1 = MathHelper.func_76128_c(d1);
               int j1 = MathHelper.func_76128_c(d2);
               int k1 = W_WorldFunc.getBlockId(world, l, i1, j1);
               
               if (k1 > 0) {
                 
                 Block block = W_Block.getBlockById(k1);
                 float f3 = block.getExplosionResistance(exploder, world, l, i1, j1, explosionX, explosionY, explosionZ);
                 f1 -= (f3 + 0.3F) * f2;
               } 
               
               if (f1 > 0.0F)
               {
                 hashset.add(new ChunkPosition(l, i1, j1));
               }
               
               d0 += d3 * f2;
               d1 += d4 * f2;
               d2 += d5 * f2;
             } 
           } 
         } 
       } 
     } 
     
     affectedBlockPositions.addAll(hashset);
 
 
     
     if (explosionSize >= 2.0F && isSmoking) {
       
       MCH_ParticlesUtil.DEF_spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
     
     }
     else {
 
       
       MCH_ParticlesUtil.DEF_spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
     } 
 
 

     if (isSmoking) {
       
       Iterator<ChunkPosition> iterator = affectedBlockPositions.iterator();
       
       int cnt = 0;
       int flareCnt = (int)explosionSize;
       while (iterator.hasNext()) {
         
         ChunkPosition chunkposition = iterator.next();
         i = W_ChunkPosition.getChunkPosX(chunkposition);
         int j = W_ChunkPosition.getChunkPosY(chunkposition);
         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
         int l = W_WorldFunc.getBlockId(world, i, j, k);
         
         cnt++;
 
         
         double d0 = (i + world.field_73012_v.nextFloat());
         double d1 = (j + world.field_73012_v.nextFloat());
         double d2 = (k + world.field_73012_v.nextFloat());
         double mx = d0 - explosionX;
         double my = d1 - explosionY;
         double mz = d2 - explosionZ;
         double d6 = MathHelper.func_76133_a(mx * mx + my * my + mz * mz);
         mx /= d6;
         my /= d6;
         mz /= d6;
         double d7 = 0.5D / (d6 / explosionSize + 0.1D);
         d7 *= (world.field_73012_v.nextFloat() * world.field_73012_v.nextFloat() + 0.3F);
         mx *= d7 * 0.5D;
         my *= d7 * 0.5D;
         mz *= d7 * 0.5D;
         
         double px = (d0 + explosionX * 1.0D) / 2.0D;
         double py = (d1 + explosionY * 1.0D) / 2.0D;
         double pz = (d2 + explosionZ * 1.0D) / 2.0D;
         
         double r = Math.PI * world.field_73012_v.nextInt(360) / 180.0D;
         if (explosionSize >= 4.0F && flareCnt > 0) {
           
           double a = Math.min((explosionSize / 12.0F), 0.6D) * (0.5F + world.field_73012_v.nextFloat() * 0.5F);
           world.func_72838_d((Entity)new MCH_EntityFlare(world, px, py + 2.0D, pz, Math.sin(r) * a, (1.0D + my / 5.0D) * a, Math.cos(r) * a, 2.0F, 0));
 
 
      
           flareCnt--;
         } 
         
         if (cnt % 4 == 0) {
           
           float bdf = Math.min(explosionSize / 3.0F, 2.0F) * (0.5F + world.field_73012_v.nextFloat() * 0.5F);
           boolean ret = MCH_ParticlesUtil.spawnParticleTileDust(world, (int)(px + 0.5D), (int)(py - 0.5D), (int)(pz + 0.5D), px, py + 1.0D, pz, Math.sin(r) * bdf, 0.5D + my / 5.0D * bdf, Math.cos(r) * bdf, Math.min(explosionSize / 2.0F, 3.0F) * (0.5F + world.field_73012_v.nextFloat() * 0.5F));
         } 
 
 
 
 
    
         int es = (int)((explosionSize >= 4.0F) ? explosionSize : 4.0F);
         if (explosionSize <= 1.0F || cnt % es == 0) {
           
           if (world.field_73012_v.nextBoolean()) {
             
             my *= 3.0D;
             mx *= 0.1D;
             mz *= 0.1D;
           }
           else {
             
             my *= 0.2D;
             mx *= 3.0D;
             mz *= 3.0D;
           } 
 
           
           MCH_ParticleParam prm = new MCH_ParticleParam(world, "explode", px, py, pz, mx, my, mz, (explosionSize < 8.0F) ? ((explosionSize < 2.0F) ? 2.0F : (explosionSize * 2.0F)) : 16.0F);
 
 
           
           prm.r = prm.g = prm.b = 0.3F + world.field_73012_v.nextFloat() * 0.4F;
           prm.r += 0.1F;
           prm.g += 0.05F;
           prm.b += 0.0F;
           prm.age = 10 + world.field_73012_v.nextInt(30);
           prm.age = (int)(prm.age * ((explosionSize < 6.0F) ? explosionSize : 6.0F));
           prm.age = prm.age * 2 / 3;
           prm.diffusible = true;
           
           MCH_ParticlesUtil.spawnParticle(prm);
         } 
       } 
     } 
   }
 
 
 
 
 
 
 
   
   public static void DEF_effectExplosion(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
     List<ChunkPosition> affectedBlockPositions = new ArrayList();
     int field_77289_h = 16;
     
     float f = explosionSize;
     HashSet<ChunkPosition> hashset = new HashSet();
 
 
     
     int i;
 
 
     
     for (i = 0; i < 16; i++) {
       
       for (int j = 0; j < 16; j++) {
         
         for (int k = 0; k < 16; k++) {
           
           if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
             
             double d3 = (i / 15.0F * 2.0F - 1.0F);
             double d4 = (j / 15.0F * 2.0F - 1.0F);
             double d5 = (k / 15.0F * 2.0F - 1.0F);
             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
             d3 /= d6;
             d4 /= d6;
             d5 /= d6;
             float f1 = explosionSize * (0.7F + world.field_73012_v.nextFloat() * 0.6F);
             double d0 = explosionX;
             double d1 = explosionY;
             double d2 = explosionZ;
             
             for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
               
               int l = MathHelper.func_76128_c(d0);
               int i1 = MathHelper.func_76128_c(d1);
               int j1 = MathHelper.func_76128_c(d2);
               int k1 = W_WorldFunc.getBlockId(world, l, i1, j1);
               
               if (k1 > 0) {
                 
                 Block block = W_Block.getBlockById(k1);
                 float f3 = block.getExplosionResistance(exploder, world, l, i1, j1, explosionX, explosionY, explosionZ);
                 f1 -= (f3 + 0.3F) * f2;
               } 
               
               if (f1 > 0.0F)
               {
                 hashset.add(new ChunkPosition(l, i1, j1));
               }
               
               d0 += d3 * f2;
               d1 += d4 * f2;
               d2 += d5 * f2;
             } 
           } 
         } 
       } 
     } 
     
     affectedBlockPositions.addAll(hashset);
 
 
     
     if (explosionSize >= 2.0F && isSmoking) {
       
       MCH_ParticlesUtil.DEF_spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
     }
     else {
       
       MCH_ParticlesUtil.DEF_spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D, 10.0F);
     } 

     if (isSmoking) {
       
       Iterator<ChunkPosition> iterator = affectedBlockPositions.iterator();
       
       while (iterator.hasNext()) {
         
         ChunkPosition chunkposition = iterator.next();
         i = W_ChunkPosition.getChunkPosX(chunkposition);
         int j = W_ChunkPosition.getChunkPosY(chunkposition);
         int k = W_ChunkPosition.getChunkPosZ(chunkposition);
         int l = W_WorldFunc.getBlockId(world, i, j, k);
 
 
         
         double d0 = (i + world.field_73012_v.nextFloat());
         double d1 = (j + world.field_73012_v.nextFloat());
         double d2 = (k + world.field_73012_v.nextFloat());
         double d3 = d0 - explosionX;
         double d4 = d1 - explosionY;
         double d5 = d2 - explosionZ;
         double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
         d3 /= d6;
         d4 /= d6;
         d5 /= d6;
         double d7 = 0.5D / (d6 / explosionSize + 0.1D);
         d7 *= (world.field_73012_v.nextFloat() * world.field_73012_v.nextFloat() + 0.3F);
         d3 *= d7;
         d4 *= d7;
         d5 *= d7;
         MCH_ParticlesUtil.DEF_spawnParticle("explode", (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3, d4, d5, 10.0F);
    
         MCH_ParticlesUtil.DEF_spawnParticle("smoke", d0, d1, d2, d3, d4, d5, 10.0F);
       } 
     } 
   }
 
 
 
 
   
   public static void effectExplosionInWater(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize, boolean isSmoking) {
     if (explosionSize <= 0.0F)
       return;  int range = (int)(explosionSize + 0.5D) / 1;
     int ex = (int)(explosionX + 0.5D);
     int ey = (int)(explosionY + 0.5D);
     int ez = (int)(explosionZ + 0.5D);
 
 
     
     for (int y = -range; y <= range; y++) {
       
       if (ey + y >= 1)
       {
         for (int x = -range; x <= range; x++) {
           
           for (int z = -range; z <= range; z++) {
             
             int d = x * x + y * y + z * z;
             if (d < range * range)
             {
               if (W_Block.func_149680_a(W_WorldFunc.getBlock(world, ex + x, ey + y, ez + z), W_Block.getWater())) {
                 
                 int n = explosionRNG.nextInt(2);
                 for (int i = 0; i < n; i++) {
                   
                   MCH_ParticleParam prm = new MCH_ParticleParam(world, "splash", (ex + x), (ey + y), (ez + z), x / range * (explosionRNG.nextFloat() - 0.2D), 1.0D - Math.sqrt((x * x + z * z)) / range + explosionRNG.nextFloat() * 0.4D * range * 0.4D, z / range * (explosionRNG.nextFloat() - 0.2D), (explosionRNG.nextInt(range) * 3 + range));
              
                   MCH_ParticlesUtil.spawnParticle(prm);
                 } 
               } 
             }
           } 
         } 
       }
     } 
   }
 }