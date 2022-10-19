/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import mcheli.MCH_IRecipeList;
/*    */ import mcheli.MCH_InfoManagerBase;
/*    */ import mcheli.aircraft.MCH_AircraftInfo;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ 
/*    */ public abstract class MCH_AircraftInfoManager
/*    */   extends MCH_InfoManagerBase
/*    */   implements MCH_IRecipeList {
/* 15 */   private List<IRecipe> listItemRecipe = new ArrayList<IRecipe>();
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRecipeListSize() {
/* 20 */     return this.listItemRecipe.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IRecipe getRecipe(int index) {
/* 26 */     return this.listItemRecipe.get(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRecipe(IRecipe recipe, int count, String name, String recipeString) {
/* 31 */     if (recipe == null || recipe.func_77571_b() == null || recipe.func_77571_b().func_77973_b() == null)
/*    */     {
/* 33 */       throw new RuntimeException("[mcheli]Recipe Parameter Error! recipe" + count + " : " + name + ".txt : " + String.valueOf(recipe) + " : " + recipeString);
/*    */     }
/* 35 */     this.listItemRecipe.add(recipe);
/*    */   }
/*    */   
/*    */   public abstract MCH_AircraftInfo getAcInfoFromItem(Item paramItem);
/*    */   
/*    */   public MCH_AircraftInfo getAcInfoFromItem(IRecipe recipe) {
/* 41 */     Map<String, MCH_AircraftInfo> map = getMap();
/* 42 */     if (recipe != null)
/*    */     {
/* 44 */       return getAcInfoFromItem(recipe.func_77571_b().func_77973_b());
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftInfoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */