/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class MCH_BoundingBox
/*    */ {
/*    */   public final AxisAlignedBB boundingBox;
/*    */   public final AxisAlignedBB backupBoundingBox;
/*    */   public final double offsetX;
/*    */   public final double offsetY;
/*    */   public final double offsetZ;
/*    */   public final float width;
/*    */   public final float height;
/*    */   public Vec3 rotatedOffset;
/*    */   public Vec3 nowPos;
/*    */   public Vec3 prevPos;
/*    */   public final float damegeFactor;
/*    */   
/*    */   public MCH_BoundingBox(double x, double y, double z, float w, float h, float df) {
/* 22 */     this.offsetX = x;
/* 23 */     this.offsetY = y;
/* 24 */     this.offsetZ = z;
/* 25 */     this.width = w;
/* 26 */     this.height = h;
/* 27 */     this.damegeFactor = df;
/* 28 */     this.boundingBox = AxisAlignedBB.func_72330_a(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
/* 29 */     this.backupBoundingBox = AxisAlignedBB.func_72330_a(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
/* 30 */     this.nowPos = Vec3.func_72443_a(x, y, z);
/* 31 */     this.prevPos = Vec3.func_72443_a(x, y, z);
/* 32 */     updatePosition(0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public mcheli.aircraft.MCH_BoundingBox copy() {
/* 37 */     return new mcheli.aircraft.MCH_BoundingBox(this.offsetX, this.offsetY, this.offsetZ, this.width, this.height, this.damegeFactor);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updatePosition(double posX, double posY, double posZ, float yaw, float pitch, float roll) {
/* 42 */     Vec3 v = Vec3.func_72443_a(this.offsetX, this.offsetY, this.offsetZ);
/* 43 */     this.rotatedOffset = MCH_Lib.RotVec3(v, -yaw, -pitch, -roll);
/*    */     
/* 45 */     float w = this.width;
/* 46 */     float h = this.height;
/*    */     
/* 48 */     double x = posX + this.rotatedOffset.field_72450_a;
/* 49 */     double y = posY + this.rotatedOffset.field_72448_b;
/* 50 */     double z = posZ + this.rotatedOffset.field_72449_c;
/*    */     
/* 52 */     this.prevPos.field_72450_a = this.nowPos.field_72450_a;
/* 53 */     this.prevPos.field_72448_b = this.nowPos.field_72448_b;
/* 54 */     this.prevPos.field_72449_c = this.nowPos.field_72449_c;
/* 55 */     this.nowPos.field_72450_a = x;
/* 56 */     this.nowPos.field_72448_b = y;
/* 57 */     this.nowPos.field_72449_c = z;
/*    */     
/* 59 */     this.backupBoundingBox.func_72328_c(this.boundingBox);
/* 60 */     this.boundingBox.func_72324_b(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_BoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */