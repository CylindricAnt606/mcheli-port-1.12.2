/*     */ package mcheli.mcheli;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.container.MCH_ItemContainer;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.helicopter.MCH_ItemHeli;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.wrapper.W_Achievement;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_LanguageRegistry;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraftforge.common.AchievementPage;
/*     */ 
/*     */ public class MCH_Achievement {
/*  20 */   public static Achievement welcome = null;
/*  21 */   public static Achievement supplyFuel = null;
/*  22 */   public static Achievement supplyAmmo = null;
/*  23 */   public static Achievement aintWarHell = null;
/*  24 */   public static Achievement reliefSupplies = null;
/*  25 */   public static Achievement rideValkyries = null;
/*     */ 
/*     */   
/*     */   public static void PreInit() {
/*  29 */     Item item = getAnyAircraftIcon("ah-64");
/*     */     
/*  31 */     int BC = 1;
/*  32 */     int BR = 1;
/*     */     
/*  34 */     String name = "McHeliWelcome";
/*  35 */     welcome = W_Achievement.registerAchievement("mcheli" + name, name, 1, 1, item, null);
/*  36 */     W_LanguageRegistry.addNameForObject(welcome, "en_US", "Welcome to MC Helicopter MOD", name, "Put the helicopter");
/*  37 */     W_LanguageRegistry.addNameForObject(welcome, "ja_JP", "MC Helicopter MOD へようこそ", name, "ヘリコプターを設置");
/*     */     
/*  39 */     name = "McHeliSupplyFuel";
/*  40 */     supplyFuel = W_Achievement.registerAchievement("mcheli" + name, name, -1, 1, (Item)MCH_MOD.itemFuel, null);
/*  41 */     W_LanguageRegistry.addNameForObject(supplyFuel, "en_US", "Refueling", name, "Refuel aircraft");
/*  42 */     W_LanguageRegistry.addNameForObject(supplyFuel, "ja_JP", "燃料補給", name, "燃料を補給");
/*     */     
/*  44 */     item = getAircraftIcon("ammo_box");
/*  45 */     name = "McHeliSupplyAmmo";
/*  46 */     supplyAmmo = W_Achievement.registerAchievement("mcheli" + name, name, 3, 1, item, null);
/*  47 */     W_LanguageRegistry.addNameForObject(supplyAmmo, "en_US", "Supply ammo", name, "Supply ammo to the aircraft");
/*  48 */     W_LanguageRegistry.addNameForObject(supplyAmmo, "ja_JP", "弾薬補給", name, "弾薬を補給");
/*     */     
/*  50 */     item = getAircraftIcon("uh-1c");
/*  51 */     name = "McHeliRideValkyries";
/*  52 */     rideValkyries = W_Achievement.registerAchievement("mcheli" + name, name, -1, 3, item, null);
/*  53 */     W_LanguageRegistry.addNameForObject(rideValkyries, "en_US", "Ride Of The Valkyries", name, "?");
/*  54 */     W_LanguageRegistry.addNameForObject(rideValkyries, "ja_JP", "ワルキューレの騎行", name, "?");
/*     */     
/*  56 */     item = getAircraftIcon("mh-60l_dap");
/*  57 */     name = "McHeliAintWarHell";
/*  58 */     aintWarHell = W_Achievement.registerAchievement("mcheli" + name, name, 3, 3, item, null);
/*  59 */     W_LanguageRegistry.addNameForObject(aintWarHell, "en_US", "Ain't war hell?", name, "?");
/*  60 */     W_LanguageRegistry.addNameForObject(aintWarHell, "ja_JP", "ホント戦争は地獄だぜ", name, "?");
/*     */     
/*  62 */     MCH_ItemContainer mCH_ItemContainer = MCH_MOD.itemContainer;
/*  63 */     name = "McHeliReliefSupplies";
/*  64 */     reliefSupplies = W_Achievement.registerAchievement("mcheli" + name, name, -1, -1, (Item)mCH_ItemContainer, null);
/*  65 */     W_LanguageRegistry.addNameForObject(reliefSupplies, "en_US", "Relief supplies", name, "Drop a container");
/*  66 */     W_LanguageRegistry.addNameForObject(reliefSupplies, "ja_JP", "支援物資", name, "コンテナを投下");
/*     */ 
/*     */     
/*  69 */     Achievement[] achievements = { welcome, supplyFuel, supplyAmmo, aintWarHell, rideValkyries, reliefSupplies };
/*  70 */     AchievementPage.registerAchievementPage(new AchievementPage("MC Helicopter", achievements));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item getAircraftIcon(String defaultIconAircraft) {
/*  75 */     Item item = W_Item.getItemByName("stone");
/*  76 */     MCH_HeliInfo mCH_HeliInfo = MCH_HeliInfoManager.get(defaultIconAircraft);
/*  77 */     if (mCH_HeliInfo != null && mCH_HeliInfo.getItem() != null)
/*     */     {
/*  79 */       return mCH_HeliInfo.getItem();
/*     */     }
/*     */     
/*  82 */     MCP_PlaneInfo mCP_PlaneInfo = MCP_PlaneInfoManager.get(defaultIconAircraft);
/*  83 */     if (mCP_PlaneInfo != null && mCP_PlaneInfo.getItem() != null)
/*     */     {
/*  85 */       return mCP_PlaneInfo.getItem();
/*     */     }
/*     */     
/*  88 */     MCH_VehicleInfo mCH_VehicleInfo = MCH_VehicleInfoManager.get(defaultIconAircraft);
/*  89 */     if (mCH_VehicleInfo != null && mCH_VehicleInfo.getItem() != null)
/*     */     {
/*  91 */       return mCH_VehicleInfo.getItem();
/*     */     }
/*     */     
/*  94 */     return item;
/*     */   }
/*     */   
/*     */   public static Item getAnyAircraftIcon(String defaultIconAircraft) {
/*     */     MCH_ItemHeli mCH_ItemHeli;
/*  99 */     Item item = W_Item.getItemByName("stone");
/* 100 */     if (MCH_HeliInfoManager.map.size() > 0) {
/*     */       
/* 102 */       MCH_HeliInfo info = MCH_HeliInfoManager.get(defaultIconAircraft);
/* 103 */       if (info != null && info.item != null) {
/*     */         
/* 105 */         mCH_ItemHeli = info.item;
/*     */       }
/*     */       else {
/*     */         
/* 109 */         for (MCH_HeliInfo i : MCH_HeliInfoManager.map.values()) {
/*     */           
/* 111 */           if (i.item != null) {
/*     */             
/* 113 */             mCH_ItemHeli = i.item;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 119 */     return (Item)mCH_ItemHeli;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addStat(Entity player, Achievement a, int i) {
/* 124 */     if (a != null)
/*     */     {
/* 126 */       if (player instanceof EntityPlayer && !player.field_70170_p.field_72995_K)
/*     */       {
/* 128 */         ((EntityPlayer)player).func_71064_a((StatBase)a, i);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Achievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */