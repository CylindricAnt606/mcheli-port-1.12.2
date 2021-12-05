/*     */ package mcheli.mcheli;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import mcheli.MCH_ClientCommonTickHandler;
/*     */ import mcheli.MCH_CommonProxy;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_InvisibleItemRender;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.MCH_RenderNull;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntityHide;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.aircraft.MCH_SoundUpdater;
/*     */ import mcheli.block.MCH_DraftingTableRenderer;
/*     */ import mcheli.block.MCH_DraftingTableTileEntity;
/*     */ import mcheli.chain.MCH_EntityChain;
/*     */ import mcheli.command.MCH_GuiTitle;
/*     */ import mcheli.container.MCH_EntityContainer;
/*     */ import mcheli.container.MCH_RenderContainer;
/*     */ import mcheli.debug.MCH_RenderTest;
/*     */ import mcheli.flare.MCH_RenderFlare;
/*     */ import mcheli.gltd.MCH_ItemGLTDRender;
/*     */ import mcheli.gltd.MCH_RenderGLTD;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.helicopter.MCH_RenderHeli;
/*     */ import mcheli.hud.MCH_HudManager;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponRender;
/*     */ import mcheli.multiplay.MCH_MultiplayClient;
/*     */ import mcheli.parachute.MCH_EntityParachute;
/*     */ import mcheli.parachute.MCH_RenderParachute;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.plane.MCP_RenderPlane;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_RenderTank;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.throwable.MCH_EntityThrowable;
/*     */ import mcheli.throwable.MCH_RenderThrowable;
/*     */ import mcheli.throwable.MCH_ThrowableInfo;
/*     */ import mcheli.tool.MCH_ItemRenderWrench;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.uav.MCH_RenderUavStation;
/*     */ import mcheli.vehicle.MCH_EntityVehicle;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityA10;
/*     */ import mcheli.weapon.MCH_EntityAAMissile;
/*     */ import mcheli.weapon.MCH_EntityASMissile;
/*     */ import mcheli.weapon.MCH_EntityATMissile;
/*     */ import mcheli.weapon.MCH_EntityBullet;
/*     */ import mcheli.weapon.MCH_EntityMarkerRocket;
/*     */ import mcheli.weapon.MCH_EntityTorpedo;
/*     */ import mcheli.weapon.MCH_EntityTvMissile;
/*     */ import mcheli.weapon.MCH_RenderA10;
/*     */ import mcheli.weapon.MCH_RenderASMissile;
/*     */ import mcheli.weapon.MCH_RenderBomb;
/*     */ import mcheli.weapon.MCH_RenderBullet;
/*     */ import mcheli.weapon.MCH_RenderNone;
/*     */ import mcheli.weapon.MCH_RenderTvMissile;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_MinecraftForgeClient;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import mcheli.wrapper.W_TickHandler;
/*     */ import mcheli.wrapper.W_TickRegistry;
/*     */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class MCH_ClientProxy extends MCH_CommonProxy {
/*  95 */   public String lastLoadHUDPath = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDataDir() {
/* 100 */     return (Minecraft.func_71410_x()).field_71412_D.getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRenderer() {
/* 107 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntitySeat.class, (Render)new MCH_RenderTest(0.0F, 0.0F, 0.0F, "seat"));
/* 108 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityHeli.class, (Render)new MCH_RenderHeli());
/* 109 */     RenderingRegistry.registerEntityRenderingHandler(MCP_EntityPlane.class, (Render)new MCP_RenderPlane());
/* 110 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTank.class, (Render)new MCH_RenderTank());
/* 111 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityGLTD.class, (Render)new MCH_RenderGLTD());
/* 112 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityChain.class, (Render)new MCH_RenderChain());
/* 113 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityParachute.class, (Render)new MCH_RenderParachute());
/* 114 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityContainer.class, (Render)new MCH_RenderContainer());
/* 115 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityVehicle.class, (Render)new MCH_RenderVehicle());
/* 116 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityUavStation.class, (Render)new MCH_RenderUavStation());
/* 117 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityCartridge.class, (Render)new MCH_RenderCartridge());
/* 118 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityHide.class, (Render)new MCH_RenderNull());
/*     */     
/* 120 */     RenderingRegistry.registerEntityRenderingHandler(MCH_ViewEntityDummy.class, (Render)new MCH_RenderNull());
/*     */     
/* 122 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityRocket.class, (Render)new MCH_RenderBullet());
/* 123 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTvMissile.class, (Render)new MCH_RenderTvMissile());
/* 124 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityBullet.class, (Render)new MCH_RenderBullet());
/* 125 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityA10.class, (Render)new MCH_RenderA10());
/* 126 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityAAMissile.class, (Render)new MCH_RenderAAMissile());
/* 127 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityASMissile.class, (Render)new MCH_RenderASMissile());
/* 128 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityATMissile.class, (Render)new MCH_RenderTvMissile());
/* 129 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTorpedo.class, (Render)new MCH_RenderBullet());
/* 130 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityBomb.class, (Render)new MCH_RenderBomb());
/* 131 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityMarkerRocket.class, (Render)new MCH_RenderBullet());
/* 132 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityDispensedItem.class, (Render)new MCH_RenderNone());
/*     */     
/* 134 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityFlare.class, (Render)new MCH_RenderFlare());
/*     */     
/* 136 */     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityThrowable.class, (Render)new MCH_RenderThrowable());
/*     */     
/* 138 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemJavelin, (IItemRenderer)new MCH_ItemLightWeaponRender());
/* 139 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemStinger, (IItemRenderer)new MCH_ItemLightWeaponRender());
/* 140 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.invisibleItem, (IItemRenderer)new MCH_InvisibleItemRender());
/*     */     
/* 142 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemGLTD, (IItemRenderer)new MCH_ItemGLTDRender());
/* 143 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemWrench, (IItemRenderer)new MCH_ItemRenderWrench());
/* 144 */     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemRangeFinder, (IItemRenderer)new MCH_ItemRenderRangeFinder());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerBlockRenderer() {
/* 151 */     ClientRegistry.bindTileEntitySpecialRenderer(MCH_DraftingTableTileEntity.class, (TileEntitySpecialRenderer)new MCH_DraftingTableRenderer());
/* 152 */     W_MinecraftForgeClient.registerItemRenderer(W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable), (IItemRenderer)new MCH_DraftingTableItemRender());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerModels() {
/* 158 */     MCH_ModelManager.setForceReloadMode(true);
/*     */     
/* 160 */     MCH_RenderAircraft.debugModel = MCH_ModelManager.load("box");
/*     */     
/* 162 */     MCH_ModelManager.load("a-10");
/*     */     
/* 164 */     MCH_RenderGLTD.model = MCH_ModelManager.load("gltd");
/*     */     
/* 166 */     MCH_ModelManager.load("chain");
/*     */     
/* 168 */     MCH_ModelManager.load("container");
/*     */     
/* 170 */     MCH_ModelManager.load("parachute1");
/* 171 */     MCH_ModelManager.load("parachute2");
/*     */     
/* 173 */     MCH_ModelManager.load("lweapons", "fim92");
/* 174 */     MCH_ModelManager.load("lweapons", "fgm148");
/*     */     
/* 176 */     for (String s : MCH_RenderUavStation.MODEL_NAME)
/*     */     {
/* 178 */       MCH_ModelManager.load(s);
/*     */     }
/*     */     
/* 181 */     MCH_ModelManager.load("wrench");
/*     */     
/* 183 */     MCH_ModelManager.load("rangefinder");
/*     */ 
/*     */     
/* 186 */     MCH_HeliInfoManager.getInstance(); for (String key : MCH_HeliInfoManager.map.keySet())
/*     */     {
/* 188 */       registerModelsHeli(key, false);
/*     */     }
/*     */ 
/*     */     
/* 192 */     for (String key : MCP_PlaneInfoManager.map.keySet())
/*     */     {
/* 194 */       registerModelsPlane(key, false);
/*     */     }
/*     */ 
/*     */     
/* 198 */     MCH_TankInfoManager.getInstance(); for (String key : MCH_TankInfoManager.map.keySet())
/*     */     {
/* 200 */       registerModelsTank(key, false);
/*     */     }
/*     */ 
/*     */     
/* 204 */     for (String key : MCH_VehicleInfoManager.map.keySet())
/*     */     {
/* 206 */       registerModelsVehicle(key, false);
/*     */     }
/*     */ 
/*     */     
/* 210 */     registerModels_Bullet();
/*     */     
/* 212 */     MCH_DefaultBulletModels.Bullet = loadBulletModel("bullet");
/* 213 */     MCH_DefaultBulletModels.AAMissile = loadBulletModel("aamissile");
/* 214 */     MCH_DefaultBulletModels.ATMissile = loadBulletModel("asmissile");
/* 215 */     MCH_DefaultBulletModels.ASMissile = loadBulletModel("asmissile");
/* 216 */     MCH_DefaultBulletModels.Bomb = loadBulletModel("bomb");
/* 217 */     MCH_DefaultBulletModels.Rocket = loadBulletModel("rocket");
/* 218 */     MCH_DefaultBulletModels.Torpedo = loadBulletModel("torpedo");
/*     */ 
/*     */     
/* 221 */     for (MCH_ThrowableInfo wi : MCH_ThrowableInfoManager.getValues())
/*     */     {
/* 223 */       wi.model = MCH_ModelManager.load("throwable", wi.name);
/*     */     }
/*     */     
/* 226 */     MCH_ModelManager.load("blocks", "drafting_table");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerModels_Bullet() {
/* 232 */     for (MCH_WeaponInfo wi : MCH_WeaponInfoManager.getValues()) {
/*     */       
/* 234 */       IModelCustom m = null;
/* 235 */       if (!wi.bulletModelName.isEmpty()) {
/*     */         
/* 237 */         m = MCH_ModelManager.load("bullets", wi.bulletModelName);
/* 238 */         if (m != null)
/*     */         {
/* 240 */           wi.bulletModel = new MCH_BulletModel(wi.bulletModelName, m);
/*     */         }
/*     */       } 
/* 243 */       if (!wi.bombletModelName.isEmpty()) {
/*     */         
/* 245 */         m = MCH_ModelManager.load("bullets", wi.bombletModelName);
/* 246 */         if (m != null)
/*     */         {
/* 248 */           wi.bombletModel = new MCH_BulletModel(wi.bombletModelName, m);
/*     */         }
/*     */       } 
/* 251 */       if (wi.cartridge != null && !wi.cartridge.name.isEmpty()) {
/*     */         
/* 253 */         wi.cartridge.model = MCH_ModelManager.load("bullets", wi.cartridge.name);
/* 254 */         if (wi.cartridge.model == null)
/*     */         {
/* 256 */           wi.cartridge = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerModelsHeli(String name, boolean reload) {
/* 264 */     MCH_ModelManager.setForceReloadMode(reload);
/*     */     
/* 266 */     MCH_HeliInfo info = (MCH_HeliInfo)MCH_HeliInfoManager.map.get(name);
/* 267 */     info.model = MCH_ModelManager.load("helicopters", info.name);
/*     */     
/* 269 */     for (MCH_HeliInfo.Rotor rotor : info.rotorList)
/*     */     {
/* 271 */       rotor.model = loadPartModel("helicopters", info.name, info.model, rotor.modelName);
/*     */     }
/*     */     
/* 274 */     registerCommonPart("helicopters", (MCH_AircraftInfo)info);
/*     */     
/* 276 */     MCH_ModelManager.setForceReloadMode(false);
/*     */   }
/*     */   
/*     */   public void registerModelsPlane(String name, boolean reload) {
/* 280 */     MCH_ModelManager.setForceReloadMode(reload);
/*     */     
/* 282 */     MCP_PlaneInfo info = (MCP_PlaneInfo)MCP_PlaneInfoManager.map.get(name);
/* 283 */     info.model = MCH_ModelManager.load("planes", info.name);
/* 284 */     for (MCH_AircraftInfo.DrawnPart n : info.nozzles)
/*     */     {
/* 286 */       n.model = loadPartModel("planes", info.name, info.model, n.modelName);
/*     */     }
/* 288 */     for (MCP_PlaneInfo.Rotor r : info.rotorList) {
/*     */       
/* 290 */       r.model = loadPartModel("planes", info.name, info.model, r.modelName);
/* 291 */       for (MCP_PlaneInfo.Blade b : r.blades)
/*     */       {
/* 293 */         b.model = loadPartModel("planes", info.name, info.model, b.modelName);
/*     */       }
/*     */     } 
/* 296 */     for (MCP_PlaneInfo.Wing w : info.wingList) {
/*     */       
/* 298 */       w.model = loadPartModel("planes", info.name, info.model, w.modelName);
/* 299 */       if (w.pylonList != null)
/*     */       {
/* 301 */         for (MCP_PlaneInfo.Pylon p : w.pylonList)
/*     */         {
/* 303 */           p.model = loadPartModel("planes", info.name, info.model, p.modelName);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 308 */     registerCommonPart("planes", (MCH_AircraftInfo)info);
/*     */     
/* 310 */     MCH_ModelManager.setForceReloadMode(false);
/*     */   }
/*     */   
/*     */   public void registerModelsVehicle(String name, boolean reload) {
/* 314 */     MCH_ModelManager.setForceReloadMode(reload);
/*     */     
/* 316 */     MCH_VehicleInfo info = (MCH_VehicleInfo)MCH_VehicleInfoManager.map.get(name);
/* 317 */     info.model = MCH_ModelManager.load("vehicles", info.name);
/*     */     
/* 319 */     for (MCH_VehicleInfo.VPart vp : info.partList) {
/*     */       
/* 321 */       vp.model = loadPartModel("vehicles", info.name, info.model, vp.modelName);
/* 322 */       if (vp.child != null)
/*     */       {
/* 324 */         registerVCPModels(info, vp);
/*     */       }
/*     */     } 
/* 327 */     registerCommonPart("vehicles", (MCH_AircraftInfo)info);
/*     */     
/* 329 */     MCH_ModelManager.setForceReloadMode(false);
/*     */   }
/*     */   
/*     */   public void registerModelsTank(String name, boolean reload) {
/* 333 */     MCH_ModelManager.setForceReloadMode(reload);
/*     */     
/* 335 */     MCH_TankInfo info = (MCH_TankInfo)MCH_TankInfoManager.map.get(name);
/* 336 */     info.model = MCH_ModelManager.load("tanks", info.name);
/*     */     
/* 338 */     registerCommonPart("tanks", (MCH_AircraftInfo)info);
/*     */     
/* 340 */     MCH_ModelManager.setForceReloadMode(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private MCH_BulletModel loadBulletModel(String name) {
/* 345 */     IModelCustom m = MCH_ModelManager.load("bullets", name);
/* 346 */     return (m != null) ? new MCH_BulletModel(name, m) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   private IModelCustom loadPartModel(String path, String name, IModelCustom body, String part) {
/* 351 */     if (body instanceof W_ModelCustom)
/*     */     {
/* 353 */       if (((W_ModelCustom)body).containsPart("$" + part))
/*     */       {
/* 355 */         return null;
/*     */       }
/*     */     }
/* 358 */     return MCH_ModelManager.load(path, name + "_" + part);
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerCommonPart(String path, MCH_AircraftInfo info) {
/* 363 */     for (MCH_AircraftInfo.Hatch h : info.hatchList)
/*     */     {
/* 365 */       h.model = loadPartModel(path, info.name, info.model, h.modelName);
/*     */     }
/* 367 */     for (MCH_AircraftInfo.Camera c : info.cameraList)
/*     */     {
/* 369 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 371 */     for (MCH_AircraftInfo.Throttle c : info.partThrottle)
/*     */     {
/* 373 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 375 */     for (MCH_AircraftInfo.RotPart c : info.partRotPart)
/*     */     {
/* 377 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 379 */     for (MCH_AircraftInfo.PartWeapon p : info.partWeapon) {
/*     */       
/* 381 */       p.model = loadPartModel(path, info.name, info.model, p.modelName);
/* 382 */       for (MCH_AircraftInfo.PartWeaponChild wc : p.child)
/*     */       {
/* 384 */         wc.model = loadPartModel(path, info.name, info.model, wc.modelName);
/*     */       }
/*     */     } 
/* 387 */     for (MCH_AircraftInfo.Canopy c : info.canopyList)
/*     */     {
/* 389 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 391 */     for (MCH_AircraftInfo.LandingGear landingGear : info.landingGear)
/*     */     {
/* 393 */       ((MCH_AircraftInfo.DrawnPart)landingGear).model = loadPartModel(path, info.name, info.model, ((MCH_AircraftInfo.DrawnPart)landingGear).modelName);
/*     */     }
/* 395 */     for (MCH_AircraftInfo.WeaponBay w : info.partWeaponBay)
/*     */     {
/* 397 */       w.model = loadPartModel(path, info.name, info.model, w.modelName);
/*     */     }
/* 399 */     for (MCH_AircraftInfo.CrawlerTrack c : info.partCrawlerTrack)
/*     */     {
/* 401 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 403 */     for (MCH_AircraftInfo.TrackRoller c : info.partTrackRoller)
/*     */     {
/* 405 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 407 */     for (MCH_AircraftInfo.PartWheel c : info.partWheel)
/*     */     {
/* 409 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/* 411 */     for (MCH_AircraftInfo.PartWheel c : info.partSteeringWheel)
/*     */     {
/* 413 */       c.model = loadPartModel(path, info.name, info.model, c.modelName);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerVCPModels(MCH_VehicleInfo info, MCH_VehicleInfo.VPart vp) {
/* 419 */     for (MCH_VehicleInfo.VPart vcp : vp.child) {
/*     */       
/* 421 */       vcp.model = loadPartModel("vehicles", info.name, info.model, vcp.modelName);
/* 422 */       if (vcp.child != null)
/*     */       {
/* 424 */         registerVCPModels(info, vcp);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerClientTick() {
/* 432 */     Minecraft mc = Minecraft.func_71410_x();
/* 433 */     MCH_ClientCommonTickHandler.instance = new MCH_ClientCommonTickHandler(mc, MCH_MOD.config);
/* 434 */     W_TickRegistry.registerTickHandler((W_TickHandler)MCH_ClientCommonTickHandler.instance, Side.CLIENT);
/*     */   }
/*     */   
/*     */   public boolean isRemote() {
/* 438 */     return true;
/*     */   }
/*     */   public String side() {
/* 441 */     return "Client";
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_SoundUpdater CreateSoundUpdater(MCH_EntityAircraft aircraft) {
/* 446 */     if (aircraft == null || !aircraft.field_70170_p.field_72995_K) return null; 
/* 447 */     return new MCH_SoundUpdater(Minecraft.func_71410_x(), aircraft, (EntityPlayerSP)(Minecraft.func_71410_x()).field_71439_g);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSounds() {
/* 453 */     W_McClient.addSound("alert.ogg");
/* 454 */     W_McClient.addSound("locked.ogg");
/* 455 */     W_McClient.addSound("gltd.ogg");
/* 456 */     W_McClient.addSound("zoom.ogg");
/* 457 */     W_McClient.addSound("ng.ogg");
/* 458 */     W_McClient.addSound("a-10_snd.ogg");
/* 459 */     W_McClient.addSound("gau-8_snd.ogg");
/* 460 */     W_McClient.addSound("hit.ogg");
/* 461 */     W_McClient.addSound("helidmg.ogg");
/* 462 */     W_McClient.addSound("heli.ogg");
/* 463 */     W_McClient.addSound("plane.ogg");
/* 464 */     W_McClient.addSound("plane_cc.ogg");
/* 465 */     W_McClient.addSound("plane_cv.ogg");
/* 466 */     W_McClient.addSound("chain.ogg");
/* 467 */     W_McClient.addSound("chain_ct.ogg");
/* 468 */     W_McClient.addSound("eject_seat.ogg");
/*     */     
/* 470 */     W_McClient.addSound("fim92_snd.ogg");
/* 471 */     W_McClient.addSound("fim92_reload.ogg");
/* 472 */     W_McClient.addSound("lockon.ogg");
/*     */     
/* 474 */     for (MCH_WeaponInfo info : MCH_WeaponInfoManager.getValues())
/*     */     {
/* 476 */       W_McClient.addSound(info.soundFileName + ".ogg");
/*     */     }
/*     */     
/* 479 */     for (MCP_PlaneInfo mCP_PlaneInfo : MCP_PlaneInfoManager.map.values()) {
/*     */       
/* 481 */       if (!((MCH_AircraftInfo)mCP_PlaneInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCP_PlaneInfo).soundMove + ".ogg"); 
/*     */     } 
/* 483 */     for (MCH_HeliInfo mCH_HeliInfo : MCH_HeliInfoManager.map.values()) {
/*     */       
/* 485 */       if (!((MCH_AircraftInfo)mCH_HeliInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_HeliInfo).soundMove + ".ogg"); 
/*     */     } 
/* 487 */     for (MCH_TankInfo mCH_TankInfo : MCH_TankInfoManager.map.values()) {
/*     */       
/* 489 */       if (!((MCH_AircraftInfo)mCH_TankInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_TankInfo).soundMove + ".ogg"); 
/*     */     } 
/* 491 */     for (MCH_VehicleInfo mCH_VehicleInfo : MCH_VehicleInfoManager.map.values()) {
/*     */       
/* 493 */       if (!((MCH_AircraftInfo)mCH_VehicleInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_VehicleInfo).soundMove + ".ogg");
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_Config loadConfig(String fileName) {
/* 500 */     this.lastConfigFileName = fileName;
/*     */     
/* 502 */     MCH_Config config = new MCH_Config((Minecraft.func_71410_x()).field_71412_D.getPath(), "/" + fileName);
/*     */     
/* 504 */     config.load();
/* 505 */     config.write();
/*     */     
/* 507 */     return config;
/*     */   }
/*     */   
/*     */   public MCH_Config reconfig() {
/* 511 */     MCH_Lib.DbgLog(false, "MCH_ClientProxy.reconfig()", new Object[0]);
/* 512 */     MCH_Config config = loadConfig(this.lastConfigFileName);
/* 513 */     MCH_ClientCommonTickHandler.instance.updatekeybind(config);
/* 514 */     return config;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadHUD(String path) {
/* 519 */     this.lastLoadHUDPath = path;
/* 520 */     MCH_HudManager.load(path);
/*     */   }
/*     */   
/*     */   public void reloadHUD() {
/* 524 */     loadHUD(this.lastLoadHUDPath);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getClientPlayer() {
/* 529 */     return (Entity)(Minecraft.func_71410_x()).field_71439_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 536 */     MinecraftForge.EVENT_BUS.register(new MCH_ParticlesUtil());
/* 537 */     MinecraftForge.EVENT_BUS.register(new MCH_ClientEventHook());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreativeDigDelay(int n) {
/* 542 */     W_Reflection.setCreativeDigSpeed(n);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFirstPerson() {
/* 547 */     return ((Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNewRenderType() {
/* 552 */     return RenderingRegistry.getNextAvailableRenderId();
/*     */   }
/*     */   public boolean isSinglePlayer() {
/* 555 */     return Minecraft.func_71410_x().func_71356_B();
/*     */   }
/*     */ 
/*     */   
/*     */   public void readClientModList() {
/*     */     try {
/* 561 */       Minecraft mc = Minecraft.func_71410_x();
/* 562 */       MCH_MultiplayClient.readModList(mc.func_110432_I().func_148255_b());
/*     */     }
/* 564 */     catch (Exception e) {
/*     */       
/* 566 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void printChatMessage(IChatComponent chat, int showTime, int pos) {
/* 572 */     ((MCH_GuiTitle)MCH_ClientCommonTickHandler.instance.gui_Title).setupTitle(chat, showTime, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void hitBullet() {
/* 577 */     MCH_ClientCommonTickHandler.instance.gui_Common.hitBullet();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clientLocked() {
/* 582 */     MCH_ClientCommonTickHandler.isLocked = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ClientProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */