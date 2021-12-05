/*     */ package mcheli.mcheli.block;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_IRecipeList;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_AircraftInfoManager;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_CurrentRecipe
/*     */ {
/*     */   public final IRecipe recipe;
/*     */   public final int index;
/*     */   public final String displayName;
/*     */   public final List<ResourceLocation> descTexture;
/*     */   private final MCH_AircraftInfo acInfo;
/*     */   public List<String> infoItem;
/*     */   public List<String> infoData;
/*     */   private int descMaxPage;
/*     */   private int descPage;
/*     */   private W_ModelCustom model;
/*     */   public int modelRot;
/*     */   private ResourceLocation modelTexture;
/*     */   
/*     */   public MCH_CurrentRecipe(MCH_IRecipeList list, int idx) {
/*  38 */     if (list.getRecipeListSize() > 0) {
/*     */       
/*  40 */       this.recipe = list.getRecipe(idx);
/*     */     }
/*     */     else {
/*     */       
/*  44 */       this.recipe = null;
/*     */     } 
/*  46 */     this.index = idx;
/*  47 */     this.displayName = (this.recipe != null) ? this.recipe.func_77571_b().func_82833_r() : "None";
/*  48 */     this.descTexture = getDescTexture(this.recipe);
/*  49 */     this.descPage = 0;
/*  50 */     this.descMaxPage = this.descTexture.size();
/*     */     
/*  52 */     MCH_AircraftInfo info = null;
/*  53 */     if (list instanceof MCH_AircraftInfoManager) {
/*     */       
/*  55 */       info = ((MCH_AircraftInfoManager)list).getAcInfoFromItem(this.recipe);
/*  56 */       if (info != null) {
/*     */ 
/*     */         
/*  59 */         this.descMaxPage++;
/*     */         
/*  61 */         String dir = info.getDirectoryName();
/*  62 */         String name = info.name;
/*  63 */         this.model = MCH_ModelManager.get(dir, name);
/*  64 */         if (this.model != null) {
/*     */           
/*  66 */           this.modelTexture = new ResourceLocation("mcheli", "textures/" + dir + "/" + name + ".png");
/*     */           
/*  68 */           this.descMaxPage++;
/*     */           
/*  70 */           if (list instanceof mcheli.plane.MCP_PlaneInfoManager) {
/*     */             
/*  72 */             this.modelRot = 0;
/*     */           }
/*     */           else {
/*     */             
/*  76 */             this.modelRot = 1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     getAcInfoText(info);
/*     */     
/*  84 */     this.acInfo = info;
/*     */   }
/*     */ 
/*     */   
/*     */   private void getAcInfoText(MCH_AircraftInfo info) {
/*  89 */     this.infoItem = new ArrayList<String>();
/*  90 */     this.infoData = new ArrayList<String>();
/*  91 */     if (info == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  96 */     getAcInfoTextSub("Name", info.getItemStack().func_82833_r());
/*  97 */     getAcInfoTextSub("HP", "" + info.maxHp);
/*  98 */     int seatNum = !info.isUAV ? info.getNumSeat() : (info.getNumSeat() - 1);
/*  99 */     getAcInfoTextSub("Num of Seat", "" + seatNum);
/* 100 */     getAcInfoTextSub("GunnerMode", info.isEnableGunnerMode ? "YES" : "NO");
/* 101 */     getAcInfoTextSub("NightVision", info.isEnableNightVision ? "YES" : "NO");
/* 102 */     getAcInfoTextSub("Radar", info.isEnableEntityRadar ? "YES" : "NO");
/* 103 */     getAcInfoTextSub("Inventory", "" + info.inventorySize);
/*     */ 
/*     */     
/* 106 */     if (info instanceof MCP_PlaneInfo) {
/*     */       
/* 108 */       MCP_PlaneInfo pinfo = (MCP_PlaneInfo)info;
/* 109 */       getAcInfoTextSub("VTOL", pinfo.isEnableVtol ? "YES" : "NO");
/*     */     } 
/*     */     
/* 112 */     if (info.getWeaponNum() > 0) {
/*     */       
/* 114 */       getAcInfoTextSub("Armed----------------");
/* 115 */       for (int i = 0; i < info.getWeaponNum(); i++) {
/*     */         
/* 117 */         String type = (info.getWeaponSetById(i)).type;
/* 118 */         MCH_WeaponInfo winfo = MCH_WeaponInfoManager.get(type);
/* 119 */         if (winfo != null) {
/*     */           
/* 121 */           getAcInfoTextSub(winfo.getWeaponTypeName(), winfo.displayName);
/*     */         }
/*     */         else {
/*     */           
/* 125 */           getAcInfoTextSub("ERROR", "Not found weapon " + (i + 1));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getAcInfoTextSub(String item, String data) {
/* 132 */     this.infoItem.add(item + " :");
/* 133 */     this.infoData.add(data);
/*     */   }
/*     */   
/*     */   private void getAcInfoTextSub(String item) {
/* 137 */     this.infoItem.add(item);
/* 138 */     this.infoData.add("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchNextPage() {
/* 143 */     if (this.descMaxPage >= 2) {
/*     */       
/* 145 */       this.descPage = (this.descPage + 1) % this.descMaxPage;
/*     */     }
/*     */     else {
/*     */       
/* 149 */       this.descPage = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void switchPrevPage() {
/* 154 */     this.descPage--;
/* 155 */     if (this.descPage < 0 && this.descMaxPage >= 2) {
/*     */       
/* 157 */       this.descPage = this.descMaxPage - 1;
/*     */     }
/*     */     else {
/*     */       
/* 161 */       this.descPage = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getDescCurrentPage() {
/* 166 */     return this.descPage;
/*     */   }
/*     */   
/*     */   public void setDescCurrentPage(int page) {
/* 170 */     if (this.descMaxPage > 0) {
/*     */       
/* 172 */       this.descPage = (page < this.descMaxPage) ? page : (this.descMaxPage - 1);
/*     */     }
/*     */     else {
/*     */       
/* 176 */       this.descPage = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getDescMaxPage() {
/* 181 */     return this.descMaxPage;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getCurrentPageTexture() {
/* 186 */     if (this.descPage < this.descTexture.size())
/*     */     {
/* 188 */       return this.descTexture.get(this.descPage);
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public W_ModelCustom getModel() {
/* 195 */     return this.model;
/*     */   }
/*     */   
/*     */   public ResourceLocation getModelTexture() {
/* 199 */     return this.modelTexture;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_AircraftInfo getAcInfo() {
/* 204 */     return this.acInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCurrentPageTexture() {
/* 209 */     return (this.descPage >= 0 && this.descPage < this.descTexture.size());
/*     */   }
/*     */   
/*     */   public boolean isCurrentPageModel() {
/* 213 */     if (getAcInfo() != null && getModel() != null)
/*     */     {
/*     */       
/* 216 */       if (this.descPage == this.descTexture.size())
/*     */       {
/* 218 */         return true;
/*     */       }
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCurrentPageAcInfo() {
/* 225 */     if (getAcInfo() != null)
/*     */     {
/*     */       
/* 228 */       if (this.descPage == this.descMaxPage - 1)
/*     */       {
/* 230 */         return true;
/*     */       }
/*     */     }
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<ResourceLocation> getDescTexture(IRecipe r) {
/* 238 */     List<ResourceLocation> list = new ArrayList<ResourceLocation>();
/* 239 */     if (r != null)
/*     */     {
/* 241 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 243 */         String itemName = r.func_77571_b().func_77977_a();
/* 244 */         if (itemName.startsWith("tile."))
/*     */         {
/* 246 */           itemName = itemName.substring(5);
/*     */         }
/* 248 */         if (itemName.indexOf(":") >= 0)
/*     */         {
/* 250 */           itemName = itemName.substring(itemName.indexOf(":") + 1);
/*     */         }
/* 252 */         itemName = "/textures/drafting_table_desc/" + itemName + "#" + i + ".png";
/* 253 */         File filePng = new File(MCH_MOD.sourcePath, "/assets/mcheli/" + itemName);
/* 254 */         if (filePng.exists())
/*     */         {
/* 256 */           list.add(new ResourceLocation("mcheli", itemName));
/*     */         }
/*     */       } 
/*     */     }
/* 260 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_CurrentRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */