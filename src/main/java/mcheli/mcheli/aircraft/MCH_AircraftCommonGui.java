/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.mcheli.MCH_Config;
/*     */ import mcheli.mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.mcheli.gui.MCH_Gui;
/*     */ import mcheli.mcheli.hud.MCH_Hud;
/*     */ import mcheli.mcheli.weapon.MCH_EntityTvMissile;
/*     */ import mcheli.mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public abstract class MCH_AircraftCommonGui
/*     */   extends MCH_Gui
/*     */ {
/*     */   public MCH_AircraftCommonGui(Minecraft minecraft) {
/*  25 */     super(minecraft);
/*     */   }
/*     */ 
/*     */   public void drawHud(MCH_EntityAircraft ac, EntityPlayer player, int seatId) {
/*  30 */     MCH_AircraftInfo info = ac.getAcInfo();
/*  31 */     if (info == null)
/*     */       return; 
/*  33 */     if (ac.isMissileCameraMode((Entity)player) && ac.getTVMissile() != null && info.hudTvMissile != null) {
/*     */       
/*  35 */       info.hudTvMissile.draw(ac, player, this.smoothCamPartialTicks);
/*     */     }
/*     */     else {
/*     */       
/*  39 */       if (seatId < 0)
/*  40 */         return;  if (seatId < info.hudList.size()) {
/*     */         
/*  42 */         MCH_Hud hud = info.hudList.get(seatId);
/*  43 */         if (hud != null)
/*     */         {
/*  45 */           hud.draw(ac, player, this.smoothCamPartialTicks);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawDebugtInfo(MCH_EntityAircraft ac) {
/*  53 */     if (MCH_Config.DebugLog)
/*     */     {
/*  55 */       int LX = this.centerX - 100;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawNightVisionNoise() {
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
/*  65 */     int srcBlend = GL11.glGetInteger(3041);
/*  66 */     int dstBlend = GL11.glGetInteger(3040);
/*     */     
/*  68 */     GL11.glBlendFunc(1, 1);
/*     */     
/*  70 */     W_McClient.MOD_bindTexture("textures/gui/alpha.png");
/*  71 */     drawTexturedModalRectRotate(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, this.rand.nextInt(256), this.rand.nextInt(256), 256.0D, 256.0D, 0.0F);
/*     */     
/*  73 */     GL11.glBlendFunc(srcBlend, dstBlend);
/*  74 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawHitBullet(int hs, int hsMax, int color) {
/*  79 */     if (hs > 0) {
/*     */       
/*  81 */       int cx = this.centerX;
/*  82 */       int cy = this.centerY;
/*  83 */       int IVX = 10;
/*  84 */       int IVY = 10;
/*  85 */       int SZX = 5;
/*  86 */       int SZY = 5;
/*  87 */       double[] ls = { (cx - IVX), (cy - IVY), (cx - SZX), (cy - SZY), (cx - IVX), (cy + IVY), (cx - SZX), (cy + SZY), (cx + IVX), (cy - IVY), (cx + SZX), (cy - SZY), (cx + IVX), (cy + IVY), (cx + SZX), (cy + SZY) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       color = MCH_Config.hitMarkColorRGB;
/*  95 */       int alpha = hs * 256 / hsMax;
/*  96 */       color |= (int)(MCH_Config.hitMarkColorAlpha * alpha) << 24;
/*  97 */       drawLine(ls, color);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawHitBullet(MCH_EntityAircraft ac, int color, int seatID) {
/* 104 */     drawHitBullet(ac.getHitStatus(), ac.getMaxHitStatus(), color);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawTvMissileNoise(MCH_EntityAircraft ac, MCH_EntityTvMissile tvmissile) {
/* 109 */     GL11.glEnable(3042);
/* 110 */     GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.4F);
/* 111 */     int srcBlend = GL11.glGetInteger(3041);
/* 112 */     int dstBlend = GL11.glGetInteger(3040);
/*     */     
/* 114 */     GL11.glBlendFunc(1, 1);
/*     */     
/* 116 */     W_McClient.MOD_bindTexture("textures/gui/noise.png");
/* 117 */     drawTexturedModalRectRotate(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, this.rand.nextInt(256), this.rand.nextInt(256), 256.0D, 256.0D, 0.0F);
/*     */     
/* 119 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 120 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public void drawKeyBind(MCH_EntityAircraft ac, MCH_AircraftInfo info, EntityPlayer player, int seatID, int RX, int LX, int colorActive, int colorInactive) {
/*     */     // Byte code:
/*     */     //   0: ldc ''
/*     */     //   2: astore #9
/*     */     //   4: iconst_0
/*     */     //   5: istore #10
/*     */     //   7: iload #4
/*     */     //   9: ifne -> 69
/*     */     //   12: aload_1
/*     */     //   13: invokevirtual canPutToRack : ()Z
/*     */     //   16: ifeq -> 69
/*     */     //   19: new java/lang/StringBuilder
/*     */     //   22: dup
/*     */     //   23: invokespecial <init> : ()V
/*     */     //   26: ldc 'PutRack : '
/*     */     //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   31: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   34: pop
/*     */     //   35: getstatic mcheli/MCH_Config.KeyPutToRack : Lmcheli/MCH_ConfigPrm;
/*     */     //   38: getfield prmInt : I
/*     */     //   41: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   47: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   50: astore #9
/*     */     //   52: aload_0
/*     */     //   53: aload #9
/*     */     //   55: iload #6
/*     */     //   57: aload_0
/*     */     //   58: getfield centerY : I
/*     */     //   61: bipush #10
/*     */     //   63: isub
/*     */     //   64: iload #7
/*     */     //   66: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   69: iload #4
/*     */     //   71: ifne -> 130
/*     */     //   74: aload_1
/*     */     //   75: invokevirtual canDownFromRack : ()Z
/*     */     //   78: ifeq -> 130
/*     */     //   81: new java/lang/StringBuilder
/*     */     //   84: dup
/*     */     //   85: invokespecial <init> : ()V
/*     */     //   88: ldc 'DownRack : '
/*     */     //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   93: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   96: pop
/*     */     //   97: getstatic mcheli/MCH_Config.KeyDownFromRack : Lmcheli/MCH_ConfigPrm;
/*     */     //   100: getfield prmInt : I
/*     */     //   103: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   109: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   112: astore #9
/*     */     //   114: aload_0
/*     */     //   115: aload #9
/*     */     //   117: iload #6
/*     */     //   119: aload_0
/*     */     //   120: getfield centerY : I
/*     */     //   123: iconst_0
/*     */     //   124: isub
/*     */     //   125: iload #7
/*     */     //   127: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   130: iload #4
/*     */     //   132: ifne -> 192
/*     */     //   135: aload_1
/*     */     //   136: invokevirtual canRideRack : ()Z
/*     */     //   139: ifeq -> 192
/*     */     //   142: new java/lang/StringBuilder
/*     */     //   145: dup
/*     */     //   146: invokespecial <init> : ()V
/*     */     //   149: ldc 'RideRack : '
/*     */     //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   154: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   157: pop
/*     */     //   158: getstatic mcheli/MCH_Config.KeyPutToRack : Lmcheli/MCH_ConfigPrm;
/*     */     //   161: getfield prmInt : I
/*     */     //   164: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   170: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   173: astore #9
/*     */     //   175: aload_0
/*     */     //   176: aload #9
/*     */     //   178: iload #6
/*     */     //   180: aload_0
/*     */     //   181: getfield centerY : I
/*     */     //   184: bipush #10
/*     */     //   186: iadd
/*     */     //   187: iload #7
/*     */     //   189: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   192: iload #4
/*     */     //   194: ifne -> 254
/*     */     //   197: aload_1
/*     */     //   198: getfield field_70154_o : Lnet/minecraft/entity/Entity;
/*     */     //   201: ifnull -> 254
/*     */     //   204: new java/lang/StringBuilder
/*     */     //   207: dup
/*     */     //   208: invokespecial <init> : ()V
/*     */     //   211: ldc 'DismountRack : '
/*     */     //   213: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   216: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   219: pop
/*     */     //   220: getstatic mcheli/MCH_Config.KeyDownFromRack : Lmcheli/MCH_ConfigPrm;
/*     */     //   223: getfield prmInt : I
/*     */     //   226: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   232: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   235: astore #9
/*     */     //   237: aload_0
/*     */     //   238: aload #9
/*     */     //   240: iload #6
/*     */     //   242: aload_0
/*     */     //   243: getfield centerY : I
/*     */     //   246: bipush #10
/*     */     //   248: iadd
/*     */     //   249: iload #7
/*     */     //   251: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   254: iload #4
/*     */     //   256: ifle -> 267
/*     */     //   259: aload_1
/*     */     //   260: invokevirtual getSeatNum : ()I
/*     */     //   263: iconst_1
/*     */     //   264: if_icmpgt -> 283
/*     */     //   267: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   270: pop
/*     */     //   271: getstatic mcheli/MCH_Config.KeyFreeLook : Lmcheli/MCH_ConfigPrm;
/*     */     //   274: getfield prmInt : I
/*     */     //   277: invokestatic isKeyDown : (I)Z
/*     */     //   280: ifeq -> 454
/*     */     //   283: iload #4
/*     */     //   285: ifne -> 294
/*     */     //   288: sipush #-208
/*     */     //   291: goto -> 296
/*     */     //   294: iload #7
/*     */     //   296: istore #10
/*     */     //   298: iload #4
/*     */     //   300: ifne -> 338
/*     */     //   303: new java/lang/StringBuilder
/*     */     //   306: dup
/*     */     //   307: invokespecial <init> : ()V
/*     */     //   310: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   313: pop
/*     */     //   314: getstatic mcheli/MCH_Config.KeyFreeLook : Lmcheli/MCH_ConfigPrm;
/*     */     //   317: getfield prmInt : I
/*     */     //   320: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   326: ldc_w ' + '
/*     */     //   329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   332: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   335: goto -> 340
/*     */     //   338: ldc ''
/*     */     //   340: astore #11
/*     */     //   342: new java/lang/StringBuilder
/*     */     //   345: dup
/*     */     //   346: invokespecial <init> : ()V
/*     */     //   349: ldc_w 'NextSeat : '
/*     */     //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   355: aload #11
/*     */     //   357: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   360: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   363: pop
/*     */     //   364: getstatic mcheli/MCH_Config.KeyGUI : Lmcheli/MCH_ConfigPrm;
/*     */     //   367: getfield prmInt : I
/*     */     //   370: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   376: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   379: astore #9
/*     */     //   381: aload_0
/*     */     //   382: aload #9
/*     */     //   384: iload #5
/*     */     //   386: aload_0
/*     */     //   387: getfield centerY : I
/*     */     //   390: bipush #70
/*     */     //   392: isub
/*     */     //   393: iload #10
/*     */     //   395: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   398: new java/lang/StringBuilder
/*     */     //   401: dup
/*     */     //   402: invokespecial <init> : ()V
/*     */     //   405: ldc_w 'PrevSeat : '
/*     */     //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   411: aload #11
/*     */     //   413: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   416: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   419: pop
/*     */     //   420: getstatic mcheli/MCH_Config.KeyExtra : Lmcheli/MCH_ConfigPrm;
/*     */     //   423: getfield prmInt : I
/*     */     //   426: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   432: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   435: astore #9
/*     */     //   437: aload_0
/*     */     //   438: aload #9
/*     */     //   440: iload #5
/*     */     //   442: aload_0
/*     */     //   443: getfield centerY : I
/*     */     //   446: bipush #60
/*     */     //   448: isub
/*     */     //   449: iload #10
/*     */     //   451: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   454: iload #4
/*     */     //   456: iflt -> 539
/*     */     //   459: iload #4
/*     */     //   461: iconst_1
/*     */     //   462: if_icmpgt -> 539
/*     */     //   465: aload_1
/*     */     //   466: invokevirtual haveFlare : ()Z
/*     */     //   469: ifeq -> 539
/*     */     //   472: aload_1
/*     */     //   473: invokevirtual isFlarePreparation : ()Z
/*     */     //   476: ifeq -> 484
/*     */     //   479: iload #8
/*     */     //   481: goto -> 486
/*     */     //   484: iload #7
/*     */     //   486: istore #10
/*     */     //   488: new java/lang/StringBuilder
/*     */     //   491: dup
/*     */     //   492: invokespecial <init> : ()V
/*     */     //   495: ldc_w 'Flare : '
/*     */     //   498: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   501: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   504: pop
/*     */     //   505: getstatic mcheli/MCH_Config.KeyFlare : Lmcheli/MCH_ConfigPrm;
/*     */     //   508: getfield prmInt : I
/*     */     //   511: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   514: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   517: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   520: astore #9
/*     */     //   522: aload_0
/*     */     //   523: aload #9
/*     */     //   525: iload #5
/*     */     //   527: aload_0
/*     */     //   528: getfield centerY : I
/*     */     //   531: bipush #50
/*     */     //   533: isub
/*     */     //   534: iload #10
/*     */     //   536: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   539: iload #4
/*     */     //   541: ifne -> 670
/*     */     //   544: aload_2
/*     */     //   545: invokevirtual haveLandingGear : ()Z
/*     */     //   548: ifeq -> 670
/*     */     //   551: aload_1
/*     */     //   552: invokevirtual canFoldLandingGear : ()Z
/*     */     //   555: ifeq -> 612
/*     */     //   558: new java/lang/StringBuilder
/*     */     //   561: dup
/*     */     //   562: invokespecial <init> : ()V
/*     */     //   565: ldc_w 'Gear Up : '
/*     */     //   568: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   571: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   574: pop
/*     */     //   575: getstatic mcheli/MCH_Config.KeyGearUpDown : Lmcheli/MCH_ConfigPrm;
/*     */     //   578: getfield prmInt : I
/*     */     //   581: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   587: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   590: astore #9
/*     */     //   592: aload_0
/*     */     //   593: aload #9
/*     */     //   595: iload #5
/*     */     //   597: aload_0
/*     */     //   598: getfield centerY : I
/*     */     //   601: bipush #40
/*     */     //   603: isub
/*     */     //   604: iload #7
/*     */     //   606: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   609: goto -> 670
/*     */     //   612: aload_1
/*     */     //   613: invokevirtual canUnfoldLandingGear : ()Z
/*     */     //   616: ifeq -> 670
/*     */     //   619: new java/lang/StringBuilder
/*     */     //   622: dup
/*     */     //   623: invokespecial <init> : ()V
/*     */     //   626: ldc_w 'Gear Down : '
/*     */     //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   632: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   635: pop
/*     */     //   636: getstatic mcheli/MCH_Config.KeyGearUpDown : Lmcheli/MCH_ConfigPrm;
/*     */     //   639: getfield prmInt : I
/*     */     //   642: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   645: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   648: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   651: astore #9
/*     */     //   653: aload_0
/*     */     //   654: aload #9
/*     */     //   656: iload #5
/*     */     //   658: aload_0
/*     */     //   659: getfield centerY : I
/*     */     //   662: bipush #40
/*     */     //   664: isub
/*     */     //   665: iload #7
/*     */     //   667: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   670: aload_1
/*     */     //   671: aload_3
/*     */     //   672: invokevirtual getCurrentWeapon : (Lnet/minecraft/entity/Entity;)Lmcheli/weapon/MCH_WeaponSet;
/*     */     //   675: astore #11
/*     */     //   677: aload_1
/*     */     //   678: invokevirtual getWeaponNum : ()I
/*     */     //   681: iconst_1
/*     */     //   682: if_icmple -> 736
/*     */     //   685: new java/lang/StringBuilder
/*     */     //   688: dup
/*     */     //   689: invokespecial <init> : ()V
/*     */     //   692: ldc_w 'Weapon : '
/*     */     //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   698: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   701: pop
/*     */     //   702: getstatic mcheli/MCH_Config.KeySwitchWeapon2 : Lmcheli/MCH_ConfigPrm;
/*     */     //   705: getfield prmInt : I
/*     */     //   708: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   711: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   714: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   717: astore #9
/*     */     //   719: aload_0
/*     */     //   720: aload #9
/*     */     //   722: iload #6
/*     */     //   724: aload_0
/*     */     //   725: getfield centerY : I
/*     */     //   728: bipush #70
/*     */     //   730: isub
/*     */     //   731: iload #7
/*     */     //   733: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   736: aload #11
/*     */     //   738: invokevirtual getCurrentWeapon : ()Lmcheli/weapon/MCH_WeaponBase;
/*     */     //   741: getfield numMode : I
/*     */     //   744: ifle -> 798
/*     */     //   747: new java/lang/StringBuilder
/*     */     //   750: dup
/*     */     //   751: invokespecial <init> : ()V
/*     */     //   754: ldc_w 'WeaponMode : '
/*     */     //   757: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   760: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   763: pop
/*     */     //   764: getstatic mcheli/MCH_Config.KeySwWeaponMode : Lmcheli/MCH_ConfigPrm;
/*     */     //   767: getfield prmInt : I
/*     */     //   770: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   773: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   776: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   779: astore #9
/*     */     //   781: aload_0
/*     */     //   782: aload #9
/*     */     //   784: iload #6
/*     */     //   786: aload_0
/*     */     //   787: getfield centerY : I
/*     */     //   790: bipush #60
/*     */     //   792: isub
/*     */     //   793: iload #7
/*     */     //   795: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   798: aload_1
/*     */     //   799: aload_3
/*     */     //   800: invokevirtual canSwitchSearchLight : (Lnet/minecraft/entity/Entity;)Z
/*     */     //   803: ifeq -> 860
/*     */     //   806: new java/lang/StringBuilder
/*     */     //   809: dup
/*     */     //   810: invokespecial <init> : ()V
/*     */     //   813: ldc_w 'SearchLight : '
/*     */     //   816: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   819: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   822: pop
/*     */     //   823: getstatic mcheli/MCH_Config.KeyCameraMode : Lmcheli/MCH_ConfigPrm;
/*     */     //   826: getfield prmInt : I
/*     */     //   829: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   832: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   835: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   838: astore #9
/*     */     //   840: aload_0
/*     */     //   841: aload #9
/*     */     //   843: iload #6
/*     */     //   845: aload_0
/*     */     //   846: getfield centerY : I
/*     */     //   849: bipush #50
/*     */     //   851: isub
/*     */     //   852: iload #7
/*     */     //   854: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   857: goto -> 920
/*     */     //   860: aload_1
/*     */     //   861: iload #4
/*     */     //   863: invokevirtual canSwitchCameraMode : (I)Z
/*     */     //   866: ifeq -> 920
/*     */     //   869: new java/lang/StringBuilder
/*     */     //   872: dup
/*     */     //   873: invokespecial <init> : ()V
/*     */     //   876: ldc_w 'CameraMode : '
/*     */     //   879: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   882: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   885: pop
/*     */     //   886: getstatic mcheli/MCH_Config.KeyCameraMode : Lmcheli/MCH_ConfigPrm;
/*     */     //   889: getfield prmInt : I
/*     */     //   892: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   895: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   898: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   901: astore #9
/*     */     //   903: aload_0
/*     */     //   904: aload #9
/*     */     //   906: iload #6
/*     */     //   908: aload_0
/*     */     //   909: getfield centerY : I
/*     */     //   912: bipush #50
/*     */     //   914: isub
/*     */     //   915: iload #7
/*     */     //   917: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   920: iload #4
/*     */     //   922: ifne -> 1091
/*     */     //   925: aload_1
/*     */     //   926: invokevirtual getSeatNum : ()I
/*     */     //   929: iconst_1
/*     */     //   930: if_icmplt -> 1091
/*     */     //   933: iload #7
/*     */     //   935: istore #12
/*     */     //   937: aload_2
/*     */     //   938: getfield isEnableParachuting : Z
/*     */     //   941: ifeq -> 991
/*     */     //   944: aload_1
/*     */     //   945: iconst_3
/*     */     //   946: bipush #-10
/*     */     //   948: invokestatic getBlockIdY : (Lnet/minecraft/entity/Entity;II)I
/*     */     //   951: ifne -> 991
/*     */     //   954: new java/lang/StringBuilder
/*     */     //   957: dup
/*     */     //   958: invokespecial <init> : ()V
/*     */     //   961: ldc_w 'Parachuting : '
/*     */     //   964: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   967: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   970: pop
/*     */     //   971: getstatic mcheli/MCH_Config.KeyUnmount : Lmcheli/MCH_ConfigPrm;
/*     */     //   974: getfield prmInt : I
/*     */     //   977: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   980: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   983: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   986: astore #9
/*     */     //   988: goto -> 1074
/*     */     //   991: aload_1
/*     */     //   992: invokevirtual canStartRepelling : ()Z
/*     */     //   995: ifeq -> 1040
/*     */     //   998: new java/lang/StringBuilder
/*     */     //   1001: dup
/*     */     //   1002: invokespecial <init> : ()V
/*     */     //   1005: ldc_w 'Repelling : '
/*     */     //   1008: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1011: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   1014: pop
/*     */     //   1015: getstatic mcheli/MCH_Config.KeyUnmount : Lmcheli/MCH_ConfigPrm;
/*     */     //   1018: getfield prmInt : I
/*     */     //   1021: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   1024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1027: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1030: astore #9
/*     */     //   1032: sipush #-256
/*     */     //   1035: istore #12
/*     */     //   1037: goto -> 1074
/*     */     //   1040: new java/lang/StringBuilder
/*     */     //   1043: dup
/*     */     //   1044: invokespecial <init> : ()V
/*     */     //   1047: ldc_w 'Dismount : '
/*     */     //   1050: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1053: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   1056: pop
/*     */     //   1057: getstatic mcheli/MCH_Config.KeyUnmount : Lmcheli/MCH_ConfigPrm;
/*     */     //   1060: getfield prmInt : I
/*     */     //   1063: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   1066: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1069: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1072: astore #9
/*     */     //   1074: aload_0
/*     */     //   1075: aload #9
/*     */     //   1077: iload #6
/*     */     //   1079: aload_0
/*     */     //   1080: getfield centerY : I
/*     */     //   1083: bipush #30
/*     */     //   1085: isub
/*     */     //   1086: iload #12
/*     */     //   1088: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   1091: iload #4
/*     */     //   1093: ifne -> 1103
/*     */     //   1096: aload_1
/*     */     //   1097: invokevirtual canSwitchFreeLook : ()Z
/*     */     //   1100: ifne -> 1116
/*     */     //   1103: iload #4
/*     */     //   1105: ifle -> 1167
/*     */     //   1108: aload_1
/*     */     //   1109: aload_3
/*     */     //   1110: invokevirtual canSwitchGunnerModeOtherSeat : (Lnet/minecraft/entity/player/EntityPlayer;)Z
/*     */     //   1113: ifeq -> 1167
/*     */     //   1116: new java/lang/StringBuilder
/*     */     //   1119: dup
/*     */     //   1120: invokespecial <init> : ()V
/*     */     //   1123: ldc_w 'FreeLook : '
/*     */     //   1126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1129: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
/*     */     //   1132: pop
/*     */     //   1133: getstatic mcheli/MCH_Config.KeyFreeLook : Lmcheli/MCH_ConfigPrm;
/*     */     //   1136: getfield prmInt : I
/*     */     //   1139: invokestatic getDescOrName : (I)Ljava/lang/String;
/*     */     //   1142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1145: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1148: astore #9
/*     */     //   1150: aload_0
/*     */     //   1151: aload #9
/*     */     //   1153: iload #6
/*     */     //   1155: aload_0
/*     */     //   1156: getfield centerY : I
/*     */     //   1159: bipush #20
/*     */     //   1161: isub
/*     */     //   1162: iload #7
/*     */     //   1164: invokevirtual drawString : (Ljava/lang/String;III)V
/*     */     //   1167: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #126	-> 0
/*     */     //   #127	-> 4
/*     */     //   #129	-> 7
/*     */     //   #131	-> 19
/*     */     //   #132	-> 52
/*     */     //   #134	-> 69
/*     */     //   #136	-> 81
/*     */     //   #137	-> 114
/*     */     //   #139	-> 130
/*     */     //   #141	-> 142
/*     */     //   #142	-> 175
/*     */     //   #144	-> 192
/*     */     //   #146	-> 204
/*     */     //   #147	-> 237
/*     */     //   #151	-> 254
/*     */     //   #153	-> 283
/*     */     //   #154	-> 298
/*     */     //   #155	-> 342
/*     */     //   #156	-> 381
/*     */     //   #157	-> 398
/*     */     //   #158	-> 437
/*     */     //   #162	-> 454
/*     */     //   #164	-> 472
/*     */     //   #165	-> 488
/*     */     //   #166	-> 522
/*     */     //   #170	-> 539
/*     */     //   #172	-> 551
/*     */     //   #174	-> 558
/*     */     //   #175	-> 592
/*     */     //   #177	-> 612
/*     */     //   #179	-> 619
/*     */     //   #180	-> 653
/*     */     //   #185	-> 670
/*     */     //   #186	-> 677
/*     */     //   #188	-> 685
/*     */     //   #189	-> 719
/*     */     //   #191	-> 736
/*     */     //   #193	-> 747
/*     */     //   #194	-> 781
/*     */     //   #198	-> 798
/*     */     //   #200	-> 806
/*     */     //   #201	-> 840
/*     */     //   #206	-> 860
/*     */     //   #208	-> 869
/*     */     //   #209	-> 903
/*     */     //   #214	-> 920
/*     */     //   #216	-> 933
/*     */     //   #217	-> 937
/*     */     //   #219	-> 954
/*     */     //   #221	-> 991
/*     */     //   #223	-> 998
/*     */     //   #224	-> 1032
/*     */     //   #228	-> 1040
/*     */     //   #230	-> 1074
/*     */     //   #234	-> 1091
/*     */     //   #236	-> 1116
/*     */     //   #237	-> 1150
/*     */     //   #239	-> 1167
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   342	112	11	sk	Ljava/lang/String;
/*     */     //   937	154	12	color	I
/*     */     //   0	1168	0	this	Lmcheli/aircraft/MCH_AircraftCommonGui;
/*     */     //   0	1168	1	ac	Lmcheli/aircraft/MCH_EntityAircraft;
/*     */     //   0	1168	2	info	Lmcheli/aircraft/MCH_AircraftInfo;
/*     */     //   0	1168	3	player	Lnet/minecraft/entity/player/EntityPlayer;
/*     */     //   0	1168	4	seatID	I
/*     */     //   0	1168	5	RX	I
/*     */     //   0	1168	6	LX	I
/*     */     //   0	1168	7	colorActive	I
/*     */     //   0	1168	8	colorInactive	I
/*     */     //   4	1164	9	msg	Ljava/lang/String;
/*     */     //   7	1161	10	c	I
/*     */     //   677	491	11	ws	Lmcheli/weapon/MCH_WeaponSet;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftCommonGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
