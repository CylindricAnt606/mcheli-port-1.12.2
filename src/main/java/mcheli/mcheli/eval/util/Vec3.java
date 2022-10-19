package mcheli.mcheli.eval.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Vec3 extends Vec3d {

    public double x;
    public double y;
    public double z;

    public Vec3(double xIn, double yIn, double zIn)
    {
        super(xIn, yIn, zIn);
    }

    public Vec3(Vec3i vector)
    {
        super(vector);
    }

    protected void setX(double xIn) {this.x = xIn;}
    protected void setY(double yIn) {this.x = yIn;}
    protected void setZ(double zIn) {this.x = zIn;}
}
