/*    */ package mcheli.mcheli.tank;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_BaseInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*    */ import mcheli.tank.MCH_TankInfo;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class MCH_TankInfoManager
/*    */   extends MCH_AircraftInfoManager {
/* 14 */   private static mcheli.tank.MCH_TankInfoManager instance = new mcheli.tank.MCH_TankInfoManager();
/* 15 */   public static HashMap<String, MCH_TankInfo> map = new LinkedHashMap<String, MCH_TankInfo>();
/*    */ 
/*    */   
/*    */   public static MCH_TankInfo get(String name) {
/* 19 */     return map.get(name); } public static mcheli.tank.MCH_TankInfoManager getInstance() {
/* 20 */     return instance;
/*    */   }
/*    */   
/* 23 */   public MCH_BaseInfo newInfo(String name) { return (MCH_BaseInfo)new MCH_TankInfo(name); } public Map getMap() {
/* 24 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MCH_TankInfo getFromItem(Item item) {
/* 29 */     return getInstance().getAcInfoFromItem(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_TankInfo getAcInfoFromItem(Item item) {
/* 35 */     if (item == null) return null; 
/* 36 */     for (MCH_TankInfo info : map.values()) {
/*    */       
/* 38 */       if (info.item == item)
/*    */       {
/* 40 */         return info;
/*    */       }
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_TankInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */