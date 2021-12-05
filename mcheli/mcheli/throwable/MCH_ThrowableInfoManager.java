/*     */ package mcheli.mcheli.throwable;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import mcheli.MCH_InputFile;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.throwable.MCH_ThrowableInfo;
/*     */ import net.minecraft.item.Item;
/*     */ 
/*     */ public class MCH_ThrowableInfoManager {
/*  13 */   private static mcheli.throwable.MCH_ThrowableInfoManager instance = new mcheli.throwable.MCH_ThrowableInfoManager();
/*  14 */   private static HashMap<String, MCH_ThrowableInfo> map = new LinkedHashMap<String, MCH_ThrowableInfo>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean load(String path) {
/*  20 */     path = path.replace('\\', '/');
/*  21 */     File dir = new File(path);
/*  22 */     File[] files = dir.listFiles((FileFilter)new Object());
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
/*  33 */     if (files == null || files.length <= 0)
/*     */     {
/*  35 */       return false;
/*     */     }
/*  37 */     for (File f : files) {
/*     */       
/*  39 */       MCH_InputFile inFile = new MCH_InputFile();
/*     */ 
/*     */       
/*  42 */       int line = 0;
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
/*  85 */     MCH_Lib.Log("Read %d throwable", new Object[] { Integer.valueOf(map.size()) });
/*     */     
/*  87 */     return (map.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static MCH_ThrowableInfo get(String name) {
/*  92 */     return map.get(name);
/*     */   }
/*     */   
/*     */   public static MCH_ThrowableInfo get(Item item) {
/*  96 */     for (MCH_ThrowableInfo info : map.values()) {
/*     */       
/*  98 */       if (info.item == item)
/*     */       {
/* 100 */         return info;
/*     */       }
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean contains(String name) {
/* 107 */     return map.containsKey(name);
/*     */   }
/*     */   
/*     */   public static Set<String> getKeySet() {
/* 111 */     return map.keySet();
/*     */   }
/*     */   
/*     */   public static Collection<MCH_ThrowableInfo> getValues() {
/* 115 */     return map.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_ThrowableInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */