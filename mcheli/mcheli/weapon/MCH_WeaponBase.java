/*     */ package mcheli.mcheli.weapon;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.weapon.MCH_IEntityLockChecker;
/*     */ import mcheli.weapon.MCH_SightType;
/*     */ import mcheli.weapon.MCH_WeaponGuidanceSystem;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class MCH_WeaponBase {
/*  17 */   protected static final Random rand = new Random();
/*     */   
/*     */   public final World worldObj;
/*     */   
/*     */   public final Vec3 position;
/*     */   
/*     */   public final float fixRotationYaw;
/*     */   
/*     */   public final float fixRotationPitch;
/*     */   
/*     */   public final String name;
/*     */   
/*     */   public final MCH_WeaponInfo weaponInfo;
/*     */   
/*     */   public String displayName;
/*     */   
/*     */   public int power;
/*     */   
/*     */   public float acceleration;
/*     */   
/*     */   public int explosionPower;
/*     */   public int explosionPowerInWater;
/*     */   public int interval;
/*     */   public int numMode;
/*     */   public int lockTime;
/*     */   public int piercing;
/*     */   public int heatCount;
/*     */   public MCH_Cartridge cartridge;
/*     */   public boolean onTurret;
/*     */   public MCH_EntityAircraft aircraft;
/*     */   public int tick;
/*     */   public int optionParameter1;
/*     */   public int optionParameter2;
/*     */   private int currentMode;
/*     */   public boolean canPlaySound;
/*     */   
/*     */   public MCH_WeaponBase(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/*  54 */     this.worldObj = w;
/*  55 */     this.position = v;
/*  56 */     this.fixRotationYaw = yaw;
/*  57 */     this.fixRotationPitch = pitch;
/*  58 */     this.name = nm;
/*  59 */     this.weaponInfo = wi;
/*     */     
/*  61 */     this.displayName = (wi != null) ? wi.displayName : "";
/*  62 */     this.power = 0;
/*  63 */     this.acceleration = 0.0F;
/*  64 */     this.explosionPower = 0;
/*  65 */     this.explosionPowerInWater = 0;
/*  66 */     this.interval = 1;
/*  67 */     this.numMode = 0;
/*  68 */     this.lockTime = 0;
/*  69 */     this.heatCount = 0;
/*  70 */     this.cartridge = null;
/*     */     
/*  72 */     this.tick = 0;
/*  73 */     this.optionParameter1 = 0;
/*  74 */     this.optionParameter2 = 0;
/*  75 */     setCurrentMode(0);
/*  76 */     this.canPlaySound = true;
/*     */   }
/*     */   public MCH_WeaponInfo getInfo() {
/*  79 */     return this.weaponInfo;
/*     */   } public abstract boolean shot(MCH_WeaponParam paramMCH_WeaponParam); public void setLockChecker(MCH_IEntityLockChecker checker) {} public String getName() {
/*  81 */     return this.displayName;
/*     */   }
/*     */   public void setLockCountMax(int n) {}
/*     */   
/*     */   public int getLockCount() {
/*  86 */     return 0; }
/*  87 */   public int getLockCountMax() { return 0; } public final int getNumAmmoMax() {
/*  88 */     return (getInfo()).round;
/*     */   }
/*     */   public int getCurrentMode() {
/*  91 */     return (getInfo() != null && (getInfo()).fixMode > 0) ? (getInfo()).fixMode : this.currentMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentMode(int currentMode) {
/*  96 */     this.currentMode = currentMode;
/*     */   }
/*     */   
/*  99 */   public final int getAllAmmoNum() { return (getInfo()).maxAmmo; }
/* 100 */   public final int getReloadCount() { return (getInfo()).reloadTime; } public final MCH_SightType getSightType() {
/* 101 */     return (getInfo()).sight;
/*     */   } public MCH_WeaponGuidanceSystem getGuidanceSystem() {
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public void update(int countWait) {
/* 107 */     if (countWait != 0) this.tick++;
/*     */   
/*     */   }
/*     */   
/*     */   public boolean isCooldownCountReloadTime() {
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void modifyCommonParameters() {
/* 117 */     modifyParameters();
/*     */   }
/*     */ 
/*     */   
/*     */   public void modifyParameters() {}
/*     */   
/*     */   public boolean switchMode() {
/* 124 */     if (getInfo() != null && (getInfo()).fixMode > 0)
/*     */     {
/* 126 */       return false;
/*     */     }
/*     */     
/* 129 */     int beforeMode = getCurrentMode();
/* 130 */     if (this.numMode > 0) {
/*     */       
/* 132 */       setCurrentMode((getCurrentMode() + 1) % this.numMode);
/*     */     }
/*     */     else {
/*     */       
/* 136 */       setCurrentMode(0);
/*     */     } 
/* 138 */     if (beforeMode != getCurrentMode())
/*     */     {
/* 140 */       onSwitchMode();
/*     */     }
/* 142 */     return (beforeMode != getCurrentMode());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSwitchMode() {}
/*     */ 
/*     */   
/*     */   public boolean use(MCH_WeaponParam prm) {
/* 151 */     Vec3 v = getShotPos(prm.entity);
/* 152 */     prm.posX += v.field_72450_a;
/* 153 */     prm.posY += v.field_72448_b;
/* 154 */     prm.posZ += v.field_72449_c;
/* 155 */     if (shot(prm)) {
/*     */       
/* 157 */       this.tick = 0;
/* 158 */       return true;
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getShotPos(Entity entity) {
/* 165 */     if (entity instanceof MCH_EntityAircraft && this.onTurret)
/*     */     {
/* 167 */       return ((MCH_EntityAircraft)entity).calcOnTurretPos(this.position);
/*     */     }
/* 169 */     Vec3 v = Vec3.func_72443_a(this.position.field_72450_a, this.position.field_72448_b, this.position.field_72449_c);
/* 170 */     float roll = (entity instanceof MCH_EntityAircraft) ? ((MCH_EntityAircraft)entity).getRotRoll() : 0.0F;
/* 171 */     return MCH_Lib.RotVec3(v, -entity.field_70177_z, -entity.field_70125_A, -roll);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(Entity e) {
/* 176 */     playSound(e, (getInfo()).soundFileName);
/*     */   }
/*     */   
/*     */   public void playSound(Entity e, String snd) {
/* 180 */     if (!e.field_70170_p.field_72995_K && this.canPlaySound && getInfo() != null) {
/*     */       
/* 182 */       float prnd = (getInfo()).soundPitchRandom;
/* 183 */       this; W_WorldFunc.MOD_playSoundEffect(this.worldObj, e.field_70165_t, e.field_70163_u, e.field_70161_v, snd, (getInfo()).soundVolume, (getInfo()).soundPitch * (1.0F - prnd) + rand.nextFloat() * prnd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundClient(Entity e, float volume, float pitch) {
/* 190 */     if (e.field_70170_p.field_72995_K && getInfo() != null)
/*     */     {
/* 192 */       W_McClient.MOD_playSoundFX((getInfo()).soundFileName, volume, pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLandInDistance(MCH_WeaponParam prm) {
/* 198 */     if (this.weaponInfo == null) return -1.0D;
/*     */     
/* 200 */     if (this.weaponInfo.gravity >= 0.0F) return -1.0D;
/*     */     
/* 202 */     Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -prm.rotYaw, -prm.rotPitch, -prm.rotRoll);
/* 203 */     double s = Math.sqrt(v.field_72450_a * v.field_72450_a + v.field_72448_b * v.field_72448_b + v.field_72449_c * v.field_72449_c);
/*     */     
/* 205 */     double acc = (this.acceleration < 4.0F) ? this.acceleration : 4.0D;
/* 206 */     double accFac = this.acceleration / acc;
/*     */     
/* 208 */     double my = v.field_72448_b * this.acceleration / s;
/* 209 */     if (my <= 0.0D) return -1.0D;
/*     */     
/* 211 */     double mx = v.field_72450_a * this.acceleration / s;
/* 212 */     double mz = v.field_72449_c * this.acceleration / s;
/*     */     
/* 214 */     double ls = my / this.weaponInfo.gravity;
/* 215 */     double gravity = this.weaponInfo.gravity * accFac;
/* 216 */     if (ls < -12.0D) {
/*     */       
/* 218 */       double f = ls / -12.0D;
/* 219 */       mx *= f;
/* 220 */       my *= f;
/* 221 */       mz *= f;
/* 222 */       gravity *= f * f * 0.95D;
/*     */     } 
/*     */     
/* 225 */     double spx = prm.posX;
/* 226 */     double spy = prm.posY + 3.0D;
/* 227 */     double spz = prm.posZ;
/*     */     
/* 229 */     Vec3 vs = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/* 230 */     Vec3 ve = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/* 231 */     for (int i = 0; i < 50; i++) {
/*     */       
/* 233 */       vs.field_72450_a = spx;
/* 234 */       vs.field_72448_b = spy;
/* 235 */       vs.field_72449_c = spz;
/* 236 */       ve.field_72450_a = spx + mx;
/* 237 */       ve.field_72448_b = spy + my;
/* 238 */       ve.field_72449_c = spz + mz;
/* 239 */       MovingObjectPosition mop = this.worldObj.func_72933_a(vs, ve);
/*     */       
/* 241 */       if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */         
/* 243 */         double dx = mop.field_72311_b - prm.posX;
/* 244 */         double dz = mop.field_72309_d - prm.posZ;
/* 245 */         return Math.sqrt(dx * dx + dz * dz);
/*     */       } 
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
/* 257 */       my += gravity;
/* 258 */       spx += mx;
/* 259 */       spy += my;
/* 260 */       spz += mz;
/*     */       
/* 262 */       if (spy < prm.posY) {
/*     */         
/* 264 */         double dx = spx - prm.posX;
/* 265 */         double dz = spz - prm.posZ;
/* 266 */         return Math.sqrt(dx * dx + dz * dz);
/*     */       } 
/*     */     } 
/*     */     
/* 270 */     return -1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */