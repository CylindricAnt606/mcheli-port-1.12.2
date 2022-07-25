 package mcheli.mcheli;
 
 import mcheli.mcheli.wrapper.W_KeyBinding;
 import net.minecraft.client.settings.KeyBinding;
 
 
 
 
 public class MCH_KeyName
 {
   private final int value;
   private final String name;
   private final String description;
   
   private MCH_KeyName(int v, String n, String d) {
     this.value = v;
     this.name = n.toUpperCase();
     this.description = d;
   }
 
   
   public static int getValue(String name) {
     String n = name.toUpperCase();
     for (mcheli.mcheli.MCH_KeyName key : list) {
       
       if (key.name.compareTo(n) == 0)
       {
         return key.value;
       }
     } 
     return 0;
   }
   
   public static String getName(int value) {
     for (mcheli.mcheli.MCH_KeyName key : list) {
       
       if (key.value == value)
       {
         return key.name;
       }
     } 
     return "";
   }
   
   public static String getDescription(int value) {
     for (mcheli.mcheli.MCH_KeyName key : list) {
       
       if (key.value == value)
       {
         return key.description;
       }
     } 
     return "";
   }
   
   public static String getDescOrName(int value) {
     for (mcheli.mcheli.MCH_KeyName key : list) {
       
       if (key.value == value)
       {
         return key.description.isEmpty() ? key.name : key.description;
       }
     } 
     return "";
   }
   
   public static String getDescOrName(KeyBinding key) {
     return getDescOrName(W_KeyBinding.getKeyCode(key));
   }
   
   private static mcheli.mcheli.MCH_KeyName[] list = new mcheli.mcheli.MCH_KeyName[] { new mcheli.mcheli.MCH_KeyName(0, "NONE", ""), new mcheli.mcheli.MCH_KeyName(1, "ESCAPE", "Escape"), new mcheli.mcheli.MCH_KeyName(2, "1", ""), new mcheli.mcheli.MCH_KeyName(3, "2", ""), new mcheli.mcheli.MCH_KeyName(4, "3", ""), new mcheli.mcheli.MCH_KeyName(5, "4", ""), new mcheli.mcheli.MCH_KeyName(6, "5", ""), new mcheli.mcheli.MCH_KeyName(7, "6", ""), new mcheli.mcheli.MCH_KeyName(8, "7", ""), new mcheli.mcheli.MCH_KeyName(9, "8", ""), new mcheli.mcheli.MCH_KeyName(10, "9", ""), new mcheli.mcheli.MCH_KeyName(11, "0", ""), new mcheli.mcheli.MCH_KeyName(12, "MINUS", "-"), new mcheli.mcheli.MCH_KeyName(13, "EQUALS", "="), new mcheli.mcheli.MCH_KeyName(14, "BACK", "Backspace"), new mcheli.mcheli.MCH_KeyName(15, "TAB", "Tab"), new mcheli.mcheli.MCH_KeyName(16, "Q", ""), new mcheli.mcheli.MCH_KeyName(17, "W", ""), new mcheli.mcheli.MCH_KeyName(18, "E", ""), new mcheli.mcheli.MCH_KeyName(19, "R", ""), new mcheli.mcheli.MCH_KeyName(20, "T", ""), new mcheli.mcheli.MCH_KeyName(21, "Y", ""), new mcheli.mcheli.MCH_KeyName(22, "U", ""), new mcheli.mcheli.MCH_KeyName(23, "I", ""), new mcheli.mcheli.MCH_KeyName(24, "O", ""), new mcheli.mcheli.MCH_KeyName(25, "P", ""), new mcheli.mcheli.MCH_KeyName(26, "LBRACKET", "["), new mcheli.mcheli.MCH_KeyName(27, "RBRACKET", "]"), new mcheli.mcheli.MCH_KeyName(28, "RETURN", "Enter"), new mcheli.mcheli.MCH_KeyName(29, "LCONTROL", "L Control"), new mcheli.mcheli.MCH_KeyName(30, "A", ""), new mcheli.mcheli.MCH_KeyName(31, "S", ""), new mcheli.mcheli.MCH_KeyName(32, "D", ""), new mcheli.mcheli.MCH_KeyName(33, "F", ""), new mcheli.mcheli.MCH_KeyName(34, "G", ""), new mcheli.mcheli.MCH_KeyName(35, "H", ""), new mcheli.mcheli.MCH_KeyName(36, "J", ""), new mcheli.mcheli.MCH_KeyName(37, "K", ""), new mcheli.mcheli.MCH_KeyName(38, "L", ""), new mcheli.mcheli.MCH_KeyName(39, "SEMICOLON", " ;"), new mcheli.mcheli.MCH_KeyName(40, "APOSTROPHE", "'"), new mcheli.mcheli.MCH_KeyName(41, "GRAVE", "`"), new mcheli.mcheli.MCH_KeyName(42, "LSHIFT", "L Shift"), new mcheli.mcheli.MCH_KeyName(43, "BACKSLASH", "\\"), new mcheli.mcheli.MCH_KeyName(44, "Z", ""), new mcheli.mcheli.MCH_KeyName(45, "X", ""), new mcheli.mcheli.MCH_KeyName(46, "C", ""), new mcheli.mcheli.MCH_KeyName(47, "V", ""), new mcheli.mcheli.MCH_KeyName(48, "B", ""), new mcheli.mcheli.MCH_KeyName(49, "N", ""), new mcheli.mcheli.MCH_KeyName(50, "M", ""), new mcheli.mcheli.MCH_KeyName(51, "COMMA", ","), new mcheli.mcheli.MCH_KeyName(52, "PERIOD", "."), new mcheli.mcheli.MCH_KeyName(53, "SLASH", "/"), new mcheli.mcheli.MCH_KeyName(54, "RSHIFT", "R Shift"), new mcheli.mcheli.MCH_KeyName(55, "MULTIPLY", ""), new mcheli.mcheli.MCH_KeyName(56, "LMENU", "L Menu/Alt"), new mcheli.mcheli.MCH_KeyName(57, "SPACE", ""), new mcheli.mcheli.MCH_KeyName(58, "CAPITAL", "Caps Lock"), new mcheli.mcheli.MCH_KeyName(59, "F1", ""), new mcheli.mcheli.MCH_KeyName(60, "F2", ""), new mcheli.mcheli.MCH_KeyName(61, "F3", ""), new mcheli.mcheli.MCH_KeyName(62, "F4", ""), new mcheli.mcheli.MCH_KeyName(63, "F5", ""), new mcheli.mcheli.MCH_KeyName(64, "F6", ""), new mcheli.mcheli.MCH_KeyName(65, "F7", ""), new mcheli.mcheli.MCH_KeyName(66, "F8", ""), new mcheli.mcheli.MCH_KeyName(67, "F9", ""), new mcheli.mcheli.MCH_KeyName(68, "F10", ""), new mcheli.mcheli.MCH_KeyName(69, "NUMLOCK", "Number Lock"), new mcheli.mcheli.MCH_KeyName(70, "SCROLL", "Scroll Lock"), new mcheli.mcheli.MCH_KeyName(71, "NUMPAD7", ""), new mcheli.mcheli.MCH_KeyName(72, "NUMPAD8", ""), new mcheli.mcheli.MCH_KeyName(73, "NUMPAD9", ""), new mcheli.mcheli.MCH_KeyName(74, "SUBTRACT", ""), new mcheli.mcheli.MCH_KeyName(75, "NUMPAD4", ""), new mcheli.mcheli.MCH_KeyName(76, "NUMPAD5", ""), new mcheli.mcheli.MCH_KeyName(77, "NUMPAD6", ""), new mcheli.mcheli.MCH_KeyName(78, "ADD", ""), new mcheli.mcheli.MCH_KeyName(79, "NUMPAD1", ""), new mcheli.mcheli.MCH_KeyName(80, "NUMPAD2", ""), new mcheli.mcheli.MCH_KeyName(81, "NUMPAD3", ""), new mcheli.mcheli.MCH_KeyName(82, "NUMPAD0", ""), new mcheli.mcheli.MCH_KeyName(83, "DECIMAL", ""), new mcheli.mcheli.MCH_KeyName(87, "F11", ""), new mcheli.mcheli.MCH_KeyName(88, "F12", ""), new mcheli.mcheli.MCH_KeyName(100, "F13", ""), new mcheli.mcheli.MCH_KeyName(101, "F14", ""), new mcheli.mcheli.MCH_KeyName(102, "F15", ""), new mcheli.mcheli.MCH_KeyName(112, "KANA", ""), new mcheli.mcheli.MCH_KeyName(121, "CONVERT", ""), new mcheli.mcheli.MCH_KeyName(123, "NOCONVERT", ""), new mcheli.mcheli.MCH_KeyName(125, "YEN", "¥"), new mcheli.mcheli.MCH_KeyName(141, "NUMPADEQUALS", ""), new mcheli.mcheli.MCH_KeyName(144, "CIRCUMFLEX", "^"), new mcheli.mcheli.MCH_KeyName(145, "AT", "@"), new mcheli.mcheli.MCH_KeyName(146, "COLON", " :"), new mcheli.mcheli.MCH_KeyName(147, "UNDERLINE", "_"), new mcheli.mcheli.MCH_KeyName(148, "KANJI", ""), new mcheli.mcheli.MCH_KeyName(149, "STOP", ""), new mcheli.mcheli.MCH_KeyName(150, "AX", ""), new mcheli.mcheli.MCH_KeyName(151, "UNLABLED", ""), new mcheli.mcheli.MCH_KeyName(156, "NUMPADENTER", ""), new mcheli.mcheli.MCH_KeyName(157, "RCONTROL", "R Control"), new mcheli.mcheli.MCH_KeyName(179, "NUMPADCOMMA", ""), new mcheli.mcheli.MCH_KeyName(181, "DIVIDE", ""), new mcheli.mcheli.MCH_KeyName(183, "SYSRQ", ""), new mcheli.mcheli.MCH_KeyName(184, "RMENU", "R Menu/Alt"), new mcheli.mcheli.MCH_KeyName(197, "PAUSE", ""), new mcheli.mcheli.MCH_KeyName(199, "HOME", ""), new mcheli.mcheli.MCH_KeyName(200, "UP", "Up Arrow"), new mcheli.mcheli.MCH_KeyName(201, "PRIOR", "Page Up"), new mcheli.mcheli.MCH_KeyName(203, "LEFT", "Left Arrow"), new mcheli.mcheli.MCH_KeyName(205, "RIGHT", "Right Arrow"), new mcheli.mcheli.MCH_KeyName(207, "END", ""), new mcheli.mcheli.MCH_KeyName(208, "DOWN", "Down Arrow"), new mcheli.mcheli.MCH_KeyName(209, "NEXT", "Page Down"), new mcheli.mcheli.MCH_KeyName(210, "INSERT", ""), new mcheli.mcheli.MCH_KeyName(211, "DELETE", ""), new mcheli.mcheli.MCH_KeyName(219, "LMETA", "LWIN [3]"), new mcheli.mcheli.MCH_KeyName(220, "RMETA", "RWIN [3]"), new mcheli.mcheli.MCH_KeyName(221, "APPS", ""), new mcheli.mcheli.MCH_KeyName(222, "POWER", ""), new mcheli.mcheli.MCH_KeyName(223, "SLEEP", ""), new mcheli.mcheli.MCH_KeyName(-100, "BUTTON0", "Left Click"), new mcheli.mcheli.MCH_KeyName(-99, "BUTTON1", "Right Click"), new mcheli.mcheli.MCH_KeyName(-98, "BUTTON2", "Middle Click"), new mcheli.mcheli.MCH_KeyName(-97, "BUTTON3", ""), new mcheli.mcheli.MCH_KeyName(-96, "BUTTON4", ""), new mcheli.mcheli.MCH_KeyName(-95, "BUTTON5", ""), new mcheli.mcheli.MCH_KeyName(-94, "BUTTON6", ""), new mcheli.mcheli.MCH_KeyName(-93, "BUTTON7", ""), new mcheli.mcheli.MCH_KeyName(-92, "BUTTON8", ""), new mcheli.mcheli.MCH_KeyName(-91, "BUTTON9", ""), new mcheli.mcheli.MCH_KeyName(-90, "BUTTON10", ""), new mcheli.mcheli.MCH_KeyName(-89, "BUTTON11", ""), new mcheli.mcheli.MCH_KeyName(-88, "BUTTON12", ""), new mcheli.mcheli.MCH_KeyName(-87, "BUTTON13", ""), new mcheli.mcheli.MCH_KeyName(-86, "BUTTON14", ""), new mcheli.mcheli.MCH_KeyName(-85, "BUTTON15", "") };
 }
