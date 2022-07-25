/*     */ package mcheli.mcheli.vehicle;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.vehicle.MCH_ItemVehicle;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_VehicleInfo
/*     */   extends MCH_AircraftInfo
/*     */ {
/*     */   public MCH_ItemVehicle item;
/*     */   public boolean isEnableMove;
/*     */   public boolean isEnableRot;
/*     */   public List<VPart> partList;
/*     */   
/*     */   public float getMinRotationPitch() {
/*  40 */     return -90.0F; } public float getMaxRotationPitch() {
/*  41 */     return 90.0F;
/*     */   } public Item getItem() {
/*  43 */     return (Item)this.item;
/*     */   }
/*     */   
/*     */   public MCH_VehicleInfo(String name) {
/*  47 */     super(name);
/*  48 */     this.item = null;
/*  49 */     this.isEnableMove = false;
/*  50 */     this.isEnableRot = false;
/*  51 */     this.partList = new ArrayList<VPart>();
/*     */   }
/*     */   
/*     */   public boolean isValidData() throws Exception {
/*  55 */     return super.isValidData();
/*     */   }
/*     */   
/*     */   public String getDefaultHudName(int seatId) {
/*  59 */     return "vehicle";
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/*  64 */     super.loadItemData(item, data);
/*     */     
/*  66 */     if (item.compareTo("canmove") == 0) {
/*     */       
/*  68 */       this.isEnableMove = toBool(data);
/*     */     }
/*  70 */     else if (item.compareTo("canrotation") == 0) {
/*     */       
/*  72 */       this.isEnableRot = toBool(data);
/*     */     }
/*  74 */     else if (item.compareTo("rotationpitchmin") == 0) {
/*     */ 
/*     */       
/*  77 */       super.loadItemData("minrotationpitch", data);
/*     */     }
/*  79 */     else if (item.compareTo("rotationpitchmax") == 0) {
/*     */ 
/*     */       
/*  82 */       super.loadItemData("maxrotationpitch", data);
/*     */     }
/*  84 */     else if (item.compareTo("addpart") == 0) {
/*     */       
/*  86 */       String[] s = data.split("\\s*,\\s*");
/*  87 */       if (s.length >= 7)
/*     */       {
/*  89 */         float rb = (s.length >= 8) ? toFloat(s[7]) : 0.0F;
/*  90 */         VPart n = new VPart(this, toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), "part" + this.partList.size(), toBool(s[0]), toBool(s[1]), toBool(s[2]), toInt(s[3]), rb);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         this.partList.add(n);
/*     */       }
/*     */     
/* 101 */     } else if (item.compareTo("addchildpart") == 0) {
/*     */       
/* 103 */       if (this.partList.size() > 0) {
/*     */         
/* 105 */         String[] s = data.split("\\s*,\\s*");
/* 106 */         if (s.length >= 7) {
/*     */           
/* 108 */           float rb = (s.length >= 8) ? toFloat(s[7]) : 0.0F;
/* 109 */           VPart p = this.partList.get(this.partList.size() - 1);
/* 110 */           if (p.child == null) p.child = new ArrayList(); 
/* 111 */           VPart n = new VPart(this, toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), p.modelName + "_" + p.child.size(), toBool(s[0]), toBool(s[1]), toBool(s[2]), toInt(s[3]), rb);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 119 */           p.child.add(n);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDirectoryName() {
/* 128 */     return "vehicles";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKindName() {
/* 134 */     return "vehicle";
/*     */   }
/*     */ 
/*     */   
/*     */   public void preReload() {
/* 139 */     super.preReload();
/* 140 */     this.partList.clear();
/*     */   }
/*     */   
/*     */   public void postReload() {
/* 144 */     MCH_MOD.proxy.registerModelsVehicle(this.name, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_VehicleInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */