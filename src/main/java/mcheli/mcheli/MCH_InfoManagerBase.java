 package mcheli.mcheli;

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileFilter;
 import java.util.Map;
 import mcheli.mcheli.MCH_BaseInfo;
 import mcheli.mcheli.MCH_InputFile;
 import mcheli.mcheli.MCH_Lib;

 public abstract class MCH_InfoManagerBase {
   public abstract MCH_BaseInfo newInfo(String paramString);

   public boolean load(String path, String type) {
     path = path.replace('\\', '/');
     File dir = new File(path + type);
     File[] files = dir.listFiles((FileFilter)this);

     if (files == null || files.length <= 0)
     {
       return false;
     }
     for (File f : files) {

       int line = 0;
       MCH_InputFile inFile = new MCH_InputFile();

       BufferedReader br = null;
     }

     MCH_Lib.Log("Read %d %s", new Object[] { Integer.valueOf(getMap().size()), type });
     return (getMap().size() > 0);
   }

   public abstract Map getMap();
 }
