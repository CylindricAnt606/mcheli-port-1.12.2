/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import mcheli.MCH_MOD;
/*    */ import mcheli.wrapper.W_Entity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ public class W_Lib
/*    */ {
/*    */   public static boolean isEntityLivingBase(Entity entity) {
/* 12 */     return entity instanceof EntityLivingBase;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EntityLivingBase castEntityLivingBase(Object entity) {
/* 21 */     return (EntityLivingBase)entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Class getEntityLivingBaseClass() {
/* 29 */     return EntityLivingBase.class;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static double getEntityMoveDist(Entity entity) {
/* 36 */     if (entity == null) return 0.0D;
/*    */     
/* 38 */     return (entity instanceof EntityLivingBase) ? ((EntityLivingBase)entity).field_70701_bs : 0.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isClientPlayer(Entity entity) {
/* 45 */     if (entity instanceof net.minecraft.entity.player.EntityPlayer)
/*    */     {
/* 47 */       if (entity.field_70170_p.field_72995_K)
/*    */       {
/* 49 */         return W_Entity.isEqual(MCH_MOD.proxy.getClientPlayer(), entity);
/*    */       }
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isFirstPerson() {
/* 57 */     return MCH_MOD.proxy.isFirstPerson();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Lib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */