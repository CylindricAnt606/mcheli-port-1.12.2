/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.weapon.MCH_EntityCartridge;
/*     */ import mcheli.weapon.MCH_WeaponBase;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class MCH_WeaponSet
/*     */ {
/*  16 */   private static Random rand = new Random();
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */   
/*     */   protected MCH_WeaponBase[] weapons;
/*     */ 
/*     */   
/*     */   private int currentWeaponIndex;
/*     */ 
/*     */   
/*     */   public float rotationYaw;
/*     */ 
/*     */   
/*     */   public float rotationPitch;
/*     */ 
/*     */   
/*     */   public float prevRotationYaw;
/*     */ 
/*     */   
/*     */   public float prevRotationPitch;
/*     */ 
/*     */   
/*     */   public float defaultRotationYaw;
/*     */ 
/*     */   
/*     */   public float rotationTurretYaw;
/*     */ 
/*     */   
/*     */   public float rotBay;
/*     */ 
/*     */   
/*     */   public float prevRotBay;
/*     */   
/*     */   public Recoil[] recoilBuf;
/*     */   
/*     */   protected int numAmmo;
/*     */   
/*     */   protected int numRestAllAmmo;
/*     */   
/*     */   public int currentHeat;
/*     */   
/*     */   public int cooldownSpeed;
/*     */   
/*     */   public int countWait;
/*     */   
/*     */   public int countReloadWait;
/*     */   
/*     */   protected int[] lastUsedCount;
/*     */   
/*     */   private static final int WAIT_CLEAR_USED_COUNT = 4;
/*     */   
/*     */   public int soundWait;
/*     */   
/*  71 */   private int lastUsedOptionParameter1 = 0;
/*  72 */   private int lastUsedOptionParameter2 = 0;
/*     */   
/*     */   public float rotBarrelSpd;
/*     */   
/*     */   public float rotBarrel;
/*     */   public float prevRotBarrel;
/*     */   
/*     */   public MCH_WeaponSet(MCH_WeaponBase[] weapon) {
/*  80 */     this.name = (weapon[0]).name;
/*  81 */     this.weapons = weapon;
/*  82 */     this.currentWeaponIndex = 0;
/*  83 */     this.countWait = 0;
/*  84 */     this.countReloadWait = 0;
/*  85 */     this.lastUsedCount = new int[weapon.length];
/*     */     
/*  87 */     this.rotationYaw = 0.0F;
/*  88 */     this.prevRotationYaw = 0.0F;
/*  89 */     this.rotationPitch = 0.0F;
/*  90 */     this.prevRotationPitch = 0.0F;
/*  91 */     setAmmoNum(0);
/*  92 */     setRestAllAmmoNum(0);
/*  93 */     this.currentHeat = 0;
/*  94 */     this.soundWait = 0;
/*  95 */     this.cooldownSpeed = 1;
/*  96 */     this.rotBarrelSpd = 0.0F;
/*  97 */     this.rotBarrel = 0.0F;
/*  98 */     this.prevRotBarrel = 0.0F;
/*  99 */     this.recoilBuf = new Recoil[weapon.length];
/* 100 */     for (int i = 0; i < this.recoilBuf.length; i++)
/*     */     {
/* 102 */       this.recoilBuf[i] = new Recoil(this, (weapon[i].getInfo()).recoilBufCount, (weapon[i].getInfo()).recoilBufCountSpeed);
/*     */     }
/*     */ 
/*     */     
/* 106 */     this.defaultRotationYaw = 0.0F;
/*     */   }
/*     */   
/*     */   public MCH_WeaponSet(MCH_WeaponBase weapon) {
/* 110 */     this(new MCH_WeaponBase[] { weapon });
/*     */   }
/*     */   public boolean isEqual(String s) {
/* 113 */     return this.name.equalsIgnoreCase(s);
/*     */   }
/* 115 */   public int getAmmoNum() { return this.numAmmo; }
/* 116 */   public int getAmmoNumMax() { return getFirstWeapon().getNumAmmoMax(); }
/* 117 */   public int getRestAllAmmoNum() { return this.numRestAllAmmo; } public int getAllAmmoNum() {
/* 118 */     return getFirstWeapon().getAllAmmoNum();
/*     */   }
/*     */   
/*     */   public void setAmmoNum(int n) {
/* 122 */     this.numAmmo = n;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestAllAmmoNum(int n) {
/* 127 */     int debugBefore = this.numRestAllAmmo;
/* 128 */     int m = (getInfo()).maxAmmo - getAmmoNum();
/* 129 */     this.numRestAllAmmo = (n <= m) ? n : m;
/* 130 */     MCH_Lib.DbgLog((getFirstWeapon()).worldObj, "MCH_WeaponSet.setRestAllAmmoNum:%s %d->%d (%d)", new Object[] { getName(), Integer.valueOf(debugBefore), Integer.valueOf(this.numRestAllAmmo), Integer.valueOf(n) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void supplyRestAllAmmo() {
/* 136 */     int m = (getInfo()).maxAmmo;
/* 137 */     if (getRestAllAmmoNum() + getAmmoNum() < m)
/*     */     {
/* 139 */       setRestAllAmmoNum(getRestAllAmmoNum() + getAmmoNum() + (getInfo()).suppliedNum); } 
/*     */   }
/*     */   
/*     */   public boolean isInPreparation() {
/* 143 */     return (this.countWait < 0 || this.countReloadWait > 0);
/*     */   }
/*     */   public String getName() {
/* 146 */     MCH_WeaponBase w = getCurrentWeapon();
/* 147 */     return (w != null) ? w.getName() : "";
/*     */   } public boolean canUse() {
/* 149 */     return (this.countWait == 0);
/*     */   }
/*     */   
/*     */   public boolean isLongDelayWeapon() {
/* 153 */     return ((getInfo()).delay > 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reload() {
/* 158 */     MCH_WeaponBase crtWpn = getCurrentWeapon();
/* 159 */     if (getAmmoNumMax() > 0 && getAmmoNum() < getAmmoNumMax() && crtWpn.getReloadCount() > 0) {
/*     */       
/* 161 */       this.countReloadWait = crtWpn.getReloadCount();
/*     */       
/* 163 */       if (crtWpn.worldObj.field_72995_K)
/*     */       {
/* 165 */         setAmmoNum(0);
/*     */       }
/*     */ 
/*     */       
/* 169 */       if (!crtWpn.worldObj.field_72995_K) {
/*     */         
/* 171 */         this.countReloadWait -= 20;
/* 172 */         if (this.countReloadWait <= 0)
/*     */         {
/* 174 */           this.countReloadWait = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reloadMag() {
/* 181 */     int restAmmo = getRestAllAmmoNum();
/* 182 */     int nAmmo = getAmmoNumMax() - getAmmoNum();
/* 183 */     if (nAmmo > 0) {
/*     */       
/* 185 */       if (nAmmo > restAmmo)
/*     */       {
/* 187 */         nAmmo = restAmmo;
/*     */       }
/* 189 */       setAmmoNum(getAmmoNum() + nAmmo);
/* 190 */       setRestAllAmmoNum(getRestAllAmmoNum() - nAmmo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchMode() {
/* 197 */     boolean isChanged = false;
/* 198 */     for (MCH_WeaponBase w : this.weapons) {
/*     */       
/* 200 */       if (w != null) isChanged = (w.switchMode() || isChanged); 
/*     */     } 
/* 202 */     if (isChanged) {
/*     */       
/* 204 */       int cntSwitch = 15;
/* 205 */       if (this.countWait >= -cntSwitch)
/*     */       {
/*     */ 
/*     */         
/* 209 */         if (this.countWait > cntSwitch) {
/*     */           
/* 211 */           this.countWait = -this.countWait;
/*     */         }
/*     */         else {
/*     */           
/* 215 */           this.countWait = -cntSwitch;
/*     */         } 
/*     */       }
/* 218 */       if ((getCurrentWeapon()).worldObj.field_72995_K)
/*     */       {
/* 220 */         W_McClient.DEF_playSoundFX("random.click", 1.0F, 1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSwitchWeapon(boolean isRemote, boolean isCreative) {
/* 227 */     int cntSwitch = 15;
/* 228 */     if (isRemote) cntSwitch += 10; 
/* 229 */     if (this.countWait >= -cntSwitch)
/*     */     {
/*     */ 
/*     */       
/* 233 */       if (this.countWait > cntSwitch) {
/*     */         
/* 235 */         this.countWait = -this.countWait;
/*     */       }
/*     */       else {
/*     */         
/* 239 */         this.countWait = -cntSwitch;
/*     */       } 
/*     */     }
/* 242 */     this.currentWeaponIndex = 0;
/*     */ 
/*     */     
/* 245 */     if (isCreative)
/*     */     {
/* 247 */       setAmmoNum(getAmmoNumMax());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsed(int index) {
/* 255 */     MCH_WeaponBase w = getFirstWeapon();
/* 256 */     if (w != null && index < this.lastUsedCount.length) {
/*     */       
/* 258 */       int cnt = this.lastUsedCount[index];
/* 259 */       return ((w.interval >= 4 && cnt > w.interval / 2) || cnt >= 4);
/*     */     } 
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(Entity shooter, boolean isSelected, boolean isUsed) {
/* 266 */     if (getCurrentWeapon().getInfo() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 270 */     if (this.countReloadWait > 0) {
/*     */       
/* 272 */       this.countReloadWait--;
/* 273 */       if (this.countReloadWait == 0)
/*     */       {
/* 275 */         reloadMag();
/*     */       }
/*     */     } 
/*     */     
/* 279 */     for (int i = 0; i < this.lastUsedCount.length; i++) {
/*     */       
/* 281 */       if (this.lastUsedCount[i] > 0)
/*     */       {
/* 283 */         if (this.lastUsedCount[i] == 4) {
/*     */           
/* 285 */           if (0 == getCurrentWeaponIndex() && canUse())
/*     */           {
/* 287 */             if (getAmmoNum() > 0 || getAllAmmoNum() <= 0)
/*     */             {
/* 289 */               this.lastUsedCount[i] = this.lastUsedCount[i] - 1;
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 295 */           this.lastUsedCount[i] = this.lastUsedCount[i] - 1;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 302 */     if (this.currentHeat > 0) {
/*     */       
/* 304 */       if (this.currentHeat < (getCurrentWeapon().getInfo()).maxHeatCount)
/*     */       {
/* 306 */         this.cooldownSpeed++;
/*     */       }
/* 308 */       this.currentHeat -= this.cooldownSpeed / 20 + 1;
/* 309 */       if (this.currentHeat < 0)
/*     */       {
/* 311 */         this.currentHeat = 0;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 317 */     if (this.countWait > 0) this.countWait--; 
/* 318 */     if (this.countWait < 0) this.countWait++;
/*     */ 
/*     */ 
/*     */     
/* 322 */     this.prevRotationYaw = this.rotationYaw;
/* 323 */     this.prevRotationPitch = this.rotationPitch;
/*     */ 
/*     */ 
/*     */     
/* 327 */     if (this.weapons != null && this.weapons.length > 0)
/*     */     {
/* 329 */       for (MCH_WeaponBase w : this.weapons) {
/*     */         
/* 331 */         if (w != null) w.update(this.countWait);
/*     */       
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 337 */     if (this.soundWait > 0)
/*     */     {
/* 339 */       this.soundWait--;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 344 */     if (isUsed)
/*     */     {
/* 346 */       if (this.rotBarrelSpd < 75.0F) {
/*     */         
/* 348 */         this; this.rotBarrelSpd += (25 + rand.nextInt(3));
/* 349 */         if (this.rotBarrelSpd > 74.0F)
/*     */         {
/* 351 */           this.rotBarrelSpd = 74.0F;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 356 */     this.prevRotBarrel = this.rotBarrel;
/* 357 */     this.rotBarrel += this.rotBarrelSpd;
/* 358 */     if (this.rotBarrel >= 360.0F) {
/*     */       
/* 360 */       this.rotBarrel -= 360.0F;
/* 361 */       this.prevRotBarrel -= 360.0F;
/*     */     } 
/* 363 */     if (this.rotBarrelSpd > 0.0F) {
/*     */       
/* 365 */       this.rotBarrelSpd -= 1.48F;
/* 366 */       if (this.rotBarrelSpd < 0.0F) this.rotBarrelSpd = 0.0F;
/*     */     
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateWeapon(Entity shooter, boolean isUsed, int index) {
/* 398 */     MCH_WeaponBase crtWpn = getWeapon(index);
/*     */ 
/*     */     
/* 401 */     if (isUsed)
/*     */     {
/* 403 */       if (shooter.field_70170_p.field_72995_K && crtWpn != null && crtWpn.cartridge != null) {
/*     */ 
/*     */ 
/*     */         
/* 407 */         Vec3 v = crtWpn.getShotPos(shooter);
/*     */         
/* 409 */         float yaw = shooter.field_70177_z;
/* 410 */         float pitch = shooter.field_70125_A;
/* 411 */         if (!(shooter instanceof mcheli.vehicle.MCH_EntityVehicle) || shooter.field_70153_n != null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 418 */         MCH_EntityCartridge.spawnCartridge(shooter.field_70170_p, crtWpn.cartridge, shooter.field_70165_t + v.field_72450_a, shooter.field_70163_u + v.field_72448_b, shooter.field_70161_v + v.field_72449_c, shooter.field_70159_w, shooter.field_70181_x, shooter.field_70179_y, yaw + this.rotationYaw, pitch + this.rotationPitch);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     if (index < this.recoilBuf.length) {
/*     */       
/* 431 */       Recoil r = this.recoilBuf[index];
/* 432 */       r.prevRecoilBuf = r.recoilBuf;
/* 433 */       if (isUsed && r.recoilBufCount <= 0)
/*     */       {
/* 435 */         r.recoilBufCount = r.recoilBufCountMax;
/*     */       }
/* 437 */       if (r.recoilBufCount > 0) {
/*     */         
/* 439 */         if (r.recoilBufCountMax <= 1) {
/*     */           
/* 441 */           r.recoilBuf = 1.0F;
/*     */         }
/* 443 */         else if (r.recoilBufCountMax == 2) {
/*     */           
/* 445 */           r.recoilBuf = (r.recoilBufCount == 2) ? 1.0F : 0.6F;
/*     */         }
/*     */         else {
/*     */           
/* 449 */           if (r.recoilBufCount > r.recoilBufCountMax / 2)
/*     */           {
/* 451 */             r.recoilBufCount -= r.recoilBufCountSpeed;
/*     */           }
/* 453 */           float rb = r.recoilBufCount / r.recoilBufCountMax;
/* 454 */           r.recoilBuf = MathHelper.func_76126_a(rb * 3.1415927F);
/*     */         } 
/* 456 */         r.recoilBufCount--;
/*     */       }
/*     */       else {
/*     */         
/* 460 */         r.recoilBuf = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean use(MCH_WeaponParam prm) {
/* 467 */     MCH_WeaponBase crtWpn = getCurrentWeapon();
/* 468 */     if (crtWpn != null && crtWpn.getInfo() != null) {
/*     */       
/* 470 */       MCH_WeaponInfo info = crtWpn.getInfo();
/* 471 */       if ((getAmmoNumMax() <= 0 || getAmmoNum() > 0) && (info.maxHeatCount <= 0 || this.currentHeat < info.maxHeatCount)) {
/*     */ 
/*     */         
/* 474 */         crtWpn.canPlaySound = (this.soundWait == 0);
/*     */ 
/*     */         
/* 477 */         prm.rotYaw = (prm.entity != null) ? prm.entity.field_70177_z : 0.0F;
/* 478 */         prm.rotPitch = (prm.entity != null) ? prm.entity.field_70125_A : 0.0F;
/*     */         
/* 480 */         prm.rotYaw += this.rotationYaw + crtWpn.fixRotationYaw;
/* 481 */         prm.rotPitch += this.rotationPitch + crtWpn.fixRotationPitch;
/*     */         
/* 483 */         if (info.accuracy > 0.0F) {
/*     */           
/* 485 */           prm.rotYaw += (rand.nextFloat() - 0.5F) * info.accuracy;
/* 486 */           prm.rotPitch += (rand.nextFloat() - 0.5F) * info.accuracy;
/*     */         } 
/*     */         
/* 489 */         prm.rotYaw = MathHelper.func_76142_g(prm.rotYaw);
/* 490 */         prm.rotPitch = MathHelper.func_76142_g(prm.rotPitch);
/*     */         
/* 492 */         if (crtWpn.use(prm)) {
/*     */           
/* 494 */           if (info.maxHeatCount > 0) {
/*     */             
/* 496 */             this.cooldownSpeed = 1;
/* 497 */             this.currentHeat += crtWpn.heatCount;
/* 498 */             if (this.currentHeat >= info.maxHeatCount)
/*     */             {
/* 500 */               this.currentHeat += 30;
/*     */             }
/*     */           } 
/*     */           
/* 504 */           if (info.soundDelay > 0 && this.soundWait == 0)
/*     */           {
/* 506 */             this.soundWait = info.soundDelay;
/*     */           }
/*     */           
/* 509 */           this.lastUsedOptionParameter1 = crtWpn.optionParameter1;
/* 510 */           this.lastUsedOptionParameter2 = crtWpn.optionParameter2;
/*     */           
/* 512 */           this.lastUsedCount[this.currentWeaponIndex] = (crtWpn.interval > 0) ? crtWpn.interval : -crtWpn.interval;
/* 513 */           if (crtWpn.isCooldownCountReloadTime() && crtWpn.getReloadCount() - 10 > this.lastUsedCount[this.currentWeaponIndex])
/*     */           {
/*     */             
/* 516 */             this.lastUsedCount[this.currentWeaponIndex] = crtWpn.getReloadCount() - 10;
/*     */           }
/* 518 */           this.currentWeaponIndex = (this.currentWeaponIndex + 1) % this.weapons.length;
/* 519 */           this.countWait = crtWpn.interval;
/*     */ 
/*     */ 
/*     */           
/* 523 */           this.countReloadWait = 0;
/* 524 */           if (getAmmoNum() > 0) setAmmoNum(getAmmoNum() - 1); 
/* 525 */           if (getAmmoNum() <= 0) {
/*     */             
/* 527 */             if (prm.isInfinity && getRestAllAmmoNum() < getAmmoNumMax())
/*     */             {
/* 529 */               setRestAllAmmoNum(getAmmoNumMax());
/*     */             }
/* 531 */             reload();
/* 532 */             prm.reload = true;
/*     */           } 
/*     */ 
/*     */           
/* 536 */           prm.result = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 540 */     return prm.result;
/*     */   }
/*     */   
/*     */   public void waitAndReloadByOther(boolean reload) {
/* 544 */     MCH_WeaponBase crtWpn = getCurrentWeapon();
/* 545 */     if (crtWpn != null && crtWpn.getInfo() != null) {
/*     */       
/* 547 */       this.countWait = crtWpn.interval;
/*     */       
/* 549 */       this.countReloadWait = 0;
/* 550 */       if (reload)
/*     */       {
/* 552 */         if (getAmmoNumMax() > 0 && crtWpn.getReloadCount() > 0) {
/*     */           
/* 554 */           this.countReloadWait = crtWpn.getReloadCount();
/*     */ 
/*     */           
/* 557 */           if (!crtWpn.worldObj.field_72995_K) {
/*     */             
/* 559 */             this.countReloadWait -= 20;
/* 560 */             if (this.countReloadWait <= 0)
/*     */             {
/* 562 */               this.countReloadWait = 1;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastUsedOptionParameter1() {
/* 572 */     return this.lastUsedOptionParameter1;
/*     */   }
/*     */   
/*     */   public int getLastUsedOptionParameter2() {
/* 576 */     return this.lastUsedOptionParameter2;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_WeaponBase getFirstWeapon() {
/* 581 */     return getWeapon(0);
/*     */   }
/*     */   
/*     */   public int getCurrentWeaponIndex() {
/* 585 */     return this.currentWeaponIndex;
/*     */   }
/*     */   
/*     */   public MCH_WeaponBase getCurrentWeapon() {
/* 589 */     return getWeapon(this.currentWeaponIndex);
/*     */   }
/*     */   
/*     */   public MCH_WeaponBase getWeapon(int idx) {
/* 593 */     if (this.weapons != null && this.weapons.length > 0 && idx < this.weapons.length)
/*     */     {
/* 595 */       return this.weapons[idx];
/*     */     }
/*     */ 
/*     */     
/* 599 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeaponNum() {
/* 604 */     return (this.weapons != null) ? this.weapons.length : 0;
/*     */   }
/*     */   
/*     */   public MCH_WeaponInfo getInfo() {
/* 608 */     return getFirstWeapon().getInfo();
/*     */   }
/*     */   
/*     */   public double getLandInDistance(MCH_WeaponParam prm) {
/* 612 */     double ret = -1.0D;
/* 613 */     MCH_WeaponBase crtWpn = getCurrentWeapon();
/* 614 */     if (crtWpn != null && crtWpn.getInfo() != null) {
/*     */       
/* 616 */       MCH_WeaponInfo info = crtWpn.getInfo();
/*     */ 
/*     */       
/* 619 */       prm.rotYaw = (prm.entity != null) ? prm.entity.field_70177_z : 0.0F;
/* 620 */       prm.rotPitch = (prm.entity != null) ? prm.entity.field_70125_A : 0.0F;
/*     */       
/* 622 */       prm.rotYaw += this.rotationYaw + crtWpn.fixRotationYaw;
/* 623 */       prm.rotPitch += this.rotationPitch + crtWpn.fixRotationPitch;
/*     */       
/* 625 */       prm.rotYaw = MathHelper.func_76142_g(prm.rotYaw);
/* 626 */       prm.rotPitch = MathHelper.func_76142_g(prm.rotPitch);
/*     */       
/* 628 */       return crtWpn.getLandInDistance(prm);
/*     */     } 
/* 630 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */