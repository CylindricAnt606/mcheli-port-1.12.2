/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import mcheli.wrapper.modelloader.W_Face;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class W_GroupObject
/*    */ {
/*    */   public String name;
/* 14 */   public ArrayList<W_Face> faces = new ArrayList<W_Face>();
/*    */   
/*    */   public int glDrawingMode;
/*    */   
/*    */   public W_GroupObject() {
/* 19 */     this("");
/*    */   }
/*    */ 
/*    */   
/*    */   public W_GroupObject(String name) {
/* 24 */     this(name, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public W_GroupObject(String name, int glDrawingMode) {
/* 29 */     this.name = name;
/* 30 */     this.glDrawingMode = glDrawingMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render() {
/* 35 */     if (this.faces.size() > 0) {
/*    */       
/* 37 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 38 */       tessellator.func_78371_b(this.glDrawingMode);
/* 39 */       render(tessellator);
/* 40 */       tessellator.func_78381_a();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Tessellator tessellator) {
/* 46 */     if (this.faces.size() > 0)
/*    */     {
/* 48 */       for (W_Face face : this.faces)
/*    */       {
/* 50 */         face.addFaceForRender(tessellator);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_GroupObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */