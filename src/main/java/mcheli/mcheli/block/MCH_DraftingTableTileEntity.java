/*    */ package mcheli.mcheli.block;
/*    */ 
/*    */ import mcheli.MCH_Lib;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_DraftingTableTileEntity
/*    */   extends TileEntity
/*    */ {
/*    */   public int func_145832_p() {
/* 19 */     if (this.field_145847_g == -1) {
/*    */       
/* 21 */       this.field_145847_g = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */       
/* 23 */       MCH_Lib.DbgLog(this.field_145850_b, "MCH_DraftingTableTileEntity.getBlockMetadata : %d(0x%08X)", new Object[] { Integer.valueOf(this.field_145847_g), Integer.valueOf(this.field_145847_g) });
/*    */     } 
/*    */ 
/*    */     
/* 27 */     return this.field_145847_g;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableTileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */