/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import mcheli.MCH_InputFile;
/*     */ import mcheli.MCH_Lib;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class MCH_BaseInfo {
/*     */   public String filePath;
/*     */   
/*     */   public boolean toBool(String s) {
/*  13 */     return s.equalsIgnoreCase("true");
/*     */   }
/*     */   public boolean toBool(String s, boolean defaultValue) {
/*  16 */     if (s.equalsIgnoreCase("true")) return true; 
/*  17 */     if (s.equalsIgnoreCase("false")) return false; 
/*  18 */     return defaultValue;
/*     */   }
/*     */   public float toFloat(String s) {
/*  21 */     return Float.parseFloat(s);
/*     */   }
/*     */   public float toFloat(String s, float min, float max) {
/*  24 */     float f = Float.parseFloat(s);
/*  25 */     return (f < min) ? min : ((f > max) ? max : f);
/*     */   } public double toDouble(String s) {
/*  27 */     return Double.parseDouble(s);
/*     */   }
/*     */   
/*     */   public Vec3 toVec3(String x, String y, String z) {
/*  31 */     return Vec3.func_72443_a(toDouble(x), toDouble(y), toDouble(z));
/*     */   }
/*     */   public int toInt(String s) {
/*  34 */     return Integer.parseInt(s);
/*     */   }
/*     */   public int toInt(String s, int min, int max) {
/*  37 */     int f = Integer.parseInt(s);
/*  38 */     return (f < min) ? min : ((f > max) ? max : f);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hex2dec(String s) {
/*  43 */     if (!s.startsWith("0x") && !s.startsWith("0X") && s.indexOf(false) != 35)
/*     */     {
/*  45 */       return (int)(Long.decode("0x" + s).longValue() & 0xFFFFFFFFFFFFFFFFL);
/*     */     }
/*  47 */     return (int)(Long.decode(s).longValue() & 0xFFFFFFFFFFFFFFFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] splitParam(String data) {
/*  52 */     return data.split("\\s*,\\s*");
/*     */   }
/*     */   
/*     */   public String[] splitParamSlash(String data) {
/*  56 */     return data.split("\\s*/\\s*");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidData() throws Exception {
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadItemData(int fileLine, String item, String data) {}
/*     */ 
/*     */   
/*     */   public void preReload() {}
/*     */ 
/*     */   
/*     */   public void postReload() {}
/*     */ 
/*     */   
/*     */   public boolean canReloadItem(String item) {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reload() {
/*  85 */     return reload(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean reload(mcheli.MCH_BaseInfo info) {
/*  90 */     int line = 0;
/*  91 */     MCH_InputFile inFile = new MCH_InputFile();
/*     */     
/*  93 */     BufferedReader br = null;
/*  94 */     File f = new File(info.filePath);
/*     */     
/*     */     try {
/*  97 */       if (inFile.openUTF8(f))
/*     */       {
/*  99 */         info.preReload();
/*     */         
/*     */         String str;
/* 102 */         while ((str = inFile.br.readLine()) != null) {
/*     */           
/* 104 */           line++;
/* 105 */           str = str.trim();
/* 106 */           int eqIdx = str.indexOf('=');
/* 107 */           if (eqIdx < 0 || 
/* 108 */             str.length() <= eqIdx + 1)
/*     */             continue; 
/* 110 */           String item = str.substring(0, eqIdx).trim().toLowerCase();
/* 111 */           if (info.canReloadItem(item))
/*     */           {
/* 113 */             info.loadItemData(item, str.substring(eqIdx + 1).trim());
/*     */           }
/*     */         } 
/* 116 */         line = 0;
/*     */         
/* 118 */         info.isValidData();
/*     */         
/* 120 */         info.postReload();
/*     */       }
/*     */     
/* 123 */     } catch (Exception e) {
/*     */       
/* 125 */       if (line > 0) { MCH_Lib.Log("### Load failed %s : line=%d", new Object[] { f.getName(), Integer.valueOf(line) }); }
/* 126 */       else { MCH_Lib.Log("### Load failed %s", new Object[] { f.getName() }); }
/* 127 */        e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 131 */       inFile.close();
/*     */     } 
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_BaseInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */