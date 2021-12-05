/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_NBTTag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class MCH_AircraftInventory
/*     */   implements IInventory {
/*  17 */   public final int SLOT_FUEL0 = 0;
/*  18 */   public final int SLOT_FUEL1 = 1;
/*  19 */   public final int SLOT_FUEL2 = 2;
/*  20 */   public final int SLOT_PARACHUTE0 = 3;
/*  21 */   public final int SLOT_PARACHUTE1 = 4;
/*     */   
/*     */   private ItemStack[] containerItems;
/*     */   
/*     */   final MCH_EntityAircraft aircraft;
/*     */   
/*     */   public MCH_AircraftInventory(MCH_EntityAircraft ac) {
/*  28 */     this.containerItems = new ItemStack[func_70302_i_()];
/*  29 */     this.aircraft = ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getFuelSlotItemStack(int i) {
/*  34 */     return func_70301_a(0 + i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getParachuteSlotItemStack(int i) {
/*  39 */     return func_70301_a(3 + i);
/*     */   }
/*     */   
/*     */   public boolean haveParachute() {
/*  43 */     for (int i = 0; i < 2; i++) {
/*     */       
/*  45 */       ItemStack item = getParachuteSlotItemStack(i);
/*  46 */       if (item != null && item.func_77973_b() instanceof mcheli.parachute.MCH_ItemParachute)
/*     */       {
/*  48 */         return true;
/*     */       }
/*     */     } 
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   public void consumeParachute() {
/*  55 */     for (int i = 0; i < 2; i++) {
/*     */       
/*  57 */       ItemStack item = getParachuteSlotItemStack(i);
/*  58 */       if (item != null && item.func_77973_b() instanceof mcheli.parachute.MCH_ItemParachute) {
/*     */         
/*  60 */         func_70299_a(3 + i, null);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/*  68 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int var1) {
/*  73 */     return this.containerItems[var1];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDead() {
/*  78 */     Random rand = new Random();
/*  79 */     if (this.aircraft.dropContentsWhenDead && !this.aircraft.field_70170_p.field_72995_K)
/*     */     {
/*  81 */       for (int i = 0; i < func_70302_i_(); i++) {
/*     */         
/*  83 */         ItemStack itemstack = func_70301_a(i);
/*     */         
/*  85 */         if (itemstack != null) {
/*     */           
/*  87 */           float x = rand.nextFloat() * 0.8F + 0.1F;
/*  88 */           float y = rand.nextFloat() * 0.8F + 0.1F;
/*  89 */           float z = rand.nextFloat() * 0.8F + 0.1F;
/*     */           
/*  91 */           while (itemstack.field_77994_a > 0) {
/*     */             
/*  93 */             int j = rand.nextInt(21) + 10;
/*     */             
/*  95 */             if (j > itemstack.field_77994_a)
/*     */             {
/*  97 */               j = itemstack.field_77994_a;
/*     */             }
/*     */             
/* 100 */             itemstack.field_77994_a -= j;
/* 101 */             EntityItem entityitem = new EntityItem(this.aircraft.field_70170_p, this.aircraft.field_70165_t + x, this.aircraft.field_70163_u + y, this.aircraft.field_70161_v + z, new ItemStack(itemstack.func_77973_b(), j, itemstack.func_77960_j()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 108 */             if (itemstack.func_77942_o())
/*     */             {
/* 110 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */             }
/*     */             
/* 113 */             float f3 = 0.05F;
/* 114 */             entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 115 */             entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 116 */             entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 117 */             this.aircraft.field_70170_p.func_72838_d((Entity)entityitem);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/* 126 */     if (this.containerItems[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/* 130 */       if ((this.containerItems[par1]).field_77994_a <= par2) {
/*     */         
/* 132 */         ItemStack itemStack = this.containerItems[par1];
/* 133 */         this.containerItems[par1] = null;
/* 134 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 138 */       ItemStack itemstack = this.containerItems[par1].func_77979_a(par2);
/*     */       
/* 140 */       if ((this.containerItems[par1]).field_77994_a == 0)
/*     */       {
/* 142 */         this.containerItems[par1] = null;
/*     */       }
/*     */       
/* 145 */       return itemstack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/* 156 */     if (this.containerItems[par1] != null) {
/*     */       
/* 158 */       ItemStack itemstack = this.containerItems[par1];
/* 159 */       this.containerItems[par1] = null;
/* 160 */       return itemstack;
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack) {
/* 170 */     this.containerItems[par1] = par2ItemStack;
/*     */     
/* 172 */     if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_())
/*     */     {
/* 174 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 180 */     return getInvName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInvName() {
/* 185 */     if (this.aircraft.getAcInfo() == null) return ""; 
/* 186 */     String s = (this.aircraft.getAcInfo()).displayName;
/* 187 */     return (s.length() <= 32) ? s : s.substring(0, 31);
/*     */   }
/*     */   
/*     */   public boolean isInvNameLocalized() {
/* 191 */     return (this.aircraft.getAcInfo() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 196 */     return isInvNameLocalized();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 201 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70296_d() {}
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer player) {
/* 210 */     return (player.func_70068_e((Entity)this.aircraft) <= 144.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 234 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 236 */     for (int i = 0; i < this.containerItems.length; i++) {
/*     */       
/* 238 */       if (this.containerItems[i] != null) {
/*     */         
/* 240 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 241 */         nbttagcompound1.func_74774_a("SlotAC", (byte)i);
/* 242 */         this.containerItems[i].func_77955_b(nbttagcompound1);
/* 243 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     par1NBTTagCompound.func_74782_a("ItemsAC", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 255 */     NBTTagList nbttaglist = W_NBTTag.getTagList(par1NBTTagCompound, "ItemsAC", 10);
/* 256 */     this.containerItems = new ItemStack[func_70302_i_()];
/*     */     
/* 258 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 260 */       NBTTagCompound nbttagcompound1 = W_NBTTag.tagAt(nbttaglist, i);
/* 261 */       int j = nbttagcompound1.func_74771_c("SlotAC") & 0xFF;
/*     */       
/* 263 */       if (j >= 0 && j < this.containerItems.length)
/*     */       {
/* 265 */         this.containerItems[j] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onInventoryChanged() {}
/*     */   
/*     */   public void openChest() {}
/*     */   
/*     */   public void closeChest() {}
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */