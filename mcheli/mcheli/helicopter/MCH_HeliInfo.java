/*     */ package mcheli.mcheli.helicopter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.helicopter.MCH_ItemHeli;
/*     */ import net.minecraft.item.Item;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_HeliInfo
/*     */   extends MCH_AircraftInfo
/*     */ {
/*     */   public MCH_ItemHeli item;
/*     */   public boolean isEnableFoldBlade;
/*     */   public List<Rotor> rotorList;
/*     */   
/*     */   public MCH_HeliInfo(String name) {
/*  36 */     super(name);
/*  37 */     this.item = null;
/*  38 */     this.isEnableGunnerMode = false;
/*  39 */     this.isEnableFoldBlade = false;
/*  40 */     this.rotorList = new ArrayList<Rotor>();
/*  41 */     this.minRotationPitch = -20.0F;
/*  42 */     this.maxRotationPitch = 20.0F;
/*     */   }
/*     */   
/*     */   public boolean isValidData() throws Exception {
/*  46 */     this.speed = (float)(this.speed * MCH_Config.AllHeliSpeed.prmDouble);
/*  47 */     return super.isValidData();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefaultSoundRange() {
/*  52 */     return 80.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefaultRotorSpeed() {
/*  57 */     return 79.99F;
/*     */   }
/*     */   public int getDefaultMaxZoom() {
/*  60 */     return 8;
/*     */   } public Item getItem() {
/*  62 */     return (Item)this.item;
/*     */   }
/*     */   
/*     */   public String getDefaultHudName(int seatId) {
/*  66 */     if (seatId <= 0) return "heli"; 
/*  67 */     if (seatId == 1) return "heli_gnr"; 
/*  68 */     return "gunner";
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/*  73 */     super.loadItemData(item, data);
/*     */     
/*  75 */     if (item.compareTo("enablefoldblade") == 0) {
/*     */       
/*  77 */       this.isEnableFoldBlade = toBool(data);
/*     */     }
/*  79 */     else if (item.compareTo("addrotor") == 0 || item.compareTo("addrotorold") == 0) {
/*     */       
/*  81 */       String[] s = data.split("\\s*,\\s*");
/*  82 */       if (s.length == 8 || s.length == 9) {
/*     */         
/*  84 */         boolean cfb = (s.length == 9 && toBool(s[8]));
/*  85 */         Rotor e = new Rotor(this, toInt(s[0]), toInt(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), toFloat(s[7]), "blade" + this.rotorList.size(), cfb, (item.compareTo("addrotorold") == 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  92 */         this.rotorList.add(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDirectoryName() {
/* 100 */     return "helicopters";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKindName() {
/* 105 */     return "helicopter";
/*     */   }
/*     */   
/*     */   public void preReload() {
/* 109 */     super.preReload();
/* 110 */     this.rotorList.clear();
/*     */   }
/*     */   
/*     */   public void postReload() {
/* 114 */     MCH_MOD.proxy.registerModelsHeli(this.name, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_HeliInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */