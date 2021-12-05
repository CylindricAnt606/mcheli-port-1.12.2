/*     */ package mcheli.mcheli.vehicle;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.vehicle.MCH_EntityVehicle;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.weapon.MCH_WeaponSet;
/*     */ import mcheli.wrapper.W_Lib;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderVehicle
/*     */   extends MCH_RenderAircraft
/*     */ {
/*     */   public void renderAircraft(MCH_EntityAircraft entity, double posX, double posY, double posZ, float yaw, float pitch, float roll, float tickTime) {
/*     */     MCH_EntityVehicle vehicle;
/*  38 */     MCH_VehicleInfo vehicleInfo = null;
/*  39 */     if (entity != null && entity instanceof MCH_EntityVehicle) {
/*     */       
/*  41 */       vehicle = (MCH_EntityVehicle)entity;
/*  42 */       vehicleInfo = vehicle.getVehicleInfo();
/*  43 */       if (vehicleInfo == null) {
/*     */         return;
/*     */       }
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/*  50 */     if (vehicle.field_70153_n != null && !vehicle.isDestroyed()) {
/*     */       
/*  52 */       vehicle.isUsedPlayer = true;
/*  53 */       vehicle.lastRiderYaw = vehicle.field_70153_n.field_70177_z;
/*  54 */       vehicle.lastRiderPitch = vehicle.field_70153_n.field_70125_A;
/*     */ 
/*     */     
/*     */     }
/*  58 */     else if (!vehicle.isUsedPlayer) {
/*     */       
/*  60 */       vehicle.lastRiderYaw = vehicle.field_70177_z;
/*  61 */       vehicle.lastRiderPitch = vehicle.field_70125_A;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  66 */     renderDebugHitBox((MCH_EntityAircraft)vehicle, posX, posY, posZ, yaw, pitch);
/*  67 */     renderDebugPilotSeat((MCH_EntityAircraft)vehicle, posX, posY, posZ, yaw, pitch, roll);
/*     */ 
/*     */     
/*  70 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */     
/*  73 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  74 */     GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  77 */     bindTexture("textures/vehicles/" + vehicle.getTextureName() + ".png", (MCH_EntityAircraft)vehicle);
/*     */     
/*  79 */     renderBody(vehicleInfo.model);
/*     */     
/*  81 */     MCH_WeaponSet ws = vehicle.getFirstSeatWeapon();
/*     */     
/*  83 */     drawPart(vehicle, vehicleInfo, yaw, pitch, ws, tickTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawPart(MCH_EntityVehicle vehicle, MCH_VehicleInfo info, float yaw, float pitch, MCH_WeaponSet ws, float tickTime) {
/*  90 */     float rotBrl = ws.prevRotBarrel + (ws.rotBarrel - ws.prevRotBarrel) * tickTime;
/*  91 */     int index = 0;
/*  92 */     for (MCH_VehicleInfo.VPart vp : info.partList)
/*     */     {
/*  94 */       index = drawPart(vp, vehicle, info, yaw, pitch, rotBrl, tickTime, ws, index);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   int drawPart(MCH_VehicleInfo.VPart vp, MCH_EntityVehicle vehicle, MCH_VehicleInfo info, float yaw, float pitch, float rotBrl, float tickTime, MCH_WeaponSet ws, int index) {
/* 100 */     GL11.glPushMatrix();
/*     */     
/* 102 */     float recoilBuf = 0.0F;
/*     */     
/* 104 */     if (index < ws.getWeaponNum()) {
/*     */       
/* 106 */       MCH_WeaponSet.Recoil r = ws.recoilBuf[index];
/* 107 */       recoilBuf = r.prevRecoilBuf + (r.recoilBuf - r.prevRecoilBuf) * tickTime;
/*     */     } 
/*     */     
/* 110 */     int bkIndex = index;
/*     */     
/* 112 */     if (vp.rotPitch || vp.rotYaw || vp.type == 1) {
/*     */       
/* 114 */       GL11.glTranslated(vp.pos.field_72450_a, vp.pos.field_72448_b, vp.pos.field_72449_c);
/*     */       
/* 116 */       if (vp.rotYaw)
/*     */       {
/* 118 */         GL11.glRotatef(-vehicle.lastRiderYaw + yaw, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 120 */       if (vp.rotPitch) {
/*     */         
/* 122 */         float p = MCH_Lib.RNG(vehicle.lastRiderPitch, info.minRotationPitch, info.maxRotationPitch);
/* 123 */         GL11.glRotatef(p - pitch, 1.0F, 0.0F, 0.0F);
/*     */       } 
/* 125 */       if (vp.type == 1)
/*     */       {
/* 127 */         GL11.glRotatef(rotBrl, 0.0F, 0.0F, -1.0F);
/*     */       }
/*     */       
/* 130 */       GL11.glTranslated(-vp.pos.field_72450_a, -vp.pos.field_72448_b, -vp.pos.field_72449_c);
/*     */     } 
/* 132 */     if (vp.type == 2)
/*     */     {
/* 134 */       GL11.glTranslated(0.0D, 0.0D, (-vp.recoilBuf * recoilBuf));
/*     */     }
/*     */     
/* 137 */     if (vp.type == 2 || vp.type == 3) index++;
/*     */     
/* 139 */     if (vp.child != null)
/*     */     {
/* 141 */       for (MCH_VehicleInfo.VPart vcp : vp.child)
/*     */       {
/* 143 */         index = drawPart(vcp, vehicle, info, yaw, pitch, rotBrl, recoilBuf, ws, index);
/*     */       }
/*     */     }
/*     */     
/* 147 */     if (vp.drawFP || !W_Lib.isClientPlayer(vehicle.field_70153_n) || !W_Lib.isFirstPerson())
/*     */     {
/*     */ 
/*     */       
/* 151 */       if (vp.type != 3 || !vehicle.isWeaponNotCooldown(ws, bkIndex)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 157 */         renderPart(vp.model, info.model, vp.modelName);
/* 158 */         MCH_ModelManager.render("vehicles", vp.modelName);
/*     */       } 
/*     */     }
/* 161 */     GL11.glPopMatrix();
/* 162 */     return index;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 167 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_RenderVehicle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */