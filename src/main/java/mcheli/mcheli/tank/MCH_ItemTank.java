/*    */ package mcheli.mcheli.tank;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_ItemAircraft;
/*    */ import mcheli.tank.MCH_EntityTank;
/*    */ import mcheli.tank.MCH_TankInfo;
/*    */ import mcheli.tank.MCH_TankInfoManager;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class MCH_ItemTank
/*    */   extends MCH_ItemAircraft
/*    */ {
/*    */   public MCH_ItemTank(int par1) {
/* 19 */     super(par1);
/* 20 */     this.field_77777_bU = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_AircraftInfo getAircraftInfo() {
/* 25 */     return (MCH_AircraftInfo)MCH_TankInfoManager.getFromItem((Item)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_EntityTank createAircraft(World world, double x, double y, double z, ItemStack itemStack) {
/* 30 */     MCH_TankInfo info = MCH_TankInfoManager.getFromItem((Item)this);
/* 31 */     if (info == null) {
/*    */       
/* 33 */       MCH_Lib.Log(world, "##### MCH_EntityTank Tank info null %s", new Object[] { func_77658_a() });
/* 34 */       return null;
/*    */     } 
/* 36 */     MCH_EntityTank tank = new MCH_EntityTank(world);
/*    */     
/* 38 */     tank.func_70107_b(x, y + tank.field_70129_M, z);
/* 39 */     tank.field_70169_q = x;
/* 40 */     tank.field_70167_r = y;
/* 41 */     tank.field_70166_s = z;
/* 42 */     tank.camera.setPosition(x, y, z);
/* 43 */     tank.setTypeName(info.name);
/* 44 */     if (!world.field_72995_K)
/*    */     {
/* 46 */       tank.setTextureName(info.getTextureName());
/*    */     }
/* 48 */     return tank;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_ItemTank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */