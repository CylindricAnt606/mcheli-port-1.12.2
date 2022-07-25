/*     */ package mcheli.mcheli.gltd;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Camera;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.gltd.MCH_EntityGLTD;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiGLTD extends MCH_Gui {
/*     */   public MCH_GuiGLTD(Minecraft minecraft) {
/*  21 */     super(minecraft);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  27 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  38 */     return (player.field_70154_o != null && player.field_70154_o instanceof MCH_EntityGLTD);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*  43 */     if (isThirdPersonView) if (!MCH_Config.DisplayHUDThirdPerson.prmBool)
/*     */         return;  
/*  45 */     this; GL11.glLineWidth(scaleFactor);
/*     */     
/*  47 */     if (!isDrawGui(player))
/*  48 */       return;  MCH_EntityGLTD gltd = (MCH_EntityGLTD)player.field_70154_o;
/*     */ 
/*     */     
/*  51 */     if (gltd.camera.getMode(0) == 1) {
/*     */       
/*  53 */       GL11.glEnable(3042);
/*  54 */       GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
/*  55 */       int srcBlend = GL11.glGetInteger(3041);
/*  56 */       int dstBlend = GL11.glGetInteger(3040);
/*     */       
/*  58 */       GL11.glBlendFunc(1, 1);
/*     */       
/*  60 */       W_McClient.MOD_bindTexture("textures/gui/alpha.png");
/*  61 */       drawTexturedModalRectRotate(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, this.rand.nextInt(256), this.rand.nextInt(256), 256.0D, 256.0D, 0.0F);
/*     */ 
/*     */       
/*  64 */       GL11.glBlendFunc(srcBlend, dstBlend);
/*  65 */       GL11.glDisable(3042);
/*     */     } 
/*     */ 
/*     */     
/*  69 */     drawString(String.format("x%.1f", new Object[] { Float.valueOf(gltd.camera.getCameraZoom()) }), this.centerX - 70, this.centerY + 10, -805306369);
/*     */ 
/*     */     
/*  72 */     drawString(gltd.weaponCAS.getName(), this.centerX - 200, this.centerY + 65, (gltd.countWait == 0) ? -819986657 : -807468024);
/*     */ 
/*     */ 
/*     */     
/*  76 */     drawCommonPosition(gltd, -819986657);
/*     */     
/*  78 */     drawString(gltd.camera.getModeName(0), this.centerX + 30, this.centerY - 50, -819986657);
/*     */     
/*  80 */     drawSight(gltd.camera, -819986657);
/*     */     
/*  82 */     drawTargetPosition(gltd, -819986657, -807468024);
/*     */     
/*  84 */     drawKeyBind(gltd.camera, -805306369, -813727873);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawKeyBind(MCH_Camera camera, int color, int colorCannotUse) {
/*  90 */     int OffX = this.centerX + 55;
/*  91 */     int OffY = this.centerY + 40;
/*  92 */     drawString("DISMOUNT :", OffX, OffY + 0, color);
/*  93 */     drawString("CAM MODE :", OffX, OffY + 10, color);
/*  94 */     drawString("ZOOM IN   :", OffX, OffY + 20, (camera.getCameraZoom() < 10.0F) ? color : colorCannotUse);
/*     */     
/*  96 */     drawString("ZOOM OUT :", OffX, OffY + 30, (camera.getCameraZoom() > 1.0F) ? color : colorCannotUse);
/*     */ 
/*     */     
/*  99 */     OffX += 60;
/* 100 */     drawString(MCH_KeyName.getDescOrName(42) + " or " + MCH_KeyName.getDescOrName(MCH_Config.KeyUnmount.prmInt), OffX, OffY + 0, color);
/*     */     
/* 102 */     drawString(MCH_KeyName.getDescOrName(MCH_Config.KeyCameraMode.prmInt), OffX, OffY + 10, color);
/* 103 */     drawString(MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt), OffX, OffY + 20, (camera.getCameraZoom() < 10.0F) ? color : colorCannotUse);
/*     */     
/* 105 */     drawString(MCH_KeyName.getDescOrName(MCH_Config.KeySwWeaponMode.prmInt), OffX, OffY + 30, (camera.getCameraZoom() > 1.0F) ? color : colorCannotUse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCommonPosition(MCH_EntityGLTD gltd, int color) {
/* 112 */     int OFFSETX = 145;
/* 113 */     drawString(String.format("X: %+.1f", new Object[] { Double.valueOf(gltd.field_70165_t) }), this.centerX - 145, this.centerY + 0, color);
/* 114 */     drawString(String.format("Y: %+.1f", new Object[] { Double.valueOf(gltd.field_70163_u) }), this.centerX - 145, this.centerY + 10, color);
/* 115 */     drawString(String.format("Z: %+.1f", new Object[] { Double.valueOf(gltd.field_70161_v) }), this.centerX - 145, this.centerY + 20, color);
/* 116 */     drawString(String.format("AX: %+.1f", new Object[] { Float.valueOf(gltd.field_70153_n.field_70177_z) }), this.centerX - 145, this.centerY + 40, color);
/* 117 */     drawString(String.format("AY: %+.1f", new Object[] { Float.valueOf(gltd.field_70153_n.field_70125_A) }), this.centerX - 145, this.centerY + 50, color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTargetPosition(MCH_EntityGLTD gltd, int color, int colorDanger) {
/* 123 */     if (gltd.field_70153_n == null)
/* 124 */       return;  World w = gltd.field_70153_n.field_70170_p;
/* 125 */     float yaw = gltd.field_70153_n.field_70177_z;
/* 126 */     float pitch = gltd.field_70153_n.field_70125_A;
/* 127 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 129 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 131 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */     
/* 133 */     double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*     */     
/* 135 */     tX = tX * 80.0D / dist;
/* 136 */     tY = tY * 80.0D / dist;
/* 137 */     tZ = tZ * 80.0D / dist;
/*     */     
/* 139 */     MCH_Camera c = gltd.camera;
/*     */     
/* 141 */     Vec3 src = W_WorldFunc.getWorldVec3(w, c.posX, c.posY, c.posZ);
/* 142 */     Vec3 dst = W_WorldFunc.getWorldVec3(w, c.posX + tX, c.posY + tY, c.posZ + tZ);
/* 143 */     MovingObjectPosition m = W_WorldFunc.clip(w, src, dst);
/*     */     
/* 145 */     int OS_X = 50;
/* 146 */     if (m != null) {
/*     */       
/* 148 */       drawString(String.format("X: %+.2fm", new Object[] { Double.valueOf(m.field_72307_f.field_72450_a) }), this.centerX + 50, this.centerY - 5 - 15, color);
/* 149 */       drawString(String.format("Y: %+.2fm", new Object[] { Double.valueOf(m.field_72307_f.field_72448_b) }), this.centerX + 50, this.centerY - 5, color);
/* 150 */       drawString(String.format("Z: %+.2fm", new Object[] { Double.valueOf(m.field_72307_f.field_72449_c) }), this.centerX + 50, this.centerY - 5 + 15, color);
/*     */       
/* 152 */       double x = m.field_72307_f.field_72450_a - c.posX;
/* 153 */       double y = m.field_72307_f.field_72448_b - c.posY;
/* 154 */       double z = m.field_72307_f.field_72449_c - c.posZ;
/* 155 */       double len = Math.sqrt(x * x + y * y + z * z);
/* 156 */       drawCenteredString(String.format("[%.2fm]", new Object[] { Double.valueOf(len) }), this.centerX, this.centerY + 30, (len > 20.0D) ? color : colorDanger);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 161 */       drawString("X: --.--m", this.centerX + 50, this.centerY - 5 - 15, color);
/* 162 */       drawString("Y: --.--m", this.centerX + 50, this.centerY - 5, color);
/* 163 */       drawString("Z: --.--m", this.centerX + 50, this.centerY - 5 + 15, color);
/* 164 */       drawCenteredString("[--.--m]", this.centerX, this.centerY + 30, colorDanger);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSight(MCH_Camera camera, int color) {
/* 171 */     double posX = this.centerX;
/* 172 */     double posY = this.centerY;
/*     */     
/* 174 */     int SW = 30;
/* 175 */     int SH = 20;
/* 176 */     int SINV = 10;
/* 177 */     double[] line2 = { posX - 30.0D, posY - 10.0D, posX - 30.0D, posY - 20.0D, posX - 30.0D, posY - 20.0D, posX - 10.0D, posY - 20.0D, posX - 30.0D, posY + 10.0D, posX - 30.0D, posY + 20.0D, posX - 30.0D, posY + 20.0D, posX - 10.0D, posY + 20.0D, posX + 30.0D, posY - 10.0D, posX + 30.0D, posY - 20.0D, posX + 30.0D, posY - 20.0D, posX + 10.0D, posY - 20.0D, posX + 30.0D, posY + 10.0D, posX + 30.0D, posY + 20.0D, posX + 30.0D, posY + 20.0D, posX + 10.0D, posY + 20.0D };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     drawLine(line2, color);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_GuiGLTD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */