/*    */ package mcheli.mcheli.plane;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.aircraft.MCH_ItemAircraft;
/*    */ import mcheli.plane.MCP_EntityPlane;
/*    */ import mcheli.plane.MCP_PlaneInfo;
/*    */ import mcheli.plane.MCP_PlaneInfoManager;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class MCP_ItemPlane
/*    */   extends MCH_ItemAircraft
/*    */ {
/*    */   public MCP_ItemPlane(int par1) {
/* 19 */     super(par1);
/* 20 */     this.field_77777_bU = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_AircraftInfo getAircraftInfo() {
/* 25 */     return (MCH_AircraftInfo)MCP_PlaneInfoManager.getFromItem((Item)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCP_EntityPlane createAircraft(World world, double x, double y, double z, ItemStack itemStack) {
/* 30 */     MCP_PlaneInfo info = MCP_PlaneInfoManager.getFromItem((Item)this);
/* 31 */     if (info == null) {
/*    */       
/* 33 */       MCH_Lib.Log(world, "##### MCP_EntityPlane Plane info null %s", new Object[] { func_77658_a() });
/* 34 */       return null;
/*    */     } 
/* 36 */     MCP_EntityPlane plane = new MCP_EntityPlane(world);
/*    */     
/* 38 */     plane.func_70107_b(x, y + plane.field_70129_M, z);
/* 39 */     plane.field_70169_q = x;
/* 40 */     plane.field_70167_r = y;
/* 41 */     plane.field_70166_s = z;
/* 42 */     plane.camera.setPosition(x, y, z);
/* 43 */     plane.setTypeName(info.name);
/* 44 */     if (!world.field_72995_K)
/*    */     {
/* 46 */       plane.setTextureName(info.getTextureName());
/*    */     }
/* 48 */     return plane;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_ItemPlane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */