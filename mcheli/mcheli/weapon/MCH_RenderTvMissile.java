/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_EntitySeat;
/*    */ import mcheli.uav.MCH_EntityUavStation;
/*    */ import mcheli.weapon.MCH_EntityBaseBullet;
/*    */ import mcheli.weapon.MCH_RenderBulletBase;
/*    */ import net.minecraft.client.Minecraft;
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
/*    */ public class MCH_RenderTvMissile
/*    */   extends MCH_RenderBulletBase
/*    */ {
/*    */   public void renderBullet(Entity entity, double posX, double posY, double posZ, float par8, float par9) {
/* 34 */     MCH_EntityAircraft ac = null;
/* 35 */     Entity ridingEntity = (Minecraft.func_71410_x()).field_71439_g.field_70154_o;
/* 36 */     if (ridingEntity instanceof MCH_EntityAircraft) {
/*    */       
/* 38 */       ac = (MCH_EntityAircraft)ridingEntity;
/*    */     }
/* 40 */     else if (ridingEntity instanceof MCH_EntitySeat) {
/*    */       
/* 42 */       ac = ((MCH_EntitySeat)ridingEntity).getParent();
/*    */     }
/* 44 */     else if (ridingEntity instanceof MCH_EntityUavStation) {
/*    */       
/* 46 */       ac = ((MCH_EntityUavStation)ridingEntity).getControlAircract();
/*    */     } 
/*    */     
/* 49 */     if (ac != null && !ac.isRenderBullet(entity, (Entity)(Minecraft.func_71410_x()).field_71439_g)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 54 */     if (entity instanceof MCH_EntityBaseBullet) {
/*    */       
/* 56 */       MCH_EntityBaseBullet bullet = (MCH_EntityBaseBullet)entity;
/*    */       
/* 58 */       GL11.glPushMatrix();
/* 59 */       GL11.glTranslated(posX, posY, posZ);
/* 60 */       GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 61 */       GL11.glRotatef(-entity.field_70125_A, -1.0F, 0.0F, 0.0F);
/*    */       
/* 63 */       renderModel(bullet);
/*    */       
/* 65 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 71 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderTvMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */