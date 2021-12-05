/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class W_TextureCoordinate
/*    */ {
/*    */   public float u;
/*    */   public float v;
/*    */   public float w;
/*    */   
/*    */   public W_TextureCoordinate(float u, float v) {
/* 14 */     this(u, v, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public W_TextureCoordinate(float u, float v, float w) {
/* 19 */     this.u = u;
/* 20 */     this.v = v;
/* 21 */     this.w = w;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_TextureCoordinate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */