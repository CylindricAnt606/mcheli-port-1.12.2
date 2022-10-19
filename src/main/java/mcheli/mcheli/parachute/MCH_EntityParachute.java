/*     */ package mcheli.mcheli.parachute;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.wrapper.W_AxisAlignedBB;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityBoat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityParachute
/*     */   extends W_Entity
/*     */ {
/*     */   private double speedMultiplier;
/*     */   private int paraPosRotInc;
/*     */   private double paraX;
/*     */   private double paraY;
/*     */   private double paraZ;
/*     */   private double paraYaw;
/*     */   private double paraPitch;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityX;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityY;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityZ;
/*     */   public Entity user;
/*     */   public int onGroundCount;
/*     */   
/*     */   public MCH_EntityParachute(World par1World) {
/*  50 */     super(par1World);
/*  51 */     this.speedMultiplier = 0.07D;
/*  52 */     this.field_70156_m = true;
/*  53 */     func_70105_a(1.5F, 0.6F);
/*  54 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  55 */     this.user = null;
/*  56 */     this.onGroundCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityParachute(World par1World, double par2, double par4, double par6) {
/*  61 */     this(par1World);
/*  62 */     func_70107_b(par2, par4 + this.field_70129_M, par6);
/*  63 */     this.field_70159_w = 0.0D;
/*  64 */     this.field_70181_x = 0.0D;
/*  65 */     this.field_70179_y = 0.0D;
/*  66 */     this.field_70169_q = par2;
/*  67 */     this.field_70167_r = par4;
/*  68 */     this.field_70166_s = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70041_e_() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  82 */     func_70096_w().func_75682_a(31, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int n) {
/*  87 */     func_70096_w().func_75692_b(31, Byte.valueOf((byte)n));
/*     */   }
/*     */   
/*     */   public int getType() {
/*  91 */     return func_70096_w().func_75683_a(31);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/* 100 */     return par1Entity.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70046_E() {
/* 108 */     return this.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 124 */     return this.field_70131_O * 0.0D - 0.30000001192092896D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 140 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 146 */     this.paraPosRotInc = par9 + 10;
/*     */     
/* 148 */     this.paraX = par1;
/* 149 */     this.paraY = par3;
/* 150 */     this.paraZ = par5;
/* 151 */     this.paraYaw = par7;
/* 152 */     this.paraPitch = par8;
/* 153 */     this.field_70159_w = this.velocityX;
/* 154 */     this.field_70181_x = this.velocityY;
/* 155 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5) {
/* 165 */     this.velocityX = this.field_70159_w = par1;
/* 166 */     this.velocityY = this.field_70181_x = par3;
/* 167 */     this.velocityZ = this.field_70179_y = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 172 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 180 */     super.func_70071_h_();
/*     */     
/* 182 */     if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 10 == 0)
/*     */     {
/* 184 */       MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityParachute.onUpdate %d, %.3f", new Object[] { Integer.valueOf(this.field_70173_aa), Double.valueOf(this.field_70181_x) });
/*     */     }
/* 186 */     if (isOpenParachute() && this.field_70181_x > -0.3D && this.field_70173_aa > 20)
/*     */     {
/* 188 */       this.field_70143_R = (float)(this.field_70143_R * 0.85D);
/*     */     }
/*     */     
/* 191 */     if (!this.field_70170_p.field_72995_K && this.user != null && this.user.field_70154_o == null) {
/*     */       
/* 193 */       this.user.func_70078_a((Entity)this);
/* 194 */       this.field_70177_z = this.field_70126_B = this.user.field_70177_z;
/* 195 */       this.user = null;
/*     */     } 
/*     */     
/* 198 */     this.field_70169_q = this.field_70165_t;
/* 199 */     this.field_70167_r = this.field_70163_u;
/* 200 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 202 */     double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.0D / 5.0D - 0.125D;
/* 203 */     double d2 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 1.0D / 5.0D - 0.125D;
/* 204 */     AxisAlignedBB axisalignedbb = W_AxisAlignedBB.getAABB(this.field_70121_D.field_72340_a, d1, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d2, this.field_70121_D.field_72334_f);
/*     */     
/* 206 */     if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h)) {
/*     */       
/* 208 */       onWaterSetBoat();
/* 209 */       func_70106_y();
/*     */     } 
/*     */     
/* 212 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 214 */       onUpdateClient();
/*     */     }
/*     */     else {
/*     */       
/* 218 */       onUpdateServer();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateClient() {
/* 224 */     if (this.paraPosRotInc > 0) {
/*     */       
/* 226 */       double x = this.field_70165_t + (this.paraX - this.field_70165_t) / this.paraPosRotInc;
/* 227 */       double y = this.field_70163_u + (this.paraY - this.field_70163_u) / this.paraPosRotInc;
/* 228 */       double z = this.field_70161_v + (this.paraZ - this.field_70161_v) / this.paraPosRotInc;
/* 229 */       double yaw = MathHelper.func_76138_g(this.paraYaw - this.field_70177_z);
/* 230 */       this.field_70177_z = (float)(this.field_70177_z + yaw / this.paraPosRotInc);
/* 231 */       this.field_70125_A = (float)(this.field_70125_A + (this.paraPitch - this.field_70125_A) / this.paraPosRotInc);
/*     */       
/* 233 */       this.paraPosRotInc--;
/*     */       
/* 235 */       func_70107_b(x, y, z);
/* 236 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       
/* 238 */       if (this.field_70153_n != null)
/*     */       {
/* 240 */         func_70101_b(this.field_70153_n.field_70126_B, this.field_70125_A);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 245 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 247 */       if (this.field_70122_E);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 252 */       this.field_70159_w *= 0.99D;
/* 253 */       this.field_70181_x *= 0.95D;
/* 254 */       this.field_70179_y *= 0.99D;
/*     */     } 
/*     */     
/* 257 */     if (!isOpenParachute() && this.field_70181_x > 0.01D) {
/*     */       
/* 259 */       float color = 0.6F + this.field_70146_Z.nextFloat() * 0.2F;
/*     */       
/* 261 */       double dx = this.field_70169_q - this.field_70165_t;
/* 262 */       double dy = this.field_70167_r - this.field_70163_u;
/* 263 */       double dz = this.field_70166_s - this.field_70161_v;
/* 264 */       int num = 1 + (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 2.0D);
/*     */       double i;
/* 266 */       for (i = 0.0D; i < num; i++) {
/*     */         
/* 268 */         MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70169_q + (this.field_70165_t - this.field_70169_q) * i / num * 0.8D, this.field_70167_r + (this.field_70163_u - this.field_70167_r) * i / num * 0.8D, this.field_70166_s + (this.field_70161_v - this.field_70166_s) * i / num * 0.8D);
/*     */ 
/*     */ 
/*     */         
/* 272 */         prm.motionX = this.field_70159_w * 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 0.5D;
/* 273 */         prm.motionX = this.field_70181_x * -0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 0.5D;
/* 274 */         prm.motionX = this.field_70179_y * 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 0.5D;
/* 275 */         prm.size = 5.0F;
/* 276 */         prm.setColor(0.8F + this.field_70146_Z.nextFloat(), color, color, color);
/* 277 */         MCH_ParticlesUtil.spawnParticle(prm);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateServer() {
/* 285 */     double prevSpeed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */     
/* 287 */     double gravity = this.field_70122_E ? 0.01D : 0.03D;
/*     */     
/* 289 */     if (getType() == 2 && this.field_70173_aa < 20)
/*     */     {
/* 291 */       gravity = 0.01D;
/*     */     }
/* 293 */     this.field_70181_x -= gravity;
/*     */     
/* 295 */     if (isOpenParachute()) {
/*     */       
/* 297 */       if (W_Lib.isEntityLivingBase(this.field_70153_n)) {
/*     */         
/* 299 */         double mv = W_Lib.getEntityMoveDist(this.field_70153_n);
/*     */         
/* 301 */         if (!isOpenParachute())
/*     */         {
/* 303 */           mv = 0.0D;
/*     */         }
/*     */         
/* 306 */         if (mv > 0.0D) {
/*     */           
/* 308 */           double mx = -Math.sin((this.field_70153_n.field_70177_z * 3.1415927F / 180.0F));
/* 309 */           double mz = Math.cos((this.field_70153_n.field_70177_z * 3.1415927F / 180.0F));
/* 310 */           this.field_70159_w += mx * this.speedMultiplier * 0.05D;
/* 311 */           this.field_70179_y += mz * this.speedMultiplier * 0.05D;
/*     */         } 
/*     */       } 
/*     */       
/* 315 */       double speed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */       
/* 317 */       if (speed > 0.35D) {
/*     */         
/* 319 */         this.field_70159_w *= 0.35D / speed;
/* 320 */         this.field_70179_y *= 0.35D / speed;
/* 321 */         speed = 0.35D;
/*     */       } 
/*     */       
/* 324 */       if (speed > prevSpeed && this.speedMultiplier < 0.35D) {
/*     */         
/* 326 */         this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
/*     */         
/* 328 */         if (this.speedMultiplier > 0.35D)
/*     */         {
/* 330 */           this.speedMultiplier = 0.35D;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 335 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
/*     */         
/* 337 */         if (this.speedMultiplier < 0.07D)
/*     */         {
/* 339 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 344 */     if (this.field_70122_E) {
/*     */       
/* 346 */       this.onGroundCount++;
/* 347 */       if (this.onGroundCount > 5) {
/*     */         
/* 349 */         onGroundAndDead();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 354 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 356 */     if (getType() == 2 && this.field_70173_aa < 20) {
/*     */       
/* 358 */       this.field_70181_x *= 0.95D;
/*     */     }
/*     */     else {
/*     */       
/* 362 */       this.field_70181_x *= 0.9D;
/*     */     } 
/*     */     
/* 365 */     if (isOpenParachute()) {
/*     */       
/* 367 */       this.field_70159_w *= 0.95D;
/* 368 */       this.field_70179_y *= 0.95D;
/*     */     }
/*     */     else {
/*     */       
/* 372 */       this.field_70159_w *= 0.99D;
/* 373 */       this.field_70179_y *= 0.99D;
/*     */     } 
/*     */     
/* 376 */     this.field_70125_A = 0.0F;
/* 377 */     double yaw = this.field_70177_z;
/* 378 */     double dx = this.field_70169_q - this.field_70165_t;
/* 379 */     double dz = this.field_70166_s - this.field_70161_v;
/*     */     
/* 381 */     if (dx * dx + dz * dz > 0.001D)
/*     */     {
/* 383 */       yaw = (float)(Math.atan2(dx, dz) * 180.0D / Math.PI);
/*     */     }
/*     */     
/* 386 */     double yawDiff = MathHelper.func_76138_g(yaw - this.field_70177_z);
/*     */     
/* 388 */     if (yawDiff > 20.0D)
/*     */     {
/* 390 */       yawDiff = 20.0D;
/*     */     }
/*     */     
/* 393 */     if (yawDiff < -20.0D)
/*     */     {
/* 395 */       yawDiff = -20.0D;
/*     */     }
/*     */     
/* 398 */     if (this.field_70153_n != null) {
/*     */       
/* 400 */       func_70101_b(this.field_70153_n.field_70177_z, this.field_70125_A);
/*     */     }
/*     */     else {
/*     */       
/* 404 */       this.field_70177_z = (float)(this.field_70177_z + yawDiff);
/* 405 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */     
/* 408 */     List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(0.2D, 0.0D, 0.2D));
/*     */ 
/*     */     
/* 411 */     if (list != null && !list.isEmpty())
/*     */     {
/* 413 */       for (int l = 0; l < list.size(); l++) {
/*     */         
/* 415 */         Entity entity = list.get(l);
/*     */         
/* 417 */         if (entity != this.field_70153_n && entity.func_70104_M() && entity instanceof mcheli.parachute.MCH_EntityParachute)
/*     */         {
/* 419 */           entity.func_70108_f((Entity)this);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 424 */     if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
/*     */       
/* 426 */       this.field_70153_n = null;
/* 427 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGroundAndDead() {
/* 433 */     this.field_70163_u += 1.2D;
/* 434 */     func_70043_V();
/* 435 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void onWaterSetBoat() {
/* 439 */     if (this.field_70170_p.field_72995_K)
/* 440 */       return;  if (getType() != 2)
/* 441 */       return;  if (this.field_70153_n == null)
/*     */       return; 
/* 443 */     int px = (int)(this.field_70165_t + 0.5D);
/* 444 */     int py = (int)(this.field_70163_u + 0.5D);
/* 445 */     int pz = (int)(this.field_70161_v + 0.5D);
/*     */     
/* 447 */     boolean foundBlock = false;
/*     */ 
/*     */     
/* 450 */     for (int y = 0; y < 5; y++) {
/*     */       
/* 452 */       if (py + y < 0 || py + y > 255)
/* 453 */         break;  Block block = W_WorldFunc.getBlock(this.field_70170_p, px, py - y, pz);
/* 454 */       if (block == W_Block.getWater()) {
/*     */         
/* 456 */         py -= y;
/* 457 */         foundBlock = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 461 */     if (!foundBlock)
/*     */       return; 
/* 463 */     int countWater = 0;
/*     */     
/* 465 */     int size = 5;
/*     */ 
/*     */     
/* 468 */     for (int i = 0; i < 3; i++) {
/*     */       
/* 470 */       if (py + i < 0 || py + i > 255)
/*     */         break; 
/* 472 */       for (int x = -2; x <= 2; x++) {
/*     */         
/* 474 */         for (int z = -2; z <= 2; z++) {
/*     */           
/* 476 */           Block block = W_WorldFunc.getBlock(this.field_70170_p, px + x, py - i, pz + z);
/* 477 */           if (block == W_Block.getWater()) {
/*     */             
/* 479 */             countWater++;
/* 480 */             if (countWater > 37) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 489 */     if (countWater > 37) {
/*     */       
/* 491 */       EntityBoat entityboat = new EntityBoat(this.field_70170_p, px, (py + 1.0F), pz);
/* 492 */       entityboat.field_70177_z = this.field_70177_z - 90.0F;
/*     */       
/* 494 */       this.field_70170_p.func_72838_d((Entity)entityboat);
/*     */       
/* 496 */       this.field_70153_n.func_70078_a((Entity)entityboat);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpenParachute() {
/* 502 */     return (getType() != 2 || this.field_70181_x < -0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70043_V() {
/* 507 */     if (this.field_70153_n != null) {
/*     */       
/* 509 */       double x = -Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.1D;
/* 510 */       double z = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.1D;
/* 511 */       this.field_70153_n.func_70107_b(this.field_70165_t + x, this.field_70163_u + func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound nbt) {
/* 520 */     nbt.func_74774_a("ParachuteModelType", (byte)getType());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound nbt) {
/* 525 */     setType(nbt.func_74771_c("ParachuteModelType"));
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 531 */     return 4.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer par1EntityPlayer) {
/* 539 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\parachute\MCH_EntityParachute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */