  package mcheli.mcheli.aircraft;

 import mcheli.mcheli.MCH_ClientTickHandlerBase;
 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_PacketIndOpenScreen;
 import mcheli.mcheli.aircraft.MCH_EntityAircraft;
 import mcheli.mcheli.aircraft.MCH_PacketPlayerControlBase;
 import mcheli.mcheli.aircraft.MCH_PacketSeatPlayerControl;
 import mcheli.mcheli.MCH_ClientTickHandlerBase;
 import mcheli.mcheli.MCH_Key;
 import mcheli.mcheli.wrapper.W_Network;
 import mcheli.mcheli.wrapper.W_PacketBase;
 import net.minecraft.client.Minecraft;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.EntityPlayer;
 import org.lwjgl.input.Keyboard;


 public abstract class MCH_AircraftClientTickHandler
   extends MCH_ClientTickHandlerBase
 {
   protected boolean isRiding = false;
   protected boolean isBeforeRiding = false;
   public MCH_Key KeyUp;
   public MCH_Key KeyDown;
   public MCH_Key KeyRight;
   public MCH_Key KeyLeft;
   public MCH_Key KeyUseWeapon;
   public MCH_Key KeySwitchWeapon1;
   public MCH_Key KeySwitchWeapon2;
   public MCH_Key KeySwWeaponMode;
   public MCH_Key KeyUnmount;
   public MCH_Key KeyUnmountForce;
   public MCH_Key KeyExtra;
   public MCH_Key KeyFlare;
   public MCH_Key KeyCameraMode;
   public MCH_Key KeyFreeLook;
   public MCH_Key KeyGUI;
   public MCH_Key KeyGearUpDown;
   public MCH_Key KeyPutToRack;
   public MCH_Key KeyDownFromRack;
   public MCH_Key KeyBrake;

   public MCH_AircraftClientTickHandler(Minecraft minecraft, MCH_Config config) {
     super(minecraft);
     updateKeybind(config);
   }

   public void updateKeybind(MCH_Config config) {
     this.KeyUp = new MCH_Key(MCH_Config.KeyUp.prmInt);
     this.KeyDown = new MCH_Key(MCH_Config.KeyDown.prmInt);
     this.KeyRight = new MCH_Key(MCH_Config.KeyRight.prmInt);
     this.KeyLeft = new MCH_Key(MCH_Config.KeyLeft.prmInt);
     this.KeyUseWeapon = new MCH_Key(MCH_Config.KeyUseWeapon.prmInt);
     this.KeySwitchWeapon1 = new MCH_Key(MCH_Config.KeySwitchWeapon1.prmInt);
     this.KeySwitchWeapon2 = new MCH_Key(MCH_Config.KeySwitchWeapon2.prmInt);
     this.KeySwWeaponMode = new MCH_Key(MCH_Config.KeySwWeaponMode.prmInt);
     this.KeyUnmount = new MCH_Key(MCH_Config.KeyUnmount.prmInt);
     this.KeyUnmountForce = new MCH_Key(42);
     this.KeyExtra = new MCH_Key(MCH_Config.KeyExtra.prmInt);
     this.KeyFlare = new MCH_Key(MCH_Config.KeyFlare.prmInt);
     this.KeyCameraMode = new MCH_Key(MCH_Config.KeyCameraMode.prmInt);
     this.KeyFreeLook = new MCH_Key(MCH_Config.KeyFreeLook.prmInt);
     this.KeyGUI = new MCH_Key(MCH_Config.KeyGUI.prmInt);
     this.KeyGearUpDown = new MCH_Key(MCH_Config.KeyGearUpDown.prmInt);
     this.KeyPutToRack = new MCH_Key(MCH_Config.KeyPutToRack.prmInt);
     this.KeyDownFromRack = new MCH_Key(MCH_Config.KeyDownFromRack.prmInt);
     this.KeyBrake = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
   }
   protected void commonPlayerControlInGUI(EntityPlayer player, MCH_EntityAircraft ac, boolean isPilot, MCH_PacketPlayerControlBase pc) {}
   public boolean commonPlayerControl(EntityPlayer player, MCH_EntityAircraft ac, boolean isPilot, MCH_PacketPlayerControlBase pc) {
     if (Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {

       if (this.KeyGUI.isKeyDown() || this.KeyExtra.isKeyDown())
       {
         MCH_PacketSeatPlayerControl psc = new MCH_PacketSeatPlayerControl();
         if (isPilot) {

           psc.switchSeat = (byte)(this.KeyGUI.isKeyDown() ? 1 : 2);
         }
         else {

           ac.keepOnRideRotation = true;
           psc.switchSeat = 3;
         }
         W_Network.sendToServer((W_PacketBase)psc);
         return false;
       }

     } else if (!isPilot && ac.getSeatNum() > 1) {

       MCH_PacketSeatPlayerControl psc = new MCH_PacketSeatPlayerControl();

       if (this.KeyGUI.isKeyDown()) {

         psc.switchSeat = 1;
         W_Network.sendToServer((W_PacketBase)psc);
         return false;
       }
       if (this.KeyExtra.isKeyDown()) {

         psc.switchSeat = 2;
         W_Network.sendToServer((W_PacketBase)psc);
         return false;
       }
     }

     boolean send = false;

     if (this.KeyCameraMode.isKeyDown())
     {
       if (ac.haveSearchLight()) {

         if (ac.canSwitchSearchLight((Entity)player))
         {
           pc.switchSearchLight = true;
           playSoundOK();
           send = true;
         }

       } else if (ac.canSwitchCameraMode()) {

         int beforeMode = ac.getCameraMode(player);
         ac.switchCameraMode(player);
         int mode = ac.getCameraMode(player);
         if (mode != beforeMode)
         {
           pc.switchCameraMode = (byte)(mode + 1);
           playSoundOK();
           send = true;
         }

       } else {

         playSoundNG();
       }
     }

     if (this.KeyUnmount.isKeyDown() && !ac.isDestroyed() && ac.func_70302_i_() > 0 && !isPilot)
     {
       MCH_PacketIndOpenScreen.send(3);
     }

     if (isPilot) {
       if (this.KeyUnmount.isKeyDown()) {


         pc.isUnmount = 2;
         send = true;
       }

       if (this.KeyPutToRack.isKeyDown()) {

         ac.checkRideRack();
         if (ac.canRideRack())
         {
           pc.putDownRack = 3;
           send = true;
         }
         else if (ac.canPutToRack())
         {
           pc.putDownRack = 1;
           send = true;
         }

       } else if (this.KeyDownFromRack.isKeyDown()) {

         if (ac.field_70154_o != null) {

           pc.isUnmount = 3;
           send = true;
         }
         else if (ac.canDownFromRack()) {

           pc.putDownRack = 2;
           send = true;
         }
       }

       if (this.KeyGearUpDown.isKeyDown() && ac.getAcInfo().haveLandingGear())
       {
         if (ac.canFoldLandingGear()) {

           pc.switchGear = 1;
           send = true;
         }
         else if (ac.canUnfoldLandingGear()) {

           pc.switchGear = 2;
           send = true;
         }
       }

       if (this.KeyFreeLook.isKeyDown())
       {
         if (ac.canSwitchFreeLook()) {

           pc.switchFreeLook = ac.isFreeLookMode() ? 2 : 1;

           send = true;
         }
       }

       if (this.KeyGUI.isKeyDown()) {

         pc.openGui = true;
         send = true;
       }


       if (ac.isRepelling()) {

         pc.throttleDown = ac.throttleDown = false;
         pc.throttleUp = ac.throttleUp = false;
         pc.moveRight = ac.moveRight = false;
         pc.moveLeft = ac.moveLeft = false;


       }
       else if (ac.hasBrake() && this.KeyBrake.isKeyPress()) {

         send |= this.KeyBrake.isKeyDown();

         pc.throttleDown = ac.throttleDown = false;
         pc.throttleUp = ac.throttleUp = false;
         double dx = ac.field_70165_t - ac.field_70169_q;
         double dz = ac.field_70161_v - ac.field_70166_s;
         double dist = dx * dx + dz * dz;
         if (ac.getCurrentThrottle() <= 0.03D && dist < 0.01D) {

           pc.moveRight = ac.moveRight = false;
           pc.moveLeft = ac.moveLeft = false;
         }
         pc.useBrake = true;
       }
       else {

         send |= this.KeyBrake.isKeyUp();

         MCH_Key[] dKey = { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft };
         for (MCH_Key k : dKey) {

           if (k.isKeyDown() || k.isKeyUp()) {

             send = true;
             break;
           }
         }
         pc.throttleDown = ac.throttleDown = this.KeyDown.isKeyPress();
         pc.throttleUp = ac.throttleUp = this.KeyUp.isKeyPress();
         pc.moveRight = ac.moveRight = this.KeyRight.isKeyPress();
         pc.moveLeft = ac.moveLeft = this.KeyLeft.isKeyPress();
       }
     }



     if (!ac.isDestroyed() && this.KeyFlare.isKeyDown())
     {

       if (ac.getSeatIdByEntity((Entity)player) <= 1)
       {
         if (ac.canUseFlare() && ac.useFlare(ac.getCurrentFlareType())) {

           pc.useFlareType = (byte)ac.getCurrentFlareType();
           ac.nextFlareType();
           send = true;
         }
         else {

           playSoundNG();
         }
       }
     }


     if (!ac.isDestroyed() && !ac.isPilotReloading())
     {

       if (this.KeySwitchWeapon1.isKeyDown() || this.KeySwitchWeapon2.isKeyDown() || getMouseWheel() != 0) {


         if (getMouseWheel() > 0) {

           pc.switchWeapon = (byte)ac.getNextWeaponID((Entity)player, -1);
         }
         else {

           pc.switchWeapon = (byte)ac.getNextWeaponID((Entity)player, 1);
         }
         setMouseWheel(0);
         ac.switchWeapon((Entity)player, pc.switchWeapon);
         send = true;

       }
       else if (this.KeySwWeaponMode.isKeyDown()) {

         ac.switchCurrentWeaponMode((Entity)player);

       }
       else if (this.KeyUseWeapon.isKeyPress()) {


         if (ac.useCurrentWeapon((Entity)player)) {

           pc.useWeapon = true;
           pc.useWeaponOption1 = ac.getCurrentWeapon((Entity)player).getLastUsedOptionParameter1();
           pc.useWeaponOption2 = ac.getCurrentWeapon((Entity)player).getLastUsedOptionParameter2();
           pc.useWeaponPosX = ac.field_70169_q;
           pc.useWeaponPosY = ac.field_70167_r;
           pc.useWeaponPosZ = ac.field_70166_s;
           send = true;
         }
       }
     }
     return (send || player.field_70173_aa % 100 == 0);
   }
 }