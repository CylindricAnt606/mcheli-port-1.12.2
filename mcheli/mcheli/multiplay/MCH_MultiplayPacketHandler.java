/*     */ package mcheli.mcheli.multiplay;
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.multiplay.MCH_GuiTargetMarker;
/*     */ import mcheli.multiplay.MCH_Multiplay;
/*     */ import mcheli.multiplay.MCH_MultiplayClient;
/*     */ import mcheli.multiplay.MCH_PacketIndClient;
/*     */ import mcheli.multiplay.MCH_PacketIndMultiplayCommand;
/*     */ import mcheli.multiplay.MCH_PacketLargeData;
/*     */ import mcheli.multiplay.MCH_PacketModList;
/*     */ import mcheli.multiplay.MCH_PacketNotifyMarkPoint;
/*     */ import mcheli.multiplay.MCH_PacketNotifySpotedEntity;
/*     */ import net.minecraft.command.ICommandManager;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.server.CommandSummon;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ 
/*     */ public class MCH_MultiplayPacketHandler {
/*  29 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   public static void onPacket_Command(EntityPlayer player, ByteArrayDataInput data) {
/*     */     ICommandManager icommandmanager;
/*  33 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  39 */     MinecraftServer minecraftServer = MinecraftServer.func_71276_C();
/*  40 */     if (minecraftServer == null)
/*     */       return; 
/*  42 */     MCH_PacketIndMultiplayCommand pc = new MCH_PacketIndMultiplayCommand();
/*  43 */     pc.readData(data);
/*     */     
/*  45 */     MCH_Lib.DbgLog(false, "MCH_MultiplayPacketHandler.onPacket_Command cmd:%d:%s", new Object[] { Integer.valueOf(pc.CmdID), pc.CmdStr });
/*     */     
/*  47 */     switch (pc.CmdID) {
/*     */       
/*     */       case 256:
/*  50 */         MCH_Multiplay.shuffleTeam(player);
/*     */         return;
/*     */       case 512:
/*  53 */         MCH_Multiplay.jumpSpawnPoint(player);
/*     */         return;
/*     */       case 768:
/*  56 */         icommandmanager = minecraftServer.func_71187_D();
/*  57 */         icommandmanager.func_71556_a((ICommandSender)player, pc.CmdStr);
/*     */         return;
/*     */       case 1024:
/*  60 */         if ((new CommandScoreboard()).func_71519_b((ICommandSender)player)) {
/*     */           
/*  62 */           minecraftServer.func_71188_g(!minecraftServer.func_71219_W());
/*  63 */           MCH_PacketNotifyServerSettings.send(null);
/*     */         } 
/*     */         return;
/*     */       case 1280:
/*  67 */         destoryAllAircraft(player);
/*     */         return;
/*     */     } 
/*  70 */     MCH_Lib.DbgLog(false, "MCH_MultiplayPacketHandler.onPacket_Command unknown cmd:%d:%s", new Object[] { Integer.valueOf(pc.CmdID), pc.CmdStr });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void destoryAllAircraft(EntityPlayer player) {
/*  76 */     CommandSummon cmd = new CommandSummon();
/*  77 */     if (cmd.func_71519_b((ICommandSender)player))
/*     */     {
/*  79 */       for (Object e : player.field_70170_p.field_72996_f) {
/*     */         
/*  81 */         if (e instanceof MCH_EntityAircraft)
/*     */         {
/*  83 */           ((MCH_EntityAircraft)e).func_70106_y();
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onPacket_NotifySpotedEntity(EntityPlayer player, ByteArrayDataInput data) {
/*  92 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/*  94 */     MCH_PacketNotifySpotedEntity pc = new MCH_PacketNotifySpotedEntity();
/*  95 */     pc.readData(data);
/*     */     
/*  97 */     if (pc.count > 0)
/*     */     {
/*  99 */       for (int i = 0; i < pc.num; i++)
/*     */       {
/* 101 */         MCH_GuiTargetMarker.addSpotedEntity(pc.entityId[i], pc.count);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onPacket_NotifyMarkPoint(EntityPlayer player, ByteArrayDataInput data) {
/* 108 */     if (!player.field_70170_p.field_72995_K)
/*     */       return; 
/* 110 */     MCH_PacketNotifyMarkPoint pc = new MCH_PacketNotifyMarkPoint();
/* 111 */     pc.readData(data);
/*     */     
/* 113 */     MCH_GuiTargetMarker.markPoint(pc.px, pc.py, pc.pz);
/*     */   }
/*     */   
/* 116 */   private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
/* 117 */   private static byte[] imageData = null;
/* 118 */   private static String lastPlayerName = "";
/* 119 */   private static double lastDataPercent = 0.0D;
/*     */   
/*     */   public static void onPacket_LargeData(EntityPlayer player, ByteArrayDataInput data) {
/* 122 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 129 */       MinecraftServer minecraftServer = MinecraftServer.func_71276_C();
/* 130 */       if (minecraftServer == null)
/*     */         return; 
/* 132 */       MCH_PacketLargeData pc = new MCH_PacketLargeData();
/* 133 */       pc.readData(data);
/*     */       
/* 135 */       if (pc.imageDataIndex < 0 || pc.imageDataTotalSize <= 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 140 */       if (pc.imageDataIndex == 0) {
/*     */         
/* 142 */         if (imageData != null && !lastPlayerName.isEmpty())
/*     */         {
/* 144 */           LogError("[mcheli]Err1:Saving the %s screen shot to server FAILED!!!", new Object[] { lastPlayerName });
/*     */         }
/* 146 */         imageData = new byte[pc.imageDataTotalSize];
/* 147 */         lastPlayerName = player.getDisplayName();
/* 148 */         lastDataPercent = 0.0D;
/*     */       } 
/*     */       
/* 151 */       double dataPercent = (pc.imageDataIndex + pc.imageDataSize) / pc.imageDataTotalSize * 100.0D;
/*     */       
/* 153 */       if (dataPercent - lastDataPercent >= 10.0D || lastDataPercent == 0.0D) {
/*     */         
/* 155 */         LogInfo("[mcheli]Saving the %s screen shot to server. %.0f%% : %dbyte / %dbyte", new Object[] { player.getDisplayName(), Double.valueOf(dataPercent), Integer.valueOf(pc.imageDataIndex), Integer.valueOf(pc.imageDataTotalSize) });
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 160 */         lastDataPercent = dataPercent;
/*     */       } 
/*     */       
/* 163 */       if (imageData == null) {
/*     */         
/* 165 */         if (imageData != null && !lastPlayerName.isEmpty())
/*     */         {
/* 167 */           LogError("[mcheli]Err2:Saving the %s screen shot to server FAILED!!!", new Object[] { player.getDisplayName() });
/*     */         }
/* 169 */         imageData = null;
/* 170 */         lastPlayerName = "";
/* 171 */         lastDataPercent = 0.0D;
/*     */         
/*     */         return;
/*     */       } 
/* 175 */       for (int i = 0; i < pc.imageDataSize; i++)
/*     */       {
/* 177 */         imageData[pc.imageDataIndex + i] = pc.buf[i];
/*     */       }
/* 179 */       if (pc.imageDataIndex + pc.imageDataSize >= pc.imageDataTotalSize)
/*     */       {
/*     */         
/* 182 */         DataOutputStream dos = null;
/* 183 */         String dt = dateFormat.format(new Date()).toString();
/* 184 */         File file = new File("screenshots_op");
/* 185 */         file.mkdir();
/* 186 */         file = new File(file, player.getDisplayName() + "_" + dt + ".png");
/* 187 */         String s = file.getAbsolutePath();
/* 188 */         LogInfo("[mcheli]Save Screenshot has been completed: %s", new Object[] { s });
/* 189 */         FileOutputStream fos = new FileOutputStream(s);
/* 190 */         dos = new DataOutputStream(fos);
/* 191 */         dos.write(imageData);
/* 192 */         dos.flush();
/* 193 */         dos.close();
/*     */         
/* 195 */         imageData = null;
/* 196 */         lastPlayerName = "";
/* 197 */         lastDataPercent = 0.0D;
/*     */       }
/*     */     
/* 200 */     } catch (Exception e) {
/*     */       
/* 202 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void LogInfo(String format, Object... args) {
/* 208 */     logger.info(String.format(format, args));
/*     */   }
/*     */   
/*     */   public static void LogError(String format, Object... args) {
/* 212 */     logger.error(String.format(format, args));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onPacket_IndClient(EntityPlayer player, ByteArrayDataInput data) {
/* 217 */     if (!player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 223 */     MCH_PacketIndClient pc = new MCH_PacketIndClient();
/* 224 */     pc.readData(data);
/*     */ 
/*     */ 
/*     */     
/* 228 */     if (pc.CmdID == 1) {
/*     */       
/* 230 */       MCH_MultiplayClient.startSendImageData();
/*     */     }
/* 232 */     else if (pc.CmdID == 2) {
/*     */       
/* 234 */       MCH_MultiplayClient.sendModsInfo(player.getDisplayName(), Integer.parseInt(pc.CmdStr));
/*     */     } 
/*     */   }
/*     */   
/* 238 */   public static EntityPlayer modListRequestPlayer = null;
/* 239 */   private static int playerInfoId = 0;
/*     */ 
/*     */   
/*     */   public static int getPlayerInfoId(EntityPlayer player) {
/* 243 */     modListRequestPlayer = player;
/*     */     
/* 245 */     playerInfoId++;
/* 246 */     if (playerInfoId > 1000000)
/*     */     {
/* 248 */       playerInfoId = 1;
/*     */     }
/* 250 */     return playerInfoId;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onPacket_ModList(EntityPlayer player, ByteArrayDataInput data) {
/* 255 */     MCH_PacketModList pc = new MCH_PacketModList();
/* 256 */     pc.readData(data);
/*     */     
/* 258 */     MCH_Lib.DbgLog(player.field_70170_p, "MCH_MultiplayPacketHandler.onPacket_ModList : ID=%d, Num=%d", new Object[] { Integer.valueOf(pc.id), Integer.valueOf(pc.num) });
/*     */     
/* 260 */     if (player.field_70170_p.field_72995_K) {
/*     */       
/* 262 */       if (pc.firstData) {
/*     */         
/* 264 */         MCH_Lib.Log(EnumChatFormatting.RED + "###### " + player.getDisplayName() + " ######", new Object[0]);
/* 265 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "###### " + player.getDisplayName() + " ######"));
/*     */       } 
/*     */ 
/*     */       
/* 269 */       for (String s : pc.list)
/*     */       {
/* 271 */         MCH_Lib.Log(s, new Object[0]);
/* 272 */         player.func_145747_a((IChatComponent)new ChatComponentText(s));
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 278 */     else if (pc.id == playerInfoId) {
/*     */       
/* 280 */       if (modListRequestPlayer != null) {
/*     */ 
/*     */ 
/*     */         
/* 284 */         MCH_PacketModList.send(modListRequestPlayer, pc);
/*     */       }
/*     */       else {
/*     */         
/* 288 */         if (pc.firstData)
/*     */         {
/* 290 */           LogInfo("###### " + player.getDisplayName() + " ######", new Object[0]);
/*     */         }
/* 292 */         for (String s : pc.list)
/*     */         {
/* 294 */           LogInfo(s, new Object[0]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_MultiplayPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */