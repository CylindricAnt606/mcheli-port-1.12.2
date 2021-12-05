/*     */ package mcheli.mcheli;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.util.ArrayList;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_IRecipeList;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_RecipeFuel;
/*     */ import mcheli.MCH_RecipeReloadRangeFinder;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.throwable.MCH_ThrowableInfo;
/*     */ import mcheli.throwable.MCH_ThrowableInfoManager;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.ShapedRecipes;
/*     */ import net.minecraft.item.crafting.ShapelessRecipes;
/*     */ 
/*     */ public class MCH_ItemRecipe implements MCH_IRecipeList {
/*  31 */   private static final mcheli.MCH_ItemRecipe instance = new mcheli.MCH_ItemRecipe(); public static mcheli.MCH_ItemRecipe getInstance() {
/*  32 */     return instance;
/*     */   }
/*  34 */   private static List<IRecipe> commonItemRecipe = new ArrayList<IRecipe>();
/*     */   
/*     */   public int getRecipeListSize() {
/*  37 */     return commonItemRecipe.size();
/*     */   }
/*     */   
/*     */   public IRecipe getRecipe(int index) {
/*  41 */     return commonItemRecipe.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addRecipeList(IRecipe recipe) {
/*  46 */     if (recipe != null) commonItemRecipe.add(recipe);
/*     */   
/*     */   }
/*     */   
/*     */   private static void registerCommonItemRecipe() {
/*  51 */     commonItemRecipe.clear();
/*     */     
/*  53 */     GameRegistry.addRecipe((IRecipe)new MCH_RecipeFuel());
/*     */ 
/*     */     
/*  56 */     addRecipeList(addRecipe((Item)MCH_MOD.itemFuel, MCH_Config.ItemRecipe_Fuel.prmString));
/*  57 */     addRecipeList(addRecipe((Item)MCH_MOD.itemGLTD, MCH_Config.ItemRecipe_GLTD.prmString));
/*  58 */     addRecipeList(addRecipe((Item)MCH_MOD.itemChain, MCH_Config.ItemRecipe_Chain.prmString));
/*  59 */     addRecipeList(addRecipe((Item)MCH_MOD.itemParachute, MCH_Config.ItemRecipe_Parachute.prmString));
/*  60 */     addRecipeList(addRecipe((Item)MCH_MOD.itemContainer, MCH_Config.ItemRecipe_Container.prmString));
/*  61 */     for (int i = 0; i < MCH_MOD.itemUavStation.length; i++)
/*  62 */       addRecipeList(addRecipe((Item)MCH_MOD.itemUavStation[i], (MCH_Config.ItemRecipe_UavStation[i]).prmString)); 
/*  63 */     addRecipeList(addRecipe((Item)MCH_MOD.itemWrench, MCH_Config.ItemRecipe_Wrench.prmString));
/*  64 */     addRecipeList(addRecipe((Item)MCH_MOD.itemRangeFinder, MCH_Config.ItemRecipe_RangeFinder.prmString));
/*  65 */     GameRegistry.addRecipe((IRecipe)new MCH_RecipeReloadRangeFinder());
/*  66 */     addRecipeList(addRecipe((Item)MCH_MOD.itemStinger, MCH_Config.ItemRecipe_Stinger.prmString));
/*  67 */     addRecipeList(addRecipe((Item)MCH_MOD.itemStingerBullet, "2," + MCH_Config.ItemRecipe_StingerMissile.prmString));
/*  68 */     addRecipeList(addRecipe((Item)MCH_MOD.itemJavelin, MCH_Config.ItemRecipe_Javelin.prmString));
/*  69 */     addRecipeList(addRecipe((Item)MCH_MOD.itemJavelinBullet, "2," + MCH_Config.ItemRecipe_JavelinMissile.prmString));
/*  70 */     addRecipeList(addRecipe(W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable), MCH_Config.ItemRecipe_DraftingTable.prmString));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerItemRecipe() {
/*  76 */     registerCommonItemRecipe();
/*     */ 
/*     */ 
/*     */     
/*  80 */     for (String name : MCH_HeliInfoManager.map.keySet()) {
/*     */       
/*  82 */       MCH_HeliInfo info = (MCH_HeliInfo)MCH_HeliInfoManager.map.get(name);
/*  83 */       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_HeliInfoManager.getInstance());
/*     */     } 
/*  85 */     for (String name : MCP_PlaneInfoManager.map.keySet()) {
/*     */       
/*  87 */       MCP_PlaneInfo info = (MCP_PlaneInfo)MCP_PlaneInfoManager.map.get(name);
/*  88 */       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCP_PlaneInfoManager.getInstance());
/*     */     } 
/*  90 */     for (String name : MCH_TankInfoManager.map.keySet()) {
/*     */       
/*  92 */       MCH_TankInfo info = (MCH_TankInfo)MCH_TankInfoManager.map.get(name);
/*  93 */       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_TankInfoManager.getInstance());
/*     */     } 
/*  95 */     for (String name : MCH_VehicleInfoManager.map.keySet()) {
/*     */       
/*  97 */       MCH_VehicleInfo info = (MCH_VehicleInfo)MCH_VehicleInfoManager.map.get(name);
/*  98 */       addRecipeAndRegisterList((MCH_AircraftInfo)info, (Item)info.item, (MCH_AircraftInfoManager)MCH_VehicleInfoManager.getInstance());
/*     */     } 
/*     */     
/* 101 */     for (String name : MCH_ThrowableInfoManager.getKeySet()) {
/*     */       
/* 103 */       MCH_ThrowableInfo info = MCH_ThrowableInfoManager.get(name);
/* 104 */       for (String s : info.recipeString) {
/*     */         
/* 106 */         if (s.length() >= 3) {
/*     */           
/* 108 */           IRecipe recipe = addRecipe((Item)info.item, s, info.isShapedRecipe);
/* 109 */           info.recipe.add(recipe);
/*     */ 
/*     */           
/* 112 */           addRecipeList(recipe);
/*     */         } 
/*     */       } 
/* 115 */       info.recipeString = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addRecipeAndRegisterList(MCH_AircraftInfo info, Item item, MCH_AircraftInfoManager im) {
/* 121 */     int count = 0;
/* 122 */     for (String s : info.recipeString) {
/*     */       
/* 124 */       count++;
/* 125 */       if (s.length() >= 3) {
/*     */         
/* 127 */         IRecipe recipe = addRecipe(item, s, info.isShapedRecipe);
/* 128 */         info.recipe.add(recipe);
/* 129 */         im.addRecipe(recipe, count, info.name, s);
/*     */       } 
/*     */     } 
/* 132 */     info.recipeString = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRecipe addRecipe(Item item, String data) {
/* 139 */     return addShapedRecipe(item, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IRecipe addRecipe(Item item, String data, boolean isShaped) {
/* 144 */     if (isShaped)
/*     */     {
/* 146 */       return addShapedRecipe(item, data);
/*     */     }
/*     */ 
/*     */     
/* 150 */     return addShapelessRecipe(item, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRecipe addShapedRecipe(Item item, String data) {
/* 157 */     ArrayList<Object> rcp = new ArrayList();
/* 158 */     String[] s = data.split("\\s*,\\s*");
/* 159 */     if (s.length < 3) return null;
/*     */     
/* 161 */     int start = 0;
/* 162 */     int createNum = 1;
/* 163 */     if (isNumber(s[0])) {
/*     */       
/* 165 */       start = 1;
/* 166 */       createNum = Integer.valueOf(s[0]).intValue();
/* 167 */       if (createNum <= 0)
/*     */       {
/* 169 */         createNum = 1;
/*     */       }
/*     */     } 
/*     */     
/* 173 */     int idx = start;
/* 174 */     for (int i = start; i < 3 + start; i++) {
/*     */       
/* 176 */       if (s[idx].length() > 0 && s[idx].charAt(0) == '"' && s[idx].charAt(s[idx].length() - 1) == '"') {
/*     */         
/* 178 */         rcp.add(s[idx].subSequence(1, s[idx].length() - 1));
/* 179 */         idx++;
/*     */       } 
/*     */     } 
/* 182 */     if (idx == 0) return null; 
/* 183 */     boolean isChar = true;
/* 184 */     for (; idx < s.length; idx++) {
/*     */       
/* 186 */       if (s[idx].length() <= 0) return null; 
/* 187 */       if (isChar) {
/*     */         
/* 189 */         if (s[idx].length() != 1) return null; 
/* 190 */         char c = s[idx].toUpperCase().charAt(0);
/* 191 */         if (c < 'A' || c > 'Z') return null;
/*     */ 
/*     */         
/* 194 */         rcp.add(Character.valueOf(c));
/*     */       }
/*     */       else {
/*     */         
/* 198 */         String nm = s[idx].trim().toLowerCase();
/*     */         
/* 200 */         int dmg = 0;
/* 201 */         if (idx + 1 < s.length && isNumber(s[idx + 1])) {
/*     */           
/* 203 */           idx++;
/* 204 */           dmg = Integer.parseInt(s[idx]);
/*     */         } 
/*     */ 
/*     */         
/* 208 */         if (isNumber(nm))
/*     */         {
/* 210 */           return null;
/*     */         }
/*     */ 
/*     */         
/* 214 */         rcp.add(new ItemStack(W_Item.getItemByName(nm), 1, dmg));
/*     */       } 
/*     */       
/* 217 */       isChar = !isChar;
/*     */     } 
/* 219 */     Object[] recipe = new Object[rcp.size()];
/* 220 */     for (int j = 0; j < recipe.length; j++)
/*     */     {
/* 222 */       recipe[j] = rcp.get(j);
/*     */     }
/*     */     
/* 225 */     ShapedRecipes r = (ShapedRecipes)GameRegistry.addShapedRecipe(new ItemStack(item, createNum), recipe);
/* 226 */     for (int k = 0; k < r.field_77574_d.length; k++) {
/*     */       
/* 228 */       if (r.field_77574_d[k] != null)
/*     */       {
/*     */         
/* 231 */         if (r.field_77574_d[k].func_77973_b() == null)
/*     */         {
/* 233 */           throw new RuntimeException("Error: Invalid ShapedRecipes! " + item + " : " + data);
/*     */         }
/*     */       }
/*     */     } 
/* 237 */     return (IRecipe)r;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRecipe addShapelessRecipe(Item item, String data) {
/* 243 */     ArrayList<Object> rcp = new ArrayList();
/* 244 */     String[] s = data.split("\\s*,\\s*");
/* 245 */     if (s.length < 1) return null;
/*     */     
/* 247 */     int start = 0;
/* 248 */     int createNum = 1;
/* 249 */     if (isNumber(s[0]))
/*     */     {
/*     */ 
/*     */       
/* 253 */       if (createNum <= 0)
/*     */       {
/* 255 */         createNum = 1;
/*     */       }
/*     */     }
/*     */     
/* 259 */     for (int idx = start; idx < s.length; idx++) {
/*     */       
/* 261 */       if (s[idx].length() <= 0) return null;
/*     */       
/* 263 */       String nm = s[idx].trim().toLowerCase();
/*     */       
/* 265 */       int dmg = 0;
/* 266 */       if (idx + 1 < s.length && isNumber(s[idx + 1])) {
/*     */         
/* 268 */         idx++;
/* 269 */         dmg = Integer.parseInt(s[idx]);
/*     */       } 
/*     */ 
/*     */       
/* 273 */       if (isNumber(nm)) {
/*     */         
/* 275 */         int n = Integer.parseInt(nm);
/* 276 */         if (n <= 255) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
/* 277 */         else if (n <= 511) { rcp.add(new ItemStack(W_Item.getItemById(n), 1, dmg)); }
/* 278 */         else if (n <= 2255) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
/* 279 */         else if (n <= 2267) { rcp.add(new ItemStack(W_Item.getItemById(n), 1, dmg)); }
/* 280 */         else if (n <= 4095) { rcp.add(new ItemStack(W_Block.getBlockById(n), 1, dmg)); }
/* 281 */         else if (n <= 31999) { rcp.add(new ItemStack(W_Item.getItemById(n + 256), 1, dmg)); }
/*     */       
/*     */       } else {
/*     */         
/* 285 */         rcp.add(new ItemStack(W_Item.getItemByName(nm), 1, dmg));
/*     */       } 
/*     */     } 
/* 288 */     Object[] recipe = new Object[rcp.size()];
/* 289 */     for (int i = 0; i < recipe.length; i++)
/*     */     {
/* 291 */       recipe[i] = rcp.get(i);
/*     */     }
/*     */     
/* 294 */     ShapelessRecipes r = getShapelessRecipe(new ItemStack(item, createNum), recipe);
/* 295 */     for (int j = 0; j < r.field_77579_b.size(); j++) {
/*     */       
/* 297 */       ItemStack is = r.field_77579_b.get(j);
/*     */       
/* 299 */       if (is.func_77973_b() == null)
/*     */       {
/* 301 */         throw new RuntimeException("Error: Invalid ShapelessRecipes! " + item + " : " + data);
/*     */       }
/*     */     } 
/* 304 */     GameRegistry.addRecipe((IRecipe)r);
/* 305 */     return (IRecipe)r;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ShapelessRecipes getShapelessRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
/* 310 */     ArrayList<ItemStack> arraylist = new ArrayList();
/* 311 */     Object[] aobject = par2ArrayOfObj;
/* 312 */     int i = par2ArrayOfObj.length;
/*     */     
/* 314 */     for (int j = 0; j < i; j++) {
/*     */       
/* 316 */       Object object1 = aobject[j];
/*     */       
/* 318 */       if (object1 instanceof ItemStack) {
/*     */         
/* 320 */         arraylist.add(((ItemStack)object1).func_77946_l());
/*     */       }
/* 322 */       else if (object1 instanceof Item) {
/*     */         
/* 324 */         arraylist.add(new ItemStack((Item)object1));
/*     */       }
/*     */       else {
/*     */         
/* 328 */         if (!(object1 instanceof Block))
/*     */         {
/* 330 */           throw new RuntimeException("Invalid shapeless recipy!");
/*     */         }
/*     */         
/* 333 */         arraylist.add(new ItemStack((Block)object1));
/*     */       } 
/*     */     } 
/*     */     
/* 337 */     return new ShapelessRecipes(par1ItemStack, arraylist);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNumber(String s) {
/* 342 */     if (s == null || s.isEmpty()) return false; 
/* 343 */     byte[] buf = s.getBytes();
/* 344 */     for (byte b : buf) { if (b < 48 || b > 57) return false;  }
/* 345 */      return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ItemRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */