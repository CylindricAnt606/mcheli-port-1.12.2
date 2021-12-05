/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import mcheli.weapon.MCH_RenderBulletBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderASMissile
/*    */   extends MCH_RenderBulletBase
/*    */ {
/*    */   public void renderBullet(Entity entity, double posX, double posY, double posZ, float yaw, float partialTickTime) {
/* 31 */     if (entity instanceof MCH_EntityBaseBullet) {
/*    */       
/* 33 */       MCH_EntityBaseBullet bullet = (MCH_EntityBaseBullet)entity;
/*    */       
/* 35 */       GL11.glPushMatrix();
/* 36 */       GL11.glTranslated(posX, posY, posZ);
/*    */       
/* 38 */       GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 39 */       GL11.glRotatef(-entity.field_70125_A, -1.0F, 0.0F, 0.0F);
/*    */       
/* 41 */       renderModel(bullet);
/*    */       
/* 43 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 49 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderASMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */