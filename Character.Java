package AoJGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

public class Character extends Rectangle
{
    protected GameEngine game;
    private Character enemy;
    private int healthPoints;
    protected Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    protected final float GRAVITAIONALFORCE = (float)(primaryScreenBounds.getHeight()*.0004);
    protected float gravity;
    protected boolean jumping = false;
    protected final int MOVEMENTSPEED = 5;
    protected String direction;
    protected double prevXPos;
    protected int maxPosition;
    protected boolean shooting = false;
    protected int sleepTime = 0;
    protected Rectangle healthBar = new Rectangle(.25 * primaryScreenBounds.getWidth(), 35);
    
    public Character() 
    {
        super.setY(primaryScreenBounds.getHeight()*(.84));
        gravity = 0;

    }

    public void reduceHealth(int dmg)
    {
        this.healthPoints -= dmg;
    }
    
    public void setPrevXPos(double prevXPos)
    {
    	this.prevXPos = prevXPos;
    }

    public void setHealth(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }
    
    public void setDirection(String direction)
	{
		this.direction = direction;
	}
    
    public double getPrevXPos()
    {
    	return this.prevXPos;
    }

    public int getHealth()
    {
        return this.healthPoints;
    }
    
    public String getDirection()
	{
		return this.direction;
	}

    public int getMovementSpeed()
    {
    	return this.MOVEMENTSPEED;
    }
    
    public void defenseAbility(){}

    public boolean isAlive()
    {
        if (this.healthPoints > 0)
            return true;

        else if (this.healthPoints <= 0)
            return false;

        else
            return true;
    }

    public void handleMovement(ArrayList input) 
    {
    	//Identifying which way the character is facing
    	if (this.getX() > this.getPrevXPos())
    	{
    		this.setDirection("right");
    		//this.setFill(archerRightP);
    	}
    	
    	if (this.getX() < this.getPrevXPos())
    	{
    		this.setDirection("left");
    		//this.setFill(archerLeftP);
    	}
    	
        if(input.contains("A"))
        {
        	this.setX(this.getX() - MOVEMENTSPEED);
        }

        if(input.contains(("D")))
        {
        	this.setX(this.getX() + MOVEMENTSPEED);
        }

        if(jumping) 
        {
            this.setY(this.getY() - 10 + gravity);
            gravity += GRAVITAIONALFORCE;

            if(this.getY() + this.getHeight() > game.getGround().getY()) 
            {
                gravity = 0;
                jumping = false;
            }
        }

        if (((this.getY()) + (this.getHeight())) > (game.getGround().getY() - game.getGround().getHeight()))
            this.setY(game.getGround().getY() - (this.getHeight()));

        if (this.getY() < 0){
            this.setY(0);
        }

        if ((this.getX() < 0)){
            this.setX(0);

        }

        if (this.getX() > maxPosition)
            this.setX(maxPosition);
    }

    public void setGame(GameEngine game) 
    {
        this.game = game;
    }

    public void jump() 
    {
        this.jumping = true;
    }

    public void setEnemy(Character enemy)
	{
		this.enemy = enemy;
	}

    public Character getEnemy()
	{
		return this.enemy;
	}

    public int getSleepTime()
    {
    	return this.sleepTime;
    }
    
    public void setSleepTime(int sleepTime)
    {
    	this.sleepTime = sleepTime;
    }
    protected javafx.geometry.Rectangle2D getBoundary(double posX, double posY, double width, double height) 
	{
        return new javafx.geometry.Rectangle2D(posX, posY, width, height);
    }
    
    public void setHealthBar(Rectangle healthBar)
	{
		this.healthBar = healthBar;
	}
	
	public Rectangle getHealthBar()
	{
		return this.healthBar;
	}
}
