/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import mcheli.wrapper.modelloader.W_Vertex;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ 
/*    */ public abstract class W_ModelCustom implements IModelCustom {
/*  7 */   public float min = 100000.0F;
/*  8 */   public float minX = 100000.0F;
/*  9 */   public float minY = 100000.0F;
/* 10 */   public float minZ = 100000.0F;
/*    */   
/* 12 */   public float max = -100000.0F;
/* 13 */   public float maxX = -100000.0F;
/* 14 */   public float maxY = -100000.0F;
/* 15 */   public float maxZ = -100000.0F;
/*    */   
/* 17 */   public float size = 0.0F;
/* 18 */   public float sizeX = 0.0F;
/* 19 */   public float sizeY = 0.0F;
/* 20 */   public float sizeZ = 0.0F;
/*    */ 
/*    */   
/*    */   public void checkMinMax(W_Vertex v) {
/* 24 */     if (v.x < this.minX) this.minX = v.x; 
/* 25 */     if (v.y < this.minY) this.minY = v.y; 
/* 26 */     if (v.z < this.minZ) this.minZ = v.z; 
/* 27 */     if (v.x > this.maxX) this.maxX = v.x; 
/* 28 */     if (v.y > this.maxY) this.maxY = v.y; 
/* 29 */     if (v.z > this.maxZ) this.maxZ = v.z; 
/*    */   }
/*    */   
/*    */   public void checkMinMaxFinal() {
/* 33 */     if (this.minX < this.min) this.min = this.minX; 
/* 34 */     if (this.minY < this.min) this.min = this.minY; 
/* 35 */     if (this.minZ < this.min) this.min = this.minZ; 
/* 36 */     if (this.maxX > this.max) this.max = this.maxX; 
/* 37 */     if (this.maxY > this.max) this.max = this.maxY; 
/* 38 */     if (this.maxZ > this.max) this.max = this.maxZ; 
/* 39 */     this.sizeX = this.maxX - this.minX;
/* 40 */     this.sizeY = this.maxY - this.minY;
/* 41 */     this.sizeZ = this.maxZ - this.minZ;
/* 42 */     this.size = this.max - this.min;
/*    */   }
/*    */   
/*    */   public abstract boolean containsPart(String paramString);
/*    */   
/*    */   public abstract void renderAll(int paramInt1, int paramInt2);
/*    */   
/*    */   public abstract void renderAllLine(int paramInt1, int paramInt2);
/*    */   
/*    */   public abstract int getVertexNum();
/*    */   
/*    */   public abstract int getFaceNum();
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_ModelCustom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */