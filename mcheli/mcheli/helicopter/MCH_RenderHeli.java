/*     */ package mcheli.mcheli.helicopter;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.aircraft.MCH_Blade;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.aircraft.MCH_Rotor;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderHeli
/*     */   extends MCH_RenderAircraft
/*     */ {
/*     */   public void renderAircraft(MCH_EntityAircraft entity, double posX, double posY, double posZ, float yaw, float pitch, float roll, float tickTime) {
/*     */     MCH_EntityHeli heli;
/*  33 */     MCH_HeliInfo heliInfo = null;
/*  34 */     if (entity != null && entity instanceof MCH_EntityHeli) {
/*     */       
/*  36 */       heli = (MCH_EntityHeli)entity;
/*  37 */       heliInfo = heli.getHeliInfo();
/*  38 */       if (heliInfo == null) {
/*     */         return;
/*     */       }
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  46 */     renderDebugHitBox((MCH_EntityAircraft)heli, posX, posY, posZ, yaw, pitch);
/*  47 */     renderDebugPilotSeat((MCH_EntityAircraft)heli, posX, posY, posZ, yaw, pitch, roll);
/*     */ 
/*     */     
/*  50 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */     
/*  53 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  54 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  55 */     GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
/*     */ 
/*     */     
/*  58 */     bindTexture("textures/helicopters/" + heli.getTextureName() + ".png", (MCH_EntityAircraft)heli);
/*     */     
/*  60 */     renderBody(heliInfo.model);
/*     */ 
/*     */     
/*  63 */     drawModelBlade(heli, heliInfo, tickTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawModelBlade(MCH_EntityHeli heli, MCH_HeliInfo info, float tickTime) {
/*  68 */     for (int i = 0; i < heli.rotors.length && i < info.rotorList.size(); i++) {
/*     */       
/*  70 */       MCH_HeliInfo.Rotor rotorInfo = info.rotorList.get(i);
/*  71 */       MCH_Rotor rotor = heli.rotors[i];
/*     */       
/*  73 */       GL11.glPushMatrix();
/*     */ 
/*     */       
/*  76 */       if (rotorInfo.oldRenderMethod)
/*     */       {
/*  78 */         GL11.glTranslated(rotorInfo.pos.field_72450_a, rotorInfo.pos.field_72448_b, rotorInfo.pos.field_72449_c);
/*     */       }
/*     */       
/*  81 */       for (MCH_Blade b : rotor.blades) {
/*     */         
/*  83 */         GL11.glPushMatrix();
/*     */         
/*  85 */         float rot = b.getRotation();
/*  86 */         float prevRot = b.getPrevRotation();
/*  87 */         if (rot - prevRot < -180.0F) { prevRot -= 360.0F; }
/*  88 */         else if (prevRot - rot < -180.0F) { prevRot += 360.0F; }
/*     */ 
/*     */         
/*  91 */         if (!rotorInfo.oldRenderMethod)
/*     */         {
/*  93 */           GL11.glTranslated(rotorInfo.pos.field_72450_a, rotorInfo.pos.field_72448_b, rotorInfo.pos.field_72449_c);
/*     */         }
/*     */         
/*  96 */         GL11.glRotatef(prevRot + (rot - prevRot) * tickTime, (float)rotorInfo.rot.field_72450_a, (float)rotorInfo.rot.field_72448_b, (float)rotorInfo.rot.field_72449_c);
/*     */ 
/*     */ 
/*     */         
/* 100 */         if (!rotorInfo.oldRenderMethod)
/*     */         {
/* 102 */           GL11.glTranslated(-rotorInfo.pos.field_72450_a, -rotorInfo.pos.field_72448_b, -rotorInfo.pos.field_72449_c);
/*     */         }
/*     */         
/* 105 */         renderPart(rotorInfo.model, info.model, rotorInfo.modelName);
/*     */ 
/*     */         
/* 108 */         GL11.glPopMatrix();
/*     */       } 
/* 110 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 116 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_RenderHeli.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */