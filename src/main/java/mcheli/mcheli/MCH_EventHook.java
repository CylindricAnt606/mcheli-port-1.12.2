/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_PacketNotifyServerSettings;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_ItemAircraft;
/*     */ import mcheli.chain.MCH_ItemChain;
/*     */ import mcheli.command.MCH_Command;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_EntityPlayer;
/*     */ import mcheli.wrapper.W_EventHook;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.event.CommandEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EventHook
/*     */   extends W_EventHook
/*     */ {
/*     */   public void commandEvent(CommandEvent event) {
/*  34 */     MCH_Command.onCommandEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entitySpawn(EntityJoinWorldEvent event) {
/*  39 */     if (W_Lib.isEntityLivingBase(event.entity) && !W_EntityPlayer.isPlayer(event.entity)) {
/*     */       
/*  41 */       event.entity.field_70155_l *= MCH_Config.MobRenderDistanceWeight.prmDouble;
/*     */     }
/*  43 */     else if (event.entity instanceof MCH_EntityAircraft) {
/*     */       
/*  45 */       MCH_EntityAircraft aircraft = (MCH_EntityAircraft)event.entity;
/*  46 */       if (!aircraft.field_70170_p.field_72995_K)
/*     */       {
/*     */ 
/*     */         
/*  50 */         if (!aircraft.isCreatedSeats())
/*     */         {
/*  52 */           aircraft.createSeats(UUID.randomUUID().toString());
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  62 */     else if (W_EntityPlayer.isPlayer(event.entity)) {
/*     */ 
/*     */       
/*  65 */       Entity e = event.entity;
/*  66 */       boolean b = Float.isNaN(e.field_70125_A);
/*  67 */       b |= Float.isNaN(e.field_70127_C);
/*  68 */       b |= Float.isInfinite(e.field_70125_A);
/*  69 */       b |= Float.isInfinite(e.field_70127_C);
/*  70 */       if (b) {
/*     */         
/*  72 */         MCH_Lib.Log(event.entity, "### EntityJoinWorldEvent Error:Player invalid rotation pitch(" + e.field_70125_A + ")", new Object[0]);
/*  73 */         e.field_70125_A = 0.0F;
/*  74 */         e.field_70127_C = 0.0F;
/*     */       } 
/*     */       
/*  77 */       b = Float.isInfinite(e.field_70177_z);
/*  78 */       b |= Float.isInfinite(e.field_70126_B);
/*  79 */       b |= Float.isNaN(e.field_70177_z);
/*  80 */       b |= Float.isNaN(e.field_70126_B);
/*  81 */       if (b) {
/*     */         
/*  83 */         MCH_Lib.Log(event.entity, "### EntityJoinWorldEvent Error:Player invalid rotation yaw(" + e.field_70177_z + ")", new Object[0]);
/*  84 */         e.field_70177_z = 0.0F;
/*  85 */         e.field_70126_B = 0.0F;
/*     */       } 
/*     */       
/*  88 */       if (!e.field_70170_p.field_72995_K && event.entity instanceof EntityPlayerMP) {
/*     */         
/*  90 */         MCH_Lib.DbgLog(false, "EntityJoinWorldEvent:" + event.entity, new Object[0]);
/*  91 */         MCH_PacketNotifyServerSettings.send((EntityPlayerMP)event.entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void livingAttackEvent(LivingAttackEvent event) {
/*  98 */     MCH_EntityAircraft ac = getRiddenAircraft(event.entity);
/*  99 */     if (ac == null)
/* 100 */       return;  if (ac.getAcInfo() == null)
/* 101 */       return;  if (ac.isDestroyed())
/* 102 */       return;  if ((ac.getAcInfo()).damageFactor > 0.0F)
/*     */       return; 
/* 104 */     Entity attackEntity = event.source.func_76346_g();
/* 105 */     if (attackEntity == null) {
/*     */       
/* 107 */       event.setCanceled(true);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 112 */     else if (W_Entity.isEqual(attackEntity, event.entity)) {
/*     */       
/* 114 */       event.setCanceled(true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 120 */     else if (ac.isMountedEntity(attackEntity)) {
/*     */       
/* 122 */       event.setCanceled(true);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 127 */       MCH_EntityAircraft atkac = getRiddenAircraft(attackEntity);
/* 128 */       if (W_Entity.isEqual((Entity)atkac, (Entity)ac))
/*     */       {
/* 130 */         event.setCanceled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void livingHurtEvent(LivingHurtEvent event) {
/* 143 */     MCH_EntityAircraft ac = getRiddenAircraft(event.entity);
/* 144 */     if (ac == null)
/* 145 */       return;  if (ac.getAcInfo() == null)
/* 146 */       return;  if (ac.isDestroyed())
/*     */       return; 
/* 148 */     Entity attackEntity = event.source.func_76346_g();
/* 149 */     if (attackEntity == null) {
/*     */       
/* 151 */       ac.func_70097_a(event.source, event.ammount * 2.0F);
/* 152 */       event.ammount *= (ac.getAcInfo()).damageFactor;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 157 */     else if (W_Entity.isEqual(attackEntity, event.entity)) {
/*     */       
/* 159 */       ac.func_70097_a(event.source, event.ammount * 2.0F);
/* 160 */       event.ammount *= (ac.getAcInfo()).damageFactor;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 166 */     else if (ac.isMountedEntity(attackEntity)) {
/*     */       
/* 168 */       event.ammount = 0.0F;
/* 169 */       event.setCanceled(true);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 174 */       MCH_EntityAircraft atkac = getRiddenAircraft(attackEntity);
/* 175 */       if (W_Entity.isEqual((Entity)atkac, (Entity)ac)) {
/*     */         
/* 177 */         event.ammount = 0.0F;
/* 178 */         event.setCanceled(true);
/*     */       }
/*     */       else {
/*     */         
/* 182 */         ac.func_70097_a(event.source, event.ammount * 2.0F);
/* 183 */         event.ammount *= (ac.getAcInfo()).damageFactor;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityAircraft getRiddenAircraft(Entity entity) {
/* 192 */     MCH_EntityAircraft ac = null;
/* 193 */     Entity ridden = entity.field_70154_o;
/*     */ 
/*     */     
/* 196 */     if (ridden instanceof MCH_EntityAircraft) {
/*     */       
/* 198 */       ac = (MCH_EntityAircraft)ridden;
/*     */     
/*     */     }
/* 201 */     else if (ridden instanceof MCH_EntitySeat) {
/*     */       
/* 203 */       ac = ((MCH_EntitySeat)ridden).getParent();
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (ac == null) {
/*     */ 
/*     */       
/* 210 */       List<MCH_EntityAircraft> list = entity.field_70170_p.func_72872_a(MCH_EntityAircraft.class, entity.field_70121_D.func_72314_b(50.0D, 50.0D, 50.0D));
/*     */       
/* 212 */       if (list != null)
/*     */       {
/* 214 */         for (int i = 0; i < list.size(); i++) {
/*     */           
/* 216 */           MCH_EntityAircraft tmp = list.get(i);
/* 217 */           if (tmp.isMountedEntity(entity))
/*     */           {
/* 219 */             return tmp;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 224 */     return ac;
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityInteractEvent(EntityInteractEvent event) {
/* 229 */     ItemStack item = event.entityPlayer.func_70694_bm();
/* 230 */     if (item == null)
/*     */       return; 
/* 232 */     if (item.func_77973_b() instanceof MCH_ItemChain) {
/*     */       
/* 234 */       MCH_ItemChain.interactEntity(item, event.target, event.entityPlayer, event.entityPlayer.field_70170_p);
/*     */ 
/*     */       
/* 237 */       event.setCanceled(true);
/*     */     }
/* 239 */     else if (item.func_77973_b() instanceof MCH_ItemAircraft) {
/*     */       
/* 241 */       ((MCH_ItemAircraft)item.func_77973_b()).rideEntity(item, event.target, event.entityPlayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityCanUpdate(EntityEvent.CanUpdate event) {
/* 247 */     if (event.entity instanceof MCH_EntityBaseBullet) {
/*     */       
/* 249 */       MCH_EntityBaseBullet bullet = (MCH_EntityBaseBullet)event.entity;
/* 250 */       bullet.func_70106_y();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_EventHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */