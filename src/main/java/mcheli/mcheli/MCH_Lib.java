/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_ItemRendererDummy;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_Vector2;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import mcheli.wrapper.W_Vec3;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.ShapedRecipes;
/*     */ import net.minecraft.item.crafting.ShapelessRecipes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_Lib {
/*  36 */   private static HashMap<String, Material> mapMaterial = new HashMap<String, Material>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*  42 */     mapMaterial.clear();
/*  43 */     mapMaterial.put("air", Material.field_151579_a);
/*  44 */     mapMaterial.put("grass", Material.field_151577_b);
/*  45 */     mapMaterial.put("ground", Material.field_151578_c);
/*  46 */     mapMaterial.put("wood", Material.field_151575_d);
/*  47 */     mapMaterial.put("rock", Material.field_151576_e);
/*  48 */     mapMaterial.put("iron", Material.field_151573_f);
/*  49 */     mapMaterial.put("anvil", Material.field_151574_g);
/*  50 */     mapMaterial.put("water", Material.field_151586_h);
/*  51 */     mapMaterial.put("lava", Material.field_151587_i);
/*  52 */     mapMaterial.put("leaves", Material.field_151584_j);
/*  53 */     mapMaterial.put("plants", Material.field_151585_k);
/*  54 */     mapMaterial.put("vine", Material.field_151582_l);
/*  55 */     mapMaterial.put("sponge", Material.field_151583_m);
/*  56 */     mapMaterial.put("cloth", Material.field_151580_n);
/*  57 */     mapMaterial.put("fire", Material.field_151581_o);
/*  58 */     mapMaterial.put("sand", Material.field_151595_p);
/*  59 */     mapMaterial.put("circuits", Material.field_151594_q);
/*  60 */     mapMaterial.put("carpet", Material.field_151593_r);
/*  61 */     mapMaterial.put("glass", Material.field_151592_s);
/*  62 */     mapMaterial.put("redstoneLight", Material.field_151591_t);
/*  63 */     mapMaterial.put("tnt", Material.field_151590_u);
/*  64 */     mapMaterial.put("coral", Material.field_151589_v);
/*  65 */     mapMaterial.put("ice", Material.field_151588_w);
/*  66 */     mapMaterial.put("packedIce", Material.field_151598_x);
/*  67 */     mapMaterial.put("snow", Material.field_151597_y);
/*  68 */     mapMaterial.put("craftedSnow", Material.field_151596_z);
/*  69 */     mapMaterial.put("cactus", Material.field_151570_A);
/*  70 */     mapMaterial.put("clay", Material.field_151571_B);
/*  71 */     mapMaterial.put("gourd", Material.field_151572_C);
/*  72 */     mapMaterial.put("dragonEgg", Material.field_151566_D);
/*  73 */     mapMaterial.put("portal", Material.field_151567_E);
/*  74 */     mapMaterial.put("cake", Material.field_151568_F);
/*  75 */     mapMaterial.put("web", Material.field_151569_G);
/*  76 */     mapMaterial.put("piston", Material.field_76233_E);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Material getMaterialFromName(String name) {
/*  81 */     if (mapMaterial.containsKey(name))
/*     */     {
/*  83 */       return mapMaterial.get(name);
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 calculateFaceNormal(Vec3[] vertices) {
/*  90 */     Vec3 v1 = Vec3.func_72443_a((vertices[1]).field_72450_a - (vertices[0]).field_72450_a, (vertices[1]).field_72448_b - (vertices[0]).field_72448_b, (vertices[1]).field_72449_c - (vertices[0]).field_72449_c);
/*     */ 
/*     */ 
/*     */     
/*  94 */     Vec3 v2 = Vec3.func_72443_a((vertices[2]).field_72450_a - (vertices[0]).field_72450_a, (vertices[2]).field_72448_b - (vertices[0]).field_72448_b, (vertices[2]).field_72449_c - (vertices[0]).field_72449_c);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     return v1.func_72431_c(v2).func_72432_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double parseDouble(String s) {
/* 105 */     return (s == null) ? 0.0D : Double.parseDouble(s.replace(',', '.'));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float RNG(float a, float min, float max) {
/* 110 */     return (a < min) ? min : ((a > max) ? max : a);
/*     */   }
/*     */   
/*     */   public static double RNG(double a, double min, double max) {
/* 114 */     return (a < min) ? min : ((a > max) ? max : a);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float smooth(float rot, float prevRot, float tick) {
/* 119 */     return prevRot + (rot - prevRot) * tick;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float smoothRot(float rot, float prevRot, float tick) {
/* 124 */     if (rot - prevRot < -180.0F) { prevRot -= 360.0F; }
/* 125 */     else if (prevRot - rot < -180.0F) { prevRot += 360.0F; }
/*     */     
/* 127 */     return prevRot + (rot - prevRot) * tick;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getRotateDiff(double base, double target) {
/* 132 */     base = getRotate360(base);
/* 133 */     target = getRotate360(target);
/*     */     
/* 135 */     if (target - base < -180.0D) {
/*     */       
/* 137 */       target += 360.0D;
/*     */     }
/* 139 */     else if (target - base > 180.0D) {
/*     */       
/* 141 */       base += 360.0D;
/*     */     } 
/*     */     
/* 144 */     return target - base;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getPosAngle(double tx, double tz, double cx, double cz) {
/* 150 */     double length_A = Math.sqrt(tx * tx + tz * tz);
/* 151 */     double length_B = Math.sqrt(cx * cx + cz * cz);
/*     */ 
/*     */     
/* 154 */     double cos_sita = (tx * cx + tz * cz) / length_A * length_B;
/*     */ 
/*     */     
/* 157 */     double sita = Math.acos(cos_sita);
/*     */     
/* 159 */     return (float)(sita * 180.0D / Math.PI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canPlayerCreateItem(IRecipe recipe, InventoryPlayer inventory) {
/* 165 */     if (recipe != null) {
/*     */       
/* 167 */       Map<Item, Integer> map = getItemMapFromRecipe(recipe);
/*     */       
/* 169 */       for (int i = 0; i < inventory.func_70302_i_(); i++) {
/*     */         
/* 171 */         ItemStack is = inventory.func_70301_a(i);
/* 172 */         if (is != null) {
/*     */           
/* 174 */           Item item = is.func_77973_b();
/* 175 */           if (map.containsKey(item))
/*     */           {
/* 177 */             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() - is.field_77994_a));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 182 */       for (Iterator<Integer> i$ = map.values().iterator(); i$.hasNext(); ) { int j = ((Integer)i$.next()).intValue();
/*     */         
/* 184 */         if (j > 0) return false;
/*     */          }
/*     */       
/* 187 */       return true;
/*     */     } 
/*     */     
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyEntityHurtResistantTimeConfig(Entity entity) {
/* 195 */     if (entity instanceof EntityLivingBase) {
/*     */       
/* 197 */       EntityLivingBase elb = (EntityLivingBase)entity;
/* 198 */       double h_time = MCH_Config.HurtResistantTime.prmDouble * elb.field_70172_ad;
/* 199 */       elb.field_70172_ad = (int)h_time;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int round(double d) {
/* 205 */     return (int)(d + 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 Rot2Vec3(float yaw, float pitch) {
/* 210 */     return Vec3.func_72443_a((-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)), -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F), (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 RotVec3(double x, double y, double z, float yaw, float pitch) {
/* 219 */     Vec3 v = Vec3.func_72443_a(x, y, z);
/* 220 */     v.func_72440_a(pitch / 180.0F * 3.1415927F);
/* 221 */     v.func_72442_b(yaw / 180.0F * 3.1415927F);
/* 222 */     return v;
/*     */   }
/*     */   
/*     */   public static Vec3 RotVec3(double x, double y, double z, float yaw, float pitch, float roll) {
/* 226 */     Vec3 v = Vec3.func_72443_a(x, y, z);
/* 227 */     W_Vec3.rotateAroundZ(roll / 180.0F * 3.1415927F, v);
/* 228 */     v.func_72440_a(pitch / 180.0F * 3.1415927F);
/* 229 */     v.func_72442_b(yaw / 180.0F * 3.1415927F);
/* 230 */     return v;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 RotVec3(Vec3 vin, float yaw, float pitch) {
/* 235 */     Vec3 v = Vec3.func_72443_a(vin.field_72450_a, vin.field_72448_b, vin.field_72449_c);
/* 236 */     v.func_72440_a(pitch / 180.0F * 3.1415927F);
/* 237 */     v.func_72442_b(yaw / 180.0F * 3.1415927F);
/* 238 */     return v;
/*     */   }
/*     */   
/*     */   public static Vec3 RotVec3(Vec3 vin, float yaw, float pitch, float roll) {
/* 242 */     Vec3 v = Vec3.func_72443_a(vin.field_72450_a, vin.field_72448_b, vin.field_72449_c);
/* 243 */     W_Vec3.rotateAroundZ(roll / 180.0F * 3.1415927F, v);
/* 244 */     v.func_72440_a(pitch / 180.0F * 3.1415927F);
/* 245 */     v.func_72442_b(yaw / 180.0F * 3.1415927F);
/* 246 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 _Rot2Vec3(float yaw, float pitch, float roll) {
/* 252 */     return Vec3.func_72443_a((-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)), -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F), (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getRotate360(double r) {
/* 261 */     r %= 360.0D;
/* 262 */     return (r >= 0.0D) ? r : (r + 360.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void Log(String format, Object... data) {
/* 267 */     String side = MCH_MOD.proxy.isRemote() ? "[Client]" : "[Server]";
/*     */     
/* 269 */     System.out.printf("[" + getTime() + "][" + "mcheli" + "]" + side + " " + format + "\n", data);
/*     */   }
/*     */   
/*     */   public static void Log(World world, String format, Object... data) {
/* 273 */     if (world != null) { Log((world.field_72995_K ? "[ClientWorld]" : "[ServerWorld]") + " " + format, data); }
/* 274 */     else { Log("[UnknownWorld]" + format, data); }
/*     */   
/*     */   }
/*     */   public static void Log(Entity entity, String format, Object... data) {
/* 278 */     if (entity != null) { Log(entity.field_70170_p, format, data); }
/* 279 */     else { Log((World)null, format, data); }
/*     */   
/*     */   }
/*     */   
/*     */   public static void DbgLog(boolean isRemote, String format, Object... data) {
/* 284 */     if (MCH_Config.DebugLog) {
/*     */       
/* 286 */       String t = getTime();
/* 287 */       if (isRemote) {
/*     */         
/* 289 */         String playerName = "null";
/* 290 */         if (getClientPlayer() instanceof EntityPlayer)
/*     */         {
/* 292 */           playerName = ((EntityPlayer)getClientPlayer()).getDisplayName();
/*     */         }
/* 294 */         System.out.println(String.format(format, data));
/*     */       }
/*     */       else {
/*     */         
/* 298 */         System.out.println(String.format(format, data));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void DbgLog(World w, String format, Object... data) {
/* 304 */     DbgLog(w.field_72995_K, format, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTime() {
/* 309 */     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
/*     */     
/* 311 */     return sdf.format(new Date());
/*     */   }
/*     */ 
/*     */   
/* 315 */   public static final String[] AZIMUTH_8 = new String[] { "S", "SW", "W", "NW", "N", "NE", "E", "SE" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public static final int AZIMUTH_8_ANG = 360 / AZIMUTH_8.length;
/*     */   
/*     */   public static String getAzimuthStr8(int dir) {
/* 328 */     dir %= 360;
/* 329 */     if (dir < 0) dir += 360; 
/* 330 */     dir /= AZIMUTH_8_ANG;
/* 331 */     return AZIMUTH_8[dir];
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rotatePoints(double[] points, float r) {
/* 349 */     r = r / 180.0F * 3.1415927F;
/* 350 */     for (int i = 0; i + 1 < points.length; i += 2) {
/*     */       
/* 352 */       double x = points[i + 0];
/* 353 */       double y = points[i + 1];
/* 354 */       points[i + 0] = x * MathHelper.func_76134_b(r) - y * MathHelper.func_76126_a(r);
/* 355 */       points[i + 1] = x * MathHelper.func_76126_a(r) + y * MathHelper.func_76134_b(r);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rotatePoints(ArrayList<MCH_Vector2> points, float r) {
/* 368 */     r = r / 180.0F * 3.1415927F;
/* 369 */     for (int i = 0; i + 1 < points.size(); i += 2) {
/*     */       
/* 371 */       double x = ((MCH_Vector2)points.get(i + 0)).x;
/* 372 */       double y = ((MCH_Vector2)points.get(i + 0)).y;
/* 373 */       ((MCH_Vector2)points.get(i + 0)).x = x * MathHelper.func_76134_b(r) - y * MathHelper.func_76126_a(r);
/* 374 */       ((MCH_Vector2)points.get(i + 0)).y = x * MathHelper.func_76126_a(r) + y * MathHelper.func_76134_b(r);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] listupFileNames(String path) {
/* 380 */     File dir = new File(path);
/* 381 */     return dir.list();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBlockInWater(World w, int x, int y, int z) {
/* 386 */     int[][] offset = { { 0, -1, 0 }, { 0, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 }, { 0, 1, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 395 */     if (y <= 0) return false; 
/* 396 */     for (int[] o : offset) {
/*     */       
/* 398 */       if (W_WorldFunc.isBlockWater(w, x + o[0], y + o[1], z + o[2]))
/*     */       {
/* 400 */         return true;
/*     */       }
/*     */     } 
/* 403 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBlockIdY(World w, double posX, double posY, double posZ, int size, int lenY, boolean canColliableOnly) {
/* 408 */     Block block = getBlockY(w, posX, posY, posZ, size, lenY, canColliableOnly);
/* 409 */     if (block == null) return 0; 
/* 410 */     return W_Block.func_149682_b(block);
/*     */   }
/*     */   
/*     */   public static int getBlockIdY(Entity entity, int size, int lenY) {
/* 414 */     return getBlockIdY(entity, size, lenY, true);
/*     */   }
/*     */   
/*     */   public static int getBlockIdY(Entity entity, int size, int lenY, boolean canColliableOnly) {
/* 418 */     Block block = getBlockY(entity, size, lenY, canColliableOnly);
/* 419 */     if (block == null) return 0; 
/* 420 */     return W_Block.func_149682_b(block);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Block getBlockY(Entity entity, int size, int lenY, boolean canColliableOnly) {
/* 425 */     return getBlockY(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, size, lenY, canColliableOnly);
/*     */   }
/*     */   
/*     */   public static Block getBlockY(World world, Vec3 pos, int size, int lenY, boolean canColliableOnly) {
/* 429 */     return getBlockY(world, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, size, lenY, canColliableOnly);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Block getBlockY(World world, double posX, double posY, double posZ, int size, int lenY, boolean canColliableOnly) {
/* 438 */     if (lenY == 0) return W_Blocks.field_150350_a;
/*     */     
/* 440 */     int px = (int)(posX + 0.5D);
/* 441 */     int py = (int)(posY + 0.5D);
/* 442 */     int pz = (int)(posZ + 0.5D);
/*     */     
/* 444 */     int cntY = (lenY > 0) ? lenY : -lenY;
/*     */     
/* 446 */     for (int y = 0; y < cntY; y++) {
/*     */       
/* 448 */       if (py + y < 0 || py + y > 255) return W_Blocks.field_150350_a;
/*     */       
/* 450 */       for (int x = -size / 2; x <= size / 2; x++) {
/*     */         
/* 452 */         for (int z = -size / 2; z <= size / 2; z++) {
/*     */           
/* 454 */           Block block = W_WorldFunc.getBlock(world, px + x, py + ((lenY > 0) ? y : -y), pz + z);
/* 455 */           if (block != null && block != W_Blocks.field_150350_a)
/*     */           {
/* 457 */             if (canColliableOnly) {
/*     */               
/* 459 */               if (block.func_149678_a(0, true))
/*     */               {
/* 461 */                 return block;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 466 */               return block;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 473 */     return W_Blocks.field_150350_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 getYawPitchFromVec(Vec3 v) {
/* 478 */     return getYawPitchFromVec(v.field_72450_a, v.field_72448_b, v.field_72449_c);
/*     */   }
/*     */   
/*     */   public static Vec3 getYawPitchFromVec(double x, double y, double z) {
/* 482 */     double p = MathHelper.func_76133_a(x * x + z * z);
/* 483 */     float yaw = (float)(Math.atan2(z, x) * 180.0D / Math.PI);
/* 484 */     float roll = (float)(Math.atan2(y, p) * 180.0D / Math.PI);
/* 485 */     return Vec3.func_72443_a(0.0D, yaw, roll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getAlpha(int argb) {
/* 492 */     return (argb >> 24) / 255.0F;
/*     */   }
/*     */   
/*     */   public static float getRed(int argb) {
/* 496 */     return (argb >> 16 & 0xFF) / 255.0F;
/*     */   }
/*     */   
/*     */   public static float getGreen(int argb) {
/* 500 */     return (argb >> 8 & 0xFF) / 255.0F;
/*     */   }
/*     */   
/*     */   public static float getBlue(int argb) {
/* 504 */     return (argb & 0xFF) / 255.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void enableFirstPersonItemRender() {
/* 510 */     switch (MCH_Config.DisableItemRender.prmInt) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 516 */         MCH_ItemRendererDummy.disableDummyItemRenderer();
/*     */         break;
/*     */       
/*     */       case 3:
/* 520 */         W_Reflection.restoreCameraZoom();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void disableFirstPersonItemRender(ItemStack itemStack) {
/* 526 */     if (itemStack != null && itemStack.func_77973_b() instanceof net.minecraft.item.ItemMapBase)
/*     */     {
/* 528 */       if (!(W_McClient.getRenderEntity() instanceof mcheli.MCH_ViewEntityDummy)) {
/*     */         return;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 535 */     disableFirstPersonItemRender();
/*     */   }
/*     */   
/*     */   public static void disableFirstPersonItemRender() {
/* 539 */     switch (MCH_Config.DisableItemRender.prmInt) {
/*     */ 
/*     */       
/*     */       case 1:
/* 543 */         W_Reflection.setItemRenderer_ItemToRender(new ItemStack((Item)MCH_MOD.invisibleItem));
/*     */         break;
/*     */       
/*     */       case 2:
/* 547 */         MCH_ItemRendererDummy.enableDummyItemRenderer();
/*     */         break;
/*     */       
/*     */       case 3:
/* 551 */         W_Reflection.setCameraZoom(1.01F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity getClientPlayer() {
/* 558 */     return MCH_MOD.proxy.getClientPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setRenderViewEntity(EntityLivingBase entity) {
/* 563 */     if (MCH_Config.ReplaceRenderViewEntity.prmBool)
/*     */     {
/* 565 */       W_McClient.setRenderEntity(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Item, Integer> getItemMapFromRecipe(IRecipe recipe) {
/* 572 */     Map<Item, Integer> map = new HashMap<Item, Integer>();
/*     */     
/* 574 */     if (recipe instanceof ShapedRecipes) {
/*     */       
/* 576 */       for (ItemStack is : ((ShapedRecipes)recipe).field_77574_d) {
/*     */         
/* 578 */         if (is != null) {
/*     */           
/* 580 */           Item item = is.func_77973_b();
/* 581 */           if (map.containsKey(item))
/*     */           {
/* 583 */             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() + 1));
/*     */           }
/*     */           else
/*     */           {
/* 587 */             map.put(item, Integer.valueOf(1));
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 592 */     } else if (recipe instanceof ShapelessRecipes) {
/*     */       
/* 594 */       for (Object o : ((ShapelessRecipes)recipe).field_77579_b) {
/*     */         
/* 596 */         ItemStack is = (ItemStack)o;
/* 597 */         if (is != null) {
/*     */           
/* 599 */           Item item = is.func_77973_b();
/* 600 */           if (map.containsKey(item)) {
/*     */             
/* 602 */             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() + 1));
/*     */             
/*     */             continue;
/*     */           } 
/* 606 */           map.put(item, Integer.valueOf(1));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 612 */     return map;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Lib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */