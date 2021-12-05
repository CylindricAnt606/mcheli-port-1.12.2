/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class W_TextureUtil
/*    */ {
/* 11 */   private static mcheli.wrapper.W_TextureUtil instance = new mcheli.wrapper.W_TextureUtil();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private TextureParam newParam() {
/* 22 */     return new TextureParam(this);
/*    */   }
/*    */   
/*    */   public static TextureParam getTextureInfo(String domain, String name) {
/* 26 */     TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
/* 27 */     ResourceLocation r = new ResourceLocation(domain, name);
/* 28 */     textureManager.func_110577_a(r);
/*    */     
/* 30 */     TextureParam info = instance.newParam();
/* 31 */     info.width = GL11.glGetTexLevelParameteri(3553, 0, 4096);
/* 32 */     info.height = GL11.glGetTexLevelParameteri(3553, 0, 4097);
/*    */     
/* 34 */     return info;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_TextureUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */