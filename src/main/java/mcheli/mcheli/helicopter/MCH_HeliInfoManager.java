/*    */ package mcheli.mcheli.helicopter;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_BaseInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*    */ import mcheli.helicopter.MCH_HeliInfo;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class MCH_HeliInfoManager extends MCH_AircraftInfoManager {
/* 13 */   private static final mcheli.helicopter.MCH_HeliInfoManager instance = new mcheli.helicopter.MCH_HeliInfoManager();
/* 14 */   public static final HashMap<String, MCH_HeliInfo> map = new LinkedHashMap<String, MCH_HeliInfo>();
/*    */ 
/*    */   
/*    */   public static mcheli.helicopter.MCH_HeliInfoManager getInstance() {
/* 18 */     return instance; } public static MCH_HeliInfo get(String name) {
/* 19 */     return map.get(name);
/*    */   }
/*    */   
/* 22 */   public MCH_BaseInfo newInfo(String name) { return (MCH_BaseInfo)new MCH_HeliInfo(name); } public Map getMap() {
/* 23 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MCH_HeliInfo getFromItem(Item item) {
/* 28 */     return getInstance().getAcInfoFromItem(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCH_HeliInfo getAcInfoFromItem(Item item) {
/* 34 */     if (item == null) return null; 
/* 35 */     for (MCH_HeliInfo info : map.values()) {
/*    */       
/* 37 */       if (info.item == item)
/*    */       {
/* 39 */         return info;
/*    */       }
/*    */     } 
/* 42 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_HeliInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */