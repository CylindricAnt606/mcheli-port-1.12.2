/*    */ package mcheli.mcheli.chain;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.chain.MCH_EntityChain;
/*    */ import mcheli.wrapper.W_Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Vec3;
/*    */ import org.lwjgl.opengl.GL11;
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
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderChain
/*    */   extends W_Render
/*    */ {
/*    */   public void func_76986_a(Entity e, double posX, double posY, double posZ, float par8, float par9) {
/* 30 */     if (!(e instanceof MCH_EntityChain))
/* 31 */       return;  MCH_EntityChain chain = (MCH_EntityChain)e;
/* 32 */     if (chain.towedEntity == null || chain.towEntity == null)
/*    */       return; 
/* 34 */     GL11.glPushMatrix();
/*    */     
/* 36 */     GL11.glEnable(2884);
/* 37 */     GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
/* 38 */     GL11.glTranslated(chain.towedEntity.field_70142_S - RenderManager.field_78725_b, chain.towedEntity.field_70137_T - RenderManager.field_78726_c, chain.towedEntity.field_70136_U - RenderManager.field_78723_d);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     bindTexture("textures/chain.png");
/*    */     
/* 45 */     double dx = chain.towEntity.field_70142_S - chain.towedEntity.field_70142_S;
/* 46 */     double dy = chain.towEntity.field_70137_T - chain.towedEntity.field_70137_T;
/* 47 */     double dz = chain.towEntity.field_70136_U - chain.towedEntity.field_70136_U;
/* 48 */     double diff = Math.sqrt(dx * dx + dy * dy + dz * dz);
/* 49 */     float CHAIN_LEN = 0.95F;
/* 50 */     double x = dx * 0.949999988079071D / diff;
/* 51 */     double y = dy * 0.949999988079071D / diff;
/* 52 */     double z = dz * 0.949999988079071D / diff;
/* 53 */     while (diff > 0.949999988079071D) {
/*    */       
/* 55 */       GL11.glTranslated(x, y, z);
/*    */       
/* 57 */       GL11.glPushMatrix();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 66 */       Vec3 v = MCH_Lib.getYawPitchFromVec(x, y, z);
/* 67 */       GL11.glRotatef((float)v.field_72448_b, 0.0F, -1.0F, 0.0F);
/* 68 */       GL11.glRotatef((float)v.field_72449_c, 0.0F, 0.0F, 1.0F);
/*    */       
/* 70 */       MCH_ModelManager.render("chain");
/*    */       
/* 72 */       GL11.glPopMatrix();
/* 73 */       diff -= 0.949999988079071D;
/*    */     } 
/*    */     
/* 76 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 81 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\chain\MCH_RenderChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */