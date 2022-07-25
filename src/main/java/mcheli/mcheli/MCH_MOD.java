/*     */ package mcheli.mcheli;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartedEvent;
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.io.File;
/*     */ import mcheli.MCH_CommonProxy;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_CreativeTabs;
/*     */ import mcheli.MCH_EventHook;
/*     */ import mcheli.MCH_InvisibleItem;
/*     */ import mcheli.MCH_ItemRecipe;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_PacketHandler;
/*     */ import mcheli.aircraft.MCH_EntityHide;
/*     */ import mcheli.aircraft.MCH_EntityHitBox;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_ItemAircraft;
/*     */ import mcheli.aircraft.MCH_ItemFuel;
/*     */ import mcheli.block.MCH_DraftingTableBlock;
/*     */ import mcheli.block.MCH_DraftingTableTileEntity;
/*     */ import mcheli.chain.MCH_EntityChain;
/*     */ import mcheli.chain.MCH_ItemChain;
/*     */ import mcheli.command.MCH_Command;
/*     */ import mcheli.container.MCH_EntityContainer;
/*     */ import mcheli.container.MCH_ItemContainer;
/*     */ import mcheli.gltd.MCH_EntityGLTD;
/*     */ import mcheli.gltd.MCH_ItemGLTD;
/*     */ import mcheli.gui.MCH_GuiCommonHandler;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.helicopter.MCH_ItemHeli;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponBase;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponBullet;
/*     */ import mcheli.parachute.MCH_EntityParachute;
/*     */ import mcheli.parachute.MCH_ItemParachute;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.throwable.MCH_EntityThrowable;
/*     */ import mcheli.throwable.MCH_ItemThrowable;
/*     */ import mcheli.throwable.MCH_ThrowableInfo;
/*     */ import mcheli.throwable.MCH_ThrowableInfoManager;
/*     */ import mcheli.tool.MCH_ItemWrench;
/*     */ import mcheli.tool.rangefinder.MCH_ItemRangeFinder;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.uav.MCH_ItemUavStation;
/*     */ import mcheli.vehicle.MCH_ItemVehicle;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.weapon.MCH_EntityAAMissile;
/*     */ import mcheli.weapon.MCH_EntityASMissile;
/*     */ import mcheli.weapon.MCH_EntityATMissile;
/*     */ import mcheli.weapon.MCH_EntityBullet;
/*     */ import mcheli.weapon.MCH_EntityMarkerRocket;
/*     */ import mcheli.weapon.MCH_EntityTorpedo;
/*     */ import mcheli.weapon.MCH_EntityTvMissile;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.NetworkMod;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_ItemList;
/*     */ import mcheli.wrapper.W_LanguageRegistry;
/*     */ import mcheli.wrapper.W_NetworkRegistry;
/*     */ import mcheli.wrapper.W_PacketHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.CommandHandler;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ @Mod(modid = "mcheli", name = "mcheli", dependencies = "required-after:Forge@[10.13.2.1230,)")
/*     */ @NetworkMod(clientSideRequired = true, serverSideRequired = false)
/*     */ public class MCH_MOD {
/*  85 */   public static String VER = "";
/*     */   
/*     */   public static final String MOD_ID = "mcheli";
/*     */   
/*     */   public static final String DOMAIN = "mcheli";
/*     */   
/*     */   public static final String MCVER = "1.7.10";
/*     */   
/*     */   public static final String MOD_CH = "MCHeli_CH";
/*     */   @Instance("mcheli")
/*     */   public static mcheli.MCH_MOD instance;
/*     */   @SidedProxy(clientSide = "mcheli.MCH_ClientProxy", serverSide = "mcheli.MCH_CommonProxy")
/*     */   public static MCH_CommonProxy proxy;
/*  98 */   public static MCH_PacketHandler packetHandler = new MCH_PacketHandler();
/*     */   
/*     */   public static MCH_Config config;
/*     */   
/*     */   public static String sourcePath;
/*     */   
/*     */   public static MCH_InvisibleItem invisibleItem;
/*     */   
/*     */   public static MCH_ItemGLTD itemGLTD;
/*     */   
/*     */   public static MCH_ItemLightWeaponBullet itemStingerBullet;
/*     */   
/*     */   public static MCH_ItemLightWeaponBase itemStinger;
/*     */   
/*     */   public static MCH_ItemLightWeaponBullet itemJavelinBullet;
/*     */   
/*     */   public static MCH_ItemLightWeaponBase itemJavelin;
/*     */   
/*     */   public static MCH_ItemUavStation[] itemUavStation;
/*     */   
/*     */   public static MCH_ItemParachute itemParachute;
/*     */   public static MCH_ItemContainer itemContainer;
/*     */   public static MCH_ItemChain itemChain;
/*     */   public static MCH_ItemFuel itemFuel;
/*     */   public static MCH_ItemWrench itemWrench;
/*     */   public static MCH_ItemRangeFinder itemRangeFinder;
/*     */   public static MCH_CreativeTabs creativeTabs;
/*     */   public static MCH_CreativeTabs creativeTabsHeli;
/*     */   public static MCH_CreativeTabs creativeTabsPlane;
/*     */   public static MCH_CreativeTabs creativeTabsTank;
/*     */   public static MCH_CreativeTabs creativeTabsVehicle;
/*     */   public static MCH_DraftingTableBlock blockDraftingTable;
/*     */   public static MCH_DraftingTableBlock blockDraftingTableLit;
/*     */   public static Item sampleHelmet;
/*     */   
/*     */   @EventHandler
/*     */   public void PreInit(FMLPreInitializationEvent evt) {
/* 135 */     VER = Loader.instance().activeModContainer().getVersion();
/*     */     
/* 137 */     MCH_Lib.init();
/*     */     
/* 139 */     MCH_Lib.Log("MC Ver:1.7.10 MOD Ver:" + VER + "", new Object[0]);
/* 140 */     MCH_Lib.Log("Start load...", new Object[0]);
/*     */     
/* 142 */     sourcePath = Loader.instance().activeModContainer().getSource().getPath();
/* 143 */     MCH_Lib.Log("SourcePath: " + sourcePath, new Object[0]);
/* 144 */     MCH_Lib.Log("CurrentDirectory:" + (new File(".")).getAbsolutePath(), new Object[0]);
/*     */     
/* 146 */     proxy.init();
/*     */     
/* 148 */     creativeTabs = new MCH_CreativeTabs("MC Heli Item");
/* 149 */     creativeTabsHeli = new MCH_CreativeTabs("MC Heli Helicopters");
/* 150 */     creativeTabsPlane = new MCH_CreativeTabs("MC Heli Planes");
/* 151 */     creativeTabsTank = new MCH_CreativeTabs("MC Heli Tanks");
/* 152 */     creativeTabsVehicle = new MCH_CreativeTabs("MC Heli Vehicles");
/*     */     
/* 154 */     W_ItemList.init();
/*     */ 
/*     */ 
/*     */     
/* 158 */     this; this; config = proxy.loadConfig("config/mcheli.cfg");
/*     */     
/* 160 */     proxy.loadHUD(sourcePath + "/assets/" + "mcheli" + "/hud");
/* 161 */     MCH_WeaponInfoManager.load(sourcePath + "/assets/" + "mcheli" + "/weapons");
/* 162 */     MCH_HeliInfoManager.getInstance().load(sourcePath + "/assets/" + "mcheli" + "/", "helicopters");
/* 163 */     MCP_PlaneInfoManager.getInstance().load(sourcePath + "/assets/" + "mcheli" + "/", "planes");
/* 164 */     MCH_TankInfoManager.getInstance().load(sourcePath + "/assets/" + "mcheli" + "/", "tanks");
/* 165 */     MCH_VehicleInfoManager.getInstance().load(sourcePath + "/assets/" + "mcheli" + "/", "vehicles");
/* 166 */     MCH_ThrowableInfoManager.load(sourcePath + "/assets/" + "mcheli" + "/throwable");
/*     */ 
/*     */ 
/*     */     
/* 170 */     MCH_SoundsJson.update(sourcePath + "/assets/" + "mcheli" + "/");
/*     */ 
/*     */ 
/*     */     
/* 174 */     MCH_Lib.Log("Register item", new Object[0]);
/*     */     
/* 176 */     registerItemRangeFinder();
/* 177 */     registerItemWrench();
/* 178 */     registerItemFuel();
/* 179 */     registerItemGLTD();
/* 180 */     registerItemChain();
/* 181 */     registerItemParachute();
/* 182 */     registerItemContainer();
/* 183 */     registerItemUavStation();
/* 184 */     registerItemInvisible();
/*     */     
/* 186 */     registerItemThrowable();
/*     */     
/* 188 */     registerItemLightWeaponBullet();
/* 189 */     registerItemLightWeapon();
/*     */     
/* 191 */     registerItemAircraft();
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
/* 202 */     blockDraftingTable = new MCH_DraftingTableBlock(MCH_Config.BlockID_DraftingTableOFF.prmInt, false);
/* 203 */     blockDraftingTable.func_149663_c("drafting_table");
/* 204 */     blockDraftingTable.func_149647_a((CreativeTabs)creativeTabs);
/* 205 */     blockDraftingTableLit = new MCH_DraftingTableBlock(MCH_Config.BlockID_DraftingTableON.prmInt, true);
/* 206 */     blockDraftingTableLit.func_149663_c("lit_drafting_table");
/* 207 */     GameRegistry.registerBlock((Block)blockDraftingTable, "drafting_table");
/* 208 */     GameRegistry.registerBlock((Block)blockDraftingTableLit, "lit_drafting_table");
/* 209 */     W_LanguageRegistry.addName(blockDraftingTable, "Drafting Table");
/* 210 */     W_LanguageRegistry.addNameForObject(blockDraftingTable, "ja_JP", "製図台");
/*     */ 
/*     */     
/* 213 */     MCH_Achievement.PreInit();
/*     */ 
/*     */     
/* 216 */     MCH_Lib.Log("Register system", new Object[0]);
/*     */     
/* 218 */     this; W_NetworkRegistry.registerChannel((W_PacketHandler)packetHandler, "MCHeli_CH");
/*     */ 
/*     */     
/* 221 */     MinecraftForge.EVENT_BUS.register(new MCH_EventHook());
/*     */ 
/*     */     
/* 224 */     this; proxy.registerClientTick();
/*     */     
/* 226 */     W_NetworkRegistry.registerGuiHandler(this, (IGuiHandler)new MCH_GuiCommonHandler());
/*     */ 
/*     */     
/* 229 */     MCH_Lib.Log("Register entity", new Object[0]);
/* 230 */     registerEntity();
/*     */ 
/*     */     
/* 233 */     MCH_Lib.Log("Register renderer", new Object[0]);
/* 234 */     this; proxy.registerRenderer();
/*     */ 
/*     */     
/* 237 */     MCH_Lib.Log("Register models", new Object[0]);
/* 238 */     this; proxy.registerModels();
/*     */ 
/*     */     
/* 241 */     MCH_Lib.Log("Register Sounds", new Object[0]);
/* 242 */     this; proxy.registerSounds();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     W_LanguageRegistry.updateLang(sourcePath + "/assets/" + "mcheli" + "/lang/");
/*     */ 
/*     */     
/* 250 */     MCH_Lib.Log("End load", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent evt) {
/* 257 */     GameRegistry.registerTileEntity(MCH_DraftingTableTileEntity.class, "drafting_table");
/* 258 */     proxy.registerBlockRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt) {
/* 264 */     creativeTabs.setFixedIconItem(MCH_Config.CreativeTabIcon.prmString);
/* 265 */     creativeTabsHeli.setFixedIconItem(MCH_Config.CreativeTabIconHeli.prmString);
/* 266 */     creativeTabsPlane.setFixedIconItem(MCH_Config.CreativeTabIconPlane.prmString);
/* 267 */     creativeTabsTank.setFixedIconItem(MCH_Config.CreativeTabIconTank.prmString);
/* 268 */     creativeTabsVehicle.setFixedIconItem(MCH_Config.CreativeTabIconVehicle.prmString);
/* 269 */     MCH_ItemRecipe.registerItemRecipe();
/* 270 */     MCH_WeaponInfoManager.setRoundItems();
/*     */     
/* 272 */     proxy.readClientModList();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onStartServer(FMLServerStartingEvent event) {
/* 278 */     this; proxy.registerServerTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerEntity() {
/* 284 */     EntityRegistry.registerModEntity(MCH_EntitySeat.class, "MCH.E.Seat", 100, this, 200, 10, true);
/*     */     
/* 286 */     EntityRegistry.registerModEntity(MCH_EntityHeli.class, "MCH.E.Heli", 101, this, 200, 10, true);
/*     */     
/* 288 */     EntityRegistry.registerModEntity(MCH_EntityGLTD.class, "MCH.E.GLTD", 102, this, 200, 10, true);
/*     */     
/* 290 */     EntityRegistry.registerModEntity(MCP_EntityPlane.class, "MCH.E.Plane", 103, this, 200, 10, true);
/*     */     
/* 292 */     EntityRegistry.registerModEntity(MCH_EntityChain.class, "MCH.E.Chain", 104, this, 200, 10, true);
/*     */     
/* 294 */     EntityRegistry.registerModEntity(MCH_EntityHitBox.class, "MCH.E.PSeat", 105, this, 200, 10, true);
/*     */     
/* 296 */     EntityRegistry.registerModEntity(MCH_EntityParachute.class, "MCH.E.Parachute", 106, this, 200, 10, true);
/*     */     
/* 298 */     EntityRegistry.registerModEntity(MCH_EntityContainer.class, "MCH.E.Container", 107, this, 200, 10, true);
/*     */     
/* 300 */     EntityRegistry.registerModEntity(MCH_EntityVehicle.class, "MCH.E.Vehicle", 108, this, 200, 10, true);
/*     */     
/* 302 */     EntityRegistry.registerModEntity(MCH_EntityUavStation.class, "MCH.E.UavStation", 109, this, 200, 10, true);
/*     */     
/* 304 */     EntityRegistry.registerModEntity(MCH_EntityHitBox.class, "MCH.E.HitBox", 110, this, 200, 10, true);
/*     */     
/* 306 */     EntityRegistry.registerModEntity(MCH_EntityHide.class, "MCH.E.Hide", 111, this, 200, 10, true);
/*     */     
/* 308 */     EntityRegistry.registerModEntity(MCH_EntityTank.class, "MCH.E.Tank", 112, this, 200, 10, true);
/*     */ 
/*     */     
/* 311 */     EntityRegistry.registerModEntity(MCH_EntityRocket.class, "MCH.E.Rocket", 200, this, 530, 5, true);
/*     */     
/* 313 */     EntityRegistry.registerModEntity(MCH_EntityTvMissile.class, "MCH.E.TvMissle", 201, this, 530, 5, true);
/*     */     
/* 315 */     EntityRegistry.registerModEntity(MCH_EntityBullet.class, "MCH.E.Bullet", 202, this, 530, 5, true);
/*     */     
/* 317 */     EntityRegistry.registerModEntity(MCH_EntityA10.class, "MCH.E.A10", 203, this, 530, 5, true);
/*     */     
/* 319 */     EntityRegistry.registerModEntity(MCH_EntityAAMissile.class, "MCH.E.AAM", 204, this, 530, 5, true);
/*     */     
/* 321 */     EntityRegistry.registerModEntity(MCH_EntityASMissile.class, "MCH.E.ASM", 205, this, 530, 5, true);
/*     */     
/* 323 */     EntityRegistry.registerModEntity(MCH_EntityTorpedo.class, "MCH.E.Torpedo", 206, this, 530, 5, true);
/*     */     
/* 325 */     EntityRegistry.registerModEntity(MCH_EntityATMissile.class, "MCH.E.ATMissle", 207, this, 530, 5, true);
/*     */     
/* 327 */     EntityRegistry.registerModEntity(MCH_EntityBomb.class, "MCH.E.Bomb", 208, this, 530, 5, true);
/*     */     
/* 329 */     EntityRegistry.registerModEntity(MCH_EntityMarkerRocket.class, "MCH.E.MkRocket", 209, this, 530, 5, true);
/*     */     
/* 331 */     EntityRegistry.registerModEntity(MCH_EntityDispensedItem.class, "MCH.E.DispItem", 210, this, 530, 5, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     EntityRegistry.registerModEntity(MCH_EntityFlare.class, "MCH.E.Flare", 300, this, 330, 10, true);
/*     */ 
/*     */     
/* 340 */     EntityRegistry.registerModEntity(MCH_EntityThrowable.class, "MCH.E.Throwable", 400, this, 330, 10, true);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void registerCommand(FMLServerStartedEvent e) {
/* 346 */     CommandHandler handler = (CommandHandler)FMLCommonHandler.instance().getSidedDelegate().getServer().func_71187_D();
/* 347 */     handler.func_71560_a((ICommand)new MCH_Command());
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerItemRangeFinder() {
/* 352 */     String name = "rangefinder";
/* 353 */     MCH_ItemRangeFinder item = new MCH_ItemRangeFinder(MCH_Config.ItemID_RangeFinder.prmInt);
/* 354 */     itemRangeFinder = item;
/* 355 */     registerItem((W_Item)item, "rangefinder", creativeTabs);
/* 356 */     W_LanguageRegistry.addName(item, "Laser Rangefinder");
/* 357 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "レーザー レンジ ファインダー");
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerItemWrench() {
/* 362 */     String name = "wrench";
/* 363 */     MCH_ItemWrench item = new MCH_ItemWrench(MCH_Config.ItemID_Wrench.prmInt, Item.ToolMaterial.IRON);
/* 364 */     itemWrench = item;
/* 365 */     registerItem((W_Item)item, "wrench", creativeTabs);
/* 366 */     W_LanguageRegistry.addName(item, "Wrench");
/* 367 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "レンチ");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemInvisible() {
/* 372 */     String name = "internal";
/* 373 */     MCH_InvisibleItem item = new MCH_InvisibleItem(MCH_Config.ItemID_InvisibleItem.prmInt);
/* 374 */     invisibleItem = item;
/* 375 */     registerItem((W_Item)item, "internal", null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemUavStation() {
/* 380 */     String[] dispName = { "UAV Station", "Portable UAV Controller" };
/*     */ 
/*     */ 
/*     */     
/* 384 */     String[] localName = { "UAVステーション", "携帯UAV制御端末" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 389 */     itemUavStation = new MCH_ItemUavStation[MCH_ItemUavStation.UAV_STATION_KIND_NUM];
/* 390 */     String name = "uav_station";
/* 391 */     for (int i = 0; i < itemUavStation.length; i++) {
/*     */       
/* 393 */       String nn = (i > 0) ? ("" + (i + 1)) : "";
/* 394 */       MCH_ItemUavStation item = new MCH_ItemUavStation((MCH_Config.ItemID_UavStation[i]).prmInt, 1 + i);
/* 395 */       itemUavStation[i] = item;
/* 396 */       registerItem((W_Item)item, "uav_station" + nn, creativeTabs);
/* 397 */       W_LanguageRegistry.addName(item, dispName[i]);
/* 398 */       W_LanguageRegistry.addNameForObject(item, "ja_JP", localName[i]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemParachute() {
/* 404 */     String name = "parachute";
/* 405 */     MCH_ItemParachute item = new MCH_ItemParachute(MCH_Config.ItemID_Parachute.prmInt);
/* 406 */     itemParachute = item;
/* 407 */     registerItem((W_Item)item, "parachute", creativeTabs);
/* 408 */     W_LanguageRegistry.addName(item, "Parachute");
/* 409 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "パラシュート");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemContainer() {
/* 414 */     String name = "container";
/* 415 */     MCH_ItemContainer item = new MCH_ItemContainer(MCH_Config.ItemID_Container.prmInt);
/* 416 */     itemContainer = item;
/* 417 */     registerItem((W_Item)item, "container", creativeTabs);
/* 418 */     W_LanguageRegistry.addName(item, "Container");
/* 419 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "コンテナ");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemLightWeapon() {
/* 424 */     String name = "fim92";
/*     */     
/* 426 */     MCH_ItemLightWeaponBase item = new MCH_ItemLightWeaponBase(MCH_Config.ItemID_Stinger.prmInt, itemStingerBullet);
/* 427 */     itemStinger = item;
/* 428 */     registerItem((W_Item)item, name, creativeTabs);
/* 429 */     W_LanguageRegistry.addName(item, "FIM-92 Stinger");
/*     */     
/* 431 */     name = "fgm148";
/* 432 */     item = new MCH_ItemLightWeaponBase(MCH_Config.ItemID_Stinger.prmInt, itemJavelinBullet);
/* 433 */     itemJavelin = item;
/* 434 */     registerItem((W_Item)item, name, creativeTabs);
/* 435 */     W_LanguageRegistry.addName(item, "FGM-148 Javelin");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemLightWeaponBullet() {
/* 440 */     String name = "fim92_bullet";
/* 441 */     MCH_ItemLightWeaponBullet item = new MCH_ItemLightWeaponBullet(MCH_Config.ItemID_StingerMissile.prmInt);
/* 442 */     itemStingerBullet = item;
/* 443 */     registerItem((W_Item)item, name, creativeTabs);
/* 444 */     W_LanguageRegistry.addName(item, "FIM-92 Stinger missile");
/*     */     
/* 446 */     name = "fgm148_bullet";
/* 447 */     item = new MCH_ItemLightWeaponBullet(MCH_Config.ItemID_StingerMissile.prmInt);
/* 448 */     itemJavelinBullet = item;
/* 449 */     registerItem((W_Item)item, name, creativeTabs);
/* 450 */     W_LanguageRegistry.addName(item, "FGM-148 Javelin missile");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemChain() {
/* 455 */     String name = "chain";
/* 456 */     MCH_ItemChain item = new MCH_ItemChain(MCH_Config.ItemID_Chain.prmInt);
/* 457 */     itemChain = item;
/* 458 */     registerItem((W_Item)item, "chain", creativeTabs);
/*     */     
/* 460 */     W_LanguageRegistry.addName(item, "Chain");
/* 461 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "鎖");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemFuel() {
/* 466 */     String name = "fuel";
/* 467 */     this; MCH_ItemFuel item = new MCH_ItemFuel(MCH_Config.ItemID_Fuel.prmInt);
/* 468 */     itemFuel = item;
/*     */     
/* 470 */     registerItem((W_Item)item, "fuel", creativeTabs);
/*     */     
/* 472 */     W_LanguageRegistry.addName(item, "Fuel");
/* 473 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "燃料");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerItemGLTD() {
/* 478 */     String name = "gltd";
/* 479 */     this; MCH_ItemGLTD item = new MCH_ItemGLTD(MCH_Config.ItemID_GLTD.prmInt);
/* 480 */     itemGLTD = item;
/*     */     
/* 482 */     registerItem((W_Item)item, "gltd", creativeTabs);
/*     */     
/* 484 */     W_LanguageRegistry.addName(item, "GLTD:Target Designator");
/* 485 */     W_LanguageRegistry.addNameForObject(item, "ja_JP", "GLTD:レーザー目標指示装置");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerItem(W_Item item, String name, MCH_CreativeTabs ct) {
/* 490 */     item.func_77655_b("mcheli:" + name);
/* 491 */     item.setTexture(name);
/* 492 */     if (ct != null) {
/*     */       
/* 494 */       item.func_77637_a((CreativeTabs)ct);
/* 495 */       ct.addIconItem((Item)item);
/*     */     } 
/* 497 */     GameRegistry.registerItem((Item)item, name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerItemThrowable() {
/* 503 */     for (String name : MCH_ThrowableInfoManager.getKeySet()) {
/*     */       
/* 505 */       MCH_ThrowableInfo info = MCH_ThrowableInfoManager.get(name);
/* 506 */       info.item = (W_Item)new MCH_ItemThrowable(info.itemID);
/* 507 */       info.item.func_77625_d(info.stackSize);
/*     */       
/* 509 */       registerItem(info.item, name, creativeTabs);
/*     */       
/* 511 */       MCH_ItemThrowable.registerDispenseBehavior((Item)info.item);
/*     */ 
/*     */ 
/*     */       
/* 515 */       info.itemID = W_Item.getIdFromItem((Item)info.item) - 256;
/*     */       
/* 517 */       W_LanguageRegistry.addName(info.item, info.displayName);
/* 518 */       for (String lang : info.displayNameLang.keySet())
/*     */       {
/* 520 */         W_LanguageRegistry.addNameForObject(info.item, lang, (String)info.displayNameLang.get(lang));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerItemAircraft() {
/* 528 */     for (String name : MCH_HeliInfoManager.map.keySet()) {
/*     */       
/* 530 */       MCH_HeliInfo info = (MCH_HeliInfo)MCH_HeliInfoManager.map.get(name);
/* 531 */       info.item = new MCH_ItemHeli(info.itemID);
/* 532 */       info.item.func_77656_e(info.maxHp);
/*     */       
/* 534 */       if (!info.canRide && (info.ammoSupplyRange > 0.0F || info.fuelSupplyRange > 0.0F)) {
/*     */         
/* 536 */         registerItem((W_Item)info.item, name, creativeTabs);
/*     */       }
/*     */       else {
/*     */         
/* 540 */         registerItem((W_Item)info.item, name, creativeTabsHeli);
/*     */       } 
/*     */       
/* 543 */       MCH_ItemAircraft.registerDispenseBehavior((Item)info.item);
/*     */ 
/*     */ 
/*     */       
/* 547 */       info.itemID = W_Item.getIdFromItem((Item)info.item) - 256;
/*     */       
/* 549 */       W_LanguageRegistry.addName(info.item, info.displayName);
/* 550 */       for (String lang : info.displayNameLang.keySet())
/*     */       {
/* 552 */         W_LanguageRegistry.addNameForObject(info.item, lang, (String)info.displayNameLang.get(lang));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 557 */     for (String name : MCP_PlaneInfoManager.map.keySet()) {
/*     */       
/* 559 */       MCP_PlaneInfo info = (MCP_PlaneInfo)MCP_PlaneInfoManager.map.get(name);
/* 560 */       info.item = new MCP_ItemPlane(info.itemID);
/* 561 */       info.item.func_77656_e(info.maxHp);
/*     */       
/* 563 */       if (!info.canRide && (info.ammoSupplyRange > 0.0F || info.fuelSupplyRange > 0.0F)) {
/*     */         
/* 565 */         registerItem((W_Item)info.item, name, creativeTabs);
/*     */       }
/*     */       else {
/*     */         
/* 569 */         registerItem((W_Item)info.item, name, creativeTabsPlane);
/*     */       } 
/*     */       
/* 572 */       MCH_ItemAircraft.registerDispenseBehavior((Item)info.item);
/*     */ 
/*     */ 
/*     */       
/* 576 */       info.itemID = W_Item.getIdFromItem((Item)info.item) - 256;
/*     */       
/* 578 */       W_LanguageRegistry.addName(info.item, info.displayName);
/* 579 */       for (String lang : info.displayNameLang.keySet())
/*     */       {
/* 581 */         W_LanguageRegistry.addNameForObject(info.item, lang, (String)info.displayNameLang.get(lang));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 586 */     for (String name : MCH_TankInfoManager.map.keySet()) {
/*     */       
/* 588 */       MCH_TankInfo info = (MCH_TankInfo)MCH_TankInfoManager.map.get(name);
/* 589 */       info.item = new MCH_ItemTank(info.itemID);
/* 590 */       info.item.func_77656_e(info.maxHp);
/*     */       
/* 592 */       if (!info.canRide && (info.ammoSupplyRange > 0.0F || info.fuelSupplyRange > 0.0F)) {
/*     */         
/* 594 */         registerItem((W_Item)info.item, name, creativeTabs);
/*     */       }
/*     */       else {
/*     */         
/* 598 */         registerItem((W_Item)info.item, name, creativeTabsTank);
/*     */       } 
/*     */       
/* 601 */       MCH_ItemAircraft.registerDispenseBehavior((Item)info.item);
/*     */ 
/*     */ 
/*     */       
/* 605 */       info.itemID = W_Item.getIdFromItem((Item)info.item) - 256;
/*     */       
/* 607 */       W_LanguageRegistry.addName(info.item, info.displayName);
/* 608 */       for (String lang : info.displayNameLang.keySet())
/*     */       {
/* 610 */         W_LanguageRegistry.addNameForObject(info.item, lang, (String)info.displayNameLang.get(lang));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 615 */     for (String name : MCH_VehicleInfoManager.map.keySet()) {
/*     */       
/* 617 */       MCH_VehicleInfo info = (MCH_VehicleInfo)MCH_VehicleInfoManager.map.get(name);
/* 618 */       info.item = new MCH_ItemVehicle(info.itemID);
/* 619 */       info.item.func_77656_e(info.maxHp);
/*     */       
/* 621 */       if (!info.canRide && (info.ammoSupplyRange > 0.0F || info.fuelSupplyRange > 0.0F)) {
/*     */         
/* 623 */         registerItem((W_Item)info.item, name, creativeTabs);
/*     */       }
/*     */       else {
/*     */         
/* 627 */         registerItem((W_Item)info.item, name, creativeTabsVehicle);
/*     */       } 
/*     */       
/* 630 */       MCH_ItemAircraft.registerDispenseBehavior((Item)info.item);
/*     */ 
/*     */ 
/*     */       
/* 634 */       info.itemID = W_Item.getIdFromItem((Item)info.item) - 256;
/*     */       
/* 636 */       W_LanguageRegistry.addName(info.item, info.displayName);
/* 637 */       for (String lang : info.displayNameLang.keySet())
/*     */       {
/* 639 */         W_LanguageRegistry.addNameForObject(info.item, lang, (String)info.displayNameLang.get(lang));
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_MOD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */