/*    */ package mcheli.mcheli.vehicle;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_ItemAircraft;
/*    */ import mcheli.vehicle.MCH_EntityVehicle;
/*    */ import mcheli.vehicle.MCH_VehicleInfo;
/*    */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class MCH_ItemVehicle
/*    */   extends MCH_ItemAircraft
/*    */ {
/*    */   public MCH_ItemVehicle(int par1) {
/* 19 */     super(par1);
/* 20 */     this.field_77777_bU = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_AircraftInfo getAircraftInfo() {
/* 25 */     return (MCH_AircraftInfo)MCH_VehicleInfoManager.getFromItem((Item)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_EntityVehicle createAircraft(World world, double x, double y, double z, ItemStack item) {
/* 30 */     MCH_VehicleInfo info = MCH_VehicleInfoManager.getFromItem((Item)this);
/* 31 */     if (info == null) {
/*    */       
/* 33 */       MCH_Lib.Log(world, "##### MCH_ItemVehicle Vehicle info null %s", new Object[] { func_77658_a() });
/* 34 */       return null;
/*    */     } 
/* 36 */     MCH_EntityVehicle vehicle = new MCH_EntityVehicle(world);
/*    */     
/* 38 */     vehicle.func_70107_b(x, y + vehicle.field_70129_M, z);
/* 39 */     vehicle.field_70169_q = x;
/* 40 */     vehicle.field_70167_r = y;
/* 41 */     vehicle.field_70166_s = z;
/* 42 */     vehicle.camera.setPosition(x, y, z);
/* 43 */     vehicle.setTypeName(info.name);
/* 44 */     if (!world.field_72995_K)
/*    */     {
/* 46 */       vehicle.setTextureName(info.getTextureName());
/*    */     }
/* 48 */     return vehicle;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_ItemVehicle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */