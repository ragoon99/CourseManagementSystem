package utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * This class is used to Create a Custom JTable by inheriting the JTable
 */
public class CustomJTable extends JTable {
	
	public CustomJTable() {
		getTableHeader().setDefaultRenderer(new TableRenderer());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	getTableHeader().setReorderingAllowed(false);
	 	setRowHeight(30);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	private class TableRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			component.setBackground(Color.black);
			component.setForeground(Color.white);
			
			return component;
		}
	}
}
