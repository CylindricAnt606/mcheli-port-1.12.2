/*    */ package mcheli.mcheli;
/*    */ 
/*    */ public class MCH_CheckTime
/*    */ {
/*  5 */   private long startTime = 0L;
/*  6 */   public int x = 0;
/*  7 */   private int y = 0;
/*  8 */   public long[][] pointTimeList = new long[1][1];
/*    */   
/* 10 */   public int MAX_Y = 0;
/* 11 */   private int MAX_X = 0;
/*    */ 
/*    */   
/*    */   public MCH_CheckTime() {
/* 15 */     this.MAX_Y = 100;
/* 16 */     this.MAX_X = 40;
/* 17 */     this.pointTimeList = new long[this.MAX_Y + 1][this.MAX_X];
/* 18 */     this.y = this.MAX_Y - 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 23 */     this.startTime = System.nanoTime();
/* 24 */     this.x = 0;
/* 25 */     this.y = (this.y + 1) % this.MAX_Y;
/*    */     
/* 27 */     if (this.y == 0)
/*    */     {
/* 29 */       for (int j = 0; j < this.MAX_X; j++) {
/*    */         
/* 31 */         this.pointTimeList[this.MAX_Y][j] = 0L;
/* 32 */         for (int i = 0; i < this.MAX_Y; i++)
/*    */         {
/* 34 */           this.pointTimeList[this.MAX_Y][j] = this.pointTimeList[this.MAX_Y][j] + this.pointTimeList[i][j];
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void timeStamp() {
/* 42 */     if (this.x < this.MAX_X) {
/*    */       
/* 44 */       this.pointTimeList[this.y][this.x] = System.nanoTime() - this.startTime;
/* 45 */       this.x++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_CheckTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */