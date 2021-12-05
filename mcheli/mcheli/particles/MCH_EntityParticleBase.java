/*     */ package mcheli.mcheli.particles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_EntityFX;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MCH_EntityParticleBase
/*     */   extends W_EntityFX
/*     */ {
/*     */   public boolean isEffectedWind;
/*     */   public boolean diffusible;
/*     */   public boolean toWhite;
/*     */   public float particleMaxScale;
/*     */   public float gravity;
/*     */   public float moutionYUpAge;
/*     */   
/*     */   public MCH_EntityParticleBase(World par1World, double x, double y, double z, double mx, double my, double mz) {
/*  31 */     super(par1World, x, y, z, mx, my, mz);
/*  32 */     this.field_70159_w = mx;
/*  33 */     this.field_70181_x = my;
/*  34 */     this.field_70179_y = mz;
/*  35 */     this.isEffectedWind = false;
/*  36 */     this.particleMaxScale = this.field_70544_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public mcheli.particles.MCH_EntityParticleBase setParticleScale(float scale) {
/*  41 */     this.field_70544_f = scale;
/*  42 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParticleMaxAge(int age) {
/*  47 */     this.field_70547_e = age;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70536_a(int par1) {
/*  52 */     this.field_94054_b = par1 % 8;
/*  53 */     this.field_94055_c = par1 / 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70537_b() {
/*  61 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70091_d(double par1, double par3, double par5) {
/*  69 */     if (this.field_70145_X) {
/*     */       
/*  71 */       this.field_70121_D.func_72317_d(par1, par3, par5);
/*  72 */       this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/*  73 */       this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V;
/*  74 */       this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*     */     }
/*     */     else {
/*     */       
/*  78 */       this.field_70170_p.field_72984_F.func_76320_a("move");
/*  79 */       this.field_70139_V *= 0.4F;
/*  80 */       double d3 = this.field_70165_t;
/*  81 */       double d4 = this.field_70163_u;
/*  82 */       double d5 = this.field_70161_v;
/*     */       
/*  84 */       double d6 = par1;
/*  85 */       double d7 = par3;
/*  86 */       double d8 = par5;
/*  87 */       AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/*  88 */       boolean flag = false;
/*     */       
/*  90 */       List<AxisAlignedBB> list = this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72321_a(par1, par3, par5));
/*     */       
/*  92 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/*  94 */         par3 = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, par3);
/*     */       }
/*     */       
/*  97 */       this.field_70121_D.func_72317_d(0.0D, par3, 0.0D);
/*     */       
/*  99 */       if (!this.field_70135_K && d7 != par3) {
/*     */         
/* 101 */         par5 = 0.0D;
/* 102 */         par3 = 0.0D;
/* 103 */         par1 = 0.0D;
/*     */       } 
/*     */       
/* 106 */       boolean flag1 = (this.field_70122_E || (d7 != par3 && d7 < 0.0D));
/*     */       
/*     */       int j;
/* 109 */       for (j = 0; j < list.size(); j++)
/*     */       {
/* 111 */         par1 = ((AxisAlignedBB)list.get(j)).func_72316_a(this.field_70121_D, par1);
/*     */       }
/*     */       
/* 114 */       this.field_70121_D.func_72317_d(par1, 0.0D, 0.0D);
/*     */       
/* 116 */       if (!this.field_70135_K && d6 != par1) {
/*     */         
/* 118 */         par5 = 0.0D;
/* 119 */         par3 = 0.0D;
/* 120 */         par1 = 0.0D;
/*     */       } 
/*     */       
/* 123 */       for (j = 0; j < list.size(); j++)
/*     */       {
/* 125 */         par5 = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, par5);
/*     */       }
/*     */       
/* 128 */       this.field_70121_D.func_72317_d(0.0D, 0.0D, par5);
/*     */       
/* 130 */       if (!this.field_70135_K && d8 != par5) {
/*     */         
/* 132 */         par5 = 0.0D;
/* 133 */         par3 = 0.0D;
/* 134 */         par1 = 0.0D;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 225 */       this.field_70170_p.field_72984_F.func_76319_b();
/* 226 */       this.field_70170_p.field_72984_F.func_76320_a("rest");
/* 227 */       this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 228 */       this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V;
/* 229 */       this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/* 230 */       this.field_70123_F = (d6 != par1 || d8 != par5);
/* 231 */       this.field_70124_G = (d7 != par3);
/* 232 */       this.field_70122_E = (d7 != par3 && d7 < 0.0D);
/* 233 */       this.field_70132_H = (this.field_70123_F || this.field_70124_G);
/* 234 */       func_70064_a(par3, this.field_70122_E);
/*     */       
/* 236 */       if (d6 != par1)
/*     */       {
/* 238 */         this.field_70159_w = 0.0D;
/*     */       }
/*     */       
/* 241 */       if (d7 != par3)
/*     */       {
/* 243 */         this.field_70181_x = 0.0D;
/*     */       }
/*     */       
/* 246 */       if (d8 != par5)
/*     */       {
/* 248 */         this.field_70179_y = 0.0D;
/*     */       }
/*     */       
/* 251 */       double d12 = this.field_70165_t - d3;
/* 252 */       double d10 = this.field_70163_u - d4;
/* 253 */       double d11 = this.field_70161_v - d5;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 282 */         doBlockCollisions();
/*     */       }
/* 284 */       catch (Throwable throwable) {
/*     */         
/* 286 */         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity block collision");
/* 287 */         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/* 288 */         func_85029_a(crashreportcategory);
/* 289 */         throw new ReportedException(crashreport);
/*     */       } 
/*     */       
/* 292 */       this.field_70170_p.field_72984_F.func_76319_b();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) {
/* 298 */     ArrayList collidingBoundingBoxes = new ArrayList();
/*     */     
/* 300 */     int i = MathHelper.func_76128_c(par2AxisAlignedBB.field_72340_a);
/* 301 */     int j = MathHelper.func_76128_c(par2AxisAlignedBB.field_72336_d + 1.0D);
/* 302 */     int k = MathHelper.func_76128_c(par2AxisAlignedBB.field_72338_b);
/* 303 */     int l = MathHelper.func_76128_c(par2AxisAlignedBB.field_72337_e + 1.0D);
/* 304 */     int i1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72339_c);
/* 305 */     int j1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72334_f + 1.0D);
/*     */     
/* 307 */     for (int k1 = i; k1 < j; k1++) {
/*     */       
/* 309 */       for (int l1 = i1; l1 < j1; l1++) {
/*     */         
/* 311 */         if (this.field_70170_p.func_72899_e(k1, 64, l1))
/*     */         {
/* 313 */           for (int i2 = k - 1; i2 < l; i2++) {
/*     */             Block block;
/*     */ 
/*     */             
/* 317 */             if (k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000) {
/*     */               
/* 319 */               block = W_WorldFunc.getBlock(this.field_70170_p, k1, i2, l1);
/*     */             }
/*     */             else {
/*     */               
/* 323 */               block = W_Blocks.field_150348_b;
/*     */             } 
/*     */             
/* 326 */             block.func_149743_a(this.field_70170_p, k1, i2, l1, par2AxisAlignedBB, collidingBoundingBoxes, par1Entity);
/*     */           } 
/*     */         }
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     return collidingBoundingBoxes;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_EntityParticleBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */