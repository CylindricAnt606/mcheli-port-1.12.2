/*     */ package mcheli.mcheli.throwable;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_BaseInfo;
/*     */ import mcheli.MCH_Color;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraftforge.client.model.IModelCustom;
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
/*     */ public class MCH_ThrowableInfo
/*     */   extends MCH_BaseInfo
/*     */ {
/*     */   public final String name;
/*     */   public String displayName;
/*     */   public HashMap<String, String> displayNameLang;
/*     */   public int itemID;
/*     */   public W_Item item;
/*     */   public List<String> recipeString;
/*     */   public List<IRecipe> recipe;
/*     */   public boolean isShapedRecipe;
/*     */   public int power;
/*     */   public float acceleration;
/*     */   public float accelerationInWater;
/*     */   public float dispenseAcceleration;
/*     */   public int explosion;
/*     */   public int delayFuse;
/*     */   public float bound;
/*     */   public int timeFuse;
/*     */   public boolean flaming;
/*     */   public int stackSize;
/*     */   public float soundVolume;
/*     */   public float soundPitch;
/*     */   public float proximityFuseDist;
/*     */   public float accuracy;
/*     */   public int aliveTime;
/*     */   public int bomblet;
/*     */   public float bombletDiff;
/*     */   public IModelCustom model;
/*     */   public float smokeSize;
/*     */   public int smokeNum;
/*     */   public float smokeVelocityVertical;
/*     */   public float smokeVelocityHorizontal;
/*     */   public float gravity;
/*     */   public float gravityInWater;
/*     */   public String particleName;
/*     */   public boolean disableSmoke;
/*     */   public MCH_Color smokeColor;
/*     */   
/*     */   public MCH_ThrowableInfo(String name) {
/*  65 */     this.name = name;
/*  66 */     this.displayName = name;
/*  67 */     this.displayNameLang = new HashMap<String, String>();
/*  68 */     this.itemID = 0;
/*  69 */     this.item = null;
/*  70 */     this.recipeString = new ArrayList<String>();
/*  71 */     this.recipe = new ArrayList<IRecipe>();
/*  72 */     this.isShapedRecipe = true;
/*  73 */     this.power = 0;
/*  74 */     this.acceleration = 1.0F;
/*  75 */     this.accelerationInWater = 1.0F;
/*  76 */     this.dispenseAcceleration = 1.0F;
/*  77 */     this.explosion = 0;
/*  78 */     this.delayFuse = 0;
/*  79 */     this.bound = 0.2F;
/*  80 */     this.timeFuse = 0;
/*  81 */     this.flaming = false;
/*  82 */     this.stackSize = 1;
/*  83 */     this.soundVolume = 1.0F;
/*  84 */     this.soundPitch = 1.0F;
/*  85 */     this.proximityFuseDist = 0.0F;
/*  86 */     this.accuracy = 0.0F;
/*  87 */     this.aliveTime = 10;
/*  88 */     this.bomblet = 0;
/*  89 */     this.bombletDiff = 0.3F;
/*  90 */     this.model = null;
/*  91 */     this.smokeSize = 10.0F;
/*  92 */     this.smokeNum = 0;
/*  93 */     this.smokeVelocityVertical = 1.0F;
/*  94 */     this.smokeVelocityHorizontal = 1.0F;
/*  95 */     this.gravity = 0.0F;
/*  96 */     this.gravityInWater = -0.04F;
/*  97 */     this.particleName = "explode";
/*  98 */     this.disableSmoke = true;
/*  99 */     this.smokeColor = new MCH_Color();
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkData() {
/* 104 */     this.timeFuse *= 20;
/* 105 */     this.aliveTime *= 20;
/* 106 */     this.delayFuse *= 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/* 111 */     if (item.compareTo("displayname") == 0) {
/*     */       
/* 113 */       this.displayName = data;
/*     */     }
/* 115 */     else if (item.compareTo("adddisplayname") == 0) {
/*     */       
/* 117 */       String[] s = data.split("\\s*,\\s*");
/* 118 */       if (s != null && s.length == 2)
/*     */       {
/* 120 */         this.displayNameLang.put(s[0].trim(), s[1].trim());
/*     */       }
/*     */     }
/* 123 */     else if (item.compareTo("itemid") == 0) {
/*     */       
/* 125 */       this.itemID = toInt(data, 0, 65535);
/*     */     }
/* 127 */     else if (item.compareTo("addrecipe") == 0 || item.compareTo("addshapelessrecipe") == 0) {
/*     */       
/* 129 */       this.isShapedRecipe = (item.compareTo("addrecipe") == 0);
/* 130 */       this.recipeString.add(data.toUpperCase());
/*     */     }
/* 132 */     else if (item.compareTo("power") == 0) {
/*     */       
/* 134 */       this.power = toInt(data);
/*     */     }
/* 136 */     else if (item.compareTo("acceleration") == 0) {
/*     */       
/* 138 */       this.acceleration = toFloat(data, 0.0F, 100.0F);
/*     */     }
/* 140 */     else if (item.compareTo("accelerationinwater") == 0) {
/*     */       
/* 142 */       this.accelerationInWater = toFloat(data, 0.0F, 100.0F);
/*     */     }
/* 144 */     else if (item.equalsIgnoreCase("DispenseAcceleration")) {
/*     */       
/* 146 */       this.dispenseAcceleration = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 148 */     else if (item.compareTo("explosion") == 0) {
/*     */       
/* 150 */       this.explosion = toInt(data, 0, 50);
/*     */     }
/* 152 */     else if (item.equalsIgnoreCase("DelayFuse")) {
/*     */       
/* 154 */       this.delayFuse = toInt(data, 0, 100000);
/*     */     }
/* 156 */     else if (item.equalsIgnoreCase("Bound")) {
/*     */       
/* 158 */       this.bound = toFloat(data, 0.0F, 100000.0F);
/*     */     }
/* 160 */     else if (item.equalsIgnoreCase("TimeFuse")) {
/*     */       
/* 162 */       this.timeFuse = toInt(data, 0, 100000);
/*     */     }
/* 164 */     else if (item.compareTo("flaming") == 0) {
/*     */       
/* 166 */       this.flaming = toBool(data);
/*     */     }
/* 168 */     else if (item.equalsIgnoreCase("StackSize")) {
/*     */       
/* 170 */       this.stackSize = toInt(data, 1, 64);
/*     */     }
/* 172 */     else if (item.compareTo("soundvolume") == 0) {
/*     */       
/* 174 */       this.soundVolume = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 176 */     else if (item.compareTo("soundpitch") == 0) {
/*     */       
/* 178 */       this.soundPitch = toFloat(data, 0.0F, 1.0F);
/*     */     }
/* 180 */     else if (item.compareTo("proximityfusedist") == 0) {
/*     */       
/* 182 */       this.proximityFuseDist = toFloat(data, 0.0F, 20.0F);
/*     */     }
/* 184 */     else if (item.compareTo("accuracy") == 0) {
/*     */       
/* 186 */       this.accuracy = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 188 */     else if (item.equalsIgnoreCase("aliveTime")) {
/*     */       
/* 190 */       this.aliveTime = toInt(data, 0, 1000000);
/*     */     }
/* 192 */     else if (item.compareTo("bomblet") == 0) {
/*     */       
/* 194 */       this.bomblet = toInt(data, 0, 1000);
/*     */     }
/* 196 */     else if (item.equalsIgnoreCase("BombletDiff")) {
/*     */       
/* 198 */       this.bombletDiff = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 200 */     else if (item.equalsIgnoreCase("SmokeSize")) {
/*     */       
/* 202 */       this.smokeSize = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 204 */     else if (item.equalsIgnoreCase("SmokeNum")) {
/*     */       
/* 206 */       this.smokeNum = toInt(data, 0, 1000);
/*     */     }
/* 208 */     else if (item.equalsIgnoreCase("SmokeVelocityVertical")) {
/*     */       
/* 210 */       this.smokeVelocityVertical = toFloat(data, -100.0F, 100.0F);
/*     */     }
/* 212 */     else if (item.equalsIgnoreCase("SmokeVelocityHorizontal")) {
/*     */       
/* 214 */       this.smokeVelocityHorizontal = toFloat(data, 0.0F, 1000.0F);
/*     */     }
/* 216 */     else if (item.compareTo("gravity") == 0) {
/*     */       
/* 218 */       this.gravity = toFloat(data, -50.0F, 50.0F);
/*     */     }
/* 220 */     else if (item.equalsIgnoreCase("gravityInWater")) {
/*     */       
/* 222 */       this.gravityInWater = toFloat(data, -50.0F, 50.0F);
/*     */     }
/* 224 */     else if (item.compareTo("particle") == 0) {
/*     */       
/* 226 */       this.particleName = data.toLowerCase().trim();
/* 227 */       if (this.particleName.equalsIgnoreCase("none"))
/*     */       {
/* 229 */         this.particleName = "";
/*     */       }
/*     */     }
/* 232 */     else if (item.equalsIgnoreCase("DisableSmoke")) {
/*     */       
/* 234 */       this.disableSmoke = toBool(data);
/*     */     }
/* 236 */     else if (item.equalsIgnoreCase("SmokeColor")) {
/*     */       
/* 238 */       String[] s = data.split("\\s*,\\s*");
/* 239 */       if (s.length >= 3) {
/*     */         
/* 241 */         float f = 0.003921569F;
/* 242 */         this.smokeColor = new MCH_Color(1.0F, 0.003921569F * toInt(s[0], 0, 255), 0.003921569F * toInt(s[1], 0, 255), 0.003921569F * toInt(s[2], 0, 255));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_ThrowableInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */