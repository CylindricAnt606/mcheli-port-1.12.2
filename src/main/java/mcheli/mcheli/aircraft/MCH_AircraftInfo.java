/*      */ package mcheli.mcheli.aircraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import mcheli.MCH_BaseInfo;
/*      */ import mcheli.MCH_MOD;
/*      */ import mcheli.aircraft.MCH_BoundingBox;
/*      */ import mcheli.aircraft.MCH_MobDropOption;
/*      */ import mcheli.aircraft.MCH_SeatInfo;
/*      */ import mcheli.aircraft.MCH_SeatRackInfo;
/*      */ import mcheli.hud.MCH_Hud;
/*      */ import mcheli.hud.MCH_HudManager;
/*      */ import mcheli.weapon.MCH_WeaponInfoManager;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraftforge.client.model.IModelCustom;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class MCH_AircraftInfo
/*      */   extends MCH_BaseInfo
/*      */ {
/*      */   public final String name;
/*      */   public String displayName;
/*      */   public HashMap<String, String> displayNameLang;
/*      */   public int itemID;
/*      */   public List<String> recipeString;
/*      */   public List<IRecipe> recipe;
/*      */   public boolean isShapedRecipe;
/*      */   public String category;
/*      */   public boolean isEnableGunnerMode;
/*      */   public int cameraZoom;
/*      */   public boolean isEnableConcurrentGunnerMode;
/*      */   public boolean isEnableNightVision;
/*      */   public boolean isEnableEntityRadar;
/*      */   public boolean isEnableEjectionSeat;
/*      */   public boolean isEnableParachuting;
/*      */   public Flare flare;
/*      */   public float bodyHeight;
/*      */   public float bodyWidth;
/*      */   public boolean isFloat;
/*      */   public float floatOffset;
/*      */   public float gravity;
/*      */   public float gravityInWater;
/*      */   public int maxHp;
/*      */   public float armorMinDamage;
/*      */   public float armorMaxDamage;
/*      */   public float armorDamageFactor;
/*      */   public boolean enableBack;
/*      */   public int inventorySize;
/*      */   public boolean isUAV;
/*      */   public boolean isSmallUAV;
/*      */   public boolean isTargetDrone;
/*      */   public float autoPilotRot;
/*      */   public float onGroundPitch;
/*      */   public boolean canMoveOnGround;
/*      */   public boolean canRotOnGround;
/*      */   public List<WeaponSet> weaponSetList;
/*      */   public List<MCH_SeatInfo> seatList;
/*      */   public List<Integer[]> exclusionSeatList;
/*      */   public List<MCH_Hud> hudList;
/*      */   public MCH_Hud hudTvMissile;
/*      */   public float damageFactor;
/*      */   public float submergedDamageHeight;
/*      */   public boolean regeneration;
/*      */   public List<MCH_BoundingBox> extraBoundingBox;
/*      */   public List<Wheel> wheels;
/*      */   public int maxFuel;
/*      */   public float fuelConsumption;
/*      */   public float fuelSupplyRange;
/*      */   public float ammoSupplyRange;
/*      */   public float repairOtherVehiclesRange;
/*      */   public int repairOtherVehiclesValue;
/*      */   public float stealth;
/*      */   public boolean canRide;
/*      */   public float entityWidth;
/*      */   public float entityHeight;
/*      */   public float entityPitch;
/*      */   public float entityRoll;
/*      */   public float stepHeight;
/*      */   public List<MCH_SeatRackInfo> entityRackList;
/*      */   public int mobSeatNum;
/*      */   public int entityRackNum;
/*      */   public MCH_MobDropOption mobDropOption;
/*      */   public List<RepellingHook> repellingHooks;
/*      */   public List<RideRack> rideRacks;
/*      */   public List<ParticleSplash> particleSplashs;
/*      */   public List<SearchLight> searchLights;
/*      */   public float rotorSpeed;
/*      */   public boolean enableSeaSurfaceParticle;
/*      */   public float pivotTurnThrottle;
/*      */   public float trackRollerRot;
/*      */   public float partWheelRot;
/*      */   public float onGroundPitchFactor;
/*      */   public float onGroundRollFactor;
/*      */   public Vec3 turretPosition;
/*      */   public boolean defaultFreelook;
/*      */   public Vec3 unmountPosition;
/*      */   public float markerWidth;
/*      */   public float markerHeight;
/*      */   public float bbZmin;
/*      */   public float bbZmax;
/*      */   public float bbZ;
/*      */   public boolean alwaysCameraView;
/*      */   public List<CameraPosition> cameraPosition;
/*      */   public float cameraRotationSpeed;
/*      */   public float speed;
/*      */   public float motionFactor;
/*      */   public float mobilityYaw;
/*      */   public float mobilityPitch;
/*      */   public float mobilityRoll;
/*      */   public float mobilityYawOnGround;
/*      */   public float minRotationPitch;
/*      */   public float maxRotationPitch;
/*      */   public float minRotationRoll;
/*      */   public float maxRotationRoll;
/*      */   public boolean limitRotation;
/*      */   public float throttleUpDown;
/*      */   public float throttleUpDownOnEntity;
/*      */   private List<String> textureNameList;
/*      */   public int textureCount;
/*      */   public float particlesScale;
/*      */   public boolean hideEntity;
/*      */   public boolean smoothShading;
/*      */   public String soundMove;
/*      */   public float soundRange;
/*      */   public float soundVolume;
/*      */   public float soundPitch;
/*      */   public IModelCustom model;
/*      */   public List<Hatch> hatchList;
/*      */   public List<Camera> cameraList;
/*      */   public List<PartWeapon> partWeapon;
/*      */   public List<WeaponBay> partWeaponBay;
/*      */   public List<Canopy> canopyList;
/*      */   public List<LandingGear> landingGear;
/*      */   public List<Throttle> partThrottle;
/*      */   public List<RotPart> partRotPart;
/*      */   public List<CrawlerTrack> partCrawlerTrack;
/*      */   public List<TrackRoller> partTrackRoller;
/*      */   public List<PartWheel> partWheel;
/*      */   public List<PartWheel> partSteeringWheel;
/*      */   public List<Hatch> lightHatchList;
/*  165 */   private String lastWeaponType = "";
/*  166 */   private int lastWeaponIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PartWeapon lastWeaponPart;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract Item getItem();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getItemStack() {
/*  553 */     return new ItemStack(getItem());
/*      */   }
/*      */   public abstract String getDirectoryName();
/*      */   
/*      */   public abstract String getKindName();
/*      */   
/*      */   public MCH_AircraftInfo(String s) {
/*  560 */     this.name = s;
/*  561 */     this.displayName = this.name;
/*  562 */     this.displayNameLang = new HashMap<String, String>();
/*  563 */     this.itemID = 0;
/*  564 */     this.recipeString = new ArrayList<String>();
/*  565 */     this.recipe = new ArrayList<IRecipe>();
/*  566 */     this.isShapedRecipe = true;
/*  567 */     this.category = "zzz";
/*      */ 
/*      */     
/*  570 */     this.isEnableGunnerMode = false;
/*  571 */     this.isEnableConcurrentGunnerMode = false;
/*  572 */     this.isEnableNightVision = false;
/*  573 */     this.isEnableEntityRadar = false;
/*  574 */     this.isEnableEjectionSeat = false;
/*  575 */     this.isEnableParachuting = false;
/*  576 */     this.flare = new Flare(this);
/*  577 */     this.weaponSetList = new ArrayList<WeaponSet>();
/*  578 */     this.seatList = new ArrayList<MCH_SeatInfo>();
/*  579 */     this.exclusionSeatList = (List)new ArrayList<Integer>();
/*  580 */     this.hudList = new ArrayList<MCH_Hud>();
/*  581 */     this.hudTvMissile = null;
/*  582 */     this.bodyHeight = 0.7F;
/*  583 */     this.bodyWidth = 2.0F;
/*  584 */     this.isFloat = false;
/*  585 */     this.floatOffset = 0.0F;
/*  586 */     this.gravity = -0.04F;
/*  587 */     this.gravityInWater = -0.04F;
/*  588 */     this.maxHp = 50;
/*  589 */     this.damageFactor = 0.2F;
/*  590 */     this.submergedDamageHeight = 0.0F;
/*  591 */     this.inventorySize = 0;
/*  592 */     this.armorDamageFactor = 1.0F;
/*  593 */     this.armorMaxDamage = 100000.0F;
/*  594 */     this.armorMinDamage = 0.0F;
/*  595 */     this.enableBack = false;
/*  596 */     this.isUAV = false;
/*  597 */     this.isSmallUAV = false;
/*  598 */     this.isTargetDrone = false;
/*  599 */     this.autoPilotRot = -0.6F;
/*  600 */     this.regeneration = false;
/*  601 */     this.onGroundPitch = 0.0F;
/*  602 */     this.canMoveOnGround = true;
/*  603 */     this.canRotOnGround = true;
/*  604 */     this.cameraZoom = getDefaultMaxZoom();
/*  605 */     this.extraBoundingBox = new ArrayList<MCH_BoundingBox>();
/*  606 */     this.maxFuel = 0;
/*  607 */     this.fuelConsumption = 1.0F;
/*  608 */     this.fuelSupplyRange = 0.0F;
/*  609 */     this.ammoSupplyRange = 0.0F;
/*  610 */     this.repairOtherVehiclesRange = 0.0F;
/*  611 */     this.repairOtherVehiclesValue = 10;
/*  612 */     this.stealth = 0.0F;
/*  613 */     this.canRide = true;
/*  614 */     this.entityWidth = 1.0F;
/*  615 */     this.entityHeight = 1.0F;
/*  616 */     this.entityPitch = 0.0F;
/*  617 */     this.entityRoll = 0.0F;
/*  618 */     this.stepHeight = getDefaultStepHeight();
/*  619 */     this.entityRackList = new ArrayList<MCH_SeatRackInfo>();
/*  620 */     this.mobSeatNum = 0;
/*  621 */     this.entityRackNum = 0;
/*  622 */     this.mobDropOption = new MCH_MobDropOption();
/*  623 */     this.repellingHooks = new ArrayList<RepellingHook>();
/*  624 */     this.rideRacks = new ArrayList<RideRack>();
/*  625 */     this.particleSplashs = new ArrayList<ParticleSplash>();
/*  626 */     this.searchLights = new ArrayList<SearchLight>();
/*  627 */     this.markerHeight = 1.0F;
/*  628 */     this.markerWidth = 2.0F;
/*  629 */     this.bbZmax = 1.0F;
/*  630 */     this.bbZmin = -1.0F;
/*  631 */     this.rotorSpeed = getDefaultRotorSpeed();
/*  632 */     this.wheels = getDefaultWheelList();
/*  633 */     this.onGroundPitchFactor = 0.0F;
/*  634 */     this.onGroundRollFactor = 0.0F;
/*  635 */     this.turretPosition = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/*  636 */     this.defaultFreelook = false;
/*  637 */     this.unmountPosition = null;
/*      */ 
/*      */     
/*  640 */     this.cameraPosition = new ArrayList<CameraPosition>();
/*  641 */     this.alwaysCameraView = false;
/*  642 */     this.cameraRotationSpeed = 1000.0F;
/*      */ 
/*      */     
/*  645 */     this.speed = 0.1F;
/*  646 */     this.motionFactor = 0.96F;
/*  647 */     this.mobilityYaw = 1.0F;
/*  648 */     this.mobilityPitch = 1.0F;
/*  649 */     this.mobilityRoll = 1.0F;
/*  650 */     this.mobilityYawOnGround = 1.0F;
/*  651 */     this.minRotationPitch = getMinRotationPitch();
/*  652 */     this.maxRotationPitch = getMaxRotationPitch();
/*  653 */     this.minRotationRoll = getMinRotationPitch();
/*  654 */     this.maxRotationRoll = getMaxRotationPitch();
/*  655 */     this.limitRotation = false;
/*  656 */     this.throttleUpDown = 1.0F;
/*  657 */     this.throttleUpDownOnEntity = 2.0F;
/*  658 */     this.pivotTurnThrottle = 0.0F;
/*  659 */     this.trackRollerRot = 30.0F;
/*  660 */     this.partWheelRot = 30.0F;
/*      */ 
/*      */     
/*  663 */     this.textureNameList = new ArrayList<String>();
/*  664 */     this.textureNameList.add(this.name);
/*  665 */     this.textureCount = 0;
/*  666 */     this.particlesScale = 1.0F;
/*  667 */     this.enableSeaSurfaceParticle = false;
/*  668 */     this.hideEntity = false;
/*  669 */     this.smoothShading = true;
/*      */ 
/*      */     
/*  672 */     this.soundMove = "";
/*  673 */     this.soundPitch = 1.0F;
/*  674 */     this.soundVolume = 1.0F;
/*  675 */     this.soundRange = getDefaultSoundRange();
/*      */ 
/*      */     
/*  678 */     this.model = null;
/*  679 */     this.hatchList = new ArrayList<Hatch>();
/*  680 */     this.cameraList = new ArrayList<Camera>();
/*  681 */     this.partWeapon = new ArrayList<PartWeapon>();
/*  682 */     this.lastWeaponPart = null;
/*  683 */     this.partWeaponBay = new ArrayList<WeaponBay>();
/*  684 */     this.canopyList = new ArrayList<Canopy>();
/*  685 */     this.landingGear = new ArrayList<LandingGear>();
/*  686 */     this.partThrottle = new ArrayList<Throttle>();
/*  687 */     this.partRotPart = new ArrayList<RotPart>();
/*  688 */     this.partCrawlerTrack = new ArrayList<CrawlerTrack>();
/*  689 */     this.partTrackRoller = new ArrayList<TrackRoller>();
/*  690 */     this.partWheel = new ArrayList<PartWheel>();
/*  691 */     this.partSteeringWheel = new ArrayList<PartWheel>();
/*  692 */     this.lightHatchList = new ArrayList<Hatch>();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getDefaultSoundRange() {
/*  697 */     return 100.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Wheel> getDefaultWheelList() {
/*  702 */     return new ArrayList<Wheel>();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getDefaultRotorSpeed() {
/*  707 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   private float getDefaultStepHeight() {
/*  712 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean haveRepellingHook() {
/*  717 */     return (this.repellingHooks.size() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean haveFlare() {
/*  722 */     return (this.flare.types.length > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean haveCanopy() {
/*  727 */     return (this.canopyList.size() > 0);
/*      */   }
/*      */   
/*      */   public boolean haveLandingGear() {
/*  731 */     return (this.landingGear.size() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String getDefaultHudName(int paramInt);
/*      */ 
/*      */   
/*      */   public boolean isValidData() throws Exception {
/*  740 */     if (this.cameraPosition.size() <= 0)
/*      */     {
/*  742 */       this.cameraPosition.add(new CameraPosition(this));
/*      */     }
/*      */     
/*  745 */     this.bbZ = (this.bbZmax + this.bbZmin) / 2.0F;
/*      */ 
/*      */     
/*  748 */     if (this.isTargetDrone)
/*      */     {
/*  750 */       this.isUAV = true;
/*      */     }
/*      */ 
/*      */     
/*  754 */     if (this.isEnableParachuting && this.repellingHooks.size() > 0) {
/*      */       
/*  756 */       this.isEnableParachuting = false;
/*  757 */       this.repellingHooks.clear();
/*      */     } 
/*      */ 
/*      */     
/*  761 */     if (this.isUAV) {
/*      */       
/*  763 */       this.alwaysCameraView = true;
/*  764 */       if (this.seatList.size() == 0) {
/*      */         
/*  766 */         MCH_SeatInfo s = new MCH_SeatInfo(Vec3.func_72443_a(0.0D, 0.0D, 0.0D), false);
/*  767 */         this.seatList.add(s);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  773 */     this.mobSeatNum = this.seatList.size();
/*  774 */     this.entityRackNum = this.entityRackList.size();
/*      */ 
/*      */     
/*  777 */     if (getNumSeat() < 1) throw new Exception();
/*      */     
/*  779 */     if (getNumHud() < getNumSeat())
/*      */     {
/*  781 */       for (int j = getNumHud(); j < getNumSeat(); j++)
/*      */       {
/*  783 */         this.hudList.add(MCH_HudManager.get(getDefaultHudName(j)));
/*      */       }
/*      */     }
/*      */     
/*  787 */     if (getNumSeat() == 1 && getNumHud() == 1)
/*      */     {
/*  789 */       this.hudList.add(MCH_HudManager.get(getDefaultHudName(1)));
/*      */     }
/*      */ 
/*      */     
/*  793 */     for (MCH_SeatRackInfo ei : this.entityRackList)
/*      */     {
/*  795 */       this.seatList.add(ei);
/*      */     }
/*  797 */     this.entityRackList.clear();
/*      */     
/*  799 */     if (this.hudTvMissile == null)
/*      */     {
/*  801 */       this.hudTvMissile = MCH_HudManager.get("tv_missile");
/*      */     }
/*      */     
/*  804 */     if (this.textureNameList.size() < 1) throw new Exception(); 
/*  805 */     if (this.itemID <= 0);
/*      */     
/*  807 */     for (int i = 0; i < this.partWeaponBay.size(); i++) {
/*      */       
/*  809 */       WeaponBay wb = this.partWeaponBay.get(i);
/*  810 */       String[] weaponNames = WeaponBay.access$000(wb).split("\\s*/\\s*");
/*  811 */       if (weaponNames.length <= 0) {
/*      */         
/*  813 */         this.partWeaponBay.remove(i);
/*      */       } else {
/*      */         
/*  816 */         List<Integer> list = new ArrayList<Integer>();
/*  817 */         for (String s : weaponNames) {
/*      */           
/*  819 */           int id = getWeaponIdByName(s);
/*  820 */           if (id >= 0)
/*      */           {
/*  822 */             list.add(Integer.valueOf(id));
/*      */           }
/*      */         } 
/*  825 */         if (list.size() <= 0) {
/*      */           
/*  827 */           this.partWeaponBay.remove(i);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  832 */           ((WeaponBay)this.partWeaponBay.get(i)).weaponIds = list.<Integer>toArray(new Integer[0]);
/*      */         } 
/*      */       } 
/*      */     } 
/*  836 */     return true;
/*      */   }
/*      */   public int getInfo_MaxSeatNum() {
/*  839 */     return 30;
/*      */   }
/*  841 */   public int getNumSeatAndRack() { return this.seatList.size(); }
/*  842 */   public int getNumSeat() { return this.mobSeatNum; } public int getNumRack() {
/*  843 */     return this.entityRackNum;
/*      */   } public int getNumHud() {
/*  845 */     return this.hudList.size();
/*      */   } public float getMaxSpeed() {
/*  847 */     return 0.8F;
/*      */   }
/*  849 */   public float getMinRotationPitch() { return -89.9F; }
/*  850 */   public float getMaxRotationPitch() { return 80.0F; }
/*  851 */   public float getMinRotationRoll() { return -80.0F; } public float getMaxRotationRoll() {
/*  852 */     return 80.0F;
/*      */   } public int getDefaultMaxZoom() {
/*  854 */     return 1;
/*      */   }
/*      */   
/*      */   public boolean haveHatch() {
/*  858 */     return (this.hatchList.size() > 0);
/*      */   }
/*      */   
/*      */   public boolean havePartCamera() {
/*  862 */     return (this.cameraList.size() > 0);
/*      */   }
/*      */   
/*      */   public boolean havePartThrottle() {
/*  866 */     return (this.partThrottle.size() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public WeaponSet getWeaponSetById(int id) {
/*  871 */     return (id >= 0 && id < this.weaponSetList.size()) ? this.weaponSetList.get(id) : null;
/*      */   }
/*      */   
/*      */   public Weapon getWeaponById(int id) {
/*  875 */     WeaponSet ws = getWeaponSetById(id);
/*  876 */     return (ws != null) ? ws.weapons.get(0) : null;
/*      */   }
/*      */   
/*      */   public int getWeaponIdByName(String s) {
/*  880 */     for (int i = 0; i < this.weaponSetList.size(); i++) {
/*      */       
/*  882 */       if (((WeaponSet)this.weaponSetList.get(i)).type.equalsIgnoreCase(s))
/*      */       {
/*  884 */         return i;
/*      */       }
/*      */     } 
/*  887 */     return -1;
/*      */   }
/*      */   
/*      */   public Weapon getWeaponByName(String s) {
/*  891 */     for (int i = 0; i < this.weaponSetList.size(); i++) {
/*      */       
/*  893 */       if (((WeaponSet)this.weaponSetList.get(i)).type.equalsIgnoreCase(s))
/*      */       {
/*  895 */         return getWeaponById(i);
/*      */       }
/*      */     } 
/*  898 */     return null;
/*      */   }
/*      */   
/*      */   public int getWeaponNum() {
/*  902 */     return this.weaponSetList.size();
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadItemData(String item, String data) {
/*  907 */     if (item.compareTo("displayname") == 0) {
/*      */       
/*  909 */       this.displayName = data.trim();
/*      */     }
/*  911 */     else if (item.compareTo("adddisplayname") == 0) {
/*      */       
/*  913 */       String[] s = data.split("\\s*,\\s*");
/*  914 */       if (s != null && s.length == 2)
/*      */       {
/*  916 */         this.displayNameLang.put(s[0].trim(), s[1].trim());
/*      */       }
/*      */     }
/*  919 */     else if (item.equalsIgnoreCase("Category")) {
/*      */       
/*  921 */       this.category = data.toUpperCase().replaceAll("[,;:]", ".").replaceAll("[ \t]", "");
/*      */     }
/*  923 */     else if (item.equalsIgnoreCase("CanRide")) {
/*      */       
/*  925 */       this.canRide = toBool(data, true);
/*      */     }
/*  927 */     else if (item.equalsIgnoreCase("MaxFuel")) {
/*      */       
/*  929 */       this.maxFuel = toInt(data, 0, 100000000);
/*      */     }
/*  931 */     else if (item.equalsIgnoreCase("FuelConsumption")) {
/*      */       
/*  933 */       this.fuelConsumption = toFloat(data, 0.0F, 10000.0F);
/*      */     }
/*  935 */     else if (item.equalsIgnoreCase("FuelSupplyRange")) {
/*      */       
/*  937 */       this.fuelSupplyRange = toFloat(data, 0.0F, 1000.0F);
/*      */     }
/*  939 */     else if (item.equalsIgnoreCase("AmmoSupplyRange")) {
/*      */       
/*  941 */       this.ammoSupplyRange = toFloat(data, 0.0F, 1000.0F);
/*      */     }
/*  943 */     else if (item.equalsIgnoreCase("RepairOtherVehicles")) {
/*      */       
/*  945 */       String[] s = splitParam(data);
/*  946 */       if (s.length >= 1) {
/*      */         
/*  948 */         this.repairOtherVehiclesRange = toFloat(s[0], 0.0F, 1000.0F);
/*  949 */         if (s.length >= 2) this.repairOtherVehiclesValue = toInt(s[1], 0, 10000000);
/*      */       
/*      */       } 
/*  952 */     } else if (item.compareTo("itemid") == 0) {
/*      */       
/*  954 */       this.itemID = toInt(data, 0, 65535);
/*      */     }
/*  956 */     else if (item.compareTo("addtexture") == 0) {
/*      */       
/*  958 */       this.textureNameList.add(data.toLowerCase());
/*      */     }
/*  960 */     else if (item.compareTo("particlesscale") == 0) {
/*      */       
/*  962 */       this.particlesScale = toFloat(data, 0.0F, 50.0F);
/*      */     }
/*  964 */     else if (item.equalsIgnoreCase("EnableSeaSurfaceParticle")) {
/*      */       
/*  966 */       this.enableSeaSurfaceParticle = toBool(data);
/*      */     }
/*  968 */     else if (item.equalsIgnoreCase("AddParticleSplash")) {
/*      */       
/*  970 */       String[] s = splitParam(data);
/*  971 */       if (s.length >= 3)
/*      */       {
/*  973 */         Vec3 v = toVec3(s[0], s[1], s[2]);
/*  974 */         int num = (s.length >= 4) ? toInt(s[3], 1, 100) : 2;
/*  975 */         float size = (s.length >= 5) ? toFloat(s[4]) : 2.0F;
/*  976 */         float acc = (s.length >= 6) ? toFloat(s[5]) : 1.0F;
/*  977 */         int age = (s.length >= 7) ? toInt(s[6], 1, 100000) : 80;
/*  978 */         float motionY = (s.length >= 8) ? toFloat(s[7]) : 0.01F;
/*  979 */         float gravity = (s.length >= 9) ? toFloat(s[8]) : 0.0F;
/*  980 */         this.particleSplashs.add(new ParticleSplash(this, v, num, size, acc, age, motionY, gravity));
/*      */       }
/*      */     
/*  983 */     } else if (item.equalsIgnoreCase("AddSearchLight") || item.equalsIgnoreCase("AddFixedSearchLight") || item.equalsIgnoreCase("AddSteeringSearchLight")) {
/*      */ 
/*      */ 
/*      */       
/*  987 */       String[] s = splitParam(data);
/*  988 */       if (s.length >= 7)
/*      */       {
/*  990 */         Vec3 v = toVec3(s[0], s[1], s[2]);
/*  991 */         int cs = hex2dec(s[3]);
/*  992 */         int ce = hex2dec(s[4]);
/*  993 */         float h = toFloat(s[5]);
/*  994 */         float w = toFloat(s[6]);
/*  995 */         float yaw = (s.length >= 8) ? toFloat(s[7]) : 0.0F;
/*  996 */         float pitch = (s.length >= 9) ? toFloat(s[8]) : 0.0F;
/*  997 */         float stRot = (s.length >= 10) ? toFloat(s[9]) : 0.0F;
/*  998 */         boolean fixDir = !item.equalsIgnoreCase("AddSearchLight");
/*  999 */         boolean steering = item.equalsIgnoreCase("AddSteeringSearchLight");
/* 1000 */         this.searchLights.add(new SearchLight(this, v, cs, ce, h, w, fixDir, yaw, pitch, steering, stRot));
/*      */       }
/*      */     
/* 1003 */     } else if (item.equalsIgnoreCase("AddPartLightHatch")) {
/*      */       
/* 1005 */       String[] s = splitParam(data);
/* 1006 */       if (s.length >= 6)
/*      */       {
/* 1008 */         float mx = (s.length >= 7) ? toFloat(s[6], -1800.0F, 1800.0F) : 90.0F;
/* 1009 */         this.lightHatchList.add(new Hatch(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), mx, "light_hatch" + this.lightHatchList.size(), false));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1016 */     else if (item.equalsIgnoreCase("AddRepellingHook")) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1021 */       String[] s = splitParam(data);
/* 1022 */       if (s != null && s.length >= 3)
/*      */       {
/* 1024 */         int inv = (s.length >= 4) ? toInt(s[3], 1, 100000) : 10;
/* 1025 */         this.repellingHooks.add(new RepellingHook(this, toVec3(s[0], s[1], s[2]), inv));
/*      */       }
/*      */     
/* 1028 */     } else if (item.equalsIgnoreCase("AddRack")) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1033 */       String[] s = data.toLowerCase().split("\\s*,\\s*");
/* 1034 */       if (s != null && s.length >= 7)
/*      */       {
/* 1036 */         String[] names = s[0].split("\\s*/\\s*");
/* 1037 */         float range = (s.length >= 8) ? toFloat(s[7]) : 6.0F;
/* 1038 */         float para = (s.length >= 9) ? toFloat(s[8], 0.0F, 1000000.0F) : 20.0F;
/* 1039 */         float yaw = (s.length >= 10) ? toFloat(s[9]) : 0.0F;
/* 1040 */         float pitch = (s.length >= 11) ? toFloat(s[10]) : 0.0F;
/* 1041 */         boolean rs = (s.length >= 12) ? toBool(s[11]) : false;
/* 1042 */         this.entityRackList.add(new MCH_SeatRackInfo(names, toDouble(s[1]), toDouble(s[2]), toDouble(s[3]), new CameraPosition(this, toVec3(s[4], s[5], s[6]).func_72441_c(0.0D, 1.5D, 0.0D)), range, para, yaw, pitch, rs));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1050 */     else if (item.equalsIgnoreCase("RideRack")) {
/*      */       
/* 1052 */       String[] s = splitParam(data);
/* 1053 */       if (s.length >= 2)
/*      */       {
/* 1055 */         RideRack r = new RideRack(this, s[0].trim().toLowerCase(), toInt(s[1], 1, 10000));
/* 1056 */         this.rideRacks.add(r);
/*      */       }
/*      */     
/* 1059 */     } else if (item.equalsIgnoreCase("AddSeat") || item.equalsIgnoreCase("AddGunnerSeat") || item.equalsIgnoreCase("AddFixRotSeat")) {
/*      */ 
/*      */ 
/*      */       
/* 1063 */       if (this.seatList.size() >= getInfo_MaxSeatNum())
/*      */         return; 
/* 1065 */       String[] s = splitParam(data);
/* 1066 */       if (s.length < 3)
/*      */         return; 
/* 1068 */       Vec3 p = toVec3(s[0], s[1], s[2]);
/* 1069 */       if (item.equalsIgnoreCase("AddSeat")) {
/*      */         
/* 1071 */         boolean rs = (s.length >= 4) ? toBool(s[3]) : false;
/* 1072 */         MCH_SeatInfo seat = new MCH_SeatInfo(p, rs);
/* 1073 */         this.seatList.add(seat);
/*      */       } else {
/*      */         MCH_SeatInfo seat;
/*      */         
/* 1077 */         if (s.length >= 6) {
/*      */           
/* 1079 */           CameraPosition c = new CameraPosition(this, toVec3(s[3], s[4], s[5]));
/*      */           
/* 1081 */           boolean sg = (s.length >= 7) ? toBool(s[6]) : false;
/* 1082 */           if (item.equalsIgnoreCase("AddGunnerSeat")) {
/*      */             
/* 1084 */             if (s.length >= 9)
/*      */             {
/* 1086 */               float minPitch = toFloat(s[7], -90.0F, 90.0F);
/* 1087 */               float maxPitch = toFloat(s[8], -90.0F, 90.0F);
/* 1088 */               if (minPitch > maxPitch) {
/*      */                 
/* 1090 */                 float t = minPitch;
/* 1091 */                 minPitch = maxPitch;
/* 1092 */                 maxPitch = t;
/*      */               } 
/* 1094 */               boolean rs = (s.length >= 10) ? toBool(s[9]) : false;
/* 1095 */               seat = new MCH_SeatInfo(p, true, c, true, sg, false, 0.0F, 0.0F, minPitch, maxPitch, rs);
/*      */             }
/*      */             else
/*      */             {
/* 1099 */               seat = new MCH_SeatInfo(p, true, c, true, sg, false, 0.0F, 0.0F, false);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1104 */             boolean fixRot = (s.length >= 9);
/* 1105 */             float fixYaw = fixRot ? toFloat(s[7]) : 0.0F;
/* 1106 */             float fixPitch = fixRot ? toFloat(s[8]) : 0.0F;
/* 1107 */             boolean rs = (s.length >= 10) ? toBool(s[9]) : false;
/* 1108 */             seat = new MCH_SeatInfo(p, true, c, true, sg, fixRot, fixYaw, fixPitch, rs);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1113 */           seat = new MCH_SeatInfo(p, true, new CameraPosition(this), false, false, false, 0.0F, 0.0F, false);
/*      */         } 
/* 1115 */         this.seatList.add(seat);
/*      */       }
/*      */     
/* 1118 */     } else if (item.equalsIgnoreCase("SetWheelPos")) {
/*      */       
/* 1120 */       String[] s = splitParam(data);
/* 1121 */       if (s.length >= 4)
/*      */       {
/* 1123 */         float x = Math.abs(toFloat(s[0]));
/* 1124 */         float y = toFloat(s[1]);
/* 1125 */         this.wheels.clear();
/* 1126 */         for (int i = 2; i < s.length; i++)
/*      */         {
/* 1128 */           this.wheels.add(new Wheel(this, Vec3.func_72443_a(x, y, toFloat(s[i]))));
/*      */         }
/* 1130 */         Collections.sort(this.wheels, (Comparator<? super Wheel>)new Object(this));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1137 */     else if (item.equalsIgnoreCase("ExclusionSeat")) {
/*      */       
/* 1139 */       String[] s = splitParam(data);
/* 1140 */       if (s.length >= 2)
/*      */       {
/* 1142 */         Integer[] a = new Integer[s.length];
/* 1143 */         for (int i = 0; i < a.length; i++)
/*      */         {
/* 1145 */           a[i] = Integer.valueOf(toInt(s[i], 1, 10000) - 1);
/*      */         }
/* 1147 */         this.exclusionSeatList.add(a);
/*      */       }
/*      */     
/* 1150 */     } else if (MCH_MOD.proxy.isRemote() && item.equalsIgnoreCase("HUD")) {
/*      */       
/* 1152 */       this.hudList.clear();
/* 1153 */       String[] ss = data.split("\\s*,\\s*");
/* 1154 */       for (String s : ss)
/*      */       {
/* 1156 */         MCH_Hud hud = MCH_HudManager.get(s);
/* 1157 */         if (hud == null)
/*      */         {
/* 1159 */           hud = MCH_Hud.NoDisp;
/*      */         }
/* 1161 */         this.hudList.add(hud);
/*      */       }
/*      */     
/* 1164 */     } else if (item.compareTo("enablenightvision") == 0) {
/*      */       
/* 1166 */       this.isEnableNightVision = toBool(data);
/*      */     }
/* 1168 */     else if (item.compareTo("enableentityradar") == 0) {
/*      */       
/* 1170 */       this.isEnableEntityRadar = toBool(data);
/*      */     }
/* 1172 */     else if (item.equalsIgnoreCase("EnableEjectionSeat")) {
/*      */       
/* 1174 */       this.isEnableEjectionSeat = toBool(data);
/*      */     }
/* 1176 */     else if (item.equalsIgnoreCase("EnableParachuting")) {
/*      */       
/* 1178 */       this.isEnableParachuting = toBool(data);
/*      */     }
/* 1180 */     else if (item.equalsIgnoreCase("MobDropOption")) {
/*      */       
/* 1182 */       String[] s = splitParam(data);
/* 1183 */       if (s.length >= 3)
/*      */       {
/* 1185 */         this.mobDropOption.pos = toVec3(s[0], s[1], s[2]);
/* 1186 */         this.mobDropOption.interval = (s.length >= 4) ? toInt(s[3]) : 12;
/*      */       }
/*      */     
/* 1189 */     } else if (item.equalsIgnoreCase("Width")) {
/*      */       
/* 1191 */       this.bodyWidth = toFloat(data, 0.1F, 1000.0F);
/*      */     }
/* 1193 */     else if (item.equalsIgnoreCase("Height")) {
/*      */       
/* 1195 */       this.bodyHeight = toFloat(data, 0.1F, 1000.0F);
/*      */     }
/* 1197 */     else if (item.compareTo("float") == 0) {
/*      */       
/* 1199 */       this.isFloat = toBool(data);
/*      */     }
/* 1201 */     else if (item.compareTo("floatoffset") == 0) {
/*      */ 
/*      */       
/* 1204 */       this.floatOffset = -toFloat(data);
/*      */     }
/* 1206 */     else if (item.compareTo("gravity") == 0) {
/*      */       
/* 1208 */       this.gravity = toFloat(data, -50.0F, 50.0F);
/*      */     }
/* 1210 */     else if (item.compareTo("gravityinwater") == 0) {
/*      */       
/* 1212 */       this.gravityInWater = toFloat(data, -50.0F, 50.0F);
/*      */     }
/* 1214 */     else if (item.compareTo("cameraposition") == 0) {
/*      */       
/* 1216 */       String[] s = data.split("\\s*,\\s*");
/* 1217 */       if (s.length >= 3)
/*      */       {
/* 1219 */         this.alwaysCameraView = (s.length >= 4) ? toBool(s[3]) : false;
/* 1220 */         boolean fixRot = (s.length >= 5);
/* 1221 */         float yaw = (s.length >= 5) ? toFloat(s[4]) : 0.0F;
/* 1222 */         float pitch = (s.length >= 6) ? toFloat(s[5]) : 0.0F;
/* 1223 */         this.cameraPosition.add(new CameraPosition(this, toVec3(s[0], s[1], s[2]), fixRot, yaw, pitch));
/*      */       }
/*      */     
/* 1226 */     } else if (item.equalsIgnoreCase("UnmountPosition")) {
/*      */       
/* 1228 */       String[] s = data.split("\\s*,\\s*");
/* 1229 */       if (s.length >= 3)
/*      */       {
/* 1231 */         this.unmountPosition = toVec3(s[0], s[1], s[2]);
/*      */       }
/*      */     }
/* 1234 */     else if (item.equalsIgnoreCase("TurretPosition")) {
/*      */       
/* 1236 */       String[] s = data.split("\\s*,\\s*");
/* 1237 */       if (s.length >= 3)
/*      */       {
/* 1239 */         this.turretPosition = toVec3(s[0], s[1], s[2]);
/*      */       }
/*      */     }
/* 1242 */     else if (item.equalsIgnoreCase("CameraRotationSpeed")) {
/*      */       
/* 1244 */       this.cameraRotationSpeed = toFloat(data, 0.0F, 10000.0F);
/*      */     }
/* 1246 */     else if (item.compareTo("regeneration") == 0) {
/*      */       
/* 1248 */       this.regeneration = toBool(data);
/*      */     }
/* 1250 */     else if (item.compareTo("speed") == 0) {
/*      */       
/* 1252 */       this.speed = toFloat(data, 0.0F, getMaxSpeed());
/*      */     }
/* 1254 */     else if (item.equalsIgnoreCase("EnableBack")) {
/*      */       
/* 1256 */       this.enableBack = toBool(data);
/*      */     }
/* 1258 */     else if (item.equalsIgnoreCase("MotionFactor")) {
/*      */       
/* 1260 */       this.motionFactor = toFloat(data, 0.0F, 1.0F);
/*      */     }
/* 1262 */     else if (item.equalsIgnoreCase("MobilityYawOnGround")) {
/*      */       
/* 1264 */       this.mobilityYawOnGround = toFloat(data, 0.0F, 100.0F);
/*      */     }
/* 1266 */     else if (item.equalsIgnoreCase("MobilityYaw")) {
/*      */       
/* 1268 */       this.mobilityYaw = toFloat(data, 0.0F, 100.0F);
/*      */     }
/* 1270 */     else if (item.equalsIgnoreCase("MobilityPitch")) {
/*      */       
/* 1272 */       this.mobilityPitch = toFloat(data, 0.0F, 100.0F);
/*      */     }
/* 1274 */     else if (item.equalsIgnoreCase("MobilityRoll")) {
/*      */       
/* 1276 */       this.mobilityRoll = toFloat(data, 0.0F, 100.0F);
/*      */     }
/* 1278 */     else if (item.equalsIgnoreCase("MinRotationPitch")) {
/*      */       
/* 1280 */       this.limitRotation = true;
/* 1281 */       this.minRotationPitch = toFloat(data, getMinRotationPitch(), 0.0F);
/*      */     }
/* 1283 */     else if (item.equalsIgnoreCase("MaxRotationPitch")) {
/*      */       
/* 1285 */       this.limitRotation = true;
/* 1286 */       this.maxRotationPitch = toFloat(data, 0.0F, getMaxRotationPitch());
/*      */     }
/* 1288 */     else if (item.equalsIgnoreCase("MinRotationRoll")) {
/*      */       
/* 1290 */       this.limitRotation = true;
/* 1291 */       this.minRotationRoll = toFloat(data, getMinRotationRoll(), 0.0F);
/*      */     }
/* 1293 */     else if (item.equalsIgnoreCase("MaxRotationRoll")) {
/*      */       
/* 1295 */       this.limitRotation = true;
/* 1296 */       this.maxRotationRoll = toFloat(data, 0.0F, getMaxRotationRoll());
/*      */     }
/* 1298 */     else if (item.compareTo("throttleupdown") == 0) {
/*      */       
/* 1300 */       this.throttleUpDown = toFloat(data, 0.0F, 3.0F);
/*      */     }
/* 1302 */     else if (item.equalsIgnoreCase("ThrottleUpDownOnEntity")) {
/*      */       
/* 1304 */       this.throttleUpDownOnEntity = toFloat(data, 0.0F, 100000.0F);
/*      */     }
/* 1306 */     else if (item.equalsIgnoreCase("Stealth")) {
/*      */       
/* 1308 */       this.stealth = toFloat(data, 0.0F, 1.0F);
/*      */     }
/* 1310 */     else if (item.equalsIgnoreCase("EntityWidth")) {
/*      */       
/* 1312 */       this.entityWidth = toFloat(data, -100.0F, 100.0F);
/*      */     }
/* 1314 */     else if (item.equalsIgnoreCase("EntityHeight")) {
/*      */       
/* 1316 */       this.entityHeight = toFloat(data, -100.0F, 100.0F);
/*      */     }
/* 1318 */     else if (item.equalsIgnoreCase("EntityPitch")) {
/*      */       
/* 1320 */       this.entityPitch = toFloat(data, -360.0F, 360.0F);
/*      */     }
/* 1322 */     else if (item.equalsIgnoreCase("EntityRoll")) {
/*      */       
/* 1324 */       this.entityRoll = toFloat(data, -360.0F, 360.0F);
/*      */     }
/* 1326 */     else if (item.equalsIgnoreCase("StepHeight")) {
/*      */       
/* 1328 */       this.stepHeight = toFloat(data, 0.0F, 1000.0F);
/*      */     }
/* 1330 */     else if (item.equalsIgnoreCase("CanMoveOnGround")) {
/*      */       
/* 1332 */       this.canMoveOnGround = toBool(data);
/*      */     }
/* 1334 */     else if (item.equalsIgnoreCase("CanRotOnGround")) {
/*      */       
/* 1336 */       this.canRotOnGround = toBool(data);
/*      */     }
/* 1338 */     else if (item.equalsIgnoreCase("AddWeapon") || item.equalsIgnoreCase("AddTurretWeapon")) {
/*      */       
/* 1340 */       String[] s = data.split("\\s*,\\s*");
/* 1341 */       String type = s[0].toLowerCase();
/* 1342 */       if (s.length >= 4 && MCH_WeaponInfoManager.contains(type))
/*      */       {
/* 1344 */         float y = (s.length >= 5) ? toFloat(s[4]) : 0.0F;
/* 1345 */         float p = (s.length >= 6) ? toFloat(s[5]) : 0.0F;
/* 1346 */         boolean canUsePilot = (s.length >= 7) ? toBool(s[6]) : true;
/* 1347 */         int seatID = (s.length >= 8) ? (toInt(s[7], 1, getInfo_MaxSeatNum()) - 1) : 0;
/* 1348 */         if (seatID <= 0) canUsePilot = true; 
/* 1349 */         float dfy = (s.length >= 9) ? toFloat(s[8]) : 0.0F;
/* 1350 */         dfy = MathHelper.func_76142_g(dfy);
/* 1351 */         float mny = (s.length >= 10) ? toFloat(s[9]) : 0.0F;
/* 1352 */         float mxy = (s.length >= 11) ? toFloat(s[10]) : 0.0F;
/* 1353 */         float mnp = (s.length >= 12) ? toFloat(s[11]) : 0.0F;
/* 1354 */         float mxp = (s.length >= 13) ? toFloat(s[12]) : 0.0F;
/* 1355 */         Weapon e = new Weapon(this, toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), y, p, canUsePilot, seatID, dfy, mny, mxy, mnp, mxp, item.equalsIgnoreCase("AddTurretWeapon"));
/*      */ 
/*      */ 
/*      */         
/* 1359 */         if (type.compareTo(this.lastWeaponType) != 0) {
/*      */           
/* 1361 */           this.weaponSetList.add(new WeaponSet(this, type));
/* 1362 */           this.lastWeaponIndex++;
/* 1363 */           this.lastWeaponType = type;
/*      */         } 
/* 1365 */         ((WeaponSet)this.weaponSetList.get(this.lastWeaponIndex)).weapons.add(e);
/*      */       }
/*      */     
/* 1368 */     } else if (item.equalsIgnoreCase("AddPartWeapon") || item.equalsIgnoreCase("AddPartRotWeapon") || item.equalsIgnoreCase("AddPartTurretWeapon") || item.equalsIgnoreCase("AddPartTurretRotWeapon") || item.equalsIgnoreCase("AddPartWeaponMissile")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1374 */       String[] s = data.split("\\s*,\\s*");
/*      */       
/* 1376 */       if (s.length >= 7)
/*      */       {
/* 1378 */         float rx = 0.0F, ry = 0.0F, rz = 0.0F, rb = 0.0F;
/* 1379 */         boolean isRot = (item.equalsIgnoreCase("AddPartRotWeapon") || item.equalsIgnoreCase("AddPartTurretRotWeapon"));
/*      */         
/* 1381 */         boolean isMissile = item.equalsIgnoreCase("AddPartWeaponMissile");
/* 1382 */         boolean turret = (item.equalsIgnoreCase("AddPartTurretWeapon") || item.equalsIgnoreCase("AddPartTurretRotWeapon"));
/*      */ 
/*      */         
/* 1385 */         if (isRot) {
/*      */ 
/*      */           
/* 1388 */           rx = (s.length >= 10) ? toFloat(s[7]) : 0.0F;
/* 1389 */           ry = (s.length >= 10) ? toFloat(s[8]) : 0.0F;
/* 1390 */           rz = (s.length >= 10) ? toFloat(s[9]) : -1.0F;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1395 */           rb = (s.length >= 8) ? toFloat(s[7]) : 0.0F;
/*      */         } 
/*      */         
/* 1398 */         PartWeapon w = new PartWeapon(this, splitParamSlash(s[0].toLowerCase().trim()), isRot, isMissile, toBool(s[1]), toBool(s[2]), toBool(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), "weapon" + this.partWeapon.size(), rx, ry, rz, rb, turret);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1408 */         this.lastWeaponPart = w;
/* 1409 */         this.partWeapon.add(w);
/*      */       }
/*      */     
/* 1412 */     } else if (item.equalsIgnoreCase("AddPartWeaponChild")) {
/*      */       
/* 1414 */       String[] s = data.split("\\s*,\\s*");
/* 1415 */       if (s.length >= 5 && this.lastWeaponPart != null)
/*      */       {
/* 1417 */         float rb = (s.length >= 6) ? toFloat(s[5]) : 0.0F;
/* 1418 */         PartWeaponChild w = new PartWeaponChild(this, this.lastWeaponPart.name, toBool(s[0]), toBool(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), this.lastWeaponPart.modelName + "_" + this.lastWeaponPart.child.size(), 0.0F, 0.0F, 0.0F, rb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1424 */         this.lastWeaponPart.child.add(w);
/*      */       }
/*      */     
/* 1427 */     } else if (item.compareTo("addrecipe") == 0 || item.compareTo("addshapelessrecipe") == 0) {
/*      */       
/* 1429 */       this.isShapedRecipe = (item.compareTo("addrecipe") == 0);
/* 1430 */       this.recipeString.add(data.toUpperCase());
/*      */     }
/* 1432 */     else if (item.compareTo("maxhp") == 0) {
/*      */       
/* 1434 */       this.maxHp = toInt(data, 1, 100000);
/*      */     }
/* 1436 */     else if (item.compareTo("inventorysize") == 0) {
/*      */       
/* 1438 */       this.inventorySize = toInt(data, 0, 54);
/*      */     }
/* 1440 */     else if (item.compareTo("damagefactor") == 0) {
/*      */       
/* 1442 */       this.damageFactor = toFloat(data, 0.0F, 1.0F);
/*      */     }
/* 1444 */     else if (item.equalsIgnoreCase("SubmergedDamageHeight")) {
/*      */       
/* 1446 */       this.submergedDamageHeight = toFloat(data, -1000.0F, 1000.0F);
/*      */     }
/* 1448 */     else if (item.equalsIgnoreCase("ArmorDamageFactor")) {
/*      */       
/* 1450 */       this.armorDamageFactor = toFloat(data, 0.0F, 10000.0F);
/*      */     }
/* 1452 */     else if (item.equalsIgnoreCase("ArmorMinDamage")) {
/*      */       
/* 1454 */       this.armorMinDamage = toFloat(data, 0.0F, 1000000.0F);
/*      */     }
/* 1456 */     else if (item.equalsIgnoreCase("ArmorMaxDamage")) {
/*      */       
/* 1458 */       this.armorMaxDamage = toFloat(data, 0.0F, 1000000.0F);
/*      */     }
/* 1460 */     else if (item.equalsIgnoreCase("FlareType")) {
/*      */       
/* 1462 */       String[] s = data.split("\\s*,\\s*");
/* 1463 */       this.flare.types = new int[s.length];
/* 1464 */       for (int i = 0; i < s.length; i++)
/*      */       {
/* 1466 */         this.flare.types[i] = toInt(s[i], 1, 10);
/*      */       }
/*      */     }
/* 1469 */     else if (item.equalsIgnoreCase("FlareOption")) {
/*      */       
/* 1471 */       String[] s = splitParam(data);
/* 1472 */       if (s.length >= 3)
/*      */       {
/* 1474 */         this.flare.pos = toVec3(s[0], s[1], s[2]);
/*      */       }
/*      */     }
/* 1477 */     else if (item.equalsIgnoreCase("Sound")) {
/*      */       
/* 1479 */       this.soundMove = data.toLowerCase();
/*      */     }
/* 1481 */     else if (item.equalsIgnoreCase("SoundRange")) {
/*      */       
/* 1483 */       this.soundRange = toFloat(data, 1.0F, 1000.0F);
/*      */     }
/* 1485 */     else if (item.equalsIgnoreCase("SoundVolume")) {
/*      */       
/* 1487 */       this.soundVolume = toFloat(data, 0.0F, 10.0F);
/*      */     }
/* 1489 */     else if (item.equalsIgnoreCase("SoundPitch")) {
/*      */       
/* 1491 */       this.soundPitch = toFloat(data, 0.0F, 10.0F);
/*      */     }
/* 1493 */     else if (item.equalsIgnoreCase("UAV")) {
/*      */       
/* 1495 */       this.isUAV = toBool(data);
/* 1496 */       this.isSmallUAV = false;
/*      */     }
/* 1498 */     else if (item.equalsIgnoreCase("SmallUAV")) {
/*      */       
/* 1500 */       this.isUAV = toBool(data);
/* 1501 */       this.isSmallUAV = true;
/*      */     }
/* 1503 */     else if (item.equalsIgnoreCase("TargetDrone")) {
/*      */       
/* 1505 */       this.isTargetDrone = toBool(data);
/*      */     }
/* 1507 */     else if (item.compareTo("autopilotrot") == 0) {
/*      */       
/* 1509 */       this.autoPilotRot = toFloat(data, -5.0F, 5.0F);
/*      */     }
/* 1511 */     else if (item.compareTo("ongroundpitch") == 0) {
/*      */ 
/*      */       
/* 1514 */       this.onGroundPitch = -toFloat(data, -90.0F, 90.0F);
/*      */     }
/* 1516 */     else if (item.compareTo("enablegunnermode") == 0) {
/*      */       
/* 1518 */       this.isEnableGunnerMode = toBool(data);
/*      */     }
/* 1520 */     else if (item.compareTo("hideentity") == 0) {
/*      */       
/* 1522 */       this.hideEntity = toBool(data);
/*      */     }
/* 1524 */     else if (item.equalsIgnoreCase("SmoothShading")) {
/*      */       
/* 1526 */       this.smoothShading = toBool(data);
/*      */     }
/* 1528 */     else if (item.compareTo("concurrentgunnermode") == 0) {
/*      */       
/* 1530 */       this.isEnableConcurrentGunnerMode = toBool(data);
/*      */     }
/* 1532 */     else if (item.equalsIgnoreCase("AddPartWeaponBay") || item.equalsIgnoreCase("AddPartSlideWeaponBay")) {
/*      */       
/* 1534 */       boolean slide = item.equalsIgnoreCase("AddPartSlideWeaponBay");
/* 1535 */       String[] s = data.split("\\s*,\\s*");
/* 1536 */       WeaponBay n = null;
/* 1537 */       if (slide)
/*      */       {
/* 1539 */         if (s.length >= 4)
/*      */         {
/* 1541 */           n = new WeaponBay(this, s[0].trim().toLowerCase(), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), 0.0F, 0.0F, 0.0F, 90.0F, "wb" + this.partWeaponBay.size(), slide);
/*      */ 
/*      */ 
/*      */           
/* 1545 */           this.partWeaponBay.add(n);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1550 */       else if (s.length >= 7)
/*      */       {
/* 1552 */         float mx = (s.length >= 8) ? toFloat(s[7], -180.0F, 180.0F) : 90.0F;
/* 1553 */         n = new WeaponBay(this, s[0].trim().toLowerCase(), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), mx / 90.0F, "wb" + this.partWeaponBay.size(), slide);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1558 */         this.partWeaponBay.add(n);
/*      */       }
/*      */     
/*      */     }
/* 1562 */     else if (item.compareTo("addparthatch") == 0 || item.compareTo("addpartslidehatch") == 0) {
/*      */       
/* 1564 */       boolean slide = (item.compareTo("addpartslidehatch") == 0);
/* 1565 */       String[] s = data.split("\\s*,\\s*");
/* 1566 */       Hatch n = null;
/* 1567 */       if (slide)
/*      */       {
/* 1569 */         if (s.length >= 3)
/*      */         {
/* 1571 */           n = new Hatch(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), 0.0F, 0.0F, 0.0F, 90.0F, "hatch" + this.hatchList.size(), slide);
/*      */ 
/*      */ 
/*      */           
/* 1575 */           this.hatchList.add(n);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1580 */       else if (s.length >= 6)
/*      */       {
/* 1582 */         float mx = (s.length >= 7) ? toFloat(s[6], -180.0F, 180.0F) : 90.0F;
/* 1583 */         n = new Hatch(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), mx, "hatch" + this.hatchList.size(), slide);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1588 */         this.hatchList.add(n);
/*      */       }
/*      */     
/*      */     }
/* 1592 */     else if (item.compareTo("addpartcanopy") == 0 || item.compareTo("addpartslidecanopy") == 0) {
/*      */       
/* 1594 */       String[] s = data.split("\\s*,\\s*");
/* 1595 */       boolean slide = (item.compareTo("addpartslidecanopy") == 0);
/*      */       
/* 1597 */       int canopyNum = this.canopyList.size();
/* 1598 */       if (canopyNum > 0) canopyNum--; 
/* 1599 */       if (slide)
/*      */       {
/* 1601 */         if (s.length >= 3)
/*      */         {
/* 1603 */           Canopy c = new Canopy(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), 0.0F, 0.0F, 0.0F, 90.0F, "canopy" + canopyNum, slide);
/*      */ 
/*      */ 
/*      */           
/* 1607 */           this.canopyList.add(c);
/*      */ 
/*      */ 
/*      */           
/* 1611 */           if (canopyNum == 0)
/*      */           {
/* 1613 */             c = new Canopy(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), 0.0F, 0.0F, 0.0F, 90.0F, "canopy", slide);
/*      */ 
/*      */ 
/*      */             
/* 1617 */             this.canopyList.add(c);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1623 */       else if (s.length >= 6)
/*      */       {
/* 1625 */         float mx = (s.length >= 7) ? toFloat(s[6], -180.0F, 180.0F) : 90.0F;
/* 1626 */         mx /= 90.0F;
/* 1627 */         Canopy c = new Canopy(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), mx, "canopy" + canopyNum, slide);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1632 */         this.canopyList.add(c);
/*      */ 
/*      */ 
/*      */         
/* 1636 */         if (canopyNum == 0)
/*      */         {
/* 1638 */           c = new Canopy(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), mx, "canopy", slide);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1643 */           this.canopyList.add(c);
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1648 */     } else if (item.equalsIgnoreCase("AddPartLG") || item.equalsIgnoreCase("AddPartSlideRotLG") || item.equalsIgnoreCase("AddPartLGRev") || item.equalsIgnoreCase("AddPartLGHatch")) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1653 */       String[] s = data.split("\\s*,\\s*");
/* 1654 */       if (!item.equalsIgnoreCase("AddPartSlideRotLG") && s.length >= 6) {
/*      */         
/* 1656 */         float maxRot = (s.length >= 7) ? toFloat(s[6], -180.0F, 180.0F) : 90.0F;
/* 1657 */         maxRot /= 90.0F;
/* 1658 */         LandingGear n = new LandingGear(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), "lg" + this.landingGear.size(), maxRot, item.equalsIgnoreCase("AddPartLgRev"), item.equalsIgnoreCase("AddPartLGHatch"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1665 */         if (s.length >= 8) {
/*      */           
/* 1667 */           n.enableRot2 = true;
/* 1668 */           n.maxRotFactor2 = (s.length >= 11) ? toFloat(s[10], -180.0F, 180.0F) : 90.0F;
/* 1669 */           n.maxRotFactor2 /= 90.0F;
/* 1670 */           n.rot2 = Vec3.func_72443_a(toFloat(s[7]), toFloat(s[8]), toFloat(s[9]));
/*      */         } 
/*      */         
/* 1673 */         this.landingGear.add(n);
/*      */       } 
/* 1675 */       if (item.equalsIgnoreCase("AddPartSlideRotLG") && s.length >= 9)
/*      */       {
/* 1677 */         float maxRot = (s.length >= 10) ? toFloat(s[9], -180.0F, 180.0F) : 90.0F;
/* 1678 */         maxRot /= 90.0F;
/* 1679 */         LandingGear n = new LandingGear(this, toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), toFloat(s[7]), toFloat(s[8]), "lg" + this.landingGear.size(), maxRot, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1684 */         n.slide = Vec3.func_72443_a(toFloat(s[0]), toFloat(s[1]), toFloat(s[2]));
/*      */         
/* 1686 */         this.landingGear.add(n);
/*      */       }
/*      */     
/* 1689 */     } else if (item.equalsIgnoreCase("AddPartThrottle")) {
/*      */       
/* 1691 */       String[] s = data.split("\\s*,\\s*");
/* 1692 */       if (s.length >= 7)
/*      */       {
/* 1694 */         float x = (s.length >= 8) ? toFloat(s[7]) : 0.0F;
/* 1695 */         float y = (s.length >= 9) ? toFloat(s[8]) : 0.0F;
/* 1696 */         float z = (s.length >= 10) ? toFloat(s[9]) : 0.0F;
/* 1697 */         Throttle c = new Throttle(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), "throttle" + this.partThrottle.size(), x, y, z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1703 */         this.partThrottle.add(c);
/*      */       }
/*      */     
/* 1706 */     } else if (item.equalsIgnoreCase("AddPartRotation")) {
/*      */       
/* 1708 */       String[] s = data.split("\\s*,\\s*");
/* 1709 */       if (s.length >= 7)
/*      */       {
/* 1711 */         boolean always = (s.length >= 8) ? toBool(s[7]) : true;
/* 1712 */         RotPart c = new RotPart(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), always, "rotpart" + this.partThrottle.size());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1718 */         this.partRotPart.add(c);
/*      */       }
/*      */     
/* 1721 */     } else if (item.compareTo("addpartcamera") == 0) {
/*      */       
/* 1723 */       String[] s = data.split("\\s*,\\s*");
/* 1724 */       if (s.length >= 3)
/*      */       {
/* 1726 */         boolean ys = (s.length >= 4) ? toBool(s[3]) : true;
/* 1727 */         boolean ps = (s.length >= 5) ? toBool(s[4]) : false;
/* 1728 */         Camera c = new Camera(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), 0.0F, -1.0F, 0.0F, "camera" + this.cameraList.size(), ys, ps);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1733 */         this.cameraList.add(c);
/*      */       }
/*      */     
/* 1736 */     } else if (item.equalsIgnoreCase("AddPartWheel")) {
/*      */       
/* 1738 */       String[] s = splitParam(data);
/* 1739 */       if (s.length >= 3)
/*      */       {
/* 1741 */         float rd = (s.length >= 4) ? toFloat(s[3], -1800.0F, 1800.0F) : 0.0F;
/* 1742 */         float rx = (s.length >= 7) ? toFloat(s[4]) : 0.0F;
/* 1743 */         float ry = (s.length >= 7) ? toFloat(s[5]) : 1.0F;
/* 1744 */         float rz = (s.length >= 7) ? toFloat(s[6]) : 0.0F;
/* 1745 */         float px = (s.length >= 10) ? toFloat(s[7]) : toFloat(s[0]);
/* 1746 */         float py = (s.length >= 10) ? toFloat(s[8]) : toFloat(s[1]);
/* 1747 */         float pz = (s.length >= 10) ? toFloat(s[9]) : toFloat(s[2]);
/* 1748 */         this.partWheel.add(new PartWheel(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), rx, ry, rz, rd, px, py, pz, "wheel" + this.partWheel.size()));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1756 */     else if (item.equalsIgnoreCase("AddPartSteeringWheel")) {
/*      */       
/* 1758 */       String[] s = splitParam(data);
/* 1759 */       if (s.length >= 7)
/*      */       {
/* 1761 */         this.partSteeringWheel.add(new PartWheel(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), toFloat(s[5]), toFloat(s[6]), "steering_wheel" + this.partSteeringWheel.size()));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1768 */     else if (item.equalsIgnoreCase("AddTrackRoller")) {
/*      */       
/* 1770 */       String[] s = splitParam(data);
/* 1771 */       if (s.length >= 3)
/*      */       {
/* 1773 */         this.partTrackRoller.add(new TrackRoller(this, toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), "track_roller" + this.partTrackRoller.size()));
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1779 */     else if (item.equalsIgnoreCase("AddCrawlerTrack")) {
/*      */       
/* 1781 */       this.partCrawlerTrack.add(createCrawlerTrack(data, "crawler_track" + this.partCrawlerTrack.size()));
/*      */ 
/*      */     
/*      */     }
/* 1785 */     else if (item.equalsIgnoreCase("PivotTurnThrottle")) {
/*      */       
/* 1787 */       this.pivotTurnThrottle = toFloat(data, 0.0F, 1.0F);
/*      */     }
/* 1789 */     else if (item.equalsIgnoreCase("TrackRollerRot")) {
/*      */       
/* 1791 */       this.trackRollerRot = toFloat(data, -10000.0F, 10000.0F);
/*      */     }
/* 1793 */     else if (item.equalsIgnoreCase("PartWheelRot")) {
/*      */       
/* 1795 */       this.partWheelRot = toFloat(data, -10000.0F, 10000.0F);
/*      */     }
/* 1797 */     else if (item.compareTo("camerazoom") == 0) {
/*      */       
/* 1799 */       this.cameraZoom = toInt(data, 1, 10);
/*      */     }
/* 1801 */     else if (item.equalsIgnoreCase("DefaultFreelook")) {
/*      */       
/* 1803 */       this.defaultFreelook = toBool(data);
/*      */     }
/* 1805 */     else if (item.equalsIgnoreCase("BoundingBox")) {
/*      */       
/* 1807 */       String[] s = data.split("\\s*,\\s*");
/* 1808 */       if (s.length >= 5)
/*      */       {
/* 1810 */         float df = (s.length >= 6) ? toFloat(s[5]) : 1.0F;
/* 1811 */         MCH_BoundingBox c = new MCH_BoundingBox(toFloat(s[0]), toFloat(s[1]), toFloat(s[2]), toFloat(s[3]), toFloat(s[4]), df);
/*      */ 
/*      */         
/* 1814 */         this.extraBoundingBox.add(c);
/* 1815 */         if (c.boundingBox.field_72337_e > this.markerHeight)
/*      */         {
/* 1817 */           this.markerHeight = (float)c.boundingBox.field_72337_e;
/*      */         }
/* 1819 */         this.markerWidth = (float)Math.max(this.markerWidth, Math.abs(c.boundingBox.field_72336_d) / 2.0D);
/* 1820 */         this.markerWidth = (float)Math.max(this.markerWidth, Math.abs(c.boundingBox.field_72340_a) / 2.0D);
/* 1821 */         this.markerWidth = (float)Math.max(this.markerWidth, Math.abs(c.boundingBox.field_72334_f) / 2.0D);
/* 1822 */         this.markerWidth = (float)Math.max(this.markerWidth, Math.abs(c.boundingBox.field_72339_c) / 2.0D);
/*      */         
/* 1824 */         this.bbZmin = (float)Math.min(this.bbZmin, c.boundingBox.field_72339_c);
/* 1825 */         this.bbZmax = (float)Math.min(this.bbZmax, c.boundingBox.field_72334_f);
/*      */       }
/*      */     
/* 1828 */     } else if (item.equalsIgnoreCase("RotorSpeed")) {
/*      */       
/* 1830 */       this.rotorSpeed = toFloat(data, -10000.0F, 10000.0F);
/* 1831 */       if (this.rotorSpeed > 0.01D) this.rotorSpeed = (float)(this.rotorSpeed - 0.01D); 
/* 1832 */       if (this.rotorSpeed < -0.01D) this.rotorSpeed = (float)(this.rotorSpeed + 0.01D);
/*      */     
/* 1834 */     } else if (item.equalsIgnoreCase("OnGroundPitchFactor")) {
/*      */       
/* 1836 */       this.onGroundPitchFactor = toFloat(data, 0.0F, 180.0F);
/*      */     }
/* 1838 */     else if (item.equalsIgnoreCase("OnGroundRollFactor")) {
/*      */       
/* 1840 */       this.onGroundRollFactor = toFloat(data, 0.0F, 180.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public CrawlerTrack createCrawlerTrack(String data, String name) {
/* 1846 */     String[] s = splitParam(data);
/* 1847 */     int PC = s.length - 3;
/*      */     
/* 1849 */     boolean REV = toBool(s[0]);
/* 1850 */     float LEN = toFloat(s[1], 0.001F, 1000.0F) * 0.9F;
/* 1851 */     float Z = toFloat(s[2]);
/*      */     
/* 1853 */     if (PC < 4) return null;
/*      */     
/* 1855 */     double[] cx = new double[PC];
/* 1856 */     double[] cy = new double[PC];
/* 1857 */     for (int i = 0; i < PC; i++) {
/*      */       
/* 1859 */       int idx = !REV ? i : (PC - i - 1);
/* 1860 */       String[] xy = splitParamSlash(s[3 + idx]);
/* 1861 */       cx[i] = toFloat(xy[0]);
/* 1862 */       cy[i] = toFloat(xy[1]);
/*      */     } 
/*      */     
/* 1865 */     List<CrawlerTrackPrm> lp = new ArrayList<CrawlerTrackPrm>();
/*      */     
/* 1867 */     lp.add(new CrawlerTrackPrm(this, (float)cx[0], (float)cy[0]));
/* 1868 */     double dist = 0.0D; int j;
/* 1869 */     for (j = 0; j < PC; j++) {
/*      */       
/* 1871 */       double x = cx[(j + 1) % PC] - cx[j];
/* 1872 */       double y = cy[(j + 1) % PC] - cy[j];
/* 1873 */       dist += Math.sqrt(x * x + y * y);
/* 1874 */       double dist2 = dist;
/* 1875 */       for (int k = 1; dist >= LEN; k++) {
/*      */         
/* 1877 */         lp.add(new CrawlerTrackPrm(this, (float)(cx[j] + x * (LEN * k) / dist2), (float)(cy[j] + y * (LEN * k) / dist2)));
/*      */ 
/*      */ 
/*      */         
/* 1881 */         dist -= LEN;
/*      */       } 
/*      */     } 
/*      */     
/* 1885 */     for (j = 0; j < lp.size(); j++) {
/*      */       
/* 1887 */       CrawlerTrackPrm pp = lp.get((j + lp.size() - 1) % lp.size());
/* 1888 */       CrawlerTrackPrm cp = lp.get(j);
/* 1889 */       CrawlerTrackPrm np = lp.get((j + 1) % lp.size());
/*      */       
/* 1891 */       float pr = (float)(Math.atan2((pp.x - cp.x), (pp.y - cp.y)) * 180.0D / Math.PI);
/* 1892 */       float nr = (float)(Math.atan2((np.x - cp.x), (np.y - cp.y)) * 180.0D / Math.PI);
/* 1893 */       float ppr = (pr + 360.0F) % 360.0F;
/* 1894 */       float nnr = nr + 180.0F;
/* 1895 */       if (nnr < ppr - 0.3D || nnr > ppr + 0.3D)
/*      */       {
/* 1897 */         if (nnr - ppr < 100.0F && nnr - ppr > -100.0F)
/*      */         {
/* 1899 */           nnr = (nnr + ppr) / 2.0F;
/*      */         }
/*      */       }
/* 1902 */       cp.r = nnr;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1908 */     CrawlerTrack c = new CrawlerTrack(this, name);
/* 1909 */     c.len = LEN;
/* 1910 */     c.cx = cx;
/* 1911 */     c.cy = cy;
/* 1912 */     c.lp = lp;
/* 1913 */     c.z = Z;
/* 1914 */     c.side = (Z >= 0.0F) ? 1 : 0;
/*      */     
/* 1916 */     return c;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTextureName() {
/* 1921 */     String s = this.textureNameList.get(this.textureCount);
/* 1922 */     this.textureCount = (this.textureCount + 1) % this.textureNameList.size();
/* 1923 */     return s;
/*      */   }
/*      */   
/*      */   public String getNextTextureName(String base) {
/* 1927 */     if (this.textureNameList.size() >= 2)
/*      */     {
/* 1929 */       for (int i = 0; i < this.textureNameList.size(); i++) {
/*      */         
/* 1931 */         String s = this.textureNameList.get(i);
/* 1932 */         if (s.equalsIgnoreCase(base)) {
/*      */           
/* 1934 */           i = (i + 1) % this.textureNameList.size();
/* 1935 */           return this.textureNameList.get(i);
/*      */         } 
/*      */       } 
/*      */     }
/* 1939 */     return base;
/*      */   }
/*      */ 
/*      */   
/*      */   public void preReload() {
/* 1944 */     this.textureNameList.clear();
/* 1945 */     this.textureNameList.add(this.name);
/* 1946 */     this.cameraList.clear();
/* 1947 */     this.cameraPosition.clear();
/* 1948 */     this.canopyList.clear();
/* 1949 */     this.flare = new Flare(this);
/* 1950 */     this.hatchList.clear();
/* 1951 */     this.hudList.clear();
/* 1952 */     this.landingGear.clear();
/* 1953 */     this.particleSplashs.clear();
/* 1954 */     this.searchLights.clear();
/* 1955 */     this.partThrottle.clear();
/* 1956 */     this.partRotPart.clear();
/* 1957 */     this.partCrawlerTrack.clear();
/* 1958 */     this.partTrackRoller.clear();
/* 1959 */     this.partWheel.clear();
/* 1960 */     this.partSteeringWheel.clear();
/* 1961 */     this.lightHatchList.clear();
/* 1962 */     this.partWeapon.clear();
/* 1963 */     this.partWeaponBay.clear();
/* 1964 */     this.repellingHooks.clear();
/* 1965 */     this.rideRacks.clear();
/* 1966 */     this.seatList.clear();
/* 1967 */     this.exclusionSeatList.clear();
/* 1968 */     this.entityRackList.clear();
/* 1969 */     this.extraBoundingBox.clear();
/* 1970 */     this.weaponSetList.clear();
/* 1971 */     this.lastWeaponIndex = -1;
/* 1972 */     this.lastWeaponType = "";
/* 1973 */     this.lastWeaponPart = null;
/* 1974 */     this.wheels.clear();
/* 1975 */     this.unmountPosition = null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getCannotReloadItem() {
/* 1980 */     return new String[] { "DisplayName", "AddDisplayName", "ItemID", "AddRecipe", "AddShapelessRecipe", "InventorySize", "Sound", "UAV", "SmallUAV", "TargetDrone", "Category" };
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canReloadItem(String item) {
/* 1997 */     String[] ignoreItems = getCannotReloadItem();
/* 1998 */     for (String s : ignoreItems) {
/*      */       
/* 2000 */       if (s.equalsIgnoreCase(item))
/*      */       {
/* 2002 */         return false;
/*      */       }
/*      */     } 
/* 2005 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */