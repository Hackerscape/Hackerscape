/**
* @Author Robgob
* Do whatever you want.
*/
public class Alert {

	public Alert(String title, String line1, String line2) {
		this.title = title;
		this.line1 = line1;
		this.line2 = line2;
		titleColor = 0xff0000;
		color1 = 0xffffff;
		color2 = 0xffffff;
		active = true;
	}
	public Alert(String title, String line1, String line2, int c1, int c2, int c3) {
		this.title = title;
		this.line1 = line1;
		this.line2 = line2;
		titleColor = c1;
		color1 = c2;
		color2 = c3;
		active = true;
	}

	public String getTitle() {
		return title;
	}
	public String getLine(int i) {
		return (i == 1 ? line1 : line2);
	}
	public int getTitleColor() {
		return titleColor;
	}
	public int getColor(int i) {
		return (i == 1 ? color1 : color2);
	}
	public boolean active() {
		return active;
	}
	public void close() {
		closed = true;
	}
	public boolean opening() {
		return open;
	}
	public int getOpacity() {
		return opacity;
	}
	public boolean isClosed() {
		return closed;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/*
	* Holds the text for the Notification
	*/
	private String title;
	private String line1;
	private String line2;

	/*
	* Holds the Colors for the Notification
	*/
	private int titleColor;
	private int color1;
	private int color2;

	/*
	* is the notification active; visible.
	*/
	public boolean active = false;

	/*
	* is the notification closed by the user?
	*/
	private boolean closed = false;
	/*
	* is the notification opening?
	*/
	public boolean open = true;

	/*
	* How visible the Notification is
	* 0 = not at all || 50 = barely || 255 = fully visible
	*/
	public int opacity = 0;

	/*
	* Holds the Screen position
	*/
	private int x = 14;
	private int y = 250;
	public int extraX = 0;
	public int extraY = 40;
}