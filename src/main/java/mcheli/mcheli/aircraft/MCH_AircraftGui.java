/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_PacketIndOpenScreen;
/*     */ import mcheli.aircraft.MCH_AircraftGuiContainer;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_PacketIndReload;
/*     */ import mcheli.command.MCH_PacketCommandSave;
/*     */ import mcheli.multiplay.MCH_PacketIndMultiplayCommand;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponSet;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_AircraftGui
/*     */   extends W_GuiContainer
/*     */ {
/*     */   private final EntityPlayer thePlayer;
/*     */   private final MCH_EntityAircraft aircraft;
/*     */   private int scaleFactor;
/*     */   private GuiButton buttonReload;
/*     */   private GuiButton buttonNext;
/*     */   private GuiButton buttonPrev;
/*     */   private GuiButton buttonInventory;
/*     */   private int currentWeaponId;
/*     */   private int reloadWait;
/*     */   private GuiTextField editCommand;
/*     */   public static final int BUTTON_RELOAD = 1;
/*     */   public static final int BUTTON_NEXT = 2;
/*     */   public static final int BUTTON_PREV = 3;
/*     */   public static final int BUTTON_CLOSE = 4;
/*     */   public static final int BUTTON_CONFIG = 5;
/*     */   public static final int BUTTON_INVENTORY = 6;
/*     */   
/*     */   public MCH_AircraftGui(EntityPlayer player, MCH_EntityAircraft ac) {
/*  47 */     super((Container)new MCH_AircraftGuiContainer(player, ac));
/*  48 */     this.aircraft = ac;
/*  49 */     this.thePlayer = player;
/*  50 */     this.field_146999_f = 210;
/*  51 */     this.field_147000_g = 236;
/*  52 */     this.buttonReload = null;
/*  53 */     this.currentWeaponId = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  58 */     super.func_73866_w_();
/*  59 */     this.field_146292_n.clear();
/*     */     
/*  61 */     this.buttonReload = new GuiButton(1, this.field_147003_i + 85, this.field_147009_r + 40, 50, 20, "Reload");
/*  62 */     this.buttonNext = new GuiButton(3, this.field_147003_i + 140, this.field_147009_r + 40, 20, 20, "<<");
/*  63 */     this.buttonPrev = new GuiButton(2, this.field_147003_i + 160, this.field_147009_r + 40, 20, 20, ">>");
/*  64 */     this.buttonReload.field_146124_l = canReload(this.thePlayer);
/*  65 */     this.buttonNext.field_146124_l = (this.aircraft.getWeaponNum() >= 2);
/*  66 */     this.buttonPrev.field_146124_l = (this.aircraft.getWeaponNum() >= 2);
/*     */     
/*  68 */     this.buttonInventory = new GuiButton(6, this.field_147003_i + 210 - 30 - 60, this.field_147009_r + 90, 80, 20, "Inventory");
/*  69 */     this.field_146292_n.add(new GuiButton(5, this.field_147003_i + 210 - 30 - 60, this.field_147009_r + 110, 80, 20, "MOD Options"));
/*  70 */     this.field_146292_n.add(new GuiButton(4, this.field_147003_i + 210 - 30 - 20, this.field_147009_r + 10, 40, 20, "Close"));
/*     */     
/*  72 */     this.field_146292_n.add(this.buttonReload);
/*  73 */     this.field_146292_n.add(this.buttonNext);
/*  74 */     this.field_146292_n.add(this.buttonPrev);
/*     */     
/*  76 */     if (this.aircraft != null && this.aircraft.func_70302_i_() > 0)
/*     */     {
/*  78 */       this.field_146292_n.add(this.buttonInventory);
/*     */     }
/*     */     
/*  81 */     this.editCommand = new GuiTextField(this.field_146289_q, this.field_147003_i + 25, this.field_147009_r + 215, 160, 15);
/*  82 */     this.editCommand.func_146180_a(this.aircraft.getCommand());
/*  83 */     this.editCommand.func_146203_f(512);
/*     */ 
/*     */     
/*  86 */     this.currentWeaponId = 0;
/*     */     
/*  88 */     this.reloadWait = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeScreen() {
/*  93 */     MCH_PacketCommandSave.send(this.editCommand.func_146179_b());
/*  94 */     this.field_146297_k.field_71439_g.func_71053_j();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReload(EntityPlayer player) {
/*  99 */     return this.aircraft.canPlayerSupplyAmmo(player, this.currentWeaponId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/* 104 */     super.func_73876_c();
/* 105 */     if (this.reloadWait > 0) {
/*     */       
/* 107 */       this.reloadWait--;
/* 108 */       if (this.reloadWait == 0) {
/*     */         
/* 110 */         this.buttonReload.field_146124_l = canReload(this.thePlayer);
/* 111 */         this.reloadWait = 20;
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     this.editCommand.func_146178_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
/* 121 */     this.editCommand.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
/* 122 */     super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 127 */     super.func_146281_b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton button) {
/* 132 */     super.func_146284_a(button);
/*     */     
/* 134 */     if (!button.field_146124_l)
/*     */       return; 
/* 136 */     switch (button.field_146127_k) {
/*     */       
/*     */       case 4:
/* 139 */         closeScreen();
/*     */         break;
/*     */       case 1:
/* 142 */         this.buttonReload.field_146124_l = canReload(this.thePlayer);
/* 143 */         if (this.buttonReload.field_146124_l) {
/*     */           
/* 145 */           MCH_PacketIndReload.send(this.aircraft, this.currentWeaponId);
/* 146 */           this.aircraft.supplyAmmo(this.currentWeaponId);
/* 147 */           this.reloadWait = 3;
/* 148 */           this.buttonReload.field_146124_l = false;
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 152 */         this.currentWeaponId++;
/* 153 */         if (this.currentWeaponId >= this.aircraft.getWeaponNum())
/*     */         {
/* 155 */           this.currentWeaponId = 0;
/*     */         }
/* 157 */         this.buttonReload.field_146124_l = canReload(this.thePlayer);
/*     */         break;
/*     */       case 3:
/* 160 */         this.currentWeaponId--;
/* 161 */         if (this.currentWeaponId < 0)
/*     */         {
/* 163 */           this.currentWeaponId = this.aircraft.getWeaponNum() - 1;
/*     */         }
/* 165 */         this.buttonReload.field_146124_l = canReload(this.thePlayer);
/*     */         break;
/*     */       case 5:
/* 168 */         MCH_PacketIndOpenScreen.send(2);
/*     */         break;
/*     */       case 6:
/* 171 */         MCH_PacketIndOpenScreen.send(3);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {
/* 178 */     super.func_146979_b(par1, par2);
/* 179 */     MCH_EntityAircraft ac = this.aircraft;
/* 180 */     drawString(ac.getGuiInventory().func_145825_b(), 10, 10, 16777215);
/*     */     
/* 182 */     if (this.aircraft.getNumEjectionSeat() > 0)
/*     */     {
/* 184 */       drawString("Parachute", 9, 95, 16777215);
/*     */     }
/*     */     
/* 187 */     if (this.aircraft.getWeaponNum() > 0) {
/*     */       
/* 189 */       MCH_WeaponSet ws = this.aircraft.getWeapon(this.currentWeaponId);
/* 190 */       if (ws != null && !(ws.getFirstWeapon() instanceof mcheli.weapon.MCH_WeaponDummy)) {
/*     */         
/* 192 */         drawString(ws.getName(), 79, 30, 16777215);
/*     */         
/* 194 */         int rest = ws.getRestAllAmmoNum() + ws.getAmmoNum();
/* 195 */         int color = (rest == 0) ? 16711680 : ((rest == ws.getAllAmmoNum()) ? 2675784 : 16777215);
/* 196 */         String s = String.format("%4d/%4d", new Object[] { Integer.valueOf(rest), Integer.valueOf(ws.getAllAmmoNum()) });
/* 197 */         drawString(s, 145, 70, color);
/*     */         
/* 199 */         int itemPosX = 90;
/* 200 */         for (MCH_WeaponInfo.RoundItem r : (ws.getInfo()).roundItems) {
/*     */           
/* 202 */           drawString("" + r.num, itemPosX, 80, 16777215);
/* 203 */           itemPosX += 20;
/*     */         } 
/*     */         
/* 206 */         itemPosX = 85;
/* 207 */         for (MCH_WeaponInfo.RoundItem r : (ws.getInfo()).roundItems)
/*     */         {
/* 209 */           drawItemStack(r.itemStack, itemPosX, 62);
/* 210 */           itemPosX += 20;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 216 */       drawString("None", 79, 45, 16777215);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char c, int code) {
/* 222 */     if (code == 1) {
/*     */       
/* 224 */       closeScreen();
/*     */     }
/* 226 */     else if (code == 28) {
/*     */       
/* 228 */       String s = this.editCommand.func_146179_b().trim();
/* 229 */       if (s.startsWith("/"))
/*     */       {
/* 231 */         s = s.substring(1);
/*     */       }
/* 233 */       if (!s.isEmpty())
/*     */       {
/* 235 */         MCH_PacketIndMultiplayCommand.send(768, s);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 240 */       this.editCommand.func_146201_a(c, code);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float var1, int var2, int var3) {
/* 246 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 247 */     this.scaleFactor = w_ScaledResolution.func_78325_e();
/*     */     
/* 249 */     W_McClient.MOD_bindTexture("textures/gui/gui.png");
/* 250 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 251 */     int x = (this.field_146294_l - this.field_146999_f) / 2;
/* 252 */     int y = (this.field_146295_m - this.field_147000_g) / 2;
/* 253 */     func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 255 */     for (int i = 0; i < this.aircraft.getNumEjectionSeat(); i++)
/*     */     {
/* 257 */       func_73729_b(x + 10 + 18 * i - 1, y + 105 - 1, 215, 55, 18, 18);
/*     */     }
/*     */     
/* 260 */     int ff = (int)(this.aircraft.getFuelP() * 50.0F);
/* 261 */     if (ff >= 99) ff = 100; 
/* 262 */     func_73729_b(x + 57, y + 30 + 50 - ff, 215, 0, 12, ff);
/*     */     
/* 264 */     ff = (int)((this.aircraft.getFuelP() * 100.0F) + 0.5D);
/* 265 */     int color = (ff > 20) ? -14101432 : 16711680;
/* 266 */     drawString(String.format("%3d", new Object[] { Integer.valueOf(ff) }) + "%", x + 30, y + 65, color);
/*     */     
/* 268 */     this.editCommand.func_146194_f();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */