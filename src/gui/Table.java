package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

import db.Read;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Table(String tableName) {
		setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new Read().getTableModel(tableName));
		table.getColumnModel().getColumn(3).setPreferredWidth(101);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		scrollPane.setViewportView(table);
	}

}
