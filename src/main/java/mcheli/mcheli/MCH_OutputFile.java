/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ 
/*    */ public class MCH_OutputFile
/*    */ {
/* 12 */   public File file = null;
/* 13 */   public PrintWriter pw = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean open(String path) {
/* 18 */     close();
/* 19 */     this.file = new File(path);
/*    */     
/*    */     try {
/* 22 */       this.pw = new PrintWriter(this.file);
/*    */     }
/* 24 */     catch (FileNotFoundException e) {
/*    */ 
/*    */       
/* 27 */       return false;
/*    */     } 
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean openUTF8(String path) {
/* 34 */     close();
/* 35 */     this.file = new File(path);
/*    */     
/*    */     try {
/* 38 */       this.pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.file), "UTF-8"));
/*    */     }
/* 40 */     catch (Exception e) {
/*    */       
/* 42 */       e.printStackTrace();
/* 43 */       return false;
/*    */     } 
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeLine(String s) {
/* 50 */     if (this.pw != null && s != null)
/*    */     {
/* 52 */       this.pw.println(s);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 58 */     if (this.pw != null) this.pw.close(); 
/* 59 */     this.pw = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_OutputFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */