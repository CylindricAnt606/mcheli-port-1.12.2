/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class MCH_SeatInfo
/*    */ {
/*    */   public final Vec3 pos;
/*    */   public final boolean gunner;
/*    */   private final MCH_AircraftInfo.CameraPosition camPos;
/*    */   public boolean invCamPos;
/*    */   public final boolean switchgunner;
/*    */   public final boolean fixRot;
/*    */   public final float fixYaw;
/*    */   public final float fixPitch;
/*    */   public final float minPitch;
/*    */   public final float maxPitch;
/*    */   public final boolean rotSeat;
/*    */   
/*    */   public MCH_SeatInfo(Vec3 p, boolean g, MCH_AircraftInfo.CameraPosition cp, boolean icp, boolean sg, boolean fr, float yaw, float pitch, float pmin, float pmax, boolean rotSeat) {
/* 21 */     this.camPos = cp;
/* 22 */     this.pos = p;
/* 23 */     this.gunner = g;
/* 24 */     this.invCamPos = icp;
/* 25 */     this.switchgunner = sg;
/* 26 */     this.fixRot = fr;
/* 27 */     this.fixYaw = yaw;
/* 28 */     this.fixPitch = pitch;
/* 29 */     this.minPitch = pmin;
/* 30 */     this.maxPitch = pmax;
/* 31 */     this.rotSeat = rotSeat;
/*    */   }
/*    */   
/*    */   public MCH_SeatInfo(Vec3 p, boolean g, MCH_AircraftInfo.CameraPosition cp, boolean icp, boolean sg, boolean fr, float yaw, float pitch, boolean rotSeat) {
/* 35 */     this(p, g, cp, icp, sg, fr, yaw, pitch, -30.0F, 70.0F, rotSeat);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_SeatInfo(Vec3 p, MCH_AircraftInfo.CameraPosition cp, float yaw, float pitch, boolean rotSeat) {
/* 40 */     this(p, false, cp, false, false, false, yaw, pitch, -30.0F, 70.0F, rotSeat);
/*    */   }
/*    */   
/*    */   public MCH_SeatInfo(Vec3 p, boolean rotSeat) {
/* 44 */     this(p, false, null, false, false, false, 0.0F, 0.0F, -30.0F, 70.0F, rotSeat);
/*    */   }
/*    */ 
/*    */   
/*    */   public MCH_AircraftInfo.CameraPosition getCamPos() {
/* 49 */     return this.camPos;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_SeatInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */