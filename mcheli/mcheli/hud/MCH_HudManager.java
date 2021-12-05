/*     */ package mcheli.mcheli.hud;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import mcheli.MCH_InputFile;
/*     */ import mcheli.hud.MCH_Hud;
/*     */ import mcheli.hud.MCH_HudItem;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ public class MCH_HudManager {
/*  13 */   private static mcheli.hud.MCH_HudManager instance = new mcheli.hud.MCH_HudManager();
/*     */   
/*     */   private static HashMap<String, MCH_Hud> map;
/*     */   
/*     */   private MCH_HudManager() {
/*  18 */     map = new HashMap<String, MCH_Hud>();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean load(String path) {
/*  23 */     MCH_HudItem.mc = Minecraft.func_71410_x();
/*     */     
/*  25 */     map.clear();
/*     */     
/*  27 */     path = path.replace('\\', '/');
/*  28 */     File dir = new File(path);
/*  29 */     File[] files = dir.listFiles((FileFilter)new Object());
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
/*  40 */     if (files == null || files.length <= 0)
/*     */     {
/*  42 */       return false;
/*     */     }
/*  44 */     for (File f : files) {
/*     */       
/*  46 */       MCH_InputFile inFile = new MCH_InputFile();
/*     */ 
/*     */       
/*  49 */       int line = 0;
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
/* 106 */     MCH_Lib.Log("Read %d HUD", new Object[] { Integer.valueOf(map.size()) });
/*     */     
/* 108 */     return (map.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MCH_Hud get(String name) {
/* 113 */     return map.get(name.toLowerCase());
/*     */   }
/*     */   
/*     */   public static boolean contains(String name) {
/* 117 */     return map.containsKey(name);
/*     */   }
/*     */   
/*     */   public static Set<String> getKeySet() {
/* 121 */     return map.keySet();
/*     */   }
/*     */   
/*     */   public static Collection<MCH_Hud> getValues() {
/* 125 */     return map.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */