/*     */ package mcheli.mcheli.particles;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import mcheli.particles.MCH_EntityBlockDustFX;
/*     */ import mcheli.particles.MCH_EntityParticleBase;
/*     */ import mcheli.particles.MCH_EntityParticleExplode;
/*     */ import mcheli.particles.MCH_EntityParticleMarkPoint;
/*     */ import mcheli.particles.MCH_EntityParticleSmoke;
/*     */ import mcheli.particles.MCH_EntityParticleSplash;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.wrapper.W_Particle;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityAuraFX;
/*     */ import net.minecraft.client.particle.EntityBreakingFX;
/*     */ import net.minecraft.client.particle.EntityBubbleFX;
/*     */ import net.minecraft.client.particle.EntityCloudFX;
/*     */ import net.minecraft.client.particle.EntityCritFX;
/*     */ import net.minecraft.client.particle.EntityDiggingFX;
/*     */ import net.minecraft.client.particle.EntityDropParticleFX;
/*     */ import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
/*     */ import net.minecraft.client.particle.EntityExplodeFX;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntityFireworkSparkFX;
/*     */ import net.minecraft.client.particle.EntityFishWakeFX;
/*     */ import net.minecraft.client.particle.EntityFlameFX;
/*     */ import net.minecraft.client.particle.EntityFootStepFX;
/*     */ import net.minecraft.client.particle.EntityHeartFX;
/*     */ import net.minecraft.client.particle.EntityHugeExplodeFX;
/*     */ import net.minecraft.client.particle.EntityLargeExplodeFX;
/*     */ import net.minecraft.client.particle.EntityLavaFX;
/*     */ import net.minecraft.client.particle.EntityNoteFX;
/*     */ import net.minecraft.client.particle.EntityPortalFX;
/*     */ import net.minecraft.client.particle.EntityReddustFX;
/*     */ import net.minecraft.client.particle.EntitySmokeFX;
/*     */ import net.minecraft.client.particle.EntitySnowShovelFX;
/*     */ import net.minecraft.client.particle.EntitySpellParticleFX;
/*     */ import net.minecraft.client.particle.EntitySplashFX;
/*     */ import net.minecraft.client.particle.EntitySuspendFX;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ParticlesUtil
/*     */ {
/*     */   public static void spawnParticleExplode(World w, double x, double y, double z, float size, float r, float g, float b, float a, int age) {
/*  50 */     MCH_EntityParticleExplode epe = new MCH_EntityParticleExplode(w, x, y, z, size, age, 0.0D);
/*  51 */     epe.setParticleMaxAge(age);
/*  52 */     epe.func_70538_b(r, g, b);
/*  53 */     epe.func_82338_g(a);
/*  54 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a((EntityFX)epe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void spawnParticleTileCrack(World w, int blockX, int blockY, int blockZ, double x, double y, double z, double mx, double my, double mz) {
/*  62 */     String name = W_Particle.getParticleTileCrackName(w, blockX, blockY, blockZ);
/*  63 */     if (!name.isEmpty())
/*     */     {
/*  65 */       DEF_spawnParticle(name, x, y, z, mx, my, mz, 20.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean spawnParticleTileDust(World w, int blockX, int blockY, int blockZ, double x, double y, double z, double mx, double my, double mz, float scale) {
/*  74 */     boolean ret = false;
/*  75 */     int[][] offset = { { 0, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { 1, 0, 0 }, { -1, 0, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     int len = offset.length;
/*  84 */     for (int i = 0; i < len; i++) {
/*     */       
/*  86 */       String name = W_Particle.getParticleTileDustName(w, blockX + offset[i][0], blockY + offset[i][1], blockZ + offset[i][2]);
/*  87 */       if (!name.isEmpty()) {
/*     */         
/*  89 */         EntityFX e = DEF_spawnParticle(name, x, y, z, mx, my, mz, 20.0F);
/*  90 */         if (e instanceof MCH_EntityBlockDustFX) {
/*     */           
/*  92 */           ((MCH_EntityBlockDustFX)e).setScale(scale * 2.0F);
/*  93 */           ret = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  98 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityFX DEF_spawnParticle(String s, double x, double y, double z, double mx, double my, double mz, float dist) {
/* 103 */     EntityFX e = doSpawnParticle(s, x, y, z, mx, my, mz);
/* 104 */     if (e != null)
/*     */     {
/* 106 */       e.field_70155_l *= dist;
/*     */     }
/* 108 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityFX doSpawnParticle(String p_72726_1_, double p_72726_2_, double p_72726_4_, double p_72726_6_, double p_72726_8_, double p_72726_10_, double p_72726_12_) {
/* 113 */     Minecraft mc = Minecraft.func_71410_x();
/* 114 */     RenderGlobal renderGlobal = mc.field_71438_f;
/* 115 */     if (mc != null && mc.field_71451_h != null && mc.field_71452_i != null) {
/*     */       EntityFireworkSparkFX entityFireworkSparkFX; EntityDiggingFX entityDiggingFX;
/* 117 */       int i = mc.field_71474_y.field_74362_aa;
/*     */       
/* 119 */       if (i == 1 && mc.field_71441_e.field_73012_v.nextInt(3) == 0)
/*     */       {
/* 121 */         i = 2;
/*     */       }
/*     */       
/* 124 */       double d6 = mc.field_71451_h.field_70165_t - p_72726_2_;
/* 125 */       double d7 = mc.field_71451_h.field_70163_u - p_72726_4_;
/* 126 */       double d8 = mc.field_71451_h.field_70161_v - p_72726_6_;
/* 127 */       EntityFX entityfx = null;
/*     */       
/* 129 */       if (p_72726_1_.equalsIgnoreCase("hugeexplosion")) {
/*     */         EntityHugeExplodeFX entityHugeExplodeFX;
/* 131 */         mc.field_71452_i.func_78873_a((EntityFX)(entityHugeExplodeFX = new EntityHugeExplodeFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_)));
/*     */       }
/* 133 */       else if (p_72726_1_.equalsIgnoreCase("largeexplode")) {
/*     */         EntityLargeExplodeFX entityLargeExplodeFX;
/* 135 */         mc.field_71452_i.func_78873_a((EntityFX)(entityLargeExplodeFX = new EntityLargeExplodeFX(mc.field_71446_o, (World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_)));
/*     */       }
/* 137 */       else if (p_72726_1_.equalsIgnoreCase("fireworksSpark")) {
/*     */         
/* 139 */         mc.field_71452_i.func_78873_a((EntityFX)(entityFireworkSparkFX = new EntityFireworkSparkFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, mc.field_71452_i)));
/*     */       } 
/*     */       
/* 142 */       if (entityFireworkSparkFX != null)
/*     */       {
/* 144 */         return (EntityFX)entityFireworkSparkFX;
/*     */       }
/*     */ 
/*     */       
/* 148 */       double d9 = 300.0D;
/*     */       
/* 150 */       if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
/*     */       {
/* 152 */         return null;
/*     */       }
/* 154 */       if (i > 1)
/*     */       {
/* 156 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 160 */       if (p_72726_1_.equalsIgnoreCase("bubble")) {
/*     */         
/* 162 */         EntityBubbleFX entityBubbleFX = new EntityBubbleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 164 */       else if (p_72726_1_.equalsIgnoreCase("suspended")) {
/*     */         
/* 166 */         EntitySuspendFX entitySuspendFX = new EntitySuspendFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 168 */       else if (p_72726_1_.equalsIgnoreCase("depthsuspend")) {
/*     */         
/* 170 */         EntityAuraFX entityAuraFX = new EntityAuraFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 172 */       else if (p_72726_1_.equalsIgnoreCase("townaura")) {
/*     */         
/* 174 */         EntityAuraFX entityAuraFX = new EntityAuraFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 176 */       else if (p_72726_1_.equalsIgnoreCase("crit")) {
/*     */         
/* 178 */         EntityCritFX entityCritFX = new EntityCritFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 180 */       else if (p_72726_1_.equalsIgnoreCase("magicCrit")) {
/*     */         
/* 182 */         EntityCritFX entityCritFX = new EntityCritFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/* 183 */         entityCritFX.func_70538_b(entityCritFX.func_70534_d() * 0.3F, entityCritFX.func_70542_f() * 0.8F, entityCritFX.func_70535_g());
/* 184 */         entityCritFX.func_94053_h();
/*     */       }
/* 186 */       else if (p_72726_1_.equalsIgnoreCase("smoke")) {
/*     */         
/* 188 */         EntitySmokeFX entitySmokeFX = new EntitySmokeFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 190 */       else if (p_72726_1_.equalsIgnoreCase("mobSpell")) {
/*     */         
/* 192 */         EntitySpellParticleFX entitySpellParticleFX = new EntitySpellParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
/* 193 */         entitySpellParticleFX.func_70538_b((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
/*     */       }
/* 195 */       else if (p_72726_1_.equalsIgnoreCase("mobSpellAmbient")) {
/*     */         
/* 197 */         EntitySpellParticleFX entitySpellParticleFX = new EntitySpellParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
/* 198 */         entitySpellParticleFX.func_82338_g(0.15F);
/* 199 */         entitySpellParticleFX.func_70538_b((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
/*     */       }
/* 201 */       else if (p_72726_1_.equalsIgnoreCase("spell")) {
/*     */         
/* 203 */         EntitySpellParticleFX entitySpellParticleFX = new EntitySpellParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 205 */       else if (p_72726_1_.equalsIgnoreCase("instantSpell")) {
/*     */         
/* 207 */         EntitySpellParticleFX entitySpellParticleFX = new EntitySpellParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/* 208 */         entitySpellParticleFX.func_70589_b(144);
/*     */       }
/* 210 */       else if (p_72726_1_.equalsIgnoreCase("witchMagic")) {
/*     */         
/* 212 */         EntitySpellParticleFX entitySpellParticleFX = new EntitySpellParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/* 213 */         entitySpellParticleFX.func_70589_b(144);
/* 214 */         float f = mc.field_71441_e.field_73012_v.nextFloat() * 0.5F + 0.35F;
/* 215 */         entitySpellParticleFX.func_70538_b(1.0F * f, 0.0F * f, 1.0F * f);
/*     */       }
/* 217 */       else if (p_72726_1_.equalsIgnoreCase("note")) {
/*     */         
/* 219 */         EntityNoteFX entityNoteFX = new EntityNoteFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 221 */       else if (p_72726_1_.equalsIgnoreCase("portal")) {
/*     */         
/* 223 */         EntityPortalFX entityPortalFX = new EntityPortalFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 225 */       else if (p_72726_1_.equalsIgnoreCase("enchantmenttable")) {
/*     */         
/* 227 */         EntityEnchantmentTableParticleFX entityEnchantmentTableParticleFX = new EntityEnchantmentTableParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 229 */       else if (p_72726_1_.equalsIgnoreCase("explode")) {
/*     */         
/* 231 */         EntityExplodeFX entityExplodeFX = new EntityExplodeFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 233 */       else if (p_72726_1_.equalsIgnoreCase("flame")) {
/*     */         
/* 235 */         EntityFlameFX entityFlameFX = new EntityFlameFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 237 */       else if (p_72726_1_.equalsIgnoreCase("lava")) {
/*     */         
/* 239 */         EntityLavaFX entityLavaFX = new EntityLavaFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_);
/*     */       }
/* 241 */       else if (p_72726_1_.equalsIgnoreCase("footstep")) {
/*     */         
/* 243 */         EntityFootStepFX entityFootStepFX = new EntityFootStepFX(mc.field_71446_o, (World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_);
/*     */       }
/* 245 */       else if (p_72726_1_.equalsIgnoreCase("splash")) {
/*     */         
/* 247 */         EntitySplashFX entitySplashFX = new EntitySplashFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 249 */       else if (p_72726_1_.equalsIgnoreCase("wake")) {
/*     */         
/* 251 */         EntityFishWakeFX entityFishWakeFX = new EntityFishWakeFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 253 */       else if (p_72726_1_.equalsIgnoreCase("largesmoke")) {
/*     */         
/* 255 */         EntitySmokeFX entitySmokeFX = new EntitySmokeFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, 2.5F);
/*     */       }
/* 257 */       else if (p_72726_1_.equalsIgnoreCase("cloud")) {
/*     */         
/* 259 */         EntityCloudFX entityCloudFX = new EntityCloudFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 261 */       else if (p_72726_1_.equalsIgnoreCase("reddust")) {
/*     */         
/* 263 */         EntityReddustFX entityReddustFX = new EntityReddustFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, (float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
/*     */       }
/* 265 */       else if (p_72726_1_.equalsIgnoreCase("snowballpoof")) {
/*     */         
/* 267 */         EntityBreakingFX entityBreakingFX = new EntityBreakingFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, Items.field_151126_ay);
/*     */       }
/* 269 */       else if (p_72726_1_.equalsIgnoreCase("dripWater")) {
/*     */         
/* 271 */         EntityDropParticleFX entityDropParticleFX = new EntityDropParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, Material.field_151586_h);
/*     */       }
/* 273 */       else if (p_72726_1_.equalsIgnoreCase("dripLava")) {
/*     */         
/* 275 */         EntityDropParticleFX entityDropParticleFX = new EntityDropParticleFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, Material.field_151587_i);
/*     */       }
/* 277 */       else if (p_72726_1_.equalsIgnoreCase("snowshovel")) {
/*     */         
/* 279 */         EntitySnowShovelFX entitySnowShovelFX = new EntitySnowShovelFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 281 */       else if (p_72726_1_.equalsIgnoreCase("slime")) {
/*     */         
/* 283 */         EntityBreakingFX entityBreakingFX = new EntityBreakingFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, Items.field_151123_aH);
/*     */       }
/* 285 */       else if (p_72726_1_.equalsIgnoreCase("heart")) {
/*     */         
/* 287 */         EntityHeartFX entityHeartFX = new EntityHeartFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/*     */       }
/* 289 */       else if (p_72726_1_.equalsIgnoreCase("angryVillager")) {
/*     */         
/* 291 */         EntityHeartFX entityHeartFX = new EntityHeartFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_ + 0.5D, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/* 292 */         entityHeartFX.func_70536_a(81);
/* 293 */         entityHeartFX.func_70538_b(1.0F, 1.0F, 1.0F);
/*     */       }
/* 295 */       else if (p_72726_1_.equalsIgnoreCase("happyVillager")) {
/*     */         
/* 297 */         EntityAuraFX entityAuraFX = new EntityAuraFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
/* 298 */         entityAuraFX.func_70536_a(82);
/* 299 */         entityAuraFX.func_70538_b(1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 306 */       else if (p_72726_1_.startsWith("iconcrack_")) {
/*     */         
/* 308 */         String[] astring = p_72726_1_.split("_", 3);
/* 309 */         int j = Integer.parseInt(astring[1]);
/*     */         
/* 311 */         if (astring.length > 2)
/*     */         {
/* 313 */           int k = Integer.parseInt(astring[2]);
/* 314 */           EntityBreakingFX entityBreakingFX = new EntityBreakingFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.func_150899_d(j), k);
/*     */         }
/*     */         else
/*     */         {
/* 318 */           EntityBreakingFX entityBreakingFX = new EntityBreakingFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.func_150899_d(j), 0);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 325 */       else if (p_72726_1_.startsWith("blockcrack_")) {
/*     */         
/* 327 */         String[] astring = p_72726_1_.split("_", 3);
/* 328 */         Block block = Block.func_149729_e(Integer.parseInt(astring[1]));
/* 329 */         int k = Integer.parseInt(astring[2]);
/* 330 */         entityDiggingFX = (new EntityDiggingFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).func_90019_g(k);
/*     */       }
/* 332 */       else if (p_72726_1_.startsWith("blockdust_")) {
/*     */         
/* 334 */         String[] astring = p_72726_1_.split("_", 3);
/* 335 */         Block block = Block.func_149729_e(Integer.parseInt(astring[1]));
/* 336 */         int k = Integer.parseInt(astring[2]);
/* 337 */         entityDiggingFX = (new MCH_EntityBlockDustFX((World)mc.field_71441_e, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).func_90019_g(k);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 342 */       if (entityDiggingFX != null)
/*     */       {
/* 344 */         mc.field_71452_i.func_78873_a((EntityFX)entityDiggingFX);
/*     */       }
/*     */       
/* 347 */       return (EntityFX)entityDiggingFX;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 353 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void spawnParticle(MCH_ParticleParam p) {
/* 359 */     if (p.world.field_72995_K) {
/*     */       MCH_EntityParticleSmoke mCH_EntityParticleSmoke;
/* 361 */       MCH_EntityParticleBase entityFX = null;
/*     */       
/* 363 */       if (p.name.equalsIgnoreCase("Splash")) {
/*     */         
/* 365 */         MCH_EntityParticleSplash mCH_EntityParticleSplash = new MCH_EntityParticleSplash(p.world, p.posX, p.posY, p.posZ, p.motionX, p.motionY, p.motionZ);
/*     */       }
/*     */       else {
/*     */         
/* 369 */         mCH_EntityParticleSmoke = new MCH_EntityParticleSmoke(p.world, p.posX, p.posY, p.posZ, p.motionX, p.motionY, p.motionZ);
/*     */       } 
/* 371 */       mCH_EntityParticleSmoke.func_70538_b(p.r, p.g, p.b);
/* 372 */       mCH_EntityParticleSmoke.func_82338_g(p.a);
/*     */       
/* 374 */       if (p.age > 0)
/*     */       {
/* 376 */         mCH_EntityParticleSmoke.setParticleMaxAge(p.age);
/*     */       }
/*     */       
/* 379 */       ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).moutionYUpAge = p.motionYUpAge;
/*     */       
/* 381 */       ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).gravity = p.gravity;
/*     */       
/* 383 */       ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).isEffectedWind = p.isEffectWind;
/* 384 */       ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).diffusible = p.diffusible;
/* 385 */       ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).toWhite = p.toWhite;
/* 386 */       if (p.diffusible) {
/*     */         
/* 388 */         mCH_EntityParticleSmoke.setParticleScale(p.size * 0.2F);
/* 389 */         ((MCH_EntityParticleBase)mCH_EntityParticleSmoke).particleMaxScale = p.size * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 393 */         mCH_EntityParticleSmoke.setParticleScale(p.size);
/*     */       } 
/*     */       
/* 396 */       (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a((EntityFX)mCH_EntityParticleSmoke);
/*     */     } 
/*     */   }
/*     */   
/* 400 */   public static MCH_EntityParticleMarkPoint markPoint = null;
/*     */   
/*     */   public static void spawnMarkPoint(EntityPlayer player, double x, double y, double z) {
/* 403 */     clearMarkPoint();
/* 404 */     markPoint = new MCH_EntityParticleMarkPoint(player.field_70170_p, x, y, z, player.func_96124_cp());
/* 405 */     (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a((EntityFX)markPoint);
/*     */   }
/*     */   
/*     */   public static void clearMarkPoint() {
/* 409 */     if (markPoint != null) {
/*     */       
/* 411 */       markPoint.func_70106_y();
/* 412 */       markPoint = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_ParticlesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */