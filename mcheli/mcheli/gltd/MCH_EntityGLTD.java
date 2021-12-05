/*     */ package mcheli.mcheli.gltd;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Camera;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.multiplay.MCH_Multiplay;
/*     */ import mcheli.weapon.MCH_WeaponCAS;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityGLTD
/*     */   extends W_Entity {
/*     */   private boolean field_70279_a;
/*     */   private double speedMultiplier;
/*     */   private int gltdPosRotInc;
/*     */   private double gltdX;
/*     */   private double gltdY;
/*     */   private double gltdZ;
/*     */   private double gltdYaw;
/*     */   private double gltdPitch;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityX;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityY;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityZ;
/*     */   public final MCH_Camera camera;
/*     */   public boolean zoomDir;
/*     */   public final MCH_WeaponCAS weaponCAS;
/*     */   public int countWait;
/*     */   public boolean isUsedPlayer;
/*     */   public float renderRotaionYaw;
/*     */   public float renderRotaionPitch;
/*     */   public int retryRiddenByEntityCheck;
/*     */   public Entity lastRiddenByEntity;
/*     */   
/*     */   public MCH_EntityGLTD(World world) {
/*  52 */     super(world);
/*  53 */     this.field_70279_a = true;
/*  54 */     this.speedMultiplier = 0.07D;
/*  55 */     this.field_70156_m = true;
/*  56 */     func_70105_a(0.5F, 0.5F);
/*  57 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  58 */     this.camera = new MCH_Camera(world, (Entity)this);
/*     */     
/*  60 */     MCH_WeaponInfo wi = MCH_WeaponInfoManager.get("a10gau8");
/*  61 */     this.weaponCAS = new MCH_WeaponCAS(world, Vec3.func_72443_a(0.0D, 0.0D, 0.0D), 0.0F, 0.0F, "a10gau8", wi);
/*  62 */     this.weaponCAS.interval += (this.weaponCAS.interval > 0) ? 150 : -150;
/*  63 */     this.weaponCAS.displayName = "A-10 GAU-8 Avenger";
/*     */     
/*  65 */     this.field_70158_ak = true;
/*  66 */     this.countWait = 0;
/*     */     
/*  68 */     this.retryRiddenByEntityCheck = 0;
/*  69 */     this.lastRiddenByEntity = null;
/*     */     
/*  71 */     this.isUsedPlayer = false;
/*  72 */     this.renderRotaionYaw = 0.0F;
/*  73 */     this.renderRotaionYaw = 0.0F;
/*  74 */     this.renderRotaionPitch = 0.0F;
/*  75 */     this.zoomDir = true;
/*     */     
/*  77 */     this.field_70155_l = 2.0D;
/*     */   }
/*     */   
/*     */   public MCH_EntityGLTD(World par1World, double x, double y, double z) {
/*  81 */     this(par1World);
/*  82 */     func_70107_b(x, y + this.field_70129_M, z);
/*  83 */     this.field_70159_w = 0.0D;
/*  84 */     this.field_70181_x = 0.0D;
/*  85 */     this.field_70179_y = 0.0D;
/*  86 */     this.field_70169_q = x;
/*  87 */     this.field_70167_r = y;
/*  88 */     this.field_70166_s = z;
/*  89 */     this.camera.setPosition(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70041_e_() {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 103 */     this.field_70180_af.func_75682_a(17, new Integer(0));
/* 104 */     this.field_70180_af.func_75682_a(18, new Integer(1));
/* 105 */     this.field_70180_af.func_75682_a(19, new Integer(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/* 114 */     return par1Entity.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70046_E() {
/* 122 */     return this.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 138 */     return this.field_70131_O * 0.0D - 0.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource ds, float damage) {
/* 146 */     if (func_85032_ar())
/*     */     {
/* 148 */       return false;
/*     */     }
/* 150 */     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
/*     */       
/* 152 */       damage = MCH_Config.applyDamageByExternal((Entity)this, ds, damage);
/*     */       
/* 154 */       if (!MCH_Multiplay.canAttackEntity(ds, (Entity)this))
/*     */       {
/* 156 */         return false;
/*     */       }
/*     */       
/* 159 */       setForwardDirection(-getForwardDirection());
/* 160 */       setTimeSinceHit(10);
/* 161 */       setDamageTaken((int)(getDamageTaken() + damage * 100.0F));
/* 162 */       func_70018_K();
/* 163 */       boolean flag = (ds.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)ds.func_76346_g()).field_71075_bZ.field_75098_d);
/*     */       
/* 165 */       if (flag || getDamageTaken() > 40.0F) {
/*     */         
/* 167 */         this.camera.initCamera(0, this.field_70153_n);
/*     */         
/* 169 */         if (this.field_70153_n != null)
/*     */         {
/* 171 */           this.field_70153_n.func_70078_a((Entity)this);
/*     */         }
/*     */         
/* 174 */         if (!flag)
/*     */         {
/* 176 */           dropItemWithOffset((Item)MCH_MOD.itemGLTD, 1, 0.0F);
/*     */         }
/*     */         
/* 179 */         W_WorldFunc.MOD_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "hit", 1.0F, 1.0F);
/*     */ 
/*     */         
/* 182 */         func_70106_y();
/*     */       } 
/*     */       
/* 185 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70057_ab() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 210 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 221 */     if (this.field_70279_a) {
/*     */       
/* 223 */       this.gltdPosRotInc = par9 + 5;
/*     */     }
/*     */     else {
/*     */       
/* 227 */       double x = par1 - this.field_70165_t;
/* 228 */       double y = par3 - this.field_70163_u;
/* 229 */       double z = par5 - this.field_70161_v;
/*     */       
/* 231 */       if (x * x + y * y + z * z <= 1.0D) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 236 */       this.gltdPosRotInc = 3;
/*     */     } 
/*     */     
/* 239 */     this.gltdX = par1;
/* 240 */     this.gltdY = par3;
/* 241 */     this.gltdZ = par5;
/* 242 */     this.gltdYaw = par7;
/* 243 */     this.gltdPitch = par8;
/* 244 */     this.field_70159_w = this.velocityX;
/* 245 */     this.field_70181_x = this.velocityY;
/* 246 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double x, double y, double z) {
/* 256 */     this.velocityX = this.field_70159_w = x;
/* 257 */     this.velocityY = this.field_70181_x = y;
/* 258 */     this.velocityZ = this.field_70179_y = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 266 */     super.func_70071_h_();
/*     */     
/* 268 */     if (getTimeSinceHit() > 0)
/*     */     {
/* 270 */       setTimeSinceHit(getTimeSinceHit() - 1);
/*     */     }
/*     */     
/* 273 */     if (getDamageTaken() > 0.0F)
/*     */     {
/* 275 */       setDamageTaken(getDamageTaken() - 1);
/*     */     }
/*     */     
/* 278 */     this.field_70169_q = this.field_70165_t;
/* 279 */     this.field_70167_r = this.field_70163_u;
/* 280 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 282 */     double d3 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */ 
/*     */     
/* 285 */     if (this.field_70153_n != null)
/*     */     {
/* 287 */       this.camera.updateViewer(0, this.field_70153_n);
/*     */     }
/*     */     
/* 290 */     if (this.field_70170_p.field_72995_K && this.field_70279_a) {
/*     */       
/* 292 */       if (this.gltdPosRotInc > 0)
/*     */       {
/* 294 */         double d4 = this.field_70165_t + (this.gltdX - this.field_70165_t) / this.gltdPosRotInc;
/* 295 */         double d5 = this.field_70163_u + (this.gltdY - this.field_70163_u) / this.gltdPosRotInc;
/* 296 */         double d11 = this.field_70161_v + (this.gltdZ - this.field_70161_v) / this.gltdPosRotInc;
/* 297 */         double d10 = MathHelper.func_76138_g(this.gltdYaw - this.field_70177_z);
/* 298 */         this.field_70177_z = (float)(this.field_70177_z + d10 / this.gltdPosRotInc);
/* 299 */         this.field_70125_A = (float)(this.field_70125_A + (this.gltdPitch - this.field_70125_A) / this.gltdPosRotInc);
/* 300 */         this.gltdPosRotInc--;
/* 301 */         func_70107_b(d4, d5, d11);
/* 302 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       }
/*     */       else
/*     */       {
/* 306 */         double d4 = this.field_70165_t + this.field_70159_w;
/* 307 */         double d5 = this.field_70163_u + this.field_70181_x;
/* 308 */         double d11 = this.field_70161_v + this.field_70179_y;
/* 309 */         func_70107_b(d4, d5, d11);
/*     */         
/* 311 */         if (this.field_70122_E) {
/*     */           
/* 313 */           this.field_70159_w *= 0.5D;
/* 314 */           this.field_70181_x *= 0.5D;
/* 315 */           this.field_70179_y *= 0.5D;
/*     */         } 
/*     */         
/* 318 */         this.field_70159_w *= 0.99D;
/* 319 */         this.field_70181_x *= 0.95D;
/* 320 */         this.field_70179_y *= 0.99D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 325 */       this.field_70181_x -= 0.04D;
/*     */       
/* 327 */       double d4 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */       
/* 329 */       if (d4 > 0.35D) {
/*     */         
/* 331 */         double d = 0.35D / d4;
/* 332 */         this.field_70159_w *= d;
/* 333 */         this.field_70179_y *= d;
/* 334 */         d4 = 0.35D;
/*     */       } 
/*     */       
/* 337 */       if (d4 > d3 && this.speedMultiplier < 0.35D) {
/*     */         
/* 339 */         this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
/*     */         
/* 341 */         if (this.speedMultiplier > 0.35D)
/*     */         {
/* 343 */           this.speedMultiplier = 0.35D;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 348 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
/*     */         
/* 350 */         if (this.speedMultiplier < 0.07D)
/*     */         {
/* 352 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       } 
/*     */       
/* 356 */       if (this.field_70122_E) {
/*     */         
/* 358 */         this.field_70159_w *= 0.5D;
/* 359 */         this.field_70181_x *= 0.5D;
/* 360 */         this.field_70179_y *= 0.5D;
/*     */       } 
/*     */       
/* 363 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 365 */       this.field_70159_w *= 0.99D;
/* 366 */       this.field_70181_x *= 0.95D;
/* 367 */       this.field_70179_y *= 0.99D;
/*     */       
/* 369 */       this.field_70125_A = 0.0F;
/* 370 */       double d5 = this.field_70177_z;
/* 371 */       double d11 = this.field_70169_q - this.field_70165_t;
/* 372 */       double d10 = this.field_70166_s - this.field_70161_v;
/*     */       
/* 374 */       if (d11 * d11 + d10 * d10 > 0.001D)
/*     */       {
/* 376 */         d5 = (float)(Math.atan2(d10, d11) * 180.0D / Math.PI);
/*     */       }
/*     */       
/* 379 */       double d12 = MathHelper.func_76138_g(d5 - this.field_70177_z);
/*     */       
/* 381 */       if (d12 > 20.0D)
/*     */       {
/* 383 */         d12 = 20.0D;
/*     */       }
/*     */       
/* 386 */       if (d12 < -20.0D)
/*     */       {
/* 388 */         d12 = -20.0D;
/*     */       }
/*     */       
/* 391 */       this.field_70177_z = (float)(this.field_70177_z + d12);
/* 392 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       
/* 394 */       if (!this.field_70170_p.field_72995_K) {
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
/* 415 */         if (MCH_Config.Collision_DestroyBlock.prmBool)
/*     */         {
/* 417 */           for (int l = 0; l < 4; l++) {
/*     */             
/* 419 */             int i1 = MathHelper.func_76128_c(this.field_70165_t + ((l % 2) - 0.5D) * 0.8D);
/* 420 */             int j1 = MathHelper.func_76128_c(this.field_70161_v + ((l / 2) - 0.5D) * 0.8D);
/*     */             
/* 422 */             for (int k1 = 0; k1 < 2; k1++) {
/*     */               
/* 424 */               int l1 = MathHelper.func_76128_c(this.field_70163_u) + k1;
/*     */               
/* 426 */               if (W_WorldFunc.isEqualBlock(this.field_70170_p, i1, l1, j1, W_Block.getSnowLayer()))
/*     */               {
/* 428 */                 this.field_70170_p.func_147468_f(i1, l1, j1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/* 434 */         if (this.field_70153_n != null && this.field_70153_n.field_70128_L)
/*     */         {
/* 436 */           this.field_70153_n = null;
/*     */         }
/*     */       } 
/*     */     } 
/* 440 */     updateCameraPosition(false);
/*     */     
/* 442 */     if (this.countWait > 0) this.countWait--; 
/* 443 */     if (this.countWait < 0) this.countWait++; 
/* 444 */     this.weaponCAS.update(this.countWait);
/*     */     
/* 446 */     if (this.lastRiddenByEntity != null && this.field_70153_n == null) {
/*     */       
/* 448 */       if (this.retryRiddenByEntityCheck < 3)
/*     */       {
/* 450 */         this.retryRiddenByEntityCheck++;
/*     */         
/* 452 */         setUnmoundPosition(this.lastRiddenByEntity);
/*     */       }
/*     */       else
/*     */       {
/* 456 */         unmountEntity();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 461 */       this.retryRiddenByEntityCheck = 0;
/*     */     } 
/*     */     
/* 464 */     if (this.field_70153_n != null)
/*     */     {
/* 466 */       this.lastRiddenByEntity = this.field_70153_n;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnmoundPosition(Entity e) {
/* 472 */     if (e == null)
/* 473 */       return;  float yaw = this.field_70177_z;
/* 474 */     double d0 = Math.sin(yaw * Math.PI / 180.0D) * 1.2D;
/* 475 */     double d1 = -Math.cos(yaw * Math.PI / 180.0D) * 1.2D;
/* 476 */     e.func_70107_b(this.field_70165_t + d0, this.field_70163_u + func_70042_X() + e.func_70033_W() + 1.0D, this.field_70161_v + d1);
/*     */ 
/*     */ 
/*     */     
/* 480 */     e.field_70142_S = e.field_70169_q = e.field_70165_t;
/* 481 */     e.field_70137_T = e.field_70167_r = e.field_70163_u;
/* 482 */     e.field_70136_U = e.field_70166_s = e.field_70161_v;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unmountEntity() {
/* 487 */     this.camera.setMode(0, 0);
/* 488 */     this.camera.setCameraZoom(1.0F);
/* 489 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 491 */       if (this.field_70153_n != null) {
/*     */         
/* 493 */         if (!this.field_70153_n.field_70128_L)
/*     */         {
/* 495 */           this.field_70153_n.func_70078_a(null);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 500 */       else if (this.lastRiddenByEntity != null && !this.lastRiddenByEntity.field_70128_L) {
/*     */         
/* 502 */         this.camera.updateViewer(0, this.lastRiddenByEntity);
/*     */         
/* 504 */         setUnmoundPosition(this.lastRiddenByEntity);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 509 */     this.field_70153_n = null;
/* 510 */     this.lastRiddenByEntity = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCameraPosition(boolean foreceUpdate) {
/* 515 */     if (foreceUpdate || (this.field_70153_n != null && this.camera != null)) {
/*     */       
/* 517 */       double x = -Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.6D;
/* 518 */       double z = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.6D;
/* 519 */       this.camera.setPosition(this.field_70165_t + x, this.field_70163_u + 0.7D, this.field_70161_v + z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void zoomCamera(float f) {
/* 526 */     float z = this.camera.getCameraZoom();
/* 527 */     z += f;
/* 528 */     if (z < 1.0F) z = 1.0F; 
/* 529 */     if (z > 10.0F) z = 10.0F; 
/* 530 */     this.camera.setCameraZoom(z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70043_V() {
/* 535 */     if (this.field_70153_n != null) {
/*     */       
/* 537 */       double x = Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.5D;
/* 538 */       double z = -Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.5D;
/* 539 */       this.field_70153_n.func_70107_b(this.field_70165_t + x, this.field_70163_u + func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchWeapon(int id) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useCurrentWeapon(int option1, int option2) {
/* 553 */     if (this.countWait == 0 && this.field_70153_n != null)
/*     */     {
/* 555 */       if (this.weaponCAS.shot(this.field_70153_n, this.camera.posX, this.camera.posY, this.camera.posZ, option1, option2)) {
/*     */ 
/*     */         
/* 558 */         this.countWait = this.weaponCAS.interval;
/* 559 */         if (this.field_70170_p.field_72995_K) {
/*     */           
/* 561 */           this.countWait += (this.countWait > 0) ? 10 : -10;
/*     */         }
/*     */         else {
/*     */           
/* 565 */           W_WorldFunc.MOD_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "gltd", 0.5F, 1.0F);
/*     */         } 
/*     */         
/* 568 */         return true;
/*     */       } 
/*     */     }
/* 571 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 587 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 592 */     if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != player)
/*     */     {
/* 594 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 599 */     player.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
/*     */ 
/*     */     
/* 602 */     player.field_70125_A = MathHelper.func_76142_g(this.field_70125_A);
/*     */     
/* 604 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 606 */       player.func_70078_a((Entity)this);
/*     */     }
/*     */     else {
/*     */       
/* 610 */       this.zoomDir = true;
/* 611 */       this.camera.setCameraZoom(1.0F);
/* 612 */       if (this.countWait > 0) this.countWait = -this.countWait; 
/* 613 */       if (this.countWait > -60) this.countWait = -60; 
/*     */     } 
/* 615 */     updateCameraPosition(true);
/*     */     
/* 617 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageTaken(int par1) {
/* 626 */     this.field_70180_af.func_75692_b(19, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageTaken() {
/* 634 */     return this.field_70180_af.func_75679_c(19);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSinceHit(int par1) {
/* 642 */     this.field_70180_af.func_75692_b(17, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeSinceHit() {
/* 650 */     return this.field_70180_af.func_75679_c(17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForwardDirection(int par1) {
/* 658 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getForwardDirection() {
/* 666 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70270_d(boolean par1) {
/* 672 */     this.field_70279_a = par1;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_EntityGLTD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */