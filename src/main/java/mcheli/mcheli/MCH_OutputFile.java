 package mcheli.mcheli;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;

 public class MCH_OutputFile
 {
   public File file = null;
   public PrintWriter pw = null;


   public boolean open(String path) {
     close();
     this.file = new File(path);

     try {
       this.pw = new PrintWriter(this.file);
     }
     catch (FileNotFoundException e) {


       return false;
     }
     return true;
   }

   public boolean openUTF8(String path) {
     close();
     this.file = new File(path);

     try {
       this.pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.file), "UTF-8"));
     }
     catch (Exception e) {

       e.printStackTrace();
       return false;
     }
     return true;
   }

   public void writeLine(String s) {
     if (this.pw != null && s != null)
     {
       this.pw.println(s);
     }
   }

   public void close() {
     if (this.pw != null) this.pw.close();
     this.pw = null;
   }
 }
