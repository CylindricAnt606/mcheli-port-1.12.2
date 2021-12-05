/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_IEntityLockChecker;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponBomb;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponTargetingPod;
/*    */ import mcheli.weapon.MCH_WeaponTorpedo;
/*    */ import mcheli.weapon.MCH_WeaponTvMissile;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponCreator {
/*    */   public static MCH_WeaponBase createWeapon(World w, String weaponName, Vec3 v, float yaw, float pitch, MCH_IEntityLockChecker lockChecker, boolean onTurret) {
/*    */     MCH_WeaponTargetingPod mCH_WeaponTargetingPod;
/* 15 */     MCH_WeaponInfo info = MCH_WeaponInfoManager.get(weaponName);
/* 16 */     if (info == null || info.type == "") return null;
/*    */     
/* 18 */     MCH_WeaponBase weapon = null;
/*    */     
/* 20 */     if (info.type.compareTo("machinegun1") == 0) MCH_WeaponMachineGun1 mCH_WeaponMachineGun1 = new MCH_WeaponMachineGun1(w, v, yaw, pitch, weaponName, info); 
/* 21 */     if (info.type.compareTo("machinegun2") == 0) MCH_WeaponMachineGun2 mCH_WeaponMachineGun2 = new MCH_WeaponMachineGun2(w, v, yaw, pitch, weaponName, info); 
/* 22 */     if (info.type.compareTo("tvmissile") == 0) MCH_WeaponTvMissile mCH_WeaponTvMissile = new MCH_WeaponTvMissile(w, v, yaw, pitch, weaponName, info); 
/* 23 */     if (info.type.compareTo("torpedo") == 0) MCH_WeaponTorpedo mCH_WeaponTorpedo = new MCH_WeaponTorpedo(w, v, yaw, pitch, weaponName, info); 
/* 24 */     if (info.type.compareTo("cas") == 0) MCH_WeaponCAS mCH_WeaponCAS = new MCH_WeaponCAS(w, v, yaw, pitch, weaponName, info); 
/* 25 */     if (info.type.compareTo("rocket") == 0) MCH_WeaponRocket mCH_WeaponRocket = new MCH_WeaponRocket(w, v, yaw, pitch, weaponName, info); 
/* 26 */     if (info.type.compareTo("asmissile") == 0) MCH_WeaponASMissile mCH_WeaponASMissile = new MCH_WeaponASMissile(w, v, yaw, pitch, weaponName, info); 
/* 27 */     if (info.type.compareTo("aamissile") == 0) MCH_WeaponAAMissile mCH_WeaponAAMissile = new MCH_WeaponAAMissile(w, v, yaw, pitch, weaponName, info); 
/* 28 */     if (info.type.compareTo("atmissile") == 0) MCH_WeaponATMissile mCH_WeaponATMissile = new MCH_WeaponATMissile(w, v, yaw, pitch, weaponName, info); 
/* 29 */     if (info.type.compareTo("bomb") == 0) MCH_WeaponBomb mCH_WeaponBomb = new MCH_WeaponBomb(w, v, yaw, pitch, weaponName, info); 
/* 30 */     if (info.type.compareTo("mkrocket") == 0) MCH_WeaponMarkerRocket mCH_WeaponMarkerRocket = new MCH_WeaponMarkerRocket(w, v, yaw, pitch, weaponName, info); 
/* 31 */     if (info.type.compareTo("dummy") == 0) MCH_WeaponDummy mCH_WeaponDummy = new MCH_WeaponDummy(w, v, yaw, pitch, weaponName, info); 
/* 32 */     if (info.type.compareTo("smoke") == 0) MCH_WeaponSmoke mCH_WeaponSmoke = new MCH_WeaponSmoke(w, v, yaw, pitch, weaponName, info); 
/* 33 */     if (info.type.compareTo("dispenser") == 0) MCH_WeaponDispenser mCH_WeaponDispenser = new MCH_WeaponDispenser(w, v, yaw, pitch, weaponName, info); 
/* 34 */     if (info.type.compareTo("targetingpod") == 0) mCH_WeaponTargetingPod = new MCH_WeaponTargetingPod(w, v, yaw, pitch, weaponName, info);
/*    */     
/* 36 */     if (mCH_WeaponTargetingPod != null) {
/*    */       
/* 38 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).displayName = info.displayName;
/* 39 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).power = info.power;
/* 40 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).acceleration = info.acceleration;
/* 41 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).explosionPower = info.explosion;
/* 42 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).explosionPowerInWater = info.explosionInWater;
/* 43 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval = info.delay;
/* 44 */       mCH_WeaponTargetingPod.setLockCountMax(info.lockTime);
/* 45 */       mCH_WeaponTargetingPod.setLockChecker(lockChecker);
/* 46 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).numMode = info.modeNum;
/* 47 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).piercing = info.piercing;
/* 48 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).heatCount = info.heatCount;
/* 49 */       ((MCH_WeaponBase)mCH_WeaponTargetingPod).onTurret = onTurret;
/*    */       
/* 51 */       if (info.maxHeatCount > 0)
/*    */       {
/* 53 */         if (((MCH_WeaponBase)mCH_WeaponTargetingPod).heatCount < 2)
/*    */         {
/* 55 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).heatCount = 2;
/*    */         }
/*    */       }
/* 58 */       if (w.field_72995_K) {
/*    */         
/* 60 */         if (((MCH_WeaponBase)mCH_WeaponTargetingPod).interval < 4) {
/*    */           
/* 62 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval++;
/*    */         }
/* 64 */         else if (((MCH_WeaponBase)mCH_WeaponTargetingPod).interval < 7) {
/*    */           
/* 66 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval += 2;
/*    */         }
/* 68 */         else if (((MCH_WeaponBase)mCH_WeaponTargetingPod).interval < 10) {
/*    */           
/* 70 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval += 3;
/*    */         }
/* 72 */         else if (((MCH_WeaponBase)mCH_WeaponTargetingPod).interval < 20) {
/*    */           
/* 74 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval += 6;
/*    */         }
/*    */         else {
/*    */           
/* 78 */           ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval += 10;
/*    */           
/* 80 */           if (((MCH_WeaponBase)mCH_WeaponTargetingPod).interval >= 40)
/*    */           {
/* 82 */             ((MCH_WeaponBase)mCH_WeaponTargetingPod).interval = -((MCH_WeaponBase)mCH_WeaponTargetingPod).interval;
/*    */           }
/*    */         } 
/*    */         
/* 86 */         ((MCH_WeaponBase)mCH_WeaponTargetingPod).heatCount++;
/*    */         
/* 88 */         ((MCH_WeaponBase)mCH_WeaponTargetingPod).cartridge = info.cartridge;
/*    */       } 
/* 90 */       mCH_WeaponTargetingPod.modifyCommonParameters();
/*    */     } 
/*    */     
/* 93 */     return (MCH_WeaponBase)mCH_WeaponTargetingPod;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */