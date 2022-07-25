/*     */ package mcheli.mcheli.hud;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_ClientCommonTickHandler;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_LowPassFilterFloat;
/*     */ import mcheli.MCH_Vector2;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.eval.eval.ExpRuleFactory;
/*     */ import mcheli.eval.eval.Expression;
/*     */ import mcheli.eval.eval.var.MapVariable;
/*     */ import mcheli.eval.eval.var.Variable;
/*     */ import mcheli.hud.MCH_Hud;
/*     */ import mcheli.hud.MCH_HudItemExit;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.weapon.MCH_EntityTvMissile;
/*     */ import mcheli.weapon.MCH_SightType;
/*     */ import mcheli.weapon.MCH_WeaponBase;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponSet;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_OpenGlHelper;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MCH_HudItem
/*     */   extends Gui
/*     */ {
/*     */   public final int fileLine;
/*     */   public static Minecraft mc;
/*     */   public static EntityPlayer player;
/*     */   public static MCH_EntityAircraft ac;
/*  52 */   protected static double centerX = 0.0D;
/*  53 */   protected static double centerY = 0.0D;
/*     */   
/*     */   public static double width;
/*     */   public static double height;
/*  57 */   protected static Random rand = new Random();
/*     */   
/*     */   public static int scaleFactor;
/*  60 */   public static int colorSetting = -16777216;
/*     */   
/*  62 */   protected static int altitudeUpdateCount = 0;
/*  63 */   protected static int Altitude = 0;
/*     */   
/*     */   protected static float prevRadarRot;
/*     */   
/*  67 */   protected static String WeaponName = "";
/*  68 */   protected static String WeaponAmmo = "";
/*  69 */   protected static String WeaponAllAmmo = "";
/*  70 */   protected static MCH_WeaponSet CurrentWeapon = null;
/*  71 */   protected static float ReloadPer = 0.0F;
/*  72 */   protected static float ReloadSec = 0.0F;
/*  73 */   protected static float MortarDist = 0.0F;
/*     */   
/*  75 */   protected static MCH_LowPassFilterFloat StickX_LPF = new MCH_LowPassFilterFloat(4);
/*  76 */   protected static MCH_LowPassFilterFloat StickY_LPF = new MCH_LowPassFilterFloat(4);
/*     */   
/*     */   protected static double StickX;
/*     */   
/*     */   protected static double StickY;
/*     */   
/*     */   protected static double TVM_PosX;
/*     */   
/*     */   protected static double TVM_PosY;
/*     */   
/*     */   protected static double TVM_PosZ;
/*     */   protected static double TVM_Diff;
/*     */   protected static double UAV_Dist;
/*     */   protected static int countFuelWarn;
/*     */   protected static ArrayList<MCH_Vector2> EntityList;
/*     */   protected static ArrayList<MCH_Vector2> EnemyList;
/*  92 */   protected static Map<String, Double> varMap = null;
/*     */   
/*     */   protected MCH_Hud parent;
/*     */   
/*     */   protected static float partialTicks;
/*     */   
/*  98 */   private static MCH_HudItemExit dummy = new MCH_HudItemExit(0);
/*     */ 
/*     */   
/*     */   public MCH_HudItem(int fileLine) {
/* 102 */     this.fileLine = fileLine;
/* 103 */     this.field_73735_i = -110.0F;
/*     */   }
/*     */   
/*     */   public abstract void execute();
/*     */   
/*     */   public boolean canExecute() {
/* 109 */     return !this.parent.isIfFalse;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void update() {
/* 114 */     MCH_WeaponSet ws = ac.getCurrentWeapon((Entity)player);
/* 115 */     updateRadar(ac);
/* 116 */     updateStick();
/* 117 */     updateAltitude(ac);
/* 118 */     updateTvMissile(ac);
/* 119 */     updateUAV(ac);
/* 120 */     updateWeapon(ac, ws);
/* 121 */     updateVarMap(ac, ws);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toFormula(String s) {
/* 126 */     return s.toLowerCase().replaceAll("#", "0x").replace("\t", " ").replace(" ", "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double calc(String s) {
/* 132 */     Expression exp = ExpRuleFactory.getDefaultRule().parse(s);
/* 133 */     exp.setVariable((Variable)new MapVariable(varMap));
/* 134 */     return exp.evalDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public static long calcLong(String s) {
/* 139 */     Expression exp = ExpRuleFactory.getDefaultRule().parse(s);
/* 140 */     exp.setVariable((Variable)new MapVariable(varMap));
/* 141 */     return exp.evalLong();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCenteredString(String s, int x, int y, int color) {
/* 149 */     this; func_73732_a(mc.field_71466_p, s, x, y, color);
/*     */   }
/*     */   
/*     */   public void drawString(String s, int x, int y, int color) {
/* 153 */     this; func_73731_b(mc.field_71466_p, s, x, y, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexture(String name, double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, float rot, int textureWidth, int textureHeight) {
/* 162 */     W_McClient.MOD_bindTexture("textures/gui/" + name + ".png");
/*     */     
/* 164 */     GL11.glPushMatrix();
/*     */     
/* 166 */     GL11.glTranslated(left + width / 2.0D, top + height / 2.0D, 0.0D);
/* 167 */     GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*     */     
/* 169 */     float fx = (float)(1.0D / textureWidth);
/* 170 */     float fy = (float)(1.0D / textureHeight);
/* 171 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 172 */     tessellator.func_78382_b();
/* 173 */     tessellator.func_78374_a(-width / 2.0D, height / 2.0D, this.field_73735_i, uLeft * fx, (vTop + vHeight) * fy);
/* 174 */     tessellator.func_78374_a(width / 2.0D, height / 2.0D, this.field_73735_i, (uLeft + uWidth) * fx, (vTop + vHeight) * fy);
/* 175 */     tessellator.func_78374_a(width / 2.0D, -height / 2.0D, this.field_73735_i, (uLeft + uWidth) * fx, vTop * fy);
/* 176 */     tessellator.func_78374_a(-width / 2.0D, -height / 2.0D, this.field_73735_i, uLeft * fx, vTop * fy);
/* 177 */     tessellator.func_78381_a();
/*     */     
/* 179 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawRect(double par0, double par1, double par2, double par3, int par4) {
/* 186 */     if (par0 < par2) {
/*     */       
/* 188 */       double j1 = par0;
/* 189 */       par0 = par2;
/* 190 */       par2 = j1;
/*     */     } 
/*     */     
/* 193 */     if (par1 < par3) {
/*     */       
/* 195 */       double j1 = par1;
/* 196 */       par1 = par3;
/* 197 */       par3 = j1;
/*     */     } 
/*     */     
/* 200 */     float f3 = (par4 >> 24 & 0xFF) / 255.0F;
/* 201 */     float f = (par4 >> 16 & 0xFF) / 255.0F;
/* 202 */     float f1 = (par4 >> 8 & 0xFF) / 255.0F;
/* 203 */     float f2 = (par4 & 0xFF) / 255.0F;
/* 204 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 205 */     GL11.glEnable(3042);
/* 206 */     GL11.glDisable(3553);
/* 207 */     W_OpenGlHelper.glBlendFunc(770, 771, 1, 0);
/* 208 */     GL11.glColor4f(f, f1, f2, f3);
/* 209 */     tessellator.func_78382_b();
/* 210 */     tessellator.func_78377_a(par0, par3, 0.0D);
/* 211 */     tessellator.func_78377_a(par2, par3, 0.0D);
/* 212 */     tessellator.func_78377_a(par2, par1, 0.0D);
/* 213 */     tessellator.func_78377_a(par0, par1, 0.0D);
/* 214 */     tessellator.func_78381_a();
/* 215 */     GL11.glEnable(3553);
/* 216 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLine(double[] line, int color) {
/* 221 */     drawLine(line, color, 1);
/*     */   }
/*     */   
/*     */   public void drawLine(double[] line, int color, int mode) {
/* 225 */     GL11.glPushMatrix();
/*     */     
/* 227 */     GL11.glEnable(3042);
/* 228 */     GL11.glDisable(3553);
/* 229 */     GL11.glBlendFunc(770, 771);
/* 230 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 234 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 235 */     tessellator.func_78371_b(mode);
/* 236 */     for (int i = 0; i < line.length; i += 2)
/*     */     {
/* 238 */       tessellator.func_78377_a(line[i + 0], line[i + 1], this.field_73735_i);
/*     */     }
/* 240 */     tessellator.func_78381_a();
/*     */     
/* 242 */     GL11.glEnable(3553);
/* 243 */     GL11.glDisable(3042);
/*     */     
/* 245 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 246 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawLineStipple(double[] line, int color, int factor, int pattern) {
/* 250 */     GL11.glEnable(2852);
/* 251 */     this; GL11.glLineStipple(factor * scaleFactor, (short)pattern);
/* 252 */     drawLine(line, color);
/* 253 */     GL11.glDisable(2852);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPoints(ArrayList<Double> points, int color, int pointWidth) {
/* 258 */     int prevWidth = GL11.glGetInteger(2833);
/*     */     
/* 260 */     GL11.glPushMatrix();
/*     */     
/* 262 */     GL11.glEnable(3042);
/* 263 */     GL11.glDisable(3553);
/* 264 */     GL11.glBlendFunc(770, 771);
/* 265 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 269 */     GL11.glPointSize(pointWidth);
/* 270 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 271 */     tessellator.func_78371_b(0);
/* 272 */     for (int i = 0; i < points.size(); i += 2)
/*     */     {
/* 274 */       tessellator.func_78377_a(((Double)points.get(i)).doubleValue(), ((Double)points.get(i + 1)).doubleValue(), 0.0D);
/*     */     }
/* 276 */     tessellator.func_78381_a();
/*     */     
/* 278 */     GL11.glEnable(3553);
/* 279 */     GL11.glDisable(3042);
/*     */     
/* 281 */     GL11.glPopMatrix();
/* 282 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 283 */     GL11.glPointSize(prevWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateVarMap(MCH_EntityAircraft ac, MCH_WeaponSet ws) {
/* 291 */     if (varMap == null)
/*     */     {
/* 293 */       varMap = new LinkedHashMap<String, Double>();
/*     */     }
/* 295 */     updateVarMapItem("color", getColor());
/* 296 */     updateVarMapItem("center_x", centerX);
/* 297 */     updateVarMapItem("center_y", centerY);
/* 298 */     updateVarMapItem("width", width);
/* 299 */     updateVarMapItem("height", height);
/* 300 */     updateVarMapItem("time", (player.field_70170_p.func_72820_D() % 24000L));
/* 301 */     updateVarMapItem("test_mode", MCH_Config.TestMode.prmBool ? 1.0D : 0.0D);
/*     */     
/* 303 */     updateVarMapItem("plyr_yaw", MathHelper.func_76142_g(player.field_70177_z));
/* 304 */     updateVarMapItem("plyr_pitch", player.field_70125_A);
/*     */     
/* 306 */     updateVarMapItem("yaw", MathHelper.func_76142_g(ac.getRotYaw()));
/* 307 */     updateVarMapItem("pitch", ac.getRotPitch());
/* 308 */     updateVarMapItem("roll", MathHelper.func_76142_g(ac.getRotRoll()));
/* 309 */     updateVarMapItem("altitude", Altitude);
/* 310 */     updateVarMapItem("sea_alt", getSeaAltitude(ac));
/* 311 */     updateVarMapItem("have_radar", ac.isEntityRadarMounted() ? 1.0D : 0.0D);
/* 312 */     updateVarMapItem("radar_rot", getRadarRot(ac));
/* 313 */     updateVarMapItem("hp", ac.getHP());
/* 314 */     updateVarMapItem("max_hp", ac.getMaxHP());
/* 315 */     updateVarMapItem("hp_rto", (ac.getMaxHP() > 0) ? (ac.getHP() / ac.getMaxHP()) : 0.0D);
/* 316 */     updateVarMapItem("throttle", ac.getCurrentThrottle());
/* 317 */     updateVarMapItem("pos_x", ac.field_70165_t);
/* 318 */     updateVarMapItem("pos_y", ac.field_70163_u);
/* 319 */     updateVarMapItem("pos_z", ac.field_70161_v);
/* 320 */     updateVarMapItem("motion_x", ac.field_70159_w);
/* 321 */     updateVarMapItem("motion_y", ac.field_70181_x);
/* 322 */     updateVarMapItem("motion_z", ac.field_70179_y);
/* 323 */     updateVarMapItem("speed", Math.sqrt(ac.field_70159_w * ac.field_70159_w + ac.field_70181_x * ac.field_70181_x + ac.field_70179_y * ac.field_70179_y));
/* 324 */     updateVarMapItem("fuel", ac.getFuelP());
/* 325 */     updateVarMapItem("low_fuel", isLowFuel(ac));
/* 326 */     updateVarMapItem("stick_x", StickX);
/* 327 */     updateVarMapItem("stick_y", StickY);
/* 328 */     updateVarMap_Weapon(ws);
/* 329 */     updateVarMapItem("vtol_stat", getVtolStat(ac));
/* 330 */     updateVarMapItem("free_look", getFreeLook(ac, player));
/* 331 */     updateVarMapItem("gunner_mode", ac.getIsGunnerMode((Entity)player) ? 1.0D : 0.0D);
/* 332 */     updateVarMapItem("cam_mode", ac.getCameraMode(player));
/* 333 */     updateVarMapItem("cam_zoom", ac.camera.getCameraZoom());
/* 334 */     updateVarMapItem("auto_pilot", getAutoPilot(ac, player));
/* 335 */     updateVarMapItem("have_flare", ac.haveFlare() ? 1.0D : 0.0D);
/* 336 */     updateVarMapItem("can_flare", ac.canUseFlare() ? 1.0D : 0.0D);
/* 337 */     updateVarMapItem("inventory", ac.func_70302_i_());
/* 338 */     updateVarMapItem("hovering", (ac instanceof mcheli.helicopter.MCH_EntityHeli && ac.isHoveringMode()) ? 1.0D : 0.0D);
/* 339 */     updateVarMapItem("is_uav", ac.isUAV() ? 1.0D : 0.0D);
/* 340 */     updateVarMapItem("uav_fs", getUAV_Fs(ac));
/*     */   }
/*     */   
/*     */   public static void updateVarMapItem(String key, double value) {
/* 344 */     varMap.put(key, Double.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawVarMap() {
/* 349 */     if (MCH_Config.TestMode.prmBool) {
/*     */       
/* 351 */       int i = 0;
/* 352 */       int x = (int)(-300.0D + centerX);
/* 353 */       int y = (int)(-100.0D + centerY);
/* 354 */       for (String key : varMap.keySet()) {
/*     */         
/* 356 */         dummy.drawString(key, x, y, -12544);
/* 357 */         Double d = varMap.get(key);
/* 358 */         String fmt = key.equalsIgnoreCase("color") ? String.format(": 0x%08X", new Object[] { Integer.valueOf(d.intValue()) }) : String.format(": %.2f", new Object[] { d });
/* 359 */         dummy.drawString(fmt, x + 50, y, -12544);
/* 360 */         i++;
/* 361 */         y += 8;
/* 362 */         if (i == varMap.size() / 2) {
/*     */           
/* 364 */           x = (int)(200.0D + centerX);
/* 365 */           y = (int)(-100.0D + centerY);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static double getUAV_Fs(MCH_EntityAircraft ac) {
/* 373 */     double uav_fs = 0.0D;
/* 374 */     if (ac.isUAV() && ac.getUavStation() != null) {
/*     */       
/* 376 */       double dx = ac.field_70165_t - (ac.getUavStation()).field_70165_t;
/* 377 */       double dz = ac.field_70161_v - (ac.getUavStation()).field_70161_v;
/*     */       
/* 379 */       float dist = (float)Math.sqrt(dx * dx + dz * dz);
/* 380 */       float distMax = 120.0F;
/* 381 */       if (dist > 120.0F) dist = 120.0F; 
/* 382 */       uav_fs = (1.0F - dist / 120.0F);
/*     */     } 
/* 384 */     return uav_fs;
/*     */   }
/*     */   
/*     */   private static void updateVarMap_Weapon(MCH_WeaponSet ws) {
/* 388 */     int reloading = 0;
/* 389 */     double wpn_heat = 0.0D;
/* 390 */     int is_heat_wpn = 0;
/* 391 */     int sight_type = 0;
/* 392 */     double lock = 0.0D;
/* 393 */     float rel_time = 0.0F;
/* 394 */     int display_mortar_dist = 0;
/*     */     
/* 396 */     if (ws != null) {
/*     */       
/* 398 */       MCH_WeaponBase wb = ws.getCurrentWeapon();
/* 399 */       MCH_WeaponInfo wi = wb.getInfo();
/* 400 */       if (wi == null)
/*     */         return; 
/* 402 */       is_heat_wpn = (wi.maxHeatCount > 0) ? 1 : 0;
/* 403 */       reloading = ws.isInPreparation() ? 1 : 0;
/*     */       
/* 405 */       display_mortar_dist = wi.displayMortarDistance ? 1 : 0;
/*     */       
/* 407 */       if (wi.delay > wi.reloadTime) {
/*     */         
/* 409 */         rel_time = ws.countWait / ((wi.delay > 0) ? wi.delay : true);
/* 410 */         if (rel_time < 0.0F) rel_time = -rel_time; 
/* 411 */         if (rel_time > 1.0F) rel_time = 1.0F;
/*     */       
/*     */       } else {
/*     */         
/* 415 */         rel_time = ws.countReloadWait / ((wi.reloadTime > 0) ? wi.reloadTime : true);
/*     */       } 
/*     */       
/* 418 */       if (wi.maxHeatCount > 0) {
/*     */         
/* 420 */         double hpp = ws.currentHeat / wi.maxHeatCount;
/* 421 */         wpn_heat = (hpp > 1.0D) ? 1.0D : hpp;
/*     */       } 
/* 423 */       int cntLockMax = wb.getLockCountMax();
/* 424 */       MCH_SightType sight = wb.getSightType();
/* 425 */       if (sight == MCH_SightType.LOCK && cntLockMax > 0) {
/*     */         
/* 427 */         lock = wb.getLockCount() / cntLockMax;
/* 428 */         sight_type = 2;
/*     */       } 
/* 430 */       if (sight == MCH_SightType.ROCKET)
/*     */       {
/* 432 */         sight_type = 1;
/*     */       }
/*     */     } 
/* 435 */     updateVarMapItem("reloading", reloading);
/* 436 */     updateVarMapItem("reload_time", rel_time);
/* 437 */     updateVarMapItem("wpn_heat", wpn_heat);
/* 438 */     updateVarMapItem("is_heat_wpn", is_heat_wpn);
/* 439 */     updateVarMapItem("sight_type", sight_type);
/* 440 */     updateVarMapItem("lock", lock);
/* 441 */     updateVarMapItem("dsp_mt_dist", display_mortar_dist);
/* 442 */     updateVarMapItem("mt_dist", MortarDist);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int isLowFuel(MCH_EntityAircraft ac) {
/* 447 */     int is_low_fuel = 0;
/*     */     
/* 449 */     if (countFuelWarn <= 0)
/*     */     {
/* 451 */       countFuelWarn = 280;
/*     */     }
/* 453 */     countFuelWarn--;
/*     */     
/* 455 */     if (countFuelWarn < 160)
/*     */     {
/* 457 */       if (ac.getMaxFuel() > 0 && ac.getFuelP() < 0.1F && !ac.isInfinityFuel((Entity)player, false))
/*     */       {
/* 459 */         is_low_fuel = 1;
/*     */       }
/*     */     }
/* 462 */     return is_low_fuel;
/*     */   }
/*     */   
/*     */   public static double getSeaAltitude(MCH_EntityAircraft ac) {
/* 466 */     double a = ac.field_70163_u - ac.field_70170_p.func_72919_O();
/* 467 */     return (a >= 0.0D) ? a : 0.0D;
/*     */   }
/*     */   
/*     */   public static float getRadarRot(MCH_EntityAircraft ac) {
/* 471 */     float rot = ac.getRadarRotate();
/* 472 */     float prevRot = prevRadarRot;
/* 473 */     if (rot < prevRot) rot += 360.0F; 
/* 474 */     prevRadarRot = ac.getRadarRotate();
/* 475 */     return MCH_Lib.smooth(rot, prevRot, partialTicks);
/*     */   }
/*     */   
/*     */   public static int getVtolStat(MCH_EntityAircraft ac) {
/* 479 */     if (ac instanceof MCP_EntityPlane)
/*     */     {
/* 481 */       return ((MCP_EntityPlane)ac).getVtolMode();
/*     */     }
/* 483 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getFreeLook(MCH_EntityAircraft ac, EntityPlayer player) {
/* 487 */     if (ac.isPilot((Entity)player) && ac.canSwitchFreeLook() && ac.isFreeLookMode())
/*     */     {
/* 489 */       return 1;
/*     */     }
/* 491 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getAutoPilot(MCH_EntityAircraft ac, EntityPlayer player) {
/* 495 */     if (ac instanceof MCP_EntityPlane)
/*     */     {
/* 497 */       if (ac.isPilot((Entity)player) && ac.getIsGunnerMode((Entity)player))
/*     */       {
/* 499 */         return 1;
/*     */       }
/*     */     }
/* 502 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getColor() {
/* 506 */     long l = colorSetting;
/* 507 */     l &= 0xFFFFFFFFL;
/* 508 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void updateStick() {
/* 517 */     StickX_LPF.put((float)(MCH_ClientCommonTickHandler.getCurrentStickX() / MCH_ClientCommonTickHandler.getMaxStickLength()));
/*     */ 
/*     */     
/* 520 */     StickY_LPF.put((float)(-MCH_ClientCommonTickHandler.getCurrentStickY() / MCH_ClientCommonTickHandler.getMaxStickLength()));
/*     */ 
/*     */     
/* 523 */     StickX = StickX_LPF.getAvg();
/* 524 */     StickY = StickY_LPF.getAvg();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updateRadar(MCH_EntityAircraft ac) {
/* 529 */     EntityList = ac.getRadarEntityList();
/* 530 */     EnemyList = ac.getRadarEnemyList();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updateAltitude(MCH_EntityAircraft ac) {
/* 535 */     if (altitudeUpdateCount <= 0) {
/*     */       
/* 537 */       int heliY = (int)ac.field_70163_u;
/* 538 */       if (heliY > 256)
/*     */       {
/* 540 */         heliY = 256;
/*     */       }
/*     */       
/* 543 */       for (int i = 0; i < 256; i++) {
/*     */         
/* 545 */         if (heliY - i <= 0)
/* 546 */           break;  int id = W_WorldFunc.getBlockId(ac.field_70170_p, (int)ac.field_70165_t, heliY - i, (int)ac.field_70161_v);
/* 547 */         if (id != 0) {
/*     */           
/* 549 */           Altitude = i;
/* 550 */           if (ac.field_70163_u > 256.0D)
/*     */           {
/* 552 */             Altitude = (int)(Altitude + ac.field_70163_u - 256.0D);
/*     */           }
/*     */           break;
/*     */         } 
/*     */       } 
/* 557 */       altitudeUpdateCount = 30;
/*     */     }
/*     */     else {
/*     */       
/* 561 */       altitudeUpdateCount--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateWeapon(MCH_EntityAircraft ac, MCH_WeaponSet ws) {
/* 567 */     if (ac.getWeaponNum() <= 0)
/* 568 */       return;  if (ws == null)
/*     */       return; 
/* 570 */     CurrentWeapon = ws;
/*     */ 
/*     */     
/* 573 */     WeaponName = ac.isPilotReloading() ? "-- Reloading --" : ws.getName();
/*     */     
/* 575 */     if (ws.getAmmoNumMax() > 0) {
/*     */       
/* 577 */       WeaponAmmo = ac.isPilotReloading() ? "----" : String.format("%4d", new Object[] { Integer.valueOf(ws.getAmmoNum()) });
/* 578 */       WeaponAllAmmo = ac.isPilotReloading() ? "----" : String.format("%4d", new Object[] { Integer.valueOf(ws.getRestAllAmmoNum()) });
/*     */     }
/*     */     else {
/*     */       
/* 582 */       WeaponAmmo = "";
/* 583 */       WeaponAllAmmo = "";
/*     */     } 
/*     */     
/* 586 */     MCH_WeaponInfo wi = ws.getInfo();
/*     */     
/* 588 */     if (wi.displayMortarDistance) {
/*     */       
/* 590 */       MortarDist = (float)ac.getLandInDistance((Entity)player);
/*     */     }
/*     */     else {
/*     */       
/* 594 */       MortarDist = -1.0F;
/*     */     } 
/*     */     
/* 597 */     if (wi.delay > wi.reloadTime) {
/*     */       
/* 599 */       ReloadSec = (ws.countWait >= 0) ? ws.countWait : -ws.countWait;
/*     */       
/* 601 */       ReloadPer = ws.countWait / ((wi.delay > 0) ? wi.delay : true);
/* 602 */       if (ReloadPer < 0.0F) ReloadPer = -ReloadPer; 
/* 603 */       if (ReloadPer > 1.0F) ReloadPer = 1.0F;
/*     */     
/*     */     } else {
/*     */       
/* 607 */       ReloadSec = ws.countReloadWait;
/* 608 */       ReloadPer = ws.countReloadWait / ((wi.reloadTime > 0) ? wi.reloadTime : true);
/*     */     } 
/* 610 */     ReloadSec /= 20.0F;
/* 611 */     ReloadPer = (1.0F - ReloadPer) * 100.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateUAV(MCH_EntityAircraft ac) {
/* 616 */     if (ac.isUAV() && ac.getUavStation() != null) {
/*     */       
/* 618 */       double dx = ac.field_70165_t - (ac.getUavStation()).field_70165_t;
/* 619 */       double dz = ac.field_70161_v - (ac.getUavStation()).field_70161_v;
/* 620 */       UAV_Dist = (float)Math.sqrt(dx * dx + dz * dz);
/*     */     }
/*     */     else {
/*     */       
/* 624 */       UAV_Dist = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updateTvMissile(MCH_EntityAircraft ac) {
/* 630 */     MCH_EntityTvMissile mCH_EntityTvMissile = ac.getTVMissile();
/* 631 */     if (mCH_EntityTvMissile != null) {
/*     */       
/* 633 */       TVM_PosX = ((Entity)mCH_EntityTvMissile).field_70165_t;
/* 634 */       TVM_PosY = ((Entity)mCH_EntityTvMissile).field_70163_u;
/* 635 */       TVM_PosZ = ((Entity)mCH_EntityTvMissile).field_70161_v;
/*     */       
/* 637 */       double dx = ((Entity)mCH_EntityTvMissile).field_70165_t - ac.field_70165_t;
/* 638 */       double dy = ((Entity)mCH_EntityTvMissile).field_70163_u - ac.field_70163_u;
/* 639 */       double dz = ((Entity)mCH_EntityTvMissile).field_70161_v - ac.field_70161_v;
/* 640 */       TVM_Diff = Math.sqrt(dx * dx + dy * dy + dz * dz);
/*     */     }
/*     */     else {
/*     */       
/* 644 */       TVM_PosX = 0.0D;
/* 645 */       TVM_PosY = 0.0D;
/* 646 */       TVM_PosZ = 0.0D;
/* 647 */       TVM_Diff = 0.0D;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\hud\MCH_HudItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */