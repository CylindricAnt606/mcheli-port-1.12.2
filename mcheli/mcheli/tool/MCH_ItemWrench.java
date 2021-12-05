/*     */ package mcheli.mcheli.tool;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ItemWrench
/*     */   extends W_Item
/*     */ {
/*     */   private float damageVsEntity;
/*     */   private final Item.ToolMaterial toolMaterial;
/*  40 */   private static Random rand = new Random();
/*     */ 
/*     */   
/*     */   public MCH_ItemWrench(int itemId, Item.ToolMaterial material) {
/*  44 */     super(itemId);
/*  45 */     this.toolMaterial = material;
/*  46 */     this.field_77777_bU = 1;
/*  47 */     func_77656_e(material.func_77997_a());
/*     */     
/*  49 */     this.damageVsEntity = 4.0F + material.func_78000_c();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_150897_b(Block b) {
/*  54 */     Material material = b.func_149688_o();
/*  55 */     if (material == Material.field_151573_f)
/*     */     {
/*  57 */       return true;
/*     */     }
/*  59 */     if (material instanceof net.minecraft.block.material.MaterialLogic)
/*     */     {
/*  61 */       return true;
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_150893_a(ItemStack itemStack, Block block) {
/*  70 */     Material material = block.func_149688_o();
/*  71 */     if (material == Material.field_151573_f)
/*     */     {
/*  73 */       return 20.5F;
/*     */     }
/*  75 */     if (material instanceof net.minecraft.block.material.MaterialLogic)
/*     */     {
/*  77 */       return 5.5F;
/*     */     }
/*  79 */     return 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getUseAnimCount(ItemStack stack) {
/*  84 */     return getAnimCount(stack, "MCH_WrenchAnim");
/*     */   }
/*     */   
/*     */   public static void setUseAnimCount(ItemStack stack, int n) {
/*  88 */     setAnimCount(stack, "MCH_WrenchAnim", n);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getAnimCount(ItemStack stack, String name) {
/*  93 */     if (!stack.func_77942_o())
/*     */     {
/*  95 */       stack.field_77990_d = new NBTTagCompound();
/*     */     }
/*     */     
/*  98 */     if (stack.field_77990_d.func_74764_b(name))
/*     */     {
/* 100 */       return stack.field_77990_d.func_74762_e(name);
/*     */     }
/*     */ 
/*     */     
/* 104 */     stack.field_77990_d.func_74768_a(name, 0);
/*     */     
/* 106 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setAnimCount(ItemStack stack, String name, int n) {
/* 110 */     if (!stack.func_77942_o())
/*     */     {
/* 112 */       stack.field_77990_d = new NBTTagCompound();
/*     */     }
/*     */     
/* 115 */     stack.field_77990_d.func_74768_a(name, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77644_a(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player) {
/* 124 */     if (!player.field_70170_p.field_72995_K)
/*     */     {
/* 126 */       if (rand.nextInt(40) == 0) {
/*     */         
/* 128 */         entity.func_70099_a(new ItemStack(W_Item.getItemByName("iron_ingot"), 1, 0), 0.0F);
/*     */       }
/* 130 */       else if (rand.nextInt(20) == 0) {
/*     */         
/* 132 */         entity.func_70099_a(new ItemStack(W_Item.getItemByName("gunpowder"), 1, 0), 0.0F);
/*     */       } 
/*     */     }
/*     */     
/* 136 */     itemStack.func_77972_a(2, player);
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count) {
/* 142 */     setUseAnimCount(stack, 0);
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
/* 146 */     if (player.field_70170_p.field_72995_K) {
/*     */       
/* 148 */       MCH_EntityAircraft ac = getMouseOverAircraft(player);
/* 149 */       if (ac != null) {
/*     */         
/* 151 */         int cnt = getUseAnimCount(stack);
/* 152 */         if (cnt <= 0) {
/*     */           
/* 154 */           cnt = 16;
/*     */         }
/*     */         else {
/*     */           
/* 158 */           cnt--;
/*     */         } 
/*     */         
/* 161 */         setUseAnimCount(stack, cnt);
/*     */       } 
/*     */     } 
/* 164 */     if (!player.field_70170_p.field_72995_K && count < func_77626_a(stack) && count % 20 == 0) {
/*     */       
/* 166 */       MCH_EntityAircraft ac = getMouseOverAircraft(player);
/*     */       
/* 168 */       if (ac != null && ac.getHP() > 0 && ac.repair(10)) {
/*     */         
/* 170 */         stack.func_77972_a(1, (EntityLivingBase)player);
/*     */         
/* 172 */         W_WorldFunc.MOD_playSoundEffect(player.field_70170_p, (int)ac.field_70165_t, (int)ac.field_70163_u, (int)ac.field_70161_v, "wrench", 1.0F, 0.9F + rand.nextFloat() * 0.2F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack item, World world, Entity entity, int n, boolean b) {
/* 183 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 185 */       EntityPlayer player = (EntityPlayer)entity;
/* 186 */       ItemStack itemStack = player.func_71045_bC();
/* 187 */       if (itemStack == item)
/*     */       {
/* 189 */         MCH_MOD.proxy.setCreativeDigDelay(0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityAircraft getMouseOverAircraft(EntityPlayer player) {
/* 197 */     MovingObjectPosition m = getMouseOver((EntityLivingBase)player, 1.0F);
/* 198 */     MCH_EntityAircraft ac = null;
/* 199 */     if (m != null)
/*     */     {
/* 201 */       if (m.field_72308_g instanceof MCH_EntityAircraft) {
/*     */         
/* 203 */         ac = (MCH_EntityAircraft)m.field_72308_g;
/*     */       }
/* 205 */       else if (m.field_72308_g instanceof MCH_EntitySeat) {
/*     */         
/* 207 */         MCH_EntitySeat seat = (MCH_EntitySeat)m.field_72308_g;
/* 208 */         if (seat.getParent() != null)
/*     */         {
/* 210 */           ac = seat.getParent();
/*     */         }
/*     */       } 
/*     */     }
/* 214 */     return ac;
/*     */   }
/*     */ 
/*     */   
/*     */   private static MovingObjectPosition rayTrace(EntityLivingBase entity, double dist, float tick) {
/* 219 */     Vec3 vec3 = Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v);
/* 220 */     Vec3 vec31 = entity.func_70676_i(tick);
/* 221 */     Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * dist, vec31.field_72448_b * dist, vec31.field_72449_c * dist);
/* 222 */     return entity.field_70170_p.func_147447_a(vec3, vec32, false, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private MovingObjectPosition getMouseOver(EntityLivingBase user, float tick) {
/* 227 */     Entity pointedEntity = null;
/* 228 */     double d0 = 4.0D;
/* 229 */     MovingObjectPosition objectMouseOver = rayTrace(user, d0, tick);
/* 230 */     double d1 = d0;
/* 231 */     Vec3 vec3 = Vec3.func_72443_a(user.field_70165_t, user.field_70163_u + user.func_70047_e(), user.field_70161_v);
/*     */     
/* 233 */     if (objectMouseOver != null)
/*     */     {
/* 235 */       d1 = objectMouseOver.field_72307_f.func_72438_d(vec3);
/*     */     }
/*     */     
/* 238 */     Vec3 vec31 = user.func_70676_i(tick);
/* 239 */     Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0);
/* 240 */     pointedEntity = null;
/* 241 */     Vec3 vec33 = null;
/* 242 */     float f1 = 1.0F;
/* 243 */     List<Entity> list = user.field_70170_p.func_72839_b((Entity)user, user.field_70121_D.func_72321_a(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0).func_72314_b(f1, f1, f1));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     double d2 = d1;
/*     */     
/* 250 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 252 */       Entity entity = list.get(i);
/*     */       
/* 254 */       if (entity.func_70067_L()) {
/*     */         
/* 256 */         float f2 = entity.func_70111_Y();
/* 257 */         AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f2, f2, f2);
/* 258 */         MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3, vec32);
/*     */         
/* 260 */         if (axisalignedbb.func_72318_a(vec3)) {
/*     */           
/* 262 */           if (0.0D < d2 || d2 == 0.0D)
/*     */           {
/* 264 */             pointedEntity = entity;
/* 265 */             vec33 = (movingobjectposition == null) ? vec3 : movingobjectposition.field_72307_f;
/* 266 */             d2 = 0.0D;
/*     */           }
/*     */         
/* 269 */         } else if (movingobjectposition != null) {
/*     */           
/* 271 */           double d3 = vec3.func_72438_d(movingobjectposition.field_72307_f);
/*     */           
/* 273 */           if (d3 < d2 || d2 == 0.0D)
/*     */           {
/* 275 */             if (entity == user.field_70154_o && !entity.canRiderInteract()) {
/*     */               
/* 277 */               if (d2 == 0.0D)
/*     */               {
/* 279 */                 pointedEntity = entity;
/* 280 */                 vec33 = movingobjectposition.field_72307_f;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 285 */               pointedEntity = entity;
/* 286 */               vec33 = movingobjectposition.field_72307_f;
/* 287 */               d2 = d3;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 294 */     if (pointedEntity != null && (d2 < d1 || objectMouseOver == null))
/*     */     {
/* 296 */       objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);
/*     */     }
/*     */     
/* 299 */     return objectMouseOver;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_150894_a(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
/* 305 */     if (block.func_149712_f(world, x, y, z) != 0.0D)
/*     */     {
/* 307 */       itemStack.func_77972_a(2, entity);
/*     */     }
/*     */     
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d() {
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack itemStack) {
/* 328 */     return EnumAction.block;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack itemStack) {
/* 336 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/* 345 */     player.func_71008_a(itemStack, func_77626_a(itemStack));
/* 346 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/* 355 */     return this.toolMaterial.func_77995_e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToolMaterialName() {
/* 363 */     return this.toolMaterial.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_82789_a(ItemStack item1, ItemStack item2) {
/* 371 */     return (this.toolMaterial.func_150995_f() == item2.func_77973_b()) ? true : super.func_82789_a(item1, item2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap func_111205_h() {
/* 380 */     Multimap multimap = super.func_111205_h();
/* 381 */     multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.damageVsEntity, 0));
/*     */ 
/*     */ 
/*     */     
/* 385 */     return multimap;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\MCH_ItemWrench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */