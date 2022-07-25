/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_WeaponInfoManager
/*     */ {
/*  21 */   private static mcheli.weapon.MCH_WeaponInfoManager instance = new mcheli.weapon.MCH_WeaponInfoManager();
/*     */   
/*     */   private static HashMap<String, MCH_WeaponInfo> map;
/*     */   private static String lastPath;
/*     */   
/*     */   private MCH_WeaponInfoManager() {
/*  27 */     map = new HashMap<String, MCH_WeaponInfo>();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean reload() {
/*  32 */     boolean ret = false;
/*     */     
/*     */     try {
/*  35 */       map.clear();
/*  36 */       ret = load(lastPath);
/*  37 */       setRoundItems();
/*  38 */       MCH_MOD.proxy.registerModels();
/*     */     }
/*  40 */     catch (Exception e) {
/*     */       
/*  42 */       e.printStackTrace();
/*     */     } 
/*  44 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean load(String path) {
/*  49 */     lastPath = path;
/*     */     
/*  51 */     path = path.replace('\\', '/');
/*  52 */     File dir = new File(path);
/*  53 */     File[] files = dir.listFiles((FileFilter)new Object());
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
/*  64 */     if (files == null || files.length <= 0)
/*     */     {
/*  66 */       return false;
/*     */     }
/*  68 */     for (File f : files) {
/*     */       
/*  70 */       BufferedReader br = null;
/*  71 */       int line = 0;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     MCH_Lib.Log("[mcheli] Read %d weapons", new Object[] { Integer.valueOf(map.size()) });
/*     */     
/* 113 */     return (map.size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setRoundItems() {
/* 119 */     for (MCH_WeaponInfo w : map.values()) {
/*     */       
/* 121 */       for (MCH_WeaponInfo.RoundItem r : w.roundItems) {
/*     */         
/* 123 */         Item item = W_Item.getItemByName(r.itemName);
/* 124 */         r.itemStack = new ItemStack(item, 1, r.damage);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static MCH_WeaponInfo get(String name) {
/* 131 */     return map.get(name);
/*     */   }
/*     */   
/*     */   public static boolean contains(String name) {
/* 135 */     return map.containsKey(name);
/*     */   }
/*     */   
/*     */   public static Set<String> getKeySet() {
/* 139 */     return map.keySet();
/*     */   }
/*     */   
/*     */   public static Collection<MCH_WeaponInfo> getValues() {
/* 143 */     return map.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */