/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
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
/*     */ public class MCH_Parts
/*     */ {
/*     */   public final Entity parent;
/*     */   public final DataWatcher dataWatcher;
/*     */   public final int shift;
/*     */   public final int dataIndex;
/*     */   public final String partName;
/*  29 */   public float prevRotation = 0.0F;
/*  30 */   public float rotation = 0.0F;
/*  31 */   public float rotationMax = 90.0F;
/*  32 */   public float rotationInv = 1.0F;
/*     */   
/*  34 */   public Sound soundStartSwichOn = new Sound(this);
/*  35 */   public Sound soundEndSwichOn = new Sound(this);
/*  36 */   public Sound soundSwitching = new Sound(this);
/*  37 */   public Sound soundStartSwichOff = new Sound(this);
/*  38 */   public Sound soundEndSwichOff = new Sound(this);
/*     */   
/*     */   private boolean status = false;
/*     */ 
/*     */   
/*     */   public MCH_Parts(Entity parent, int shiftBit, int dataWatcherIndex, String name) {
/*  44 */     this.parent = parent;
/*  45 */     this.dataWatcher = parent.func_70096_w();
/*  46 */     this.shift = shiftBit;
/*  47 */     this.dataIndex = dataWatcherIndex;
/*  48 */     this.status = ((getDataWatcherValue() & 1 << this.shift) != 0);
/*  49 */     this.partName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDataWatcherValue() {
/*  54 */     return this.dataWatcher.func_75679_c(this.dataIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatusServer(boolean stat) {
/*  59 */     setStatusServer(stat, true);
/*     */   }
/*     */   
/*     */   public void setStatusServer(boolean stat, boolean playSound) {
/*  63 */     if (!this.parent.field_70170_p.field_72995_K)
/*     */     {
/*  65 */       if (getStatus() != stat) {
/*     */         
/*  67 */         MCH_Lib.DbgLog(false, "setStatusServer(ID=%d %s :%s -> %s)", new Object[] { Integer.valueOf(this.shift), this.partName, getStatus() ? "ON" : "OFF", stat ? "ON" : "OFF" });
/*     */ 
/*     */         
/*  70 */         updateDataWatcher(stat);
/*  71 */         playSound(this.soundSwitching);
/*  72 */         if (!stat) {
/*     */           
/*  74 */           playSound(this.soundStartSwichOff);
/*     */         }
/*     */         else {
/*     */           
/*  78 */           playSound(this.soundStartSwichOn);
/*     */         } 
/*  80 */         update();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateDataWatcher(boolean stat) {
/*  87 */     int currentStatus = this.dataWatcher.func_75679_c(this.dataIndex);
/*  88 */     int mask = 1 << this.shift;
/*  89 */     if (!stat) {
/*     */       
/*  91 */       this.dataWatcher.func_75692_b(this.dataIndex, Integer.valueOf(currentStatus & (mask ^ 0xFFFFFFFF)));
/*     */     }
/*     */     else {
/*     */       
/*  95 */       this.dataWatcher.func_75692_b(this.dataIndex, Integer.valueOf(currentStatus | mask));
/*     */     } 
/*  97 */     this.status = stat;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getStatus() {
/* 102 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOFF() {
/* 109 */     return (!this.status && this.rotation <= 0.02F);
/*     */   }
/*     */   
/*     */   public boolean isON() {
/* 113 */     return (this.status == true && this.rotation >= this.rotationMax - 0.02F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateStatusClient(int statFromDataWatcher) {
/* 118 */     if (this.parent.field_70170_p.field_72995_K)
/*     */     {
/* 120 */       this.status = ((statFromDataWatcher & 1 << this.shift) != 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 126 */     this.prevRotation = this.rotation;
/*     */     
/* 128 */     if (getStatus()) {
/*     */       
/* 130 */       if (this.rotation < this.rotationMax)
/*     */       {
/* 132 */         this.rotation += this.rotationInv;
/* 133 */         if (this.rotation >= this.rotationMax)
/*     */         {
/* 135 */           playSound(this.soundEndSwichOn);
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 141 */     else if (this.rotation > 0.0F) {
/*     */       
/* 143 */       this.rotation -= this.rotationInv;
/* 144 */       if (this.rotation <= 0.0F)
/*     */       {
/* 146 */         playSound(this.soundEndSwichOff);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceSwitch(boolean onoff) {
/* 155 */     updateDataWatcher(onoff);
/* 156 */     this.rotation = this.prevRotation = this.rotationMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFactor() {
/* 161 */     if (this.rotationMax > 0.0F)
/*     */     {
/* 163 */       return this.rotation / this.rotationMax;
/*     */     }
/* 165 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(Sound snd) {
/* 170 */     if (!snd.name.isEmpty() && !this.parent.field_70170_p.field_72995_K)
/*     */     {
/* 172 */       W_WorldFunc.MOD_playSoundAtEntity(this.parent, snd.name, snd.volume, snd.pitch);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_Parts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */