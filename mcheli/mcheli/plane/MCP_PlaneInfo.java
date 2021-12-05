/*     */ package mcheli.mcheli.plane;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.plane.MCP_ItemPlane;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCP_PlaneInfo
/*     */   extends MCH_AircraftInfo
/*     */ {
/*     */   public MCP_ItemPlane item;
/*     */   public List<MCH_AircraftInfo.DrawnPart> nozzles;
/*     */   public List<Rotor> rotorList;
/*     */   public List<Wing> wingList;
/*     */   public boolean isEnableVtol;
/*     */   public boolean isDefaultVtol;
/*     */   public float vtolYaw;
/*     */   public float vtolPitch;
/*     */   public boolean isEnableAutoPilot;
/*     */   public boolean isVariableSweepWing;
/*     */   public float sweepWingSpeed;
/*     */   
/*     */   public Item getItem() {
/*  77 */     return (Item)this.item;
/*     */   }
/*     */   
/*     */   public MCP_PlaneInfo(String name) {
/*  81 */     super(name);
/*  82 */     this.item = null;
/*  83 */     this.nozzles = new ArrayList<MCH_AircraftInfo.DrawnPart>();
/*  84 */     this.rotorList = new ArrayList<Rotor>();
/*  85 */     this.wingList = new ArrayList<Wing>();
/*  86 */     this.isEnableVtol = false;
/*  87 */     this.vtolYaw = 0.3F;
/*  88 */     this.vtolPitch = 0.2F;
/*  89 */     this.isEnableAutoPilot = false;
/*  90 */     this.isVariableSweepWing = false;
/*  91 */     this.sweepWingSpeed = this.speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDefaultRotorSpeed() {
/*  96 */     return 47.94F;
/*     */   }
/*     */ 
/*     */   
/*     */   private float getDefaultStepHeight() {
/* 101 */     return 0.6F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean haveNozzle() {
/* 106 */     return (this.nozzles.size() > 0);
/*     */   }
/*     */   
/*     */   public boolean haveRotor() {
/* 110 */     return (this.rotorList.size() > 0);
/*     */   }
/*     */   
/*     */   public boolean haveWing() {
/* 114 */     return (this.wingList.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxSpeed() {
/* 119 */     return 1.8F;
/*     */   }
/*     */   public int getDefaultMaxZoom() {
/* 122 */     return 8;
/*     */   }
/*     */   
/*     */   public String getDefaultHudName(int seatId) {
/* 126 */     if (seatId <= 0) return "plane"; 
/* 127 */     if (seatId == 1) return "plane"; 
/* 128 */     return "gunner";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidData() throws Exception {
/* 134 */     if (haveHatch() && haveWing()) {
/*     */       
/* 136 */       this.wingList.clear();
/* 137 */       this.hatchList.clear();
/*     */     } 
/*     */     
/* 140 */     this.speed = (float)(this.speed * MCH_Config.AllPlaneSpeed.prmDouble);
/* 141 */     this.sweepWingSpeed = (float)(this.sweepWingSpeed * MCH_Config.AllPlaneSpeed.prmDouble);
/*     */     
/* 143 */     return super.isValidData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadItemData(String item, String data) {
/* 148 */     super.loadItemData(item, data);
/*     */     
/* 150 */     if (item.compareTo("addpartrotor") == 0) {
/*     */       
/* 152 */       String[] s = data.split("\\s*,\\s*");
/* 153 */       if (s.length >= 6)
/*     */       {
/* 155 */         float m = (s.length >= 7) ? (toFloat(s[6], -180.0F, 180.0F) / 90.0F) : 1.0F;
/* 156 */         Rotor e = new Rotor(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), m, "rotor" + this.rotorList.size());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 162 */         this.rotorList.add(e);
/*     */       }
/*     */     
/* 165 */     } else if (item.compareTo("addblade") == 0) {
/*     */       
/* 167 */       int idx = this.rotorList.size() - 1;
/* 168 */       Rotor r = (this.rotorList.size() > 0) ? this.rotorList.get(idx) : null;
/* 169 */       if (r != null) {
/*     */         
/* 171 */         String[] s = data.split("\\s*,\\s*");
/* 172 */         if (s.length == 8)
/*     */         {
/* 174 */           Blade b = new Blade(this, toInt(s[0]), toInt(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), toFloat(s[7]), "blade" + idx);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 179 */           r.blades.add(b);
/*     */         }
/*     */       
/*     */       } 
/* 183 */     } else if (item.compareTo("addpartwing") == 0) {
/*     */       
/* 185 */       String[] s = data.split("\\s*,\\s*");
/* 186 */       if (s.length == 7)
/*     */       {
/* 188 */         Wing n = new Wing(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), "wing" + this.wingList.size());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 193 */         this.wingList.add(n);
/*     */       }
/*     */     
/* 196 */     } else if (item.equalsIgnoreCase("AddPartPylon")) {
/*     */       
/* 198 */       String[] s = data.split("\\s*,\\s*");
/* 199 */       if (s.length >= 7 && this.wingList.size() > 0)
/*     */       {
/* 201 */         Wing w = this.wingList.get(this.wingList.size() - 1);
/* 202 */         if (w.pylonList == null) w.pylonList = new ArrayList(); 
/* 203 */         Pylon n = new Pylon(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), w.modelName + "_pylon" + w.pylonList.size());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 208 */         w.pylonList.add(n);
/*     */       }
/*     */     
/* 211 */     } else if (item.compareTo("addpartnozzle") == 0) {
/*     */       
/* 213 */       String[] s = data.split("\\s*,\\s*");
/* 214 */       if (s.length == 6)
/*     */       {
/* 216 */         MCH_AircraftInfo.DrawnPart n = new MCH_AircraftInfo.DrawnPart(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), "nozzle" + this.nozzles.size());
/*     */ 
/*     */ 
/*     */         
/* 220 */         this.nozzles.add(n);
/*     */       }
/*     */     
/* 223 */     } else if (item.compareTo("variablesweepwing") == 0) {
/*     */       
/* 225 */       this.isVariableSweepWing = toBool(data);
/*     */     }
/* 227 */     else if (item.compareTo("sweepwingspeed") == 0) {
/*     */       
/* 229 */       this.sweepWingSpeed = toFloat(data, 0.0F, 5.0F);
/*     */     }
/* 231 */     else if (item.compareTo("enablevtol") == 0) {
/*     */       
/* 233 */       this.isEnableVtol = toBool(data);
/*     */     }
/* 235 */     else if (item.compareTo("defaultvtol") == 0) {
/*     */       
/* 237 */       this.isDefaultVtol = toBool(data);
/*     */     }
/* 239 */     else if (item.compareTo("vtolyaw") == 0) {
/*     */       
/* 241 */       this.vtolYaw = toFloat(data, 0.0F, 1.0F);
/*     */     }
/* 243 */     else if (item.compareTo("vtolpitch") == 0) {
/*     */       
/* 245 */       this.vtolPitch = toFloat(data, 0.01F, 1.0F);
/*     */     }
/* 247 */     else if (item.compareTo("enableautopilot") == 0) {
/*     */       
/* 249 */       this.isEnableAutoPilot = toBool(data);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDirectoryName() {
/* 256 */     return "planes";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKindName() {
/* 262 */     return "plane";
/*     */   }
/*     */ 
/*     */   
/*     */   public void preReload() {
/* 267 */     super.preReload();
/* 268 */     this.nozzles.clear();
/* 269 */     this.rotorList.clear();
/* 270 */     this.wingList.clear();
/*     */   }
/*     */   
/*     */   public void postReload() {
/* 274 */     MCH_MOD.proxy.registerModelsPlane(this.name, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_PlaneInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */