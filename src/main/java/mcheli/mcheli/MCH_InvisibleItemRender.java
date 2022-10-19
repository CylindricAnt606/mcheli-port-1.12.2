 package mcheli.mcheli;

 import net.minecraft.item.ItemStack;

 //TODO: IItemRenderer ?
 public class MCH_InvisibleItemRender
   implements IItemRenderer
 {
   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
   }

   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
     return false;
   } public boolean useCurrentWeapon() {
     return false;
   }

   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {}
 }
