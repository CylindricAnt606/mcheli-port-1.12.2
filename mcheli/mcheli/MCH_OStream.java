/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class MCH_OStream
/*    */   extends ByteArrayOutputStream {
/*  9 */   public int index = 0;
/*    */   
/*    */   public static final int SIZE = 30720;
/*    */   
/*    */   public void write(DataOutputStream dos) {
/*    */     try {
/*    */       int datasize;
/* 16 */       if (this.index + 30720 <= size()) {
/*    */         
/* 18 */         datasize = 30720;
/*    */       }
/*    */       else {
/*    */         
/* 22 */         datasize = size() - this.index;
/*    */       } 
/* 24 */       dos.writeInt(this.index);
/* 25 */       dos.writeInt(datasize);
/* 26 */       dos.writeInt(size());
/* 27 */       dos.write(this.buf, this.index, datasize);
/* 28 */       this.index += datasize;
/*    */     }
/* 30 */     catch (IOException e) {
/*    */       
/* 32 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDataEnd() {
/* 38 */     return (this.index >= size());
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_OStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */