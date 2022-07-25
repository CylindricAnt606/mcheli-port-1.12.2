/* Ported to 1.12.2 by super_craft_alex */

package mcheli.mcheli.aircraft;

import mcheli.mcheli.MCH_Lib;
import mcheli.mcheli.eval.util.Vec3;
import net.minecraft.util.math.AxisAlignedBB;

 public class MCH_BoundingBox
 {
   public AxisAlignedBB boundingBox;
   public AxisAlignedBB backupBoundingBox;
   public final double offsetX;
   public final double offsetY;
   public final double offsetZ;
   public final float width;
   public final float height;
   public Vec3 rotatedOffset;
   public Vec3 nowPos;
   public Vec3 prevPos;
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
     this.nowPos = new Vec3(x, y, z);
     this.prevPos = new Vec3(x, y, z);
     updatePosition(0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 0.0F);
   }


   public mcheli.mcheli.aircraft.MCH_BoundingBox copy() {
     return new mcheli.mcheli.aircraft.MCH_BoundingBox(this.offsetX, this.offsetY, this.offsetZ, this.width, this.height, this.damegeFactor);
   }

   public void updatePosition(double posX, double posY, double posZ, float yaw, float pitch, float roll) {
     Vec3 v = new Vec3(this.offsetX, this.offsetY, this.offsetZ);
     this.rotatedOffset = MCH_Lib.RotVec3(v, -yaw, -pitch, -roll);

     float w = this.width;
     float h = this.height;

     double x = posX + this.rotatedOffset.x;
     double y = posY + this.rotatedOffset.y;
     double z = posZ + this.rotatedOffset.z;

     this.prevPos.x = this.nowPos.x;
     this.prevPos.y = this.nowPos.y;
     this.prevPos.z = this.nowPos.z;
     this.nowPos.x = x;
     this.nowPos.y = y;
     this.nowPos.z = z;

     this.backupBoundingBox = this.boundingBox;
     this.boundingBox = new AxisAlignedBB(x - (w / 2.0F), y - (h / 2.0F), z - (w / 2.0F), x + (w / 2.0F), y + (h / 2.0F), z + (w / 2.0F));
   }
 }