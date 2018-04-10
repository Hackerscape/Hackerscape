import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AlertHandler {

	public boolean hovered = false;
	public boolean close = false;
	public boolean show = false;
	public boolean remove = false;
	public Alert alert = null;
	public client c;

	public AlertHandler(client c) {
		this.c = c;
	}
	public void close() {
		close = true;
	}
	public void processAlerts() {
		
		if(alert == null)
			return;
		if (alert.active()) {
			if (close) {
				alert.close();
				close = false;
			}
			if (!alert.isClosed() && alert.getOpacity() < 90 && alert.extraY > 0) {
				alert.opacity += 5;
				if(alert.extraY > 0)
					alert.extraY -= 5;
				if(alert.extraY < 0)
					alert.extraY = 0;
				c.alertBack.drawSpriteOpacity(alert.getX(), alert.getY()+alert.extraY, alert.getOpacity());
			} else if (alert.isClosed()) {
				alert.extraY += 5;
				if(alert.getOpacity() > 0)
					alert.opacity -= 5;
				else {
					remove = true;
					show = false;
					alert.active = false;
				}
				c.alertBack.drawSpriteOpacity(alert.getX(), alert.getY()+alert.extraY, alert.getOpacity());
			} else {
				if(alert.getOpacity() < 90)
					alert.opacity = 90;
				if (show) {
					c.alertBack.drawSpriteOpacity(alert.getX(), alert.getY(), hovered ? alert.getOpacity()+25 : alert.getOpacity());
					if(hovered)
						c.alertBorderH.drawSprite(alert.getX(), alert.getY());
					else
						c.alertBorder.drawSprite(alert.getX(), alert.getY());
				}

				c.aTextDrawingArea_1271.drawText(0, alert.getTitle(), alert.getY()+16, alert.getX()+243);
				c.aTextDrawingArea_1271.drawText(alert.getTitleColor(), alert.getTitle(), alert.getY()+15, alert.getX()+242);
				c.smallText.drawText(0, alert.getLine(1), alert.getY()+36, alert.getX()+243);
				c.smallText.drawText(alert.getColor(2), alert.getLine(1), alert.getY()+35, alert.getX()+242);
				c.smallText.drawText(0, alert.getLine(2), alert.getY()+56, alert.getX()+243);
				c.smallText.drawText(alert.getColor(2), alert.getLine(2), alert.getY()+55, alert.getX()+242);
				show = true;
			}
		}
		if (remove) {
			alert = null;
			remove = false;
		}
	}
	public void processMouse(int x, int y) {
		if(alert == null)
			return;
		if (alert.active()) {
			hovered = (x >= alert.getX() && x <= alert.getX()+484 && y >= alert.getY() && y <= alert.getY()+79);
			if(hovered) {
				c.menuActionName[1] = "Dismiss";
				c.menuActionID[1] = 476;
				c.menuActionRow = 2;
			}
		}
	}
}