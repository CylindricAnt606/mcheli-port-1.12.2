/*     */ package mcheli.mcheli.uav;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ItemUavStation extends W_Item {
/*  18 */   public static int UAV_STATION_KIND_NUM = 2;
/*     */   
/*     */   public final int UavStationKind;
/*     */   
/*     */   public MCH_ItemUavStation(int par1, int kind) {
/*  23 */     super(par1);
/*  24 */     this.field_77777_bU = 1;
/*  25 */     this.UavStationKind = kind;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityUavStation createUavStation(World world, double x, double y, double z, int kind) {
/*  30 */     MCH_EntityUavStation uavst = new MCH_EntityUavStation(world);
/*     */     
/*  32 */     uavst.func_70107_b(x, y + uavst.field_70129_M, z);
/*  33 */     uavst.field_70169_q = x;
/*  34 */     uavst.field_70167_r = y;
/*  35 */     uavst.field_70166_s = z;
/*  36 */     uavst.setKind(kind);
/*  37 */     return uavst;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/*  45 */     float f = 1.0F;
/*  46 */     float f1 = par3EntityPlayer.field_70127_C + (par3EntityPlayer.field_70125_A - par3EntityPlayer.field_70127_C) * f;
/*  47 */     float f2 = par3EntityPlayer.field_70126_B + (par3EntityPlayer.field_70177_z - par3EntityPlayer.field_70126_B) * f;
/*  48 */     double d0 = par3EntityPlayer.field_70169_q + (par3EntityPlayer.field_70165_t - par3EntityPlayer.field_70169_q) * f;
/*  49 */     double d1 = par3EntityPlayer.field_70167_r + (par3EntityPlayer.field_70163_u - par3EntityPlayer.field_70167_r) * f + 1.62D - par3EntityPlayer.field_70129_M;
/*  50 */     double d2 = par3EntityPlayer.field_70166_s + (par3EntityPlayer.field_70161_v - par3EntityPlayer.field_70166_s) * f;
/*  51 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(par2World, d0, d1, d2);
/*  52 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/*  53 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/*  54 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/*  55 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/*  56 */     float f7 = f4 * f5;
/*  57 */     float f8 = f3 * f5;
/*  58 */     double d3 = 5.0D;
/*  59 */     Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
/*  60 */     MovingObjectPosition movingobjectposition = W_WorldFunc.clip(par2World, vec3, vec31, true);
/*     */     
/*  62 */     if (movingobjectposition == null)
/*     */     {
/*  64 */       return par1ItemStack;
/*     */     }
/*     */ 
/*     */     
/*  68 */     Vec3 vec32 = par3EntityPlayer.func_70676_i(f);
/*  69 */     boolean flag = false;
/*  70 */     float f9 = 1.0F;
/*  71 */     List<Entity> list = par2World.func_72839_b((Entity)par3EntityPlayer, par3EntityPlayer.field_70121_D.func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b(f9, f9, f9));
/*     */     
/*     */     int i;
/*  74 */     for (i = 0; i < list.size(); i++) {
/*     */       
/*  76 */       Entity entity = list.get(i);
/*     */       
/*  78 */       if (entity.func_70067_L()) {
/*     */         
/*  80 */         float f10 = entity.func_70111_Y();
/*  81 */         AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f10, f10, f10);
/*     */         
/*  83 */         if (axisalignedbb.func_72318_a(vec3))
/*     */         {
/*  85 */           flag = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     if (flag)
/*     */     {
/*  92 */       return par1ItemStack;
/*     */     }
/*     */ 
/*     */     
/*  96 */     if (W_MovingObjectPosition.isHitTypeTile(movingobjectposition)) {
/*     */       
/*  98 */       i = movingobjectposition.field_72311_b;
/*  99 */       int j = movingobjectposition.field_72312_c;
/* 100 */       int k = movingobjectposition.field_72309_d;
/*     */       
/* 102 */       MCH_EntityUavStation entityUavSt = createUavStation(par2World, (i + 0.5F), (j + 1.0F), (k + 0.5F), this.UavStationKind);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       int rot = (int)(MCH_Lib.getRotate360(par3EntityPlayer.field_70177_z) + 45.0D);
/* 109 */       entityUavSt.field_70177_z = (rot / 90 * 90 - 180);
/* 110 */       entityUavSt.initUavPostion();
/*     */       
/* 112 */       if (!par2World.func_72945_a((Entity)entityUavSt, entityUavSt.field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty())
/*     */       {
/* 114 */         return par1ItemStack;
/*     */       }
/*     */       
/* 117 */       if (!par2World.field_72995_K)
/*     */       {
/* 119 */         par2World.func_72838_d((Entity)entityUavSt);
/*     */       }
/*     */       
/* 122 */       if (!par3EntityPlayer.field_71075_bZ.field_75098_d)
/*     */       {
/* 124 */         par1ItemStack.field_77994_a--;
/*     */       }
/*     */     } 
/*     */     
/* 128 */     return par1ItemStack;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_ItemUavStation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */