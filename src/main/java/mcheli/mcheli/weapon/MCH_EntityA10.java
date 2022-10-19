/*     */ package mcheli.mcheli.weapon;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.weapon.MCH_EntityBullet;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityA10 extends W_Entity {
/*  15 */   public final int DESPAWN_COUNT = 70; public static final int DATAWT_NAME = 29;
/*  16 */   public int despawnCount = 0;
/*     */   public Entity shootingAircraft;
/*     */   public Entity shootingEntity;
/*  19 */   public int shotCount = 0;
/*  20 */   public int direction = 0;
/*     */   
/*     */   public int power;
/*     */   public float acceleration;
/*     */   public int explosionPower;
/*     */   public boolean isFlaming;
/*     */   public String name;
/*     */   public MCH_WeaponInfo weaponInfo;
/*  28 */   static int snd_num = 0;
/*     */ 
/*     */   
/*     */   public MCH_EntityA10(World world) {
/*  32 */     super(world);
/*     */     
/*  34 */     this.field_70158_ak = true;
/*  35 */     this.field_70156_m = false;
/*  36 */     func_70105_a(5.0F, 3.0F);
/*  37 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  38 */     this.field_70159_w = 0.0D;
/*  39 */     this.field_70181_x = 0.0D;
/*  40 */     this.field_70179_y = 0.0D;
/*  41 */     this.power = 32;
/*  42 */     this.acceleration = 4.0F;
/*  43 */     this.explosionPower = 1;
/*  44 */     this.isFlaming = false;
/*  45 */     this.shootingEntity = null;
/*  46 */     this.shootingAircraft = null;
/*  47 */     this.field_70178_ae = true;
/*     */     
/*  49 */     this.field_70155_l *= 10.0D;
/*     */   }
/*     */   
/*     */   public MCH_EntityA10(World world, double x, double y, double z) {
/*  53 */     this(world);
/*  54 */     this.field_70142_S = this.field_70169_q = this.field_70165_t = x;
/*  55 */     this.field_70137_T = this.field_70167_r = this.field_70163_u = y;
/*  56 */     this.field_70136_U = this.field_70166_s = this.field_70161_v = z;
/*     */   }
/*     */   protected boolean func_70041_e_() {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/*  63 */     func_70096_w().func_75682_a(29, String.valueOf(""));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWeaponName(String s) {
/*  68 */     if (s != null && !s.isEmpty()) {
/*     */       
/*  70 */       this.weaponInfo = MCH_WeaponInfoManager.get(s);
/*  71 */       if (this.weaponInfo != null && !this.field_70170_p.field_72995_K)
/*     */       {
/*  73 */         func_70096_w().func_75692_b(29, String.valueOf(s));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getWeaponName() {
/*  79 */     return func_70096_w().func_75681_e(29);
/*     */   }
/*     */   
/*     */   public MCH_WeaponInfo getInfo() {
/*  83 */     return this.weaponInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/*  90 */     return par1Entity.field_70121_D;
/*     */   }
/*     */   public AxisAlignedBB func_70046_E() {
/*  93 */     return this.field_70121_D;
/*     */   }
/*     */   public boolean func_70104_M() {
/*  96 */     return false;
/*     */   }
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/*  99 */     return false;
/*     */   }
/*     */   public boolean func_70067_L() {
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 107 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 114 */     super.func_70071_h_();
/*     */     
/* 116 */     if (!this.field_70128_L)
/*     */     {
/* 118 */       this.despawnCount++;
/*     */     }
/*     */     
/* 121 */     if (this.weaponInfo == null) {
/*     */       
/* 123 */       setWeaponName(getWeaponName());
/* 124 */       if (this.weaponInfo == null) {
/*     */         
/* 126 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 134 */       onUpdate_Client();
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 142 */       onUpdate_Server();
/*     */     } 
/*     */     
/* 145 */     if (!this.field_70128_L)
/*     */     {
/* 147 */       if (this.despawnCount <= 20) {
/*     */ 
/*     */         
/* 150 */         this.field_70181_x = -0.3D;
/*     */       }
/*     */       else {
/*     */         
/* 154 */         func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 155 */         this.field_70181_x += 0.02D;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRender() {
/* 162 */     return (this.despawnCount > 20);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Client() {
/* 168 */     this.shotCount += 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Server() {
/* 174 */     if (!this.field_70128_L)
/*     */     {
/* 176 */       if (this.despawnCount > 70) {
/*     */         
/* 178 */         func_70106_y();
/*     */ 
/*     */       
/*     */       }
/* 182 */       else if (this.despawnCount > 0 && this.shotCount < 40) {
/*     */         
/* 184 */         for (int i = 0; i < 2; i++) {
/*     */           
/* 186 */           shotGAU8(true, this.shotCount);
/* 187 */           this.shotCount++;
/*     */         } 
/* 189 */         if (this.shotCount == 38)
/*     */         {
/* 191 */           W_WorldFunc.MOD_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "gau-8_snd", 150.0F, 1.0F);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void shotGAU8(boolean playSound, int cnt) {
/* 201 */     float yaw = (90 * this.direction);
/* 202 */     float pitch = 30.0F;
/* 203 */     double x = this.field_70165_t;
/* 204 */     double y = this.field_70163_u;
/* 205 */     double z = this.field_70161_v;
/* 206 */     double tX = this.field_70146_Z.nextDouble() - 0.5D;
/* 207 */     double tY = -2.6D;
/* 208 */     double tZ = this.field_70146_Z.nextDouble() - 0.5D;
/* 209 */     if (this.direction == 0) { tZ += 10.0D; z += cnt * 0.6D; }
/* 210 */      if (this.direction == 1) { tX -= 10.0D; x -= cnt * 0.6D; }
/* 211 */      if (this.direction == 2) { tZ -= 10.0D; z -= cnt * 0.6D; }
/* 212 */      if (this.direction == 3) { tX += 10.0D; x += cnt * 0.6D; }
/* 213 */      double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/* 214 */     tX = tX * 4.0D / dist;
/* 215 */     tY = tY * 4.0D / dist;
/* 216 */     tZ = tZ * 4.0D / dist;
/*     */     
/* 218 */     MCH_EntityBullet e = new MCH_EntityBullet(this.field_70170_p, x, y, z, tX, tY, tZ, yaw, pitch, this.acceleration);
/* 219 */     e.setName(getWeaponName());
/* 220 */     e.explosionPower = (this.shotCount % 4 == 0) ? this.explosionPower : 0;
/* 221 */     e.setPower(this.power);
/* 222 */     e.shootingEntity = this.shootingEntity;
/* 223 */     e.shootingAircraft = this.shootingAircraft;
/*     */     
/* 225 */     this.field_70170_p.func_72838_d((Entity)e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 233 */     par1NBTTagCompound.func_74778_a("WeaponName", getWeaponName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 241 */     this.despawnCount = 200;
/* 242 */     if (par1NBTTagCompound.func_74764_b("WeaponName"))
/*     */     {
/* 244 */       setWeaponName(par1NBTTagCompound.func_74779_i("WeaponName")); } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 249 */     return 10.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityA10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */