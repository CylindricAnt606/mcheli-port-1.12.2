/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_SoundUpdater;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_SoundUpdater
/*     */   extends W_SoundUpdater
/*     */ {
/*     */   private final MCH_EntityAircraft theAircraft;
/*     */   private final EntityPlayerSP thePlayer;
/*     */   private boolean isMoving;
/*     */   private boolean silent;
/*     */   private float aircraftPitch;
/*     */   private float aircraftVolume;
/*     */   private float addPitch;
/*     */   private boolean isFirstUpdate;
/*     */   private double prevDist;
/*  30 */   private int soundDelay = 0;
/*     */ 
/*     */   
/*     */   public MCH_SoundUpdater(Minecraft mc, MCH_EntityAircraft aircraft, EntityPlayerSP entityPlayerSP) {
/*  34 */     super(mc, (Entity)aircraft);
/*  35 */     this.theAircraft = aircraft;
/*  36 */     this.thePlayer = entityPlayerSP;
/*  37 */     this.isFirstUpdate = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  46 */     if (this.theAircraft.getSoundName().isEmpty() || this.theAircraft.getAcInfo() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  50 */     if (this.isFirstUpdate) {
/*     */       
/*  52 */       this.isFirstUpdate = false;
/*  53 */       initEntitySound(this.theAircraft.getSoundName());
/*     */     } 
/*     */     
/*  56 */     MCH_AircraftInfo info = this.theAircraft.getAcInfo();
/*     */     
/*  58 */     boolean isBeforeMoving = this.isMoving;
/*  59 */     boolean isDead = this.theAircraft.field_70128_L;
/*     */ 
/*     */     
/*  62 */     if (isDead || (!this.silent && this.aircraftVolume == 0.0F)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  67 */       if (isDead) stopEntitySound((Entity)this.theAircraft);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       this.silent = true;
/*     */       
/*  76 */       if (isDead) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  82 */     boolean isRide = (this.theAircraft.getSeatIdByEntity((Entity)this.thePlayer) >= 0);
/*  83 */     boolean isPlaying = isEntitySoundPlaying((Entity)this.theAircraft);
/*  84 */     boolean onPlaySound = false;
/*     */     
/*  86 */     if (!isPlaying && this.aircraftVolume > 0.0F) {
/*     */ 
/*     */ 
/*     */       
/*  90 */       if (this.soundDelay > 0) {
/*     */         
/*  92 */         this.soundDelay--;
/*     */       }
/*     */       else {
/*     */         
/*  96 */         this.soundDelay = 20;
/*  97 */         playEntitySound(this.theAircraft.getSoundName(), (Entity)this.theAircraft, this.aircraftVolume, this.aircraftPitch, true);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 105 */       this.silent = false;
/* 106 */       onPlaySound = true;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     float prevVolume = this.aircraftVolume;
/* 111 */     float prevPitch = this.aircraftPitch;
/* 112 */     this.isMoving = ((info.soundVolume * this.theAircraft.getSoundVolume()) >= 0.01D);
/* 113 */     if (this.isMoving) {
/*     */       
/* 115 */       this.aircraftVolume = info.soundVolume * this.theAircraft.getSoundVolume();
/* 116 */       this.aircraftPitch = info.soundPitch * this.theAircraft.getSoundPitch();
/* 117 */       if (!isRide) {
/*     */         
/* 119 */         double dist = this.thePlayer.func_70011_f(this.theAircraft.field_70165_t, this.thePlayer.field_70163_u, this.theAircraft.field_70161_v);
/* 120 */         double pitch = this.prevDist - dist;
/* 121 */         if (Math.abs(pitch) > 0.3D) {
/*     */           
/* 123 */           this.addPitch = (float)(this.addPitch + pitch / 40.0D);
/* 124 */           float maxAddPitch = 0.2F;
/* 125 */           if (this.addPitch < -maxAddPitch) this.addPitch = -maxAddPitch; 
/* 126 */           if (this.addPitch > maxAddPitch) this.addPitch = maxAddPitch; 
/*     */         } 
/* 128 */         this.addPitch = (float)(this.addPitch * 0.9D);
/* 129 */         this.aircraftPitch += this.addPitch;
/* 130 */         this.prevDist = dist;
/*     */       } 
/* 132 */       if (this.aircraftPitch < 0.0F)
/*     */       {
/* 134 */         this.aircraftPitch = 0.0F;
/*     */       }
/*     */     }
/* 137 */     else if (isBeforeMoving) {
/*     */       
/* 139 */       this.aircraftVolume = 0.0F;
/* 140 */       this.aircraftPitch = 0.0F;
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (!this.silent) {
/*     */       
/* 146 */       if (this.aircraftPitch != prevPitch)
/*     */       {
/*     */         
/* 149 */         setEntitySoundPitch((Entity)this.theAircraft, this.aircraftPitch);
/*     */       }
/*     */       
/* 152 */       if (this.aircraftVolume != prevVolume)
/*     */       {
/* 154 */         setEntitySoundVolume((Entity)this.theAircraft, this.aircraftVolume);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 159 */     boolean updateLocation = false;
/*     */ 
/*     */ 
/*     */     
/* 163 */     updateLocation = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     if (updateLocation && this.aircraftVolume > 0.0F) {
/*     */       
/* 173 */       if (isRide)
/*     */       {
/* 175 */         updateSoundLocation((Entity)this.theAircraft);
/*     */       }
/*     */       else
/*     */       {
/* 179 */         double px = this.thePlayer.field_70165_t;
/* 180 */         double py = this.thePlayer.field_70163_u;
/* 181 */         double pz = this.thePlayer.field_70161_v;
/* 182 */         double dx = this.theAircraft.field_70165_t - px;
/* 183 */         double dy = this.theAircraft.field_70163_u - py;
/* 184 */         double dz = this.theAircraft.field_70161_v - pz;
/* 185 */         double dist = info.soundRange / 16.0D;
/* 186 */         dx /= dist;
/* 187 */         dy /= dist;
/* 188 */         dz /= dist;
/* 189 */         updateSoundLocation(px + dx, py + dy, pz + dz);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 194 */     else if (isEntitySoundPlaying((Entity)this.theAircraft)) {
/*     */       
/* 196 */       stopEntitySound((Entity)this.theAircraft);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_SoundUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */