/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_NBTTag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class W_EntityContainer
/*     */   extends W_Entity
/*     */   implements IInventory {
/*     */   public static final int MAX_INVENTORY_SIZE = 54;
/*     */   private ItemStack[] containerItems;
/*     */   public boolean dropContentsWhenDead = true;
/*     */   
/*     */   public W_EntityContainer(World par1World) {
/*  24 */     super(par1World);
/*  25 */     this.containerItems = new ItemStack[54];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int par1) {
/*  35 */     return this.containerItems[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUsingSlotNum() {
/*  40 */     int numUsingSlot = 0;
/*  41 */     if (this.containerItems == null) {
/*     */       
/*  43 */       numUsingSlot = 0;
/*     */     }
/*     */     else {
/*     */       
/*  47 */       int n = func_70302_i_();
/*  48 */       numUsingSlot = 0;
/*  49 */       for (int i = 0; i < n && i < this.containerItems.length; i++) {
/*     */         
/*  51 */         if (func_70301_a(i) != null) numUsingSlot++; 
/*     */       } 
/*     */     } 
/*  54 */     return numUsingSlot;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/*  59 */     if (this.containerItems[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  63 */       if ((this.containerItems[par1]).field_77994_a <= par2) {
/*     */         
/*  65 */         ItemStack itemStack = this.containerItems[par1];
/*  66 */         this.containerItems[par1] = null;
/*  67 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  71 */       ItemStack itemstack = this.containerItems[par1].func_77979_a(par2);
/*     */       
/*  73 */       if ((this.containerItems[par1]).field_77994_a == 0)
/*     */       {
/*  75 */         this.containerItems[par1] = null;
/*     */       }
/*     */       
/*  78 */       return itemstack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/*  93 */     if (this.containerItems[par1] != null) {
/*     */       
/*  95 */       ItemStack itemstack = this.containerItems[par1];
/*  96 */       this.containerItems[par1] = null;
/*  97 */       return itemstack;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack) {
/* 110 */     this.containerItems[par1] = par2ItemStack;
/*     */     
/* 112 */     if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_())
/*     */     {
/* 114 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 117 */     func_70296_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
/* 130 */     return this.field_70128_L ? false : ((par1EntityPlayer.func_70068_e((Entity)this) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void openChest() {}
/*     */ 
/*     */   
/*     */   public void closeChest() {}
/*     */   
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInvName() {
/* 153 */     return "Inventory";
/*     */   }
/*     */   
/*     */   public String func_145825_b() {
/* 157 */     return getInvName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvNameLocalized() {
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_() {
/* 166 */     return isInvNameLocalized();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 175 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 183 */     if (this.dropContentsWhenDead && !this.field_70170_p.field_72995_K)
/*     */     {
/* 185 */       for (int i = 0; i < func_70302_i_(); i++) {
/*     */         
/* 187 */         ItemStack itemstack = func_70301_a(i);
/*     */         
/* 189 */         if (itemstack != null) {
/*     */           
/* 191 */           float x = this.field_70146_Z.nextFloat() * 0.8F + 0.1F;
/* 192 */           float y = this.field_70146_Z.nextFloat() * 0.8F + 0.1F;
/* 193 */           float z = this.field_70146_Z.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 195 */           while (itemstack.field_77994_a > 0) {
/*     */             
/* 197 */             int j = this.field_70146_Z.nextInt(21) + 10;
/*     */             
/* 199 */             if (j > itemstack.field_77994_a)
/*     */             {
/* 201 */               j = itemstack.field_77994_a;
/*     */             }
/*     */             
/* 204 */             itemstack.field_77994_a -= j;
/* 205 */             EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t + x, this.field_70163_u + y, this.field_70161_v + z, new ItemStack(itemstack.func_77973_b(), j, itemstack.func_77960_j()));
/*     */             
/* 207 */             if (itemstack.func_77942_o())
/*     */             {
/* 209 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */             }
/*     */             
/* 212 */             float f3 = 0.05F;
/* 213 */             entityitem.field_70159_w = ((float)this.field_70146_Z.nextGaussian() * f3);
/* 214 */             entityitem.field_70181_x = ((float)this.field_70146_Z.nextGaussian() * f3 + 0.2F);
/* 215 */             entityitem.field_70179_y = ((float)this.field_70146_Z.nextGaussian() * f3);
/* 216 */             this.field_70170_p.func_72838_d((Entity)entityitem);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 222 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 230 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 232 */     for (int i = 0; i < this.containerItems.length; i++) {
/*     */       
/* 234 */       if (this.containerItems[i] != null) {
/*     */         
/* 236 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 237 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 238 */         this.containerItems[i].func_77955_b(nbttagcompound1);
/* 239 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 243 */     par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 251 */     NBTTagList nbttaglist = W_NBTTag.getTagList(par1NBTTagCompound, "Items", 10);
/* 252 */     this.containerItems = new ItemStack[func_70302_i_()];
/*     */     
/* 254 */     MCH_Lib.DbgLog(this.field_70170_p, "W_EntityContainer.readEntityFromNBT.InventorySize = %d", new Object[] { Integer.valueOf(func_70302_i_()) });
/*     */     
/* 256 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 258 */       NBTTagCompound nbttagcompound1 = W_NBTTag.tagAt(nbttaglist, i);
/* 259 */       int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
/*     */       
/* 261 */       if (j >= 0 && j < this.containerItems.length)
/*     */       {
/* 263 */         this.containerItems[j] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71027_c(int par1) {
/* 270 */     this.dropContentsWhenDead = false;
/* 271 */     super.func_71027_c(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean openInventory(EntityPlayer player) {
/* 276 */     if (!this.field_70170_p.field_72995_K && func_70302_i_() > 0) {
/*     */       
/* 278 */       player.func_71007_a(this);
/* 279 */       return true;
/*     */     } 
/* 281 */     return false;
/*     */   }
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */   
/*     */   public void func_70296_d() {}
/*     */   
/*     */   public int func_70302_i_() {
/* 290 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_EntityContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */