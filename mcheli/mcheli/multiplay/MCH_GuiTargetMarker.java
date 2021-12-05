/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MarkEntityPos;
/*     */ import mcheli.MCH_ServerSettings;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.gui.MCH_Gui;
/*     */ import mcheli.multiplay.MCH_Multiplay;
/*     */ import mcheli.multiplay.MCH_TargetType;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiTargetMarker
/*     */   extends MCH_Gui
/*     */ {
/*  36 */   private static FloatBuffer matModel = BufferUtils.createFloatBuffer(16);
/*  37 */   private static FloatBuffer matProjection = BufferUtils.createFloatBuffer(16);
/*  38 */   private static IntBuffer matViewport = BufferUtils.createIntBuffer(16);
/*  39 */   private static ArrayList<MCH_MarkEntityPos> entityPos = new ArrayList<MCH_MarkEntityPos>();
/*     */   
/*  41 */   private static HashMap<Integer, Integer> spotedEntity = new HashMap<Integer, Integer>();
/*     */   
/*     */   private static Minecraft s_minecraft;
/*     */   
/*     */   public MCH_GuiTargetMarker(Minecraft minecraft) {
/*  46 */     super(minecraft);
/*  47 */     s_minecraft = minecraft;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  53 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  64 */     return (player != null && player.field_70170_p != null);
/*     */   }
/*     */   
/*  67 */   private static int spotedEntityCountdown = 0;
/*     */   
/*     */   public static void onClientTick() {
/*  70 */     if (!Minecraft.func_71410_x().func_147113_T())
/*     */     {
/*  72 */       spotedEntityCountdown++;
/*     */     }
/*  74 */     if (spotedEntityCountdown >= 20) {
/*     */       
/*  76 */       spotedEntityCountdown = 0;
/*     */       
/*  78 */       for (Integer key : spotedEntity.keySet()) {
/*     */         
/*  80 */         int count = ((Integer)spotedEntity.get(key)).intValue();
/*  81 */         if (count > 0)
/*     */         {
/*  83 */           spotedEntity.put(key, Integer.valueOf(count - 1));
/*     */         }
/*     */       } 
/*  86 */       for (Iterator<Integer> i = spotedEntity.values().iterator(); i.hasNext();) {
/*     */         
/*  88 */         if (((Integer)i.next()).intValue() <= 0)
/*     */         {
/*  90 */           i.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSpotedEntity(Entity entity) {
/*  98 */     int entityId = entity.func_145782_y();
/*  99 */     for (Iterator<Integer> i$ = spotedEntity.keySet().iterator(); i$.hasNext(); ) { int key = ((Integer)i$.next()).intValue();
/*     */       
/* 101 */       if (key == entityId) return true;  }
/*     */     
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public static void addSpotedEntity(int entityId, int count) {
/* 107 */     if (spotedEntity.containsKey(Integer.valueOf(entityId))) {
/*     */       
/* 109 */       int now = ((Integer)spotedEntity.get(Integer.valueOf(entityId))).intValue();
/* 110 */       if (count > now)
/*     */       {
/* 112 */         spotedEntity.put(Integer.valueOf(entityId), Integer.valueOf(count));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 117 */       spotedEntity.put(Integer.valueOf(entityId), Integer.valueOf(count));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addMarkEntityPos(int reserve, Entity entity, double x, double y, double z) {
/* 123 */     addMarkEntityPos(reserve, entity, x, y, z, false);
/*     */   }
/*     */   
/*     */   public static void addMarkEntityPos(int reserve, Entity entity, double x, double y, double z, boolean nazo) {
/* 127 */     if (!isEnableEntityMarker())
/*     */       return; 
/* 129 */     MCH_TargetType spotType = MCH_TargetType.NONE;
/*     */     
/* 131 */     EntityClientPlayerMP entityClientPlayerMP = s_minecraft.field_71439_g;
/*     */     
/* 133 */     if (entity instanceof MCH_EntityAircraft) {
/*     */       
/* 135 */       MCH_EntityAircraft ac = (MCH_EntityAircraft)entity;
/* 136 */       if (ac.isMountedEntity((Entity)entityClientPlayerMP)) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 141 */       if (ac.isMountedSameTeamEntity((EntityLivingBase)entityClientPlayerMP))
/*     */       {
/*     */         
/* 144 */         spotType = MCH_TargetType.SAME_TEAM_PLAYER;
/*     */       }
/*     */     }
/* 147 */     else if (entity instanceof EntityPlayer) {
/*     */       
/* 149 */       if (entity == entityClientPlayerMP || entity.field_70154_o instanceof mcheli.aircraft.MCH_EntitySeat || entity.field_70154_o instanceof MCH_EntityAircraft) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       if (entityClientPlayerMP.func_96124_cp() != null && entityClientPlayerMP.func_142014_c((EntityLivingBase)entity))
/*     */       {
/* 158 */         spotType = MCH_TargetType.SAME_TEAM_PLAYER;
/*     */       }
/*     */     } 
/*     */     
/* 162 */     if (spotType == MCH_TargetType.NONE && isSpotedEntity(entity))
/*     */     {
/* 164 */       spotType = MCH_Multiplay.canSpotEntity((Entity)entityClientPlayerMP, ((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u + entityClientPlayerMP.func_70047_e(), ((EntityPlayer)entityClientPlayerMP).field_70161_v, entity, false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 169 */     if (reserve == 100)
/*     */     {
/* 171 */       spotType = MCH_TargetType.POINT;
/*     */     }
/*     */     
/* 174 */     if (spotType != MCH_TargetType.NONE) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       MCH_MarkEntityPos e = new MCH_MarkEntityPos(spotType.ordinal(), entity);
/* 182 */       GL11.glGetFloat(2982, matModel);
/* 183 */       GL11.glGetFloat(2983, matProjection);
/* 184 */       GL11.glGetInteger(2978, matViewport);
/* 185 */       if (nazo) {
/*     */         
/* 187 */         GLU.gluProject((float)z, (float)y, (float)x, matModel, matProjection, matViewport, e.pos);
/* 188 */         float yy = e.pos.get(1);
/* 189 */         GLU.gluProject((float)x, (float)y, (float)z, matModel, matProjection, matViewport, e.pos);
/* 190 */         e.pos.put(1, yy);
/*     */       }
/*     */       else {
/*     */         
/* 194 */         GLU.gluProject((float)x, (float)y, (float)z, matModel, matProjection, matViewport, e.pos);
/*     */       } 
/* 196 */       entityPos.add(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearMarkEntityPos() {
/* 202 */     entityPos.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEnableEntityMarker() {
/* 207 */     if (MCH_Config.DisplayEntityMarker.prmBool && (Minecraft.func_71410_x().func_71356_B() || MCH_ServerSettings.enableEntityMarker)) if (MCH_Config.EntityMarkerSize.prmDouble > 0.0D);  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/* 214 */     this; GL11.glLineWidth((scaleFactor * 2));
/*     */     
/* 216 */     if (!isDrawGui(player))
/*     */       return; 
/* 218 */     GL11.glDisable(3042);
/*     */     
/* 220 */     if (isEnableEntityMarker())
/*     */     {
/* 222 */       drawMark();
/*     */     }
/*     */   }
/*     */   
/*     */   void drawMark() {
/* 227 */     int[] COLOR_TABLE = { 0, -808464433, -805371904, -805306624, -822018049, -805351649, -65536, 0 };
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
/* 238 */     int scale = (scaleFactor > 0) ? scaleFactor : 2;
/*     */     
/* 240 */     GL11.glEnable(3042);
/* 241 */     GL11.glDisable(3553);
/* 242 */     GL11.glBlendFunc(770, 771);
/* 243 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 244 */     GL11.glDepthMask(false);
/*     */     
/* 246 */     int DW = this.field_146297_k.field_71443_c;
/* 247 */     int DH = this.field_146297_k.field_71440_d;
/* 248 */     int DSW = this.field_146297_k.field_71443_c / scale;
/* 249 */     int DSH = this.field_146297_k.field_71440_d / scale;
/*     */     
/* 251 */     double x = 9999.0D;
/* 252 */     double z = 9999.0D;
/* 253 */     double y = 9999.0D;
/*     */     
/* 255 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 257 */     for (int i = 0; i < 2; i++) {
/*     */       
/* 259 */       if (i == 0)
/*     */       {
/* 261 */         tessellator.func_78371_b((i == 0) ? 4 : 1);
/*     */       }
/*     */       
/* 264 */       this; for (MCH_MarkEntityPos e : entityPos) {
/*     */         
/* 266 */         int color = COLOR_TABLE[e.type];
/*     */         
/* 268 */         x = (e.pos.get(0) / scale);
/* 269 */         z = e.pos.get(2);
/* 270 */         y = (e.pos.get(1) / scale);
/*     */ 
/*     */         
/* 273 */         if (z < 1.0D) {
/*     */           
/* 275 */           y = DSH - y;
/*     */ 
/*     */         
/*     */         }
/* 279 */         else if (x < (DW / 2)) {
/*     */           
/* 281 */           x = 10000.0D;
/*     */         }
/* 283 */         else if (x >= (DW / 2)) {
/*     */           
/* 285 */           x = -10000.0D;
/*     */         } 
/*     */ 
/*     */         
/* 289 */         if (i == 0) {
/*     */           
/* 291 */           double size = MCH_Config.EntityMarkerSize.prmDouble;
/* 292 */           if (e.type < MCH_TargetType.POINT.ordinal() && z < 1.0D && x >= 0.0D && x <= DSW && y >= 0.0D && y <= DSH)
/*     */           {
/* 294 */             drawTriangle1(tessellator, x, y, size, color); } 
/*     */           continue;
/*     */         } 
/* 297 */         if (e.type == MCH_TargetType.POINT.ordinal() && e.entity != null) {
/*     */           
/* 299 */           double MARK_SIZE = MCH_Config.BlockMarkerSize.prmDouble;
/* 300 */           if (z < 1.0D && x >= 0.0D && x <= (DSW - 20) && y >= 0.0D && y <= (DSH - 40)) {
/*     */             
/* 302 */             double dist = this.field_146297_k.field_71439_g.func_70032_d(e.entity);
/* 303 */             GL11.glEnable(3553);
/* 304 */             drawCenteredString(String.format("%.0fm", new Object[] { Double.valueOf(dist) }), (int)x, (int)(y + MARK_SIZE * 1.1D + 16.0D), color);
/* 305 */             if (x >= (DSW / 2 - 20) && x <= (DSW / 2 + 20) && y >= (DSH / 2 - 20) && y <= (DSH / 2 + 20)) {
/*     */               
/* 307 */               drawString(String.format("x : %.0f", new Object[] { Double.valueOf(e.entity.field_70165_t) }), (int)(x + MARK_SIZE + 18.0D), (int)y - 12, color);
/* 308 */               drawString(String.format("y : %.0f", new Object[] { Double.valueOf(e.entity.field_70163_u) }), (int)(x + MARK_SIZE + 18.0D), (int)y - 4, color);
/* 309 */               drawString(String.format("z : %.0f", new Object[] { Double.valueOf(e.entity.field_70161_v) }), (int)(x + MARK_SIZE + 18.0D), (int)y + 4, color);
/*     */             } 
/* 311 */             GL11.glDisable(3553);
/*     */             
/* 313 */             tessellator.func_78371_b(1);
/* 314 */             drawRhombus(tessellator, 15, x, y, this.field_73735_i, MARK_SIZE, color);
/*     */           }
/*     */           else {
/*     */             
/* 318 */             tessellator.func_78371_b(1);
/* 319 */             double S = 30.0D;
/* 320 */             if (x < S) {
/*     */               
/* 322 */               drawRhombus(tessellator, 1, S, (DSH / 2), this.field_73735_i, MARK_SIZE, color);
/*     */             }
/* 324 */             else if (x > DSW - S) {
/*     */               
/* 326 */               drawRhombus(tessellator, 4, DSW - S, (DSH / 2), this.field_73735_i, MARK_SIZE, color);
/*     */             } 
/* 328 */             if (y < S) {
/*     */               
/* 330 */               drawRhombus(tessellator, 8, (DSW / 2), S, this.field_73735_i, MARK_SIZE, color);
/*     */             }
/* 332 */             else if (y > DSH - S * 2.0D) {
/*     */               
/* 334 */               drawRhombus(tessellator, 2, (DSW / 2), DSH - S * 2.0D, this.field_73735_i, MARK_SIZE, color);
/*     */             } 
/*     */           } 
/* 337 */           tessellator.func_78381_a();
/*     */         } 
/*     */       } 
/* 340 */       if (i == 0)
/*     */       {
/* 342 */         tessellator.func_78381_a();
/*     */       }
/*     */     } 
/*     */     
/* 346 */     GL11.glDepthMask(true);
/* 347 */     GL11.glEnable(3553);
/* 348 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawRhombus(Tessellator tessellator, int dir, double x, double y, double z, double size, int color) {
/* 358 */     size *= 2.0D;
/* 359 */     tessellator.func_78384_a(0xFFFFFF & color, color >> 24 & 0xFF);
/* 360 */     double M = size / 3.0D;
/* 361 */     if ((dir & 0x1) != 0) {
/*     */       
/* 363 */       tessellator.func_78377_a(x - size, y, z);
/* 364 */       tessellator.func_78377_a(x - size + M, y - M, z);
/* 365 */       tessellator.func_78377_a(x - size, y, z);
/* 366 */       tessellator.func_78377_a(x - size + M, y + M, z);
/*     */     } 
/*     */     
/* 369 */     if ((dir & 0x4) != 0) {
/*     */       
/* 371 */       tessellator.func_78377_a(x + size, y, z);
/* 372 */       tessellator.func_78377_a(x + size - M, y - M, z);
/* 373 */       tessellator.func_78377_a(x + size, y, z);
/* 374 */       tessellator.func_78377_a(x + size - M, y + M, z);
/*     */     } 
/*     */     
/* 377 */     if ((dir & 0x8) != 0) {
/*     */       
/* 379 */       tessellator.func_78377_a(x, y - size, z);
/* 380 */       tessellator.func_78377_a(x + M, y - size + M, z);
/* 381 */       tessellator.func_78377_a(x, y - size, z);
/* 382 */       tessellator.func_78377_a(x - M, y - size + M, z);
/*     */     } 
/*     */     
/* 385 */     if ((dir & 0x2) != 0) {
/*     */       
/* 387 */       tessellator.func_78377_a(x, y + size, z);
/* 388 */       tessellator.func_78377_a(x + M, y + size - M, z);
/* 389 */       tessellator.func_78377_a(x, y + size, z);
/* 390 */       tessellator.func_78377_a(x - M, y + size - M, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawTriangle1(Tessellator tessellator, double x, double y, double size, int color) {
/* 396 */     tessellator.func_78384_a(0xFFFFFF & color, color >> 24 & 0xFF);
/* 397 */     tessellator.func_78377_a(x + size / 2.0D, y - 10.0D - size, this.field_73735_i);
/* 398 */     tessellator.func_78377_a(x - size / 2.0D, y - 10.0D - size, this.field_73735_i);
/* 399 */     tessellator.func_78377_a(x + 0.0D, y - 10.0D, this.field_73735_i);
/*     */   }
/*     */   
/*     */   public void drawTriangle2(Tessellator tessellator, double x, double y, double size, int color) {
/* 403 */     tessellator.func_78384_a(0x7F7F7F & color, color >> 24 & 0xFF);
/* 404 */     tessellator.func_78377_a(x + size / 2.0D, y - 10.0D - size, this.field_73735_i);
/* 405 */     tessellator.func_78377_a(x - size / 2.0D, y - 10.0D - size, this.field_73735_i);
/* 406 */     tessellator.func_78377_a(x - size / 2.0D, y - 10.0D - size, this.field_73735_i);
/* 407 */     tessellator.func_78377_a(x + 0.0D, y - 10.0D, this.field_73735_i);
/* 408 */     tessellator.func_78377_a(x + 0.0D, y - 10.0D, this.field_73735_i);
/* 409 */     tessellator.func_78377_a(x + size / 2.0D, y - 10.0D - size, this.field_73735_i);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void markPoint(int px, int py, int pz) {
/* 414 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/* 415 */     if (entityClientPlayerMP != null && ((EntityPlayer)entityClientPlayerMP).field_70170_p != null)
/*     */     {
/* 417 */       if (py < 1000) {
/*     */         
/* 419 */         MCH_ParticlesUtil.spawnMarkPoint((EntityPlayer)entityClientPlayerMP, 0.5D + px, 1.0D + py, 0.5D + pz);
/*     */       }
/*     */       else {
/*     */         
/* 423 */         MCH_ParticlesUtil.clearMarkPoint();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_GuiTargetMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */