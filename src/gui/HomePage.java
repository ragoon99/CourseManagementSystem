package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import backend.UserType;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HomePage extends JFrame {
	
	private JPanel contentPane;
	private static int uType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					HomePage frame = new HomePage(uType);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage(int uType) {
		HomePage.uType = uType;
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mid_panel = new JPanel();
		mid_panel.setBackground(new Color(255, 255, 255));
		contentPane.add(mid_panel, BorderLayout.CENTER);
		mid_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel title_panel = new JPanel();
		title_panel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(0, 0, 0)));
		title_panel.setPreferredSize(new Dimension(10, 100));
		mid_panel.add(title_panel, BorderLayout.NORTH);
		title_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title_label = new JLabel("");
		title_label.setFont(new Font("Tahoma", Font.BOLD, 32));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_panel.add(title_label, BorderLayout.CENTER);
		
		JPanel center_panel = new JPanel();
		mid_panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel left_panel = new JPanel();
		contentPane.add(left_panel, BorderLayout.WEST);
		left_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel lTop_panel = new JPanel();
		lTop_panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lTop_panel.setPreferredSize(new Dimension(150, 100));
		left_panel.add(lTop_panel, BorderLayout.NORTH);
		lTop_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("CMS");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 26));
		lTop_panel.add(lblNewLabel);
		
		JPanel lCenter_panel = new JPanel();
		left_panel.add(lCenter_panel, BorderLayout.CENTER);
		lCenter_panel.setLayout(new BorderLayout(0, 0));
		
		JList<Object> list = new JList<Object>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue().equals("Admin Controls")) {
					center_panel.removeAll();
					center_panel.add(new AdminControls());
					title_label.setText("Admin Controls");
				}
				if (list.getSelectedValue().equals("View Courses")) {
					center_panel.removeAll();
					center_panel.add(new Table("courses"));
					title_label.setText("Courses");
				}
				if (list.getSelectedValue().equals("View Tutors")) {
					center_panel.removeAll();
					center_panel.add(new Table("tutor"));
					title_label.setText("Tutors");
				}
				if (list.getSelectedValue().equals("View Students")) {
					center_panel.removeAll();
					center_panel.add(new Table("student"));
					title_label.setText("Students");
				}
				if (list.getSelectedValue().equals("View Modules")) {
					center_panel.removeAll();
//					center_panel.add(new Table("courses"));
				}
				
				center_panel.updateUI();
			}
		});
		list.setBackground(null);
		list.setFixedCellHeight(50);
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		ArrayList<String> listData = new ArrayList<>(Arrays.asList("View Courses", "View Students", "View Tutors", "View Modules", "View Report"));
		if (uType == 0) {
			listData.add("Admin Controls");
		}
		
		list.setModel(new AbstractListModel<Object>() {
			Object[] values = listData.toArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lCenter_panel.add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setMaximum(5);
		scrollBar.setPreferredSize(new Dimension(10, 0));
		lCenter_panel.add(scrollBar, BorderLayout.EAST);
		
		JPanel lBottom_panel = new JPanel();
		left_panel.add(lBottom_panel, BorderLayout.SOUTH);
		
		ImageIcon settingImageIcon = new ImageIcon(getClass().getResource("/resource/settings.png"));
		Image settingImage = settingImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel setting_icon = new JLabel(new ImageIcon(settingImage));
		setting_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) System.out.println("Hello");
			}
		});
		lBottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setting_icon.setToolTipText("Settings");
		setting_icon.setPreferredSize(new Dimension(35, 35));
		setting_icon.setHorizontalTextPosition(SwingConstants.CENTER);
		lBottom_panel.add(setting_icon);
		
		ImageIcon helpImageIcon = new ImageIcon(getClass().getResource("/resource/help-web-button.png"));
		Image helpImage = helpImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel help_icon = new JLabel(new ImageIcon(helpImage));
		help_icon.setToolTipText("Help");
		help_icon.setPreferredSize(new Dimension(35, 35));
		lBottom_panel.add(help_icon);
		
		ImageIcon logoutImageIcon = new ImageIcon(getClass().getResource("/resource/logout.png"));
		Image logoutImage = logoutImageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		JLabel logout_icon = new JLabel(new ImageIcon(logoutImage));
		logout_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new LoginSignup();
				dispose();
			}
		});
		logout_icon.setPreferredSize(new Dimension(35, 35));
		logout_icon.setToolTipText("Logout");
		logout_icon.setHorizontalAlignment(SwingConstants.CENTER);
		lBottom_panel.add(logout_icon);
	}
}
