/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_IEntityLockChecker;
/*    */ import mcheli.weapon.MCH_WeaponGuidanceSystem;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class MCH_WeaponEntitySeeker extends MCH_WeaponBase {
/*    */   public MCH_IEntityLockChecker entityLockChecker;
/*    */   public MCH_WeaponGuidanceSystem guidanceSystem;
/*    */   
/*    */   public MCH_WeaponEntitySeeker(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.guidanceSystem = new MCH_WeaponGuidanceSystem(w);
/* 15 */     this.guidanceSystem.lockRange = 200.0D;
/* 16 */     this.guidanceSystem.lockAngle = 5;
/* 17 */     this.guidanceSystem.setLockCountMax(25);
/*    */   }
/*    */   
/* 20 */   public MCH_WeaponGuidanceSystem getGuidanceSystem() { return this.guidanceSystem; } public int getLockCount() {
/* 21 */     return this.guidanceSystem.getLockCount();
/*    */   }
/*    */   
/*    */   public void setLockCountMax(int n) {
/* 25 */     this.guidanceSystem.setLockCountMax(n);
/*    */   }
/*    */   
/*    */   public int getLockCountMax() {
/* 29 */     return this.guidanceSystem.getLockCountMax();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLockChecker(MCH_IEntityLockChecker checker) {
/* 34 */     this.guidanceSystem.checker = checker;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(int countWait) {
/* 40 */     super.update(countWait);
/* 41 */     this.guidanceSystem.update();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponEntitySeeker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */