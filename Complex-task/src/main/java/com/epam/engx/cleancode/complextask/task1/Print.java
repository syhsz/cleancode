package com.epam.engx.cleancode.complextask.task1;



import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;
    private final String[] headerSymbols = {"╔", "╦", "╗\n"};
    private final String[] middleSymbols = {"╠", "╬", "╣\n"};
    private final String[] footerSymbols = {"╚", "╩", "╝\n"};

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
        tableName = command[1];
        List<DataSet> data = manager.getTableData(tableName);
        view.write(getTableString(data));
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = getMaxColumnSize(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }
    }

    private int getMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.size() > 0) {
            List<String> columnNames = dataSets.get(0).getColumnNames();
            for (String columnName : columnNames) {
                if (columnName.length() > maxLength) {
                    maxLength = columnName.length();
                }
            }
            for (DataSet dataSet : dataSets) {
                List<Object> values = dataSet.getValues();
                for (Object value : values) {
                        if (String.valueOf(value).length() > maxLength) {
                            maxLength = String.valueOf(value).length();
                        }
                }
            }
        }
        return maxLength;
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        int columnSize = textEmptyTable.length() - 2;
        String result = "";
        result += printSplitLine(columnSize, 1, headerSymbols);
        result += textEmptyTable + "\n";
        result += printSplitLine(columnSize, 1, footerSymbols);
        return result;
    }

    private String getStringTableData(List<DataSet> dataSets) {
        int rowsCount;
        rowsCount = dataSets.size();
        int maxColumnPrintSize = getMaxColumnPrintSize(getMaxColumnSize(dataSets));
        String result = "";

        int columnCount = getColumnCount(dataSets);
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            result += printValesPerRow(maxColumnPrintSize, values, columnCount);
            if (row < rowsCount - 1) {
                result += printSplitLine(maxColumnPrintSize, columnCount, middleSymbols);
            }
        }
        result += printSplitLine(maxColumnPrintSize, columnCount, footerSymbols);
        return result;
    }

    private int getMaxColumnPrintSize(int maxColumnSize) {
        int maxSize;
        if (maxColumnSize % 2 == 0) {
            maxSize = maxColumnSize + 2;
        } else {
            maxSize = maxColumnSize + 3;
        }
        return maxSize;
    }

    private String printOneColumn(int maxColumnPrintSize, String columnValue){
        String result = "║";
        int columnValueLength = columnValue.length();
        int spaceCount = (maxColumnPrintSize - columnValueLength) / 2;
        result += printSymbolMultiTimes(spaceCount, " ");
        result += columnValue;
        result += printSymbolMultiTimes(spaceCount, " ");
        if (columnValueLength % 2 != 0) result += " ";
        return result;
    }

    private String printValesPerRow(int maxColumnPrintSize, List<Object> values, int columnCount){
        String result = "";
        for (int column = 0; column < columnCount; column++) {
            String columnValue = String.valueOf(values.get(column));
            result += printOneColumn(maxColumnPrintSize, columnValue);
        }
        result += "║\n";
        return result;
    }

    private String printSplitLine(int maxColumnPrintSize, int columnCount, String[] symbols ){
        String result = "";
        result += symbols[0];
        for (int j = 1; j < columnCount; j++) {
            result += printSymbolMultiTimes(maxColumnPrintSize, "═");
            result += symbols[1];
        }
        result += printSymbolMultiTimes(maxColumnPrintSize, "═");
        result += symbols[2];
        return result;
    }

    private String printSymbolMultiTimes(int count, String symbol){
        String result = "";
        for (int i = 0; i < count; i++) {
            result += symbol;
        }
        return result;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.size() > 0) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnPrintSize = getMaxColumnPrintSize(getMaxColumnSize(dataSets));
        int columnCount = getColumnCount(dataSets);
        List<String> columnNames = dataSets.get(0).getColumnNames();

        String result = "";
        result += printSplitLine(maxColumnPrintSize, columnCount, headerSymbols);
        result += printColumnNames(maxColumnPrintSize, columnNames, columnCount);

        //last string of the header
        if (dataSets.size() > 0) {
            result += printSplitLine(maxColumnPrintSize, columnCount, middleSymbols);
        } else {
            result += printSplitLine(maxColumnPrintSize, columnCount, footerSymbols);
        }
        return result;
    }

    private String printColumnNames(int maxColumnPrintSize, List<String> columnNames, int columnCount){
        String result = "";
        for (int column = 0; column < columnCount; column++) {
            String columnName = columnNames.get(column);
            result += printOneColumn(maxColumnPrintSize, columnName);
        }
        result += "║\n";
        return result;
    }
}