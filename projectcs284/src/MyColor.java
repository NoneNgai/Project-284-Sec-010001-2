import java.awt.Color;

public enum MyColor {
	
	BLUE(new Color(0,174,241)),CORAL(new Color(255,100,103)),LOWNAVY(new Color(54,57,62)),NAVY(new Color(47,49,54)),DARKNAVY(new Color(32,34,37)),
	MIDNIGHTBLUE(new Color(22,56,81)),SALMON(new Color(201,101,101)),GRAY(new Color(219,227,229)),LIGHTBLUB(new Color(255,202,93)),
	WHITEALPHA(new Color(255,255,255,50));
	
	private Color myColor;
	
	private MyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	public Color getColor() {
		return myColor;
	}
	
}
