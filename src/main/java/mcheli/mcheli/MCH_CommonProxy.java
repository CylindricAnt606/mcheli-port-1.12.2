 package mcheli.mcheli;

 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.MCH_ServerTickHandler;
 import mcheli.mcheli.aircraft.MCH_EntityAircraft;
 import mcheli.mcheli.aircraft.MCH_SoundUpdater;
 import mcheli.mcheli.wrapper.ChatMessageComponent;
 import net.minecraft.entity.Entity;
 import net.minecraft.server.MinecraftServer;
 import net.minecraftforge.fml.common.FMLCommonHandler;

 public class MCH_CommonProxy
 {
   public String lastConfigFileName;

   public void registerRenderer() {}

   public String getDataDir() {
       //Maybe .isFile but can be something different like this too
     return MinecraftServer.USER_CACHE_FILE.getPath();
   }
   public void registerBlockRenderer() {}
   public void registerModels() {}
   public void registerModelsHeli(String name, boolean reload) {}
   public void registerModelsPlane(String name, boolean reload) {}
   public void registerModelsVehicle(String name, boolean reload) {}

   public void registerModelsTank(String name, boolean reload) {}

   public void registerClientTick() {}

   public void registerServerTick() {
     FMLCommonHandler.instance().bus().register(new MCH_ServerTickHandler());
   }
   public boolean isRemote() { return false; }
   public String side() { return "Server"; } public MCH_SoundUpdater CreateSoundUpdater(MCH_EntityAircraft aircraft) {
     return null;
   }


   public void registerSounds() {}

   public MCH_Config loadConfig(String fileName) {
     this.lastConfigFileName = fileName;

     MCH_Config config = new MCH_Config("./", fileName);

     config.load();
     config.write();

     return config;
   }

   public MCH_Config reconfig() {
     MCH_Lib.DbgLog(false, "MCH_CommonProxy.reconfig()", new Object[0]);
     return loadConfig(this.lastConfigFileName);
   }

   public void loadHUD(String path) {}

   public void reloadHUD() {}

   public Entity getClientPlayer() {
     return null;
   }
   public void setCreativeDigDelay(int n) {}

   public void init() {}

   public boolean isFirstPerson() {
     return false;
   } public int getNewRenderType() {
     return -1;
   } public boolean isSinglePlayer() {
       //Maybe .isFile but can be something different like this too
     return MinecraftServer.USER_CACHE_FILE.isFile();
   }

   public void readClientModList() {}

   public void printChatMessage(ChatMessageComponent chat, int showTime, int pos) {}

   public void hitBullet() {}

   public void clientLocked() {}
 }