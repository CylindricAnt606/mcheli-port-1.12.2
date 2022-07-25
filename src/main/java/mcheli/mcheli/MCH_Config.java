/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_ConfigPrm;
/*     */ import mcheli.MCH_InputFile;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_OutputFile;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_Config
/*     */ {
/*     */   public static String mcPath;
/*     */   public static String configFilePath;
/*     */   public static boolean DebugLog;
/*     */   public static String configVer;
/*     */   public static int hitMarkColorRGB;
/*     */   public static float hitMarkColorAlpha;
/*     */   public static List<Block> bulletBreakableBlocks;
/*  32 */   public static final List<Block> dummyBreakableBlocks = new ArrayList<Block>();
/*  33 */   public static final List<Material> dummyBreakableMaterials = new ArrayList<Material>();
/*     */   
/*     */   public static List<Block> carNoBreakableBlocks;
/*     */   
/*     */   public static List<Block> carBreakableBlocks;
/*     */   
/*     */   public static List<Material> carBreakableMaterials;
/*     */   
/*     */   public static List<Block> tankNoBreakableBlocks;
/*     */   
/*     */   public static List<Block> tankBreakableBlocks;
/*     */   
/*     */   public static List<Material> tankBreakableMaterials;
/*     */   
/*     */   public static MCH_ConfigPrm KeyUp;
/*     */   
/*     */   public static MCH_ConfigPrm KeyDown;
/*     */   
/*     */   public static MCH_ConfigPrm KeyRight;
/*     */   
/*     */   public static MCH_ConfigPrm KeyLeft;
/*     */   
/*     */   public static MCH_ConfigPrm KeySwitchMode;
/*     */   
/*     */   public static MCH_ConfigPrm KeySwitchHovering;
/*     */   
/*     */   public static MCH_ConfigPrm KeyAttack;
/*     */   
/*     */   public static MCH_ConfigPrm KeyUseWeapon;
/*     */   
/*     */   public static MCH_ConfigPrm KeySwitchWeapon1;
/*     */   
/*     */   public static MCH_ConfigPrm KeySwitchWeapon2;
/*     */   
/*     */   public static MCH_ConfigPrm KeySwWeaponMode;
/*     */   
/*     */   public static MCH_ConfigPrm KeyZoom;
/*     */   
/*     */   public static MCH_ConfigPrm KeyCameraMode;
/*     */   
/*     */   public static MCH_ConfigPrm KeyUnmount;
/*     */   
/*     */   public static MCH_ConfigPrm KeyFlare;
/*     */   
/*     */   public static MCH_ConfigPrm KeyExtra;
/*     */   
/*     */   public static MCH_ConfigPrm KeyCameraDistUp;
/*     */   
/*     */   public static MCH_ConfigPrm KeyCameraDistDown;
/*     */   
/*     */   public static MCH_ConfigPrm KeyFreeLook;
/*     */   
/*     */   public static MCH_ConfigPrm KeyGUI;
/*     */   
/*     */   public static MCH_ConfigPrm KeyGearUpDown;
/*     */   
/*     */   public static MCH_ConfigPrm KeyPutToRack;
/*     */   
/*     */   public static MCH_ConfigPrm KeyDownFromRack;
/*     */   
/*     */   public static MCH_ConfigPrm KeyScoreboard;
/*     */   
/*     */   public static MCH_ConfigPrm KeyMultiplayManager;
/*     */   
/*     */   public static List<MCH_ConfigPrm> DamageVs;
/*     */   
/*     */   public static List<String> IgnoreBulletHitList;
/*     */   
/*     */   public static MCH_ConfigPrm IgnoreBulletHitItem;
/*     */   
/*     */   public static DamageFactor[] DamageFactorList;
/*     */   
/*     */   public static DamageFactor DamageVsEntity;
/*     */   
/*     */   public static DamageFactor DamageVsLiving;
/*     */   
/*     */   public static DamageFactor DamageVsPlayer;
/*     */   
/*     */   public static DamageFactor DamageVsMCHeliAircraft;
/*     */   
/*     */   public static DamageFactor DamageVsMCHeliTank;
/*     */   
/*     */   public static DamageFactor DamageVsMCHeliVehicle;
/*     */   
/*     */   public static DamageFactor DamageVsMCHeliOther;
/*     */   
/*     */   public static DamageFactor DamageAircraftByExternal;
/*     */   
/*     */   public static DamageFactor DamageTankByExternal;
/*     */   
/*     */   public static DamageFactor DamageVehicleByExternal;
/*     */   
/*     */   public static DamageFactor DamageOtherByExternal;
/*     */   
/*     */   public static List<MCH_ConfigPrm> CommandPermission;
/*     */   
/*     */   public static List<CommandPermission> CommandPermissionList;
/*     */   
/*     */   public static MCH_ConfigPrm TestMode;
/*     */   
/*     */   public static MCH_ConfigPrm EnableCommand;
/*     */   public static MCH_ConfigPrm PlaceableOnSpongeOnly;
/*     */   public static MCH_ConfigPrm HideKeybind;
/*     */   public static MCH_ConfigPrm ItemDamage;
/*     */   public static MCH_ConfigPrm ItemFuel;
/*     */   public static MCH_ConfigPrm AutoRepairHP;
/*     */   public static MCH_ConfigPrm Collision_DestroyBlock;
/*     */   public static MCH_ConfigPrm Explosion_DestroyBlock;
/*     */   public static MCH_ConfigPrm Explosion_FlamingBlock;
/*     */   public static MCH_ConfigPrm BulletBreakableBlock;
/*     */   public static MCH_ConfigPrm Collision_Car_BreakableBlock;
/*     */   public static MCH_ConfigPrm Collision_Car_NoBreakableBlock;
/*     */   public static MCH_ConfigPrm Collision_Car_BreakableMaterial;
/*     */   public static MCH_ConfigPrm Collision_Tank_BreakableBlock;
/*     */   public static MCH_ConfigPrm Collision_Tank_NoBreakableBlock;
/*     */   public static MCH_ConfigPrm Collision_Tank_BreakableMaterial;
/*     */   public static MCH_ConfigPrm Collision_EntityDamage;
/*     */   public static MCH_ConfigPrm Collision_EntityTankDamage;
/*     */   public static MCH_ConfigPrm LWeaponAutoFire;
/*     */   public static MCH_ConfigPrm DismountAll;
/*     */   public static MCH_ConfigPrm MountMinecartHeli;
/*     */   public static MCH_ConfigPrm MountMinecartPlane;
/*     */   public static MCH_ConfigPrm MountMinecartVehicle;
/*     */   public static MCH_ConfigPrm MountMinecartTank;
/*     */   public static MCH_ConfigPrm AutoThrottleDownHeli;
/*     */   public static MCH_ConfigPrm AutoThrottleDownPlane;
/*     */   public static MCH_ConfigPrm AutoThrottleDownTank;
/*     */   public static MCH_ConfigPrm DisableItemRender;
/*     */   public static MCH_ConfigPrm RenderDistanceWeight;
/*     */   public static MCH_ConfigPrm MobRenderDistanceWeight;
/*     */   public static MCH_ConfigPrm CreativeTabIcon;
/*     */   public static MCH_ConfigPrm CreativeTabIconHeli;
/*     */   public static MCH_ConfigPrm CreativeTabIconPlane;
/*     */   public static MCH_ConfigPrm CreativeTabIconTank;
/*     */   public static MCH_ConfigPrm CreativeTabIconVehicle;
/*     */   public static MCH_ConfigPrm DisableShader;
/*     */   public static MCH_ConfigPrm AliveTimeOfCartridge;
/*     */   public static MCH_ConfigPrm InfinityAmmo;
/*     */   public static MCH_ConfigPrm InfinityFuel;
/*     */   public static MCH_ConfigPrm HitMarkColor;
/*     */   public static MCH_ConfigPrm SmoothShading;
/*     */   public static MCH_ConfigPrm EnableModEntityRender;
/*     */   public static MCH_ConfigPrm DisableRenderLivingSpecials;
/*     */   public static MCH_ConfigPrm PreventingBroken;
/*     */   public static MCH_ConfigPrm DropItemInCreativeMode;
/*     */   public static MCH_ConfigPrm BreakableOnlyPickaxe;
/*     */   public static MCH_ConfigPrm InvertMouse;
/*     */   public static MCH_ConfigPrm MouseSensitivity;
/*     */   public static MCH_ConfigPrm MouseControlStickModeHeli;
/*     */   public static MCH_ConfigPrm MouseControlStickModePlane;
/*     */   public static MCH_ConfigPrm MouseControlFlightSimMode;
/*     */   public static MCH_ConfigPrm SwitchWeaponWithMouseWheel;
/*     */   public static MCH_ConfigPrm AllPlaneSpeed;
/*     */   public static MCH_ConfigPrm AllHeliSpeed;
/*     */   public static MCH_ConfigPrm AllTankSpeed;
/*     */   public static MCH_ConfigPrm HurtResistantTime;
/*     */   public static MCH_ConfigPrm DisplayHUDThirdPerson;
/*     */   public static MCH_ConfigPrm DisableCameraDistChange;
/*     */   public static MCH_ConfigPrm EnableReplaceTextureManager;
/*     */   public static MCH_ConfigPrm DisplayEntityMarker;
/*     */   public static MCH_ConfigPrm EntityMarkerSize;
/*     */   public static MCH_ConfigPrm BlockMarkerSize;
/*     */   public static MCH_ConfigPrm DisplayMarkThroughWall;
/*     */   public static MCH_ConfigPrm ReplaceRenderViewEntity;
/*     */   public static MCH_ConfigPrm StingerLockRange;
/*     */   public static MCH_ConfigPrm DefaultExplosionParticle;
/*     */   public static MCH_ConfigPrm RangeFinderSpotDist;
/*     */   public static MCH_ConfigPrm RangeFinderSpotTime;
/*     */   public static MCH_ConfigPrm RangeFinderConsume;
/*     */   public static MCH_ConfigPrm EnablePutRackInFlying;
/*     */   public static MCH_ConfigPrm EnableDebugBoundingBox;
/*     */   public static MCH_ConfigPrm ItemID_Fuel;
/*     */   public static MCH_ConfigPrm ItemID_GLTD;
/*     */   public static MCH_ConfigPrm ItemID_Chain;
/*     */   public static MCH_ConfigPrm ItemID_Parachute;
/*     */   public static MCH_ConfigPrm ItemID_Container;
/*     */   public static MCH_ConfigPrm ItemID_Stinger;
/*     */   public static MCH_ConfigPrm ItemID_StingerMissile;
/*     */   public static MCH_ConfigPrm[] ItemID_UavStation;
/*     */   public static MCH_ConfigPrm ItemID_InvisibleItem;
/*     */   public static MCH_ConfigPrm ItemID_DraftingTable;
/*     */   public static MCH_ConfigPrm ItemID_Wrench;
/*     */   public static MCH_ConfigPrm ItemID_RangeFinder;
/*     */   public static MCH_ConfigPrm BlockID_DraftingTableOFF;
/*     */   public static MCH_ConfigPrm BlockID_DraftingTableON;
/*     */   public static MCH_ConfigPrm ItemRecipe_Fuel;
/*     */   public static MCH_ConfigPrm ItemRecipe_GLTD;
/*     */   public static MCH_ConfigPrm ItemRecipe_Chain;
/*     */   public static MCH_ConfigPrm ItemRecipe_Parachute;
/*     */   public static MCH_ConfigPrm ItemRecipe_Container;
/*     */   public static MCH_ConfigPrm ItemRecipe_Stinger;
/*     */   public static MCH_ConfigPrm ItemRecipe_StingerMissile;
/*     */   public static MCH_ConfigPrm ItemRecipe_Javelin;
/*     */   public static MCH_ConfigPrm ItemRecipe_JavelinMissile;
/*     */   public static MCH_ConfigPrm[] ItemRecipe_UavStation;
/*     */   public static MCH_ConfigPrm ItemRecipe_DraftingTable;
/*     */   public static MCH_ConfigPrm ItemRecipe_Wrench;
/*     */   public static MCH_ConfigPrm ItemRecipe_RangeFinder;
/*     */   public static MCH_ConfigPrm[] KeyConfig;
/*     */   public static MCH_ConfigPrm[] General;
/* 233 */   public final String destroyBlockNames = "glass_pane, stained_glass_pane, tallgrass, double_plant, yellow_flower, red_flower, vine, wheat, reeds, waterlily";
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
/*     */   public MCH_Config(String minecraftPath, String cfgFile) {
/* 247 */     this; mcPath = minecraftPath;
/* 248 */     this; this; configFilePath = mcPath + cfgFile;
/*     */     
/* 250 */     this; DebugLog = false;
/* 251 */     this; configVer = "0.0.0";
/*     */     
/* 253 */     this; bulletBreakableBlocks = new ArrayList<Block>();
/* 254 */     this; carBreakableBlocks = new ArrayList<Block>();
/* 255 */     this; carNoBreakableBlocks = new ArrayList<Block>();
/* 256 */     this; carBreakableMaterials = new ArrayList<Material>();
/* 257 */     this; tankBreakableBlocks = new ArrayList<Block>();
/* 258 */     this; tankNoBreakableBlocks = new ArrayList<Block>();
/* 259 */     this; tankBreakableMaterials = new ArrayList<Material>();
/*     */     
/* 261 */     this; KeyUp = new MCH_ConfigPrm("KeyUp", 17);
/* 262 */     this; KeyDown = new MCH_ConfigPrm("KeyDown", 31);
/* 263 */     this; KeyRight = new MCH_ConfigPrm("KeyRight", 32);
/* 264 */     this; KeyLeft = new MCH_ConfigPrm("KeyLeft", 30);
/* 265 */     this; KeySwitchMode = new MCH_ConfigPrm("KeySwitchGunner", 35);
/* 266 */     this; KeySwitchHovering = new MCH_ConfigPrm("KeySwitchHovering", 57);
/* 267 */     this; KeyAttack = new MCH_ConfigPrm("KeyAttack", -100);
/* 268 */     this; KeyUseWeapon = new MCH_ConfigPrm("KeyUseWeapon", -99);
/* 269 */     this; KeySwitchWeapon1 = new MCH_ConfigPrm("KeySwitchWeapon1", -98);
/* 270 */     this; KeySwitchWeapon2 = new MCH_ConfigPrm("KeySwitchWeapon2", 34);
/* 271 */     this; KeySwWeaponMode = new MCH_ConfigPrm("KeySwitchWeaponMode", 45);
/* 272 */     this; KeyZoom = new MCH_ConfigPrm("KeyZoom", 44);
/* 273 */     this; KeyCameraMode = new MCH_ConfigPrm("KeyCameraMode", 46);
/* 274 */     this; KeyUnmount = new MCH_ConfigPrm("KeyUnmountMob", 21);
/* 275 */     this; KeyFlare = new MCH_ConfigPrm("KeyFlare", 47);
/* 276 */     this; KeyExtra = new MCH_ConfigPrm("KeyExtra", 33);
/* 277 */     this; KeyCameraDistUp = new MCH_ConfigPrm("KeyCameraDistanceUp", 201);
/* 278 */     this; KeyCameraDistDown = new MCH_ConfigPrm("KeyCameraDistanceDown", 209);
/* 279 */     this; KeyFreeLook = new MCH_ConfigPrm("KeyFreeLook", 29);
/* 280 */     this; KeyGUI = new MCH_ConfigPrm("KeyGUI", 19);
/* 281 */     this; KeyGearUpDown = new MCH_ConfigPrm("KeyGearUpDown", 48);
/* 282 */     this; KeyPutToRack = new MCH_ConfigPrm("KeyPutToRack", 36);
/* 283 */     this; KeyDownFromRack = new MCH_ConfigPrm("KeyDownFromRack", 22);
/* 284 */     this; KeyScoreboard = new MCH_ConfigPrm("KeyScoreboard", 38);
/* 285 */     this; KeyMultiplayManager = new MCH_ConfigPrm("KeyMultiplayManager", 50);
/* 286 */     this; this; (new MCH_ConfigPrm[23])[0] = KeyUp; this; (new MCH_ConfigPrm[23])[1] = KeyDown; this; (new MCH_ConfigPrm[23])[2] = KeyRight; this; (new MCH_ConfigPrm[23])[3] = KeyLeft; this; (new MCH_ConfigPrm[23])[4] = KeySwitchMode; this; (new MCH_ConfigPrm[23])[5] = KeySwitchHovering; this; (new MCH_ConfigPrm[23])[6] = KeySwitchWeapon1; this; (new MCH_ConfigPrm[23])[7] = KeySwitchWeapon2; this; (new MCH_ConfigPrm[23])[8] = KeySwWeaponMode; this; (new MCH_ConfigPrm[23])[9] = KeyZoom; this; (new MCH_ConfigPrm[23])[10] = KeyCameraMode; this; (new MCH_ConfigPrm[23])[11] = KeyUnmount; this; (new MCH_ConfigPrm[23])[12] = KeyFlare; this; (new MCH_ConfigPrm[23])[13] = KeyExtra; this; (new MCH_ConfigPrm[23])[14] = KeyCameraDistUp; this; (new MCH_ConfigPrm[23])[15] = KeyCameraDistDown; this; (new MCH_ConfigPrm[23])[16] = KeyFreeLook; this; (new MCH_ConfigPrm[23])[17] = KeyGUI; this; (new MCH_ConfigPrm[23])[18] = KeyGearUpDown; this; (new MCH_ConfigPrm[23])[19] = KeyPutToRack; this; (new MCH_ConfigPrm[23])[20] = KeyDownFromRack; this; (new MCH_ConfigPrm[23])[21] = KeyScoreboard; this; KeyConfig = new MCH_ConfigPrm[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, KeyMultiplayManager };
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
/* 314 */     this; DamageVs = new ArrayList<MCH_ConfigPrm>();
/* 315 */     this; CommandPermission = new ArrayList<MCH_ConfigPrm>();
/* 316 */     this; CommandPermissionList = new ArrayList<CommandPermission>();
/* 317 */     this; IgnoreBulletHitList = new ArrayList<String>();
/* 318 */     this; IgnoreBulletHitItem = new MCH_ConfigPrm("IgnoreBulletHit", "");
/*     */     
/* 320 */     this; TestMode = new MCH_ConfigPrm("TestMode", false);
/* 321 */     this; EnableCommand = new MCH_ConfigPrm("EnableCommand", true);
/* 322 */     this; PlaceableOnSpongeOnly = new MCH_ConfigPrm("PlaceableOnSpongeOnly", false);
/* 323 */     this; HideKeybind = new MCH_ConfigPrm("HideKeybind", false);
/* 324 */     this; ItemDamage = new MCH_ConfigPrm("ItemDamage", true);
/* 325 */     this; ItemFuel = new MCH_ConfigPrm("ItemFuel", true);
/* 326 */     this; AutoRepairHP = new MCH_ConfigPrm("AutoRepairHP", 0.5D);
/* 327 */     this; Collision_DestroyBlock = new MCH_ConfigPrm("Collision_DestroyBlock", true);
/* 328 */     this; Explosion_DestroyBlock = new MCH_ConfigPrm("Explosion_DestroyBlock", true);
/* 329 */     this; Explosion_FlamingBlock = new MCH_ConfigPrm("Explosion_FlamingBlock", true);
/* 330 */     this; Collision_Car_BreakableBlock = new MCH_ConfigPrm("Collision_Car_BreakableBlock", "double_plant, glass_pane,stained_glass_pane");
/*     */     
/* 332 */     this; Collision_Car_NoBreakableBlock = new MCH_ConfigPrm("Collision_Car_NoBreakBlock", "torch");
/* 333 */     this; Collision_Car_BreakableMaterial = new MCH_ConfigPrm("Collision_Car_BreakableMaterial", "cactus, cake, gourd, leaves, vine, plants");
/*     */     
/* 335 */     this; Collision_Tank_BreakableBlock = new MCH_ConfigPrm("Collision_Tank_BreakableBlock", "nether_brick_fence");
/* 336 */     this; Collision_Tank_BreakableBlock.validVer = "1.0.0";
/* 337 */     this; Collision_Tank_NoBreakableBlock = new MCH_ConfigPrm("Collision_Tank_NoBreakBlock", "torch, glowstone");
/* 338 */     this; Collision_Tank_BreakableMaterial = new MCH_ConfigPrm("Collision_Tank_BreakableMaterial", "cactus, cake, carpet, circuits, glass, gourd, leaves, vine, wood, plants");
/*     */     
/* 340 */     this; Collision_EntityDamage = new MCH_ConfigPrm("Collision_EntityDamage", true);
/* 341 */     this; Collision_EntityTankDamage = new MCH_ConfigPrm("Collision_EntityTankDamage", false);
/* 342 */     this; LWeaponAutoFire = new MCH_ConfigPrm("LWeaponAutoFire", false);
/* 343 */     this; DismountAll = new MCH_ConfigPrm("DismountAll", false);
/* 344 */     this; MountMinecartHeli = new MCH_ConfigPrm("MountMinecartHeli", true);
/* 345 */     this; MountMinecartPlane = new MCH_ConfigPrm("MountMinecartPlane", true);
/* 346 */     this; MountMinecartVehicle = new MCH_ConfigPrm("MountMinecartVehicle", false);
/* 347 */     this; MountMinecartTank = new MCH_ConfigPrm("MountMinecartTank", true);
/* 348 */     this; AutoThrottleDownHeli = new MCH_ConfigPrm("AutoThrottleDownHeli", true);
/* 349 */     this; AutoThrottleDownPlane = new MCH_ConfigPrm("AutoThrottleDownPlane", false);
/* 350 */     this; AutoThrottleDownTank = new MCH_ConfigPrm("AutoThrottleDownTank", false);
/* 351 */     this; DisableItemRender = new MCH_ConfigPrm("DisableItemRender", 1);
/* 352 */     this; DisableItemRender.desc = ";DisableItemRender = 0 ~ 3 (1 = Recommended)";
/* 353 */     this; RenderDistanceWeight = new MCH_ConfigPrm("RenderDistanceWeight", 10.0D);
/* 354 */     this; MobRenderDistanceWeight = new MCH_ConfigPrm("MobRenderDistanceWeight", 1.0D);
/* 355 */     this; CreativeTabIcon = new MCH_ConfigPrm("CreativeTabIconItem", "fuel");
/* 356 */     this; CreativeTabIconHeli = new MCH_ConfigPrm("CreativeTabIconHeli", "ah-64");
/* 357 */     this; CreativeTabIconPlane = new MCH_ConfigPrm("CreativeTabIconPlane", "f22a");
/* 358 */     this; CreativeTabIconTank = new MCH_ConfigPrm("CreativeTabIconTank", "merkava_mk4");
/* 359 */     this; CreativeTabIconVehicle = new MCH_ConfigPrm("CreativeTabIconVehicle", "mk15");
/* 360 */     this; DisableShader = new MCH_ConfigPrm("DisableShader", false);
/* 361 */     this; AliveTimeOfCartridge = new MCH_ConfigPrm("AliveTimeOfCartridge", 200);
/* 362 */     this; InfinityAmmo = new MCH_ConfigPrm("InfinityAmmo", false);
/* 363 */     this; InfinityFuel = new MCH_ConfigPrm("InfinityFuel", false);
/* 364 */     this; HitMarkColor = new MCH_ConfigPrm("HitMarkColor", "255, 255, 0, 0");
/* 365 */     this; HitMarkColor.desc = ";HitMarkColor = Alpha, Red, Green, Blue";
/* 366 */     this; SmoothShading = new MCH_ConfigPrm("SmoothShading", true);
/* 367 */     this; BulletBreakableBlock = new MCH_ConfigPrm("BulletBreakableBlocks", "glass_pane, stained_glass_pane, tallgrass, double_plant, yellow_flower, red_flower, vine, wheat, reeds, waterlily");
/* 368 */     this; BulletBreakableBlock.validVer = "0.10.4";
/* 369 */     this; EnableModEntityRender = new MCH_ConfigPrm("EnableModEntityRender", true);
/* 370 */     this; DisableRenderLivingSpecials = new MCH_ConfigPrm("DisableRenderLivingSpecials", true);
/* 371 */     this; PreventingBroken = new MCH_ConfigPrm("PreventingBroken", false);
/* 372 */     this; DropItemInCreativeMode = new MCH_ConfigPrm("DropItemInCreativeMode", false);
/* 373 */     this; BreakableOnlyPickaxe = new MCH_ConfigPrm("BreakableOnlyPickaxe", false);
/* 374 */     this; InvertMouse = new MCH_ConfigPrm("InvertMouse", false);
/* 375 */     this; MouseSensitivity = new MCH_ConfigPrm("MouseSensitivity", 10.0D);
/* 376 */     this; MouseControlStickModeHeli = new MCH_ConfigPrm("MouseControlStickModeHeli", false);
/* 377 */     this; MouseControlStickModePlane = new MCH_ConfigPrm("MouseControlStickModePlane", false);
/* 378 */     this; MouseControlFlightSimMode = new MCH_ConfigPrm("MouseControlFlightSimMode", false);
/* 379 */     this; MouseControlFlightSimMode.desc = ";MouseControlFlightSimMode = true ( Yaw:key, Roll=mouse )";
/* 380 */     this; SwitchWeaponWithMouseWheel = new MCH_ConfigPrm("SwitchWeaponWithMouseWheel", true);
/* 381 */     this; AllHeliSpeed = new MCH_ConfigPrm("AllHeliSpeed", 1.0D);
/* 382 */     this; AllPlaneSpeed = new MCH_ConfigPrm("AllPlaneSpeed", 1.0D);
/* 383 */     this; AllTankSpeed = new MCH_ConfigPrm("AllTankSpeed", 1.0D);
/* 384 */     this; HurtResistantTime = new MCH_ConfigPrm("HurtResistantTime", 0.0D);
/* 385 */     this; DisplayHUDThirdPerson = new MCH_ConfigPrm("DisplayHUDThirdPerson", false);
/* 386 */     this; DisableCameraDistChange = new MCH_ConfigPrm("DisableThirdPersonCameraDistChange", false);
/* 387 */     this; EnableReplaceTextureManager = new MCH_ConfigPrm("EnableReplaceTextureManager", true);
/* 388 */     this; DisplayEntityMarker = new MCH_ConfigPrm("DisplayEntityMarker", true);
/* 389 */     this; DisplayMarkThroughWall = new MCH_ConfigPrm("DisplayMarkThroughWall", true);
/* 390 */     this; EntityMarkerSize = new MCH_ConfigPrm("EntityMarkerSize", 10.0D);
/* 391 */     this; BlockMarkerSize = new MCH_ConfigPrm("BlockMarkerSize", 10.0D);
/* 392 */     this; ReplaceRenderViewEntity = new MCH_ConfigPrm("ReplaceRenderViewEntity", true);
/* 393 */     this; StingerLockRange = new MCH_ConfigPrm("StingerLockRange", 320.0D);
/* 394 */     this; StingerLockRange.validVer = "1.0.0";
/* 395 */     this; DefaultExplosionParticle = new MCH_ConfigPrm("DefaultExplosionParticle", false);
/* 396 */     this; RangeFinderSpotDist = new MCH_ConfigPrm("RangeFinderSpotDist", 400);
/* 397 */     this; RangeFinderSpotTime = new MCH_ConfigPrm("RangeFinderSpotTime", 15);
/* 398 */     this; RangeFinderConsume = new MCH_ConfigPrm("RangeFinderConsume", true);
/* 399 */     this; EnablePutRackInFlying = new MCH_ConfigPrm("EnablePutRackInFlying", true);
/* 400 */     this; EnableDebugBoundingBox = new MCH_ConfigPrm("EnableDebugBoundingBox", true);
/*     */     
/* 402 */     this; hitMarkColorAlpha = 1.0F;
/* 403 */     this; hitMarkColorRGB = 16711680;
/*     */     
/* 405 */     this; ItemRecipe_Fuel = new MCH_ConfigPrm("ItemRecipe_Fuel", "\"ICI\", \"III\", I, iron_ingot, C, coal");
/* 406 */     this; ItemRecipe_GLTD = new MCH_ConfigPrm("ItemRecipe_GLTD", "\" B \", \"IDI\", \"IRI\", B, iron_block, I, iron_ingot, D, diamond, R, redstone");
/* 407 */     this; ItemRecipe_Chain = new MCH_ConfigPrm("ItemRecipe_Chain", "\"I I\", \"III\", \"I I\", I, iron_ingot");
/* 408 */     this; ItemRecipe_Parachute = new MCH_ConfigPrm("ItemRecipe_Parachute", "\"WWW\", \"S S\", \" W \", W, wool, S, string");
/* 409 */     this; ItemRecipe_Container = new MCH_ConfigPrm("ItemRecipe_Container", "\"CCI\", C, chest, I, iron_ingot");
/* 410 */     this; ItemRecipe_UavStation = new MCH_ConfigPrm[] { new MCH_ConfigPrm("ItemRecipe_UavStation", "\"III\", \"IDI\", \"IRI\", I, iron_ingot, D, diamond, R, redstone_block"), new MCH_ConfigPrm("ItemRecipe_UavStation2", "\"IDI\", \"IRI\", I, iron_ingot, D, diamond, R, redstone") };
/*     */ 
/*     */ 
/*     */     
/* 414 */     this; ItemRecipe_DraftingTable = new MCH_ConfigPrm("ItemRecipe_DraftingTable", "\"R  \", \"PCP\", \"F F\", R, redstone, C, crafting_table, P, planks, F, fence");
/* 415 */     this; ItemRecipe_Wrench = new MCH_ConfigPrm("ItemRecipe_Wrench", "\" I \", \" II\", \"I  \", I, iron_ingot");
/* 416 */     this; ItemRecipe_RangeFinder = new MCH_ConfigPrm("ItemRecipe_RangeFinder", "\"III\", \"RGR\", \"III\", I, iron_ingot, G, glass, R, redstone");
/* 417 */     this; ItemRecipe_Stinger = new MCH_ConfigPrm("ItemRecipe_Stinger", "\"G  \", \"III\", \"RI \", G, glass, I, iron_ingot, R, redstone");
/* 418 */     this; ItemRecipe_StingerMissile = new MCH_ConfigPrm("ItemRecipe_StingerMissile", "\"R  \", \" I \", \"  G\", G, gunpowder, I, iron_ingot, R, redstone");
/* 419 */     this; ItemRecipe_Javelin = new MCH_ConfigPrm("ItemRecipe_Javelin", "\"III\", \"GR \", G, glass, I, iron_ingot, R, redstone");
/* 420 */     this; ItemRecipe_JavelinMissile = new MCH_ConfigPrm("ItemRecipe_JavelinMissile", "\" R \", \" I \", \" G \", G, gunpowder, I, iron_ingot, R, redstone");
/*     */     
/* 422 */     this; ItemID_GLTD = new MCH_ConfigPrm("ItemID_GLTD", 28799);
/* 423 */     this; ItemID_Chain = new MCH_ConfigPrm("ItemID_Chain", 28798);
/* 424 */     this; ItemID_Parachute = new MCH_ConfigPrm("ItemID_Parachute", 28797);
/* 425 */     this; ItemID_Container = new MCH_ConfigPrm("ItemID_Container", 28796);
/* 426 */     this; ItemID_UavStation = new MCH_ConfigPrm[] { new MCH_ConfigPrm("ItemID_UavStation", 28795), new MCH_ConfigPrm("ItemID_UavStation2", 28790) };
/*     */ 
/*     */ 
/*     */     
/* 430 */     this; ItemID_InvisibleItem = new MCH_ConfigPrm("ItemID_Internal", 28794);
/* 431 */     this; ItemID_Fuel = new MCH_ConfigPrm("ItemID_Fuel", 28793);
/* 432 */     this; ItemID_DraftingTable = new MCH_ConfigPrm("ItemID_DraftingTable", 28792);
/* 433 */     this; ItemID_Wrench = new MCH_ConfigPrm("ItemID_Wrench", 28791);
/* 434 */     this; ItemID_RangeFinder = new MCH_ConfigPrm("ItemID_RangeFinder", 28789);
/* 435 */     this; ItemID_Stinger = new MCH_ConfigPrm("ItemID_Stinger", 28900);
/* 436 */     this; ItemID_StingerMissile = new MCH_ConfigPrm("ItemID_StingerMissile", 28901);
/*     */     
/* 438 */     this; BlockID_DraftingTableOFF = new MCH_ConfigPrm("BlockID_DraftingTable", 3450);
/* 439 */     this; BlockID_DraftingTableON = new MCH_ConfigPrm("BlockID_DraftingTableON", 3451);
/*     */     
/* 441 */     this; this; (new MCH_ConfigPrm[86])[0] = TestMode; this; (new MCH_ConfigPrm[86])[1] = EnableCommand; (new MCH_ConfigPrm[86])[2] = null; this; (new MCH_ConfigPrm[86])[3] = PlaceableOnSpongeOnly; this; (new MCH_ConfigPrm[86])[4] = ItemDamage; this; (new MCH_ConfigPrm[86])[5] = ItemFuel; this; (new MCH_ConfigPrm[86])[6] = AutoRepairHP; this; (new MCH_ConfigPrm[86])[7] = Explosion_DestroyBlock; this; (new MCH_ConfigPrm[86])[8] = Explosion_FlamingBlock; this; (new MCH_ConfigPrm[86])[9] = BulletBreakableBlock; this; (new MCH_ConfigPrm[86])[10] = Collision_DestroyBlock; this; (new MCH_ConfigPrm[86])[11] = Collision_Car_BreakableBlock; this; (new MCH_ConfigPrm[86])[12] = Collision_Car_BreakableMaterial; this; (new MCH_ConfigPrm[86])[13] = Collision_Tank_BreakableBlock; this; (new MCH_ConfigPrm[86])[14] = Collision_Tank_BreakableMaterial; this; (new MCH_ConfigPrm[86])[15] = Collision_EntityDamage; this; (new MCH_ConfigPrm[86])[16] = Collision_EntityTankDamage; this; (new MCH_ConfigPrm[86])[17] = InfinityAmmo; this; (new MCH_ConfigPrm[86])[18] = InfinityFuel; this; (new MCH_ConfigPrm[86])[19] = DismountAll; this; (new MCH_ConfigPrm[86])[20] = MountMinecartHeli; this; (new MCH_ConfigPrm[86])[21] = MountMinecartPlane; this; (new MCH_ConfigPrm[86])[22] = MountMinecartVehicle; this; (new MCH_ConfigPrm[86])[23] = MountMinecartTank; this; (new MCH_ConfigPrm[86])[24] = PreventingBroken; this; (new MCH_ConfigPrm[86])[25] = DropItemInCreativeMode; this; (new MCH_ConfigPrm[86])[26] = BreakableOnlyPickaxe; this; (new MCH_ConfigPrm[86])[27] = AllHeliSpeed; this; (new MCH_ConfigPrm[86])[28] = AllPlaneSpeed; this; (new MCH_ConfigPrm[86])[29] = AllTankSpeed; this; (new MCH_ConfigPrm[86])[30] = HurtResistantTime; this; (new MCH_ConfigPrm[86])[31] = StingerLockRange; this; (new MCH_ConfigPrm[86])[32] = RangeFinderSpotDist; this; (new MCH_ConfigPrm[86])[33] = RangeFinderSpotTime; this; (new MCH_ConfigPrm[86])[34] = RangeFinderConsume; this; (new MCH_ConfigPrm[86])[35] = EnablePutRackInFlying; this; (new MCH_ConfigPrm[86])[36] = EnableDebugBoundingBox; (new MCH_ConfigPrm[86])[37] = null; this; (new MCH_ConfigPrm[86])[38] = InvertMouse; this; (new MCH_ConfigPrm[86])[39] = MouseSensitivity; this; (new MCH_ConfigPrm[86])[40] = MouseControlStickModeHeli; this; (new MCH_ConfigPrm[86])[41] = MouseControlStickModePlane; this; (new MCH_ConfigPrm[86])[42] = MouseControlFlightSimMode; this; (new MCH_ConfigPrm[86])[43] = AutoThrottleDownHeli; this; (new MCH_ConfigPrm[86])[44] = AutoThrottleDownPlane; this; (new MCH_ConfigPrm[86])[45] = AutoThrottleDownTank; this; (new MCH_ConfigPrm[86])[46] = SwitchWeaponWithMouseWheel; this; (new MCH_ConfigPrm[86])[47] = LWeaponAutoFire; this; (new MCH_ConfigPrm[86])[48] = DisableItemRender; this; (new MCH_ConfigPrm[86])[49] = HideKeybind; this; (new MCH_ConfigPrm[86])[50] = RenderDistanceWeight; this; (new MCH_ConfigPrm[86])[51] = MobRenderDistanceWeight; this; (new MCH_ConfigPrm[86])[52] = CreativeTabIcon; this; (new MCH_ConfigPrm[86])[53] = CreativeTabIconHeli; this; (new MCH_ConfigPrm[86])[54] = CreativeTabIconPlane; this; (new MCH_ConfigPrm[86])[55] = CreativeTabIconTank; this; (new MCH_ConfigPrm[86])[56] = CreativeTabIconVehicle; this; (new MCH_ConfigPrm[86])[57] = DisableShader; this; (new MCH_ConfigPrm[86])[58] = DefaultExplosionParticle; this; (new MCH_ConfigPrm[86])[59] = AliveTimeOfCartridge; this; (new MCH_ConfigPrm[86])[60] = HitMarkColor; this; (new MCH_ConfigPrm[86])[61] = SmoothShading; this; (new MCH_ConfigPrm[86])[62] = EnableModEntityRender; this; (new MCH_ConfigPrm[86])[63] = DisableRenderLivingSpecials; this; (new MCH_ConfigPrm[86])[64] = DisplayHUDThirdPerson; this; (new MCH_ConfigPrm[86])[65] = DisableCameraDistChange; this; (new MCH_ConfigPrm[86])[66] = EnableReplaceTextureManager; this; (new MCH_ConfigPrm[86])[67] = DisplayEntityMarker; this; (new MCH_ConfigPrm[86])[68] = EntityMarkerSize; this; (new MCH_ConfigPrm[86])[69] = BlockMarkerSize; this; (new MCH_ConfigPrm[86])[70] = ReplaceRenderViewEntity; (new MCH_ConfigPrm[86])[71] = null; this; (new MCH_ConfigPrm[86])[72] = ItemRecipe_Fuel; this; (new MCH_ConfigPrm[86])[73] = ItemRecipe_GLTD; this; (new MCH_ConfigPrm[86])[74] = ItemRecipe_Chain; this; (new MCH_ConfigPrm[86])[75] = ItemRecipe_Parachute; this; (new MCH_ConfigPrm[86])[76] = ItemRecipe_Container; this; (new MCH_ConfigPrm[86])[77] = ItemRecipe_UavStation[0]; this; (new MCH_ConfigPrm[86])[78] = ItemRecipe_UavStation[1]; this; (new MCH_ConfigPrm[86])[79] = ItemRecipe_DraftingTable; this; (new MCH_ConfigPrm[86])[80] = ItemRecipe_Wrench; this; (new MCH_ConfigPrm[86])[81] = ItemRecipe_RangeFinder; this; (new MCH_ConfigPrm[86])[82] = ItemRecipe_Stinger; this; (new MCH_ConfigPrm[86])[83] = ItemRecipe_StingerMissile; this; (new MCH_ConfigPrm[86])[84] = ItemRecipe_Javelin; this; General = new MCH_ConfigPrm[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ItemRecipe_JavelinMissile };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 550 */     this; DamageVsEntity = new DamageFactor(this, "DamageVsEntity");
/* 551 */     this; DamageVsLiving = new DamageFactor(this, "DamageVsLiving");
/* 552 */     this; DamageVsPlayer = new DamageFactor(this, "DamageVsPlayer");
/* 553 */     this; DamageVsMCHeliAircraft = new DamageFactor(this, "DamageVsMCHeliAircraft");
/* 554 */     this; DamageVsMCHeliTank = new DamageFactor(this, "DamageVsMCHeliTank");
/* 555 */     this; DamageVsMCHeliVehicle = new DamageFactor(this, "DamageVsMCHeliVehicle");
/* 556 */     this; DamageVsMCHeliOther = new DamageFactor(this, "DamageVsMCHeliOther");
/* 557 */     this; DamageAircraftByExternal = new DamageFactor(this, "DamageMCHeliAircraftByExternal");
/* 558 */     this; DamageTankByExternal = new DamageFactor(this, "DamageMCHeliTankByExternal");
/* 559 */     this; DamageVehicleByExternal = new DamageFactor(this, "DamageMCHeliVehicleByExternal");
/* 560 */     this; DamageOtherByExternal = new DamageFactor(this, "DamageMCHeliOtherByExternal");
/* 561 */     this; this; (new DamageFactor[11])[0] = DamageVsEntity; this; (new DamageFactor[11])[1] = DamageVsLiving; this; (new DamageFactor[11])[2] = DamageVsPlayer; this; (new DamageFactor[11])[3] = DamageVsMCHeliAircraft; this; (new DamageFactor[11])[4] = DamageVsMCHeliTank; this; (new DamageFactor[11])[5] = DamageVsMCHeliVehicle; this; (new DamageFactor[11])[6] = DamageVsMCHeliOther; this; (new DamageFactor[11])[7] = DamageAircraftByExternal; this; (new DamageFactor[11])[8] = DamageTankByExternal; this; (new DamageFactor[11])[9] = DamageVehicleByExternal; this; DamageFactorList = new DamageFactor[] { null, null, null, null, null, null, null, null, null, null, DamageOtherByExternal };
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
/*     */   public void setBlockListFromString(List<Block> list, String str) {
/* 578 */     list.clear();
/* 579 */     String[] s = str.split("\\s*,\\s*");
/* 580 */     for (String blockName : s) {
/*     */       
/* 582 */       Block b = W_Block.getBlockFromName(blockName);
/* 583 */       if (b != null)
/*     */       {
/* 585 */         list.add(b);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialListFromString(List<Material> list, String str) {
/* 592 */     list.clear();
/* 593 */     String[] s = str.split("\\s*,\\s*");
/* 594 */     for (String name : s) {
/*     */       
/* 596 */       Material m = MCH_Lib.getMaterialFromName(name);
/* 597 */       if (m != null)
/*     */       {
/* 599 */         list.add(m);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void correctionParameter() {
/* 607 */     this; String[] s = HitMarkColor.prmString.split("\\s*,\\s*");
/* 608 */     if (s.length == 4) {
/*     */       
/* 610 */       this; hitMarkColorAlpha = toInt255(s[0]) / 255.0F;
/* 611 */       this; hitMarkColorRGB = toInt255(s[1]) << 16 | toInt255(s[2]) << 8 | toInt255(s[3]);
/*     */     } 
/*     */     
/* 614 */     this; this; AllHeliSpeed.prmDouble = MCH_Lib.RNG(AllHeliSpeed.prmDouble, 0.0D, 1000.0D);
/* 615 */     this; this; AllPlaneSpeed.prmDouble = MCH_Lib.RNG(AllPlaneSpeed.prmDouble, 0.0D, 1000.0D);
/* 616 */     this; this; AllTankSpeed.prmDouble = MCH_Lib.RNG(AllTankSpeed.prmDouble, 0.0D, 1000.0D);
/*     */     
/* 618 */     this; setBlockListFromString(bulletBreakableBlocks, BulletBreakableBlock.prmString);
/* 619 */     this; setBlockListFromString(carBreakableBlocks, Collision_Car_BreakableBlock.prmString);
/* 620 */     this; setBlockListFromString(carNoBreakableBlocks, Collision_Car_NoBreakableBlock.prmString);
/* 621 */     this; setMaterialListFromString(carBreakableMaterials, Collision_Car_BreakableMaterial.prmString);
/* 622 */     this; setBlockListFromString(tankBreakableBlocks, Collision_Tank_BreakableBlock.prmString);
/* 623 */     this; setBlockListFromString(tankNoBreakableBlocks, Collision_Tank_NoBreakableBlock.prmString);
/* 624 */     this; setMaterialListFromString(tankBreakableMaterials, Collision_Tank_BreakableMaterial.prmString);
/*     */     
/* 626 */     this; if (EntityMarkerSize.prmDouble < 0.0D) {
/*     */       
/* 628 */       this; EntityMarkerSize.prmDouble = 0.0D;
/*     */     } 
/* 630 */     this; if (BlockMarkerSize.prmDouble < 0.0D) {
/*     */       
/* 632 */       this; BlockMarkerSize.prmDouble = 0.0D;
/*     */     } 
/*     */     
/* 635 */     if (HurtResistantTime.prmDouble < 0.0D)
/*     */     {
/* 637 */       HurtResistantTime.prmDouble = 0.0D;
/*     */     }
/* 639 */     if (HurtResistantTime.prmDouble > 10000.0D)
/*     */     {
/* 641 */       HurtResistantTime.prmDouble = 10000.0D;
/*     */     }
/*     */     
/* 644 */     if (MobRenderDistanceWeight.prmDouble < 0.1D) {
/*     */       
/* 646 */       MobRenderDistanceWeight.prmDouble = 0.1D;
/*     */     }
/* 648 */     else if (MobRenderDistanceWeight.prmDouble > 10.0D) {
/*     */       
/* 650 */       MobRenderDistanceWeight.prmDouble = 10.0D;
/*     */     } 
/*     */     
/* 653 */     for (MCH_ConfigPrm p : CommandPermission) {
/*     */       
/* 655 */       CommandPermission cpm = new CommandPermission(this, p.prmString);
/* 656 */       if (!cpm.name.isEmpty())
/*     */       {
/* 658 */         CommandPermissionList.add(cpm);
/*     */       }
/*     */     } 
/*     */     
/* 662 */     this; if (IgnoreBulletHitList.size() <= 0) {
/*     */       
/* 664 */       this; IgnoreBulletHitList.add("flansmod.common.guns.EntityBullet");
/* 665 */       this; IgnoreBulletHitList.add("flansmod.common.guns.EntityGrenade");
/*     */     } 
/*     */     
/* 668 */     boolean isNoDamageVsSetting = (DamageVs.size() <= 0);
/*     */     
/* 670 */     for (MCH_ConfigPrm p : DamageVs) {
/*     */       
/* 672 */       this; for (DamageFactor df : DamageFactorList) {
/*     */         
/* 674 */         if (p.name.equals(df.itemName))
/*     */         {
/* 676 */           df.list.add(newDamageEntity(p.prmString));
/*     */         }
/*     */       } 
/*     */     } 
/* 680 */     this; for (DamageFactor df : DamageFactorList) {
/*     */       
/* 682 */       if (df.list.size() <= 0) {
/*     */         
/* 684 */         DamageVs.add(new MCH_ConfigPrm(df.itemName, "1.0"));
/*     */       }
/*     */       else {
/*     */         
/* 688 */         boolean foundCommon = false;
/* 689 */         for (DamageEntity n : df.list) {
/*     */           
/* 691 */           if (n.name.isEmpty()) {
/*     */             
/* 693 */             foundCommon = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 697 */         if (!foundCommon)
/*     */         {
/* 699 */           DamageVs.add(new MCH_ConfigPrm(df.itemName, "1.0"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 704 */     if (isNoDamageVsSetting) {
/*     */       
/* 706 */       DamageVs.add(new MCH_ConfigPrm("DamageVsEntity", "3.0, flansmod"));
/* 707 */       DamageVs.add(new MCH_ConfigPrm("DamageMCHeliAircraftByExternal", "0.5, flansmod"));
/* 708 */       DamageVs.add(new MCH_ConfigPrm("DamageMCHeliVehicleByExternal", "0.5, flansmod"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public DamageEntity newDamageEntity(String s) {
/* 713 */     String[] splt = s.split("\\s*,\\s*");
/* 714 */     if (splt.length == 1)
/*     */     {
/* 716 */       return new DamageEntity(this, Double.parseDouble(splt[0]), "");
/*     */     }
/* 718 */     if (splt.length == 2)
/*     */     {
/* 720 */       return new DamageEntity(this, Double.parseDouble(splt[0]), splt[1]);
/*     */     }
/* 722 */     return new DamageEntity(this, 1.0D, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float applyDamageByExternal(Entity target, DamageSource ds, float damage) {
/*     */     List<DamageEntity> list;
/* 732 */     if (target instanceof mcheli.helicopter.MCH_EntityHeli || target instanceof mcheli.plane.MCP_EntityPlane) {
/*     */       
/* 734 */       list = DamageAircraftByExternal.list;
/*     */     }
/* 736 */     else if (target instanceof mcheli.tank.MCH_EntityTank) {
/*     */       
/* 738 */       list = DamageTankByExternal.list;
/*     */     }
/* 740 */     else if (target instanceof mcheli.vehicle.MCH_EntityVehicle) {
/*     */       
/* 742 */       list = DamageVehicleByExternal.list;
/*     */     }
/*     */     else {
/*     */       
/* 746 */       list = DamageOtherByExternal.list;
/*     */     } 
/*     */     
/* 749 */     Entity attacker = ds.func_76346_g();
/* 750 */     Entity attackerSource = ds.func_76364_f();
/* 751 */     for (DamageEntity de : list) {
/*     */       
/* 753 */       if (de.name.isEmpty() || (attacker != null && attacker.getClass().toString().indexOf(de.name) > 0) || (attackerSource != null && attackerSource.getClass().toString().indexOf(de.name) > 0))
/*     */       {
/*     */ 
/*     */         
/* 757 */         damage = (float)(damage * de.factor);
/*     */       }
/*     */     } 
/* 760 */     return damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float applyDamageVsEntity(Entity target, DamageSource ds, float damage) {
/*     */     List<DamageEntity> list;
/* 768 */     if (target == null) return damage;
/*     */     
/* 770 */     String targetName = target.getClass().toString();
/*     */     
/* 772 */     if (target instanceof mcheli.helicopter.MCH_EntityHeli || target instanceof mcheli.plane.MCP_EntityPlane) {
/*     */       
/* 774 */       list = DamageVsMCHeliAircraft.list;
/*     */     }
/* 776 */     else if (target instanceof mcheli.tank.MCH_EntityTank) {
/*     */       
/* 778 */       list = DamageVsMCHeliTank.list;
/*     */     }
/* 780 */     else if (target instanceof mcheli.vehicle.MCH_EntityVehicle) {
/*     */       
/* 782 */       list = DamageVsMCHeliVehicle.list;
/*     */     }
/* 784 */     else if (targetName.indexOf("mcheli.") > 0) {
/*     */       
/* 786 */       list = DamageVsMCHeliOther.list;
/*     */     }
/* 788 */     else if (target instanceof net.minecraft.entity.player.EntityPlayer) {
/*     */       
/* 790 */       list = DamageVsPlayer.list;
/*     */     }
/* 792 */     else if (target instanceof net.minecraft.entity.EntityLivingBase) {
/*     */       
/* 794 */       list = DamageVsLiving.list;
/*     */     }
/*     */     else {
/*     */       
/* 798 */       list = DamageVsEntity.list;
/*     */     } 
/*     */     
/* 801 */     for (DamageEntity de : list) {
/*     */       
/* 803 */       if (de.name.isEmpty() || targetName.indexOf(de.name) > 0)
/*     */       {
/* 805 */         damage = (float)(damage * de.factor);
/*     */       }
/*     */     } 
/*     */     
/* 809 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Block> getBreakableBlockListFromType(int n) {
/* 814 */     if (n == 2) return tankBreakableBlocks; 
/* 815 */     if (n == 1) return carBreakableBlocks; 
/* 816 */     return dummyBreakableBlocks;
/*     */   }
/*     */   
/*     */   public static List<Block> getNoBreakableBlockListFromType(int n) {
/* 820 */     if (n == 2) return tankNoBreakableBlocks; 
/* 821 */     if (n == 1) return carNoBreakableBlocks; 
/* 822 */     return dummyBreakableBlocks;
/*     */   }
/*     */   
/*     */   public static List<Material> getBreakableMaterialListFromType(int n) {
/* 826 */     if (n == 2) return tankBreakableMaterials; 
/* 827 */     if (n == 1) return carBreakableMaterials; 
/* 828 */     return dummyBreakableMaterials;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toInt255(String s) {
/* 833 */     int a = Integer.valueOf(s).intValue();
/* 834 */     return (a < 0) ? 0 : ((a > 255) ? 255 : a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() {
/* 839 */     MCH_InputFile file = new MCH_InputFile();
/* 840 */     this; if (file.open(configFilePath)) {
/*     */       
/* 842 */       String str = file.readLine();
/* 843 */       while (str != null) {
/*     */         
/* 845 */         if (str.trim().equalsIgnoreCase("McHeliOutputDebugLog")) {
/*     */           
/* 847 */           this; DebugLog = true;
/*     */         }
/*     */         else {
/*     */           
/* 851 */           readConfigData(str);
/*     */         } 
/* 853 */         str = file.readLine();
/*     */       } 
/*     */       
/* 856 */       file.close();
/*     */       
/* 858 */       MCH_Lib.Log("loaded " + file.file.getAbsolutePath(), new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 862 */       this; MCH_Lib.Log("" + (new File(configFilePath)).getAbsolutePath() + " not found.", new Object[0]);
/*     */     } 
/*     */     
/* 865 */     correctionParameter();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readConfigData(String str) {
/* 870 */     String[] s = str.split("=");
/*     */     
/* 872 */     if (s.length != 2)
/* 873 */       return;  s[0] = s[0].trim();
/* 874 */     s[1] = s[1].trim();
/*     */     
/* 876 */     if (s[0].equalsIgnoreCase("MOD_Version")) {
/*     */       
/* 878 */       this; configVer = s[1];
/*     */       
/*     */       return;
/*     */     } 
/* 882 */     if (s[0].equalsIgnoreCase("CommandPermission"))
/*     */     {
/* 884 */       CommandPermission.add(new MCH_ConfigPrm("CommandPermission", s[1]));
/*     */     }
/*     */     
/* 887 */     this; for (DamageFactor item : DamageFactorList) {
/*     */       
/* 889 */       if (item.itemName.equalsIgnoreCase(s[0])) {
/*     */         
/* 891 */         this; DamageVs.add(new MCH_ConfigPrm(item.itemName, s[1]));
/*     */       } 
/*     */     } 
/*     */     
/* 895 */     this; if (IgnoreBulletHitItem.compare(s[0])) {
/*     */       
/* 897 */       this; IgnoreBulletHitList.add(s[1]);
/*     */     } 
/*     */     
/* 900 */     this; for (MCH_ConfigPrm p : KeyConfig) {
/*     */       
/* 902 */       this; if (p != null && p.compare(s[0]) && p.isValidVer(configVer)) {
/*     */         
/* 904 */         p.setPrm(s[1]);
/*     */         return;
/*     */       } 
/*     */     } 
/* 908 */     this; for (MCH_ConfigPrm p : General) {
/*     */       
/* 910 */       this; if (p != null && p.compare(s[0]) && p.isValidVer(configVer)) {
/*     */         
/* 912 */         p.setPrm(s[1]);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void write() {
/* 920 */     MCH_OutputFile file = new MCH_OutputFile();
/* 921 */     this; if (file.open(configFilePath)) {
/*     */       
/* 923 */       writeConfigData(file.pw);
/* 924 */       file.close();
/* 925 */       MCH_Lib.Log("update " + file.file.getAbsolutePath(), new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 929 */       this; MCH_Lib.Log("" + (new File(configFilePath)).getAbsolutePath() + " cannot open.", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeConfigData(PrintWriter pw) {
/* 936 */     pw.println("[General]");
/* 937 */     pw.println("MOD_Name = mcheli");
/* 938 */     pw.println("MOD_Version = " + MCH_MOD.VER);
/* 939 */     pw.println("MOD_MC_Version = 1.7.10");
/* 940 */     pw.println();
/*     */     
/* 942 */     this; if (DebugLog) {
/*     */       
/* 944 */       pw.println("McHeliOutputDebugLog");
/* 945 */       pw.println();
/*     */     } 
/*     */     
/* 948 */     this; for (MCH_ConfigPrm p : General) {
/*     */       
/* 950 */       if (p != null) {
/*     */         
/* 952 */         if (!p.desc.isEmpty()) pw.println(p.desc); 
/* 953 */         pw.println(p.name + " = " + p);
/*     */       }
/*     */       else {
/*     */         
/* 957 */         pw.println("");
/*     */       } 
/*     */     } 
/* 960 */     pw.println();
/*     */     
/* 962 */     this; for (MCH_ConfigPrm p : DamageVs)
/*     */     {
/* 964 */       pw.println(p.name + " = " + p);
/*     */     }
/* 966 */     pw.println();
/*     */     
/* 968 */     this; for (String s : IgnoreBulletHitList) {
/*     */       
/* 970 */       this; pw.println(IgnoreBulletHitItem.name + " = " + s);
/*     */     } 
/* 972 */     pw.println();
/*     */     
/* 974 */     pw.println(";CommandPermission = commandName(eg, modlist, status, fill...):playerName1, playerName2, playerName3...");
/* 975 */     this; if (CommandPermission.size() == 0) {
/*     */       
/* 977 */       pw.println(";CommandPermission = modlist :example1, example2");
/* 978 */       pw.println(";CommandPermission = status :  example2");
/*     */     } 
/* 980 */     this; for (MCH_ConfigPrm p : CommandPermission)
/*     */     {
/* 982 */       pw.println(p.name + " = " + p);
/*     */     }
/* 984 */     pw.println();
/*     */     
/* 986 */     pw.println();
/* 987 */     pw.println("[Key config]");
/* 988 */     pw.println("http://minecraft.gamepedia.com/Key_codes");
/* 989 */     pw.println();
/*     */     
/* 991 */     this; for (MCH_ConfigPrm p : KeyConfig)
/*     */     {
/* 993 */       pw.println(p.name + " = " + p);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */