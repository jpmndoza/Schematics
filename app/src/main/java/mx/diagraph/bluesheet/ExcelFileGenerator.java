package mx.diagraph.bluesheet;


import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.java.awt.Color;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelFileGenerator {

    public static ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
    public static ArrayList<ArrayList<String>> arrayList2 = new ArrayList<>();


    public void setArrayList(ArrayList<ArrayList<String>> arrayList) {
        this.arrayList = arrayList;
    }

    public void setArrayList2(ArrayList<ArrayList<String>> arrayList2) {
        this.arrayList2 = arrayList2;
    }

    public XSSFWorkbook printExcel()throws IOException{


        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Hoja azul");

        int rowCount = 1;

        blueStyle();

        XSSFCellStyle blueStyle = workbook.createCellStyle();
        XSSFFont my_font= workbook.createFont();
        XSSFColor color = new XSSFColor(Color.decode("#3589F1"));
        my_font.setBold(true);
        my_font.setFontHeight(15);
        my_font.setColor(new XSSFColor(Color.white));
        blueStyle.setFont(my_font);
        blueStyle.setAlignment(HorizontalAlignment.LEFT);
        blueStyle.setFillForegroundColor(color);
        blueStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        blueStyle.setBorderBottom(BorderStyle.THIN);
        blueStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        blueStyle.setBorderLeft(BorderStyle.THIN);
        blueStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        blueStyle.setBorderRight(BorderStyle.THIN);
        blueStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        blueStyle.setBorderTop(BorderStyle.THIN);
        blueStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());

        XSSFCellStyle inputStyle = workbook.createCellStyle();
        XSSFFont inputFont = workbook.createFont();
        inputFont.setFontHeight(15);
        inputFont.setBold(true);
        inputStyle.setFont(inputFont);

        inputStyle.setBorderBottom(BorderStyle.THIN);
        inputStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        inputStyle.setBorderTop(BorderStyle.THIN);
        inputStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        inputStyle.setBorderRight(BorderStyle.THIN);
        inputStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeight(18);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);



        for (ArrayList aBook : arrayList2) {
            int columnCount = 0;
            if(rowCount==1){
                Row header = sheet.createRow(rowCount);
                Cell headerCell = header.createCell(1);
                headerCell.setCellValue("Hoja Azul");
                headerCell.setCellStyle(headerStyle);
            }
            Row row = sheet.createRow(++rowCount);
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                cell.setCellValue((String) field);
                if(columnCount==1){
                    cell.setCellStyle(blueStyle);
                }else{
                    cell.setCellStyle(inputStyle);
                }
            }
        }



        int rowCount2 = 1;
        for (ArrayList aBook2: arrayList) {
            int columnCount2 = 2;
            if(rowCount2==1){
                Row header = sheet.getRow(rowCount2);
                Cell headerCell = header.createCell(3);
                headerCell.setCellValue("Info. del cliente");
                headerCell.setCellStyle(headerStyle);
            }
            Row row = sheet.getRow(++rowCount2);
            for (Object field : aBook2) {
                Cell cell2 = row.createCell(++columnCount2);
                cell2.setCellValue((String) field);
                if(columnCount2==3){
                    cell2.setCellStyle(blueStyle);
                }else{
                    cell2.setCellStyle(inputStyle);
                }
            }
        }


        sheet.setColumnWidth(1,12000);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(3,8000);
        sheet.setColumnWidth(4,8000);

        return workbook;
    }

    private void blueStyle() {
    }


}