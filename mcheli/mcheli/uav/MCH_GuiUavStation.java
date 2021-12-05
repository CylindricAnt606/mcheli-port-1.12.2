/*     */ package mcheli.mcheli.uav;
/*     */ 
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfo;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.plane.MCP_PlaneInfo;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_TankInfo;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.uav.MCH_ContainerUavStation;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.uav.MCH_UavPacketStatus;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MCH_GuiUavStation extends W_GuiContainer {
/*     */   final MCH_EntityUavStation uavStation;
/*  26 */   private final int BUTTON_ID_CONTINUE = 256;
/*     */   static final int BX = 20;
/*     */   static final int BY = 22;
/*     */   private GuiButton buttonContinue;
/*     */   
/*     */   public MCH_GuiUavStation(InventoryPlayer inventoryPlayer, MCH_EntityUavStation uavStation) {
/*  32 */     super((Container)new MCH_ContainerUavStation(inventoryPlayer, uavStation));
/*  33 */     this.uavStation = uavStation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int param1, int param2) {
/*     */     MCH_TankInfo mCH_TankInfo;
/*  39 */     if (this.uavStation == null)
/*     */       return; 
/*  41 */     ItemStack item = this.uavStation.func_70301_a(0);
/*  42 */     MCH_AircraftInfo info = null;
/*  43 */     if (item != null && item.func_77973_b() instanceof mcheli.plane.MCP_ItemPlane)
/*     */     {
/*  45 */       MCP_PlaneInfo mCP_PlaneInfo = MCP_PlaneInfoManager.getFromItem(item.func_77973_b());
/*     */     }
/*  47 */     if (item != null && item.func_77973_b() instanceof mcheli.helicopter.MCH_ItemHeli)
/*     */     {
/*  49 */       MCH_HeliInfo mCH_HeliInfo = MCH_HeliInfoManager.getFromItem(item.func_77973_b());
/*     */     }
/*  51 */     if (item != null && item.func_77973_b() instanceof mcheli.tank.MCH_ItemTank)
/*     */     {
/*  53 */       mCH_TankInfo = MCH_TankInfoManager.getFromItem(item.func_77973_b());
/*     */     }
/*     */     
/*  56 */     if (item == null || (item != null && mCH_TankInfo != null && ((MCH_AircraftInfo)mCH_TankInfo).isUAV)) {
/*     */       
/*  58 */       if (this.uavStation.getKind() <= 1)
/*     */       {
/*  60 */         drawString("UAV Station", 8, 6, 16777215);
/*     */ 
/*     */       
/*     */       }
/*  64 */       else if (item == null || ((MCH_AircraftInfo)mCH_TankInfo).isSmallUAV)
/*     */       {
/*  66 */         drawString("UAV Controller", 8, 6, 16777215);
/*     */       }
/*     */       else
/*     */       {
/*  70 */         drawString("Small UAV only", 8, 6, 16711680);
/*     */       }
/*     */     
/*     */     }
/*  74 */     else if (item != null) {
/*     */       
/*  76 */       drawString("Not UAV", 8, 6, 16711680);
/*     */     } 
/*     */     
/*  79 */     drawString(StatCollector.func_74838_a("container.inventory"), 8, this.field_147000_g - 96 + 2, 16777215);
/*     */     
/*  81 */     drawString(String.format("X.%+2d", new Object[] { Integer.valueOf(this.uavStation.posUavX) }), 58, 15, 16777215);
/*  82 */     drawString(String.format("Y.%+2d", new Object[] { Integer.valueOf(this.uavStation.posUavY) }), 58, 37, 16777215);
/*  83 */     drawString(String.format("Z.%+2d", new Object[] { Integer.valueOf(this.uavStation.posUavZ) }), 58, 59, 16777215);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/*  90 */     W_McClient.MOD_bindTexture("textures/gui/uav_station.png");
/*  91 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  92 */     int x = (this.field_146294_l - this.field_146999_f) / 2;
/*  93 */     int y = (this.field_146295_m - this.field_147000_g) / 2;
/*  94 */     func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton btn) {
/*  98 */     if (btn != null && btn.field_146124_l)
/*     */     {
/* 100 */       if (btn.field_146127_k == 256) {
/*     */         
/* 102 */         if (this.uavStation != null && !this.uavStation.field_70128_L && this.uavStation.getLastControlAircraft() != null && !(this.uavStation.getLastControlAircraft()).field_70128_L) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 107 */           MCH_UavPacketStatus data = new MCH_UavPacketStatus();
/* 108 */           data.posUavX = (byte)this.uavStation.posUavX;
/* 109 */           data.posUavY = (byte)this.uavStation.posUavY;
/* 110 */           data.posUavZ = (byte)this.uavStation.posUavZ;
/* 111 */           data.continueControl = true;
/* 112 */           W_Network.sendToServer((W_PacketBase)data);
/*     */         } 
/*     */         
/* 115 */         this.buttonContinue.field_146124_l = false;
/*     */       }
/*     */       else {
/*     */         
/* 119 */         int[] pos = { this.uavStation.posUavX, this.uavStation.posUavY, this.uavStation.posUavZ };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 125 */         int i = btn.field_146127_k >> 4 & 0xF;
/* 126 */         int j = (btn.field_146127_k & 0xF) - 1;
/* 127 */         int[] BTN = { -10, -1, 1, 10 };
/* 128 */         pos[i] = pos[i] + BTN[j];
/*     */         
/* 130 */         if (pos[i] < -50) pos[i] = -50; 
/* 131 */         if (pos[i] > 50) pos[i] = 50;
/*     */         
/* 133 */         if (this.uavStation.posUavX != pos[0] || this.uavStation.posUavY != pos[1] || this.uavStation.posUavZ != pos[2]) {
/*     */ 
/*     */ 
/*     */           
/* 137 */           MCH_UavPacketStatus data = new MCH_UavPacketStatus();
/* 138 */           data.posUavX = (byte)pos[0];
/* 139 */           data.posUavY = (byte)pos[1];
/* 140 */           data.posUavZ = (byte)pos[2];
/* 141 */           W_Network.sendToServer((W_PacketBase)data);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/* 149 */     super.func_73866_w_();
/* 150 */     this.field_146292_n.clear();
/* 151 */     int x = this.field_146294_l / 2 - 5;
/* 152 */     int y = this.field_146295_m / 2 - 76;
/* 153 */     String[] BTN = { "-10", "-1", "+1", "+10" };
/* 154 */     for (int row = 0; row < 3; row++) {
/*     */       
/* 156 */       for (int col = 0; col < 4; col++) {
/*     */ 
/*     */         
/* 159 */         int id = row << 4 | col + 1;
/* 160 */         this.field_146292_n.add(new GuiButton(id, x + col * 20, y + row * 22, 20, 20, BTN[col]));
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     this.buttonContinue = new GuiButton(256, x - 80 + 3, y + 44, 50, 20, "Continue");
/* 165 */     this.buttonContinue.field_146124_l = false;
/* 166 */     if (this.uavStation != null && !this.uavStation.field_70128_L)
/*     */     {
/* 168 */       if (this.uavStation.getAndSearchLastControlAircraft() != null)
/*     */       {
/* 170 */         this.buttonContinue.field_146124_l = true;
/*     */       }
/*     */     }
/* 173 */     this.field_146292_n.add(this.buttonContinue);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mchel\\uav\MCH_GuiUavStation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */