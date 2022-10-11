//Lyndon Zhang drop
package Loading;

class Drops{
	// making of the variables inside drop
	int dropx, dropy, dropyFinal, fade;
	//these variables are the initial or opening values that were set. We need them to be able to reset the drop. 
	int dropxO, dropyO, dropyFinalO, fadeO;
	//this is for the reflections y value
	int dropyR;
	//the change of y for the reflection
	int yRChange = 45;
	//fade of R as well as the original value
	double fadeR;
	double fadeRO;
	//sets up our drop.
	Drops(int dropx, int dropy, int fade){
		this.dropx = dropx;
		this.dropy = dropy;
		this.dropyFinal = dropy+295;
		this.fade = fade;
		this.fadeR = fade*0.4;
		this.dropyR = dropy +610;
		
		this.dropxO = dropx;
		this.dropyO = dropy;
		this.dropyFinalO = dropy+295;
		this.fadeO = fade;
		this.fadeRO = fade*0.4;
		moveDrop();
	}
	//moves drop. if the drop is greater then the final one, make the opacity 0 for both the actual drop and reflection.
	void moveDrop() {
		dropy = dropy + 44;
		yRChange = yRChange +43;
		if (dropy>dropyFinal) {
			fade = 0;
			fadeR = 0;
		}
	}
	//changes the opacity of the drops
	void fade(int n) {
		this.fade = n;
		this.fadeR = n*0.4;
	}
	//resets the drop using the original values we had
	void reset() {
		yRChange = 45;
		dropx = dropxO;
		dropy = dropyO;
		dropyFinal = dropyFinalO;
		fade = fadeO;
		fadeR = fadeRO;
	}
}
