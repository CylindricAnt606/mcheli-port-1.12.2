/*     */ package mcheli.mcheli.command;
/*     */ 
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.MCH_PacketNotifyServerSettings;
/*     */ import mcheli.command.MCH_PacketTitle;
/*     */ import mcheli.multiplay.MCH_MultiplayPacketHandler;
/*     */ import mcheli.multiplay.MCH_PacketIndClient;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.CommandGameMode;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.SyntaxErrorException;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.CommandEvent;
/*     */ import org.apache.commons.lang3.exception.ExceptionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_Command
/*     */   extends CommandBase
/*     */ {
/*     */   public static final String CMD_GET_SS = "sendss";
/*     */   public static final String CMD_MOD_LIST = "modlist";
/*     */   public static final String CMD_RECONFIG = "reconfig";
/*     */   public static final String CMD_TITLE = "title";
/*     */   public static final String CMD_FILL = "fill";
/*     */   public static final String CMD_STATUS = "status";
/*     */   public static final String CMD_KILL_ENTITY = "killentity";
/*     */   public static final String CMD_REMOVE_ENTITY = "removeentity";
/*     */   public static final String CMD_ATTACK_ENTITY = "attackentity";
/*     */   public static final String CMD_SHOW_BB = "showboundingbox";
/*     */   public static final String CMD_LIST = "list";
/*  62 */   public static String[] ALL_COMMAND = new String[] { "sendss", "modlist", "reconfig", "title", "fill", "status", "killentity", "removeentity", "attackentity", "showboundingbox", "list" };
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
/*  77 */   public static mcheli.command.MCH_Command instance = new mcheli.command.MCH_Command();
/*     */ 
/*     */   
/*     */   public static boolean canUseCommand(Entity player) {
/*  81 */     return (player instanceof EntityPlayer) ? instance.func_71519_b((ICommandSender)player) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  87 */     return "mcheli";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkCommandPermission(ICommandSender sender, String cmd) {
/*  93 */     if ((new CommandGameMode()).func_71519_b(sender))
/*     */     {
/*  95 */       return true;
/*     */     }
/*     */     
/*  98 */     if (sender instanceof EntityPlayer && cmd.length() > 0) {
/*     */       
/* 100 */       String playerName = ((EntityPlayer)sender).func_146103_bH().getName();
/*     */ 
/*     */       
/* 103 */       for (MCH_Config.CommandPermission c : MCH_Config.CommandPermissionList) {
/*     */         
/* 105 */         if (c.name.equals(cmd))
/*     */         {
/*     */           
/* 108 */           for (String s : c.players) {
/*     */             
/* 110 */             if (s.equalsIgnoreCase(playerName))
/*     */             {
/* 112 */               return true;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void onCommandEvent(CommandEvent event) {
/* 124 */     if (!(event.command instanceof mcheli.command.MCH_Command)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 129 */     if (event.parameters.length <= 0 || event.parameters[0].length() <= 0) {
/*     */       
/* 131 */       event.setCanceled(true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 136 */     if (!checkCommandPermission(event.sender, event.parameters[0])) {
/*     */       
/* 138 */       event.setCanceled(true);
/* 139 */       ChatComponentTranslation c = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
/* 140 */       c.func_150256_b().func_150238_a(EnumChatFormatting.RED);
/* 141 */       event.sender.func_145747_a((IChatComponent)c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender player) {
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender p_71518_1_) {
/* 153 */     return "commands.mcheli.usage";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] prm) {
/* 159 */     if (!MCH_Config.EnableCommand.prmBool)
/*     */       return; 
/* 161 */     if (!checkCommandPermission(sender, prm[0])) {
/*     */       
/* 163 */       ChatComponentTranslation c = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
/* 164 */       c.func_150256_b().func_150238_a(EnumChatFormatting.RED);
/* 165 */       sender.func_145747_a((IChatComponent)c);
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     if (prm[0].equalsIgnoreCase("sendss")) {
/*     */       
/* 171 */       if (prm.length == 2)
/*     */       {
/* 173 */         EntityPlayerMP player = func_82359_c(sender, prm[1]);
/* 174 */         if (player != null)
/*     */         {
/* 176 */           MCH_PacketIndClient.send((EntityPlayer)player, 1, prm[1]);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 181 */         throw new CommandException("Parameter error! : /mcheli sendss playerName", new Object[0]);
/*     */       }
/*     */     
/* 184 */     } else if (prm[0].equalsIgnoreCase("modlist")) {
/*     */       
/* 186 */       if (prm.length == 2)
/*     */       {
/* 188 */         EntityPlayerMP reqPlayer = (sender instanceof EntityPlayerMP) ? (EntityPlayerMP)sender : null;
/* 189 */         EntityPlayerMP player = func_82359_c(sender, prm[1]);
/* 190 */         if (player != null)
/*     */         {
/* 192 */           MCH_PacketIndClient.send((EntityPlayer)player, 2, "" + MCH_MultiplayPacketHandler.getPlayerInfoId((EntityPlayer)reqPlayer));
/*     */         
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 198 */         throw new CommandException("Parameter error! : /mcheli modlist playerName", new Object[0]);
/*     */       }
/*     */     
/* 201 */     } else if (prm[0].equalsIgnoreCase("reconfig")) {
/*     */       
/* 203 */       if (prm.length == 1) {
/*     */         
/* 205 */         MCH_MOD.proxy.reconfig();
/* 206 */         if (sender.func_130014_f_() != null)
/*     */         {
/* 208 */           if (!(sender.func_130014_f_()).field_72995_K)
/*     */           {
/* 210 */             MCH_PacketNotifyServerSettings.sendAll();
/*     */           }
/*     */         }
/*     */         
/* 214 */         if (MCH_MOD.proxy.isSinglePlayer())
/*     */         {
/* 216 */           sender.func_145747_a((IChatComponent)new ChatComponentText("Reload mcheli.cfg"));
/*     */         }
/*     */         else
/*     */         {
/* 220 */           sender.func_145747_a((IChatComponent)new ChatComponentText("Reload server side mcheli.cfg"));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 225 */         throw new CommandException("Parameter error! : /mcheli reconfig", new Object[0]);
/*     */       }
/*     */     
/* 228 */     } else if (prm[0].equalsIgnoreCase("title")) {
/*     */       
/* 230 */       if (prm.length < 4)
/*     */       {
/* 232 */         throw new WrongUsageException("Parameter error! : /mcheli title time[1~180] position[0~4] messege[JSON format]", new Object[0]);
/*     */       }
/*     */ 
/*     */       
/* 236 */       String s = func_82360_a(sender, prm, 3);
/*     */       
/* 238 */       int showTime = Integer.valueOf(prm[1]).intValue();
/* 239 */       if (showTime < 1) showTime = 1; 
/* 240 */       if (showTime > 180) showTime = 180;
/*     */       
/* 242 */       int pos = Integer.valueOf(prm[2]).intValue();
/* 243 */       if (pos < 0) pos = 0; 
/* 244 */       if (pos > 5) pos = 5;
/*     */ 
/*     */       
/*     */       try {
/* 248 */         IChatComponent ichatcomponent = IChatComponent.Serializer.func_150699_a(s);
/* 249 */         MCH_PacketTitle.send(ichatcomponent, 20 * showTime, pos);
/*     */       }
/* 251 */       catch (JsonParseException jsonparseexception) {
/*     */         
/* 253 */         Throwable throwable = ExceptionUtils.getRootCause((Throwable)jsonparseexception);
/* 254 */         throw new SyntaxErrorException("mcheli.title.jsonException", new Object[] { (throwable == null) ? "" : throwable.getMessage() });
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 259 */     else if (prm[0].equalsIgnoreCase("fill")) {
/*     */       
/* 261 */       executeFill(sender, prm);
/*     */     }
/* 263 */     else if (prm[0].equalsIgnoreCase("status")) {
/*     */       
/* 265 */       executeStatus(sender, prm);
/*     */     }
/* 267 */     else if (prm[0].equalsIgnoreCase("killentity")) {
/*     */       
/* 269 */       executeKillEntity(sender, prm);
/*     */     }
/* 271 */     else if (prm[0].equalsIgnoreCase("removeentity")) {
/*     */       
/* 273 */       executeRemoveEntity(sender, prm);
/*     */     }
/* 275 */     else if (prm[0].equalsIgnoreCase("attackentity")) {
/*     */       
/* 277 */       executeAttackEntity(sender, prm);
/*     */     }
/* 279 */     else if (prm[0].equalsIgnoreCase("showboundingbox")) {
/*     */       
/* 281 */       if (prm.length != 2)
/*     */       {
/* 283 */         throw new CommandException("Parameter error! : /mcheli showboundingbox true or false", new Object[0]);
/*     */       }
/*     */       
/* 286 */       if (!func_110662_c(sender, prm[1]))
/*     */       {
/* 288 */         MCH_Config.EnableDebugBoundingBox.prmBool = false;
/* 289 */         MCH_PacketNotifyServerSettings.sendAll();
/* 290 */         sender.func_145747_a((IChatComponent)new ChatComponentText("Disabled bounding box"));
/*     */       }
/*     */       else
/*     */       {
/* 294 */         MCH_Config.EnableDebugBoundingBox.prmBool = true;
/* 295 */         MCH_PacketNotifyServerSettings.sendAll();
/* 296 */         sender.func_145747_a((IChatComponent)new ChatComponentText("Enabled bounding box [F3 + b]"));
/*     */       }
/*     */     
/* 299 */     } else if (prm[0].equalsIgnoreCase("list")) {
/*     */       
/* 301 */       String msg = "";
/* 302 */       for (String s : ALL_COMMAND) msg = msg + s + ", "; 
/* 303 */       sender.func_145747_a((IChatComponent)new ChatComponentText("/mcheli command list : " + msg));
/*     */     }
/*     */     else {
/*     */       
/* 307 */       throw new CommandException("Unknown mcheli command. please type /mcheli list", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void executeAttackEntity(ICommandSender sender, String[] args) {
/* 313 */     if (args.length < 3)
/*     */     {
/* 315 */       throw new WrongUsageException("/mcheli attackentity <entity class name : example1 EntityBat , example2 minecraft.entity.passive> <damage> [damage source]", new Object[0]);
/*     */     }
/*     */     
/* 318 */     String className = args[1].toLowerCase();
/* 319 */     float damage = Float.valueOf(args[2]).floatValue();
/* 320 */     String damageName = (args.length >= 4) ? args[3].toLowerCase() : "";
/*     */     
/* 322 */     DamageSource ds = DamageSource.field_76377_j;
/* 323 */     if (!damageName.isEmpty())
/*     */     {
/* 325 */       if (damageName.equals("player"))
/*     */       
/* 327 */       { if (sender instanceof EntityPlayer)
/*     */         {
/* 329 */           ds = DamageSource.func_76365_a((EntityPlayer)sender);
/*     */         } }
/*     */       
/* 332 */       else if (damageName.equals("anvil")) { ds = DamageSource.field_82728_o; }
/* 333 */       else if (damageName.equals("cactus")) { ds = DamageSource.field_76367_g; }
/* 334 */       else if (damageName.equals("drown")) { ds = DamageSource.field_76369_e; }
/* 335 */       else if (damageName.equals("fall")) { ds = DamageSource.field_76379_h; }
/* 336 */       else if (damageName.equals("fallingblock")) { ds = DamageSource.field_82729_p; }
/* 337 */       else if (damageName.equals("generic")) { ds = DamageSource.field_76377_j; }
/* 338 */       else if (damageName.equals("infire")) { ds = DamageSource.field_76372_a; }
/* 339 */       else if (damageName.equals("inwall")) { ds = DamageSource.field_76368_d; }
/* 340 */       else if (damageName.equals("lava")) { ds = DamageSource.field_76371_c; }
/* 341 */       else if (damageName.equals("magic")) { ds = DamageSource.field_76376_m; }
/* 342 */       else if (damageName.equals("onfire")) { ds = DamageSource.field_76370_b; }
/* 343 */       else if (damageName.equals("starve")) { ds = DamageSource.field_76366_f; }
/* 344 */       else if (damageName.equals("wither")) { ds = DamageSource.field_82727_n; }
/*     */     
/*     */     }
/* 347 */     int attacked = 0;
/* 348 */     List<E> list = (sender.func_130014_f_()).field_72996_f;
/* 349 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 351 */       if (list.get(i) != null && !(list.get(i) instanceof EntityPlayer))
/*     */       {
/* 353 */         if (list.get(i).getClass().getName().toLowerCase().indexOf(className) >= 0) {
/*     */           
/* 355 */           ((Entity)list.get(i)).func_70097_a(ds, damage);
/* 356 */           attacked++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 361 */     sender.func_145747_a((IChatComponent)new ChatComponentText(attacked + " entity attacked(" + args[1] + ", damage=" + damage + ")."));
/*     */   }
/*     */ 
/*     */   
/*     */   private void executeKillEntity(ICommandSender sender, String[] args) {
/* 366 */     if (args.length < 2)
/*     */     {
/* 368 */       throw new WrongUsageException("/mcheli killentity <entity class name : example1 EntityBat , example2 minecraft.entity.passive>", new Object[0]);
/*     */     }
/*     */     
/* 371 */     String className = args[1].toLowerCase();
/* 372 */     int killed = 0;
/* 373 */     List<E> list = (sender.func_130014_f_()).field_72996_f;
/* 374 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 376 */       if (list.get(i) != null && !(list.get(i) instanceof EntityPlayer))
/*     */       {
/* 378 */         if (list.get(i).getClass().getName().toLowerCase().indexOf(className) >= 0) {
/*     */           
/* 380 */           ((Entity)list.get(i)).func_70106_y();
/* 381 */           killed++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 386 */     sender.func_145747_a((IChatComponent)new ChatComponentText(killed + " entity killed(" + args[1] + ")."));
/*     */   }
/*     */   
/*     */   private void executeRemoveEntity(ICommandSender sender, String[] args) {
/* 390 */     if (args.length < 2)
/*     */     {
/* 392 */       throw new WrongUsageException("/mcheli removeentity <entity class name : example1 EntityBat , example2 minecraft.entity.passive>", new Object[0]);
/*     */     }
/*     */     
/* 395 */     String className = args[1].toLowerCase();
/* 396 */     List<E> list = (sender.func_130014_f_()).field_72996_f;
/* 397 */     int removed = 0;
/* 398 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 400 */       if (list.get(i) != null && !(list.get(i) instanceof EntityPlayer))
/*     */       {
/* 402 */         if (list.get(i).getClass().getName().toLowerCase().indexOf(className) >= 0) {
/*     */           
/* 404 */           ((Entity)list.get(i)).field_70128_L = true;
/* 405 */           removed++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 410 */     sender.func_145747_a((IChatComponent)new ChatComponentText(removed + " entity removed(" + args[1] + ")."));
/*     */   }
/*     */ 
/*     */   
/*     */   private void executeStatus(ICommandSender sender, String[] args) {
/* 415 */     if (args.length < 2)
/*     */     {
/* 417 */       throw new WrongUsageException("/mcheli status <entity or tile> [min num]", new Object[0]);
/*     */     }
/* 419 */     if (args[1].equalsIgnoreCase("entity")) {
/*     */       
/* 421 */       executeStatusSub(sender, args, "Server loaded Entity List", (sender.func_130014_f_()).field_72996_f);
/*     */     }
/* 423 */     else if (args[1].equalsIgnoreCase("tile")) {
/*     */       
/* 425 */       executeStatusSub(sender, args, "Server loaded Tile Entity List", (sender.func_130014_f_()).field_147482_g);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeStatusSub(ICommandSender sender, String[] args, String title, List<E> list) {
/* 430 */     int minNum = (args.length >= 3) ? Integer.valueOf(args[2]).intValue() : 0;
/* 431 */     HashMap<String, Integer> map = new HashMap<String, Integer>();
/* 432 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 434 */       String key = list.get(i).getClass().getName();
/* 435 */       if (map.containsKey(key)) {
/*     */         
/* 437 */         map.put(key, Integer.valueOf(((Integer)map.get(key)).intValue() + 1));
/*     */       }
/*     */       else {
/*     */         
/* 441 */         map.put(key, Integer.valueOf(1));
/*     */       } 
/*     */     } 
/*     */     
/* 445 */     List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
/* 446 */     Collections.sort(entries, (Comparator<? super Map.Entry<String, Integer>>)new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 456 */     boolean send = false;
/* 457 */     sender.func_145747_a((IChatComponent)new ChatComponentText("--- " + title + " ---"));
/* 458 */     for (Map.Entry<String, Integer> s : entries) {
/*     */       
/* 460 */       if (((Integer)s.getValue()).intValue() >= minNum) {
/*     */         
/* 462 */         String msg = " " + (String)s.getKey() + " : " + s.getValue();
/* 463 */         System.out.println(msg);
/* 464 */         sender.func_145747_a((IChatComponent)new ChatComponentText(msg));
/* 465 */         send = true;
/*     */       } 
/*     */     } 
/* 468 */     if (!send) {
/*     */       
/* 470 */       System.out.println("none");
/* 471 */       sender.func_145747_a((IChatComponent)new ChatComponentText("none"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeFill(ICommandSender sender, String[] args) {
/* 477 */     if (args.length < 8)
/*     */     {
/* 479 */       throw new WrongUsageException("/mcheli fill <x1> <y1> <z1> <x2> <y2> <z2> <block name> [meta data] [oldBlockHandling] [data tag]", new Object[0]);
/*     */     }
/* 481 */     int x1 = (sender.func_82114_b()).field_71574_a;
/* 482 */     int y1 = (sender.func_82114_b()).field_71572_b;
/* 483 */     int z1 = (sender.func_82114_b()).field_71573_c;
/* 484 */     int x2 = (sender.func_82114_b()).field_71574_a;
/* 485 */     int y2 = (sender.func_82114_b()).field_71572_b;
/* 486 */     int z2 = (sender.func_82114_b()).field_71573_c;
/* 487 */     x1 = MathHelper.func_76128_c(func_110666_a(sender, x1, args[1]));
/* 488 */     y1 = MathHelper.func_76128_c(func_110666_a(sender, y1, args[2]));
/* 489 */     z1 = MathHelper.func_76128_c(func_110666_a(sender, z1, args[3]));
/* 490 */     x2 = MathHelper.func_76128_c(func_110666_a(sender, x2, args[4]));
/* 491 */     y2 = MathHelper.func_76128_c(func_110666_a(sender, y2, args[5]));
/* 492 */     z2 = MathHelper.func_76128_c(func_110666_a(sender, z2, args[6]));
/* 493 */     Block block = CommandBase.func_147180_g(sender, args[7]);
/*     */     
/* 495 */     int metadata = 0;
/* 496 */     if (args.length >= 9)
/*     */     {
/* 498 */       metadata = func_71532_a(sender, args[8], 0, 15);
/*     */     }
/*     */     
/* 501 */     World world = sender.func_130014_f_();
/*     */ 
/*     */     
/* 504 */     if (x1 > x2) { int t = x1; x1 = x2; x2 = t; }
/* 505 */      if (y1 > y2) { int t = y1; y1 = y2; y2 = t; }
/* 506 */      if (z1 > z2) { int t = z1; z1 = z2; z2 = t; }
/*     */     
/* 508 */     if (y1 < 0 || y2 >= 256)
/*     */     {
/* 510 */       throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/* 513 */     int blockNum = (x2 - x1 + 1) * (y2 - y1 + 1) * (z2 - z1 + 1);
/*     */     
/* 515 */     if (blockNum > 3000000)
/*     */     {
/* 517 */       throw new CommandException("commands.setblock.tooManyBlocks " + blockNum + " limit=327680", new Object[] { Integer.valueOf(blockNum), Integer.valueOf(3276800) });
/*     */     }
/*     */ 
/*     */     
/* 521 */     boolean result = false;
/* 522 */     boolean keep = (args.length >= 10 && args[9].equals("keep"));
/* 523 */     boolean destroy = (args.length >= 10 && args[9].equals("destroy"));
/* 524 */     boolean override = (args.length >= 10 && args[9].equals("override"));
/*     */     
/* 526 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 527 */     boolean flag = false;
/*     */     
/* 529 */     if (args.length >= 11 && block.func_149716_u()) {
/*     */       
/* 531 */       String s = func_147178_a(sender, args, 10).func_150260_c();
/*     */ 
/*     */       
/*     */       try {
/* 535 */         NBTBase nbtbase = JsonToNBT.func_150315_a(s);
/*     */         
/* 537 */         if (!(nbtbase instanceof NBTTagCompound))
/*     */         {
/* 539 */           throw new CommandException("commands.setblock.tagError", new Object[] { "Not a valid tag" });
/*     */         }
/*     */         
/* 542 */         nbttagcompound = (NBTTagCompound)nbtbase;
/* 543 */         flag = true;
/*     */       }
/* 545 */       catch (NBTException nbtexception) {
/*     */         
/* 547 */         throw new CommandException("commands.setblock.tagError", new Object[] { nbtexception.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/* 551 */     for (int x = x1; x <= x2; x++) {
/* 552 */       for (int y = y1; y <= y2; y++) {
/* 553 */         for (int z = z1; z <= z2; z++) {
/*     */           
/* 555 */           if (!world.func_72899_e(x, y, z)) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 560 */           if (world.func_147437_c(x, y, z)) {
/*     */             
/* 562 */             if (override)
/*     */             {
/*     */               continue;
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 569 */           else if (keep) {
/*     */             continue;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 575 */           if (destroy)
/*     */           {
/* 577 */             world.func_147480_a(x, y, z, false);
/*     */           }
/*     */           
/* 580 */           TileEntity block2 = world.func_147438_o(x, y, z);
/* 581 */           if (block2 instanceof IInventory) {
/*     */             
/* 583 */             IInventory ii = (IInventory)block2;
/* 584 */             for (int i = 0; i < ii.func_70302_i_(); i++) {
/*     */               
/* 586 */               ItemStack is = ii.func_70304_b(i);
/* 587 */               if (is != null) is.field_77994_a = 0;
/*     */             
/*     */             } 
/*     */           } 
/* 591 */           if (world.func_147465_d(x, y, z, block, metadata, 3))
/*     */           
/* 593 */           { if (flag) {
/*     */               
/* 595 */               TileEntity tileentity = world.func_147438_o(x, y, z);
/*     */               
/* 597 */               if (tileentity != null) {
/*     */                 
/* 599 */                 nbttagcompound.func_74768_a("x", x);
/* 600 */                 nbttagcompound.func_74768_a("y", y);
/* 601 */                 nbttagcompound.func_74768_a("z", z);
/* 602 */                 tileentity.func_145839_a(nbttagcompound);
/*     */               } 
/*     */             } 
/*     */             
/* 606 */             result = true; }  continue;
/*     */         } 
/*     */       } 
/* 609 */     }  if (result) {
/*     */       
/* 611 */       func_152373_a(sender, (ICommand)this, "commands.setblock.success", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 615 */       throw new CommandException("commands.setblock.noChange", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_71516_a(ICommandSender sender, String[] prm) {
/* 621 */     if (!MCH_Config.EnableCommand.prmBool) return null;
/*     */     
/* 623 */     if (prm.length <= 1)
/*     */     {
/* 625 */       return func_71530_a(prm, ALL_COMMAND);
/*     */     }
/* 627 */     if (prm[0].equalsIgnoreCase("sendss")) {
/*     */       
/* 629 */       if (prm.length == 2)
/*     */       {
/* 631 */         return func_71530_a(prm, MinecraftServer.func_71276_C().func_71213_z());
/*     */       }
/*     */     }
/* 634 */     else if (prm[0].equalsIgnoreCase("modlist")) {
/*     */       
/* 636 */       if (prm.length == 3)
/*     */       {
/* 638 */         return func_71530_a(prm, MinecraftServer.func_71276_C().func_71213_z());
/*     */       }
/*     */     } else {
/* 641 */       if (prm[0].equalsIgnoreCase("fill")) {
/*     */         
/* 643 */         if ((prm.length == 2 || prm.length == 5) && sender instanceof Entity) {
/*     */           
/* 645 */           Entity entity = (Entity)sender;
/* 646 */           List<String> a = new ArrayList();
/* 647 */           int x = (entity.field_70165_t < 0.0D) ? (int)(entity.field_70165_t - 1.0D) : (int)entity.field_70165_t;
/* 648 */           int z = (entity.field_70161_v < 0.0D) ? (int)(entity.field_70161_v - 1.0D) : (int)entity.field_70161_v;
/* 649 */           a.add("" + x + " " + (int)(entity.field_70163_u + 0.5D) + " " + z);
/* 650 */           return a;
/*     */         } 
/*     */         
/* 653 */         return (prm.length == 8) ? func_71531_a(prm, Block.field_149771_c.func_148742_b()) : ((prm.length == 10) ? func_71530_a(prm, new String[] { "replace", "destroy", "keep", "override" }) : null);
/*     */       } 
/*     */ 
/*     */       
/* 657 */       if (prm[0].equalsIgnoreCase("status")) {
/*     */         
/* 659 */         if (prm.length == 2)
/*     */         {
/* 661 */           return func_71530_a(prm, new String[] { "entity", "tile" });
/*     */         
/*     */         }
/*     */       }
/* 665 */       else if (prm[0].equalsIgnoreCase("attackentity")) {
/*     */         
/* 667 */         if (prm.length == 4)
/*     */         {
/* 669 */           return func_71530_a(prm, new String[] { "player", "inFire", "onFire", "lava", "inWall", "drown", "starve", "cactus", "fall", "outOfWorld", "generic", "magic", "wither", "anvil", "fallingBlock" });
/*     */         
/*     */         }
/*     */       }
/* 673 */       else if (prm[0].equalsIgnoreCase("showboundingbox")) {
/*     */         
/* 675 */         if (prm.length == 2)
/*     */         {
/* 677 */           return func_71530_a(prm, new String[] { "true", "false" });
/*     */         }
/*     */       } 
/*     */     } 
/* 681 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\command\MCH_Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */