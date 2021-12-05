/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ 
/*     */ 
/*     */ public class MCH_Blade
/*     */ {
/*     */   private float baseRotation;
/*     */   private float rotation;
/*     */   private float prevRotation;
/*     */   private float foldSpeed;
/*     */   private float foldRotation;
/*     */   
/*     */   public MCH_Blade(float baseRotation) {
/*  15 */     this.rotation = 0.0F;
/*  16 */     this.prevRotation = 0.0F;
/*  17 */     this.baseRotation = baseRotation;
/*  18 */     this.foldSpeed = 3.0F;
/*  19 */     this.foldRotation = 5.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRotation() {
/*  24 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void setRotation(float rotation) {
/*  28 */     this.rotation = (float)MCH_Lib.getRotate360(rotation);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPrevRotation() {
/*  33 */     return this.prevRotation;
/*     */   }
/*     */   
/*     */   public void setPrevRotation(float rotation) {
/*  37 */     this.prevRotation = (float)MCH_Lib.getRotate360(rotation);
/*     */   }
/*     */ 
/*     */   
/*     */   public mcheli.aircraft.MCH_Blade setBaseRotation(float rotation) {
/*  42 */     if (rotation >= 0.0D) this.baseRotation = rotation; 
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public float getBaseRotation() {
/*  47 */     return this.baseRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public mcheli.aircraft.MCH_Blade setFoldSpeed(float speed) {
/*  52 */     if (speed > 0.1D) this.foldSpeed = speed; 
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public mcheli.aircraft.MCH_Blade setFoldRotation(float rotation) {
/*  57 */     if (rotation > this.foldSpeed * 2.0F) this.foldRotation = rotation; 
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceFold() {
/*  63 */     if (this.rotation > this.foldRotation && this.rotation < 360.0F - this.foldRotation) {
/*     */       
/*  65 */       if (this.rotation < 180.0F) {
/*     */         
/*  67 */         this.rotation = this.foldRotation;
/*     */       }
/*     */       else {
/*     */         
/*  71 */         this.rotation = 360.0F - this.foldRotation;
/*     */       } 
/*  73 */       this.rotation %= 360.0F;
/*  74 */       this.prevRotation = this.rotation;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean folding() {
/*  80 */     boolean isCmpFolding = false;
/*  81 */     if (this.rotation > this.foldRotation && this.rotation < 360.0F - this.foldRotation) {
/*     */       
/*  83 */       if (this.rotation < 180.0F) {
/*     */         
/*  85 */         this.rotation -= this.foldSpeed;
/*     */       }
/*     */       else {
/*     */         
/*  89 */         this.rotation += this.foldSpeed;
/*     */       } 
/*  91 */       this.rotation %= 360.0F;
/*     */     }
/*     */     else {
/*     */       
/*  95 */       isCmpFolding = true;
/*     */     } 
/*  97 */     return isCmpFolding;
/*     */   }
/*     */   
/*     */   public boolean unfolding(float rot) {
/* 101 */     boolean isCmpUnfolding = false;
/* 102 */     float targetRot = (float)MCH_Lib.getRotate360((rot + this.baseRotation));
/* 103 */     float prevRot = this.rotation;
/* 104 */     if (targetRot <= 180.0F) {
/*     */       
/* 106 */       this.rotation = (float)MCH_Lib.getRotate360((this.rotation + this.foldSpeed));
/* 107 */       if (this.rotation >= targetRot && prevRot <= targetRot)
/*     */       {
/* 109 */         this.rotation = targetRot;
/* 110 */         isCmpUnfolding = true;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 115 */       this.rotation = (float)MCH_Lib.getRotate360((this.rotation - this.foldSpeed));
/* 116 */       if (this.rotation <= targetRot && prevRot >= targetRot) {
/*     */         
/* 118 */         this.rotation = targetRot;
/* 119 */         isCmpUnfolding = true;
/*     */       } 
/*     */     } 
/* 122 */     return isCmpUnfolding;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_Blade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */