package com.example.model;

import java.util.List;
import java.lang.reflect.Field;
import javax.swing.table.AbstractTableModel;

public abstract class AbstractEntityTableModel<T> extends AbstractTableModel {
    private List<T> entities;
    private String[] columnNames;

    public AbstractEntityTableModel(List<T> entities) {
        this.entities = entities;
        this.columnNames = getColumnNames();
    }

    
    /** 
     * @return int
     */
    @Override
    public int getRowCount() {
        return entities.size();
    }

    
    /** 
     * @return int
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    
    /** 
     * @param column
     * @return String
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    
    /** 
     * @param rowIndex
     * @param columnIndex
     * @return Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T entity = entities.get(rowIndex);
        Field[] fields = getEntityClass().getDeclaredFields();

        try {
            Field field = fields[columnIndex];
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    /** 
     * @return String[]
     */
    private String[] getColumnNames() {
        Field[] fields = getEntityClass().getDeclaredFields();
        String[] columnNames = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }

        return columnNames;
    }

    protected abstract Class<?> getEntityClass();
}
