package mcheli.mcheli;

 import mcheli.mcheli.MCH_ClientEventHook;
 import mcheli.mcheli.MCH_ClientTickHandlerBase;
 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_GuiCommon;
 import mcheli.mcheli.MCH_Key;
 import mcheli.mcheli.MCH_ServerSettings;
 import mcheli.mcheli.MCH_ViewEntityDummy;
 import mcheli.mcheli.aircraft.MCH_AircraftInfo;
 import mcheli.mcheli.aircraft.MCH_ClientSeatTickHandler;
 import mcheli.mcheli.aircraft.MCH_EntityAircraft;
 import mcheli.mcheli.aircraft.MCH_EntitySeat;
 import mcheli.mcheli.aircraft.MCH_SeatInfo;
 import mcheli.mcheli.command.MCH_GuiTitle;
 import mcheli.mcheli.gltd.MCH_ClientGLTDTickHandler;
 import mcheli.mcheli.gltd.MCH_EntityGLTD;
 import mcheli.mcheli.gltd.MCH_GuiGLTD;
 import mcheli.mcheli.gui.MCH_Gui;
 import mcheli.mcheli.helicopter.MCH_ClientHeliTickHandler;
 import mcheli.mcheli.helicopter.MCH_GuiHeli;
 import mcheli.mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
 import mcheli.mcheli.lweapon.MCH_GuiLightWeapon;
 import mcheli.mcheli.multiplay.MCH_GuiScoreboard;
 import mcheli.mcheli.multiplay.MCH_GuiTargetMarker;
 import mcheli.mcheli.plane.MCP_ClientPlaneTickHandler;
 import mcheli.mcheli.plane.MCP_GuiPlane;
 import mcheli.mcheli.tank.MCH_ClientTankTickHandler;
 import mcheli.mcheli.tank.MCH_GuiTank;
 import mcheli.mcheli.tool.MCH_ClientToolTickHandler;
 import mcheli.mcheli.tool.MCH_GuiWrench;
 import mcheli.mcheli.tool.rangefinder.MCH_GuiRangeFinder;
 import mcheli.mcheli.uav.MCH_EntityUavStation;
 import mcheli.mcheli.vehicle.MCH_ClientVehicleTickHandler;
 import mcheli.mcheli.vehicle.MCH_GuiVehicle;
 import mcheli.mcheli.weapon.MCH_WeaponSet;
 import mcheli.mcheli.wrapper.W_Lib;
 import mcheli.mcheli.wrapper.W_McClient;
 import mcheli.mcheli.wrapper.W_Reflection;
 import mcheli.mcheli.wrapper.W_TickHandler;
 import mcheli.mcheli.wrapper.W_Vec3;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.entity.RenderManager;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraft.item.ItemStack;
 import net.minecraftforge.fml.relauncher.Side;
 import net.minecraftforge.fml.relauncher.SideOnly;
 import org.lwjgl.opengl.Display;

 //TODO: Complete Mess (bytecode)    I think it would be better to just rewrite the whole mod lol

 @SideOnly(Side.CLIENT)
 public class MCH_ClientCommonTickHandler
   extends W_TickHandler
 {
   public static mcheli.mcheli.MCH_ClientCommonTickHandler instance;
   public MCH_GuiCommon gui_Common;
   public MCH_Gui gui_Heli;
   public MCH_Gui gui_Plane;
   public MCH_Gui gui_Tank;
   public MCH_Gui gui_GLTD;
   public MCH_Gui gui_Vehicle;
   public MCH_Gui gui_LWeapon;
   public MCH_Gui gui_Wrench;
   public MCH_Gui gui_EMarker;
   public static int cameraMode = 0; public MCH_Gui gui_RngFndr; public MCH_Gui gui_Title; public MCH_Gui[] guis; public MCH_Gui[] guiTicks; public MCH_ClientTickHandlerBase[] ticks; public MCH_Key[] Keys; public MCH_Key KeyCamDistUp; public MCH_Key KeyCamDistDown; public MCH_Key KeyScoreboard; public MCH_Key KeyMultiplayManager;
   public static MCH_EntityAircraft ridingAircraft = null;

   public static boolean isDrawScoreboard = false;

   public static int sendLDCount = 0;

   public static boolean isLocked = false;
   public static int lockedSoundCount = 0; int debugcnt; private static double prevMouseDeltaX;
   private static double prevMouseDeltaY;

   public MCH_ClientCommonTickHandler(Minecraft minecraft, MCH_Config config) {
     super(minecraft);

     this.gui_Common = new MCH_GuiCommon(minecraft);
     this.gui_Heli = (MCH_Gui)new MCH_GuiHeli(minecraft);
     this.gui_Plane = (MCH_Gui)new MCP_GuiPlane(minecraft);
     this.gui_Tank = (MCH_Gui)new MCH_GuiTank(minecraft);
     this.gui_GLTD = (MCH_Gui)new MCH_GuiGLTD(minecraft);
     this.gui_Vehicle = (MCH_Gui)new MCH_GuiVehicle(minecraft);
     this.gui_LWeapon = (MCH_Gui)new MCH_GuiLightWeapon(minecraft);
     this.gui_Wrench = (MCH_Gui)new MCH_GuiWrench(minecraft);
     this.gui_RngFndr = (MCH_Gui)new MCH_GuiRangeFinder(minecraft);
     this.gui_EMarker = (MCH_Gui)new MCH_GuiTargetMarker(minecraft);
     this.gui_Title = (MCH_Gui)new MCH_GuiTitle(minecraft);
     this.guis = new MCH_Gui[] { this.gui_RngFndr, this.gui_LWeapon, this.gui_Heli, this.gui_Plane, this.gui_Tank, this.gui_GLTD, this.gui_Vehicle };

     this.guiTicks = new MCH_Gui[] { (MCH_Gui)this.gui_Common, this.gui_Heli, this.gui_Plane, this.gui_Tank, this.gui_GLTD, this.gui_Vehicle, this.gui_LWeapon, this.gui_Wrench, this.gui_RngFndr, this.gui_EMarker, this.gui_Title };

     this.ticks = new MCH_ClientTickHandlerBase[] { (MCH_ClientTickHandlerBase)new MCH_ClientHeliTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCP_ClientPlaneTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientTankTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientGLTDTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientVehicleTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientLightWeaponTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientSeatTickHandler(minecraft, config), (MCH_ClientTickHandlerBase)new MCH_ClientToolTickHandler(minecraft, config) };

     updatekeybind(config);
   }

   public void updatekeybind(MCH_Config config) {
     this.KeyCamDistUp = new MCH_Key(MCH_Config.KeyCameraDistUp.prmInt);
     this.KeyCamDistDown = new MCH_Key(MCH_Config.KeyCameraDistDown.prmInt);
     this.KeyScoreboard = new MCH_Key(MCH_Config.KeyScoreboard.prmInt);
     this.KeyMultiplayManager = new MCH_Key(MCH_Config.KeyMultiplayManager.prmInt);
     this.Keys = new MCH_Key[] { this.KeyCamDistUp, this.KeyCamDistDown, this.KeyScoreboard, this.KeyMultiplayManager };

     for (MCH_ClientTickHandlerBase t : this.ticks)
     {
       t.updateKeybind(config);
     }
   }

   public String getLabel() {
     return null;
   }

   public void onTick() {
     // Byte code:
     //   0: invokestatic initRotLimit : ()V
     //   3: aload_0
     //   4: getfield Keys : [Lmcheli/MCH_Key;
     //   7: astore_1
     //   8: aload_1
     //   9: arraylength
     //   10: istore_2
     //   11: iconst_0
     //   12: istore_3
     //   13: iload_3
     //   14: iload_2
     //   15: if_icmpge -> 34
     //   18: aload_1
     //   19: iload_3
     //   20: aaload
     //   21: astore #4
     //   23: aload #4
     //   25: invokevirtual update : ()V
     //   28: iinc #3, 1
     //   31: goto -> 13
     //   34: aload_0
     //   35: getfield mc : Lnet/minecraft/client/Minecraft;
     //   38: getfield field_71439_g : Lnet/minecraft/client/entity/EntityClientPlayerMP;
     //   41: astore_1
     //   42: aload_1
     //   43: ifnull -> 208
     //   46: aload_0
     //   47: getfield mc : Lnet/minecraft/client/Minecraft;
     //   50: getfield field_71462_r : Lnet/minecraft/client/gui/GuiScreen;
     //   53: ifnonnull -> 208
     //   56: getstatic mcheli/MCH_ServerSettings.enableCamDistChange : Z
     //   59: ifeq -> 148
     //   62: aload_0
     //   63: getfield KeyCamDistUp : Lmcheli/MCH_Key;
     //   66: invokevirtual isKeyDown : ()Z
     //   69: ifne -> 82
     //   72: aload_0
     //   73: getfield KeyCamDistDown : Lmcheli/MCH_Key;
     //   76: invokevirtual isKeyDown : ()Z
     //   79: ifeq -> 148
     //   82: invokestatic getThirdPersonDistance : ()F
     //   85: f2i
     //   86: istore_2
     //   87: aload_0
     //   88: getfield KeyCamDistUp : Lmcheli/MCH_Key;
     //   91: invokevirtual isKeyDown : ()Z
     //   94: ifeq -> 123
     //   97: iload_2
     //   98: bipush #60
     //   100: if_icmpge -> 123
     //   103: iinc #2, 4
     //   106: iload_2
     //   107: bipush #60
     //   109: if_icmple -> 115
     //   112: bipush #60
     //   114: istore_2
     //   115: iload_2
     //   116: i2f
     //   117: invokestatic setThirdPersonDistance : (F)V
     //   120: goto -> 148
     //   123: aload_0
     //   124: getfield KeyCamDistDown : Lmcheli/MCH_Key;
     //   127: invokevirtual isKeyDown : ()Z
     //   130: ifeq -> 148
     //   133: iinc #2, -4
     //   136: iload_2
     //   137: iconst_4
     //   138: if_icmpge -> 143
     //   141: iconst_4
     //   142: istore_2
     //   143: iload_2
     //   144: i2f
     //   145: invokestatic setThirdPersonDistance : (F)V
     //   148: aload_0
     //   149: getfield mc : Lnet/minecraft/client/Minecraft;
     //   152: getfield field_71462_r : Lnet/minecraft/client/gui/GuiScreen;
     //   155: ifnonnull -> 208
     //   158: aload_0
     //   159: getfield mc : Lnet/minecraft/client/Minecraft;
     //   162: invokevirtual func_71356_B : ()Z
     //   165: ifeq -> 178
     //   168: getstatic mcheli/MCH_MOD.config : Lmcheli/MCH_Config;
     //   171: pop
     //   172: getstatic mcheli/MCH_Config.DebugLog : Z
     //   175: ifeq -> 208
     //   178: aload_0
     //   179: getfield KeyScoreboard : Lmcheli/MCH_Key;
     //   182: invokevirtual isKeyPress : ()Z
     //   185: putstatic mcheli/MCH_ClientCommonTickHandler.isDrawScoreboard : Z
     //   188: getstatic mcheli/MCH_ClientCommonTickHandler.isDrawScoreboard : Z
     //   191: ifne -> 208
     //   194: aload_0
     //   195: getfield KeyMultiplayManager : Lmcheli/MCH_Key;
     //   198: invokevirtual isKeyDown : ()Z
     //   201: ifeq -> 208
     //   204: iconst_5
     //   205: invokestatic send : (I)V
     //   208: getstatic mcheli/MCH_ClientCommonTickHandler.sendLDCount : I
     //   211: bipush #10
     //   213: if_icmpge -> 227
     //   216: getstatic mcheli/MCH_ClientCommonTickHandler.sendLDCount : I
     //   219: iconst_1
     //   220: iadd
     //   221: putstatic mcheli/MCH_ClientCommonTickHandler.sendLDCount : I
     //   224: goto -> 234
     //   227: invokestatic sendImageData : ()V
     //   230: iconst_0
     //   231: putstatic mcheli/MCH_ClientCommonTickHandler.sendLDCount : I
     //   234: aload_0
     //   235: getfield mc : Lnet/minecraft/client/Minecraft;
     //   238: getfield field_71462_r : Lnet/minecraft/client/gui/GuiScreen;
     //   241: ifnull -> 248
     //   244: iconst_1
     //   245: goto -> 249
     //   248: iconst_0
     //   249: istore_2
     //   250: aload_0
     //   251: getfield ticks : [Lmcheli/MCH_ClientTickHandlerBase;
     //   254: astore_3
     //   255: aload_3
     //   256: arraylength
     //   257: istore #4
     //   259: iconst_0
     //   260: istore #5
     //   262: iload #5
     //   264: iload #4
     //   266: if_icmpge -> 287
     //   269: aload_3
     //   270: iload #5
     //   272: aaload
     //   273: astore #6
     //   275: aload #6
     //   277: iload_2
     //   278: invokevirtual onTick : (Z)V
     //   281: iinc #5, 1
     //   284: goto -> 262
     //   287: aload_0
     //   288: getfield guiTicks : [Lmcheli/gui/MCH_Gui;
     //   291: astore_3
     //   292: aload_3
     //   293: arraylength
     //   294: istore #4
     //   296: iconst_0
     //   297: istore #5
     //   299: iload #5
     //   301: iload #4
     //   303: if_icmpge -> 323
     //   306: aload_3
     //   307: iload #5
     //   309: aaload
     //   310: astore #6
     //   312: aload #6
     //   314: invokevirtual onTick : ()V
     //   317: iinc #5, 1
     //   320: goto -> 299
     //   323: aload_1
     //   324: invokestatic getAircraft_RiddenOrControl : (Lnet/minecraft/entity/Entity;)Lmcheli/aircraft/MCH_EntityAircraft;
     //   327: astore_3
     //   328: aload_1
     //   329: ifnull -> 373
     //   332: aload_3
     //   333: ifnull -> 373
     //   336: aload_3
     //   337: invokevirtual isDestroyed : ()Z
     //   340: ifne -> 373
     //   343: getstatic mcheli/MCH_ClientCommonTickHandler.isLocked : Z
     //   346: ifeq -> 381
     //   349: getstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   352: ifne -> 381
     //   355: iconst_0
     //   356: putstatic mcheli/MCH_ClientCommonTickHandler.isLocked : Z
     //   359: bipush #20
     //   361: putstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   364: ldc_w 'locked'
     //   367: invokestatic playSound : (Ljava/lang/String;)V
     //   370: goto -> 381
     //   373: iconst_0
     //   374: putstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   377: iconst_0
     //   378: putstatic mcheli/MCH_ClientCommonTickHandler.isLocked : Z
     //   381: getstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   384: ifle -> 395
     //   387: getstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   390: iconst_1
     //   391: isub
     //   392: putstatic mcheli/MCH_ClientCommonTickHandler.lockedSoundCount : I
     //   395: return
     // Line number table:
     //   Java source line number -> byte code offset
     //   #138	-> 0
     //   #140	-> 3
     //   #142	-> 34
     //   #143	-> 42
     //   #145	-> 56
     //   #147	-> 62
     //   #149	-> 82
     //   #150	-> 87
     //   #152	-> 103
     //   #153	-> 106
     //   #154	-> 115
     //   #156	-> 123
     //   #158	-> 133
     //   #159	-> 136
     //   #160	-> 143
     //   #165	-> 148
     //   #167	-> 178
     //   #169	-> 188
     //   #174	-> 204
     //   #180	-> 208
     //   #182	-> 216
     //   #186	-> 227
     //   #187	-> 230
     //   #190	-> 234
     //   #192	-> 250
     //   #194	-> 275
     //   #192	-> 281
     //   #197	-> 287
     //   #199	-> 312
     //   #197	-> 317
     //   #202	-> 323
     //   #203	-> 328
     //   #205	-> 343
     //   #207	-> 355
     //   #208	-> 359
     //   #209	-> 364
     //   #214	-> 373
     //   #215	-> 377
     //   #217	-> 381
     //   #219	-> 387
     //   #221	-> 395
     // Local variable table:
     //   start	length	slot	name	descriptor
     //   23	5	4	k	Lmcheli/MCH_Key;
     //   8	26	1	arr$	[Lmcheli/MCH_Key;
     //   11	23	2	len$	I
     //   13	21	3	i$	I
     //   87	61	2	camdist	I
     //   275	6	6	t	Lmcheli/MCH_ClientTickHandlerBase;
     //   255	32	3	arr$	[Lmcheli/MCH_ClientTickHandlerBase;
     //   259	28	4	len$	I
     //   262	25	5	i$	I
     //   312	5	6	g	Lmcheli/gui/MCH_Gui;
     //   292	31	3	arr$	[Lmcheli/gui/MCH_Gui;
     //   296	27	4	len$	I
     //   299	24	5	i$	I
     //   0	396	0	this	Lmcheli/MCH_ClientCommonTickHandler;
     //   42	354	1	player	Lnet/minecraft/entity/player/EntityPlayer;
     //   250	146	2	inOtherGui	Z
     //   328	68	3	ac	Lmcheli/aircraft/MCH_EntityAircraft;
   }

   public void onTickPre() {
     if (this.mc.field_71439_g != null && this.mc.field_71441_e != null)
     {
       onTick();
     }
   }


   public void onTickPost() {
     if (this.mc.field_71439_g != null && this.mc.field_71441_e != null)
     {
       MCH_GuiTargetMarker.onClientTick();
     }
   }



   private static double mouseDeltaX = 0.0D;
   private static double mouseDeltaY = 0.0D;
   private static double mouseRollDeltaX = 0.0D;
   private static double mouseRollDeltaY = 0.0D;
   private static boolean isRideAircraft = false;
   private static float prevTick = 0.0F;


   public static double getCurrentStickX() {
     return mouseRollDeltaX;
   }

   public static double getCurrentStickY() {
     double inv = 1.0D;
     if ((Minecraft.func_71410_x()).field_71474_y.field_74338_d)
     {
       inv = -inv;
     }
     if (MCH_Config.InvertMouse.prmBool)
     {
       inv = -inv;
     }
     return mouseRollDeltaY * inv;
   }

   public static double getMaxStickLength() {
     return 40.0D;
   }


   public void updateMouseDelta(boolean stickMode, float partialTicks) {
     this; this; prevMouseDeltaX = mouseDeltaX;
     this; this; prevMouseDeltaY = mouseDeltaY;
     this; mouseDeltaX = 0.0D;
     this; mouseDeltaY = 0.0D;

     if (this.mc.field_71415_G && Display.isActive() && this.mc.field_71462_r == null) {

       if (stickMode) {

         this; if (Math.abs(mouseRollDeltaX) < getMaxStickLength() * 0.2D) {

           this; mouseRollDeltaX *= (1.0F - 0.15F * partialTicks);
         }
         this; if (Math.abs(mouseRollDeltaY) < getMaxStickLength() * 0.2D) {

           this; mouseRollDeltaY *= (1.0F - 0.15F * partialTicks);
         }
       }

       this.mc.field_71417_B.func_74374_c();
       float f1 = this.mc.field_71474_y.field_74341_c * 0.6F + 0.2F;
       float f2 = f1 * f1 * f1 * 8.0F;

       double ms = MCH_Config.MouseSensitivity.prmDouble * 0.1D;

       this; mouseDeltaX = ms * this.mc.field_71417_B.field_74377_a * f2;
       this; mouseDeltaY = ms * this.mc.field_71417_B.field_74375_b * f2;

       byte inv = 1;

       if (this.mc.field_71474_y.field_74338_d)
       {
         inv = -1;
       }
       if (MCH_Config.InvertMouse.prmBool)
       {
         inv = (byte)(inv * -1);
       }

       this; mouseRollDeltaX += mouseDeltaX;
       this; mouseRollDeltaY += mouseDeltaY * inv;



       this; this; this; this; double dist = mouseRollDeltaX * mouseRollDeltaX + mouseRollDeltaY * mouseRollDeltaY;
       if (dist > 1.0D) {

         dist = MathHelper.func_76133_a(dist);
         double d = dist;
         if (d > getMaxStickLength()) d = getMaxStickLength();

         this; mouseRollDeltaX /= dist;
         this; mouseRollDeltaY /= dist;
         this; mouseRollDeltaX *= d;
         this; mouseRollDeltaY *= d;
       }
     }
   }

   public void onRenderTickPre(float partialTicks) {
     MCH_GuiTargetMarker.clearMarkEntityPos();

     if (!MCH_ServerSettings.enableDebugBoundingBox)
     {
       RenderManager.field_85095_o = false;
     }

     MCH_ClientEventHook.haveSearchLightAircraft.clear();
     if (this.mc != null && this.mc.field_71441_e != null) {
       for (Object o : (Minecraft.func_71410_x()).field_71441_e.field_72996_f) {

         if (o instanceof MCH_EntityAircraft)
         {
           if (((MCH_EntityAircraft)o).haveSearchLight())
           {
             MCH_ClientEventHook.haveSearchLightAircraft.add((MCH_EntityAircraft)o);
           }
         }
       }
     }
     if (W_McClient.isGamePaused())
       return;  EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
     if (entityClientPlayerMP == null)
       return;
     ItemStack currentItemstack = entityClientPlayerMP.func_71045_bC();
     if (currentItemstack != null && currentItemstack.func_77973_b() instanceof mcheli.tool.MCH_ItemWrench)
     {
       if (entityClientPlayerMP.func_71052_bv() > 0)
       {
         W_Reflection.setItemRendererProgress(1.0F);
       }
     }

     ridingAircraft = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)entityClientPlayerMP);
     if (ridingAircraft != null) {

       cameraMode = ridingAircraft.getCameraMode((EntityPlayer)entityClientPlayerMP);


     }
     else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityGLTD) {

       MCH_EntityGLTD gltd = (MCH_EntityGLTD)((EntityPlayer)entityClientPlayerMP).field_70154_o;
       cameraMode = gltd.camera.getMode(0);
     }
     else {

       cameraMode = 0;
     }

     MCH_EntityAircraft ac = null;
     if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof mcheli.helicopter.MCH_EntityHeli || ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof mcheli.plane.MCP_EntityPlane || ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof mcheli.tank.MCH_EntityTank) {


       ac = (MCH_EntityAircraft)((EntityPlayer)entityClientPlayerMP).field_70154_o;
     }
     else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityUavStation) {

       ac = ((MCH_EntityUavStation)((EntityPlayer)entityClientPlayerMP).field_70154_o).getControlAircract();
     }
     else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof mcheli.vehicle.MCH_EntityVehicle) {

       MCH_EntityAircraft vehicle = (MCH_EntityAircraft)((EntityPlayer)entityClientPlayerMP).field_70154_o;
       vehicle.setupAllRiderRenderPosition(partialTicks, (EntityPlayer)entityClientPlayerMP);
     }

     boolean stickMode = false;
     if (ac instanceof mcheli.helicopter.MCH_EntityHeli)
     {
       stickMode = MCH_Config.MouseControlStickModeHeli.prmBool;
     }
     if (ac instanceof mcheli.plane.MCP_EntityPlane)
     {
       stickMode = MCH_Config.MouseControlStickModePlane.prmBool;
     }

     for (int i = 0; i < 10; ) { this; if (prevTick > partialTicks) {

         this; prevTick--; i++;
       }  }

     if (ac != null && ac.canMouseRot()) {

       this; if (!isRideAircraft)
       {
         ac.onInteractFirst((EntityPlayer)entityClientPlayerMP);
       }
       this; isRideAircraft = true;

       updateMouseDelta(stickMode, partialTicks);

       boolean fixRot = false;
       float fixYaw = 0.0F;
       float fixPitch = 0.0F;
       MCH_SeatInfo seatInfo = ac.getSeatInfo((Entity)entityClientPlayerMP);
       if (seatInfo != null && seatInfo.fixRot && ac.getIsGunnerMode((Entity)entityClientPlayerMP) && !ac.isGunnerLookMode((EntityPlayer)entityClientPlayerMP)) {

         fixRot = true;
         fixYaw = seatInfo.fixYaw;
         fixPitch = seatInfo.fixPitch;
         this; mouseRollDeltaX *= 0.0D;
         this; mouseRollDeltaY *= 0.0D;
         this; mouseDeltaX *= 0.0D;
         this; mouseDeltaY *= 0.0D;
       }
       else if (ac.isPilot((Entity)entityClientPlayerMP)) {

         MCH_AircraftInfo.CameraPosition cp = ac.getCameraPosInfo();
         if (cp != null) {

           fixYaw = cp.yaw;
           fixPitch = cp.pitch;
         }
       }

       if (ac.getAcInfo() == null) {

         this; this; entityClientPlayerMP.func_70082_c((float)mouseDeltaX, (float)mouseDeltaY);
       }
       else {

         this; this; this; this; this; this; this; ac.setAngles((Entity)entityClientPlayerMP, fixRot, fixYaw, fixPitch, (float)(mouseDeltaX + prevMouseDeltaX) / 2.0F, (float)(mouseDeltaY + prevMouseDeltaY) / 2.0F, (float)mouseRollDeltaX, (float)mouseRollDeltaY, partialTicks - prevTick);
       }

       ac.setupAllRiderRenderPosition(partialTicks, (EntityPlayer)entityClientPlayerMP);

       this; this; this; this; double dist = MathHelper.func_76133_a(mouseRollDeltaX * mouseRollDeltaX + mouseRollDeltaY * mouseRollDeltaY);


       if (!stickMode || dist < getMaxStickLength() * 0.1D) {

         this; mouseRollDeltaX *= 0.95D;
         this; mouseRollDeltaY *= 0.95D;
       }

       float roll = MathHelper.func_76142_g(ac.getRotRoll());
       float yaw = MathHelper.func_76142_g(ac.getRotYaw() - ((EntityPlayer)entityClientPlayerMP).field_70177_z);
       roll *= MathHelper.func_76134_b((float)(yaw * Math.PI / 180.0D));
       if (ac.getTVMissile() != null && W_Lib.isClientPlayer((ac.getTVMissile()).shootingEntity) && ac.getIsGunnerMode((Entity)entityClientPlayerMP))
       {


         roll = 0.0F;
       }
       W_Reflection.setCameraRoll(roll);
       correctViewEntityDummy((Entity)entityClientPlayerMP);
     }
     else {

       MCH_EntitySeat seat = (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) ? (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o : null;
       if (seat != null && seat.getParent() != null) {

         updateMouseDelta(stickMode, partialTicks);

         ac = seat.getParent();

         boolean fixRot = false;
         MCH_SeatInfo seatInfo = ac.getSeatInfo((Entity)entityClientPlayerMP);
         if (seatInfo != null && seatInfo.fixRot && ac.getIsGunnerMode((Entity)entityClientPlayerMP) && !ac.isGunnerLookMode((EntityPlayer)entityClientPlayerMP)) {

           fixRot = true;
           this; mouseRollDeltaX *= 0.0D;
           this; mouseRollDeltaY *= 0.0D;
           this; mouseDeltaX *= 0.0D;
           this; mouseDeltaY *= 0.0D;
         }

         this; this; Vec3 v = Vec3.func_72443_a(mouseDeltaX, mouseRollDeltaY, 0.0D);
         W_Vec3.rotateAroundZ((float)((ac.calcRotRoll(partialTicks) / 180.0F) * Math.PI), v);

         MCH_WeaponSet ws = ac.getCurrentWeapon((Entity)entityClientPlayerMP);
         this; mouseDeltaY *= (ws != null && ws.getInfo() != null) ? (ws.getInfo()).cameraRotationSpeedPitch : 1.0D;

         this; this; entityClientPlayerMP.func_70082_c((float)mouseDeltaX, (float)mouseDeltaY);

         float y = ac.getRotYaw();
         float p = ac.getRotPitch();
         float r = ac.getRotRoll();
         ac.setRotYaw(ac.calcRotYaw(partialTicks));
         ac.setRotPitch(ac.calcRotPitch(partialTicks));
         ac.setRotRoll(ac.calcRotRoll(partialTicks));

         float revRoll = 0.0F;
         if (fixRot) {

           ((EntityPlayer)entityClientPlayerMP).field_70177_z = ac.getRotYaw() + seatInfo.fixYaw;
           ((EntityPlayer)entityClientPlayerMP).field_70125_A = ac.getRotPitch() + seatInfo.fixPitch;
           if (((EntityPlayer)entityClientPlayerMP).field_70125_A > 90.0F) {

             ((EntityPlayer)entityClientPlayerMP).field_70127_C -= (((EntityPlayer)entityClientPlayerMP).field_70125_A - 90.0F) * 2.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70125_A -= (((EntityPlayer)entityClientPlayerMP).field_70125_A - 90.0F) * 2.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70126_B += 180.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70177_z += 180.0F;
             revRoll = 180.0F;
           }
           else if (((EntityPlayer)entityClientPlayerMP).field_70125_A < -90.0F) {

             ((EntityPlayer)entityClientPlayerMP).field_70127_C -= (((EntityPlayer)entityClientPlayerMP).field_70125_A - 90.0F) * 2.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70125_A -= (((EntityPlayer)entityClientPlayerMP).field_70125_A - 90.0F) * 2.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70126_B += 180.0F;
             ((EntityPlayer)entityClientPlayerMP).field_70177_z += 180.0F;
             revRoll = 180.0F;
           }
         }

         ac.setupAllRiderRenderPosition(partialTicks, (EntityPlayer)entityClientPlayerMP);
         ac.setRotYaw(y);
         ac.setRotPitch(p);
         ac.setRotRoll(r);

         this; mouseRollDeltaX *= 0.9D;
         this; mouseRollDeltaY *= 0.9D;

         float roll = MathHelper.func_76142_g(ac.getRotRoll());
         float yaw = MathHelper.func_76142_g(ac.getRotYaw() - ((EntityPlayer)entityClientPlayerMP).field_70177_z);
         roll *= MathHelper.func_76134_b((float)(yaw * Math.PI / 180.0D));
         if (ac.getTVMissile() != null && W_Lib.isClientPlayer((ac.getTVMissile()).shootingEntity) && ac.getIsGunnerMode((Entity)entityClientPlayerMP))
         {


           roll = 0.0F;
         }
         W_Reflection.setCameraRoll(roll + revRoll);
         correctViewEntityDummy((Entity)entityClientPlayerMP);
       }
       else {

         this; if (isRideAircraft) {

           W_Reflection.setCameraRoll(0.0F);
           this; isRideAircraft = false;
         }
         this; mouseRollDeltaX = 0.0D;
         this; mouseRollDeltaY = 0.0D;
       }
     }

     if (ac != null) {

       if (ac.getSeatIdByEntity((Entity)entityClientPlayerMP) == 0 && !ac.isDestroyed()) {

         ac.lastRiderYaw = ((EntityPlayer)entityClientPlayerMP).field_70177_z;
         ac.prevLastRiderYaw = ((EntityPlayer)entityClientPlayerMP).field_70126_B;
         ac.lastRiderPitch = ((EntityPlayer)entityClientPlayerMP).field_70125_A;
         ac.prevLastRiderPitch = ((EntityPlayer)entityClientPlayerMP).field_70127_C;
       }

       ac.updateWeaponsRotation();
     }

     MCH_ViewEntityDummy mCH_ViewEntityDummy = MCH_ViewEntityDummy.getInstance(((EntityPlayer)entityClientPlayerMP).field_70170_p);
     if (mCH_ViewEntityDummy != null) {

       ((Entity)mCH_ViewEntityDummy).field_70177_z = ((EntityPlayer)entityClientPlayerMP).field_70177_z;
       ((Entity)mCH_ViewEntityDummy).field_70126_B = ((EntityPlayer)entityClientPlayerMP).field_70126_B;

       if (ac != null) {

         MCH_WeaponSet wi = ac.getCurrentWeapon((Entity)entityClientPlayerMP);
         if (wi != null && wi.getInfo() != null && (wi.getInfo()).fixCameraPitch)
         {
           ((Entity)mCH_ViewEntityDummy).field_70125_A = ((Entity)mCH_ViewEntityDummy).field_70127_C = 0.0F;
         }
       }
     }

     this; prevTick = partialTicks;
   }


   public void correctViewEntityDummy(Entity entity) {
     MCH_ViewEntityDummy mCH_ViewEntityDummy = MCH_ViewEntityDummy.getInstance(entity.field_70170_p);
     if (mCH_ViewEntityDummy != null)
     {
       if (((Entity)mCH_ViewEntityDummy).field_70177_z - ((Entity)mCH_ViewEntityDummy).field_70126_B > 180.0F) {

         ((Entity)mCH_ViewEntityDummy).field_70126_B += 360.0F;
       }
       else if (((Entity)mCH_ViewEntityDummy).field_70177_z - ((Entity)mCH_ViewEntityDummy).field_70126_B < -180.0F) {

         ((Entity)mCH_ViewEntityDummy).field_70126_B -= 360.0F;
       }
     }
   }



   public void onPlayerTickPre(EntityPlayer player) {
     if (player.field_70170_p.field_72995_K) {

       ItemStack currentItemstack = player.func_71045_bC();
       if (currentItemstack != null && currentItemstack.func_77973_b() instanceof mcheli.tool.MCH_ItemWrench)
       {
         if (player.func_71052_bv() > 0 && player.func_71011_bu() != currentItemstack) {

           int maxdm = currentItemstack.func_77958_k();
           int dm = currentItemstack.func_77960_j();
           if (dm <= maxdm && dm > 0)
           {
             player.func_71008_a(currentItemstack, player.func_71052_bv());
           }
         }
       }
     }
   }


   public void onPlayerTickPost(EntityPlayer player) {}


   public void onRenderTickPost(float partialTicks) {
     if (this.mc.field_71439_g != null) {

       MCH_ClientTickHandlerBase.applyRotLimit((Entity)this.mc.field_71439_g);


       MCH_ViewEntityDummy mCH_ViewEntityDummy = MCH_ViewEntityDummy.getInstance(this.mc.field_71439_g.field_70170_p);





       if (mCH_ViewEntityDummy != null) {

         ((Entity)mCH_ViewEntityDummy).field_70125_A = this.mc.field_71439_g.field_70125_A;
         ((Entity)mCH_ViewEntityDummy).field_70177_z = this.mc.field_71439_g.field_70177_z;
         ((Entity)mCH_ViewEntityDummy).field_70127_C = this.mc.field_71439_g.field_70127_C;
         ((Entity)mCH_ViewEntityDummy).field_70126_B = this.mc.field_71439_g.field_70126_B;
       }
     }

     if (this.mc.field_71462_r == null || this.mc.field_71462_r instanceof net.minecraft.client.gui.GuiChat || this.mc.field_71462_r.getClass().toString().indexOf("GuiDriveableController") >= 0) {



       for (MCH_Gui gui : this.guis) {

         if (drawGui(gui, partialTicks)) {
           break;
         }
       }


       drawGui((MCH_Gui)this.gui_Common, partialTicks);
       drawGui(this.gui_Wrench, partialTicks);
       drawGui(this.gui_EMarker, partialTicks);

       if (isDrawScoreboard)
       {
         MCH_GuiScoreboard.drawList(this.mc, this.mc.field_71466_p, false);
       }

       drawGui(this.gui_Title, partialTicks);
     }
   }


   public boolean drawGui(MCH_Gui gui, float partialTicks) {
     if (gui.isDrawGui((EntityPlayer)this.mc.field_71439_g)) {

       gui.func_73863_a(0, 0, partialTicks);
       return true;
     }
     return false;
   }
 }