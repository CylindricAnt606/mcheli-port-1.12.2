 package mcheli.mcheli;
 import cpw.mods.fml.common.registry.GameRegistry;
 import java.util.ArrayList;
 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_IRecipeList;
 import mcheli.mcheli.MCH_MOD;
 import mcheli.mcheli.MCH_RecipeFuel;
 import mcheli.mcheli.MCH_RecipeReloadRangeFinder;
 import mcheli.mcheli.aircraft.MCH_AircraftInfo;
 import mcheli.mcheli.aircraft.MCH_AircraftInfoManager;
 import mcheli.mcheli.helicopter.MCH_HeliInfo;
 import mcheli.mcheli.helicopter.MCH_HeliInfoManager;
 import mcheli.mcheli.plane.MCP_PlaneInfo;
 import mcheli.mcheli.plane.MCP_PlaneInfoManager;
 import mcheli.mcheli.tank.MCH_TankInfo;
 import mcheli.mcheli.tank.MCH_TankInfoManager;
 import mcheli.mcheli.throwable.MCH_ThrowableInfo;
 import mcheli.mcheli.throwable.MCH_ThrowableInfoManager;
 import mcheli.mcheli.vehicle.MCH_VehicleInfo;
 import mcheli.mcheli.vehicle.MCH_VehicleInfoManager;
 import mcheli.mcheli.wrapper.W_Block;
 import mcheli.mcheli.wrapper.W_Item;
 import net.minecraft.block.Block;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraft.item.crafting.IRecipe;
 import net.minecraft.item.crafting.ShapedRecipes;
 import net.minecraft.item.crafting.ShapelessRecipes;

 public class MCH_ItemRecipe implements MCH_IRecipeList {
   private static final mcheli.MCH_ItemRecipe instance = new mcheli.MCH_ItemRecipe(); public static mcheli.MCH_ItemRecipe getInstance() {
     return instance;
   }
   private static List<IRecipe> commonItemRecipe = new ArrayList<IRecipe>();

   public int getRecipeListSize() {
     return commonItemRecipe.size();
   }

   public IRecipe getRecipe(int index) {
     return commonItemRecipe.get(index);
   }


   private static void addRecipeList(IRecipe recipe) {
     if (recipe != null) commonItemRecipe.add(recipe);

   }

   private static void registerCommonItemRecipe() {
     commonItemRecipe.clear();

     GameRegistry.addRecipe((IRecipe)new MCH_RecipeFuel());


     addRecipeList(addRecipe((Item)MCH_MOD.itemFuel, MCH_Config.ItemRecipe_Fuel.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemGLTD, MCH_Config.ItemRecipe_GLTD.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemChain, MCH_Config.ItemRecipe_Chain.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemParachute, MCH_Config.ItemRecipe_Parachute.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemContainer, MCH_Config.ItemRecipe_Container.prmString));
     for (int i = 0; i < MCH_MOD.itemUavStation.length; i++)
       addRecipeList(addRecipe((Item)MCH_MOD.itemUavStation[i], (MCH_Config.ItemRecipe_UavStation[i]).prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemWrench, MCH_Config.ItemRecipe_Wrench.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemRangeFinder, MCH_Config.ItemRecipe_RangeFinder.prmString));
     GameRegistry.addRecipe((IRecipe)new MCH_RecipeReloadRangeFinder());
     addRecipeList(addRecipe((Item)MCH_MOD.itemStinger, MCH_Config.ItemRecipe_Stinger.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemStingerBullet, "2," + MCH_Config.ItemRecipe_StingerMissile.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemJavelin, MCH_Config.ItemRecipe_Javelin.prmString));
     addRecipeList(addRecipe((Item)MCH_MOD.itemJavelinBullet, "2," + MCH_Config.ItemRecipe_JavelinMissile.prmString));
     addRecipeList(addRecipe(W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable), MCH_Config.ItemRecipe_DraftingTable.prmString));
   }



   public static void registerItemRecipe() {
     registerCommonItemRecipe();



     for (String name : MCH_HeliInfoManager.map.keySet()) {

       MCH_HeliInfo info = (MCH_HeliInfo)MCH_HeliInfoManager.map.get(name);
       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_HeliInfoManager.getInstance());
     }
     for (String name : MCP_PlaneInfoManager.map.keySet()) {

       MCP_PlaneInfo info = (MCP_PlaneInfo)MCP_PlaneInfoManager.map.get(name);
       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCP_PlaneInfoManager.getInstance());
     }
     for (String name : MCH_TankInfoManager.map.keySet()) {

       MCH_TankInfo info = (MCH_TankInfo)MCH_TankInfoManager.map.get(name);
       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_TankInfoManager.getInstance());
     }
     for (String name : MCH_VehicleInfoManager.map.keySet()) {

       MCH_VehicleInfo info = (MCH_VehicleInfo)MCH_VehicleInfoManager.map.get(name);
       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_VehicleInfoManager.getInstance());
     }

     for (String name : MCH_ThrowableInfoManager.getKeySet()) {

       MCH_ThrowableInfo info = MCH_ThrowableInfoManager.get(name);
       for (String s : info.recipeString) {

         if (s.length() >= 3) {

           IRecipe recipe = addRecipe((Item)info.item, s, info.isShapedRecipe);
           info.recipe.add(recipe);


           addRecipeList(recipe);
         }
       }
       info.recipeString = null;
     }
   }


   private static void addRecipeAndRegisterList(MCH_AircraftInfo info, Item item, MCH_AircraftInfoManager im) {
     int count = 0;
     for (String s : info.recipeString) {

       count++;
       if (s.length() >= 3) {

         IRecipe recipe = addRecipe(item, s, info.isShapedRecipe);
         info.recipe.add(recipe);
         im.addRecipe(recipe, count, info.name, s);
       }
     }
     info.recipeString = null;
   }




   public static IRecipe addRecipe(Item item, String data) {
     return addShapedRecipe(item, data);
   }


   public static IRecipe addRecipe(Item item, String data, boolean isShaped) {
     if (isShaped)
     {
       return addShapedRecipe(item, data);
     }


     return addShapelessRecipe(item, data);
   }




   public static IRecipe addShapedRecipe(Item item, String data) {
     ArrayList<Object> rcp = new ArrayList();
     String[] s = data.split("\\s*,\\s*");
     if (s.length < 3) return null;

     int start = 0;
     int createNum = 1;
     if (isNumber(s[0])) {

       start = 1;
       createNum = Integer.valueOf(s[0]).intValue();
       if (createNum <= 0)
       {
         createNum = 1;
       }
     }

     int idx = start;
     for (int i = start; i < 3 + start; i++) {

       if (s[idx].length() > 0 && s[idx].charAt(0) == '"' && s[idx].charAt(s[idx].length() - 1) == '"') {

         rcp.add(s[idx].subSequence(1, s[idx].length() - 1));
         idx++;
       }
     }
     if (idx == 0) return null;
     boolean isChar = true;
     for (; idx < s.length; idx++) {

       if (s[idx].length() <= 0) return null;
       if (isChar) {

         if (s[idx].length() != 1) return null;
         char c = s[idx].toUpperCase().charAt(0);
         if (c < 'A' || c > 'Z') return null;


         rcp.add(Character.valueOf(c));
       }
       else {

         String nm = s[idx].trim().toLowerCase();

         int dmg = 0;
         if (idx + 1 < s.length && isNumber(s[idx + 1])) {

           idx++;
           dmg = Integer.parseInt(s[idx]);
         }


         if (isNumber(nm))
         {
           return null;
         }


         rcp.add(new ItemStack(W_Item.getItemByName(nm), 1, dmg));
       }

       isChar = !isChar;
     }
     Object[] recipe = new Object[rcp.size()];
     for (int j = 0; j < recipe.length; j++)
     {
       recipe[j] = rcp.get(j);
     }

     ShapedRecipes r = (ShapedRecipes)GameRegistry.addShapedRecipe(new ItemStack(item, createNum), recipe);
     for (int k = 0; k < r.field_77574_d.length; k++) {

       if (r.field_77574_d[k] != null)
       {

         if (r.field_77574_d[k].func_77973_b() == null)
         {
           throw new RuntimeException("Error: Invalid ShapedRecipes! " + item + " : " + data);
         }
       }
     }
     return (IRecipe)r;
   }



   public static IRecipe addShapelessRecipe(Item item, String data) {
     ArrayList<Object> rcp = new ArrayList();
     String[] s = data.split("\\s*,\\s*");
     if (s.length < 1) return null;

     int start = 0;
     int createNum = 1;
     if (isNumber(s[0]))
     {


       if (createNum <= 0)
       {
         createNum = 1;
       }
     }

     for (int idx = start; idx < s.length; idx++) {

       if (s[idx].length() <= 0) return null;

       String nm = s[idx].trim().toLowerCase();

       int dmg = 0;
       if (idx + 1 < s.length && isNumber(s[idx + 1])) {

         idx++;
         dmg = Integer.parseInt(s[idx]);
       }


       if (isNumber(nm)) {

         int n = Integer.parseInt(nm);
         if (n <= 255) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
         else if (n <= 511) { rcp.add(new ItemStack(W_Item.getItemById(n), 1, dmg)); }
         else if (n <= 2255) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
         else if (n <= 2267) { rcp.add(new ItemStack(W_Item.getItemById(n), 1, dmg)); }
         else if (n <= 4095) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
         else if (n <= 31999) { rcp.add(new ItemStack(W_Item.getItemById(n + 256), 1, dmg)); }

       } else {

         rcp.add(new ItemStack(W_Item.getItemByName(nm), 1, dmg));
       }
     }
     Object[] recipe = new Object[rcp.size()];
     for (int i = 0; i < recipe.length; i++)
     {
       recipe[i] = rcp.get(i);
     }

     ShapelessRecipes r = getShapelessRecipe(new ItemStack(item, createNum), recipe);
     for (int j = 0; j < r.field_77579_b.size(); j++) {

       ItemStack is = r.field_77579_b.get(j);

       if (is.func_77973_b() == null)
       {
         throw new RuntimeException("Error: Invalid ShapelessRecipes! " + item + " : " + data);
       }
     }
     GameRegistry.addRecipe((IRecipe)r);
     return (IRecipe)r;
   }


   public static ShapelessRecipes getShapelessRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
     ArrayList<ItemStack> arraylist = new ArrayList();
     Object[] aobject = par2ArrayOfObj;
     int i = par2ArrayOfObj.length;

     for (int j = 0; j < i; j++) {

       Object object1 = aobject[j];

       if (object1 instanceof ItemStack) {

         arraylist.add(((ItemStack)object1).func_77946_l());
       }
       else if (object1 instanceof Item) {

         arraylist.add(new ItemStack((Item)object1));
       }
       else {

         if (!(object1 instanceof Block))
         {
           throw new RuntimeException("Invalid shapeless recipy!");
         }

         arraylist.add(new ItemStack((Block)object1));
       }
     }

     return new ShapelessRecipes(par1ItemStack, arraylist);
   }


   public static boolean isNumber(String s) {
     if (s == null || s.isEmpty()) return false;
     byte[] buf = s.getBytes();
     for (byte b : buf) { if (b < 48 || b > 57) return false;  }
      return true;
   }
 }
