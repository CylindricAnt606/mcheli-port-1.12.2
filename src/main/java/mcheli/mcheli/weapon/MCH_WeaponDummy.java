/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponDummy extends MCH_WeaponBase {
/* 10 */   static final MCH_WeaponInfo dummy = new MCH_WeaponInfo("none"); public int getUseInterval() {
/* 11 */     return 0;
/*    */   }
/*    */   
/*    */   public MCH_WeaponDummy(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 15 */     super(w, v, yaw, pitch, !nm.isEmpty() ? nm : "none", (wi != null) ? wi : dummy);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */