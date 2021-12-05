/*    */ package mcheli.mcheli.particles;
/*    */ 
/*    */ import mcheli.particles.MCH_EntityParticleBase;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class MCH_EntityParticleExplode
/*    */   extends MCH_EntityParticleBase
/*    */ {
/* 16 */   private static final ResourceLocation texture = new ResourceLocation("textures/entity/explosion.png");
/*    */   
/*    */   private int nowCount;
/*    */   
/*    */   private int endCount;
/*    */   
/*    */   private TextureManager theRenderEngine;
/*    */   private float size;
/*    */   
/*    */   public MCH_EntityParticleExplode(World w, double x, double y, double z, double size, double age, double mz) {
/* 26 */     super(w, x, y, z, 0.0D, 0.0D, 0.0D);
/* 27 */     this.theRenderEngine = (Minecraft.func_71410_x()).field_71446_o;
/* 28 */     this.endCount = 1 + (int)age;
/* 29 */     this.size = (float)size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70539_a(Tessellator tessellator, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
/* 35 */     int i = (int)((this.nowCount + p_70539_2_) * 15.0F / this.endCount);
/*    */     
/* 37 */     if (i <= 15) {
/*    */       
/* 39 */       GL11.glEnable(3042);
/* 40 */       int srcBlend = GL11.glGetInteger(3041);
/* 41 */       int dstBlend = GL11.glGetInteger(3040);
/* 42 */       GL11.glBlendFunc(770, 771);
/*    */       
/* 44 */       GL11.glDisable(2884);
/* 45 */       this.theRenderEngine.func_110577_a(texture);
/* 46 */       float f6 = (i % 4) / 4.0F;
/* 47 */       float f7 = f6 + 0.24975F;
/* 48 */       float f8 = (i / 4) / 4.0F;
/* 49 */       float f9 = f8 + 0.24975F;
/* 50 */       float f10 = 2.0F * this.size;
/* 51 */       float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * p_70539_2_ - field_70556_an);
/* 52 */       float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * p_70539_2_ - field_70554_ao);
/* 53 */       float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * p_70539_2_ - field_70555_ap);
/* 54 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 56 */       RenderHelper.func_74518_a();
/* 57 */       tessellator.func_78382_b();
/* 58 */       tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
/* 59 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 60 */       tessellator.func_78380_c(15728880);
/* 61 */       tessellator.func_78374_a((f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (f12 - p_70539_4_ * f10), (f13 - p_70539_5_ * f10 - p_70539_7_ * f10), f7, f9);
/* 62 */       tessellator.func_78374_a((f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (f12 + p_70539_4_ * f10), (f13 - p_70539_5_ * f10 + p_70539_7_ * f10), f7, f8);
/* 63 */       tessellator.func_78374_a((f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (f12 + p_70539_4_ * f10), (f13 + p_70539_5_ * f10 + p_70539_7_ * f10), f6, f8);
/* 64 */       tessellator.func_78374_a((f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (f12 - p_70539_4_ * f10), (f13 + p_70539_5_ * f10 - p_70539_7_ * f10), f6, f9);
/* 65 */       tessellator.func_78381_a();
/* 66 */       GL11.glPolygonOffset(0.0F, 0.0F);
/* 67 */       GL11.glEnable(2896);
/* 68 */       GL11.glEnable(2884);
/*    */       
/* 70 */       GL11.glBlendFunc(srcBlend, dstBlend);
/* 71 */       GL11.glDisable(3042);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_70070_b(float p_70070_1_) {
/* 77 */     return 15728880;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 85 */     this.field_70169_q = this.field_70165_t;
/* 86 */     this.field_70167_r = this.field_70163_u;
/* 87 */     this.field_70166_s = this.field_70161_v;
/* 88 */     this.nowCount++;
/*    */     
/* 90 */     if (this.nowCount == this.endCount)
/*    */     {
/* 92 */       func_70106_y();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_70537_b() {
/* 98 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_EntityParticleExplode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */