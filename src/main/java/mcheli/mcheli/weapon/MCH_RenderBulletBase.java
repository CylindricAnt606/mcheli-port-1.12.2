/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.MCH_Color;
/*    */ import mcheli.weapon.MCH_BulletModel;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import mcheli.wrapper.W_Block;
/*    */ import mcheli.wrapper.W_Render;
/*    */ import mcheli.wrapper.W_WorldFunc;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public abstract class MCH_RenderBulletBase
/*    */   extends W_Render {
/*    */   public void func_76986_a(Entity e, double var2, double var4, double var6, float var8, float var9) {
/* 16 */     if (e instanceof MCH_EntityBaseBullet && ((MCH_EntityBaseBullet)e).getInfo() != null) {
/*    */       
/* 18 */       MCH_Color c = (((MCH_EntityBaseBullet)e).getInfo()).color;
/*    */       
/* 20 */       for (int y = 0; y < 3; y++) {
/*    */         
/* 22 */         Block b = W_WorldFunc.getBlock(e.field_70170_p, (int)(e.field_70165_t + 0.5D), (int)(e.field_70163_u + 1.5D - y), (int)(e.field_70161_v + 0.5D));
/* 23 */         if (b != null && b == W_Block.getWater()) {
/*    */           
/* 25 */           c = (((MCH_EntityBaseBullet)e).getInfo()).colorInWater;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 30 */       GL11.glColor4f(c.r, c.g, c.b, c.a);
/*    */     }
/*    */     else {
/*    */       
/* 34 */       GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*    */     } 
/*    */     
/* 37 */     GL11.glAlphaFunc(516, 0.001F);
/*    */     
/* 39 */     GL11.glEnable(2884);
/*    */ 
/*    */     
/* 42 */     GL11.glEnable(3042);
/* 43 */     int srcBlend = GL11.glGetInteger(3041);
/* 44 */     int dstBlend = GL11.glGetInteger(3040);
/* 45 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 47 */     renderBullet(e, var2, var4, var6, var8, var9);
/*    */     
/* 49 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*    */     
/* 51 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 52 */     GL11.glDisable(3042);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderModel(MCH_EntityBaseBullet e) {
/* 57 */     MCH_BulletModel model = e.getBulletModel();
/* 58 */     if (model != null) {
/*    */       
/* 60 */       bindTexture("textures/bullets/" + model.name + ".png");
/* 61 */       model.model.renderAll();
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract void renderBullet(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderBulletBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */