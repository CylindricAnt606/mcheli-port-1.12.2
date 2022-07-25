/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_EntityRenderer;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_Camera
/*     */ {
/*     */   private final World worldObj;
/*     */   private float zoom;
/*     */   private int[] mode;
/*     */   private boolean[] canUseShader;
/*     */   private int[] lastMode;
/*     */   public double posX;
/*     */   public double posY;
/*     */   public double posZ;
/*     */   public float rotationYaw;
/*     */   public float rotationPitch;
/*     */   public float prevRotationYaw;
/*     */   public float prevRotationPitch;
/*     */   private int lastZoomDir;
/*     */   public float partRotationYaw;
/*     */   public float partRotationPitch;
/*     */   public float prevPartRotationYaw;
/*     */   public float prevPartRotationPitch;
/*     */   public static final int MODE_NORMAL = 0;
/*     */   public static final int MODE_NIGHTVISION = 1;
/*     */   public static final int MODE_THERMALVISION = 2;
/*     */   
/*     */   public MCH_Camera(World w, Entity p) {
/*  38 */     this.worldObj = w;
/*  39 */     this.mode = new int[] { 0, 0 };
/*  40 */     this.zoom = 1.0F;
/*  41 */     this.lastMode = new int[getUserMax()];
/*  42 */     this.lastZoomDir = 0;
/*  43 */     this.canUseShader = new boolean[getUserMax()];
/*     */   }
/*     */   
/*     */   public MCH_Camera(World w, Entity p, double x, double y, double z) {
/*  47 */     this(w, p);
/*  48 */     setPosition(x, y, z);
/*  49 */     setCameraZoom(1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUserMax() {
/*  54 */     return this.mode.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initCamera(int uid, Entity viewer) {
/*  59 */     setCameraZoom(1.0F);
/*     */     
/*  61 */     setMode(uid, 0);
/*  62 */     updateViewer(uid, viewer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMode(int uid, int m) {
/*  67 */     if (!isValidUid(uid))
/*  68 */       return;  this.mode[uid] = (m < 0) ? 0 : (m % getModeNum(uid));
/*     */     
/*  70 */     switch (this.mode[uid]) {
/*     */       
/*     */       case 2:
/*  73 */         if (this.worldObj.field_72995_K)
/*     */         {
/*  75 */           W_EntityRenderer.activateShader("pencil");
/*     */         }
/*     */         break;
/*     */       case 0:
/*     */       case 1:
/*  80 */         if (this.worldObj.field_72995_K)
/*     */         {
/*  82 */           W_EntityRenderer.deactivateShader();
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShaderSupport(int uid, Boolean b) {
/*  92 */     if (isValidUid(uid)) {
/*     */       
/*  94 */       setMode(uid, 0);
/*  95 */       this.canUseShader[uid] = b.booleanValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidUid(int uid) {
/* 101 */     return (uid >= 0 && uid < getUserMax());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getModeNum(int uid) {
/* 106 */     if (!isValidUid(uid)) return 2; 
/* 107 */     return this.canUseShader[uid] ? 3 : 2;
/*     */   }
/*     */   public int getMode(int uid) {
/* 110 */     return isValidUid(uid) ? this.mode[uid] : 0;
/*     */   }
/*     */   public String getModeName(int uid) {
/* 113 */     if (getMode(uid) == 1) return "NIGHT VISION"; 
/* 114 */     if (getMode(uid) == 2) return "THERMAL VISION"; 
/* 115 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateViewer(int uid, Entity viewer) {
/* 120 */     if (!isValidUid(uid) || viewer == null)
/* 121 */       return;  if (W_Lib.isEntityLivingBase(viewer) && !viewer.field_70128_L) {
/*     */ 
/*     */       
/* 124 */       if (getMode(uid) == 0 && this.lastMode[uid] != 0) {
/*     */         
/* 126 */         PotionEffect pe = W_Entity.getActivePotionEffect(viewer, Potion.field_76439_r);
/* 127 */         if (pe != null && pe.func_76459_b() > 0 && pe.func_76459_b() < 500)
/*     */         {
/* 129 */           if (viewer.field_70170_p.field_72995_K) {
/*     */             
/* 131 */             W_Entity.removePotionEffectClient(viewer, Potion.field_76439_r.field_76415_H);
/*     */           }
/*     */           else {
/*     */             
/* 135 */             W_Entity.removePotionEffect(viewer, Potion.field_76439_r.field_76415_H);
/*     */           } 
/*     */         }
/*     */       } 
/* 139 */       if (getMode(uid) == 1 || getMode(uid) == 2) {
/*     */         
/* 141 */         PotionEffect pe = W_Entity.getActivePotionEffect(viewer, Potion.field_76439_r);
/* 142 */         if (pe == null || (pe != null && pe.func_76459_b() < 500))
/*     */         {
/* 144 */           if (!viewer.field_70170_p.field_72995_K)
/*     */           {
/* 146 */             W_Entity.addPotionEffect(viewer, new PotionEffect(Potion.field_76439_r.field_76415_H, 250, 0, true));
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     this.lastMode[uid] = getMode(uid);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(double x, double y, double z) {
/* 157 */     this.posX = x;
/* 158 */     this.posY = y;
/* 159 */     this.posZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCameraZoom(float z) {
/* 164 */     float prevZoom = this.zoom;
/* 165 */     this.zoom = (z < 1.0F) ? 1.0F : z;
/* 166 */     if (this.zoom > prevZoom) {
/*     */       
/* 168 */       this.lastZoomDir = 1;
/*     */     }
/* 170 */     else if (this.zoom < prevZoom) {
/*     */       
/* 172 */       this.lastZoomDir = -1;
/*     */     }
/*     */     else {
/*     */       
/* 176 */       this.lastZoomDir = 0;
/*     */     } 
/*     */   } public float getCameraZoom() {
/* 179 */     return this.zoom;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Camera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */