/*      */ package mcheli.mcheli.tank;
/*      */ 
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.List;
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.MCH_MOD;
/*      */ import mcheli.MCH_Math;
/*      */ import mcheli.aircraft.MCH_AircraftInfo;
/*      */ import mcheli.aircraft.MCH_BoundingBox;
/*      */ import mcheli.aircraft.MCH_EntityAircraft;
/*      */ import mcheli.aircraft.MCH_EntityHitBox;
/*      */ import mcheli.aircraft.MCH_EntitySeat;
/*      */ import mcheli.aircraft.MCH_PacketStatusRequest;
/*      */ import mcheli.aircraft.MCH_Parts;
/*      */ import mcheli.mcheli.aircraft.MCH_BoundingBox;
import mcheli.particles.MCH_ParticleParam;
/*      */ import mcheli.particles.MCH_ParticlesUtil;
/*      */ import mcheli.tank.MCH_TankInfo;
/*      */ import mcheli.tank.MCH_TankInfoManager;
/*      */ import mcheli.tank.MCH_WheelManager;
/*      */ import mcheli.weapon.MCH_WeaponSet;
/*      */ import mcheli.wrapper.W_Block;
/*      */ import mcheli.wrapper.W_Blocks;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_Lib;
/*      */ import mcheli.wrapper.W_WorldFunc;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
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
/*      */ public class MCH_EntityTank
/*      */   extends MCH_EntityAircraft
/*      */ {
/*      */   private MCH_TankInfo tankInfo;
/*      */   public float soundVolume;
/*      */   public float soundVolumeTarget;
/*      */   public float rotationRotor;
/*      */   public float prevRotationRotor;
/*      */   public float addkeyRotValue;
/*      */   public final MCH_WheelManager WheelMng;
/*      */   
/*      */   public MCH_EntityTank(World world) {
/*   71 */     super(world);
/*      */     
/*   73 */     this.tankInfo = null;
/*      */     
/*   75 */     this.currentSpeed = 0.07D;
/*   76 */     this.field_70156_m = true;
/*   77 */     func_70105_a(2.0F, 0.7F);
/*   78 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*   79 */     this.field_70159_w = 0.0D;
/*   80 */     this.field_70181_x = 0.0D;
/*   81 */     this.field_70179_y = 0.0D;
/*      */     
/*   83 */     this.weapons = createWeapon(0);
/*      */     
/*   85 */     this.soundVolume = 0.0F;
/*      */     
/*   87 */     this.field_70138_W = 0.6F;
/*      */     
/*   89 */     this.rotationRotor = 0.0F;
/*   90 */     this.prevRotationRotor = 0.0F;
/*      */     
/*   92 */     this.WheelMng = new MCH_WheelManager(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getKindName() {
/*   97 */     return "tanks";
/*      */   }
/*      */   
/*      */   public String getEntityType() {
/*  101 */     return "Vehicle";
/*      */   }
/*      */   
/*      */   public MCH_TankInfo getTankInfo() {
/*  105 */     return this.tankInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void changeType(String type) {
/*  110 */     if (!type.isEmpty())
/*      */     {
/*  112 */       this.tankInfo = MCH_TankInfoManager.get(type);
/*      */     }
/*  114 */     if (this.tankInfo == null) {
/*      */       
/*  116 */       MCH_Lib.Log((Entity)this, "##### MCH_EntityTank changeTankType() Tank info null %d, %s, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), type, getEntityName() });
/*      */       
/*  118 */       func_70106_y();
/*      */     }
/*      */     else {
/*      */       
/*  122 */       setAcInfo((MCH_AircraftInfo)this.tankInfo);
/*  123 */       newSeats(getAcInfo().getNumSeatAndRack());
/*      */       
/*  125 */       switchFreeLookModeClient((getAcInfo()).defaultFreelook);
/*      */       
/*  127 */       this.weapons = createWeapon(1 + getSeatNum());
/*      */       
/*  129 */       initPartRotation(getRotYaw(), getRotPitch());
/*      */       
/*  131 */       this.WheelMng.createWheels(this.field_70170_p, (getAcInfo()).wheels, Vec3.func_72443_a(0.0D, -0.35D, (getTankInfo()).weightedCenterZ));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getItem() {
/*  138 */     return (getTankInfo() != null) ? (Item)(getTankInfo()).item : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMountWithNearEmptyMinecart() {
/*  143 */     return MCH_Config.MountMinecartTank.prmBool;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  148 */     super.func_70088_a();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getGiveDamageRot() {
/*  153 */     return 91.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/*  159 */     super.func_70014_b(par1NBTTagCompound);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/*  165 */     super.func_70037_a(par1NBTTagCompound);
/*      */     
/*  167 */     if (this.tankInfo == null) {
/*      */       
/*  169 */       this.tankInfo = MCH_TankInfoManager.get(getTypeName());
/*  170 */       if (this.tankInfo == null) {
/*      */         
/*  172 */         MCH_Lib.Log((Entity)this, "##### MCH_EntityTank readEntityFromNBT() Tank info null %d, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), getEntityName() });
/*      */         
/*  174 */         func_70106_y();
/*      */       }
/*      */       else {
/*      */         
/*  178 */         setAcInfo((MCH_AircraftInfo)this.tankInfo);
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
/*      */   public void func_70106_y() {
/*  191 */     super.func_70106_y();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onInteractFirst(EntityPlayer player) {
/*  196 */     this.addkeyRotValue = 0.0F;
/*      */     
/*  198 */     player.field_70759_as = player.field_70758_at = getLastRiderYaw();
/*  199 */     player.field_70126_B = player.field_70177_z = getLastRiderYaw();
/*  200 */     player.field_70125_A = getLastRiderPitch();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSwitchGunnerMode() {
/*  209 */     if (!super.canSwitchGunnerMode()) return false;
/*      */     
/*  211 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdateAircraft() {
/*  219 */     if (this.tankInfo == null) {
/*      */       
/*  221 */       changeType(getTypeName());
/*  222 */       this.field_70169_q = this.field_70165_t;
/*  223 */       this.field_70167_r = this.field_70163_u;
/*  224 */       this.field_70166_s = this.field_70161_v;
/*      */       
/*      */       return;
/*      */     } 
/*  228 */     if (!this.isRequestedSyncStatus) {
/*      */       
/*  230 */       this.isRequestedSyncStatus = true;
/*  231 */       if (this.field_70170_p.field_72995_K)
/*      */       {
/*  233 */         MCH_PacketStatusRequest.requestStatus(this);
/*      */       }
/*      */     } 
/*      */     
/*  237 */     if (this.lastRiddenByEntity == null && getRiddenByEntity() != null)
/*      */     {
/*  239 */       initCurrentWeapon(getRiddenByEntity());
/*      */     }
/*      */     
/*  242 */     updateWeapons();
/*  243 */     onUpdate_Seats();
/*  244 */     onUpdate_Control();
/*      */     
/*  246 */     this.prevRotationRotor = this.rotationRotor;
/*  247 */     this.rotationRotor = (float)(this.rotationRotor + getCurrentThrottle() * (getAcInfo()).rotorSpeed);
/*  248 */     if (this.rotationRotor > 360.0F) {
/*      */       
/*  250 */       this.rotationRotor -= 360.0F;
/*  251 */       this.prevRotationRotor -= 360.0F;
/*      */     } 
/*  253 */     if (this.rotationRotor < 0.0F) {
/*      */       
/*  255 */       this.rotationRotor += 360.0F;
/*  256 */       this.prevRotationRotor += 360.0F;
/*      */     } 
/*      */     
/*  259 */     this.field_70169_q = this.field_70165_t;
/*  260 */     this.field_70167_r = this.field_70163_u;
/*  261 */     this.field_70166_s = this.field_70161_v;
/*      */     
/*  263 */     if (isDestroyed())
/*      */     {
/*  265 */       if (getCurrentThrottle() > 0.0D) {
/*      */         
/*  267 */         if (MCH_Lib.getBlockIdY((Entity)this, 3, -2) > 0)
/*      */         {
/*  269 */           setCurrentThrottle(getCurrentThrottle() * 0.8D);
/*      */         }
/*  271 */         if (isExploded())
/*      */         {
/*  273 */           setCurrentThrottle(getCurrentThrottle() * 0.98D);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  280 */     updateCameraViewers();
/*      */ 
/*      */     
/*  283 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  285 */       onUpdate_Client();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  290 */       onUpdate_Server();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean func_90999_ad() {
/*  297 */     return (isDestroyed() || super.func_90999_ad());
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateExtraBoundingBox() {
/*  302 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  304 */       super.updateExtraBoundingBox();
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  309 */     else if (getCountOnUpdate() <= 1) {
/*      */       
/*  311 */       super.updateExtraBoundingBox();
/*  312 */       super.updateExtraBoundingBox();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double calculateXOffset(List<AxisAlignedBB> list, AxisAlignedBB bb, double parX) {
/*  319 */     for (int i = 0; i < list.size(); i++)
/*      */     {
/*  321 */       parX = ((AxisAlignedBB)list.get(i)).func_72316_a(bb, parX);
/*      */     }
/*  323 */     bb.func_72317_d(parX, 0.0D, 0.0D);
/*      */     
/*  325 */     return parX;
/*      */   }
/*      */   
/*      */   public double calculateYOffset(List<AxisAlignedBB> list, AxisAlignedBB bb, double parY) {
/*  329 */     for (int i = 0; i < list.size(); i++)
/*      */     {
/*  331 */       parY = ((AxisAlignedBB)list.get(i)).func_72323_b(bb, parY);
/*      */     }
/*  333 */     bb.func_72317_d(0.0D, parY, 0.0D);
/*      */     
/*  335 */     return parY;
/*      */   }
/*      */   
/*      */   public double calculateZOffset(List<AxisAlignedBB> list, AxisAlignedBB bb, double parZ) {
/*  339 */     for (int i = 0; i < list.size(); i++)
/*      */     {
/*  341 */       parZ = ((AxisAlignedBB)list.get(i)).func_72322_c(bb, parZ);
/*      */     }
/*  343 */     bb.func_72317_d(0.0D, 0.0D, parZ);
/*      */     
/*  345 */     return parZ;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70091_d(double parX, double parY, double parZ) {
/*  350 */     this.field_70170_p.field_72984_F.func_76320_a("move");
/*  351 */     this.field_70139_V *= 0.4F;
/*  352 */     double nowPosX = this.field_70165_t;
/*  353 */     double nowPosY = this.field_70163_u;
/*  354 */     double nowPosZ = this.field_70161_v;
/*      */     
/*  356 */     double mx = parX;
/*  357 */     double my = parY;
/*  358 */     double mz = parZ;
/*  359 */     AxisAlignedBB backUpAxisalignedBB = this.field_70121_D.func_72329_c();
/*      */     
/*  361 */     List list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(parX, parY, parZ));
/*      */     
/*  363 */     parY = calculateYOffset(list, this.field_70121_D, parY);
/*      */     
/*  365 */     boolean flag1 = (this.field_70122_E || (my != parY && my < 0.0D));
/*      */     
/*  367 */     for (MCH_BoundingBox ebb : this.extraBoundingBox)
/*      */     {
/*  369 */       ebb.updatePosition(this.field_70165_t, this.field_70163_u, this.field_70161_v, getRotYaw(), getRotPitch(), getRotRoll());
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
/*      */     
/*  384 */     parX = calculateXOffset(list, this.field_70121_D, parX);
/*  385 */     parZ = calculateZOffset(list, this.field_70121_D, parZ);
/*      */     
/*  387 */     if (this.field_70138_W > 0.0F && flag1 && this.field_70139_V < 0.05F && (mx != parX || mz != parZ)) {
/*      */       
/*  389 */       double bkParX = parX;
/*  390 */       double bkParY = parY;
/*  391 */       double bkParZ = parZ;
/*  392 */       parX = mx;
/*  393 */       parY = this.field_70138_W;
/*  394 */       parZ = mz;
/*  395 */       AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
/*  396 */       this.field_70121_D.func_72328_c(backUpAxisalignedBB);
/*  397 */       list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(mx, parY, mz));
/*      */       
/*  399 */       parY = calculateYOffset(list, this.field_70121_D, parY);
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
/*      */ 
/*      */ 
/*      */       
/*  418 */       parX = calculateXOffset(list, this.field_70121_D, parX);
/*  419 */       parZ = calculateZOffset(list, this.field_70121_D, parZ);
/*      */       
/*  421 */       parY = calculateYOffset(list, this.field_70121_D, -this.field_70138_W);
/*      */       
/*  423 */       if (bkParX * bkParX + bkParZ * bkParZ >= parX * parX + parZ * parZ) {
/*      */         
/*  425 */         parX = bkParX;
/*  426 */         parY = bkParY;
/*  427 */         parZ = bkParZ;
/*  428 */         this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */       } 
/*      */     } 
/*      */     
/*  432 */     double prevPX = this.field_70165_t;
/*  433 */     double prevPZ = this.field_70161_v;
/*      */     
/*  435 */     this.field_70170_p.field_72984_F.func_76319_b();
/*  436 */     this.field_70170_p.field_72984_F.func_76320_a("rest");
/*      */     
/*  438 */     double minX = this.field_70121_D.field_72340_a;
/*  439 */     double minZ = this.field_70121_D.field_72339_c;
/*  440 */     double maxX = this.field_70121_D.field_72336_d;
/*  441 */     double maxZ = this.field_70121_D.field_72334_f;
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
/*  452 */     this.field_70165_t = (minX + maxX) / 2.0D;
/*  453 */     this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V;
/*  454 */     this.field_70161_v = (minZ + maxZ) / 2.0D;
/*  455 */     this.field_70123_F = (mx != parX || mz != parZ);
/*  456 */     this.field_70124_G = (my != parY);
/*  457 */     this.field_70122_E = (my != parY && my < 0.0D);
/*  458 */     this.field_70132_H = (this.field_70123_F || this.field_70124_G);
/*  459 */     func_70064_a(parY, this.field_70122_E);
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
/*  472 */     if (mx != parX) this.field_70159_w = 0.0D; 
/*  473 */     if (my != parY) this.field_70181_x = 0.0D; 
/*  474 */     if (mz != parZ) this.field_70179_y = 0.0D;
/*      */ 
/*      */     
/*      */     try {
/*  478 */       doBlockCollisions();
/*      */     }
/*  480 */     catch (Throwable throwable) {
/*      */       
/*  482 */       CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision");
/*  483 */       CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/*  484 */       func_85029_a(crashreportcategory);
/*      */     } 
/*      */ 
/*      */     
/*  488 */     this.field_70170_p.field_72984_F.func_76319_b();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void rotationByKey(float partialTicks) {
/*  494 */     float rot = 0.2F;
/*      */     
/*  496 */     if (this.moveLeft && !this.moveRight)
/*      */     {
/*  498 */       this.addkeyRotValue -= rot * partialTicks;
/*      */     }
/*  500 */     if (this.moveRight && !this.moveLeft)
/*      */     {
/*  502 */       this.addkeyRotValue += rot * partialTicks;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdateAngles(float partialTicks) {
/*  508 */     if (isDestroyed())
/*      */       return; 
/*  510 */     if (this.isGunnerMode) {
/*      */       
/*  512 */       setRotPitch(getRotPitch() * 0.95F);
/*  513 */       setRotYaw(getRotYaw() + (getAcInfo()).autoPilotRot * 0.2F);
/*  514 */       if (MathHelper.func_76135_e(getRotRoll()) > 20.0F)
/*      */       {
/*  516 */         setRotRoll(getRotRoll() * 0.95F);
/*      */       }
/*      */     } 
/*      */     
/*  520 */     updateRecoil(partialTicks);
/*  521 */     setRotPitch(getRotPitch() + (this.WheelMng.targetPitch - getRotPitch()) * partialTicks);
/*  522 */     setRotRoll(getRotRoll() + (this.WheelMng.targetRoll - getRotRoll()) * partialTicks);
/*      */ 
/*      */     
/*  525 */     boolean isFly = (MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0);
/*  526 */     if (!isFly || ((getAcInfo()).isFloat && getWaterDepth() > 0.0D)) {
/*      */       
/*  528 */       float gmy = 1.0F;
/*  529 */       if (!isFly) {
/*      */         
/*  531 */         gmy = (getAcInfo()).mobilityYawOnGround;
/*      */ 
/*      */         
/*  534 */         if (!(getAcInfo()).canRotOnGround) {
/*      */           
/*  536 */           Block block = MCH_Lib.getBlockY((Entity)this, 3, -2, false);
/*  537 */           if (!W_Block.isEqual(block, W_Block.getWater()) && !W_Block.isEqual(block, W_Blocks.field_150350_a))
/*      */           {
/*      */             
/*  540 */             gmy = 0.0F;
/*      */           }
/*      */         } 
/*      */       } 
/*  544 */       float pivotTurnThrottle = (getAcInfo()).pivotTurnThrottle;
/*  545 */       double dx = this.field_70165_t - this.field_70169_q;
/*  546 */       double dz = this.field_70161_v - this.field_70166_s;
/*  547 */       double dist = dx * dx + dz * dz;
/*      */       
/*  549 */       if (pivotTurnThrottle <= 0.0F || getCurrentThrottle() >= pivotTurnThrottle || this.throttleBack >= pivotTurnThrottle / 10.0F || dist > this.throttleBack * 0.01D) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  554 */         float sf = (float)Math.sqrt((dist <= 1.0D) ? dist : 1.0D);
/*  555 */         if (pivotTurnThrottle <= 0.0F)
/*      */         {
/*  557 */           sf = 1.0F;
/*      */         }
/*      */         
/*  560 */         float flag = (!this.throttleUp && this.throttleDown && getCurrentThrottle() < pivotTurnThrottle + 0.05D) ? -1.0F : 1.0F;
/*      */         
/*  562 */         if (this.moveLeft && !this.moveRight)
/*      */         {
/*  564 */           setRotYaw(getRotYaw() - 0.6F * gmy * partialTicks * flag * sf);
/*      */         }
/*  566 */         if (this.moveRight && !this.moveLeft)
/*      */         {
/*  568 */           setRotYaw(getRotYaw() + 0.6F * gmy * partialTicks * flag * sf);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  573 */     this.addkeyRotValue = (float)(this.addkeyRotValue * (1.0D - (0.1F * partialTicks)));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_Control() {
/*  578 */     if (this.isGunnerMode && !canUseFuel())
/*      */     {
/*  580 */       switchGunnerMode(false);
/*      */     }
/*      */     
/*  583 */     this.throttleBack = (float)(this.throttleBack * 0.8D);
/*      */     
/*  585 */     if (getBrake()) {
/*      */       
/*  587 */       this.throttleBack = (float)(this.throttleBack * 0.5D);
/*  588 */       if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.02D * (getAcInfo()).throttleUpDown); }
/*  589 */       else { setCurrentThrottle(0.0D); }
/*      */     
/*      */     } 
/*  592 */     if (getRiddenByEntity() != null && !(getRiddenByEntity()).field_70128_L && isCanopyClose() && canUseFuel() && !isDestroyed())
/*      */     
/*      */     { 
/*  595 */       onUpdate_ControlSub(); }
/*      */     
/*  597 */     else if (isTargetDrone() && canUseFuel() && !isDestroyed())
/*      */     
/*  599 */     { this.throttleUp = true;
/*  600 */       onUpdate_ControlSub();
/*      */       
/*      */        }
/*      */     
/*  604 */     else if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.0025D * (getAcInfo()).throttleUpDown); }
/*  605 */     else { setCurrentThrottle(0.0D); }
/*      */     
/*  607 */     if (getCurrentThrottle() < 0.0D) setCurrentThrottle(0.0D);
/*      */ 
/*      */     
/*  610 */     if (this.field_70170_p.field_72995_K) {
/*      */ 
/*      */       
/*  613 */       if (!W_Lib.isClientPlayer(getRiddenByEntity()) || getCountOnUpdate() % 200 == 0)
/*      */       {
/*  615 */         double ct = getThrottle();
/*      */         
/*  617 */         if (getCurrentThrottle() > ct) addCurrentThrottle(-0.005D); 
/*  618 */         if (getCurrentThrottle() < ct) addCurrentThrottle(0.005D);
/*      */       
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  624 */       setThrottle(getCurrentThrottle());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_ControlSub() {
/*  631 */     if (!this.isGunnerMode) {
/*      */       
/*  633 */       float throttleUpDown = (getAcInfo()).throttleUpDown;
/*      */       
/*  635 */       if (this.throttleUp) {
/*      */         
/*  637 */         float f = throttleUpDown;
/*  638 */         if (getRidingEntity() != null) {
/*      */           
/*  640 */           double mx = (getRidingEntity()).field_70159_w;
/*  641 */           double mz = (getRidingEntity()).field_70179_y;
/*  642 */           f *= MathHelper.func_76133_a(mx * mx + mz * mz) * (getAcInfo()).throttleUpDownOnEntity;
/*      */         } 
/*      */         
/*  645 */         if ((getAcInfo()).enableBack && this.throttleBack > 0.0F) {
/*      */           
/*  647 */           this.throttleBack = (float)(this.throttleBack - 0.01D * f);
/*      */         }
/*      */         else {
/*      */           
/*  651 */           this.throttleBack = 0.0F;
/*  652 */           if (getCurrentThrottle() < 1.0D) { addCurrentThrottle(0.01D * f); }
/*  653 */           else { setCurrentThrottle(1.0D); }
/*      */         
/*      */         } 
/*  656 */       } else if (this.throttleDown) {
/*      */         
/*  658 */         if (getCurrentThrottle() > 0.0D)
/*      */         {
/*  660 */           addCurrentThrottle(-0.01D * throttleUpDown);
/*      */         }
/*      */         else
/*      */         {
/*  664 */           setCurrentThrottle(0.0D);
/*      */           
/*  666 */           if ((getAcInfo()).enableBack)
/*      */           {
/*  668 */             this.throttleBack = (float)(this.throttleBack + 0.0025D * throttleUpDown);
/*  669 */             if (this.throttleBack > 0.6F)
/*      */             {
/*  671 */               this.throttleBack = 0.6F;
/*      */             
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  679 */       else if (this.cs_tankAutoThrottleDown) {
/*      */         
/*  681 */         if (getCurrentThrottle() > 0.0D) {
/*      */           
/*  683 */           addCurrentThrottle(-0.005D * throttleUpDown);
/*  684 */           if (getCurrentThrottle() <= 0.0D)
/*      */           {
/*  686 */             setCurrentThrottle(0.0D);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Particle2() {
/*  698 */     if (!this.field_70170_p.field_72995_K)
/*  699 */       return;  if (getHP() >= getMaxHP() * 0.5D)
/*      */       return; 
/*  701 */     if (getTankInfo() == null)
/*  702 */       return;  int bbNum = (getTankInfo()).extraBoundingBox.size();
/*  703 */     if (bbNum < 0) bbNum = 0;
/*      */     
/*  705 */     if (this.isFirstDamageSmoke || this.prevDamageSmokePos.length != bbNum + 1)
/*      */     {
/*      */       
/*  708 */       this.prevDamageSmokePos = new Vec3[bbNum + 1];
/*      */     }
/*      */     
/*  711 */     float yaw = getRotYaw();
/*  712 */     float pitch = getRotPitch();
/*  713 */     float roll = getRotRoll();
/*      */     
/*  715 */     for (int ri = 0; ri < bbNum; ri++) {
/*      */       
/*  717 */       if (getHP() >= getMaxHP() * 0.2D && getMaxHP() > 0) {
/*      */         
/*  719 */         int d = (int)((getHP() / getMaxHP() - 0.2D) / 0.3D * 15.0D);
/*  720 */         if (d > 0 && this.field_70146_Z.nextInt(d) > 0) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */       
/*  725 */       MCH_BoundingBox bb = (getTankInfo()).extraBoundingBox.get(ri);
/*  726 */       Vec3 pos = getTransformedPosition(bb.offsetX, bb.offsetY, bb.offsetZ);
/*  727 */       double x = pos.field_72450_a;
/*  728 */       double y = pos.field_72448_b;
/*  729 */       double z = pos.field_72449_c;
/*      */       
/*  731 */       onUpdate_Particle2SpawnSmoke(ri, x, y, z, 1.0F);
/*      */       continue;
/*      */     } 
/*  734 */     boolean b = true;
/*  735 */     if (getHP() >= getMaxHP() * 0.2D && getMaxHP() > 0) {
/*      */       
/*  737 */       int d = (int)((getHP() / getMaxHP() - 0.2D) / 0.3D * 15.0D);
/*  738 */       if (d > 0 && this.field_70146_Z.nextInt(d) > 0)
/*      */       {
/*  740 */         b = false;
/*      */       }
/*      */     } 
/*  743 */     if (b) {
/*      */       
/*  745 */       double px = this.field_70165_t;
/*  746 */       double py = this.field_70163_u;
/*  747 */       double pz = this.field_70161_v;
/*  748 */       if (getSeatInfo(0) != null && (getSeatInfo(0)).pos != null) {
/*      */         
/*  750 */         Vec3 pos = MCH_Lib.RotVec3(0.0D, (getSeatInfo(0)).pos.field_72448_b, -2.0D, -yaw, -pitch, -roll);
/*  751 */         px += pos.field_72450_a;
/*  752 */         py += pos.field_72448_b;
/*  753 */         pz += pos.field_72449_c;
/*      */       } 
/*  755 */       onUpdate_Particle2SpawnSmoke(bbNum, px, py, pz, (bbNum == 0) ? 2.0F : 1.0F);
/*      */     } 
/*      */     
/*  758 */     this.isFirstDamageSmoke = false;
/*      */   }
/*      */   
/*      */   public void onUpdate_Particle2SpawnSmoke(int ri, double x, double y, double z, float size) {
/*  762 */     if (this.isFirstDamageSmoke || this.prevDamageSmokePos[ri] == null)
/*      */     {
/*  764 */       this.prevDamageSmokePos[ri] = Vec3.func_72443_a(x, y, z);
/*      */     }
/*  766 */     Vec3 prev = this.prevDamageSmokePos[ri];
/*      */ 
/*      */ 
/*      */     
/*  770 */     double dx = x - prev.field_72450_a;
/*  771 */     double dy = y - prev.field_72448_b;
/*  772 */     double dz = z - prev.field_72449_c;
/*  773 */     int num = 1;
/*  774 */     for (int i = 0; i < num; i++) {
/*      */       
/*  776 */       float c = 0.2F + this.field_70146_Z.nextFloat() * 0.3F;
/*      */       
/*  778 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", x, y, z);
/*      */ 
/*      */ 
/*      */       
/*  782 */       prm.motionX = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  783 */       prm.motionY = size * this.field_70146_Z.nextDouble() * 0.1D;
/*  784 */       prm.motionZ = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  785 */       prm.size = size * (this.field_70146_Z.nextInt(5) + 5.0F) * 1.0F;
/*  786 */       prm.setColor(0.7F + this.field_70146_Z.nextFloat() * 0.1F, c, c, c);
/*  787 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */     
/*  790 */     (this.prevDamageSmokePos[ri]).field_72450_a = x;
/*  791 */     (this.prevDamageSmokePos[ri]).field_72448_b = y;
/*  792 */     (this.prevDamageSmokePos[ri]).field_72449_c = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdate_Particle2SpawnSmode(int ri, double x, double y, double z, float size) {
/*  797 */     if (this.isFirstDamageSmoke)
/*      */     {
/*  799 */       this.prevDamageSmokePos[ri] = Vec3.func_72443_a(x, y, z);
/*      */     }
/*  801 */     Vec3 prev = this.prevDamageSmokePos[ri];
/*      */ 
/*      */ 
/*      */     
/*  805 */     double dx = x - prev.field_72450_a;
/*  806 */     double dy = y - prev.field_72448_b;
/*  807 */     double dz = z - prev.field_72449_c;
/*  808 */     int num = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) / 0.3D) + 1;
/*  809 */     for (int i = 0; i < num; i++) {
/*      */       
/*  811 */       float c = 0.2F + this.field_70146_Z.nextFloat() * 0.3F;
/*      */       
/*  813 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", x, y, z);
/*      */ 
/*      */ 
/*      */       
/*  817 */       prm.motionX = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  818 */       prm.motionY = size * this.field_70146_Z.nextDouble() * 0.1D;
/*  819 */       prm.motionZ = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  820 */       prm.size = size * (this.field_70146_Z.nextInt(5) + 5.0F) * 1.0F;
/*  821 */       prm.setColor(0.7F + this.field_70146_Z.nextFloat() * 0.1F, c, c, c);
/*  822 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */     
/*  825 */     (this.prevDamageSmokePos[ri]).field_72450_a = x;
/*  826 */     (this.prevDamageSmokePos[ri]).field_72448_b = y;
/*  827 */     (this.prevDamageSmokePos[ri]).field_72449_c = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdate_ParticleLandingGear() {
/*  832 */     this.WheelMng.particleLandingGear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void onUpdate_ParticleSplash() {
/*  838 */     if (getAcInfo() == null)
/*  839 */       return;  if (!this.field_70170_p.field_72995_K)
/*      */       return; 
/*  841 */     double mx = this.field_70165_t - this.field_70169_q;
/*  842 */     double mz = this.field_70161_v - this.field_70166_s;
/*  843 */     double dist = mx * mx + mz * mz;
/*  844 */     if (dist > 1.0D) dist = 1.0D; 
/*  845 */     for (MCH_AircraftInfo.ParticleSplash p : (getAcInfo()).particleSplashs) {
/*      */       
/*  847 */       for (int i = 0; i < p.num; i++) {
/*      */         
/*  849 */         if (dist > 0.03D + this.field_70146_Z.nextFloat() * 0.1D)
/*      */         {
/*  851 */           setParticleSplash(p.pos, -mx * p.acceleration, p.motionY, -mz * p.acceleration, p.gravity, p.size * (0.5D + dist * 0.5D), p.age);
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
/*      */   
/*      */   private void setParticleSplash(Vec3 pos, double mx, double my, double mz, float gravity, double size, int age) {
/*  866 */     Vec3 v = getTransformedPosition(pos);
/*  867 */     v = v.func_72441_c(this.field_70146_Z.nextDouble() - 0.5D, (this.field_70146_Z.nextDouble() - 0.5D) * 0.5D, this.field_70146_Z.nextDouble() - 0.5D);
/*  868 */     int x = (int)(v.field_72450_a + 0.5D);
/*  869 */     int y = (int)(v.field_72448_b + 0.0D);
/*  870 */     int z = (int)(v.field_72449_c + 0.5D);
/*  871 */     if (W_WorldFunc.isBlockWater(this.field_70170_p, x, y, z)) {
/*      */       
/*  873 */       float c = this.field_70146_Z.nextFloat() * 0.3F + 0.7F;
/*      */       
/*  875 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", v.field_72450_a, v.field_72448_b, v.field_72449_c);
/*  876 */       prm.motionX = mx + (this.field_70146_Z.nextFloat() - 0.5D) * 0.7D;
/*  877 */       prm.motionY = my;
/*  878 */       prm.motionZ = mz + (this.field_70146_Z.nextFloat() - 0.5D) * 0.7D;
/*  879 */       prm.size = (float)size * (this.field_70146_Z.nextFloat() * 0.2F + 0.8F);
/*  880 */       prm.setColor(0.9F, c, c, c);
/*  881 */       prm.age = age + (int)(this.field_70146_Z.nextFloat() * 0.5D * age);
/*  882 */       prm.gravity = gravity;
/*      */       
/*  884 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyAircraft() {
/*  891 */     super.destroyAircraft();
/*  892 */     this.rotDestroyedPitch = 0.0F;
/*  893 */     this.rotDestroyedRoll = 0.0F;
/*  894 */     this.rotDestroyedYaw = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getClientPositionDelayCorrection() {
/*  899 */     return (getTankInfo() == null) ? 7 : (((getTankInfo()).weightType == 1) ? 2 : 7);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Client() {
/*  905 */     if (getRiddenByEntity() != null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  910 */       if (W_Lib.isClientPlayer(getRiddenByEntity()))
/*      */       {
/*  912 */         (getRiddenByEntity()).field_70125_A = (getRiddenByEntity()).field_70127_C;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  917 */     if (this.aircraftPosRotInc > 0) {
/*      */       
/*  919 */       applyServerPositionAndRotation();
/*      */     }
/*      */     else {
/*      */       
/*  923 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*      */ 
/*      */ 
/*      */       
/*  927 */       if (!isDestroyed() && (this.field_70122_E || MCH_Lib.getBlockIdY((Entity)this, 1, -2) > 0)) {
/*      */         
/*  929 */         this.field_70159_w *= 0.95D;
/*  930 */         this.field_70179_y *= 0.95D;
/*  931 */         applyOnGroundPitch(0.95F);
/*      */       } 
/*      */       
/*  934 */       if (func_70090_H()) {
/*      */         
/*  936 */         this.field_70159_w *= 0.99D;
/*  937 */         this.field_70179_y *= 0.99D;
/*      */       } 
/*      */     } 
/*      */     
/*  941 */     updateWheels();
/*      */     
/*  943 */     onUpdate_Particle2();
/*      */     
/*  945 */     updateSound();
/*      */     
/*  947 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  949 */       onUpdate_ParticleLandingGear();
/*  950 */       onUpdate_ParticleSplash();
/*  951 */       onUpdate_ParticleSandCloud(true);
/*      */     } 
/*      */     
/*  954 */     updateCamera(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyOnGroundPitch(float factor) {}
/*      */ 
/*      */ 
/*      */   
/*      */   private void onUpdate_Server() {
/*  964 */     Entity rdnEnt = getRiddenByEntity();
/*  965 */     double prevMotion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */     
/*  967 */     double dp = 0.0D;
/*      */     
/*  969 */     if (canFloatWater())
/*      */     {
/*  971 */       dp = getWaterDepth();
/*      */     }
/*      */     
/*  974 */     boolean levelOff = this.isGunnerMode;
/*  975 */     if (dp == 0.0D) {
/*      */       
/*  977 */       if (!levelOff)
/*      */       {
/*      */         
/*  980 */         this.field_70181_x += 0.04D + (!func_70090_H() ? (getAcInfo()).gravity : (getAcInfo()).gravityInWater);
/*  981 */         this.field_70181_x += -0.047D * (1.0D - getCurrentThrottle());
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  986 */         this.field_70181_x *= 0.8D;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  993 */       if (MathHelper.func_76135_e(getRotRoll()) < 40.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  998 */       if (dp < 1.0D) {
/*      */         
/* 1000 */         this.field_70181_x -= 1.0E-4D;
/* 1001 */         this.field_70181_x += 0.007D * getCurrentThrottle();
/*      */       }
/*      */       else {
/*      */         
/* 1005 */         if (this.field_70181_x < 0.0D)
/*      */         {
/* 1007 */           this.field_70181_x /= 2.0D;
/*      */         }
/* 1009 */         this.field_70181_x += 0.007D;
/*      */       } 
/*      */     } 
/*      */     
/* 1013 */     float throttle = (float)(getCurrentThrottle() / 10.0D);
/*      */     
/* 1015 */     Vec3 v = MCH_Lib.Rot2Vec3(getRotYaw(), getRotPitch() - 10.0F);
/*      */     
/* 1017 */     if (!levelOff)
/*      */     {
/* 1019 */       this.field_70181_x += v.field_72448_b * throttle / 8.0D;
/*      */     }
/*      */     
/* 1022 */     boolean canMove = true;
/*      */     
/* 1024 */     if (!(getAcInfo()).canMoveOnGround) {
/*      */       
/* 1026 */       Block block = MCH_Lib.getBlockY((Entity)this, 3, -2, false);
/* 1027 */       if (!W_Block.isEqual(block, W_Block.getWater()) && !W_Block.isEqual(block, W_Blocks.field_150350_a))
/*      */       {
/* 1029 */         canMove = false;
/*      */       }
/*      */     } 
/*      */     
/* 1033 */     if (canMove)
/*      */     {
/* 1035 */       if ((getAcInfo()).enableBack && this.throttleBack > 0.0F) {
/*      */         
/* 1037 */         this.field_70159_w -= v.field_72450_a * this.throttleBack;
/* 1038 */         this.field_70179_y -= v.field_72449_c * this.throttleBack;
/*      */       }
/*      */       else {
/*      */         
/* 1042 */         this.field_70159_w += v.field_72450_a * throttle;
/* 1043 */         this.field_70179_y += v.field_72449_c * throttle;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1048 */     double motion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */ 
/*      */     
/* 1051 */     float speedLimit = getMaxSpeed();
/* 1052 */     if (motion > speedLimit) {
/*      */       
/* 1054 */       this.field_70159_w *= speedLimit / motion;
/* 1055 */       this.field_70179_y *= speedLimit / motion;
/* 1056 */       motion = speedLimit;
/*      */     } 
/*      */     
/* 1059 */     if (motion > prevMotion && this.currentSpeed < speedLimit) {
/*      */       
/* 1061 */       this.currentSpeed += (speedLimit - this.currentSpeed) / 35.0D;
/*      */       
/* 1063 */       if (this.currentSpeed > speedLimit)
/*      */       {
/* 1065 */         this.currentSpeed = speedLimit;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1070 */       this.currentSpeed -= (this.currentSpeed - 0.07D) / 35.0D;
/*      */       
/* 1072 */       if (this.currentSpeed < 0.07D)
/*      */       {
/* 1074 */         this.currentSpeed = 0.07D;
/*      */       }
/*      */     } 
/*      */     
/* 1078 */     if (this.field_70122_E || MCH_Lib.getBlockIdY((Entity)this, 1, -2) > 0) {
/*      */       
/* 1080 */       this.field_70159_w *= (getAcInfo()).motionFactor;
/* 1081 */       this.field_70179_y *= (getAcInfo()).motionFactor;
/*      */       
/* 1083 */       if (MathHelper.func_76135_e(getRotPitch()) < 40.0F)
/*      */       {
/*      */         
/* 1086 */         applyOnGroundPitch(0.8F);
/*      */       }
/*      */     } 
/*      */     
/* 1090 */     updateWheels();
/*      */     
/* 1092 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*      */ 
/*      */     
/* 1095 */     this.field_70181_x *= 0.95D;
/* 1096 */     this.field_70159_w *= (getAcInfo()).motionFactor;
/* 1097 */     this.field_70179_y *= (getAcInfo()).motionFactor;
/*      */     
/* 1099 */     func_70101_b(getRotYaw(), getRotPitch());
/*      */ 
/*      */     
/* 1102 */     onUpdate_updateBlock();
/*      */     
/* 1104 */     updateCollisionBox();
/*      */     
/* 1106 */     if (getRiddenByEntity() != null && (getRiddenByEntity()).field_70128_L) {
/*      */       
/* 1108 */       unmountEntity();
/* 1109 */       this.field_70153_n = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void collisionEntity(AxisAlignedBB bb) {
/* 1115 */     if (bb == null)
/*      */       return; 
/* 1117 */     double speed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 1118 */     if (speed <= 0.05D)
/*      */       return; 
/* 1120 */     Entity rider = getRiddenByEntity();
/*      */     
/* 1122 */     float damage = (float)(speed * 15.0D);
/*      */     
/* 1124 */     MCH_EntityAircraft rideAc = (this.field_70154_o instanceof MCH_EntityAircraft) ? (MCH_EntityAircraft)this.field_70154_o : ((this.field_70154_o instanceof MCH_EntitySeat) ? ((MCH_EntitySeat)this.field_70154_o).getParent() : null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1131 */     List<Entity> list = this.field_70170_p.func_94576_a((Entity)this, bb.func_72314_b(0.3D, 0.3D, 0.3D), (IEntitySelector)new Object(this, rideAc));
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
/* 1158 */     for (int i = 0; i < list.size(); i++) {
/*      */       
/* 1160 */       Entity e = list.get(i);
/* 1161 */       if (shouldCollisionDamage(e)) {
/*      */         DamageSource ds;
/* 1163 */         double dx = e.field_70165_t - this.field_70165_t;
/* 1164 */         double dz = e.field_70161_v - this.field_70161_v;
/* 1165 */         double dist = Math.sqrt(dx * dx + dz * dz);
/* 1166 */         if (dist > 5.0D) dist = 5.0D; 
/* 1167 */         damage = (float)(damage + 5.0D - dist);
/*      */ 
/*      */         
/* 1170 */         if (rider instanceof EntityLivingBase) {
/*      */           
/* 1172 */           ds = DamageSource.func_76358_a((EntityLivingBase)rider);
/*      */         }
/*      */         else {
/*      */           
/* 1176 */           ds = DamageSource.field_76377_j;
/*      */         } 
/*      */         
/* 1179 */         MCH_Lib.applyEntityHurtResistantTimeConfig(e);
/*      */         
/* 1181 */         e.func_70097_a(ds, damage);
/* 1182 */         if (e instanceof MCH_EntityAircraft) {
/*      */           
/* 1184 */           e.field_70159_w += this.field_70159_w * 0.05D;
/* 1185 */           e.field_70179_y += this.field_70179_y * 0.05D;
/*      */         }
/* 1187 */         else if (e instanceof net.minecraft.entity.projectile.EntityArrow) {
/*      */           
/* 1189 */           e.func_70106_y();
/*      */         }
/*      */         else {
/*      */           
/* 1193 */           e.field_70159_w += this.field_70159_w * 1.5D;
/* 1194 */           e.field_70179_y += this.field_70179_y * 1.5D;
/*      */         } 
/*      */         
/* 1197 */         if ((getTankInfo()).weightType != 2 && (e.field_70130_N >= 1.0F || e.field_70131_O >= 1.5D)) {
/*      */           
/* 1199 */           if (e instanceof EntityLivingBase) {
/*      */             
/* 1201 */             ds = DamageSource.func_76358_a((EntityLivingBase)e);
/*      */           }
/*      */           else {
/*      */             
/* 1205 */             ds = DamageSource.field_76377_j;
/*      */           } 
/* 1207 */           func_70097_a(ds, damage / 3.0F);
/*      */         } 
/*      */         
/* 1210 */         MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityTank.collisionEntity damage=%.1f %s", new Object[] { Float.valueOf(damage), e.toString() });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean shouldCollisionDamage(Entity e) {
/* 1217 */     if (getSeatIdByEntity(e) >= 0) return false; 
/* 1218 */     if (this.noCollisionEntities.containsKey(e)) return false;
/*      */     
/* 1220 */     if (e instanceof MCH_EntityHitBox && ((MCH_EntityHitBox)e).parent != null) {
/*      */       
/* 1222 */       MCH_EntityAircraft ac = ((MCH_EntityHitBox)e).parent;
/* 1223 */       if (this.noCollisionEntities.containsKey(ac)) return false;
/*      */     
/*      */     } 
/* 1226 */     if (e.field_70154_o instanceof MCH_EntityAircraft)
/*      */     {
/* 1228 */       if (this.noCollisionEntities.containsKey(e.field_70154_o)) return false;
/*      */     
/*      */     }
/* 1231 */     if (e.field_70154_o instanceof MCH_EntitySeat && ((MCH_EntitySeat)e.field_70154_o).getParent() != null)
/*      */     {
/* 1233 */       if (this.noCollisionEntities.containsKey(((MCH_EntitySeat)e.field_70154_o).getParent())) return false;
/*      */     
/*      */     }
/* 1236 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateCollisionBox() {
/* 1241 */     if (getAcInfo() == null)
/*      */       return; 
/* 1243 */     this.WheelMng.updateBlock();
/*      */     
/* 1245 */     for (MCH_BoundingBox bb : this.extraBoundingBox) {
/*      */       
/* 1247 */       if (this.field_70146_Z.nextInt(3) == 0) {
/*      */         
/* 1249 */         if (MCH_Config.Collision_DestroyBlock.prmBool) {
/*      */           
/* 1251 */           Vec3 v = getTransformedPosition(bb.offsetX, bb.offsetY, bb.offsetZ);
/* 1252 */           destoryBlockRange(v, bb.width, bb.height);
/*      */         } 
/* 1254 */         collisionEntity(bb.boundingBox);
/*      */       } 
/*      */     } 
/* 1257 */     if (MCH_Config.Collision_DestroyBlock.prmBool)
/*      */     {
/* 1259 */       destoryBlockRange(getTransformedPosition(0.0D, 0.0D, 0.0D), this.field_70130_N * 1.5D, (this.field_70131_O * 2.0F));
/*      */     }
/*      */     
/* 1262 */     collisionEntity(func_70046_E());
/*      */   }
/*      */ 
/*      */   
/*      */   public void destoryBlockRange(Vec3 v, double w, double h) {
/* 1267 */     if (getAcInfo() == null)
/*      */       return; 
/* 1269 */     List<Block> destroyBlocks = MCH_Config.getBreakableBlockListFromType((getTankInfo()).weightType);
/* 1270 */     List<Block> noDestroyBlocks = MCH_Config.getNoBreakableBlockListFromType((getTankInfo()).weightType);
/* 1271 */     List<Material> destroyMaterials = MCH_Config.getBreakableMaterialListFromType((getTankInfo()).weightType);
/*      */     
/* 1273 */     int ws = (int)(w + 2.0D) / 2;
/* 1274 */     int hs = (int)(h + 2.0D) / 2;
/*      */     
/* 1276 */     for (int x = -ws; x <= ws; x++) {
/*      */       
/* 1278 */       for (int z = -ws; z <= ws; z++) {
/*      */         
/* 1280 */         for (int y = -hs; y <= hs + 1; y++) {
/*      */           
/* 1282 */           int bx = (int)(v.field_72450_a + x - 0.5D);
/* 1283 */           int by = (int)(v.field_72448_b + y - 1.0D);
/* 1284 */           int bz = (int)(v.field_72449_c + z - 0.5D);
/*      */           
/* 1286 */           Block block = (by >= 0 && by < 256) ? this.field_70170_p.func_147439_a(bx, by, bz) : Blocks.field_150350_a;
/* 1287 */           Material mat = block.func_149688_o();
/* 1288 */           if (!Block.func_149680_a(block, Blocks.field_150350_a)) {
/*      */             
/* 1290 */             for (Block c : noDestroyBlocks) {
/*      */               
/* 1292 */               if (Block.func_149680_a(block, c)) {
/*      */                 
/* 1294 */                 block = null;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1298 */             if (block == null)
/*      */               break; 
/* 1300 */             for (Block c : destroyBlocks) {
/*      */               
/* 1302 */               if (Block.func_149680_a(block, c)) {
/*      */                 
/* 1304 */                 destroyBlock(bx, by, bz);
/* 1305 */                 mat = null;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1309 */             if (mat == null)
/*      */               break; 
/* 1311 */             for (Material m : destroyMaterials) {
/*      */               
/* 1313 */               if (block.func_149688_o() == m) {
/*      */                 
/* 1315 */                 destroyBlock(bx, by, bz);
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void destroyBlock(int bx, int by, int bz) {
/* 1327 */     if (this.field_70146_Z.nextInt(8) == 0) {
/*      */       
/* 1329 */       W_WorldFunc.destroyBlock(this.field_70170_p, bx, by, bz, true);
/*      */     }
/*      */     else {
/*      */       
/* 1333 */       this.field_70170_p.func_147468_f(bx, by, bz);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateWheels() {
/* 1339 */     this.WheelMng.move(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getMaxSpeed() {
/* 1344 */     return (getTankInfo()).speed + 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAngles(Entity player, boolean fixRot, float fixYaw, float fixPitch, float deltaX, float deltaY, float x, float y, float partialTicks) {
/* 1354 */     if (partialTicks < 0.03F) partialTicks = 0.4F; 
/* 1355 */     if (partialTicks > 0.9F) partialTicks = 0.6F; 
/* 1356 */     this.lowPassPartialTicks.put(partialTicks);
/* 1357 */     partialTicks = this.lowPassPartialTicks.getAvg();
/*      */     
/* 1359 */     float ac_pitch = getRotPitch();
/* 1360 */     float ac_yaw = getRotYaw();
/* 1361 */     float ac_roll = getRotRoll();
/*      */     
/* 1363 */     if (isFreeLookMode())
/*      */     {
/* 1365 */       x = y = 0.0F;
/*      */     }
/*      */     
/* 1368 */     float yaw = 0.0F;
/* 1369 */     float pitch = 0.0F;
/* 1370 */     float roll = 0.0F;
/*      */     
/* 1372 */     MCH_Math.FMatrix m_add = MCH_Math.newMatrix();
/*      */     
/* 1374 */     MCH_Math.MatTurnZ(m_add, roll / 180.0F * 3.1415927F);
/* 1375 */     MCH_Math.MatTurnX(m_add, pitch / 180.0F * 3.1415927F);
/* 1376 */     MCH_Math.MatTurnY(m_add, yaw / 180.0F * 3.1415927F);
/*      */     
/* 1378 */     MCH_Math.MatTurnZ(m_add, (float)((getRotRoll() / 180.0F) * Math.PI));
/* 1379 */     MCH_Math.MatTurnX(m_add, (float)((getRotPitch() / 180.0F) * Math.PI));
/* 1380 */     MCH_Math.MatTurnY(m_add, (float)((getRotYaw() / 180.0F) * Math.PI));
/* 1381 */     MCH_Math.FVector3D v = MCH_Math.MatrixToEuler(m_add);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1386 */     v.x = MCH_Lib.RNG(v.x, -90.0F, 90.0F);
/* 1387 */     v.z = MCH_Lib.RNG(v.z, -90.0F, 90.0F);
/*      */     
/* 1389 */     if (v.z > 180.0F) v.z -= 360.0F; 
/* 1390 */     if (v.z < -180.0F) v.z += 360.0F;
/*      */     
/* 1392 */     setRotYaw(v.y);
/* 1393 */     setRotPitch(v.x);
/* 1394 */     setRotRoll(v.z);
/*      */     
/* 1396 */     onUpdateAngles(partialTicks);
/*      */     
/* 1398 */     if ((getAcInfo()).limitRotation) {
/*      */       
/* 1400 */       v.x = MCH_Lib.RNG(getRotPitch(), -90.0F, 90.0F);
/* 1401 */       v.z = MCH_Lib.RNG(getRotRoll(), -90.0F, 90.0F);
/* 1402 */       setRotPitch(v.x);
/* 1403 */       setRotRoll(v.z);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1410 */     float RV = 180.0F;
/* 1411 */     if (MathHelper.func_76135_e(getRotPitch()) > 90.0F) {
/*      */       
/* 1413 */       MCH_Lib.DbgLog(true, "MCH_EntityAircraft.setAngles Error:Pitch=%.1f", new Object[] { Float.valueOf(getRotPitch()) });
/* 1414 */       setRotPitch(0.0F);
/*      */     } 
/*      */     
/* 1417 */     if (getRotRoll() > 180.0F)
/*      */     {
/* 1419 */       setRotRoll(getRotRoll() - 360.0F);
/*      */     }
/* 1421 */     if (getRotRoll() < -180.0F)
/*      */     {
/* 1423 */       setRotRoll(getRotRoll() + 360.0F);
/*      */     }
/*      */     
/* 1426 */     this.prevRotationRoll = getRotRoll();
/* 1427 */     this.field_70127_C = getRotPitch();
/*      */     
/* 1429 */     if (getRidingEntity() == null)
/*      */     {
/* 1431 */       this.field_70126_B = getRotYaw();
/*      */     }
/*      */     
/* 1434 */     float deltaLimit = (getAcInfo()).cameraRotationSpeed * partialTicks;
/*      */     
/* 1436 */     MCH_WeaponSet ws = getCurrentWeapon(player);
/* 1437 */     deltaLimit *= (ws != null && ws.getInfo() != null) ? (ws.getInfo()).cameraRotationSpeedPitch : 1.0F;
/*      */     
/* 1439 */     if (deltaX > deltaLimit)
/*      */     {
/* 1441 */       deltaX = deltaLimit;
/*      */     }
/* 1443 */     if (deltaX < -deltaLimit)
/*      */     {
/* 1445 */       deltaX = -deltaLimit;
/*      */     }
/*      */     
/* 1448 */     if (deltaY > deltaLimit)
/*      */     {
/* 1450 */       deltaY = deltaLimit;
/*      */     }
/* 1452 */     if (deltaY < -deltaLimit)
/*      */     {
/* 1454 */       deltaY = -deltaLimit;
/*      */     }
/*      */     
/* 1457 */     if (isOverridePlayerYaw() || fixRot) {
/*      */       
/* 1459 */       if (getRidingEntity() == null) {
/*      */         
/* 1461 */         player.field_70126_B = getRotYaw() + fixYaw;
/*      */       }
/*      */       else {
/*      */         
/* 1465 */         if (getRotYaw() - player.field_70177_z > 180.0F)
/*      */         {
/* 1467 */           player.field_70126_B += 360.0F;
/*      */         }
/* 1469 */         if (getRotYaw() - player.field_70177_z < -180.0F)
/*      */         {
/* 1471 */           player.field_70126_B -= 360.0F;
/*      */         }
/*      */       } 
/* 1474 */       player.field_70177_z = getRotYaw() + fixYaw;
/*      */     }
/*      */     else {
/*      */       
/* 1478 */       player.func_70082_c(deltaX, 0.0F);
/*      */     } 
/*      */     
/* 1481 */     if (isOverridePlayerPitch() || fixRot) {
/*      */       
/* 1483 */       player.field_70127_C = getRotPitch() + fixPitch;
/* 1484 */       player.field_70125_A = getRotPitch() + fixPitch;
/*      */     }
/*      */     else {
/*      */       
/* 1488 */       player.func_70082_c(0.0F, deltaY);
/*      */     } 
/*      */     
/* 1491 */     float playerYaw = MathHelper.func_76142_g(getRotYaw() - player.field_70177_z);
/* 1492 */     float playerPitch = getRotPitch() * MathHelper.func_76134_b((float)(playerYaw * Math.PI / 180.0D)) + -getRotRoll() * MathHelper.func_76126_a((float)(playerYaw * Math.PI / 180.0D));
/*      */ 
/*      */     
/* 1495 */     if (MCH_MOD.proxy.isFirstPerson()) {
/*      */       
/* 1497 */       player.field_70125_A = MCH_Lib.RNG(player.field_70125_A, playerPitch + (getAcInfo()).minRotationPitch, playerPitch + (getAcInfo()).maxRotationPitch);
/*      */ 
/*      */       
/* 1500 */       player.field_70125_A = MCH_Lib.RNG(player.field_70125_A, -90.0F, 90.0F);
/*      */     } 
/* 1502 */     player.field_70127_C = player.field_70125_A;
/*      */     
/* 1504 */     if ((getRidingEntity() == null && ac_yaw != getRotYaw()) || ac_pitch != getRotPitch() || ac_roll != getRotRoll())
/*      */     {
/*      */ 
/*      */       
/* 1508 */       this.aircraftRotChanged = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSoundVolume() {
/* 1517 */     if (getAcInfo() != null && (getAcInfo()).throttleUpDown <= 0.0F) return 0.0F; 
/* 1518 */     return this.soundVolume * 0.7F;
/*      */   }
/*      */   
/*      */   public void updateSound() {
/* 1522 */     float target = (float)getCurrentThrottle();
/*      */     
/* 1524 */     if (getRiddenByEntity() != null)
/*      */     {
/* 1526 */       if (this.partCanopy == null || getCanopyRotation() < 1.0F)
/*      */       {
/* 1528 */         target += 0.1F;
/*      */       }
/*      */     }
/*      */     
/* 1532 */     if (this.moveLeft || this.moveRight || this.throttleDown) {
/*      */       
/* 1534 */       this.soundVolumeTarget += 0.1F;
/* 1535 */       if (this.soundVolumeTarget > 0.75F)
/*      */       {
/* 1537 */         this.soundVolumeTarget = 0.75F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1542 */       this.soundVolumeTarget *= 0.8F;
/*      */     } 
/*      */     
/* 1545 */     if (target < this.soundVolumeTarget)
/*      */     {
/* 1547 */       target = this.soundVolumeTarget;
/*      */     }
/*      */     
/* 1550 */     if (this.soundVolume < target) {
/*      */       
/* 1552 */       this.soundVolume += 0.02F;
/* 1553 */       if (this.soundVolume >= target)
/*      */       {
/* 1555 */         this.soundVolume = target;
/*      */       }
/*      */     }
/* 1558 */     else if (this.soundVolume > target) {
/*      */       
/* 1560 */       this.soundVolume -= 0.02F;
/* 1561 */       if (this.soundVolume <= target)
/*      */       {
/* 1563 */         this.soundVolume = target;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSoundPitch() {
/* 1570 */     float target1 = (float)(0.5D + getCurrentThrottle() * 0.5D);
/* 1571 */     float target2 = (float)(0.5D + this.soundVolumeTarget * 0.5D);
/* 1572 */     return (target1 > target2) ? target1 : target2;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDefaultSoundName() {
/* 1577 */     return "prop";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasBrake() {
/* 1582 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateParts(int stat) {
/* 1590 */     super.updateParts(stat);
/*      */     
/* 1592 */     if (isDestroyed())
/*      */       return; 
/* 1594 */     MCH_Parts[] parts = new MCH_Parts[0];
/* 1595 */     for (MCH_Parts p : parts) {
/*      */       
/* 1597 */       if (p != null) {
/*      */         
/* 1599 */         p.updateStatusClient(stat);
/* 1600 */         p.update();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getUnfoldLandingGearThrottle() {
/* 1607 */     return 0.7F;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_EntityTank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */