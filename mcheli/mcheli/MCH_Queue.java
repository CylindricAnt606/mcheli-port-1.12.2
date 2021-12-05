/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class MCH_Queue<T>
/*    */ {
/*    */   private int current;
/*    */   private List<T> list;
/*    */   
/*    */   public MCH_Queue(int filterLength, T initVal) {
/* 13 */     if (filterLength <= 0) filterLength = 1;
/*    */     
/* 15 */     this.list = new ArrayList<T>();
/* 16 */     for (int i = 0; i < filterLength; ) { this.list.add(initVal); i++; }
/* 17 */      this.current = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear(T clearVal) {
/* 22 */     for (int i = 0; i < size(); ) { this.list.set(i, clearVal); i++; }
/*    */   
/*    */   }
/*    */   
/*    */   public void put(T t) {
/* 27 */     this.list.set(this.current, t);
/*    */     
/* 29 */     this.current++;
/* 30 */     this.current %= size();
/*    */   }
/*    */ 
/*    */   
/*    */   private int getIndex(int offset) {
/* 35 */     offset %= size();
/* 36 */     int index = this.current + offset;
/* 37 */     if (index < 0)
/* 38 */       return index + size(); 
/* 39 */     return index % size();
/*    */   }
/*    */   
/* 42 */   public T oldest() { return this.list.get(getIndex(1)); }
/* 43 */   public T get(int i) { return this.list.get(i); } public int size() {
/* 44 */     return this.list.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Queue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */