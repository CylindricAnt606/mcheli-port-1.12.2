/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.weapon.MCH_EntityCartridge;
/*    */ import mcheli.wrapper.W_Render;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_RenderCartridge
/*    */   extends W_Render
/*    */ {
/*    */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/* 27 */     MCH_EntityCartridge cartridge = null;
/*    */ 
/*    */     
/* 30 */     cartridge = (MCH_EntityCartridge)entity;
/*    */     
/* 32 */     if (cartridge.model != null && !cartridge.texture_name.isEmpty()) {
/*    */       
/* 34 */       GL11.glPushMatrix();
/*    */ 
/*    */       
/* 37 */       GL11.glTranslated(posX, posY, posZ);
/*    */ 
/*    */       
/* 40 */       GL11.glScalef(cartridge.getScale(), cartridge.getScale(), cartridge.getScale());
/*    */ 
/*    */ 
/*    */       
/* 44 */       float prevYaw = cartridge.field_70126_B;
/* 45 */       if (cartridge.field_70177_z - prevYaw < -180.0F) { prevYaw -= 360.0F; }
/* 46 */       else if (prevYaw - cartridge.field_70177_z < -180.0F) { prevYaw += 360.0F; }
/*    */       
/* 48 */       float yaw = -(prevYaw + (cartridge.field_70177_z - prevYaw) * tickTime);
/* 49 */       float pitch = cartridge.field_70127_C + (cartridge.field_70125_A - cartridge.field_70127_C) * tickTime;
/* 50 */       GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
/* 51 */       GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*    */       
/* 53 */       bindTexture("textures/bullets/" + cartridge.texture_name + ".png");
/* 54 */       cartridge.model.renderAll();
/* 55 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity) {
/* 62 */     return TEX_DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_RenderCartridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */