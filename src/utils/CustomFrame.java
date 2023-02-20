package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * This Class is used to Create a Custom JFrame by Inheriting the JFrame
 * 
 */
public class CustomFrame extends JFrame{
	
	private int mouseX;
	private int mouseY;
	private int oldHeight;
	private int oldWidth;
	private JLabel titleJLabel = new JLabel();
	private final JPanel titlePanel = new JPanel(new BorderLayout());
	private final JPanel operationsIconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
	private CloseButton closeButton = new CloseButton();
	private MaximizeButton maximizeButton = new MaximizeButton();
	private MinimzeButton minimzeButton = new MinimzeButton();
	
	public CustomFrame() {
		super();

		setUndecorated(true);
		setLayout(new BorderLayout());
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(titlePanel, BorderLayout.NORTH);
		
		operationsIconPanel.add(minimzeButton);
		operationsIconPanel.add(maximizeButton);
		operationsIconPanel.add(closeButton);
		
		titlePanel.add(operationsIconPanel, BorderLayout.EAST);
		titlePanel.setBounds(0, 0, getWidth(), 0);
		titlePanel.setPreferredSize(new Dimension(getWidth(), 25));
		titlePanel.setBackground(null);		
		
		minimzeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		
		maximizeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				if (getExtendedState() == MAXIMIZED_BOTH) {
					setExtendedState(NORMAL);
				} else {
					setExtendedState(MAXIMIZED_BOTH);					
				}
			}
		});
		
		addComponentListener((ComponentListener) new ComponentAdapter() {
            @Override
             public void componentResized(ComponentEvent e) {
                 setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight()+50, 20, 20));
             }
           });
		
		titlePanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
			}
		});
		
		titlePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
	}
	
	@Override
	public void setDefaultCloseOperation(int operation) {
		// TODO Auto-generated method stub
		if (operation == JFrame.DISPOSE_ON_CLOSE) {
			closeButton.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					dispose();
				}
			});
		} 
		if (operation == JFrame.EXIT_ON_CLOSE) {
			closeButton.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.exit(0);
				}
			});
		}
		
	}
	
	public void setTitle(String title) {
		setTitle(title, new Font(Font.SANS_SERIF, Font.PLAIN, 10));
	}
	
	public void setTitle(String title, Font font) {
		titleJLabel.setBorder(new EmptyBorder(0,10,0,0));
		titleJLabel.setText(title);
		titleJLabel.setFont(font);
		
		titlePanel.add(titleJLabel, BorderLayout.WEST);
	}
	
	public void setTitleBackground(Color color) {
		titlePanel.setBackground(color);
	}
	
	public void setTitleForeground(Color color) {
		titlePanel.setForeground(color);
	}
	
	public class CloseButton extends JPanel {
		@Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	        g2d.setColor(Color.red);
	        g2d.fillOval(0, 0, 10, 10);
	    }
	}
	
	public class MinimzeButton extends JPanel {
		@Override
	    public void paint(Graphics g)
	    {
	        super.paint(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	        g2d.setColor(Color.yellow);
	        g2d.fillOval(0, 0, 10, 10);
	    }
	}
	
	public class MaximizeButton extends JPanel {
		@Override
	    public void paint(Graphics g)
	    {
	        super.paint(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	        g2d.setColor(Color.green);
	        g2d.fillOval(0, 0, 10, 10);
	    }
	}
	
	public static void main(String[] args) {
		CustomFrame frame = new CustomFrame();
		frame.setBounds(0, 0, 500, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
