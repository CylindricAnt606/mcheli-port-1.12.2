/*     */ package mcheli.mcheli.tank;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.tank.MCH_ItemTank;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ public class MCH_TankInfo
/*     */   extends MCH_AircraftInfo
/*     */ {
/*     */   public MCH_ItemTank item;
/*     */   public int weightType;
/*     */   public float weightedCenterZ;
/*     */   
/*     */   public Item getItem() {
/*  21 */     return (Item)this.item;
/*     */   }
/*     */   
/*     */   public MCH_TankInfo(String name) {
/*  25 */     super(name);
/*  26 */     this.item = null;
/*  27 */     this.weightType = 0;
/*  28 */     this.weightedCenterZ = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MCH_AircraftInfo.Wheel> getDefaultWheelList() {
/*  33 */     List<MCH_AircraftInfo.Wheel> list = new ArrayList<MCH_AircraftInfo.Wheel>();
/*  34 */     list.add(new MCH_AircraftInfo.Wheel(this, Vec3.func_72443_a(1.5D, -0.24D, 2.0D)));
/*  35 */     list.add(new MCH_AircraftInfo.Wheel(this, Vec3.func_72443_a(1.5D, -0.24D, -2.0D)));
/*  36 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefaultSoundRange() {
/*  41 */     return 50.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefaultRotorSpeed() {
/*  46 */     return 47.94F;
/*     */   }
/*     */ 
/*     */   
/*     */   private float getDefaultStepHeight() {
/*  51 */     return 0.6F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxSpeed() {
/*  56 */     return 1.8F;
/*     */   }
/*     */   public int getDefaultMaxZoom() {
/*  59 */     return 8;
/*     */   }
/*     */   
/*     */   public String getDefaultHudName(int seatId) {
/*  63 */     if (seatId <= 0) return "tank"; 
/*  64 */     if (seatId == 1) return "tank"; 
/*  65 */     return "gunner";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidData() throws Exception {
/*  70 */     this.speed = (float)(this.speed * MCH_Config.AllTankSpeed.prmDouble);
/*     */     
/*  72 */     return super.isValidData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/*  77 */     super.loadItemData(item, data);
/*     */     
/*  79 */     if (item.equalsIgnoreCase("WeightType")) {
/*     */       
/*  81 */       data = data.toLowerCase();
/*  82 */       this.weightType = data.equals("tank") ? 2 : (data.equals("car") ? 1 : 0);
/*     */     }
/*  84 */     else if (item.equalsIgnoreCase("WeightedCenterZ")) {
/*     */       
/*  86 */       this.weightedCenterZ = toFloat(data, -1000.0F, 1000.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDirectoryName() {
/*  93 */     return "tanks";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKindName() {
/*  99 */     return "tank";
/*     */   }
/*     */ 
/*     */   
/*     */   public void preReload() {
/* 104 */     super.preReload();
/*     */   }
/*     */   
/*     */   public void postReload() {
/* 108 */     MCH_MOD.proxy.registerModelsTank(this.name, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_TankInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */