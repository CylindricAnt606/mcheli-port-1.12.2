/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.aircraft.MCH_BoundingBox;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
import net.minecraft.util.math.AxisAlignedBB;

/*     */
/*     */ public class MCH_AircraftBoundingBox extends AxisAlignedBB {
/*     */   private final MCH_EntityAircraft ac;
/*     */   
/*     */   protected MCH_AircraftBoundingBox(MCH_EntityAircraft ac) {
/*  13 */     super(ac.field_70121_D.field_72340_a, ac.field_70121_D.field_72338_b, ac.field_70121_D.field_72339_c, ac.field_70121_D.field_72336_d, ac.field_70121_D.field_72337_e, ac.field_70121_D.field_72334_f);
/*     */     
/*  15 */     this.ac = ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB NewAABB(double p_72324_1_, double p_72324_3_, double p_72324_5_, double p_72324_7_, double p_72324_9_, double p_72324_11_) {
/*  20 */     return (new mcheli.aircraft.MCH_AircraftBoundingBox(this.ac)).func_72324_b(p_72324_1_, p_72324_3_, p_72324_5_, p_72324_7_, p_72324_9_, p_72324_11_);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDistSq(AxisAlignedBB a1, AxisAlignedBB a2) {
/*  25 */     double x1 = (a1.field_72336_d + a1.field_72340_a) / 2.0D;
/*  26 */     double y1 = (a1.field_72337_e + a1.field_72338_b) / 2.0D;
/*  27 */     double z1 = (a1.field_72334_f + a1.field_72339_c) / 2.0D;
/*  28 */     double x2 = (a2.field_72336_d + a2.field_72340_a) / 2.0D;
/*  29 */     double y2 = (a2.field_72337_e + a2.field_72338_b) / 2.0D;
/*  30 */     double z2 = (a2.field_72334_f + a2.field_72339_c) / 2.0D;
/*  31 */     double dx = x1 - x2;
/*  32 */     double dy = y1 - y2;
/*  33 */     double dz = z1 - z2;
/*  34 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_72326_a(AxisAlignedBB aabb) {
/*  39 */     boolean ret = false;
/*  40 */     double dist = 1.0E7D;
/*  41 */     this.ac.lastBBDamageFactor = 1.0F;
/*  42 */     if (super.func_72326_a(aabb)) {
/*     */       
/*  44 */       dist = getDistSq(aabb, this);
/*  45 */       ret = true;
/*     */     } 
/*  47 */     for (MCH_BoundingBox bb : this.ac.extraBoundingBox) {
/*     */       
/*  49 */       if (bb.boundingBox.func_72326_a(aabb)) {
/*     */         
/*  51 */         double dist2 = getDistSq(aabb, this);
/*  52 */         if (dist2 < dist) {
/*     */           
/*  54 */           dist = dist2;
/*  55 */           this.ac.lastBBDamageFactor = bb.damegeFactor;
/*     */         } 
/*  57 */         ret = true;
/*     */       } 
/*     */     } 
/*  60 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_72314_b(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
/*  65 */     double d3 = this.field_72340_a - p_72314_1_;
/*  66 */     double d4 = this.field_72338_b - p_72314_3_;
/*  67 */     double d5 = this.field_72339_c - p_72314_5_;
/*  68 */     double d6 = this.field_72336_d + p_72314_1_;
/*  69 */     double d7 = this.field_72337_e + p_72314_3_;
/*  70 */     double d8 = this.field_72334_f + p_72314_5_;
/*  71 */     return NewAABB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_111270_a(AxisAlignedBB p_111270_1_) {
/*  76 */     double d0 = Math.min(this.field_72340_a, p_111270_1_.field_72340_a);
/*  77 */     double d1 = Math.min(this.field_72338_b, p_111270_1_.field_72338_b);
/*  78 */     double d2 = Math.min(this.field_72339_c, p_111270_1_.field_72339_c);
/*  79 */     double d3 = Math.max(this.field_72336_d, p_111270_1_.field_72336_d);
/*  80 */     double d4 = Math.max(this.field_72337_e, p_111270_1_.field_72337_e);
/*  81 */     double d5 = Math.max(this.field_72334_f, p_111270_1_.field_72334_f);
/*  82 */     return NewAABB(d0, d1, d2, d3, d4, d5);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_72321_a(double p_72321_1_, double p_72321_3_, double p_72321_5_) {
/*  87 */     double d3 = this.field_72340_a;
/*  88 */     double d4 = this.field_72338_b;
/*  89 */     double d5 = this.field_72339_c;
/*  90 */     double d6 = this.field_72336_d;
/*  91 */     double d7 = this.field_72337_e;
/*  92 */     double d8 = this.field_72334_f;
/*     */     
/*  94 */     if (p_72321_1_ < 0.0D)
/*     */     {
/*  96 */       d3 += p_72321_1_;
/*     */     }
/*     */     
/*  99 */     if (p_72321_1_ > 0.0D)
/*     */     {
/* 101 */       d6 += p_72321_1_;
/*     */     }
/*     */     
/* 104 */     if (p_72321_3_ < 0.0D)
/*     */     {
/* 106 */       d4 += p_72321_3_;
/*     */     }
/*     */     
/* 109 */     if (p_72321_3_ > 0.0D)
/*     */     {
/* 111 */       d7 += p_72321_3_;
/*     */     }
/*     */     
/* 114 */     if (p_72321_5_ < 0.0D)
/*     */     {
/* 116 */       d5 += p_72321_5_;
/*     */     }
/*     */     
/* 119 */     if (p_72321_5_ > 0.0D)
/*     */     {
/* 121 */       d8 += p_72321_5_;
/*     */     }
/*     */     
/* 124 */     return NewAABB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_72331_e(double p_72331_1_, double p_72331_3_, double p_72331_5_) {
/* 129 */     double d3 = this.field_72340_a + p_72331_1_;
/* 130 */     double d4 = this.field_72338_b + p_72331_3_;
/* 131 */     double d5 = this.field_72339_c + p_72331_5_;
/* 132 */     double d6 = this.field_72336_d - p_72331_1_;
/* 133 */     double d7 = this.field_72337_e - p_72331_3_;
/* 134 */     double d8 = this.field_72334_f - p_72331_5_;
/* 135 */     return NewAABB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_72329_c() {
/* 140 */     return NewAABB(this.field_72340_a, this.field_72338_b, this.field_72339_c, this.field_72336_d, this.field_72337_e, this.field_72334_f);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_72325_c(double x, double y, double z) {
/* 145 */     return NewAABB(this.field_72340_a + x, this.field_72338_b + y, this.field_72339_c + z, this.field_72336_d + x, this.field_72337_e + y, this.field_72334_f + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_72327_a(Vec3 v1, Vec3 v2) {
/* 152 */     this.ac.lastBBDamageFactor = 1.0F;
/* 153 */     MovingObjectPosition mop = super.func_72327_a(v1, v2);
/* 154 */     double dist = 1.0E7D;
/* 155 */     if (mop != null)
/*     */     {
/* 157 */       dist = v1.func_72438_d(mop.field_72307_f);
/*     */     }
/* 159 */     for (MCH_BoundingBox bb : this.ac.extraBoundingBox) {
/*     */       
/* 161 */       MovingObjectPosition mop2 = bb.boundingBox.func_72327_a(v1, v2);
/* 162 */       if (mop2 != null) {
/*     */         
/* 164 */         double dist2 = v1.func_72438_d(mop2.field_72307_f);
/* 165 */         if (dist2 < dist) {
/*     */           
/* 167 */           mop = mop2;
/* 168 */           dist = dist2;
/* 169 */           this.ac.lastBBDamageFactor = bb.damegeFactor;
/*     */         } 
/*     */       } 
/*     */     } 
/* 173 */     return mop;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftBoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */