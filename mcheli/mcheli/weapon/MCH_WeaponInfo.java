/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_BaseInfo;
/*     */ import mcheli.MCH_Color;
/*     */ import mcheli.MCH_DamageFactor;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.vehicle.MCH_EntityVehicle;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_Cartridge;
/*     */ import mcheli.weapon.MCH_SightType;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
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
/*     */ public class MCH_WeaponInfo
/*     */   extends MCH_BaseInfo
/*     */ {
/*     */   public final String name;
/*     */   public String displayName;
/*     */   public String type;
/*     */   public int power;
/*     */   public float acceleration;
/*     */   public float accelerationInWater;
/*     */   public int explosion;
/*     */   public int explosionBlock;
/*     */   public int explosionInWater;
/*     */   public int explosionAltitude;
/*     */   public int delayFuse;
/*     */   public float bound;
/*     */   public int timeFuse;
/*     */   public boolean flaming;
/*     */   public MCH_SightType sight;
/*     */   public float[] zoom;
/*     */   public int delay;
/*     */   public int reloadTime;
/*     */   public int round;
/*     */   public int suppliedNum;
/*     */   public int maxAmmo;
/*     */   public List<RoundItem> roundItems;
/*     */   public int soundDelay;
/*     */   public float soundVolume;
/*     */   public float soundPitch;
/*     */   public float soundPitchRandom;
/*     */   public int soundPattern;
/*     */   public int lockTime;
/*     */   public boolean ridableOnly;
/*     */   public float proximityFuseDist;
/*     */   public int rigidityTime;
/*     */   public float accuracy;
/*     */   public int bomblet;
/*     */   public int bombletSTime;
/*     */   public float bombletDiff;
/*     */   public int modeNum;
/*     */   public int fixMode;
/*     */   public int piercing;
/*     */   public int heatCount;
/*     */   public int maxHeatCount;
/*     */   public boolean isFAE;
/*     */   public boolean isGuidedTorpedo;
/*     */   public float gravity;
/*     */   public float gravityInWater;
/*     */   public float velocityInWater;
/*     */   public boolean destruct;
/*     */   public String trajectoryParticleName;
/*     */   public int trajectoryParticleStartTick;
/*     */   public boolean disableSmoke;
/*     */   public MCH_Cartridge cartridge;
/*     */   public MCH_Color color;
/*     */   public MCH_Color colorInWater;
/*     */   public String soundFileName;
/*     */   public float smokeSize;
/*     */   public int smokeNum;
/*     */   public int smokeMaxAge;
/*     */   public Item dispenseItem;
/*     */   public int dispenseDamege;
/*     */   public int dispenseRange;
/*     */   public int recoilBufCount;
/*     */   public int recoilBufCountSpeed;
/*     */   public float length;
/*     */   public float radius;
/*     */   public float angle;
/*     */   public boolean displayMortarDistance;
/*     */   public boolean fixCameraPitch;
/*     */   public float cameraRotationSpeedPitch;
/*     */   public int target;
/*     */   public int markTime;
/*     */   public float recoil;
/*     */   public String bulletModelName;
/*     */   public MCH_BulletModel bulletModel;
/*     */   public String bombletModelName;
/*     */   public MCH_BulletModel bombletModel;
/*     */   public MCH_DamageFactor damageFactor;
/*     */   public String group;
/*     */   public List<MuzzleFlash> listMuzzleFlash;
/*     */   public List<MuzzleFlash> listMuzzleFlashSmoke;
/*     */   
/*     */   public MCH_WeaponInfo(String name) {
/* 153 */     this.name = name;
/* 154 */     this.displayName = name;
/* 155 */     this.type = "";
/* 156 */     this.power = 0;
/* 157 */     this.acceleration = 1.0F;
/* 158 */     this.accelerationInWater = 1.0F;
/* 159 */     this.explosion = 0;
/* 160 */     this.explosionBlock = -1;
/* 161 */     this.explosionInWater = 0;
/* 162 */     this.explosionAltitude = 0;
/* 163 */     this.delayFuse = 0;
/* 164 */     this.timeFuse = 0;
/* 165 */     this.flaming = false;
/* 166 */     this.sight = MCH_SightType.NONE;
/* 167 */     this.zoom = new float[] { 1.0F };
/* 168 */     this.delay = 10;
/* 169 */     this.reloadTime = 30;
/* 170 */     this.round = 0;
/* 171 */     this.suppliedNum = 1;
/* 172 */     this.roundItems = new ArrayList<RoundItem>();
/* 173 */     this.maxAmmo = 0;
/* 174 */     this.soundDelay = 0;
/* 175 */     this.soundPattern = 0;
/* 176 */     this.soundVolume = 1.0F;
/* 177 */     this.soundPitch = 1.0F;
/* 178 */     this.soundPitchRandom = 0.1F;
/* 179 */     this.lockTime = 30;
/* 180 */     this.ridableOnly = false;
/* 181 */     this.proximityFuseDist = 0.0F;
/* 182 */     this.rigidityTime = 7;
/* 183 */     this.accuracy = 0.0F;
/* 184 */     this.bomblet = 0;
/* 185 */     this.bombletSTime = 10;
/* 186 */     this.bombletDiff = 0.3F;
/* 187 */     this.modeNum = 0;
/* 188 */     this.fixMode = 0;
/* 189 */     this.piercing = 0;
/* 190 */     this.heatCount = 0;
/* 191 */     this.maxHeatCount = 0;
/* 192 */     this.bulletModelName = "";
/* 193 */     this.bombletModelName = "";
/* 194 */     this.bulletModel = null;
/* 195 */     this.bombletModel = null;
/* 196 */     this.isFAE = false;
/* 197 */     this.isGuidedTorpedo = false;
/* 198 */     this.gravity = 0.0F;
/* 199 */     this.gravityInWater = 0.0F;
/* 200 */     this.velocityInWater = 0.999F;
/* 201 */     this.destruct = false;
/*     */     
/* 203 */     this.trajectoryParticleName = "explode";
/* 204 */     this.trajectoryParticleStartTick = 0;
/* 205 */     this.cartridge = null;
/* 206 */     this.disableSmoke = false;
/* 207 */     this.color = new MCH_Color();
/* 208 */     this.colorInWater = new MCH_Color();
/* 209 */     this.soundFileName = name + "_snd";
/* 210 */     this.smokeMaxAge = 100;
/* 211 */     this.smokeNum = 1;
/* 212 */     this.smokeSize = 2.0F;
/* 213 */     this.dispenseItem = null;
/* 214 */     this.dispenseDamege = 0;
/* 215 */     this.dispenseRange = 1;
/* 216 */     this.recoilBufCount = 2;
/* 217 */     this.recoilBufCountSpeed = 3;
/* 218 */     this.length = 0.0F;
/* 219 */     this.radius = 0.0F;
/* 220 */     this.target = 1;
/* 221 */     this.recoil = 0.0F;
/* 222 */     this.damageFactor = null;
/* 223 */     this.group = "";
/* 224 */     this.listMuzzleFlash = null;
/* 225 */     this.listMuzzleFlashSmoke = null;
/* 226 */     this.displayMortarDistance = false;
/* 227 */     this.fixCameraPitch = false;
/* 228 */     this.cameraRotationSpeedPitch = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkData() {
/* 233 */     if (this.explosionBlock < 0)
/*     */     {
/* 235 */       this.explosionBlock = this.explosion;
/*     */     }
/*     */     
/* 238 */     if (this.fixMode >= this.modeNum)
/*     */     {
/* 240 */       this.fixMode = 0;
/*     */     }
/*     */     
/* 243 */     if (this.round <= 0)
/*     */     {
/* 245 */       this.round = this.maxAmmo;
/*     */     }
/*     */     
/* 248 */     if (this.round > this.maxAmmo)
/*     */     {
/* 250 */       this.round = this.maxAmmo;
/*     */     }
/*     */ 
/*     */     
/* 254 */     if (this.explosion <= 0)
/*     */     {
/* 256 */       this.isFAE = false;
/*     */     }
/*     */ 
/*     */     
/* 260 */     if (this.delayFuse <= 0)
/*     */     {
/* 262 */       this.bound = 0.0F;
/*     */     }
/*     */ 
/*     */     
/* 266 */     if (this.isFAE)
/*     */     {
/* 268 */       this.explosionInWater = 0;
/*     */     }
/*     */     
/* 271 */     if (this.bomblet > 0 && this.bombletSTime < 1)
/*     */     {
/* 273 */       this.bombletSTime = 1;
/*     */     }
/*     */     
/* 276 */     if (this.destruct)
/*     */     {
/* 278 */       this.delay = 1000000;
/*     */     }
/*     */     
/* 281 */     this.angle = (float)(Math.atan2(this.radius, this.length) * 180.0D / Math.PI);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/* 286 */     if (item.compareTo("displayname") == 0) {
/*     */       
/* 288 */       this.displayName = data;
/*     */     }
/* 290 */     else if (item.compareTo("type") == 0) {
/*     */       
/* 292 */       this.type = data.toLowerCase();
/* 293 */       if (this.type.equalsIgnoreCase("bomb") || this.type.equalsIgnoreCase("dispenser"))
/*     */       {
/* 295 */         this.gravity = -0.03F;
/* 296 */         this.gravityInWater = -0.03F;
/*     */       }
/*     */     
/* 299 */     } else if (item.compareTo("group") == 0) {
/*     */       
/* 301 */       this.group = data.toLowerCase().trim();
/*     */     }
/* 303 */     else if (item.compareTo("power") == 0) {
/*     */       
/* 305 */       this.power = toInt(data);
/*     */     }
/* 307 */     else if (item.equalsIgnoreCase("sound")) {
/*     */       
/* 309 */       this.soundFileName = data.toLowerCase().trim();
/*     */     }
/* 311 */     else if (item.compareTo("acceleration") == 0) {
/*     */       
/* 313 */       this.acceleration = toFloat(data, 0.0F, 100.0F);
/*     */     }
/* 315 */     else if (item.compareTo("accelerationinwater") == 0) {
/*     */       
/* 317 */       this.accelerationInWater = toFloat(data, 0.0F, 100.0F);
/*     */     }
/* 319 */     else if (item.compareTo("gravity") == 0) {
/*     */       
/* 321 */       this.gravity = toFloat(data, -50.0F, 50.0F);
/*     */     }
/* 323 */     else if (item.compareTo("gravityinwater") == 0) {
/*     */       
/* 325 */       this.gravityInWater = toFloat(data, -50.0F, 50.0F);
/*     */     }
/* 327 */     else if (item.equalsIgnoreCase("VelocityInWater")) {
/*     */       
/* 329 */       this.velocityInWater = toFloat(data);
/*     */     }
/* 331 */     else if (item.compareTo("explosion") == 0) {
/*     */       
/* 333 */       this.explosion = toInt(data, 0, 50);
/*     */     }
/* 335 */     else if (item.equalsIgnoreCase("explosionBlock")) {
/*     */       
/* 337 */       this.explosionBlock = toInt(data, 0, 50);
/*     */     }
/* 339 */     else if (item.compareTo("explosioninwater") == 0) {
/*     */       
/* 341 */       this.explosionInWater = toInt(data, 0, 50);
/*     */     }
/* 343 */     else if (item.equalsIgnoreCase("ExplosionAltitude")) {
/*     */       
/* 345 */       this.explosionAltitude = toInt(data, 0, 100);
/*     */     }
/* 347 */     else if (item.equalsIgnoreCase("TimeFuse")) {
/*     */       
/* 349 */       this.timeFuse = toInt(data, 0, 100000);
/*     */     }
/* 351 */     else if (item.equalsIgnoreCase("DelayFuse")) {
/*     */       
/* 353 */       this.delayFuse = toInt(data, 0, 100000);
/*     */     }
/* 355 */     else if (item.equalsIgnoreCase("Bound")) {
/*     */       
/* 357 */       this.bound = toFloat(data, 0.0F, 100000.0F);
/*     */     }
/* 359 */     else if (item.compareTo("flaming") == 0) {
/*     */       
/* 361 */       this.flaming = toBool(data);
/*     */     }
/* 363 */     else if (item.equalsIgnoreCase("DisplayMortarDistance")) {
/*     */       
/* 365 */       this.displayMortarDistance = toBool(data);
/*     */     }
/* 367 */     else if (item.equalsIgnoreCase("FixCameraPitch")) {
/*     */       
/* 369 */       this.fixCameraPitch = toBool(data);
/*     */     }
/* 371 */     else if (item.equalsIgnoreCase("CameraRotationSpeedPitch")) {
/*     */       
/* 373 */       this.cameraRotationSpeedPitch = toFloat(data, 0.0F, 100.0F);
/*     */     }
/* 375 */     else if (item.compareTo("sight") == 0) {
/*     */       
/* 377 */       data = data.toLowerCase();
/* 378 */       if (data.compareTo("movesight") == 0)
/*     */       {
/* 380 */         this.sight = MCH_SightType.ROCKET;
/*     */       }
/* 382 */       if (data.compareTo("missilesight") == 0)
/*     */       {
/* 384 */         this.sight = MCH_SightType.LOCK;
/*     */       }
/*     */     }
/* 387 */     else if (item.equalsIgnoreCase("Zoom")) {
/*     */       
/* 389 */       String[] s = splitParam(data);
/* 390 */       if (s.length > 0)
/*     */       {
/* 392 */         this.zoom = new float[s.length];
/* 393 */         for (int i = 0; i < s.length; i++)
/*     */         {
/* 395 */           this.zoom[i] = toFloat(s[i], 0.1F, 10.0F);
/*     */         }
/*     */       }
/*     */     
/* 399 */     } else if (item.compareTo("delay") == 0) {
/*     */       
/* 401 */       this.delay = toInt(data, 0, 100000);
/*     */     }
/* 403 */     else if (item.compareTo("reloadtime") == 0) {
/*     */       
/* 405 */       this.reloadTime = toInt(data, 3, 1000);
/*     */     }
/* 407 */     else if (item.compareTo("round") == 0) {
/*     */       
/* 409 */       this.round = toInt(data, 1, 30000);
/*     */     }
/* 411 */     else if (item.equalsIgnoreCase("MaxAmmo")) {
/*     */       
/* 413 */       this.maxAmmo = toInt(data, 0, 30000);
/*     */     }
/* 415 */     else if (item.equalsIgnoreCase("SuppliedNum")) {
/*     */       
/* 417 */       this.suppliedNum = toInt(data, 1, 30000);
/*     */     }
/* 419 */     else if (item.equalsIgnoreCase("Item")) {
/*     */       
/* 421 */       String[] s = data.split("\\s*,\\s*");
/* 422 */       if (s.length >= 2 && s[1].length() > 0 && this.roundItems.size() < 3) {
/*     */         
/* 424 */         int n = toInt(s[0], 1, 64);
/* 425 */         if (n > 0)
/*     */         {
/* 427 */           int damage = (s.length >= 3) ? toInt(s[2], 0, 100000000) : 0;
/* 428 */           this.roundItems.add(new RoundItem(this, n, s[1].toLowerCase().trim(), damage));
/*     */         }
/*     */       
/*     */       } 
/* 432 */     } else if (item.compareTo("sounddelay") == 0) {
/*     */       
/* 434 */       this.soundDelay = toInt(data, 0, 1000);
/*     */     }
/* 436 */     else if (item.compareTo("soundpattern") != 0) {
/*     */ 
/*     */ 
/*     */       
/* 440 */       if (item.compareTo("soundvolume") == 0) {
/*     */         
/* 442 */         this.soundVolume = toFloat(data, 0.0F, 1000.0F);
/*     */       }
/* 444 */       else if (item.compareTo("soundpitch") == 0) {
/*     */         
/* 446 */         this.soundPitch = toFloat(data, 0.0F, 1.0F);
/*     */       }
/* 448 */       else if (item.equalsIgnoreCase("SoundPitchRandom")) {
/*     */         
/* 450 */         this.soundPitchRandom = toFloat(data, 0.0F, 1.0F);
/*     */       }
/* 452 */       else if (item.compareTo("locktime") == 0) {
/*     */         
/* 454 */         this.lockTime = toInt(data, 2, 1000);
/*     */       }
/* 456 */       else if (item.equalsIgnoreCase("RidableOnly")) {
/*     */         
/* 458 */         this.ridableOnly = toBool(data);
/*     */       }
/* 460 */       else if (item.compareTo("proximityfusedist") == 0) {
/*     */         
/* 462 */         this.proximityFuseDist = toFloat(data, 0.0F, 2000.0F);
/*     */       }
/* 464 */       else if (item.equalsIgnoreCase("RigidityTime")) {
/*     */         
/* 466 */         this.rigidityTime = toInt(data, 0, 1000000);
/*     */       }
/* 468 */       else if (item.compareTo("accuracy") == 0) {
/*     */         
/* 470 */         this.accuracy = toFloat(data, 0.0F, 1000.0F);
/*     */       }
/* 472 */       else if (item.compareTo("bomblet") == 0) {
/*     */         
/* 474 */         this.bomblet = toInt(data, 0, 1000);
/*     */       }
/* 476 */       else if (item.compareTo("bombletstime") == 0) {
/*     */         
/* 478 */         this.bombletSTime = toInt(data, 0, 1000);
/*     */       }
/* 480 */       else if (item.equalsIgnoreCase("BombletDiff")) {
/*     */         
/* 482 */         this.bombletDiff = toFloat(data, 0.0F, 1000.0F);
/*     */       }
/* 484 */       else if (item.equalsIgnoreCase("RecoilBufCount")) {
/*     */         
/* 486 */         String[] s = splitParam(data);
/* 487 */         if (s.length >= 1)
/*     */         {
/* 489 */           this.recoilBufCount = toInt(s[0], 1, 10000);
/*     */         }
/* 491 */         if (s.length >= 2 && this.recoilBufCount > 2)
/*     */         {
/* 493 */           this.recoilBufCountSpeed = toInt(s[1], 1, 10000) - 1;
/* 494 */           if (this.recoilBufCountSpeed > this.recoilBufCount / 2)
/*     */           {
/* 496 */             this.recoilBufCountSpeed = this.recoilBufCount / 2;
/*     */           }
/*     */         }
/*     */       
/* 500 */       } else if (item.compareTo("modenum") == 0) {
/*     */         
/* 502 */         this.modeNum = toInt(data, 0, 1000);
/*     */       }
/* 504 */       else if (item.equalsIgnoreCase("FixMode")) {
/*     */         
/* 506 */         this.fixMode = toInt(data, 0, 10);
/*     */       }
/* 508 */       else if (item.compareTo("piercing") == 0) {
/*     */         
/* 510 */         this.piercing = toInt(data, 0, 100000);
/*     */       }
/* 512 */       else if (item.compareTo("heatcount") == 0) {
/*     */         
/* 514 */         this.heatCount = toInt(data, 0, 100000);
/*     */       }
/* 516 */       else if (item.compareTo("maxheatcount") == 0) {
/*     */         
/* 518 */         this.maxHeatCount = toInt(data, 0, 100000);
/*     */       }
/* 520 */       else if (item.compareTo("modelbullet") == 0) {
/*     */         
/* 522 */         this.bulletModelName = data.toLowerCase().trim();
/*     */       }
/* 524 */       else if (item.equalsIgnoreCase("ModelBomblet")) {
/*     */         
/* 526 */         this.bombletModelName = data.toLowerCase().trim();
/*     */       }
/* 528 */       else if (item.compareTo("fae") == 0) {
/*     */         
/* 530 */         this.isFAE = toBool(data);
/*     */       }
/* 532 */       else if (item.compareTo("guidedtorpedo") == 0) {
/*     */         
/* 534 */         this.isGuidedTorpedo = toBool(data);
/*     */       }
/* 536 */       else if (item.compareTo("destruct") == 0) {
/*     */         
/* 538 */         this.destruct = toBool(data);
/*     */       }
/* 540 */       else if (item.equalsIgnoreCase("AddMuzzleFlash")) {
/*     */         
/* 542 */         String[] s = splitParam(data);
/* 543 */         if (s.length >= 7)
/*     */         {
/* 545 */           if (this.listMuzzleFlash == null)
/*     */           {
/* 547 */             this.listMuzzleFlash = new ArrayList<MuzzleFlash>();
/*     */           }
/* 549 */           this.listMuzzleFlash.add(new MuzzleFlash(this, toFloat(s[0]), toFloat(s[1]), 0.0F, toInt(s[2]), toFloat(s[3]) / 255.0F, toFloat(s[4]) / 255.0F, toFloat(s[5]) / 255.0F, toFloat(s[6]) / 255.0F, 1));
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 554 */       else if (item.equalsIgnoreCase("AddMuzzleFlashSmoke")) {
/*     */         
/* 556 */         String[] s = splitParam(data);
/* 557 */         if (s.length >= 9)
/*     */         {
/* 559 */           if (this.listMuzzleFlashSmoke == null)
/*     */           {
/* 561 */             this.listMuzzleFlashSmoke = new ArrayList<MuzzleFlash>();
/*     */           }
/* 563 */           this.listMuzzleFlashSmoke.add(new MuzzleFlash(this, toFloat(s[0]), toFloat(s[2]), toFloat(s[3]), toInt(s[4]), toFloat(s[5]) / 255.0F, toFloat(s[6]) / 255.0F, toFloat(s[7]) / 255.0F, toFloat(s[8]) / 255.0F, toInt(s[1], 1, 1000)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 579 */       else if (item.equalsIgnoreCase("TrajectoryParticle")) {
/*     */         
/* 581 */         this.trajectoryParticleName = data.toLowerCase().trim();
/* 582 */         if (this.trajectoryParticleName.equalsIgnoreCase("none"))
/*     */         {
/* 584 */           this.trajectoryParticleName = "";
/*     */         }
/*     */       }
/* 587 */       else if (item.equalsIgnoreCase("TrajectoryParticleStartTick")) {
/*     */         
/* 589 */         this.trajectoryParticleStartTick = toInt(data, 0, 10000);
/*     */       }
/* 591 */       else if (item.equalsIgnoreCase("DisableSmoke")) {
/*     */         
/* 593 */         this.disableSmoke = toBool(data);
/*     */       }
/* 595 */       else if (item.equalsIgnoreCase("SetCartridge")) {
/*     */         
/* 597 */         String[] s = data.split("\\s*,\\s*");
/* 598 */         if (s.length > 0 && s[0].length() > 0)
/*     */         {
/* 600 */           float ac = (s.length >= 2) ? toFloat(s[1]) : 0.0F;
/* 601 */           float yw = (s.length >= 3) ? toFloat(s[2]) : 0.0F;
/* 602 */           float pt = (s.length >= 4) ? toFloat(s[3]) : 0.0F;
/* 603 */           float sc = (s.length >= 5) ? toFloat(s[4]) : 1.0F;
/* 604 */           float gr = (s.length >= 6) ? toFloat(s[5]) : -0.04F;
/* 605 */           float bo = (s.length >= 7) ? toFloat(s[6]) : 0.5F;
/* 606 */           this.cartridge = new MCH_Cartridge(s[0].toLowerCase(), ac, yw, pt, bo, gr, sc);
/*     */         }
/*     */       
/* 609 */       } else if (item.equalsIgnoreCase("BulletColorInWater") || item.equalsIgnoreCase("BulletColor") || item.equalsIgnoreCase("SmokeColor")) {
/*     */ 
/*     */ 
/*     */         
/* 613 */         String[] s = data.split("\\s*,\\s*");
/* 614 */         if (s.length >= 4) {
/*     */           
/* 616 */           float f = 0.003921569F;
/* 617 */           MCH_Color c = new MCH_Color(0.003921569F * toInt(s[0], 0, 255), 0.003921569F * toInt(s[1], 0, 255), 0.003921569F * toInt(s[2], 0, 255), 0.003921569F * toInt(s[3], 0, 255));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 622 */           if (item.equalsIgnoreCase("BulletColorInWater"))
/*     */           {
/* 624 */             this.colorInWater = c;
/*     */           }
/*     */           else
/*     */           {
/* 628 */             this.color = c;
/*     */           }
/*     */         
/*     */         } 
/* 632 */       } else if (item.equalsIgnoreCase("SmokeSize")) {
/*     */         
/* 634 */         this.smokeSize = toFloat(data, 0.0F, 100.0F);
/*     */       }
/* 636 */       else if (item.equalsIgnoreCase("SmokeNum")) {
/*     */         
/* 638 */         this.smokeNum = toInt(data, 1, 100);
/*     */       }
/* 640 */       else if (item.equalsIgnoreCase("SmokeMaxAge")) {
/*     */         
/* 642 */         this.smokeMaxAge = toInt(data, 2, 1000);
/*     */       }
/* 644 */       else if (item.equalsIgnoreCase("DispenseItem")) {
/*     */         
/* 646 */         String[] s = data.split("\\s*,\\s*");
/* 647 */         if (s.length >= 2)
/*     */         {
/* 649 */           this.dispenseDamege = toInt(s[1], 0, 100000000);
/*     */         }
/* 651 */         this.dispenseItem = W_Item.getItemByName(s[0]);
/*     */       }
/* 653 */       else if (item.equalsIgnoreCase("DispenseRange")) {
/*     */         
/* 655 */         this.dispenseRange = toInt(data, 1, 100);
/*     */       }
/* 657 */       else if (item.equalsIgnoreCase("Length")) {
/*     */         
/* 659 */         this.length = toInt(data, 1, 300);
/*     */       }
/* 661 */       else if (item.equalsIgnoreCase("Radius")) {
/*     */         
/* 663 */         this.radius = toInt(data, 1, 1000);
/*     */       }
/* 665 */       else if (item.equalsIgnoreCase("Target")) {
/*     */         
/* 667 */         if (data.indexOf("block") >= 0)
/*     */         {
/* 669 */           this.target = 64;
/*     */         }
/*     */         else
/*     */         {
/* 673 */           this.target = 0;
/* 674 */           this.target |= (data.indexOf("planes") >= 0) ? 32 : 0;
/* 675 */           this.target |= (data.indexOf("helicopters") >= 0) ? 16 : 0;
/* 676 */           this.target |= (data.indexOf("vehicles") >= 0) ? 8 : 0;
/* 677 */           this.target |= (data.indexOf("tanks") >= 0) ? 8 : 0;
/* 678 */           this.target |= (data.indexOf("players") >= 0) ? 4 : 0;
/* 679 */           this.target |= (data.indexOf("monsters") >= 0) ? 2 : 0;
/* 680 */           this.target |= (data.indexOf("others") >= 0) ? 1 : 0;
/*     */         }
/*     */       
/* 683 */       } else if (item.equalsIgnoreCase("MarkTime")) {
/*     */         
/* 685 */         this.markTime = toInt(data, 1, 30000) + 1;
/*     */       }
/* 687 */       else if (item.equalsIgnoreCase("Recoil")) {
/*     */         
/* 689 */         this.recoil = toFloat(data, 0.0F, 100.0F);
/*     */       }
/* 691 */       else if (item.equalsIgnoreCase("DamageFactor")) {
/*     */         
/* 693 */         String[] s = splitParam(data);
/* 694 */         if (s.length >= 2) {
/*     */           Class<MCH_EntityVehicle> clazz;
/* 696 */           Class<EntityPlayer> c = null;
/* 697 */           String className = s[0].toLowerCase();
/* 698 */           if (className.equals("player")) {
/*     */             
/* 700 */             c = EntityPlayer.class;
/*     */           }
/* 702 */           else if (className.equals("heli") || className.equals("helicopter")) {
/*     */             
/* 704 */             Class<MCH_EntityHeli> clazz1 = MCH_EntityHeli.class;
/*     */           }
/* 706 */           else if (className.equals("plane")) {
/*     */             
/* 708 */             Class<MCP_EntityPlane> clazz1 = MCP_EntityPlane.class;
/*     */           }
/* 710 */           else if (className.equals("tank")) {
/*     */             
/* 712 */             Class<MCH_EntityTank> clazz1 = MCH_EntityTank.class;
/*     */           }
/* 714 */           else if (className.equals("vehicle")) {
/*     */             
/* 716 */             clazz = MCH_EntityVehicle.class;
/*     */           } 
/*     */           
/* 719 */           if (clazz != null) {
/*     */             
/* 721 */             if (this.damageFactor == null)
/*     */             {
/* 723 */               this.damageFactor = new MCH_DamageFactor();
/*     */             }
/* 725 */             this.damageFactor.add(clazz, toFloat(s[1], 0.0F, 1000000.0F));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getDamageFactor(Entity e) {
/* 733 */     return (this.damageFactor != null) ? this.damageFactor.getDamageFactor(e) : 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWeaponTypeName() {
/* 738 */     if (this.type.equalsIgnoreCase("MachineGun1")) return "MachineGun"; 
/* 739 */     if (this.type.equalsIgnoreCase("MachineGun2")) return "MachineGun"; 
/* 740 */     if (this.type.equalsIgnoreCase("Torpedo")) return "Torpedo"; 
/* 741 */     if (this.type.equalsIgnoreCase("CAS")) return "CAS"; 
/* 742 */     if (this.type.equalsIgnoreCase("Rocket")) return "Rocket"; 
/* 743 */     if (this.type.equalsIgnoreCase("ASMissile")) return "AS Missile"; 
/* 744 */     if (this.type.equalsIgnoreCase("AAMissile")) return "AA Missile"; 
/* 745 */     if (this.type.equalsIgnoreCase("TVMissile")) return "TV Missile"; 
/* 746 */     if (this.type.equalsIgnoreCase("ATMissile")) return "AT Missile"; 
/* 747 */     if (this.type.equalsIgnoreCase("Bomb")) return "Bomb"; 
/* 748 */     if (this.type.equalsIgnoreCase("MkRocket")) return "Mk Rocket"; 
/* 749 */     if (this.type.equalsIgnoreCase("Dummy")) return "Dummy"; 
/* 750 */     if (this.type.equalsIgnoreCase("Smoke")) return "Smoke"; 
/* 751 */     if (this.type.equalsIgnoreCase("Smoke")) return "Smoke"; 
/* 752 */     if (this.type.equalsIgnoreCase("Dispenser")) return "Dispenser"; 
/* 753 */     if (this.type.equalsIgnoreCase("TargetingPod")) return "Targeting Pod"; 
/* 754 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */