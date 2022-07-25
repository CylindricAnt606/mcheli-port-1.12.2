 package mcheli.mcheli;

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 import mcheli.mcheli.wrapper.W_Item;
 import net.minecraft.creativetab.CreativeTabs;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemStack;
 import net.minecraftforge.fml.relauncher.Side;
 import net.minecraftforge.fml.relauncher.SideOnly;

//TODO: small problems
 public class MCH_CreativeTabs extends CreativeTabs {
   private List<Item> iconItems;
   private Item lastItem;
   private int currentIconIndex;
   private int switchItemWait;
   private Item fixedItem = null;

   public MCH_CreativeTabs(String label) {
     super(label);
     this.iconItems = new ArrayList<Item>();
     this.currentIconIndex = 0;
     this.switchItemWait = 0;
   }

     public void setFixedIconItem(String itemName) {
     if (itemName.indexOf(':') >= 0) {
       this.fixedItem = W_Item.getItemByName(itemName);
       if (this.fixedItem != null) this.fixedItem.func_111206_d(itemName);
     } else {
       this.fixedItem = W_Item.getItemByName("mcheli:" + itemName);
       if (this.fixedItem != null) this.fixedItem.func_111206_d("mcheli:" + itemName);
     }
   }

   public Item func_78016_d() {
     if (this.iconItems.size() <= 0) return null;
     this.currentIconIndex = (this.currentIconIndex + 1) % this.iconItems.size();
     return this.iconItems.get(this.currentIconIndex);
   }
   @Override
   public ItemStack getTabIconItem() {
     if (this.fixedItem != null)
     {
       return new ItemStack(this.fixedItem, 1, 0);
     }

     if (this.switchItemWait > 0) {

       this.switchItemWait--;
     }
     else {

       this.lastItem = func_78016_d();
       this.switchItemWait = 60;
     }

     if (this.lastItem == null)
     {
       this.lastItem = W_Item.getItemByName("iron_block");
     }

     return new ItemStack(this.lastItem, 1, 0);
   }

   @SideOnly(Side.CLIENT)
   public void func_78018_a(List<?> list) {
     super.func_78018_a(list);

     Object object = new Object(this);

     Collections.sort(list, (Comparator<?>)object);
   }

   public void addIconItem(Item i) {
     if (i != null) this.iconItems.add(i);

   }

   public String getModName() {
     return "MC Heli";
   }
 }
