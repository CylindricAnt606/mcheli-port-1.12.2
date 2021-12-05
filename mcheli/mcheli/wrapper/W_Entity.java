/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import mcheli.wrapper.W_Block;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class W_Entity
/*     */   extends Entity {
/*     */   public W_Entity(World par1World) {
/*  21 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEntityFallingBlock(Entity entity) {
/*  32 */     return entity instanceof net.minecraft.entity.item.EntityFallingBlock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEntityId(Entity entity) {
/*  40 */     return (entity != null) ? entity.func_145782_y() : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEqual(Entity e1, Entity e2) {
/*  46 */     int i1 = getEntityId(e1);
/*  47 */     int i2 = getEntityId(e2);
/*  48 */     return (i1 == i2);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItem dropItemWithOffset(Item item, int par2, float par3) {
/*  53 */     return func_70099_a(new ItemStack(item, par2, 0), par3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityName() {
/*  59 */     return func_70022_Q();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer par1EntityPlayer) {
/*  67 */     return interact(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer par1EntityPlayer) {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
/*  84 */     return func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean attackEntityFrom(Entity entity, DamageSource ds, float par2) {
/*  95 */     return entity.func_70097_a(ds, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_85029_a(CrashReportCategory par1CrashReportCategory) {
/* 103 */     super.func_85029_a(par1CrashReportCategory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getBlockExplosionResistance(Entity entity, Explosion par1Explosion, World par2World, int par3, int par4, int par5, Block par6Block) {
/* 110 */     if (par6Block != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 115 */         return entity.func_145772_a(par1Explosion, par2World, par3, par4, par5, par6Block);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 121 */       catch (Exception e) {
/*     */         
/* 123 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 127 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean shouldExplodeBlock(Entity entity, Explosion par1Explosion, World par2World, int par3, int par4, int par5, int par6, float par7) {
/* 133 */     return entity.func_145774_a(par1Explosion, par2World, par3, par4, par5, W_Block.getBlockById(par6), par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PotionEffect getActivePotionEffect(Entity entity, Potion par1Potion) {
/* 143 */     return (entity instanceof EntityLivingBase) ? ((EntityLivingBase)entity).func_70660_b(par1Potion) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removePotionEffectClient(Entity entity, int id) {
/* 150 */     if (entity instanceof EntityLivingBase) ((EntityLivingBase)entity).func_70618_n(id);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removePotionEffect(Entity entity, int id) {
/* 157 */     if (entity instanceof EntityLivingBase) ((EntityLivingBase)entity).func_82170_o(id);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addPotionEffect(Entity entity, PotionEffect pe) {
/* 165 */     if (entity instanceof EntityLivingBase) ((EntityLivingBase)entity).func_70690_d(pe);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doBlockCollisions() {
/* 173 */     func_145775_I();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */