/*     */ package mcheli.mcheli.gltd;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_RenderLib;
/*     */ import mcheli.gltd.MCH_EntityGLTD;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_Render;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderGLTD
/*     */   extends W_Render
/*     */ {
/*  23 */   public static final Random rand = new Random();
/*     */   
/*     */   public static IModelCustom model;
/*     */   
/*     */   public MCH_RenderGLTD() {
/*  28 */     this.field_76989_e = 0.5F;
/*  29 */     this; model = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76986_a(Entity entity, double posX, double posY, double posZ, float par8, float tickTime) {
/*  40 */     if (!(entity instanceof MCH_EntityGLTD))
/*  41 */       return;  MCH_EntityGLTD gltd = (MCH_EntityGLTD)entity;
/*     */     
/*  43 */     GL11.glPushMatrix();
/*     */     
/*  45 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */     
/*  48 */     setCommonRenderParam(true, entity.func_70070_b(tickTime));
/*     */ 
/*     */     
/*  51 */     bindTexture("textures/gltd.png");
/*     */     
/*  53 */     Minecraft mc = Minecraft.func_71410_x();
/*  54 */     boolean isNotRenderHead = false;
/*     */     
/*  56 */     if (gltd.field_70153_n != null) {
/*     */       
/*  58 */       gltd.isUsedPlayer = true;
/*     */ 
/*     */       
/*  61 */       gltd.renderRotaionYaw = gltd.field_70153_n.field_70177_z;
/*  62 */       gltd.renderRotaionPitch = gltd.field_70153_n.field_70125_A;
/*     */ 
/*     */       
/*  65 */       isNotRenderHead = (mc.field_71474_y.field_74320_O == 0 && W_Lib.isClientPlayer(gltd.field_70153_n));
/*     */     } 
/*     */ 
/*     */     
/*  69 */     if (gltd.isUsedPlayer) {
/*     */       
/*  71 */       GL11.glPushMatrix();
/*  72 */       GL11.glRotatef(-gltd.field_70177_z, 0.0F, 1.0F, 0.0F);
/*  73 */       model.renderPart("$body");
/*  74 */       GL11.glPopMatrix();
/*     */     }
/*     */     else {
/*     */       
/*  78 */       GL11.glRotatef(-gltd.field_70177_z, 0.0F, 1.0F, 0.0F);
/*  79 */       model.renderPart("$body");
/*     */     } 
/*     */ 
/*     */     
/*  83 */     GL11.glTranslatef(0.0F, 0.45F, 0.0F);
/*  84 */     if (gltd.isUsedPlayer) {
/*     */       
/*  86 */       GL11.glRotatef(gltd.renderRotaionYaw, 0.0F, -1.0F, 0.0F);
/*  87 */       GL11.glRotatef(gltd.renderRotaionPitch, 1.0F, 0.0F, 0.0F);
/*     */     } 
/*  89 */     GL11.glTranslatef(0.0F, -0.45F, 0.0F);
/*  90 */     if (!isNotRenderHead)
/*     */     {
/*  92 */       model.renderPart("$head");
/*     */     }
/*  94 */     GL11.glTranslatef(0.0F, 0.45F, 0.0F);
/*     */ 
/*     */     
/*  97 */     restoreCommonRenderParam();
/*     */     
/*  99 */     GL11.glDisable(2896);
/*     */     
/* 101 */     Vec3[] v = { Vec3.func_72443_a(0.0D, 0.2D, 0.0D), Vec3.func_72443_a(0.0D, 0.2D, 100.0D) };
/*     */ 
/*     */ 
/*     */     
/* 105 */     int a = rand.nextInt(64);
/* 106 */     MCH_RenderLib.drawLine(v, 0x6080FF80 | a << 24);
/* 107 */     GL11.glEnable(2896);
/*     */     
/* 109 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 114 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_RenderGLTD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */