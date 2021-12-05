/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import mcheli.MCH_Config;
/*    */ import mcheli.wrapper.W_MOD;
/*    */ import net.minecraft.client.renderer.GLAllocation;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class W_Render
/*    */   extends Render
/*    */ {
/* 19 */   private static FloatBuffer colorBuffer = GLAllocation.func_74529_h(16);
/*    */ 
/*    */ 
/*    */   
/*    */   protected void bindTexture(String path) {
/* 24 */     func_110776_a(new ResourceLocation(W_MOD.DOMAIN, path));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   protected static final ResourceLocation TEX_DEFAULT = new ResourceLocation(W_MOD.DOMAIN, "textures/default.png");
/*    */   
/*    */   public int srcBlend;
/*    */   public int dstBlend;
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 37 */     return TEX_DEFAULT;
/*    */   }
/*    */ 
/*    */   
/*    */   public static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_) {
/* 42 */     colorBuffer.clear();
/* 43 */     colorBuffer.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
/* 44 */     colorBuffer.flip();
/*    */     
/* 46 */     return colorBuffer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCommonRenderParam(boolean smoothShading, int lighting) {
/* 55 */     if (smoothShading) if (MCH_Config.SmoothShading.prmBool)
/*    */       {
/* 57 */         GL11.glShadeModel(7425);
/*    */       }
/*    */     
/* 60 */     GL11.glAlphaFunc(516, 0.001F);
/*    */     
/* 62 */     GL11.glEnable(2884);
/*    */     
/* 64 */     int j = lighting % 65536;
/* 65 */     int k = lighting / 65536;
/* 66 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*    */ 
/*    */     
/* 69 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*    */ 
/*    */     
/* 72 */     GL11.glEnable(3042);
/* 73 */     this.srcBlend = GL11.glGetInteger(3041);
/* 74 */     this.dstBlend = GL11.glGetInteger(3040);
/* 75 */     GL11.glBlendFunc(770, 771);
/*    */   }
/*    */ 
/*    */   
/*    */   public void restoreCommonRenderParam() {
/* 80 */     GL11.glBlendFunc(this.srcBlend, this.dstBlend);
/* 81 */     GL11.glDisable(3042);
/*    */     
/* 83 */     GL11.glShadeModel(7424);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Render.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */