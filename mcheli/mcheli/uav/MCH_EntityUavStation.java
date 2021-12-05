/*     */ package mcheli.mcheli.uav;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Explosion;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.helicopter.MCH_ItemHeli;
/*     */ import mcheli.multiplay.MCH_Multiplay;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_ItemPlane;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_ItemTank;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_EntityContainer;
/*     */ import mcheli.wrapper.W_EntityPlayer;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityUavStation
/*     */   extends W_EntityContainer
/*     */ {
/*     */   protected static final int DATAWT_ID_KIND = 27;
/*     */   protected static final int DATAWT_ID_LAST_AC = 28;
/*     */   protected static final int DATAWT_ID_UAV_X = 29;
/*     */   protected static final int DATAWT_ID_UAV_Y = 30;
/*     */   protected static final int DATAWT_ID_UAV_Z = 31;
/*     */   protected Entity lastRiddenByEntity;
/*     */   public boolean isRequestedSyncStatus;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected double velocityX;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected double velocityY;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected double velocityZ;
/*     */   protected int aircraftPosRotInc;
/*     */   protected double aircraftX;
/*     */   protected double aircraftY;
/*     */   protected double aircraftZ;
/*     */   protected double aircraftYaw;
/*     */   protected double aircraftPitch;
/*     */   private MCH_EntityAircraft controlAircraft;
/*     */   private MCH_EntityAircraft lastControlAircraft;
/*     */   private String loadedLastControlAircraftGuid;
/*     */   public int posUavX;
/*     */   public int posUavY;
/*     */   public int posUavZ;
/*     */   public float rotCover;
/*     */   public float prevRotCover;
/*     */   
/*     */   public MCH_EntityUavStation(World world) {
/*  83 */     super(world);
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.dropContentsWhenDead = false;
/*     */     
/*  89 */     this.field_70156_m = true;
/*  90 */     func_70105_a(2.0F, 0.7F);
/*  91 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  92 */     this.field_70159_w = 0.0D;
/*  93 */     this.field_70181_x = 0.0D;
/*  94 */     this.field_70179_y = 0.0D;
/*  95 */     this.field_70158_ak = true;
/*     */     
/*  97 */     this.lastRiddenByEntity = null;
/*     */     
/*  99 */     this.aircraftPosRotInc = 0;
/* 100 */     this.aircraftX = 0.0D;
/* 101 */     this.aircraftY = 0.0D;
/* 102 */     this.aircraftZ = 0.0D;
/* 103 */     this.aircraftYaw = 0.0D;
/* 104 */     this.aircraftPitch = 0.0D;
/*     */     
/* 106 */     this.posUavX = 0;
/* 107 */     this.posUavY = 0;
/* 108 */     this.posUavZ = 0;
/*     */     
/* 110 */     this.rotCover = 0.0F;
/* 111 */     this.prevRotCover = 0.0F;
/*     */     
/* 113 */     setControlAircract(null);
/* 114 */     setLastControlAircraft(null);
/* 115 */     this.loadedLastControlAircraftGuid = "";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 120 */     super.func_70088_a();
/* 121 */     func_70096_w().func_75682_a(27, Byte.valueOf((byte)0));
/* 122 */     func_70096_w().func_75682_a(28, Integer.valueOf(0));
/* 123 */     func_70096_w().func_75682_a(29, Integer.valueOf(0));
/* 124 */     func_70096_w().func_75682_a(30, Integer.valueOf(0));
/* 125 */     func_70096_w().func_75682_a(31, Integer.valueOf(0));
/*     */     
/* 127 */     setOpen(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatus() {
/* 132 */     return func_70096_w().func_75683_a(27);
/*     */   }
/*     */   
/*     */   public void setStatus(int n) {
/* 136 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 138 */       MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityUavStation.setStatus(%d)", new Object[] { Integer.valueOf(n) });
/* 139 */       func_70096_w().func_75692_b(27, Byte.valueOf((byte)n));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKind() {
/* 146 */     return 0x7F & getStatus();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKind(int n) {
/* 151 */     setStatus(getStatus() & 0x80 | n);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 156 */     return ((getStatus() & 0x80) != 0);
/*     */   }
/*     */   
/*     */   public void setOpen(boolean b) {
/* 160 */     setStatus((b ? 128 : 0) | getStatus() & 0x7F);
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityAircraft getControlAircract() {
/* 165 */     return this.controlAircraft;
/*     */   }
/*     */   
/*     */   public void setControlAircract(MCH_EntityAircraft ac) {
/* 169 */     this.controlAircraft = ac;
/* 170 */     if (ac != null && !ac.field_70128_L)
/*     */     {
/* 172 */       setLastControlAircraft(ac);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUavPosition(int x, int y, int z) {
/* 179 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 181 */       this.posUavX = x;
/* 182 */       this.posUavY = y;
/* 183 */       this.posUavZ = z;
/* 184 */       func_70096_w().func_75692_b(29, Integer.valueOf(x));
/* 185 */       func_70096_w().func_75692_b(30, Integer.valueOf(y));
/* 186 */       func_70096_w().func_75692_b(31, Integer.valueOf(z));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateUavPosition() {
/* 193 */     this.posUavX = func_70096_w().func_75679_c(29);
/* 194 */     this.posUavY = func_70096_w().func_75679_c(30);
/* 195 */     this.posUavZ = func_70096_w().func_75679_c(31);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound nbt) {
/* 201 */     super.func_70014_b(nbt);
/* 202 */     nbt.func_74768_a("UavStatus", getStatus());
/* 203 */     nbt.func_74768_a("PosUavX", this.posUavX);
/* 204 */     nbt.func_74768_a("PosUavY", this.posUavY);
/* 205 */     nbt.func_74768_a("PosUavZ", this.posUavZ);
/* 206 */     String s = "";
/* 207 */     if (getLastControlAircraft() != null && !(getLastControlAircraft()).field_70128_L)
/*     */     {
/* 209 */       s = getLastControlAircraft().getCommonUniqueId();
/*     */     }
/* 211 */     if (s.isEmpty())
/*     */     {
/* 213 */       s = this.loadedLastControlAircraftGuid;
/*     */     }
/* 215 */     nbt.func_74778_a("LastCtrlAc", s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound nbt) {
/* 221 */     super.func_70037_a(nbt);
/* 222 */     setUavPosition(nbt.func_74762_e("PosUavX"), nbt.func_74762_e("PosUavY"), nbt.func_74762_e("PosUavZ"));
/*     */     
/* 224 */     if (nbt.func_74764_b("UavStatus")) {
/*     */       
/* 226 */       setStatus(nbt.func_74762_e("UavStatus"));
/*     */     }
/*     */     else {
/*     */       
/* 230 */       setKind(1);
/*     */     } 
/*     */     
/* 233 */     this.loadedLastControlAircraftGuid = nbt.func_74779_i("LastCtrlAc");
/*     */   }
/*     */ 
/*     */   
/*     */   public void initUavPostion() {
/* 238 */     int rt = (int)(MCH_Lib.getRotate360((this.field_70177_z + 45.0F)) / 90.0D);
/* 239 */     int D = 12;
/* 240 */     this.posUavX = (rt == 0 || rt == 3) ? 12 : -12;
/* 241 */     this.posUavZ = (rt == 0 || rt == 1) ? 12 : -12;
/* 242 */     this.posUavY = 2;
/* 243 */     setUavPosition(this.posUavX, this.posUavY, this.posUavZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 253 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damage) {
/* 258 */     if (func_85032_ar()) return false; 
/* 259 */     if (this.field_70128_L) return true; 
/* 260 */     if (this.field_70170_p.field_72995_K) return true; 
/* 261 */     String dmt = damageSource.func_76355_l();
/*     */     
/* 263 */     damage = MCH_Config.applyDamageByExternal((Entity)this, damageSource, damage);
/*     */     
/* 265 */     if (!MCH_Multiplay.canAttackEntity(damageSource, (Entity)this))
/*     */     {
/* 267 */       return false;
/*     */     }
/*     */     
/* 270 */     boolean isCreative = false;
/* 271 */     Entity entity = damageSource.func_76346_g();
/* 272 */     boolean isDamegeSourcePlayer = false;
/* 273 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 275 */       isCreative = ((EntityPlayer)entity).field_71075_bZ.field_75098_d;
/* 276 */       if (dmt.compareTo("player") == 0)
/*     */       {
/* 278 */         isDamegeSourcePlayer = true;
/*     */       }
/*     */       
/* 281 */       W_WorldFunc.MOD_playSoundAtEntity((Entity)this, "hit", 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 285 */       W_WorldFunc.MOD_playSoundAtEntity((Entity)this, "helidmg", 1.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */     } 
/*     */     
/* 288 */     func_70018_K();
/*     */     
/* 290 */     if (damage > 0.0F) {
/*     */       
/* 292 */       if (this.field_70153_n != null)
/*     */       {
/* 294 */         this.field_70153_n.func_70078_a((Entity)this);
/*     */       }
/*     */       
/* 297 */       this.dropContentsWhenDead = true;
/* 298 */       func_70106_y();
/*     */       
/* 300 */       if (!isDamegeSourcePlayer)
/*     */       {
/* 302 */         MCH_Explosion.newExplosion(this.field_70170_p, null, this.field_70153_n, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 0.0F, true, true, false, false, 0);
/*     */       }
/*     */ 
/*     */       
/* 306 */       if (!isCreative) {
/*     */         
/* 308 */         int kind = getKind();
/* 309 */         if (kind > 0)
/*     */         {
/* 311 */           dropItemWithOffset((Item)MCH_MOD.itemUavStation[kind - 1], 1, 0.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 316 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_70041_e_() {
/* 321 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/* 325 */     return par1Entity.field_70121_D;
/*     */   }
/*     */   public AxisAlignedBB func_70046_E() {
/* 328 */     return this.field_70121_D;
/*     */   }
/*     */   public boolean func_70104_M() {
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 336 */     if (getKind() == 2 && this.field_70153_n != null) {
/*     */       
/* 338 */       double px = -Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.9D;
/* 339 */       double pz = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.9D;
/* 340 */       int x = (int)(this.field_70165_t + px);
/* 341 */       int y = (int)(this.field_70163_u - 0.5D);
/* 342 */       int z = (int)(this.field_70161_v + pz);
/* 343 */       Block block = this.field_70170_p.func_147439_a(x, y, z);
/* 344 */       return block.func_149662_c() ? -0.4D : -0.9D;
/*     */     } 
/* 346 */     return 0.35D;
/*     */   }
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 350 */     return 2.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70067_L() {
/* 354 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70108_f(Entity par1Entity) {}
/*     */ 
/*     */   
/*     */   public void func_70024_g(double par1, double par3, double par5) {}
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5) {
/* 366 */     this.velocityX = this.field_70159_w = par1;
/* 367 */     this.velocityY = this.field_70181_x = par3;
/* 368 */     this.velocityZ = this.field_70179_y = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 377 */     super.func_70071_h_();
/*     */     
/* 379 */     this.prevRotCover = this.rotCover;
/*     */     
/* 381 */     if (isOpen()) {
/*     */       
/* 383 */       if (this.rotCover < 1.0F)
/*     */       {
/* 385 */         this.rotCover += 0.1F;
/*     */       }
/*     */       else
/*     */       {
/* 389 */         this.rotCover = 1.0F;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 394 */     else if (this.rotCover > 0.0F) {
/*     */       
/* 396 */       this.rotCover -= 0.1F;
/*     */     }
/*     */     else {
/*     */       
/* 400 */       this.rotCover = 0.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 405 */     if (this.field_70153_n == null) {
/*     */       
/* 407 */       if (this.lastRiddenByEntity != null)
/*     */       {
/* 409 */         unmountEntity(true);
/*     */       }
/* 411 */       setControlAircract(null);
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
/*     */     
/* 425 */     int uavStationKind = getKind();
/* 426 */     if (this.field_70173_aa < 30 && uavStationKind > 0)
/*     */     {
/* 428 */       if (uavStationKind != 1)
/*     */       {
/*     */ 
/*     */         
/* 432 */         if (uavStationKind == 2);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 438 */     if (this.field_70170_p.field_72995_K && !this.isRequestedSyncStatus)
/*     */     {
/* 440 */       this.isRequestedSyncStatus = true;
/*     */     }
/*     */ 
/*     */     
/* 444 */     this.field_70169_q = this.field_70165_t;
/* 445 */     this.field_70167_r = this.field_70163_u;
/* 446 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 448 */     if (getControlAircract() != null && (getControlAircract()).field_70128_L)
/*     */     {
/* 450 */       setControlAircract(null);
/*     */     }
/*     */     
/* 453 */     if (getLastControlAircraft() != null && (getLastControlAircraft()).field_70128_L)
/*     */     {
/* 455 */       setLastControlAircraft(null);
/*     */     }
/*     */ 
/*     */     
/* 459 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 461 */       onUpdate_Client();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 466 */       onUpdate_Server();
/*     */     } 
/*     */     
/* 469 */     this.lastRiddenByEntity = this.field_70153_n;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityAircraft getLastControlAircraft() {
/* 474 */     return this.lastControlAircraft;
/*     */   }
/*     */   
/*     */   public MCH_EntityAircraft getAndSearchLastControlAircraft() {
/* 478 */     if (getLastControlAircraft() == null) {
/*     */       
/* 480 */       int id = getLastControlAircraftEntityId().intValue();
/* 481 */       if (id > 0) {
/*     */         
/* 483 */         Entity entity = this.field_70170_p.func_73045_a(id);
/* 484 */         if (entity instanceof MCH_EntityAircraft) {
/*     */           
/* 486 */           MCH_EntityAircraft ac = (MCH_EntityAircraft)entity;
/* 487 */           if (ac.isUAV())
/*     */           {
/* 489 */             setLastControlAircraft(ac);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 494 */     return getLastControlAircraft();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastControlAircraft(MCH_EntityAircraft ac) {
/* 499 */     MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityUavStation.setLastControlAircraft:" + ac, new Object[0]);
/* 500 */     this.lastControlAircraft = ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getLastControlAircraftEntityId() {
/* 505 */     return Integer.valueOf(func_70096_w().func_75679_c(28));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastControlAircraftEntityId(int s) {
/* 510 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 512 */       func_70096_w().func_75692_b(28, Integer.valueOf(s));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void searchLastControlAircraft() {
/* 518 */     if (this.loadedLastControlAircraftGuid.isEmpty())
/*     */       return; 
/* 520 */     List<MCH_EntityAircraft> list = this.field_70170_p.func_72872_a(MCH_EntityAircraft.class, func_70046_E().func_72314_b(120.0D, 120.0D, 120.0D));
/*     */     
/* 522 */     if (list == null)
/*     */       return; 
/* 524 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 526 */       MCH_EntityAircraft ac = list.get(i);
/* 527 */       if (ac.getCommonUniqueId().equals(this.loadedLastControlAircraftGuid)) {
/*     */         
/* 529 */         String n = (ac.getAcInfo() != null) ? (ac.getAcInfo()).displayName : ("no info : " + ac);
/* 530 */         MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityUavStation.searchLastControlAircraft:found" + n, new Object[0]);
/* 531 */         setLastControlAircraft(ac);
/* 532 */         setLastControlAircraftEntityId(W_Entity.getEntityId((Entity)ac));
/* 533 */         this.loadedLastControlAircraftGuid = "";
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdate_Client() {
/* 543 */     if (this.aircraftPosRotInc > 0) {
/*     */ 
/*     */       
/* 546 */       double rpinc = this.aircraftPosRotInc;
/* 547 */       double yaw = MathHelper.func_76138_g(this.aircraftYaw - this.field_70177_z);
/* 548 */       this.field_70177_z = (float)(this.field_70177_z + yaw / rpinc);
/* 549 */       this.field_70125_A = (float)(this.field_70125_A + (this.aircraftPitch - this.field_70125_A) / rpinc);
/* 550 */       func_70107_b(this.field_70165_t + (this.aircraftX - this.field_70165_t) / rpinc, this.field_70163_u + (this.aircraftY - this.field_70163_u) / rpinc, this.field_70161_v + (this.aircraftZ - this.field_70161_v) / rpinc);
/*     */ 
/*     */       
/* 553 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/* 554 */       this.aircraftPosRotInc--;
/*     */     }
/*     */     else {
/*     */       
/* 558 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */ 
/*     */ 
/*     */       
/* 562 */       this.field_70181_x *= 0.96D;
/* 563 */       this.field_70159_w = 0.0D;
/* 564 */       this.field_70179_y = 0.0D;
/*     */     } 
/*     */     
/* 567 */     updateUavPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Server() {
/* 574 */     this.field_70181_x -= 0.03D;
/*     */     
/* 576 */     func_70091_d(0.0D, this.field_70181_x, 0.0D);
/*     */ 
/*     */     
/* 579 */     this.field_70181_x *= 0.96D;
/* 580 */     this.field_70159_w = 0.0D;
/* 581 */     this.field_70179_y = 0.0D;
/*     */     
/* 583 */     func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     
/* 585 */     if (this.field_70153_n != null)
/*     */     {
/* 587 */       if (this.field_70153_n.field_70128_L) {
/*     */         
/* 589 */         unmountEntity(true);
/* 590 */         this.field_70153_n = null;
/*     */       }
/*     */       else {
/*     */         
/* 594 */         ItemStack item = func_70301_a(0);
/* 595 */         if (item != null && item.field_77994_a > 0) {
/*     */           
/* 597 */           handleItem(this.field_70153_n, item);
/* 598 */           if (item.field_77994_a == 0)
/*     */           {
/* 600 */             func_70299_a(0, null);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 606 */     if (getLastControlAircraft() == null)
/*     */     {
/*     */       
/* 609 */       if (this.field_70173_aa % 40 == 0)
/*     */       {
/* 611 */         searchLastControlAircraft();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 618 */     this.aircraftPosRotInc = par9 + 8;
/*     */     
/* 620 */     this.aircraftX = par1;
/* 621 */     this.aircraftY = par3;
/* 622 */     this.aircraftZ = par5;
/* 623 */     this.aircraftYaw = par7;
/* 624 */     this.aircraftPitch = par8;
/* 625 */     this.field_70159_w = this.velocityX;
/* 626 */     this.field_70181_x = this.velocityY;
/* 627 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70043_V() {
/* 632 */     if (this.field_70153_n != null) {
/*     */       
/* 634 */       double x = -Math.sin(this.field_70177_z * Math.PI / 180.0D) * 0.9D;
/* 635 */       double z = Math.cos(this.field_70177_z * Math.PI / 180.0D) * 0.9D;
/* 636 */       this.field_70153_n.func_70107_b(this.field_70165_t + x, this.field_70163_u + func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void controlLastAircraft(Entity user) {
/* 645 */     if (getLastControlAircraft() != null && !(getLastControlAircraft()).field_70128_L) {
/*     */       
/* 647 */       getLastControlAircraft().setUavStation(this);
/* 648 */       setControlAircract(getLastControlAircraft());
/*     */ 
/*     */ 
/*     */       
/* 652 */       W_EntityPlayer.closeScreen(user);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleItem(Entity user, ItemStack itemStack) {
/*     */     MCH_EntityTank mCH_EntityTank;
/* 659 */     if (user == null || user.field_70128_L || itemStack == null || itemStack.field_77994_a != 1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 664 */     if (this.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 669 */     MCH_EntityAircraft ac = null;
/*     */     
/* 671 */     double x = this.field_70165_t + this.posUavX;
/* 672 */     double y = this.field_70163_u + this.posUavY;
/* 673 */     double z = this.field_70161_v + this.posUavZ;
/*     */     
/* 675 */     if (y <= 1.0D) y = 2.0D;
/*     */     
/* 677 */     Item item = itemStack.func_77973_b();
/* 678 */     if (item instanceof MCP_ItemPlane) {
/*     */       
/* 680 */       MCP_PlaneInfo pi = MCP_PlaneInfoManager.getFromItem(item);
/* 681 */       if (pi != null && pi.isUAV)
/*     */       {
/* 683 */         if (!pi.isSmallUAV && getKind() == 2) {
/*     */ 
/*     */           
/* 686 */           ac = null;
/*     */         }
/*     */         else {
/*     */           
/* 690 */           MCP_EntityPlane mCP_EntityPlane = ((MCP_ItemPlane)item).createAircraft(this.field_70170_p, x, y, z, itemStack);
/*     */         } 
/*     */       }
/*     */     } 
/* 694 */     if (item instanceof MCH_ItemHeli) {
/*     */       
/* 696 */       MCH_HeliInfo hi = MCH_HeliInfoManager.getFromItem(item);
/* 697 */       if (hi != null && hi.isUAV)
/*     */       {
/* 699 */         if (!hi.isSmallUAV && getKind() == 2) {
/*     */ 
/*     */           
/* 702 */           ac = null;
/*     */         }
/*     */         else {
/*     */           
/* 706 */           MCH_EntityHeli mCH_EntityHeli = ((MCH_ItemHeli)item).createAircraft(this.field_70170_p, x, y, z, itemStack);
/*     */         } 
/*     */       }
/*     */     } 
/* 710 */     if (item instanceof MCH_ItemTank) {
/*     */       
/* 712 */       MCH_TankInfo hi = MCH_TankInfoManager.getFromItem(item);
/* 713 */       if (hi != null && hi.isUAV)
/*     */       {
/* 715 */         if (!hi.isSmallUAV && getKind() == 2) {
/*     */ 
/*     */           
/* 718 */           ac = null;
/*     */         }
/*     */         else {
/*     */           
/* 722 */           mCH_EntityTank = ((MCH_ItemTank)item).createAircraft(this.field_70170_p, x, y, z, itemStack);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 728 */     if (mCH_EntityTank == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 734 */     ((MCH_EntityAircraft)mCH_EntityTank).field_70177_z = this.field_70177_z - 180.0F;
/* 735 */     ((MCH_EntityAircraft)mCH_EntityTank).field_70126_B = ((MCH_EntityAircraft)mCH_EntityTank).field_70177_z;
/*     */     
/* 737 */     user.field_70177_z = this.field_70177_z - 180.0F;
/*     */     
/* 739 */     if (this.field_70170_p.func_72945_a((Entity)mCH_EntityTank, ((MCH_EntityAircraft)mCH_EntityTank).field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
/*     */       
/* 741 */       itemStack.field_77994_a--;
/*     */       
/* 743 */       MCH_Lib.DbgLog(this.field_70170_p, "Create UAV: %s : %s", new Object[] { item.func_77658_a(), item });
/*     */       
/* 745 */       user.field_70177_z = this.field_70177_z - 180.0F;
/*     */       
/* 747 */       if (!mCH_EntityTank.isTargetDrone()) {
/*     */         
/* 749 */         mCH_EntityTank.setUavStation(this);
/* 750 */         setControlAircract((MCH_EntityAircraft)mCH_EntityTank);
/*     */       } 
/* 752 */       this.field_70170_p.func_72838_d((Entity)mCH_EntityTank);
/*     */       
/* 754 */       if (!mCH_EntityTank.isTargetDrone())
/*     */       {
/* 756 */         mCH_EntityTank.setFuel((int)(mCH_EntityTank.getMaxFuel() * 0.05F));
/* 757 */         W_EntityPlayer.closeScreen(user);
/*     */       }
/*     */       else
/*     */       {
/* 761 */         mCH_EntityTank.setFuel(mCH_EntityTank.getMaxFuel());
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 767 */       mCH_EntityTank.func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void _setInventorySlotContents(int par1, ItemStack itemStack) {
/* 773 */     func_70299_a(par1, itemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 782 */     int kind = getKind();
/*     */     
/* 784 */     if (kind <= 0) return false;
/*     */     
/* 786 */     if (this.field_70153_n != null)
/*     */     {
/* 788 */       return false;
/*     */     }
/*     */     
/* 791 */     if (kind == 2) {
/*     */       
/* 793 */       if (player.func_70093_af()) {
/*     */         
/* 795 */         setOpen(!isOpen());
/* 796 */         return false;
/*     */       } 
/*     */       
/* 799 */       if (!isOpen())
/*     */       {
/* 801 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 805 */     this.field_70153_n = null;
/* 806 */     this.lastRiddenByEntity = null;
/*     */     
/* 808 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 810 */       player.func_70078_a((Entity)this);
/* 811 */       player.openGui(MCH_MOD.instance, 0, player.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
/*     */     } 
/*     */ 
/*     */     
/* 815 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 821 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_70297_j_() {
/* 825 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unmountEntity(boolean unmountAllEntity) {
/* 831 */     Entity rByEntity = null;
/*     */     
/* 833 */     if (this.field_70153_n != null) {
/*     */       
/* 835 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 837 */         rByEntity = this.field_70153_n;
/* 838 */         this.field_70153_n.func_70078_a(null);
/*     */       }
/*     */     
/* 841 */     } else if (this.lastRiddenByEntity != null) {
/*     */       
/* 843 */       rByEntity = this.lastRiddenByEntity;
/*     */     } 
/*     */     
/* 846 */     if (getControlAircract() != null)
/*     */     {
/* 848 */       getControlAircract().setUavStation(null);
/*     */     }
/* 850 */     setControlAircract(null);
/*     */     
/* 852 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 854 */       W_EntityPlayer.closeScreen(rByEntity);
/*     */     }
/*     */     
/* 857 */     this.field_70153_n = null;
/* 858 */     this.lastRiddenByEntity = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_EntityUavStation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */