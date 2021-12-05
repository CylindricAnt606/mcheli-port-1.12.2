/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import mcheli.aircraft.MCH_SeatInfo;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_SeatRackInfo
/*    */   extends MCH_SeatInfo
/*    */ {
/*    */   public final float range;
/*    */   public final float openParaAlt;
/*    */   public final String[] names;
/*    */   
/*    */   public MCH_SeatRackInfo(String[] entityNames, double x, double y, double z, MCH_AircraftInfo.CameraPosition ep, float rng, float paraAlt, float yaw, float pitch, boolean rotSeat) {
/* 17 */     super(Vec3.func_72443_a(x, y, z), ep, yaw, pitch, rotSeat);
/* 18 */     this.range = rng;
/* 19 */     this.openParaAlt = paraAlt;
/* 20 */     this.names = entityNames;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3 getEntryPos() {
/* 26 */     return (getCamPos()).pos;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_SeatRackInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */