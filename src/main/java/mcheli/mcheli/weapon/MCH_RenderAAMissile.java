/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.weapon.MCH_EntityAAMissile;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import mcheli.weapon.MCH_RenderBulletBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderAAMissile
/*    */   extends MCH_RenderBulletBase
/*    */ {
/*    */   public void renderBullet(Entity entity, double posX, double posY, double posZ, float par8, float par9) {
/* 32 */     if (!(entity instanceof MCH_EntityAAMissile)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 37 */     MCH_EntityAAMissile aam = (MCH_EntityAAMissile)entity;
/*    */     
/* 39 */     double mx = aam.prevMotionX + (aam.field_70159_w - aam.prevMotionX) * par9;
/* 40 */     double my = aam.prevMotionY + (aam.field_70181_x - aam.prevMotionY) * par9;
/* 41 */     double mz = aam.prevMotionZ + (aam.field_70179_y - aam.prevMotionZ) * par9;
/*    */     
/* 43 */     GL11.glPushMatrix();
/* 44 */     GL11.glTranslated(posX, posY, posZ);
/*    */     
/* 46 */     Vec3 v = MCH_Lib.getYawPitchFromVec(mx, my, mz);
/* 47 */     GL11.glRotatef((float)v.field_72448_b - 90.0F, 0.0F, -1.0F, 0.0F);
/* 48 */     GL11.glRotatef((float)v.field_72449_c, -1.0F, 0.0F, 0.0F);
/*    */     
/* 50 */     renderModel((MCH_EntityBaseBullet)aam);
/*    */     
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 57 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderAAMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */