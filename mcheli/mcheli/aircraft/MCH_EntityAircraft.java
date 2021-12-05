/*      */ package mcheli.mcheli.aircraft;
/*      */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import mcheli.MCH_Achievement;
/*      */ import mcheli.MCH_Camera;
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Explosion;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.MCH_LowPassFilterFloat;
/*      */ import mcheli.MCH_MOD;
/*      */ import mcheli.MCH_Math;
/*      */ import mcheli.MCH_Queue;
/*      */ import mcheli.MCH_Vector2;
/*      */ import mcheli.MCH_ViewEntityDummy;
/*      */ import mcheli.aircraft.MCH_AircraftInfo;
/*      */ import mcheli.aircraft.MCH_AircraftInventory;
/*      */ import mcheli.aircraft.MCH_BoundingBox;
/*      */ import mcheli.aircraft.MCH_DummyCommandSender;
/*      */ import mcheli.aircraft.MCH_EntityHide;
/*      */ import mcheli.aircraft.MCH_EntityHitBox;
/*      */ import mcheli.aircraft.MCH_EntitySeat;
/*      */ import mcheli.aircraft.MCH_IEntityCanRideAircraft;
/*      */ import mcheli.aircraft.MCH_MissileDetector;
/*      */ import mcheli.aircraft.MCH_PacketIndRotation;
/*      */ import mcheli.aircraft.MCH_PacketNotifyAmmoNum;
/*      */ import mcheli.aircraft.MCH_PacketSeatListRequest;
/*      */ import mcheli.aircraft.MCH_Parts;
/*      */ import mcheli.aircraft.MCH_Radar;
/*      */ import mcheli.aircraft.MCH_SeatInfo;
/*      */ import mcheli.aircraft.MCH_SeatRackInfo;
/*      */ import mcheli.aircraft.MCH_SoundUpdater;
/*      */ import mcheli.chain.MCH_EntityChain;
/*      */ import mcheli.command.MCH_Command;
/*      */ import mcheli.flare.MCH_Flare;
/*      */ import mcheli.multiplay.MCH_Multiplay;
/*      */ import mcheli.parachute.MCH_EntityParachute;
/*      */ import mcheli.particles.MCH_ParticleParam;
/*      */ import mcheli.particles.MCH_ParticlesUtil;
/*      */ import mcheli.uav.MCH_EntityUavStation;
/*      */ import mcheli.weapon.MCH_EntityTvMissile;
/*      */ import mcheli.weapon.MCH_WeaponBase;
/*      */ import mcheli.weapon.MCH_WeaponInfo;
/*      */ import mcheli.weapon.MCH_WeaponParam;
/*      */ import mcheli.weapon.MCH_WeaponSet;
/*      */ import mcheli.wrapper.W_AxisAlignedBB;
/*      */ import mcheli.wrapper.W_Block;
/*      */ import mcheli.wrapper.W_Blocks;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_EntityContainer;
/*      */ import mcheli.wrapper.W_EntityPlayer;
/*      */ import mcheli.wrapper.W_Item;
/*      */ import mcheli.wrapper.W_Lib;
/*      */ import mcheli.wrapper.W_NBTTag;
/*      */ import mcheli.wrapper.W_Reflection;
/*      */ import mcheli.wrapper.W_WorldFunc;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ public abstract class MCH_EntityAircraft extends W_EntityContainer implements MCH_IEntityLockChecker, MCH_IEntityCanRideAircraft, IEntityAdditionalSpawnData {
/*      */   private static final int DATAWT_ID_DAMAGE = 19;
/*      */   private static final int DATAWT_ID_TYPE = 20;
/*      */   private static final int DATAWT_ID_TEXTURE_NAME = 21;
/*      */   private static final int DATAWT_ID_UAV_STATION = 22;
/*      */   private static final int DATAWT_ID_STATUS = 23;
/*      */   private static final int CMN_ID_FLARE = 0;
/*      */   private static final int CMN_ID_FREE_LOOK = 1;
/*      */   private static final int CMN_ID_RELOADING = 2;
/*      */   private static final int CMN_ID_INGINITY_AMMO = 3;
/*      */   private static final int CMN_ID_INGINITY_FUEL = 4;
/*      */   private static final int CMN_ID_RAPELLING = 5;
/*      */   private static final int CMN_ID_SEARCHLIGHT = 6;
/*      */   private static final int CMN_ID_CNTRL_LEFT = 7;
/*      */   private static final int CMN_ID_CNTRL_RIGHT = 8;
/*      */   private static final int CMN_ID_CNTRL_UP = 9;
/*      */   private static final int CMN_ID_CNTRL_DOWN = 10;
/*      */   private static final int CMN_ID_CNTRL_BRAKE = 11;
/*      */   private static final int DATAWT_ID_USE_WEAPON = 24;
/*      */   private static final int DATAWT_ID_FUEL = 25;
/*      */   private static final int DATAWT_ID_ROT_ROLL = 26;
/*      */   private static final int DATAWT_ID_COMMAND = 27;
/*      */   private static final int DATAWT_ID_THROTTLE = 29;
/*      */   protected static final int DATAWT_ID_FOLD_STAT = 30;
/*      */   protected static final int DATAWT_ID_PART_STAT = 31;
/*      */   protected static final int PART_ID_CANOPY = 0;
/*      */   protected static final int PART_ID_NOZZLE = 1;
/*      */   protected static final int PART_ID_LANDINGGEAR = 2;
/*      */   protected static final int PART_ID_WING = 3;
/*      */   protected static final int PART_ID_HATCH = 4;
/*      */   public static final byte LIMIT_GROUND_PITCH = 40;
/*      */   public static final byte LIMIT_GROUND_ROLL = 40;
/*      */   public boolean isRequestedSyncStatus;
/*      */   private MCH_AircraftInfo acInfo;
/*      */   private int commonStatus;
/*      */   private Entity[] partEntities;
/*      */   private MCH_EntityHitBox pilotSeat;
/*      */   private MCH_EntitySeat[] seats;
/*      */   private MCH_SeatInfo[] seatsInfo;
/*      */   private String commonUniqueId;
/*      */   private int seatSearchCount;
/*      */   protected double velocityX;
/*      */   protected double velocityY;
/*      */   protected double velocityZ;
/*      */   public boolean keepOnRideRotation;
/*      */   protected int aircraftPosRotInc;
/*      */   protected double aircraftX;
/*      */   protected double aircraftY;
/*      */   protected double aircraftZ;
/*      */   protected double aircraftYaw;
/*      */   protected double aircraftPitch;
/*      */   public boolean aircraftRollRev;
/*      */   public boolean aircraftRotChanged;
/*      */   public float rotationRoll;
/*      */   public float prevRotationRoll;
/*      */   private double currentThrottle;
/*      */   private double prevCurrentThrottle;
/*      */   public double currentSpeed;
/*      */   public int currentFuel;
/*  145 */   public float throttleBack = 0.0F;
/*      */   
/*      */   public double beforeHoverThrottle;
/*      */   
/*  149 */   public int waitMountEntity = 0;
/*      */ 
/*      */   
/*      */   public boolean throttleUp = false;
/*      */ 
/*      */   
/*      */   public boolean throttleDown = false;
/*      */ 
/*      */   
/*      */   public boolean moveLeft = false;
/*      */ 
/*      */   
/*      */   public boolean moveRight = false;
/*      */ 
/*      */   
/*      */   public MCH_LowPassFilterFloat lowPassPartialTicks;
/*      */ 
/*      */   
/*      */   private MCH_Radar entityRadar;
/*      */ 
/*      */   
/*      */   private int radarRotate;
/*      */ 
/*      */   
/*      */   private MCH_Flare flareDv;
/*      */   
/*      */   private int currentFlareIndex;
/*      */   
/*      */   protected MCH_WeaponSet[] weapons;
/*      */   
/*      */   protected int[] currentWeaponID;
/*      */   
/*      */   public float lastRiderYaw;
/*      */   
/*      */   public float prevLastRiderYaw;
/*      */   
/*      */   public float lastRiderPitch;
/*      */   
/*      */   public float prevLastRiderPitch;
/*      */   
/*      */   protected MCH_WeaponSet dummyWeapon;
/*      */   
/*      */   protected int useWeaponStat;
/*      */   
/*      */   protected int hitStatus;
/*      */   
/*      */   protected final MCH_SoundUpdater soundUpdater;
/*      */   
/*      */   protected Entity lastRiddenByEntity;
/*      */   
/*      */   protected Entity lastRidingEntity;
/*      */   
/*  201 */   public List<UnmountReserve> listUnmountReserve = new ArrayList<UnmountReserve>();
/*      */   
/*      */   private int countOnUpdate;
/*      */   
/*      */   private MCH_EntityChain towChainEntity;
/*      */   
/*      */   private MCH_EntityChain towedChainEntity;
/*      */   
/*      */   public MCH_Camera camera;
/*      */   
/*      */   private int cameraId;
/*      */   
/*      */   protected boolean isGunnerMode = false;
/*      */   
/*      */   protected boolean isGunnerModeOtherSeat = false;
/*      */   
/*      */   private boolean isHoveringMode = false;
/*      */   
/*      */   public static final int CAMERA_PITCH_MIN = -30;
/*      */   
/*      */   public static final int CAMERA_PITCH_MAX = 70;
/*      */   
/*      */   private MCH_EntityTvMissile TVmissile;
/*      */   
/*      */   protected boolean isGunnerFreeLookMode = false;
/*      */   public final MCH_MissileDetector missileDetector;
/*  227 */   public int serverNoMoveCount = 0;
/*      */   
/*      */   public int repairCount;
/*      */   
/*      */   public int beforeDamageTaken;
/*      */   
/*      */   public int timeSinceHit;
/*      */   private int despawnCount;
/*      */   public float rotDestroyedYaw;
/*      */   public float rotDestroyedPitch;
/*      */   public float rotDestroyedRoll;
/*      */   public int damageSinceDestroyed;
/*      */   public boolean isFirstDamageSmoke = true;
/*  240 */   public Vec3[] prevDamageSmokePos = new Vec3[0];
/*      */   
/*      */   private MCH_EntityUavStation uavStation;
/*      */   
/*      */   public boolean cs_dismountAll;
/*      */   
/*      */   public boolean cs_heliAutoThrottleDown;
/*      */   
/*      */   public boolean cs_planeAutoThrottleDown;
/*      */   
/*      */   public boolean cs_tankAutoThrottleDown;
/*      */   
/*      */   public MCH_Parts partHatch;
/*      */   
/*      */   public MCH_Parts partCanopy;
/*      */   
/*      */   public MCH_Parts partLandingGear;
/*      */   
/*      */   public double prevRidingEntityPosX;
/*      */   
/*      */   public double prevRidingEntityPosY;
/*      */   
/*      */   public double prevRidingEntityPosZ;
/*      */   
/*      */   public boolean canRideRackStatus;
/*      */   
/*      */   private int modeSwitchCooldown;
/*      */   
/*      */   public MCH_BoundingBox[] extraBoundingBox;
/*      */   
/*      */   public float lastBBDamageFactor;
/*      */   
/*      */   private final MCH_AircraftInventory inventory;
/*      */   private double fuelConsumption;
/*      */   private int fuelSuppliedCount;
/*      */   private int supplyAmmoWait;
/*      */   private boolean beforeSupplyAmmo;
/*      */   public WeaponBay[] weaponBays;
/*      */   public float[] rotPartRotation;
/*      */   public float[] prevRotPartRotation;
/*  280 */   public float[] rotCrawlerTrack = new float[2];
/*      */   
/*  282 */   public float[] prevRotCrawlerTrack = new float[2];
/*      */   
/*  284 */   public float[] throttleCrawlerTrack = new float[2];
/*      */ 
/*      */   
/*  287 */   public float[] rotTrackRoller = new float[2];
/*      */   
/*  289 */   public float[] prevRotTrackRoller = new float[2];
/*      */   
/*  291 */   public float rotWheel = 0.0F;
/*  292 */   public float prevRotWheel = 0.0F;
/*  293 */   public float rotYawWheel = 0.0F;
/*  294 */   public float prevRotYawWheel = 0.0F;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isParachuting;
/*      */ 
/*      */   
/*  301 */   public float ropesLength = 0.0F;
/*      */   
/*      */   private MCH_Queue<Vec3> prevPosition;
/*      */   
/*      */   private int tickRepelling;
/*      */   
/*      */   private int lastUsedRopeIndex;
/*      */   
/*      */   private boolean dismountedUserCtrl;
/*      */   
/*      */   public float lastSearchLightYaw;
/*      */   public float lastSearchLightPitch;
/*  313 */   public float rotLightHatch = 0.0F;
/*  314 */   public float prevRotLightHatch = 0.0F;
/*      */   
/*  316 */   public int recoilCount = 0;
/*  317 */   public float recoilYaw = 0.0F;
/*  318 */   public float recoilValue = 0.0F;
/*      */   
/*  320 */   public int brightnessHigh = 240;
/*  321 */   public int brightnessLow = 240;
/*      */   
/*  323 */   public final HashMap<Entity, Integer> noCollisionEntities = new HashMap<Entity, Integer>();
/*      */   private double lastCalcLandInDistanceCount;
/*      */   private double lastLandInDistance;
/*      */   protected void func_70088_a() { super.func_70088_a(); func_70096_w().func_75682_a(20, ""); func_70096_w().func_75682_a(19, new Integer(0)); func_70096_w().func_75682_a(23, new Integer(0)); func_70096_w().func_75682_a(24, new Integer(0)); func_70096_w().func_75682_a(25, new Integer(0)); func_70096_w().func_75682_a(21, ""); func_70096_w().func_75682_a(22, new Integer(0)); func_70096_w().func_75682_a(26, new Short((short)0)); func_70096_w().func_75682_a(27, new String("")); func_70096_w().func_75682_a(29, new Integer(0)); func_70096_w().func_75682_a(31, new Integer(0)); if (!this.field_70170_p.field_72995_K) { setCommonStatus(3, MCH_Config.InfinityAmmo.prmBool); setCommonStatus(4, MCH_Config.InfinityFuel.prmBool); }  getEntityData().func_74778_a("EntityType", getEntityType()); }
/*      */   public float getServerRoll() { return func_70096_w().func_75693_b(26); }
/*      */   public float getRotYaw() { return this.field_70177_z; }
/*      */   public float getRotPitch() { return this.field_70125_A; }
/*      */   public float getRotRoll() { return this.rotationRoll; }
/*      */   public void setRotYaw(float f) { this.field_70177_z = f; }
/*      */   public void setRotPitch(float f) { this.field_70125_A = f; }
/*      */   public void setRotPitch(float f, String msg) { setRotPitch(f); }
/*      */   public void setRotRoll(float f) { this.rotationRoll = f; }
/*      */   public void applyOnGroundPitch(float factor) { if (getAcInfo() != null) { float ogp = (getAcInfo()).onGroundPitch; float pitch = getRotPitch(); pitch -= ogp; pitch *= factor; pitch += ogp; setRotPitch(pitch, "applyOnGroundPitch"); }  setRotRoll(getRotRoll() * factor); }
/*      */   public float calcRotYaw(float partialTicks) { return this.field_70126_B + (getRotYaw() - this.field_70126_B) * partialTicks; }
/*      */   public float calcRotPitch(float partialTicks) { return this.field_70127_C + (getRotPitch() - this.field_70127_C) * partialTicks; }
/*      */   public float calcRotRoll(float partialTicks) { return this.prevRotationRoll + (getRotRoll() - this.prevRotationRoll) * partialTicks; }
/*      */   protected void func_70101_b(float y, float p) { setRotYaw(y % 360.0F); setRotPitch(p % 360.0F); }
/*      */   public boolean isInfinityAmmo(Entity player) { return (isCreative(player) || getCommonStatus(3)); }
/*  341 */   public boolean isInfinityFuel(Entity player, boolean checkOtherSeet) { if (isCreative(player) || getCommonStatus(4)) return true;  if (checkOtherSeet) for (MCH_EntitySeat seat : getSeats()) { if (seat != null) if (isCreative(seat.field_70153_n)) return true;   }   return false; } public void setCommand(String s, EntityPlayer player) { if (!this.field_70170_p.field_72995_K) if (MCH_Command.canUseCommand((Entity)player)) setCommandForce(s);   } public void setCommandForce(String s) { if (!this.field_70170_p.field_72995_K) func_70096_w().func_75692_b(27, s);  } public String getCommand() { return func_70096_w().func_75681_e(27); } public String getKindName() { return ""; } public String getEntityType() { return ""; } public void setTypeName(String s) { String beforeType = getTypeName(); if (s != null && !s.isEmpty()) if (s.compareTo(beforeType) != 0) { func_70096_w().func_75692_b(20, String.valueOf(s)); changeType(s); initRotationYaw(getRotYaw()); }   } public String getTypeName() { return func_70096_w().func_75681_e(20); } public abstract void changeType(String paramString); public boolean isTargetDrone() { return (getAcInfo() != null && (getAcInfo()).isTargetDrone); } public boolean isUAV() { return (getAcInfo() != null && (getAcInfo()).isUAV); } public boolean isSmallUAV() { return (getAcInfo() != null && (getAcInfo()).isSmallUAV); } public boolean isAlwaysCameraView() { return (getAcInfo() != null && (getAcInfo()).alwaysCameraView); } public void setUavStation(MCH_EntityUavStation uavSt) { this.uavStation = uavSt; if (!this.field_70170_p.field_72995_K) if (uavSt != null) { func_70096_w().func_75692_b(22, Integer.valueOf(W_Entity.getEntityId((Entity)uavSt))); } else { func_70096_w().func_75692_b(22, Integer.valueOf(0)); }   } public float getStealth() { return (getAcInfo() != null) ? (getAcInfo()).stealth : 0.0F; } public MCH_AircraftInventory getGuiInventory() { return this.inventory; } public void openGui(EntityPlayer player) { if (!this.field_70170_p.field_72995_K) player.openGui(MCH_MOD.instance, 1, this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);  } public MCH_EntityUavStation getUavStation() { return isUAV() ? this.uavStation : null; } public static mcheli.aircraft.MCH_EntityAircraft getAircraft_RiddenOrControl(Entity rider) { if (rider != null) { if (rider.field_70154_o instanceof mcheli.aircraft.MCH_EntityAircraft) return (mcheli.aircraft.MCH_EntityAircraft)rider.field_70154_o;  if (rider.field_70154_o instanceof MCH_EntitySeat) return ((MCH_EntitySeat)rider.field_70154_o).getParent();  if (rider.field_70154_o instanceof MCH_EntityUavStation) { MCH_EntityUavStation uavStation = (MCH_EntityUavStation)rider.field_70154_o; return uavStation.getControlAircract(); }  }  return null; } public boolean isCreative(Entity entity) { return (entity instanceof EntityPlayer && ((EntityPlayer)entity).field_71075_bZ.field_75098_d); } public Entity getRiddenByEntity() { if (isUAV()) if (this.uavStation != null) return this.uavStation.field_70153_n;   return this.field_70153_n; } public boolean getCommonStatus(int bit) { return ((this.commonStatus >> bit & 0x1) != 0); } public void setCommonStatus(int bit, boolean b) { setCommonStatus(bit, b, false); } public void setCommonStatus(int bit, boolean b, boolean writeClient) { if (!this.field_70170_p.field_72995_K || writeClient) { int bofore = this.commonStatus; int mask = 1 << bit; if (b) { this.commonStatus |= mask; } else { this.commonStatus &= mask ^ 0xFFFFFFFF; }  if (bofore != this.commonStatus) func_70096_w().func_75692_b(23, Integer.valueOf(this.commonStatus));  }  } public double getThrottle() { return 0.05D * func_70096_w().func_75679_c(29); } public void setThrottle(double t) { int n = (int)(t * 20.0D); if (n == 0 && t > 0.0D) n = 1;  func_70096_w().func_75692_b(29, Integer.valueOf(n)); } public int getMaxHP() { return (getAcInfo() != null) ? (getAcInfo()).maxHp : 100; } public int getHP() { return (getMaxHP() - getDamageTaken() >= 0) ? (getMaxHP() - getDamageTaken()) : 0; } public void setDamageTaken(int par1) { if (par1 < 0) par1 = 0;  if (par1 > getMaxHP()) par1 = getMaxHP();  func_70096_w().func_75692_b(19, Integer.valueOf(par1)); } public int getDamageTaken() { return func_70096_w().func_75679_c(19); } public void destroyAircraft() { setSearchLight(false); switchHoveringMode(false); switchGunnerMode(false); for (int i = 0; i < getSeatNum() + 1; i++) { Entity e = getEntityBySeatId(i); if (e instanceof EntityPlayer) switchCameraMode((EntityPlayer)e, 0);  }  if (isTargetDrone()) { setDespawnCount(50); } else { setDespawnCount(500); }  this.rotDestroyedPitch = this.field_70146_Z.nextFloat() - 0.5F; this.rotDestroyedRoll = (this.field_70146_Z.nextFloat() - 0.5F) * 0.5F; this.rotDestroyedYaw = 0.0F; if (isUAV() && getRiddenByEntity() != null) getRiddenByEntity().func_70078_a(null);  if (!this.field_70170_p.field_72995_K) { ejectSeat(getRiddenByEntity()); Entity entity = getEntityBySeatId(1); if (entity != null) ejectSeat(entity);  }  } public boolean isDestroyed() { return (getDespawnCount() > 0); } public int getDespawnCount() { return this.despawnCount; } public void setDespawnCount(int despawnCount) { this.despawnCount = despawnCount; } public boolean isEntityRadarMounted() { return (getAcInfo() != null) ? (getAcInfo()).isEnableEntityRadar : false; } public boolean canFloatWater() { return (getAcInfo() != null && (getAcInfo()).isFloat && !isDestroyed()); } @SideOnly(Side.CLIENT) public int func_70070_b(float par1) { if (haveSearchLight() && isSearchLightON()) return 15728880;  int i = MathHelper.func_76128_c(this.field_70165_t); int j = MathHelper.func_76128_c(this.field_70161_v); if (this.field_70170_p.func_72899_e(i, 0, j)) { double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66D; float fo = (getAcInfo() != null) ? (getAcInfo()).submergedDamageHeight : 0.0F; if (canFloatWater()) { fo = (getAcInfo()).floatOffset; if (fo < 0.0F) fo = -fo;  fo++; }  int k = MathHelper.func_76128_c(this.field_70163_u + fo - this.field_70129_M + d0); int val = this.field_70170_p.func_72802_i(i, k, j, 0); int low = val & 0xFFFF; int high = val >> 16 & 0xFFFF; if (high < this.brightnessHigh) { if (this.brightnessHigh > 0 && getCountOnUpdate() % 2 == 0) this.brightnessHigh--;  } else if (high > this.brightnessHigh) { this.brightnessHigh += 4; if (this.brightnessHigh > 240) this.brightnessHigh = 240;  }  return this.brightnessHigh << 16 | low; }  return 0; } public MCH_AircraftInfo.CameraPosition getCameraPosInfo() { if (getAcInfo() == null) return null;  Entity player = MCH_Lib.getClientPlayer(); int sid = getSeatIdByEntity(player); if (sid == 0 && canSwitchCameraPos()) if (getCameraId() > 0 && getCameraId() < (getAcInfo()).cameraPosition.size()) return (getAcInfo()).cameraPosition.get(getCameraId());   if (sid > 0 && sid < (getSeatsInfo()).length && (getSeatsInfo()[sid]).invCamPos) return getSeatsInfo()[sid].getCamPos();  return (getAcInfo()).cameraPosition.get(0); } public int getCameraId() { return this.cameraId; } public void setCameraId(int cameraId) { MCH_Lib.DbgLog(true, "MCH_EntityAircraft.setCameraId %d -> %d", new Object[] { Integer.valueOf(this.cameraId), Integer.valueOf(cameraId) }); this.cameraId = cameraId; } public boolean canSwitchCameraPos() { return (getCameraPosNum() >= 2); } public int getCameraPosNum() { if (getAcInfo() != null) return (getAcInfo()).cameraPosition.size();  return 1; } public void onAcInfoReloaded() { if (getAcInfo() == null) return;  func_70105_a((getAcInfo()).bodyWidth, (getAcInfo()).bodyHeight); } public void writeSpawnData(ByteBuf buffer) { if (getAcInfo() != null) { buffer.writeFloat((getAcInfo()).bodyHeight); buffer.writeFloat(2.0F); } else { buffer.writeFloat(this.field_70131_O); buffer.writeFloat(this.field_70130_N); }  } public void readSpawnData(ByteBuf additionalData) { try { float height = additionalData.readFloat(); float width = additionalData.readFloat(); func_70105_a(width, height); } catch (Exception e) { MCH_Lib.Log((Entity)this, "readSpawnData error!", new Object[0]); e.printStackTrace(); }  } protected void func_70037_a(NBTTagCompound nbt) { setDespawnCount(nbt.func_74762_e("AcDespawnCount")); setTextureName(nbt.func_74779_i("TextureName")); setCommonUniqueId(nbt.func_74779_i("AircraftUniqueId")); setRotRoll(nbt.func_74760_g("AcRoll")); this.prevRotationRoll = getRotRoll(); this.prevLastRiderYaw = this.lastRiderYaw = nbt.func_74760_g("AcLastRYaw"); this.prevLastRiderPitch = this.lastRiderPitch = nbt.func_74760_g("AcLastRPitch"); setPartStatus(nbt.func_74762_e("PartStatus")); setTypeName(nbt.func_74779_i("TypeName")); super.func_70037_a(nbt); getGuiInventory().readEntityFromNBT(nbt); setCommandForce(nbt.func_74779_i("AcCommand")); setFuel(nbt.func_74762_e("AcFuel")); int[] wa_list = nbt.func_74759_k("AcWeaponsAmmo"); for (int i = 0; i < wa_list.length; i++) { getWeapon(i).setRestAllAmmoNum(wa_list[i]); getWeapon(i).reloadMag(); }  if (getDespawnCount() > 0) { setDamageTaken(getMaxHP()); } else if (nbt.func_74764_b("AcDamage")) { setDamageTaken(nbt.func_74762_e("AcDamage")); }  if (haveSearchLight() && nbt.func_74764_b("SearchLight")) setSearchLight(nbt.func_74767_n("SearchLight"));  this.dismountedUserCtrl = nbt.func_74767_n("AcDismounted"); } protected void func_70014_b(NBTTagCompound nbt) { nbt.func_74778_a("TextureName", getTextureName()); nbt.func_74778_a("AircraftUniqueId", getCommonUniqueId()); nbt.func_74778_a("TypeName", getTypeName()); nbt.func_74768_a("PartStatus", getPartStatus() & getLastPartStatusMask()); nbt.func_74768_a("AcFuel", getFuel()); nbt.func_74768_a("AcDespawnCount", getDespawnCount()); nbt.func_74776_a("AcRoll", getRotRoll()); nbt.func_74757_a("SearchLight", isSearchLightON()); nbt.func_74776_a("AcLastRYaw", getLastRiderYaw()); nbt.func_74776_a("AcLastRPitch", getLastRiderPitch()); nbt.func_74778_a("AcCommand", getCommand()); super.func_70014_b(nbt); getGuiInventory().writeEntityToNBT(nbt); int[] wa_list = new int[getWeaponNum()]; for (int i = 0; i < wa_list.length; i++) wa_list[i] = getWeapon(i).getRestAllAmmoNum() + getWeapon(i).getAmmoNum();  nbt.func_74782_a("AcWeaponsAmmo", (NBTBase)W_NBTTag.newTagIntArray("AcWeaponsAmmo", wa_list)); nbt.func_74768_a("AcDamage", getDamageTaken()); nbt.func_74757_a("AcDismounted", this.dismountedUserCtrl); } public boolean func_70097_a(DamageSource damageSource, float org_damage) { float damage = org_damage; float damageFactor = this.lastBBDamageFactor; this.lastBBDamageFactor = 1.0F; if (func_85032_ar()) return false;  if (this.field_70128_L) return false;  if (this.timeSinceHit > 0) return false;  String dmt = damageSource.func_76355_l(); if (dmt.equalsIgnoreCase("inFire")) return false;  if (dmt.equalsIgnoreCase("cactus")) return false;  if (this.field_70170_p.field_72995_K) return true;  damage = MCH_Config.applyDamageByExternal((Entity)this, damageSource, damage); if (!MCH_Multiplay.canAttackEntity(damageSource, (Entity)this)) return false;  if (dmt.equalsIgnoreCase("lava")) { damage *= (this.field_70146_Z.nextInt(8) + 2); this.timeSinceHit = 2; }  if (dmt.startsWith("explosion")) { this.timeSinceHit = 1; } else if (isMountedEntity(damageSource.func_76346_g())) { return false; }  if (dmt.equalsIgnoreCase("onFire")) this.timeSinceHit = 10;  boolean isCreative = false; boolean isSneaking = false; Entity entity = damageSource.func_76346_g(); boolean isDamegeSourcePlayer = false; boolean playDamageSound = false; if (entity instanceof EntityPlayer) { EntityPlayer player = (EntityPlayer)entity; isCreative = player.field_71075_bZ.field_75098_d; isSneaking = player.func_70093_af(); if (dmt.equalsIgnoreCase("player")) if (isCreative) { isDamegeSourcePlayer = true; } else if (!MCH_Config.PreventingBroken.prmBool) { if (MCH_Config.BreakableOnlyPickaxe.prmBool) { if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemPickaxe) isDamegeSourcePlayer = true;  } else { isDamegeSourcePlayer = !isRidePlayer(); }  }   W_WorldFunc.MOD_playSoundAtEntity((Entity)this, "hit", 1.0F, 1.0F); } else { playDamageSound = true; }  if (!isDestroyed()) { if (!isDamegeSourcePlayer) { MCH_AircraftInfo acInfo = getAcInfo(); if (acInfo != null) if (!dmt.equalsIgnoreCase("lava") && !dmt.equalsIgnoreCase("onFire")) { if (damage > acInfo.armorMaxDamage) damage = acInfo.armorMaxDamage;  if (damageFactor <= 1.0F) damage *= damageFactor;  damage *= acInfo.armorDamageFactor; damage -= acInfo.armorMinDamage; if (damage <= 0.0F) { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.attackEntityFrom:no damage=%.1f -> %.1f(factor=%.2f):%s", new Object[] { Float.valueOf(org_damage), Float.valueOf(damage), Float.valueOf(damageFactor), dmt }); return false; }  if (damageFactor > 1.0F) damage *= damageFactor;  }   MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.attackEntityFrom:damage=%.1f(factor=%.2f):%s", new Object[] { Float.valueOf(damage), Float.valueOf(damageFactor), dmt }); setDamageTaken(getDamageTaken() + (int)damage); }  func_70018_K(); if (getDamageTaken() >= getMaxHP() || isDamegeSourcePlayer) if (!isDamegeSourcePlayer) { setDamageTaken(getMaxHP()); destroyAircraft(); this.timeSinceHit = 20; String cmd = getCommand().trim(); if (cmd.startsWith("/")) cmd = cmd.substring(1);  if (!cmd.isEmpty()) MCH_DummyCommandSender.execCommand(cmd);  if (dmt.equalsIgnoreCase("inWall")) { explosionByCrash(0.0D); this.damageSinceDestroyed = getMaxHP(); } else { MCH_Explosion.newExplosion(this.field_70170_p, null, entity, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, 2.0F, true, true, true, true, 5); }  } else { if (getAcInfo() != null && getAcInfo().getItem() != null) if (isCreative) { if (MCH_Config.DropItemInCreativeMode.prmBool && !isSneaking) dropItemWithOffset(getAcInfo().getItem(), 1, 0.0F);  if (!MCH_Config.DropItemInCreativeMode.prmBool && isSneaking) dropItemWithOffset(getAcInfo().getItem(), 1, 0.0F);  } else { dropItemWithOffset(getAcInfo().getItem(), 1, 0.0F); }   setDead(true); }   } else if (isDamegeSourcePlayer && isCreative) { setDead(true); }  if (playDamageSound) W_WorldFunc.MOD_playSoundAtEntity((Entity)this, "helidmg", 1.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.1F);  return true; } public boolean isExploded() { return (isDestroyed() && this.damageSinceDestroyed > getMaxHP() / 10 + 1); } public void destruct() { if (getRiddenByEntity() != null) getRiddenByEntity().func_70078_a(null);  setDead(true); } public EntityItem func_70099_a(ItemStack is, float par2) { if (is.field_77994_a == 0) return null;  setAcDataToItem(is); return super.func_70099_a(is, par2); } public void setAcDataToItem(ItemStack is) { if (!is.func_77942_o()) is.func_77982_d(new NBTTagCompound());  NBTTagCompound nbt = is.func_77978_p(); nbt.func_74778_a("MCH_Command", getCommand()); if (MCH_Config.ItemFuel.prmBool) nbt.func_74768_a("MCH_Fuel", getFuel());  if (MCH_Config.ItemDamage.prmBool) is.func_77964_b(getDamageTaken());  } public void getAcDataFromItem(ItemStack is) { if (!is.func_77942_o()) return;  NBTTagCompound nbt = is.func_77978_p(); setCommandForce(nbt.func_74779_i("MCH_Command")); if (MCH_Config.ItemFuel.prmBool) setFuel(nbt.func_74762_e("MCH_Fuel"));  if (MCH_Config.ItemDamage.prmBool) setDamageTaken(is.func_77960_j());  } public boolean func_70300_a(EntityPlayer player) { if (isUAV()) return super.func_70300_a(player);  if (!this.field_70128_L) { if (getSeatIdByEntity((Entity)player) >= 0) return (player.func_70068_e((Entity)this) <= 4096.0D);  return (player.func_70068_e((Entity)this) <= 64.0D); }  return false; } public void func_70108_f(Entity par1Entity) {} public void func_70024_g(double par1, double par3, double par5) {} public void func_70016_h(double par1, double par3, double par5) { this.velocityX = this.field_70159_w = par1; this.velocityY = this.field_70181_x = par3; this.velocityZ = this.field_70179_y = par5; } public void onFirstUpdate() { if (!this.field_70170_p.field_72995_K) { setCommonStatus(3, MCH_Config.InfinityAmmo.prmBool); setCommonStatus(4, MCH_Config.InfinityFuel.prmBool); }  } public void onRidePilotFirstUpdate() { if (this.field_70170_p.field_72995_K) if (W_Lib.isClientPlayer(getRiddenByEntity())) updateClientSettings(0);   Entity pilot = getRiddenByEntity(); if (pilot != null) { pilot.field_70177_z = getLastRiderYaw(); pilot.field_70125_A = getLastRiderPitch(); }  this.keepOnRideRotation = false; if (getAcInfo() != null) switchFreeLookModeClient((getAcInfo()).defaultFreelook);  } public double getCurrentThrottle() { return this.currentThrottle; } public void setCurrentThrottle(double throttle) { this.currentThrottle = throttle; } public void addCurrentThrottle(double throttle) { setCurrentThrottle(getCurrentThrottle() + throttle); } public double getPrevCurrentThrottle() { return this.prevCurrentThrottle; } public boolean canMouseRot() { return (!this.field_70128_L && getRiddenByEntity() != null && !isDestroyed()); } public boolean canUpdateYaw(Entity player) { if (getRidingEntity() != null) return false;  if (getCountOnUpdate() < 30) return false;  return (MCH_Lib.getBlockIdY((Entity)this, 3, -2) == 0); } public boolean canUpdatePitch(Entity player) { if (getCountOnUpdate() < 30) return false;  return (MCH_Lib.getBlockIdY((Entity)this, 3, -2) == 0); } public boolean canUpdateRoll(Entity player) { if (getRidingEntity() != null) return false;  if (getCountOnUpdate() < 30) return false;  return (MCH_Lib.getBlockIdY((Entity)this, 3, -2) == 0); } public boolean isOverridePlayerYaw() { return !isFreeLookMode(); } public boolean isOverridePlayerPitch() { return !isFreeLookMode(); } public double getAddRotationYawLimit() { return (getAcInfo() != null) ? (40.0D * (getAcInfo()).mobilityYaw) : 40.0D; } public double getAddRotationPitchLimit() { return (getAcInfo() != null) ? (40.0D * (getAcInfo()).mobilityPitch) : 40.0D; } public double getAddRotationRollLimit() { return (getAcInfo() != null) ? (40.0D * (getAcInfo()).mobilityRoll) : 40.0D; } public float getYawFactor() { return 1.0F; } public float getPitchFactor() { return 1.0F; } public float getRollFactor() { return 1.0F; } public abstract void onUpdateAngles(float paramFloat); public float getControlRotYaw(float mouseX, float mouseY, float tick) { return 0.0F; } public float getControlRotPitch(float mouseX, float mouseY, float tick) { return 0.0F; } public float getControlRotRoll(float mouseX, float mouseY, float tick) { return 0.0F; } public void setAngles(Entity player, boolean fixRot, float fixYaw, float fixPitch, float deltaX, float deltaY, float x, float y, float partialTicks) { if (partialTicks < 0.03F) partialTicks = 0.4F;  if (partialTicks > 0.9F) partialTicks = 0.6F;  this.lowPassPartialTicks.put(partialTicks); partialTicks = this.lowPassPartialTicks.getAvg(); float ac_pitch = getRotPitch(); float ac_yaw = getRotYaw(); float ac_roll = getRotRoll(); if (isFreeLookMode()) x = y = 0.0F;  float yaw = 0.0F; float pitch = 0.0F; float roll = 0.0F; if (canUpdateYaw(player)) { double limit = getAddRotationYawLimit(); yaw = getControlRotYaw(x, y, partialTicks); if (yaw < -limit) yaw = (float)-limit;  if (yaw > limit) yaw = (float)limit;  yaw = (float)((yaw * getYawFactor()) * 0.06D * partialTicks); }  if (canUpdatePitch(player)) { double limit = getAddRotationPitchLimit(); pitch = getControlRotPitch(x, y, partialTicks); if (pitch < -limit) pitch = (float)-limit;  if (pitch > limit) pitch = (float)limit;  pitch = (float)((-pitch * getPitchFactor()) * 0.06D * partialTicks); }  if (canUpdateRoll(player)) { double limit = getAddRotationRollLimit(); roll = getControlRotRoll(x, y, partialTicks); if (roll < -limit) roll = (float)-limit;  if (roll > limit) roll = (float)limit;  roll = roll * getRollFactor() * 0.06F * partialTicks; }  MCH_Math.FMatrix m_add = MCH_Math.newMatrix(); MCH_Math.MatTurnZ(m_add, roll / 180.0F * 3.1415927F); MCH_Math.MatTurnX(m_add, pitch / 180.0F * 3.1415927F); MCH_Math.MatTurnY(m_add, yaw / 180.0F * 3.1415927F); MCH_Math.MatTurnZ(m_add, (float)((getRotRoll() / 180.0F) * Math.PI)); MCH_Math.MatTurnX(m_add, (float)((getRotPitch() / 180.0F) * Math.PI)); MCH_Math.MatTurnY(m_add, (float)((getRotYaw() / 180.0F) * Math.PI)); MCH_Math.FVector3D v = MCH_Math.MatrixToEuler(m_add); if ((getAcInfo()).limitRotation) { v.x = MCH_Lib.RNG(v.x, (getAcInfo()).minRotationPitch, (getAcInfo()).maxRotationPitch); v.z = MCH_Lib.RNG(v.z, (getAcInfo()).minRotationRoll, (getAcInfo()).maxRotationRoll); }  if (v.z > 180.0F) v.z -= 360.0F;  if (v.z < -180.0F) v.z += 360.0F;  setRotYaw(v.y); setRotPitch(v.x); setRotRoll(v.z); onUpdateAngles(partialTicks); if ((getAcInfo()).limitRotation) { v.x = MCH_Lib.RNG(getRotPitch(), (getAcInfo()).minRotationPitch, (getAcInfo()).maxRotationPitch); v.z = MCH_Lib.RNG(getRotRoll(), (getAcInfo()).minRotationRoll, (getAcInfo()).maxRotationRoll); setRotPitch(v.x); setRotRoll(v.z); }  float RV = 180.0F; if (MathHelper.func_76135_e(getRotPitch()) > 90.0F) MCH_Lib.DbgLog(true, "MCH_EntityAircraft.setAngles Error:Pitch=%.1f", new Object[] { Float.valueOf(getRotPitch()) });  if (getRotRoll() > 180.0F) setRotRoll(getRotRoll() - 360.0F);  if (getRotRoll() < -180.0F) setRotRoll(getRotRoll() + 360.0F);  this.prevRotationRoll = getRotRoll(); this.field_70127_C = getRotPitch(); if (getRidingEntity() == null) this.field_70126_B = getRotYaw();  if (isOverridePlayerYaw() || fixRot) { if (getRidingEntity() == null) { player.field_70126_B = getRotYaw() + (fixRot ? fixYaw : 0.0F); } else { if (getRotYaw() - player.field_70177_z > 180.0F) player.field_70126_B += 360.0F;  if (getRotYaw() - player.field_70177_z < -180.0F) player.field_70126_B -= 360.0F;  }  player.field_70177_z = getRotYaw() + (fixRot ? fixYaw : 0.0F); } else { player.func_70082_c(deltaX, 0.0F); }  if (isOverridePlayerPitch() || fixRot) { player.field_70127_C = getRotPitch() + (fixRot ? fixPitch : 0.0F); player.field_70125_A = getRotPitch() + (fixRot ? fixPitch : 0.0F); } else { player.func_70082_c(0.0F, deltaY); }  if ((getRidingEntity() == null && ac_yaw != getRotYaw()) || ac_pitch != getRotPitch() || ac_roll != getRotRoll()) this.aircraftRotChanged = true;  } public boolean canSwitchSearchLight(Entity entity) { if (haveSearchLight()) if (getSeatIdByEntity(entity) <= 1) return true;   return false; } public boolean isSearchLightON() { return getCommonStatus(6); } public void setSearchLight(boolean onoff) { setCommonStatus(6, onoff); } public boolean haveSearchLight() { return (getAcInfo() != null && (getAcInfo()).searchLights.size() > 0); } public float getSearchLightValue(Entity entity) { if (haveSearchLight() && isSearchLightON()) for (MCH_AircraftInfo.SearchLight sl : (getAcInfo()).searchLights) { Vec3 pos = getTransformedPosition(sl.pos); double dist = entity.func_70092_e(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c); if (dist > 2.0D && dist < (sl.height * sl.height + 20.0F)) { double cx = entity.field_70165_t - pos.field_72450_a; double cy = entity.field_70163_u - pos.field_72448_b; double cz = entity.field_70161_v - pos.field_72449_c; double h = 0.0D, v = 0.0D; if (!sl.fixDir) { Vec3 vv = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -this.lastSearchLightYaw + sl.yaw, -this.lastSearchLightPitch + sl.pitch, -getRotRoll()); h = MCH_Lib.getPosAngle(vv.field_72450_a, vv.field_72449_c, cx, cz); v = Math.atan2(cy, Math.sqrt(cx * cx + cz * cz)) * 180.0D / Math.PI; v = Math.abs(v + this.lastSearchLightPitch + sl.pitch); } else { float stRot = 0.0F; if (sl.steering) stRot = this.rotYawWheel * sl.stRot;  Vec3 vv = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -getRotYaw() + sl.yaw + stRot, -getRotPitch() + sl.pitch, -getRotRoll()); h = MCH_Lib.getPosAngle(vv.field_72450_a, vv.field_72449_c, cx, cz); v = Math.atan2(cy, Math.sqrt(cx * cx + cz * cz)) * 180.0D / Math.PI; v = Math.abs(v + getRotPitch() + sl.pitch); }  float angle = sl.angle * 3.0F; if (h < angle && v < angle) { float value = 0.0F; if (h + v < angle) value = (float)(1440.0D * (1.0D - (h + v) / angle));  return (value <= 240.0F) ? value : 240.0F; }  }  }   return 0.0F; } public abstract void onUpdateAircraft(); public void func_70071_h_() { if (getCountOnUpdate() < 2) this.prevPosition.clear(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));  this.prevCurrentThrottle = getCurrentThrottle(); this.lastBBDamageFactor = 1.0F; updateControl(); checkServerNoMove(); onUpdate_RidingEntity(); Iterator<UnmountReserve> itr = this.listUnmountReserve.iterator(); while (itr.hasNext()) { UnmountReserve ur = itr.next(); if (ur.entity != null && !ur.entity.field_70128_L) { ur.entity.func_70107_b(ur.posX, ur.posY, ur.posZ); ur.entity.field_70143_R = this.field_70143_R; }  if (ur.cnt > 0) ur.cnt--;  if (ur.cnt == 0) itr.remove();  }  if (isDestroyed() && getCountOnUpdate() % 20 == 0) for (int sid = 0; sid < getSeatNum() + 1; sid++) { Entity entity = getEntityBySeatId(sid); if (entity != null) if (sid != 0 || !isUAV()) if (MCH_Config.applyDamageVsEntity(entity, DamageSource.field_76372_a, 1.0F) > 0.0F) entity.func_70015_d(5);    }   if (this.aircraftRotChanged || this.aircraftRollRev) if (this.field_70170_p.field_72995_K && getRiddenByEntity() != null) { MCH_PacketIndRotation.send(this); this.aircraftRotChanged = false; this.aircraftRollRev = false; }   if (!this.field_70170_p.field_72995_K) if ((int)this.prevRotationRoll != (int)getRotRoll()) { float roll = MathHelper.func_76142_g(getRotRoll()); func_70096_w().func_75692_b(26, new Short((short)(int)roll)); }   this.prevRotationRoll = getRotRoll(); if (!this.field_70170_p.field_72995_K) if (isTargetDrone() && !isDestroyed()) if (getCountOnUpdate() > 20 && !canUseFuel()) { setDamageTaken(getMaxHP()); destroyAircraft(); MCH_Explosion.newExplosion(this.field_70170_p, null, null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, 2.0F, true, true, true, true, 5); }    if (this.field_70170_p.field_72995_K && getAcInfo() != null) if (getHP() <= 0 && getDespawnCount() <= 0) destroyAircraft();   if (!this.field_70170_p.field_72995_K && getDespawnCount() > 0) { setDespawnCount(getDespawnCount() - 1); if (getDespawnCount() <= 1) setDead(true);  }  super.func_70071_h_(); if (func_70021_al() != null) for (Entity entity : func_70021_al()) { if (entity != null) entity.func_70071_h_();  }   updateNoCollisionEntities(); updateUAV(); supplyFuel(); supplyAmmoToOtherAircraft(); updateFuel(); repairOtherAircraft(); if (this.modeSwitchCooldown > 0) this.modeSwitchCooldown--;  if (this.lastRiddenByEntity == null && getRiddenByEntity() != null) onRidePilotFirstUpdate();  if (this.countOnUpdate == 0) onFirstUpdate();  this.countOnUpdate++; if (this.countOnUpdate >= 1000000) this.countOnUpdate = 1;  if (this.field_70170_p.field_72995_K) this.commonStatus = func_70096_w().func_75679_c(23);  this.field_70143_R = 0.0F; if (this.field_70153_n != null) this.field_70153_n.field_70143_R = 0.0F;  if (this.missileDetector != null) this.missileDetector.update();  if (this.soundUpdater != null) this.soundUpdater.update();  if (getTowChainEntity() != null && (getTowChainEntity()).field_70128_L) setTowChainEntity(null);  updateSupplyAmmo(); autoRepair(); int ft = getFlareTick(); this.flareDv.update(); if (!this.field_70170_p.field_72995_K && getFlareTick() == 0 && ft != 0) setCommonStatus(0, false);  Entity e = getRiddenByEntity(); if (e != null && !e.field_70128_L && !isDestroyed()) { this.lastRiderYaw = e.field_70177_z; this.prevLastRiderYaw = e.field_70126_B; this.lastRiderPitch = e.field_70125_A; this.prevLastRiderPitch = e.field_70127_C; } else if (getTowedChainEntity() != null || this.field_70154_o != null) { this.lastRiderYaw = this.field_70177_z; this.prevLastRiderYaw = this.field_70126_B; this.lastRiderPitch = this.field_70125_A; this.prevLastRiderPitch = this.field_70127_C; }  updatePartCameraRotate(); updatePartWheel(); updatePartCrawlerTrack(); updatePartLightHatch(); regenerationMob(); if (getRiddenByEntity() == null && this.lastRiddenByEntity != null) unmountEntity();  updateExtraBoundingBox(); boolean prevOnGround = this.field_70122_E; double prevMotionY = this.field_70181_x; onUpdateAircraft(); if (getAcInfo() != null) updateParts(getPartStatus());  if (this.recoilCount > 0) this.recoilCount--;  if (!W_Entity.isEqual(MCH_MOD.proxy.getClientPlayer(), getRiddenByEntity())) updateRecoil(1.0F);  if (!this.field_70170_p.field_72995_K && isDestroyed() && !isExploded()) if (!prevOnGround && this.field_70122_E && prevMotionY < -0.2D) { explosionByCrash(prevMotionY); this.damageSinceDestroyed = getMaxHP(); }   onUpdate_PartRotation(); onUpdate_ParticleSmoke(); updateSeatsPosition(this.field_70165_t, this.field_70163_u, this.field_70161_v, false); updateHitBoxPosition(); onUpdate_CollisionGroundDamage(); onUpdate_UnmountCrew(); onUpdate_Repelling(); checkRideRack(); if (this.lastRidingEntity == null && getRidingEntity() != null) onRideEntity(getRidingEntity());  this.lastRiddenByEntity = getRiddenByEntity(); this.lastRidingEntity = getRidingEntity(); this.prevPosition.put(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)); } private void updateNoCollisionEntities() { if (this.field_70170_p.field_72995_K) return;  if (getCountOnUpdate() % 10 != 0) return;  for (int i = 0; i < 1 + getSeatNum(); i++) { Entity e = getEntityBySeatId(i); if (e != null) this.noCollisionEntities.put(e, Integer.valueOf(8));  }  if (getTowChainEntity() != null && (getTowChainEntity()).towedEntity != null) this.noCollisionEntities.put((getTowChainEntity()).towedEntity, Integer.valueOf(60));  if (getTowedChainEntity() != null && (getTowedChainEntity()).towEntity != null) this.noCollisionEntities.put((getTowedChainEntity()).towEntity, Integer.valueOf(60));  if (this.field_70154_o instanceof MCH_EntitySeat) { mcheli.aircraft.MCH_EntityAircraft ac = ((MCH_EntitySeat)this.field_70154_o).getParent(); if (ac != null) this.noCollisionEntities.put(ac, Integer.valueOf(60));  } else if (this.field_70154_o != null) { this.noCollisionEntities.put(this.field_70154_o, Integer.valueOf(60)); }  for (Entity entity : this.noCollisionEntities.keySet()) this.noCollisionEntities.put(entity, Integer.valueOf(((Integer)this.noCollisionEntities.get(entity)).intValue() - 1));  for (Iterator<Integer> key = this.noCollisionEntities.values().iterator(); key.hasNext();) { if (((Integer)key.next()).intValue() <= 0) key.remove();  }  } public MCH_EntityAircraft(World world) { super(world);
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
/*      */ 
/*      */     
/* 5492 */     this.switchSeat = false; this.isRequestedSyncStatus = false; setAcInfo(null); this.commonStatus = 0; this.dropContentsWhenDead = false; this.field_70158_ak = true; this.flareDv = new MCH_Flare(world, this); this.currentFlareIndex = 0; this.entityRadar = new MCH_Radar(world); this.radarRotate = 0; this.currentWeaponID = new int[0]; this.aircraftPosRotInc = 0; this.aircraftX = 0.0D; this.aircraftY = 0.0D; this.aircraftZ = 0.0D; this.aircraftYaw = 0.0D; this.aircraftPitch = 0.0D; this.currentSpeed = 0.0D; setCurrentThrottle(0.0D); this.currentFuel = 0; this.cs_dismountAll = false; this.cs_heliAutoThrottleDown = true; this.cs_planeAutoThrottleDown = false; this.field_70155_l = MCH_Config.RenderDistanceWeight.prmDouble; setCommonUniqueId(""); this.seatSearchCount = 0; this.seatsInfo = null; this.seats = new MCH_EntitySeat[0]; this.pilotSeat = new MCH_EntityHitBox(world, this, 1.0F, 1.0F); this.pilotSeat.parent = this; this.partEntities = new Entity[] { (Entity)this.pilotSeat }; setTextureName(""); this.camera = new MCH_Camera(world, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v); setCameraId(0); this.lastRiddenByEntity = null; this.lastRidingEntity = null; this.soundUpdater = MCH_MOD.proxy.CreateSoundUpdater(this); this.countOnUpdate = 0; setTowChainEntity(null); this.dummyWeapon = new MCH_WeaponSet((MCH_WeaponBase)new MCH_WeaponDummy(this.field_70170_p, Vec3.func_72443_a(0.0D, 0.0D, 0.0D), 0.0F, 0.0F, "", null)); this.useWeaponStat = 0; this.hitStatus = 0; this.repairCount = 0; this.beforeDamageTaken = 0; this.timeSinceHit = 0; setDespawnCount(0); this.missileDetector = new MCH_MissileDetector(this, world); this.uavStation = null; this.modeSwitchCooldown = 0; this.partHatch = null; this.partCanopy = null; this.partLandingGear = null; this.weaponBays = new WeaponBay[0]; this.rotPartRotation = new float[0]; this.prevRotPartRotation = new float[0]; this.lastRiderYaw = 0.0F; this.prevLastRiderYaw = 0.0F; this.lastRiderPitch = 0.0F; this.prevLastRiderPitch = 0.0F; this.rotationRoll = 0.0F; this.prevRotationRoll = 0.0F; this.lowPassPartialTicks = new MCH_LowPassFilterFloat(10); this.extraBoundingBox = new MCH_BoundingBox[0]; W_Reflection.setBoundingBox((Entity)this, (AxisAlignedBB)new MCH_AircraftBoundingBox(this)); this.lastBBDamageFactor = 1.0F; this.inventory = new MCH_AircraftInventory(this); this.fuelConsumption = 0.0D; this.fuelSuppliedCount = 0; this.canRideRackStatus = false; this.isParachuting = false; this.prevPosition = new MCH_Queue(10, Vec3.func_72443_a(0.0D, 0.0D, 0.0D)); this.lastSearchLightYaw = this.lastSearchLightPitch = 0.0F; }
/*      */   public void updateControl() { if (!this.field_70170_p.field_72995_K) { setCommonStatus(7, this.moveLeft); setCommonStatus(8, this.moveRight); setCommonStatus(9, this.throttleUp); setCommonStatus(10, this.throttleDown); } else if (MCH_MOD.proxy.getClientPlayer() != getRiddenByEntity()) { this.moveLeft = getCommonStatus(7); this.moveRight = getCommonStatus(8); this.throttleUp = getCommonStatus(9); this.throttleDown = getCommonStatus(10); }  }
/*      */   public void updateRecoil(float partialTicks) { if (this.recoilCount > 0 && this.recoilCount >= 12) { float pitch = MathHelper.func_76134_b((float)((this.recoilYaw - getRotRoll()) * Math.PI / 180.0D)); float roll = MathHelper.func_76126_a((float)((this.recoilYaw - getRotRoll()) * Math.PI / 180.0D)); float recoil = MathHelper.func_76134_b((float)((this.recoilCount * 6) * Math.PI / 180.0D)) * this.recoilValue; setRotPitch(getRotPitch() + recoil * pitch * partialTicks); setRotRoll(getRotRoll() + recoil * roll * partialTicks); }  }
/* 5495 */   private void updatePartLightHatch() { this.prevRotLightHatch = this.rotLightHatch; if (isSearchLightON()) { this.rotLightHatch = (float)(this.rotLightHatch + 0.5D); } else { this.rotLightHatch = (float)(this.rotLightHatch - 0.5D); }  if (this.rotLightHatch > 1.0F) this.rotLightHatch = 1.0F;  if (this.rotLightHatch < 0.0F) this.rotLightHatch = 0.0F;  } public void updateExtraBoundingBox() { for (MCH_BoundingBox bb : this.extraBoundingBox) bb.updatePosition(this.field_70165_t, this.field_70163_u, this.field_70161_v, getRotYaw(), getRotPitch(), getRotRoll());  } public void updatePartWheel() { if (!this.field_70170_p.field_72995_K) return;  if (getAcInfo() == null) return;  this.prevRotWheel = this.rotWheel; this.prevRotYawWheel = this.rotYawWheel; float LEN = 1.0F; float MIN = 0.0F; double throttle = getCurrentThrottle(); double pivotTurnThrottle = (getAcInfo()).pivotTurnThrottle; if (pivotTurnThrottle <= 0.0D) { pivotTurnThrottle = 1.0D; } else { pivotTurnThrottle *= 0.10000000149011612D; }  boolean localMoveLeft = this.moveLeft; boolean localMoveRight = this.moveRight; if ((getAcInfo()).enableBack && this.throttleBack > 0.01D && throttle <= 0.0D) throttle = (-this.throttleBack * 15.0F);  if (localMoveLeft && !localMoveRight) { this.rotYawWheel += 0.1F; if (this.rotYawWheel > 1.0F) this.rotYawWheel = 1.0F;  } else if (!localMoveLeft && localMoveRight) { this.rotYawWheel -= 0.1F; if (this.rotYawWheel < -1.0F) this.rotYawWheel = -1.0F;  } else { this.rotYawWheel *= 0.9F; }  this.rotWheel = (float)(this.rotWheel + throttle * (getAcInfo()).partWheelRot); if (this.rotWheel >= 360.0F) { this.rotWheel -= 360.0F; this.prevRotWheel -= 360.0F; } else if (this.rotWheel < 0.0F) { this.rotWheel += 360.0F; this.prevRotWheel += 360.0F; }  } public void updatePartCrawlerTrack() { if (!this.field_70170_p.field_72995_K) return;  if (getAcInfo() == null) return;  this.prevRotTrackRoller[0] = this.rotTrackRoller[0]; this.prevRotTrackRoller[1] = this.rotTrackRoller[1]; this.prevRotCrawlerTrack[0] = this.rotCrawlerTrack[0]; this.prevRotCrawlerTrack[1] = this.rotCrawlerTrack[1]; float LEN = 1.0F; float MIN = 0.0F; double throttle = getCurrentThrottle(); double pivotTurnThrottle = (getAcInfo()).pivotTurnThrottle; if (pivotTurnThrottle <= 0.0D) { pivotTurnThrottle = 1.0D; } else { pivotTurnThrottle *= 0.10000000149011612D; }  boolean localMoveLeft = this.moveLeft; boolean localMoveRight = this.moveRight; int dir = 1; if ((getAcInfo()).enableBack && this.throttleBack > 0.0F && throttle <= 0.0D) { throttle = (-this.throttleBack * 5.0F); if (localMoveLeft != localMoveRight) { boolean tmp = localMoveLeft; localMoveLeft = localMoveRight; localMoveRight = tmp; dir = -1; }  }  if (localMoveLeft && !localMoveRight) { throttle = 0.2D * dir; this.throttleCrawlerTrack[0] = (float)(this.throttleCrawlerTrack[0] + throttle); this.throttleCrawlerTrack[1] = (float)(this.throttleCrawlerTrack[1] - pivotTurnThrottle * throttle); } else if (!localMoveLeft && localMoveRight) { throttle = 0.2D * dir; this.throttleCrawlerTrack[0] = (float)(this.throttleCrawlerTrack[0] - pivotTurnThrottle * throttle); this.throttleCrawlerTrack[1] = (float)(this.throttleCrawlerTrack[1] + throttle); } else { if (throttle > 0.2D) throttle = 0.2D;  if (throttle < -0.2D) throttle = -0.2D;  this.throttleCrawlerTrack[0] = (float)(this.throttleCrawlerTrack[0] + throttle); this.throttleCrawlerTrack[1] = (float)(this.throttleCrawlerTrack[1] + throttle); }  for (int i = 0; i < 2; i++) { if (this.throttleCrawlerTrack[i] < -0.72F) { this.throttleCrawlerTrack[i] = -0.72F; } else if (this.throttleCrawlerTrack[i] > 0.72F) { this.throttleCrawlerTrack[i] = 0.72F; }  this.rotTrackRoller[i] = this.rotTrackRoller[i] + this.throttleCrawlerTrack[i] * (getAcInfo()).trackRollerRot; if (this.rotTrackRoller[i] >= 360.0F) { this.rotTrackRoller[i] = this.rotTrackRoller[i] - 360.0F; this.prevRotTrackRoller[i] = this.prevRotTrackRoller[i] - 360.0F; } else if (this.rotTrackRoller[i] < 0.0F) { this.rotTrackRoller[i] = this.rotTrackRoller[i] + 360.0F; this.prevRotTrackRoller[i] = this.prevRotTrackRoller[i] + 360.0F; }  this.rotCrawlerTrack[i] = this.rotCrawlerTrack[i] - this.throttleCrawlerTrack[i]; while (this.rotCrawlerTrack[i] >= 1.0F) { this.rotCrawlerTrack[i] = this.rotCrawlerTrack[i] - 1.0F; this.prevRotCrawlerTrack[i] = this.prevRotCrawlerTrack[i] - 1.0F; }  for (; this.rotCrawlerTrack[i] < 0.0F; this.rotCrawlerTrack[i] = this.rotCrawlerTrack[i] + 1.0F); for (; this.prevRotCrawlerTrack[i] < 0.0F; this.prevRotCrawlerTrack[i] = this.prevRotCrawlerTrack[i] + 1.0F); this.throttleCrawlerTrack[i] = (float)(this.throttleCrawlerTrack[i] * 0.75D); }  } public void checkServerNoMove() { if (!this.field_70170_p.field_72995_K) { double moti = this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y; if (moti < 1.0E-4D) { if (this.serverNoMoveCount < 20) { this.serverNoMoveCount++; if (this.serverNoMoveCount >= 20) { this.serverNoMoveCount = 0; if (this.field_70170_p instanceof WorldServer) ((WorldServer)this.field_70170_p).func_73039_n().func_151247_a((Entity)this, (Packet)new S12PacketEntityVelocity(func_145782_y(), 0.0D, 0.0D, 0.0D));  }  }  } else { this.serverNoMoveCount = 0; }  }  } public boolean haveRotPart() { if (this.field_70170_p.field_72995_K && getAcInfo() != null) if (this.rotPartRotation.length > 0 && this.rotPartRotation.length == (getAcInfo()).partRotPart.size()) return true;   return false; } public void onUpdate_PartRotation() { if (haveRotPart()) for (int i = 0; i < this.rotPartRotation.length; i++) { this.prevRotPartRotation[i] = this.rotPartRotation[i]; if ((!isDestroyed() && ((MCH_AircraftInfo.RotPart)(getAcInfo()).partRotPart.get(i)).rotAlways) || getRiddenByEntity() != null) { this.rotPartRotation[i] = this.rotPartRotation[i] + ((MCH_AircraftInfo.RotPart)(getAcInfo()).partRotPart.get(i)).rotSpeed; if (this.rotPartRotation[i] < 0.0F) this.rotPartRotation[i] = this.rotPartRotation[i] + 360.0F;  if (this.rotPartRotation[i] >= 360.0F) this.rotPartRotation[i] = this.rotPartRotation[i] - 360.0F;  }  }   } public void onRideEntity(Entity ridingEntity) {} public int getAlt(double px, double py, double pz) { int i; for (i = 0; i < 256; i++) { if (py - i <= 0.0D || (py - i < 256.0D && 0 != W_WorldFunc.getBlockId(this.field_70170_p, (int)px, (int)py - i, (int)pz))) break;  }  return i; } public boolean canRepelling(Entity entity) { if (isRepelling()) if (this.tickRepelling > 50) return true;   return false; } private void onUpdate_Repelling() { if (getAcInfo() != null && getAcInfo().haveRepellingHook()) if (isRepelling()) { int alt = getAlt(this.field_70165_t, this.field_70163_u, this.field_70161_v); if (this.ropesLength > -50.0F && this.ropesLength > -alt) this.ropesLength = (float)(this.ropesLength - (this.field_70170_p.field_72995_K ? 0.30000001192092896D : 0.25D));  } else { this.ropesLength = 0.0F; }   onUpdate_UnmountCrewRepelling(); } private void onUpdate_UnmountCrewRepelling() { if (getAcInfo() == null) return;  if (!isRepelling()) { this.tickRepelling = 0; return; }  if (this.tickRepelling < 60) { this.tickRepelling++; return; }  if (this.field_70170_p.field_72995_K) return;  for (int ropeIdx = 0; ropeIdx < (getAcInfo()).repellingHooks.size(); ropeIdx++) { MCH_AircraftInfo.RepellingHook hook = (getAcInfo()).repellingHooks.get(ropeIdx); if (getCountOnUpdate() % hook.interval == 0) for (int i = 1; i < getSeatNum(); i++) { MCH_EntitySeat seat = getSeat(i); if (seat != null && seat.field_70153_n != null && !W_EntityPlayer.isPlayer(seat.field_70153_n)) if (!(getSeatInfo(i + 1) instanceof MCH_SeatRackInfo)) { Entity entity = seat.field_70153_n; Vec3 dropPos = getTransformedPosition(hook.pos, (Vec3)this.prevPosition.oldest()); seat.field_70165_t = dropPos.field_72450_a; seat.field_70163_u = dropPos.field_72448_b - 2.0D; seat.field_70161_v = dropPos.field_72449_c; entity.func_70078_a(null); unmountEntityRepelling(entity, dropPos, ropeIdx); this.lastUsedRopeIndex = ropeIdx; break; }   }   }  } public void unmountEntityRepelling(Entity entity, Vec3 dropPos, int ropeIdx) { entity.field_70165_t = dropPos.field_72450_a; entity.field_70163_u = dropPos.field_72448_b - 2.0D; entity.field_70161_v = dropPos.field_72449_c; MCH_EntityHide hideEntity = new MCH_EntityHide(this.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v); hideEntity.setParent(this, entity, ropeIdx); hideEntity.field_70159_w = entity.field_70159_w = 0.0D; hideEntity.field_70181_x = entity.field_70181_x = 0.0D; hideEntity.field_70179_y = entity.field_70179_y = 0.0D; hideEntity.field_70143_R = entity.field_70143_R = 0.0F; this.field_70170_p.func_72838_d((Entity)hideEntity); } private void onUpdate_UnmountCrew() { if (getAcInfo() == null) return;  if (this.isParachuting) if (MCH_Lib.getBlockIdY((Entity)this, 3, -10) != 0) { stopUnmountCrew(); } else if (!haveHatch() || getHatchRotation() > 89.0F) { if (getCountOnUpdate() % (getAcInfo()).mobDropOption.interval == 0) if (!unmountCrew(true)) stopUnmountCrew();   }   } public void unmountAircraft() { Vec3 v = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v); if (this.field_70154_o instanceof MCH_EntitySeat) { mcheli.aircraft.MCH_EntityAircraft ac = ((MCH_EntitySeat)this.field_70154_o).getParent(); MCH_SeatInfo seatInfo = ac.getSeatInfo((Entity)this); if (seatInfo instanceof MCH_SeatRackInfo) { v = ((MCH_SeatRackInfo)seatInfo).getEntryPos(); v = ac.getTransformedPosition(v); }  } else if (this.field_70154_o instanceof net.minecraft.entity.item.EntityMinecartEmpty) { this.dismountedUserCtrl = true; }  func_70012_b(v.field_72450_a, v.field_72448_b, v.field_72449_c, getRotYaw(), getRotPitch()); func_70078_a(null); func_70012_b(v.field_72450_a, v.field_72448_b, v.field_72449_c, getRotYaw(), getRotPitch()); } public boolean canUnmount(Entity entity) { if (getAcInfo() == null) return false;  if (!(getAcInfo()).isEnableParachuting) return false;  if (getSeatIdByEntity(entity) <= 1) return false;  if (haveHatch() && getHatchRotation() < 89.0F) return false;  return true; } public void unmount(Entity entity) { if (getAcInfo() == null) return;  if (canRepelling(entity) && getAcInfo().haveRepellingHook()) { MCH_EntitySeat seat = getSeatByEntity(entity); if (seat != null) { this.lastUsedRopeIndex = (this.lastUsedRopeIndex + 1) % (getAcInfo()).repellingHooks.size(); Vec3 dropPos = getTransformedPosition(((MCH_AircraftInfo.RepellingHook)(getAcInfo()).repellingHooks.get(this.lastUsedRopeIndex)).pos, (Vec3)this.prevPosition.oldest()); dropPos = dropPos.func_72441_c(0.0D, -2.0D, 0.0D); seat.field_70165_t = dropPos.field_72450_a; seat.field_70163_u = dropPos.field_72448_b; seat.field_70161_v = dropPos.field_72449_c; entity.func_70078_a(null); entity.field_70165_t = dropPos.field_72450_a; entity.field_70163_u = dropPos.field_72448_b; entity.field_70161_v = dropPos.field_72449_c; unmountEntityRepelling(entity, dropPos, this.lastUsedRopeIndex); } else { MCH_Lib.Log((Entity)this, "Error:MCH_EntityAircraft.unmount seat=null : " + entity, new Object[0]); }  } else if (canUnmount(entity)) { MCH_EntitySeat seat = getSeatByEntity(entity); if (seat != null) { Vec3 dropPos = getTransformedPosition((getAcInfo()).mobDropOption.pos, (Vec3)this.prevPosition.oldest()); seat.field_70165_t = dropPos.field_72450_a; seat.field_70163_u = dropPos.field_72448_b; seat.field_70161_v = dropPos.field_72449_c; entity.func_70078_a(null); entity.field_70165_t = dropPos.field_72450_a; entity.field_70163_u = dropPos.field_72448_b; entity.field_70161_v = dropPos.field_72449_c; dropEntityParachute(entity); } else { MCH_Lib.Log((Entity)this, "Error:MCH_EntityAircraft.unmount seat=null : " + entity, new Object[0]); }  }  } public boolean canParachuting(Entity entity) { if (getAcInfo() != null && (getAcInfo()).isEnableParachuting) if (getSeatIdByEntity(entity) > 1) if (MCH_Lib.getBlockIdY((Entity)this, 3, -13) == 0) { if (haveHatch() && getHatchRotation() > 89.0F) return (getSeatIdByEntity(entity) > 1);  return (getSeatIdByEntity(entity) > 1); }    return false; } public void onUpdate_RidingEntity() { if (!this.field_70170_p.field_72995_K && this.waitMountEntity == 0 && getCountOnUpdate() > 20) if (canMountWithNearEmptyMinecart()) mountWithNearEmptyMinecart();   if (this.waitMountEntity > 0) this.waitMountEntity--;  if (!this.field_70170_p.field_72995_K && getRidingEntity() != null) { setRotRoll(getRotRoll() * 0.9F); setRotPitch(getRotPitch() * 0.95F); Entity re = getRidingEntity(); float target = MathHelper.func_76142_g(re.field_70177_z + 90.0F); if (target - this.field_70177_z > 180.0F) target -= 360.0F;  if (target - this.field_70177_z < -180.0F) target += 360.0F;  if (this.field_70173_aa % 2 == 0); float dist = 50.0F * (float)re.func_70092_e(re.field_70169_q, re.field_70167_r, re.field_70166_s); if (dist > 0.001D) { dist = MathHelper.func_76133_a(dist); float distYaw = MCH_Lib.RNG(target - this.field_70177_z, -dist, dist); this.field_70177_z += distYaw; }  double bkPosX = this.field_70165_t; double bkPosY = this.field_70163_u; double bkPosZ = this.field_70161_v; if ((getRidingEntity()).field_70128_L) { func_70078_a(null); this.waitMountEntity = 20; } else if (getCurrentThrottle() > 0.8D) { this.field_70159_w = (getRidingEntity()).field_70159_w; this.field_70181_x = (getRidingEntity()).field_70181_x; this.field_70179_y = (getRidingEntity()).field_70179_y; func_70078_a(null); this.waitMountEntity = 20; }  this.field_70165_t = bkPosX; this.field_70163_u = bkPosY; this.field_70161_v = bkPosZ; }  } public void explosionByCrash(double prevMotionY) { float exp = (getAcInfo() != null) ? ((getAcInfo()).maxFuel / 400.0F) : 2.0F; if (exp < 1.0F) exp = 1.0F;  if (exp > 15.0F) exp = 15.0F;  MCH_Lib.DbgLog(this.field_70170_p, "OnGroundAfterDestroyed:motionY=%.3f", new Object[] { Float.valueOf((float)prevMotionY) }); MCH_Explosion.newExplosion(this.field_70170_p, null, null, this.field_70165_t, this.field_70163_u, this.field_70161_v, exp, (exp >= 2.0F) ? (exp * 0.5F) : 1.0F, true, true, true, true, 5); } public void onUpdate_CollisionGroundDamage() { if (isDestroyed()) return;  if (MCH_Lib.getBlockIdY((Entity)this, 3, -3) > 0) if (!this.field_70170_p.field_72995_K) { float roll = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotRoll())); float pitch = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotPitch())); if (roll > getGiveDamageRot() || pitch > getGiveDamageRot()) { float dmg = MathHelper.func_76135_e(roll) + MathHelper.func_76135_e(pitch); if (dmg < 90.0F) { dmg *= 0.4F * (float)func_70011_f(this.field_70169_q, this.field_70167_r, this.field_70166_s); } else { dmg *= 0.4F; }  if (dmg > 1.0F && this.field_70146_Z.nextInt(4) == 0) func_70097_a(DamageSource.field_76368_d, dmg);  }  }   if (getCountOnUpdate() % 30 == 0) if (getAcInfo() == null || !(getAcInfo()).isFloat) if (MCH_Lib.isBlockInWater(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 1.5D + (getAcInfo()).submergedDamageHeight), (int)(this.field_70161_v + 0.5D))) { int hp = getMaxHP() / 10; if (hp <= 0) hp = 1;  attackEntityFrom(DamageSource.field_76368_d, hp); }    } public float getGiveDamageRot() { return 40.0F; } public void applyServerPositionAndRotation() { double rpinc = this.aircraftPosRotInc; double yaw = MathHelper.func_76138_g(this.aircraftYaw - getRotYaw()); double roll = MathHelper.func_76138_g(getServerRoll() - getRotRoll()); if (!isDestroyed() && (!W_Lib.isClientPlayer(getRiddenByEntity()) || getRidingEntity() != null)) { setRotYaw((float)(getRotYaw() + yaw / rpinc)); setRotPitch((float)(getRotPitch() + (this.aircraftPitch - getRotPitch()) / rpinc)); setRotRoll((float)(getRotRoll() + roll / rpinc)); }  func_70107_b(this.field_70165_t + (this.aircraftX - this.field_70165_t) / rpinc, this.field_70163_u + (this.aircraftY - this.field_70163_u) / rpinc, this.field_70161_v + (this.aircraftZ - this.field_70161_v) / rpinc); func_70101_b(getRotYaw(), getRotPitch()); this.aircraftPosRotInc--; } protected void autoRepair() { if (this.timeSinceHit > 0) this.timeSinceHit--;  if (getMaxHP() <= 0) return;  if (!isDestroyed()) if (getDamageTaken() > this.beforeDamageTaken) { this.repairCount = 600; } else if (this.repairCount > 0) { this.repairCount--; } else { this.repairCount = 40; double hpp = getHP() / getMaxHP(); if (hpp >= MCH_Config.AutoRepairHP.prmDouble) repair(getMaxHP() / 100);  }   this.beforeDamageTaken = getDamageTaken(); } public boolean repair(int tpd) { if (tpd < 1) tpd = 1;  int damage = getDamageTaken(); if (damage > 0) { if (!this.field_70170_p.field_72995_K) setDamageTaken(damage - tpd);  return true; }  return false; } public void repairOtherAircraft() { float range = (getAcInfo() != null) ? (getAcInfo()).repairOtherVehiclesRange : 0.0F; if (range <= 0.0F) return;  if (!this.field_70170_p.field_72995_K && getCountOnUpdate() % 20 == 0) { List<mcheli.aircraft.MCH_EntityAircraft> list = this.field_70170_p.func_72872_a(mcheli.aircraft.MCH_EntityAircraft.class, func_70046_E().func_72314_b(range, range, range)); for (int i = 0; i < list.size(); i++) { mcheli.aircraft.MCH_EntityAircraft ac = list.get(i); if (!W_Entity.isEqual((Entity)this, (Entity)ac)) if (ac.getHP() < ac.getMaxHP()) ac.setDamageTaken(ac.getDamageTaken() - (getAcInfo()).repairOtherVehiclesValue);   }  }  } protected void regenerationMob() { if (isDestroyed()) return;  if (this.field_70170_p.field_72995_K) return;  if (getAcInfo() != null && (getAcInfo()).regeneration && getRiddenByEntity() != null) { MCH_EntitySeat[] st = getSeats(); for (MCH_EntitySeat s : st) { if (s != null && !s.field_70128_L) { Entity e = s.field_70153_n; if (W_Lib.isEntityLivingBase(e) && !e.field_70128_L) { PotionEffect pe = W_Entity.getActivePotionEffect(e, Potion.field_76428_l); if (pe == null || (pe != null && pe.func_76459_b() < 500)) W_Entity.addPotionEffect(e, new PotionEffect(Potion.field_76428_l.field_76415_H, 250, 0, true));  }  }  }  }  } public double getWaterDepth() { byte b0 = 5; double d0 = 0.0D; for (int i = 0; i < b0; i++) { double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 0) / b0 - 0.125D; double d2 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 1) / b0 - 0.125D; d1 += (getAcInfo()).floatOffset; d2 += (getAcInfo()).floatOffset; AxisAlignedBB axisalignedbb = W_AxisAlignedBB.getAABB(this.field_70121_D.field_72340_a, d1, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d2, this.field_70121_D.field_72334_f); if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h)) d0 += 1.0D / b0;  }  return d0; } public int getCountOnUpdate() { return this.countOnUpdate; } public boolean canSupply() { if (!canFloatWater()) return (MCH_Lib.getBlockIdY((Entity)this, 1, -3) != 0 && !func_70090_H());  return (MCH_Lib.getBlockIdY((Entity)this, 1, -3) != 0); } public void setFuel(int fuel) { if (!this.field_70170_p.field_72995_K) { if (fuel < 0) fuel = 0;  if (fuel > getMaxFuel()) fuel = getMaxFuel();  if (fuel != getFuel()) func_70096_w().func_75692_b(25, Integer.valueOf(fuel));  }  } public int getFuel() { return func_70096_w().func_75679_c(25); } public boolean interactFirst(EntityPlayer player, boolean ss) { this.switchSeat = ss;
/* 5496 */     boolean ret = func_130002_c(player);
/* 5497 */     this.switchSeat = false;
/* 5498 */     return ret; } public float getFuelP() { int m = getMaxFuel(); if (m == 0) return 0.0F;  return getFuel() / m; } public boolean canUseFuel(boolean checkOtherSeet) { return (getMaxFuel() <= 0 || getFuel() > 1 || isInfinityFuel(getRiddenByEntity(), checkOtherSeet)); } public boolean canUseFuel() { return canUseFuel(false); } public int getMaxFuel() { return (getAcInfo() != null) ? (getAcInfo()).maxFuel : 0; } public void supplyFuel() { float range = (getAcInfo() != null) ? (getAcInfo()).fuelSupplyRange : 0.0F; if (range <= 0.0F) return;  if (!this.field_70170_p.field_72995_K && getCountOnUpdate() % 10 == 0) { List<mcheli.aircraft.MCH_EntityAircraft> list = this.field_70170_p.func_72872_a(mcheli.aircraft.MCH_EntityAircraft.class, func_70046_E().func_72314_b(range, range, range)); for (int i = 0; i < list.size(); i++) { mcheli.aircraft.MCH_EntityAircraft ac = list.get(i); if (!W_Entity.isEqual((Entity)this, (Entity)ac)) { if ((!this.field_70122_E || ac.canSupply()) && ac.getFuel() < ac.getMaxFuel()) { int fc = ac.getMaxFuel() - ac.getFuel(); if (fc > 30) fc = 30;  ac.setFuel(ac.getFuel() + fc); }  ac.fuelSuppliedCount = 40; }  }  }  } public void updateFuel() { if (getMaxFuel() == 0) return;  if (this.fuelSuppliedCount > 0) this.fuelSuppliedCount--;  if (!isDestroyed() && !this.field_70170_p.field_72995_K) { if (getCountOnUpdate() % 20 == 0 && getFuel() > 1 && getThrottle() > 0.0D && this.fuelSuppliedCount <= 0) { double t = getThrottle() * 1.4D; if (t > 1.0D) t = 1.0D;  this.fuelConsumption += t * (getAcInfo()).fuelConsumption * getFuelConsumptionFactor(); if (this.fuelConsumption > 1.0D) { int f = (int)this.fuelConsumption; this.fuelConsumption -= f; setFuel(getFuel() - f); }  }  int curFuel = getFuel(); if (canSupply() && getCountOnUpdate() % 10 == 0 && curFuel < getMaxFuel()) { for (int i = 0; i < 3; i++) { if (curFuel < getMaxFuel()) { ItemStack fuel = getGuiInventory().getFuelSlotItemStack(i); if (fuel != null && fuel.func_77973_b() instanceof mcheli.aircraft.MCH_ItemFuel) if (fuel.func_77960_j() < fuel.func_77958_k()) { int fc = getMaxFuel() - curFuel; if (fc > 100) fc = 100;  if (fuel.func_77960_j() > fuel.func_77958_k() - fc) fc = fuel.func_77958_k() - fuel.func_77960_j();  fuel.func_77964_b(fuel.func_77960_j() + fc); curFuel += fc; }   }  }  if (getFuel() != curFuel) MCH_Achievement.addStat(this.field_70153_n, MCH_Achievement.supplyFuel, 1);  setFuel(curFuel); }  }  } public float getFuelConsumptionFactor() { return 1.0F; } public void updateSupplyAmmo() { if (!this.field_70170_p.field_72995_K) { boolean isReloading = false; if (getRiddenByEntity() instanceof EntityPlayer && !(getRiddenByEntity()).field_70128_L) if (((EntityPlayer)getRiddenByEntity()).field_71070_bA instanceof mcheli.aircraft.MCH_AircraftGuiContainer) isReloading = true;   setCommonStatus(2, isReloading); if (!isDestroyed() && this.beforeSupplyAmmo == true && !isReloading) { reloadAllWeapon(); MCH_PacketNotifyAmmoNum.sendAllAmmoNum(this, null); }  this.beforeSupplyAmmo = isReloading; }  if (getCommonStatus(2)) this.supplyAmmoWait = 20;  if (this.supplyAmmoWait > 0) this.supplyAmmoWait--;  } public void supplyAmmo(int weaponID) { if (this.field_70170_p.field_72995_K) { MCH_WeaponSet ws = getWeapon(weaponID); ws.supplyRestAllAmmo(); } else { MCH_Achievement.addStat(this.field_70153_n, MCH_Achievement.supplyAmmo, 1); if (getRiddenByEntity() instanceof EntityPlayer) { EntityPlayer player = (EntityPlayer)getRiddenByEntity(); if (canPlayerSupplyAmmo(player, weaponID)) { MCH_WeaponSet ws = getWeapon(weaponID); for (MCH_WeaponInfo.RoundItem ri : (ws.getInfo()).roundItems) { int num = ri.num; for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) { ItemStack itemStack = player.field_71071_by.field_70462_a[i]; if (itemStack != null && itemStack.func_77969_a(ri.itemStack)) if (itemStack.func_77973_b() == W_Item.getItemByName("water_bucket") || itemStack.func_77973_b() == W_Item.getItemByName("lava_bucket")) { if (itemStack.field_77994_a == 1) { player.field_71071_by.func_70299_a(i, new ItemStack(W_Item.getItemByName("bucket"), 1)); num--; }  } else if (itemStack.field_77994_a > num) { itemStack.field_77994_a -= num; num = 0; } else { num -= itemStack.field_77994_a; itemStack.field_77994_a = 0; player.field_71071_by.field_70462_a[i] = null; }   if (num <= 0) break;  }  }  ws.supplyRestAllAmmo(); }  }  }  } public void supplyAmmoToOtherAircraft() { float range = (getAcInfo() != null) ? (getAcInfo()).ammoSupplyRange : 0.0F; if (range <= 0.0F) return;  if (!this.field_70170_p.field_72995_K && getCountOnUpdate() % 40 == 0) { List<mcheli.aircraft.MCH_EntityAircraft> list = this.field_70170_p.func_72872_a(mcheli.aircraft.MCH_EntityAircraft.class, func_70046_E().func_72314_b(range, range, range)); for (int i = 0; i < list.size(); i++) { mcheli.aircraft.MCH_EntityAircraft ac = list.get(i); if (!W_Entity.isEqual((Entity)this, (Entity)ac)) if (ac.canSupply()) for (int wid = 0; wid < ac.getWeaponNum(); wid++) { MCH_WeaponSet ws = ac.getWeapon(wid); int num = ws.getRestAllAmmoNum() + ws.getAmmoNum(); if (num < ws.getAllAmmoNum()) { int ammo = ws.getAllAmmoNum() / 10; if (ammo < 1) ammo = 1;  ws.setRestAllAmmoNum(num + ammo); EntityPlayer player = ac.getEntityByWeaponId(wid); if (num != ws.getRestAllAmmoNum() + ws.getAmmoNum()) { if (ws.getAmmoNum() <= 0) ws.reloadMag();  MCH_PacketNotifyAmmoNum.sendAmmoNum(ac, player, wid); }  }  }    }  }  } public boolean canPlayerSupplyAmmo(EntityPlayer player, int weaponId) { if (MCH_Lib.getBlockIdY((Entity)this, 1, -3) == 0) return false;  if (!canSupply()) return false;  MCH_WeaponSet ws = getWeapon(weaponId); if (ws.getRestAllAmmoNum() + ws.getAmmoNum() >= ws.getAllAmmoNum()) return false;  for (MCH_WeaponInfo.RoundItem ri : (ws.getInfo()).roundItems) { int num = ri.num; for (ItemStack itemStack : player.field_71071_by.field_70462_a) { if (itemStack != null && itemStack.func_77969_a(ri.itemStack)) num -= itemStack.field_77994_a;  if (num <= 0) break;  }  if (num > 0) return false;  }  return true; } public mcheli.aircraft.MCH_EntityAircraft setTextureName(String name) { if (name != null && !name.isEmpty()) func_70096_w().func_75692_b(21, String.valueOf(name));  return this; } public String getTextureName() { return func_70096_w().func_75681_e(21); } public void switchNextTextureName() { if (getAcInfo() != null) setTextureName(getAcInfo().getNextTextureName(getTextureName()));  } public void zoomCamera() { if (canZoom()) { float z = this.camera.getCameraZoom(); if (z >= getZoomMax() - 0.01D) { z = 1.0F; } else { z *= 2.0F; if (z >= getZoomMax()) z = getZoomMax();  }  this.camera.setCameraZoom((z <= getZoomMax() + 0.01D) ? z : 1.0F); }  } public int getZoomMax() { return (getAcInfo() != null) ? (getAcInfo()).cameraZoom : 1; } public boolean canZoom() { return (getZoomMax() > 1); } public boolean canSwitchCameraMode() { if (isDestroyed()) return false;  return (getAcInfo() != null && (getAcInfo()).isEnableNightVision); } public boolean canSwitchCameraMode(int seatID) { if (isDestroyed()) return false;  return (canSwitchCameraMode() && this.camera.isValidUid(seatID)); } public int getCameraMode(EntityPlayer player) { return this.camera.getMode(getSeatIdByEntity((Entity)player)); } public String getCameraModeName(EntityPlayer player) { return this.camera.getModeName(getSeatIdByEntity((Entity)player)); } public void switchCameraMode(EntityPlayer player) { switchCameraMode(player, this.camera.getMode(getSeatIdByEntity((Entity)player)) + 1); } public void switchCameraMode(EntityPlayer player, int mode) { this.camera.setMode(getSeatIdByEntity((Entity)player), mode); } public void updateCameraViewers() { for (int i = 0; i < getSeatNum() + 1; i++) this.camera.updateViewer(i, getEntityBySeatId(i));  } public void updateRadar(int radarSpeed) { if (isEntityRadarMounted()) { this.radarRotate += radarSpeed; if (this.radarRotate >= 360) this.radarRotate = 0;  if (this.radarRotate == 0) this.entityRadar.updateXZ((Entity)this, 64);  }  } public int getRadarRotate() { return this.radarRotate; } public void initRadar() { this.entityRadar.clear(); this.radarRotate = 0; } public ArrayList<MCH_Vector2> getRadarEntityList() { return this.entityRadar.getEntityList(); } public ArrayList<MCH_Vector2> getRadarEnemyList() { return this.entityRadar.getEnemyList(); } public void func_70091_d(double par1, double par3, double par5) { if (getAcInfo() == null) return;  this.field_70170_p.field_72984_F.func_76320_a("move"); this.field_70139_V *= 0.4F; double d3 = this.field_70165_t; double d4 = this.field_70163_u; double d5 = this.field_70161_v; double d6 = par1; double d7 = par3; double d8 = par5; AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c(); List<AxisAlignedBB> list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(par1, par3, par5)); for (int i = 0; i < list.size(); i++) par3 = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, par3);  this.field_70121_D.func_72317_d(0.0D, par3, 0.0D); if (!this.field_70135_K && d7 != par3) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  boolean flag1 = (this.field_70122_E || (d7 != par3 && d7 < 0.0D)); int j; for (j = 0; j < list.size(); j++) par1 = ((AxisAlignedBB)list.get(j)).func_72316_a(this.field_70121_D, par1);  this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D); if (!this.field_70135_K && d6 != par1) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  for (j = 0; j < list.size(); j++) par5 = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, par5);  this.field_70121_D.func_72317_d(0.0D, 0.0D, par5); if (!this.field_70135_K && d8 != par5) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  if (this.field_70138_W > 0.0F && flag1 && this.field_70139_V < 0.05F && (d6 != par1 || d8 != par5)) { double d9 = par1; double d1 = par3; double d2 = par5; par1 = d6; par3 = this.field_70138_W; par5 = d8; AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c(); this.field_70121_D.func_72328_c(axisalignedbb); list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(d6, par3, d8)); int k; for (k = 0; k < list.size(); k++) par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);  this.field_70121_D.func_72317_d(0.0D, par3, 0.0D); if (!this.field_70135_K && d7 != par3) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  for (k = 0; k < list.size(); k++) par1 = ((AxisAlignedBB)list.get(k)).func_72316_a(this.field_70121_D, par1);  this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D); if (!this.field_70135_K && d6 != par1) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  for (k = 0; k < list.size(); k++) par5 = ((AxisAlignedBB)list.get(k)).func_72322_c(this.field_70121_D, par5);  this.field_70121_D.func_72317_d(0.0D, 0.0D, par5); if (!this.field_70135_K && d8 != par5) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; }  if (!this.field_70135_K && d7 != par3) { par5 = 0.0D; par3 = 0.0D; par1 = 0.0D; } else { par3 = -this.field_70138_W; for (k = 0; k < list.size(); k++) par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);  this.field_70121_D.func_72317_d(0.0D, par3, 0.0D); }  if (d9 * d9 + d2 * d2 >= par1 * par1 + par5 * par5) { par1 = d9; par3 = d1; par5 = d2; this.field_70121_D.func_72328_c(axisalignedbb1); }  }  this.field_70170_p.field_72984_F.func_76319_b(); this.field_70170_p.field_72984_F.func_76320_a("rest"); this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D; this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V; this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D; this.field_70123_F = (d6 != par1 || d8 != par5); this.field_70124_G = (d7 != par3); this.field_70122_E = (d7 != par3 && d7 < 0.0D); this.field_70132_H = (this.field_70123_F || this.field_70124_G); func_70064_a(par3, this.field_70122_E); if (d6 != par1) this.field_70159_w = 0.0D;  if (d7 != par3) this.field_70181_x = 0.0D;  if (d8 != par5) this.field_70179_y = 0.0D;  double d12 = this.field_70165_t - d3; double d10 = this.field_70163_u - d4; double d11 = this.field_70161_v - d5; try { doBlockCollisions(); } catch (Throwable throwable) { CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision"); CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision"); func_85029_a(crashreportcategory); throw new ReportedException(crashreport); }  this.field_70170_p.field_72984_F.func_76319_b(); } public static List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) { ArrayList<AxisAlignedBB> collidingBoundingBoxes = new ArrayList(); collidingBoundingBoxes.clear(); int i = MathHelper.func_76128_c(par2AxisAlignedBB.field_72340_a); int j = MathHelper.func_76128_c(par2AxisAlignedBB.field_72336_d + 1.0D); int k = MathHelper.func_76128_c(par2AxisAlignedBB.field_72338_b); int l = MathHelper.func_76128_c(par2AxisAlignedBB.field_72337_e + 1.0D); int i1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72339_c); int j1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72334_f + 1.0D); for (int k1 = i; k1 < j; k1++) { for (int l1 = i1; l1 < j1; l1++) { if (par1Entity.field_70170_p.func_72899_e(k1, 64, l1)) for (int i2 = k - 1; i2 < l; i2++) { Block block = W_WorldFunc.getBlock(par1Entity.field_70170_p, k1, i2, l1); if (block != null) block.func_149743_a(par1Entity.field_70170_p, k1, i2, l1, par2AxisAlignedBB, collidingBoundingBoxes, par1Entity);  }   }  }  double d0 = 0.25D; List<Entity> list = par1Entity.field_70170_p.func_72839_b(par1Entity, par2AxisAlignedBB.func_72314_b(d0, d0, d0)); for (int j2 = 0; j2 < list.size(); j2++) { Entity entity = list.get(j2); if (!W_Lib.isEntityLivingBase(entity) && !(entity instanceof MCH_EntitySeat) && !(entity instanceof MCH_EntityHitBox)) { AxisAlignedBB axisalignedbb1 = entity.func_70046_E(); if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB)) collidingBoundingBoxes.add(axisalignedbb1);  axisalignedbb1 = par1Entity.func_70114_g(entity); if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB)) collidingBoundingBoxes.add(axisalignedbb1);  }  }  return collidingBoundingBoxes; } protected void onUpdate_updateBlock() { if (!MCH_Config.Collision_DestroyBlock.prmBool) return;  for (int l = 0; l < 4; l++) { int i1 = MathHelper.func_76128_c(this.field_70165_t + ((l % 2) - 0.5D) * 0.8D); int j1 = MathHelper.func_76128_c(this.field_70161_v + ((l / 2) - 0.5D) * 0.8D); for (int k1 = 0; k1 < 2; k1++) { int l1 = MathHelper.func_76128_c(this.field_70163_u) + k1; Block block = W_WorldFunc.getBlock(this.field_70170_p, i1, l1, j1); if (!W_Block.isNull(block)) { if (block == W_Block.getSnowLayer()) this.field_70170_p.func_147468_f(i1, l1, j1);  if (block == W_Blocks.field_150392_bi || block == W_Blocks.field_150414_aQ) W_WorldFunc.destroyBlock(this.field_70170_p, i1, l1, j1, false);  }  }  }  } public void onUpdate_ParticleSmoke() { if (!this.field_70170_p.field_72995_K) return;  if (getCurrentThrottle() <= 0.10000000149011612D) return;  float yaw = getRotYaw(); float pitch = getRotPitch(); float roll = getRotRoll(); MCH_WeaponSet ws = getCurrentWeapon(getRiddenByEntity()); if (!(ws.getFirstWeapon() instanceof mcheli.weapon.MCH_WeaponSmoke)) return;  for (int i = 0; i < ws.getWeaponNum(); i++) { MCH_WeaponBase wb = ws.getWeapon(i); if (wb != null) { MCH_WeaponInfo wi = wb.getInfo(); if (wi != null) { Vec3 rot = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -yaw - 180.0F + wb.fixRotationYaw, pitch - wb.fixRotationPitch, roll); if (this.field_70146_Z.nextFloat() <= getCurrentThrottle() * 1.5D) { Vec3 pos = MCH_Lib.RotVec3(wb.position, -yaw, -pitch, -roll); double x = this.field_70165_t + pos.field_72450_a + rot.field_72450_a; double y = this.field_70163_u + pos.field_72448_b + rot.field_72448_b; double z = this.field_70161_v + pos.field_72449_c + rot.field_72449_c; for (int smk = 0; smk < wi.smokeNum; smk++) { float c = this.field_70146_Z.nextFloat() * 0.05F; int maxAge = (int)(this.field_70146_Z.nextDouble() * wi.smokeMaxAge); MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", x, y, z); prm.setMotion(rot.field_72450_a * wi.acceleration + (this.field_70146_Z.nextDouble() - 0.5D) * 0.2D, rot.field_72448_b * wi.acceleration + (this.field_70146_Z.nextDouble() - 0.5D) * 0.2D, rot.field_72449_c * wi.acceleration + (this.field_70146_Z.nextDouble() - 0.5D) * 0.2D); prm.size = (this.field_70146_Z.nextInt(5) + 5.0F) * wi.smokeSize; prm.setColor(wi.color.a + this.field_70146_Z.nextFloat() * 0.05F, wi.color.r + c, wi.color.g + c, wi.color.b + c); prm.age = maxAge; prm.toWhite = true; prm.diffusible = true; MCH_ParticlesUtil.spawnParticle(prm); }  }  }  }  }  } protected void onUpdate_ParticleSandCloud(boolean seaOnly) { if (seaOnly && !(getAcInfo()).enableSeaSurfaceParticle) return;  double particlePosY = (int)this.field_70163_u; boolean b = false; float scale = (getAcInfo()).particlesScale * 3.0F; if (seaOnly) scale *= 2.0F;  double throttle = getCurrentThrottle(); throttle *= 2.0D; if (throttle > 1.0D) throttle = 1.0D;  int count = seaOnly ? (int)(scale * 7.0F) : 0; int rangeY = (int)(scale * 10.0F) + 1; int y; for (y = 0; y < rangeY && !b; y++) { for (int x = -1; x <= 1; x++) { for (int z = -1; z <= 1; z++) { Block block = W_WorldFunc.getBlock(this.field_70170_p, (int)(this.field_70165_t + 0.5D) + x, (int)(this.field_70163_u + 0.5D) - y, (int)(this.field_70161_v + 0.5D) + z); if (!b && block != null) if (!Block.func_149680_a(block, Blocks.field_150350_a)) { if (seaOnly && W_Block.isEqual(block, W_Block.getWater())) count--;  if (count <= 0) { particlePosY = this.field_70163_u + 1.0D + (scale / 5.0F) - y; b = true; x += 100; break; }  }   }  }  }  double pn = (rangeY - y + 1) / 5.0D * scale / 2.0D; if (b && (getAcInfo()).particlesScale > 0.01F) for (int k = 0; k < (int)(throttle * 6.0D * pn); k++) { float r = (float)(this.field_70146_Z.nextDouble() * Math.PI * 2.0D); double dx = MathHelper.func_76134_b(r); double dz = MathHelper.func_76126_a(r); MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70165_t + dx * scale * 3.0D, particlePosY + (this.field_70146_Z.nextDouble() - 0.5D) * scale, this.field_70161_v + dz * scale * 3.0D, scale * dx * 0.3D, scale * -0.4D * 0.05D, scale * dz * 0.3D, scale * 5.0F); prm.setColor(prm.a * 0.6F, prm.r, prm.g, prm.b); prm.age = (int)(10.0F * scale); prm.motionYUpAge = seaOnly ? 0.2F : 0.1F; MCH_ParticlesUtil.spawnParticle(prm); }   } protected boolean func_70041_e_() { return false; } public AxisAlignedBB func_70114_g(Entity par1Entity) { return par1Entity.field_70121_D; } public AxisAlignedBB func_70046_E() { return this.field_70121_D; } public boolean func_70104_M() { return false; } public double func_70042_X() { return 0.0D; } public float func_70053_R() { return 2.0F; } public boolean func_70067_L() { return !this.field_70128_L; } public boolean useFlare(int type) { if (getAcInfo() == null || !getAcInfo().haveFlare()) return false;  for (int i : (getAcInfo()).flare.types) { if (i == type) { setCommonStatus(0, true); if (this.flareDv.use(type)) return true;  }  }  return false; } public int getCurrentFlareType() { if (!haveFlare()) return 0;  return (getAcInfo()).flare.types[this.currentFlareIndex]; } public void nextFlareType() { if (haveFlare()) this.currentFlareIndex = (this.currentFlareIndex + 1) % (getAcInfo()).flare.types.length;  } public boolean canUseFlare() { if (getAcInfo() == null || !getAcInfo().haveFlare()) return false;  if (getCommonStatus(0)) return false;  return (this.flareDv.tick == 0); } public boolean isFlarePreparation() { return this.flareDv.isInPreparation(); } public boolean isFlareUsing() { return this.flareDv.isUsing(); } public int getFlareTick() { return this.flareDv.tick; } public boolean haveFlare() { return (getAcInfo() != null && getAcInfo().haveFlare()); } public boolean haveFlare(int seatID) { return (haveFlare() && seatID >= 0 && seatID <= 1); } private static final MCH_EntitySeat[] seatsDummy = new MCH_EntitySeat[0]; private boolean switchSeat; public MCH_EntitySeat[] getSeats() { return (this.seats != null) ? this.seats : seatsDummy; } public int getSeatIdByEntity(Entity entity) { if (entity == null) return -1;  if (isEqual(getRiddenByEntity(), entity)) return 0;  for (int i = 0; i < (getSeats()).length; i++) { MCH_EntitySeat seat = getSeats()[i]; if (seat != null && isEqual(seat.field_70153_n, entity)) return i + 1;  }  return -1; } public MCH_EntitySeat getSeatByEntity(Entity entity) { int idx = getSeatIdByEntity(entity); if (idx > 0) return getSeat(idx - 1);  return null; } public Entity getEntityBySeatId(int id) { if (id == 0) return getRiddenByEntity();  id--; if (id < 0 || id >= (getSeats()).length) return null;  return (this.seats[id] != null) ? (this.seats[id]).field_70153_n : null; } public EntityPlayer getEntityByWeaponId(int id) { if (id >= 0 && id < getWeaponNum()) for (int i = 0; i < this.currentWeaponID.length; i++) { if (this.currentWeaponID[i] == id) { Entity e = getEntityBySeatId(i); if (e instanceof EntityPlayer) return (EntityPlayer)e;  }  }   return null; } public Entity getWeaponUserByWeaponName(String name) { if (getAcInfo() == null) return null;  MCH_AircraftInfo.Weapon weapon = getAcInfo().getWeaponByName(name); Entity entity = null; if (weapon != null) { entity = getEntityBySeatId(getWeaponSeatID(null, weapon)); if (entity == null && weapon.canUsePilot) entity = getRiddenByEntity();  }  return entity; } protected void newSeats(int seatsNum) { if (seatsNum >= 2) { if (this.seats != null) for (int i = 0; i < this.seats.length; i++) { if (this.seats[i] != null) { this.seats[i].func_70106_y(); this.seats[i] = null; }  }   this.seats = new MCH_EntitySeat[seatsNum - 1]; }  } public MCH_EntitySeat getSeat(int idx) { return (idx < this.seats.length) ? this.seats[idx] : null; } public void setSeat(int idx, MCH_EntitySeat seat) { if (idx < this.seats.length) { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.setSeat SeatID=" + idx + " / seat[]" + ((this.seats[idx] != null) ? 1 : 0) + " / " + ((seat.field_70153_n != null) ? 1 : 0), new Object[0]); if (this.seats[idx] == null || (this.seats[idx]).field_70153_n != null); this.seats[idx] = seat; }  } public boolean isValidSeatID(int seatID) { return (seatID >= 0 && seatID < getSeatNum() + 1); } public void updateHitBoxPosition() {} public void updateSeatsPosition(double px, double py, double pz, boolean setPrevPos) { MCH_SeatInfo[] info = getSeatsInfo(); if (this.pilotSeat != null && !this.pilotSeat.field_70128_L) { this.pilotSeat.field_70169_q = this.pilotSeat.field_70165_t; this.pilotSeat.field_70167_r = this.pilotSeat.field_70163_u; this.pilotSeat.field_70166_s = this.pilotSeat.field_70161_v; this.pilotSeat.func_70107_b(px, py, pz); if (info != null && info.length > 0 && info[0] != null) { Vec3 v = getTransformedPosition((info[0]).pos.field_72450_a, (info[0]).pos.field_72448_b, (info[0]).pos.field_72449_c, px, py, pz, (info[0]).rotSeat); this.pilotSeat.func_70107_b(v.field_72450_a, v.field_72448_b, v.field_72449_c); }  this.pilotSeat.field_70125_A = getRotPitch(); this.pilotSeat.field_70177_z = getRotYaw(); if (setPrevPos) { this.pilotSeat.field_70169_q = this.pilotSeat.field_70165_t; this.pilotSeat.field_70167_r = this.pilotSeat.field_70163_u; this.pilotSeat.field_70166_s = this.pilotSeat.field_70161_v; }  }  int i = 0; for (MCH_EntitySeat seat : this.seats) { i++; if (seat != null && !seat.field_70128_L) { float offsetY = 0.0F; if (seat.field_70153_n != null) if (W_Lib.isClientPlayer(seat.field_70153_n)) { offsetY = 1.0F; } else if (seat.field_70153_n.field_70131_O >= 1.0F) { offsetY = -seat.field_70153_n.field_70131_O + 1.0F; }   seat.field_70169_q = seat.field_70165_t; seat.field_70167_r = seat.field_70163_u; seat.field_70166_s = seat.field_70161_v; MCH_SeatInfo si = (i < info.length) ? info[i] : info[0]; Vec3 v = getTransformedPosition(si.pos.field_72450_a, si.pos.field_72448_b + offsetY, si.pos.field_72449_c, px, py, pz, si.rotSeat); seat.func_70107_b(v.field_72450_a, v.field_72448_b, v.field_72449_c); seat.field_70125_A = getRotPitch(); seat.field_70177_z = getRotYaw(); if (setPrevPos) { seat.field_70169_q = seat.field_70165_t; seat.field_70167_r = seat.field_70163_u; seat.field_70166_s = seat.field_70161_v; }  if (si instanceof MCH_SeatRackInfo) seat.updateRotation(((MCH_SeatRackInfo)si).fixYaw + getRotYaw(), ((MCH_SeatRackInfo)si).fixPitch);  seat.updatePosition(); }  }  } public int getClientPositionDelayCorrection() { return 7; }
/*      */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) { this.aircraftPosRotInc = par9 + getClientPositionDelayCorrection(); this.aircraftX = par1; this.aircraftY = par3; this.aircraftZ = par5; this.aircraftYaw = par7; this.aircraftPitch = par8; this.field_70159_w = this.velocityX; this.field_70181_x = this.velocityY; this.field_70179_y = this.velocityZ; }
/*      */   public void updateRiderPosition(double px, double py, double pz) { MCH_SeatInfo[] info = getSeatsInfo(); if (this.field_70153_n != null && !this.field_70153_n.field_70128_L) { Vec3 v; float riddenEntityYOffset = this.field_70153_n.field_70129_M; float offset = 0.0F; if (this.field_70153_n instanceof EntityPlayer) if (!W_Lib.isClientPlayer(this.field_70153_n)) offset -= 1.62F;   if (info != null && info.length > 0) { v = getTransformedPosition((info[0]).pos.field_72450_a, (info[0]).pos.field_72448_b + riddenEntityYOffset - 0.5D, (info[0]).pos.field_72449_c, px, py, pz, (info[0]).rotSeat); } else { v = getTransformedPosition(0.0D, (riddenEntityYOffset - 1.0F), 0.0D); }  this.field_70153_n.field_70129_M = 0.0F; this.field_70153_n.func_70107_b(v.field_72450_a, v.field_72448_b, v.field_72449_c); this.field_70153_n.field_70129_M = riddenEntityYOffset; }  }
/*      */   public void func_70043_V() { updateRiderPosition(this.field_70165_t, this.field_70163_u, this.field_70161_v); }
/*      */   public Vec3 calcOnTurretPos(Vec3 pos) { float ry = getLastRiderYaw(); if (getRiddenByEntity() != null) ry = (getRiddenByEntity()).field_70177_z;  Vec3 tpos = (getAcInfo()).turretPosition.func_72441_c(0.0D, pos.field_72448_b, 0.0D); Vec3 v = pos.func_72441_c(-tpos.field_72450_a, -tpos.field_72448_b, -tpos.field_72449_c); v = MCH_Lib.RotVec3(v, -ry, 0.0F, 0.0F); Vec3 vv = MCH_Lib.RotVec3(tpos, -getRotYaw(), -getRotPitch(), -getRotRoll()); v.field_72450_a += vv.field_72450_a; v.field_72448_b += vv.field_72448_b; v.field_72449_c += vv.field_72449_c; return v; }
/* 5503 */   public boolean func_130002_c(EntityPlayer player) { if (isDestroyed()) return false;
/*      */     
/* 5505 */     if (getAcInfo() == null) return false;
/*      */     
/* 5507 */     if (!checkTeam(player)) return false;
/*      */     
/* 5509 */     ItemStack itemStack = player.func_71045_bC();
/* 5510 */     if (itemStack != null && itemStack.func_77973_b() instanceof mcheli.tool.MCH_ItemWrench) {
/*      */ 
/*      */ 
/*      */       
/* 5514 */       if (!this.field_70170_p.field_72995_K && player.func_70093_af())
/*      */       {
/* 5516 */         switchNextTextureName();
/*      */       }
/* 5518 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 5522 */     if (player.func_70093_af()) {
/*      */       
/* 5524 */       openInventory(player);
/* 5525 */       return false;
/*      */     } 
/*      */     
/* 5528 */     if (!(getAcInfo()).canRide)
/*      */     {
/* 5530 */       return false;
/*      */     }
/*      */     
/* 5533 */     if (this.field_70153_n != null || isUAV())
/*      */     {
/*      */       
/* 5536 */       return interactFirstSeat(player);
/*      */     }
/*      */     
/* 5539 */     if (player.field_70154_o instanceof MCH_EntitySeat)
/*      */     {
/* 5541 */       return false;
/*      */     }
/*      */     
/* 5544 */     if (!canRideSeatOrRack(0, (Entity)player))
/*      */     {
/* 5546 */       return false;
/*      */     }
/*      */     
/* 5549 */     if (!this.switchSeat) {
/*      */       
/* 5551 */       if (getAcInfo().haveCanopy() && isCanopyClose()) {
/*      */         
/* 5553 */         openCanopy();
/* 5554 */         return false;
/*      */       } 
/*      */       
/* 5557 */       if (getModeSwitchCooldown() > 0)
/*      */       {
/* 5559 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 5563 */     closeCanopy();
/*      */     
/* 5565 */     this.field_70153_n = null;
/* 5566 */     this.lastRiddenByEntity = null;
/*      */ 
/*      */     
/* 5569 */     initRadar();
/*      */     
/* 5571 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/* 5573 */       player.func_70078_a((Entity)this);
/*      */       
/* 5575 */       if (!this.keepOnRideRotation)
/*      */       {
/*      */         
/* 5578 */         mountMobToSeats();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 5583 */       updateClientSettings(0);
/*      */     } 
/*      */     
/* 5586 */     setCameraId(0);
/*      */     
/* 5588 */     initPilotWeapon();
/*      */     
/* 5590 */     this.lowPassPartialTicks.clear();
/*      */     
/* 5592 */     if ((getAcInfo()).name.equalsIgnoreCase("uh-1c"))
/*      */     {
/* 5594 */       MCH_Achievement.addStat(this.field_70153_n, MCH_Achievement.rideValkyries, 1);
/*      */     }
/*      */     
/* 5597 */     onInteractFirst(player);
/*      */     
/* 5599 */     return true; } public float getLastRiderYaw() { return this.lastRiderYaw; } public float getLastRiderPitch() { return this.lastRiderPitch; } @SideOnly(Side.CLIENT) public void setupAllRiderRenderPosition(float tick, EntityPlayer player) { double x = this.field_70142_S + (this.field_70165_t - this.field_70142_S) * tick; double y = this.field_70137_T + (this.field_70163_u - this.field_70137_T) * tick; double z = this.field_70136_U + (this.field_70161_v - this.field_70136_U) * tick; updateRiderPosition(x, y, z); updateSeatsPosition(x, y, z, true); for (int i = 0; i < getSeatNum() + 1; i++) { Entity e = getEntityBySeatId(i); if (e != null) { e.field_70142_S = e.field_70165_t; e.field_70137_T = e.field_70163_u; e.field_70136_U = e.field_70161_v; }  }  if (getTVMissile() != null && W_Lib.isClientPlayer((getTVMissile()).shootingEntity)) { MCH_EntityTvMissile mCH_EntityTvMissile = getTVMissile(); x = ((Entity)mCH_EntityTvMissile).field_70169_q + (((Entity)mCH_EntityTvMissile).field_70165_t - ((Entity)mCH_EntityTvMissile).field_70169_q) * tick; y = ((Entity)mCH_EntityTvMissile).field_70167_r + (((Entity)mCH_EntityTvMissile).field_70163_u - ((Entity)mCH_EntityTvMissile).field_70167_r) * tick; z = ((Entity)mCH_EntityTvMissile).field_70166_s + (((Entity)mCH_EntityTvMissile).field_70161_v - ((Entity)mCH_EntityTvMissile).field_70166_s) * tick; MCH_ViewEntityDummy.setCameraPosition(x, y, z); } else { MCH_AircraftInfo.CameraPosition cpi = getCameraPosInfo(); if (cpi != null && cpi.pos != null) { Vec3 v; MCH_SeatInfo seatInfo = getSeatInfo((Entity)player); if (seatInfo != null && seatInfo.rotSeat) { v = calcOnTurretPos(cpi.pos); } else { v = MCH_Lib.RotVec3(cpi.pos, -getRotYaw(), -getRotPitch(), -getRotRoll()); }  MCH_ViewEntityDummy.setCameraPosition(x + v.field_72450_a, y + v.field_72448_b, z + v.field_72449_c); if (cpi.fixRot); }  }  } public Vec3 getTurretPos(Vec3 pos, boolean turret) { if (turret) { float ry = getLastRiderYaw(); if (getRiddenByEntity() != null) ry = (getRiddenByEntity()).field_70177_z;  Vec3 tpos = (getAcInfo()).turretPosition.func_72441_c(0.0D, pos.field_72448_b, 0.0D); Vec3 v = pos.func_72441_c(-tpos.field_72450_a, -tpos.field_72448_b, -tpos.field_72449_c); v = MCH_Lib.RotVec3(v, -ry, 0.0F, 0.0F); Vec3 vv = MCH_Lib.RotVec3(tpos, -getRotYaw(), -getRotPitch(), -getRotRoll()); v.field_72450_a += vv.field_72450_a; v.field_72448_b += vv.field_72448_b; v.field_72449_c += vv.field_72449_c; return v; }  return Vec3.func_72443_a(0.0D, 0.0D, 0.0D); } public Vec3 getTransformedPosition(Vec3 v) { return getTransformedPosition(v.field_72450_a, v.field_72448_b, v.field_72449_c); } public Vec3 getTransformedPosition(double x, double y, double z) { return getTransformedPosition(x, y, z, this.field_70165_t, this.field_70163_u, this.field_70161_v); } public Vec3 getTransformedPosition(Vec3 v, Vec3 pos) { return getTransformedPosition(v.field_72450_a, v.field_72448_b, v.field_72449_c, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c); } public Vec3 getTransformedPosition(Vec3 v, double px, double py, double pz) { return getTransformedPosition(v.field_72450_a, v.field_72448_b, v.field_72449_c, this.field_70165_t, this.field_70163_u, this.field_70161_v); } public Vec3 getTransformedPosition(double x, double y, double z, double px, double py, double pz) { Vec3 v = MCH_Lib.RotVec3(x, y, z, -getRotYaw(), -getRotPitch(), -getRotRoll()); return v.func_72441_c(px, py, pz); } public Vec3 getTransformedPosition(double x, double y, double z, double px, double py, double pz, boolean rotSeat) { if (rotSeat && getAcInfo() != null) { MCH_AircraftInfo info = getAcInfo(); Vec3 tv = MCH_Lib.RotVec3(x - info.turretPosition.field_72450_a, y - info.turretPosition.field_72448_b, z - info.turretPosition.field_72449_c, -getLastRiderYaw() + getRotYaw(), 0.0F, 0.0F); x = tv.field_72450_a + info.turretPosition.field_72450_a; y = tv.field_72448_b + info.turretPosition.field_72450_a; z = tv.field_72449_c + info.turretPosition.field_72450_a; }  Vec3 v = MCH_Lib.RotVec3(x, y, z, -getRotYaw(), -getRotPitch(), -getRotRoll()); return v.func_72441_c(px, py, pz); } protected MCH_SeatInfo[] getSeatsInfo() { if (this.seatsInfo != null) return this.seatsInfo;  newSeatsPos(); return this.seatsInfo; } public MCH_SeatInfo getSeatInfo(int index) { MCH_SeatInfo[] seats = getSeatsInfo(); if (index >= 0 && seats != null && index < seats.length) return seats[index];  return null; } public MCH_SeatInfo getSeatInfo(Entity entity) { return getSeatInfo(getSeatIdByEntity(entity)); } protected void setSeatsInfo(MCH_SeatInfo[] v) { this.seatsInfo = v; } public int getSeatNum() { if (getAcInfo() == null) return 0;  int s = getAcInfo().getNumSeatAndRack(); return (s >= 1) ? (s - 1) : 1; } protected void newSeatsPos() { if (getAcInfo() != null) { MCH_SeatInfo[] v = new MCH_SeatInfo[getAcInfo().getNumSeatAndRack()]; for (int i = 0; i < v.length; i++) v[i] = (getAcInfo()).seatList.get(i);  setSeatsInfo(v); }  } public void createSeats(String uuid) { if (this.field_70170_p.field_72995_K) return;  if (uuid.isEmpty()) return;  setCommonUniqueId(uuid); this.seats = new MCH_EntitySeat[getSeatNum()]; for (int i = 0; i < this.seats.length; i++) { this.seats[i] = new MCH_EntitySeat(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v); (this.seats[i]).parentUniqueID = getCommonUniqueId(); (this.seats[i]).seatID = i; this.seats[i].setParent(this); this.field_70170_p.func_72838_d((Entity)this.seats[i]); }  } public boolean interactFirstSeat(EntityPlayer player) { if (getSeats() == null) return false;  int seatId = 1; for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n == null && !isMountedEntity((Entity)player) && canRideSeatOrRack(seatId, (Entity)player)) { if (!this.field_70170_p.field_72995_K) player.func_70078_a((Entity)seat);  break; }  seatId++; }  return true; } public void onMountPlayerSeat(MCH_EntitySeat seat, Entity entity) { if (seat == null || !(entity instanceof EntityPlayer)) return;  if (this.field_70170_p.field_72995_K && MCH_Lib.getClientPlayer() == entity) switchGunnerFreeLookMode(false);  initCurrentWeapon(entity); MCH_Lib.DbgLog(this.field_70170_p, "onMountEntitySeat:%d", new Object[] { Integer.valueOf(W_Entity.getEntityId(entity)) }); Entity pilot = getRiddenByEntity(); int sid = getSeatIdByEntity(entity); if (sid == 1 && (getAcInfo() == null || !(getAcInfo()).isEnableConcurrentGunnerMode)) switchGunnerMode(false);  if (sid > 0) this.isGunnerModeOtherSeat = true;  if (pilot != null && getAcInfo() != null) { int cwid = getCurrentWeaponID(pilot); MCH_AircraftInfo.Weapon w = getAcInfo().getWeaponById(cwid); if (w != null && getWeaponSeatID(getWeaponInfoById(cwid), w) == sid) { int next = getNextWeaponID(pilot, 1); MCH_Lib.DbgLog(this.field_70170_p, "onMountEntitySeat:%d:->%d", new Object[] { Integer.valueOf(W_Entity.getEntityId(pilot)), Integer.valueOf(next) }); if (next >= 0) switchWeapon(pilot, next);  }  }  if (this.field_70170_p.field_72995_K) updateClientSettings(sid);  } public MCH_WeaponInfo getWeaponInfoById(int id) { if (id >= 0) { MCH_WeaponSet ws = getWeapon(id); if (ws != null) return ws.getInfo();  }  return null; } public abstract boolean canMountWithNearEmptyMinecart(); protected void mountWithNearEmptyMinecart() { if (getRidingEntity() != null) return;  int d = 2; if (this.dismountedUserCtrl) d = 6;  List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(d, d, d)); if (list != null && !list.isEmpty()) for (int i = 0; i < list.size(); i++) { Entity entity = list.get(i); if (entity instanceof net.minecraft.entity.item.EntityMinecartEmpty) { if (this.dismountedUserCtrl) return;  if (entity.field_70153_n == null && entity.func_70104_M()) { this.waitMountEntity = 20; MCH_Lib.DbgLog(this.field_70170_p.field_72995_K, "MCH_EntityAircraft.mountWithNearEmptyMinecart:" + entity, new Object[0]); func_70078_a(entity); return; }  }  }   this.dismountedUserCtrl = false; } public boolean isRidePlayer() { if (getRiddenByEntity() instanceof EntityPlayer) return true;  for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n instanceof EntityPlayer) return true;  }  return false; } public void onUnmountPlayerSeat(MCH_EntitySeat seat, Entity entity) { MCH_Lib.DbgLog(this.field_70170_p, "onUnmountPlayerSeat:%d", new Object[] { Integer.valueOf(W_Entity.getEntityId(entity)) }); int sid = getSeatIdByEntity(entity); this.camera.initCamera(sid, entity); MCH_SeatInfo seatInfo = getSeatInfo(seat.seatID + 1); if (seatInfo != null) setUnmountPosition(entity, Vec3.func_72443_a(seatInfo.pos.field_72450_a, 0.0D, seatInfo.pos.field_72449_c));  if (!isRidePlayer()) { switchGunnerMode(false); switchHoveringMode(false); }  } public boolean isCreatedSeats() { return !getCommonUniqueId().isEmpty(); } public void onUpdate_Seats() { boolean b = false; for (int i = 0; i < this.seats.length; i++) { if (this.seats[i] != null) { if (!(this.seats[i]).field_70128_L) (this.seats[i]).field_70143_R = 0.0F;  } else { b = true; }  }  if (b) { if (this.seatSearchCount > 40) { if (this.field_70170_p.field_72995_K) { MCH_PacketSeatListRequest.requestSeatList(this); } else { searchSeat(); }  this.seatSearchCount = 0; }  this.seatSearchCount++; }  } public void searchSeat() { List<MCH_EntitySeat> list = this.field_70170_p.func_72872_a(MCH_EntitySeat.class, this.field_70121_D.func_72314_b(60.0D, 60.0D, 60.0D)); for (int i = 0; i < list.size(); i++) { MCH_EntitySeat seat = list.get(i); if (!seat.field_70128_L && seat.parentUniqueID.equals(getCommonUniqueId())) if (seat.seatID >= 0 && seat.seatID < getSeatNum() && this.seats[seat.seatID] == null) { this.seats[seat.seatID] = seat; seat.setParent(this); }   }  } public String getCommonUniqueId() { return this.commonUniqueId; } public void setCommonUniqueId(String uniqId) { this.commonUniqueId = uniqId; } public void func_70106_y() { setDead(false); } public void setDead(boolean dropItems) { this.dropContentsWhenDead = dropItems; super.func_70106_y(); if (getRiddenByEntity() != null) getRiddenByEntity().func_70078_a(null);  getGuiInventory().setDead(); for (MCH_EntitySeat s : this.seats) { if (s != null) s.func_70106_y();  }  if (this.soundUpdater != null) this.soundUpdater.update();  if (getTowChainEntity() != null) { getTowChainEntity().func_70106_y(); setTowChainEntity(null); }  for (Entity e : func_70021_al()) { if (e != null) e.func_70106_y();  }  MCH_Lib.DbgLog(this.field_70170_p, "setDead:" + ((getAcInfo() != null) ? (getAcInfo()).name : "null"), new Object[0]); } public void unmountEntity() { if (!isRidePlayer()) switchHoveringMode(false);  this.moveLeft = this.moveRight = this.throttleDown = this.throttleUp = false; Entity rByEntity = null; if (this.field_70153_n != null) { rByEntity = this.field_70153_n; this.camera.initCamera(0, rByEntity); if (!this.field_70170_p.field_72995_K) this.field_70153_n.func_70078_a(null);  } else if (this.lastRiddenByEntity != null) { rByEntity = this.lastRiddenByEntity; if (rByEntity instanceof EntityPlayer) this.camera.initCamera(0, rByEntity);  }  MCH_Lib.DbgLog(this.field_70170_p, "unmountEntity:" + rByEntity, new Object[0]); if (!isRidePlayer()) switchGunnerMode(false);  setCommonStatus(1, false); if (!isUAV()) { setUnmountPosition(rByEntity, (getSeatsInfo()[0]).pos); } else if (rByEntity != null && rByEntity.field_70154_o instanceof MCH_EntityUavStation) { rByEntity.func_70078_a(null); }  this.field_70153_n = null; this.lastRiddenByEntity = null; if (this.cs_dismountAll) unmountCrew(false);  } public Entity getRidingEntity() { return this.field_70154_o; } public void startUnmountCrew() { this.isParachuting = true; if (haveHatch()) foldHatch(true, true);  } public void stopUnmountCrew() { this.isParachuting = false; } public void unmountCrew() { if (getAcInfo() == null) return;  if (getAcInfo().haveRepellingHook()) { if (!isRepelling()) { if (MCH_Lib.getBlockIdY((Entity)this, 3, -4) > 0) { unmountCrew(false); } else if (canStartRepelling()) { startRepelling(); }  } else { stopRepelling(); }  } else if (this.isParachuting) { stopUnmountCrew(); } else if ((getAcInfo()).isEnableParachuting && MCH_Lib.getBlockIdY((Entity)this, 3, -10) == 0) { startUnmountCrew(); } else { unmountCrew(false); }  } public boolean isRepelling() { return getCommonStatus(5); } public void setRepellingStat(boolean b) { setCommonStatus(5, b); } public Vec3 getRopePos(int ropeIndex) { if (getAcInfo() != null && getAcInfo().haveRepellingHook()) if (ropeIndex < (getAcInfo()).repellingHooks.size()) return getTransformedPosition(((MCH_AircraftInfo.RepellingHook)(getAcInfo()).repellingHooks.get(ropeIndex)).pos);   return Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v); } private void startRepelling() { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.startRepelling()", new Object[0]); setRepellingStat(true); this.throttleUp = false; this.throttleDown = false; this.moveLeft = false; this.moveRight = false; this.tickRepelling = 0; } private void stopRepelling() { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.stopRepelling()", new Object[0]); setRepellingStat(false); } public static float abs(float p_76135_0_) { return (p_76135_0_ >= 0.0F) ? p_76135_0_ : -p_76135_0_; } public static double abs(double p_76135_0_) { return (p_76135_0_ >= 0.0D) ? p_76135_0_ : -p_76135_0_; } public boolean canStartRepelling() { if (getAcInfo().haveRepellingHook()) if (isHovering()) if (abs(getRotPitch()) < 3.0F && abs(getRotRoll()) < 3.0F) { Vec3 v = ((Vec3)this.prevPosition.oldest()).func_72441_c(-this.field_70165_t, -this.field_70163_u, -this.field_70161_v); if (v.func_72433_c() < 0.3D) return true;  }    return false; } public boolean unmountCrew(boolean unmountParachute) { boolean ret = false; MCH_SeatInfo[] pos = getSeatsInfo(); for (int i = 0; i < this.seats.length; i++) { if (this.seats[i] != null && (this.seats[i]).field_70153_n != null) { Entity entity = (this.seats[i]).field_70153_n; if (!(entity instanceof EntityPlayer) && !(pos[i + 1] instanceof MCH_SeatRackInfo)) if (unmountParachute) { if (getSeatIdByEntity(entity) > 1) { ret = true; Vec3 dropPos = getTransformedPosition((getAcInfo()).mobDropOption.pos, (Vec3)this.prevPosition.oldest()); (this.seats[i]).field_70165_t = dropPos.field_72450_a; (this.seats[i]).field_70163_u = dropPos.field_72448_b; (this.seats[i]).field_70161_v = dropPos.field_72449_c; entity.func_70078_a(null); entity.field_70165_t = dropPos.field_72450_a; entity.field_70163_u = dropPos.field_72448_b; entity.field_70161_v = dropPos.field_72449_c; dropEntityParachute(entity); break; }  } else { ret = true; Vec3 dropPos = (pos[i + 1]).pos; setUnmountPosition((Entity)this.seats[i], (pos[i + 1]).pos); entity.func_70078_a(null); setUnmountPosition(entity, (pos[i + 1]).pos); }   }  }  return ret; } public void setUnmountPosition(Entity rByEntity, Vec3 pos) { if (rByEntity != null) { Vec3 v; MCH_AircraftInfo info = getAcInfo(); if (info != null && info.unmountPosition != null) { v = getTransformedPosition(info.unmountPosition); } else { double x = pos.field_72450_a; x = (x >= 0.0D) ? (x + 3.0D) : (x - 3.0D); v = getTransformedPosition(x, 2.0D, pos.field_72449_c); }  rByEntity.func_70107_b(v.field_72450_a, v.field_72448_b, v.field_72449_c); this.listUnmountReserve.add(new UnmountReserve(this, rByEntity, v.field_72450_a, v.field_72448_b, v.field_72449_c)); }  } public boolean unmountEntityFromSeat(Entity entity) { if (entity == null || this.seats == null || this.seats.length == 0) return false;  for (MCH_EntitySeat seat : this.seats) { if (seat != null && seat.field_70153_n != null && W_Entity.isEqual(seat.field_70153_n, entity)) entity.func_70078_a(null);  }  return false; } public void ejectSeat(Entity entity) { int sid = getSeatIdByEntity(entity); if (sid < 0 || sid > 1) return;  if (getGuiInventory().haveParachute()) { if (sid == 0) { getGuiInventory().consumeParachute(); unmountEntity(); ejectSeatSub(entity, 0); entity = getEntityBySeatId(1); if (entity instanceof EntityPlayer) entity = null;  }  if (getGuiInventory().haveParachute()) if (entity != null) { getGuiInventory().consumeParachute(); unmountEntityFromSeat(entity); ejectSeatSub(entity, 1); }   }  } public void ejectSeatSub(Entity entity, int sid) { Vec3 pos = (getSeatInfo(sid) != null) ? (getSeatInfo(sid)).pos : null; if (pos != null) { Vec3 vec3 = getTransformedPosition(pos.field_72450_a, pos.field_72448_b + 2.0D, pos.field_72449_c); entity.func_70107_b(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c); }  Vec3 v = MCH_Lib.RotVec3(0.0D, 2.0D, 0.0D, -getRotYaw(), -getRotPitch(), -getRotRoll()); entity.field_70159_w = this.field_70159_w + v.field_72450_a + (this.field_70146_Z.nextFloat() - 0.5D) * 0.1D; entity.field_70181_x = this.field_70181_x + v.field_72448_b; entity.field_70179_y = this.field_70179_y + v.field_72449_c + (this.field_70146_Z.nextFloat() - 0.5D) * 0.1D; MCH_EntityParachute parachute = new MCH_EntityParachute(this.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v); parachute.field_70177_z = entity.field_70177_z; parachute.field_70159_w = entity.field_70159_w; parachute.field_70181_x = entity.field_70181_x; parachute.field_70179_y = entity.field_70179_y; parachute.field_70143_R = entity.field_70143_R; parachute.user = entity; parachute.setType(2); this.field_70170_p.func_72838_d((Entity)parachute); if (getAcInfo().haveCanopy() && isCanopyClose()) openCanopy_EjectSeat();  W_WorldFunc.MOD_playSoundAtEntity(entity, "eject_seat", 5.0F, 1.0F); } public boolean canEjectSeat(Entity entity) { int sid = getSeatIdByEntity(entity); if (sid == 0 && isUAV()) return false;  return (sid >= 0 && sid < 2 && getAcInfo() != null && (getAcInfo()).isEnableEjectionSeat); } public int getNumEjectionSeat() { return 0; } public int getMountedEntityNum() { int num = 0; if (this.field_70153_n != null && !this.field_70153_n.field_70128_L) num++;  if (this.seats != null && this.seats.length > 0) for (MCH_EntitySeat seat : this.seats) { if (seat != null && seat.field_70153_n != null && !seat.field_70153_n.field_70128_L) num++;  }   return num; } public void mountMobToSeats() { List<Entity> list = this.field_70170_p.func_72872_a(W_Lib.getEntityLivingBaseClass(), this.field_70121_D.func_72314_b(3.0D, 2.0D, 3.0D)); for (int i = 0; i < list.size(); i++) { Entity entity = list.get(i); if (!(entity instanceof EntityPlayer) && entity.field_70154_o == null) { int sid = 1; for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n == null && !isMountedEntity(entity) && canRideSeatOrRack(sid, entity)) { if (getSeatInfo(sid) instanceof MCH_SeatRackInfo) break;  entity.func_70078_a((Entity)seat); }  sid++; }  }  }  } public void mountEntityToRack() { if (!MCH_Config.EnablePutRackInFlying.prmBool) { if (getCurrentThrottle() > 0.3D) return;  Block block = MCH_Lib.getBlockY((Entity)this, 1, -3, true); if (block == null || W_Block.isEqual(block, Blocks.field_150350_a)) return;  }  int countRideEntity = 0; for (int sid = 0; sid < getSeatNum(); sid++) { MCH_EntitySeat seat = getSeat(sid); if (getSeatInfo(1 + sid) instanceof MCH_SeatRackInfo && seat != null && seat.field_70153_n == null) { MCH_SeatRackInfo info = (MCH_SeatRackInfo)getSeatInfo(1 + sid); Vec3 v = MCH_Lib.RotVec3((info.getEntryPos()).field_72450_a, (info.getEntryPos()).field_72448_b, (info.getEntryPos()).field_72449_c, -getRotYaw(), -getRotPitch(), -getRotRoll()); v.field_72450_a += this.field_70165_t; v.field_72448_b += this.field_70163_u; v.field_72449_c += this.field_70161_v; AxisAlignedBB bb = AxisAlignedBB.func_72330_a(v.field_72450_a, v.field_72448_b, v.field_72449_c, v.field_72450_a, v.field_72448_b, v.field_72449_c); float range = info.range; List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, bb.func_72314_b(range, range, range)); for (int i = 0; i < list.size(); i++) { Entity entity = list.get(i); if (canRideSeatOrRack(1 + sid, entity)) if (entity instanceof MCH_IEntityCanRideAircraft) { if (((MCH_IEntityCanRideAircraft)entity).canRideAircraft(this, sid, info)) { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.mountEntityToRack:%d:%s", new Object[] { Integer.valueOf(sid), entity }); entity.func_70078_a((Entity)seat); countRideEntity++; break; }  } else if (entity.field_70154_o == null) { NBTTagCompound nbt = entity.getEntityData(); if (nbt.func_74764_b("CanMountEntity") && nbt.func_74767_n("CanMountEntity")) { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.mountEntityToRack:%d:%s:%s", new Object[] { Integer.valueOf(sid), entity, entity.getClass() }); entity.func_70078_a((Entity)seat); countRideEntity++; break; }  }   }  }  }  if (countRideEntity > 0) W_WorldFunc.DEF_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.click", 1.0F, 1.0F);  } public void unmountEntityFromRack() { for (int sid = getSeatNum() - 1; sid >= 0; sid--) { MCH_EntitySeat seat = getSeat(sid); if (getSeatInfo(sid + 1) instanceof MCH_SeatRackInfo && seat != null && seat.field_70153_n != null) { MCH_SeatRackInfo info = (MCH_SeatRackInfo)getSeatInfo(sid + 1); Entity entity = seat.field_70153_n; Vec3 pos = info.getEntryPos(); if (entity instanceof mcheli.aircraft.MCH_EntityAircraft) if (pos.field_72449_c >= (getAcInfo()).bbZ) { pos = pos.func_72441_c(0.0D, 0.0D, 12.0D); } else { pos = pos.func_72441_c(0.0D, 0.0D, -12.0D); }   Vec3 v = MCH_Lib.RotVec3(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, -getRotYaw(), -getRotPitch(), -getRotRoll()); seat.field_70165_t = entity.field_70165_t = this.field_70165_t + v.field_72450_a; seat.field_70163_u = entity.field_70163_u = this.field_70163_u + v.field_72448_b; seat.field_70161_v = entity.field_70161_v = this.field_70161_v + v.field_72449_c; UnmountReserve ur = new UnmountReserve(this, entity, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v); ur.cnt = 8; this.listUnmountReserve.add(ur); entity.func_70078_a(null); if (MCH_Lib.getBlockIdY((Entity)this, 3, -20) > 0) { MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.unmountEntityFromRack:%d:%s", new Object[] { Integer.valueOf(sid), entity }); break; }  MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityAircraft.unmountEntityFromRack:%d Parachute:%s", new Object[] { Integer.valueOf(sid), entity }); dropEntityParachute(entity); break; }  }  } public void dropEntityParachute(Entity entity) { entity.field_70159_w = this.field_70159_w; entity.field_70181_x = this.field_70181_x; entity.field_70179_y = this.field_70179_y; MCH_EntityParachute parachute = new MCH_EntityParachute(this.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v); parachute.field_70177_z = entity.field_70177_z; parachute.field_70159_w = entity.field_70159_w; parachute.field_70181_x = entity.field_70181_x; parachute.field_70179_y = entity.field_70179_y; parachute.field_70143_R = entity.field_70143_R; parachute.user = entity; parachute.setType(3); this.field_70170_p.func_72838_d((Entity)parachute); } public void rideRack() { if (this.field_70154_o != null) return;  AxisAlignedBB bb = func_70046_E(); List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, bb.func_72314_b(60.0D, 60.0D, 60.0D)); for (int i = 0; i < list.size(); i++) { Entity entity = list.get(i); if (entity instanceof mcheli.aircraft.MCH_EntityAircraft) { mcheli.aircraft.MCH_EntityAircraft ac = (mcheli.aircraft.MCH_EntityAircraft)entity; if (ac.getAcInfo() != null) for (int sid = 0; sid < ac.getSeatNum(); sid++) { MCH_SeatInfo seatInfo = ac.getSeatInfo(1 + sid); if (seatInfo instanceof MCH_SeatRackInfo) if (ac.canRideSeatOrRack(1 + sid, entity)) { MCH_SeatRackInfo info = (MCH_SeatRackInfo)seatInfo; MCH_EntitySeat seat = ac.getSeat(sid); if (seat != null && seat.field_70153_n == null) { Vec3 v = ac.getTransformedPosition(info.getEntryPos()); float r = info.range; if (this.field_70165_t >= v.field_72450_a - r && this.field_70165_t <= v.field_72450_a + r && this.field_70163_u >= v.field_72448_b - r && this.field_70163_u <= v.field_72448_b + r && this.field_70161_v >= v.field_72449_c - r && this.field_70161_v <= v.field_72449_c + r) if (canRideAircraft(ac, sid, info)) { W_WorldFunc.DEF_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.click", 1.0F, 1.0F); func_70078_a((Entity)seat); return; }   }  }   }   }  }  } public boolean canPutToRack() { for (int i = 0; i < getSeatNum(); i++) { MCH_EntitySeat seat = getSeat(i); MCH_SeatInfo seatInfo = getSeatInfo(i + 1); if (seat != null && seat.field_70153_n == null && seatInfo instanceof MCH_SeatRackInfo) return true;  }  return false; } public boolean canDownFromRack() { for (int i = 0; i < getSeatNum(); i++) { MCH_EntitySeat seat = getSeat(i); MCH_SeatInfo seatInfo = getSeatInfo(i + 1); if (seat != null && seat.field_70153_n != null && seatInfo instanceof MCH_SeatRackInfo) return true;  }  return false; } public void checkRideRack() { if (getCountOnUpdate() % 10 != 0) return;  this.canRideRackStatus = false; if (this.field_70154_o != null) return;  AxisAlignedBB bb = func_70046_E(); List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, bb.func_72314_b(60.0D, 60.0D, 60.0D)); for (int i = 0; i < list.size(); i++) { Entity entity = list.get(i); if (entity instanceof mcheli.aircraft.MCH_EntityAircraft) { mcheli.aircraft.MCH_EntityAircraft ac = (mcheli.aircraft.MCH_EntityAircraft)entity; if (ac.getAcInfo() != null) for (int sid = 0; sid < ac.getSeatNum(); sid++) { MCH_SeatInfo seatInfo = ac.getSeatInfo(1 + sid); if (seatInfo instanceof MCH_SeatRackInfo) { MCH_SeatRackInfo info = (MCH_SeatRackInfo)seatInfo; MCH_EntitySeat seat = ac.getSeat(sid); if (seat != null && seat.field_70153_n == null) { Vec3 v = ac.getTransformedPosition(info.getEntryPos()); float r = info.range; boolean rx = (this.field_70165_t >= v.field_72450_a - r && this.field_70165_t <= v.field_72450_a + r); boolean ry = (this.field_70163_u >= v.field_72448_b - r && this.field_70163_u <= v.field_72448_b + r); boolean rz = (this.field_70161_v >= v.field_72449_c - r && this.field_70161_v <= v.field_72449_c + r); if (this.field_70165_t >= v.field_72450_a - r && this.field_70165_t <= v.field_72450_a + r && this.field_70163_u >= v.field_72448_b - r && this.field_70163_u <= v.field_72448_b + r && this.field_70161_v >= v.field_72449_c - r && this.field_70161_v <= v.field_72449_c + r) if (canRideAircraft(ac, sid, info)) { this.canRideRackStatus = true; return; }   }  }  }   }  }  } public boolean canRideRack() { return (this.field_70154_o == null && this.canRideRackStatus); } public boolean canRideAircraft(mcheli.aircraft.MCH_EntityAircraft ac, int seatID, MCH_SeatRackInfo info) { if (getAcInfo() == null) return false;  if (ac.field_70154_o != null) return false;  if (this.field_70154_o != null) return false;  boolean canRide = false; for (String s : info.names) { if (s.equalsIgnoreCase((getAcInfo()).name) || s.equalsIgnoreCase(getAcInfo().getKindName())) { canRide = true; break; }  }  if (!canRide) { for (MCH_AircraftInfo.RideRack rr : (getAcInfo()).rideRacks) { int id = ac.getAcInfo().getNumSeat() - 1 + rr.rackID - 1; if (id == seatID && rr.name.equalsIgnoreCase((ac.getAcInfo()).name)) { MCH_EntitySeat seat = ac.getSeat(ac.getAcInfo().getNumSeat() - 1 + rr.rackID - 1); if (seat != null && seat.field_70153_n == null) { canRide = true; break; }  }  }  if (!canRide) return false;  }  for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n instanceof MCH_IEntityCanRideAircraft) return false;  }  return true; } public boolean isMountedEntity(Entity entity) { if (entity == null) return false;  return isMountedEntity(W_Entity.getEntityId(entity)); } public EntityPlayer getFirstMountPlayer() { if (getRiddenByEntity() instanceof EntityPlayer) return (EntityPlayer)getRiddenByEntity();  for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n instanceof EntityPlayer) return (EntityPlayer)seat.field_70153_n;  }  return null; }
/*      */   public boolean isMountedSameTeamEntity(EntityLivingBase player) { if (player == null || player.func_96124_cp() == null) return false;  if (this.field_70153_n instanceof EntityLivingBase) if (player.func_142014_c((EntityLivingBase)this.field_70153_n)) return true;   for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n instanceof EntityLivingBase) if (player.func_142014_c((EntityLivingBase)seat.field_70153_n)) return true;   }  return false; }
/*      */   public boolean isMountedOtherTeamEntity(EntityLivingBase player) { if (player == null) return false;  EntityLivingBase target = null; if (this.field_70153_n instanceof EntityLivingBase) { target = (EntityLivingBase)this.field_70153_n; if (player.func_96124_cp() != null && target.func_96124_cp() != null && !player.func_142014_c(target)) return true;  }  for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n instanceof EntityLivingBase) { target = (EntityLivingBase)seat.field_70153_n; if (player.func_96124_cp() != null && target.func_96124_cp() != null && !player.func_142014_c(target)) return true;  }  }  return false; }
/*      */   public boolean isMountedEntity(int entityId) { if (W_Entity.getEntityId(this.field_70153_n) == entityId) return true;  for (MCH_EntitySeat seat : getSeats()) { if (seat != null && seat.field_70153_n != null && W_Entity.getEntityId(seat.field_70153_n) == entityId) return true;  }  return false; }
/*      */   public void onInteractFirst(EntityPlayer player) {}
/*      */   public boolean checkTeam(EntityPlayer player) { for (int i = 0; i < 1 + getSeatNum(); i++) { Entity entity = getEntityBySeatId(i); if (entity instanceof EntityPlayer) { EntityPlayer riddenPlayer = (EntityPlayer)entity; if (riddenPlayer.func_96124_cp() != null && !riddenPlayer.func_142014_c((EntityLivingBase)player)) return false;  }  }  return true; }
/* 5605 */   public boolean canRideSeatOrRack(int seatId, Entity entity) { if (getAcInfo() == null) return false; 
/* 5606 */     for (Integer[] a : (getAcInfo()).exclusionSeatList) {
/*      */       
/* 5608 */       if (Arrays.<Integer>asList(a).contains(Integer.valueOf(seatId))) {
/*      */         Integer[] arr$; int len$; int i$;
/* 5610 */         for (arr$ = a, len$ = arr$.length, i$ = 0; i$ < len$; ) { int id = arr$[i$].intValue();
/*      */           
/* 5612 */           if (getEntityBySeatId(id) != null)
/*      */           {
/* 5614 */             return false; } 
/*      */           i$++; }
/*      */       
/*      */       } 
/*      */     } 
/* 5619 */     return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateClientSettings(int seatId) {
/* 5625 */     this.cs_dismountAll = MCH_Config.DismountAll.prmBool;
/* 5626 */     this.cs_heliAutoThrottleDown = MCH_Config.AutoThrottleDownHeli.prmBool;
/* 5627 */     this.cs_planeAutoThrottleDown = MCH_Config.AutoThrottleDownPlane.prmBool;
/* 5628 */     this.cs_tankAutoThrottleDown = MCH_Config.AutoThrottleDownTank.prmBool;
/* 5629 */     this.camera.setShaderSupport(seatId, Boolean.valueOf(W_EntityRenderer.isShaderSupport()));
/* 5630 */     MCH_PacketNotifyClientSetting.send();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canLockEntity(Entity entity) {
/* 5636 */     return !isMountedEntity(entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void switchNextSeat(Entity entity) {
/* 5641 */     if (entity == null)
/* 5642 */       return;  if (this.seats == null || this.seats.length <= 0)
/* 5643 */       return;  if (!isMountedEntity(entity)) {
/*      */       return;
/*      */     }
/* 5646 */     boolean isFound = false;
/* 5647 */     int sid = 1;
/* 5648 */     for (MCH_EntitySeat seat : this.seats) {
/*      */       
/* 5650 */       if (seat != null) {
/*      */         
/* 5652 */         if (getSeatInfo(sid) instanceof MCH_SeatRackInfo)
/*      */           break; 
/* 5654 */         if (W_Entity.isEqual(seat.field_70153_n, entity)) {
/*      */           
/* 5656 */           isFound = true;
/*      */         }
/* 5658 */         else if (isFound && seat.field_70153_n == null) {
/*      */           
/* 5660 */           entity.func_70078_a((Entity)seat);
/*      */           return;
/*      */         } 
/* 5663 */         sid++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5669 */     sid = 1;
/* 5670 */     for (MCH_EntitySeat seat : this.seats) {
/*      */       
/* 5672 */       if (seat != null && seat.field_70153_n == null) {
/*      */         
/* 5674 */         if (getSeatInfo(sid) instanceof MCH_SeatRackInfo)
/*      */           break; 
/* 5676 */         entity.func_70078_a((Entity)seat);
/* 5677 */         onMountPlayerSeat(seat, entity);
/*      */         return;
/*      */       } 
/* 5680 */       sid++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void switchPrevSeat(Entity entity) {
/* 5686 */     if (entity == null)
/* 5687 */       return;  if (this.seats == null || this.seats.length <= 0)
/* 5688 */       return;  if (!isMountedEntity(entity)) {
/*      */       return;
/*      */     }
/* 5691 */     boolean isFound = false; int i;
/* 5692 */     for (i = this.seats.length - 1; i >= 0; i--) {
/*      */       
/* 5694 */       MCH_EntitySeat seat = this.seats[i];
/* 5695 */       if (seat != null) {
/* 5696 */         if (W_Entity.isEqual(seat.field_70153_n, entity)) {
/*      */           
/* 5698 */           isFound = true;
/*      */         }
/* 5700 */         else if (isFound && seat.field_70153_n == null) {
/*      */           
/* 5702 */           entity.func_70078_a((Entity)seat);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 5711 */     for (i = this.seats.length - 1; i >= 0; i--) {
/*      */       
/* 5713 */       MCH_EntitySeat seat = this.seats[i];
/* 5714 */       if (!(getSeatInfo(i + 1) instanceof MCH_SeatRackInfo) && 
/* 5715 */         seat != null && seat.field_70153_n == null) {
/*      */         
/* 5717 */         entity.func_70078_a((Entity)seat);
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity[] func_70021_al() {
/* 5727 */     return this.partEntities;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSoundVolume() {
/* 5736 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSoundPitch() {
/* 5741 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract String getDefaultSoundName();
/*      */   
/*      */   public String getSoundName() {
/* 5748 */     if (getAcInfo() == null) return ""; 
/* 5749 */     return !(getAcInfo()).soundMove.isEmpty() ? (getAcInfo()).soundMove : getDefaultSoundName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSkipNormalRender() {
/* 5759 */     return this.field_70154_o instanceof MCH_EntitySeat;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRenderBullet(Entity entity, Entity rider) {
/* 5765 */     if (isCameraView(rider))
/*      */     {
/* 5767 */       if (W_Entity.isEqual((Entity)getTVMissile(), entity) && W_Entity.isEqual((getTVMissile()).shootingEntity, rider))
/*      */       {
/* 5769 */         return false;
/*      */       }
/*      */     }
/* 5772 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCameraView(Entity entity) {
/* 5777 */     return (getIsGunnerMode(entity) || isUAV());
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateCamera(double x, double y, double z) {
/* 5782 */     if (!this.field_70170_p.field_72995_K)
/* 5783 */       return;  if (getTVMissile() != null) {
/*      */       
/* 5785 */       this.camera.setPosition(this.TVmissile.field_70165_t, this.TVmissile.field_70163_u, this.TVmissile.field_70161_v);
/* 5786 */       this.camera.setCameraZoom(1.0F);
/* 5787 */       this.TVmissile.isSpawnParticle = !isMissileCameraMode(this.TVmissile.shootingEntity);
/*      */     }
/*      */     else {
/*      */       
/* 5791 */       setTVMissile(null);
/*      */       
/* 5793 */       MCH_AircraftInfo.CameraPosition cpi = getCameraPosInfo();
/* 5794 */       Vec3 cp = (cpi != null) ? cpi.pos : Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/* 5795 */       Vec3 v = MCH_Lib.RotVec3(cp, -getRotYaw(), -getRotPitch(), -getRotRoll());
/* 5796 */       this.camera.setPosition(x + v.field_72450_a, y + v.field_72448_b, z + v.field_72449_c);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateCameraRotate(float yaw, float pitch) {
/* 5802 */     this.camera.prevRotationYaw = this.camera.rotationYaw;
/* 5803 */     this.camera.prevRotationPitch = this.camera.rotationPitch;
/* 5804 */     this.camera.rotationYaw = yaw;
/* 5805 */     this.camera.rotationPitch = pitch;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updatePartCameraRotate() {
/* 5811 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/* 5813 */       Entity e = getEntityBySeatId(1);
/* 5814 */       if (e == null) e = getRiddenByEntity();
/*      */ 
/*      */ 
/*      */       
/* 5818 */       if (e != null) {
/*      */         
/* 5820 */         this.camera.partRotationYaw = e.field_70177_z;
/*      */         
/* 5822 */         float pitch = e.field_70125_A;
/* 5823 */         this.camera.prevPartRotationYaw = this.camera.partRotationYaw;
/* 5824 */         this.camera.prevPartRotationPitch = this.camera.partRotationPitch;
/*      */ 
/*      */         
/* 5827 */         this.camera.partRotationPitch = pitch;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTVMissile(MCH_EntityTvMissile entity) {
/* 5834 */     this.TVmissile = entity;
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_EntityTvMissile getTVMissile() {
/* 5839 */     return (this.TVmissile != null && !this.TVmissile.field_70128_L) ? this.TVmissile : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MCH_WeaponSet[] createWeapon(int seat_num) {
/* 5848 */     this.currentWeaponID = new int[seat_num];
/* 5849 */     for (int i = 0; i < this.currentWeaponID.length; ) { this.currentWeaponID[i] = -1; i++; }
/*      */     
/* 5851 */     if (getAcInfo() == null || (getAcInfo()).weaponSetList.size() <= 0 || seat_num <= 0)
/*      */     {
/* 5853 */       return new MCH_WeaponSet[] { this.dummyWeapon };
/*      */     }
/*      */     
/* 5856 */     MCH_WeaponSet[] weaponSetArray = new MCH_WeaponSet[(getAcInfo()).weaponSetList.size()];
/* 5857 */     for (int j = 0; j < (getAcInfo()).weaponSetList.size(); j++) {
/*      */       
/* 5859 */       MCH_AircraftInfo.WeaponSet ws = (getAcInfo()).weaponSetList.get(j);
/* 5860 */       MCH_WeaponBase[] wb = new MCH_WeaponBase[ws.weapons.size()];
/* 5861 */       for (int k = 0; k < ws.weapons.size(); k++) {
/*      */         
/* 5863 */         wb[k] = MCH_WeaponCreator.createWeapon(this.field_70170_p, ws.type, ((MCH_AircraftInfo.Weapon)ws.weapons.get(k)).pos, ((MCH_AircraftInfo.Weapon)ws.weapons.get(k)).yaw, ((MCH_AircraftInfo.Weapon)ws.weapons.get(k)).pitch, this, ((MCH_AircraftInfo.Weapon)ws.weapons.get(k)).turret);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5871 */         (wb[k]).aircraft = this;
/*      */       } 
/* 5873 */       if (wb.length > 0 && wb[0] != null) {
/*      */         
/* 5875 */         float defYaw = ((MCH_AircraftInfo.Weapon)ws.weapons.get(0)).defaultYaw;
/* 5876 */         weaponSetArray[j] = new MCH_WeaponSet(wb);
/* 5877 */         (weaponSetArray[j]).prevRotationYaw = defYaw;
/* 5878 */         (weaponSetArray[j]).rotationYaw = defYaw;
/* 5879 */         (weaponSetArray[j]).defaultRotationYaw = defYaw;
/*      */       } 
/*      */     } 
/*      */     
/* 5883 */     return weaponSetArray;
/*      */   }
/*      */ 
/*      */   
/*      */   public void switchWeapon(Entity entity, int id) {
/* 5888 */     int sid = getSeatIdByEntity(entity);
/* 5889 */     if (!isValidSeatID(sid))
/*      */       return; 
/* 5891 */     int beforeWeaponID = this.currentWeaponID[sid];
/*      */     
/* 5893 */     if (getWeaponNum() <= 0 || this.currentWeaponID.length <= 0)
/* 5894 */       return;  if (id < 0)
/*      */     {
/* 5896 */       this.currentWeaponID[sid] = -1;
/*      */     }
/* 5898 */     if (id >= getWeaponNum()) id = getWeaponNum() - 1;
/*      */     
/* 5900 */     MCH_Lib.DbgLog(this.field_70170_p, "switchWeapon:" + W_Entity.getEntityId(entity) + " -> " + id, new Object[0]);
/*      */     
/* 5902 */     getCurrentWeapon(entity).reload();
/* 5903 */     this.currentWeaponID[sid] = id;
/* 5904 */     MCH_WeaponSet ws = getCurrentWeapon(entity);
/* 5905 */     ws.onSwitchWeapon(this.field_70170_p.field_72995_K, isInfinityAmmo(entity));
/*      */     
/* 5907 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/* 5909 */       MCH_PacketNotifyWeaponID.send((Entity)this, sid, id, ws.getAmmoNum(), ws.getRestAllAmmoNum());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateWeaponID(int sid, int id) {
/* 5916 */     if (sid < 0 || sid >= this.currentWeaponID.length)
/*      */       return; 
/* 5918 */     if (getWeaponNum() <= 0 || this.currentWeaponID.length <= 0)
/* 5919 */       return;  if (id < 0)
/*      */     {
/* 5921 */       this.currentWeaponID[sid] = -1;
/*      */     }
/* 5923 */     if (id >= getWeaponNum()) id = getWeaponNum() - 1;
/*      */     
/* 5925 */     MCH_Lib.DbgLog(this.field_70170_p, "switchWeapon:seatID=" + sid + ", WeaponID=" + id, new Object[0]);
/*      */     
/* 5927 */     this.currentWeaponID[sid] = id;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateWeaponRestAmmo(int id, int num) {
/* 5932 */     if (id < getWeaponNum())
/*      */     {
/* 5934 */       getWeapon(id).setRestAllAmmoNum(num);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_WeaponSet getWeaponByName(String name) {
/* 5940 */     for (MCH_WeaponSet ws : this.weapons) {
/*      */       
/* 5942 */       if (ws.isEqual(name)) return ws; 
/*      */     } 
/* 5944 */     return null;
/*      */   }
/*      */   
/*      */   public int getWeaponIdByName(String name) {
/* 5948 */     int id = 0;
/* 5949 */     for (MCH_WeaponSet ws : this.weapons) {
/*      */       
/* 5951 */       if (ws.isEqual(name)) return id; 
/* 5952 */       id++;
/*      */     } 
/* 5954 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void reloadAllWeapon() {
/* 5959 */     for (int i = 0; i < getWeaponNum(); i++)
/*      */     {
/* 5961 */       getWeapon(i).reloadMag();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MCH_WeaponSet getFirstSeatWeapon() {
/* 5968 */     if (this.currentWeaponID != null && this.currentWeaponID.length > 0 && this.currentWeaponID[0] >= 0)
/*      */     {
/*      */ 
/*      */       
/* 5972 */       return getWeapon(this.currentWeaponID[0]);
/*      */     }
/* 5974 */     return getWeapon(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initCurrentWeapon(Entity entity) {
/* 5980 */     int sid = getSeatIdByEntity(entity);
/* 5981 */     MCH_Lib.DbgLog(this.field_70170_p, "initCurrentWeapon:" + W_Entity.getEntityId(entity) + ":%d", new Object[] { Integer.valueOf(sid) });
/* 5982 */     if (sid < 0 || sid >= this.currentWeaponID.length)
/*      */       return; 
/* 5984 */     this.currentWeaponID[sid] = -1;
/*      */     
/* 5986 */     if (entity instanceof EntityPlayer) {
/*      */       
/* 5988 */       this.currentWeaponID[sid] = getNextWeaponID(entity, 1);
/* 5989 */       switchWeapon(entity, getCurrentWeaponID(entity));
/*      */ 
/*      */       
/* 5992 */       if (this.field_70170_p.field_72995_K)
/*      */       {
/* 5994 */         MCH_PacketIndNotifyAmmoNum.send(this, -1);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void initPilotWeapon() {
/* 6000 */     this.currentWeaponID[0] = -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_WeaponSet getCurrentWeapon(Entity entity) {
/* 6005 */     return getWeapon(getCurrentWeaponID(entity));
/*      */   }
/*      */   
/*      */   protected MCH_WeaponSet getWeapon(int id) {
/* 6009 */     if (id < 0 || this.weapons.length <= 0 || id >= this.weapons.length)
/*      */     {
/* 6011 */       return this.dummyWeapon;
/*      */     }
/* 6013 */     return this.weapons[id];
/*      */   }
/*      */   
/*      */   public int getWeaponIDBySeatID(int sid) {
/* 6017 */     if (sid < 0 || sid >= this.currentWeaponID.length)
/*      */     {
/* 6019 */       return -1;
/*      */     }
/* 6021 */     return this.currentWeaponID[sid];
/*      */   }
/*      */ 
/*      */   
/*      */   public double getLandInDistance(Entity user) {
/* 6026 */     if (this.lastCalcLandInDistanceCount != getCountOnUpdate() && getCountOnUpdate() % 5 == 0) {
/*      */       
/* 6028 */       this.lastCalcLandInDistanceCount = getCountOnUpdate();
/*      */       
/* 6030 */       MCH_WeaponParam prm = new MCH_WeaponParam();
/* 6031 */       prm.setPosition(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 6032 */       prm.entity = (Entity)this;
/* 6033 */       prm.user = user;
/* 6034 */       prm.isInfinity = isInfinityAmmo(prm.user);
/*      */       
/* 6036 */       if (prm.user != null) {
/*      */         
/* 6038 */         MCH_WeaponSet currentWs = getCurrentWeapon(prm.user);
/* 6039 */         if (currentWs != null) {
/*      */           
/* 6041 */           int sid = getSeatIdByEntity(prm.user);
/* 6042 */           if (getAcInfo().getWeaponSetById(sid) != null)
/*      */           {
/* 6044 */             prm.isTurret = ((MCH_AircraftInfo.Weapon)(getAcInfo().getWeaponSetById(sid)).weapons.get(0)).turret;
/*      */           }
/* 6046 */           this.lastLandInDistance = currentWs.getLandInDistance(prm);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 6051 */     return this.lastLandInDistance;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean useCurrentWeapon(Entity user) {
/* 6057 */     MCH_WeaponParam prm = new MCH_WeaponParam();
/* 6058 */     prm.setPosition(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 6059 */     prm.entity = (Entity)this;
/* 6060 */     prm.user = user;
/* 6061 */     return useCurrentWeapon(prm);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean useCurrentWeapon(MCH_WeaponParam prm) {
/* 6066 */     prm.isInfinity = isInfinityAmmo(prm.user);
/*      */     
/* 6068 */     if (prm.user != null) {
/*      */       
/* 6070 */       MCH_WeaponSet currentWs = getCurrentWeapon(prm.user);
/* 6071 */       if (currentWs != null && currentWs.canUse()) {
/*      */         
/* 6073 */         int sid = getSeatIdByEntity(prm.user);
/* 6074 */         if (getAcInfo().getWeaponSetById(sid) != null)
/*      */         {
/* 6076 */           prm.isTurret = ((MCH_AircraftInfo.Weapon)(getAcInfo().getWeaponSetById(sid)).weapons.get(0)).turret;
/*      */         }
/* 6078 */         int lastUsedIndex = currentWs.getCurrentWeaponIndex();
/* 6079 */         if (currentWs.use(prm)) {
/*      */           
/* 6081 */           for (MCH_WeaponSet ws : this.weapons) {
/*      */             
/* 6083 */             if (ws != currentWs && !(ws.getInfo()).group.isEmpty() && (ws.getInfo()).group.equals((currentWs.getInfo()).group))
/*      */             {
/* 6085 */               ws.waitAndReloadByOther(prm.reload);
/*      */             }
/*      */           } 
/*      */           
/* 6089 */           if (!this.field_70170_p.field_72995_K) {
/*      */             
/* 6091 */             int shift = 0;
/* 6092 */             for (MCH_WeaponSet ws : this.weapons) {
/*      */               
/* 6094 */               if (ws == currentWs)
/* 6095 */                 break;  shift += ws.getWeaponNum();
/*      */             } 
/* 6097 */             shift += lastUsedIndex;
/* 6098 */             this.useWeaponStat |= (shift < 32) ? (1 << shift) : 0;
/*      */           } 
/* 6100 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/* 6104 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void switchCurrentWeaponMode(Entity entity) {
/* 6109 */     getCurrentWeapon(entity).switchMode();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWeaponNum() {
/* 6114 */     return this.weapons.length;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCurrentWeaponID(Entity entity) {
/* 6119 */     if (!(entity instanceof EntityPlayer)) return -1; 
/* 6120 */     int id = getSeatIdByEntity(entity);
/* 6121 */     return (id >= 0 && id < this.currentWeaponID.length) ? this.currentWeaponID[id] : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNextWeaponID(Entity entity, int step) {
/* 6126 */     if (getAcInfo() == null) return -1; 
/* 6127 */     int sid = getSeatIdByEntity(entity);
/* 6128 */     if (sid < 0) return -1; 
/* 6129 */     int id = getCurrentWeaponID(entity);
/*      */     
/*      */     int i;
/*      */     
/* 6133 */     for (i = 0; i < getWeaponNum(); i++) {
/*      */       
/* 6135 */       if (step >= 0) {
/*      */         
/* 6137 */         id = (id + 1) % getWeaponNum();
/*      */       }
/*      */       else {
/*      */         
/* 6141 */         id = (id > 0) ? (id - 1) : (getWeaponNum() - 1);
/*      */       } 
/* 6143 */       MCH_AircraftInfo.Weapon w = getAcInfo().getWeaponById(id);
/* 6144 */       if (w != null) {
/* 6145 */         MCH_WeaponInfo wi = getWeaponInfoById(id);
/* 6146 */         int wpsid = getWeaponSeatID(wi, w);
/* 6147 */         if (wpsid < getSeatNum() + 1 + 1) {
/*      */ 
/*      */ 
/*      */           
/* 6151 */           if (wpsid == sid) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/* 6156 */           if (sid == 0 && w.canUsePilot && !(getEntityBySeatId(wpsid) instanceof EntityPlayer))
/*      */             break; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 6161 */     if (i >= getWeaponNum()) return -1;
/*      */     
/* 6163 */     MCH_Lib.DbgLog(this.field_70170_p, "getNextWeaponID:%d:->%d", new Object[] { Integer.valueOf(W_Entity.getEntityId(entity)), Integer.valueOf(id) });
/*      */     
/* 6165 */     return id;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWeaponSeatID(MCH_WeaponInfo wi, MCH_AircraftInfo.Weapon w) {
/* 6170 */     if (wi != null && (wi.target & 0xC3) == 0 && wi.type.isEmpty())
/*      */     {
/* 6172 */       if (MCH_MOD.proxy.isSinglePlayer() || MCH_Config.TestMode.prmBool)
/*      */       {
/* 6174 */         return 1000;
/*      */       }
/*      */     }
/* 6177 */     return w.seatID;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMissileCameraMode(Entity entity) {
/* 6182 */     return (getTVMissile() != null && isCameraView(entity));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPilotReloading() {
/* 6187 */     return (getCommonStatus(2) || this.supplyAmmoWait > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUsedWeaponStat() {
/* 6192 */     if (getAcInfo() == null) return 0; 
/* 6193 */     if (getAcInfo().getWeaponNum() <= 0) return 0;
/*      */     
/* 6195 */     int stat = 0;
/* 6196 */     int i = 0;
/* 6197 */     for (MCH_WeaponSet w : this.weapons) {
/*      */       
/* 6199 */       if (i >= 32)
/* 6200 */         break;  for (int wi = 0; wi < w.getWeaponNum(); wi++) {
/*      */         
/* 6202 */         if (i >= 32)
/* 6203 */           break;  stat |= w.isUsed(wi) ? (1 << i) : 0;
/* 6204 */         i++;
/*      */       } 
/*      */     } 
/* 6207 */     return stat;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWeaponNotCooldown(MCH_WeaponSet checkWs, int index) {
/* 6212 */     if (getAcInfo() == null) return false; 
/* 6213 */     if (getAcInfo().getWeaponNum() <= 0) return false; 
/* 6214 */     int shift = 0;
/* 6215 */     for (MCH_WeaponSet ws : this.weapons) {
/*      */       
/* 6217 */       if (ws == checkWs) {
/*      */         break;
/*      */       }
/*      */       
/* 6221 */       shift += ws.getWeaponNum();
/*      */     } 
/* 6223 */     shift += index;
/* 6224 */     return (shift < 32) ? (((this.useWeaponStat & 1 << shift) != 0)) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateWeapons() {
/* 6229 */     if (getAcInfo() == null)
/* 6230 */       return;  if (getAcInfo().getWeaponNum() <= 0)
/*      */       return; 
/* 6232 */     int prevUseWeaponStat = this.useWeaponStat;
/* 6233 */     if (!this.field_70170_p.field_72995_K) {
/*      */ 
/*      */       
/* 6236 */       this.useWeaponStat |= getUsedWeaponStat();
/* 6237 */       func_70096_w().func_75692_b(24, new Integer(this.useWeaponStat));
/* 6238 */       this.useWeaponStat = 0;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 6243 */       this.useWeaponStat = func_70096_w().func_75679_c(24);
/*      */     } 
/*      */     
/* 6246 */     float yaw = MathHelper.func_76142_g(getRotYaw());
/* 6247 */     float pitch = MathHelper.func_76142_g(getRotPitch());
/*      */     
/* 6249 */     int id = 0;
/* 6250 */     for (int wid = 0; wid < this.weapons.length; wid++) {
/*      */       
/* 6252 */       MCH_WeaponSet w = this.weapons[wid];
/*      */       
/* 6254 */       boolean isLongDelay = false;
/* 6255 */       if (w.getFirstWeapon() != null)
/*      */       {
/* 6257 */         isLongDelay = w.isLongDelayWeapon();
/*      */       }
/*      */       
/* 6260 */       boolean isSelected = false;
/* 6261 */       for (int swid : this.currentWeaponID) {
/*      */         
/* 6263 */         if (swid == wid) {
/*      */           
/* 6265 */           isSelected = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 6270 */       boolean isWpnUsed = false;
/* 6271 */       for (int index = 0; index < w.getWeaponNum(); index++) {
/*      */         
/* 6273 */         boolean isPrevUsed = (id < 32 && (prevUseWeaponStat & 1 << id) != 0);
/* 6274 */         boolean isUsed = (id < 32 && (this.useWeaponStat & 1 << id) != 0);
/* 6275 */         if (isLongDelay && isPrevUsed && isUsed)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 6280 */           isUsed = false;
/*      */         }
/* 6282 */         isWpnUsed |= isUsed;
/*      */         
/* 6284 */         if (!isPrevUsed && isUsed == true) {
/*      */           
/* 6286 */           float recoil = (w.getInfo()).recoil;
/* 6287 */           if (recoil > 0.0F) {
/*      */             
/* 6289 */             this.recoilCount = 30;
/* 6290 */             this.recoilValue = recoil;
/* 6291 */             this.recoilYaw = w.rotationYaw;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 6297 */         if (this.field_70170_p.field_72995_K && isUsed) {
/*      */           
/* 6299 */           Vec3 wrv = MCH_Lib.RotVec3(0.0D, 0.0D, -1.0D, -w.rotationYaw - yaw, -w.rotationPitch);
/* 6300 */           Vec3 spv = w.getCurrentWeapon().getShotPos((Entity)this);
/* 6301 */           spawnParticleMuzzleFlash(this.field_70170_p, w.getInfo(), this.field_70165_t + spv.field_72450_a, this.field_70163_u + spv.field_72448_b, this.field_70161_v + spv.field_72449_c, wrv);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 6309 */         w.updateWeapon((Entity)this, isUsed, index);
/* 6310 */         id++;
/*      */       } 
/* 6312 */       w.update((Entity)this, isSelected, isWpnUsed);
/*      */       
/* 6314 */       MCH_AircraftInfo.Weapon wi = getAcInfo().getWeaponById(wid);
/* 6315 */       if (wi != null && !isDestroyed()) {
/*      */         
/* 6317 */         Entity entity = getEntityBySeatId(getWeaponSeatID(getWeaponInfoById(wid), wi));
/* 6318 */         if (wi.canUsePilot && !(entity instanceof EntityPlayer))
/*      */         {
/* 6320 */           entity = getEntityBySeatId(0);
/*      */         }
/* 6322 */         if (entity instanceof EntityPlayer) {
/*      */           
/* 6324 */           if ((int)wi.minYaw != 0 || (int)wi.maxYaw != 0) {
/*      */             
/* 6326 */             float ty = wi.turret ? (MathHelper.func_76142_g(getLastRiderYaw()) - yaw) : 0.0F;
/* 6327 */             float ey = MathHelper.func_76142_g(entity.field_70177_z - yaw - wi.defaultYaw - ty);
/* 6328 */             if (Math.abs((int)wi.minYaw) < 360 && Math.abs((int)wi.maxYaw) < 360) {
/*      */               
/* 6330 */               float targetYaw = MCH_Lib.RNG(ey, wi.minYaw, wi.maxYaw);
/*      */               
/* 6332 */               float wy = w.rotationYaw - wi.defaultYaw - ty;
/*      */               
/* 6334 */               if (targetYaw < wy) {
/*      */                 
/* 6336 */                 if (wy - targetYaw > 15.0F) { wy -= 15.0F; }
/* 6337 */                 else { wy = targetYaw; }
/*      */               
/* 6339 */               } else if (targetYaw > wy) {
/*      */                 
/* 6341 */                 if (targetYaw - wy > 15.0F) { wy += 15.0F; }
/* 6342 */                 else { wy = targetYaw; }
/*      */               
/* 6344 */               }  w.rotationYaw = wy + wi.defaultYaw + ty;
/*      */             }
/*      */             else {
/*      */               
/* 6348 */               w.rotationYaw = ey + ty;
/*      */             } 
/*      */           } 
/*      */           
/* 6352 */           float ep = MathHelper.func_76142_g(entity.field_70125_A - pitch);
/* 6353 */           w.rotationPitch = MCH_Lib.RNG(ep, wi.minPitch, wi.maxPitch);
/*      */           
/* 6355 */           w.rotationTurretYaw = 0.0F;
/*      */         }
/*      */         else {
/*      */           
/* 6359 */           w.rotationTurretYaw = getLastRiderYaw() - getRotYaw();
/* 6360 */           if (getTowedChainEntity() != null || this.field_70154_o != null)
/*      */           {
/* 6362 */             w.rotationYaw = 0.0F;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 6368 */     updateWeaponBay();
/*      */     
/* 6370 */     if (this.hitStatus > 0) this.hitStatus--;
/*      */   
/*      */   }
/*      */   
/*      */   public void updateWeaponsRotation() {
/* 6375 */     if (getAcInfo() == null)
/* 6376 */       return;  if (getAcInfo().getWeaponNum() <= 0)
/* 6377 */       return;  if (isDestroyed())
/*      */       return; 
/* 6379 */     float yaw = MathHelper.func_76142_g(getRotYaw());
/* 6380 */     float pitch = MathHelper.func_76142_g(getRotPitch());
/*      */     
/* 6382 */     for (int wid = 0; wid < this.weapons.length; wid++) {
/*      */       
/* 6384 */       MCH_WeaponSet w = this.weapons[wid];
/*      */       
/* 6386 */       MCH_AircraftInfo.Weapon wi = getAcInfo().getWeaponById(wid);
/* 6387 */       if (wi != null) {
/*      */         
/* 6389 */         Entity entity = getEntityBySeatId(getWeaponSeatID(getWeaponInfoById(wid), wi));
/* 6390 */         if (wi.canUsePilot && !(entity instanceof EntityPlayer))
/*      */         {
/* 6392 */           entity = getEntityBySeatId(0);
/*      */         }
/* 6394 */         if (entity instanceof EntityPlayer) {
/*      */           
/* 6396 */           if ((int)wi.minYaw != 0 || (int)wi.maxYaw != 0) {
/*      */             
/* 6398 */             float ty = wi.turret ? (MathHelper.func_76142_g(getLastRiderYaw()) - yaw) : 0.0F;
/* 6399 */             float ey = MathHelper.func_76142_g(entity.field_70177_z - yaw - wi.defaultYaw - ty);
/* 6400 */             if (Math.abs((int)wi.minYaw) < 360 && Math.abs((int)wi.maxYaw) < 360) {
/*      */               
/* 6402 */               float targetYaw = MCH_Lib.RNG(ey, wi.minYaw, wi.maxYaw);
/*      */               
/* 6404 */               float wy = w.rotationYaw - wi.defaultYaw - ty;
/*      */               
/* 6406 */               if (targetYaw < wy) {
/*      */                 
/* 6408 */                 if (wy - targetYaw > 15.0F) { wy -= 15.0F; }
/* 6409 */                 else { wy = targetYaw; }
/*      */               
/* 6411 */               } else if (targetYaw > wy) {
/*      */                 
/* 6413 */                 if (targetYaw - wy > 15.0F) { wy += 15.0F; }
/* 6414 */                 else { wy = targetYaw; }
/*      */               
/* 6416 */               }  w.rotationYaw = wy + wi.defaultYaw + ty;
/*      */             }
/*      */             else {
/*      */               
/* 6420 */               w.rotationYaw = ey + ty;
/*      */             } 
/*      */           } 
/*      */           
/* 6424 */           float ep = MathHelper.func_76142_g(entity.field_70125_A - pitch);
/* 6425 */           w.rotationPitch = MCH_Lib.RNG(ep, wi.minPitch, wi.maxPitch);
/*      */           
/* 6427 */           w.rotationTurretYaw = 0.0F;
/*      */         }
/*      */         else {
/*      */           
/* 6431 */           w.rotationTurretYaw = getLastRiderYaw() - getRotYaw();
/*      */         } 
/*      */       } 
/*      */       
/* 6435 */       w.prevRotationYaw = w.rotationYaw;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void spawnParticleMuzzleFlash(World w, MCH_WeaponInfo wi, double px, double py, double pz, Vec3 wrv) {
/* 6441 */     if (wi.listMuzzleFlashSmoke != null)
/*      */     {
/* 6443 */       for (MCH_WeaponInfo.MuzzleFlash mf : wi.listMuzzleFlashSmoke) {
/*      */         
/* 6445 */         double x = px + -wrv.field_72450_a * mf.dist;
/* 6446 */         double y = py + -wrv.field_72448_b * mf.dist;
/* 6447 */         double z = pz + -wrv.field_72449_c * mf.dist;
/*      */         
/* 6449 */         MCH_ParticleParam p = new MCH_ParticleParam(w, "smoke", px, py, pz);
/* 6450 */         p.size = mf.size;
/* 6451 */         for (int i = 0; i < mf.num; i++) {
/*      */           
/* 6453 */           p.a = mf.a * 0.9F + w.field_73012_v.nextFloat() * 0.1F;
/*      */           
/* 6455 */           float color = w.field_73012_v.nextFloat() * 0.1F;
/* 6456 */           p.r = color + mf.r * 0.9F;
/* 6457 */           p.g = color + mf.g * 0.9F;
/* 6458 */           p.b = color + mf.b * 0.9F;
/*      */           
/* 6460 */           p.age = (int)(mf.age + 0.1D * mf.age * w.field_73012_v.nextFloat());
/* 6461 */           p.posX = x + (w.field_73012_v.nextDouble() - 0.5D) * mf.range;
/* 6462 */           p.posY = y + (w.field_73012_v.nextDouble() - 0.5D) * mf.range;
/* 6463 */           p.posZ = z + (w.field_73012_v.nextDouble() - 0.5D) * mf.range;
/*      */           
/* 6465 */           p.motionX = w.field_73012_v.nextDouble() * ((p.posX < x) ? -0.2D : 0.2D);
/* 6466 */           p.motionY = w.field_73012_v.nextDouble() * ((p.posY < y) ? -0.03D : 0.03D);
/* 6467 */           p.motionZ = w.field_73012_v.nextDouble() * ((p.posZ < z) ? -0.2D : 0.2D);
/*      */           
/* 6469 */           MCH_ParticlesUtil.spawnParticle(p);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 6474 */     if (wi.listMuzzleFlash != null)
/*      */     {
/* 6476 */       for (MCH_WeaponInfo.MuzzleFlash mf : wi.listMuzzleFlash) {
/*      */         
/* 6478 */         float color = this.field_70146_Z.nextFloat() * 0.1F + 0.9F;
/* 6479 */         MCH_ParticlesUtil.spawnParticleExplode(this.field_70170_p, px + -wrv.field_72450_a * mf.dist, py + -wrv.field_72448_b * mf.dist, pz + -wrv.field_72449_c * mf.dist, mf.size, color * mf.r, color * mf.g, color * mf.b, mf.a, mf.age + w.field_73012_v.nextInt(3));
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
/*      */ 
/*      */   
/*      */   private void updateWeaponBay() {
/* 6495 */     for (int i = 0; i < this.weaponBays.length; i++) {
/*      */       
/* 6497 */       WeaponBay wb = this.weaponBays[i];
/* 6498 */       MCH_AircraftInfo.WeaponBay info = (getAcInfo()).partWeaponBay.get(i);
/* 6499 */       boolean isSelected = false; Integer[] arr$; int len$, i$;
/* 6500 */       for (arr$ = info.weaponIds, len$ = arr$.length, i$ = 0; i$ < len$; ) { int wid = arr$[i$].intValue();
/*      */         
/* 6502 */         for (int sid = 0; sid < this.currentWeaponID.length; sid++) {
/*      */           
/* 6504 */           if (wid == this.currentWeaponID[sid] && getEntityBySeatId(sid) != null)
/*      */           {
/* 6506 */             isSelected = true; } 
/*      */         } 
/*      */         i$++; }
/*      */       
/* 6510 */       wb.prevRot = wb.rot;
/* 6511 */       if (isSelected) {
/*      */         
/* 6513 */         if (wb.rot < 90.0F)
/*      */         {
/* 6515 */           wb.rot += 3.0F;
/*      */         }
/* 6517 */         if (wb.rot >= 90.0F)
/*      */         {
/* 6519 */           wb.rot = 90.0F;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 6524 */         if (wb.rot > 0.0F)
/*      */         {
/* 6526 */           wb.rot -= 3.0F;
/*      */         }
/* 6528 */         if (wb.rot <= 0.0F)
/*      */         {
/* 6530 */           wb.rot = 0.0F;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHitStatus() {
/* 6538 */     return this.hitStatus;
/*      */   }
/*      */   
/*      */   public int getMaxHitStatus() {
/* 6542 */     return 15;
/*      */   }
/*      */ 
/*      */   
/*      */   public void hitBullet() {
/* 6547 */     this.hitStatus = getMaxHitStatus();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initRotationYaw(float yaw) {
/* 6557 */     this.field_70177_z = yaw;
/* 6558 */     this.field_70126_B = yaw;
/* 6559 */     this.lastRiderYaw = yaw;
/* 6560 */     this.lastSearchLightYaw = yaw;
/*      */     
/* 6562 */     for (MCH_WeaponSet w : this.weapons) {
/*      */       
/* 6564 */       w.rotationYaw = w.defaultRotationYaw;
/* 6565 */       w.rotationPitch = 0.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_AircraftInfo getAcInfo() {
/* 6571 */     return this.acInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract Item getItem();
/*      */   
/*      */   public void setAcInfo(MCH_AircraftInfo info) {
/* 6578 */     this.acInfo = info;
/* 6579 */     if (info != null) {
/*      */       
/* 6581 */       this.partHatch = createHatch();
/* 6582 */       this.partCanopy = createCanopy();
/* 6583 */       this.partLandingGear = createLandingGear();
/*      */       
/* 6585 */       this.weaponBays = createWeaponBays();
/*      */       
/* 6587 */       this.rotPartRotation = new float[info.partRotPart.size()];
/* 6588 */       this.prevRotPartRotation = new float[info.partRotPart.size()];
/*      */       
/* 6590 */       this.extraBoundingBox = createExtraBoundingBox();
/*      */       
/* 6592 */       this.partEntities = createParts();
/*      */       
/* 6594 */       this.field_70138_W = info.stepHeight;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_BoundingBox[] createExtraBoundingBox() {
/* 6600 */     MCH_BoundingBox[] ar = new MCH_BoundingBox[(getAcInfo()).extraBoundingBox.size()];
/*      */     
/* 6602 */     int i = 0;
/* 6603 */     for (MCH_BoundingBox bb : (getAcInfo()).extraBoundingBox) {
/*      */       
/* 6605 */       ar[i] = bb.copy();
/* 6606 */       i++;
/*      */     } 
/*      */     
/* 6609 */     return ar;
/*      */   }
/*      */   
/*      */   public Entity[] createParts() {
/* 6613 */     Entity[] list = new Entity[1];
/* 6614 */     list[0] = this.partEntities[0];
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
/* 6626 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateUAV() {
/* 6632 */     if (!isUAV()) {
/*      */       return;
/*      */     }
/* 6635 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/* 6637 */       int eid = func_70096_w().func_75679_c(22);
/* 6638 */       if (eid > 0)
/*      */       {
/* 6640 */         if (this.uavStation == null)
/*      */         {
/* 6642 */           Entity uavEntity = this.field_70170_p.func_73045_a(eid);
/* 6643 */           if (uavEntity instanceof MCH_EntityUavStation)
/*      */           {
/* 6645 */             this.uavStation = (MCH_EntityUavStation)uavEntity;
/* 6646 */             this.uavStation.setControlAircract(this);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 6652 */       else if (this.uavStation != null)
/*      */       {
/* 6654 */         this.uavStation.setControlAircract(null);
/* 6655 */         this.uavStation = null;
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 6662 */     else if (this.uavStation != null) {
/*      */       
/* 6664 */       double udx = this.field_70165_t - this.uavStation.field_70165_t;
/* 6665 */       double udz = this.field_70161_v - this.uavStation.field_70161_v;
/* 6666 */       if (udx * udx + udz * udz > 15129.0D) {
/*      */         
/* 6668 */         this.uavStation.setControlAircract(null);
/* 6669 */         setUavStation(null);
/* 6670 */         attackEntityFrom(DamageSource.field_76380_i, getMaxHP() + 10);
/*      */       } 
/*      */     } 
/*      */     
/* 6674 */     if (this.uavStation != null && this.uavStation.field_70128_L)
/*      */     {
/* 6676 */       this.uavStation = null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void switchGunnerMode(boolean mode) {
/* 6686 */     boolean debug_bk_mode = this.isGunnerMode;
/* 6687 */     Entity pilot = getEntityBySeatId(0);
/*      */ 
/*      */ 
/*      */     
/* 6691 */     if (!mode || canSwitchGunnerMode())
/*      */     {
/* 6693 */       if (this.isGunnerMode == true && !mode) {
/*      */         
/* 6695 */         setCurrentThrottle(this.beforeHoverThrottle);
/* 6696 */         this.isGunnerMode = false;
/* 6697 */         this.camera.setCameraZoom(1.0F);
/* 6698 */         getCurrentWeapon(pilot).onSwitchWeapon(this.field_70170_p.field_72995_K, isInfinityAmmo(pilot));
/*      */       }
/* 6700 */       else if (!this.isGunnerMode && mode == true) {
/*      */         
/* 6702 */         this.beforeHoverThrottle = getCurrentThrottle();
/* 6703 */         this.isGunnerMode = true;
/* 6704 */         this.camera.setCameraZoom(1.0F);
/* 6705 */         getCurrentWeapon(pilot).onSwitchWeapon(this.field_70170_p.field_72995_K, isInfinityAmmo(pilot));
/*      */       } 
/*      */     }
/*      */     
/* 6709 */     MCH_Lib.DbgLog(this.field_70170_p, "switchGunnerMode %s->%s", new Object[] { debug_bk_mode ? "ON" : "OFF", mode ? "ON" : "OFF" });
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSwitchGunnerMode() {
/* 6714 */     if (getAcInfo() == null || !(getAcInfo()).isEnableGunnerMode) return false;
/*      */ 
/*      */     
/* 6717 */     if (!isCanopyClose()) return false;
/*      */     
/* 6719 */     if (!(getAcInfo()).isEnableConcurrentGunnerMode)
/*      */     {
/*      */       
/* 6722 */       if (getEntityBySeatId(1) instanceof EntityPlayer) return false; 
/*      */     }
/* 6724 */     return !isHoveringMode();
/*      */   }
/*      */   
/*      */   public boolean canSwitchGunnerModeOtherSeat(EntityPlayer player) {
/* 6728 */     int sid = getSeatIdByEntity((Entity)player);
/*      */     
/* 6730 */     if (sid > 0) {
/*      */       
/* 6732 */       MCH_SeatInfo info = getSeatInfo(sid);
/* 6733 */       if (info != null)
/*      */       {
/* 6735 */         return (info.gunner && info.switchgunner);
/*      */       }
/*      */     } 
/* 6738 */     return false;
/*      */   }
/*      */   
/*      */   public void switchGunnerModeOtherSeat(EntityPlayer player) {
/* 6742 */     this.isGunnerModeOtherSeat = !this.isGunnerModeOtherSeat;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHoveringMode() {
/* 6748 */     return this.isHoveringMode;
/*      */   }
/*      */   
/*      */   public void switchHoveringMode(boolean mode) {
/* 6752 */     stopRepelling();
/*      */     
/* 6754 */     if (canSwitchHoveringMode())
/*      */     {
/* 6756 */       if (isHoveringMode() != mode) {
/*      */         
/* 6758 */         if (mode) {
/*      */           
/* 6760 */           this.beforeHoverThrottle = getCurrentThrottle();
/*      */         }
/*      */         else {
/*      */           
/* 6764 */           setCurrentThrottle(this.beforeHoverThrottle);
/*      */         } 
/*      */         
/* 6767 */         this.isHoveringMode = mode;
/* 6768 */         if (this.field_70153_n != null) {
/*      */           
/* 6770 */           this.field_70153_n.field_70125_A = 0.0F;
/* 6771 */           this.field_70153_n.field_70127_C = 0.0F;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean canSwitchHoveringMode() {
/* 6778 */     if (getAcInfo() == null) return false; 
/* 6779 */     return !this.isGunnerMode;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHovering() {
/* 6784 */     return (this.isGunnerMode || isHoveringMode());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIsGunnerMode(Entity entity) {
/* 6790 */     if (getAcInfo() == null) return false;
/*      */     
/* 6792 */     int id = getSeatIdByEntity(entity);
/* 6793 */     if (id < 0) return false;
/*      */ 
/*      */     
/* 6796 */     if (id == 0 && (getAcInfo()).isEnableGunnerMode)
/*      */     {
/* 6798 */       return this.isGunnerMode;
/*      */     }
/*      */     
/* 6801 */     MCH_SeatInfo[] st = getSeatsInfo();
/* 6802 */     if (id < st.length)
/*      */     {
/* 6804 */       if ((st[id]).gunner) {
/*      */         
/* 6806 */         if (this.field_70170_p.field_72995_K && (st[id]).switchgunner)
/*      */         {
/* 6808 */           return this.isGunnerModeOtherSeat;
/*      */         }
/*      */ 
/*      */         
/* 6812 */         return true;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 6817 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPilot(Entity player) {
/* 6822 */     return W_Entity.isEqual(getRiddenByEntity(), player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSwitchFreeLook() {
/* 6828 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFreeLookMode() {
/* 6833 */     return (getCommonStatus(1) || isRepelling());
/*      */   }
/*      */   
/*      */   public void switchFreeLookMode(boolean b) {
/* 6837 */     setCommonStatus(1, b);
/*      */   }
/*      */   
/*      */   public void switchFreeLookModeClient(boolean b) {
/* 6841 */     setCommonStatus(1, b, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSwitchGunnerFreeLook(EntityPlayer player) {
/* 6846 */     MCH_SeatInfo seatInfo = getSeatInfo((Entity)player);
/* 6847 */     if (seatInfo != null && seatInfo.fixRot && getIsGunnerMode((Entity)player))
/*      */     {
/* 6849 */       return true;
/*      */     }
/* 6851 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isGunnerLookMode(EntityPlayer player) {
/* 6856 */     if (isPilot((Entity)player))
/*      */     {
/* 6858 */       return false;
/*      */     }
/* 6860 */     return this.isGunnerFreeLookMode;
/*      */   }
/*      */ 
/*      */   
/*      */   public void switchGunnerFreeLookMode(boolean b) {
/* 6865 */     this.isGunnerFreeLookMode = b;
/*      */   }
/*      */   
/*      */   public void switchGunnerFreeLookMode() {
/* 6869 */     switchGunnerFreeLookMode(!this.isGunnerFreeLookMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateParts(int stat) {
/* 6878 */     if (isDestroyed())
/*      */       return; 
/* 6880 */     MCH_Parts[] parts = { this.partHatch, this.partCanopy, this.partLandingGear };
/*      */ 
/*      */     
/* 6883 */     for (MCH_Parts p : parts) {
/*      */       
/* 6885 */       if (p != null) {
/*      */         
/* 6887 */         p.updateStatusClient(stat);
/* 6888 */         p.update();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 6893 */     if (!isDestroyed() && !this.field_70170_p.field_72995_K && this.partLandingGear != null) {
/*      */       
/* 6895 */       int blockId = 0;
/*      */       
/* 6897 */       if (!isLandingGearFolded() && this.partLandingGear.getFactor() <= 0.1F) {
/*      */         
/* 6899 */         blockId = MCH_Lib.getBlockIdY((Entity)this, 3, -20);
/*      */         
/* 6901 */         if (getCurrentThrottle() <= 0.800000011920929D || this.field_70122_E || blockId != 0)
/*      */         {
/*      */ 
/*      */           
/* 6905 */           if ((getAcInfo()).isFloat && (func_70090_H() || MCH_Lib.getBlockY((Entity)this, 3, -20, true) == W_Block.getWater()))
/*      */           {
/* 6907 */             this.partLandingGear.setStatusServer(true);
/*      */           }
/*      */         }
/* 6910 */       } else if (isLandingGearFolded() == true && this.partLandingGear.getFactor() >= 0.9F) {
/*      */ 
/*      */         
/* 6913 */         blockId = MCH_Lib.getBlockIdY((Entity)this, 3, -10);
/* 6914 */         if (getCurrentThrottle() < getUnfoldLandingGearThrottle() && blockId != 0) {
/*      */           
/* 6916 */           boolean unfold = true;
/*      */ 
/*      */           
/* 6919 */           if ((getAcInfo()).isFloat) {
/*      */             
/* 6921 */             blockId = MCH_Lib.getBlockIdY(this.field_70170_p, this.field_70165_t, this.field_70163_u + 1.0D + (getAcInfo()).floatOffset, this.field_70161_v, 1, -150, true);
/*      */ 
/*      */ 
/*      */             
/* 6925 */             if (W_Block.isEqual(blockId, W_Block.getWater()))
/*      */             {
/* 6927 */               unfold = false;
/*      */             }
/*      */           } 
/*      */           
/* 6931 */           if (unfold)
/*      */           {
/* 6933 */             this.partLandingGear.setStatusServer(false);
/*      */           }
/*      */         }
/* 6936 */         else if (getVtolMode() == 2) {
/*      */ 
/*      */           
/* 6939 */           if (blockId != 0)
/*      */           {
/* 6941 */             this.partLandingGear.setStatusServer(false);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getUnfoldLandingGearThrottle() {
/* 6950 */     return 0.8F;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getPartStatus() {
/* 6955 */     return func_70096_w().func_75679_c(31);
/*      */   }
/*      */   
/*      */   private void setPartStatus(int n) {
/* 6959 */     func_70096_w().func_75692_b(31, Integer.valueOf(n));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initPartRotation(float yaw, float pitch) {
/* 6964 */     this.lastRiderYaw = yaw;
/* 6965 */     this.prevLastRiderYaw = yaw;
/* 6966 */     this.camera.partRotationYaw = yaw;
/* 6967 */     this.camera.prevPartRotationYaw = yaw;
/* 6968 */     this.lastSearchLightYaw = yaw;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLastPartStatusMask() {
/* 6974 */     return 24;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getModeSwitchCooldown() {
/* 6983 */     return this.modeSwitchCooldown;
/*      */   }
/*      */   
/*      */   public void setModeSwitchCooldown(int n) {
/* 6987 */     this.modeSwitchCooldown = n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected WeaponBay[] createWeaponBays() {
/* 6996 */     WeaponBay[] wbs = new WeaponBay[(getAcInfo()).partWeaponBay.size()];
/* 6997 */     for (int i = 0; i < wbs.length; i++)
/*      */     {
/* 6999 */       wbs[i] = new WeaponBay(this);
/*      */     }
/* 7001 */     return wbs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MCH_Parts createHatch() {
/* 7010 */     MCH_Parts hatch = null;
/* 7011 */     if (getAcInfo().haveHatch()) {
/*      */       
/* 7013 */       hatch = new MCH_Parts((Entity)this, 4, 31, "Hatch");
/* 7014 */       hatch.rotationMax = 90.0F;
/* 7015 */       hatch.rotationInv = 1.5F;
/* 7016 */       hatch.soundEndSwichOn.setPrm("plane_cc", 1.0F, 1.0F);
/* 7017 */       hatch.soundEndSwichOff.setPrm("plane_cc", 1.0F, 1.0F);
/* 7018 */       hatch.soundSwitching.setPrm("plane_cv", 1.0F, 0.5F);
/*      */     } 
/* 7020 */     return hatch;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean haveHatch() {
/* 7025 */     return (this.partHatch != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canFoldHatch() {
/* 7030 */     if (this.partHatch == null || this.modeSwitchCooldown > 0) return false; 
/* 7031 */     return this.partHatch.isOFF();
/*      */   }
/*      */   
/*      */   public boolean canUnfoldHatch() {
/* 7035 */     if (this.partHatch == null || this.modeSwitchCooldown > 0) return false; 
/* 7036 */     return this.partHatch.isON();
/*      */   }
/*      */   
/*      */   public void foldHatch(boolean fold) {
/* 7040 */     foldHatch(fold, false);
/*      */   }
/*      */   
/*      */   public void foldHatch(boolean fold, boolean force) {
/* 7044 */     if (this.partHatch == null)
/* 7045 */       return;  if (!force && this.modeSwitchCooldown > 0)
/* 7046 */       return;  this.partHatch.setStatusServer(fold);
/* 7047 */     this.modeSwitchCooldown = 20;
/* 7048 */     if (!fold)
/*      */     {
/* 7050 */       stopUnmountCrew();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public float getHatchRotation() {
/* 7056 */     return (this.partHatch != null) ? this.partHatch.rotation : 0.0F;
/*      */   }
/*      */   
/*      */   public float getPrevHatchRotation() {
/* 7060 */     return (this.partHatch != null) ? this.partHatch.prevRotation : 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void foldLandingGear() {
/* 7070 */     if (this.partLandingGear == null || getModeSwitchCooldown() > 0)
/*      */       return; 
/* 7072 */     this.partLandingGear.setStatusServer(true);
/* 7073 */     setModeSwitchCooldown(20);
/*      */   }
/*      */   
/*      */   public void unfoldLandingGear() {
/* 7077 */     if (this.partLandingGear == null || getModeSwitchCooldown() > 0)
/* 7078 */       return;  if (isLandingGearFolded()) {
/*      */       
/* 7080 */       this.partLandingGear.setStatusServer(false);
/* 7081 */       setModeSwitchCooldown(20);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canFoldLandingGear() {
/* 7087 */     if (getLandingGearRotation() >= 1.0F) return false; 
/* 7088 */     Block block = MCH_Lib.getBlockY((Entity)this, 3, -10, true);
/* 7089 */     return (!isLandingGearFolded() && block == W_Blocks.field_150350_a);
/*      */   }
/*      */   
/*      */   public boolean canUnfoldLandingGear() {
/* 7093 */     if (getLandingGearRotation() < 89.0F) return false; 
/* 7094 */     return isLandingGearFolded();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLandingGearFolded() {
/* 7099 */     return (this.partLandingGear != null) ? this.partLandingGear.getStatus() : false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected MCH_Parts createLandingGear() {
/* 7104 */     MCH_Parts lg = null;
/* 7105 */     if (getAcInfo().haveLandingGear()) {
/*      */       
/* 7107 */       lg = new MCH_Parts((Entity)this, 2, 31, "LandingGear");
/* 7108 */       lg.rotationMax = 90.0F;
/* 7109 */       lg.rotationInv = 2.5F;
/* 7110 */       lg.soundStartSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 7111 */       lg.soundEndSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 7112 */       lg.soundStartSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/* 7113 */       lg.soundEndSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/* 7114 */       lg.soundSwitching.setPrm("plane_cv", 1.0F, 0.75F);
/*      */     } 
/* 7116 */     return lg;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getLandingGearRotation() {
/* 7121 */     return (this.partLandingGear != null) ? this.partLandingGear.rotation : 0.0F;
/*      */   }
/*      */   
/*      */   public float getPrevLandingGearRotation() {
/* 7125 */     return (this.partLandingGear != null) ? this.partLandingGear.prevRotation : 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVtolMode() {
/* 7131 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void openCanopy() {
/* 7140 */     if (this.partCanopy == null || getModeSwitchCooldown() > 0)
/*      */       return; 
/* 7142 */     this.partCanopy.setStatusServer(true);
/* 7143 */     setModeSwitchCooldown(20);
/*      */   }
/*      */   
/*      */   public void openCanopy_EjectSeat() {
/* 7147 */     if (this.partCanopy == null)
/*      */       return; 
/* 7149 */     this.partCanopy.setStatusServer(true, false);
/* 7150 */     setModeSwitchCooldown(40);
/*      */   }
/*      */   
/*      */   public void closeCanopy() {
/* 7154 */     if (this.partCanopy == null || getModeSwitchCooldown() > 0)
/* 7155 */       return;  if (getCanopyStat()) {
/*      */       
/* 7157 */       this.partCanopy.setStatusServer(false);
/* 7158 */       setModeSwitchCooldown(20);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCanopyStat() {
/* 7164 */     return (this.partCanopy != null) ? this.partCanopy.getStatus() : false;
/*      */   }
/*      */   
/*      */   public boolean isCanopyClose() {
/* 7168 */     if (this.partCanopy == null) return true; 
/* 7169 */     return (!getCanopyStat() && getCanopyRotation() <= 0.01F);
/*      */   }
/*      */   
/*      */   public float getCanopyRotation() {
/* 7173 */     return (this.partCanopy != null) ? this.partCanopy.rotation : 0.0F;
/*      */   }
/*      */   
/*      */   public float getPrevCanopyRotation() {
/* 7177 */     return (this.partCanopy != null) ? this.partCanopy.prevRotation : 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected MCH_Parts createCanopy() {
/* 7182 */     MCH_Parts canopy = null;
/* 7183 */     if (getAcInfo().haveCanopy()) {
/*      */       
/* 7185 */       canopy = new MCH_Parts((Entity)this, 0, 31, "Canopy");
/* 7186 */       canopy.rotationMax = 90.0F;
/* 7187 */       canopy.rotationInv = 3.5F;
/* 7188 */       canopy.soundEndSwichOn.setPrm("plane_cc", 1.0F, 1.0F);
/* 7189 */       canopy.soundEndSwichOff.setPrm("plane_cc", 1.0F, 1.0F);
/*      */     } 
/*      */     
/* 7192 */     return canopy;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasBrake() {
/* 7198 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBrake(boolean b) {
/* 7203 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/* 7205 */       setCommonStatus(11, b);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getBrake() {
/* 7211 */     return getCommonStatus(11);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70302_i_() {
/* 7220 */     return (getAcInfo() != null) ? (getAcInfo()).inventorySize : 0;
/*      */   }
/*      */   
/*      */   public String getInvName() {
/* 7224 */     if (getAcInfo() == null) return super.getInvName(); 
/* 7225 */     String s = (getAcInfo()).displayName;
/* 7226 */     return (s.length() <= 32) ? s : s.substring(0, 31);
/*      */   }
/*      */   
/*      */   public boolean isInvNameLocalized() {
/* 7230 */     return (getAcInfo() != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_EntityChain getTowChainEntity() {
/* 7235 */     return this.towChainEntity;
/*      */   }
/*      */   
/*      */   public void setTowChainEntity(MCH_EntityChain chainEntity) {
/* 7239 */     this.towChainEntity = chainEntity;
/*      */   }
/*      */ 
/*      */   
/*      */   public MCH_EntityChain getTowedChainEntity() {
/* 7244 */     return this.towedChainEntity;
/*      */   }
/*      */   
/*      */   public void setTowedChainEntity(MCH_EntityChain towedChainEntity) {
/* 7248 */     this.towedChainEntity = towedChainEntity;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_EntityAircraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */