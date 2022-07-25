/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_BaseInfo;
/*    */ import mcheli.MCH_InputFile;
/*    */ import mcheli.MCH_Lib;
/*    */ 
/*    */ public abstract class MCH_InfoManagerBase {
/*    */   public abstract MCH_BaseInfo newInfo(String paramString);
/*    */   
/*    */   public boolean load(String path, String type) {
/* 15 */     path = path.replace('\\', '/');
/* 16 */     File dir = new File(path + type);
/* 17 */     File[] files = dir.listFiles((FileFilter)new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     if (files == null || files.length <= 0)
/*    */     {
/* 30 */       return false;
/*    */     }
/* 32 */     for (File f : files) {
/*    */       
/* 34 */       int line = 0;
/* 35 */       MCH_InputFile inFile = new MCH_InputFile();
/*    */       
/* 37 */       BufferedReader br = null;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 81 */     MCH_Lib.Log("Read %d %s", new Object[] { Integer.valueOf(getMap().size()), type });
/* 82 */     return (getMap().size() > 0);
/*    */   }
/*    */   
/*    */   public abstract Map getMap();
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_InfoManagerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */