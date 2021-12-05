/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.MCH_ClientCommonTickHandler;
/*    */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*    */ import net.minecraft.client.renderer.texture.ITextureObject;
/*    */ import net.minecraft.client.renderer.texture.ITickableTextureObject;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_TextureManagerDummy
/*    */   extends TextureManager
/*    */ {
/* 24 */   public static ResourceLocation R = new ResourceLocation("mcheli", "textures/test.png");
/*    */   
/*    */   private TextureManager tm;
/*    */   
/*    */   public MCH_TextureManagerDummy(TextureManager t) {
/* 29 */     super(null);
/* 30 */     this.tm = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_110577_a(ResourceLocation resouce) {
/* 35 */     if (MCH_ClientCommonTickHandler.cameraMode == 2) {
/*    */       
/* 37 */       this.tm.func_110577_a(R);
/*    */     }
/*    */     else {
/*    */       
/* 41 */       this.tm.func_110577_a(resouce);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_130087_a(int p_130087_1_) {
/* 47 */     return this.tm.func_130087_a(p_130087_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_130088_a(ResourceLocation p_130088_1_, TextureMap p_130088_2_) {
/* 52 */     return this.tm.func_130088_a(p_130088_1_, p_130088_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_110580_a(ResourceLocation p_110580_1_, ITickableTextureObject p_110580_2_) {
/* 57 */     return this.tm.func_110580_a(p_110580_1_, p_110580_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_110579_a(ResourceLocation p_110579_1_, ITextureObject p_110579_2_) {
/* 62 */     return this.tm.func_110579_a(p_110579_1_, p_110579_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ITextureObject func_110581_b(ResourceLocation p_110581_1_) {
/* 67 */     return this.tm.func_110581_b(p_110581_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110578_a(String p_110578_1_, DynamicTexture p_110578_2_) {
/* 72 */     return this.tm.func_110578_a(p_110578_1_, p_110578_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_110550_d() {
/* 77 */     this.tm.func_110550_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_147645_c(ResourceLocation p_147645_1_) {
/* 82 */     this.tm.func_147645_c(p_147645_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_110549_a(IResourceManager p_110549_1_) {
/* 87 */     this.tm.func_110549_a(p_110549_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_TextureManagerDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */