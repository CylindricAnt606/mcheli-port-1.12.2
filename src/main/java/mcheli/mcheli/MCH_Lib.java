 package mcheli.mcheli;

 import java.io.File;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_ItemRendererDummy;
 import mcheli.mcheli.MCH_MOD;
 import mcheli.mcheli.MCH_Vector2;
 import mcheli.mcheli.eval.util.Vec3;
 import mcheli.mcheli.wrapper.W_Block;
 import mcheli.mcheli.wrapper.W_Blocks;
 import mcheli.mcheli.wrapper.W_McClient;
 import mcheli.mcheli.wrapper.W_Reflection;
 import mcheli.mcheli.wrapper.W_Vec3;
 import mcheli.mcheli.wrapper.W_WorldFunc;
 import net.minecraft.block.Block;
 import net.minecraft.block.material.Material;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityLivingBase;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraft.entity.player.InventoryPlayer;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.ShapedRecipes;
 import net.minecraft.item.crafting.ShapelessRecipes;
 import net.minecraft.world.World;


 //TODO: Complete Mess (Important file)
 public class MCH_Lib {
   private static HashMap<String, Material> mapMaterial = new HashMap<String, Material>();

   public static void init() {
     mapMaterial.clear();
     mapMaterial.put("air", Material.AIR);
     mapMaterial.put("grass", Material.GRASS);
     mapMaterial.put("ground", Material.GROUND);
     mapMaterial.put("wood", Material.WOOD);
     mapMaterial.put("rock", Material.ROCK);
     mapMaterial.put("iron", Material.IRON);
     mapMaterial.put("anvil", Material.ANVIL);
     mapMaterial.put("water", Material.WATER);
     mapMaterial.put("lava", Material.LAVA);
     mapMaterial.put("leaves", Material.LEAVES);
     mapMaterial.put("plants", Material.PLANTS);
     mapMaterial.put("vine", Material.VINE);
     mapMaterial.put("sponge", Material.SPONGE);
     mapMaterial.put("cloth", Material.CLOTH);
     mapMaterial.put("fire", Material.FIRE);
     mapMaterial.put("sand", Material.SAND);
     mapMaterial.put("circuits", Material.CIRCUITS);
     mapMaterial.put("carpet", Material.CARPET);
     mapMaterial.put("glass", Material.GLASS);
     mapMaterial.put("redstoneLight", Material.REDSTONE_LIGHT);
     mapMaterial.put("tnt", Material.TNT);
     mapMaterial.put("coral", Material.CORAL);
     mapMaterial.put("ice", Material.ICE);
     mapMaterial.put("packedIce", Material.PACKED_ICE);
     mapMaterial.put("snow", Material.SNOW);
     mapMaterial.put("craftedSnow", Material.CRAFTED_SNOW);
     mapMaterial.put("cactus", Material.CACTUS);
     mapMaterial.put("clay", Material.CLAY);
     mapMaterial.put("gourd", Material.GOURD);
     mapMaterial.put("dragonEgg", Material.DRAGON_EGG);
     mapMaterial.put("portal", Material.PORTAL);
     mapMaterial.put("cake", Material.CAKE);
     mapMaterial.put("web", Material.WEB);
     mapMaterial.put("piston", Material.PISTON);
   }


   public static Material getMaterialFromName(String name) {
     if (mapMaterial.containsKey(name))
     {
       return mapMaterial.get(name);
     }
     return null;
   }


   public static Vec3 calculateFaceNormal(Vec3[] vertices) {
     Vec3 v1 = Vec3.func_72443_a((vertices[1]).field_72450_a - (vertices[0]).field_72450_a, (vertices[1]).field_72448_b - (vertices[0]).field_72448_b, (vertices[1]).field_72449_c - (vertices[0]).field_72449_c);



     Vec3 v2 = Vec3.func_72443_a((vertices[2]).field_72450_a - (vertices[0]).field_72450_a, (vertices[2]).field_72448_b - (vertices[0]).field_72448_b, (vertices[2]).field_72449_c - (vertices[0]).field_72449_c);




     return v1.func_72431_c(v2).func_72432_b();
   }



   public static double parseDouble(String s) {
     return (s == null) ? 0.0D : Double.parseDouble(s.replace(',', '.'));
   }


   public static float RNG(float a, float min, float max) {
     return (a < min) ? min : ((a > max) ? max : a);
   }

   public static double RNG(double a, double min, double max) {
     return (a < min) ? min : ((a > max) ? max : a);
   }


   public static float smooth(float rot, float prevRot, float tick) {
     return prevRot + (rot - prevRot) * tick;
   }


   public static float smoothRot(float rot, float prevRot, float tick) {
     if (rot - prevRot < -180.0F) { prevRot -= 360.0F; }
     else if (prevRot - rot < -180.0F) { prevRot += 360.0F; }

     return prevRot + (rot - prevRot) * tick;
   }


   public static double getRotateDiff(double base, double target) {
     base = getRotate360(base);
     target = getRotate360(target);

     if (target - base < -180.0D) {

       target += 360.0D;
     }
     else if (target - base > 180.0D) {

       base += 360.0D;
     }

     return target - base;
   }



   public static float getPosAngle(double tx, double tz, double cx, double cz) {
     double length_A = Math.sqrt(tx * tx + tz * tz);
     double length_B = Math.sqrt(cx * cx + cz * cz);


     double cos_sita = (tx * cx + tz * cz) / length_A * length_B;


     double sita = Math.acos(cos_sita);

     return (float)(sita * 180.0D / Math.PI);
   }



   public static boolean canPlayerCreateItem(IRecipe recipe, InventoryPlayer inventory) {
     if (recipe != null) {

       Map<Item, Integer> map = getItemMapFromRecipe(recipe);

       for (int i = 0; i < inventory.func_70302_i_(); i++) {

         ItemStack is = inventory.func_70301_a(i);
         if (is != null) {

           Item item = is.func_77973_b();
           if (map.containsKey(item))
           {
             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() - is.field_77994_a));
           }
         }
       }

       for (Iterator<Integer> i$ = map.values().iterator(); i$.hasNext(); ) { int j = ((Integer)i$.next()).intValue();

         if (j > 0) return false;
          }

       return true;
     }

     return false;
   }


   public static void applyEntityHurtResistantTimeConfig(Entity entity) {
     if (entity instanceof EntityLivingBase) {

       EntityLivingBase elb = (EntityLivingBase)entity;
       double h_time = MCH_Config.HurtResistantTime.prmDouble * elb.field_70172_ad;
       elb.field_70172_ad = (int)h_time;
     }
   }


   public static int round(double d) {
     return (int)(d + 0.5D);
   }


   public static Vec3 Rot2Vec3(float yaw, float pitch) {
     return Vec3.func_72443_a((-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)), -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F), (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)));
   }






   public static Vec3 RotVec3(double x, double y, double z, float yaw, float pitch) {
     Vec3 v = Vec3.func_72443_a(x, y, z);
     v.func_72440_a(pitch / 180.0F * 3.1415927F);
     v.func_72442_b(yaw / 180.0F * 3.1415927F);
     return v;
   }

   public static Vec3 RotVec3(double x, double y, double z, float yaw, float pitch, float roll) {
     Vec3 v = Vec3.func_72443_a(x, y, z);
     W_Vec3.rotateAroundZ(roll / 180.0F * 3.1415927F, v);
     v.func_72440_a(pitch / 180.0F * 3.1415927F);
     v.func_72442_b(yaw / 180.0F * 3.1415927F);
     return v;
   }


   public static Vec3 RotVec3(Vec3 vin, float yaw, float pitch) {
     Vec3 v = Vec3.func_72443_a(vin.field_72450_a, vin.field_72448_b, vin.field_72449_c);
     v.func_72440_a(pitch / 180.0F * 3.1415927F);
     v.func_72442_b(yaw / 180.0F * 3.1415927F);
     return v;
   }

   public static Vec3 RotVec3(Vec3 vin, float yaw, float pitch, float roll) {
     Vec3 v = Vec3.func_72443_a(vin.field_72450_a, vin.field_72448_b, vin.field_72449_c);
     W_Vec3.rotateAroundZ(roll / 180.0F * 3.1415927F, v);
     v.func_72440_a(pitch / 180.0F * 3.1415927F);
     v.func_72442_b(yaw / 180.0F * 3.1415927F);
     return v;
   }



   public static Vec3 _Rot2Vec3(float yaw, float pitch, float roll) {
     return Vec3.func_72443_a((-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)), -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F), (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F)));
   }






   public static double getRotate360(double r) {
     r %= 360.0D;
     return (r >= 0.0D) ? r : (r + 360.0D);
   }


   public static void Log(String format, Object... data) {
     String side = MCH_MOD.proxy.isRemote() ? "[Client]" : "[Server]";

     System.out.printf("[" + getTime() + "][" + "mcheli" + "]" + side + " " + format + "\n", data);
   }

   public static void Log(World world, String format, Object... data) {
     if (world != null) { Log((world.field_72995_K ? "[ClientWorld]" : "[ServerWorld]") + " " + format, data); }
     else { Log("[UnknownWorld]" + format, data); }

   }
   public static void Log(Entity entity, String format, Object... data) {
     if (entity != null) { Log(entity.field_70170_p, format, data); }
     else { Log((World)null, format, data); }

   }

   public static void DbgLog(boolean isRemote, String format, Object... data) {
     if (MCH_Config.DebugLog) {

       String t = getTime();
       if (isRemote) {

         String playerName = "null";
         if (getClientPlayer() instanceof EntityPlayer)
         {
           playerName = ((EntityPlayer)getClientPlayer()).getDisplayName();
         }
         System.out.println(String.format(format, data));
       }
       else {

         System.out.println(String.format(format, data));
       }
     }
   }

   public static void DbgLog(World w, String format, Object... data) {
     DbgLog(w.field_72995_K, format, data);
   }


   public static String getTime() {
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

     return sdf.format(new Date());
   }


   public static final String[] AZIMUTH_8 = new String[] { "S", "SW", "W", "NW", "N", "NE", "E", "SE" };









   public static final int AZIMUTH_8_ANG = 360 / AZIMUTH_8.length;

   public static String getAzimuthStr8(int dir) {
     dir %= 360;
     if (dir < 0) dir += 360;
     dir /= AZIMUTH_8_ANG;
     return AZIMUTH_8[dir];
   }















   public static void rotatePoints(double[] points, float r) {
     r = r / 180.0F * 3.1415927F;
     for (int i = 0; i + 1 < points.length; i += 2) {

       double x = points[i + 0];
       double y = points[i + 1];
       points[i + 0] = x * MathHelper.func_76134_b(r) - y * MathHelper.func_76126_a(r);
       points[i + 1] = x * MathHelper.func_76126_a(r) + y * MathHelper.func_76134_b(r);
     }
   }









   public static void rotatePoints(ArrayList<MCH_Vector2> points, float r) {
     r = r / 180.0F * 3.1415927F;
     for (int i = 0; i + 1 < points.size(); i += 2) {

       double x = ((MCH_Vector2)points.get(i + 0)).x;
       double y = ((MCH_Vector2)points.get(i + 0)).y;
       ((MCH_Vector2)points.get(i + 0)).x = x * MathHelper.func_76134_b(r) - y * MathHelper.func_76126_a(r);
       ((MCH_Vector2)points.get(i + 0)).y = x * MathHelper.func_76126_a(r) + y * MathHelper.func_76134_b(r);
     }
   }


   public static String[] listupFileNames(String path) {
     File dir = new File(path);
     return dir.list();
   }


   public static boolean isBlockInWater(World w, int x, int y, int z) {
     int[][] offset = { { 0, -1, 0 }, { 0, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 }, { 0, 1, 0 } };








     if (y <= 0) return false;
     for (int[] o : offset) {

       if (W_WorldFunc.isBlockWater(w, x + o[0], y + o[1], z + o[2]))
       {
         return true;
       }
     }
     return false;
   }


   public static int getBlockIdY(World w, double posX, double posY, double posZ, int size, int lenY, boolean canColliableOnly) {
     Block block = getBlockY(w, posX, posY, posZ, size, lenY, canColliableOnly);
     if (block == null) return 0;
     return W_Block.func_149682_b(block);
   }

   public static int getBlockIdY(Entity entity, int size, int lenY) {
     return getBlockIdY(entity, size, lenY, true);
   }

   public static int getBlockIdY(Entity entity, int size, int lenY, boolean canColliableOnly) {
     Block block = getBlockY(entity, size, lenY, canColliableOnly);
     if (block == null) return 0;
     return W_Block.func_149682_b(block);
   }


   public static Block getBlockY(Entity entity, int size, int lenY, boolean canColliableOnly) {
     return getBlockY(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, size, lenY, canColliableOnly);
   }

   public static Block getBlockY(World world, Vec3 pos, int size, int lenY, boolean canColliableOnly) {
     return getBlockY(world, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, size, lenY, canColliableOnly);
   }






   public static Block getBlockY(World world, double posX, double posY, double posZ, int size, int lenY, boolean canColliableOnly) {
     if (lenY == 0) return W_Blocks.field_150350_a;

     int px = (int)(posX + 0.5D);
     int py = (int)(posY + 0.5D);
     int pz = (int)(posZ + 0.5D);

     int cntY = (lenY > 0) ? lenY : -lenY;

     for (int y = 0; y < cntY; y++) {

       if (py + y < 0 || py + y > 255) return W_Blocks.field_150350_a;

       for (int x = -size / 2; x <= size / 2; x++) {

         for (int z = -size / 2; z <= size / 2; z++) {

           Block block = W_WorldFunc.getBlock(world, px + x, py + ((lenY > 0) ? y : -y), pz + z);
           if (block != null && block != W_Blocks.field_150350_a)
           {
             if (canColliableOnly) {

               if (block.func_149678_a(0, true))
               {
                 return block;
               }
             }
             else {

               return block;
             }
           }
         }
       }
     }

     return W_Blocks.field_150350_a;
   }


   public static Vec3 getYawPitchFromVec(Vec3 v) {
     return getYawPitchFromVec(v.field_72450_a, v.field_72448_b, v.field_72449_c);
   }

   public static Vec3 getYawPitchFromVec(double x, double y, double z) {
     double p = MathHelper.func_76133_a(x * x + z * z);
     float yaw = (float)(Math.atan2(z, x) * 180.0D / Math.PI);
     float roll = (float)(Math.atan2(y, p) * 180.0D / Math.PI);
     return Vec3.func_72443_a(0.0D, yaw, roll);
   }




   public static float getAlpha(int argb) {
     return (argb >> 24) / 255.0F;
   }

   public static float getRed(int argb) {
     return (argb >> 16 & 0xFF) / 255.0F;
   }

   public static float getGreen(int argb) {
     return (argb >> 8 & 0xFF) / 255.0F;
   }

   public static float getBlue(int argb) {
     return (argb & 0xFF) / 255.0F;
   }



   public static void enableFirstPersonItemRender() {
     switch (MCH_Config.DisableItemRender.prmInt) {




       case 2:
         MCH_ItemRendererDummy.disableDummyItemRenderer();
         break;

       case 3:
         W_Reflection.restoreCameraZoom();
         break;
     }
   }

   public static void disableFirstPersonItemRender(ItemStack itemStack) {
     if (itemStack != null && itemStack.func_77973_b() instanceof net.minecraft.item.ItemMapBase)
     {
       if (!(W_McClient.getRenderEntity() instanceof mcheli.MCH_ViewEntityDummy)) {
         return;
       }
     }



     disableFirstPersonItemRender();
   }

   public static void disableFirstPersonItemRender() {
     switch (MCH_Config.DisableItemRender.prmInt) {


       case 1:
         W_Reflection.setItemRenderer_ItemToRender(new ItemStack((Item)MCH_MOD.invisibleItem));
         break;

       case 2:
         MCH_ItemRendererDummy.enableDummyItemRenderer();
         break;

       case 3:
         W_Reflection.setCameraZoom(1.01F);
         break;
     }
   }


   public static Entity getClientPlayer() {
     return MCH_MOD.proxy.getClientPlayer();
   }


   public static void setRenderViewEntity(EntityLivingBase entity) {
     if (MCH_Config.ReplaceRenderViewEntity.prmBool)
     {
       W_McClient.setRenderEntity(entity);
     }
   }



   public static Map<Item, Integer> getItemMapFromRecipe(IRecipe recipe) {
     Map<Item, Integer> map = new HashMap<Item, Integer>();

     if (recipe instanceof ShapedRecipes) {

       for (ItemStack is : ((ShapedRecipes)recipe).field_77574_d) {

         if (is != null) {

           Item item = is.func_77973_b();
           if (map.containsKey(item))
           {
             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() + 1));
           }
           else
           {
             map.put(item, Integer.valueOf(1));
           }

         }
       }
     } else if (recipe instanceof ShapelessRecipes) {

       for (Object o : ((ShapelessRecipes)recipe).field_77579_b) {

         ItemStack is = (ItemStack)o;
         if (is != null) {

           Item item = is.func_77973_b();
           if (map.containsKey(item)) {

             map.put(item, Integer.valueOf(((Integer)map.get(item)).intValue() + 1));

             continue;
           }
           map.put(item, Integer.valueOf(1));
         }
       }
     }


     return map;
   }
 }
