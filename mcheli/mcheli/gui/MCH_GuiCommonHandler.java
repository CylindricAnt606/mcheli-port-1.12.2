/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_AircraftGui;
/*     */ import mcheli.aircraft.MCH_AircraftGuiContainer;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.block.MCH_DraftingTableGui;
/*     */ import mcheli.block.MCH_DraftingTableGuiContainer;
/*     */ import mcheli.gui.MCH_ConfigGui;
/*     */ import mcheli.gui.MCH_ConfigGuiContainer;
/*     */ import mcheli.multiplay.MCH_ContainerScoreboard;
/*     */ import mcheli.multiplay.MCH_GuiScoreboard;
/*     */ import mcheli.uav.MCH_ContainerUavStation;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.uav.MCH_GuiUavStation;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_GuiCommonHandler
/*     */   implements IGuiHandler
/*     */ {
/*     */   public static final int GUIID_UAV_STATION = 0;
/*     */   public static final int GUIID_AIRCRAFT = 1;
/*     */   public static final int GUIID_CONFG = 2;
/*     */   public static final int GUIID_INVENTORY = 3;
/*     */   public static final int GUIID_DRAFTING = 4;
/*     */   public static final int GUIID_MULTI_MNG = 5;
/*     */   
/*     */   public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/*     */     MCH_EntityAircraft ac;
/*  37 */     MCH_Lib.DbgLog(world, "MCH_GuiCommonHandler.getServerGuiElement ID=%d (%d, %d, %d)", new Object[] { Integer.valueOf(id), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/*     */     
/*  39 */     switch (id) {
/*     */       
/*     */       case 0:
/*  42 */         if (player.field_70154_o instanceof MCH_EntityUavStation)
/*     */         {
/*  44 */           return new MCH_ContainerUavStation(player.field_71071_by, (MCH_EntityUavStation)player.field_70154_o);
/*     */         }
/*     */         break;
/*     */       
/*     */       case 1:
/*  49 */         ac = null;
/*  50 */         if (player.field_70154_o instanceof MCH_EntityAircraft) {
/*     */           
/*  52 */           ac = (MCH_EntityAircraft)player.field_70154_o;
/*     */         }
/*  54 */         else if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*     */           
/*  56 */           ac = ((MCH_EntityUavStation)player.field_70154_o).getControlAircract();
/*     */         } 
/*     */         
/*  59 */         if (ac != null)
/*     */         {
/*  61 */           return new MCH_AircraftGuiContainer(player, ac);
/*     */         }
/*     */         break;
/*     */       
/*     */       case 2:
/*  66 */         return new MCH_ConfigGuiContainer(player);
/*     */       
/*     */       case 4:
/*  69 */         return new MCH_DraftingTableGuiContainer(player, x, y, z);
/*     */       
/*     */       case 5:
/*  72 */         if (MinecraftServer.func_71276_C().func_71264_H()) { if (MCH_Config.DebugLog)
/*     */           {
/*  74 */             return new MCH_ContainerScoreboard(player); }  break; }  return new MCH_ContainerScoreboard(player);
/*     */     } 
/*     */ 
/*     */     
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/*     */     MCH_EntityAircraft ac;
/*  85 */     MCH_Lib.DbgLog(world, "MCH_GuiCommonHandler.getClientGuiElement ID=%d (%d, %d, %d)", new Object[] { Integer.valueOf(id), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/*     */     
/*  87 */     switch (id) {
/*     */       
/*     */       case 0:
/*  90 */         if (player.field_70154_o instanceof MCH_EntityUavStation)
/*     */         {
/*  92 */           return new MCH_GuiUavStation(player.field_71071_by, (MCH_EntityUavStation)player.field_70154_o);
/*     */         }
/*     */         break;
/*     */       
/*     */       case 1:
/*  97 */         ac = null;
/*  98 */         if (player.field_70154_o instanceof MCH_EntityAircraft) {
/*     */           
/* 100 */           ac = (MCH_EntityAircraft)player.field_70154_o;
/*     */         }
/* 102 */         else if (player.field_70154_o instanceof MCH_EntityUavStation) {
/*     */           
/* 104 */           ac = ((MCH_EntityUavStation)player.field_70154_o).getControlAircract();
/*     */         } 
/*     */         
/* 107 */         if (ac != null)
/*     */         {
/* 109 */           return new MCH_AircraftGui(player, ac);
/*     */         }
/*     */         break;
/*     */       
/*     */       case 2:
/* 114 */         return new MCH_ConfigGui(player);
/*     */       
/*     */       case 4:
/* 117 */         return new MCH_DraftingTableGui(player, x, y, z);
/*     */       
/*     */       case 5:
/* 120 */         return new MCH_GuiScoreboard(player);
/*     */     } 
/*     */     
/* 123 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiCommonHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */