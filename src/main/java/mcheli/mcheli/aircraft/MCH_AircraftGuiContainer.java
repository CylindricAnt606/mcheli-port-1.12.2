/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.mcheli.MCH_Lib;
/*     */ import mcheli.mcheli.aircraft.MCH_AircraftInventory;
/*     */ import mcheli.mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.mcheli.uav.MCH_EntityUavStation;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class MCH_AircraftGuiContainer extends Container {
/*     */   public final EntityPlayer player;
/*     */   public final MCH_EntityAircraft aircraft;
/*     */   
/*     */   public MCH_AircraftGuiContainer(EntityPlayer player, MCH_EntityAircraft ac) {
/*  18 */     this.player = player;
/*  19 */     this.aircraft = ac;
/*     */     
/*  21 */     MCH_AircraftInventory iv = this.aircraft.getGuiInventory();
/*  22 */     iv.getClass(); func_75146_a(new Slot((IInventory)iv, 0, 10, 30));
/*  23 */     iv.getClass(); func_75146_a(new Slot((IInventory)iv, 1, 10, 48));
/*  24 */     iv.getClass(); func_75146_a(new Slot((IInventory)iv, 2, 10, 66));
/*     */     
/*  26 */     int num = this.aircraft.getNumEjectionSeat();
/*  27 */     for (int i = 0; i < num; i++) {
/*     */       
/*  29 */       iv.getClass(); func_75146_a(new Slot((IInventory)iv, 3 + i, 10 + 18 * i, 105));
/*     */     } 
/*     */ 
/*     */     
/*  33 */     for (int y = 0; y < 3; y++) {
/*     */       
/*  35 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  37 */         func_75146_a(new Slot((IInventory)player.field_71071_by, 9 + j + y * 9, 25 + j * 18, 135 + y * 18));
/*     */       }
/*     */     } 
/*     */     
/*  42 */     for (int x = 0; x < 9; x++)
/*     */     {
/*  44 */       func_75146_a(new Slot((IInventory)player.field_71071_by, x, 25 + x * 18, 195));
/*     */     }
/*     */   }
/*     */  
/*     */   public int getInventoryStartIndex() {
/*  50 */     if (this.aircraft == null) return 3; 
/*  51 */     return 3 + this.aircraft.getNumEjectionSeat();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  57 */     super.func_75142_b();
/*     */   }
/*     */ 
/*     */  
/*     */   public boolean func_75145_c(EntityPlayer player) {
/*  63 */     if (this.aircraft.getGuiInventory().func_70300_a(player))
/*     */     {
/*  65 */       return true;
/*     */     }
/*     */     
/*  68 */     if (this.aircraft.isUAV()) {
/*     */       
/*  70 */       MCH_EntityUavStation us = this.aircraft.getUavStation();
/*  71 */       if (us != null) {
/*     */         
/*  73 */         double x = us.field_70165_t + us.posUavX;
/*  74 */         double z = us.field_70161_v + us.posUavZ;
/*  75 */         if (this.aircraft.field_70165_t < x + 10.0D && this.aircraft.field_70165_t > x - 10.0D && this.aircraft.field_70161_v < z + 10.0D && this.aircraft.field_70161_v > z - 10.0D)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*  80 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */  
/*     */   public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
/*  91 */     MCH_AircraftInventory iv = this.aircraft.getGuiInventory();
/*  92 */     Slot slot = this.field_75151_b.get(slotIndex);
/*  93 */     if (slot == null) return null;
/*     */     
/*  95 */     ItemStack itemStack = slot.func_75211_c();
/*  96 */     MCH_Lib.DbgLog(player.field_70170_p, "transferStackInSlot : %d :" + itemStack, new Object[] { Integer.valueOf(slotIndex) });
/*  97 */     if (itemStack == null) return null;
/*     */ 
/*     */     
/* 100 */     if (slotIndex < getInventoryStartIndex()) {
/*     */       
/* 102 */       for (int i = getInventoryStartIndex(); i < this.field_75151_b.size(); i++)
/*     */       {
/* 104 */         Slot playerSlot = this.field_75151_b.get(i);
/* 105 */         if (playerSlot.func_75211_c() == null)
/*     */         {
/* 107 */           playerSlot.func_75215_d(itemStack);
/* 108 */           slot.func_75215_d(null);
/* 109 */           return itemStack;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 116 */     else if (itemStack.func_77973_b() instanceof mcheli.aircraft.MCH_ItemFuel) {
/*     */       
/* 118 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 120 */         if (iv.getFuelSlotItemStack(i) == null)
/*     */         {
/* 122 */           iv.getClass(); iv.func_70299_a(0 + i, itemStack);
/* 123 */           slot.func_75215_d(null);
/* 124 */           return itemStack;
/*     */         }
/*     */       
/*     */       } 
/* 128 */     } else if (itemStack.func_77973_b() instanceof mcheli.parachute.MCH_ItemParachute) {
/*     */       
/* 130 */       int num = this.aircraft.getNumEjectionSeat();
/* 131 */       for (int i = 0; i < num; i++) {
/*     */         
/* 133 */         if (iv.getParachuteSlotItemStack(i) == null) {
/*     */           
/* 135 */           iv.getClass(); iv.func_70299_a(3 + i, itemStack);
/* 136 */           slot.func_75215_d(null);
/* 137 */           return itemStack;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/* 147 */     super.func_75134_a(player);
/*     */     
/* 149 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 151 */       MCH_AircraftInventory iv = this.aircraft.getGuiInventory();
/*     */       int i;
/* 153 */       for (i = 0; i < 3; i++) {
/*     */         
/* 155 */         ItemStack is = iv.getFuelSlotItemStack(i);
/* 156 */         if (is != null && !(is.func_77973_b() instanceof mcheli.aircraft.MCH_ItemFuel)) {
/*     */           
/* 158 */           iv.getClass(); dropPlayerItem(player, 0 + i);
/*     */         } 
/*     */       } 
/*     */       
/* 162 */       for (i = 0; i < 2; i++) {
/*     */         
/* 164 */         ItemStack is = iv.getParachuteSlotItemStack(i);
/* 165 */         if (is != null && !(is.func_77973_b() instanceof mcheli.parachute.MCH_ItemParachute)) {
/*     */           
/* 167 */           iv.getClass(); dropPlayerItem(player, 3 + i);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dropPlayerItem(EntityPlayer player, int slotID) {
/* 174 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 176 */       ItemStack itemstack = this.aircraft.getGuiInventory().func_70304_b(slotID);
/*     */       
/* 178 */       if (itemstack != null)
/*     */       {
/* 180 */         player.func_71019_a(itemstack, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }
