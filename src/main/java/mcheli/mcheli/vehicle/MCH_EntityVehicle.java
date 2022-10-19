/*     */ package mcheli.mcheli.vehicle;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_PacketStatusRequest;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.weapon.MCH_WeaponSet;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityVehicle
/*     */   extends MCH_EntityAircraft
/*     */ {
/*     */   private MCH_VehicleInfo vehicleInfo;
/*     */   public boolean isUsedPlayer;
/*     */   public float lastRiderYaw;
/*     */   public float lastRiderPitch;
/*     */   
/*     */   public MCH_EntityVehicle(World world) {
/*  36 */     super(world);
/*     */     
/*  38 */     this.vehicleInfo = null;
/*     */     
/*  40 */     this.currentSpeed = 0.07D;
/*  41 */     this.field_70156_m = true;
/*  42 */     func_70105_a(2.0F, 0.7F);
/*  43 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  44 */     this.field_70159_w = 0.0D;
/*  45 */     this.field_70181_x = 0.0D;
/*  46 */     this.field_70179_y = 0.0D;
/*     */     
/*  48 */     this.isUsedPlayer = false;
/*  49 */     this.lastRiderYaw = 0.0F;
/*  50 */     this.lastRiderPitch = 0.0F;
/*     */     
/*  52 */     this.weapons = createWeapon(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKindName() {
/*  57 */     return "vehicles";
/*     */   }
/*     */   
/*     */   public String getEntityType() {
/*  61 */     return "Vehicle";
/*     */   }
/*     */   
/*     */   public MCH_VehicleInfo getVehicleInfo() {
/*  65 */     return this.vehicleInfo;
/*     */   }
/*     */   
/*     */   public void changeType(String type) {
/*  69 */     if (!type.isEmpty())
/*     */     {
/*  71 */       this.vehicleInfo = MCH_VehicleInfoManager.get(type);
/*     */     }
/*  73 */     if (this.vehicleInfo == null) {
/*     */       
/*  75 */       MCH_Lib.Log((Entity)this, "##### MCH_EntityVehicle changeVehicleType() Vehicle info null %d, %s, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), type, getEntityName() });
/*     */       
/*  77 */       func_70106_y();
/*     */     }
/*     */     else {
/*     */       
/*  81 */       setAcInfo((MCH_AircraftInfo)this.vehicleInfo);
/*  82 */       newSeats(getAcInfo().getNumSeatAndRack());
/*     */       
/*  84 */       this.weapons = createWeapon(1 + getSeatNum());
/*  85 */       initPartRotation(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMountWithNearEmptyMinecart() {
/*  91 */     return MCH_Config.MountMinecartVehicle.prmBool;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  96 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 102 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 108 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 110 */     if (this.vehicleInfo == null) {
/*     */       
/* 112 */       this.vehicleInfo = MCH_VehicleInfoManager.get(getTypeName());
/* 113 */       if (this.vehicleInfo == null) {
/*     */         
/* 115 */         MCH_Lib.Log((Entity)this, "##### MCH_EntityVehicle readEntityFromNBT() Vehicle info null %d, %s", new Object[] { Integer.valueOf(W_Entity.getEntityId((Entity)this)), getEntityName() });
/*     */         
/* 117 */         func_70106_y();
/*     */       }
/*     */       else {
/*     */         
/* 121 */         setAcInfo((MCH_AircraftInfo)this.vehicleInfo);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem() {
/* 128 */     return (getVehicleInfo() != null) ? (Item)(getVehicleInfo()).item : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 138 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSoundVolume() {
/* 147 */     return (float)getCurrentThrottle() * 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSoundPitch() {
/* 152 */     return (float)(getCurrentThrottle() * 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDefaultSoundName() {
/* 157 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void zoomCamera() {
/* 167 */     if (canZoom()) {
/*     */       
/* 169 */       float z = this.camera.getCameraZoom();
/* 170 */       z++;
/* 171 */       this.camera.setCameraZoom((z <= getZoomMax() + 0.01D) ? z : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void _updateCameraRotate(float yaw, float pitch) {
/* 177 */     this.camera.prevRotationYaw = this.camera.rotationYaw;
/* 178 */     this.camera.prevRotationPitch = this.camera.rotationPitch;
/* 179 */     if (pitch > 89.0F) pitch = 89.0F; 
/* 180 */     if (pitch < -89.0F) pitch = -89.0F; 
/* 181 */     this.camera.rotationYaw = yaw;
/* 182 */     this.camera.rotationPitch = pitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCameraView(Entity entity) {
/* 191 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useCurrentWeapon(MCH_WeaponParam prm) {
/* 197 */     if (prm.user != null) {
/*     */       
/* 199 */       MCH_WeaponSet currentWs = getCurrentWeapon(prm.user);
/* 200 */       if (currentWs != null) {
/*     */         
/* 202 */         MCH_AircraftInfo.Weapon w = getAcInfo().getWeaponByName((currentWs.getInfo()).name);
/* 203 */         if (w != null)
/*     */         {
/* 205 */           if (w.maxYaw != 0.0F && w.minYaw != 0.0F)
/*     */           {
/* 207 */             return super.useCurrentWeapon(prm);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 212 */     float breforeUseWeaponPitch = this.field_70125_A;
/* 213 */     float breforeUseWeaponYaw = this.field_70177_z;
/* 214 */     this.field_70125_A = prm.user.field_70125_A;
/* 215 */     this.field_70177_z = prm.user.field_70177_z;
/*     */     
/* 217 */     boolean result = super.useCurrentWeapon(prm);
/*     */     
/* 219 */     this.field_70125_A = breforeUseWeaponPitch;
/* 220 */     this.field_70177_z = breforeUseWeaponYaw;
/*     */     
/* 222 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateAircraft() {
/* 230 */     if (this.vehicleInfo == null) {
/*     */       
/* 232 */       changeType(getTypeName());
/* 233 */       this.field_70169_q = this.field_70165_t;
/* 234 */       this.field_70167_r = this.field_70163_u;
/* 235 */       this.field_70166_s = this.field_70161_v;
/*     */       
/*     */       return;
/*     */     } 
/* 239 */     if (!this.isRequestedSyncStatus) {
/*     */       
/* 241 */       this.isRequestedSyncStatus = true;
/* 242 */       if (this.field_70170_p.field_72995_K)
/*     */       {
/* 244 */         MCH_PacketStatusRequest.requestStatus(this);
/*     */       }
/*     */     } 
/*     */     
/* 248 */     if (this.lastRiddenByEntity == null && getRiddenByEntity() != null) {
/*     */       
/* 250 */       (getRiddenByEntity()).field_70125_A = 0.0F;
/* 251 */       (getRiddenByEntity()).field_70127_C = 0.0F;
/* 252 */       initCurrentWeapon(getRiddenByEntity());
/*     */     } 
/*     */     
/* 255 */     updateWeapons();
/* 256 */     onUpdate_Seats();
/* 257 */     onUpdate_Control();
/*     */     
/* 259 */     this.field_70169_q = this.field_70165_t;
/* 260 */     this.field_70167_r = this.field_70163_u;
/* 261 */     this.field_70166_s = this.field_70161_v;
/*     */ 
/*     */     
/* 264 */     if (func_70090_H())
/*     */     {
/* 266 */       this.field_70125_A *= 0.9F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 274 */       onUpdate_Client();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 279 */       onUpdate_Server();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdate_Control() {
/* 286 */     double max_y = 1.0D;
/* 287 */     if (this.field_70153_n != null && !this.field_70153_n.field_70128_L)
/*     */     
/* 289 */     { if ((getVehicleInfo()).isEnableMove || (getVehicleInfo()).isEnableRot)
/*     */       {
/* 291 */         onUpdate_ControlOnGround();
/*     */       
/*     */       }
/*     */        }
/*     */     
/* 296 */     else if (getCurrentThrottle() > 0.0D) { addCurrentThrottle(-0.00125D); }
/* 297 */     else { setCurrentThrottle(0.0D); }
/*     */     
/* 299 */     if (getCurrentThrottle() < 0.0D) setCurrentThrottle(0.0D);
/*     */ 
/*     */     
/* 302 */     if (this.field_70170_p.field_72995_K) {
/*     */ 
/*     */       
/* 305 */       if (!W_Lib.isClientPlayer(getRiddenByEntity()))
/*     */       {
/* 307 */         double ct = getThrottle();
/*     */         
/* 309 */         if (getCurrentThrottle() > ct) addCurrentThrottle(-0.005D); 
/* 310 */         if (getCurrentThrottle() < ct) addCurrentThrottle(0.005D);
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 316 */       setThrottle(getCurrentThrottle());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdate_ControlOnGround() {
/* 323 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 325 */       boolean move = false;
/* 326 */       float yaw = this.field_70177_z;
/* 327 */       double x = 0.0D;
/* 328 */       double z = 0.0D;
/*     */       
/* 330 */       if ((getVehicleInfo()).isEnableMove) {
/*     */         
/* 332 */         if (this.throttleUp) {
/*     */           
/* 334 */           yaw = this.field_70177_z;
/* 335 */           x += Math.sin(yaw * Math.PI / 180.0D);
/* 336 */           z += Math.cos(yaw * Math.PI / 180.0D);
/* 337 */           move = true;
/*     */         } 
/* 339 */         if (this.throttleDown) {
/*     */           
/* 341 */           yaw = this.field_70177_z - 180.0F;
/* 342 */           x += Math.sin(yaw * Math.PI / 180.0D);
/* 343 */           z += Math.cos(yaw * Math.PI / 180.0D);
/* 344 */           move = true;
/*     */         } 
/*     */       } 
/*     */       
/* 348 */       if ((getVehicleInfo()).isEnableMove) {
/*     */         
/* 350 */         if (this.moveLeft && !this.moveRight)
/*     */         {
/* 352 */           this.field_70177_z = (float)(this.field_70177_z - 0.5D);
/*     */         }
/* 354 */         if (this.moveRight && !this.moveLeft)
/*     */         {
/* 356 */           this.field_70177_z = (float)(this.field_70177_z + 0.5D);
/*     */         }
/*     */       } 
/*     */       
/* 360 */       if (move) {
/*     */         
/* 362 */         double d = Math.sqrt(x * x + z * z);
/* 363 */         this.field_70159_w -= x / d * 0.029999999329447746D;
/* 364 */         this.field_70179_y += z / d * 0.029999999329447746D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdate_Particle() {
/* 372 */     double particlePosY = this.field_70163_u;
/* 373 */     boolean b = false;
/*     */     int y;
/* 375 */     for (y = 0; y < 5 && !b; y++) {
/*     */       int x;
/* 377 */       for (x = -1; x <= 1; x++) {
/*     */         
/* 379 */         for (int z = -1; z <= 1; z++) {
/*     */           
/* 381 */           int block = W_WorldFunc.getBlockId(this.field_70170_p, (int)(this.field_70165_t + 0.5D) + x, (int)(this.field_70163_u + 0.5D) - y, (int)(this.field_70161_v + 0.5D) + z);
/*     */ 
/*     */ 
/*     */           
/* 385 */           if (block != 0 && !b) {
/*     */             
/* 387 */             particlePosY = ((int)(this.field_70163_u + 1.0D) - y);
/* 388 */             b = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 393 */       for (x = -3; b && x <= 3; x++) {
/*     */         
/* 395 */         for (int z = -3; z <= 3; z++) {
/*     */           
/* 397 */           if (W_WorldFunc.isBlockWater(this.field_70170_p, (int)(this.field_70165_t + 0.5D) + x, (int)(this.field_70163_u + 0.5D) - y, (int)(this.field_70161_v + 0.5D) + z))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 402 */             for (int i = 0; i < 7.0D * getCurrentThrottle(); i++)
/*     */             {
/* 404 */               this.field_70170_p.func_72869_a("splash", this.field_70165_t + 0.5D + x + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, particlePosY + this.field_70146_Z.nextDouble(), this.field_70161_v + 0.5D + z + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, x + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -0.3D, z + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 416 */     double pn = (5 - y + 1) / 5.0D;
/* 417 */     if (b)
/*     */     {
/* 419 */       for (int k = 0; k < (int)(getCurrentThrottle() * 6.0D * pn); k++) {
/*     */         
/* 421 */         float f3 = 0.25F;
/* 422 */         this.field_70170_p.func_72869_a("explode", this.field_70165_t + this.field_70146_Z.nextDouble() - 0.5D, particlePosY + this.field_70146_Z.nextDouble() - 0.5D, this.field_70161_v + this.field_70146_Z.nextDouble() - 0.5D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -0.4D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
/*     */       } 
/*     */     }
/*     */   }
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
/*     */   protected void onUpdate_Client() {
/* 437 */     updateCameraViewers();
/*     */     
/* 439 */     if (this.field_70153_n != null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 444 */       if (W_Lib.isClientPlayer(getRiddenByEntity()))
/*     */       {
/* 446 */         (getRiddenByEntity()).field_70125_A = (getRiddenByEntity()).field_70127_C;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 451 */     if (this.aircraftPosRotInc > 0) {
/*     */ 
/*     */       
/* 454 */       double rpinc = this.aircraftPosRotInc;
/* 455 */       double yaw = MathHelper.func_76138_g(this.aircraftYaw - this.field_70177_z);
/* 456 */       this.field_70177_z = (float)(this.field_70177_z + yaw / rpinc);
/* 457 */       this.field_70125_A = (float)(this.field_70125_A + (this.aircraftPitch - this.field_70125_A) / rpinc);
/* 458 */       func_70107_b(this.field_70165_t + (this.aircraftX - this.field_70165_t) / rpinc, this.field_70163_u + (this.aircraftY - this.field_70163_u) / rpinc, this.field_70161_v + (this.aircraftZ - this.field_70161_v) / rpinc);
/*     */ 
/*     */       
/* 461 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/* 462 */       this.aircraftPosRotInc--;
/*     */     }
/*     */     else {
/*     */       
/* 466 */       func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */ 
/*     */ 
/*     */       
/* 470 */       if (this.field_70122_E) {
/*     */         
/* 472 */         this.field_70159_w *= 0.95D;
/* 473 */         this.field_70179_y *= 0.95D;
/*     */       } 
/*     */       
/* 476 */       if (func_70090_H()) {
/*     */         
/* 478 */         this.field_70159_w *= 0.99D;
/* 479 */         this.field_70179_y *= 0.99D;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 484 */     if (this.field_70153_n != null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 490 */     updateCamera(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUpdate_Server() {
/* 496 */     double prevMotion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */     
/* 498 */     updateCameraViewers();
/*     */     
/* 500 */     double dp = 0.0D;
/*     */     
/* 502 */     if (canFloatWater())
/*     */     {
/* 504 */       dp = getWaterDepth();
/*     */     }
/*     */     
/* 507 */     if (dp == 0.0D) {
/*     */       
/* 509 */       this.field_70181_x += (!func_70090_H() ? (getAcInfo()).gravity : (getAcInfo()).gravityInWater);
/*     */ 
/*     */     
/*     */     }
/* 513 */     else if (dp < 1.0D) {
/*     */       
/* 515 */       this.field_70181_x -= 1.0E-4D;
/* 516 */       this.field_70181_x += 0.007D * getCurrentThrottle();
/*     */     }
/*     */     else {
/*     */       
/* 520 */       if (this.field_70181_x < 0.0D)
/*     */       {
/* 522 */         this.field_70181_x /= 2.0D;
/*     */       }
/* 524 */       this.field_70181_x += 0.007D;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 529 */     double motion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */ 
/*     */     
/* 532 */     float speedLimit = (getAcInfo()).speed;
/* 533 */     if (motion > speedLimit) {
/*     */       
/* 535 */       this.field_70159_w *= speedLimit / motion;
/* 536 */       this.field_70179_y *= speedLimit / motion;
/* 537 */       motion = speedLimit;
/*     */     } 
/*     */     
/* 540 */     if (motion > prevMotion && this.currentSpeed < speedLimit) {
/*     */       
/* 542 */       this.currentSpeed += (speedLimit - this.currentSpeed) / 35.0D;
/*     */       
/* 544 */       if (this.currentSpeed > speedLimit)
/*     */       {
/* 546 */         this.currentSpeed = speedLimit;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 551 */       this.currentSpeed -= (this.currentSpeed - 0.07D) / 35.0D;
/*     */       
/* 553 */       if (this.currentSpeed < 0.07D)
/*     */       {
/* 555 */         this.currentSpeed = 0.07D;
/*     */       }
/*     */     } 
/*     */     
/* 559 */     if (this.field_70122_E) {
/*     */       
/* 561 */       this.field_70159_w *= 0.5D;
/* 562 */       this.field_70179_y *= 0.5D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 568 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */ 
/*     */     
/* 571 */     this.field_70181_x *= 0.95D;
/* 572 */     this.field_70159_w *= 0.99D;
/* 573 */     this.field_70179_y *= 0.99D;
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
/* 608 */     onUpdate_updateBlock();
/*     */     
/* 610 */     if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
/*     */       
/* 612 */       unmountEntity();
/* 613 */       this.field_70153_n = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateAngles(float partialTicks) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void _updateRiderPosition() {
/* 630 */     float yaw = this.field_70177_z;
/* 631 */     if (this.field_70153_n != null)
/*     */     {
/* 633 */       this.field_70177_z = this.field_70153_n.field_70177_z;
/*     */     }
/* 635 */     func_70043_V();
/* 636 */     this.field_70177_z = yaw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSwitchFreeLook() {
/* 645 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_EntityVehicle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */