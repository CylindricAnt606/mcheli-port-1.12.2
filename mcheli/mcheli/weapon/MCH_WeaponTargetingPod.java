/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.multiplay.MCH_Multiplay;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponTargetingPod extends MCH_WeaponBase {
/*    */   public MCH_WeaponTargetingPod(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.interval = -90;
/* 15 */     if (w.field_72995_K) this.interval -= 10;
/*    */   
/*    */   }
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 20 */     if (!this.worldObj.field_72995_K) {
/*    */       
/* 22 */       MCH_WeaponInfo info = getInfo();
/*    */ 
/*    */       
/* 25 */       if ((info.target & 0x40) != 0) {
/*    */         
/* 27 */         if (MCH_Multiplay.markPoint((EntityPlayer)prm.user, prm.posX, prm.posY, prm.posZ))
/*    */         {
/*    */ 
/*    */ 
/*    */           
/* 32 */           playSound(prm.user);
/*    */         }
/*    */         else
/*    */         {
/* 36 */           playSound(prm.user, "ng");
/*    */         
/*    */         }
/*    */       
/*    */       }
/* 41 */       else if (MCH_Multiplay.spotEntity((EntityPlayer)prm.user, (MCH_EntityAircraft)prm.entity, prm.posX, prm.posY, prm.posZ, info.target, info.length, info.markTime, info.angle)) {
/*    */ 
/*    */ 
/*    */         
/* 45 */         playSound(prm.entity);
/*    */       }
/*    */       else {
/*    */         
/* 49 */         playSound(prm.entity, "ng");
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponTargetingPod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */