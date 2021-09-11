package com.teamup.demo.tool;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static List<String> run (MultipartFile file) throws IOException, BiffException {
        //创建一个workbook，在里面执行读取操作
        //从前端传来的文件格式是MultipartFile file，用file.getInputStream()将file从MultipartFile转到file格式，从而使用jxl读取
        Workbook book = Workbook.getWorkbook(file.getInputStream());
        Sheet[] sheets = book.getSheets();
        List<String> idList = new ArrayList<String>();
        for (Sheet sheet:sheets) {
            int clos = sheet.getColumns();
            int rows = sheet.getRows();
            if(clos>0)
            // [学号,]
            for (int x = 0; x < rows; x++) {
                    Cell cell = sheet.getCell(0, x);
                    idList.add(cell.getContents());
                    System.out.print(cell.getContents());
            }
        }
        book.close();
        return idList;
    }
}
