/*    */ package mcheli.mcheli;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import mcheli.MCH_Lib;
/*    */ 
/*    */ public class MCH_InputFile {
/* 12 */   public File file = null;
/* 13 */   public BufferedReader br = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean open(String path) {
/* 18 */     close();
/* 19 */     this.file = new File(path);
/* 20 */     String filePath = this.file.getAbsolutePath();
/*    */     try {
/* 22 */       this.br = new BufferedReader(new FileReader(this.file));
/* 23 */     } catch (FileNotFoundException e) {
/* 24 */       MCH_Lib.DbgLog(true, "FILE open failed MCH_InputFile.open:" + filePath, new Object[0]);
/* 25 */       e.printStackTrace();
/* 26 */       return false;
/*    */     } 
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean openUTF8(File file) {
/* 33 */     return openUTF8(file.getPath());
/*    */   }
/*    */   
/*    */   public boolean openUTF8(String path) {
/* 37 */     close();
/* 38 */     this.file = new File(path);
/*    */     
/*    */     try {
/* 41 */       this.br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), "UTF-8"));
/*    */     }
/* 43 */     catch (Exception e) {
/*    */       
/* 45 */       e.printStackTrace();
/* 46 */       return false;
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String readLine() {
/*    */     try {
/* 54 */       return (this.br != null) ? this.br.readLine() : null;
/*    */     }
/* 56 */     catch (IOException e) {
/*    */       
/* 58 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     try {
/* 66 */       if (this.br != null) this.br.close();
/*    */     
/* 68 */     } catch (IOException e) {}
/*    */ 
/*    */     
/* 71 */     this.br = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_InputFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */