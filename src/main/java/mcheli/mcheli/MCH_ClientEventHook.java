 package mcheli.mcheli;
 import java.util.ArrayList;
 import java.util.List;
 import mcheli.mcheli.MCH_ClientCommonTickHandler;
 import mcheli.mcheli.MCH_ClientTickHandlerBase;
 import mcheli.mcheli.MCH_Config;
 import mcheli.mcheli.MCH_Lib;
 import mcheli.mcheli.MCH_TextureManagerDummy;
 import mcheli.mcheli.MCH_ViewEntityDummy;
 import mcheli.mcheli.aircraft.MCH_EntityAircraft;
 import mcheli.mcheli.aircraft.MCH_RenderAircraft;
 import mcheli.mcheli.lweapon.MCH_ClientLightWeaponTickHandler;
 import mcheli.mcheli.multiplay.MCH_GuiTargetMarker;
 import mcheli.mcheli.particles.MCH_ParticlesUtil;
 import mcheli.mcheli.tool.rangefinder.MCH_ItemRangeFinder;
 import mcheli.mcheli.wrapper.W_ClientEventHook;
 import mcheli.mcheli.wrapper.W_Reflection;
 import net.minecraft.client.Minecraft;
 import net.minecraft.client.renderer.OpenGlHelper;
 import net.minecraft.client.renderer.Tessellator;
 import net.minecraft.client.renderer.entity.Render;
 import net.minecraft.client.renderer.entity.RenderManager;
 import net.minecraft.entity.Entity;
 import net.minecraft.entity.EntityLivingBase;
 import net.minecraft.util.ResourceLocation;
 import net.minecraftforge.client.event.MouseEvent;
 import net.minecraftforge.client.event.RenderLivingEvent;
 import net.minecraftforge.client.event.RenderPlayerEvent;
 import net.minecraftforge.event.entity.EntityJoinWorldEvent;
 import net.minecraftforge.event.world.WorldEvent;
 import org.lwjgl.opengl.GL11;

 //TODO: half mess

 public class MCH_ClientEventHook extends W_ClientEventHook {
   MCH_TextureManagerDummy dummyTextureManager = null;

   public static List<MCH_EntityAircraft> haveSearchLightAircraft = new ArrayList<MCH_EntityAircraft>();


   public void renderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre event) {
     if (MCH_Config.DisableRenderLivingSpecials.prmBool) {

       MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)(Minecraft.func_71410_x()).field_71439_g);

       if (ac != null && ac.isMountedEntity((Entity)event.entity)) {

         event.setCanceled(true);
         return;
       }
     }
   }

   public void renderLivingEventSpecialsPost(RenderLivingEvent.Specials.Post event) {}

   private static final ResourceLocation ir_strobe = new ResourceLocation("mcheli", "textures/ir_strobe.png");

   private void renderIRStrobe(EntityLivingBase entity, RenderLivingEvent.Specials.Post event) {
     int cm = MCH_ClientCommonTickHandler.cameraMode;
     if (cm == 0)
       return;
     int ticks = entity.field_70173_aa % 20;
     if (ticks >= 4)
       return;  float alpha = (ticks == 2 || ticks == 1) ? 1.0F : 0.5F;

     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
     if (entityClientPlayerMP == null)
       return;
     if (!entityClientPlayerMP.func_142014_c(entity)) {
       return;
     }

     int j = 240;
     int k = 240;
     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);

     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     float f1 = 0.080000006F;
     GL11.glPushMatrix();
     GL11.glTranslated(event.x, event.y + (float)(entity.field_70131_O * 0.75D), event.z);
     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
     GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
     GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F, 0.0F, 0.0F);
     GL11.glScalef(-f1, -f1, f1);

     GL11.glEnable(3042);
     OpenGlHelper.func_148821_a(770, 771, 1, 0);

     GL11.glEnable(3553);

     RenderManager.field_78727_a.field_78724_e.func_110577_a(ir_strobe);
     GL11.glAlphaFunc(516, 0.003921569F);

     Tessellator tessellator = Tessellator.field_78398_a;
     tessellator.func_78382_b();
     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, alpha * ((cm == 1) ? 0.9F : 0.5F));
     int i = (int)Math.max(entity.field_70130_N, entity.field_70131_O) * 20;
     tessellator.func_78374_a(-i, -i, 0.1D, 0.0D, 0.0D);
     tessellator.func_78374_a(-i, i, 0.1D, 0.0D, 1.0D);
     tessellator.func_78374_a(i, i, 0.1D, 1.0D, 1.0D);
     tessellator.func_78374_a(i, -i, 0.1D, 1.0D, 0.0D);
     tessellator.func_78381_a();

     GL11.glEnable(2896);
     GL11.glPopMatrix();
   }


   public void mouseEvent(MouseEvent event) {
     if (MCH_ClientTickHandlerBase.updateMouseWheel(event.dwheel))
     {
       event.setCanceled(true);
     }
   }

   private static boolean cancelRender = true;

   public static void setCancelRender(boolean cancel) {
     cancelRender = cancel;
   }

   public void renderLivingEventPre(RenderLivingEvent.Pre event) {
     for (MCH_EntityAircraft ac : haveSearchLightAircraft)
     {
       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, ac.getSearchLightValue((Entity)event.entity), 240.0F);
     }

     if (MCH_Config.EnableModEntityRender.prmBool)
     {
       if (cancelRender)
       {
         if (event.entity.field_70154_o instanceof MCH_EntityAircraft || event.entity.field_70154_o instanceof mcheli.aircraft.MCH_EntitySeat) {


           event.setCanceled(true);

           return;
         }
       }
     }
     if (MCH_Config.EnableReplaceTextureManager.prmBool) {
       RenderManager rm = W_Reflection.getRenderManager((Render)event.renderer);
       if (rm != null && !(rm.field_78724_e instanceof MCH_TextureManagerDummy)) {

         if (this.dummyTextureManager == null)
         {
           this.dummyTextureManager = new MCH_TextureManagerDummy(rm.field_78724_e);
         }
         rm.field_78724_e = (TextureManager)this.dummyTextureManager;
       }
     }
   }

   public void renderLivingEventPost(RenderLivingEvent.Post event) {
     MCH_RenderAircraft.renderEntityMarker((Entity)event.entity);
     MCH_GuiTargetMarker.addMarkEntityPos(2, (Entity)event.entity, event.x, event.y + event.entity.field_70131_O + 0.5D, event.z);
     MCH_ClientLightWeaponTickHandler.markEntity((Entity)event.entity, event.x, event.y + (event.entity.field_70131_O / 2.0F), event.z);
   }

   public void renderPlayerPre(RenderPlayerEvent.Pre event) {
     if (event.entity == null) {
       return;
     }

     if (event.entity.field_70154_o instanceof MCH_EntityAircraft) {

       MCH_EntityAircraft v = (MCH_EntityAircraft)event.entity.field_70154_o;
       if (v.getAcInfo() != null && (v.getAcInfo()).hideEntity) {

         event.setCanceled(true);
         return;
       }
     }
   }

   public void renderPlayerPost(RenderPlayerEvent.Post event) {}

   public void worldEventUnload(WorldEvent.Unload event) {
     MCH_ViewEntityDummy.onUnloadWorld();
   }

   public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
     if (event.entity.func_70028_i(MCH_Lib.getClientPlayer())) {

       MCH_Lib.DbgLog(true, "MCH_ClientEventHook.entityJoinWorldEvent : " + event.entity, new Object[0]);
       MCH_ItemRangeFinder.mode = Minecraft.func_71410_x().func_71356_B() ? 1 : 0;

       MCH_ParticlesUtil.clearMarkPoint();
     }
   }
 }
