/*      */ package mcheli.mcheli.plane;
/*      */ 
/*      */ import mcheli.MCH_Config;
/*      */ import mcheli.MCH_Lib;
/*      */ import mcheli.aircraft.MCH_AircraftInfo;
/*      */ import mcheli.aircraft.MCH_EntityAircraft;
/*      */ import mcheli.aircraft.MCH_PacketStatusRequest;
/*      */ import mcheli.aircraft.MCH_Parts;
/*      */ import mcheli.particles.MCH_ParticleParam;
/*      */ import mcheli.particles.MCH_ParticlesUtil;
/*      */ import mcheli.plane.MCP_PlaneInfo;
/*      */ import mcheli.plane.MCP_PlaneInfoManager;
/*      */ import mcheli.wrapper.W_Block;
/*      */ import mcheli.wrapper.W_Blocks;
/*      */ import mcheli.wrapper.W_Entity;
/*      */ import mcheli.wrapper.W_Lib;
/*      */ import mcheli.wrapper.W_WorldFunc;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MCP_EntityPlane
/*      */   extends MCH_EntityAircraft
/*      */ {
/*      */   private MCP_PlaneInfo planeInfo;
/*      */   public float soundVolume;
/*      */   public MCH_Parts partNozzle;
/*      */   public MCH_Parts partWing;
/*      */   public float rotationRotor;
/*      */   public float prevRotationRotor;
/*      */   public float addkeyRotValue;
/*      */   
/*      */   public MCP_EntityPlane(World world) {
/*   43 */     super(world);
/*      */     
/*   45 */     this.planeInfo = null;
/*      */     
/*   47 */     this.currentSpeed = 0.07D;
/*   48 */     this.field_70156_m = true;
/*   49 */     func_70105_a(2.0F, 0.7F);
/*   50 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*   51 */     this.field_70159_w = 0.0D;
/*   52 */     this.field_70181_x = 0.0D;
/*   53 */     this.field_70179_y = 0.0D;
/*      */     
/*   55 */     this.weapons = createWeapon(0);
/*      */     
/*   57 */     this.soundVolume = 0.0F;
/*      */     
/*   59 */     this.partNozzle = null;
/*   60 */     this.partWing = null;
/*      */     
/*   62 */     this.field_70138_W = 0.6F;
/*      */     
/*   64 */     this.rotationRotor = 0.0F;
/*   65 */     this.prevRotationRotor = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getKindName() {
/*   70 */     return "planes";
/*      */   }
/*      */   
/*      */   public String getEntityType() {
/*   74 */     return "Plane";
/*      */   }
/*      */   
/*      */   public MCP_PlaneInfo getPlaneInfo() {
/*   78 */     return this.planeInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void changeType(String type) {
/*   83 */     if (!type.isEmpty())
/*      */     {
/*   85 */       this.planeInfo = MCP_PlaneInfoManager.get(type);
/*      */     }
/*   87 */     if (this.planeInfo == null) {
/*      */       
/*   89 */       MCH_Lib.Log((Entity)this, "##### MCP_EntityPlane changePlaneType() Plane info null %d, %s, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), type, getEntityName() });
/*      */       
/*   91 */       func_70106_y();
/*      */     }
/*      */     else {
/*      */       
/*   95 */       setAcInfo((MCH_AircraftInfo)this.planeInfo);
/*   96 */       newSeats(getAcInfo().getNumSeatAndRack());
/*      */       
/*   98 */       this.partNozzle = createNozzle(this.planeInfo);
/*   99 */       this.partWing = createWing(this.planeInfo);
/*      */       
/*  101 */       this.weapons = createWeapon(1 + getSeatNum());
/*      */       
/*  103 */       initPartRotation(getRotYaw(), getRotPitch());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Item getItem() {
/*  109 */     return (getPlaneInfo() != null) ? (Item)(getPlaneInfo()).item : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMountWithNearEmptyMinecart() {
/*  114 */     return MCH_Config.MountMinecartPlane.prmBool;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  119 */     super.func_70088_a();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/*  125 */     super.func_70014_b(par1NBTTagCompound);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/*  131 */     super.func_70037_a(par1NBTTagCompound);
/*      */     
/*  133 */     if (this.planeInfo == null) {
/*      */       
/*  135 */       this.planeInfo = MCP_PlaneInfoManager.get(getTypeName());
/*  136 */       if (this.planeInfo == null) {
/*      */         
/*  138 */         MCH_Lib.Log((Entity)this, "##### MCP_EntityPlane readEntityFromNBT() Plane info null %d, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), getEntityName() });
/*      */         
/*  140 */         func_70106_y();
/*      */       }
/*      */       else {
/*      */         
/*  144 */         setAcInfo((MCH_AircraftInfo)this.planeInfo);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70106_y() {
/*  157 */     super.func_70106_y();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumEjectionSeat() {
/*  162 */     if (getAcInfo() != null)
/*      */     {
/*  164 */       if ((getAcInfo()).isEnableEjectionSeat) {
/*      */         
/*  166 */         int n = getSeatNum() + 1;
/*  167 */         return (n <= 2) ? n : 0;
/*      */       } 
/*      */     }
/*  170 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onInteractFirst(EntityPlayer player) {
/*  175 */     this.addkeyRotValue = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSwitchGunnerMode() {
/*  184 */     if (!super.canSwitchGunnerMode()) return false;
/*      */     
/*  186 */     float roll = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotRoll()));
/*  187 */     float pitch = MathHelper.func_76135_e(MathHelper.func_76142_g(getRotPitch()));
/*  188 */     if (roll > 40.0F || pitch > 40.0F)
/*      */     {
/*  190 */       return false;
/*      */     }
/*      */     
/*  193 */     return (getCurrentThrottle() > 0.6000000238418579D && MCH_Lib.getBlockIdY((Entity)this, 3, -5) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdateAircraft() {
/*  202 */     if (this.planeInfo == null) {
/*      */       
/*  204 */       changeType(getTypeName());
/*  205 */       this.field_70169_q = this.field_70165_t;
/*  206 */       this.field_70167_r = this.field_70163_u;
/*  207 */       this.field_70166_s = this.field_70161_v;
/*      */       
/*      */       return;
/*      */     } 
/*  211 */     if (!this.isRequestedSyncStatus) {
/*      */       
/*  213 */       this.isRequestedSyncStatus = true;
/*  214 */       if (this.field_70170_p.field_72995_K)
/*      */       {
/*  216 */         MCH_PacketStatusRequest.requestStatus(this);
/*      */       }
/*      */     } 
/*      */     
/*  220 */     if (this.lastRiddenByEntity == null && getRiddenByEntity() != null)
/*      */     {
/*  222 */       initCurrentWeapon(getRiddenByEntity());
/*      */     }
/*      */     
/*  225 */     updateWeapons();
/*  226 */     onUpdate_Seats();
/*  227 */     onUpdate_Control();
/*      */     
/*  229 */     this.prevRotationRotor = this.rotationRotor;
/*  230 */     this.rotationRotor = (float)(this.rotationRotor + getCurrentThrottle() * (getAcInfo()).rotorSpeed);
/*  231 */     if (this.rotationRotor > 360.0F) {
/*      */       
/*  233 */       this.rotationRotor -= 360.0F;
/*  234 */       this.prevRotationRotor -= 360.0F;
/*      */     } 
/*  236 */     if (this.rotationRotor < 0.0F) {
/*      */       
/*  238 */       this.rotationRotor += 360.0F;
/*  239 */       this.prevRotationRotor += 360.0F;
/*      */     } 
/*      */     
/*  242 */     if (this.field_70122_E && getVtolMode() == 0 && this.planeInfo.isDefaultVtol)
/*      */     {
/*  244 */       swithVtolMode(true);
/*      */     }
/*      */     
/*  247 */     this.field_70169_q = this.field_70165_t;
/*  248 */     this.field_70167_r = this.field_70163_u;
/*  249 */     this.field_70166_s = this.field_70161_v;
/*      */ 
/*      */     
/*  252 */     if (!isDestroyed() && isHovering())
/*      */     {
/*  254 */       if (MathHelper.func_76135_e(getRotPitch()) < 70.0F)
/*      */       {
/*  256 */         setRotPitch(getRotPitch() * 0.95F, "isHovering()");
/*      */       }
/*      */     }
/*      */     
/*  260 */     if (isDestroyed())
/*      */     {
/*  262 */       if (getCurrentThrottle() > 0.0D) {
/*      */         
/*  264 */         if (MCH_Lib.getBlockIdY((Entity)this, 3, -2) > 0)
/*      */         {
/*  266 */           setCurrentThrottle(getCurrentThrottle() * 0.8D);
/*      */         }
/*  268 */         if (isExploded())
/*      */         {
/*  270 */           setCurrentThrottle(getCurrentThrottle() * 0.98D);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  275 */     updateCameraViewers();
/*      */ 
/*      */     
/*  278 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  280 */       onUpdate_Client();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  285 */       onUpdate_Server();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canUpdateYaw(Entity player) {
/*  291 */     return (super.canUpdateYaw(player) && !isHovering());
/*      */   }
/*      */   
/*      */   public boolean canUpdatePitch(Entity player) {
/*  295 */     return (super.canUpdatePitch(player) && !isHovering());
/*      */   }
/*      */   
/*      */   public boolean canUpdateRoll(Entity player) {
/*  299 */     return (super.canUpdateRoll(player) && !isHovering());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getYawFactor() {
/*  304 */     float yaw = (getVtolMode() > 0) ? (getPlaneInfo()).vtolYaw : super.getYawFactor();
/*  305 */     return yaw * 0.8F;
/*      */   }
/*      */   
/*      */   public float getPitchFactor() {
/*  309 */     float pitch = (getVtolMode() > 0) ? (getPlaneInfo()).vtolPitch : super.getPitchFactor();
/*  310 */     return pitch * 0.8F;
/*      */   }
/*      */   
/*      */   public float getRollFactor() {
/*  314 */     float roll = (getVtolMode() > 0) ? (getPlaneInfo()).vtolYaw : super.getRollFactor();
/*  315 */     return roll * 0.8F;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOverridePlayerPitch() {
/*  320 */     return (super.isOverridePlayerPitch() && !isHovering());
/*      */   }
/*      */   
/*      */   public boolean isOverridePlayerYaw() {
/*  324 */     return (super.isOverridePlayerYaw() && !isHovering());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getControlRotYaw(float mouseX, float mouseY, float tick) {
/*  329 */     if (MCH_Config.MouseControlFlightSimMode.prmBool) {
/*      */       
/*  331 */       rotationByKey(tick);
/*  332 */       return this.addkeyRotValue * 20.0F;
/*      */     } 
/*      */     
/*  335 */     return mouseX;
/*      */   } public float getControlRotPitch(float mouseX, float mouseY, float tick) {
/*  337 */     return mouseY;
/*      */   }
/*      */   public float getControlRotRoll(float mouseX, float mouseY, float tick) {
/*  340 */     if (MCH_Config.MouseControlFlightSimMode.prmBool)
/*      */     {
/*  342 */       return mouseX * 2.0F;
/*      */     }
/*  344 */     if (getVtolMode() == 0)
/*      */     {
/*  346 */       return mouseX * 0.5F;
/*      */     }
/*  348 */     return mouseX;
/*      */   }
/*      */ 
/*      */   
/*      */   private void rotationByKey(float partialTicks) {
/*  353 */     float rot = 0.2F;
/*      */     
/*  355 */     if (!MCH_Config.MouseControlFlightSimMode.prmBool)
/*      */     {
/*  357 */       if (getVtolMode() != 0)
/*      */       {
/*  359 */         rot *= 0.0F;
/*      */       }
/*      */     }
/*      */     
/*  363 */     if (this.moveLeft && !this.moveRight)
/*      */     {
/*  365 */       this.addkeyRotValue -= rot * partialTicks;
/*      */     }
/*  367 */     if (this.moveRight && !this.moveLeft)
/*      */     {
/*  369 */       this.addkeyRotValue += rot * partialTicks;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdateAngles(float partialTicks) {
/*  375 */     if (isDestroyed())
/*      */       return; 
/*  377 */     if (this.isGunnerMode) {
/*      */       
/*  379 */       setRotPitch(getRotPitch() * 0.95F);
/*  380 */       setRotYaw(getRotYaw() + (getAcInfo()).autoPilotRot * 0.2F);
/*  381 */       if (MathHelper.func_76135_e(getRotRoll()) > 20.0F)
/*      */       {
/*  383 */         setRotRoll(getRotRoll() * 0.95F);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  388 */     boolean isFly = (MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0);
/*  389 */     if (!isFly || isFreeLookMode() || this.isGunnerMode || ((getAcInfo()).isFloat && getWaterDepth() > 0.0D)) {
/*      */       
/*  391 */       float gmy = 1.0F;
/*  392 */       if (!isFly) {
/*      */         
/*  394 */         gmy = (getAcInfo()).mobilityYawOnGround;
/*      */ 
/*      */         
/*  397 */         if (!(getAcInfo()).canRotOnGround) {
/*      */           
/*  399 */           Block block = MCH_Lib.getBlockY((Entity)this, 3, -2, false);
/*  400 */           if (!W_Block.isEqual(block, W_Block.getWater()) && !W_Block.isEqual(block, W_Blocks.field_150350_a))
/*      */           {
/*      */             
/*  403 */             gmy = 0.0F;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  409 */       if (this.moveLeft && !this.moveRight)
/*      */       {
/*  411 */         setRotYaw(getRotYaw() - 0.6F * gmy * partialTicks);
/*      */       }
/*  413 */       if (this.moveRight && !this.moveLeft)
/*      */       {
/*  415 */         setRotYaw(getRotYaw() + 0.6F * gmy * partialTicks);
/*      */       }
/*      */     }
/*  418 */     else if (isFly) {
/*      */       
/*  420 */       if (!MCH_Config.MouseControlFlightSimMode.prmBool) {
/*      */         
/*  422 */         rotationByKey(partialTicks);
/*  423 */         setRotRoll(getRotRoll() + this.addkeyRotValue * 0.5F * (getAcInfo()).mobilityRoll);
/*      */       } 
/*      */     } 
/*      */     
/*  427 */     this.addkeyRotValue = (float)(this.addkeyRotValue * (1.0D - (0.1F * partialTicks)));
/*      */     
/*  429 */     if (!isFly && MathHelper.func_76135_e(getRotPitch()) < 40.0F)
/*      */     {
/*  431 */       applyOnGroundPitch(0.97F);
/*      */     }
/*      */     
/*  434 */     if (getNozzleRotation() > 0.001F) {
/*      */ 
/*      */       
/*  437 */       float rot = 1.0F - 0.03F * partialTicks;
/*  438 */       setRotPitch(getRotPitch() * rot);
/*      */       
/*  440 */       rot = 1.0F - 0.1F * partialTicks;
/*  441 */       setRotRoll(getRotRoll() * rot);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onUpdate_Control() {
/*  447 */     if (this.isGunnerMode && !canUseFuel())
/*      */     {
/*  449 */       switchGunnerMode(false);
/*      */     }
/*      */     
/*  452 */     this.throttleBack = (float)(this.throttleBack * 0.8D);
/*      */     
/*  454 */     if (getRiddenByEntity() != null && !(getRiddenByEntity()).field_70128_L && isCanopyClose() && canUseWing() && canUseFuel() && !isDestroyed())
/*      */     
/*      */     { 
/*  457 */       onUpdate_ControlNotHovering(); }
/*      */     
/*  459 */     else if (isTargetDrone() && canUseFuel() && !isDestroyed())
/*      */     
/*  461 */     { this.throttleUp = true;
/*  462 */       onUpdate_ControlNotHovering();
/*      */       
/*      */        }
/*      */     
/*  466 */     else if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.0025D * (getAcInfo()).throttleUpDown); }
/*  467 */     else { setCurrentThrottle(0.0D); }
/*      */     
/*  469 */     if (getCurrentThrottle() < 0.0D) setCurrentThrottle(0.0D);
/*      */ 
/*      */     
/*  472 */     if (this.field_70170_p.field_72995_K) {
/*      */ 
/*      */       
/*  475 */       if (!W_Lib.isClientPlayer(getRiddenByEntity()))
/*      */       {
/*  477 */         double ct = getThrottle();
/*      */         
/*  479 */         if (getCurrentThrottle() > ct) addCurrentThrottle(-0.005D); 
/*  480 */         if (getCurrentThrottle() < ct) addCurrentThrottle(0.005D);
/*      */       
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  486 */       setThrottle(getCurrentThrottle());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_ControlNotHovering() {
/*  493 */     if (!this.isGunnerMode) {
/*      */       
/*  495 */       float throttleUpDown = (getAcInfo()).throttleUpDown;
/*  496 */       boolean turn = ((this.moveLeft && !this.moveRight) || (!this.moveLeft && this.moveRight));
/*  497 */       float pivotTurnThrottle = (getAcInfo()).pivotTurnThrottle;
/*      */       
/*  499 */       boolean localThrottleUp = this.throttleUp;
/*  500 */       if (turn && getCurrentThrottle() < (getAcInfo()).pivotTurnThrottle)
/*      */       {
/*  502 */         if (!localThrottleUp && !this.throttleDown) {
/*      */           
/*  504 */           localThrottleUp = true;
/*  505 */           throttleUpDown *= 2.0F;
/*      */         } 
/*      */       }
/*      */       
/*  509 */       if (localThrottleUp) {
/*      */         
/*  511 */         float f = throttleUpDown;
/*  512 */         if (getRidingEntity() != null) {
/*      */           
/*  514 */           double mx = (getRidingEntity()).field_70159_w;
/*  515 */           double mz = (getRidingEntity()).field_70179_y;
/*  516 */           f *= MathHelper.func_76133_a(mx * mx + mz * mz) * (getAcInfo()).throttleUpDownOnEntity;
/*      */         } 
/*      */         
/*  519 */         if ((getAcInfo()).enableBack && this.throttleBack > 0.0F) {
/*      */           
/*  521 */           this.throttleBack = (float)(this.throttleBack - 0.01D * f);
/*      */         }
/*      */         else {
/*      */           
/*  525 */           this.throttleBack = 0.0F;
/*  526 */           if (getCurrentThrottle() < 1.0D) { addCurrentThrottle(0.01D * f); }
/*  527 */           else { setCurrentThrottle(1.0D); }
/*      */         
/*      */         } 
/*  530 */       } else if (this.throttleDown) {
/*      */         
/*  532 */         if (getCurrentThrottle() > 0.0D)
/*      */         {
/*  534 */           addCurrentThrottle(-0.01D * throttleUpDown);
/*      */         }
/*      */         else
/*      */         {
/*  538 */           setCurrentThrottle(0.0D);
/*      */           
/*  540 */           if ((getAcInfo()).enableBack)
/*      */           {
/*  542 */             this.throttleBack = (float)(this.throttleBack + 0.0025D * throttleUpDown);
/*  543 */             if (this.throttleBack > 0.6F)
/*      */             {
/*  545 */               this.throttleBack = 0.6F;
/*      */             
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  553 */       else if (this.cs_planeAutoThrottleDown) {
/*      */         
/*  555 */         if (getCurrentThrottle() > 0.0D) {
/*      */           
/*  557 */           addCurrentThrottle(-0.005D * throttleUpDown);
/*  558 */           if (getCurrentThrottle() <= 0.0D)
/*      */           {
/*  560 */             setCurrentThrottle(0.0D);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Particle() {
/*  572 */     if (this.field_70170_p.field_72995_K) {
/*      */       
/*  574 */       onUpdate_ParticleLandingGear();
/*  575 */       onUpdate_ParticleNozzle();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Particle2() {
/*  582 */     if (!this.field_70170_p.field_72995_K)
/*  583 */       return;  if (getHP() >= getMaxHP() * 0.5D)
/*      */       return; 
/*  585 */     if (getPlaneInfo() == null)
/*  586 */       return;  int rotorNum = (getPlaneInfo()).rotorList.size();
/*  587 */     if (rotorNum < 0) rotorNum = 0;
/*      */     
/*  589 */     if (this.isFirstDamageSmoke)
/*      */     {
/*      */       
/*  592 */       this.prevDamageSmokePos = new Vec3[rotorNum + 1];
/*      */     }
/*      */     
/*  595 */     float yaw = getRotYaw();
/*  596 */     float pitch = getRotPitch();
/*  597 */     float roll = getRotRoll();
/*  598 */     boolean spawnSmoke = true;
/*      */     
/*  600 */     for (int ri = 0; ri < rotorNum; ri++) {
/*      */       
/*  602 */       if (getHP() >= getMaxHP() * 0.2D && getMaxHP() > 0) {
/*      */         
/*  604 */         int d = (int)((getHP() / getMaxHP() - 0.2D) / 0.3D * 15.0D);
/*  605 */         if (d > 0 && this.field_70146_Z.nextInt(d) > 0)
/*      */         {
/*  607 */           spawnSmoke = false;
/*      */         }
/*      */       } 
/*  610 */       Vec3 rotor_pos = ((MCP_PlaneInfo.Rotor)(getPlaneInfo()).rotorList.get(ri)).pos;
/*  611 */       Vec3 pos = MCH_Lib.RotVec3(rotor_pos, -yaw, -pitch, -roll);
/*  612 */       double x = this.field_70165_t + pos.field_72450_a;
/*  613 */       double y = this.field_70163_u + pos.field_72448_b;
/*  614 */       double z = this.field_70161_v + pos.field_72449_c;
/*      */       
/*  616 */       onUpdate_Particle2SpawnSmoke(ri, x, y, z, 1.0F, spawnSmoke);
/*      */     } 
/*      */     
/*  619 */     spawnSmoke = true;
/*  620 */     if (getHP() >= getMaxHP() * 0.2D && getMaxHP() > 0) {
/*      */       
/*  622 */       int d = (int)((getHP() / getMaxHP() - 0.2D) / 0.3D * 15.0D);
/*  623 */       if (d > 0 && this.field_70146_Z.nextInt(d) > 0)
/*      */       {
/*  625 */         spawnSmoke = false;
/*      */       }
/*      */     } 
/*  628 */     double px = this.field_70165_t;
/*  629 */     double py = this.field_70163_u;
/*  630 */     double pz = this.field_70161_v;
/*  631 */     if (getSeatInfo(0) != null && (getSeatInfo(0)).pos != null) {
/*      */       
/*  633 */       Vec3 pos = MCH_Lib.RotVec3(0.0D, (getSeatInfo(0)).pos.field_72448_b, -2.0D, -yaw, -pitch, -roll);
/*  634 */       px += pos.field_72450_a;
/*  635 */       py += pos.field_72448_b;
/*  636 */       pz += pos.field_72449_c;
/*      */     } 
/*  638 */     onUpdate_Particle2SpawnSmoke(rotorNum, px, py, pz, (rotorNum == 0) ? 2.0F : 1.0F, spawnSmoke);
/*      */     
/*  640 */     this.isFirstDamageSmoke = false;
/*      */   }
/*      */   
/*      */   public void onUpdate_Particle2SpawnSmoke(int ri, double x, double y, double z, float size, boolean spawnSmoke) {
/*  644 */     if (this.isFirstDamageSmoke || this.prevDamageSmokePos[ri] == null)
/*      */     {
/*  646 */       this.prevDamageSmokePos[ri] = Vec3.func_72443_a(x, y, z);
/*      */     }
/*  648 */     Vec3 prev = this.prevDamageSmokePos[ri];
/*      */ 
/*      */ 
/*      */     
/*  652 */     double dx = x - prev.field_72450_a;
/*  653 */     double dy = y - prev.field_72448_b;
/*  654 */     double dz = z - prev.field_72449_c;
/*  655 */     int num = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) / 0.3D) + 1;
/*  656 */     for (int i = 0; i < num; i++) {
/*      */       
/*  658 */       float c = 0.2F + this.field_70146_Z.nextFloat() * 0.3F;
/*      */       
/*  660 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", prev.field_72450_a + (x - prev.field_72450_a) * i / 3.0D, prev.field_72448_b + (y - prev.field_72448_b) * i / 3.0D, prev.field_72449_c + (z - prev.field_72449_c) * i / 3.0D);
/*      */ 
/*      */ 
/*      */       
/*  664 */       prm.motionX = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  665 */       prm.motionY = size * this.field_70146_Z.nextDouble() * 0.1D;
/*  666 */       prm.motionZ = size * (this.field_70146_Z.nextDouble() - 0.5D) * 0.3D;
/*  667 */       prm.size = size * (this.field_70146_Z.nextInt(5) + 5.0F) * 1.0F;
/*  668 */       prm.setColor(0.7F + this.field_70146_Z.nextFloat() * 0.1F, c, c, c);
/*  669 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */     
/*  672 */     (this.prevDamageSmokePos[ri]).field_72450_a = x;
/*  673 */     (this.prevDamageSmokePos[ri]).field_72448_b = y;
/*  674 */     (this.prevDamageSmokePos[ri]).field_72449_c = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdate_ParticleLandingGear() {
/*  679 */     double d = this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y;
/*  680 */     if (d > 0.01D) {
/*      */       
/*  682 */       int x = MathHelper.func_76128_c(this.field_70165_t + 0.5D);
/*  683 */       int y = MathHelper.func_76128_c(this.field_70163_u - 0.5D);
/*  684 */       int z = MathHelper.func_76128_c(this.field_70161_v + 0.5D);
/*  685 */       MCH_ParticlesUtil.spawnParticleTileCrack(this.field_70170_p, x, y, z, this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, -this.field_70159_w * 4.0D, 1.5D, -this.field_70179_y * 4.0D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void onUpdate_ParticleSplash() {
/*  698 */     if (getAcInfo() == null)
/*  699 */       return;  if (!this.field_70170_p.field_72995_K)
/*      */       return; 
/*  701 */     double mx = this.field_70165_t - this.field_70169_q;
/*  702 */     double mz = this.field_70161_v - this.field_70166_s;
/*  703 */     double dist = mx * mx + mz * mz;
/*  704 */     if (dist > 1.0D) dist = 1.0D; 
/*  705 */     for (MCH_AircraftInfo.ParticleSplash p : (getAcInfo()).particleSplashs) {
/*      */       
/*  707 */       for (int i = 0; i < p.num; i++) {
/*      */         
/*  709 */         if (dist > 0.03D + this.field_70146_Z.nextFloat() * 0.1D)
/*      */         {
/*  711 */           setParticleSplash(p.pos, -mx * p.acceleration, p.motionY, -mz * p.acceleration, p.gravity, p.size * (0.5D + dist * 0.5D), p.age);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setParticleSplash(Vec3 pos, double mx, double my, double mz, float gravity, double size, int age) {
/*  726 */     Vec3 v = getTransformedPosition(pos);
/*  727 */     v = v.func_72441_c(this.field_70146_Z.nextDouble() - 0.5D, (this.field_70146_Z.nextDouble() - 0.5D) * 0.5D, this.field_70146_Z.nextDouble() - 0.5D);
/*  728 */     int x = (int)(v.field_72450_a + 0.5D);
/*  729 */     int y = (int)(v.field_72448_b + 0.0D);
/*  730 */     int z = (int)(v.field_72449_c + 0.5D);
/*  731 */     if (W_WorldFunc.isBlockWater(this.field_70170_p, x, y, z)) {
/*      */       
/*  733 */       float c = this.field_70146_Z.nextFloat() * 0.3F + 0.7F;
/*      */       
/*  735 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", v.field_72450_a, v.field_72448_b, v.field_72449_c);
/*  736 */       prm.motionX = mx + (this.field_70146_Z.nextFloat() - 0.5D) * 0.7D;
/*  737 */       prm.motionY = my;
/*  738 */       prm.motionZ = mz + (this.field_70146_Z.nextFloat() - 0.5D) * 0.7D;
/*  739 */       prm.size = (float)size * (this.field_70146_Z.nextFloat() * 0.2F + 0.8F);
/*  740 */       prm.setColor(0.9F, c, c, c);
/*  741 */       prm.age = age + (int)(this.field_70146_Z.nextFloat() * 0.5D * age);
/*  742 */       prm.gravity = gravity;
/*      */       
/*  744 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdate_ParticleNozzle() {
/*  750 */     if (this.planeInfo == null || !this.planeInfo.haveNozzle())
/*  751 */       return;  if (getCurrentThrottle() <= 0.10000000149011612D)
/*      */       return; 
/*  753 */     float yaw = getRotYaw();
/*  754 */     float pitch = getRotPitch();
/*  755 */     float roll = getRotRoll();
/*      */     
/*  757 */     Vec3 nozzleRot = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -yaw - 180.0F, pitch - getNozzleRotation(), roll);
/*      */     
/*  759 */     for (MCH_AircraftInfo.DrawnPart nozzle : this.planeInfo.nozzles) {
/*      */       
/*  761 */       if (this.field_70146_Z.nextFloat() > getCurrentThrottle() * 1.5D)
/*  762 */         continue;  Vec3 nozzlePos = MCH_Lib.RotVec3(nozzle.pos, -yaw, -pitch, -roll);
/*  763 */       double x = this.field_70165_t + nozzlePos.field_72450_a + nozzleRot.field_72450_a;
/*  764 */       double y = this.field_70163_u + nozzlePos.field_72448_b + nozzleRot.field_72448_b;
/*  765 */       double z = this.field_70161_v + nozzlePos.field_72449_c + nozzleRot.field_72449_c;
/*      */       
/*  767 */       float a = 0.7F;
/*  768 */       if (W_WorldFunc.getBlockId(this.field_70170_p, (int)(x + nozzleRot.field_72450_a * 3.0D), (int)(y + nozzleRot.field_72448_b * 3.0D), (int)(z + nozzleRot.field_72449_c * 3.0D)) != 0)
/*      */       {
/*  770 */         a = 2.0F;
/*      */       }
/*  772 */       MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", x, y, z, nozzleRot.field_72450_a + ((this.field_70146_Z.nextFloat() - 0.5F) * a), nozzleRot.field_72448_b, nozzleRot.field_72449_c + ((this.field_70146_Z.nextFloat() - 0.5F) * a), 5.0F * (getAcInfo()).particlesScale);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  778 */       MCH_ParticlesUtil.spawnParticle(prm);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void destroyAircraft() {
/*  784 */     super.destroyAircraft();
/*  785 */     int inv = 1;
/*  786 */     if (getRotRoll() >= 0.0F) {
/*      */       
/*  788 */       if (getRotRoll() > 90.0F)
/*      */       {
/*  790 */         inv = -1;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  795 */     else if (getRotRoll() > -90.0F) {
/*      */       
/*  797 */       inv = -1;
/*      */     } 
/*      */     
/*  800 */     this.rotDestroyedRoll = (0.5F + this.field_70146_Z.nextFloat()) * inv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onUpdate_Client() {
/*  807 */     if (getRiddenByEntity() != null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  812 */       if (W_Lib.isClientPlayer(getRiddenByEntity()))
/*      */       {
/*  814 */         (getRiddenByEntity()).field_70125_A = (getRiddenByEntity()).field_70127_C;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  819 */     if (this.aircraftPosRotInc > 0) {
/*      */       
/*  821 */       applyServerPositionAndRotation();
/*      */     }
/*      */     else {
/*      */       
/*  825 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*      */ 
/*      */ 
/*      */       
/*  829 */       if (!isDestroyed() && (this.field_70122_E || MCH_Lib.getBlockIdY((Entity)this, 1, -2) > 0)) {
/*      */         
/*  831 */         this.field_70159_w *= 0.95D;
/*  832 */         this.field_70179_y *= 0.95D;
/*  833 */         applyOnGroundPitch(0.95F);
/*      */       } 
/*      */       
/*  836 */       if (func_70090_H()) {
/*      */         
/*  838 */         this.field_70159_w *= 0.99D;
/*  839 */         this.field_70179_y *= 0.99D;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  844 */     if (isDestroyed())
/*      */     {
/*  846 */       if (MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0) {
/*      */         
/*  848 */         if (MathHelper.func_76135_e(getRotPitch()) < 10.0F)
/*      */         {
/*  850 */           setRotPitch(getRotPitch() + this.rotDestroyedPitch);
/*      */         }
/*      */         
/*  853 */         float roll = MathHelper.func_76135_e(getRotRoll());
/*  854 */         if (roll < 45.0F || roll > 135.0F)
/*      */         {
/*  856 */           setRotRoll(getRotRoll() + this.rotDestroyedRoll);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  861 */       else if (MathHelper.func_76135_e(getRotPitch()) > 20.0F) {
/*      */         
/*  863 */         setRotPitch(getRotPitch() * 0.99F);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  869 */     if (getRiddenByEntity() != null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  874 */     updateSound();
/*      */     
/*  876 */     onUpdate_Particle();
/*  877 */     onUpdate_Particle2();
/*  878 */     onUpdate_ParticleSplash();
/*  879 */     onUpdate_ParticleSandCloud(true);
/*      */     
/*  881 */     updateCamera(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */ 
/*      */   
/*      */   private void onUpdate_Server() {
/*      */     Vec3 v;
/*  887 */     Entity rdnEnt = getRiddenByEntity();
/*  888 */     double prevMotion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */     
/*  890 */     double dp = 0.0D;
/*      */     
/*  892 */     if (canFloatWater())
/*      */     {
/*  894 */       dp = getWaterDepth();
/*      */     }
/*      */     
/*  897 */     boolean levelOff = this.isGunnerMode;
/*  898 */     if (dp == 0.0D) {
/*      */       
/*  900 */       if (isTargetDrone() && canUseFuel() && !isDestroyed()) {
/*      */         
/*  902 */         Block block = MCH_Lib.getBlockY((Entity)this, 3, -40, true);
/*      */         
/*  904 */         if (block == null || W_Block.isEqual(block, W_Blocks.field_150350_a)) {
/*      */           
/*  906 */           setRotYaw(getRotYaw() + (getAcInfo()).autoPilotRot * 1.0F);
/*  907 */           setRotPitch(getRotPitch() * 0.95F);
/*      */           
/*  909 */           if (canFoldLandingGear())
/*      */           {
/*  911 */             foldLandingGear();
/*      */           }
/*      */           
/*  914 */           levelOff = true;
/*      */         }
/*      */         else {
/*      */           
/*  918 */           block = MCH_Lib.getBlockY((Entity)this, 3, -5, true);
/*      */           
/*  920 */           if (block == null || W_Block.isEqual(block, W_Blocks.field_150350_a)) {
/*      */             
/*  922 */             setRotYaw(getRotYaw() + (getAcInfo()).autoPilotRot * 2.0F);
/*  923 */             if (getRotPitch() > -20.0F)
/*      */             {
/*  925 */               setRotPitch(getRotPitch() - 0.5F);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  931 */       if (!levelOff)
/*      */       {
/*      */         
/*  934 */         this.field_70181_x += 0.04D + (!func_70090_H() ? (getAcInfo()).gravity : (getAcInfo()).gravityInWater);
/*  935 */         this.field_70181_x += -0.047D * (1.0D - getCurrentThrottle());
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  940 */         this.field_70181_x *= 0.8D;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  945 */       setRotPitch(getRotPitch() * 0.8F, "getWaterDepth != 0");
/*      */       
/*  947 */       if (MathHelper.func_76135_e(getRotRoll()) < 40.0F)
/*      */       {
/*  949 */         setRotRoll(getRotRoll() * 0.9F);
/*      */       }
/*      */       
/*  952 */       if (dp < 1.0D) {
/*      */         
/*  954 */         this.field_70181_x -= 1.0E-4D;
/*  955 */         this.field_70181_x += 0.007D * getCurrentThrottle();
/*      */       }
/*      */       else {
/*      */         
/*  959 */         if (this.field_70181_x < 0.0D)
/*      */         {
/*  961 */           this.field_70181_x /= 2.0D;
/*      */         }
/*  963 */         this.field_70181_x += 0.007D;
/*      */       } 
/*      */     } 
/*      */     
/*  967 */     float throttle = (float)(getCurrentThrottle() / 10.0D);
/*      */ 
/*      */     
/*  970 */     if (getNozzleRotation() > 0.001F) {
/*      */       
/*  972 */       setRotPitch(getRotPitch() * 0.95F);
/*  973 */       v = MCH_Lib.Rot2Vec3(getRotYaw(), getRotPitch() - getNozzleRotation());
/*  974 */       if (getNozzleRotation() >= 90.0F)
/*      */       {
/*  976 */         v.field_72450_a *= 0.800000011920929D;
/*  977 */         v.field_72449_c *= 0.800000011920929D;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  982 */       v = MCH_Lib.Rot2Vec3(getRotYaw(), getRotPitch() - 10.0F);
/*      */     } 
/*      */     
/*  985 */     if (!levelOff)
/*      */     {
/*  987 */       if (getNozzleRotation() <= 0.01F) {
/*      */         
/*  989 */         this.field_70181_x += v.field_72448_b * throttle / 2.0D;
/*      */       }
/*      */       else {
/*      */         
/*  993 */         this.field_70181_x += v.field_72448_b * throttle / 8.0D;
/*      */       } 
/*      */     }
/*      */     
/*  997 */     boolean canMove = true;
/*      */     
/*  999 */     if (!(getAcInfo()).canMoveOnGround) {
/*      */       
/* 1001 */       Block block = MCH_Lib.getBlockY((Entity)this, 3, -2, false);
/* 1002 */       if (!W_Block.isEqual(block, W_Block.getWater()) && !W_Block.isEqual(block, W_Blocks.field_150350_a))
/*      */       {
/* 1004 */         canMove = false;
/*      */       }
/*      */     } 
/*      */     
/* 1008 */     if (canMove)
/*      */     {
/* 1010 */       if ((getAcInfo()).enableBack && this.throttleBack > 0.0F) {
/*      */         
/* 1012 */         this.field_70159_w -= v.field_72450_a * this.throttleBack;
/* 1013 */         this.field_70179_y -= v.field_72449_c * this.throttleBack;
/*      */       }
/*      */       else {
/*      */         
/* 1017 */         this.field_70159_w += v.field_72450_a * throttle;
/* 1018 */         this.field_70179_y += v.field_72449_c * throttle;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1023 */     double motion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*      */ 
/*      */     
/* 1026 */     float speedLimit = getMaxSpeed();
/* 1027 */     if (motion > speedLimit) {
/*      */       
/* 1029 */       this.field_70159_w *= speedLimit / motion;
/* 1030 */       this.field_70179_y *= speedLimit / motion;
/* 1031 */       motion = speedLimit;
/*      */     } 
/*      */     
/* 1034 */     if (motion > prevMotion && this.currentSpeed < speedLimit) {
/*      */       
/* 1036 */       this.currentSpeed += (speedLimit - this.currentSpeed) / 35.0D;
/*      */       
/* 1038 */       if (this.currentSpeed > speedLimit)
/*      */       {
/* 1040 */         this.currentSpeed = speedLimit;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1045 */       this.currentSpeed -= (this.currentSpeed - 0.07D) / 35.0D;
/*      */       
/* 1047 */       if (this.currentSpeed < 0.07D)
/*      */       {
/* 1049 */         this.currentSpeed = 0.07D;
/*      */       }
/*      */     } 
/*      */     
/* 1053 */     if (this.field_70122_E || MCH_Lib.getBlockIdY((Entity)this, 1, -2) > 0) {
/*      */       
/* 1055 */       this.field_70159_w *= (getAcInfo()).motionFactor;
/* 1056 */       this.field_70179_y *= (getAcInfo()).motionFactor;
/*      */       
/* 1058 */       if (MathHelper.func_76135_e(getRotPitch()) < 40.0F)
/*      */       {
/*      */         
/* 1061 */         applyOnGroundPitch(0.8F);
/*      */       }
/*      */     } 
/*      */     
/* 1065 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*      */ 
/*      */     
/* 1068 */     this.field_70181_x *= 0.95D;
/* 1069 */     this.field_70159_w *= (getAcInfo()).motionFactor;
/* 1070 */     this.field_70179_y *= (getAcInfo()).motionFactor;
/*      */     
/* 1072 */     func_70101_b(getRotYaw(), getRotPitch());
/*      */ 
/*      */     
/* 1075 */     onUpdate_updateBlock();
/*      */     
/* 1077 */     if (getRiddenByEntity() != null && (getRiddenByEntity()).field_70128_L) {
/*      */       
/* 1079 */       unmountEntity();
/* 1080 */       this.field_70153_n = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getMaxSpeed() {
/* 1086 */     float f = 0.0F;
/* 1087 */     if (this.partWing != null && (getPlaneInfo()).isVariableSweepWing) {
/*      */ 
/*      */       
/* 1090 */       f = ((getPlaneInfo()).sweepWingSpeed - (getPlaneInfo()).speed) * this.partWing.getFactor();
/*      */     }
/* 1092 */     else if (this.partHatch != null && (getPlaneInfo()).isVariableSweepWing) {
/*      */ 
/*      */       
/* 1095 */       f = ((getPlaneInfo()).sweepWingSpeed - (getPlaneInfo()).speed) * this.partHatch.getFactor();
/*      */     } 
/*      */     
/* 1098 */     return (getPlaneInfo()).speed + f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSoundVolume() {
/* 1107 */     if (getAcInfo() != null && (getAcInfo()).throttleUpDown <= 0.0F) return 0.0F; 
/* 1108 */     return this.soundVolume * 0.7F;
/*      */   }
/*      */   
/*      */   public void updateSound() {
/* 1112 */     float target = (float)getCurrentThrottle();
/*      */     
/* 1114 */     if (getRiddenByEntity() != null)
/*      */     {
/* 1116 */       if (this.partCanopy == null || getCanopyRotation() < 1.0F)
/*      */       {
/* 1118 */         target += 0.1F;
/*      */       }
/*      */     }
/*      */     
/* 1122 */     if (this.soundVolume < target) {
/*      */       
/* 1124 */       this.soundVolume += 0.02F;
/* 1125 */       if (this.soundVolume >= target)
/*      */       {
/* 1127 */         this.soundVolume = target;
/*      */       }
/*      */     }
/* 1130 */     else if (this.soundVolume > target) {
/*      */       
/* 1132 */       this.soundVolume -= 0.02F;
/* 1133 */       if (this.soundVolume <= target)
/*      */       {
/* 1135 */         this.soundVolume = target;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSoundPitch() {
/* 1142 */     return (float)(0.6D + getCurrentThrottle() * 0.4D);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDefaultSoundName() {
/* 1147 */     return "plane";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateParts(int stat) {
/* 1155 */     super.updateParts(stat);
/*      */     
/* 1157 */     if (isDestroyed())
/*      */       return; 
/* 1159 */     MCH_Parts[] parts = { this.partNozzle, this.partWing };
/*      */ 
/*      */     
/* 1162 */     for (MCH_Parts p : parts) {
/*      */       
/* 1164 */       if (p != null) {
/*      */         
/* 1166 */         p.updateStatusClient(stat);
/* 1167 */         p.update();
/*      */       } 
/*      */     } 
/*      */     
/* 1171 */     if (!this.field_70170_p.field_72995_K && this.partWing != null)
/*      */     {
/* 1173 */       if ((getPlaneInfo()).isVariableSweepWing && this.partWing.isON())
/*      */       {
/* 1175 */         if (getCurrentThrottle() >= 0.20000000298023224D)
/*      */         {
/*      */ 
/*      */           
/* 1179 */           if (getCurrentThrottle() < 0.5D || MCH_Lib.getBlockIdY((Entity)this, 1, -10) != 0)
/*      */           {
/* 1181 */             this.partWing.setStatusServer(false);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public float getUnfoldLandingGearThrottle() {
/* 1189 */     return 0.7F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSwitchVtol() {
/* 1197 */     if (this.planeInfo == null || !this.planeInfo.isEnableVtol) return false;
/*      */     
/* 1199 */     if (getModeSwitchCooldown() > 0) return false; 
/* 1200 */     if (getVtolMode() == 1) return false; 
/* 1201 */     if (MathHelper.func_76135_e(getRotRoll()) > 30.0F) return false;
/*      */     
/* 1203 */     if (this.field_70122_E && this.planeInfo.isDefaultVtol) return false;
/*      */     
/* 1205 */     setModeSwitchCooldown(20);
/* 1206 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getNozzleStat() {
/* 1211 */     return (this.partNozzle != null) ? this.partNozzle.getStatus() : false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVtolMode() {
/* 1217 */     if (!getNozzleStat())
/*      */     {
/* 1219 */       return (getNozzleRotation() <= 0.005F) ? 0 : 1;
/*      */     }
/*      */ 
/*      */     
/* 1223 */     return (getNozzleRotation() >= 89.995F) ? 2 : 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFuleConsumptionFactor() {
/* 1229 */     return getFuelConsumptionFactor() * ((getVtolMode() == 2) ? true : true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getNozzleRotation() {
/* 1235 */     return (this.partNozzle != null) ? this.partNozzle.rotation : 0.0F;
/*      */   }
/*      */   
/*      */   public float getPrevNozzleRotation() {
/* 1239 */     return (this.partNozzle != null) ? this.partNozzle.prevRotation : 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void swithVtolMode(boolean mode) {
/* 1244 */     if (this.partNozzle != null) {
/*      */ 
/*      */       
/* 1247 */       if (this.planeInfo.isDefaultVtol && this.field_70122_E && !mode)
/*      */         return; 
/* 1249 */       if (!this.field_70170_p.field_72995_K)
/*      */       {
/* 1251 */         this.partNozzle.setStatusServer(mode);
/*      */       }
/*      */       
/* 1254 */       if (getRiddenByEntity() != null && !(getRiddenByEntity()).field_70128_L)
/*      */       {
/* 1256 */         (getRiddenByEntity()).field_70125_A = (getRiddenByEntity()).field_70127_C = 0.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected MCH_Parts createNozzle(MCP_PlaneInfo info) {
/* 1264 */     MCH_Parts nozzle = null;
/* 1265 */     if (info.haveNozzle() || info.haveRotor() || info.isEnableVtol) {
/*      */       
/* 1267 */       nozzle = new MCH_Parts((Entity)this, 1, 31, "Nozzle");
/* 1268 */       nozzle.rotationMax = 90.0F;
/* 1269 */       nozzle.rotationInv = 1.5F;
/* 1270 */       nozzle.soundStartSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 1271 */       nozzle.soundEndSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 1272 */       nozzle.soundStartSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/* 1273 */       nozzle.soundEndSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/* 1274 */       nozzle.soundSwitching.setPrm("plane_cv", 1.0F, 0.5F);
/* 1275 */       if (info.isDefaultVtol)
/*      */       {
/* 1277 */         nozzle.forceSwitch(true);
/*      */       }
/*      */     } 
/* 1280 */     return nozzle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MCH_Parts createWing(MCP_PlaneInfo info) {
/* 1290 */     MCH_Parts wing = null;
/* 1291 */     if (this.planeInfo.haveWing()) {
/*      */       
/* 1293 */       wing = new MCH_Parts((Entity)this, 3, 31, "Wing");
/* 1294 */       wing.rotationMax = 90.0F;
/* 1295 */       wing.rotationInv = 2.5F;
/* 1296 */       wing.soundStartSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 1297 */       wing.soundEndSwichOn.setPrm("plane_cc", 1.0F, 0.5F);
/* 1298 */       wing.soundStartSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/* 1299 */       wing.soundEndSwichOff.setPrm("plane_cc", 1.0F, 0.5F);
/*      */     } 
/* 1301 */     return wing;
/*      */   }
/*      */   
/*      */   public boolean canUseWing() {
/* 1305 */     if (this.partWing == null) return true;
/*      */     
/* 1307 */     if ((getPlaneInfo()).isVariableSweepWing) {
/*      */       
/* 1309 */       if (getCurrentThrottle() < 0.2D)
/*      */       {
/* 1311 */         return this.partWing.isOFF();
/*      */       }
/* 1313 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1317 */     return this.partWing.isOFF();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canFoldWing() {
/* 1322 */     if (this.partWing == null || getModeSwitchCooldown() > 0) return false;
/*      */     
/* 1324 */     if ((getPlaneInfo()).isVariableSweepWing) {
/*      */       
/* 1326 */       if (this.field_70122_E == true || MCH_Lib.getBlockIdY((Entity)this, 3, -20) != 0)
/*      */       
/*      */       { 
/* 1329 */         if (getCurrentThrottle() > 0.10000000149011612D) return false;
/*      */         
/*      */         
/*      */          }
/*      */       
/* 1334 */       else if (getCurrentThrottle() < 0.699999988079071D) { return false; }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1340 */       if (!this.field_70122_E && MCH_Lib.getBlockIdY((Entity)this, 3, -3) == 0) return false; 
/* 1341 */       if (getCurrentThrottle() > 0.009999999776482582D) return false; 
/*      */     } 
/* 1343 */     return this.partWing.isOFF();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canUnfoldWing() {
/* 1348 */     if (this.partWing == null || getModeSwitchCooldown() > 0) return false; 
/* 1349 */     return this.partWing.isON();
/*      */   }
/*      */   
/*      */   public void foldWing(boolean fold) {
/* 1353 */     if (this.partWing == null || getModeSwitchCooldown() > 0)
/* 1354 */       return;  this.partWing.setStatusServer(fold);
/* 1355 */     setModeSwitchCooldown(20);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getWingRotation() {
/* 1360 */     return (this.partWing != null) ? this.partWing.rotation : 0.0F;
/*      */   }
/*      */   
/*      */   public float getPrevWingRotation() {
/* 1364 */     return (this.partWing != null) ? this.partWing.prevRotation : 0.0F;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_EntityPlane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */