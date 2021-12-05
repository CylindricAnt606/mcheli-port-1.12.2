/*    */ package mcheli.mcheli.plane;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_BaseInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*    */ import mcheli.plane.MCP_PlaneInfo;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class MCP_PlaneInfoManager
/*    */   extends MCH_AircraftInfoManager {
/* 14 */   private static mcheli.plane.MCP_PlaneInfoManager instance = new mcheli.plane.MCP_PlaneInfoManager();
/* 15 */   public static HashMap<String, MCP_PlaneInfo> map = new LinkedHashMap<String, MCP_PlaneInfo>();
/*    */ 
/*    */   
/*    */   public static MCP_PlaneInfo get(String name) {
/* 19 */     return map.get(name); } public static mcheli.plane.MCP_PlaneInfoManager getInstance() {
/* 20 */     return instance;
/*    */   }
/*    */   
/* 23 */   public MCH_BaseInfo newInfo(String name) { return (MCH_BaseInfo)new MCP_PlaneInfo(name); } public Map getMap() {
/* 24 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   public static MCP_PlaneInfo getFromItem(Item item) {
/* 29 */     return getInstance().getAcInfoFromItem(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MCP_PlaneInfo getAcInfoFromItem(Item item) {
/* 35 */     if (item == null) return null; 
/* 36 */     for (MCP_PlaneInfo info : map.values()) {
/*    */       
/* 38 */       if (info.item == item)
/*    */       {
/* 40 */         return info;
/*    */       }
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_PlaneInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */