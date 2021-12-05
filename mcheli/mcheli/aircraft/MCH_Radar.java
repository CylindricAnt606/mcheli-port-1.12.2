/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import mcheli.MCH_Vector2;
/*    */ import mcheli.wrapper.W_WorldFunc;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_Radar
/*    */ {
/*    */   private World worldObj;
/* 15 */   private ArrayList<MCH_Vector2> entityList = new ArrayList<MCH_Vector2>();
/* 16 */   private ArrayList<MCH_Vector2> enemyList = new ArrayList<MCH_Vector2>();
/*    */   
/* 18 */   public ArrayList<MCH_Vector2> getEntityList() { return this.entityList; } public ArrayList<MCH_Vector2> getEnemyList() {
/* 19 */     return this.enemyList;
/*    */   }
/*    */   
/*    */   public MCH_Radar(World world) {
/* 23 */     this.worldObj = world;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 28 */     this.entityList.clear();
/* 29 */     this.enemyList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateXZ(Entity centerEntity, int range) {
/* 34 */     if (!this.worldObj.field_72995_K)
/*    */       return; 
/* 36 */     clear();
/*    */     
/* 38 */     List<Entity> list = centerEntity.field_70170_p.func_72839_b(centerEntity, centerEntity.field_70121_D.func_72314_b(range, range, range));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     for (int i = 0; i < list.size(); i++) {
/*    */       
/* 45 */       Entity entity = list.get(i);
/* 46 */       if (entity instanceof net.minecraft.entity.EntityLiving) {
/*    */         
/* 48 */         double x = entity.field_70165_t - centerEntity.field_70165_t;
/* 49 */         double z = entity.field_70161_v - centerEntity.field_70161_v;
/* 50 */         if (x * x + z * z < (range * range)) {
/*    */           
/* 52 */           int y = 1 + (int)entity.field_70163_u;
/* 53 */           if (y < 0) y = 1; 
/* 54 */           int blockCnt = 0;
/* 55 */           for (; y < 200; y++) {
/*    */             
/* 57 */             if (W_WorldFunc.getBlockId(this.worldObj, (int)entity.field_70165_t, y, (int)entity.field_70161_v) != 0) {
/*    */               
/* 59 */               blockCnt++;
/* 60 */               if (blockCnt >= 5) {
/*    */                 break;
/*    */               }
/*    */             } 
/*    */           } 
/*    */           
/* 66 */           if (blockCnt < 5)
/*    */           {
/* 68 */             if (entity instanceof net.minecraft.entity.monster.EntityMob) {
/*    */               
/* 70 */               this.enemyList.add(new MCH_Vector2(x, z));
/*    */             }
/*    */             else {
/*    */               
/* 74 */               this.entityList.add(new MCH_Vector2(x, z));
/*    */             } 
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_Radar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */