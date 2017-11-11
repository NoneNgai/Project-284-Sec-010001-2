import java.awt.Color;

public enum MyColor {
	
	BLUE(new Color(0,174,241)),CORAL(new Color(255,100,103)),LOWNAVY(new Color(54,57,62)),NAVY(new Color(47,49,54)),DARKNAVY(new Color(32,34,37)),
	MIDNIGHTBLUE(new Color(25,56,81)),GRAY(new Color(219,227,229)),WHITEALPHA(new Color(255,255,255,100)),RED(new Color(220, 20, 60)),
	GREEN(new Color(18,173,42));
	
	private Color myColor;
	
	private MyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	public Color getColor() {
		return myColor;
	}
	
}
