/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.aircraft.MCH_Blade;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_Rotor
/*     */ {
/*     */   public MCH_Blade[] blades;
/*     */   private int numBlade;
/*     */   private int invRot;
/*     */   private boolean isFoldBlade;
/*     */   private boolean isFoldBladeTarget;
/*     */   private boolean haveFoldBladeFunc;
/*     */   
/*     */   public MCH_Rotor(int numBlade, int invRot, int foldSpeed, float posx, float posy, float posz, float rotx, float roty, float rotz, boolean canFoldBlade) {
/*  19 */     setupBlade(numBlade, invRot, foldSpeed);
/*  20 */     this.isFoldBlade = false;
/*  21 */     this.isFoldBladeTarget = false;
/*  22 */     this.haveFoldBladeFunc = canFoldBlade;
/*  23 */     setPostion(posx, posy, posz);
/*  24 */     setRotation(rotx, roty, rotz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public mcheli.aircraft.MCH_Rotor setPostion(float x, float y, float z) {
/*  32 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public mcheli.aircraft.MCH_Rotor setRotation(float x, float y, float z) {
/*  40 */     return this;
/*     */   }
/*     */   public int getBladeNum() {
/*  43 */     return this.numBlade;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupBlade(int num, int invRot, int foldSpeed) {
/*  48 */     this.invRot = (invRot > 0) ? invRot : 1;
/*  49 */     this.numBlade = (num > 0) ? num : 1;
/*  50 */     this.blades = new MCH_Blade[this.numBlade];
/*  51 */     for (int i = 0; i < this.numBlade; i++) {
/*     */       
/*  53 */       this.blades[i] = new MCH_Blade((i * this.invRot));
/*  54 */       this.blades[i].setFoldRotation((5 + i * 3)).setFoldSpeed(foldSpeed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFoldingOrUnfolding() {
/*  60 */     return (this.isFoldBlade != this.isFoldBladeTarget);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBladeRotaion(int bladeIndex) {
/*  65 */     if (bladeIndex >= this.numBlade) return 0.0F; 
/*  66 */     return this.blades[bladeIndex].getRotation();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startUnfold() {
/*  72 */     this.isFoldBladeTarget = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startFold() {
/*  79 */     if (this.haveFoldBladeFunc)
/*     */     {
/*  81 */       this.isFoldBladeTarget = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceFold() {
/*  89 */     if (this.haveFoldBladeFunc) {
/*     */       
/*  91 */       this.isFoldBladeTarget = true;
/*  92 */       this.isFoldBlade = true;
/*  93 */       for (MCH_Blade b : this.blades)
/*     */       {
/*  95 */         b.forceFold();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(float rot) {
/* 102 */     boolean isCmpFoldUnfold = true;
/* 103 */     for (MCH_Blade b : this.blades) {
/*     */       
/* 105 */       b.setPrevRotation(b.getRotation());
/*     */ 
/*     */       
/* 108 */       if (!this.isFoldBlade)
/*     */       
/*     */       { 
/* 111 */         if (!this.isFoldBladeTarget)
/*     */         
/* 113 */         { b.setRotation(rot + b.getBaseRotation());
/* 114 */           isCmpFoldUnfold = false;
/*     */ 
/*     */ 
/*     */           
/*     */            }
/*     */         
/* 120 */         else if (!b.folding()) { isCmpFoldUnfold = false;
/*     */           
/*     */            }
/*     */ 
/*     */         
/*     */          }
/*     */       
/* 127 */       else if (this.isFoldBladeTarget == true)
/*     */       
/* 129 */       { isCmpFoldUnfold = false;
/*     */ 
/*     */ 
/*     */         
/*     */          }
/*     */       
/* 135 */       else if (!b.unfolding(rot)) { isCmpFoldUnfold = false; }
/*     */     
/*     */     } 
/*     */ 
/*     */     
/* 140 */     if (isCmpFoldUnfold)
/*     */     {
/* 142 */       this.isFoldBlade = this.isFoldBladeTarget;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_Rotor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */