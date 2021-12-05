/*     */ package mcheli.mcheli.block;
/*     */ 
/*     */ import java.util.Map;
/*     */ import mcheli.MCH_IRecipeList;
/*     */ import mcheli.MCH_ItemRecipe;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_EntityPlayer;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCraftResult;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ 
/*     */ 
/*     */ public class MCH_DraftingTableGuiContainer
/*     */   extends Container
/*     */ {
/*     */   public final EntityPlayer player;
/*     */   public final int posX;
/*     */   public final int posY;
/*     */   public final int posZ;
/*     */   public final int outputSlotIndex;
/*  34 */   private IInventory outputSlot = (IInventory)new InventoryCraftResult();
/*     */ 
/*     */   
/*     */   public MCH_DraftingTableGuiContainer(EntityPlayer player, int posX, int posY, int posZ) {
/*  38 */     this.player = player;
/*  39 */     this.posX = posX;
/*  40 */     this.posY = posY;
/*  41 */     this.posZ = posZ;
/*     */ 
/*     */     
/*  44 */     for (int y = 0; y < 3; y++) {
/*     */       
/*  46 */       for (int i = 0; i < 9; i++)
/*     */       {
/*  48 */         func_75146_a(new Slot((IInventory)player.field_71071_by, 9 + i + y * 9, 30 + i * 18, 140 + y * 18));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  53 */     for (int x = 0; x < 9; x++)
/*     */     {
/*  55 */       func_75146_a(new Slot((IInventory)player.field_71071_by, x, 30 + x * 18, 198));
/*     */     }
/*     */     
/*  58 */     this.outputSlotIndex = this.field_75153_a.size();
/*  59 */     Object object = new Object(this, this.outputSlot, this.outputSlotIndex, 178, 90);
/*     */ 
/*     */ 
/*     */     
/*  63 */     func_75146_a((Slot)object);
/*     */     
/*  65 */     MCH_Lib.DbgLog(player.field_70170_p, "MCH_DraftingTableGuiContainer.MCH_DraftingTableGuiContainer", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  71 */     super.func_75142_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer player) {
/*  77 */     Block block = W_WorldFunc.getBlock(player.field_70170_p, this.posX, this.posY, this.posZ);
/*     */     
/*  79 */     if (W_Block.isEqual(block, (Block)MCH_MOD.blockDraftingTable) || W_Block.isEqual(block, (Block)MCH_MOD.blockDraftingTableLit))
/*     */     {
/*  81 */       return (player.func_70092_e(this.posX, this.posY, this.posZ) <= 144.0D);
/*     */     }
/*     */     
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
/*  90 */     ItemStack itemstack = null;
/*  91 */     Slot slot = this.field_75151_b.get(slotIndex);
/*     */     
/*  93 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  95 */       ItemStack itemstack1 = slot.func_75211_c();
/*  96 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  98 */       if (slotIndex == this.outputSlotIndex) {
/*     */         
/* 100 */         if (!func_75135_a(itemstack1, 0, 36, true))
/*     */         {
/* 102 */           return null;
/*     */         }
/*     */         
/* 105 */         slot.func_75220_a(itemstack1, itemstack);
/*     */       }
/*     */       else {
/*     */         
/* 109 */         return null;
/*     */       } 
/*     */       
/* 112 */       if (itemstack1.field_77994_a == 0) {
/*     */         
/* 114 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 118 */         slot.func_75218_e();
/*     */       } 
/*     */       
/* 121 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 123 */         return null;
/*     */       }
/*     */       
/* 126 */       slot.func_82870_a(player, itemstack1);
/*     */     } 
/*     */     
/* 129 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/* 134 */     super.func_75134_a(player);
/*     */     
/* 136 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 138 */       ItemStack itemstack = func_75139_a(this.outputSlotIndex).func_75211_c();
/*     */       
/* 140 */       if (itemstack != null)
/*     */       {
/* 142 */         W_EntityPlayer.dropPlayerItemWithRandomChoice(player, itemstack, false, false);
/*     */       }
/*     */     } 
/*     */     
/* 146 */     MCH_Lib.DbgLog(player.field_70170_p, "MCH_DraftingTableGuiContainer.onContainerClosed", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createRecipeItem(Item outputItem, Map<Item, Integer> map) {
/* 151 */     boolean isCreativeMode = this.player.field_71075_bZ.field_75098_d;
/* 152 */     if (func_75139_a(this.outputSlotIndex).func_75216_d() && !isCreativeMode) {
/*     */       
/* 154 */       MCH_Lib.DbgLog(this.player.field_70170_p, "MCH_DraftingTableGuiContainer.createRecipeItem:OutputSlot is not empty", new Object[0]);
/*     */       return;
/*     */     } 
/* 157 */     if (outputItem == null) {
/*     */       
/* 159 */       MCH_Lib.DbgLog(this.player.field_70170_p, "Error:MCH_DraftingTableGuiContainer.createRecipeItem:outputItem = null", new Object[0]);
/*     */       return;
/*     */     } 
/* 162 */     if (map == null || map.size() <= 0) {
/*     */       
/* 164 */       MCH_Lib.DbgLog(this.player.field_70170_p, "Error:MCH_DraftingTableGuiContainer.createRecipeItem:map is null : " + map, new Object[0]);
/*     */       
/*     */       return;
/*     */     } 
/* 168 */     ItemStack itemStack = new ItemStack(outputItem);
/* 169 */     boolean result = false;
/* 170 */     IRecipe recipe = null;
/* 171 */     MCH_IRecipeList[] recipeLists = { (MCH_IRecipeList)MCH_ItemRecipe.getInstance(), (MCH_IRecipeList)MCH_HeliInfoManager.getInstance(), (MCH_IRecipeList)MCP_PlaneInfoManager.getInstance(), (MCH_IRecipeList)MCH_VehicleInfoManager.getInstance(), (MCH_IRecipeList)MCH_TankInfoManager.getInstance() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     for (MCH_IRecipeList rl : recipeLists) {
/*     */       
/* 180 */       int index = searchRecipeFromList(rl, itemStack);
/* 181 */       if (index >= 0) {
/*     */         
/* 183 */         recipe = isValidRecipe(rl, itemStack, index, map);
/*     */         break;
/*     */       } 
/*     */     } 
/* 187 */     if (recipe != null)
/*     */     {
/* 189 */       if (isCreativeMode || MCH_Lib.canPlayerCreateItem(recipe, this.player.field_71071_by))
/*     */       {
/* 191 */         for (Item key : map.keySet()) {
/*     */           
/* 193 */           for (int i = 0; i < ((Integer)map.get(key)).intValue(); i++) {
/*     */             
/* 195 */             if (!isCreativeMode)
/*     */             {
/* 197 */               W_EntityPlayer.consumeInventoryItem(this.player, key);
/*     */             }
/* 199 */             func_75139_a(this.outputSlotIndex).func_75215_d(recipe.func_77571_b().func_77946_l());
/* 200 */             result = true;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/* 206 */     MCH_Lib.DbgLog(this.player.field_70170_p, "MCH_DraftingTableGuiContainer:Result=" + result + ":Recipe=" + recipe + " :" + outputItem.func_77658_a() + ": map=" + map, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IRecipe isValidRecipe(MCH_IRecipeList list, ItemStack itemStack, int startIndex, Map<Item, Integer> map) {
/* 213 */     for (int index = startIndex; index >= 0 && index < list.getRecipeListSize(); index++) {
/*     */       
/* 215 */       IRecipe recipe = list.getRecipe(index);
/* 216 */       if (itemStack.func_77969_a(recipe.func_77571_b())) {
/*     */         
/* 218 */         Map<Item, Integer> mapRecipe = MCH_Lib.getItemMapFromRecipe(recipe);
/* 219 */         boolean isEqual = true;
/* 220 */         for (Item key : map.keySet()) {
/*     */           
/* 222 */           if (!mapRecipe.containsKey(key) || mapRecipe.get(key) != map.get(key)) {
/*     */             
/* 224 */             isEqual = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 228 */         if (isEqual)
/*     */         {
/* 230 */           return recipe;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 235 */         return null;
/*     */       } 
/*     */     } 
/* 238 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int searchRecipeFromList(MCH_IRecipeList list, ItemStack item) {
/* 243 */     for (int i = 0; i < list.getRecipeListSize(); i++) {
/*     */       
/* 245 */       if (list.getRecipe(i).func_77571_b().func_77969_a(item))
/*     */       {
/* 247 */         return i;
/*     */       }
/*     */     } 
/* 250 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableGuiContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */