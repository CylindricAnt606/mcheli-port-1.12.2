/*      */ package mcheli.mcheli.weapon;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.List;
/*      */ import mcheli.MCH_Achievement;
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Explosion;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.aircraft.MCH_EntityAircraft;
/*      */ import mcheli.aircraft.MCH_PacketNotifyHitBullet;
/*      */ import mcheli.particles.MCH_ParticleParam;
/*      */ import mcheli.particles.MCH_ParticlesUtil;
/*      */ import mcheli.weapon.MCH_BulletModel;
/*      */ import mcheli.weapon.MCH_WeaponBase;
/*      */ import mcheli.weapon.MCH_WeaponInfo;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_EntityPlayer;
/*      */ import mcheli.wrapper.W_MovingObjectPosition;
/*      */ import mcheli.wrapper.W_WorldFunc;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public abstract class MCH_EntityBaseBullet extends W_Entity {
/*      */   public static final int DATAWT_RESERVE1 = 26;
/*      */   public static final int DATAWT_TARGET_ENTITY = 27;
/*   34 */   private int countOnUpdate = 0; public static final int DATAWT_MARKER_STAT = 28; public static final int DATAWT_NAME = 29; public static final int DATAWT_BULLET_MODEL = 30;
/*      */   public static final int DATAWT_BOMBLET_FLAG = 31;
/*      */   public Entity shootingEntity;
/*      */   public Entity shootingAircraft;
/*      */   public int explosionPower;
/*      */   public int explosionPowerInWater;
/*      */   private int power;
/*      */   public double acceleration;
/*      */   public double accelerationFactor;
/*      */   public Entity targetEntity;
/*      */   public int piercing;
/*      */   public int delayFuse;
/*      */   public int sprinkleTime;
/*      */   public byte isBomblet;
/*      */   private MCH_WeaponInfo weaponInfo;
/*      */   private MCH_BulletModel model;
/*      */   public double prevPosX2;
/*      */   public double prevPosY2;
/*      */   public double prevPosZ2;
/*      */   public double prevMotionX;
/*      */   public double prevMotionY;
/*      */   public double prevMotionZ;
/*      */   
/*      */   public MCH_EntityBaseBullet(World par1World) {
/*   58 */     super(par1World);
/*   59 */     func_70105_a(1.0F, 1.0F);
/*   60 */     this.field_70126_B = this.field_70177_z;
/*   61 */     this.field_70127_C = this.field_70125_A;
/*   62 */     this.targetEntity = null;
/*   63 */     setPower(1);
/*   64 */     this.acceleration = 1.0D;
/*   65 */     this.accelerationFactor = 1.0D;
/*   66 */     this.piercing = 0;
/*   67 */     this.explosionPower = 0;
/*   68 */     this.explosionPowerInWater = 0;
/*   69 */     this.delayFuse = 0;
/*   70 */     this.sprinkleTime = 0;
/*   71 */     this.isBomblet = -1;
/*   72 */     this.weaponInfo = null;
/*   73 */     this.field_70158_ak = true;
/*   74 */     if (par1World.field_72995_K)
/*      */     {
/*   76 */       this.model = null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MCH_EntityBaseBullet(World par1World, double px, double py, double pz, double mx, double my, double mz, float yaw, float pitch, double acceleration) {
/*   86 */     this(par1World);
/*   87 */     func_70105_a(1.0F, 1.0F);
/*   88 */     func_70012_b(px, py, pz, yaw, pitch);
/*   89 */     func_70107_b(px, py, pz);
/*   90 */     this.field_70126_B = yaw;
/*   91 */     this.field_70127_C = pitch;
/*   92 */     this.field_70129_M = 0.0F;
/*      */ 
/*      */     
/*   95 */     if (acceleration > 3.9D)
/*      */     {
/*   97 */       acceleration = 3.9D;
/*      */     }
/*      */     
/*  100 */     double d = MathHelper.func_76133_a(mx * mx + my * my + mz * mz);
/*  101 */     this.field_70159_w = mx * acceleration / d;
/*  102 */     this.field_70181_x = my * acceleration / d;
/*  103 */     this.field_70179_y = mz * acceleration / d;
/*  104 */     this.prevMotionX = this.field_70159_w;
/*  105 */     this.prevMotionY = this.field_70181_x;
/*  106 */     this.prevMotionZ = this.field_70179_y;
/*  107 */     this.acceleration = acceleration;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70012_b(double par1, double par3, double par5, float par7, float par8) {
/*  112 */     super.func_70012_b(par1, par3, par5, par7, par8);
/*  113 */     this.prevPosX2 = par1;
/*  114 */     this.prevPosY2 = par3;
/*  115 */     this.prevPosZ2 = par5;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  120 */     super.func_70088_a();
/*  121 */     func_70096_w().func_75682_a(27, Integer.valueOf(0));
/*  122 */     func_70096_w().func_75682_a(29, String.valueOf(""));
/*  123 */     func_70096_w().func_75682_a(30, String.valueOf(""));
/*  124 */     func_70096_w().func_75682_a(31, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setName(String s) {
/*  129 */     if (s != null && !s.isEmpty()) {
/*      */       
/*  131 */       this.weaponInfo = MCH_WeaponInfoManager.get(s);
/*  132 */       if (this.weaponInfo != null) {
/*      */         
/*  134 */         if (!this.field_70170_p.field_72995_K)
/*      */         {
/*  136 */           func_70096_w().func_75692_b(29, String.valueOf(s));
/*      */         }
/*  138 */         onSetWeasponInfo();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getName() {
/*  145 */     return func_70096_w().func_75681_e(29);
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_WeaponInfo getInfo() {
/*  150 */     return this.weaponInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onSetWeasponInfo() {
/*  155 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  157 */       this.isBomblet = 0;
/*      */     }
/*  159 */     if ((getInfo()).bomblet > 0)
/*      */     {
/*  161 */       this.sprinkleTime = (getInfo()).bombletSTime;
/*      */     }
/*      */     
/*  164 */     this.piercing = (getInfo()).piercing;
/*      */     
/*  166 */     if (this instanceof mcheli.weapon.MCH_EntityBullet) {
/*      */       
/*  168 */       if ((getInfo()).acceleration > 4.0F)
/*      */       {
/*  170 */         this.accelerationFactor = ((getInfo()).acceleration / 4.0F);
/*      */       }
/*      */     }
/*  173 */     else if (this instanceof mcheli.weapon.MCH_EntityRocket) {
/*      */ 
/*      */       
/*  176 */       if (this.isBomblet == 0 && (getInfo()).acceleration > 4.0F)
/*      */       {
/*  178 */         this.accelerationFactor = ((getInfo()).acceleration / 4.0F);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70106_y() {
/*  185 */     super.func_70106_y();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBomblet() {
/*  193 */     this.isBomblet = 1;
/*  194 */     this.sprinkleTime = 0;
/*  195 */     this.field_70180_af.func_75692_b(31, Byte.valueOf((byte)1));
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getBomblet() {
/*  200 */     return this.field_70180_af.func_75683_a(31);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTargetEntity(Entity entity) {
/*  205 */     this.targetEntity = entity;
/*  206 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  208 */       if (entity != null) {
/*      */         
/*  210 */         func_70096_w().func_75692_b(27, Integer.valueOf(W_Entity.getEntityId(entity)));
/*      */       }
/*      */       else {
/*      */         
/*  214 */         func_70096_w().func_75692_b(27, Integer.valueOf(0));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTargetEntityID() {
/*  221 */     if (this.targetEntity != null) return W_Entity.getEntityId(this.targetEntity); 
/*  222 */     return func_70096_w().func_75679_c(27);
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_BulletModel getBulletModel() {
/*  227 */     if (getInfo() == null) return null; 
/*  228 */     if (this.isBomblet < 0) return null;
/*      */     
/*  230 */     if (this.model == null) {
/*      */       
/*  232 */       if (this.isBomblet == 1) {
/*      */         
/*  234 */         this.model = (getInfo()).bombletModel;
/*      */       }
/*      */       else {
/*      */         
/*  238 */         this.model = (getInfo()).bulletModel;
/*      */       } 
/*  240 */       if (this.model == null)
/*      */       {
/*  242 */         this.model = getDefaultBulletModel();
/*      */       }
/*      */     } 
/*      */     
/*  246 */     return this.model;
/*      */   }
/*      */   
/*      */   public abstract MCH_BulletModel getDefaultBulletModel();
/*      */   
/*      */   public void sprinkleBomblet() {}
/*      */   
/*      */   public void spawnParticle(String name, int num, float size) {
/*  254 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  256 */       if (name.isEmpty() || num < 1 || num > 50)
/*      */         return; 
/*  258 */       double x = (this.field_70165_t - this.field_70169_q) / num;
/*  259 */       double y = (this.field_70163_u - this.field_70167_r) / num;
/*  260 */       double z = (this.field_70161_v - this.field_70166_s) / num;
/*  261 */       double x2 = (this.field_70169_q - this.prevPosX2) / num;
/*  262 */       double y2 = (this.field_70167_r - this.prevPosY2) / num;
/*  263 */       double z2 = (this.field_70166_s - this.prevPosZ2) / num;
/*      */       
/*  265 */       if (name.equals("explode")) {
/*      */         
/*  267 */         for (int i = 0; i < num; i++)
/*      */         {
/*  269 */           MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", (this.field_70169_q + x * i + this.prevPosX2 + x2 * i) / 2.0D, (this.field_70167_r + y * i + this.prevPosY2 + y2 * i) / 2.0D, (this.field_70166_s + z * i + this.prevPosZ2 + z2 * i) / 2.0D);
/*      */ 
/*      */ 
/*      */           
/*  273 */           prm.size = size + this.field_70146_Z.nextFloat();
/*  274 */           MCH_ParticlesUtil.spawnParticle(prm);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  279 */         for (int i = 0; i < num; i++)
/*      */         {
/*  281 */           MCH_ParticlesUtil.DEF_spawnParticle(name, (this.field_70169_q + x * i + this.prevPosX2 + x2 * i) / 2.0D, (this.field_70167_r + y * i + this.prevPosY2 + y2 * i) / 2.0D, (this.field_70166_s + z * i + this.prevPosZ2 + z2 * i) / 2.0D, 0.0D, 0.0D, 0.0D, 50.0F);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void DEF_spawnParticle(String name, int num, float size) {
/*  292 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  294 */       if (name.isEmpty() || num < 1 || num > 50)
/*  295 */         return;  double x = (this.field_70165_t - this.field_70169_q) / num;
/*  296 */       double y = (this.field_70163_u - this.field_70167_r) / num;
/*  297 */       double z = (this.field_70161_v - this.field_70166_s) / num;
/*  298 */       double x2 = (this.field_70169_q - this.prevPosX2) / num;
/*  299 */       double y2 = (this.field_70167_r - this.prevPosY2) / num;
/*  300 */       double z2 = (this.field_70166_s - this.prevPosZ2) / num;
/*  301 */       for (int i = 0; i < num; i++)
/*      */       {
/*  303 */         MCH_ParticlesUtil.DEF_spawnParticle(name, (this.field_70169_q + x * i + this.prevPosX2 + x2 * i) / 2.0D, (this.field_70167_r + y * i + this.prevPosY2 + y2 * i) / 2.0D, (this.field_70166_s + z * i + this.prevPosZ2 + z2 * i) / 2.0D, 0.0D, 0.0D, 0.0D, 150.0F);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCountOnUpdate() {
/*  314 */     return this.countOnUpdate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearCountOnUpdate() {
/*  319 */     this.countOnUpdate = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean func_70112_a(double par1) {
/*  329 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  330 */     d1 *= 64.0D;
/*  331 */     return (par1 < d1 * d1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setParameterFromWeapon(MCH_WeaponBase w, Entity entity, Entity user) {
/*  336 */     this.explosionPower = w.explosionPower;
/*  337 */     this.explosionPowerInWater = w.explosionPowerInWater;
/*  338 */     setPower(w.power);
/*  339 */     this.piercing = w.piercing;
/*  340 */     this.shootingAircraft = entity;
/*  341 */     this.shootingEntity = user;
/*      */   }
/*      */   
/*      */   public void setParameterFromWeapon(mcheli.weapon.MCH_EntityBaseBullet b, Entity entity, Entity user) {
/*  345 */     this.explosionPower = b.explosionPower;
/*  346 */     this.explosionPowerInWater = b.explosionPowerInWater;
/*  347 */     setPower(b.getPower());
/*  348 */     this.piercing = b.piercing;
/*  349 */     this.shootingAircraft = entity;
/*  350 */     this.shootingEntity = user;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMotion(double targetX, double targetY, double targetZ) {
/*  355 */     double d6 = MathHelper.func_76133_a(targetX * targetX + targetY * targetY + targetZ * targetZ);
/*  356 */     this.field_70159_w = targetX * this.acceleration / d6;
/*  357 */     this.field_70181_x = targetY * this.acceleration / d6;
/*  358 */     this.field_70179_y = targetZ * this.acceleration / d6;
/*      */   }
/*      */ 
/*      */   
/*      */   public void guidanceToTarget(double targetPosX, double targetPosY, double targetPosZ) {
/*  363 */     guidanceToTarget(targetPosX, targetPosY, targetPosZ, 1.0F);
/*      */   }
/*      */   
/*      */   public void guidanceToTarget(double targetPosX, double targetPosY, double targetPosZ, float accelerationFactor) {
/*  367 */     double tx = targetPosX - this.field_70165_t;
/*  368 */     double ty = targetPosY - this.field_70163_u;
/*  369 */     double tz = targetPosZ - this.field_70161_v;
/*      */     
/*  371 */     double d = MathHelper.func_76133_a(tx * tx + ty * ty + tz * tz);
/*  372 */     double mx = tx * this.acceleration / d;
/*  373 */     double my = ty * this.acceleration / d;
/*  374 */     double mz = tz * this.acceleration / d;
/*      */     
/*  376 */     this.field_70159_w = (this.field_70159_w * 6.0D + mx) / 7.0D;
/*  377 */     this.field_70181_x = (this.field_70181_x * 6.0D + my) / 7.0D;
/*  378 */     this.field_70179_y = (this.field_70179_y * 6.0D + mz) / 7.0D;
/*      */     
/*  380 */     double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/*  381 */     this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*      */     
/*  383 */     double r = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*  384 */     this.field_70125_A = -((float)(Math.atan2(this.field_70181_x, r) * 180.0D / Math.PI));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean checkValid() {
/*  389 */     if (this.shootingEntity == null && this.shootingAircraft == null) return false; 
/*  390 */     if (this.shootingEntity != null && this.shootingEntity.field_70128_L) return false; 
/*  391 */     if (this.shootingAircraft == null || this.shootingAircraft.field_70128_L);
/*      */ 
/*      */ 
/*      */     
/*  395 */     Entity shooter = (this.shootingEntity != null) ? this.shootingEntity : this.shootingAircraft;
/*      */     
/*  397 */     double x = this.field_70165_t - shooter.field_70165_t;
/*      */     
/*  399 */     double z = this.field_70161_v - shooter.field_70161_v;
/*  400 */     return (x * x + z * z < 3.38724E7D);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getGravity() {
/*  405 */     return (getInfo() != null) ? (getInfo()).gravity : 0.0F;
/*      */   }
/*      */   
/*      */   public float getGravityInWater() {
/*  409 */     return (getInfo() != null) ? (getInfo()).gravityInWater : 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70071_h_() {
/*  418 */     if (this.field_70170_p.field_72995_K && this.countOnUpdate == 0) {
/*      */       
/*  420 */       int tgtEttId = getTargetEntityID();
/*  421 */       if (tgtEttId > 0)
/*      */       {
/*  423 */         setTargetEntity(this.field_70170_p.func_73045_a(tgtEttId));
/*      */       }
/*      */     } 
/*      */     
/*  427 */     if (this.prevMotionX != this.field_70159_w || this.prevMotionY != this.field_70181_x || this.prevMotionZ != this.field_70179_y) {
/*      */       
/*  429 */       double a = (float)Math.atan2(this.field_70179_y, this.field_70159_w);
/*  430 */       this.field_70177_z = (float)(a * 180.0D / Math.PI) - 90.0F;
/*      */       
/*  432 */       double r = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*  433 */       this.field_70125_A = -((float)(Math.atan2(this.field_70181_x, r) * 180.0D / Math.PI));
/*      */     } 
/*      */     
/*  436 */     this.prevMotionX = this.field_70159_w;
/*  437 */     this.prevMotionY = this.field_70181_x;
/*  438 */     this.prevMotionZ = this.field_70179_y;
/*      */     
/*  440 */     this.countOnUpdate++;
/*  441 */     if (this.countOnUpdate > 10000000)
/*      */     {
/*  443 */       clearCountOnUpdate();
/*      */     }
/*      */     
/*  446 */     this.prevPosX2 = this.field_70169_q;
/*  447 */     this.prevPosY2 = this.field_70167_r;
/*  448 */     this.prevPosZ2 = this.field_70166_s;
/*  449 */     super.func_70071_h_();
/*      */     
/*  451 */     if (getInfo() == null) {
/*      */       
/*  453 */       if (this.countOnUpdate < 2) {
/*      */         
/*  455 */         setName(getName());
/*      */       }
/*      */       else {
/*      */         
/*  459 */         MCH_Lib.Log((Entity)this, "##### MCH_EntityBaseBullet onUpdate() Weapon info null %d, %s, Name=%s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), getEntityName(), getName() });
/*      */         
/*  461 */         func_70106_y();
/*      */         return;
/*      */       } 
/*  464 */       if (getInfo() == null)
/*      */         return; 
/*      */     } 
/*  467 */     if (this.field_70170_p.field_72995_K)
/*      */     {
/*  469 */       if (this.isBomblet < 0)
/*      */       {
/*  471 */         this.isBomblet = getBomblet();
/*      */       }
/*      */     }
/*      */     
/*  475 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  477 */       if ((int)this.field_70163_u <= 255 && !this.field_70170_p.func_72899_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v))
/*      */       {
/*  479 */         if ((getInfo()).delayFuse > 0) {
/*      */           
/*  481 */           if (this.delayFuse == 0)
/*      */           {
/*  483 */             this.delayFuse = (getInfo()).delayFuse;
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  488 */           func_70106_y();
/*      */           
/*      */           return;
/*      */         } 
/*      */       }
/*  493 */       if (this.delayFuse > 0) {
/*      */         
/*  495 */         this.delayFuse--;
/*  496 */         if (this.delayFuse == 0) {
/*      */           
/*  498 */           onUpdateTimeout();
/*  499 */           func_70106_y();
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*  504 */       if (!checkValid()) {
/*      */         
/*  506 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*  510 */       if ((getInfo()).timeFuse > 0 && getCountOnUpdate() > (getInfo()).timeFuse) {
/*      */         
/*  512 */         onUpdateTimeout();
/*  513 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*  517 */       if ((getInfo()).explosionAltitude > 0)
/*      */       {
/*      */         
/*  520 */         if (MCH_Lib.getBlockIdY((Entity)this, 3, -(getInfo()).explosionAltitude) != 0) {
/*      */           
/*  522 */           MovingObjectPosition mop = new MovingObjectPosition((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0, Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));
/*      */           
/*  524 */           onImpact(mop, 1.0F);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  529 */     if (!func_70090_H()) {
/*      */       
/*  531 */       this.field_70181_x += getGravity();
/*      */     }
/*      */     else {
/*      */       
/*  535 */       this.field_70181_x += getGravityInWater();
/*      */     } 
/*  537 */     if (!this.field_70128_L)
/*      */     {
/*  539 */       onUpdateCollided();
/*      */     }
/*      */     
/*  542 */     this.field_70165_t += this.field_70159_w * this.accelerationFactor;
/*  543 */     this.field_70163_u += this.field_70181_x * this.accelerationFactor;
/*  544 */     this.field_70161_v += this.field_70179_y * this.accelerationFactor;
/*      */     
/*  546 */     if (this.field_70170_p.field_72995_K)
/*      */     {
/*  548 */       updateSplash();
/*      */     }
/*      */     
/*  551 */     if (func_70090_H()) {
/*      */       
/*  553 */       float f3 = 0.25F;
/*  554 */       this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f3, this.field_70163_u - this.field_70181_x * f3, this.field_70161_v - this.field_70179_y * f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateSplash() {
/*  566 */     if (getInfo() == null)
/*  567 */       return;  if ((getInfo()).power <= 0) {
/*      */       return;
/*      */     }
/*  570 */     if (!W_WorldFunc.isBlockWater(this.field_70170_p, (int)(this.field_70169_q + 0.5D), (int)(this.field_70167_r + 0.5D), (int)(this.field_70166_s + 0.5D)))
/*      */     {
/*  572 */       if (W_WorldFunc.isBlockWater(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 0.5D), (int)(this.field_70161_v + 0.5D))) {
/*      */         
/*  574 */         double x = this.field_70165_t - this.field_70169_q;
/*  575 */         double y = this.field_70163_u - this.field_70167_r;
/*  576 */         double z = this.field_70161_v - this.field_70166_s;
/*  577 */         double d = Math.sqrt(x * x + y * y + z * z);
/*      */ 
/*      */         
/*  580 */         if (d <= 0.15D)
/*      */           return; 
/*  582 */         x /= d;
/*  583 */         y /= d;
/*  584 */         z /= d;
/*  585 */         double px = this.field_70169_q;
/*  586 */         double py = this.field_70167_r;
/*  587 */         double pz = this.field_70166_s;
/*  588 */         for (int i = 0; i <= d; i++) {
/*      */           
/*  590 */           px += x;
/*  591 */           py += y;
/*  592 */           pz += z;
/*  593 */           if (W_WorldFunc.isBlockWater(this.field_70170_p, (int)(px + 0.5D), (int)(py + 0.5D), (int)(pz + 0.5D))) {
/*      */             
/*  595 */             float pwr = ((getInfo()).power < 20) ? (getInfo()).power : 20.0F;
/*  596 */             int n = this.field_70146_Z.nextInt(1 + (int)pwr / 3) + (int)pwr / 2 + 1;
/*      */             
/*  598 */             pwr *= 0.03F;
/*      */             
/*  600 */             for (int j = 0; j < n; j++) {
/*      */               
/*  602 */               MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "splash", px, py + 0.5D, pz, pwr * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D, pwr * (this.field_70146_Z.nextDouble() * 0.5D + 0.5D) * 1.8D, pwr * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D, pwr * 5.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  608 */               MCH_ParticlesUtil.spawnParticle(prm);
/*      */             } 
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdateTimeout() {
/*  619 */     if (func_70090_H()) {
/*      */       
/*  621 */       if (this.explosionPowerInWater > 0)
/*      */       {
/*  623 */         newExplosion(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explosionPowerInWater, this.explosionPowerInWater, true);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  628 */     else if (this.explosionPower > 0) {
/*      */       
/*  630 */       newExplosion(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explosionPower, (getInfo()).explosionBlock, false);
/*      */     
/*      */     }
/*  633 */     else if (this.explosionPower < 0) {
/*      */       
/*  635 */       playExplosionSound();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdateBomblet() {
/*  642 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  644 */       if (this.sprinkleTime > 0 && !this.field_70128_L) {
/*      */         
/*  646 */         this.sprinkleTime--;
/*  647 */         if (this.sprinkleTime == 0) {
/*      */           
/*  649 */           for (int i = 0; i < (getInfo()).bomblet; i++)
/*      */           {
/*  651 */             sprinkleBomblet();
/*      */           }
/*      */           
/*  654 */           func_70106_y();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void boundBullet(int sideHit) {
/*  662 */     switch (sideHit) {
/*      */       
/*      */       case 0:
/*  665 */         if (this.field_70181_x > 0.0D) this.field_70181_x = -this.field_70181_x * (getInfo()).bound; 
/*      */         break;
/*      */       case 1:
/*  668 */         if (this.field_70181_x < 0.0D) this.field_70181_x = -this.field_70181_x * (getInfo()).bound; 
/*      */         break;
/*      */       case 2:
/*  671 */         if (this.field_70179_y > 0.0D) { this.field_70179_y = -this.field_70179_y * (getInfo()).bound; break; }
/*  672 */          this.field_70161_v += this.field_70179_y;
/*      */         break;
/*      */       case 3:
/*  675 */         if (this.field_70179_y < 0.0D) { this.field_70179_y = -this.field_70179_y * (getInfo()).bound; break; }
/*  676 */          this.field_70161_v += this.field_70179_y;
/*      */         break;
/*      */       case 4:
/*  679 */         if (this.field_70159_w > 0.0D) { this.field_70159_w = -this.field_70159_w * (getInfo()).bound; break; }
/*  680 */          this.field_70165_t += this.field_70159_w;
/*      */         break;
/*      */       case 5:
/*  683 */         if (this.field_70159_w < 0.0D) { this.field_70159_w = -this.field_70159_w * (getInfo()).bound; break; }
/*  684 */          this.field_70165_t += this.field_70159_w;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdateCollided() {
/*  691 */     float damageFator = 1.0F;
/*  692 */     double mx = this.field_70159_w * this.accelerationFactor;
/*  693 */     double my = this.field_70181_x * this.accelerationFactor;
/*  694 */     double mz = this.field_70179_y * this.accelerationFactor;
/*      */ 
/*      */     
/*  697 */     MovingObjectPosition m = null;
/*  698 */     for (int i = 0; i < 5; i++) {
/*      */       
/*  700 */       Vec3 vec32 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  701 */       Vec3 vec33 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + mx, this.field_70163_u + my, this.field_70161_v + mz);
/*  702 */       m = W_WorldFunc.clip(this.field_70170_p, vec32, vec33);
/*      */       
/*  704 */       boolean continueClip = false;
/*      */       
/*  706 */       if (this.shootingEntity != null && W_MovingObjectPosition.isHitTypeTile(m)) {
/*      */         
/*  708 */         Block block = W_WorldFunc.getBlock(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d);
/*  709 */         if (MCH_Config.bulletBreakableBlocks.contains(block)) {
/*      */           
/*  711 */           W_WorldFunc.destroyBlock(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d, true);
/*  712 */           continueClip = true;
/*      */         } 
/*      */       } 
/*      */       
/*  716 */       if (!continueClip) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  722 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  723 */     Vec3 vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + mx, this.field_70163_u + my, this.field_70161_v + mz);
/*      */     
/*  725 */     if ((getInfo()).delayFuse > 0) {
/*      */       
/*  727 */       if (m != null) {
/*      */         
/*  729 */         boundBullet(m.field_72310_e);
/*  730 */         if (this.delayFuse == 0)
/*      */         {
/*  732 */           this.delayFuse = (getInfo()).delayFuse;
/*      */         }
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*  738 */     if (m != null)
/*      */     {
/*  740 */       vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, m.field_72307_f.field_72450_a, m.field_72307_f.field_72448_b, m.field_72307_f.field_72449_c);
/*      */     }
/*      */     
/*  743 */     Entity entity = null;
/*  744 */     List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(mx, my, mz).func_72314_b(21.0D, 21.0D, 21.0D));
/*  745 */     double d0 = 0.0D;
/*      */     
/*  747 */     for (int j = 0; j < list.size(); j++) {
/*      */       
/*  749 */       Entity entity1 = list.get(j);
/*      */ 
/*      */       
/*  752 */       if (canBeCollidedEntity(entity1)) {
/*      */         
/*  754 */         float f = 0.3F;
/*  755 */         AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/*  756 */         MovingObjectPosition m1 = axisalignedbb.func_72327_a(vec3, vec31);
/*      */         
/*  758 */         if (m1 != null) {
/*      */           
/*  760 */           double d1 = vec3.func_72438_d(m1.field_72307_f);
/*      */           
/*  762 */           if (d1 < d0 || d0 == 0.0D) {
/*      */             
/*  764 */             entity = entity1;
/*  765 */             d0 = d1;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  782 */     if (entity != null)
/*      */     {
/*  784 */       m = new MovingObjectPosition(entity);
/*      */     }
/*      */     
/*  787 */     if (m != null)
/*      */     {
/*  789 */       onImpact(m, damageFator);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedEntity(Entity entity) {
/*  796 */     if (entity instanceof mcheli.chain.MCH_EntityChain) return false;
/*      */     
/*  798 */     if (!entity.func_70067_L()) return false;
/*      */     
/*  800 */     if (entity instanceof mcheli.weapon.MCH_EntityBaseBullet) {
/*      */       
/*  802 */       if (this.field_70170_p.field_72995_K) return false;
/*      */       
/*  804 */       mcheli.weapon.MCH_EntityBaseBullet blt = (mcheli.weapon.MCH_EntityBaseBullet)entity;
/*  805 */       if (W_Entity.isEqual(blt.shootingAircraft, this.shootingAircraft)) return false; 
/*  806 */       if (W_Entity.isEqual(blt.shootingEntity, this.shootingEntity)) return false;
/*      */     
/*      */     } 
/*  809 */     if (entity instanceof mcheli.aircraft.MCH_EntitySeat) return false; 
/*  810 */     if (entity instanceof mcheli.aircraft.MCH_EntityHitBox) return false;
/*      */     
/*  812 */     if (W_Entity.isEqual(entity, this.shootingEntity)) return false; 
/*  813 */     if (this.shootingAircraft instanceof MCH_EntityAircraft) {
/*      */ 
/*      */       
/*  816 */       if (W_Entity.isEqual(entity, this.shootingAircraft)) return false;
/*      */       
/*  818 */       if (((MCH_EntityAircraft)this.shootingAircraft).isMountedEntity(entity)) return false; 
/*      */     } 
/*  820 */     for (String s : MCH_Config.IgnoreBulletHitList) {
/*      */       
/*  822 */       if (entity.getClass().getName().toLowerCase().indexOf(s.toLowerCase()) >= 0) return false; 
/*      */     } 
/*  824 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyHitBullet() {
/*  829 */     if (this.shootingAircraft instanceof MCH_EntityAircraft && W_EntityPlayer.isPlayer(this.shootingEntity))
/*      */     {
/*  831 */       MCH_PacketNotifyHitBullet.send((MCH_EntityAircraft)this.shootingAircraft, (EntityPlayer)this.shootingEntity);
/*      */     }
/*  833 */     if (W_EntityPlayer.isPlayer(this.shootingEntity))
/*      */     {
/*  835 */       MCH_PacketNotifyHitBullet.send(null, (EntityPlayer)this.shootingEntity);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onImpact(MovingObjectPosition m, float damageFactor) {
/*  844 */     if (!this.field_70170_p.field_72995_K) {
/*      */ 
/*      */       
/*  847 */       if (m.field_72308_g != null) {
/*      */         
/*  849 */         onImpactEntity(m.field_72308_g, damageFactor);
/*  850 */         this.piercing = 0;
/*      */       } 
/*      */       
/*  853 */       float expPower = this.explosionPower * damageFactor;
/*  854 */       float expPowerInWater = this.explosionPowerInWater * damageFactor;
/*      */       
/*  856 */       double dx = 0.0D;
/*  857 */       double dy = 0.0D;
/*  858 */       double dz = 0.0D;
/*      */ 
/*      */       
/*  861 */       if (this.piercing > 0)
/*      */       {
/*  863 */         this.piercing--;
/*  864 */         if (expPower > 0.0F)
/*      */         {
/*  866 */           newExplosion(m.field_72307_f.field_72450_a + dx, m.field_72307_f.field_72448_b + dy, m.field_72307_f.field_72449_c + dz, 1.0F, 1.0F, false);
/*      */         
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  872 */         if (expPowerInWater == 0.0F) {
/*      */           
/*  874 */           if ((getInfo()).isFAE)
/*      */           {
/*  876 */             newFAExplosion(this.field_70165_t, this.field_70163_u, this.field_70161_v, expPower, (getInfo()).explosionBlock);
/*      */           }
/*  878 */           else if (expPower > 0.0F)
/*      */           {
/*  880 */             newExplosion(m.field_72307_f.field_72450_a + dx, m.field_72307_f.field_72448_b + dy, m.field_72307_f.field_72449_c + dz, expPower, (getInfo()).explosionBlock, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*  887 */           else if (expPower < 0.0F)
/*      */           {
/*  889 */             playExplosionSound();
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*  896 */         else if (m.field_72308_g != null) {
/*      */           
/*  898 */           if (func_70090_H())
/*      */           {
/*  900 */             newExplosion(m.field_72307_f.field_72450_a + dx, m.field_72307_f.field_72448_b + dy, m.field_72307_f.field_72449_c + dz, expPowerInWater, expPowerInWater, true);
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*  905 */             newExplosion(m.field_72307_f.field_72450_a + dx, m.field_72307_f.field_72448_b + dy, m.field_72307_f.field_72449_c + dz, expPower, (getInfo()).explosionBlock, false);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  910 */         else if (func_70090_H() || MCH_Lib.isBlockInWater(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d)) {
/*      */           
/*  912 */           newExplosion(m.field_72311_b, m.field_72312_c, m.field_72309_d, expPowerInWater, expPowerInWater, true);
/*      */         
/*      */         }
/*  915 */         else if (expPower > 0.0F) {
/*      */           
/*  917 */           newExplosion(m.field_72307_f.field_72450_a + dx, m.field_72307_f.field_72448_b + dy, m.field_72307_f.field_72449_c + dz, expPower, (getInfo()).explosionBlock, false);
/*      */         
/*      */         }
/*  920 */         else if (expPower < 0.0F) {
/*      */           
/*  922 */           playExplosionSound();
/*      */         } 
/*      */ 
/*      */         
/*  926 */         func_70106_y();
/*      */       }
/*      */     
/*  929 */     } else if (getInfo() != null && ((getInfo()).explosion == 0 || (getInfo()).modeNum >= 2)) {
/*      */       
/*  931 */       if (W_MovingObjectPosition.isHitTypeTile(m)) {
/*      */         
/*  933 */         float p = (getInfo()).power;
/*  934 */         for (int i = 0; i < p / 3.0F; i++)
/*      */         {
/*  936 */           MCH_ParticlesUtil.spawnParticleTileCrack(this.field_70170_p, m.field_72311_b, m.field_72312_c, m.field_72309_d, m.field_72307_f.field_72450_a + (this.field_70146_Z.nextFloat() - 0.5D) * p / 10.0D, m.field_72307_f.field_72448_b + 0.1D, m.field_72307_f.field_72449_c + (this.field_70146_Z.nextFloat() - 0.5D) * p / 10.0D, -this.field_70159_w * p / 2.0D, (p / 2.0F), -this.field_70179_y * p / 2.0D);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onImpactEntity(Entity entity, float damageFactor) {
/*  950 */     if (!entity.field_70128_L) {
/*      */       
/*  952 */       MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityBaseBullet.onImpactEntity:Damage=%d:" + entity.getClass(), new Object[] { Integer.valueOf(getPower()) });
/*      */ 
/*      */       
/*  955 */       MCH_Lib.applyEntityHurtResistantTimeConfig(entity);
/*      */       
/*  957 */       DamageSource ds = DamageSource.func_76356_a((Entity)this, this.shootingEntity);
/*  958 */       float damage = MCH_Config.applyDamageVsEntity(entity, ds, getPower() * damageFactor);
/*  959 */       damage *= (getInfo() != null) ? getInfo().getDamageFactor(entity) : 1.0F;
/*  960 */       entity.func_70097_a(ds, damage);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  965 */       if (this instanceof mcheli.weapon.MCH_EntityBullet && entity instanceof net.minecraft.entity.passive.EntityVillager)
/*      */       {
/*  967 */         if (this.shootingEntity != null && this.shootingEntity.field_70154_o instanceof mcheli.aircraft.MCH_EntitySeat)
/*      */         {
/*  969 */           MCH_Achievement.addStat(this.shootingEntity, MCH_Achievement.aintWarHell, 1);
/*      */         }
/*      */       }
/*      */       
/*  973 */       if (entity.field_70128_L);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  979 */     notifyHitBullet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void newFAExplosion(double x, double y, double z, float exp, float expBlock) {
/*  986 */     MCH_Explosion.ExplosionResult result = MCH_Explosion.newExplosion(this.field_70170_p, (Entity)this, this.shootingEntity, x, y, z, exp, expBlock, true, true, (getInfo()).flaming, false, 15);
/*      */ 
/*      */     
/*  989 */     if (result != null && result.hitEntity)
/*      */     {
/*  991 */       notifyHitBullet();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void newExplosion(double x, double y, double z, float exp, float expBlock, boolean inWater) {
/*      */     MCH_Explosion.ExplosionResult result;
/*  998 */     if (!inWater) {
/*      */       
/* 1000 */       result = MCH_Explosion.newExplosion(this.field_70170_p, (Entity)this, this.shootingEntity, x, y, z, exp, expBlock, (this.isBomblet == 1) ? ((this.field_70146_Z.nextInt(3) == 0)) : true, true, (getInfo()).flaming, true, 0, (getInfo() != null) ? (getInfo()).damageFactor : null);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1006 */       result = MCH_Explosion.newExplosionInWater(this.field_70170_p, (Entity)this, this.shootingEntity, x, y, z, exp, expBlock, (this.isBomblet == 1) ? ((this.field_70146_Z.nextInt(3) == 0)) : true, true, (getInfo()).flaming, true, 0, (getInfo() != null) ? (getInfo()).damageFactor : null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1011 */     if (result != null && result.hitEntity)
/*      */     {
/* 1013 */       notifyHitBullet();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void playExplosionSound() {
/* 1019 */     MCH_Explosion.playExplosionSound(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 1027 */     par1NBTTagCompound.func_74782_a("direction", (NBTBase)func_70087_a(new double[] { this.field_70159_w, this.field_70181_x, this.field_70179_y }));
/* 1028 */     par1NBTTagCompound.func_74778_a("WeaponName", getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 1046 */     func_70106_y();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70067_L() {
/* 1054 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public float func_70111_Y() {
/* 1059 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70097_a(DamageSource ds, float par2) {
/* 1065 */     if (func_85032_ar()) return false;
/*      */     
/* 1067 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/* 1069 */       if (par2 > 0.0F && ds.func_76355_l().equalsIgnoreCase("thrown")) {
/*      */         
/* 1071 */         func_70018_K();
/* 1072 */         MovingObjectPosition m = new MovingObjectPosition((int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 0.5D), (int)(this.field_70161_v + 0.5D), 0, Vec3.func_72443_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1078 */         onImpact(m, 1.0F);
/* 1079 */         return true;
/*      */       } 
/*      */     }
/*      */     
/* 1083 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public float func_70053_R() {
/* 1089 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float func_70013_c(float par1) {
/* 1097 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public int func_70070_b(float par1) {
/* 1103 */     return 15728880;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPower() {
/* 1108 */     return this.power;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPower(int power) {
/* 1113 */     this.power = power;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityBaseBullet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */