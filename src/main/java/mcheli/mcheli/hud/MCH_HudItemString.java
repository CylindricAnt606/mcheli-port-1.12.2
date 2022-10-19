/*     */ package mcheli.mcheli.hud;
/*     */ 
/*     */ import java.util.Date;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.hud.MCH_HudItem;
/*     */ import mcheli.hud.MCH_HudItemStringArgs;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class MCH_HudItemString extends MCH_HudItem {
/*     */   private final String posX;
/*     */   private final String posY;
/*     */   private final String format;
/*     */   private final MCH_HudItemStringArgs[] args;
/*     */   private final boolean isCenteredString;
/*     */   
/*     */   public MCH_HudItemString(int fileLine, String posx, String posy, String fmt, String[] arg, boolean centered) {
/*  21 */     super(fileLine);
/*  22 */     this.posX = posx.toLowerCase();
/*  23 */     this.posY = posy.toLowerCase();
/*  24 */     this.format = fmt;
/*  25 */     int len = (arg.length < 3) ? 0 : (arg.length - 3);
/*  26 */     this.args = new MCH_HudItemStringArgs[len];
/*  27 */     for (int i = 0; i < len; i++)
/*     */     {
/*  29 */       this.args[i] = MCH_HudItemStringArgs.toArgs(arg[3 + i]);
/*     */     }
/*  31 */     this.isCenteredString = centered;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/*  37 */     int x = (int)(centerX + calc(this.posX));
/*  38 */     int y = (int)(centerY + calc(this.posY));
/*     */     
/*  40 */     long dateCount = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_82737_E();
/*     */     
/*  42 */     int worldTime = (int)((ac.field_70170_p.func_72820_D() + 6000L) % 24000L);
/*  43 */     Date date = new Date();
/*  44 */     Object[] prm = new Object[this.args.length];
/*  45 */     double hp_per = (ac.getMaxHP() > 0) ? (ac.getHP() / ac.getMaxHP()) : 0.0D;
/*     */     
/*  47 */     for (int i = 0; i < prm.length; i++) {
/*     */       
/*  49 */       switch (null.$SwitchMap$mcheli$hud$MCH_HudItemStringArgs[this.args[i].ordinal()]) {
/*     */         case 1:
/*  51 */           prm[i] = (ac.getAcInfo()).displayName;
/*     */           break;
/*     */         case 2:
/*  54 */           prm[i] = Integer.valueOf(Altitude);
/*     */           break;
/*     */         case 3:
/*  57 */           prm[i] = date; break;
/*  58 */         case 4: prm[i] = Integer.valueOf(worldTime / 1000); break;
/*  59 */         case 5: prm[i] = Integer.valueOf(worldTime % 1000 * 36 / 10 / 60); break;
/*  60 */         case 6: prm[i] = Integer.valueOf(worldTime % 1000 * 36 / 10 % 60); break;
/*     */         case 7:
/*  62 */           prm[i] = Integer.valueOf(ac.getMaxHP()); break;
/*  63 */         case 8: prm[i] = Integer.valueOf(ac.getHP()); break;
/*  64 */         case 9: prm[i] = Double.valueOf(hp_per * 100.0D); break;
/*     */         case 10:
/*  66 */           prm[i] = Double.valueOf(ac.field_70165_t); break;
/*  67 */         case 11: prm[i] = Double.valueOf(ac.field_70163_u); break;
/*  68 */         case 12: prm[i] = Double.valueOf(ac.field_70161_v); break;
/*  69 */         case 13: prm[i] = Double.valueOf(ac.field_70159_w); break;
/*  70 */         case 14: prm[i] = Double.valueOf(ac.field_70181_x); break;
/*  71 */         case 15: prm[i] = Double.valueOf(ac.field_70179_y); break;
/*     */         case 16:
/*  73 */           prm[i] = Integer.valueOf(ac.func_70302_i_());
/*     */           break;
/*     */         case 17:
/*  76 */           prm[i] = WeaponName;
/*  77 */           if (CurrentWeapon == null)
/*     */             return;  break;
/*     */         case 18:
/*  80 */           prm[i] = WeaponAmmo;
/*  81 */           if (CurrentWeapon == null)
/*  82 */             return;  if (CurrentWeapon.getAmmoNumMax() <= 0)
/*     */             return;  break;
/*     */         case 19:
/*  85 */           prm[i] = WeaponAllAmmo;
/*  86 */           if (CurrentWeapon == null)
/*  87 */             return;  if (CurrentWeapon.getAmmoNumMax() <= 0)
/*     */             return; 
/*     */           break;
/*     */         case 20:
/*  91 */           prm[i] = Float.valueOf(ReloadPer);
/*  92 */           if (CurrentWeapon == null)
/*     */             return; 
/*     */           break;
/*     */         case 21:
/*  96 */           prm[i] = Float.valueOf(ReloadSec);
/*  97 */           if (CurrentWeapon == null)
/*     */             return; 
/*     */           break;
/*     */         case 22:
/* 101 */           prm[i] = Float.valueOf(MortarDist);
/* 102 */           if (CurrentWeapon == null)
/*     */             return;  break;
/*     */         case 23:
/* 105 */           prm[i] = "1.7.10"; break;
/* 106 */         case 24: prm[i] = MCH_MOD.VER; break;
/* 107 */         case 25: prm[i] = "MC Helicopter MOD"; break;
/*     */         case 26:
/* 109 */           prm[i] = Double.valueOf(MCH_Lib.getRotate360((ac.getRotYaw() + 180.0F))); break;
/* 110 */         case 27: prm[i] = Float.valueOf(-ac.getRotPitch()); break;
/* 111 */         case 28: prm[i] = Float.valueOf(MathHelper.func_76142_g(ac.getRotRoll())); break;
/*     */         case 29:
/* 113 */           prm[i] = Double.valueOf(MCH_Lib.getRotate360((player.field_70177_z + 180.0F))); break;
/* 114 */         case 30: prm[i] = Float.valueOf(-player.field_70125_A); break;
/*     */         case 31:
/* 116 */           prm[i] = Double.valueOf(TVM_PosX); break;
/* 117 */         case 32: prm[i] = Double.valueOf(TVM_PosY); break;
/* 118 */         case 33: prm[i] = Double.valueOf(TVM_PosZ); break;
/* 119 */         case 34: prm[i] = Double.valueOf(TVM_Diff); break;
/*     */         case 35:
/* 121 */           prm[i] = Float.valueOf(ac.camera.getCameraZoom()); break;
/*     */         case 36:
/* 123 */           prm[i] = Double.valueOf(UAV_Dist); break;
/*     */         case 37:
/* 125 */           prm[i] = MCH_KeyName.getDescOrName(MCH_Config.KeyGUI.prmInt); break;
/*     */         case 38:
/* 127 */           prm[i] = Double.valueOf(ac.getCurrentThrottle() * 100.0D);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 135 */     if (this.isCenteredString) {
/*     */       
/* 137 */       drawCenteredString(String.format(this.format, prm), x, y, colorSetting);
/*     */     }
/*     */     else {
/*     */       
/* 141 */       drawString(String.format(this.format, prm), x, y, colorSetting);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItemString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */