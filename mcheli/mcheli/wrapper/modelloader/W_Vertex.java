/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class W_Vertex
/*    */ {
/*    */   public float x;
/*    */   public float y;
/*    */   public float z;
/*    */   
/*    */   public W_Vertex(float x, float y) {
/* 14 */     this(x, y, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public W_Vertex(float x, float y, float z) {
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.z = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public void normalize() {
/* 26 */     double d = Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z));
/* 27 */     this.x = (float)(this.x / d);
/* 28 */     this.y = (float)(this.y / d);
/* 29 */     this.z = (float)(this.z / d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(mcheli.wrapper.modelloader.W_Vertex v) {
/* 34 */     this.x += v.x;
/* 35 */     this.y += v.y;
/* 36 */     this.z += v.z;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equal(mcheli.wrapper.modelloader.W_Vertex v) {
/* 41 */     return (this.x == v.x && this.y == v.y && this.z == v.z);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_Vertex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */