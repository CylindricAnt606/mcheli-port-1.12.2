/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.MCH_Camera;
/*    */ import mcheli.wrapper.W_Session;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_ViewEntityDummy extends EntityPlayerSP {
/* 11 */   private static mcheli.MCH_ViewEntityDummy instance = null;
/*    */   
/*    */   private float zoom;
/*    */   
/*    */   private MCH_ViewEntityDummy(World world) {
/* 16 */     super(Minecraft.func_71410_x(), world, W_Session.newSession(), 0);
/* 17 */     this.field_70737_aN = 0;
/* 18 */     this.field_70738_aO = 1;
/* 19 */     func_70105_a(1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static mcheli.MCH_ViewEntityDummy getInstance(World w) {
/* 24 */     if (instance == null || instance.field_70128_L)
/*    */     {
/* 26 */       if (w.field_72995_K) {
/*    */         
/* 28 */         instance = new mcheli.MCH_ViewEntityDummy(w);
/* 29 */         if ((Minecraft.func_71410_x()).field_71439_g != null)
/*    */         {
/* 31 */           instance.field_71158_b = (Minecraft.func_71410_x()).field_71439_g.field_71158_b;
/*    */         }
/* 33 */         instance.func_70107_b(0.0D, -4.0D, 0.0D);
/* 34 */         w.func_72838_d((Entity)instance);
/*    */       } 
/*    */     }
/* 37 */     return instance;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void onUnloadWorld() {
/* 42 */     if (instance != null) {
/*    */       
/* 44 */       instance.func_70106_y();
/* 45 */       instance = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(MCH_Camera camera) {
/* 56 */     if (camera == null)
/* 57 */       return;  this.zoom = camera.getCameraZoom();
/* 58 */     this.field_70126_B = this.field_70177_z;
/* 59 */     this.field_70127_C = this.field_70125_A;
/*    */     
/* 61 */     this.field_70177_z = camera.rotationYaw;
/* 62 */     this.field_70125_A = camera.rotationPitch;
/* 63 */     this.field_70169_q = camera.posX;
/* 64 */     this.field_70167_r = camera.posY;
/* 65 */     this.field_70166_s = camera.posZ;
/* 66 */     this.field_70165_t = camera.posX;
/* 67 */     this.field_70163_u = camera.posY;
/* 68 */     this.field_70161_v = camera.posZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setCameraPosition(double x, double y, double z) {
/* 73 */     if (instance == null)
/* 74 */       return;  instance.field_70169_q = x;
/* 75 */     instance.field_70167_r = y;
/* 76 */     instance.field_70166_s = z;
/* 77 */     instance.field_70142_S = x;
/* 78 */     instance.field_70137_T = y;
/* 79 */     instance.field_70136_U = z;
/* 80 */     instance.field_70165_t = x;
/* 81 */     instance.field_70163_u = y;
/* 82 */     instance.field_70161_v = z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_71151_f() {
/* 88 */     return super.func_71151_f() * 1.0F / this.zoom;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ViewEntityDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */