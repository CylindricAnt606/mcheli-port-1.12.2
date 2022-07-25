/*     */ package mcheli.mcheli;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_ClientCommonTickHandler;
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_TextureManagerDummy;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
/*     */ import mcheli.multiplay.MCH_GuiTargetMarker;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.tool.rangefinder.MCH_ItemRangeFinder;
/*     */ import mcheli.wrapper.W_ClientEventHook;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.MouseEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MCH_ClientEventHook extends W_ClientEventHook {
/*  35 */   MCH_TextureManagerDummy dummyTextureManager = null;
/*     */   
/*  37 */   public static List<MCH_EntityAircraft> haveSearchLightAircraft = new ArrayList<MCH_EntityAircraft>();
/*     */ 
/*     */   
/*     */   public void renderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre event) {
/*  41 */     if (MCH_Config.DisableRenderLivingSpecials.prmBool) {
/*     */       
/*  43 */       MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)(Minecraft.func_71410_x()).field_71439_g);
/*     */       
/*  45 */       if (ac != null && ac.isMountedEntity((Entity)event.entity)) {
/*     */         
/*  47 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLivingEventSpecialsPost(RenderLivingEvent.Specials.Post event) {}
/*     */ 
/*     */   
/*  57 */   private static final ResourceLocation ir_strobe = new ResourceLocation("mcheli", "textures/ir_strobe.png");
/*     */ 
/*     */   
/*     */   private void renderIRStrobe(EntityLivingBase entity, RenderLivingEvent.Specials.Post event) {
/*  61 */     int cm = MCH_ClientCommonTickHandler.cameraMode;
/*  62 */     if (cm == 0)
/*     */       return; 
/*  64 */     int ticks = entity.field_70173_aa % 20;
/*  65 */     if (ticks >= 4)
/*  66 */       return;  float alpha = (ticks == 2 || ticks == 1) ? 1.0F : 0.5F;
/*     */     
/*  68 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  69 */     if (entityClientPlayerMP == null)
/*     */       return; 
/*  71 */     if (!entityClientPlayerMP.func_142014_c(entity)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  76 */     int j = 240;
/*  77 */     int k = 240;
/*  78 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*     */     
/*  80 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  81 */     float f1 = 0.080000006F;
/*  82 */     GL11.glPushMatrix();
/*  83 */     GL11.glTranslated(event.x, event.y + (float)(entity.field_70131_O * 0.75D), event.z);
/*  84 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  85 */     GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  86 */     GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  87 */     GL11.glScalef(-f1, -f1, f1);
/*     */     
/*  89 */     GL11.glEnable(3042);
/*  90 */     OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*     */     
/*  92 */     GL11.glEnable(3553);
/*     */     
/*  94 */     RenderManager.field_78727_a.field_78724_e.func_110577_a(ir_strobe);
/*  95 */     GL11.glAlphaFunc(516, 0.003921569F);
/*     */     
/*  97 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  98 */     tessellator.func_78382_b();
/*  99 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, alpha * ((cm == 1) ? 0.9F : 0.5F));
/* 100 */     int i = (int)Math.max(entity.field_70130_N, entity.field_70131_O) * 20;
/* 101 */     tessellator.func_78374_a(-i, -i, 0.1D, 0.0D, 0.0D);
/* 102 */     tessellator.func_78374_a(-i, i, 0.1D, 0.0D, 1.0D);
/* 103 */     tessellator.func_78374_a(i, i, 0.1D, 1.0D, 1.0D);
/* 104 */     tessellator.func_78374_a(i, -i, 0.1D, 1.0D, 0.0D);
/* 105 */     tessellator.func_78381_a();
/*     */     
/* 107 */     GL11.glEnable(2896);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEvent(MouseEvent event) {
/* 113 */     if (MCH_ClientTickHandlerBase.updateMouseWheel(event.dwheel))
/*     */     {
/* 115 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean cancelRender = true;
/*     */ 
/*     */   
/*     */   public static void setCancelRender(boolean cancel) {
/* 124 */     cancelRender = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLivingEventPre(RenderLivingEvent.Pre event) {
/* 129 */     for (MCH_EntityAircraft ac : haveSearchLightAircraft)
/*     */     {
/* 131 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, ac.getSearchLightValue((Entity)event.entity), 240.0F);
/*     */     }
/*     */     
/* 134 */     if (MCH_Config.EnableModEntityRender.prmBool)
/*     */     {
/* 136 */       if (cancelRender)
/*     */       {
/* 138 */         if (event.entity.field_70154_o instanceof MCH_EntityAircraft || event.entity.field_70154_o instanceof mcheli.aircraft.MCH_EntitySeat) {
/*     */ 
/*     */           
/* 141 */           event.setCanceled(true);
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/*     */     }
/* 147 */     if (MCH_Config.EnableReplaceTextureManager.prmBool) {
/*     */ 
/*     */       
/* 150 */       RenderManager rm = W_Reflection.getRenderManager((Render)event.renderer);
/* 151 */       if (rm != null && !(rm.field_78724_e instanceof MCH_TextureManagerDummy)) {
/*     */         
/* 153 */         if (this.dummyTextureManager == null)
/*     */         {
/* 155 */           this.dummyTextureManager = new MCH_TextureManagerDummy(rm.field_78724_e);
/*     */         }
/* 157 */         rm.field_78724_e = (TextureManager)this.dummyTextureManager;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLivingEventPost(RenderLivingEvent.Post event) {
/* 164 */     MCH_RenderAircraft.renderEntityMarker((Entity)event.entity);
/* 165 */     MCH_GuiTargetMarker.addMarkEntityPos(2, (Entity)event.entity, event.x, event.y + event.entity.field_70131_O + 0.5D, event.z);
/* 166 */     MCH_ClientLightWeaponTickHandler.markEntity((Entity)event.entity, event.x, event.y + (event.entity.field_70131_O / 2.0F), event.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderPlayerPre(RenderPlayerEvent.Pre event) {
/* 171 */     if (event.entity == null) {
/*     */       return;
/*     */     }
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
/* 191 */     if (event.entity.field_70154_o instanceof MCH_EntityAircraft) {
/*     */       
/* 193 */       MCH_EntityAircraft v = (MCH_EntityAircraft)event.entity.field_70154_o;
/* 194 */       if (v.getAcInfo() != null && (v.getAcInfo()).hideEntity) {
/*     */         
/* 196 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPlayerPost(RenderPlayerEvent.Post event) {}
/*     */ 
/*     */   
/*     */   public void worldEventUnload(WorldEvent.Unload event) {
/* 208 */     MCH_ViewEntityDummy.onUnloadWorld();
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
/* 213 */     if (event.entity.func_70028_i(MCH_Lib.getClientPlayer())) {
/*     */       
/* 215 */       MCH_Lib.DbgLog(true, "MCH_ClientEventHook.entityJoinWorldEvent : " + event.entity, new Object[0]);
/* 216 */       MCH_ItemRangeFinder.mode = Minecraft.func_71410_x().func_71356_B() ? 1 : 0;
/*     */       
/* 218 */       MCH_ParticlesUtil.clearMarkPoint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ClientEventHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */