package mcheli.mcheli.aircraft;

import mcheli.MCH_Lib;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

 public class MCH_BoundingBox
 {
   public final AxisAlignedBB boundingBox;
   public final AxisAlignedBB backupBoundingBox;
   public final double offsetX;
   public final double offsetY;
   public final double offsetZ;
   public final float width;
   public final float height;
   public Vec3d rotatedOffset;
   public Vec3d nowPos;
   public Vec3d prevPos;
   public final float damegeFactor;

   public MCH_BoundingBox(double x, double y, double z, float w, float h, float df) {
     this.offsetX = x;
     this.offsetY = y;
     this.offsetZ = z;
     this.width = w;
     this.height = h;
     this.damegeFactor = df;
     this.boundingBox = new AxisAlignedBB(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
     this.backupBoundingBox = new AxisAlignedBB(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
     this.nowPos = Vec3.func_72443_a(x, y, z);
     this.prevPos = Vec3.func_72443_a(x, y, z);
     updatePosition(0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 0.0F);
   }


   public mcheli.aircraft.MCH_BoundingBox copy() {
     return new mcheli.aircraft.MCH_BoundingBox(this.offsetX, this.offsetY, this.offsetZ, this.width, this.height, this.damegeFactor);
   }


   public void updatePosition(double posX, double posY, double posZ, float yaw, float pitch, float roll) {
     Vec3 v = Vec3.func_72443_a(this.offsetX, this.offsetY, this.offsetZ);
     this.rotatedOffset = MCH_Lib.RotVec3(v, -yaw, -pitch, -roll);

     float w = this.width;
     float h = this.height;

     double x = posX + this.rotatedOffset.field_72450_a;
     double y = posY + this.rotatedOffset.field_72448_b;
     double z = posZ + this.rotatedOffset.field_72449_c;

     this.prevPos.field_72450_a = this.nowPos.field_72450_a;
     this.prevPos.field_72448_b = this.nowPos.field_72448_b;
     this.prevPos.field_72449_c = this.nowPos.field_72449_c;
     this.nowPos.field_72450_a = x;
     this.nowPos.field_72448_b = y;
     this.nowPos.field_72449_c = z;

     this.backupBoundingBox.func_72328_c(this.boundingBox);
     this.boundingBox.func_72324_b(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
   }
 }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_BoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */