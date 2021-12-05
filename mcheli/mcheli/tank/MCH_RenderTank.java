/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_EntityWheel;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderTank
/*     */   extends MCH_RenderAircraft
/*     */ {
/*     */   public void renderAircraft(MCH_EntityAircraft entity, double posX, double posY, double posZ, float yaw, float pitch, float roll, float tickTime) {
/*     */     MCH_EntityTank tank;
/*  40 */     MCH_TankInfo tankInfo = null;
/*  41 */     if (entity != null && entity instanceof MCH_EntityTank) {
/*     */       
/*  43 */       tank = (MCH_EntityTank)entity;
/*  44 */       tankInfo = tank.getTankInfo();
/*  45 */       if (tankInfo == null) {
/*     */         return;
/*     */       }
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  53 */     renderWheel(tank, posX, posY, posZ);
/*  54 */     renderDebugHitBox((MCH_EntityAircraft)tank, posX, posY, posZ, yaw, pitch);
/*  55 */     renderDebugPilotSeat((MCH_EntityAircraft)tank, posX, posY, posZ, yaw, pitch, roll);
/*     */ 
/*     */     
/*  58 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */     
/*  61 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  62 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  63 */     GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
/*     */ 
/*     */     
/*  66 */     bindTexture("textures/tanks/" + tank.getTextureName() + ".png", (MCH_EntityAircraft)tank);
/*     */     
/*  68 */     renderBody(tankInfo.model);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderWheel(MCH_EntityTank tank, double posX, double posY, double posZ) {
/*  73 */     if (!MCH_Config.TestMode.prmBool)
/*  74 */       return;  if (debugModel == null)
/*     */       return; 
/*  76 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 0.5F);
/*  77 */     for (MCH_EntityWheel w : tank.WheelMng.wheels) {
/*     */       
/*  79 */       GL11.glPushMatrix();
/*  80 */       GL11.glTranslated(w.field_70165_t - tank.field_70165_t + posX, w.field_70163_u - tank.field_70163_u + posY + 0.25D, w.field_70161_v - tank.field_70161_v + posZ);
/*     */       
/*  82 */       GL11.glScalef(w.field_70130_N, w.field_70131_O / 2.0F, w.field_70130_N);
/*     */       
/*  84 */       bindTexture("textures/seat_pilot.png");
/*  85 */       debugModel.renderAll();
/*  86 */       GL11.glPopMatrix();
/*     */     } 
/*  88 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*  89 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  90 */     tessellator.func_78371_b(1);
/*  91 */     Vec3 wp = tank.getTransformedPosition(tank.WheelMng.weightedCenter);
/*  92 */     wp.field_72450_a -= tank.field_70165_t;
/*  93 */     wp.field_72448_b -= tank.field_70163_u;
/*  94 */     wp.field_72449_c -= tank.field_70161_v;
/*  95 */     for (int i = 0; i < tank.WheelMng.wheels.length / 2; i++) {
/*     */       
/*  97 */       tessellator.func_78384_a((((i & 0x4) > 0) ? 16711680 : 0) | (((i & 0x2) > 0) ? 65280 : 0) | (((i & 0x1) > 0) ? 255 : 0), 192);
/*     */       
/*  99 */       MCH_EntityWheel w1 = tank.WheelMng.wheels[i * 2 + 0];
/* 100 */       MCH_EntityWheel w2 = tank.WheelMng.wheels[i * 2 + 1];
/* 101 */       if (w1.isPlus) {
/*     */         
/* 103 */         tessellator.func_78377_a(w2.field_70165_t - tank.field_70165_t + posX, w2.field_70163_u - tank.field_70163_u + posY, w2.field_70161_v - tank.field_70161_v + posZ);
/* 104 */         tessellator.func_78377_a(w1.field_70165_t - tank.field_70165_t + posX, w1.field_70163_u - tank.field_70163_u + posY, w1.field_70161_v - tank.field_70161_v + posZ);
/* 105 */         tessellator.func_78377_a(w1.field_70165_t - tank.field_70165_t + posX, w1.field_70163_u - tank.field_70163_u + posY, w1.field_70161_v - tank.field_70161_v + posZ);
/* 106 */         tessellator.func_78377_a(posX + wp.field_72450_a, posY + wp.field_72448_b, posZ + wp.field_72449_c);
/* 107 */         tessellator.func_78377_a(posX + wp.field_72450_a, posY + wp.field_72448_b, posZ + wp.field_72449_c);
/* 108 */         tessellator.func_78377_a(w2.field_70165_t - tank.field_70165_t + posX, w2.field_70163_u - tank.field_70163_u + posY, w2.field_70161_v - tank.field_70161_v + posZ);
/*     */       }
/*     */       else {
/*     */         
/* 112 */         tessellator.func_78377_a(w1.field_70165_t - tank.field_70165_t + posX, w1.field_70163_u - tank.field_70163_u + posY, w1.field_70161_v - tank.field_70161_v + posZ);
/* 113 */         tessellator.func_78377_a(w2.field_70165_t - tank.field_70165_t + posX, w2.field_70163_u - tank.field_70163_u + posY, w2.field_70161_v - tank.field_70161_v + posZ);
/* 114 */         tessellator.func_78377_a(w2.field_70165_t - tank.field_70165_t + posX, w2.field_70163_u - tank.field_70163_u + posY, w2.field_70161_v - tank.field_70161_v + posZ);
/* 115 */         tessellator.func_78377_a(posX + wp.field_72450_a, posY + wp.field_72448_b, posZ + wp.field_72449_c);
/* 116 */         tessellator.func_78377_a(posX + wp.field_72450_a, posY + wp.field_72448_b, posZ + wp.field_72449_c);
/* 117 */         tessellator.func_78377_a(w1.field_70165_t - tank.field_70165_t + posX, w1.field_70163_u - tank.field_70163_u + posY, w1.field_70161_v - tank.field_70161_v + posZ);
/*     */       } 
/*     */     } 
/* 120 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 125 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_RenderTank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */