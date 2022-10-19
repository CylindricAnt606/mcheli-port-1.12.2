/*     */ package mcheli.mcheli.plane;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_PlaneInfo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCP_RenderPlane
/*     */   extends MCH_RenderAircraft
/*     */ {
/*     */   public void renderAircraft(MCH_EntityAircraft entity, double posX, double posY, double posZ, float yaw, float pitch, float roll, float tickTime) {
/*     */     MCP_EntityPlane plane;
/*  39 */     MCP_PlaneInfo planeInfo = null;
/*  40 */     if (entity != null && entity instanceof MCP_EntityPlane) {
/*     */       
/*  42 */       plane = (MCP_EntityPlane)entity;
/*  43 */       planeInfo = plane.getPlaneInfo();
/*  44 */       if (planeInfo == null) {
/*     */         return;
/*     */       }
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  52 */     renderDebugHitBox((MCH_EntityAircraft)plane, posX, posY, posZ, yaw, pitch);
/*  53 */     renderDebugPilotSeat((MCH_EntityAircraft)plane, posX, posY, posZ, yaw, pitch, roll);
/*     */ 
/*     */     
/*  56 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */     
/*  59 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  60 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  61 */     GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
/*     */ 
/*     */     
/*  64 */     bindTexture("textures/planes/" + plane.getTextureName() + ".png", (MCH_EntityAircraft)plane);
/*     */ 
/*     */     
/*  67 */     if (planeInfo.haveNozzle() && plane.partNozzle != null)
/*     */     {
/*  69 */       renderNozzle(plane, planeInfo, tickTime);
/*     */     }
/*     */ 
/*     */     
/*  73 */     if (planeInfo.haveWing() && plane.partWing != null)
/*     */     {
/*  75 */       renderWing(plane, planeInfo, tickTime);
/*     */     }
/*     */ 
/*     */     
/*  79 */     if (planeInfo.haveRotor() && plane.partNozzle != null)
/*     */     {
/*  81 */       renderRotor(plane, planeInfo, tickTime);
/*     */     }
/*     */     
/*  84 */     renderBody(planeInfo.model);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderRotor(MCP_EntityPlane plane, MCP_PlaneInfo planeInfo, float tickTime) {
/*  89 */     float rot = plane.getNozzleRotation();
/*  90 */     float prevRot = plane.getPrevNozzleRotation();
/*  91 */     for (MCP_PlaneInfo.Rotor r : planeInfo.rotorList) {
/*     */       
/*  93 */       GL11.glPushMatrix();
/*     */       
/*  95 */       GL11.glTranslated(r.pos.field_72450_a, r.pos.field_72448_b, r.pos.field_72449_c);
/*     */       
/*  97 */       GL11.glRotatef((prevRot + (rot - prevRot) * tickTime) * r.maxRotFactor, (float)r.rot.field_72450_a, (float)r.rot.field_72448_b, (float)r.rot.field_72449_c);
/*     */ 
/*     */       
/* 100 */       GL11.glTranslated(-r.pos.field_72450_a, -r.pos.field_72448_b, -r.pos.field_72449_c);
/*     */       
/* 102 */       renderPart(r.model, planeInfo.model, r.modelName);
/*     */       
/* 104 */       for (MCP_PlaneInfo.Blade b : r.blades) {
/*     */         
/* 106 */         float br = plane.prevRotationRotor;
/* 107 */         br += (plane.rotationRotor - plane.prevRotationRotor) * tickTime;
/*     */         
/* 109 */         GL11.glPushMatrix();
/*     */         
/* 111 */         GL11.glTranslated(b.pos.field_72450_a, b.pos.field_72448_b, b.pos.field_72449_c);
/*     */         
/* 113 */         GL11.glRotatef(br, (float)b.rot.field_72450_a, (float)b.rot.field_72448_b, (float)b.rot.field_72449_c);
/*     */         
/* 115 */         GL11.glTranslated(-b.pos.field_72450_a, -b.pos.field_72448_b, -b.pos.field_72449_c);
/*     */ 
/*     */         
/* 118 */         for (int i = 0; i < b.numBlade; i++) {
/*     */           
/* 120 */           GL11.glTranslated(b.pos.field_72450_a, b.pos.field_72448_b, b.pos.field_72449_c);
/* 121 */           GL11.glRotatef(b.rotBlade, (float)b.rot.field_72450_a, (float)b.rot.field_72448_b, (float)b.rot.field_72449_c);
/*     */           
/* 123 */           GL11.glTranslated(-b.pos.field_72450_a, -b.pos.field_72448_b, -b.pos.field_72449_c);
/*     */           
/* 125 */           renderPart(b.model, planeInfo.model, b.modelName);
/*     */         } 
/* 127 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 130 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderWing(MCP_EntityPlane plane, MCP_PlaneInfo planeInfo, float tickTime) {
/* 135 */     float rot = plane.getWingRotation();
/* 136 */     float prevRot = plane.getPrevWingRotation();
/* 137 */     for (MCP_PlaneInfo.Wing w : planeInfo.wingList) {
/*     */       
/* 139 */       GL11.glPushMatrix();
/*     */       
/* 141 */       GL11.glTranslated(w.pos.field_72450_a, w.pos.field_72448_b, w.pos.field_72449_c);
/*     */       
/* 143 */       GL11.glRotatef((prevRot + (rot - prevRot) * tickTime) * w.maxRotFactor, (float)w.rot.field_72450_a, (float)w.rot.field_72448_b, (float)w.rot.field_72449_c);
/*     */ 
/*     */       
/* 146 */       GL11.glTranslated(-w.pos.field_72450_a, -w.pos.field_72448_b, -w.pos.field_72449_c);
/*     */       
/* 148 */       renderPart(w.model, planeInfo.model, w.modelName);
/*     */       
/* 150 */       if (w.pylonList != null)
/*     */       {
/* 152 */         for (MCP_PlaneInfo.Pylon p : w.pylonList) {
/*     */           
/* 154 */           GL11.glPushMatrix();
/*     */           
/* 156 */           GL11.glTranslated(p.pos.field_72450_a, p.pos.field_72448_b, p.pos.field_72449_c);
/*     */           
/* 158 */           GL11.glRotatef((prevRot + (rot - prevRot) * tickTime) * p.maxRotFactor, (float)p.rot.field_72450_a, (float)p.rot.field_72448_b, (float)p.rot.field_72449_c);
/*     */ 
/*     */           
/* 161 */           GL11.glTranslated(-p.pos.field_72450_a, -p.pos.field_72448_b, -p.pos.field_72449_c);
/*     */           
/* 163 */           renderPart(p.model, planeInfo.model, p.modelName);
/*     */           
/* 165 */           GL11.glPopMatrix();
/*     */         } 
/*     */       }
/*     */       
/* 169 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderNozzle(MCP_EntityPlane plane, MCP_PlaneInfo planeInfo, float tickTime) {
/* 174 */     float rot = plane.getNozzleRotation();
/* 175 */     float prevRot = plane.getPrevNozzleRotation();
/* 176 */     for (MCH_AircraftInfo.DrawnPart n : planeInfo.nozzles) {
/*     */       
/* 178 */       GL11.glPushMatrix();
/*     */       
/* 180 */       GL11.glTranslated(n.pos.field_72450_a, n.pos.field_72448_b, n.pos.field_72449_c);
/*     */       
/* 182 */       GL11.glRotatef(prevRot + (rot - prevRot) * tickTime, (float)n.rot.field_72450_a, (float)n.rot.field_72448_b, (float)n.rot.field_72449_c);
/*     */ 
/*     */       
/* 185 */       GL11.glTranslated(-n.pos.field_72450_a, -n.pos.field_72448_b, -n.pos.field_72449_c);
/*     */       
/* 187 */       renderPart(n.model, planeInfo.model, n.modelName);
/*     */       
/* 189 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 195 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_RenderPlane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */