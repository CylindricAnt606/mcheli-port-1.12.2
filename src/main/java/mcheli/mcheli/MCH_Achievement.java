  package mcheli.mcheli;
 import mcheli.mcheli.MCH_MOD;
 import mcheli.mcheli.container.MCH_ItemContainer;
 import mcheli.mcheli.helicopter.MCH_HeliInfo;
 import mcheli.mcheli.helicopter.MCH_HeliInfoManager;
 import mcheli.mcheli.helicopter.MCH_ItemHeli;
 import mcheli.mcheli.plane.MCP_PlaneInfo;
 import mcheli.mcheli.plane.MCP_PlaneInfoManager;
 import mcheli.mcheli.vehicle.MCH_VehicleInfo;
 import mcheli.mcheli.vehicle.MCH_VehicleInfoManager;
 import mcheli.mcheli.wrapper.W_Achievement;
 import mcheli.mcheli.wrapper.W_Item;
 import mcheli.mcheli.wrapper.W_LanguageRegistry;
 import net.minecraft.advancements.Advancement;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraft.item.Item;
 
 public class MCH_Achievement {
     //TODO: Achievments?
   public static Advancement welcome = null;
   public static Advancement supplyFuel = null;
   public static Advancement supplyAmmo = null;
   public static Advancement aintWarHell = null;
   public static Advancement reliefSupplies = null;
   public static Advancement rideValkyries = null;
 
   
   public static void PreInit() {
     Item item = getAnyAircraftIcon("ah-64");
     
     int BC = 1;
     int BR = 1;
     
     String name = "McHeliWelcome";
     welcome = W_Achievement.registerAchievement("mcheli" + name, name, 1, 1, item, null);
     W_LanguageRegistry.addNameForObject(welcome, "en_US", "Welcome to MC Helicopter MOD", name, "Put the helicopter");
     W_LanguageRegistry.addNameForObject(welcome, "ja_JP", "MC Helicopter MOD へようこそ", name, "ヘリコプターを設置");
     
     name = "McHeliSupplyFuel";
     supplyFuel = W_Achievement.registerAchievement("mcheli" + name, name, -1, 1, (Item)MCH_MOD.itemFuel, null);
     W_LanguageRegistry.addNameForObject(supplyFuel, "en_US", "Refueling", name, "Refuel aircraft");
     W_LanguageRegistry.addNameForObject(supplyFuel, "ja_JP", "燃料補給", name, "燃料を補給");
     
     item = getAircraftIcon("ammo_box");
     name = "McHeliSupplyAmmo";
     supplyAmmo = W_Achievement.registerAchievement("mcheli" + name, name, 3, 1, item, null);
     W_LanguageRegistry.addNameForObject(supplyAmmo, "en_US", "Supply ammo", name, "Supply ammo to the aircraft");
     W_LanguageRegistry.addNameForObject(supplyAmmo, "ja_JP", "弾薬補給", name, "弾薬を補給");
     
     item = getAircraftIcon("uh-1c");
     name = "McHeliRideValkyries";
     rideValkyries = W_Achievement.registerAchievement("mcheli" + name, name, -1, 3, item, null);
     W_LanguageRegistry.addNameForObject(rideValkyries, "en_US", "Ride Of The Valkyries", name, "?");
     W_LanguageRegistry.addNameForObject(rideValkyries, "ja_JP", "ワルキューレの騎行", name, "?");
     
     item = getAircraftIcon("mh-60l_dap");
     name = "McHeliAintWarHell";
     aintWarHell = W_Achievement.registerAchievement("mcheli" + name, name, 3, 3, item, null);
     W_LanguageRegistry.addNameForObject(aintWarHell, "en_US", "Ain't war hell?", name, "?");
     W_LanguageRegistry.addNameForObject(aintWarHell, "ja_JP", "ホント戦争は地獄だぜ", name, "?");
     
     MCH_ItemContainer mCH_ItemContainer = MCH_MOD.itemContainer;
     name = "McHeliReliefSupplies";
     reliefSupplies = W_Achievement.registerAchievement("mcheli" + name, name, -1, -1, (Item)mCH_ItemContainer, null);
     W_LanguageRegistry.addNameForObject(reliefSupplies, "en_US", "Relief supplies", name, "Drop a container");
     W_LanguageRegistry.addNameForObject(reliefSupplies, "ja_JP", "支援物資", name, "コンテナを投下");


     Advancement[] achievements = { welcome, supplyFuel, supplyAmmo, aintWarHell, rideValkyries, reliefSupplies };
     AchievementPage.registerAchievementPage(new AchievementPage("MC Helicopter", achievements));
   }
 
   
   public static Item getAircraftIcon(String defaultIconAircraft) {
     Item item = W_Item.getItemByName("stone");
     MCH_HeliInfo mCH_HeliInfo = MCH_HeliInfoManager.get(defaultIconAircraft);
     if (mCH_HeliInfo != null && mCH_HeliInfo.getItem() != null)
     {
       return mCH_HeliInfo.getItem();
     }
     
     MCP_PlaneInfo mCP_PlaneInfo = MCP_PlaneInfoManager.get(defaultIconAircraft);
     if (mCP_PlaneInfo != null && mCP_PlaneInfo.getItem() != null)
     {
       return mCP_PlaneInfo.getItem();
     }
     
     MCH_VehicleInfo mCH_VehicleInfo = MCH_VehicleInfoManager.get(defaultIconAircraft);
     if (mCH_VehicleInfo != null && mCH_VehicleInfo.getItem() != null)
     {
       return mCH_VehicleInfo.getItem();
     }
     
     return item;
   }
   
   public static Item getAnyAircraftIcon(String defaultIconAircraft) {
     MCH_ItemHeli mCH_ItemHeli = null;
     Item item = W_Item.getItemByName("stone");
     if (MCH_HeliInfoManager.map.size() > 0) {
       
       MCH_HeliInfo info = MCH_HeliInfoManager.get(defaultIconAircraft);
       if (info != null && info.item != null) {
         
         mCH_ItemHeli = info.item;
       }
       else {
         
         for (MCH_HeliInfo i : MCH_HeliInfoManager.map.values()) {
           
           if (i.item != null) {
             
             mCH_ItemHeli = i.item;
             break;
           } 
         } 
       } 
     } 
     return (Item)mCH_ItemHeli;
   }


   public static void addStat(Entity player, Achievement a, int i) {
     if (a != null)
     {
       if (player instanceof EntityPlayer && !player.field_70170_p.field_72995_K)
       {
         ((EntityPlayer)player).func_71064_a((StatBase)a, i);
       }
     }
   }
 }