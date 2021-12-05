/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityWheel
/*     */   extends W_Entity
/*     */ {
/*     */   private MCH_EntityAircraft parents;
/*     */   public Vec3 pos;
/*     */   boolean isPlus;
/*     */   
/*     */   public MCH_EntityWheel(World w) {
/*  31 */     super(w);
/*  32 */     func_70105_a(1.0F, 1.0F);
/*  33 */     this.field_70138_W = 1.5F;
/*  34 */     this.field_70178_ae = true;
/*  35 */     this.isPlus = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWheelPos(Vec3 pos, Vec3 weightedCenter) {
/*  40 */     this.pos = pos;
/*  41 */     this.isPlus = (pos.field_72449_c >= weightedCenter.field_72449_c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71027_c(int p_71027_1_) {}
/*     */   
/*     */   public MCH_EntityAircraft getParents() {
/*  48 */     return this.parents;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParents(MCH_EntityAircraft parents) {
/*  53 */     this.parents = parents;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound p_70037_1_) {
/*  59 */     func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound p_70014_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70091_d(double parX, double parY, double parZ) {
/*  70 */     this.field_70170_p.field_72984_F.func_76320_a("move");
/*  71 */     this.field_70139_V *= 0.4F;
/*  72 */     double nowPosX = this.field_70165_t;
/*  73 */     double nowPosY = this.field_70163_u;
/*  74 */     double nowPosZ = this.field_70161_v;
/*     */     
/*  76 */     double mx = parX;
/*  77 */     double my = parY;
/*  78 */     double mz = parZ;
/*  79 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/*     */     
/*  81 */     List<AxisAlignedBB> list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(parX, parY, parZ));
/*     */     
/*  83 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/*  85 */       parY = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, parY);
/*     */     }
/*  87 */     this.field_70121_D.func_72317_d(0.0D, parY, 0.0D);
/*     */     
/*  89 */     boolean flag1 = (this.field_70122_E || (my != parY && my < 0.0D));
/*     */     
/*  91 */     for (int k = 0; k < list.size(); k++)
/*     */     {
/*  93 */       parX = ((AxisAlignedBB)list.get(k)).func_72316_a(this.field_70121_D, parX);
/*     */     }
/*  95 */     this.field_70121_D.func_72317_d(parX, 0.0D, 0.0D);
/*     */     
/*  97 */     for (int j = 0; j < list.size(); j++)
/*     */     {
/*  99 */       parZ = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, parZ);
/*     */     }
/* 101 */     this.field_70121_D.func_72317_d(0.0D, 0.0D, parZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (this.field_70138_W > 0.0F && flag1 && this.field_70139_V < 0.05F && (mx != parX || mz != parZ)) {
/*     */       
/* 109 */       double bkParX = parX;
/* 110 */       double bkParY = parY;
/* 111 */       double bkParZ = parZ;
/* 112 */       parX = mx;
/* 113 */       parY = this.field_70138_W;
/* 114 */       parZ = mz;
/* 115 */       AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
/* 116 */       this.field_70121_D.func_72328_c(axisalignedbb);
/* 117 */       list = getCollidingBoundingBoxes((Entity)this, this.field_70121_D.func_72321_a(mx, parY, mz));
/*     */       int m;
/* 119 */       for (m = 0; m < list.size(); m++)
/*     */       {
/* 121 */         parY = ((AxisAlignedBB)list.get(m)).func_72323_b(this.field_70121_D, parY);
/*     */       }
/* 123 */       this.field_70121_D.func_72317_d(0.0D, parY, 0.0D);
/*     */       
/* 125 */       for (m = 0; m < list.size(); m++)
/*     */       {
/* 127 */         parX = ((AxisAlignedBB)list.get(m)).func_72316_a(this.field_70121_D, parX);
/*     */       }
/* 129 */       this.field_70121_D.func_72317_d(parX, 0.0D, 0.0D);
/*     */       
/* 131 */       for (m = 0; m < list.size(); m++)
/*     */       {
/* 133 */         parZ = ((AxisAlignedBB)list.get(m)).func_72322_c(this.field_70121_D, parZ);
/*     */       }
/* 135 */       this.field_70121_D.func_72317_d(0.0D, 0.0D, parZ);
/*     */       
/* 137 */       parY = -this.field_70138_W;
/* 138 */       for (m = 0; m < list.size(); m++)
/*     */       {
/* 140 */         parY = ((AxisAlignedBB)list.get(m)).func_72323_b(this.field_70121_D, parY);
/*     */       }
/* 142 */       this.field_70121_D.func_72317_d(0.0D, parY, 0.0D);
/*     */       
/* 144 */       if (bkParX * bkParX + bkParZ * bkParZ >= parX * parX + parZ * parZ) {
/*     */         
/* 146 */         parX = bkParX;
/* 147 */         parY = bkParY;
/* 148 */         parZ = bkParZ;
/* 149 */         this.field_70121_D.func_72328_c(axisalignedbb1);
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     this.field_70170_p.field_72984_F.func_76319_b();
/* 154 */     this.field_70170_p.field_72984_F.func_76320_a("rest");
/* 155 */     this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 156 */     this.field_70163_u = this.field_70121_D.field_72338_b + this.field_70129_M - this.field_70139_V;
/* 157 */     this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/* 158 */     this.field_70123_F = (mx != parX || mz != parZ);
/* 159 */     this.field_70124_G = (my != parY);
/* 160 */     this.field_70122_E = (my != parY && my < 0.0D);
/* 161 */     this.field_70132_H = (this.field_70123_F || this.field_70124_G);
/* 162 */     func_70064_a(parY, this.field_70122_E);
/*     */     
/* 164 */     if (mx != parX) this.field_70159_w = 0.0D; 
/* 165 */     if (my != parY) this.field_70181_x = 0.0D; 
/* 166 */     if (mz != parZ) this.field_70179_y = 0.0D;
/*     */ 
/*     */     
/*     */     try {
/* 170 */       doBlockCollisions();
/*     */     }
/* 172 */     catch (Throwable throwable) {
/*     */       
/* 174 */       CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision");
/* 175 */       CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/* 176 */       func_85029_a(crashreportcategory);
/*     */     } 
/*     */ 
/*     */     
/* 180 */     this.field_70170_p.field_72984_F.func_76319_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB) {
/* 185 */     ArrayList<AxisAlignedBB> collidingBoundingBoxes = new ArrayList();
/* 186 */     collidingBoundingBoxes.clear();
/* 187 */     int i = MathHelper.func_76128_c(par2AxisAlignedBB.field_72340_a);
/* 188 */     int j = MathHelper.func_76128_c(par2AxisAlignedBB.field_72336_d + 1.0D);
/* 189 */     int k = MathHelper.func_76128_c(par2AxisAlignedBB.field_72338_b);
/* 190 */     int l = MathHelper.func_76128_c(par2AxisAlignedBB.field_72337_e + 1.0D);
/* 191 */     int i1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72339_c);
/* 192 */     int j1 = MathHelper.func_76128_c(par2AxisAlignedBB.field_72334_f + 1.0D);
/*     */     
/* 194 */     for (int k1 = i; k1 < j; k1++) {
/*     */       
/* 196 */       for (int l1 = i1; l1 < j1; l1++) {
/*     */         
/* 198 */         if (par1Entity.field_70170_p.func_72899_e(k1, 64, l1))
/*     */         {
/* 200 */           for (int i2 = k - 1; i2 < l; i2++) {
/*     */             
/* 202 */             Block block = W_WorldFunc.getBlock(par1Entity.field_70170_p, k1, i2, l1);
/*     */             
/* 204 */             if (block != null)
/*     */             {
/* 206 */               block.func_149743_a(par1Entity.field_70170_p, k1, i2, l1, par2AxisAlignedBB, collidingBoundingBoxes, par1Entity);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     double d0 = 0.25D;
/* 214 */     List<Entity> list = par1Entity.field_70170_p.func_72839_b(par1Entity, par2AxisAlignedBB.func_72314_b(d0, d0, d0));
/*     */     
/* 216 */     for (int j2 = 0; j2 < list.size(); j2++) {
/*     */       
/* 218 */       Entity entity = list.get(j2);
/*     */       
/* 220 */       if (!W_Lib.isEntityLivingBase(entity) && 
/* 221 */         !(entity instanceof mcheli.aircraft.MCH_EntitySeat) && 
/* 222 */         !(entity instanceof mcheli.aircraft.MCH_EntityHitBox) && 
/* 223 */         entity != this.parents) {
/*     */         
/* 225 */         AxisAlignedBB axisalignedbb1 = entity.func_70046_E();
/*     */         
/* 227 */         if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB))
/*     */         {
/* 229 */           collidingBoundingBoxes.add(axisalignedbb1);
/*     */         }
/*     */         
/* 232 */         axisalignedbb1 = par1Entity.func_70114_g(entity);
/*     */         
/* 234 */         if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(par2AxisAlignedBB))
/*     */         {
/* 236 */           collidingBoundingBoxes.add(axisalignedbb1);
/*     */         }
/*     */       } 
/*     */     } 
/* 240 */     return collidingBoundingBoxes;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_EntityWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */