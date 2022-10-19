/*     */ package mcheli.mcheli.particles;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.multiplay.MCH_GuiTargetMarker;
/*     */ import mcheli.particles.MCH_EntityParticleBase;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityParticleMarkPoint
/*     */   extends MCH_EntityParticleBase
/*     */ {
/*     */   final Team taem;
/*     */   
/*     */   public MCH_EntityParticleMarkPoint(World par1World, double x, double y, double z, Team team) {
/*  26 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*  27 */     setParticleMaxAge(30);
/*  28 */     this.taem = team;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  34 */     this.field_70169_q = this.field_70165_t;
/*  35 */     this.field_70167_r = this.field_70163_u;
/*  36 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  38 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  39 */     if (entityClientPlayerMP == null) {
/*     */       
/*  41 */       func_70106_y();
/*     */     }
/*  43 */     else if (entityClientPlayerMP.func_96124_cp() == null && this.taem != null) {
/*     */       
/*  45 */       func_70106_y();
/*     */     }
/*  47 */     else if (entityClientPlayerMP.func_96124_cp() != null && !entityClientPlayerMP.func_142012_a(this.taem)) {
/*     */       
/*  49 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/*  55 */     super.func_70106_y();
/*  56 */     MCH_Lib.DbgLog(true, "MCH_EntityParticleMarkPoint.setDead : " + this, new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70537_b() {
/*  61 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  66 */     GL11.glPushMatrix();
/*     */     
/*  68 */     Minecraft mc = Minecraft.func_71410_x();
/*  69 */     EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
/*  70 */     if (entityClientPlayerMP == null)
/*     */       return; 
/*  72 */     double ix = field_70556_an;
/*  73 */     double iy = field_70554_ao;
/*  74 */     double iz = field_70555_ap;
/*     */     
/*  76 */     if (mc.field_71474_y.field_74320_O > 0 && mc.field_71451_h != null) {
/*     */       
/*  78 */       EntityLivingBase entityLivingBase = mc.field_71451_h;
/*  79 */       double dist = W_Reflection.getThirdPersonDistance();
/*  80 */       float yaw = (mc.field_71474_y.field_74320_O != 2) ? -((Entity)entityLivingBase).field_70177_z : -((Entity)entityLivingBase).field_70177_z;
/*  81 */       float pitch = (mc.field_71474_y.field_74320_O != 2) ? -((Entity)entityLivingBase).field_70125_A : -((Entity)entityLivingBase).field_70125_A;
/*  82 */       Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, -dist, yaw, pitch);
/*  83 */       if (mc.field_71474_y.field_74320_O == 2) {
/*     */         
/*  85 */         v.field_72450_a = -v.field_72450_a;
/*  86 */         v.field_72448_b = -v.field_72448_b;
/*  87 */         v.field_72449_c = -v.field_72449_c;
/*     */       } 
/*  89 */       Vec3 vs = Vec3.func_72443_a(((Entity)entityLivingBase).field_70165_t, ((Entity)entityLivingBase).field_70163_u + entityLivingBase.func_70047_e(), ((Entity)entityLivingBase).field_70161_v);
/*  90 */       MovingObjectPosition mop = mc.field_71451_h.field_70170_p.func_72933_a(vs.func_72441_c(0.0D, 0.0D, 0.0D), vs.func_72441_c(v.field_72450_a, v.field_72448_b, v.field_72449_c));
/*     */ 
/*     */       
/*  93 */       double block_dist = dist;
/*  94 */       if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */         
/*  96 */         block_dist = vs.func_72438_d(mop.field_72307_f) - 0.4D;
/*  97 */         if (block_dist < 0.0D) block_dist = 0.0D;
/*     */       
/*     */       } 
/* 100 */       GL11.glTranslated(v.field_72450_a * block_dist / dist, v.field_72448_b * block_dist / dist, v.field_72449_c * block_dist / dist);
/* 101 */       ix += v.field_72450_a * block_dist / dist;
/* 102 */       iy += v.field_72448_b * block_dist / dist;
/* 103 */       iz += v.field_72449_c * block_dist / dist;
/*     */     } 
/*     */     
/* 106 */     double px = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - ix);
/* 107 */     double py = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - iy);
/* 108 */     double pz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - iz);
/* 109 */     double scale = Math.sqrt(px * px + py * py + pz * pz) / 10.0D;
/* 110 */     if (scale < 1.0D)
/*     */     {
/* 112 */       scale = 1.0D;
/*     */     }
/*     */     
/* 115 */     MCH_GuiTargetMarker.addMarkEntityPos(100, (Entity)this, px / scale, py / scale, pz / scale, false);
/*     */     
/* 117 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_EntityParticleMarkPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */