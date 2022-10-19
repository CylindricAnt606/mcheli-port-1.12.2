/*     */ package mcheli.mcheli.chain;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.chain.MCH_EntityChain;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ItemChain
/*     */   extends W_Item
/*     */ {
/*     */   public MCH_ItemChain(int par1) {
/*  22 */     super(par1);
/*  23 */     func_77625_d(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void interactEntity(ItemStack item, Entity entity, EntityPlayer player, World world) {
/*  28 */     if (!world.field_72995_K && entity != null && !entity.field_70128_L) {
/*     */       
/*  30 */       if (entity instanceof net.minecraft.entity.item.EntityItem)
/*  31 */         return;  if (entity instanceof MCH_EntityChain)
/*  32 */         return;  if (entity instanceof mcheli.aircraft.MCH_EntityHitBox)
/*  33 */         return;  if (entity instanceof mcheli.aircraft.MCH_EntitySeat)
/*  34 */         return;  if (entity instanceof mcheli.uav.MCH_EntityUavStation)
/*  35 */         return;  if (entity instanceof mcheli.parachute.MCH_EntityParachute)
/*  36 */         return;  if (W_Lib.isEntityLivingBase(entity)) {
/*     */         return;
/*     */       }
/*  39 */       MCH_EntityChain towingChain = getTowedEntityChain(entity);
/*  40 */       if (towingChain != null) {
/*     */         
/*  42 */         towingChain.func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  46 */       Entity entityTowed = getTowedEntity(item, world);
/*     */ 
/*     */       
/*  49 */       if (entityTowed == null) {
/*     */         
/*  51 */         playConnectTowedEntity(entity);
/*  52 */         setTowedEntity(item, entity);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  57 */         if (W_Entity.isEqual(entityTowed, entity)) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  63 */         double diff = entity.func_70032_d(entityTowed);
/*  64 */         if (diff < 2.0D || diff > 16.0D) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  69 */         MCH_EntityChain chain = new MCH_EntityChain(world, (entityTowed.field_70165_t + entity.field_70165_t) / 2.0D, (entityTowed.field_70163_u + entity.field_70163_u) / 2.0D, (entityTowed.field_70161_v + entity.field_70161_v) / 2.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  74 */         chain.setChainLength((int)diff);
/*  75 */         chain.setTowEntity(entityTowed, entity);
/*  76 */         chain.field_70169_q = chain.field_70165_t;
/*  77 */         chain.field_70167_r = chain.field_70163_u;
/*  78 */         chain.field_70166_s = chain.field_70161_v;
/*     */         
/*  80 */         world.func_72838_d((Entity)chain);
/*     */         
/*  82 */         playConnectTowingEntity(entity);
/*     */         
/*  84 */         setTowedEntity(item, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void playConnectTowingEntity(Entity e) {
/*  91 */     W_WorldFunc.MOD_playSoundEffect(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v, "chain_ct", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playConnectTowedEntity(Entity e) {
/*  95 */     W_WorldFunc.MOD_playSoundEffect(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v, "chain", 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static MCH_EntityChain getTowedEntityChain(Entity entity) {
/* 106 */     List<MCH_EntityChain> list = entity.field_70170_p.func_72872_a(MCH_EntityChain.class, entity.field_70121_D.func_72314_b(25.0D, 25.0D, 25.0D));
/*     */     
/* 108 */     if (list == null) return null; 
/* 109 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 111 */       MCH_EntityChain chain = list.get(i);
/* 112 */       if (chain.isTowingEntity()) {
/*     */ 
/*     */         
/* 115 */         if (W_Entity.isEqual(chain.towEntity, entity)) return chain; 
/* 116 */         if (W_Entity.isEqual(chain.towedEntity, entity)) return chain; 
/*     */       } 
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setTowedEntity(ItemStack item, Entity entity) {
/* 124 */     NBTTagCompound nbt = item.func_77978_p();
/* 125 */     if (nbt == null) {
/*     */       
/* 127 */       nbt = new NBTTagCompound();
/* 128 */       item.func_77982_d(nbt);
/*     */     } 
/* 130 */     if (entity != null && !entity.field_70128_L) {
/*     */       
/* 132 */       nbt.func_74768_a("TowedEntityId", W_Entity.getEntityId(entity));
/* 133 */       nbt.func_74778_a("TowedEntityUUID", entity.getPersistentID().toString());
/*     */     }
/*     */     else {
/*     */       
/* 137 */       nbt.func_74768_a("TowedEntityId", 0);
/* 138 */       nbt.func_74778_a("TowedEntityUUID", "");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity getTowedEntity(ItemStack item, World world) {
/* 144 */     NBTTagCompound nbt = item.func_77978_p();
/* 145 */     if (nbt == null) {
/*     */       
/* 147 */       nbt = new NBTTagCompound();
/* 148 */       item.func_77982_d(nbt);
/*     */ 
/*     */     
/*     */     }
/* 152 */     else if (nbt.func_74764_b("TowedEntityId") && nbt.func_74764_b("TowedEntityUUID")) {
/*     */       
/* 154 */       int id = nbt.func_74762_e("TowedEntityId");
/* 155 */       String uuid = nbt.func_74779_i("TowedEntityUUID");
/* 156 */       Entity entity = world.func_73045_a(id);
/* 157 */       if (entity != null && !entity.field_70128_L && uuid.compareTo(entity.getPersistentID().toString()) == 0)
/*     */       {
/* 159 */         return entity;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\chain\MCH_ItemChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */