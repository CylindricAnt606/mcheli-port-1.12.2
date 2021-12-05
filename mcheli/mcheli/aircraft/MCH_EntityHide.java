/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
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
/*     */ public class MCH_EntityHide
/*     */   extends W_Entity
/*     */ {
/*     */   private MCH_EntityAircraft ac;
/*     */   private Entity user;
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
/*     */   
/*     */   public MCH_EntityHide(World par1World) {
/*  52 */     super(par1World);
/*     */     
/*  54 */     func_70105_a(1.0F, 1.0F);
/*     */     
/*  56 */     this.field_70156_m = true;
/*  57 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  58 */     this.user = null;
/*  59 */     this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
/*     */   }
/*     */   
/*     */   public MCH_EntityHide(World par1World, double x, double y, double z) {
/*  63 */     this(par1World);
/*  64 */     this.field_70165_t = x;
/*  65 */     this.field_70163_u = y;
/*  66 */     this.field_70161_v = z;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  71 */     super.func_70088_a();
/*  72 */     createRopeIndex(-1);
/*  73 */     func_70096_w().func_75682_a(31, new Integer(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParent(MCH_EntityAircraft ac, Entity user, int ropeIdx) {
/*  78 */     this.ac = ac;
/*  79 */     setRopeIndex(ropeIdx);
/*  80 */     this.user = user;
/*     */   }
/*     */   
/*  83 */   protected boolean func_70041_e_() { return false; }
/*  84 */   public AxisAlignedBB func_70114_g(Entity par1Entity) { return par1Entity.field_70121_D; }
/*  85 */   public AxisAlignedBB func_70046_E() { return this.field_70121_D; }
/*  86 */   public boolean func_70104_M() { return true; }
/*  87 */   public double func_70042_X() { return this.field_70131_O * 0.0D - 0.3D; }
/*  88 */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) { return false; } public boolean func_70067_L() {
/*  89 */     return !this.field_70128_L;
/*     */   }
/*     */   protected void func_70014_b(NBTTagCompound nbt) {}
/*     */   @SideOnly(Side.CLIENT)
/*  93 */   public float func_70053_R() { return 0.0F; } protected void func_70037_a(NBTTagCompound nbt) {} public boolean func_130002_c(EntityPlayer par1EntityPlayer) {
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public void createRopeIndex(int defaultValue) {
/*  98 */     func_70096_w().func_75682_a(30, new Integer(defaultValue));
/*     */   }
/*     */   
/*     */   public int getRopeIndex() {
/* 102 */     return func_70096_w().func_75679_c(30);
/*     */   }
/*     */   
/*     */   public void setRopeIndex(int value) {
/* 106 */     func_70096_w().func_75692_b(30, new Integer(value));
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 112 */     this.paraPosRotInc = par9 + 10;
/*     */     
/* 114 */     this.paraX = par1;
/* 115 */     this.paraY = par3;
/* 116 */     this.paraZ = par5;
/* 117 */     this.paraYaw = par7;
/* 118 */     this.paraPitch = par8;
/* 119 */     this.field_70159_w = this.velocityX;
/* 120 */     this.field_70181_x = this.velocityY;
/* 121 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5) {
/* 127 */     this.velocityX = this.field_70159_w = par1;
/* 128 */     this.velocityY = this.field_70181_x = par3;
/* 129 */     this.velocityZ = this.field_70179_y = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 134 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 139 */     super.func_70071_h_();
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (this.user != null && !this.field_70170_p.field_72995_K) {
/*     */       
/* 145 */       if (this.ac != null)
/*     */       {
/* 147 */         func_70096_w().func_75692_b(31, new Integer(this.ac.func_145782_y()));
/*     */       }
/* 149 */       this.user.func_70078_a((Entity)this);
/* 150 */       this.user = null;
/*     */     } 
/*     */     
/* 153 */     if (this.ac == null && this.field_70170_p.field_72995_K) {
/*     */       
/* 155 */       int id = func_70096_w().func_75679_c(31);
/* 156 */       if (id > 0) {
/*     */         
/* 158 */         Entity entity = this.field_70170_p.func_73045_a(id);
/* 159 */         if (entity instanceof MCH_EntityAircraft)
/*     */         {
/* 161 */           this.ac = (MCH_EntityAircraft)entity;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     this.field_70169_q = this.field_70165_t;
/* 167 */     this.field_70167_r = this.field_70163_u;
/* 168 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 170 */     this.field_70143_R = 0.0F;
/* 171 */     if (this.field_70153_n != null)
/*     */     {
/* 173 */       this.field_70153_n.field_70143_R = 0.0F;
/*     */     }
/*     */     
/* 176 */     if (this.ac != null) {
/*     */       
/* 178 */       if (!this.ac.isRepelling())
/*     */       {
/* 180 */         func_70106_y();
/*     */       }
/* 182 */       int id = getRopeIndex();
/* 183 */       if (id >= 0) {
/*     */         
/* 185 */         Vec3 v = this.ac.getRopePos(id);
/* 186 */         this.field_70165_t = v.field_72450_a;
/* 187 */         this.field_70161_v = v.field_72449_c;
/*     */       } 
/*     */     } 
/*     */     
/* 191 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     
/* 193 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 195 */       onUpdateClient();
/*     */     }
/*     */     else {
/*     */       
/* 199 */       onUpdateServer();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateClient() {
/* 205 */     if (this.paraPosRotInc > 0) {
/*     */       
/* 207 */       double x = this.field_70165_t + (this.paraX - this.field_70165_t) / this.paraPosRotInc;
/* 208 */       double y = this.field_70163_u + (this.paraY - this.field_70163_u) / this.paraPosRotInc;
/* 209 */       double z = this.field_70161_v + (this.paraZ - this.field_70161_v) / this.paraPosRotInc;
/* 210 */       double yaw = MathHelper.func_76138_g(this.paraYaw - this.field_70177_z);
/* 211 */       this.field_70177_z = (float)(this.field_70177_z + yaw / this.paraPosRotInc);
/* 212 */       this.field_70125_A = (float)(this.field_70125_A + (this.paraPitch - this.field_70125_A) / this.paraPosRotInc);
/*     */       
/* 214 */       this.paraPosRotInc--;
/*     */       
/* 216 */       func_70107_b(x, y, z);
/* 217 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       
/* 219 */       if (this.field_70153_n != null)
/*     */       {
/* 221 */         func_70101_b(this.field_70153_n.field_70126_B, this.field_70125_A);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 226 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 228 */       this.field_70159_w *= 0.99D;
/* 229 */       this.field_70181_x *= 0.95D;
/* 230 */       this.field_70179_y *= 0.99D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateServer() {
/* 236 */     this.field_70181_x -= this.field_70122_E ? 0.01D : 0.03D;
/*     */     
/* 238 */     if (this.field_70122_E) {
/*     */       
/* 240 */       onGroundAndDead();
/*     */       
/*     */       return;
/*     */     } 
/* 244 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 246 */     this.field_70181_x *= 0.9D;
/* 247 */     this.field_70159_w *= 0.95D;
/* 248 */     this.field_70179_y *= 0.95D;
/*     */     
/* 250 */     int id = getRopeIndex();
/* 251 */     if (this.ac != null && id >= 0) {
/*     */       
/* 253 */       Vec3 v = this.ac.getRopePos(id);
/* 254 */       if (Math.abs(this.field_70163_u - v.field_72448_b) > (Math.abs(this.ac.ropesLength) + 5.0F))
/*     */       {
/* 256 */         onGroundAndDead();
/*     */       }
/*     */     } 
/*     */     
/* 260 */     if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
/*     */       
/* 262 */       this.field_70153_n = null;
/* 263 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) {
/* 270 */     ArrayList<AxisAlignedBB> collidingBoundingBoxes = new ArrayList();
/* 271 */     collidingBoundingBoxes.clear();
/* 272 */     int i = MathHelper.func_76128_c(par2AxisAlignedBB.field_72340_a);
/* 273 */     int j = MathHelper.func_76128_c(par2AxisAlignedBB.field_72336_d + 1.0D);
/* 274 */     int k = MathHelper.func_76128_c(par2AxisAlignedBB.field_72338_b);
/* 275 */     int l = MathHelper.func_76128_c(par2AxisAlignedBB.field_72337_e + 1.0D);
/* 276 */     int i1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72339_c);
/* 277 */     int j1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72334_f + 1.0D);
/*     */     
/* 279 */     for (int k1 = i; k1 < j; k1++) {
/*     */       
/* 281 */       for (int l1 = i1; l1 < j1; l1++) {
/*     */         
/* 283 */         if (this.field_70170_p.func_72899_e(k1, 64, l1))
/*     */         {
/* 285 */           for (int i2 = k - 1; i2 < l; i2++) {
/*     */             
/* 287 */             Block block = W_WorldFunc.getBlock(this.field_70170_p, k1, i2, l1);
/*     */             
/* 289 */             if (block != null)
/*     */             {
/* 291 */               block.func_149743_a(this.field_70170_p, k1, i2, l1, par2AxisAlignedBB, collidingBoundingBoxes, par1Entity);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     double d0 = 0.25D;
/* 299 */     List<Entity> list = this.field_70170_p.func_72839_b(par1Entity, par2AxisAlignedBB.func_72314_b(d0, d0, d0));
/*     */     
/* 301 */     for (int j2 = 0; j2 < list.size(); j2++) {
/*     */       
/* 303 */       Entity entity = list.get(j2);
/*     */       
/* 305 */       if (!W_Lib.isEntityLivingBase(entity) && 
/* 306 */         !(entity instanceof mcheli.aircraft.MCH_EntitySeat) && 
/* 307 */         !(entity instanceof mcheli.aircraft.MCH_EntityHitBox)) {
/*     */         
/* 309 */         AxisAlignedBB axisalignedbb1 = entity.func_70046_E();
/*     */         
/* 311 */         if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB))
/*     */         {
/* 313 */           collidingBoundingBoxes.add(axisalignedbb1);
/*     */         }
/*     */         
/* 316 */         axisalignedbb1 = par1Entity.func_70114_g(entity);
/*     */         
/* 318 */         if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB))
/*     */         {
/* 320 */           collidingBoundingBoxes.add(axisalignedbb1);
/*     */         }
/*     */       } 
/*     */     } 
/* 324 */     return collidingBoundingBoxes;
/*     */   }
/*     */   
/*     */   public void func_70091_d(double par1, double par3, double par5) {
/* 328 */     this.field_70170_p.field_72984_F.func_76320_a("move");
/* 329 */     this.field_70139_V *= 0.4F;
/* 330 */     double d3 = this.field_70165_t;
/* 331 */     double d4 = this.field_70163_u;
/* 332 */     double d5 = this.field_70161_v;
/*     */     
/* 334 */     double d6 = par1;
/* 335 */     double d7 = par3;
/* 336 */     double d8 = par5;
/* 337 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/*     */ 
/*     */     
/* 340 */     List<AxisAlignedBB> list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(par1, par3, par5));
/*     */     
/* 342 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 344 */       par3 = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, par3);
/*     */     }
/*     */     
/* 347 */     this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */     
/* 349 */     if (!this.field_70135_K && d7 != par3) {
/*     */       
/* 351 */       par5 = 0.0D;
/* 352 */       par3 = 0.0D;
/* 353 */       par1 = 0.0D;
/*     */     } 
/*     */     
/* 356 */     boolean flag1 = (this.field_70122_E || (d7 != par3 && d7 < 0.0D));
/*     */     
/*     */     int j;
/* 359 */     for (j = 0; j < list.size(); j++)
/*     */     {
/* 361 */       par1 = ((AxisAlignedBB)list.get(j)).func_72316_a(this.field_70121_D, par1);
/*     */     }
/*     */     
/* 364 */     this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D);
/*     */     
/* 366 */     if (!this.field_70135_K && d6 != par1) {
/*     */       
/* 368 */       par5 = 0.0D;
/* 369 */       par3 = 0.0D;
/* 370 */       par1 = 0.0D;
/*     */     } 
/*     */     
/* 373 */     for (j = 0; j < list.size(); j++)
/*     */     {
/* 375 */       par5 = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, par5);
/*     */     }
/*     */     
/* 378 */     this.field_70121_D.func_72317_d(0.0D, 0.0D, par5);
/*     */     
/* 380 */     if (!this.field_70135_K && d8 != par5) {
/*     */       
/* 382 */       par5 = 0.0D;
/* 383 */       par3 = 0.0D;
/* 384 */       par1 = 0.0D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     if (this.field_70138_W > 0.0F && flag1 && this.field_70139_V < 0.05F && (d6 != par1 || d8 != par5)) {
/*     */       
/* 394 */       double d9 = par1;
/* 395 */       double d1 = par3;
/* 396 */       double d2 = par5;
/* 397 */       par1 = d6;
/* 398 */       par3 = this.field_70138_W;
/* 399 */       par5 = d8;
/* 400 */       AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
/* 401 */       this.field_70121_D.func_72328_c(axisalignedbb);
/*     */       
/* 403 */       list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(d6, par3, d8));
/*     */       int k;
/* 405 */       for (k = 0; k < list.size(); k++)
/*     */       {
/* 407 */         par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
/*     */       }
/*     */       
/* 410 */       this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */       
/* 412 */       if (!this.field_70135_K && d7 != par3) {
/*     */         
/* 414 */         par5 = 0.0D;
/* 415 */         par3 = 0.0D;
/* 416 */         par1 = 0.0D;
/*     */       } 
/*     */       
/* 419 */       for (k = 0; k < list.size(); k++)
/*     */       {
/* 421 */         par1 = ((AxisAlignedBB)list.get(k)).func_72316_a(this.field_70121_D, par1);
/*     */       }
/*     */       
/* 424 */       this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D);
/*     */       
/* 426 */       if (!this.field_70135_K && d6 != par1) {
/*     */         
/* 428 */         par5 = 0.0D;
/* 429 */         par3 = 0.0D;
/* 430 */         par1 = 0.0D;
/*     */       } 
/*     */       
/* 433 */       for (k = 0; k < list.size(); k++)
/*     */       {
/* 435 */         par5 = ((AxisAlignedBB)list.get(k)).func_72322_c(this.field_70121_D, par5);
/*     */       }
/*     */       
/* 438 */       this.field_70121_D.func_72317_d(0.0D, 0.0D, par5);
/*     */       
/* 440 */       if (!this.field_70135_K && d8 != par5) {
/*     */         
/* 442 */         par5 = 0.0D;
/* 443 */         par3 = 0.0D;
/* 444 */         par1 = 0.0D;
/*     */       } 
/*     */       
/* 447 */       if (!this.field_70135_K && d7 != par3) {
/*     */         
/* 449 */         par5 = 0.0D;
/* 450 */         par3 = 0.0D;
/* 451 */         par1 = 0.0D;
/*     */       }
/*     */       else {
/*     */         
/* 455 */         par3 = -this.field_70138_W;
/*     */         
/* 457 */         for (k = 0; k < list.size(); k++)
/*     */         {
/* 459 */           par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
/*     */         }
/*     */         
/* 462 */         this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */       } 
/*     */       
/* 465 */       if (d9 * d9 + d2 * d2 >= par1 * par1 + par5 * par5) {
/*     */         
/* 467 */         par1 = d9;
/* 468 */         par3 = d1;
/* 469 */         par5 = d2;
/* 470 */         this.field_70121_D.func_72328_c(axisalignedbb1);
/*     */       } 
/*     */     } 
/*     */     
/* 474 */     this.field_70170_p.field_72984_F.func_76319_b();
/* 475 */     this.field_70170_p.field_72984_F.func_76320_a("rest");
/* 476 */     this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 477 */     this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V;
/* 478 */     this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/* 479 */     this.field_70123_F = (d6 != par1 || d8 != par5);
/* 480 */     this.field_70124_G = (d7 != par3);
/* 481 */     this.field_70122_E = (d7 != par3 && d7 < 0.0D);
/* 482 */     this.field_70132_H = (this.field_70123_F || this.field_70124_G);
/* 483 */     func_70064_a(par3, this.field_70122_E);
/*     */     
/* 485 */     if (d6 != par1)
/*     */     {
/* 487 */       this.field_70159_w = 0.0D;
/*     */     }
/*     */     
/* 490 */     if (d7 != par3)
/*     */     {
/* 492 */       this.field_70181_x = 0.0D;
/*     */     }
/*     */     
/* 495 */     if (d8 != par5)
/*     */     {
/* 497 */       this.field_70179_y = 0.0D;
/*     */     }
/*     */     
/* 500 */     double d12 = this.field_70165_t - d3;
/* 501 */     double d10 = this.field_70163_u - d4;
/* 502 */     double d11 = this.field_70161_v - d5;
/*     */ 
/*     */     
/*     */     try {
/* 506 */       doBlockCollisions();
/*     */     }
/* 508 */     catch (Throwable throwable) {
/*     */       
/* 510 */       CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision");
/* 511 */       CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/* 512 */       func_85029_a(crashreportcategory);
/* 513 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */     
/* 516 */     this.field_70170_p.field_72984_F.func_76319_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGroundAndDead() {
/* 521 */     this.field_70163_u += 0.5D;
/* 522 */     func_70043_V();
/* 523 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public void _updateRiderPosition() {
/* 527 */     if (this.field_70153_n != null) {
/*     */       
/* 529 */       double x = -Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.1D;
/* 530 */       double z = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.1D;
/* 531 */       this.field_70153_n.func_70107_b(this.field_70165_t + x, this.field_70163_u + func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + z);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_EntityHide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */