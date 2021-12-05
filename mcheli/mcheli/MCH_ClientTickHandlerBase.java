/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public abstract class MCH_ClientTickHandlerBase
/*     */ {
/*     */   protected Minecraft mc;
/*  20 */   public static float playerRotMinPitch = -90.0F;
/*  21 */   public static float playerRotMaxPitch = 90.0F;
/*     */   public static boolean playerRotLimitPitch = false;
/*  23 */   public static float playerRotMinYaw = -180.0F;
/*  24 */   public static float playerRotMaxYaw = 180.0F;
/*     */   
/*     */   public static boolean playerRotLimitYaw = false;
/*  27 */   private static int mouseWheel = 0;
/*     */ 
/*     */   
/*     */   public abstract void updateKeybind(MCH_Config paramMCH_Config);
/*     */   
/*     */   public static void setRotLimitPitch(float min, float max, Entity player) {
/*  33 */     playerRotMinPitch = min;
/*  34 */     playerRotMaxPitch = max;
/*  35 */     playerRotLimitPitch = true;
/*  36 */     if (player != null)
/*     */     {
/*  38 */       player.field_70125_A = MCH_Lib.RNG(player.field_70125_A, playerRotMinPitch, playerRotMaxPitch);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setRotLimitYaw(float min, float max, Entity e) {
/*  43 */     playerRotMinYaw = min;
/*  44 */     playerRotMaxYaw = max;
/*  45 */     playerRotLimitYaw = true;
/*  46 */     if (e != null)
/*     */     {
/*  48 */       if (e.field_70125_A < playerRotMinPitch) {
/*     */         
/*  50 */         e.field_70125_A = playerRotMinPitch;
/*  51 */         e.field_70127_C = playerRotMinPitch;
/*     */       }
/*  53 */       else if (e.field_70125_A > playerRotMaxPitch) {
/*     */         
/*  55 */         e.field_70125_A = playerRotMaxPitch;
/*  56 */         e.field_70127_C = playerRotMaxPitch;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void initRotLimit() {
/*  62 */     playerRotMinPitch = -90.0F;
/*  63 */     playerRotMaxPitch = 90.0F;
/*  64 */     playerRotLimitYaw = false;
/*  65 */     playerRotMinYaw = -180.0F;
/*  66 */     playerRotMaxYaw = 180.0F;
/*  67 */     playerRotLimitYaw = false;
/*     */   }
/*     */   
/*     */   public static void applyRotLimit(Entity e) {
/*  71 */     if (e != null) {
/*     */       
/*  73 */       if (playerRotLimitPitch)
/*     */       {
/*  75 */         if (e.field_70125_A < playerRotMinPitch) {
/*     */           
/*  77 */           e.field_70125_A = playerRotMinPitch;
/*  78 */           e.field_70127_C = playerRotMinPitch;
/*     */         }
/*  80 */         else if (e.field_70125_A > playerRotMaxPitch) {
/*     */           
/*  82 */           e.field_70125_A = playerRotMaxPitch;
/*  83 */           e.field_70127_C = playerRotMaxPitch;
/*     */         } 
/*     */       }
/*     */       
/*  87 */       if (playerRotLimitYaw);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_ClientTickHandlerBase(Minecraft minecraft) {
/*  96 */     this.mc = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean updateMouseWheel(int wheel) {
/* 101 */     boolean cancelEvent = false;
/* 102 */     if (wheel != 0) if (MCH_Config.SwitchWeaponWithMouseWheel.prmBool) {
/*     */         
/* 104 */         setMouseWheel(0);
/* 105 */         EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/* 106 */         if (entityClientPlayerMP != null) {
/*     */           
/* 108 */           MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)entityClientPlayerMP);
/* 109 */           if (ac != null) {
/*     */             
/* 111 */             int cwid = ac.getWeaponIDBySeatID(ac.getSeatIdByEntity((Entity)entityClientPlayerMP));
/* 112 */             int nwid = ac.getNextWeaponID((Entity)entityClientPlayerMP, 1);
/* 113 */             if (cwid != nwid) {
/*     */               
/* 115 */               setMouseWheel(wheel);
/* 116 */               cancelEvent = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }  
/* 121 */     return cancelEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void onTick(boolean paramBoolean);
/*     */   
/*     */   public static void playSoundOK() {
/* 128 */     W_McClient.DEF_playSoundFX("random.click", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playSoundNG() {
/* 132 */     W_McClient.MOD_playSoundFX("ng", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playSound(String name) {
/* 136 */     W_McClient.MOD_playSoundFX(name, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playSound(String name, float vol, float pitch) {
/* 140 */     W_McClient.MOD_playSoundFX(name, vol, pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMouseWheel() {
/* 145 */     return mouseWheel;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setMouseWheel(int mouseWheel) {
/* 150 */     mcheli.MCH_ClientTickHandlerBase.mouseWheel = mouseWheel;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ClientTickHandlerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */