/*     */ package mcheli.mcheli.particles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.particles.MCH_EntityParticleBase;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityParticleSmoke
/*     */   extends MCH_EntityParticleBase
/*     */ {
/*     */   public MCH_EntityParticleSmoke(World par1World, double x, double y, double z, double mx, double my, double mz) {
/*  25 */     super(par1World, x, y, z, mx, my, mz);
/*  26 */     this.field_70552_h = this.field_70553_i = this.field_70551_j = this.field_70146_Z.nextFloat() * 0.3F + 0.7F;
/*  27 */     setParticleScale(this.field_70146_Z.nextFloat() * 0.5F + 5.0F);
/*  28 */     setParticleMaxAge((int)(16.0D / (this.field_70146_Z.nextFloat() * 0.8D + 0.2D)) + 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  40 */     this.field_70169_q = this.field_70165_t;
/*  41 */     this.field_70167_r = this.field_70163_u;
/*  42 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  44 */     if (this.field_70546_d < this.field_70547_e) {
/*     */       
/*  46 */       func_70536_a((int)(8.0D * this.field_70546_d / this.field_70547_e));
/*  47 */       this.field_70546_d++;
/*     */     }
/*     */     else {
/*     */       
/*  51 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     if (this.diffusible)
/*     */     {
/*  57 */       if (this.field_70544_f < this.particleMaxScale)
/*     */       {
/*  59 */         this.field_70544_f += 0.8F;
/*     */       }
/*     */     }
/*     */     
/*  63 */     if (this.toWhite) {
/*     */       
/*  65 */       float mn = getMinColor();
/*  66 */       float mx = getMaxColor();
/*  67 */       float dist = mx - mn;
/*  68 */       if (dist > 0.2D) {
/*     */         
/*  70 */         this.field_70552_h += (mx - this.field_70552_h) * 0.016F;
/*  71 */         this.field_70553_i += (mx - this.field_70553_i) * 0.016F;
/*  72 */         this.field_70551_j += (mx - this.field_70551_j) * 0.016F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     effectWind();
/*     */     
/*  81 */     if (this.field_70546_d / this.field_70547_e > this.moutionYUpAge) {
/*     */       
/*  83 */       this.field_70181_x += 0.02D;
/*     */     }
/*     */     else {
/*     */       
/*  87 */       this.field_70181_x += this.gravity;
/*     */     } 
/*     */     
/*  90 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */ 
/*     */ 
/*     */     
/*  94 */     if (this.diffusible) {
/*     */       
/*  96 */       this.field_70159_w *= 0.96D;
/*  97 */       this.field_70179_y *= 0.96D;
/*  98 */       this.field_70181_x *= 0.96D;
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.field_70159_w *= 0.9D;
/* 103 */       this.field_70179_y *= 0.9D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinColor() {
/* 110 */     return min(min(this.field_70551_j, this.field_70553_i), this.field_70552_h);
/*     */   }
/*     */   
/*     */   public float getMaxColor() {
/* 114 */     return max(max(this.field_70551_j, this.field_70553_i), this.field_70552_h);
/*     */   }
/*     */   
/*     */   public float min(float a, float b) {
/* 118 */     return (a < b) ? a : b;
/*     */   }
/*     */   
/*     */   public float max(float a, float b) {
/* 122 */     return (a > b) ? a : b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void effectWind() {
/* 127 */     if (this.isEffectedWind) {
/*     */       
/* 129 */       int range = 15;
/* 130 */       List<MCH_EntityAircraft> list = this.field_70170_p.func_72872_a(MCH_EntityAircraft.class, func_70046_E().func_72314_b(15.0D, 15.0D, 15.0D));
/*     */       
/* 132 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 134 */         MCH_EntityAircraft ac = list.get(i);
/* 135 */         if (ac.getThrottle() > 0.10000000149011612D) {
/*     */           
/* 137 */           float dist = func_70032_d((Entity)ac);
/* 138 */           double vel = (23.0D - dist) * 0.009999999776482582D * ac.getThrottle();
/* 139 */           double mx = ac.field_70165_t - this.field_70165_t;
/* 140 */           double mz = ac.field_70161_v - this.field_70161_v;
/*     */           
/* 142 */           this.field_70159_w -= mx * vel;
/* 143 */           this.field_70179_y -= mz * vel;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70537_b() {
/* 151 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float p_70070_1_) {
/* 157 */     double y = this.field_70163_u;
/*     */     
/* 159 */     this.field_70163_u += 3000.0D;
/* 160 */     int i = super.func_70070_b(p_70070_1_);
/* 161 */     this.field_70163_u = y;
/*     */     
/* 163 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 168 */     W_McClient.MOD_bindTexture("textures/particles/smoke.png");
/*     */     
/* 170 */     GL11.glEnable(3042);
/* 171 */     int srcBlend = GL11.glGetInteger(3041);
/* 172 */     int dstBlend = GL11.glGetInteger(3040);
/* 173 */     GL11.glBlendFunc(770, 771);
/* 174 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 176 */     GL11.glDisable(2896);
/*     */     
/* 178 */     GL11.glDisable(2884);
/*     */     
/* 180 */     float f6 = this.field_94054_b / 8.0F;
/* 181 */     float f7 = f6 + 0.125F;
/* 182 */     float f8 = 0.0F;
/* 183 */     float f9 = 1.0F;
/* 184 */     float f10 = 0.1F * this.field_70544_f;
/*     */     
/* 186 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - field_70556_an);
/* 187 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - field_70554_ao);
/* 188 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - field_70555_ap);
/* 189 */     par1Tessellator.func_78382_b();
/* 190 */     par1Tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
/* 191 */     par1Tessellator.func_78380_c(func_70070_b(par2));
/* 192 */     par1Tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 193 */     par1Tessellator.func_78374_a((f11 - par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 - par5 * f10 - par7 * f10), f7, f9);
/* 194 */     par1Tessellator.func_78374_a((f11 - par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 - par5 * f10 + par7 * f10), f7, f8);
/* 195 */     par1Tessellator.func_78374_a((f11 + par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 + par5 * f10 + par7 * f10), f6, f8);
/* 196 */     par1Tessellator.func_78374_a((f11 + par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 + par5 * f10 - par7 * f10), f6, f9);
/* 197 */     par1Tessellator.func_78381_a();
/*     */     
/* 199 */     GL11.glEnable(2884);
/*     */     
/* 201 */     GL11.glEnable(2896);
/*     */     
/* 203 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 204 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_EntityParticleSmoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */