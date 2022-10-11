//Lyndon Zhang Circle (ripple)
package Loading;

import java.awt.Rectangle;

class Circle extends Rectangle{
	//initializing our variables that we need for our circle
	int circlex, circley, radius, fade, fixY, increaseC;
	int circlexO, circleyO, radiusO, fadeO, fixYO, increaseCO;
	//setting our our circle
	Circle(int circlex, int circley, int radius,int fade) {
		this.circlex = circlex;
		this.circley = circley;
		this.radius = radius;
		this.fade = fade;
		this.fixY = 0;
		this.increaseC = 0;
		
		this.circlexO = circlex;
		this.circleyO = circley;
		this.radiusO = radius;
		this.fadeO = fade;
		this.fixYO = 0;
		this.increaseCO = 0;
		recalc();
	}
	//this allows us to recalculate our circle when we make a change to the radius
	void recalc() {		
		width = height = radius*2;
		height = height/2;
		//fixY is used to make the circle move up slightly to make it expand in all directions instead of one since
		//we are using more of an oval shape then we are circle.
		fixY = fixY+1;
		x = circlex-radius;
		y = circley;
		if (fade >0) {
			fade = fade-4;
		}
	}
	//increases Radius.
	void increaseC() {
		increaseC = increaseC+ 5;
	}
	//resets the circle to the original one.
	void reset() {
		circlex = circlexO;
		circley = circleyO;
		radius = radiusO;
		fade = fadeO;
		fixY = fixYO;
		increaseC = increaseCO;
	}
}