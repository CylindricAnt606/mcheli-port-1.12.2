/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.MCH_ModelManager;
/*    */ import mcheli.weapon.MCH_EntityA10;
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
/*    */ public class MCH_RenderA10
/*    */   extends MCH_RenderBulletBase
/*    */ {
/*    */   public void renderBullet(Entity e, double posX, double posY, double posZ, float par8, float tickTime) {
/* 32 */     if (!(e instanceof MCH_EntityA10))
/*    */       return; 
/* 34 */     if (!((MCH_EntityA10)e).isRender())
/*    */       return; 
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glTranslated(posX, posY, posZ);
/*    */     
/* 39 */     float yaw = -(e.field_70126_B + (e.field_70177_z - e.field_70126_B) * tickTime);
/* 40 */     float pitch = -(e.field_70127_C + (e.field_70125_A - e.field_70127_C) * tickTime);
/* 41 */     GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 42 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*    */ 
/*    */     
/* 45 */     bindTexture("textures/bullets/a10.png");
/* 46 */     MCH_ModelManager.render("a-10");
/*    */     
/* 48 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 53 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderA10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */