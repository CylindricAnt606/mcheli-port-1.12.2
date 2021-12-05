/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.multiplay.MCH_PacketNotifyMarkPoint;
/*     */ import mcheli.multiplay.MCH_PacketNotifySpotedEntity;
/*     */ import mcheli.multiplay.MCH_TargetType;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.server.CommandScoreboard;
/*     */ import net.minecraft.command.server.CommandTeleport;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_Multiplay
/*     */ {
/*     */   public static boolean canSpotEntityWithFilter(int filter, Entity entity) {
/*  32 */     if (entity instanceof mcheli.plane.MCP_EntityPlane)
/*     */     {
/*  34 */       return ((filter & 0x20) != 0);
/*     */     }
/*  36 */     if (entity instanceof mcheli.helicopter.MCH_EntityHeli)
/*     */     {
/*  38 */       return ((filter & 0x10) != 0);
/*     */     }
/*  40 */     if (entity instanceof mcheli.vehicle.MCH_EntityVehicle || entity instanceof mcheli.tank.MCH_EntityTank)
/*     */     {
/*  42 */       return ((filter & 0x8) != 0);
/*     */     }
/*  44 */     if (entity instanceof EntityPlayer)
/*     */     {
/*  46 */       return ((filter & 0x4) != 0);
/*     */     }
/*  48 */     if (entity instanceof EntityLivingBase) {
/*     */       
/*  50 */       if (isMonster(entity))
/*     */       {
/*  52 */         return ((filter & 0x2) != 0);
/*     */       }
/*     */ 
/*     */       
/*  56 */       return ((filter & 0x1) != 0);
/*     */     } 
/*     */     
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isMonster(Entity entity) {
/*  64 */     return (entity.getClass().toString().toLowerCase().indexOf("monster") >= 0);
/*     */   }
/*     */   
/*  67 */   public static final MCH_TargetType[][] ENTITY_SPOT_TABLE = new MCH_TargetType[][] { { MCH_TargetType.NONE, MCH_TargetType.NONE }, { MCH_TargetType.OTHER_MOB, MCH_TargetType.OTHER_MOB }, { MCH_TargetType.MONSTER, MCH_TargetType.MONSTER }, { MCH_TargetType.NONE, MCH_TargetType.NO_TEAM_PLAYER }, { MCH_TargetType.NONE, MCH_TargetType.SAME_TEAM_PLAYER }, { MCH_TargetType.NONE, MCH_TargetType.OTHER_TEAM_PLAYER }, { MCH_TargetType.NONE, MCH_TargetType.NONE }, { MCH_TargetType.NONE, MCH_TargetType.NO_TEAM_PLAYER }, { MCH_TargetType.NONE, MCH_TargetType.SAME_TEAM_PLAYER }, { MCH_TargetType.NONE, MCH_TargetType.OTHER_TEAM_PLAYER } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MCH_TargetType canSpotEntity(Entity user, double posX, double posY, double posZ, Entity target, boolean checkSee) {
/*  89 */     if (!(user instanceof EntityLivingBase)) return MCH_TargetType.NONE;
/*     */     
/*  91 */     EntityLivingBase spotter = (EntityLivingBase)user;
/*  92 */     int col = (spotter.func_96124_cp() == null) ? 0 : 1;
/*  93 */     int row = 0;
/*     */     
/*  95 */     if (target instanceof EntityLivingBase)
/*     */     {
/*  97 */       if (!isMonster(target)) {
/*     */         
/*  99 */         row = 1;
/*     */       }
/*     */       else {
/*     */         
/* 103 */         row = 2;
/*     */       } 
/*     */     }
/*     */     
/* 107 */     if (spotter.func_96124_cp() != null) {
/*     */       
/* 109 */       if (target instanceof EntityPlayer) {
/*     */         
/* 111 */         EntityPlayer player = (EntityPlayer)target;
/* 112 */         if (player.func_96124_cp() == null)
/*     */         {
/* 114 */           row = 3;
/*     */         }
/* 116 */         else if (spotter.func_142014_c((EntityLivingBase)player))
/*     */         {
/* 118 */           row = 4;
/*     */         }
/*     */         else
/*     */         {
/* 122 */           row = 5;
/*     */         }
/*     */       
/* 125 */       } else if (target instanceof MCH_EntityAircraft) {
/*     */         
/* 127 */         MCH_EntityAircraft ac = (MCH_EntityAircraft)target;
/* 128 */         EntityPlayer rideEntity = ac.getFirstMountPlayer();
/* 129 */         if (rideEntity == null)
/*     */         {
/* 131 */           row = 6;
/*     */         }
/* 133 */         else if (rideEntity.func_96124_cp() == null)
/*     */         {
/* 135 */           row = 7;
/*     */         }
/* 137 */         else if (spotter.func_142014_c((EntityLivingBase)rideEntity))
/*     */         {
/* 139 */           row = 8;
/*     */         }
/*     */         else
/*     */         {
/* 143 */           row = 9;
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 149 */     else if (target instanceof EntityPlayer || target instanceof MCH_EntityAircraft) {
/*     */       
/* 151 */       row = 0;
/*     */     } 
/*     */ 
/*     */     
/* 155 */     MCH_TargetType ret = ENTITY_SPOT_TABLE[row][col];
/* 156 */     if (checkSee && ret != MCH_TargetType.NONE) {
/*     */       
/* 158 */       Vec3 vs = Vec3.func_72443_a(posX, posY, posZ);
/* 159 */       Vec3 ve = Vec3.func_72443_a(target.field_70165_t, target.field_70163_u + target.func_70047_e(), target.field_70161_v);
/* 160 */       MovingObjectPosition mop = target.field_70170_p.func_72933_a(vs, ve);
/* 161 */       if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
/*     */       {
/* 163 */         ret = MCH_TargetType.NONE;
/*     */       }
/*     */     } 
/*     */     
/* 167 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canAttackEntity(DamageSource ds, Entity target) {
/* 172 */     return canAttackEntity(ds.func_76346_g(), target);
/*     */   }
/*     */   
/*     */   public static boolean canAttackEntity(Entity attacker, Entity target) {
/* 176 */     if (attacker != null && target != null) {
/*     */       
/* 178 */       EntityPlayer attackPlayer = null;
/* 179 */       EntityPlayer targetPlayer = null;
/*     */       
/* 181 */       if (attacker instanceof EntityPlayer)
/*     */       {
/* 183 */         attackPlayer = (EntityPlayer)attacker;
/*     */       }
/*     */       
/* 186 */       if (target instanceof EntityPlayer) {
/*     */         
/* 188 */         targetPlayer = (EntityPlayer)target;
/*     */       }
/* 190 */       else if (target.field_70153_n instanceof EntityPlayer) {
/*     */         
/* 192 */         targetPlayer = (EntityPlayer)target.field_70153_n;
/*     */       } 
/* 194 */       if (target instanceof MCH_EntityAircraft) {
/*     */         
/* 196 */         MCH_EntityAircraft ac = (MCH_EntityAircraft)target;
/* 197 */         if (ac.getRiddenByEntity() instanceof EntityPlayer)
/*     */         {
/* 199 */           targetPlayer = (EntityPlayer)ac.getRiddenByEntity();
/*     */         }
/*     */       } 
/*     */       
/* 203 */       if (attackPlayer != null && targetPlayer != null)
/*     */       {
/* 205 */         if (!attackPlayer.func_96122_a(targetPlayer))
/*     */         {
/* 207 */           return false;
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 212 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void jumpSpawnPoint(EntityPlayer player) {
/* 217 */     MCH_Lib.DbgLog(false, "JumpSpawnPoint", new Object[0]);
/* 218 */     CommandTeleport cmd = new CommandTeleport();
/* 219 */     if (cmd.func_71519_b((ICommandSender)player)) {
/*     */       
/* 221 */       MinecraftServer minecraftServer = MinecraftServer.func_71276_C();
/* 222 */       for (String playerName : minecraftServer.func_71203_ab().func_72369_d()) {
/*     */         
/* 224 */         EntityPlayerMP jumpPlayer = CommandTeleport.func_82359_c((ICommandSender)player, playerName);
/* 225 */         ChunkCoordinates cc = null;
/* 226 */         if (jumpPlayer != null && jumpPlayer.field_71093_bK == player.field_71093_bK) {
/*     */           
/* 228 */           cc = jumpPlayer.getBedLocation(jumpPlayer.field_71093_bK);
/* 229 */           if (cc != null)
/*     */           {
/* 231 */             cc = EntityPlayer.func_71056_a((World)minecraftServer.func_71218_a(jumpPlayer.field_71093_bK), cc, true);
/*     */           }
/*     */           
/* 234 */           if (cc == null)
/*     */           {
/* 236 */             cc = jumpPlayer.field_70170_p.field_73011_w.getRandomizedSpawnPoint();
/*     */           }
/*     */         } 
/*     */         
/* 240 */         if (cc != null) {
/*     */           
/* 242 */           String[] cmdStr = { playerName, String.format("%.1f", new Object[] { Double.valueOf(cc.field_71574_a + 0.5D) }), String.format("%.1f", new Object[] { Double.valueOf(cc.field_71572_b + 0.1D) }), String.format("%.1f", new Object[] { Double.valueOf(cc.field_71573_c + 0.5D) }) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 248 */           cmd.func_71515_b((ICommandSender)player, cmdStr);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void shuffleTeam(EntityPlayer player) {
/* 256 */     Collection teams = player.field_70170_p.func_96441_U().func_96525_g();
/* 257 */     int teamNum = teams.size();
/* 258 */     MCH_Lib.DbgLog(false, "ShuffleTeam:%d teams ----------", new Object[] { Integer.valueOf(teamNum) });
/* 259 */     if (teamNum > 0) {
/*     */       
/* 261 */       CommandScoreboard cmd = new CommandScoreboard();
/* 262 */       if (cmd.func_71519_b((ICommandSender)player)) {
/*     */         
/* 264 */         List<String> list = Arrays.asList(MinecraftServer.func_71276_C().func_71203_ab().func_72369_d());
/*     */         
/* 266 */         Collections.shuffle(list);
/*     */         
/* 268 */         ArrayList<String> listTeam = new ArrayList<String>();
/* 269 */         for (Object o : teams) {
/*     */           
/* 271 */           ScorePlayerTeam team = (ScorePlayerTeam)o;
/* 272 */           listTeam.add(team.func_96661_b());
/*     */         } 
/* 274 */         Collections.shuffle(listTeam);
/*     */         
/* 276 */         for (int i = 0, k = 0; i < list.size(); i++) {
/*     */           
/* 278 */           listTeam.set(k, (String)listTeam.get(k) + " " + (String)list.get(i));
/* 279 */           k++;
/* 280 */           if (k >= teamNum)
/*     */           {
/* 282 */             k = 0;
/*     */           }
/*     */         } 
/*     */         
/* 286 */         for (int j = 0; j < listTeam.size(); j++) {
/*     */           
/* 288 */           String exe_cmd = "teams join " + (String)listTeam.get(j);
/* 289 */           String[] process_cmd = exe_cmd.split(" ");
/* 290 */           if (process_cmd.length > 3) {
/*     */             
/* 292 */             MCH_Lib.DbgLog(false, "ShuffleTeam:" + exe_cmd, new Object[0]);
/* 293 */             cmd.func_71515_b((ICommandSender)player, process_cmd);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean spotEntity(EntityPlayer player, MCH_EntityAircraft ac, double posX, double posY, double posZ, int targetFilter, float spotLength, int markTime, float angle) {
/* 304 */     boolean ret = false;
/* 305 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 307 */       float acYaw = 0.0F;
/* 308 */       float acPitch = 0.0F;
/* 309 */       float acRoll = 0.0F;
/*     */       
/* 311 */       if (ac != null) {
/*     */         
/* 313 */         acYaw = ac.getRotYaw();
/* 314 */         acPitch = ac.getRotPitch();
/* 315 */         acRoll = ac.getRotRoll();
/*     */       } 
/*     */       
/* 318 */       Vec3 vv = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -player.field_70177_z, -player.field_70125_A, -acRoll);
/* 319 */       double tx = vv.field_72450_a;
/* 320 */       double tz = vv.field_72449_c;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 325 */       List<Entity> list = player.field_70170_p.func_72839_b((Entity)player, player.field_70121_D.func_72314_b(spotLength, spotLength, spotLength));
/*     */       
/* 327 */       List<Integer> entityList = new ArrayList<Integer>();
/*     */       
/* 329 */       Vec3 pos = Vec3.func_72443_a(posX, posY, posZ);
/* 330 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 332 */         Entity entity = list.get(i);
/*     */         
/* 334 */         if (canSpotEntityWithFilter(targetFilter, entity)) {
/*     */           
/* 336 */           MCH_TargetType stopType = canSpotEntity((Entity)player, posX, posY, posZ, entity, true);
/*     */           
/* 338 */           if (stopType != MCH_TargetType.NONE && stopType != MCH_TargetType.SAME_TEAM_PLAYER) {
/*     */             
/* 340 */             double dist = entity.func_70092_e(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
/* 341 */             if (dist > 1.0D && dist < (spotLength * spotLength)) {
/*     */               
/* 343 */               double cx = entity.field_70165_t - pos.field_72450_a;
/* 344 */               double cy = entity.field_70163_u - pos.field_72448_b;
/* 345 */               double cz = entity.field_70161_v - pos.field_72449_c;
/*     */               
/* 347 */               double h = MCH_Lib.getPosAngle(tx, tz, cx, cz);
/* 348 */               double v = Math.atan2(cy, Math.sqrt(cx * cx + cz * cz)) * 180.0D / Math.PI;
/* 349 */               v = Math.abs(v + player.field_70125_A);
/* 350 */               if (h < (angle * 2.0F) && v < (angle * 2.0F))
/*     */               {
/* 352 */                 entityList.add(Integer.valueOf(entity.func_145782_y()));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 359 */       if (entityList.size() > 0) {
/*     */         
/* 361 */         int[] entityId = new int[entityList.size()];
/* 362 */         for (int j = 0; j < entityId.length; j++)
/*     */         {
/* 364 */           entityId[j] = ((Integer)entityList.get(j)).intValue();
/*     */         }
/* 366 */         sendSpotedEntityListToSameTeam(player, markTime, entityId);
/* 367 */         ret = true;
/*     */       }
/*     */       else {
/*     */         
/* 371 */         ret = false;
/*     */       } 
/*     */     } 
/*     */     
/* 375 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendSpotedEntityListToSameTeam(EntityPlayer player, int count, int[] entityId) {
/* 380 */     ServerConfigurationManager svCnf = MinecraftServer.func_71276_C().func_71203_ab();
/* 381 */     for (EntityPlayerMP entityPlayerMP : svCnf.field_72404_b) {
/*     */       
/* 383 */       if (player == entityPlayerMP || player.func_142014_c((EntityLivingBase)entityPlayerMP))
/*     */       {
/* 385 */         MCH_PacketNotifySpotedEntity.send((EntityPlayer)entityPlayerMP, count, entityId);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean markPoint(EntityPlayer player, double posX, double posY, double posZ) {
/* 392 */     Vec3 vs = Vec3.func_72443_a(posX, posY, posZ);
/* 393 */     Vec3 ve = MCH_Lib.Rot2Vec3(player.field_70177_z, player.field_70125_A);
/* 394 */     ve = vs.func_72441_c(ve.field_72450_a * 300.0D, ve.field_72448_b * 300.0D, ve.field_72449_c * 300.0D);
/* 395 */     MovingObjectPosition mop = player.field_70170_p.func_72901_a(vs, ve, true);
/*     */     
/* 397 */     if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/* 399 */       sendMarkPointToSameTeam(player, mop.field_72311_b, mop.field_72312_c + 2, mop.field_72309_d);
/* 400 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 404 */     sendMarkPointToSameTeam(player, 0, 1000, 0);
/*     */     
/* 406 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMarkPointToSameTeam(EntityPlayer player, int x, int y, int z) {
/* 411 */     ServerConfigurationManager svCnf = MinecraftServer.func_71276_C().func_71203_ab();
/* 412 */     for (EntityPlayerMP entityPlayerMP : svCnf.field_72404_b) {
/*     */       
/* 414 */       if (player == entityPlayerMP || player.func_142014_c((EntityLivingBase)entityPlayerMP))
/*     */       {
/* 416 */         MCH_PacketNotifyMarkPoint.send((EntityPlayer)entityPlayerMP, x, y, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_Multiplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */