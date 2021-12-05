/*     */ package mcheli.mcheli.hud;
/*     */ import java.util.ArrayList;
/*     */ import mcheli.MCH_BaseInfo;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.hud.MCH_HudItem;
/*     */ import mcheli.hud.MCH_HudItemColor;
/*     */ import mcheli.hud.MCH_HudItemConditional;
/*     */ import mcheli.hud.MCH_HudItemExit;
/*     */ import mcheli.hud.MCH_HudItemRect;
/*     */ import mcheli.hud.MCH_HudItemString;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_Hud extends MCH_BaseInfo {
/*  16 */   public static final mcheli.hud.MCH_Hud NoDisp = new mcheli.hud.MCH_Hud("none", "none");
/*     */   
/*     */   public final String name;
/*     */   
/*     */   public final String fileName;
/*     */   
/*     */   private List<MCH_HudItem> list;
/*     */   
/*     */   public boolean isWaitEndif;
/*     */   
/*     */   private boolean isDrawing;
/*     */   
/*     */   public boolean isIfFalse;
/*     */   public boolean exit;
/*     */   
/*     */   public MCH_Hud(String name, String fname) {
/*  32 */     this.name = name;
/*  33 */     this.fileName = fname;
/*  34 */     this.list = new ArrayList<MCH_HudItem>();
/*  35 */     this.isDrawing = false;
/*  36 */     this.isIfFalse = false;
/*  37 */     this.exit = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkData() {
/*  42 */     for (MCH_HudItem hud : this.list)
/*     */     {
/*  44 */       hud.parent = this;
/*     */     }
/*  46 */     if (this.isWaitEndif) throw new RuntimeException("Endif not found!");
/*     */   
/*     */   }
/*     */   
/*     */   public void loadItemData(int fileLine, String item, String data) {
/*  51 */     String[] prm = data.split("\\s*,\\s*");
/*  52 */     if (prm == null || prm.length == 0)
/*     */       return; 
/*  54 */     if (item.equalsIgnoreCase("If")) {
/*     */ 
/*     */       
/*  57 */       if (this.isWaitEndif)
/*     */       {
/*  59 */         throw new RuntimeException("Endif not found!");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  64 */       this.list.add(new MCH_HudItemConditional(fileLine, false, prm[0]));
/*  65 */       this.isWaitEndif = true;
/*     */     
/*     */     }
/*  68 */     else if (item.equalsIgnoreCase("Endif")) {
/*     */ 
/*     */       
/*  71 */       if (this.isWaitEndif)
/*     */       {
/*  73 */         this.list.add(new MCH_HudItemConditional(fileLine, true, ""));
/*  74 */         this.isWaitEndif = false;
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*  79 */         throw new RuntimeException("IF in a pair can not be found!");
/*     */       }
/*     */     
/*  82 */     } else if (item.equalsIgnoreCase("DrawString") || item.equalsIgnoreCase("DrawCenteredString")) {
/*     */       
/*  84 */       if (prm.length >= 3)
/*     */       {
/*  86 */         String s = prm[2];
/*  87 */         if (s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"')
/*     */         {
/*  89 */           s = s.substring(1, s.length() - 1);
/*  90 */           this.list.add(new MCH_HudItemString(fileLine, prm[0], prm[1], s, prm, item.equalsIgnoreCase("DrawCenteredString")));
/*     */         }
/*     */       
/*     */       }
/*     */     
/*  95 */     } else if (item.equalsIgnoreCase("Exit")) {
/*     */       
/*  97 */       this.list.add(new MCH_HudItemExit(fileLine));
/*     */     }
/*  99 */     else if (item.equalsIgnoreCase("Color")) {
/*     */ 
/*     */       
/* 102 */       if (prm.length == 1) {
/*     */         
/* 104 */         MCH_HudItemColor c = MCH_HudItemColor.createByParams(fileLine, new String[] { prm[0] });
/* 105 */         if (c != null) this.list.add(c);
/*     */       
/* 107 */       } else if (prm.length == 4) {
/*     */ 
/*     */         
/* 110 */         String[] s = { prm[0], prm[1], prm[2], prm[3] };
/* 111 */         MCH_HudItemColor c = MCH_HudItemColor.createByParams(fileLine, s);
/* 112 */         if (c != null) this.list.add(c);
/*     */       
/*     */       } 
/* 115 */     } else if (item.equalsIgnoreCase("DrawTexture")) {
/*     */       
/* 117 */       if (prm.length >= 9 && prm.length <= 10)
/*     */       {
/* 119 */         String rot = (prm.length == 10) ? prm[9] : "0";
/* 120 */         this.list.add(new MCH_HudItemTexture(fileLine, prm[0], prm[1], prm[2], prm[3], prm[4], prm[5], prm[6], prm[7], prm[8], rot));
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 127 */     else if (item.equalsIgnoreCase("DrawRect")) {
/*     */       
/* 129 */       if (prm.length == 4)
/*     */       {
/* 131 */         this.list.add(new MCH_HudItemRect(fileLine, prm[0], prm[1], prm[2], prm[3]));
/*     */       }
/*     */     }
/* 134 */     else if (item.equalsIgnoreCase("DrawLine")) {
/*     */       
/* 136 */       int len = prm.length;
/* 137 */       if (len >= 4 && len % 2 == 0)
/*     */       {
/* 139 */         this.list.add(new MCH_HudItemLine(fileLine, prm));
/*     */       }
/*     */     }
/* 142 */     else if (item.equalsIgnoreCase("DrawLineStipple")) {
/*     */       
/* 144 */       int len = prm.length;
/* 145 */       if (len >= 6 && len % 2 == 0)
/*     */       {
/* 147 */         this.list.add(new MCH_HudItemLineStipple(fileLine, prm));
/*     */       }
/*     */     }
/* 150 */     else if (item.equalsIgnoreCase("Call")) {
/*     */       
/* 152 */       int len = prm.length;
/* 153 */       if (len == 1)
/*     */       {
/* 155 */         this.list.add(new MCH_HudItemCall(fileLine, prm[0]));
/*     */       }
/*     */     }
/* 158 */     else if (item.equalsIgnoreCase("DrawEntityRadar") || item.equalsIgnoreCase("DrawEnemyRadar")) {
/*     */       
/* 160 */       if (prm.length == 5)
/*     */       {
/* 162 */         this.list.add(new MCH_HudItemRadar(fileLine, item.equalsIgnoreCase("DrawEntityRadar"), prm[0], prm[1], prm[2], prm[3], prm[4]));
/*     */       
/*     */       }
/*     */     }
/* 166 */     else if (item.equalsIgnoreCase("DrawGraduationYaw") || item.equalsIgnoreCase("DrawGraduationPitch1") || item.equalsIgnoreCase("DrawGraduationPitch2") || item.equalsIgnoreCase("DrawGraduationPitch3")) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (prm.length == 4)
/*     */       {
/* 173 */         int type = -1;
/* 174 */         if (item.equalsIgnoreCase("DrawGraduationYaw")) type = 0; 
/* 175 */         if (item.equalsIgnoreCase("DrawGraduationPitch1")) type = 1; 
/* 176 */         if (item.equalsIgnoreCase("DrawGraduationPitch2")) type = 2; 
/* 177 */         if (item.equalsIgnoreCase("DrawGraduationPitch3")) type = 3; 
/* 178 */         this.list.add(new MCH_HudItemGraduation(fileLine, type, prm[0], prm[1], prm[2], prm[3]));
/*     */       }
/*     */     
/*     */     }
/* 182 */     else if (item.equalsIgnoreCase("DrawCameraRot")) {
/*     */       
/* 184 */       if (prm.length == 2)
/*     */       {
/* 186 */         this.list.add(new MCH_HudItemCameraRot(fileLine, prm[0], prm[1]));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(MCH_EntityAircraft ac, EntityPlayer player, float partialTicks) {
/* 193 */     MCH_HudItem.ac = ac;
/* 194 */     MCH_HudItem.player = player;
/* 195 */     MCH_HudItem.partialTicks = partialTicks;
/*     */     
/* 197 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(MCH_HudItem.mc, MCH_HudItem.mc.field_71443_c, MCH_HudItem.mc.field_71440_d);
/*     */     
/* 199 */     MCH_HudItem.scaleFactor = w_ScaledResolution.func_78325_e();
/* 200 */     if (MCH_HudItem.scaleFactor <= 0) MCH_HudItem.scaleFactor = 1;
/*     */     
/* 202 */     MCH_HudItem.width = MCH_HudItem.mc.field_71443_c / MCH_HudItem.scaleFactor;
/* 203 */     MCH_HudItem.height = MCH_HudItem.mc.field_71440_d / MCH_HudItem.scaleFactor;
/* 204 */     MCH_HudItem.centerX = MCH_HudItem.width / 2.0D;
/* 205 */     MCH_HudItem.centerY = MCH_HudItem.height / 2.0D;
/*     */     
/* 207 */     this.isIfFalse = false;
/* 208 */     this.isDrawing = false;
/* 209 */     this.exit = false;
/*     */     
/* 211 */     if (ac != null && ac.getAcInfo() != null && player != null) {
/*     */       
/* 213 */       MCH_HudItem.update();
/* 214 */       drawItems();
/* 215 */       MCH_HudItem.drawVarMap();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawItems() {
/* 221 */     if (!this.isDrawing) {
/*     */ 
/*     */       
/* 224 */       this.isDrawing = true;
/*     */       
/* 226 */       for (MCH_HudItem hud : this.list) {
/*     */         
/* 228 */         int line = -1;
/*     */         
/*     */         try {
/* 231 */           line = hud.fileLine;
/* 232 */           if (hud.canExecute())
/*     */           {
/* 234 */             hud.execute();
/* 235 */             if (this.exit) {
/*     */               break;
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 241 */         catch (Exception e) {
/*     */           
/* 243 */           MCH_Lib.Log("#### Draw HUD Error!!!: line=%d, file=%s", new Object[] { Integer.valueOf(line), this.fileName });
/* 244 */           e.printStackTrace();
/* 245 */           throw new RuntimeException(e);
/*     */         } 
/*     */       } 
/* 248 */       this.exit = false;
/* 249 */       this.isIfFalse = false;
/* 250 */       this.isDrawing = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_Hud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */