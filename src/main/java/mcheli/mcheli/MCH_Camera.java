 package mcheli.mcheli;

 import mcheli.mcheli.wrapper.W_Entity;
 import mcheli.mcheli.wrapper.W_EntityRenderer;
 import mcheli.mcheli.wrapper.W_Lib;
 import net.minecraft.entity.Entity;
 import net.minecraft.potion.Potion;
 import net.minecraft.potion.PotionEffect;
 import net.minecraft.world.World;




 public class MCH_Camera
 {
   private final World worldObj;
   private float zoom;
   private int[] mode;
   private boolean[] canUseShader;
   private int[] lastMode;
   public double posX;
   public double posY;
   public double posZ;
   public float rotationYaw;
   public float rotationPitch;
   public float prevRotationYaw;
   public float prevRotationPitch;
   private int lastZoomDir;
   public float partRotationYaw;
   public float partRotationPitch;
   public float prevPartRotationYaw;
   public float prevPartRotationPitch;
   public static final int MODE_NORMAL = 0;
   public static final int MODE_NIGHTVISION = 1;
   public static final int MODE_THERMALVISION = 2;

   public MCH_Camera(World w, Entity p) {
     this.worldObj = w;
     this.mode = new int[] { 0, 0 };
     this.zoom = 1.0F;
     this.lastMode = new int[getUserMax()];
     this.lastZoomDir = 0;
     this.canUseShader = new boolean[getUserMax()];
   }

   public MCH_Camera(World w, Entity p, double x, double y, double z) {
     this(w, p);
     setPosition(x, y, z);
     setCameraZoom(1.0F);
   }


   public int getUserMax() {
     return this.mode.length;
   }


   public void initCamera(int uid, Entity viewer) {
     setCameraZoom(1.0F);

     setMode(uid, 0);
     updateViewer(uid, viewer);
   }


   public void setMode(int uid, int m) {
     if (!isValidUid(uid))
       return;  this.mode[uid] = (m < 0) ? 0 : (m % getModeNum(uid));

     switch (this.mode[uid]) {

       case 2:
         if (this.worldObj.isRemote)
         {
           W_EntityRenderer.activateShader("pencil");
         }
         break;
       case 0:
       case 1:
         if (this.worldObj.isRemote)
         {
           W_EntityRenderer.deactivateShader();
         }
         break;
     }
   }




   public void setShaderSupport(int uid, Boolean b) {
     if (isValidUid(uid)) {

       setMode(uid, 0);
       this.canUseShader[uid] = b.booleanValue();
     }
   }


   public boolean isValidUid(int uid) {
     return (uid >= 0 && uid < getUserMax());
   }


   public int getModeNum(int uid) {
     if (!isValidUid(uid)) return 2;
     return this.canUseShader[uid] ? 3 : 2;
   }
   public int getMode(int uid) {
     return isValidUid(uid) ? this.mode[uid] : 0;
   }
   public String getModeName(int uid) {
     if (getMode(uid) == 1) return "NIGHT VISION";
     if (getMode(uid) == 2) return "THERMAL VISION";
     return "";
   }

//TODO: mappings?
   public void updateViewer(int uid, Entity viewer) {
     if (!isValidUid(uid) || viewer == null)
       return;
     if (W_Lib.isEntityLivingBase(viewer) && !viewer.field_70128_L) {

       if (getMode(uid) == 0 && this.lastMode[uid] != 0) {

         PotionEffect pe = W_Entity.getActivePotionEffect(viewer, Potion.field_76439_r);
         if (pe != null && pe.func_76459_b() > 0 && pe.func_76459_b() < 500)
         {
           if (viewer.field_70170_p.field_72995_K) {

             W_Entity.removePotionEffectClient(viewer, Potion.field_76439_r.field_76415_H);
           }
           else {

             W_Entity.removePotionEffect(viewer, Potion.field_76439_r.field_76415_H);
           }
         }
       }
       if (getMode(uid) == 1 || getMode(uid) == 2) {

         PotionEffect pe = W_Entity.getActivePotionEffect(viewer, Potion.field_76439_r);
         if (pe == null || (pe != null && pe.func_76459_b() < 500))
         {
           if (!viewer.field_70170_p.field_72995_K)
           {
             W_Entity.addPotionEffect(viewer, new PotionEffect(Potion.field_76439_r.field_76415_H, 250, 0, true));
           }
         }
       }
     }

     this.lastMode[uid] = getMode(uid);
   }


   public void setPosition(double x, double y, double z) {
     this.posX = x;
     this.posY = y;
     this.posZ = z;
   }


   public void setCameraZoom(float z) {
     float prevZoom = this.zoom;
     this.zoom = (z < 1.0F) ? 1.0F : z;
     if (this.zoom > prevZoom) {

       this.lastZoomDir = 1;
     }
     else if (this.zoom < prevZoom) {

       this.lastZoomDir = -1;
     }
     else {

       this.lastZoomDir = 0;
     }
   } public float getCameraZoom() {
     return this.zoom;
   }
 }