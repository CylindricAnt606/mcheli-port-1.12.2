/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.weapon.MCH_EntityATMissile;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponInfo;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_WeaponATMissile extends MCH_WeaponEntitySeeker {
/*    */   public MCH_WeaponATMissile(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/* 13 */     super(w, v, yaw, pitch, nm, wi);
/* 14 */     this.power = 32;
/* 15 */     this.acceleration = 2.0F;
/* 16 */     this.explosionPower = 4;
/* 17 */     this.interval = -100;
/* 18 */     if (w.field_72995_K) this.interval -= 10; 
/* 19 */     this.numMode = 2;
/*    */     
/* 21 */     this.guidanceSystem.canLockOnGround = true;
/* 22 */     this.guidanceSystem.ridableOnly = wi.ridableOnly;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCooldownCountReloadTime() {
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     String opt = "";
/* 34 */     if (getCurrentMode() == 1) opt = " [TA]"; 
/* 35 */     return super.getName() + opt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(int countWait) {
/* 41 */     super.update(countWait);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shot(MCH_WeaponParam prm) {
/* 46 */     if (this.worldObj.field_72995_K) {
/* 47 */       return shotClient(prm.entity, prm.user);
/*    */     }
/* 49 */     return shotServer(prm);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean shotClient(Entity entity, Entity user) {
/* 55 */     boolean result = false;
/*    */     
/* 57 */     if (this.guidanceSystem.lock(user))
/*    */     {
/* 59 */       if (this.guidanceSystem.lastLockEntity != null) {
/*    */         
/* 61 */         result = true;
/* 62 */         this.optionParameter1 = W_Entity.getEntityId(this.guidanceSystem.lastLockEntity);
/*    */       } 
/*    */     }
/*    */     
/* 66 */     this.optionParameter2 = getCurrentMode();
/*    */     
/* 68 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean shotServer(MCH_WeaponParam prm) {
/* 74 */     Entity tgtEnt = null;
/* 75 */     tgtEnt = prm.user.field_70170_p.func_73045_a(prm.option1);
/* 76 */     if (tgtEnt == null || tgtEnt.field_70128_L) return false;
/*    */     
/* 78 */     float yaw = prm.user.field_70177_z + this.fixRotationYaw;
/* 79 */     float pitch = prm.entity.field_70125_A + this.fixRotationPitch;
/*    */     
/* 81 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */     
/* 83 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*    */     
/* 85 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*    */     
/* 87 */     MCH_EntityATMissile e = new MCH_EntityATMissile(this.worldObj, prm.posX, prm.posY, prm.posZ, tX, tY, tZ, yaw, pitch, this.acceleration);
/*    */     
/* 89 */     e.setName(this.name);
/* 90 */     e.setParameterFromWeapon((MCH_WeaponBase)this, prm.entity, prm.user);
/*    */     
/* 92 */     e.setTargetEntity(tgtEnt);
/* 93 */     e.guidanceType = prm.option2;
/*    */     
/* 95 */     this.worldObj.func_72838_d((Entity)e);
/*    */     
/* 97 */     playSound(prm.entity);
/*    */     
/* 99 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponATMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */