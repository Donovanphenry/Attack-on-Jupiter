package AoJGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class Projectile extends Rectangle
{
	private final int MOVEMENTSPEED = 10;
	private String direction;
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
	private final double GROUNDLEVEL = primaryScreenBounds.getHeight()*(.84);
	
	public Projectile(double x, double y, String direction)
	{
		this.direction = direction;
		this.setWidth(40);
		this.setHeight(10);
		this.setX(x);
		this.setY(y);
		if (((this.getY()) + (this.getHeight())) > (GROUNDLEVEL))
            this.setY(GROUNDLEVEL - y);
		//this.setFill(Color.RED);
		//this.setStrokeWidth(100);
	}
	
	public String getDirection()
	{
		return this.direction;
	}
	
	public void launchRight()
	{
		this.direction = "right";
		this.setX(this.getX() + MOVEMENTSPEED);
	}
	
	public void launchLeft()
	{
		this.direction = "left";
		this.setX(this.getX() - MOVEMENTSPEED);
	}
}
