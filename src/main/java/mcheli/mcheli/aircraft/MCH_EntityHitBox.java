/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityHitBox
/*     */   extends W_Entity
/*     */ {
/*     */   public MCH_EntityAircraft parent;
/*     */   public int debugId;
/*     */   
/*     */   public MCH_EntityHitBox(World world) {
/*  21 */     super(world);
/*  22 */     func_70105_a(1.0F, 1.0F);
/*  23 */     this.field_70129_M = 0.0F;
/*  24 */     this.field_70159_w = 0.0D;
/*  25 */     this.field_70181_x = 0.0D;
/*  26 */     this.field_70179_y = 0.0D;
/*  27 */     this.parent = null;
/*  28 */     this.field_70158_ak = true;
/*  29 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */   public MCH_EntityHitBox(World world, MCH_EntityAircraft ac, float w, float h) {
/*  33 */     this(world);
/*  34 */     func_70107_b(ac.field_70165_t, ac.field_70163_u + 1.0D, ac.field_70161_v);
/*  35 */     this.field_70169_q = ac.field_70165_t;
/*  36 */     this.field_70167_r = ac.field_70163_u + 1.0D;
/*  37 */     this.field_70166_s = ac.field_70161_v;
/*  38 */     this.parent = ac;
/*  39 */     func_70105_a(w, h);
/*     */   }
/*     */   protected boolean func_70041_e_() {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/*  48 */     return par1Entity.field_70121_D;
/*     */   }
/*     */   public AxisAlignedBB func_70046_E() {
/*  51 */     return this.field_70121_D;
/*     */   }
/*     */   public boolean func_70104_M() {
/*  54 */     return false;
/*     */   }
/*     */   public double func_70042_X() {
/*  57 */     return -0.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/*  62 */     if (this.parent != null)
/*     */     {
/*  64 */       return this.parent.func_70097_a(par1DamageSource, par2);
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70067_L() {
/*  70 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/*  75 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  81 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 101 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 109 */     return (this.parent != null) ? this.parent.func_130002_c(player) : false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_EntityHitBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */