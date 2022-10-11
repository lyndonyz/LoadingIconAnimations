//Lyndon Zhang Loading Icon (in a circle)
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

public class LoadingIconCircle extends JFrame{

	public static void main(String[] args) {
		new LoadingIconCircle();
	}
	//setting up window size as well as object arrays
	static final int WINW = 620;
	static final int WINH = 650;
	DrawingPanel panel;
	Circle[] circle = new Circle[9];
	Drops[] drop =  new Drops[9];
	Timer timer;
	int timerspeed = 50; 

	LoadingIconCircle() {
		//setting up circle x,y positions as well as initial radius and opacity
		circle[1] = new Circle(300,125,1,0);	
//		The grayed out lines of code is for the cords for the drops to go counterclockwise instead of clockwise.
//		circle[2] = new Circle(175,175,1,0);	
//		circle[3] = new Circle(125,300,1,0);
//		circle[4] = new Circle(175,422,1,0);
//		circle[5] = new Circle(300,475,1,0);
//		circle[6] = new Circle(425,422,1,0);
//		circle[7] = new Circle(475,300,1,0);
//		circle[8] = new Circle(425,198,1,0);
//		these lines of code below allows us to go clockwise.
		circle[2] = new Circle(425,198,1,0);	
		circle[3] = new Circle(475,300,1,0);
		circle[4] = new Circle(425,422,1,0);
		circle[5] = new Circle(300,475,1,0);
		circle[6] = new Circle(175,422,1,0);
		circle[7] = new Circle(125,300,1,0);
		circle[8] = new Circle(175,198,1,0);
		//assigning a drop to each circle that we make.
		for(int i = 0; i < 8; i++) {
			drop[i+1] = new Drops(circle[i+1].circlex,circle[i+1].circley-300,0);
		}
		//setting up window, drawing panel and timer
		this.setSize(WINW,WINH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Water Dropping (circle)");
		panel = new DrawingPanel();
		panel.setBackground(new Color(13, 18, 31));
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
		timer = new Timer(timerspeed, new MyTimer());
		timer.start();
	}
	//setting up out drawing panel
	class DrawingPanel extends JPanel {			

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); 
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//going through object arrays for both drop and circle and drawing them.
			for(int i = 0; i < 8; i++) {
				//drawing actual drop
				g2.setStroke(new BasicStroke(3));
				g.setColor(new Color(255, 255, 255,drop[i+1].fade).brighter());
				g.drawLine(drop[i+1].dropx, drop[i+1].dropy, drop[i+1].dropx, drop[i+1].dropy+40);
				//drawing drop reflection
				g.setColor(new Color(255, 255, 255, (int) drop[i+1].fadeR).brighter());
				g.drawLine(drop[i+1].dropx, drop[i+1].dropyR-drop[i+1].yRChange, drop[i+1].dropx, drop[i+1].dropyR+40-drop[i+1].yRChange);
				//drawing water ripple (circle)
				g2.setStroke(new BasicStroke(4));
				g.setColor(new Color(255, 255, 255, circle[i+1].fade).brighter());
				g.drawArc(circle[i+1].x, circle[i+1].y-circle[i+1].fixY,circle[i+1].width, circle[i+1].height, 0, 360);
			}

		}	
	}

	class MyTimer implements ActionListener {
		//setting a variable called dropN to help us initialize the movement of that said drop
		int dropN = 1;
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i<8; i++) {
				//only allows one drop to exist at a time. This also lets the drop move.
				if (dropN == (i+1)) {
					drop[i+1].moveDrop();
					if(drop[i+1].dropy<drop[i+1].dropyFinal) {
						//sets drop opacity to 100
						drop[i+1].fade(100);
					}
				}
				//if the drop reaches the final position, while still being the current drop that is being moved and while the circle
				//hasn't expanded yet, turn the the ripples opacity to 100. Then start the next drop. If the dropN = 9, set it back to 1. 
				if (drop[i+1].dropy > drop[i+1].dropyFinal && circle[i+1].increaseC == 0 && dropN == (i+1)) {
					circle[i+1].fade = 100;
					dropN++;
					if (dropN == 9) {
						dropN = 1;						
					}
				}
				//if the circle (ripple) has finished fading out and if the drop is past the final position, reset both the drop and circle.
				if (circle[i+1].fade == 0 && drop[i+1].dropy>drop[i+1].dropyFinal) {
					circle[i+1].reset();
					drop[i+1].reset();
				}
				//if the drop is past the final drop position, make the circle increase in radius.
				if(drop[i+1].dropy>drop[i+1].dropyFinal) {
					circle[i+1].increaseC();
					circle[i+1].radius = circle[i+1].increaseC;
				}
				//we then recalculate the circle dimensions 
				circle[i+1].recalc();
			}
			repaint();
		}
	}
}
