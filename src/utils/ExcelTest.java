package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelTest {
    public static HSSFWorkbook getExpLogExcel() {

        String[] fields = {"UCID", "用户昵称", "时间"};
        HSSFWorkbook wb = ExcelWriter.createSheet("sheet", fields);
        HSSFSheet sheet = wb.getSheet("sheet");

     
        for (int i=1; i<=5;i++) {
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(i*5);

            row.createCell(1).setCellValue("用户" + i);
            row.createCell(7).setCellValue(System.currentTimeMillis());
        }
        return wb;
    }
    
    
    public static void main(String[] args) {
    	export(getExpLogExcel(), "file");
    }
    private static void export(HSSFWorkbook wb, String fileName) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            wb.write(stream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
            //renderBinary(inputStream, fileName + ".xls");
        } catch (IOException e) {
        }finally{
            try {
                if(stream != null){
                    stream.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
