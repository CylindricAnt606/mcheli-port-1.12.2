/*    */ package mcheli.mcheli.weapon;
/*    */ 
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ 
/*    */ 
/*    */ public class MCH_Cartridge
/*    */ {
/*    */   public IModelCustom model;
/*    */   public final String name;
/*    */   public final float acceleration;
/*    */   public final float yaw;
/*    */   public final float pitch;
/*    */   public final float bound;
/*    */   public final float gravity;
/*    */   public final float scale;
/*    */   
/*    */   public MCH_Cartridge(String nm, float a, float y, float p, float b, float g, float s) {
/* 18 */     this.name = nm;
/* 19 */     this.acceleration = a;
/* 20 */     this.yaw = y;
/* 21 */     this.pitch = p;
/* 22 */     this.bound = b;
/* 23 */     this.gravity = g;
/* 24 */     this.scale = s;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_Cartridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */