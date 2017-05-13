import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Main {
   public static void main(String args[]){
	   SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
       Date date1 = new Date();   
	   String submitTime= df2.format(date1);
	      //127.0.0.1/meidi?user=root&password=liaowuhen&characterEncoding=utf-8");
		 //voip022.gotoftp3.com/voip022?user=voip022&password=808080&characterEncoding=utf-8");
		 //liaowuhentest.gotoftp5.com/liaowuhentest?user=liaowuhentest&password=liaowuhen&characterEncoding=utf-8");
			//liaowuhendg.gotoftp1.com/liaowuhendg?user=liaowuhendg&password=eWp2046Mdaq76&characterEncoding=utf-8");
		   // liaowuhen.gotoftp3.com/liaowuhen?user=liaowuhen&password=liaowuhen&characterEncoding=utf-8");   //   聚美
	  // save(submitTime,"voip022测试","voip022.gotoftp3.com","voip022","808080","voip022");  
	   save(submitTime,"liaowuhentest圣荣","liaowuhentest.gotoftp5.com","liaowuhentest","liaowuhen","liaowuhentest");  
	   save(submitTime,"liaowuhendg大港","liaowuhendg.gotoftp1.com","liaowuhendg","eWp2046Mdaq76","liaowuhendg");  
	   save(submitTime,"liaowuhen聚美","liaowuhen.gotoftp3.com","liaowuhen","liaowuhen","liaowuhen");  
	   save(submitTime,"圣荣测试","voip022.gotoftp3.com","voip022","808080","voip022");   
	   
	   
   }
    
    
   /*public static void main(String[] args) {   
         
        * 备份和导入是一个互逆的过程。  
        * 备份：程序调用mysql的备份命令，读出控制台输入流信息，写入.sql文件；  
        * 导入：程序调用mysql的导入命令，把从.sql文件中读出的信息写入控制台的输出流  
        * 注意：此时定向符">"和"<"是不能用的  
          
       backup();   
      // load();   
   }  */ 
  public static void save(String date,String name,String url,String username,String password,String datebase){
	  try {   
		  String file = "E:\\databasebat\\"+date+"\\"; 
		  File f = new File(file); 
		  if(!f.exists()){ 
			  f.mkdirs();
		  }  
          Runtime rt = Runtime.getRuntime();   
          String cmd ="E:\\soft\\mysql-5.6.20-winx64\\bin\\mysqldump -h "+url+" -u"+username+" -p"+password+" --secure_auth=off --set-charset=utf8 "+datebase+" > "+ file+name+".sql"; //一定要加 -h localhost(或是服务器IP地址)  
          System.out.println(cmd);         
          Process process =rt.exec("cmd /c " + cmd);        
          InputStreamReader isr = new InputStreamReader(process.getErrorStream(),"utf-8");    
          LineNumberReader input = new LineNumberReader(isr);  
          String line;
            
          while((line = input.readLine())!= null){  
          System.out.println(line+"~~~~~~~~~~");  
          // out.write(line);            
             }     
         System.out.println("备份成功!");
      } catch (IOException e) {  
         System.out.println("备份失败!");
          e.printStackTrace();  
      }finally{  
      	
      }
  }
   /**  
    * 备份检验一个sql文件是否可以做导入文件用的一个判断方法：把该sql文件分别用记事本和ultra  
    * edit打开，如果看到的中文均正常没有乱码，则可以用来做导入的源文件（不管sql文件的编码格式如何，也不管db的编码格式如何）  
    */  
   public static void backup() {    
       try {
    	   Random r = new Random();
    	   int random = r.nextInt();
    	   String file = "E:\\databasebat\\";  
           Runtime rt = Runtime.getRuntime();
           String cmd ="E:\\soft\\mysql-5.6.20-winx64\\bin\\mysqldump -h localhost -uroot -pliaowuhen --set-charset=utf8 meidi > "+ file+"mysql"+random+".sql"; //一定要加 -h localhost(或是服务器IP地址)  
           System.out.println(cmd);         
           Process child =rt.exec("cmd /c " + cmd);    
           
                 //String cmd ="D:\\soft\\mysql-5.6.19-winx64\\bin\\mysqldump -h localhost -uroot -pliaowuhen meidi mdbranch > "+ file+"mysql.sql"; //一定要加 -h localhost(或是服务器IP地址)   
           // 调用 mysql 的 cmd:   
           //Process child = rt   
          //         .exec("D:\\soft\\mysql-5.6.19-winx64\\bin\\mysqldump -h localhost -uroot -pliaowuhen --set-charset=utf8 meidi");// 设置导出编码为utf8。这里必须是utf8   
              
           // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行   
           InputStream in = child.getInputStream();// 控制台的输出信息作为输入流   
                          
           InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码   
              
           String inStr;   
           StringBuffer sb = new StringBuffer("");   
           String outStr;   
           // 组合控制台输出信息字符串   
           BufferedReader br = new BufferedReader(xx);   
           while ((inStr = br.readLine()) != null) {   
               sb.append(inStr + "\r\n"); 
           }   
           outStr = sb.toString();   
              
           // 要用来做导入用的sql目标文件：   
           FileOutputStream fout = new FileOutputStream(file+"mysql.sql");   
           OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");   
           writer.write(outStr);   
           // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
           writer.flush();   
  
           // 别忘记关闭输入输出流   
           in.close();   
           xx.close();   
           br.close();   
           writer.close();   
           fout.close();   
  
           System.out.println("/* Output OK! */");   
  
       } catch (Exception e) {   
           e.printStackTrace();   
       }   
  
   }   
  
   /**  
    * 导入  
    *  
    */  
   public static void load() {   
       try {   
           String fPath = "e:/mysql-5.0.27-win32/bin/bjse22.sql";   
           Runtime rt = Runtime.getRuntime();   
  
           // 调用 mysql 的 cmd:   
           Process child = rt.exec("mysql -u root bjse ");   
           OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流   
           String inStr;   
           StringBuffer sb = new StringBuffer("");   
           String outStr;   
           BufferedReader br = new BufferedReader(new InputStreamReader(   
                   new FileInputStream(fPath), "utf8"));   
           while ((inStr = br.readLine()) != null) {   
               sb.append(inStr + "\r\n");   
           }   
           outStr = sb.toString();   
  
           OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");   
           writer.write(outStr);   
           // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
           writer.flush();   
           // 别忘记关闭输入输出流   
           out.close();   
           br.close();   
           writer.close();   
  
           System.out.println("/* Load OK! */");   
  
       } catch (Exception e) {   
           e.printStackTrace();   
       }    
  
   }  
   
}
