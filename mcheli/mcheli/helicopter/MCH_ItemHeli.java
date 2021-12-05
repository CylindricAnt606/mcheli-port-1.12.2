/*    */ package mcheli.mcheli.helicopter;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_ItemAircraft;
/*    */ import mcheli.helicopter.MCH_EntityHeli;
/*    */ import mcheli.helicopter.MCH_HeliInfo;
/*    */ import mcheli.helicopter.MCH_HeliInfoManager;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class MCH_ItemHeli
/*    */   extends MCH_ItemAircraft
/*    */ {
/*    */   public MCH_ItemHeli(int par1) {
/* 19 */     super(par1);
/* 20 */     this.field_77777_bU = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_AircraftInfo getAircraftInfo() {
/* 25 */     return (MCH_AircraftInfo)MCH_HeliInfoManager.getFromItem((Item)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_EntityHeli createAircraft(World world, double x, double y, double z, ItemStack itemStack) {
/* 30 */     MCH_HeliInfo info = MCH_HeliInfoManager.getFromItem((Item)this);
/* 31 */     if (info == null) {
/*    */       
/* 33 */       MCH_Lib.Log(world, "##### MCH_ItemHeli Heli info null %s", new Object[] { func_77658_a() });
/* 34 */       return null;
/*    */     } 
/* 36 */     MCH_EntityHeli heli = new MCH_EntityHeli(world);
/*    */     
/* 38 */     heli.func_70107_b(x, y + heli.field_70129_M, z);
/* 39 */     heli.field_70169_q = x;
/* 40 */     heli.field_70167_r = y;
/* 41 */     heli.field_70166_s = z;
/* 42 */     heli.camera.setPosition(x, y, z);
/* 43 */     heli.setTypeName(info.name);
/* 44 */     if (!world.field_72995_K)
/*    */     {
/* 46 */       heli.setTextureName(info.getTextureName());
/*    */     }
/* 48 */     return heli;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_ItemHeli.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */