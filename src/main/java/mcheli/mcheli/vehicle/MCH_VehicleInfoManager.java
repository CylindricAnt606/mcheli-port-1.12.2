/*    */ package mcheli.mcheli.vehicle;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_BaseInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*    */ import mcheli.vehicle.MCH_VehicleInfo;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class MCH_VehicleInfoManager
/*    */   extends MCH_AircraftInfoManager {
/* 14 */   private static mcheli.vehicle.MCH_VehicleInfoManager instance = new mcheli.vehicle.MCH_VehicleInfoManager();
/* 15 */   public static HashMap<String, MCH_VehicleInfo> map = new LinkedHashMap<String, MCH_VehicleInfo>();
/*    */ 
/*    */   
/*    */   public static MCH_VehicleInfo get(String name) {
/* 19 */     return map.get(name); } public static mcheli.vehicle.MCH_VehicleInfoManager getInstance() {
/* 20 */     return instance;
/*    */   }
/*    */   
/* 23 */   public MCH_BaseInfo newInfo(String name) { return (MCH_BaseInfo)new MCH_VehicleInfo(name); } public Map getMap() {
/* 24 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MCH_VehicleInfo getFromItem(Item item) {
/* 29 */     return getInstance().getAcInfoFromItem(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_VehicleInfo getAcInfoFromItem(Item item) {
/* 35 */     if (item == null) return null; 
/* 36 */     for (MCH_VehicleInfo info : map.values()) {
/*    */       
/* 38 */       if (info.item == item)
/*    */       {
/* 40 */         return info;
/*    */       }
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_VehicleInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */