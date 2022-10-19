/*     */ package mcheli.mcheli.parachute;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.parachute.MCH_EntityParachute;
/*     */ import mcheli.wrapper.W_Render;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_RenderParachute
/*     */   extends W_Render
/*     */ {
/*  24 */   public static final Random rand = new Random();
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
/*  39 */     if (!(entity instanceof MCH_EntityParachute))
/*     */       return; 
/*  41 */     MCH_EntityParachute parachute = (MCH_EntityParachute)entity;
/*  42 */     int type = parachute.getType();
/*  43 */     if (type <= 0)
/*     */       return; 
/*  45 */     GL11.glPushMatrix();
/*     */     
/*  47 */     GL11.glEnable(2884);
/*     */     
/*  49 */     GL11.glTranslated(posX, posY, posZ);
/*     */ 
/*     */ 
/*     */     
/*  53 */     float prevYaw = entity.field_70126_B;
/*  54 */     if (entity.field_70177_z - prevYaw < -180.0F) { prevYaw -= 360.0F; }
/*  55 */     else if (prevYaw - entity.field_70177_z < -180.0F) { prevYaw += 360.0F; }
/*     */     
/*  57 */     float yaw = prevYaw + (entity.field_70177_z - prevYaw) * tickTime;
/*  58 */     GL11.glRotatef(yaw, 0.0F, -1.0F, 0.0F);
/*     */ 
/*     */     
/*  61 */     GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
/*     */     
/*  63 */     GL11.glEnable(3042);
/*  64 */     int srcBlend = GL11.glGetInteger(3041);
/*  65 */     int dstBlend = GL11.glGetInteger(3040);
/*  66 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  68 */     if (MCH_Config.SmoothShading.prmBool)
/*     */     {
/*  70 */       GL11.glShadeModel(7425);
/*     */     }
/*     */ 
/*     */     
/*  74 */     switch (type) {
/*     */       
/*     */       case 1:
/*  77 */         bindTexture("textures/parachute1.png");
/*  78 */         MCH_ModelManager.render("parachute1");
/*     */         break;
/*     */       case 2:
/*  81 */         bindTexture("textures/parachute2.png");
/*  82 */         if (parachute.isOpenParachute()) {
/*     */           
/*  84 */           MCH_ModelManager.renderPart("parachute2", "$parachute");
/*     */           
/*     */           break;
/*     */         } 
/*  88 */         MCH_ModelManager.renderPart("parachute2", "$seat");
/*     */         break;
/*     */       
/*     */       case 3:
/*  92 */         bindTexture("textures/parachute2.png");
/*  93 */         MCH_ModelManager.renderPart("parachute2", "$parachute");
/*     */         break;
/*     */     } 
/*     */     
/*  97 */     GL11.glBlendFunc(srcBlend, dstBlend);
/*  98 */     GL11.glDisable(3042);
/*  99 */     GL11.glShadeModel(7424);
/*     */     
/* 101 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) {
/* 106 */     return TEX_DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\parachute\MCH_RenderParachute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */