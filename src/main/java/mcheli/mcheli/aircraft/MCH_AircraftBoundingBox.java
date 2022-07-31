/* Ported to 1.12.2 by super_craft_alex */

package mcheli.mcheli.aircraft;

import mcheli.mcheli.aircraft.MCH_BoundingBox;
import mcheli.mcheli.aircraft.MCH_EntityAircraft;
import mcheli.mcheli.eval.util.Vec3;
import net.minecraft.util.math.AxisAlignedBB;

public class MCH_AircraftBoundingBox extends AxisAlignedBB {
    private final MCH_EntityAircraft ac;protected MCH_AircraftBoundingBox(MCH_EntityAircraft ac) {
        super(ac.field_70121_D.field_72340_a, ac.field_70121_D.field_72338_b, ac.field_70121_D.field_72339_c, ac.field_70121_D.field_72336_d, ac.field_70121_D.field_72337_e, ac.field_70121_D.field_72334_f);

        this.ac = ac;
    }


    public AxisAlignedBB NewAABB(double p_72324_1_, double p_72324_3_, double p_72324_5_, double p_72324_7_, double p_72324_9_, double p_72324_11_) {
        return (new mcheli.aircraft.MCH_AircraftBoundingBox(this.ac)).func_72324_b(p_72324_1_, p_72324_3_, p_72324_5_, p_72324_7_, p_72324_9_, p_72324_11_);
    }


    public double getDistSq(AxisAlignedBB a1, AxisAlignedBB a2) {
        double x1 = (a1.field_72336_d + a1.field_72340_a) / 2.0D;
        double y1 = (a1.field_72337_e + a1.field_72338_b) / 2.0D;
        double z1 = (a1.field_72334_f + a1.field_72339_c) / 2.0D;
        double x2 = (a2.field_72336_d + a2.field_72340_a) / 2.0D;
        double y2 = (a2.field_72337_e + a2.field_72338_b) / 2.0D;
        double z2 = (a2.field_72334_f + a2.field_72339_c) / 2.0D;
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return dx * dx + dy * dy + dz * dz;
    }


    public boolean func_72326_a(AxisAlignedBB aabb) {
        boolean ret = false;
        double dist = 1.0E7D;
        this.ac.lastBBDamageFactor = 1.0F;
        if (super.func_72326_a(aabb)) { dist = getDistSq(aabb, this);ret = true;
        }
        for (MCH_BoundingBox bb : this.ac.extraBoundingBox) { if (bb.boundingBox.func_72326_a(aabb)) {

            double dist2 = getDistSq(aabb, this);
            if (dist2 < dist) { dist = dist2;this.ac.lastBBDamageFactor = bb.damegeFactor;
            }
            ret = true;
        }
        }
        return ret;
    }

    public AxisAlignedBB func_72314_b(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
        double d3 = this.field_72340_a - p_72314_1_;
        double d4 = this.field_72338_b - p_72314_3_;
        double d5 = this.field_72339_c - p_72314_5_;
        double d6 = this.field_72336_d + p_72314_1_;
        double d7 = this.field_72337_e + p_72314_3_;
        double d8 = this.field_72334_f + p_72314_5_;
        return NewAABB(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB func_111270_a(AxisAlignedBB p_111270_1_) {
        double d0 = Math.min(this.field_72340_a, p_111270_1_.field_72340_a);
        double d1 = Math.min(this.field_72338_b, p_111270_1_.field_72338_b);
        double d2 = Math.min(this.field_72339_c, p_111270_1_.field_72339_c);
        double d3 = Math.max(this.field_72336_d, p_111270_1_.field_72336_d);
        double d4 = Math.max(this.field_72337_e, p_111270_1_.field_72337_e);
        double d5 = Math.max(this.field_72334_f, p_111270_1_.field_72334_f);
        return NewAABB(d0, d1, d2, d3, d4, d5);
    }


    public AxisAlignedBB func_72321_a(double p_72321_1_, double p_72321_3_, double p_72321_5_) {
        double d3 = this.field_72340_a;
        double d4 = this.field_72338_b;
        double d5 = this.field_72339_c;
        double d6 = this.field_72336_d;
        double d7 = this.field_72337_e;
        double d8 = this.field_72334_f;

        if (p_72321_1_ < 0.0D)
        { d3 += p_72321_1_;
        }

        if (p_72321_1_ > 0.0D)
        { d6 += p_72321_1_;
        }

        if (p_72321_3_ < 0.0D)
        { d4 += p_72321_3_;
        }

        if (p_72321_3_ > 0.0D)
        { d7 += p_72321_3_;
        }

        if (p_72321_5_ < 0.0D)
        { d5 += p_72321_5_;
        }

        if (p_72321_5_ > 0.0D)
        { d8 += p_72321_5_;
        }

        return NewAABB(d3, d4, d5, d6, d7, d8);
    }


    public AxisAlignedBB func_72331_e(double p_72331_1_, double p_72331_3_, double p_72331_5_) {
        double d3 = this.field_72340_a + p_72331_1_;
        double d4 = this.field_72338_b + p_72331_3_;
        double d5 = this.field_72339_c + p_72331_5_;
        double d6 = this.field_72336_d - p_72331_1_;
        double d7 = this.field_72337_e - p_72331_3_;
        double d8 = this.field_72334_f - p_72331_5_;
        return NewAABB(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB func_72329_c() {
        return NewAABB(this.field_72340_a, this.field_72338_b, this.field_72339_c, this.field_72336_d, this.field_72337_e, this.field_72334_f);
    }

    public AxisAlignedBB func_72325_c(double x, double y, double z) {
        return NewAABB(this.field_72340_a + x, this.field_72338_b + y, this.field_72339_c + z, this.field_72336_d + x, this.field_72337_e + y, this.field_72334_f + z);
    }

    public MovingObjectPosition func_72327_a(Vec3 v1, Vec3 v2) {
        this.ac.lastBBDamageFactor = 1.0F;
        MovingObjectPosition mop = super.func_72327_a(v1, v2);
        double dist = 1.0E7D;
        if (mop != null){
            dist = v1.func_72438_d(mop.field_72307_f);
        }
        for (MCH_BoundingBox bb : this.ac.extraBoundingBox) {
            MovingObjectPosition mop2 = bb.boundingBox.func_72327_a(v1, v2);
            if (mop2 != null) {
                double dist2 = v1.func_72438_d(mop2.field_72307_f);
                if (dist2 < dist) {
                    mop = mop2;dist = dist2;this.ac.lastBBDamageFactor = bb.damegeFactor;
                }
            }
        }
        return mop;
    }
}
