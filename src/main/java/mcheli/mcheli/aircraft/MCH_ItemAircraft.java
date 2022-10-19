/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Achievement;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_ItemAircraftDispenseBehavior;
/*     */ import mcheli.wrapper.W_EntityPlayer;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class MCH_ItemAircraft
/*     */   extends W_Item
/*     */ {
/*     */   public MCH_ItemAircraft(int i) {
/*  29 */     super(i);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isRegistedDispenseBehavior = false;
/*     */   
/*     */   public static void registerDispenseBehavior(Item item) {
/*  36 */     if (isRegistedDispenseBehavior == true) {
/*     */       return;
/*     */     }
/*  39 */     BlockDispenser.field_149943_a.func_82595_a(item, new MCH_ItemAircraftDispenseBehavior());
/*     */   }
/*     */   
/*     */   public abstract MCH_AircraftInfo getAircraftInfo();
/*     */   
/*     */   public abstract MCH_EntityAircraft createAircraft(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, ItemStack paramItemStack);
/*     */   
/*     */   public MCH_EntityAircraft onTileClick(ItemStack itemStack, World world, float rotationYaw, int x, int y, int z) {
/*  47 */     MCH_EntityAircraft ac = createAircraft(world, (x + 0.5F), (y + 1.0F), (z + 0.5F), itemStack);
/*  48 */     if (ac == null)
/*     */     {
/*  50 */       return null;
/*     */     }
/*     */     
/*  53 */     ac.initRotationYaw((((MathHelper.func_76128_c((rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) - 1) * 90));
/*     */     
/*  55 */     if (!world.func_72945_a((Entity)ac, ac.field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty())
/*     */     {
/*  57 */       return null;
/*     */     }
/*     */     
/*  60 */     return ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  65 */     MCH_AircraftInfo info = getAircraftInfo();
/*  66 */     if (info != null)
/*     */     {
/*  68 */       return super.toString() + "(" + info.getDirectoryName() + ":" + info.name + ")";
/*     */     }
/*     */ 
/*     */     
/*  72 */     return super.toString() + "(null)";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
/*  81 */     float f = 1.0F;
/*  82 */     float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
/*  83 */     float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
/*  84 */     double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
/*  85 */     double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f + 1.62D - player.field_70129_M;
/*  86 */     double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
/*  87 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(world, d0, d1, d2);
/*  88 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/*  89 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/*  90 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/*  91 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/*  92 */     float f7 = f4 * f5;
/*  93 */     float f8 = f3 * f5;
/*  94 */     double d3 = 5.0D;
/*  95 */     Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
/*  96 */     MovingObjectPosition mop = W_WorldFunc.clip(world, vec3, vec31, true);
/*     */     
/*  98 */     if (mop == null)
/*     */     {
/* 100 */       return par1ItemStack;
/*     */     }
/*     */     
/* 103 */     Vec3 vec32 = player.func_70676_i(f);
/* 104 */     boolean flag = false;
/* 105 */     float f9 = 1.0F;
/* 106 */     List<Entity> list = world.func_72839_b((Entity)player, player.field_70121_D.func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b(f9, f9, f9));
/*     */ 
/*     */     
/* 109 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 111 */       Entity entity = list.get(i);
/*     */       
/* 113 */       if (entity.func_70067_L()) {
/*     */         
/* 115 */         float f10 = entity.func_70111_Y();
/* 116 */         AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f10, f10, f10);
/*     */         
/* 118 */         if (axisalignedbb.func_72318_a(vec3))
/*     */         {
/* 120 */           flag = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     if (flag)
/*     */     {
/* 127 */       return par1ItemStack;
/*     */     }
/*     */ 
/*     */     
/* 131 */     if (W_MovingObjectPosition.isHitTypeTile(mop)) {
/*     */       
/* 133 */       if (MCH_Config.PlaceableOnSpongeOnly.prmBool) {
/*     */         
/* 135 */         Block block = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 136 */         if (!(block instanceof net.minecraft.block.BlockSponge))
/*     */         {
/* 138 */           return par1ItemStack;
/*     */         }
/*     */       } 
/* 141 */       spawnAircraft(par1ItemStack, world, player, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */     } 
/*     */ 
/*     */     
/* 145 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityAircraft spawnAircraft(ItemStack itemStack, World world, EntityPlayer player, int x, int y, int z) {
/* 150 */     MCH_EntityAircraft ac = onTileClick(itemStack, world, player.field_70177_z, x, y, z);
/*     */     
/* 152 */     if (ac != null)
/*     */     {
/* 154 */       if (ac.isUAV()) {
/*     */         
/* 156 */         if (world.field_72995_K)
/*     */         {
/* 158 */           if (ac.isSmallUAV()) {
/*     */             
/* 160 */             W_EntityPlayer.addChatMessage(player, "Please use the UAV station OR Portable Controller");
/*     */           }
/*     */           else {
/*     */             
/* 164 */             W_EntityPlayer.addChatMessage(player, "Please use the UAV station");
/*     */           } 
/*     */         }
/* 167 */         ac = null;
/*     */       }
/*     */       else {
/*     */         
/* 171 */         if (!world.field_72995_K) {
/*     */           
/* 173 */           ac.getAcDataFromItem(itemStack);
/* 174 */           world.func_72838_d((Entity)ac);
/*     */ 
/*     */           
/* 177 */           MCH_Achievement.addStat((Entity)player, MCH_Achievement.welcome, 1);
/*     */         } 
/*     */         
/* 180 */         if (!player.field_71075_bZ.field_75098_d)
/*     */         {
/* 182 */           itemStack.field_77994_a--;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 187 */     return ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rideEntity(ItemStack item, Entity target, EntityPlayer player) {
/* 192 */     if (!MCH_Config.PlaceableOnSpongeOnly.prmBool)
/*     */     {
/* 194 */       if (target instanceof net.minecraft.entity.item.EntityMinecartEmpty && target.field_70153_n == null) {
/*     */         
/* 196 */         MCH_EntityAircraft ac = spawnAircraft(item, player.field_70170_p, player, (int)target.field_70165_t, (int)target.field_70163_u + 2, (int)target.field_70161_v);
/*     */         
/* 198 */         if (!player.field_70170_p.field_72995_K && ac != null)
/*     */         {
/* 200 */           ac.func_70078_a(target);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_ItemAircraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */