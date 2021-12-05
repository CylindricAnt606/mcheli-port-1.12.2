/*     */ package mcheli.mcheli.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.block.MCH_DraftingTableTileEntity;
/*     */ import mcheli.wrapper.IconRegister;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_BlockContainer;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_DraftingTableBlock extends W_BlockContainer implements ITileEntityProvider {
/*     */   private final boolean isLighting;
/*     */   
/*     */   public MCH_DraftingTableBlock(int blockId, boolean p_i45421_1_) {
/*  30 */     super(blockId, Material.field_151573_f);
/*  31 */     func_149672_a(W_Block.field_149777_j);
/*  32 */     func_149711_c(0.2F);
/*  33 */     this.isLighting = p_i45421_1_;
/*     */     
/*  35 */     if (p_i45421_1_)
/*     */     {
/*  37 */       func_149715_a(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
/*  48 */     if (!world.field_72995_K)
/*     */     {
/*  50 */       if (!player.func_70093_af()) {
/*     */         
/*  52 */         MCH_Lib.DbgLog(player.field_70170_p, "MCH_DraftingTableGui.MCH_DraftingTableGui OPEN GUI (%d, %d, %d)", new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/*     */         
/*  54 */         player.openGui(MCH_MOD.instance, 4, world, x, y, z);
/*     */       }
/*     */       else {
/*     */         
/*  58 */         int yaw = world.func_72805_g(x, y, z);
/*  59 */         MCH_Lib.DbgLog(world, "MCH_DraftingTableBlock.onBlockActivated:yaw=%d Light %s", new Object[] { Integer.valueOf(yaw), this.isLighting ? "OFF->ON" : "ON->OFF" });
/*  60 */         if (this.isLighting) {
/*     */           
/*  62 */           W_WorldFunc.setBlock(world, x, y, z, (Block)MCH_MOD.blockDraftingTable, yaw + 180, 2);
/*     */         }
/*     */         else {
/*     */           
/*  66 */           W_WorldFunc.setBlock(world, x, y, z, (Block)MCH_MOD.blockDraftingTableLit, yaw + 180, 2);
/*     */         } 
/*     */         
/*  69 */         world.func_72921_c(x, y, z, yaw, 2);
/*  70 */         world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.5F);
/*     */       } 
/*     */     }
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int a) {
/*  81 */     return (TileEntity)new MCH_DraftingTableTileEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World world) {
/*  86 */     return (TileEntity)new MCH_DraftingTableTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHarvestBlock(EntityPlayer player, int meta) {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRenderInPass(int pass) {
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149656_h() {
/* 118 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int par2, int par3, int par4, EntityLivingBase entity, ItemStack itemStack) {
/* 125 */     float pyaw = (float)MCH_Lib.getRotate360(entity.field_70177_z);
/* 126 */     pyaw += 22.5F;
/* 127 */     int yaw = (int)(pyaw / 45.0F);
/* 128 */     if (yaw < 0) yaw = yaw % 8 + 8; 
/* 129 */     world.func_72921_c(par2, par3, par4, yaw, 2);
/* 130 */     MCH_Lib.DbgLog(world, "MCH_DraftingTableBlock.onBlockPlacedBy:yaw=%d", new Object[] { Integer.valueOf(yaw) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149710_n() {
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/* 142 */     this.field_149761_L = par1IconRegister.func_94245_a("mcheli:drafting_table");
/*     */   }
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 146 */     this.field_149761_L = par1IconRegister.registerIcon("mcheli:drafting_table");
/*     */   }
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
/* 151 */     return W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World world, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
/* 160 */     return W_Item.getItemFromBlock((Block)MCH_MOD.blockDraftingTable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack func_149644_j(int p_149644_1_) {
/* 169 */     return new ItemStack((Block)MCH_MOD.blockDraftingTable);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */