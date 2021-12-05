/*    */ package mcheli.mcheli.hud;
/*    */ 
/*    */ public enum MCH_HudItemStringArgs
/*    */ {
/*  5 */   NONE,
/*  6 */   NAME,
/*  7 */   ALTITUDE,
/*  8 */   DATE,
/*  9 */   MC_THOR, MC_TMIN, MC_TSEC,
/* 10 */   MAX_HP,
/* 11 */   HP,
/* 12 */   HP_PER,
/* 13 */   POS_X, POS_Y, POS_Z,
/* 14 */   MOTION_X, MOTION_Y, MOTION_Z,
/* 15 */   INVENTORY,
/* 16 */   WPN_NAME,
/* 17 */   WPN_AMMO,
/* 18 */   WPN_RM_AMMO,
/* 19 */   RELOAD_PER,
/* 20 */   RELOAD_SEC,
/* 21 */   MORTAR_DIST,
/* 22 */   MC_VER,
/* 23 */   MOD_VER,
/* 24 */   MOD_NAME,
/* 25 */   YAW, PITCH, ROLL,
/* 26 */   PLYR_YAW, PLYR_PITCH,
/* 27 */   TVM_POS_X,
/* 28 */   TVM_POS_Y,
/* 29 */   TVM_POS_Z,
/* 30 */   TVM_DIFF,
/* 31 */   CAM_ZOOM,
/* 32 */   UAV_DIST,
/* 33 */   KEY_GUI,
/* 34 */   THROTTLE;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static mcheli.hud.MCH_HudItemStringArgs toArgs(String name) {
/* 40 */     mcheli.hud.MCH_HudItemStringArgs a = NONE;
/*    */     
/*    */     try {
/* 43 */       a = valueOf(name.toUpperCase());
/*    */     }
/* 45 */     catch (Exception e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } finally {}
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemStringArgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */