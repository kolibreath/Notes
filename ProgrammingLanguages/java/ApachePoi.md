# Apache poi
现在主要流行的是xlsx格式的文件了，所以需要使用XssfWorkBook 来打开和处理这种文件：
打开文件和创建文件都是通过stream完成的：
[refernce](https://www.tutorialspoint.com/apache_poi/apache_poi_workbooks.htm)

```
public class OpenWorkBook {
   public static void main(String args[])throws Exception { 
      File file = new File("openworkbook.xlsx");
      FileInputStream fIP = new FileInputStream(file);
      
      //Get the workbook instance for XLSX file 
      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
      
      if(file.isFile() && file.exists()) {
         System.out.println("openworkbook.xlsx file open successfully.");
      } else {
         System.out.println("Error to open openworkbook.xlsx file.");
      }
   }
}
```
