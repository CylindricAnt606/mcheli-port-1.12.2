/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.tank.MCH_EntityWheel;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_WheelManager
/*     */ {
/*     */   public final MCH_EntityAircraft parent;
/*     */   public MCH_EntityWheel[] wheels;
/*     */   private double minZ;
/*     */   private double maxZ;
/*     */   private double avgZ;
/*     */   public Vec3 weightedCenter;
/*     */   public float targetPitch;
/*     */   public float targetRoll;
/*     */   public float prevYaw;
/*  35 */   private static Random rand = new Random();
/*     */ 
/*     */   
/*     */   public MCH_WheelManager(MCH_EntityAircraft ac) {
/*  39 */     this.parent = ac;
/*  40 */     this.wheels = new MCH_EntityWheel[0];
/*  41 */     this.weightedCenter = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createWheels(World w, List<MCH_AircraftInfo.Wheel> list, Vec3 weightedCenter) {
/*  46 */     this.wheels = new MCH_EntityWheel[list.size() * 2];
/*     */     
/*  48 */     this.minZ = 999999.0D;
/*  49 */     this.maxZ = -999999.0D;
/*     */     
/*  51 */     this.weightedCenter = weightedCenter;
/*     */     
/*  53 */     for (int i = 0; i < this.wheels.length; i++) {
/*     */       
/*  55 */       MCH_EntityWheel wheel = new MCH_EntityWheel(w);
/*  56 */       wheel.setParents(this.parent);
/*  57 */       Vec3 wp = ((MCH_AircraftInfo.Wheel)list.get(i / 2)).pos;
/*  58 */       wheel.setWheelPos(Vec3.func_72443_a((i % 2 == 0) ? wp.field_72450_a : -wp.field_72450_a, wp.field_72448_b, wp.field_72449_c), this.weightedCenter);
/*     */       
/*  60 */       Vec3 v = this.parent.getTransformedPosition(wheel.pos.field_72450_a, wheel.pos.field_72448_b, wheel.pos.field_72449_c);
/*  61 */       wheel.func_70012_b(v.field_72450_a, v.field_72448_b + 1.0D, v.field_72449_c, 0.0F, 0.0F);
/*  62 */       this.wheels[i] = wheel;
/*     */       
/*  64 */       if (wheel.pos.field_72449_c <= this.minZ) this.minZ = wheel.pos.field_72449_c; 
/*  65 */       if (wheel.pos.field_72449_c >= this.maxZ) this.maxZ = wheel.pos.field_72449_c; 
/*     */     } 
/*  67 */     this.avgZ = this.maxZ - this.minZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(double x, double y, double z) {
/*  72 */     MCH_EntityAircraft ac = this.parent;
/*  73 */     if (ac.getAcInfo() == null)
/*     */       return; 
/*  75 */     boolean showLog = (ac.field_70173_aa % 1 == 1);
/*     */     
/*  77 */     if (showLog)
/*     */     {
/*  79 */       MCH_Lib.DbgLog(ac.field_70170_p, "[" + (ac.field_70170_p.field_72995_K ? "Client" : "Server") + "] ==============================", new Object[0]);
/*     */     }
/*     */     
/*  82 */     for (MCH_EntityWheel wheel : this.wheels) {
/*     */       
/*  84 */       wheel.field_70169_q = wheel.field_70165_t;
/*  85 */       wheel.field_70167_r = wheel.field_70163_u;
/*  86 */       wheel.field_70166_s = wheel.field_70161_v;
/*  87 */       Vec3 v = ac.getTransformedPosition(wheel.pos.field_72450_a, wheel.pos.field_72448_b, wheel.pos.field_72449_c);
/*  88 */       wheel.field_70159_w = v.field_72450_a - wheel.field_70165_t + x;
/*  89 */       wheel.field_70181_x = v.field_72448_b - wheel.field_70163_u;
/*  90 */       wheel.field_70179_y = v.field_72449_c - wheel.field_70161_v + z;
/*     */     } 
/*  92 */     for (MCH_EntityWheel wheel : this.wheels) {
/*     */       
/*  94 */       wheel.field_70181_x *= 0.15D;
/*  95 */       wheel.func_70091_d(wheel.field_70159_w, wheel.field_70181_x, wheel.field_70179_y);
/*  96 */       double f = 1.0D;
/*  97 */       wheel.func_70091_d(0.0D, -0.1D * f, 0.0D);
/*     */     } 
/*     */     
/* 100 */     int zmog = -1; int i;
/* 101 */     for (i = 0; i < this.wheels.length / 2; i++) {
/*     */       
/* 103 */       zmog = i;
/* 104 */       MCH_EntityWheel w1 = this.wheels[i * 2 + 0];
/* 105 */       MCH_EntityWheel w2 = this.wheels[i * 2 + 1];
/* 106 */       if (!w1.isPlus)
/*     */       {
/* 108 */         if (w1.field_70122_E || w2.field_70122_E) {
/*     */           
/* 110 */           zmog = -1;
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/* 115 */     if (zmog >= 0) {
/*     */       
/* 117 */       (this.wheels[zmog * 2 + 0]).field_70122_E = true;
/* 118 */       (this.wheels[zmog * 2 + 1]).field_70122_E = true;
/*     */     } 
/*     */     
/* 121 */     zmog = -1;
/* 122 */     for (i = this.wheels.length / 2 - 1; i >= 0; i--) {
/*     */       
/* 124 */       zmog = i;
/* 125 */       MCH_EntityWheel w1 = this.wheels[i * 2 + 0];
/* 126 */       MCH_EntityWheel w2 = this.wheels[i * 2 + 1];
/* 127 */       if (w1.isPlus)
/*     */       {
/* 129 */         if (w1.field_70122_E || w2.field_70122_E) {
/*     */           
/* 131 */           zmog = -1;
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/* 136 */     if (zmog >= 0) {
/*     */       
/* 138 */       (this.wheels[zmog * 2 + 0]).field_70122_E = true;
/* 139 */       (this.wheels[zmog * 2 + 1]).field_70122_E = true;
/*     */     } 
/*     */     
/* 142 */     Vec3 rv = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/*     */     
/* 144 */     Vec3 wc = ac.getTransformedPosition(this.weightedCenter);
/* 145 */     wc.field_72450_a -= ac.field_70165_t;
/* 146 */     wc.field_72448_b = this.weightedCenter.field_72448_b;
/* 147 */     wc.field_72449_c -= ac.field_70161_v;
/*     */     
/* 149 */     for (int j = 0; j < this.wheels.length / 2; j++) {
/*     */       
/* 151 */       MCH_EntityWheel w1 = this.wheels[j * 2 + 0];
/* 152 */       MCH_EntityWheel w2 = this.wheels[j * 2 + 1];
/*     */       
/* 154 */       Vec3 v1 = Vec3.func_72443_a(w1.field_70165_t - ac.field_70165_t + wc.field_72450_a, w1.field_70163_u - ac.field_70163_u + wc.field_72448_b, w1.field_70161_v - ac.field_70161_v + wc.field_72449_c);
/*     */ 
/*     */ 
/*     */       
/* 158 */       Vec3 v2 = Vec3.func_72443_a(w2.field_70165_t - ac.field_70165_t + wc.field_72450_a, w2.field_70163_u - ac.field_70163_u + wc.field_72448_b, w2.field_70161_v - ac.field_70161_v + wc.field_72449_c);
/*     */ 
/*     */ 
/*     */       
/* 162 */       Vec3 v = (w1.pos.field_72449_c >= 0.0D) ? v2.func_72431_c(v1) : v1.func_72431_c(v2);
/* 163 */       v = v.func_72432_b();
/* 164 */       double f = Math.abs(w1.pos.field_72449_c / this.avgZ);
/*     */       
/* 166 */       if (!w1.field_70122_E && !w2.field_70122_E)
/*     */       {
/* 168 */         f = 0.0D;
/*     */       }
/* 170 */       rv.field_72450_a += v.field_72450_a * f;
/* 171 */       rv.field_72448_b += v.field_72448_b * f;
/* 172 */       rv.field_72449_c += v.field_72449_c * f;
/*     */       
/* 174 */       if (showLog) {
/*     */         
/* 176 */         v.func_72442_b((float)(ac.getRotYaw() * Math.PI / 180.0D));
/* 177 */         MCH_Lib.DbgLog(ac.field_70170_p, "%2d : %.2f :[%+.1f, %+.1f, %+.1f][%s %d %d][%+.2f(%+.2f), %+.2f(%+.2f)][%+.1f, %+.1f, %+.1f]", new Object[] { Integer.valueOf(j), Double.valueOf(f), Double.valueOf(v.field_72450_a), Double.valueOf(v.field_72448_b), Double.valueOf(v.field_72449_c), w1.isPlus ? "+" : "-", Integer.valueOf(w1.field_70122_E ? 1 : 0), Integer.valueOf(w2.field_70122_E ? 1 : 0), Double.valueOf(w1.field_70163_u - w1.field_70167_r), Double.valueOf(w1.field_70181_x), Double.valueOf(w2.field_70163_u - w2.field_70167_r), Double.valueOf(w2.field_70181_x), Double.valueOf(v.field_72450_a), Double.valueOf(v.field_72448_b), Double.valueOf(v.field_72449_c) });
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     rv = rv.func_72432_b();
/* 188 */     if (rv.field_72448_b > 0.01D && rv.field_72448_b < 0.7D) {
/*     */       
/* 190 */       ac.field_70159_w += rv.field_72450_a / 50.0D;
/* 191 */       ac.field_70179_y += rv.field_72449_c / 50.0D;
/*     */     } 
/*     */     
/* 194 */     rv.func_72442_b((float)(ac.getRotYaw() * Math.PI / 180.0D));
/* 195 */     float pitch = (float)(90.0D - Math.atan2(rv.field_72448_b, rv.field_72449_c) * 180.0D / Math.PI);
/* 196 */     float roll = -((float)(90.0D - Math.atan2(rv.field_72448_b, rv.field_72450_a) * 180.0D / Math.PI));
/*     */     
/* 198 */     float ogpf = (ac.getAcInfo()).onGroundPitchFactor;
/* 199 */     if (pitch - ac.getRotPitch() > ogpf) pitch = ac.getRotPitch() + ogpf; 
/* 200 */     if (pitch - ac.getRotPitch() < -ogpf) pitch = ac.getRotPitch() - ogpf; 
/* 201 */     float ogrf = (ac.getAcInfo()).onGroundRollFactor;
/* 202 */     if (roll - ac.getRotRoll() > ogrf) roll = ac.getRotRoll() + ogrf; 
/* 203 */     if (roll - ac.getRotRoll() < -ogrf) roll = ac.getRotRoll() - ogrf;
/*     */     
/* 205 */     this.targetPitch = pitch;
/* 206 */     this.targetRoll = roll;
/*     */     
/* 208 */     if (!W_Lib.isClientPlayer(ac.getRiddenByEntity())) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 214 */       ac.setRotPitch(pitch);
/* 215 */       ac.setRotRoll(roll);
/*     */     } 
/*     */     
/* 218 */     if (showLog)
/*     */     {
/* 220 */       MCH_Lib.DbgLog(ac.field_70170_p, "%+03d, %+03d :[%.2f, %.2f, %.2f] yaw=%.2f, pitch=%.2f, roll=%.2f", new Object[] { Integer.valueOf((int)pitch), Integer.valueOf((int)roll), Double.valueOf(rv.field_72450_a), Double.valueOf(rv.field_72448_b), Double.valueOf(rv.field_72449_c), Float.valueOf(ac.getRotYaw()), Float.valueOf(this.targetPitch), Float.valueOf(this.targetRoll) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     for (MCH_EntityWheel wheel : this.wheels) {
/*     */       
/* 228 */       Vec3 v = getTransformedPosition(wheel.pos.field_72450_a, wheel.pos.field_72448_b, wheel.pos.field_72449_c, ac, ac.getRotYaw(), this.targetPitch, this.targetRoll);
/*     */       
/* 230 */       double offset = wheel.field_70122_E ? 0.01D : -0.0D;
/* 231 */       double rangeH = 2.0D;
/* 232 */       double poy = (wheel.field_70138_W / 2.0F);
/* 233 */       int b = 0;
/* 234 */       if (wheel.field_70165_t > v.field_72450_a + rangeH) { wheel.field_70165_t = v.field_72450_a + rangeH; wheel.field_70163_u = v.field_72448_b + poy; b |= 0x1; }
/* 235 */        if (wheel.field_70165_t < v.field_72450_a - rangeH) { wheel.field_70165_t = v.field_72450_a - rangeH; wheel.field_70163_u = v.field_72448_b + poy; b |= 0x2; }
/* 236 */        if (wheel.field_70161_v > v.field_72449_c + rangeH) { wheel.field_70161_v = v.field_72449_c + rangeH; wheel.field_70163_u = v.field_72448_b + poy; b |= 0x4; }
/* 237 */        if (wheel.field_70161_v < v.field_72449_c - rangeH) { wheel.field_70161_v = v.field_72449_c - rangeH; wheel.field_70163_u = v.field_72448_b + poy; b |= 0x8; }
/* 238 */        wheel.func_70080_a(wheel.field_70165_t, wheel.field_70163_u, wheel.field_70161_v, 0.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getTransformedPosition(double x, double y, double z, MCH_EntityAircraft ac, float yaw, float pitch, float roll) {
/* 247 */     Vec3 v = MCH_Lib.RotVec3(x, y, z, -yaw, -pitch, -roll);
/* 248 */     return v.func_72441_c(ac.field_70165_t, ac.field_70163_u, ac.field_70161_v);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBlock() {
/* 253 */     if (!MCH_Config.Collision_DestroyBlock.prmBool)
/*     */       return; 
/* 255 */     MCH_EntityAircraft ac = this.parent;
/*     */     
/* 257 */     for (MCH_EntityWheel w : this.wheels) {
/*     */       
/* 259 */       Vec3 v = ac.getTransformedPosition(w.pos);
/* 260 */       int x = (int)(v.field_72450_a + 0.5D);
/* 261 */       int y = (int)(v.field_72448_b + 0.5D);
/* 262 */       int z = (int)(v.field_72449_c + 0.5D);
/* 263 */       Block block = ac.field_70170_p.func_147439_a(x, y, z);
/*     */       
/* 265 */       if (block == W_Block.getSnowLayer())
/*     */       {
/* 267 */         ac.field_70170_p.func_147468_f(x, y, z);
/*     */       }
/* 269 */       if (block == W_Blocks.field_150392_bi || block == W_Blocks.field_150414_aQ)
/*     */       {
/* 271 */         W_WorldFunc.destroyBlock(ac.field_70170_p, x, y, z, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void particleLandingGear() {
/* 278 */     if (this.wheels.length <= 0)
/*     */       return; 
/* 280 */     MCH_EntityAircraft ac = this.parent;
/*     */     
/* 282 */     double d = ac.field_70159_w * ac.field_70159_w + ac.field_70179_y * ac.field_70179_y + Math.abs(this.prevYaw - ac.getRotYaw());
/* 283 */     this.prevYaw = ac.getRotYaw();
/* 284 */     if (d > 0.001D)
/*     */     {
/* 286 */       for (int i = 0; i < 2; i++) {
/*     */         
/* 288 */         MCH_EntityWheel w = this.wheels[rand.nextInt(this.wheels.length)];
/* 289 */         Vec3 v = ac.getTransformedPosition(w.pos);
/* 290 */         int x = MathHelper.func_76128_c(v.field_72450_a + 0.5D);
/* 291 */         int y = MathHelper.func_76128_c(v.field_72448_b - 0.5D);
/* 292 */         int z = MathHelper.func_76128_c(v.field_72449_c + 0.5D);
/* 293 */         Block block = ac.field_70170_p.func_147439_a(x, y, z);
/* 294 */         if (Block.func_149680_a(block, Blocks.field_150350_a)) {
/*     */           
/* 296 */           y = MathHelper.func_76128_c(v.field_72448_b + 0.5D);
/* 297 */           block = ac.field_70170_p.func_147439_a(x, y, z);
/*     */         } 
/* 299 */         if (!Block.func_149680_a(block, Blocks.field_150350_a))
/*     */         {
/* 301 */           MCH_ParticlesUtil.spawnParticleTileCrack(ac.field_70170_p, x, y, z, v.field_72450_a + rand.nextFloat() - 0.5D, v.field_72448_b + 0.1D, v.field_72449_c + rand.nextFloat() - 0.5D, -ac.field_70159_w * 4.0D + (rand.nextFloat() - 0.5D) * 0.1D, rand.nextFloat() * 0.5D, -ac.field_70179_y * 4.0D + (rand.nextFloat() - 0.5D) * 0.1D);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_WheelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */