/*     */ package mcheli.mcheli.uav;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.wrapper.W_Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderUavStation
/*     */   extends W_Render
/*     */ {
/*  17 */   public static final String[] MODEL_NAME = new String[] { "uav_station", "uav_portable_controller" };
/*     */ 
/*     */   
/*  20 */   public static final String[] TEX_NAME_ON = new String[] { "uav_station_on", "uav_portable_controller_on" };
/*     */ 
/*     */   
/*  23 */   public static final String[] TEX_NAME_OFF = new String[] { "uav_station", "uav_portable_controller" };
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
/*     */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/*  41 */     if (!(entity instanceof MCH_EntityUavStation))
/*     */       return; 
/*  43 */     MCH_EntityUavStation uavSt = (MCH_EntityUavStation)entity;
/*     */     
/*  45 */     if (uavSt.getKind() <= 0)
/*  46 */       return;  int kind = uavSt.getKind() - 1;
/*     */ 
/*     */     
/*  49 */     GL11.glPushMatrix();
/*     */     
/*  51 */     GL11.glTranslated(posX, posY, posZ);
/*     */     
/*  53 */     GL11.glEnable(2884);
/*     */ 
/*     */     
/*  56 */     GL11.glRotatef(entity.field_70177_z, 0.0F, -1.0F, 0.0F);
/*  57 */     GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  60 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*     */ 
/*     */     
/*  63 */     GL11.glEnable(3042);
/*  64 */     int srcBlend = GL11.glGetInteger(3041);
/*  65 */     int dstBlend = GL11.glGetInteger(3040);
/*  66 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  68 */     if (kind == 0) {
/*     */       
/*  70 */       if (uavSt.getControlAircract() != null && uavSt.field_70153_n != null) {
/*     */         
/*  72 */         bindTexture("textures/" + TEX_NAME_ON[kind] + ".png");
/*     */       }
/*     */       else {
/*     */         
/*  76 */         bindTexture("textures/" + TEX_NAME_OFF[kind] + ".png");
/*     */       } 
/*  78 */       MCH_ModelManager.render(MODEL_NAME[kind]);
/*     */     }
/*     */     else {
/*     */       
/*  82 */       if (uavSt.rotCover > 0.95F) {
/*     */         
/*  84 */         bindTexture("textures/" + TEX_NAME_ON[kind] + ".png");
/*     */       }
/*     */       else {
/*     */         
/*  88 */         bindTexture("textures/" + TEX_NAME_OFF[kind] + ".png");
/*     */       } 
/*  90 */       renderPortableController(uavSt, MODEL_NAME[kind], tickTime);
/*     */     } 
/*     */     
/*  93 */     GL11.glBlendFunc(srcBlend, dstBlend);
/*  94 */     GL11.glDisable(3042);
/*     */     
/*  96 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderPortableController(MCH_EntityUavStation uavSt, String name, float tickTime) {
/* 101 */     MCH_ModelManager.renderPart(name, "$body");
/*     */     
/* 103 */     float rot = MCH_Lib.smooth(uavSt.rotCover, uavSt.prevRotCover, tickTime);
/*     */     
/* 105 */     renderRotPart(name, "$cover", rot * 60.0F, 0.0D, -0.1812D, -0.3186D);
/* 106 */     renderRotPart(name, "$laptop_cover", rot * 95.0F, 0.0D, -0.1808D, -0.0422D);
/* 107 */     renderRotPart(name, "$display", rot * -85.0F, 0.0D, -0.1807D, 0.2294D);
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderRotPart(String modelName, String partName, float rot, double x, double y, double z) {
/* 112 */     GL11.glPushMatrix();
/* 113 */     GL11.glTranslated(x, y, z);
/* 114 */     GL11.glRotatef(rot, -1.0F, 0.0F, 0.0F);
/* 115 */     GL11.glTranslated(-x, -y, -z);
/* 116 */     MCH_ModelManager.renderPart(modelName, partName);
/* 117 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 122 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_RenderUavStation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */