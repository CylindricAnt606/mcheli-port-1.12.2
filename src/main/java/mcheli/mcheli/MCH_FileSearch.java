/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TreeSet;
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
/*     */ public class MCH_FileSearch
/*     */ {
/*     */   public static final int TYPE_FILE_OR_DIR = 1;
/*     */   public static final int TYPE_FILE = 2;
/*     */   public static final int TYPE_DIR = 3;
/*     */   
/*     */   public File[] listFiles(String directoryPath, String fileName) {
/*  29 */     if (fileName != null) {
/*  30 */       fileName = fileName.replace(".", "\\.");
/*  31 */       fileName = fileName.replace("*", ".*");
/*     */     } 
/*  33 */     return listFiles(directoryPath, fileName, 2, true, 0);
/*     */   }
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
/*     */   public File[] listFiles(String directoryPath, String fileNamePattern, int type, boolean isRecursive, int period) {
/*  63 */     File dir = new File(directoryPath);
/*  64 */     if (!dir.isDirectory()) {
/*  65 */       throw new IllegalArgumentException("引数で指定されたパス[" + dir.getAbsolutePath() + "]はディレクトリではありません。");
/*     */     }
/*     */     
/*  68 */     File[] files = dir.listFiles();
/*     */     
/*  70 */     for (int i = 0; i < files.length; i++) {
/*  71 */       File file = files[i];
/*  72 */       addFile(type, fileNamePattern, this.set, file, period);
/*     */       
/*  74 */       if (isRecursive && file.isDirectory()) {
/*  75 */         listFiles(file.getAbsolutePath(), fileNamePattern, type, isRecursive, period);
/*     */       }
/*     */     } 
/*     */     
/*  79 */     return (File[])this.set.toArray((Object[])new File[this.set.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addFile(int type, String match, TreeSet<File> set, File file, int period) {
/*  84 */     switch (type) {
/*     */       case 2:
/*  86 */         if (!file.isFile()) {
/*     */           return;
/*     */         }
/*     */         break;
/*     */       case 3:
/*  91 */         if (!file.isDirectory()) {
/*     */           return;
/*     */         }
/*     */         break;
/*     */     } 
/*  96 */     if (match != null && !file.getName().matches(match)) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     if (period != 0) {
/*     */       
/* 102 */       Date lastModifiedDate = new Date(file.lastModified());
/* 103 */       String lastModifiedDateStr = (new SimpleDateFormat("yyyyMMdd")).format(lastModifiedDate);
/*     */ 
/*     */ 
/*     */       
/* 107 */       long oneDayTime = 86400000L;
/* 108 */       long periodTime = oneDayTime * Math.abs(period);
/* 109 */       Date designatedDate = new Date(System.currentTimeMillis() - periodTime);
/*     */       
/* 111 */       String designatedDateStr = (new SimpleDateFormat("yyyyMMdd")).format(designatedDate);
/*     */       
/* 113 */       if (period > 0) {
/* 114 */         if (lastModifiedDateStr.compareTo(designatedDateStr) < 0) {
/*     */           return;
/*     */         }
/*     */       }
/* 118 */       else if (lastModifiedDateStr.compareTo(designatedDateStr) > 0) {
/*     */         return;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 124 */     set.add(file);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 129 */   private TreeSet set = new TreeSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 137 */     this.set.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_FileSearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */