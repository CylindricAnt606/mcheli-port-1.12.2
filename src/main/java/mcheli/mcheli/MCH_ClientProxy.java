package mcheli.mcheli;
import mcheli.mcheli.MCH_ClientCommonTickHandler;
import mcheli.mcheli.MCH_CommonProxy;
import mcheli.mcheli.MCH_Config;
import mcheli.mcheli.MCH_InvisibleItemRender;
import mcheli.mcheli.MCH_Lib;
import mcheli.mcheli.MCH_MOD;
import mcheli.mcheli.MCH_ModelManager;
import mcheli.mcheli.MCH_RenderNull;
import mcheli.mcheli.MCH_ViewEntityDummy;
import mcheli.mcheli.aircraft.MCH_AircraftInfo;
import mcheli.mcheli.aircraft.MCH_EntityAircraft;
import mcheli.mcheli.aircraft.MCH_EntityHide;
import mcheli.mcheli.aircraft.MCH_EntitySeat;
import mcheli.mcheli.aircraft.MCH_RenderAircraft;
import mcheli.mcheli.aircraft.MCH_SoundUpdater;
import mcheli.mcheli.block.MCH_DraftingTableRenderer;
import mcheli.mcheli.block.MCH_DraftingTableTileEntity;
import mcheli.mcheli.chain.MCH_EntityChain;
import mcheli.mcheli.command.MCH_GuiTitle;
import mcheli.mcheli.container.MCH_EntityContainer;
import mcheli.mcheli.container.MCH_RenderContainer;
import mcheli.mcheli.debug.MCH_RenderTest;
import mcheli.mcheli.flare.MCH_EntityFlare;
import mcheli.mcheli.flare.MCH_RenderFlare;
import mcheli.mcheli.gltd.MCH_EntityGLTD;
import mcheli.mcheli.gltd.MCH_ItemGLTDRender;
import mcheli.mcheli.gltd.MCH_RenderGLTD;
import mcheli.mcheli.helicopter.MCH_EntityHeli;
import mcheli.mcheli.helicopter.MCH_HeliInfo;
import mcheli.mcheli.helicopter.MCH_HeliInfoManager;
import mcheli.mcheli.helicopter.MCH_RenderHeli;
import mcheli.mcheli.hud.MCH_HudManager;
import mcheli.mcheli.lweapon.MCH_ItemLightWeaponRender;
import mcheli.mcheli.multiplay.MCH_MultiplayClient;
import mcheli.mcheli.parachute.MCH_EntityParachute;
import mcheli.mcheli.parachute.MCH_RenderParachute;
import mcheli.mcheli.particles.MCH_ParticlesUtil;
import mcheli.mcheli.plane.MCP_EntityPlane;
import mcheli.mcheli.plane.MCP_PlaneInfo;
import mcheli.mcheli.plane.MCP_PlaneInfoManager;
import mcheli.mcheli.plane.MCP_RenderPlane;
import mcheli.mcheli.tank.MCH_EntityTank;
import mcheli.mcheli.tank.MCH_RenderTank;
import mcheli.mcheli.tank.MCH_TankInfo;
import mcheli.mcheli.tank.MCH_TankInfoManager;
import mcheli.mcheli.throwable.MCH_EntityThrowable;
import mcheli.mcheli.throwable.MCH_RenderThrowable;
import mcheli.mcheli.throwable.MCH_ThrowableInfo;
import mcheli.mcheli.throwable.MCH_ThrowableInfoManager;
import mcheli.mcheli.tool.MCH_ItemRenderWrench;
import mcheli.mcheli.uav.MCH_EntityUavStation;
import mcheli.mcheli.uav.MCH_RenderUavStation;
import mcheli.mcheli.vehicle.MCH_EntityVehicle;
import mcheli.mcheli.vehicle.MCH_VehicleInfo;
import mcheli.mcheli.vehicle.MCH_VehicleInfoManager;
import mcheli.mcheli.weapon.*;
import mcheli.mcheli.wrapper.W_Item;
import mcheli.mcheli.wrapper.W_McClient;
import mcheli.mcheli.wrapper.W_MinecraftForgeClient;
import mcheli.mcheli.wrapper.W_Reflection;
import mcheli.mcheli.wrapper.W_TickHandler;
import mcheli.mcheli.wrapper.W_TickRegistry;
import mcheli.mcheli.wrapper.modelloader.W_ModelCustom;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

 //TODO: Complet Mess (MAIN REGISTRY)
 public class MCH_ClientProxy extends MCH_CommonProxy {
   public String lastLoadHUDPath = "";



   public String getDataDir() {
     return (Minecraft.func_71410_x()).field_71412_D.getPath();
   }




   public void registerRenderer() {
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntitySeat.class, (Render)new MCH_RenderTest(0.0F, 0.0F, 0.0F, "seat"));
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityHeli.class, (Render)new MCH_RenderHeli());
     RenderingRegistry.registerEntityRenderingHandler(MCP_EntityPlane.class, (Render)new MCP_RenderPlane());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTank.class, (Render)new MCH_RenderTank());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityGLTD.class, (Render)new MCH_RenderGLTD());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityChain.class, (Render)new MCH_RenderChain());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityParachute.class, (Render)new MCH_RenderParachute());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityContainer.class, (Render)new MCH_RenderContainer());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityVehicle.class, (Render)new MCH_RenderVehicle());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityUavStation.class, (Render)new MCH_RenderUavStation());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityCartridge.class, (Render)new MCH_RenderCartridge());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityHide.class, (Render)new MCH_RenderNull());

     RenderingRegistry.registerEntityRenderingHandler(MCH_ViewEntityDummy.class, (Render)new MCH_RenderNull());

     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityRocket.class, (Render)new MCH_RenderBullet());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTvMissile.class, (Render)new MCH_RenderTvMissile());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityBullet.class, (Render)new MCH_RenderBullet());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityA10.class, (Render)new MCH_RenderA10());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityAAMissile.class, (Render)new MCH_RenderAAMissile());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityASMissile.class, (Render)new MCH_RenderASMissile());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityATMissile.class, (Render)new MCH_RenderTvMissile());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityTorpedo.class, (Render)new MCH_RenderBullet());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityBomb.class, (Render)new MCH_RenderBomb());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityMarkerRocket.class, (Render)new MCH_RenderBullet());
     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityDispensedItem.class, (Render)new MCH_RenderNone());

     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityFlare.class, (Render)new MCH_RenderFlare());

     RenderingRegistry.registerEntityRenderingHandler(MCH_EntityThrowable.class, (Render)new MCH_RenderThrowable());

     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemJavelin, (IItemRenderer)new MCH_ItemLightWeaponRender());
     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemStinger, (IItemRenderer)new MCH_ItemLightWeaponRender());
     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.invisibleItem, (IItemRenderer)new MCH_InvisibleItemRender());

     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemGLTD, (IItemRenderer)new MCH_ItemGLTDRender());
     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemWrench, (IItemRenderer)new MCH_ItemRenderWrench());
     W_MinecraftForgeClient.registerItemRenderer((Item)MCH_MOD.itemRangeFinder, (IItemRenderer)new MCH_ItemRenderRangeFinder());
   }




   public void registerBlockRenderer() {
     ClientRegistry.bindTileEntitySpecialRenderer(MCH_DraftingTableTileEntity.class, (TileEntitySpecialRenderer)new MCH_DraftingTableRenderer());
     W_MinecraftForgeClient.registerItemRenderer(W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable), (IItemRenderer)new MCH_DraftingTableItemRender());
   }



   public void registerModels() {
     MCH_ModelManager.setForceReloadMode(true);

     MCH_RenderAircraft.debugModel = MCH_ModelManager.load("box");

     MCH_ModelManager.load("a-10");

     MCH_RenderGLTD.model = MCH_ModelManager.load("gltd");

     MCH_ModelManager.load("chain");

     MCH_ModelManager.load("container");

     MCH_ModelManager.load("parachute1");
     MCH_ModelManager.load("parachute2");

     MCH_ModelManager.load("lweapons", "fim92");
     MCH_ModelManager.load("lweapons", "fgm148");

     for (String s : MCH_RenderUavStation.MODEL_NAME)
     {
       MCH_ModelManager.load(s);
     }

     MCH_ModelManager.load("wrench");

     MCH_ModelManager.load("rangefinder");


     MCH_HeliInfoManager.getInstance(); for (String key : MCH_HeliInfoManager.map.keySet())
     {
       registerModelsHeli(key, false);
     }


     for (String key : MCP_PlaneInfoManager.map.keySet())
     {
       registerModelsPlane(key, false);
     }


     MCH_TankInfoManager.getInstance(); for (String key : MCH_TankInfoManager.map.keySet())
     {
       registerModelsTank(key, false);
     }


     for (String key : MCH_VehicleInfoManager.map.keySet())
     {
       registerModelsVehicle(key, false);
     }


     registerModels_Bullet();

     MCH_DefaultBulletModels.Bullet = loadBulletModel("bullet");
     MCH_DefaultBulletModels.AAMissile = loadBulletModel("aamissile");
     MCH_DefaultBulletModels.ATMissile = loadBulletModel("asmissile");
     MCH_DefaultBulletModels.ASMissile = loadBulletModel("asmissile");
     MCH_DefaultBulletModels.Bomb = loadBulletModel("bomb");
     MCH_DefaultBulletModels.Rocket = loadBulletModel("rocket");
     MCH_DefaultBulletModels.Torpedo = loadBulletModel("torpedo");


     for (MCH_ThrowableInfo wi : MCH_ThrowableInfoManager.getValues())
     {
       wi.model = MCH_ModelManager.load("throwable", wi.name);
     }

     MCH_ModelManager.load("blocks", "drafting_table");
   }



   public static void registerModels_Bullet() {
     for (MCH_WeaponInfo wi : MCH_WeaponInfoManager.getValues()) {

       IModelCustom m = null;
       if (!wi.bulletModelName.isEmpty()) {

         m = MCH_ModelManager.load("bullets", wi.bulletModelName);
         if (m != null)
         {
           wi.bulletModel = new MCH_BulletModel(wi.bulletModelName, m);
         }
       }
       if (!wi.bombletModelName.isEmpty()) {

         m = MCH_ModelManager.load("bullets", wi.bombletModelName);
         if (m != null)
         {
           wi.bombletModel = new MCH_BulletModel(wi.bombletModelName, m);
         }
       }
       if (wi.cartridge != null && !wi.cartridge.name.isEmpty()) {

         wi.cartridge.model = MCH_ModelManager.load("bullets", wi.cartridge.name);
         if (wi.cartridge.model == null)
         {
           wi.cartridge = null;
         }
       }
     }
   }


   public void registerModelsHeli(String name, boolean reload) {
     MCH_ModelManager.setForceReloadMode(reload);

     MCH_HeliInfo info = (MCH_HeliInfo)MCH_HeliInfoManager.map.get(name);
     info.model = MCH_ModelManager.load("helicopters", info.name);

     for (MCH_HeliInfo.Rotor rotor : info.rotorList)
     {
       rotor.model = loadPartModel("helicopters", info.name, info.model, rotor.modelName);
     }

     registerCommonPart("helicopters", (MCH_AircraftInfo)info);

     MCH_ModelManager.setForceReloadMode(false);
   }

   public void registerModelsPlane(String name, boolean reload) {
     MCH_ModelManager.setForceReloadMode(reload);

     MCP_PlaneInfo info = (MCP_PlaneInfo)MCP_PlaneInfoManager.map.get(name);
     info.model = MCH_ModelManager.load("planes", info.name);
     for (MCH_AircraftInfo.DrawnPart n : info.nozzles)
     {
       n.model = loadPartModel("planes", info.name, info.model, n.modelName);
     }
     for (MCP_PlaneInfo.Rotor r : info.rotorList) {

       r.model = loadPartModel("planes", info.name, info.model, r.modelName);
       for (MCP_PlaneInfo.Blade b : r.blades)
       {
         b.model = loadPartModel("planes", info.name, info.model, b.modelName);
       }
     }
     for (MCP_PlaneInfo.Wing w : info.wingList) {

       w.model = loadPartModel("planes", info.name, info.model, w.modelName);
       if (w.pylonList != null)
       {
         for (MCP_PlaneInfo.Pylon p : w.pylonList)
         {
           p.model = loadPartModel("planes", info.name, info.model, p.modelName);
         }
       }
     }

     registerCommonPart("planes", (MCH_AircraftInfo)info);

     MCH_ModelManager.setForceReloadMode(false);
   }

   public void registerModelsVehicle(String name, boolean reload) {
     MCH_ModelManager.setForceReloadMode(reload);

     MCH_VehicleInfo info = (MCH_VehicleInfo)MCH_VehicleInfoManager.map.get(name);
     info.model = MCH_ModelManager.load("vehicles", info.name);

     for (MCH_VehicleInfo.VPart vp : info.partList) {

       vp.model = loadPartModel("vehicles", info.name, info.model, vp.modelName);
       if (vp.child != null)
       {
         registerVCPModels(info, vp);
       }
     }
     registerCommonPart("vehicles", (MCH_AircraftInfo)info);

     MCH_ModelManager.setForceReloadMode(false);
   }

   public void registerModelsTank(String name, boolean reload) {
     MCH_ModelManager.setForceReloadMode(reload);

     MCH_TankInfo info = (MCH_TankInfo)MCH_TankInfoManager.map.get(name);
     info.model = MCH_ModelManager.load("tanks", info.name);

     registerCommonPart("tanks", (MCH_AircraftInfo)info);

     MCH_ModelManager.setForceReloadMode(false);
   }


   private MCH_BulletModel loadBulletModel(String name) {
     IModelCustom m = MCH_ModelManager.load("bullets", name);
     return (m != null) ? new MCH_BulletModel(name, m) : null;
   }


   private IModelCustom loadPartModel(String path, String name, IModelCustom body, String part) {
     if (body instanceof W_ModelCustom)
     {
       if (((W_ModelCustom)body).containsPart("$" + part))
       {
         return null;
       }
     }
     return MCH_ModelManager.load(path, name + "_" + part);
   }


   private void registerCommonPart(String path, MCH_AircraftInfo info) {
     for (MCH_AircraftInfo.Hatch h : info.hatchList)
     {
       h.model = loadPartModel(path, info.name, info.model, h.modelName);
     }
     for (MCH_AircraftInfo.Camera c : info.cameraList)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.Throttle c : info.partThrottle)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.RotPart c : info.partRotPart)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.PartWeapon p : info.partWeapon) {

       p.model = loadPartModel(path, info.name, info.model, p.modelName);
       for (MCH_AircraftInfo.PartWeaponChild wc : p.child)
       {
         wc.model = loadPartModel(path, info.name, info.model, wc.modelName);
       }
     }
     for (MCH_AircraftInfo.Canopy c : info.canopyList)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.LandingGear landingGear : info.landingGear)
     {
       ((MCH_AircraftInfo.DrawnPart)landingGear).model = loadPartModel(path, info.name, info.model, ((MCH_AircraftInfo.DrawnPart)landingGear).modelName);
     }
     for (MCH_AircraftInfo.WeaponBay w : info.partWeaponBay)
     {
       w.model = loadPartModel(path, info.name, info.model, w.modelName);
     }
     for (MCH_AircraftInfo.CrawlerTrack c : info.partCrawlerTrack)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.TrackRoller c : info.partTrackRoller)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.PartWheel c : info.partWheel)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
     for (MCH_AircraftInfo.PartWheel c : info.partSteeringWheel)
     {
       c.model = loadPartModel(path, info.name, info.model, c.modelName);
     }
   }


   private void registerVCPModels(MCH_VehicleInfo info, MCH_VehicleInfo.VPart vp) {
     for (MCH_VehicleInfo.VPart vcp : vp.child) {

       vcp.model = loadPartModel("vehicles", info.name, info.model, vcp.modelName);
       if (vcp.child != null)
       {
         registerVCPModels(info, vcp);
       }
     }
   }



   public void registerClientTick() {
     Minecraft mc = Minecraft.func_71410_x();
     MCH_ClientCommonTickHandler.instance = new MCH_ClientCommonTickHandler(mc, MCH_MOD.config);
     W_TickRegistry.registerTickHandler((W_TickHandler)MCH_ClientCommonTickHandler.instance, Side.CLIENT);
   }

   public boolean isRemote() {
     return true;
   }
   public String side() {
     return "Client";
   }


   public MCH_SoundUpdater CreateSoundUpdater(MCH_EntityAircraft aircraft) {
     if (aircraft == null || !aircraft.field_70170_p.field_72995_K) return null;
     return new MCH_SoundUpdater(Minecraft.func_71410_x(), aircraft, (EntityPlayerSP)(Minecraft.func_71410_x()).field_71439_g);
   }



   public void registerSounds() {
     W_McClient.addSound("alert.ogg");
     W_McClient.addSound("locked.ogg");
     W_McClient.addSound("gltd.ogg");
     W_McClient.addSound("zoom.ogg");
     W_McClient.addSound("ng.ogg");
     W_McClient.addSound("a-10_snd.ogg");
     W_McClient.addSound("gau-8_snd.ogg");
     W_McClient.addSound("hit.ogg");
     W_McClient.addSound("helidmg.ogg");
     W_McClient.addSound("heli.ogg");
     W_McClient.addSound("plane.ogg");
     W_McClient.addSound("plane_cc.ogg");
     W_McClient.addSound("plane_cv.ogg");
     W_McClient.addSound("chain.ogg");
     W_McClient.addSound("chain_ct.ogg");
     W_McClient.addSound("eject_seat.ogg");

     W_McClient.addSound("fim92_snd.ogg");
     W_McClient.addSound("fim92_reload.ogg");
     W_McClient.addSound("lockon.ogg");

     for (MCH_WeaponInfo info : MCH_WeaponInfoManager.getValues())
     {
       W_McClient.addSound(info.soundFileName + ".ogg");
     }

     for (MCP_PlaneInfo mCP_PlaneInfo : MCP_PlaneInfoManager.map.values()) {

       if (!((MCH_AircraftInfo)mCP_PlaneInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCP_PlaneInfo).soundMove + ".ogg");
     }
     for (MCH_HeliInfo mCH_HeliInfo : MCH_HeliInfoManager.map.values()) {

       if (!((MCH_AircraftInfo)mCH_HeliInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_HeliInfo).soundMove + ".ogg");
     }
     for (MCH_TankInfo mCH_TankInfo : MCH_TankInfoManager.map.values()) {

       if (!((MCH_AircraftInfo)mCH_TankInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_TankInfo).soundMove + ".ogg");
     }
     for (MCH_VehicleInfo mCH_VehicleInfo : MCH_VehicleInfoManager.map.values()) {

       if (!((MCH_AircraftInfo)mCH_VehicleInfo).soundMove.isEmpty()) W_McClient.addSound(((MCH_AircraftInfo)mCH_VehicleInfo).soundMove + ".ogg");

     }
   }


   public MCH_Config loadConfig(String fileName) {
     this.lastConfigFileName = fileName;

     MCH_Config config = new MCH_Config((Minecraft.func_71410_x()).field_71412_D.getPath(), "/" + fileName);

     config.load();
     config.write();

     return config;
   }

   public MCH_Config reconfig() {
     MCH_Lib.DbgLog(false, "MCH_ClientProxy.reconfig()", new Object[0]);
     MCH_Config config = loadConfig(this.lastConfigFileName);
     MCH_ClientCommonTickHandler.instance.updatekeybind(config);
     return config;
   }


   public void loadHUD(String path) {
     this.lastLoadHUDPath = path;
     MCH_HudManager.load(path);
   }

   public void reloadHUD() {
     loadHUD(this.lastLoadHUDPath);
   }


   public Entity getClientPlayer() {
     return (Entity)(Minecraft.func_71410_x()).field_71439_g;
   }




   public void init() {
     MinecraftForge.EVENT_BUS.register(new MCH_ParticlesUtil());
     MinecraftForge.EVENT_BUS.register(new MCH_ClientEventHook());
   }


   public void setCreativeDigDelay(int n) {
     W_Reflection.setCreativeDigSpeed(n);
   }


   public boolean isFirstPerson() {
     return ((Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0);
   }


   public int getNewRenderType() {
     return RenderingRegistry.getNextAvailableRenderId();
   }
   public boolean isSinglePlayer() {
     return Minecraft.func_71410_x().func_71356_B();
   }


   public void readClientModList() {
     try {
       Minecraft mc = Minecraft.func_71410_x();
       MCH_MultiplayClient.readModList(mc.func_110432_I().func_148255_b());
     }
     catch (Exception e) {

       e.printStackTrace();
     }
   }


   public void printChatMessage(IChatComponent chat, int showTime, int pos) {
     ((MCH_GuiTitle)MCH_ClientCommonTickHandler.instance.gui_Title).setupTitle(chat, showTime, pos);
   }


   public void hitBullet() {
     MCH_ClientCommonTickHandler.instance.gui_Common.hitBullet();
   }


   public void clientLocked() {
     MCH_ClientCommonTickHandler.isLocked = true;
   }
 }