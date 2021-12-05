/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import mcheli.weapon.MCH_EntityBomb;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderBomb
/*    */   extends MCH_RenderBulletBase
/*    */ {
/*    */   public void renderBullet(Entity entity, double posX, double posY, double posZ, float yaw, float partialTickTime) {
/* 31 */     if (!(entity instanceof MCH_EntityBomb))
/*    */       return; 
/* 33 */     MCH_EntityBomb bomb = (MCH_EntityBomb)entity;
/* 34 */     if (bomb.getInfo() == null)
/*    */       return; 
/* 36 */     GL11.glPushMatrix();
/*    */     
/* 38 */     GL11.glTranslated(posX, posY, posZ);
/* 39 */     GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 40 */     GL11.glRotatef(-entity.field_70125_A, -1.0F, 0.0F, 0.0F);
/*    */     
/* 42 */     if (bomb.isBomblet > 0 || (bomb.getInfo()).bomblet <= 0 || (bomb.getInfo()).bombletSTime > 0)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 48 */       renderModel((MCH_EntityBaseBullet)bomb);
/*    */     }
/*    */     
/* 51 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 56 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderBomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */