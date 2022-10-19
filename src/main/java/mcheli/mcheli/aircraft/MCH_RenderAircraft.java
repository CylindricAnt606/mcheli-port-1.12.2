/*      */ package mcheli.mcheli.aircraft;
/*      */ 
/*      */ import mcheli.MCH_ClientCommonTickHandler;
/*      */ import mcheli.MCH_ClientEventHook;
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.aircraft.MCH_AircraftInfo;
/*      */ import mcheli.aircraft.MCH_BoundingBox;
/*      */ import mcheli.aircraft.MCH_EntityAircraft;
/*      */ import mcheli.aircraft.MCH_EntitySeat;
/*      */ import mcheli.aircraft.MCH_IEntityCanRideAircraft;
/*      */ import mcheli.aircraft.MCH_SeatInfo;
/*      */ import mcheli.gui.MCH_Gui;
/*      */ import mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
/*      */ import mcheli.multiplay.MCH_GuiTargetMarker;
/*      */ import mcheli.uav.MCH_EntityUavStation;
/*      */ import mcheli.weapon.MCH_WeaponGuidanceSystem;
/*      */ import mcheli.weapon.MCH_WeaponSet;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_EntityRenderer;
/*      */ import mcheli.wrapper.W_Lib;
/*      */ import mcheli.wrapper.W_MOD;
/*      */ import mcheli.wrapper.W_Render;
/*      */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.entity.RenderManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraftforge.client.model.IModelCustom;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class MCH_RenderAircraft
/*      */   extends W_Render
/*      */ {
/*      */   public static boolean renderingEntity = false;
/*   51 */   public static IModelCustom debugModel = null;
/*      */ 
/*      */   
/*      */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/*   55 */     MCH_EntityAircraft ac = (MCH_EntityAircraft)entity;
/*   56 */     MCH_AircraftInfo info = ac.getAcInfo();
/*   57 */     if (info != null) {
/*      */       
/*   59 */       GL11.glPushMatrix();
/*      */       
/*   61 */       float yaw = calcRot(ac.getRotYaw(), ac.field_70126_B, tickTime);
/*   62 */       float pitch = ac.calcRotPitch(tickTime);
/*   63 */       float roll = calcRot(ac.getRotRoll(), ac.prevRotationRoll, tickTime);
/*      */       
/*   65 */       if (MCH_Config.EnableModEntityRender.prmBool)
/*      */       {
/*   67 */         renderRiddenEntity(ac, tickTime, yaw, pitch + info.entityPitch, roll + info.entityRoll, info.entityWidth, info.entityHeight);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*   72 */       if (!shouldSkipRender(entity)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*   88 */         setCommonRenderParam(info.smoothShading, ac.func_70070_b(tickTime));
/*      */         
/*   90 */         if (ac.isDestroyed()) {
/*      */           
/*   92 */           GL11.glColor4f(0.15F, 0.15F, 0.15F, 1.0F);
/*      */         }
/*      */         else {
/*      */           
/*   96 */           GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*      */         } 
/*   98 */         renderAircraft(ac, posX, posY, posZ, yaw, pitch, roll, tickTime);
/*      */         
/*  100 */         renderCommonPart(ac, info, posX, posY, posZ, tickTime);
/*      */         
/*  102 */         renderLight(posX, posY, posZ, tickTime, ac, info);
/*      */         
/*  104 */         restoreCommonRenderParam();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  110 */       GL11.glPopMatrix();
/*      */       
/*  112 */       MCH_GuiTargetMarker.addMarkEntityPos(1, entity, posX, posY + info.markerHeight, posZ);
/*  113 */       MCH_ClientLightWeaponTickHandler.markEntity(entity, posX, posY, posZ);
/*      */ 
/*      */ 
/*      */       
/*  117 */       renderEntityMarker((Entity)ac);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean shouldSkipRender(Entity entity) {
/*  124 */     if (entity instanceof MCH_IEntityCanRideAircraft) {
/*      */       
/*  126 */       MCH_IEntityCanRideAircraft e = (MCH_IEntityCanRideAircraft)entity;
/*  127 */       if (e.isSkipNormalRender())
/*      */       {
/*  129 */         return !renderingEntity;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  134 */     else if (entity.getClass().toString().indexOf("flansmod.common.driveables.EntityPlane") > 0 || entity.getClass().toString().indexOf("flansmod.common.driveables.EntityVehicle") > 0) {
/*      */ 
/*      */       
/*  137 */       if (entity.field_70154_o instanceof MCH_EntitySeat)
/*      */       {
/*  139 */         return !renderingEntity;
/*      */       }
/*      */     } 
/*      */     
/*  143 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_76979_b(Entity entity, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_) {
/*  149 */     if (entity.func_90999_ad())
/*      */     {
/*  151 */       renderEntityOnFire(entity, p_76979_2_, p_76979_4_, p_76979_6_, p_76979_9_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderEntityOnFire(Entity entity, double x, double y, double z, float tick) {
/*  157 */     GL11.glDisable(2896);
/*  158 */     IIcon iicon = Blocks.field_150480_ab.func_149840_c(0);
/*  159 */     IIcon iicon1 = Blocks.field_150480_ab.func_149840_c(1);
/*  160 */     GL11.glPushMatrix();
/*  161 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  162 */     float f1 = entity.field_70130_N * 1.4F;
/*  163 */     GL11.glScalef(f1 * 2.0F, f1 * 2.0F, f1 * 2.0F);
/*  164 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  165 */     float f2 = 1.5F;
/*  166 */     float f3 = 0.0F;
/*  167 */     float f4 = entity.field_70131_O / f1;
/*  168 */     float f5 = (float)(entity.field_70163_u + entity.field_70121_D.field_72338_b);
/*  169 */     GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  170 */     GL11.glTranslatef(0.0F, 0.0F, -0.3F + (int)f4 * 0.02F);
/*  171 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  172 */     float f6 = 0.0F;
/*  173 */     int i = 0;
/*  174 */     tessellator.func_78382_b();
/*      */     
/*  176 */     while (f4 > 0.0F) {
/*      */       
/*  178 */       IIcon iicon2 = (i % 2 == 0) ? iicon : iicon1;
/*  179 */       func_110776_a(TextureMap.field_110575_b);
/*  180 */       float f7 = iicon2.func_94209_e();
/*  181 */       float f8 = iicon2.func_94206_g();
/*  182 */       float f9 = iicon2.func_94212_f();
/*  183 */       float f10 = iicon2.func_94210_h();
/*      */       
/*  185 */       if (i / 2 % 2 == 0) {
/*      */         
/*  187 */         float f11 = f9;
/*  188 */         f9 = f7;
/*  189 */         f7 = f11;
/*      */       } 
/*      */       
/*  192 */       tessellator.func_78374_a((f2 - f3), (0.0F - f5), f6, f9, f10);
/*  193 */       tessellator.func_78374_a((-f2 - f3), (0.0F - f5), f6, f7, f10);
/*  194 */       tessellator.func_78374_a((-f2 - f3), (1.4F - f5), f6, f7, f8);
/*  195 */       tessellator.func_78374_a((f2 - f3), (1.4F - f5), f6, f9, f8);
/*  196 */       f4 -= 0.45F;
/*  197 */       f5 -= 0.45F;
/*  198 */       f2 *= 0.9F;
/*  199 */       f6 += 0.03F;
/*  200 */       i++;
/*      */     } 
/*      */     
/*  203 */     tessellator.func_78381_a();
/*  204 */     GL11.glPopMatrix();
/*  205 */     GL11.glEnable(2896);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderLight(double x, double y, double z, float tickTime, MCH_EntityAircraft ac, MCH_AircraftInfo info) {
/*  210 */     if (!ac.haveSearchLight())
/*  211 */       return;  if (!ac.isSearchLightON())
/*      */       return; 
/*  213 */     Entity entity = ac.getEntityBySeatId(1);
/*  214 */     if (entity != null) {
/*      */       
/*  216 */       ac.lastSearchLightYaw = entity.field_70177_z;
/*  217 */       ac.lastSearchLightPitch = entity.field_70125_A;
/*      */     }
/*      */     else {
/*      */       
/*  221 */       entity = ac.getEntityBySeatId(0);
/*  222 */       if (entity != null) {
/*      */         
/*  224 */         ac.lastSearchLightYaw = entity.field_70177_z;
/*  225 */         ac.lastSearchLightPitch = entity.field_70125_A;
/*      */       } 
/*      */     } 
/*      */     
/*  229 */     float yaw = ac.lastSearchLightYaw;
/*  230 */     float pitch = ac.lastSearchLightPitch;
/*      */     
/*  232 */     RenderHelper.func_74518_a();
/*  233 */     GL11.glDisable(3553);
/*  234 */     GL11.glShadeModel(7425);
/*  235 */     GL11.glEnable(3042);
/*  236 */     GL11.glBlendFunc(770, 1);
/*  237 */     GL11.glDisable(3008);
/*  238 */     GL11.glDisable(2884);
/*  239 */     GL11.glDepthMask(false);
/*      */     
/*  241 */     float rot = ac.prevRotYawWheel + (ac.rotYawWheel - ac.prevRotYawWheel) * tickTime;
/*      */     
/*  243 */     for (MCH_AircraftInfo.SearchLight sl : info.searchLights) {
/*      */       
/*  245 */       GL11.glPushMatrix();
/*      */       
/*  247 */       GL11.glTranslated(sl.pos.field_72450_a, sl.pos.field_72448_b, sl.pos.field_72449_c);
/*      */       
/*  249 */       if (!sl.fixDir) {
/*      */         
/*  251 */         GL11.glRotatef(yaw - ac.getRotYaw() + sl.yaw, 0.0F, -1.0F, 0.0F);
/*  252 */         GL11.glRotatef(pitch + 90.0F - ac.getRotPitch() + sl.pitch, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */       else {
/*      */         
/*  256 */         float stRot = 0.0F;
/*  257 */         if (sl.steering)
/*      */         {
/*  259 */           stRot = -rot * sl.stRot;
/*      */         }
/*  261 */         GL11.glRotatef(0.0F + sl.yaw + stRot, 0.0F, -1.0F, 0.0F);
/*  262 */         GL11.glRotatef(90.0F + sl.pitch, 1.0F, 0.0F, 0.0F);
/*      */       } 
/*  264 */       float height = sl.height;
/*  265 */       float width = sl.width / 2.0F;
/*  266 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  267 */       tessellator.func_78371_b(6);
/*  268 */       tessellator.func_78384_a(0xFFFFFF & sl.colorStart, sl.colorStart >> 24 & 0xFF);
/*  269 */       tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
/*  270 */       tessellator.func_78384_a(0xFFFFFF & sl.colorEnd, sl.colorEnd >> 24 & 0xFF);
/*  271 */       int VNUM = 24;
/*  272 */       for (int i = 0; i < 25; i++) {
/*      */         
/*  274 */         float angle = (float)(15.0D * i / 180.0D * Math.PI);
/*  275 */         tessellator.func_78377_a((MathHelper.func_76126_a(angle) * width), height, (MathHelper.func_76134_b(angle) * width));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  281 */       tessellator.func_78381_a();
/*      */       
/*  283 */       GL11.glPopMatrix();
/*      */     } 
/*      */     
/*  286 */     GL11.glDepthMask(true);
/*      */ 
/*      */ 
/*      */     
/*  290 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  291 */     GL11.glEnable(3553);
/*  292 */     GL11.glEnable(3008);
/*  293 */     GL11.glBlendFunc(770, 771);
/*  294 */     RenderHelper.func_74519_b();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void bindTexture(String path, MCH_EntityAircraft ac) {
/*  299 */     if (ac == MCH_ClientCommonTickHandler.ridingAircraft) {
/*      */       
/*  301 */       int bk = MCH_ClientCommonTickHandler.cameraMode;
/*  302 */       MCH_ClientCommonTickHandler.cameraMode = 0;
/*  303 */       func_110776_a(new ResourceLocation(W_MOD.DOMAIN, path));
/*  304 */       MCH_ClientCommonTickHandler.cameraMode = bk;
/*      */     }
/*      */     else {
/*      */       
/*  308 */       func_110776_a(new ResourceLocation(W_MOD.DOMAIN, path));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderRiddenEntity(MCH_EntityAircraft ac, float tickTime, float yaw, float pitch, float roll, float width, float height) {
/*  316 */     MCH_ClientEventHook.setCancelRender(false);
/*      */     
/*  318 */     GL11.glPushMatrix();
/*      */     
/*  320 */     renderEntitySimple(ac, ac.field_70153_n, tickTime, yaw, pitch, roll, width, height);
/*  321 */     for (MCH_EntitySeat s : ac.getSeats()) {
/*      */       
/*  323 */       if (s != null)
/*      */       {
/*  325 */         renderEntitySimple(ac, s.field_70153_n, tickTime, yaw, pitch, roll, width, height);
/*      */       }
/*      */     } 
/*      */     
/*  329 */     GL11.glPopMatrix();
/*      */     
/*  331 */     MCH_ClientEventHook.setCancelRender(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderEntitySimple(MCH_EntityAircraft ac, Entity entity, float tickTime, float yaw, float pitch, float roll, float width, float height) {
/*  338 */     if (entity != null) {
/*      */       
/*  340 */       boolean isPilot = ac.isPilot(entity);
/*  341 */       boolean isClientPlayer = W_Lib.isClientPlayer(entity);
/*  342 */       if (!isClientPlayer || !W_Lib.isFirstPerson() || (isClientPlayer && isPilot && ac.getCameraId() > 0)) {
/*      */ 
/*      */         
/*  345 */         GL11.glPushMatrix();
/*      */         
/*  347 */         if (entity.field_70173_aa == 0) {
/*      */           
/*  349 */           entity.field_70142_S = entity.field_70165_t;
/*  350 */           entity.field_70137_T = entity.field_70163_u;
/*  351 */           entity.field_70136_U = entity.field_70161_v;
/*      */         } 
/*      */         
/*  354 */         double x = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * tickTime;
/*  355 */         double y = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * tickTime;
/*  356 */         double z = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * tickTime;
/*  357 */         float f1 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * tickTime;
/*  358 */         int i = entity.func_70070_b(tickTime);
/*      */         
/*  360 */         if (entity.func_70027_ad())
/*      */         {
/*  362 */           i = 15728880;
/*      */         }
/*      */         
/*  365 */         int j = i % 65536;
/*  366 */         int k = i / 65536;
/*  367 */         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*  368 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         
/*  370 */         double dx = x - RenderManager.field_78725_b;
/*  371 */         double dy = y - RenderManager.field_78726_c;
/*  372 */         double dz = z - RenderManager.field_78723_d;
/*      */ 
/*      */         
/*  375 */         GL11.glTranslated(dx, dy, dz);
/*  376 */         GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  377 */         GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  378 */         GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
/*  379 */         GL11.glScaled(width, height, width);
/*  380 */         GL11.glRotatef(-yaw, 0.0F, -1.0F, 0.0F);
/*  381 */         GL11.glTranslated(-dx, -dy, -dz);
/*      */ 
/*      */ 
/*      */         
/*  385 */         boolean bk = renderingEntity;
/*  386 */         renderingEntity = true;
/*  387 */         Entity ridingEntity = entity.field_70154_o;
/*  388 */         if (!W_Lib.isEntityLivingBase(entity) && !(entity instanceof MCH_IEntityCanRideAircraft))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  394 */           entity.field_70154_o = null;
/*      */         }
/*      */         
/*  397 */         EntityLivingBase entityLiving = (entity instanceof EntityLivingBase) ? (EntityLivingBase)entity : null;
/*  398 */         float bkYaw = 0.0F, bkPrevYaw = 0.0F;
/*  399 */         float bkPitch = 0.0F, bkPrevPitch = 0.0F;
/*  400 */         if (isPilot && entityLiving != null) {
/*      */           
/*  402 */           entityLiving.field_70761_aq = ac.getRotYaw();
/*  403 */           entityLiving.field_70760_ar = ac.getRotYaw();
/*  404 */           if (ac.getCameraId() > 0) {
/*      */             
/*  406 */             entityLiving.field_70759_as = ac.getRotYaw();
/*  407 */             entityLiving.field_70758_at = ac.getRotYaw();
/*  408 */             bkPitch = entityLiving.field_70125_A;
/*  409 */             bkPrevPitch = entityLiving.field_70127_C;
/*  410 */             entityLiving.field_70125_A = ac.getRotPitch();
/*  411 */             entityLiving.field_70127_C = ac.getRotPitch();
/*      */           } 
/*      */         } 
/*      */         
/*  415 */         W_EntityRenderer.renderEntityWithPosYaw(this.field_76990_c, entity, dx, dy, dz, f1, tickTime, false);
/*      */         
/*  417 */         if (isPilot && entityLiving != null)
/*      */         {
/*  419 */           if (ac.getCameraId() > 0) {
/*      */             
/*  421 */             entityLiving.field_70125_A = bkPitch;
/*  422 */             entityLiving.field_70127_C = bkPrevPitch;
/*      */           } 
/*      */         }
/*  425 */         entity.field_70154_o = ridingEntity;
/*      */         
/*  427 */         renderingEntity = bk;
/*      */         
/*  429 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void Test_Material(int light, float a, float b, float c) {
/*  436 */     GL11.glMaterial(1032, light, setColorBuffer(a, b, c, 1.0F));
/*      */   }
/*      */   
/*      */   public static void Test_Light(int light, float a, float b, float c) {
/*  440 */     GL11.glLight(16384, light, setColorBuffer(a, b, c, 1.0F));
/*  441 */     GL11.glLight(16385, light, setColorBuffer(a, b, c, 1.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void renderAircraft(MCH_EntityAircraft paramMCH_EntityAircraft, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*      */ 
/*      */ 
/*      */   
/*      */   public float calcRot(float rot, float prevRot, float tickTime) {
/*  451 */     rot = MathHelper.func_76142_g(rot);
/*  452 */     prevRot = MathHelper.func_76142_g(prevRot);
/*  453 */     if (rot - prevRot < -180.0F) { prevRot -= 360.0F; }
/*  454 */     else if (prevRot - rot < -180.0F) { prevRot += 360.0F; }
/*      */     
/*  456 */     return prevRot + (rot - prevRot) * tickTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void renderDebugHitBox(MCH_EntityAircraft e, double x, double y, double z, float yaw, float pitch) {
/*  461 */     if (MCH_Config.TestMode.prmBool && debugModel != null) {
/*      */       
/*  463 */       GL11.glPushMatrix();
/*      */       
/*  465 */       GL11.glTranslated(x, y, z);
/*      */       
/*  467 */       GL11.glScalef(e.field_70130_N, e.field_70131_O, e.field_70130_N);
/*      */       
/*  469 */       bindTexture("textures/hit_box.png");
/*  470 */       debugModel.renderAll();
/*      */       
/*  472 */       GL11.glPopMatrix();
/*      */       
/*  474 */       GL11.glPushMatrix();
/*      */       
/*  476 */       GL11.glTranslated(x, y, z);
/*      */       
/*  478 */       for (MCH_BoundingBox bb : e.extraBoundingBox) {
/*      */         
/*  480 */         GL11.glPushMatrix();
/*      */         
/*  482 */         GL11.glTranslated(bb.rotatedOffset.field_72450_a, bb.rotatedOffset.field_72448_b, bb.rotatedOffset.field_72449_c);
/*      */         
/*  484 */         GL11.glPushMatrix();
/*      */         
/*  486 */         GL11.glScalef(bb.width, bb.height, bb.width);
/*      */         
/*  488 */         bindTexture("textures/bounding_box.png");
/*  489 */         debugModel.renderAll();
/*      */         
/*  491 */         GL11.glPopMatrix();
/*      */         
/*  493 */         drawHitBoxDetail(bb);
/*      */         
/*  495 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/*  498 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawHitBoxDetail(MCH_BoundingBox bb) {
/*  504 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  505 */     float f1 = 0.080000006F;
/*  506 */     String s = String.format("%.2f", new Object[] { Float.valueOf(bb.damegeFactor) });
/*  507 */     GL11.glPushMatrix();
/*  508 */     GL11.glTranslatef(0.0F, 0.5F + (float)(bb.offsetY * 0.0D + bb.height), 0.0F);
/*  509 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  510 */     GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  511 */     GL11.glRotatef(this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  512 */     GL11.glScalef(-f1, -f1, f1);
/*  513 */     GL11.glDisable(2896);
/*  514 */     GL11.glEnable(3042);
/*  515 */     OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*      */     
/*  517 */     GL11.glDisable(3553);
/*  518 */     FontRenderer fontrenderer = func_76983_a();
/*      */     
/*  520 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  521 */     tessellator.func_78382_b();
/*  522 */     int i = fontrenderer.func_78256_a(s) / 2;
/*  523 */     tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.4F);
/*  524 */     tessellator.func_78377_a((-i - 1), -1.0D, 0.1D);
/*  525 */     tessellator.func_78377_a((-i - 1), 8.0D, 0.1D);
/*  526 */     tessellator.func_78377_a((i + 1), 8.0D, 0.1D);
/*  527 */     tessellator.func_78377_a((i + 1), -1.0D, 0.1D);
/*  528 */     tessellator.func_78381_a();
/*      */     
/*  530 */     GL11.glEnable(3553);
/*  531 */     GL11.glDepthMask(false);
/*  532 */     int color = (bb.damegeFactor < 1.0F) ? 65535 : ((bb.damegeFactor > 1.0F) ? 16711680 : 16777215);
/*  533 */     fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 0xC0000000 | color);
/*  534 */     GL11.glDepthMask(true);
/*  535 */     GL11.glEnable(2896);
/*      */     
/*  537 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  538 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public void renderDebugPilotSeat(MCH_EntityAircraft e, double x, double y, double z, float yaw, float pitch, float roll) {
/*  543 */     if (MCH_Config.TestMode.prmBool && debugModel != null) {
/*      */       
/*  545 */       GL11.glPushMatrix();
/*      */       
/*  547 */       MCH_SeatInfo seat = e.getSeatInfo(0);
/*      */       
/*  549 */       GL11.glTranslated(x, y, z);
/*      */       
/*  551 */       GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*  552 */       GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*  553 */       GL11.glRotatef(roll, 0.0F, 0.0F, 1.0F);
/*      */       
/*  555 */       GL11.glTranslated(seat.pos.field_72450_a, seat.pos.field_72448_b, seat.pos.field_72449_c);
/*      */       
/*  557 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*      */       
/*  559 */       bindTexture("textures/seat_pilot.png");
/*  560 */       debugModel.renderAll();
/*      */       
/*  562 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderBody(IModelCustom model) {
/*  568 */     if (model != null)
/*      */     {
/*  570 */       if (model instanceof W_ModelCustom) {
/*      */         
/*  572 */         if (((W_ModelCustom)model).containsPart("$body"))
/*      */         {
/*  574 */           model.renderPart("$body");
/*      */         }
/*      */         else
/*      */         {
/*  578 */           model.renderAll();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  583 */         model.renderAll();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderPart(IModelCustom model, IModelCustom modelBody, String partName) {
/*  593 */     if (model != null) {
/*      */       
/*  595 */       model.renderAll();
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  600 */     else if (modelBody instanceof W_ModelCustom) {
/*      */       
/*  602 */       if (((W_ModelCustom)modelBody).containsPart("$" + partName))
/*      */       {
/*  604 */         modelBody.renderPart("$" + partName);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderCommonPart(MCH_EntityAircraft ac, MCH_AircraftInfo info, double x, double y, double z, float tickTime) {
/*  613 */     renderRope(ac, info, x, y, z, tickTime);
/*  614 */     renderWeapon(ac, info, tickTime);
/*  615 */     renderRotPart(ac, info, tickTime);
/*  616 */     renderHatch(ac, info, tickTime);
/*  617 */     renderTrackRoller(ac, info, tickTime);
/*  618 */     renderCrawlerTrack(ac, info, tickTime);
/*  619 */     renderSteeringWheel(ac, info, tickTime);
/*  620 */     renderLightHatch(ac, info, tickTime);
/*  621 */     renderWheel(ac, info, tickTime);
/*  622 */     renderThrottle(ac, info, tickTime);
/*  623 */     renderCamera(ac, info, tickTime);
/*  624 */     renderLandingGear(ac, info, tickTime);
/*  625 */     renderWeaponBay(ac, info, tickTime);
/*  626 */     renderCanopy(ac, info, tickTime);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderLightHatch(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/*  632 */     if (info.lightHatchList.size() <= 0)
/*      */       return; 
/*  634 */     float rot = ac.prevRotLightHatch + (ac.rotLightHatch - ac.prevRotLightHatch) * tickTime;
/*  635 */     for (MCH_AircraftInfo.Hatch t : info.lightHatchList) {
/*      */       
/*  637 */       GL11.glPushMatrix();
/*      */       
/*  639 */       GL11.glTranslated(t.pos.field_72450_a, t.pos.field_72448_b, t.pos.field_72449_c);
/*      */       
/*  641 */       GL11.glRotated((rot * t.maxRot), t.rot.field_72450_a, t.rot.field_72448_b, t.rot.field_72449_c);
/*      */       
/*  643 */       GL11.glTranslated(-t.pos.field_72450_a, -t.pos.field_72448_b, -t.pos.field_72449_c);
/*      */       
/*  645 */       renderPart(t.model, info.model, t.modelName);
/*      */       
/*  647 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderSteeringWheel(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/*  653 */     if (info.partSteeringWheel.size() <= 0)
/*      */       return; 
/*  655 */     float rot = ac.prevRotYawWheel + (ac.rotYawWheel - ac.prevRotYawWheel) * tickTime;
/*  656 */     for (MCH_AircraftInfo.PartWheel t : info.partSteeringWheel) {
/*      */       
/*  658 */       GL11.glPushMatrix();
/*      */       
/*  660 */       GL11.glTranslated(t.pos.field_72450_a, t.pos.field_72448_b, t.pos.field_72449_c);
/*      */       
/*  662 */       GL11.glRotated((rot * t.rotDir), t.rot.field_72450_a, t.rot.field_72448_b, t.rot.field_72449_c);
/*      */       
/*  664 */       GL11.glTranslated(-t.pos.field_72450_a, -t.pos.field_72448_b, -t.pos.field_72449_c);
/*      */       
/*  666 */       renderPart(t.model, info.model, t.modelName);
/*      */       
/*  668 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderWheel(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/*  674 */     if (info.partWheel.size() <= 0)
/*      */       return; 
/*  676 */     float yaw = ac.prevRotYawWheel + (ac.rotYawWheel - ac.prevRotYawWheel) * tickTime;
/*      */     
/*  678 */     for (MCH_AircraftInfo.PartWheel t : info.partWheel) {
/*      */       
/*  680 */       GL11.glPushMatrix();
/*      */       
/*  682 */       GL11.glTranslated(t.pos2.field_72450_a, t.pos2.field_72448_b, t.pos2.field_72449_c);
/*  683 */       GL11.glRotated((yaw * t.rotDir), t.rot.field_72450_a, t.rot.field_72448_b, t.rot.field_72449_c);
/*  684 */       GL11.glTranslated(-t.pos2.field_72450_a, -t.pos2.field_72448_b, -t.pos2.field_72449_c);
/*      */       
/*  686 */       GL11.glTranslated(t.pos.field_72450_a, t.pos.field_72448_b, t.pos.field_72449_c);
/*  687 */       GL11.glRotatef(ac.prevRotWheel + (ac.rotWheel - ac.prevRotWheel) * tickTime, 1.0F, 0.0F, 0.0F);
/*  688 */       GL11.glTranslated(-t.pos.field_72450_a, -t.pos.field_72448_b, -t.pos.field_72449_c);
/*      */       
/*  690 */       renderPart(t.model, info.model, t.modelName);
/*      */       
/*  692 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderRotPart(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/*  698 */     if (!ac.haveRotPart())
/*      */       return; 
/*  700 */     for (int i = 0; i < ac.rotPartRotation.length; i++) {
/*      */       
/*  702 */       float rot = ac.rotPartRotation[i];
/*  703 */       float prevRot = ac.prevRotPartRotation[i];
/*  704 */       if (prevRot > rot)
/*      */       {
/*  706 */         rot += 360.0F;
/*      */       }
/*  708 */       rot = MCH_Lib.smooth(rot, prevRot, tickTime);
/*      */       
/*  710 */       MCH_AircraftInfo.RotPart h = info.partRotPart.get(i);
/*      */       
/*  712 */       GL11.glPushMatrix();
/*      */       
/*  714 */       GL11.glTranslated(h.pos.field_72450_a, h.pos.field_72448_b, h.pos.field_72449_c);
/*  715 */       GL11.glRotatef(rot, (float)h.rot.field_72450_a, (float)h.rot.field_72448_b, (float)h.rot.field_72449_c);
/*  716 */       GL11.glTranslated(-h.pos.field_72450_a, -h.pos.field_72448_b, -h.pos.field_72449_c);
/*      */       
/*  718 */       renderPart(h.model, info.model, h.modelName);
/*      */       
/*  720 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderWeapon(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/*  726 */     MCH_WeaponSet beforeWs = null;
/*      */ 
/*      */     
/*  729 */     Entity e = ac.getRiddenByEntity();
/*  730 */     int weaponIndex = 0;
/*  731 */     int cnt = 0;
/*  732 */     for (MCH_AircraftInfo.PartWeapon w : info.partWeapon) {
/*      */       
/*  734 */       MCH_WeaponSet ws = ac.getWeaponByName(w.name[0]);
/*  735 */       boolean onTurret = (ws != null && (ws.getFirstWeapon()).onTurret);
/*      */       
/*  737 */       if (ws != beforeWs) {
/*      */         
/*  739 */         weaponIndex = 0;
/*  740 */         beforeWs = ws;
/*      */       } 
/*      */       
/*  743 */       float rotYaw = 0.0F;
/*  744 */       float prevYaw = 0.0F;
/*  745 */       float rotPitch = 0.0F;
/*  746 */       float prevPitch = 0.0F;
/*      */ 
/*      */       
/*  749 */       if (w.hideGM && W_Lib.isFirstPerson())
/*      */       {
/*  751 */         if (ws != null) {
/*      */           
/*  753 */           boolean hide = false;
/*  754 */           for (String s : w.name) {
/*      */             
/*  756 */             if (W_Lib.isClientPlayer(ac.getWeaponUserByWeaponName(s))) {
/*      */               
/*  758 */               hide = true;
/*      */               break;
/*      */             } 
/*      */           } 
/*  762 */           if (hide) {
/*      */             continue;
/*      */           }
/*      */         }
/*  766 */         else if (ac.isMountedEntity(MCH_Lib.getClientPlayer())) {
/*      */           continue;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  773 */       GL11.glPushMatrix();
/*      */       
/*  775 */       if (w.turret) {
/*      */         
/*  777 */         GL11.glTranslated(info.turretPosition.field_72450_a, info.turretPosition.field_72448_b, info.turretPosition.field_72449_c);
/*  778 */         float ty = MCH_Lib.smooth(ac.getLastRiderYaw() - ac.getRotYaw(), ac.prevLastRiderYaw - ac.field_70126_B, tickTime);
/*  779 */         GL11.glRotatef(ty, 0.0F, -1.0F, 0.0F);
/*  780 */         GL11.glTranslated(-info.turretPosition.field_72450_a, -info.turretPosition.field_72448_b, -info.turretPosition.field_72449_c);
/*      */       } 
/*      */       
/*  783 */       GL11.glTranslated(w.pos.field_72450_a, w.pos.field_72448_b, w.pos.field_72449_c);
/*      */       
/*  785 */       if (w.yaw) {
/*      */         
/*  787 */         if (ws != null) {
/*      */           
/*  789 */           rotYaw = ws.rotationYaw - ws.defaultRotationYaw;
/*  790 */           prevYaw = ws.prevRotationYaw - ws.defaultRotationYaw;
/*      */         }
/*  792 */         else if (e != null) {
/*      */           
/*  794 */           rotYaw = e.field_70177_z - ac.getRotYaw();
/*  795 */           prevYaw = e.field_70126_B - ac.field_70126_B;
/*      */         }
/*      */         else {
/*      */           
/*  799 */           rotYaw = ac.getLastRiderYaw() - ac.field_70177_z;
/*  800 */           prevYaw = ac.prevLastRiderYaw - ac.field_70126_B;
/*      */         } 
/*  802 */         if (rotYaw - prevYaw > 180.0F) { prevYaw += 360.0F; }
/*  803 */         else if (rotYaw - prevYaw < -180.0F) { prevYaw -= 360.0F; }
/*      */         
/*  805 */         GL11.glRotatef(prevYaw + (rotYaw - prevYaw) * tickTime, 0.0F, -1.0F, 0.0F);
/*      */       } 
/*      */       
/*  808 */       if (w.turret) {
/*      */         
/*  810 */         float ty = MCH_Lib.smooth(ac.getLastRiderYaw() - ac.getRotYaw(), ac.prevLastRiderYaw - ac.field_70126_B, tickTime);
/*  811 */         ty -= ws.rotationTurretYaw;
/*  812 */         GL11.glRotatef(-ty, 0.0F, -1.0F, 0.0F);
/*      */       } 
/*      */       
/*  815 */       boolean rev_sign = false;
/*  816 */       if (ws != null && (int)ws.defaultRotationYaw != 0) {
/*      */         
/*  818 */         float t = MathHelper.func_76142_g(ws.defaultRotationYaw);
/*  819 */         rev_sign = ((t >= 45.0F && t <= 135.0F) || (t <= -45.0F && t >= -135.0F));
/*  820 */         GL11.glRotatef(-ws.defaultRotationYaw, 0.0F, -1.0F, 0.0F);
/*      */       } 
/*  822 */       if (w.pitch) {
/*      */         
/*  824 */         if (ws != null) {
/*      */           
/*  826 */           rotPitch = ws.rotationPitch;
/*  827 */           prevPitch = ws.prevRotationPitch;
/*      */         }
/*  829 */         else if (e != null) {
/*      */           
/*  831 */           rotPitch = e.field_70125_A;
/*  832 */           prevPitch = e.field_70127_C;
/*      */         }
/*      */         else {
/*      */           
/*  836 */           rotPitch = ac.getLastRiderPitch();
/*  837 */           prevPitch = ac.prevLastRiderPitch;
/*      */         } 
/*      */         
/*  840 */         if (rev_sign) {
/*      */           
/*  842 */           rotPitch = -rotPitch;
/*  843 */           prevPitch = -prevPitch;
/*      */         } 
/*      */         
/*  846 */         GL11.glRotatef(prevPitch + (rotPitch - prevPitch) * tickTime, 1.0F, 0.0F, 0.0F);
/*      */       } 
/*      */       
/*  849 */       if (ws != null && w.recoilBuf != 0.0F) {
/*      */         
/*  851 */         MCH_WeaponSet.Recoil r = ws.recoilBuf[0];
/*  852 */         if (w.name.length > 1)
/*      */         {
/*  854 */           for (String wnm : w.name) {
/*      */             
/*  856 */             MCH_WeaponSet tws = ac.getWeaponByName(wnm);
/*  857 */             if (tws != null && (tws.recoilBuf[0]).recoilBuf > r.recoilBuf)
/*      */             {
/*  859 */               r = tws.recoilBuf[0];
/*      */             }
/*      */           } 
/*      */         }
/*  863 */         float recoilBuf = r.prevRecoilBuf + (r.recoilBuf - r.prevRecoilBuf) * tickTime;
/*  864 */         GL11.glTranslated(0.0D, 0.0D, (w.recoilBuf * recoilBuf));
/*      */       } 
/*      */       
/*  867 */       if (ws != null) {
/*      */         
/*  869 */         GL11.glRotatef(ws.defaultRotationYaw, 0.0F, -1.0F, 0.0F);
/*      */         
/*  871 */         if (w.rotBarrel) {
/*      */           
/*  873 */           float rotBrl = ws.prevRotBarrel + (ws.rotBarrel - ws.prevRotBarrel) * tickTime;
/*  874 */           GL11.glRotatef(rotBrl, (float)w.rot.field_72450_a, (float)w.rot.field_72448_b, (float)w.rot.field_72449_c);
/*      */         } 
/*      */       } 
/*      */       
/*  878 */       GL11.glTranslated(-w.pos.field_72450_a, -w.pos.field_72448_b, -w.pos.field_72449_c);
/*      */       
/*  880 */       if (!w.isMissile || !ac.isWeaponNotCooldown(ws, weaponIndex)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  886 */         renderPart(w.model, info.model, w.modelName);
/*      */         
/*  888 */         for (MCH_AircraftInfo.PartWeaponChild wc : w.child) {
/*      */           
/*  890 */           GL11.glPushMatrix();
/*  891 */           renderWeaponChild(ac, info, wc, ws, e, tickTime);
/*  892 */           GL11.glPopMatrix();
/*      */         } 
/*      */       } 
/*      */       
/*  896 */       GL11.glPopMatrix();
/*      */       
/*  898 */       weaponIndex++;
/*  899 */       cnt++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderWeaponChild(MCH_EntityAircraft ac, MCH_AircraftInfo info, MCH_AircraftInfo.PartWeaponChild w, MCH_WeaponSet ws, Entity e, float tickTime) {
/*  906 */     float rotYaw = 0.0F;
/*  907 */     float prevYaw = 0.0F;
/*  908 */     float rotPitch = 0.0F;
/*  909 */     float prevPitch = 0.0F;
/*      */     
/*  911 */     GL11.glTranslated(w.pos.field_72450_a, w.pos.field_72448_b, w.pos.field_72449_c);
/*      */     
/*  913 */     if (w.yaw) {
/*      */       
/*  915 */       if (ws != null) {
/*      */         
/*  917 */         rotYaw = ws.rotationYaw - ws.defaultRotationYaw;
/*  918 */         prevYaw = ws.prevRotationYaw - ws.defaultRotationYaw;
/*      */       }
/*  920 */       else if (e != null) {
/*      */         
/*  922 */         rotYaw = e.field_70177_z - ac.getRotYaw();
/*  923 */         prevYaw = e.field_70126_B - ac.field_70126_B;
/*      */       }
/*      */       else {
/*      */         
/*  927 */         rotYaw = ac.getLastRiderYaw() - ac.field_70177_z;
/*  928 */         prevYaw = ac.prevLastRiderYaw - ac.field_70126_B;
/*      */       } 
/*  930 */       if (rotYaw - prevYaw > 180.0F) { prevYaw += 360.0F; }
/*  931 */       else if (rotYaw - prevYaw < -180.0F) { prevYaw -= 360.0F; }
/*      */       
/*  933 */       GL11.glRotatef(prevYaw + (rotYaw - prevYaw) * tickTime, 0.0F, -1.0F, 0.0F);
/*      */     } 
/*      */     
/*  936 */     boolean rev_sign = false;
/*  937 */     if (ws != null && (int)ws.defaultRotationYaw != 0) {
/*      */       
/*  939 */       float t = MathHelper.func_76142_g(ws.defaultRotationYaw);
/*  940 */       rev_sign = ((t >= 45.0F && t <= 135.0F) || (t <= -45.0F && t >= -135.0F));
/*  941 */       GL11.glRotatef(-ws.defaultRotationYaw, 0.0F, -1.0F, 0.0F);
/*      */     } 
/*  943 */     if (w.pitch) {
/*      */       
/*  945 */       if (ws != null) {
/*      */         
/*  947 */         rotPitch = ws.rotationPitch;
/*  948 */         prevPitch = ws.prevRotationPitch;
/*      */       }
/*  950 */       else if (e != null) {
/*      */         
/*  952 */         rotPitch = e.field_70125_A;
/*  953 */         prevPitch = e.field_70127_C;
/*      */       }
/*      */       else {
/*      */         
/*  957 */         rotPitch = ac.getLastRiderPitch();
/*  958 */         prevPitch = ac.prevLastRiderPitch;
/*      */       } 
/*      */       
/*  961 */       if (rev_sign) {
/*      */         
/*  963 */         rotPitch = -rotPitch;
/*  964 */         prevPitch = -prevPitch;
/*      */       } 
/*      */       
/*  967 */       GL11.glRotatef(prevPitch + (rotPitch - prevPitch) * tickTime, 1.0F, 0.0F, 0.0F);
/*      */     } 
/*      */     
/*  970 */     if (ws != null && w.recoilBuf != 0.0F) {
/*      */       
/*  972 */       MCH_WeaponSet.Recoil r = ws.recoilBuf[0];
/*  973 */       if (w.name.length > 1)
/*      */       {
/*  975 */         for (String wnm : w.name) {
/*      */           
/*  977 */           MCH_WeaponSet tws = ac.getWeaponByName(wnm);
/*  978 */           if (tws != null && (tws.recoilBuf[0]).recoilBuf > r.recoilBuf)
/*      */           {
/*  980 */             r = tws.recoilBuf[0];
/*      */           }
/*      */         } 
/*      */       }
/*  984 */       float recoilBuf = r.prevRecoilBuf + (r.recoilBuf - r.prevRecoilBuf) * tickTime;
/*  985 */       GL11.glTranslated(0.0D, 0.0D, (-w.recoilBuf * recoilBuf));
/*      */     } 
/*      */     
/*  988 */     if (ws != null)
/*      */     {
/*  990 */       GL11.glRotatef(ws.defaultRotationYaw, 0.0F, -1.0F, 0.0F);
/*      */     }
/*      */     
/*  993 */     GL11.glTranslated(-w.pos.field_72450_a, -w.pos.field_72448_b, -w.pos.field_72449_c);
/*      */     
/*  995 */     renderPart(w.model, info.model, w.modelName);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderTrackRoller(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1000 */     if (info.partTrackRoller.size() <= 0)
/*      */       return; 
/* 1002 */     float[] rot = ac.rotTrackRoller;
/* 1003 */     float[] prevRot = ac.prevRotTrackRoller;
/* 1004 */     for (MCH_AircraftInfo.TrackRoller t : info.partTrackRoller) {
/*      */       
/* 1006 */       GL11.glPushMatrix();
/*      */       
/* 1008 */       GL11.glTranslated(t.pos.field_72450_a, t.pos.field_72448_b, t.pos.field_72449_c);
/*      */       
/* 1010 */       GL11.glRotatef(prevRot[t.side] + (rot[t.side] - prevRot[t.side]) * tickTime, 1.0F, 0.0F, 0.0F);
/*      */       
/* 1012 */       GL11.glTranslated(-t.pos.field_72450_a, -t.pos.field_72448_b, -t.pos.field_72449_c);
/*      */       
/* 1014 */       renderPart(t.model, info.model, t.modelName);
/*      */       
/* 1016 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void renderCrawlerTrack(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1021 */     if (info.partCrawlerTrack.size() <= 0)
/*      */       return; 
/* 1023 */     int prevWidth = GL11.glGetInteger(2833);
/*      */     
/* 1025 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 1026 */     for (MCH_AircraftInfo.CrawlerTrack c : info.partCrawlerTrack) {
/*      */       
/* 1028 */       GL11.glPointSize(c.len * 20.0F);
/*      */       
/* 1030 */       if (MCH_Config.TestMode.prmBool) {
/*      */         
/* 1032 */         GL11.glDisable(3553);
/* 1033 */         GL11.glDisable(3042);
/* 1034 */         tessellator.func_78371_b(0);
/* 1035 */         for (int j = 0; j < c.cx.length; j++) {
/*      */           
/* 1037 */           tessellator.func_78370_a((int)(255.0F / c.cx.length * j), 80, 255 - (int)(255.0F / c.cx.length * j), 255);
/* 1038 */           tessellator.func_78377_a(c.z, c.cx[j], c.cy[j]);
/*      */         } 
/* 1040 */         tessellator.func_78381_a();
/*      */       } 
/*      */       
/* 1043 */       GL11.glEnable(3553);
/* 1044 */       GL11.glEnable(3042);
/*      */       
/* 1046 */       int L = c.lp.size() - 1;
/* 1047 */       double rc = (ac != null) ? ac.rotCrawlerTrack[c.side] : 0.0D;
/* 1048 */       double pc = (ac != null) ? ac.prevRotCrawlerTrack[c.side] : 0.0D;
/* 1049 */       for (int i = 0; i < L; i++) {
/*      */         
/* 1051 */         MCH_AircraftInfo.CrawlerTrackPrm cp = c.lp.get(i);
/* 1052 */         MCH_AircraftInfo.CrawlerTrackPrm np = c.lp.get((i + 1) % L);
/* 1053 */         double x1 = cp.x;
/* 1054 */         double x2 = np.x;
/* 1055 */         double r1 = cp.r;
/* 1056 */         double y1 = cp.y;
/* 1057 */         double y2 = np.y;
/* 1058 */         double r2 = np.r;
/* 1059 */         if (r2 - r1 < -180.0D)
/*      */         {
/* 1061 */           r2 += 360.0D;
/*      */         }
/* 1063 */         if (r2 - r1 > 180.0D)
/*      */         {
/* 1065 */           r2 -= 360.0D;
/*      */         }
/* 1067 */         double sx = x1 + (x2 - x1) * rc;
/* 1068 */         double sy = y1 + (y2 - y1) * rc;
/* 1069 */         double sr = r1 + (r2 - r1) * rc;
/* 1070 */         double ex = x1 + (x2 - x1) * pc;
/* 1071 */         double ey = y1 + (y2 - y1) * pc;
/* 1072 */         double er = r1 + (r2 - r1) * pc;
/* 1073 */         double x = sx + (ex - sx) * pc;
/* 1074 */         double y = sy + (ey - sy) * pc;
/* 1075 */         double r = sr + (er - sr) * pc;
/* 1076 */         GL11.glPushMatrix();
/* 1077 */         GL11.glTranslated(0.0D, x, y);
/* 1078 */         GL11.glRotatef((float)r, -1.0F, 0.0F, 0.0F);
/* 1079 */         renderPart(c.model, info.model, c.modelName);
/* 1080 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } 
/*      */     
/* 1084 */     GL11.glEnable(3042);
/*      */     
/* 1086 */     GL11.glPointSize(prevWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderHatch(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1091 */     if (!info.haveHatch() || ac.partHatch == null)
/*      */       return; 
/* 1093 */     float rot = ac.getHatchRotation();
/* 1094 */     float prevRot = ac.getPrevHatchRotation();
/* 1095 */     for (MCH_AircraftInfo.Hatch h : info.hatchList) {
/*      */       
/* 1097 */       GL11.glPushMatrix();
/*      */       
/* 1099 */       if (h.isSlide) {
/*      */         
/* 1101 */         float r = ac.partHatch.rotation / ac.partHatch.rotationMax;
/* 1102 */         float pr = ac.partHatch.prevRotation / ac.partHatch.rotationMax;
/* 1103 */         float f = pr + (r - pr) * tickTime;
/* 1104 */         GL11.glTranslated(h.pos.field_72450_a * f, h.pos.field_72448_b * f, h.pos.field_72449_c * f);
/*      */       }
/*      */       else {
/*      */         
/* 1108 */         GL11.glTranslated(h.pos.field_72450_a, h.pos.field_72448_b, h.pos.field_72449_c);
/*      */         
/* 1110 */         GL11.glRotatef((prevRot + (rot - prevRot) * tickTime) * h.maxRotFactor, (float)h.rot.field_72450_a, (float)h.rot.field_72448_b, (float)h.rot.field_72449_c);
/*      */ 
/*      */         
/* 1113 */         GL11.glTranslated(-h.pos.field_72450_a, -h.pos.field_72448_b, -h.pos.field_72449_c);
/*      */       } 
/*      */       
/* 1116 */       renderPart(h.model, info.model, h.modelName);
/*      */       
/* 1118 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void renderThrottle(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1123 */     if (!info.havePartThrottle())
/*      */       return; 
/* 1125 */     float throttle = MCH_Lib.smooth((float)ac.getCurrentThrottle(), (float)ac.getPrevCurrentThrottle(), tickTime);
/* 1126 */     for (MCH_AircraftInfo.Throttle h : info.partThrottle) {
/*      */       
/* 1128 */       GL11.glPushMatrix();
/*      */       
/* 1130 */       GL11.glTranslated(h.pos.field_72450_a, h.pos.field_72448_b, h.pos.field_72449_c);
/* 1131 */       GL11.glRotatef(throttle * h.rot2, (float)h.rot.field_72450_a, (float)h.rot.field_72448_b, (float)h.rot.field_72449_c);
/* 1132 */       GL11.glTranslated(-h.pos.field_72450_a, -h.pos.field_72448_b, -h.pos.field_72449_c);
/*      */       
/* 1134 */       GL11.glTranslated(h.slide.field_72450_a * throttle, h.slide.field_72448_b * throttle, h.slide.field_72449_c * throttle);
/*      */       
/* 1136 */       renderPart(h.model, info.model, h.modelName);
/*      */       
/* 1138 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void renderWeaponBay(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1143 */     for (int i = 0; i < info.partWeaponBay.size(); i++) {
/*      */       
/* 1145 */       MCH_AircraftInfo.WeaponBay w = info.partWeaponBay.get(i);
/* 1146 */       MCH_EntityAircraft.WeaponBay ws = ac.weaponBays[i];
/*      */       
/* 1148 */       GL11.glPushMatrix();
/*      */       
/* 1150 */       if (w.isSlide) {
/*      */         
/* 1152 */         float r = ws.rot / 90.0F;
/* 1153 */         float pr = ws.prevRot / 90.0F;
/* 1154 */         float f = pr + (r - pr) * tickTime;
/* 1155 */         GL11.glTranslated(w.pos.field_72450_a * f, w.pos.field_72448_b * f, w.pos.field_72449_c * f);
/*      */       }
/*      */       else {
/*      */         
/* 1159 */         GL11.glTranslated(w.pos.field_72450_a, w.pos.field_72448_b, w.pos.field_72449_c);
/*      */         
/* 1161 */         GL11.glRotatef((ws.prevRot + (ws.rot - ws.prevRot) * tickTime) * w.maxRotFactor, (float)w.rot.field_72450_a, (float)w.rot.field_72448_b, (float)w.rot.field_72449_c);
/*      */ 
/*      */         
/* 1164 */         GL11.glTranslated(-w.pos.field_72450_a, -w.pos.field_72448_b, -w.pos.field_72449_c);
/*      */       } 
/*      */       
/* 1167 */       renderPart(w.model, info.model, w.modelName);
/*      */       
/* 1169 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderCamera(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1175 */     if (!info.havePartCamera())
/*      */       return; 
/* 1177 */     float rotYaw = ac.camera.partRotationYaw;
/* 1178 */     float prevRotYaw = ac.camera.prevPartRotationYaw;
/* 1179 */     float rotPitch = ac.camera.partRotationPitch;
/* 1180 */     float prevRotPitch = ac.camera.prevPartRotationPitch;
/*      */     
/* 1182 */     float yaw = prevRotYaw + (rotYaw - prevRotYaw) * tickTime - ac.getRotYaw();
/* 1183 */     float pitch = prevRotPitch + (rotPitch - prevRotPitch) * tickTime - ac.getRotPitch();
/*      */     
/* 1185 */     for (MCH_AircraftInfo.Camera c : info.cameraList) {
/*      */       
/* 1187 */       GL11.glPushMatrix();
/*      */       
/* 1189 */       GL11.glTranslated(c.pos.field_72450_a, c.pos.field_72448_b, c.pos.field_72449_c);
/*      */       
/* 1191 */       if (c.yawSync)
/*      */       {
/* 1193 */         GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*      */       }
/* 1195 */       if (c.pitchSync)
/*      */       {
/* 1197 */         GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */       
/* 1200 */       GL11.glTranslated(-c.pos.field_72450_a, -c.pos.field_72448_b, -c.pos.field_72449_c);
/*      */       
/* 1202 */       renderPart(c.model, info.model, c.modelName);
/*      */       
/* 1204 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void renderCanopy(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1209 */     if (info.haveCanopy() && ac.partCanopy != null) {
/*      */       
/* 1211 */       float rot = ac.getCanopyRotation();
/* 1212 */       float prevRot = ac.getPrevCanopyRotation();
/*      */       
/* 1214 */       for (MCH_AircraftInfo.Canopy c : info.canopyList) {
/*      */         
/* 1216 */         GL11.glPushMatrix();
/*      */ 
/*      */         
/* 1219 */         if (c.isSlide) {
/*      */ 
/*      */           
/* 1222 */           float r = ac.partCanopy.rotation / ac.partCanopy.rotationMax;
/* 1223 */           float pr = ac.partCanopy.prevRotation / ac.partCanopy.rotationMax;
/* 1224 */           float f = pr + (r - pr) * tickTime;
/* 1225 */           GL11.glTranslated(c.pos.field_72450_a * f, c.pos.field_72448_b * f, c.pos.field_72449_c * f);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1230 */           GL11.glTranslated(c.pos.field_72450_a, c.pos.field_72448_b, c.pos.field_72449_c);
/*      */           
/* 1232 */           GL11.glRotatef((prevRot + (rot - prevRot) * tickTime) * c.maxRotFactor, (float)c.rot.field_72450_a, (float)c.rot.field_72448_b, (float)c.rot.field_72449_c);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1237 */           GL11.glTranslated(-c.pos.field_72450_a, -c.pos.field_72448_b, -c.pos.field_72449_c);
/*      */         } 
/*      */         
/* 1240 */         renderPart(c.model, info.model, c.modelName);
/*      */         
/* 1242 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void renderLandingGear(MCH_EntityAircraft ac, MCH_AircraftInfo info, float tickTime) {
/* 1248 */     if (info.haveLandingGear() && ac.partLandingGear != null) {
/*      */       
/* 1250 */       float rot = ac.getLandingGearRotation();
/* 1251 */       float prevRot = ac.getPrevLandingGearRotation();
/* 1252 */       float revR = 90.0F - rot;
/* 1253 */       float revPr = 90.0F - prevRot;
/* 1254 */       float rot1 = prevRot + (rot - prevRot) * tickTime;
/* 1255 */       float rot1Rev = revPr + (revR - revPr) * tickTime;
/* 1256 */       float rotHatch = 90.0F * MathHelper.func_76126_a(rot1 * 2.0F * 3.1415927F / 180.0F) * 3.0F;
/* 1257 */       if (rotHatch > 90.0F) rotHatch = 90.0F; 
/* 1258 */       for (MCH_AircraftInfo.LandingGear n : info.landingGear) {
/*      */         
/* 1260 */         GL11.glPushMatrix();
/*      */         
/* 1262 */         GL11.glTranslated(n.pos.field_72450_a, n.pos.field_72448_b, n.pos.field_72449_c);
/*      */         
/* 1264 */         if (!n.reverse) {
/*      */           
/* 1266 */           if (!n.hatch)
/*      */           {
/* 1268 */             GL11.glRotatef(rot1 * n.maxRotFactor, (float)n.rot.field_72450_a, (float)n.rot.field_72448_b, (float)n.rot.field_72449_c);
/*      */           
/*      */           }
/*      */           else
/*      */           {
/* 1273 */             GL11.glRotatef(rotHatch * n.maxRotFactor, (float)n.rot.field_72450_a, (float)n.rot.field_72448_b, (float)n.rot.field_72449_c);
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1279 */           GL11.glRotatef(rot1Rev * n.maxRotFactor, (float)n.rot.field_72450_a, (float)n.rot.field_72448_b, (float)n.rot.field_72449_c);
/*      */         } 
/*      */ 
/*      */         
/* 1283 */         if (n.enableRot2)
/*      */         {
/* 1285 */           if (!n.reverse) {
/*      */             
/* 1287 */             GL11.glRotatef(rot1 * n.maxRotFactor2, (float)n.rot2.field_72450_a, (float)n.rot2.field_72448_b, (float)n.rot2.field_72449_c);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1292 */             GL11.glRotatef(rot1Rev * n.maxRotFactor2, (float)n.rot2.field_72450_a, (float)n.rot2.field_72448_b, (float)n.rot2.field_72449_c);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1297 */         GL11.glTranslated(-n.pos.field_72450_a, -n.pos.field_72448_b, -n.pos.field_72449_c);
/*      */         
/* 1299 */         if (n.slide != null) {
/*      */           
/* 1301 */           float f = rot / 90.0F;
/* 1302 */           if (n.reverse)
/*      */           {
/* 1304 */             f = 1.0F - f;
/*      */           }
/* 1306 */           GL11.glTranslated(f * n.slide.field_72450_a, f * n.slide.field_72448_b, f * n.slide.field_72449_c);
/*      */         } 
/*      */         
/* 1309 */         renderPart(n.model, info.model, n.modelName);
/*      */         
/* 1311 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderEntityMarker(Entity entity) {
/* 1319 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/* 1320 */     if (entityClientPlayerMP == null)
/* 1321 */       return;  if (W_Entity.isEqual((Entity)entityClientPlayerMP, entity))
/* 1322 */       return;  MCH_EntityAircraft ac = null;
/* 1323 */     if (((Entity)entityClientPlayerMP).field_70154_o instanceof MCH_EntityAircraft) {
/*      */       
/* 1325 */       ac = (MCH_EntityAircraft)((Entity)entityClientPlayerMP).field_70154_o;
/*      */     }
/* 1327 */     else if (((Entity)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*      */       
/* 1329 */       ac = ((MCH_EntitySeat)((Entity)entityClientPlayerMP).field_70154_o).getParent();
/*      */     }
/* 1331 */     else if (((Entity)entityClientPlayerMP).field_70154_o instanceof MCH_EntityUavStation) {
/*      */       
/* 1333 */       ac = ((MCH_EntityUavStation)((Entity)entityClientPlayerMP).field_70154_o).getControlAircract();
/*      */     } 
/*      */     
/* 1336 */     if (ac == null)
/* 1337 */       return;  if (W_Entity.isEqual((Entity)ac, entity))
/*      */       return; 
/* 1339 */     MCH_WeaponGuidanceSystem gs = ac.getCurrentWeapon((Entity)entityClientPlayerMP).getCurrentWeapon().getGuidanceSystem();
/* 1340 */     if (gs == null || !gs.canLockEntity(entity))
/*      */       return; 
/* 1342 */     RenderManager rm = RenderManager.field_78727_a;
/*      */     
/* 1344 */     double dist = entity.func_70068_e((Entity)rm.field_78734_h);
/*      */     
/* 1346 */     double x = entity.field_70165_t - RenderManager.field_78725_b;
/* 1347 */     double y = entity.field_70163_u - RenderManager.field_78726_c;
/* 1348 */     double z = entity.field_70161_v - RenderManager.field_78723_d;
/*      */     
/* 1350 */     if (dist < 10000.0D) {
/*      */       
/* 1352 */       float scl = 0.02666667F;
/*      */       
/* 1354 */       GL11.glPushMatrix();
/* 1355 */       GL11.glTranslatef((float)x, (float)y + entity.field_70131_O + 0.5F, (float)z);
/* 1356 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 1357 */       GL11.glRotatef(-rm.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 1358 */       GL11.glRotatef(rm.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 1359 */       GL11.glScalef(-0.02666667F, -0.02666667F, 0.02666667F);
/*      */       
/* 1361 */       GL11.glDisable(2896);
/*      */       
/* 1363 */       GL11.glTranslatef(0.0F, 9.374999F, 0.0F);
/* 1364 */       GL11.glDepthMask(false);
/*      */       
/* 1366 */       GL11.glEnable(3042);
/* 1367 */       GL11.glBlendFunc(770, 771);
/*      */       
/* 1369 */       GL11.glDisable(3553);
/*      */       
/* 1371 */       int prevWidth = GL11.glGetInteger(2849);
/*      */       
/* 1373 */       float size = Math.max(entity.field_70130_N, entity.field_70131_O) * 20.0F;
/* 1374 */       if (entity instanceof MCH_EntityAircraft)
/*      */       {
/* 1376 */         size *= 2.0F;
/*      */       }
/*      */       
/* 1379 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 1380 */       tessellator.func_78371_b(2);
/* 1381 */       tessellator.func_78380_c(240);
/*      */ 
/*      */       
/* 1384 */       boolean isLockEntity = gs.isLockingEntity(entity);
/* 1385 */       if (isLockEntity) {
/*      */         
/* 1387 */         GL11.glLineWidth(MCH_Gui.scaleFactor * 1.5F);
/* 1388 */         tessellator.func_78369_a(1.0F, 0.0F, 0.0F, 1.0F);
/*      */       }
/*      */       else {
/*      */         
/* 1392 */         GL11.glLineWidth(MCH_Gui.scaleFactor);
/* 1393 */         tessellator.func_78369_a(1.0F, 0.3F, 0.0F, 8.0F);
/*      */       } 
/* 1395 */       tessellator.func_78377_a((-size - 1.0F), 0.0D, 0.0D);
/* 1396 */       tessellator.func_78377_a((-size - 1.0F), (size * 2.0F), 0.0D);
/* 1397 */       tessellator.func_78377_a((size + 1.0F), (size * 2.0F), 0.0D);
/* 1398 */       tessellator.func_78377_a((size + 1.0F), 0.0D, 0.0D);
/* 1399 */       tessellator.func_78381_a();
/*      */       
/* 1401 */       GL11.glPopMatrix();
/*      */       
/* 1403 */       if (!ac.isUAV() && isLockEntity && (Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0) {
/*      */         
/* 1405 */         GL11.glPushMatrix();
/* 1406 */         tessellator.func_78371_b(1);
/* 1407 */         GL11.glLineWidth(1.0F);
/* 1408 */         tessellator.func_78369_a(1.0F, 0.0F, 0.0F, 1.0F);
/* 1409 */         tessellator.func_78377_a(x, y + (entity.field_70131_O / 2.0F), z);
/* 1410 */         tessellator.func_78377_a(ac.field_70142_S - RenderManager.field_78725_b, ac.field_70137_T - RenderManager.field_78726_c - 1.0D, ac.field_70136_U - RenderManager.field_78723_d);
/* 1411 */         tessellator.func_78380_c(240);
/* 1412 */         tessellator.func_78381_a();
/* 1413 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/* 1416 */       GL11.glLineWidth(prevWidth);
/*      */       
/* 1418 */       GL11.glEnable(3553);
/* 1419 */       GL11.glDepthMask(true);
/* 1420 */       GL11.glEnable(2896);
/* 1421 */       GL11.glDisable(3042);
/* 1422 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderRope(MCH_EntityAircraft ac, MCH_AircraftInfo info, double x, double y, double z, float tickTime) {
/* 1429 */     GL11.glPushMatrix();
/*      */     
/* 1431 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/* 1433 */     if (ac.isRepelling()) {
/*      */       
/* 1435 */       GL11.glDisable(3553);
/* 1436 */       GL11.glDisable(2896);
/*      */       
/* 1438 */       for (int i = 0; i < info.repellingHooks.size(); i++) {
/*      */         
/* 1440 */         tessellator.func_78371_b(3);
/* 1441 */         tessellator.func_78378_d(0);
/*      */         
/* 1443 */         tessellator.func_78377_a(((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72450_a, ((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72448_b, ((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72449_c);
/*      */ 
/*      */ 
/*      */         
/* 1447 */         tessellator.func_78377_a(((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72450_a, ((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72448_b + ac.ropesLength, ((MCH_AircraftInfo.RepellingHook)info.repellingHooks.get(i)).pos.field_72449_c);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1452 */         tessellator.func_78381_a();
/*      */       } 
/*      */       
/* 1455 */       GL11.glEnable(2896);
/* 1456 */       GL11.glEnable(3553);
/*      */     } 
/* 1458 */     GL11.glPopMatrix();
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_RenderAircraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */