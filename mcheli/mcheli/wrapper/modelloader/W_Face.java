/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.wrapper.modelloader.W_TextureCoordinate;
/*    */ import mcheli.wrapper.modelloader.W_Vertex;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class W_Face
/*    */ {
/*    */   public int[] verticesID;
/*    */   public W_Vertex[] vertices;
/*    */   public W_Vertex[] vertexNormals;
/*    */   public W_Vertex faceNormal;
/*    */   public W_TextureCoordinate[] textureCoordinates;
/*    */   
/*    */   public mcheli.wrapper.modelloader.W_Face copy() {
/* 20 */     mcheli.wrapper.modelloader.W_Face f = new mcheli.wrapper.modelloader.W_Face();
/*    */     
/* 22 */     return f;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addFaceForRender(Tessellator tessellator) {
/* 27 */     addFaceForRender(tessellator, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addFaceForRender(Tessellator tessellator, float textureOffset) {
/* 32 */     if (this.faceNormal == null)
/*    */     {
/* 34 */       this.faceNormal = calculateFaceNormal();
/*    */     }
/*    */     
/* 37 */     tessellator.func_78375_b(this.faceNormal.x, this.faceNormal.y, this.faceNormal.z);
/*    */     
/* 39 */     float averageU = 0.0F;
/* 40 */     float averageV = 0.0F;
/*    */     
/* 42 */     if (this.textureCoordinates != null && this.textureCoordinates.length > 0) {
/*    */       
/* 44 */       for (int j = 0; j < this.textureCoordinates.length; j++) {
/*    */         
/* 46 */         averageU += (this.textureCoordinates[j]).u;
/* 47 */         averageV += (this.textureCoordinates[j]).v;
/*    */       } 
/*    */       
/* 50 */       averageU /= this.textureCoordinates.length;
/* 51 */       averageV /= this.textureCoordinates.length;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 56 */     for (int i = 0; i < this.vertices.length; i++) {
/*    */ 
/*    */       
/* 59 */       if (this.textureCoordinates != null && this.textureCoordinates.length > 0) {
/*    */         
/* 61 */         float offsetU = textureOffset;
/* 62 */         float offsetV = textureOffset;
/*    */         
/* 64 */         if ((this.textureCoordinates[i]).u > averageU)
/*    */         {
/* 66 */           offsetU = -offsetU;
/*    */         }
/* 68 */         if ((this.textureCoordinates[i]).v > averageV)
/*    */         {
/* 70 */           offsetV = -offsetV;
/*    */         }
/*    */         
/* 73 */         if (this.vertexNormals != null && i < this.vertexNormals.length)
/*    */         {
/* 75 */           tessellator.func_78375_b((this.vertexNormals[i]).x, (this.vertexNormals[i]).y, (this.vertexNormals[i]).z);
/*    */         }
/*    */         
/* 78 */         tessellator.func_78374_a((this.vertices[i]).x, (this.vertices[i]).y, (this.vertices[i]).z, ((this.textureCoordinates[i]).u + offsetU), ((this.textureCoordinates[i]).v + offsetV));
/*    */       }
/*    */       else {
/*    */         
/* 82 */         tessellator.func_78377_a((this.vertices[i]).x, (this.vertices[i]).y, (this.vertices[i]).z);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public W_Vertex calculateFaceNormal() {
/* 89 */     Vec3 v1 = Vec3.func_72443_a(((this.vertices[1]).x - (this.vertices[0]).x), ((this.vertices[1]).y - (this.vertices[0]).y), ((this.vertices[1]).z - (this.vertices[0]).z));
/* 90 */     Vec3 v2 = Vec3.func_72443_a(((this.vertices[2]).x - (this.vertices[0]).x), ((this.vertices[2]).y - (this.vertices[0]).y), ((this.vertices[2]).z - (this.vertices[0]).z));
/* 91 */     Vec3 normalVector = null;
/*    */     
/* 93 */     normalVector = v1.func_72431_c(v2).func_72432_b();
/*    */     
/* 95 */     return new W_Vertex((float)normalVector.field_72450_a, (float)normalVector.field_72448_b, (float)normalVector.field_72449_c);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_Face.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */