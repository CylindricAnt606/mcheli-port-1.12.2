/*     */ package mcheli.mcheli;
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import mcheli.MCH_Lib;
/*     */ 
/*     */ public class MCH_SoundsJson {
/*     */   public static boolean update(String path) {
/*  10 */     boolean result = true;
/*  11 */     path = path.replace('\\', '/');
/*  12 */     File dir = new File(path + "sounds");
/*  13 */     File[] files = dir.listFiles((FileFilter)new Object());
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
/*  25 */     int cnt = 0;
/*     */     
/*  27 */     PrintWriter pw = null;
/*     */ 
/*     */     
/*     */     try {
/*  31 */       File file = new File(path + "sounds.json");
/*     */       
/*  33 */       pw = new PrintWriter(file);
/*     */       
/*  35 */       pw.println("{");
/*     */       
/*  37 */       if (files != null) {
/*     */         
/*  39 */         LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
/*  40 */         for (File f : files) {
/*     */           
/*  42 */           String name = f.getName().toLowerCase();
/*  43 */           int ei = name.lastIndexOf(".");
/*  44 */           name = name.substring(0, ei);
/*  45 */           String key = name;
/*  46 */           char c = key.charAt(key.length() - 1);
/*  47 */           if (c >= '0' && c <= '9')
/*     */           {
/*  49 */             key = key.substring(0, key.length() - 1);
/*     */           }
/*     */           
/*  52 */           if (!map.containsKey(key))
/*     */           {
/*  54 */             map.put(key, new ArrayList<String>());
/*     */           }
/*  56 */           ((ArrayList<String>)map.get(key)).add(name);
/*     */         } 
/*  58 */         for (String key : map.keySet()) {
/*     */           
/*  60 */           cnt++;
/*  61 */           ArrayList<String> list = map.get(key);
/*  62 */           String line = "";
/*     */           
/*  64 */           line = "\"" + key + "\": {\"category\": \"master\",\"sounds\": [";
/*  65 */           for (int fi = 0; fi < list.size(); fi++)
/*     */           {
/*  67 */             line = line + ((fi > 0) ? "," : "") + "\"" + (String)list.get(fi) + "\"";
/*     */           }
/*  69 */           line = line + "]}";
/*     */           
/*  71 */           if (cnt < map.size()) line = line + ","; 
/*  72 */           pw.println(line);
/*     */         } 
/*     */       } 
/*     */       
/*  76 */       pw.println("}");
/*  77 */       pw.println("");
/*     */       
/*  79 */       result = true;
/*     */     }
/*  81 */     catch (IOException e) {
/*     */       
/*  83 */       result = false;
/*  84 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/*  88 */       if (pw != null)
/*     */       {
/*  90 */         pw.close();
/*     */       }
/*     */     } 
/*     */     
/*  94 */     if (result) {
/*     */       
/*  96 */       MCH_Lib.Log("Update sounds.json. %d sounds", new Object[] { Integer.valueOf(cnt) });
/*     */     }
/*     */     else {
/*     */       
/* 100 */       MCH_Lib.Log("Failed sounds.json update! %d sounds", new Object[] { Integer.valueOf(cnt) });
/*     */     } 
/* 102 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_SoundsJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */