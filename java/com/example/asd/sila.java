package com.example.asd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class sila {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dan;

    @FXML
    private Button but6;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private TextArea rezult;

    @FXML
    void initialize() {
        but6.setOnAction(event -> {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(new File("C:\\Users\\Super Sany\\IdeaProjects\\asd\\Сила.xlsx"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            XSSFWorkbook workbook = null;
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case _NONE:
                            System.out.print("");
                            System.out.print("\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            System.out.print("\t");
                            break;
                        case BLANK:
                            System.out.print("");
                            System.out.print("\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula());
                            System.out.print("\t");
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            System.out.print(evaluator.evaluate(cell).getNumberValue());
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            System.out.print("\t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue());
                            System.out.print("\t");
                            break;
                        case ERROR:
                            System.out.print("!");
                            System.out.print("\t");
                            break;
                    }
                }
                System.out.println("");
            }

        });
    }
}

