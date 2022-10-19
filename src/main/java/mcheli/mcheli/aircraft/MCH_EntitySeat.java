/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_EntitySeat
/*     */   extends W_Entity
/*     */ {
/*     */   public String parentUniqueID;
/*     */   private MCH_EntityAircraft parent;
/*     */   public int seatID;
/*     */   public int parentSearchCount;
/*     */   protected Entity lastRiddenByEntity;
/*     */   public static final float BB_SIZE = 1.0F;
/*     */   
/*     */   public MCH_EntitySeat(World world) {
/*  28 */     super(world);
/*  29 */     func_70105_a(1.0F, 1.0F);
/*  30 */     this.field_70129_M = 0.0F;
/*  31 */     this.field_70159_w = 0.0D;
/*  32 */     this.field_70181_x = 0.0D;
/*  33 */     this.field_70179_y = 0.0D;
/*  34 */     this.seatID = -1;
/*  35 */     setParent(null);
/*  36 */     this.parentSearchCount = 0;
/*  37 */     this.lastRiddenByEntity = null;
/*  38 */     this.field_70158_ak = true;
/*  39 */     this.field_70178_ae = true;
/*     */   }
/*     */   
/*     */   public MCH_EntitySeat(World world, double x, double y, double z) {
/*  43 */     this(world);
/*  44 */     func_70107_b(x, y + 1.0D, z);
/*  45 */     this.field_70169_q = x;
/*  46 */     this.field_70167_r = y + 1.0D;
/*  47 */     this.field_70166_s = z;
/*     */   }
/*     */   protected boolean func_70041_e_() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/*  56 */     return par1Entity.field_70121_D;
/*     */   }
/*     */   public AxisAlignedBB func_70046_E() {
/*  59 */     return this.field_70121_D;
/*     */   }
/*     */   public boolean func_70104_M() {
/*  62 */     return false;
/*     */   }
/*     */   public double func_70042_X() {
/*  65 */     return -0.3D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/*  70 */     if (getParent() != null)
/*     */     {
/*  72 */       return getParent().func_70097_a(par1DamageSource, par2);
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70067_L() {
/*  78 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/*  94 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 100 */     super.func_70071_h_();
/*     */ 
/*     */     
/* 103 */     this.field_70143_R = 0.0F;
/* 104 */     if (this.field_70153_n != null)
/*     */     {
/* 106 */       this.field_70153_n.field_70143_R = 0.0F;
/*     */     }
/*     */ 
/*     */     
/* 110 */     if (this.lastRiddenByEntity == null && this.field_70153_n != null) {
/*     */       
/* 112 */       if (getParent() != null)
/*     */       {
/* 114 */         MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntitySeat.onUpdate:SeatID=%d", new Object[] { Integer.valueOf(this.seatID), this.field_70153_n.toString() });
/* 115 */         getParent().onMountPlayerSeat(this, this.field_70153_n);
/*     */       }
/*     */     
/* 118 */     } else if (this.lastRiddenByEntity != null && this.field_70153_n == null) {
/*     */       
/* 120 */       if (getParent() != null) {
/*     */         
/* 122 */         MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntitySeat.onUpdate:SeatID=%d", new Object[] { Integer.valueOf(this.seatID), this.lastRiddenByEntity.toString() });
/* 123 */         getParent().onUnmountPlayerSeat(this, this.lastRiddenByEntity);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 128 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 130 */       onUpdate_Client();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 135 */       onUpdate_Server();
/*     */     } 
/*     */     
/* 138 */     this.lastRiddenByEntity = this.field_70153_n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Client() {
/* 145 */     checkDetachmentAndDelete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Server() {
/* 152 */     checkDetachmentAndDelete();
/*     */     
/* 154 */     if (this.field_70153_n != null && this.field_70153_n.field_70128_L)
/*     */     {
/* 156 */       this.field_70153_n = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70043_V() {
/* 163 */     updatePosition();
/*     */   }
/*     */   
/*     */   public void updatePosition() {
/* 167 */     Entity ridEnt = this.field_70153_n;
/* 168 */     if (ridEnt != null) {
/*     */       
/* 170 */       ridEnt.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 171 */       ridEnt.field_70159_w = ridEnt.field_70181_x = ridEnt.field_70179_y = 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateRotation(float yaw, float pitch) {
/* 176 */     Entity ridEnt = this.field_70153_n;
/* 177 */     if (ridEnt != null) {
/*     */       
/* 179 */       ridEnt.field_70177_z = yaw;
/* 180 */       ridEnt.field_70125_A = pitch;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkDetachmentAndDelete() {
/* 189 */     if (!this.field_70128_L && (this.seatID < 0 || getParent() == null || (getParent()).field_70128_L)) {
/*     */ 
/*     */       
/* 192 */       if (getParent() != null && (getParent()).field_70128_L) this.parentSearchCount = 100000000;
/*     */ 
/*     */       
/* 195 */       if (this.parentSearchCount >= 1200)
/*     */       {
/* 197 */         func_70106_y();
/* 198 */         if (!this.field_70170_p.field_72995_K)
/*     */         {
/* 200 */           if (this.field_70153_n != null) this.field_70153_n.func_70078_a(null); 
/*     */         }
/* 202 */         setParent(null);
/*     */         
/* 204 */         MCH_Lib.DbgLog(this.field_70170_p, "[Error]座席エンティティは本体が見つからないため削除 seat=%d, parentUniqueID=%s", new Object[] { Integer.valueOf(this.seatID), this.parentUniqueID });
/*     */       }
/*     */       else
/*     */       {
/* 208 */         this.parentSearchCount++;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 213 */       this.parentSearchCount = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 223 */     par1NBTTagCompound.func_74768_a("SeatID", this.seatID);
/* 224 */     par1NBTTagCompound.func_74778_a("ParentUniqueID", this.parentUniqueID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 232 */     this.seatID = par1NBTTagCompound.func_74762_e("SeatID");
/* 233 */     this.parentUniqueID = par1NBTTagCompound.func_74779_i("ParentUniqueID");
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 239 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canRideMob(Entity entity) {
/* 244 */     if (getParent() == null || this.seatID < 0) return false;
/*     */     
/* 246 */     if (getParent().getSeatInfo(this.seatID + 1) instanceof mcheli.aircraft.MCH_SeatRackInfo)
/*     */     {
/* 248 */       return false;
/*     */     }
/*     */     
/* 251 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGunnerMode() {
/* 256 */     if (this.field_70153_n != null)
/*     */     {
/* 258 */       if (getParent() != null)
/*     */       {
/* 260 */         return getParent().getIsGunnerMode(this.field_70153_n);
/*     */       }
/*     */     }
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 271 */     if (getParent() == null || getParent().isDestroyed())
/*     */     {
/* 273 */       return false;
/*     */     }
/*     */     
/* 276 */     if (!getParent().checkTeam(player))
/*     */     {
/* 278 */       return false;
/*     */     }
/*     */     
/* 281 */     ItemStack itemStack = player.func_71045_bC();
/* 282 */     if (itemStack != null && itemStack.func_77973_b() instanceof mcheli.tool.MCH_ItemWrench)
/*     */     {
/* 284 */       return getParent().func_130002_c(player);
/*     */     }
/*     */     
/* 287 */     if (this.field_70153_n != null)
/*     */     {
/*     */ 
/*     */       
/* 291 */       return false;
/*     */     }
/*     */     
/* 294 */     if (player.field_70154_o != null)
/*     */     {
/*     */       
/* 297 */       return false;
/*     */     }
/*     */     
/* 300 */     if (!canRideMob((Entity)player))
/*     */     {
/* 302 */       return false;
/*     */     }
/*     */     
/* 305 */     player.func_70078_a((Entity)this);
/*     */     
/* 307 */     return true;
/*     */   }
/*     */   
/*     */   public MCH_EntityAircraft getParent() {
/* 311 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParent(MCH_EntityAircraft parent) {
/* 315 */     this.parent = parent;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_EntitySeat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */