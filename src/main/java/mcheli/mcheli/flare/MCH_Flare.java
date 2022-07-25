/*     */ package mcheli.mcheli.flare;
/*     */ 
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.flare.MCH_EntityFlare;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_Flare
/*     */ {
/*     */   public final World worldObj;
/*     */   public final MCH_EntityAircraft aircraft;
/*     */   public final Random rand;
/*     */   public int numFlare;
/*     */   public int tick;
/*     */   private int flareType;
/*  26 */   private static FlareParam[] FLARE_DATA = null;
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
/*     */   public MCH_Flare(World w, MCH_EntityAircraft ac) {
/*  48 */     this.worldObj = w;
/*  49 */     this.aircraft = ac;
/*  50 */     this.rand = new Random();
/*     */     
/*  52 */     this.tick = 0;
/*  53 */     this.numFlare = 0;
/*  54 */     this.flareType = 0;
/*     */     
/*  56 */     this; if (FLARE_DATA == null) {
/*     */       
/*  58 */       int delay = w.field_72995_K ? 50 : 0;
/*  59 */       this; FLARE_DATA = new FlareParam[11];
/*  60 */       this; FLARE_DATA[1] = new FlareParam(this, 1, 3, 200 + delay, 100, 16);
/*  61 */       this; FLARE_DATA[2] = new FlareParam(this, 3, 5, 300 + delay, 200, 16);
/*  62 */       this; FLARE_DATA[3] = new FlareParam(this, 2, 3, 200 + delay, 100, 16);
/*  63 */       this; FLARE_DATA[4] = new FlareParam(this, 1, 3, 200 + delay, 100, 16);
/*  64 */       this; FLARE_DATA[5] = new FlareParam(this, 2, 3, 200 + delay, 100, 16);
/*  65 */       this; FLARE_DATA[10] = new FlareParam(this, 8, 1, 250 + delay, 60, 1);
/*     */       
/*  67 */       this; this; FLARE_DATA[0] = FLARE_DATA[1];
/*  68 */       this; this; FLARE_DATA[6] = FLARE_DATA[1];
/*  69 */       this; this; FLARE_DATA[7] = FLARE_DATA[1];
/*  70 */       this; this; FLARE_DATA[8] = FLARE_DATA[1];
/*  71 */       this; this; FLARE_DATA[9] = FLARE_DATA[1];
/*     */     } 
/*     */   }
/*     */   public boolean isInPreparation() {
/*  75 */     return (this.tick != 0);
/*     */   }
/*     */   public boolean isUsing() {
/*  78 */     int type = getFlareType();
/*  79 */     return (this.tick != 0 && type < FLARE_DATA.length && this.tick > (FLARE_DATA[type]).tickWait - (FLARE_DATA[type]).tickEnable);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlareType() {
/*  94 */     return this.flareType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnParticle(String name, int num, float size) {
/* 100 */     if (this.worldObj.field_72995_K) {
/*     */       
/* 102 */       if (name.isEmpty() || num < 1 || num > 50)
/* 103 */         return;  double x = (this.aircraft.field_70165_t - this.aircraft.field_70169_q) / num;
/* 104 */       double y = (this.aircraft.field_70163_u - this.aircraft.field_70167_r) / num;
/* 105 */       double z = (this.aircraft.field_70161_v - this.aircraft.field_70166_s) / num;
/* 106 */       for (int i = 0; i < num; i++) {
/*     */         
/* 108 */         MCH_ParticleParam prm = new MCH_ParticleParam(this.worldObj, "smoke", this.aircraft.field_70169_q + x * i, this.aircraft.field_70167_r + y * i, this.aircraft.field_70166_s + z * i);
/*     */ 
/*     */ 
/*     */         
/* 112 */         prm.size = size + this.rand.nextFloat();
/* 113 */         MCH_ParticlesUtil.spawnParticle(prm);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean use(int type) {
/* 120 */     boolean result = false;
/*     */     
/* 122 */     MCH_Lib.DbgLog(this.aircraft.field_70170_p, "MCH_Flare.use type = %d", new Object[] { Integer.valueOf(type) });
/*     */     
/* 124 */     this.flareType = type;
/*     */     
/* 126 */     this; if (type <= 0 && type >= FLARE_DATA.length)
/*     */     {
/* 128 */       return false;
/*     */     }
/*     */     
/* 131 */     if (this.worldObj.field_72995_K) {
/*     */       
/* 133 */       if (this.tick == 0)
/*     */       {
/* 135 */         this.tick = (FLARE_DATA[getFlareType()]).tickWait;
/* 136 */         result = true;
/* 137 */         this.numFlare = 0;
/* 138 */         W_McClient.DEF_playSoundFX("random.click", 1.0F, 1.0F);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 143 */       result = true;
/* 144 */       this.numFlare = 0;
/* 145 */       this.tick = (FLARE_DATA[getFlareType()]).tickWait;
/*     */       
/* 147 */       this.aircraft.getEntityData().func_74757_a("FlareUsing", true);
/*     */     } 
/*     */     
/* 150 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 155 */     int type = getFlareType();
/*     */     
/* 157 */     this; if (this.aircraft == null || this.aircraft.field_70128_L || type <= 0 || type > FLARE_DATA.length) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (this.tick > 0)
/*     */     {
/* 164 */       this.tick--;
/*     */     }
/*     */     
/* 167 */     if (!this.worldObj.field_72995_K)
/*     */     {
/* 169 */       if (this.tick > 0 && this.tick % (FLARE_DATA[type]).interval == 0 && this.numFlare < (FLARE_DATA[type]).numFlareMax) {
/*     */         
/* 171 */         Vec3 v = (this.aircraft.getAcInfo()).flare.pos;
/* 172 */         v = this.aircraft.getTransformedPosition(v.field_72450_a, v.field_72448_b, v.field_72449_c, this.aircraft.field_70169_q, this.aircraft.field_70167_r, this.aircraft.field_70166_s);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 177 */         spawnFlare(v);
/*     */       } 
/*     */     }
/*     */     
/* 181 */     if (!isUsing() && this.aircraft.getEntityData().func_74767_n("FlareUsing"))
/*     */     {
/* 183 */       this.aircraft.getEntityData().func_74757_a("FlareUsing", false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnFlare(Vec3 v) {
/* 189 */     this.numFlare++;
/*     */     
/* 191 */     int type = getFlareType();
/* 192 */     int num = (FLARE_DATA[type]).num;
/*     */     
/* 194 */     double x = v.field_72450_a - this.aircraft.field_70159_w * 2.0D;
/* 195 */     double y = v.field_72448_b - this.aircraft.field_70181_x * 2.0D - 1.0D;
/* 196 */     double z = v.field_72449_c - this.aircraft.field_70179_y * 2.0D;
/* 197 */     this.worldObj.func_72889_a((EntityPlayer)null, 1004, (int)x, (int)y, (int)z, 0);
/*     */     
/* 199 */     for (int i = 0; i < num; i++) {
/*     */       
/* 201 */       x = v.field_72450_a - this.aircraft.field_70159_w * 2.0D;
/* 202 */       y = v.field_72448_b - this.aircraft.field_70181_x * 2.0D - 1.0D;
/* 203 */       z = v.field_72449_c - this.aircraft.field_70179_y * 2.0D;
/*     */       
/* 205 */       double tx = 0.0D;
/* 206 */       double ty = this.aircraft.field_70181_x;
/* 207 */       double tz = 0.0D;
/*     */       
/* 209 */       int fuseCount = 0;
/*     */       
/* 211 */       double r = this.aircraft.field_70177_z;
/* 212 */       if (type == 1) {
/*     */         
/* 214 */         tx = MathHelper.func_76126_a(this.rand.nextFloat() * 360.0F);
/* 215 */         tz = MathHelper.func_76134_b(this.rand.nextFloat() * 360.0F);
/*     */       }
/* 217 */       else if (type == 2 || type == 3) {
/*     */         
/* 219 */         if (i == 0) r += 90.0D; 
/* 220 */         if (i == 1) r -= 90.0D; 
/* 221 */         if (i == 2) r += 180.0D; 
/* 222 */         r *= 0.017453292519943295D;
/* 223 */         tx = -Math.sin(r) + (this.rand.nextFloat() - 0.5D) * 0.6D;
/* 224 */         tz = Math.cos(r) + (this.rand.nextFloat() - 0.5D) * 0.6D;
/*     */       }
/* 226 */       else if (type == 4) {
/*     */         
/* 228 */         r *= 0.017453292519943295D;
/* 229 */         tx = -Math.sin(r) + (this.rand.nextFloat() - 0.5D) * 1.3D;
/* 230 */         tz = Math.cos(r) + (this.rand.nextFloat() - 0.5D) * 1.3D;
/*     */       }
/* 232 */       else if (type == 5) {
/*     */         
/* 234 */         r *= 0.017453292519943295D;
/* 235 */         tx = -Math.sin(r) + (this.rand.nextFloat() - 0.5D) * 0.9D;
/* 236 */         tz = Math.cos(r) + (this.rand.nextFloat() - 0.5D) * 0.9D;
/* 237 */         tx *= 0.3D;
/* 238 */         tz *= 0.3D;
/*     */       } 
/*     */       
/* 241 */       tx += this.aircraft.field_70159_w;
/* 242 */       ty += this.aircraft.field_70181_x / 2.0D;
/* 243 */       tz += this.aircraft.field_70179_y;
/*     */       
/* 245 */       if (type == 10) {
/*     */         
/* 247 */         r += (360 / num / 2 + i * 360 / num);
/* 248 */         r *= 0.017453292519943295D;
/* 249 */         tx = -Math.sin(r) * 2.0D;
/* 250 */         tz = Math.cos(r) * 2.0D;
/* 251 */         ty = 0.7D;
/* 252 */         y += 2.0D;
/* 253 */         fuseCount = 10;
/*     */       } 
/*     */       
/* 256 */       MCH_EntityFlare e = new MCH_EntityFlare(this.worldObj, x, y, z, tx * 0.5D, ty * 0.5D, tz * 0.5D, 6.0F, fuseCount);
/* 257 */       e.field_70125_A = this.rand.nextFloat() * 360.0F;
/* 258 */       e.field_70177_z = this.rand.nextFloat() * 360.0F;
/* 259 */       e.field_70127_C = this.rand.nextFloat() * 360.0F;
/* 260 */       e.field_70126_B = this.rand.nextFloat() * 360.0F;
/*     */       
/* 262 */       if (type == 4) {
/*     */         
/* 264 */         e.gravity *= 0.6D;
/* 265 */         e.airResistance = 0.995D;
/*     */       } 
/*     */       
/* 268 */       this.worldObj.func_72838_d((Entity)e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\flare\MCH_Flare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */