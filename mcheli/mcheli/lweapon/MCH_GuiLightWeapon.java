/*     */ package mcheli.mcheli.lweapon;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.gui.MCH_Gui;
/*     */ import mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponBase;
/*     */ import mcheli.weapon.MCH_WeaponGuidanceSystem;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiLightWeapon
/*     */   extends MCH_Gui
/*     */ {
/*     */   public MCH_GuiLightWeapon(Minecraft minecraft) {
/*  32 */     super(minecraft);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  38 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  49 */     if (MCH_ItemLightWeaponBase.isHeld(player)) {
/*     */       
/*  51 */       Entity re = player.field_70154_o;
/*  52 */       if (!(re instanceof mcheli.aircraft.MCH_EntityAircraft) && !(re instanceof mcheli.gltd.MCH_EntityGLTD))
/*     */       {
/*     */         
/*  55 */         return true;
/*     */       }
/*     */     } 
/*  58 */     return false;
/*     */   }
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*     */     int srcBlend, dstBlend;
/*     */     boolean canFire;
/*  63 */     if (isThirdPersonView)
/*     */       return; 
/*  65 */     this; GL11.glLineWidth(scaleFactor);
/*     */     
/*  67 */     if (!isDrawGui(player))
/*  68 */       return;  MCH_ItemLightWeaponBase item = (MCH_ItemLightWeaponBase)player.func_70694_bm().func_77973_b();
/*     */     
/*  70 */     MCH_WeaponGuidanceSystem gs = MCH_ClientLightWeaponTickHandler.gs;
/*     */     
/*  72 */     if (gs != null && MCH_ClientLightWeaponTickHandler.weapon != null && MCH_ClientLightWeaponTickHandler.weapon.getInfo() != null)
/*     */     
/*     */     { 
/*     */       
/*  76 */       PotionEffect pe = player.func_70660_b(Potion.field_76439_r);
/*  77 */       if (pe != null)
/*     */       {
/*  79 */         drawNightVisionNoise();
/*     */       }
/*     */       
/*  82 */       GL11.glEnable(3042);
/*  83 */       GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
/*  84 */       srcBlend = GL11.glGetInteger(3041);
/*  85 */       dstBlend = GL11.glGetInteger(3040);
/*  86 */       GL11.glBlendFunc(770, 771);
/*     */       
/*  88 */       double dist = 0.0D;
/*  89 */       if (gs.getTargetEntity() != null) {
/*     */         
/*  91 */         double dx = (gs.getTargetEntity()).field_70165_t - player.field_70165_t;
/*  92 */         double dz = (gs.getTargetEntity()).field_70161_v - player.field_70161_v;
/*  93 */         dist = Math.sqrt(dx * dx + dz * dz);
/*     */       } 
/*     */       
/*  96 */       canFire = (MCH_ClientLightWeaponTickHandler.weaponMode == 0 || dist >= 40.0D || gs.getLockCount() <= 0);
/*     */       
/*  98 */       if ("fgm148".equalsIgnoreCase(MCH_ItemLightWeaponBase.getName(player.func_70694_bm())))
/*     */       
/* 100 */       { drawGuiFGM148(player, gs, canFire, player.func_70694_bm());
/* 101 */         drawKeyBind(-805306369, true); }
/*     */       
/*     */       else
/*     */       
/* 105 */       { GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 107 */         W_McClient.MOD_bindTexture("textures/gui/stinger.png");
/* 108 */         double size = 512.0D; while (true)
/* 109 */         { if (size < this.field_146294_l || size < this.field_146295_m) { size *= 2.0D; continue; }
/* 110 */            drawTexturedModalRectRotate(-(size - this.field_146294_l) / 2.0D, -(size - this.field_146295_m) / 2.0D - 20.0D, size, size, 0.0D, 0.0D, 256.0D, 256.0D, 0.0F);
/*     */           
/* 112 */           drawKeyBind(-805306369, false);
/*     */ 
/*     */           
/* 115 */           GL11.glBlendFunc(srcBlend, dstBlend);
/* 116 */           GL11.glDisable(3042);
/*     */           
/* 118 */           drawLock(-14101432, -2161656, gs.getLockCount(), gs.getLockCountMax());
/* 119 */           drawRange(player, gs, canFire, -14101432, -2161656); break; }  return; }  } else { return; }  GL11.glBlendFunc(srcBlend, dstBlend); GL11.glDisable(3042); drawLock(-14101432, -2161656, gs.getLockCount(), gs.getLockCountMax()); drawRange(player, gs, canFire, -14101432, -2161656);
/*     */     break;
/*     */   }
/*     */   
/*     */   public void drawNightVisionNoise() {
/* 124 */     GL11.glEnable(3042);
/* 125 */     GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
/* 126 */     int srcBlend = GL11.glGetInteger(3041);
/* 127 */     int dstBlend = GL11.glGetInteger(3040);
/*     */     
/* 129 */     GL11.glBlendFunc(1, 1);
/*     */     
/* 131 */     W_McClient.MOD_bindTexture("textures/gui/alpha.png");
/* 132 */     drawTexturedModalRectRotate(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, this.rand.nextInt(256), this.rand.nextInt(256), 256.0D, 256.0D, 0.0F);
/*     */     
/* 134 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 135 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   void drawLock(int color, int colorLock, int cntLock, int cntMax) {
/* 140 */     int posX = this.centerX;
/* 141 */     int posY = this.centerY + 20;
/*     */     
/* 143 */     int WID = 20;
/* 144 */     int INV = 10;
/*     */     
/* 146 */     double[] line = { (posX - 20), (posY - 10), (posX - 20), (posY - 20), (posX - 20), (posY - 20), (posX - 10), (posY - 20), (posX - 20), (posY + 10), (posX - 20), (posY + 20), (posX - 20), (posY + 20), (posX - 10), (posY + 20), (posX + 20), (posY - 10), (posX + 20), (posY - 20), (posX + 20), (posY - 20), (posX + 10), (posY - 20), (posX + 20), (posY + 10), (posX + 20), (posY + 20), (posX + 20), (posY + 20), (posX + 10), (posY + 20) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     func_73734_a(posX - 20, posY + 20 + 1, posX - 20 + 40, posY + 20 + 1 + 1 + 3 + 1, color);
/*     */ 
/*     */     
/* 156 */     float lock = cntLock / cntMax;
/* 157 */     func_73734_a(posX - 20 + 1, posY + 20 + 1 + 1, posX - 20 + 1 + (int)(38.0D * lock), posY + 20 + 1 + 1 + 3, -2161656);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void drawRange(EntityPlayer player, MCH_WeaponGuidanceSystem gs, boolean canFire, int color1, int color2) {
/* 163 */     String msgLockDist = "[--.--]";
/* 164 */     int color = color2;
/* 165 */     if (gs.getLockCount() > 0) {
/*     */       
/* 167 */       Entity target = gs.getLockingEntity();
/* 168 */       if (target != null) {
/*     */         
/* 170 */         double dx = target.field_70165_t - player.field_70165_t;
/* 171 */         double dz = target.field_70161_v - player.field_70161_v;
/* 172 */         msgLockDist = String.format("[%.2f]", new Object[] { Double.valueOf(Math.sqrt(dx * dx + dz * dz)) });
/* 173 */         color = canFire ? color1 : color2;
/*     */         
/* 175 */         if (!MCH_Config.HideKeybind.prmBool)
/*     */         {
/* 177 */           if (gs.isLockComplete()) {
/*     */             
/* 179 */             String k = MCH_KeyName.getDescOrName(MCH_Config.KeyAttack.prmInt);
/* 180 */             drawCenteredString("Shot : " + k, this.centerX, this.centerY + 65, -805306369);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     drawCenteredString(msgLockDist, this.centerX, this.centerY + 50, color);
/*     */   }
/*     */ 
/*     */   
/*     */   void drawGuiFGM148(EntityPlayer player, MCH_WeaponGuidanceSystem gs, boolean canFire, ItemStack itemStack) {
/* 191 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 193 */     double fac = (this.field_146294_l / 800.0D < this.field_146295_m / 700.0D) ? (this.field_146294_l / 800.0D) : (this.field_146295_m / 700.0D);
/*     */     
/* 195 */     int size = (int)(1024.0D * fac);
/* 196 */     size = size / 64 * 64;
/* 197 */     fac = size / 1024.0D;
/* 198 */     double left = (-(size - this.field_146294_l) / 2);
/* 199 */     double top = (-(size - this.field_146295_m) / 2 - 20);
/* 200 */     double right = left + size;
/* 201 */     double bottom = top + size;
/*     */     
/* 203 */     Vec3 pos = MCH_ClientLightWeaponTickHandler.getMartEntityPos();
/* 204 */     if (gs.getLockCount() > 0) {
/*     */       
/* 206 */       int scale = (scaleFactor > 0) ? scaleFactor : 2;
/*     */       
/* 208 */       if (pos == null)
/*     */       {
/* 210 */         pos = Vec3.func_72443_a((this.field_146294_l / 2 * scale), (this.field_146295_m / 2 * scale), 0.0D);
/*     */       }
/*     */       
/* 213 */       double IX = 280.0D * fac;
/* 214 */       double IY = 370.0D * fac;
/* 215 */       double cx = pos.field_72450_a / scale;
/* 216 */       double cy = this.field_146295_m - pos.field_72448_b / scale;
/* 217 */       double sx = MCH_Lib.RNG(cx, left + IX, right - IX);
/* 218 */       double sy = MCH_Lib.RNG(cy, top + IY, bottom - IY);
/*     */       
/* 220 */       if (gs.getLockCount() >= gs.getLockCountMax() / 2)
/*     */       {
/* 222 */         drawLine(new double[] { -1.0D, sy, (this.field_146294_l + 1), sy, sx, -1.0D, sx, (this.field_146295_m + 1) }, -1593835521);
/*     */       }
/*     */       
/* 225 */       if (player.field_70173_aa % 6 >= 3) {
/*     */         
/* 227 */         pos = MCH_ClientLightWeaponTickHandler.getMartEntityBBPos();
/* 228 */         if (pos == null)
/*     */         {
/* 230 */           pos = Vec3.func_72443_a(((this.field_146294_l / 2 - 65) * scale), ((this.field_146295_m / 2 + 50) * scale), 0.0D);
/*     */         }
/* 232 */         double bx = pos.field_72450_a / scale;
/* 233 */         double by = this.field_146295_m - pos.field_72448_b / scale;
/* 234 */         double dx = Math.abs(cx - bx);
/* 235 */         double dy = Math.abs(cy - by);
/* 236 */         double p = 1.0D - gs.getLockCount() / gs.getLockCountMax();
/* 237 */         dx = MCH_Lib.RNG(dx, 25.0D, 70.0D);
/* 238 */         dy = MCH_Lib.RNG(dy, 15.0D, 70.0D);
/*     */         
/* 240 */         dx += (70.0D - dx) * p;
/* 241 */         dy += (70.0D - dy) * p;
/*     */         
/* 243 */         int lx = 10;
/* 244 */         int ly = 6;
/* 245 */         drawLine(new double[] { sx - dx, sy - dy + ly, sx - dx, sy - dy, sx - dx + lx, sy - dy }, -1593835521, 3);
/* 246 */         drawLine(new double[] { sx + dx, sy - dy + ly, sx + dx, sy - dy, sx + dx - lx, sy - dy }, -1593835521, 3);
/* 247 */         dy /= 6.0D;
/* 248 */         drawLine(new double[] { sx - dx, sy + dy - ly, sx - dx, sy + dy, sx - dx + lx, sy + dy }, -1593835521, 3);
/* 249 */         drawLine(new double[] { sx + dx, sy + dy - ly, sx + dx, sy + dy, sx + dx - lx, sy + dy }, -1593835521, 3);
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     func_73734_a(-1, -1, (int)left + 1, this.field_146295_m + 1, -16777216);
/* 254 */     func_73734_a((int)right - 1, -1, this.field_146294_l + 1, this.field_146295_m + 1, -16777216);
/* 255 */     func_73734_a(-1, -1, this.field_146294_l + 1, (int)top + 1, -16777216);
/* 256 */     func_73734_a(-1, (int)bottom - 1, this.field_146294_l + 1, this.field_146295_m + 1, -16777216);
/*     */     
/* 258 */     GL11.glEnable(3042);
/* 259 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 260 */     W_McClient.MOD_bindTexture("textures/gui/javelin.png");
/* 261 */     drawTexturedModalRectRotate(left, top, size, size, 0.0D, 0.0D, 256.0D, 256.0D, 0.0F);
/*     */     
/* 263 */     W_McClient.MOD_bindTexture("textures/gui/javelin2.png");
/*     */ 
/*     */     
/* 266 */     PotionEffect pe = player.func_70660_b(Potion.field_76439_r);
/*     */     
/* 268 */     if (pe == null) {
/*     */       
/* 270 */       double x = 247.0D, y = 211.0D, w = 380.0D, h = 350.0D;
/* 271 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 274 */     if (player.func_71057_bx() <= 60) {
/*     */       
/* 276 */       double x = 130.0D, y = 334.0D, w = 257.0D, h = 455.0D;
/* 277 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 280 */     if (MCH_ClientLightWeaponTickHandler.selectedZoom == 0) {
/*     */       
/* 282 */       double x = 387.0D, y = 211.0D, w = 510.0D, h = 350.0D;
/* 283 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/* 285 */     if (MCH_ClientLightWeaponTickHandler.selectedZoom == (MCH_ClientLightWeaponTickHandler.weapon.getInfo()).zoom.length - 1) {
/*     */       
/* 287 */       double x = 511.0D, y = 211.0D, w = 645.0D, h = 350.0D;
/* 288 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 291 */     if (gs.getLockCount() > 0) {
/*     */       
/* 293 */       double x = 643.0D, y = 211.0D, w = 775.0D, h = 350.0D;
/* 294 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 297 */     if (MCH_ClientLightWeaponTickHandler.weaponMode == 1) {
/*     */       
/* 299 */       double x = 768.0D, y = 340.0D, w = 890.0D, h = 455.0D;
/* 300 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     }
/*     */     else {
/*     */       
/* 304 */       double x = 768.0D, y = 456.0D, w = 890.0D, h = 565.0D;
/* 305 */       drawTexturedRect(left + x * fac, top + y * fac, (w - x) * fac, (h - y) * fac, x, y, w - x, h - y, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 308 */     if (!canFire) {
/*     */       
/* 310 */       double d1 = 379.0D, d2 = 670.0D, d3 = 511.0D, d4 = 810.0D;
/* 311 */       drawTexturedRect(left + d1 * fac, top + d2 * fac, (d3 - d1) * fac, (d4 - d2) * fac, d1, d2, d3 - d1, d4 - d2, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 314 */     if (itemStack.func_77960_j() >= itemStack.func_77958_k()) {
/*     */       
/* 316 */       double d1 = 512.0D, d2 = 670.0D, d3 = 645.0D, d4 = 810.0D;
/* 317 */       drawTexturedRect(left + d1 * fac, top + d2 * fac, (d3 - d1) * fac, (d4 - d2) * fac, d1, d2, d3 - d1, d4 - d2, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 320 */     if (gs.getLockCount() < gs.getLockCountMax()) {
/*     */       
/* 322 */       double d1 = 646.0D, d2 = 670.0D, d3 = 776.0D, d4 = 810.0D;
/* 323 */       drawTexturedRect(left + d1 * fac, top + d2 * fac, (d3 - d1) * fac, (d4 - d2) * fac, d1, d2, d3 - d1, d4 - d2, 1024.0D, 1024.0D);
/*     */     } 
/*     */     
/* 326 */     if (pe != null) {
/*     */       
/* 328 */       double d1 = 768.0D, d2 = 562.0D, d3 = 890.0D, d4 = 694.0D;
/* 329 */       drawTexturedRect(left + d1 * fac, top + d2 * fac, (d3 - d1) * fac, (d4 - d2) * fac, d1, d2, d3 - d1, d4 - d2, 1024.0D, 1024.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawKeyBind(int color, boolean canSwitchMode) {
/* 336 */     int OffX = this.centerX + 55;
/* 337 */     int OffY = this.centerY + 40;
/* 338 */     drawString("CAM MODE :", OffX, OffY + 10, color);
/* 339 */     drawString("ZOOM      :", OffX, OffY + 20, color);
/* 340 */     if (canSwitchMode)
/*     */     {
/* 342 */       drawString("MODE      :", OffX, OffY + 30, color);
/*     */     }
/* 344 */     OffX += 60;
/*     */     
/* 346 */     drawString(MCH_KeyName.getDescOrName(MCH_Config.KeyCameraMode.prmInt), OffX, OffY + 10, color);
/* 347 */     drawString(MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt), OffX, OffY + 20, color);
/* 348 */     if (canSwitchMode)
/*     */     {
/* 350 */       drawString(MCH_KeyName.getDescOrName(MCH_Config.KeySwWeaponMode.prmInt), OffX, OffY + 30, color);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_GuiLightWeapon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */