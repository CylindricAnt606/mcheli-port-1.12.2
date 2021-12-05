/*      */ package mcheli.mcheli.helicopter;
/*      */ 
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.aircraft.MCH_AircraftInfo;
/*      */ import mcheli.aircraft.MCH_EntityAircraft;
/*      */ import mcheli.aircraft.MCH_PacketStatusRequest;
/*      */ import mcheli.aircraft.MCH_Rotor;
/*      */ import mcheli.helicopter.MCH_HeliInfo;
/*      */ import mcheli.helicopter.MCH_HeliInfoManager;
/*      */ import mcheli.particles.MCH_ParticleParam;
/*      */ import mcheli.particles.MCH_ParticlesUtil;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_Lib;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MCH_EntityHeli
/*      */   extends MCH_EntityAircraft
/*      */ {
/*      */   public static final byte FOLD_STAT_FOLDED = 0;
/*      */   public static final byte FOLD_STAT_FOLDING = 1;
/*      */   public static final byte FOLD_STAT_UNFOLDED = 2;
/*      */   public static final byte FOLD_STAT_UNFOLDING = 3;
/*      */   private MCH_HeliInfo heliInfo;
/*   33 */   public double prevRotationRotor = 0.0D;
/*   34 */   public double rotationRotor = 0.0D; public MCH_Rotor[] rotors; public byte lastFoldBladeStat; public int foldBladesCooldown; public float prevRollFactor; public String getKindName() { return "helicopters"; } public String getEntityType() { return "Plane"; } public MCH_HeliInfo getHeliInfo() { return this.heliInfo; } public void changeType(String type) { if (!type.isEmpty()) this.heliInfo = MCH_HeliInfoManager.get(type);  if (this.heliInfo == null) { MCH_Lib.Log((Entity)this, "##### MCH_EntityHeli changeHeliType() Heli info null %d, %s, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), type, getEntityName() }); setDead(true); } else { setAcInfo((MCH_AircraftInfo)this.heliInfo); newSeats(getAcInfo().getNumSeatAndRack()); createRotors(); this.weapons = createWeapon(1 + getSeatNum()); initPartRotation(getRotYaw(), getRotPitch()); }  }
/*      */   public Item getItem() { return (getHeliInfo() != null) ? (Item)(getHeliInfo()).item : null; }
/*      */   public boolean canMountWithNearEmptyMinecart() { return MCH_Config.MountMinecartHeli.prmBool; }
/*      */   protected void func_70088_a() { super.func_70088_a(); this.field_70180_af.func_75682_a(30, Byte.valueOf((byte)2)); }
/*      */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) { super.func_70014_b(par1NBTTagCompound); par1NBTTagCompound.func_74780_a("RotorSpeed", getCurrentThrottle()); par1NBTTagCompound.func_74780_a("rotetionRotor", this.rotationRotor); par1NBTTagCompound.func_74757_a("FoldBlade", (getFoldBladeStat() == 0)); }
/*      */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) { super.func_70037_a(par1NBTTagCompound); boolean beforeFoldBlade = (getFoldBladeStat() == 0); if (getCommonUniqueId().isEmpty()) { setCommonUniqueId(par1NBTTagCompound.func_74779_i("HeliUniqueId")); MCH_Lib.Log((Entity)this, "# MCH_EntityHeli readEntityFromNBT() " + W_Entity.getEntityId((Entity)this) + ", " + getEntityName() + ", AircraftUniqueId=null, HeliUniqueId=" + getCommonUniqueId(), new Object[0]); }  if (getTypeName().isEmpty()) { setTypeName(par1NBTTagCompound.func_74779_i("HeliType")); MCH_Lib.Log((Entity)this, "# MCH_EntityHeli readEntityFromNBT() " + W_Entity.getEntityId((Entity)this) + ", " + getEntityName() + ", TypeName=null, HeliType=" + getTypeName(), new Object[0]); }  setCurrentThrottle(par1NBTTagCompound.func_74769_h("RotorSpeed")); this.rotationRotor = par1NBTTagCompound.func_74769_h("rotetionRotor"); setFoldBladeStat(par1NBTTagCompound.func_74767_n("FoldBlade") ? 0 : 2); if (this.heliInfo == null) { this.heliInfo = MCH_HeliInfoManager.get(getTypeName()); if (this.heliInfo == null) { MCH_Lib.Log((Entity)this, "##### MCH_EntityHeli readEntityFromNBT() Heli info null %d, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), getEntityName() }); setDead(true); } else { setAcInfo((MCH_AircraftInfo)this.heliInfo); }  }  if (!beforeFoldBlade && getFoldBladeStat() == 0) forceFoldBlade();  this.prevRotationRotor = this.rotationRotor; }
/*      */   public float getSoundVolume() { if (getAcInfo() != null && (getAcInfo()).throttleUpDown <= 0.0F) return 0.0F;  return (float)getCurrentThrottle() * 2.0F; }
/*      */   public float getSoundPitch() { return (float)(0.2D + getCurrentThrottle() * 0.2D); }
/*      */   public String getDefaultSoundName() { return "heli"; }
/*      */   public float getUnfoldLandingGearThrottle() { double x = this.field_70165_t - this.field_70169_q; double y = this.field_70163_u - this.field_70167_r; double z = this.field_70161_v - this.field_70166_s; float s = (getAcInfo()).speed / 3.5F; return (x * x + y * y + z * z <= s) ? 0.8F : 0.3F; }
/*      */   protected void createRotors() { if (this.heliInfo == null) return;  this.rotors = new MCH_Rotor[this.heliInfo.rotorList.size()]; int i = 0; for (MCH_HeliInfo.Rotor r : this.heliInfo.rotorList) { this.rotors[i] = new MCH_Rotor(r.bladeNum, r.bladeRot, this.field_70170_p.field_72995_K ? 2 : 2, (float)r.pos.field_72450_a, (float)r.pos.field_72448_b, (float)r.pos.field_72449_c, (float)r.rot.field_72450_a, (float)r.rot.field_72448_b, (float)r.rot.field_72449_c, r.haveFoldFunc); i++; }  }
/*      */   protected void forceFoldBlade() { if (this.heliInfo != null && this.rotors.length > 0) if (this.heliInfo.isEnableFoldBlade) for (MCH_Rotor r : this.rotors) { r.update((float)this.rotationRotor); foldBlades(); r.forceFold(); }    }
/*   46 */   public MCH_EntityHeli(World world) { super(world);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  452 */     this.prevRollFactor = 0.0F; this.heliInfo = null; this.currentSpeed = 0.07D; this.field_70156_m = true; func_70105_a(2.0F, 0.7F); this.field_70129_M = this.field_70131_O / 2.0F; this.field_70159_w = 0.0D; this.field_70181_x = 0.0D; this.field_70179_y = 0.0D; this.weapons = createWeapon(0); this.rotors = new MCH_Rotor[0]; this.lastFoldBladeStat = -1; if (this.field_70170_p.field_72995_K) this.foldBladesCooldown = 40;  }
/*      */   public boolean isFoldBlades() { if (this.heliInfo == null || this.rotors.length <= 0) return false;  return (getFoldBladeStat() == 0); }
/*      */   protected boolean canSwitchFoldBlades() { if (this.heliInfo == null || this.rotors.length <= 0) return false;  return (this.heliInfo.isEnableFoldBlade && getCurrentThrottle() <= 0.01D && this.foldBladesCooldown == 0 && (getFoldBladeStat() == 2 || getFoldBladeStat() == 0)); }
/*  455 */   protected boolean canUseBlades() { if (this.heliInfo == null) return false;  if (this.rotors.length <= 0) return true;  if (getFoldBladeStat() == 2) { for (MCH_Rotor r : this.rotors) { if (r.isFoldingOrUnfolding()) return false;  }  return true; }  return false; } protected void foldBlades() { if (this.heliInfo == null || this.rotors.length <= 0) return;  setCurrentThrottle(0.0D); for (MCH_Rotor r : this.rotors) r.startFold();  } public void unfoldBlades() { if (this.heliInfo == null || this.rotors.length <= 0) return;  for (MCH_Rotor r : this.rotors) r.startUnfold();  } public void onRideEntity(Entity ridingEntity) { if (ridingEntity instanceof mcheli.aircraft.MCH_EntitySeat) { if (this.heliInfo == null || this.rotors.length <= 0) return;  if (this.heliInfo.isEnableFoldBlade) { forceFoldBlade(); setFoldBladeStat((byte)0); }  }  } protected byte getFoldBladeStat() { return this.field_70180_af.func_75683_a(30); } public float getRollFactor() { float roll = super.getRollFactor();
/*      */     
/*  457 */     double d = func_70092_e(this.field_70169_q, this.field_70163_u, this.field_70166_s);
/*  458 */     double s = (getAcInfo()).speed;
/*  459 */     d = (s > 0.1D) ? (d / s) : 0.0D;
/*      */ 
/*      */     
/*  462 */     float f = this.prevRollFactor;
/*  463 */     this.prevRollFactor = roll;
/*  464 */     return (roll + f) / 2.0F; }
/*      */   public void setFoldBladeStat(byte b) { if (!this.field_70170_p.field_72995_K) if (b >= 0 && b <= 3) this.field_70180_af.func_75692_b(30, Byte.valueOf(b));   }
/*      */   public boolean canSwitchGunnerMode() { if (super.canSwitchGunnerMode() && canUseBlades()) { float roll = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotRoll())); float pitch = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotPitch())); if (roll < 40.0F && pitch < 40.0F) return true;  }  return false; }
/*  467 */   public boolean canSwitchHoveringMode() { if (super.canSwitchHoveringMode() && canUseBlades()) { float roll = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotRoll())); float pitch = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotPitch())); if (roll < 40.0F && pitch < 40.0F) return true;  }  return false; } public void onUpdateAircraft() { if (this.heliInfo == null) { changeType(getTypeName()); this.field_70169_q = this.field_70165_t; this.field_70167_r = this.field_70163_u; this.field_70166_s = this.field_70161_v; return; }  if (!this.isRequestedSyncStatus) { this.isRequestedSyncStatus = true; if (this.field_70170_p.field_72995_K) { int stat = getFoldBladeStat(); if (stat == 1 || stat == 0) forceFoldBlade();  MCH_PacketStatusRequest.requestStatus(this); }  }  if (this.lastRiddenByEntity == null && getRiddenByEntity() != null) initCurrentWeapon(getRiddenByEntity());  updateWeapons(); onUpdate_Seats(); onUpdate_Control(); onUpdate_Rotor(); this.field_70169_q = this.field_70165_t; this.field_70167_r = this.field_70163_u; this.field_70166_s = this.field_70161_v; if (!isDestroyed() && isHovering()) if (MathHelper.func_76135_e(getRotPitch()) < 70.0F) setRotPitch(getRotPitch() * 0.95F);   if (isDestroyed()) if (getCurrentThrottle() > 0.0D) { if (MCH_Lib.getBlockIdY((Entity)this, 3, -2) > 0) setCurrentThrottle(getCurrentThrottle() * 0.8D);  if (isExploded()) setCurrentThrottle(getCurrentThrottle() * 0.98D);  }   updateCameraViewers(); if (this.field_70170_p.field_72995_K) { onUpdate_Client(); } else { onUpdate_Server(); }  } public boolean canMouseRot() { return super.canMouseRot(); } public boolean canUpdatePitch(Entity player) { return (super.canUpdatePitch(player) && !isHovering()); } public boolean canUpdateRoll(Entity player) { return (super.canUpdateRoll(player) && !isHovering()); } public boolean isOverridePlayerPitch() { return (super.isOverridePlayerPitch() && !isHovering()); } public float getControlRotYaw(float mouseX, float mouseY, float tick) { return mouseX; }
/*  468 */   public float getControlRotPitch(float mouseX, float mouseY, float tick) { return mouseY; } public float getControlRotRoll(float mouseX, float mouseY, float tick) {
/*  469 */     return mouseX;
/*      */   }
/*      */   
/*      */   public void onUpdateAngles(float partialTicks) {
/*  473 */     if (isDestroyed())
/*      */       return; 
/*  475 */     float rotRoll = !isHovering() ? 0.04F : 0.07F;
/*  476 */     rotRoll = 1.0F - rotRoll * partialTicks;
/*      */     
/*  478 */     if (getRotRoll() > 0.1D && getRotRoll() < 65.0F)
/*      */     {
/*  480 */       setRotRoll(getRotRoll() * rotRoll);
/*      */     }
/*  482 */     if (getRotRoll() < -0.1D && getRotRoll() > -65.0F)
/*      */     {
/*  484 */       setRotRoll(getRotRoll() * rotRoll);
/*      */     }
/*      */     
/*  487 */     if (MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0) {
/*      */ 
/*      */       
/*  490 */       if (this.moveLeft && !this.moveRight)
/*      */       {
/*  492 */         setRotRoll(getRotRoll() - 1.2F * partialTicks);
/*      */       }
/*  494 */       if (this.moveRight && !this.moveLeft)
/*      */       {
/*  496 */         setRotRoll(getRotRoll() + 1.2F * partialTicks);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  501 */       if (MathHelper.func_76135_e(getRotPitch()) < 40.0F)
/*      */       {
/*  503 */         applyOnGroundPitch(0.97F);
/*      */       }
/*      */       
/*  506 */       if (this.heliInfo.isEnableFoldBlade && this.rotors.length > 0 && getFoldBladeStat() == 0 && !isDestroyed()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  511 */         if (this.moveLeft && !this.moveRight)
/*      */         {
/*  513 */           setRotYaw(getRotYaw() - 0.5F * partialTicks);
/*      */         }
/*  515 */         if (this.moveRight && !this.moveLeft)
/*      */         {
/*  517 */           setRotYaw(getRotYaw() + 0.5F * partialTicks);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_Rotor() {
/*  525 */     byte stat = getFoldBladeStat();
/*  526 */     boolean isEndSwitch = true;
/*      */ 
/*      */     
/*  529 */     if (stat != this.lastFoldBladeStat) {
/*      */ 
/*      */       
/*  532 */       if (stat == 1) {
/*      */         
/*  534 */         foldBlades();
/*      */       
/*      */       }
/*  537 */       else if (stat == 3) {
/*      */         
/*  539 */         unfoldBlades();
/*      */       } 
/*      */       
/*  542 */       if (this.field_70170_p.field_72995_K)
/*      */       {
/*  544 */         this.foldBladesCooldown = 40;
/*      */       }
/*      */       
/*  547 */       this.lastFoldBladeStat = stat;
/*      */ 
/*      */     
/*      */     }
/*  551 */     else if (this.foldBladesCooldown > 0) {
/*      */       
/*  553 */       this.foldBladesCooldown--;
/*      */     } 
/*      */ 
/*      */     
/*  557 */     for (MCH_Rotor r : this.rotors) {
/*      */       
/*  559 */       r.update((float)this.rotationRotor);
/*  560 */       if (r.isFoldingOrUnfolding())
/*      */       {
/*  562 */         isEndSwitch = false;
/*      */       }
/*      */     } 
/*      */     
/*  566 */     if (isEndSwitch)
/*      */     {
/*  568 */       if (stat == 1) {
/*      */         
/*  570 */         setFoldBladeStat((byte)0);
/*      */       }
/*  572 */       else if (stat == 3) {
/*      */         
/*  574 */         setFoldBladeStat((byte)2);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_Control() {
/*  581 */     if (isHoveringMode() && !canUseFuel(true))
/*      */     {
/*  583 */       switchHoveringMode(false);
/*      */     }
/*  585 */     if (this.isGunnerMode && !canUseFuel())
/*      */     {
/*  587 */       switchGunnerMode(false);
/*      */     }
/*      */     
/*  590 */     if (!isDestroyed() && (getRiddenByEntity() != null || isHoveringMode()) && canUseBlades() && isCanopyClose() && canUseFuel(true)) {
/*      */       
/*  592 */       if (!isHovering())
/*      */       {
/*  594 */         onUpdate_ControlNotHovering();
/*      */       }
/*      */       else
/*      */       {
/*  598 */         onUpdate_ControlHovering();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  603 */       if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.00125D); }
/*  604 */       else { setCurrentThrottle(0.0D); }
/*      */       
/*  606 */       if (this.heliInfo.isEnableFoldBlade && this.rotors.length > 0 && getFoldBladeStat() == 0 && this.field_70122_E && !isDestroyed())
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  612 */         onUpdate_ControlFoldBladeAndOnGround();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  617 */     if (this.field_70170_p.field_72995_K) {
/*      */ 
/*      */       
/*  620 */       if (!W_Lib.isClientPlayer(getRiddenByEntity())) {
/*      */         
/*  622 */         double ct = getThrottle();
/*      */         
/*  624 */         if (getCurrentThrottle() >= ct - 0.02D) { addCurrentThrottle(-0.01D); }
/*  625 */         else if (getCurrentThrottle() < ct) { addCurrentThrottle(0.01D); }
/*      */ 
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  631 */       setThrottle(getCurrentThrottle());
/*      */     } 
/*      */ 
/*      */     
/*  635 */     if (getCurrentThrottle() < 0.0D) setCurrentThrottle(0.0D); 
/*  636 */     this.prevRotationRotor = this.rotationRotor;
/*  637 */     float rp = (float)(1.0D - getCurrentThrottle());
/*  638 */     this.rotationRotor += ((1.0F - rp * rp * rp) * (getAcInfo()).rotorSpeed);
/*  639 */     this.rotationRotor %= 360.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_ControlNotHovering() {
/*  645 */     float throttleUpDown = (getAcInfo()).throttleUpDown;
/*  646 */     if (this.throttleUp) {
/*      */       
/*  648 */       if (getCurrentThrottle() < 1.0D) { addCurrentThrottle(0.02D * throttleUpDown); }
/*  649 */       else { setCurrentThrottle(1.0D); }
/*      */     
/*  651 */     } else if (this.throttleDown) {
/*      */       
/*  653 */       if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.014285714285714285D * throttleUpDown); }
/*  654 */       else { setCurrentThrottle(0.0D); }
/*      */     
/*  656 */     } else if (!this.field_70170_p.field_72995_K || W_Lib.isClientPlayer(getRiddenByEntity())) {
/*      */ 
/*      */       
/*  659 */       if (this.cs_heliAutoThrottleDown)
/*      */       {
/*  661 */         if (getCurrentThrottle() > 0.52D) {
/*      */           
/*  663 */           addCurrentThrottle(-0.01D * throttleUpDown);
/*      */         }
/*  665 */         else if (getCurrentThrottle() < 0.48D) {
/*      */           
/*  667 */           addCurrentThrottle(0.01D * throttleUpDown);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  673 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  675 */       boolean move = false;
/*  676 */       float yaw = getRotYaw();
/*  677 */       double x = 0.0D;
/*  678 */       double z = 0.0D;
/*  679 */       if (this.moveLeft && !this.moveRight) {
/*      */         
/*  681 */         yaw = getRotYaw() - 90.0F;
/*  682 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  683 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  684 */         move = true;
/*      */       } 
/*  686 */       if (this.moveRight && !this.moveLeft) {
/*      */         
/*  688 */         yaw = getRotYaw() + 90.0F;
/*  689 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  690 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  691 */         move = true;
/*      */       } 
/*      */       
/*  694 */       if (move) {
/*      */         
/*  696 */         double f = 1.0D;
/*  697 */         double d = Math.sqrt(x * x + z * z);
/*  698 */         this.field_70159_w -= x / d * 0.019999999552965164D * f * (getAcInfo()).speed;
/*  699 */         this.field_70179_y += z / d * 0.019999999552965164D * f * (getAcInfo()).speed;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_ControlHovering() {
/*  706 */     if (getCurrentThrottle() < 1.0D) { addCurrentThrottle(0.03333333333333333D); }
/*  707 */     else { setCurrentThrottle(1.0D); }
/*      */     
/*  709 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  711 */       boolean move = false;
/*  712 */       float yaw = getRotYaw();
/*  713 */       double x = 0.0D;
/*  714 */       double z = 0.0D;
/*  715 */       if (this.throttleUp) {
/*      */         
/*  717 */         yaw = getRotYaw();
/*  718 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  719 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  720 */         move = true;
/*      */       } 
/*  722 */       if (this.throttleDown) {
/*      */         
/*  724 */         yaw = getRotYaw() - 180.0F;
/*  725 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  726 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  727 */         move = true;
/*      */       } 
/*      */       
/*  730 */       if (this.moveLeft && !this.moveRight) {
/*      */         
/*  732 */         yaw = getRotYaw() - 90.0F;
/*  733 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  734 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  735 */         move = true;
/*      */       } 
/*  737 */       if (this.moveRight && !this.moveLeft) {
/*      */         
/*  739 */         yaw = getRotYaw() + 90.0F;
/*  740 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  741 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  742 */         move = true;
/*      */       } 
/*      */       
/*  745 */       if (move) {
/*      */         
/*  747 */         double d = Math.sqrt(x * x + z * z);
/*  748 */         this.field_70159_w -= x / d * 0.009999999776482582D * (getAcInfo()).speed;
/*  749 */         this.field_70179_y += z / d * 0.009999999776482582D * (getAcInfo()).speed;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_ControlFoldBladeAndOnGround() {
/*  756 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  758 */       boolean move = false;
/*  759 */       float yaw = getRotYaw();
/*  760 */       double x = 0.0D;
/*  761 */       double z = 0.0D;
/*  762 */       if (this.throttleUp) {
/*      */         
/*  764 */         yaw = getRotYaw();
/*  765 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  766 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  767 */         move = true;
/*      */       } 
/*  769 */       if (this.throttleDown) {
/*      */         
/*  771 */         yaw = getRotYaw() - 180.0F;
/*  772 */         x += Math.sin(yaw * Math.PI / 180.0D);
/*  773 */         z += Math.cos(yaw * Math.PI / 180.0D);
/*  774 */         move = true;
/*      */       } 
/*      */       
/*  777 */       if (move) {
/*      */         
/*  779 */         double d = Math.sqrt(x * x + z * z);
/*  780 */         this.field_70159_w -= x / d * 0.029999999329447746D;
/*  781 */         this.field_70179_y += z / d * 0.029999999329447746D;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_Particle2() {
/*  788 */     if (!this.field_70170_p.field_72995_K)
/*  789 */       return;  if (getHP() > getMaxHP() * 0.5D)
/*      */       return; 
/*  791 */     if (getHeliInfo() == null)
/*  792 */       return;  int rotorNum = (getHeliInfo()).rotorList.size();
/*  793 */     if (rotorNum <= 0)
/*      */       return; 
/*  795 */     if (this.isFirstDamageSmoke)
/*      */     {
/*  797 */       this.prevDamageSmokePos = new Vec3[rotorNum];
/*      */     }
/*      */     
/*  800 */     for (int ri = 0; ri < rotorNum; ri++) {
/*      */       
/*  802 */       Vec3 rotor_pos = ((MCH_HeliInfo.Rotor)(getHeliInfo()).rotorList.get(ri)).pos;
/*      */       
/*  804 */       float yaw = getRotYaw();
/*  805 */       float pitch = getRotPitch();
/*      */       
/*  807 */       Vec3 pos = MCH_Lib.RotVec3(rotor_pos, -yaw, -pitch, -getRotRoll());
/*  808 */       double x = this.field_70165_t + pos.field_72450_a;
/*  809 */       double y = this.field_70163_u + pos.field_72448_b;
/*  810 */       double z = this.field_70161_v + pos.field_72449_c;
/*      */       
/*  812 */       if (this.isFirstDamageSmoke)
/*      */       {
/*  814 */         this.prevDamageSmokePos[ri] = Vec3.func_72443_a(x, y, z);
/*      */       }
/*  816 */       Vec3 prev = this.prevDamageSmokePos[ri];
/*      */ 
/*      */ 
/*      */       
/*  820 */       double dx = x - prev.field_72450_a;
/*  821 */       double dy = y - prev.field_72448_b;
/*  822 */       double dz = z - prev.field_72449_c;
/*  823 */       int num = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 2.0F) + 1; double i;
/*  824 */       for (i = 0.0D; i < num; i++) {
/*      */         
/*  826 */         double p = getHP() / getMaxHP();
/*  827 */         if (p < (this.field_70146_Z.nextFloat() / 2.0F)) {
/*      */           
/*  829 */           float c = 0.2F + this.field_70146_Z.nextFloat() * 0.3F;
/*  830 */           MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", prev.field_72450_a + (x - prev.field_72450_a) * i / num, prev.field_72448_b + (y - prev.field_72448_b) * i / num, prev.field_72449_c + (z - prev.field_72449_c) * i / num);
/*      */ 
/*      */ 
/*      */           
/*  834 */           prm.motionX = (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  835 */           prm.motionY = this.field_70146_Z.nextDouble() * 0.1D;
/*  836 */           prm.motionZ = (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  837 */           prm.size = (this.field_70146_Z.nextInt(5) + 5.0F) * 1.0F;
/*  838 */           prm.setColor(0.7F + this.field_70146_Z.nextFloat() * 0.1F, c, c, c);
/*  839 */           MCH_ParticlesUtil.spawnParticle(prm);
/*      */           
/*  841 */           int ebi = this.field_70146_Z.nextInt(1 + this.extraBoundingBox.length);
/*  842 */           if (p < 0.3D && ebi > 0) {
/*      */             
/*  844 */             AxisAlignedBB bb = (this.extraBoundingBox[ebi - 1]).boundingBox;
/*  845 */             double bx = (bb.field_72336_d + bb.field_72340_a) / 2.0D;
/*  846 */             double by = (bb.field_72337_e + bb.field_72338_b) / 2.0D;
/*  847 */             double bz = (bb.field_72334_f + bb.field_72339_c) / 2.0D;
/*  848 */             prm.posX = bx;
/*  849 */             prm.posY = by;
/*  850 */             prm.posZ = bz;
/*  851 */             MCH_ParticlesUtil.spawnParticle(prm);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  856 */       (this.prevDamageSmokePos[ri]).field_72450_a = x;
/*  857 */       (this.prevDamageSmokePos[ri]).field_72448_b = y;
/*  858 */       (this.prevDamageSmokePos[ri]).field_72449_c = z;
/*      */     } 
/*  860 */     this.isFirstDamageSmoke = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Client() {
/*  867 */     if (getRiddenByEntity() != null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  872 */       if (W_Lib.isClientPlayer(getRiddenByEntity()))
/*      */       {
/*  874 */         (getRiddenByEntity()).field_70125_A = (getRiddenByEntity()).field_70127_C;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  879 */     if (this.aircraftPosRotInc > 0) {
/*      */       
/*  881 */       applyServerPositionAndRotation();
/*      */     }
/*      */     else {
/*      */       
/*  885 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*      */ 
/*      */ 
/*      */       
/*  889 */       if (!isDestroyed() && (this.field_70122_E || MCH_Lib.getBlockIdY((Entity)this, 1, -2) > 0)) {
/*      */         
/*  891 */         this.field_70159_w *= 0.95D;
/*  892 */         this.field_70179_y *= 0.95D;
/*  893 */         applyOnGroundPitch(0.95F);
/*      */       } 
/*      */       
/*  896 */       if (func_70090_H()) {
/*      */         
/*  898 */         this.field_70159_w *= 0.99D;
/*  899 */         this.field_70179_y *= 0.99D;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  904 */     if (isDestroyed()) {
/*      */       
/*  906 */       if (this.rotDestroyedYaw < 15.0F)
/*      */       {
/*  908 */         this.rotDestroyedYaw += 0.3F;
/*      */       }
/*  910 */       setRotYaw(getRotYaw() + this.rotDestroyedYaw * (float)getCurrentThrottle());
/*      */       
/*  912 */       if (MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0) {
/*      */         
/*  914 */         if (MathHelper.func_76135_e(getRotPitch()) < 10.0F)
/*      */         {
/*  916 */           setRotPitch(getRotPitch() + this.rotDestroyedPitch);
/*      */         }
/*  918 */         setRotRoll(getRotRoll() + this.rotDestroyedRoll);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  923 */     if (getRiddenByEntity() != null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  928 */     onUpdate_ParticleSandCloud(false);
/*  929 */     onUpdate_Particle2();
/*      */     
/*  931 */     updateCamera(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void onUpdate_Server() {
/*  937 */     double prevMotion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */     
/*  939 */     float ogp = (getAcInfo()).onGroundPitch;
/*      */ 
/*      */     
/*  942 */     if (!isHovering()) {
/*      */       
/*  944 */       double dp = 0.0D;
/*      */       
/*  946 */       if (canFloatWater())
/*      */       {
/*  948 */         dp = getWaterDepth();
/*      */       }
/*      */       
/*  951 */       if (dp == 0.0D) {
/*      */         
/*  953 */         this.field_70181_x += !func_70090_H() ? (getAcInfo()).gravity : (getAcInfo()).gravityInWater;
/*      */         
/*  955 */         float yaw = getRotYaw() / 180.0F * 3.1415927F;
/*  956 */         float pitch = getRotPitch();
/*  957 */         if (MCH_Lib.getBlockIdY((Entity)this, 3, -3) > 0)
/*      */         {
/*  959 */           pitch -= ogp;
/*      */         }
/*      */ 
/*      */         
/*  963 */         this.field_70159_w += 0.1D * MathHelper.func_76126_a(yaw) * this.currentSpeed * -(pitch * pitch * pitch / 30000.0F) * getCurrentThrottle();
/*  964 */         this.field_70179_y += 0.1D * MathHelper.func_76134_b(yaw) * this.currentSpeed * (pitch * pitch * pitch / 30000.0F) * getCurrentThrottle();
/*  965 */         double y = (MathHelper.func_76135_e(getRotPitch()) + MathHelper.func_76135_e(getRotRoll()));
/*  966 */         y *= 0.6000000238418579D;
/*  967 */         if (y <= 50.0D) {
/*      */           
/*  969 */           y = 1.0D - y / 50.0D;
/*      */         }
/*      */         else {
/*      */           
/*  973 */           y = 0.0D;
/*      */         } 
/*  975 */         double throttle = getCurrentThrottle();
/*  976 */         if (isDestroyed())
/*      */         {
/*  978 */           throttle *= 0.65D;
/*      */         }
/*  980 */         this.field_70181_x += (y * 0.025D + 0.03D) * throttle;
/*      */       }
/*      */       else {
/*      */         
/*  984 */         if (MathHelper.func_76135_e(getRotPitch()) < 40.0F) {
/*      */ 
/*      */           
/*  987 */           float pitch = getRotPitch();
/*  988 */           pitch -= ogp;
/*  989 */           pitch *= 0.9F;
/*  990 */           pitch += ogp;
/*  991 */           setRotPitch(pitch);
/*      */         } 
/*  993 */         if (MathHelper.func_76135_e(getRotRoll()) < 40.0F)
/*      */         {
/*  995 */           setRotRoll(getRotRoll() * 0.9F);
/*      */         }
/*      */         
/*  998 */         if (dp < 1.0D)
/*      */         {
/* 1000 */           this.field_70181_x -= 1.0E-4D;
/* 1001 */           this.field_70181_x += 0.007D * getCurrentThrottle();
/*      */         }
/*      */         else
/*      */         {
/* 1005 */           if (this.field_70181_x < 0.0D)
/*      */           {
/* 1007 */             this.field_70181_x *= 0.7D;
/*      */           }
/* 1009 */           this.field_70181_x += 0.007D;
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1015 */       if (this.field_70146_Z.nextInt(50) == 0) this.field_70159_w += (this.field_70146_Z.nextDouble() - 0.5D) / 30.0D; 
/* 1016 */       if (this.field_70146_Z.nextInt(50) == 0) this.field_70181_x += (this.field_70146_Z.nextDouble() - 0.5D) / 50.0D; 
/* 1017 */       if (this.field_70146_Z.nextInt(50) == 0) this.field_70179_y += (this.field_70146_Z.nextDouble() - 0.5D) / 30.0D;
/*      */     
/*      */     } 
/*      */     
/* 1021 */     double motion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */ 
/*      */     
/* 1024 */     float speedLimit = (getAcInfo()).speed;
/* 1025 */     if (motion > speedLimit) {
/*      */       
/* 1027 */       this.field_70159_w *= speedLimit / motion;
/* 1028 */       this.field_70179_y *= speedLimit / motion;
/* 1029 */       motion = speedLimit;
/*      */     } 
/*      */     
/* 1032 */     if (motion > prevMotion && this.currentSpeed < speedLimit) {
/*      */       
/* 1034 */       this.currentSpeed += (speedLimit - this.currentSpeed) / 35.0D;
/*      */       
/* 1036 */       if (this.currentSpeed > speedLimit)
/*      */       {
/* 1038 */         this.currentSpeed = speedLimit;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1043 */       this.currentSpeed -= (this.currentSpeed - 0.07D) / 35.0D;
/*      */       
/* 1045 */       if (this.currentSpeed < 0.07D)
/*      */       {
/* 1047 */         this.currentSpeed = 0.07D;
/*      */       }
/*      */     } 
/*      */     
/* 1051 */     if (this.field_70122_E) {
/*      */       
/* 1053 */       this.field_70159_w *= 0.5D;
/* 1054 */       this.field_70179_y *= 0.5D;
/*      */       
/* 1056 */       if (MathHelper.func_76135_e(getRotPitch()) < 40.0F) {
/*      */         
/* 1058 */         float pitch = getRotPitch();
/* 1059 */         pitch -= ogp;
/* 1060 */         pitch *= 0.9F;
/* 1061 */         pitch += ogp;
/* 1062 */         setRotPitch(pitch);
/*      */       } 
/*      */       
/* 1065 */       if (MathHelper.func_76135_e(getRotRoll()) < 40.0F)
/*      */       {
/* 1067 */         setRotRoll(getRotRoll() * 0.9F);
/*      */       }
/*      */     } 
/*      */     
/* 1071 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*      */ 
/*      */     
/* 1074 */     this.field_70181_x *= 0.95D;
/* 1075 */     this.field_70159_w *= 0.99D;
/* 1076 */     this.field_70179_y *= 0.99D;
/*      */     
/* 1078 */     func_70101_b(getRotYaw(), getRotPitch());
/*      */ 
/*      */ 
/*      */     
/* 1082 */     onUpdate_updateBlock();
/*      */     
/* 1084 */     if (getRiddenByEntity() != null && (getRiddenByEntity()).field_70128_L) {
/*      */       
/* 1086 */       unmountEntity();
/* 1087 */       this.field_70153_n = null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_EntityHeli.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */