 package mcheli.mcheli;

 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import mcheli.mcheli.MCH_ConfigPrm;
 import mcheli.mcheli.MCH_InputFile;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.MCH_MOD;
 import mcheli.mcheli.MCH_OutputFile;
 import mcheli.mcheli.wrapper.W_Block;
 import net.minecraft.block.Block;
 import net.minecraft.block.material.Material;
 import net.minecraft.entity.Entity;
 import net.minecraft.util.DamageSource;

 //TODO: DamageFactor (No idea what that is)
 public class MCH_Config
 {
   public static String mcPath;
   public static String configFilePath;
   public static boolean DebugLog;
   public static String configVer;
   public static int hitMarkColorRGB;
   public static float hitMarkColorAlpha;
   public static List<Block> bulletBreakableBlocks;
   public static final List<Block> dummyBreakableBlocks = new ArrayList<Block>();
   public static final List<Material> dummyBreakableMaterials = new ArrayList<Material>();

   public static List<Block> carNoBreakableBlocks;

   public static List<Block> carBreakableBlocks;

   public static List<Material> carBreakableMaterials;

   public static List<Block> tankNoBreakableBlocks;

   public static List<Block> tankBreakableBlocks;

   public static List<Material> tankBreakableMaterials;

   public static MCH_ConfigPrm KeyUp;

   public static MCH_ConfigPrm KeyDown;

   public static MCH_ConfigPrm KeyRight;

   public static MCH_ConfigPrm KeyLeft;

   public static MCH_ConfigPrm KeySwitchMode;

   public static MCH_ConfigPrm KeySwitchHovering;

   public static MCH_ConfigPrm KeyAttack;

   public static MCH_ConfigPrm KeyUseWeapon;

   public static MCH_ConfigPrm KeySwitchWeapon1;

   public static MCH_ConfigPrm KeySwitchWeapon2;

   public static MCH_ConfigPrm KeySwWeaponMode;

   public static MCH_ConfigPrm KeyZoom;

   public static MCH_ConfigPrm KeyCameraMode;

   public static MCH_ConfigPrm KeyUnmount;

   public static MCH_ConfigPrm KeyFlare;

   public static MCH_ConfigPrm KeyExtra;

   public static MCH_ConfigPrm KeyCameraDistUp;

   public static MCH_ConfigPrm KeyCameraDistDown;

   public static MCH_ConfigPrm KeyFreeLook;

   public static MCH_ConfigPrm KeyGUI;

   public static MCH_ConfigPrm KeyGearUpDown;

   public static MCH_ConfigPrm KeyPutToRack;

   public static MCH_ConfigPrm KeyDownFromRack;

   public static MCH_ConfigPrm KeyScoreboard;

   public static MCH_ConfigPrm KeyMultiplayManager;

   public static List<MCH_ConfigPrm> DamageVs;

   public static List<String> IgnoreBulletHitList;

   public static MCH_ConfigPrm IgnoreBulletHitItem;

   public static DamageFactor[] DamageFactorList;

   public static DamageFactor DamageVsEntity;

   public static DamageFactor DamageVsLiving;

   public static DamageFactor DamageVsPlayer;

   public static DamageFactor DamageVsMCHeliAircraft;

   public static DamageFactor DamageVsMCHeliTank;

   public static DamageFactor DamageVsMCHeliVehicle;

   public static DamageFactor DamageVsMCHeliOther;

   public static DamageFactor DamageAircraftByExternal;

   public static DamageFactor DamageTankByExternal;

   public static DamageFactor DamageVehicleByExternal;

   public static DamageFactor DamageOtherByExternal;

   public static List<MCH_ConfigPrm> CommandPermission;

   public static List<CommandPermission> CommandPermissionList;

   public static MCH_ConfigPrm TestMode;

   public static MCH_ConfigPrm EnableCommand;
   public static MCH_ConfigPrm PlaceableOnSpongeOnly;
   public static MCH_ConfigPrm HideKeybind;
   public static MCH_ConfigPrm ItemDamage;
   public static MCH_ConfigPrm ItemFuel;
   public static MCH_ConfigPrm AutoRepairHP;
   public static MCH_ConfigPrm Collision_DestroyBlock;
   public static MCH_ConfigPrm Explosion_DestroyBlock;
   public static MCH_ConfigPrm Explosion_FlamingBlock;
   public static MCH_ConfigPrm BulletBreakableBlock;
   public static MCH_ConfigPrm Collision_Car_BreakableBlock;
   public static MCH_ConfigPrm Collision_Car_NoBreakableBlock;
   public static MCH_ConfigPrm Collision_Car_BreakableMaterial;
   public static MCH_ConfigPrm Collision_Tank_BreakableBlock;
   public static MCH_ConfigPrm Collision_Tank_NoBreakableBlock;
   public static MCH_ConfigPrm Collision_Tank_BreakableMaterial;
   public static MCH_ConfigPrm Collision_EntityDamage;
   public static MCH_ConfigPrm Collision_EntityTankDamage;
   public static MCH_ConfigPrm LWeaponAutoFire;
   public static MCH_ConfigPrm DismountAll;
   public static MCH_ConfigPrm MountMinecartHeli;
   public static MCH_ConfigPrm MountMinecartPlane;
   public static MCH_ConfigPrm MountMinecartVehicle;
   public static MCH_ConfigPrm MountMinecartTank;
   public static MCH_ConfigPrm AutoThrottleDownHeli;
   public static MCH_ConfigPrm AutoThrottleDownPlane;
   public static MCH_ConfigPrm AutoThrottleDownTank;
   public static MCH_ConfigPrm DisableItemRender;
   public static MCH_ConfigPrm RenderDistanceWeight;
   public static MCH_ConfigPrm MobRenderDistanceWeight;
   public static MCH_ConfigPrm CreativeTabIcon;
   public static MCH_ConfigPrm CreativeTabIconHeli;
   public static MCH_ConfigPrm CreativeTabIconPlane;
   public static MCH_ConfigPrm CreativeTabIconTank;
   public static MCH_ConfigPrm CreativeTabIconVehicle;
   public static MCH_ConfigPrm DisableShader;
   public static MCH_ConfigPrm AliveTimeOfCartridge;
   public static MCH_ConfigPrm InfinityAmmo;
   public static MCH_ConfigPrm InfinityFuel;
   public static MCH_ConfigPrm HitMarkColor;
   public static MCH_ConfigPrm SmoothShading;
   public static MCH_ConfigPrm EnableModEntityRender;
   public static MCH_ConfigPrm DisableRenderLivingSpecials;
   public static MCH_ConfigPrm PreventingBroken;
   public static MCH_ConfigPrm DropItemInCreativeMode;
   public static MCH_ConfigPrm BreakableOnlyPickaxe;
   public static MCH_ConfigPrm InvertMouse;
   public static MCH_ConfigPrm MouseSensitivity;
   public static MCH_ConfigPrm MouseControlStickModeHeli;
   public static MCH_ConfigPrm MouseControlStickModePlane;
   public static MCH_ConfigPrm MouseControlFlightSimMode;
   public static MCH_ConfigPrm SwitchWeaponWithMouseWheel;
   public static MCH_ConfigPrm AllPlaneSpeed;
   public static MCH_ConfigPrm AllHeliSpeed;
   public static MCH_ConfigPrm AllTankSpeed;
   public static MCH_ConfigPrm HurtResistantTime;
   public static MCH_ConfigPrm DisplayHUDThirdPerson;
   public static MCH_ConfigPrm DisableCameraDistChange;
   public static MCH_ConfigPrm EnableReplaceTextureManager;
   public static MCH_ConfigPrm DisplayEntityMarker;
   public static MCH_ConfigPrm EntityMarkerSize;
   public static MCH_ConfigPrm BlockMarkerSize;
   public static MCH_ConfigPrm DisplayMarkThroughWall;
   public static MCH_ConfigPrm ReplaceRenderViewEntity;
   public static MCH_ConfigPrm StingerLockRange;
   public static MCH_ConfigPrm DefaultExplosionParticle;
   public static MCH_ConfigPrm RangeFinderSpotDist;
   public static MCH_ConfigPrm RangeFinderSpotTime;
   public static MCH_ConfigPrm RangeFinderConsume;
   public static MCH_ConfigPrm EnablePutRackInFlying;
   public static MCH_ConfigPrm EnableDebugBoundingBox;
   public static MCH_ConfigPrm ItemID_Fuel;
   public static MCH_ConfigPrm ItemID_GLTD;
   public static MCH_ConfigPrm ItemID_Chain;
   public static MCH_ConfigPrm ItemID_Parachute;
   public static MCH_ConfigPrm ItemID_Container;
   public static MCH_ConfigPrm ItemID_Stinger;
   public static MCH_ConfigPrm ItemID_StingerMissile;
   public static MCH_ConfigPrm[] ItemID_UavStation;
   public static MCH_ConfigPrm ItemID_InvisibleItem;
   public static MCH_ConfigPrm ItemID_DraftingTable;
   public static MCH_ConfigPrm ItemID_Wrench;
   public static MCH_ConfigPrm ItemID_RangeFinder;
   public static MCH_ConfigPrm BlockID_DraftingTableOFF;
   public static MCH_ConfigPrm BlockID_DraftingTableON;
   public static MCH_ConfigPrm ItemRecipe_Fuel;
   public static MCH_ConfigPrm ItemRecipe_GLTD;
   public static MCH_ConfigPrm ItemRecipe_Chain;
   public static MCH_ConfigPrm ItemRecipe_Parachute;
   public static MCH_ConfigPrm ItemRecipe_Container;
   public static MCH_ConfigPrm ItemRecipe_Stinger;
   public static MCH_ConfigPrm ItemRecipe_StingerMissile;
   public static MCH_ConfigPrm ItemRecipe_Javelin;
   public static MCH_ConfigPrm ItemRecipe_JavelinMissile;
   public static MCH_ConfigPrm[] ItemRecipe_UavStation;
   public static MCH_ConfigPrm ItemRecipe_DraftingTable;
   public static MCH_ConfigPrm ItemRecipe_Wrench;
   public static MCH_ConfigPrm ItemRecipe_RangeFinder;
   public static MCH_ConfigPrm[] KeyConfig;
   public static MCH_ConfigPrm[] General;
   public final String destroyBlockNames = "glass_pane, stained_glass_pane, tallgrass, double_plant, yellow_flower, red_flower, vine, wheat, reeds, waterlily";
   public MCH_Config(String minecraftPath, String cfgFile) {
      mcPath = minecraftPath;
       configFilePath = mcPath + cfgFile;

      DebugLog = false;
      configVer = "0.0.0";

      bulletBreakableBlocks = new ArrayList<Block>();
      carBreakableBlocks = new ArrayList<Block>();
      carNoBreakableBlocks = new ArrayList<Block>();
      carBreakableMaterials = new ArrayList<Material>();
      tankBreakableBlocks = new ArrayList<Block>();
      tankNoBreakableBlocks = new ArrayList<Block>();
      tankBreakableMaterials = new ArrayList<Material>();

      KeyUp = new MCH_ConfigPrm("KeyUp", 17);
      KeyDown = new MCH_ConfigPrm("KeyDown", 31);
      KeyRight = new MCH_ConfigPrm("KeyRight", 32);
      KeyLeft = new MCH_ConfigPrm("KeyLeft", 30);
      KeySwitchMode = new MCH_ConfigPrm("KeySwitchGunner", 35);
      KeySwitchHovering = new MCH_ConfigPrm("KeySwitchHovering", 57);
      KeyAttack = new MCH_ConfigPrm("KeyAttack", -100);
      KeyUseWeapon = new MCH_ConfigPrm("KeyUseWeapon", -99);
      KeySwitchWeapon1 = new MCH_ConfigPrm("KeySwitchWeapon1", -98);
      KeySwitchWeapon2 = new MCH_ConfigPrm("KeySwitchWeapon2", 34);
      KeySwWeaponMode = new MCH_ConfigPrm("KeySwitchWeaponMode", 45);
      KeyZoom = new MCH_ConfigPrm("KeyZoom", 44);
      KeyCameraMode = new MCH_ConfigPrm("KeyCameraMode", 46);
      KeyUnmount = new MCH_ConfigPrm("KeyUnmountMob", 21);
      KeyFlare = new MCH_ConfigPrm("KeyFlare", 47);
      KeyExtra = new MCH_ConfigPrm("KeyExtra", 33);
      KeyCameraDistUp = new MCH_ConfigPrm("KeyCameraDistanceUp", 201);
      KeyCameraDistDown = new MCH_ConfigPrm("KeyCameraDistanceDown", 209);
      KeyFreeLook = new MCH_ConfigPrm("KeyFreeLook", 29);
      KeyGUI = new MCH_ConfigPrm("KeyGUI", 19);
      KeyGearUpDown = new MCH_ConfigPrm("KeyGearUpDown", 48);
      KeyPutToRack = new MCH_ConfigPrm("KeyPutToRack", 36);
      KeyDownFromRack = new MCH_ConfigPrm("KeyDownFromRack", 22);
      KeyScoreboard = new MCH_ConfigPrm("KeyScoreboard", 38);
      KeyMultiplayManager = new MCH_ConfigPrm("KeyMultiplayManager", 50);
       (new MCH_ConfigPrm[23])[0] = KeyUp;  (new MCH_ConfigPrm[23])[1] = KeyDown;  (new MCH_ConfigPrm[23])[2] = KeyRight;  (new MCH_ConfigPrm[23])[3] = KeyLeft;  (new MCH_ConfigPrm[23])[4] = KeySwitchMode;  (new MCH_ConfigPrm[23])[5] = KeySwitchHovering;  (new MCH_ConfigPrm[23])[6] = KeySwitchWeapon1;  (new MCH_ConfigPrm[23])[7] = KeySwitchWeapon2;  (new MCH_ConfigPrm[23])[8] = KeySwWeaponMode;  (new MCH_ConfigPrm[23])[9] = KeyZoom;  (new MCH_ConfigPrm[23])[10] = KeyCameraMode;  (new MCH_ConfigPrm[23])[11] = KeyUnmount;  (new MCH_ConfigPrm[23])[12] = KeyFlare;  (new MCH_ConfigPrm[23])[13] = KeyExtra;  (new MCH_ConfigPrm[23])[14] = KeyCameraDistUp;  (new MCH_ConfigPrm[23])[15] = KeyCameraDistDown;  (new MCH_ConfigPrm[23])[16] = KeyFreeLook;  (new MCH_ConfigPrm[23])[17] = KeyGUI;  (new MCH_ConfigPrm[23])[18] = KeyGearUpDown;  (new MCH_ConfigPrm[23])[19] = KeyPutToRack;  (new MCH_ConfigPrm[23])[20] = KeyDownFromRack;  (new MCH_ConfigPrm[23])[21] = KeyScoreboard;  KeyConfig = new MCH_ConfigPrm[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, KeyMultiplayManager };      DamageVs = new ArrayList<MCH_ConfigPrm>();
      CommandPermission = new ArrayList<MCH_ConfigPrm>();
      CommandPermissionList = new ArrayList<CommandPermission>();
      IgnoreBulletHitList = new ArrayList<String>();
      IgnoreBulletHitItem = new MCH_ConfigPrm("IgnoreBulletHit", "");

      TestMode = new MCH_ConfigPrm("TestMode", false);
      EnableCommand = new MCH_ConfigPrm("EnableCommand", true);
      PlaceableOnSpongeOnly = new MCH_ConfigPrm("PlaceableOnSpongeOnly", false);
      HideKeybind = new MCH_ConfigPrm("HideKeybind", false);
      ItemDamage = new MCH_ConfigPrm("ItemDamage", true);
      ItemFuel = new MCH_ConfigPrm("ItemFuel", true);
      AutoRepairHP = new MCH_ConfigPrm("AutoRepairHP", 0.5D);
      Collision_DestroyBlock = new MCH_ConfigPrm("Collision_DestroyBlock", true);
      Explosion_DestroyBlock = new MCH_ConfigPrm("Explosion_DestroyBlock", true);
      Explosion_FlamingBlock = new MCH_ConfigPrm("Explosion_FlamingBlock", true);
      Collision_Car_BreakableBlock = new MCH_ConfigPrm("Collision_Car_BreakableBlock", "double_plant, glass_pane,stained_glass_pane");

      Collision_Car_NoBreakableBlock = new MCH_ConfigPrm("Collision_Car_NoBreakBlock", "torch");
      Collision_Car_BreakableMaterial = new MCH_ConfigPrm("Collision_Car_BreakableMaterial", "cactus, cake, gourd, leaves, vine, plants");

      Collision_Tank_BreakableBlock = new MCH_ConfigPrm("Collision_Tank_BreakableBlock", "nether_brick_fence");
      Collision_Tank_BreakableBlock.validVer = "1.0.0";
      Collision_Tank_NoBreakableBlock = new MCH_ConfigPrm("Collision_Tank_NoBreakBlock", "torch, glowstone");
      Collision_Tank_BreakableMaterial = new MCH_ConfigPrm("Collision_Tank_BreakableMaterial", "cactus, cake, carpet, circuits, glass, gourd, leaves, vine, wood, plants");

      Collision_EntityDamage = new MCH_ConfigPrm("Collision_EntityDamage", true);
      Collision_EntityTankDamage = new MCH_ConfigPrm("Collision_EntityTankDamage", false);
      LWeaponAutoFire = new MCH_ConfigPrm("LWeaponAutoFire", false);
      DismountAll = new MCH_ConfigPrm("DismountAll", false);
      MountMinecartHeli = new MCH_ConfigPrm("MountMinecartHeli", true);
      MountMinecartPlane = new MCH_ConfigPrm("MountMinecartPlane", true);
      MountMinecartVehicle = new MCH_ConfigPrm("MountMinecartVehicle", false);
      MountMinecartTank = new MCH_ConfigPrm("MountMinecartTank", true);
      AutoThrottleDownHeli = new MCH_ConfigPrm("AutoThrottleDownHeli", true);
      AutoThrottleDownPlane = new MCH_ConfigPrm("AutoThrottleDownPlane", false);
      AutoThrottleDownTank = new MCH_ConfigPrm("AutoThrottleDownTank", false);
      DisableItemRender = new MCH_ConfigPrm("DisableItemRender", 1);
      DisableItemRender.desc = ";DisableItemRender = 0 ~ 3 (1 = Recommended)";
      RenderDistanceWeight = new MCH_ConfigPrm("RenderDistanceWeight", 10.0D);
      MobRenderDistanceWeight = new MCH_ConfigPrm("MobRenderDistanceWeight", 1.0D);
      CreativeTabIcon = new MCH_ConfigPrm("CreativeTabIconItem", "fuel");
      CreativeTabIconHeli = new MCH_ConfigPrm("CreativeTabIconHeli", "ah-64");
      CreativeTabIconPlane = new MCH_ConfigPrm("CreativeTabIconPlane", "f22a");
      CreativeTabIconTank = new MCH_ConfigPrm("CreativeTabIconTank", "merkava_mk4");
      CreativeTabIconVehicle = new MCH_ConfigPrm("CreativeTabIconVehicle", "mk15");
      DisableShader = new MCH_ConfigPrm("DisableShader", false);
      AliveTimeOfCartridge = new MCH_ConfigPrm("AliveTimeOfCartridge", 200);
      InfinityAmmo = new MCH_ConfigPrm("InfinityAmmo", false);
      InfinityFuel = new MCH_ConfigPrm("InfinityFuel", false);
      HitMarkColor = new MCH_ConfigPrm("HitMarkColor", "255, 255, 0, 0");
      HitMarkColor.desc = ";HitMarkColor = Alpha, Red, Green, Blue";
      SmoothShading = new MCH_ConfigPrm("SmoothShading", true);
      BulletBreakableBlock = new MCH_ConfigPrm("BulletBreakableBlocks", "glass_pane, stained_glass_pane, tallgrass, double_plant, yellow_flower, red_flower, vine, wheat, reeds, waterlily");
      BulletBreakableBlock.validVer = "0.10.4";
      EnableModEntityRender = new MCH_ConfigPrm("EnableModEntityRender", true);
      DisableRenderLivingSpecials = new MCH_ConfigPrm("DisableRenderLivingSpecials", true);
      PreventingBroken = new MCH_ConfigPrm("PreventingBroken", false);
      DropItemInCreativeMode = new MCH_ConfigPrm("DropItemInCreativeMode", false);
      BreakableOnlyPickaxe = new MCH_ConfigPrm("BreakableOnlyPickaxe", false);
      InvertMouse = new MCH_ConfigPrm("InvertMouse", false);
      MouseSensitivity = new MCH_ConfigPrm("MouseSensitivity", 10.0D);
      MouseControlStickModeHeli = new MCH_ConfigPrm("MouseControlStickModeHeli", false);
      MouseControlStickModePlane = new MCH_ConfigPrm("MouseControlStickModePlane", false);
      MouseControlFlightSimMode = new MCH_ConfigPrm("MouseControlFlightSimMode", false);
      MouseControlFlightSimMode.desc = ";MouseControlFlightSimMode = true ( Yaw:key, Roll=mouse )";
      SwitchWeaponWithMouseWheel = new MCH_ConfigPrm("SwitchWeaponWithMouseWheel", true);
      AllHeliSpeed = new MCH_ConfigPrm("AllHeliSpeed", 1.0D);
      AllPlaneSpeed = new MCH_ConfigPrm("AllPlaneSpeed", 1.0D);
      AllTankSpeed = new MCH_ConfigPrm("AllTankSpeed", 1.0D);
      HurtResistantTime = new MCH_ConfigPrm("HurtResistantTime", 0.0D);
      DisplayHUDThirdPerson = new MCH_ConfigPrm("DisplayHUDThirdPerson", false);
      DisableCameraDistChange = new MCH_ConfigPrm("DisableThirdPersonCameraDistChange", false);
      EnableReplaceTextureManager = new MCH_ConfigPrm("EnableReplaceTextureManager", true);
      DisplayEntityMarker = new MCH_ConfigPrm("DisplayEntityMarker", true);
      DisplayMarkThroughWall = new MCH_ConfigPrm("DisplayMarkThroughWall", true);
      EntityMarkerSize = new MCH_ConfigPrm("EntityMarkerSize", 10.0D);
      BlockMarkerSize = new MCH_ConfigPrm("BlockMarkerSize", 10.0D);
      ReplaceRenderViewEntity = new MCH_ConfigPrm("ReplaceRenderViewEntity", true);
      StingerLockRange = new MCH_ConfigPrm("StingerLockRange", 320.0D);
      StingerLockRange.validVer = "1.0.0";
      DefaultExplosionParticle = new MCH_ConfigPrm("DefaultExplosionParticle", false);
      RangeFinderSpotDist = new MCH_ConfigPrm("RangeFinderSpotDist", 400);
      RangeFinderSpotTime = new MCH_ConfigPrm("RangeFinderSpotTime", 15);
      RangeFinderConsume = new MCH_ConfigPrm("RangeFinderConsume", true);
      EnablePutRackInFlying = new MCH_ConfigPrm("EnablePutRackInFlying", true);
      EnableDebugBoundingBox = new MCH_ConfigPrm("EnableDebugBoundingBox", true);

      hitMarkColorAlpha = 1.0F;
      hitMarkColorRGB = 16711680;

      ItemRecipe_Fuel = new MCH_ConfigPrm("ItemRecipe_Fuel", "\"ICI\", \"III\", I, iron_ingot, C, coal");
      ItemRecipe_GLTD = new MCH_ConfigPrm("ItemRecipe_GLTD", "\" B \", \"IDI\", \"IRI\", B, iron_block, I, iron_ingot, D, diamond, R, redstone");
      ItemRecipe_Chain = new MCH_ConfigPrm("ItemRecipe_Chain", "\"I I\", \"III\", \"I I\", I, iron_ingot");
      ItemRecipe_Parachute = new MCH_ConfigPrm("ItemRecipe_Parachute", "\"WWW\", \"S S\", \" W \", W, wool, S, string");
      ItemRecipe_Container = new MCH_ConfigPrm("ItemRecipe_Container", "\"CCI\", C, chest, I, iron_ingot");
      ItemRecipe_UavStation = new MCH_ConfigPrm[] { new MCH_ConfigPrm("ItemRecipe_UavStation", "\"III\", \"IDI\", \"IRI\", I, iron_ingot, D, diamond, R, redstone_block"), new MCH_ConfigPrm("ItemRecipe_UavStation2", "\"IDI\", \"IRI\", I, iron_ingot, D, diamond, R, redstone") };      ItemRecipe_DraftingTable = new MCH_ConfigPrm("ItemRecipe_DraftingTable", "\"R  \", \"PCP\", \"F F\", R, redstone, C, crafting_table, P, planks, F, fence");
      ItemRecipe_Wrench = new MCH_ConfigPrm("ItemRecipe_Wrench", "\" I \", \" II\", \"I  \", I, iron_ingot");
      ItemRecipe_RangeFinder = new MCH_ConfigPrm("ItemRecipe_RangeFinder", "\"III\", \"RGR\", \"III\", I, iron_ingot, G, glass, R, redstone");
      ItemRecipe_Stinger = new MCH_ConfigPrm("ItemRecipe_Stinger", "\"G  \", \"III\", \"RI \", G, glass, I, iron_ingot, R, redstone");
      ItemRecipe_StingerMissile = new MCH_ConfigPrm("ItemRecipe_StingerMissile", "\"R  \", \" I \", \"  G\", G, gunpowder, I, iron_ingot, R, redstone");
      ItemRecipe_Javelin = new MCH_ConfigPrm("ItemRecipe_Javelin", "\"III\", \"GR \", G, glass, I, iron_ingot, R, redstone");
      ItemRecipe_JavelinMissile = new MCH_ConfigPrm("ItemRecipe_JavelinMissile", "\" R \", \" I \", \" G \", G, gunpowder, I, iron_ingot, R, redstone");

      ItemID_GLTD = new MCH_ConfigPrm("ItemID_GLTD", 28799);
      ItemID_Chain = new MCH_ConfigPrm("ItemID_Chain", 28798);
      ItemID_Parachute = new MCH_ConfigPrm("ItemID_Parachute", 28797);
      ItemID_Container = new MCH_ConfigPrm("ItemID_Container", 28796);
      ItemID_UavStation = new MCH_ConfigPrm[] { new MCH_ConfigPrm("ItemID_UavStation", 28795), new MCH_ConfigPrm("ItemID_UavStation2", 28790) };      ItemID_InvisibleItem = new MCH_ConfigPrm("ItemID_Internal", 28794);
      ItemID_Fuel = new MCH_ConfigPrm("ItemID_Fuel", 28793);
      ItemID_DraftingTable = new MCH_ConfigPrm("ItemID_DraftingTable", 28792);
      ItemID_Wrench = new MCH_ConfigPrm("ItemID_Wrench", 28791);
      ItemID_RangeFinder = new MCH_ConfigPrm("ItemID_RangeFinder", 28789);
      ItemID_Stinger = new MCH_ConfigPrm("ItemID_Stinger", 28900);
      ItemID_StingerMissile = new MCH_ConfigPrm("ItemID_StingerMissile", 28901);

      BlockID_DraftingTableOFF = new MCH_ConfigPrm("BlockID_DraftingTable", 3450);
      BlockID_DraftingTableON = new MCH_ConfigPrm("BlockID_DraftingTableON", 3451);

       (new MCH_ConfigPrm[86])[0] = TestMode;  (new MCH_ConfigPrm[86])[1] = EnableCommand; (new MCH_ConfigPrm[86])[2] = null;  (new MCH_ConfigPrm[86])[3] = PlaceableOnSpongeOnly;  (new MCH_ConfigPrm[86])[4] = ItemDamage;  (new MCH_ConfigPrm[86])[5] = ItemFuel;  (new MCH_ConfigPrm[86])[6] = AutoRepairHP;  (new MCH_ConfigPrm[86])[7] = Explosion_DestroyBlock;  (new MCH_ConfigPrm[86])[8] = Explosion_FlamingBlock;  (new MCH_ConfigPrm[86])[9] = BulletBreakableBlock;  (new MCH_ConfigPrm[86])[10] = Collision_DestroyBlock;  (new MCH_ConfigPrm[86])[11] = Collision_Car_BreakableBlock;  (new MCH_ConfigPrm[86])[12] = Collision_Car_BreakableMaterial;  (new MCH_ConfigPrm[86])[13] = Collision_Tank_BreakableBlock;  (new MCH_ConfigPrm[86])[14] = Collision_Tank_BreakableMaterial;  (new MCH_ConfigPrm[86])[15] = Collision_EntityDamage;  (new MCH_ConfigPrm[86])[16] = Collision_EntityTankDamage;  (new MCH_ConfigPrm[86])[17] = InfinityAmmo;  (new MCH_ConfigPrm[86])[18] = InfinityFuel;  (new MCH_ConfigPrm[86])[19] = DismountAll;  (new MCH_ConfigPrm[86])[20] = MountMinecartHeli;  (new MCH_ConfigPrm[86])[21] = MountMinecartPlane;  (new MCH_ConfigPrm[86])[22] = MountMinecartVehicle;  (new MCH_ConfigPrm[86])[23] = MountMinecartTank;  (new MCH_ConfigPrm[86])[24] = PreventingBroken;  (new MCH_ConfigPrm[86])[25] = DropItemInCreativeMode;  (new MCH_ConfigPrm[86])[26] = BreakableOnlyPickaxe;  (new MCH_ConfigPrm[86])[27] = AllHeliSpeed;  (new MCH_ConfigPrm[86])[28] = AllPlaneSpeed;  (new MCH_ConfigPrm[86])[29] = AllTankSpeed;  (new MCH_ConfigPrm[86])[30] = HurtResistantTime;  (new MCH_ConfigPrm[86])[31] = StingerLockRange;  (new MCH_ConfigPrm[86])[32] = RangeFinderSpotDist;  (new MCH_ConfigPrm[86])[33] = RangeFinderSpotTime;  (new MCH_ConfigPrm[86])[34] = RangeFinderConsume;  (new MCH_ConfigPrm[86])[35] = EnablePutRackInFlying;  (new MCH_ConfigPrm[86])[36] = EnableDebugBoundingBox; (new MCH_ConfigPrm[86])[37] = null;  (new MCH_ConfigPrm[86])[38] = InvertMouse;  (new MCH_ConfigPrm[86])[39] = MouseSensitivity;  (new MCH_ConfigPrm[86])[40] = MouseControlStickModeHeli;  (new MCH_ConfigPrm[86])[41] = MouseControlStickModePlane;  (new MCH_ConfigPrm[86])[42] = MouseControlFlightSimMode;  (new MCH_ConfigPrm[86])[43] = AutoThrottleDownHeli;  (new MCH_ConfigPrm[86])[44] = AutoThrottleDownPlane;  (new MCH_ConfigPrm[86])[45] = AutoThrottleDownTank;  (new MCH_ConfigPrm[86])[46] = SwitchWeaponWithMouseWheel;  (new MCH_ConfigPrm[86])[47] = LWeaponAutoFire;  (new MCH_ConfigPrm[86])[48] = DisableItemRender;  (new MCH_ConfigPrm[86])[49] = HideKeybind;  (new MCH_ConfigPrm[86])[50] = RenderDistanceWeight;  (new MCH_ConfigPrm[86])[51] = MobRenderDistanceWeight;  (new MCH_ConfigPrm[86])[52] = CreativeTabIcon;  (new MCH_ConfigPrm[86])[53] = CreativeTabIconHeli;  (new MCH_ConfigPrm[86])[54] = CreativeTabIconPlane;  (new MCH_ConfigPrm[86])[55] = CreativeTabIconTank;  (new MCH_ConfigPrm[86])[56] = CreativeTabIconVehicle;  (new MCH_ConfigPrm[86])[57] = DisableShader;  (new MCH_ConfigPrm[86])[58] = DefaultExplosionParticle;  (new MCH_ConfigPrm[86])[59] = AliveTimeOfCartridge;  (new MCH_ConfigPrm[86])[60] = HitMarkColor;  (new MCH_ConfigPrm[86])[61] = SmoothShading;  (new MCH_ConfigPrm[86])[62] = EnableModEntityRender;  (new MCH_ConfigPrm[86])[63] = DisableRenderLivingSpecials;  (new MCH_ConfigPrm[86])[64] = DisplayHUDThirdPerson;  (new MCH_ConfigPrm[86])[65] = DisableCameraDistChange;  (new MCH_ConfigPrm[86])[66] = EnableReplaceTextureManager;  (new MCH_ConfigPrm[86])[67] = DisplayEntityMarker;  (new MCH_ConfigPrm[86])[68] = EntityMarkerSize;  (new MCH_ConfigPrm[86])[69] = BlockMarkerSize;  (new MCH_ConfigPrm[86])[70] = ReplaceRenderViewEntity; (new MCH_ConfigPrm[86])[71] = null;  (new MCH_ConfigPrm[86])[72] = ItemRecipe_Fuel;  (new MCH_ConfigPrm[86])[73] = ItemRecipe_GLTD;  (new MCH_ConfigPrm[86])[74] = ItemRecipe_Chain;  (new MCH_ConfigPrm[86])[75] = ItemRecipe_Parachute;  (new MCH_ConfigPrm[86])[76] = ItemRecipe_Container;  (new MCH_ConfigPrm[86])[77] = ItemRecipe_UavStation[0];  (new MCH_ConfigPrm[86])[78] = ItemRecipe_UavStation[1];  (new MCH_ConfigPrm[86])[79] = ItemRecipe_DraftingTable;  (new MCH_ConfigPrm[86])[80] = ItemRecipe_Wrench;  (new MCH_ConfigPrm[86])[81] = ItemRecipe_RangeFinder;  (new MCH_ConfigPrm[86])[82] = ItemRecipe_Stinger;  (new MCH_ConfigPrm[86])[83] = ItemRecipe_StingerMissile;  (new MCH_ConfigPrm[86])[84] = ItemRecipe_Javelin;  General = new MCH_ConfigPrm[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ItemRecipe_JavelinMissile };

      DamageVsEntity = new DamageFactor(this, "DamageVsEntity");
      DamageVsLiving = new DamageFactor(this, "DamageVsLiving");
      DamageVsPlayer = new DamageFactor(this, "DamageVsPlayer");
      DamageVsMCHeliAircraft = new DamageFactor(this, "DamageVsMCHeliAircraft");
      DamageVsMCHeliTank = new DamageFactor(this, "DamageVsMCHeliTank");
      DamageVsMCHeliVehicle = new DamageFactor(this, "DamageVsMCHeliVehicle");
      DamageVsMCHeliOther = new DamageFactor(this, "DamageVsMCHeliOther");
      DamageAircraftByExternal = new DamageFactor(this, "DamageMCHeliAircraftByExternal");
      DamageTankByExternal = new DamageFactor(this, "DamageMCHeliTankByExternal");
      DamageVehicleByExternal = new DamageFactor(this, "DamageMCHeliVehicleByExternal");
      DamageOtherByExternal = new DamageFactor(this, "DamageMCHeliOtherByExternal");
       (new DamageFactor[11])[0] = DamageVsEntity;  (new DamageFactor[11])[1] = DamageVsLiving;  (new DamageFactor[11])[2] = DamageVsPlayer;  (new DamageFactor[11])[3] = DamageVsMCHeliAircraft;  (new DamageFactor[11])[4] = DamageVsMCHeliTank;  (new DamageFactor[11])[5] = DamageVsMCHeliVehicle;  (new DamageFactor[11])[6] = DamageVsMCHeliOther;  (new DamageFactor[11])[7] = DamageAircraftByExternal;  (new DamageFactor[11])[8] = DamageTankByExternal;  (new DamageFactor[11])[9] = DamageVehicleByExternal;  DamageFactorList = new DamageFactor[] { null, null, null, null, null, null, null, null, null, null, DamageOtherByExternal };
   }


   public void setBlockListFromString(List<Block> list, String str) {
     list.clear();
     String[] s = str.split("\\s*,\\s*");
     for (String blockName : s) {

       Block b = W_Block.getBlockFromName(blockName);
       if (b != null)
       {
         list.add(b);
       }
     }
   }


   public void setMaterialListFromString(List<Material> list, String str) {
     list.clear();
     String[] s = str.split("\\s*,\\s*");
     for (String name : s) {

       Material m = MCH_Lib.getMaterialFromName(name);
       if (m != null)
       {
         list.add(m);
       }
     }
   }   public void correctionParameter() {
      String[] s = HitMarkColor.prmString.split("\\s*,\\s*");
     if (s.length == 4) {

        hitMarkColorAlpha = toInt255(s[0]) / 255.0F;
        hitMarkColorRGB = toInt255(s[1]) << 16 | toInt255(s[2]) << 8 | toInt255(s[3]);
     }

       AllHeliSpeed.prmDouble = MCH_Lib.RNG(AllHeliSpeed.prmDouble, 0.0D, 1000.0D);
       AllPlaneSpeed.prmDouble = MCH_Lib.RNG(AllPlaneSpeed.prmDouble, 0.0D, 1000.0D);
       AllTankSpeed.prmDouble = MCH_Lib.RNG(AllTankSpeed.prmDouble, 0.0D, 1000.0D);

      setBlockListFromString(bulletBreakableBlocks, BulletBreakableBlock.prmString);
      setBlockListFromString(carBreakableBlocks, Collision_Car_BreakableBlock.prmString);
      setBlockListFromString(carNoBreakableBlocks, Collision_Car_NoBreakableBlock.prmString);
      setMaterialListFromString(carBreakableMaterials, Collision_Car_BreakableMaterial.prmString);
      setBlockListFromString(tankBreakableBlocks, Collision_Tank_BreakableBlock.prmString);
      setBlockListFromString(tankNoBreakableBlocks, Collision_Tank_NoBreakableBlock.prmString);
      setMaterialListFromString(tankBreakableMaterials, Collision_Tank_BreakableMaterial.prmString);

      if (EntityMarkerSize.prmDouble < 0.0D) {

        EntityMarkerSize.prmDouble = 0.0D;
     }
      if (BlockMarkerSize.prmDouble < 0.0D) {

        BlockMarkerSize.prmDouble = 0.0D;
     }

     if (HurtResistantTime.prmDouble < 0.0D)
     {
       HurtResistantTime.prmDouble = 0.0D;
     }
     if (HurtResistantTime.prmDouble > 10000.0D)
     {
       HurtResistantTime.prmDouble = 10000.0D;
     }

     if (MobRenderDistanceWeight.prmDouble < 0.1D) {

       MobRenderDistanceWeight.prmDouble = 0.1D;
     }
     else if (MobRenderDistanceWeight.prmDouble > 10.0D) {

       MobRenderDistanceWeight.prmDouble = 10.0D;
     }

     for (MCH_ConfigPrm p : CommandPermission) {

       CommandPermission cpm = new CommandPermission(this, p.prmString);
       if (!cpm.name.isEmpty())
       {
         CommandPermissionList.add(cpm);
       }
     }

      if (IgnoreBulletHitList.size() <= 0) {

        IgnoreBulletHitList.add("flansmod.common.guns.EntityBullet");
        IgnoreBulletHitList.add("flansmod.common.guns.EntityGrenade");
     }

     boolean isNoDamageVsSetting = (DamageVs.size() <= 0);

     for (MCH_ConfigPrm p : DamageVs) {

        for (DamageFactor df : DamageFactorList) {

         if (p.name.equals(df.itemName))
         {
           df.list.add(newDamageEntity(p.prmString));
         }
       }
     }
      for (DamageFactor df : DamageFactorList) {

       if (df.list.size() <= 0) {

         DamageVs.add(new MCH_ConfigPrm(df.itemName, "1.0"));
       }
       else {

         boolean foundCommon = false;
         for (DamageEntity n : df.list) {

           if (n.name.isEmpty()) {

             foundCommon = true;
             break;
           }
         }
         if (!foundCommon)
         {
           DamageVs.add(new MCH_ConfigPrm(df.itemName, "1.0"));
         }
       }
     }

     if (isNoDamageVsSetting) {

       DamageVs.add(new MCH_ConfigPrm("DamageVsEntity", "3.0, flansmod"));
       DamageVs.add(new MCH_ConfigPrm("DamageMCHeliAircraftByExternal", "0.5, flansmod"));
       DamageVs.add(new MCH_ConfigPrm("DamageMCHeliVehicleByExternal", "0.5, flansmod"));
     }
   }

   public DamageEntity newDamageEntity(String s) {
     String[] splt = s.split("\\s*,\\s*");
     if (splt.length == 1)
     {
       return new DamageEntity(this, Double.parseDouble(splt[0]), "");
     }
     if (splt.length == 2)
     {
       return new DamageEntity(this, Double.parseDouble(splt[0]), splt[1]);
     }
     return new DamageEntity(this, 1.0D, "");
   }


   public static float applyDamageByExternal(Entity target, DamageSource ds, float damage) {
     List<DamageEntity> list;
     if (target instanceof mcheli.helicopter.MCH_EntityHeli || target instanceof mcheli.plane.MCP_EntityPlane) {

       list = DamageAircraftByExternal.list;
     }
     else if (target instanceof mcheli.tank.MCH_EntityTank) {

       list = DamageTankByExternal.list;
     }
     else if (target instanceof mcheli.vehicle.MCH_EntityVehicle) {

       list = DamageVehicleByExternal.list;
     }
     else {

       list = DamageOtherByExternal.list;
     }

     Entity attacker = ds.func_76346_g();
     Entity attackerSource = ds.func_76364_f();
     for (DamageEntity de : list) {

       if (de.name.isEmpty() || (attacker != null && attacker.getClass().toString().indexOf(de.name) > 0) || (attackerSource != null && attackerSource.getClass().toString().indexOf(de.name) > 0))
       {


         damage = (float)(damage * de.factor);
       }
     }
     return damage;
   }
   public static float applyDamageVsEntity(Entity target, DamageSource ds, float damage) {
     List<DamageEntity> list;
     if (target == null) return damage;

     String targetName = target.getClass().toString();

     if (target instanceof mcheli.helicopter.MCH_EntityHeli || target instanceof mcheli.plane.MCP_EntityPlane) {

       list = DamageVsMCHeliAircraft.list;
     }
     else if (target instanceof mcheli.tank.MCH_EntityTank) {

       list = DamageVsMCHeliTank.list;
     }
     else if (target instanceof mcheli.vehicle.MCH_EntityVehicle) {

       list = DamageVsMCHeliVehicle.list;
     }
     else if (targetName.indexOf("mcheli.") > 0) {

       list = DamageVsMCHeliOther.list;
     }
     else if (target instanceof net.minecraft.entity.player.EntityPlayer) {

       list = DamageVsPlayer.list;
     }
     else if (target instanceof net.minecraft.entity.EntityLivingBase) {

       list = DamageVsLiving.list;
     }
     else {

       list = DamageVsEntity.list;
     }

     for (DamageEntity de : list) {

       if (de.name.isEmpty() || targetName.indexOf(de.name) > 0)
       {
         damage = (float)(damage * de.factor);
       }
     }

     return damage;
   }


   public static List<Block> getBreakableBlockListFromType(int n) {
     if (n == 2) return tankBreakableBlocks;
     if (n == 1) return carBreakableBlocks;
     return dummyBreakableBlocks;
   }

   public static List<Block> getNoBreakableBlockListFromType(int n) {
     if (n == 2) return tankNoBreakableBlocks;
     if (n == 1) return carNoBreakableBlocks;
     return dummyBreakableBlocks;
   }

   public static List<Material> getBreakableMaterialListFromType(int n) {
     if (n == 2) return tankBreakableMaterials;
     if (n == 1) return carBreakableMaterials;
     return dummyBreakableMaterials;
   }


   public int toInt255(String s) {
     int a = Integer.valueOf(s).intValue();
     return (a < 0) ? 0 : ((a > 255) ? 255 : a);
   }


   public void load() {
     MCH_InputFile file = new MCH_InputFile();
      if (file.open(configFilePath)) {

       String str = file.readLine();
       while (str != null) {

         if (str.trim().equalsIgnoreCase("McHeliOutputDebugLog")) {

            DebugLog = true;
         }
         else {

           readConfigData(str);
         }
         str = file.readLine();
       }

       file.close();

       MCH_Lib.Log("loaded " + file.file.getAbsolutePath(), new Object[0]);
     }
     else {

        MCH_Lib.Log("" + (new File(configFilePath)).getAbsolutePath() + " not found.", new Object[0]);
     }

     correctionParameter();
   }


   private void readConfigData(String str) {
     String[] s = str.split("=");

     if (s.length != 2)
       return;  s[0] = s[0].trim();
     s[1] = s[1].trim();

     if (s[0].equalsIgnoreCase("MOD_Version")) {

        configVer = s[1];

       return;
     }
     if (s[0].equalsIgnoreCase("CommandPermission"))
     {
       CommandPermission.add(new MCH_ConfigPrm("CommandPermission", s[1]));
     }

      for (DamageFactor item : DamageFactorList) {

       if (item.itemName.equalsIgnoreCase(s[0])) {

          DamageVs.add(new MCH_ConfigPrm(item.itemName, s[1]));
       }
     }

      if (IgnoreBulletHitItem.compare(s[0])) {

        IgnoreBulletHitList.add(s[1]);
     }

      for (MCH_ConfigPrm p : KeyConfig) {

        if (p != null && p.compare(s[0]) && p.isValidVer(configVer)) {

         p.setPrm(s[1]);
         return;
       }
     }
      for (MCH_ConfigPrm p : General) {

        if (p != null && p.compare(s[0]) && p.isValidVer(configVer)) {

         p.setPrm(s[1]);
         return;
       }
     }
   }


   public void write() {
     MCH_OutputFile file = new MCH_OutputFile();
      if (file.open(configFilePath)) {

       writeConfigData(file.pw);
       file.close();
       MCH_Lib.Log("update " + file.file.getAbsolutePath(), new Object[0]);
     }
     else {

        MCH_Lib.Log("" + (new File(configFilePath)).getAbsolutePath() + " cannot open.", new Object[0]);
     }
   }   private void writeConfigData(PrintWriter pw) {
     pw.println("[General]");
     pw.println("MOD_Name = mcheli");
     pw.println("MOD_Version = " + MCH_MOD.VER);
     pw.println("MOD_MC_Version = 1.7.10");
     pw.println();

      if (DebugLog) {

       pw.println("McHeliOutputDebugLog");
       pw.println();
     }

      for (MCH_ConfigPrm p : General) {

       if (p != null) {

         if (!p.desc.isEmpty()) pw.println(p.desc);
         pw.println(p.name + " = " + p);
       }
       else {

         pw.println("");
       }
     }
     pw.println();

      for (MCH_ConfigPrm p : DamageVs)
     {
       pw.println(p.name + " = " + p);
     }
     pw.println();

      for (String s : IgnoreBulletHitList) {

        pw.println(IgnoreBulletHitItem.name + " = " + s);
     }
     pw.println();

     pw.println(";CommandPermission = commandName(eg, modlist, status, fill...):playerName1, playerName2, playerName3...");
      if (CommandPermission.size() == 0) {

       pw.println(";CommandPermission = modlist :example1, example2");
       pw.println(";CommandPermission = status :  example2");
     }
      for (MCH_ConfigPrm p : CommandPermission)
     {
       pw.println(p.name + " = " + p);
     }
     pw.println();

     pw.println();
     pw.println("[Key config]");
     pw.println("http://minecraft.gamepedia.com/Key_codes");
     pw.println();

      for (MCH_ConfigPrm p : KeyConfig)
     {
       pw.println(p.name + " = " + p);
     }
   }
 }
