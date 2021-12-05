/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ import mcheli.hud.MCH_HudItem;
/*    */ import mcheli.wrapper.W_TextureUtil;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_HudItemTexture
/*    */   extends MCH_HudItem
/*    */ {
/*    */   private final String name;
/*    */   private final String left;
/*    */   private final String top;
/*    */   private final String width;
/*    */   private final String height;
/*    */   private final String uLeft;
/*    */   private final String vTop;
/*    */   private final String uWidth;
/*    */   private final String vHeight;
/*    */   private final String rot;
/*    */   private int textureWidth;
/*    */   private int textureHeight;
/*    */   
/*    */   public MCH_HudItemTexture(int fileLine, String name, String left, String top, String width, String height, String uLeft, String vTop, String uWidth, String vHeight, String rot) {
/* 29 */     super(fileLine);
/* 30 */     this.name = name;
/* 31 */     this.left = toFormula(left);
/* 32 */     this.top = toFormula(top);
/* 33 */     this.width = toFormula(width);
/* 34 */     this.height = toFormula(height);
/* 35 */     this.uLeft = toFormula(uLeft);
/* 36 */     this.vTop = toFormula(vTop);
/* 37 */     this.uWidth = toFormula(uWidth);
/* 38 */     this.vHeight = toFormula(vHeight);
/* 39 */     this.rot = toFormula(rot);
/* 40 */     this.textureWidth = this.textureHeight = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 46 */     GL11.glEnable(3042);
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 49 */     if (this.textureWidth == 0 || this.textureHeight == 0) {
/*    */       
/* 51 */       int w = 0;
/* 52 */       int h = 0;
/* 53 */       W_TextureUtil.TextureParam prm = W_TextureUtil.getTextureInfo("mcheli", "textures/gui/" + this.name + ".png");
/* 54 */       if (prm != null) {
/*    */         
/* 56 */         w = prm.width;
/* 57 */         h = prm.height;
/*    */       } 
/* 59 */       this.textureWidth = (w > 0) ? w : 256;
/* 60 */       this.textureHeight = (h > 0) ? h : 256;
/*    */     } 
/*    */     
/* 63 */     this; this; drawTexture(this.name, centerX + calc(this.left), centerY + calc(this.top), calc(this.width), calc(this.height), calc(this.uLeft), calc(this.vTop), calc(this.uWidth), calc(this.vHeight), (float)calc(this.rot), this.textureWidth, this.textureHeight);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */