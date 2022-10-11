package Loading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingIconRandom extends JFrame{

	public static void main(String[] args) {
		new LoadingIconRandom();
	}

	static final int WINW = 900;
	static final int WINH = 900;
	DrawingPanel panel;
	Circle[] circle = new Circle[9];
	Drops[] drop =  new Drops[9];
	Timer timer;
	int timerspeed = 50; 

	LoadingIconRandom() {
		for(int i = 0; i < 8; i++) {
			circle[i+1] = new Circle((int)(Math.random()*750+50),(int)(Math.random()*750+50),1,0);
			drop[i+1] = new Drops(circle[i+1].circlex,circle[i+1].circley-300,0);
		}
		this.setSize(WINW,WINH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Water Dropping (random)");

		panel = new DrawingPanel();
		panel.setBackground(new Color(13, 18, 31));
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
		timer = new Timer(timerspeed, new MyTimer());
		timer.start();
	}

	class DrawingPanel extends JPanel {			

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); 
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for(int i = 0; i < 8; i++) {
				g2.setStroke(new BasicStroke(3));
				g.setColor(new Color(255, 255, 255,drop[i+1].fade).brighter());
				g.drawLine(drop[i+1].dropx, drop[i+1].dropy, drop[i+1].dropx, drop[i+1].dropy+40);
	
				g.setColor(new Color(255, 255, 255, (int) drop[i+1].fadeR).brighter());
				g.drawLine(drop[i+1].dropx, drop[i+1].dropyR-drop[i+1].yRChange, drop[i+1].dropx, drop[i+1].dropyR+40-drop[i+1].yRChange);
	
				g2.setStroke(new BasicStroke(4));
				g.setColor(new Color(255, 255, 255, circle[i+1].fade).brighter());
				g.drawArc(circle[i+1].x, circle[i+1].y-circle[i+1].fixY,circle[i+1].width, circle[i+1].height, 0, 360);
			}

		}	
	}

	class MyTimer implements ActionListener {
		int tick = 0;
		int dropN = 1;
		@Override
		public void actionPerformed(ActionEvent e) {
				for (int i = 0; i<8; i++) {
					if (dropN == (i+1)) {
						drop[i+1].moveDrop();
						if(drop[i+1].dropy<drop[i+1].dropyFinal) {
							drop[i+1].fade(100);
						}
					}
					if (drop[i+1].dropy > drop[i+1].dropyFinal && circle[i+1].increaseC == 0 && dropN == (i+1)) {
						circle[i+1].fade = 100;
						dropN++;
						if (dropN == 9) {
							dropN = 1;
						}
					}
					if (circle[i+1].fade == 0 && drop[i+1].dropy>drop[i+1].dropyFinal) {
						circle[i+1] = new Circle((int)(Math.random()*750+50),(int)(Math.random()*750+50),1,0);
						drop[i+1] = new Drops(circle[i+1].circlex,circle[i+1].circley-300,0);
						circle[i+1].reset();
						drop[i+1].reset();
					}
					if(drop[i+1].dropy>drop[i+1].dropyFinal) {
						circle[i+1].increaseC();
						circle[i+1].radius = circle[i+1].increaseC;
					}
					circle[i+1].recalc();
				}
					if (tick >90) {
						tick = 0;
					}
			repaint();
			tick++;
		}
	}
}
