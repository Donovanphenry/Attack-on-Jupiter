package AoJGame;

import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class RangedCharacter extends Character
{
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
	
	private Image archerLeft = new Image("file:Pictures/lisa_range/lisa_range_left.gif");
	private ImagePattern archerLeftP = new ImagePattern(archerLeft);
	private Image archerRight = new Image("file:Pictures/lisa_range/lisa_range_right.gif");
	private ImagePattern archerRightP = new ImagePattern(archerRight);
	
	private Image archerAttackRight = new Image("file:Pictures/lisa_range/lisa_attack_right.gif");
	private ImagePattern archerAttackRightP = new ImagePattern(archerAttackRight);
	private Image archerAttackLeft = new Image("file:Pictures/lisa_range/lisa_attack_left.gif");
	private ImagePattern archerAttackLeftP = new ImagePattern(archerAttackLeft);
	
	private Image archerStillLeft = new Image("file:Pictures/lisa_range/lisa_range_left_still.png");
	private ImagePattern archerStillLeftP = new ImagePattern(archerStillLeft);
	private Image archerStillRight = new Image("file:Pictures/lisa_range/lisa_range_right_still.png");
	private ImagePattern archerStillRightP = new ImagePattern(archerStillRight);
    
	private Image bulletRight = new Image("file:Pictures/lisa_range/arrow_right.png");
    private ImagePattern bulletRightP = new ImagePattern(bulletRight);
    private Image bulletLeft = new Image("file:Pictures/lisa_range/arrow_left.png");
    private ImagePattern bulletLeftP = new ImagePattern(bulletLeft);
    private Projectile proj;
    
    private int missionLevel = 0;
	 
	public RangedCharacter(int missionLevel)
	{
		this.missionLevel = missionLevel;
		this.sleepTime = 0;
        this.setFill(archerRightP);
		this.setDirection("right");
		this.setHealth(120);
		this.setWidth(primaryScreenBounds.getWidth() * .15);
		this.setHeight(primaryScreenBounds.getHeight()*(.25));
		this.setX(0);
		this.setY(primaryScreenBounds.getHeight()*(.84));
		maxPosition = (int)(primaryScreenBounds.getWidth() - this.getWidth());
		while(maxPosition % 5 != 0)
			maxPosition--;
		
		if (this.missionLevel == 3)
			healthBar.setWidth(300);
		else 
			healthBar.setWidth(210);
		
		healthBar.setFill(Color.LIGHTGREY);
		healthBar.setX(primaryScreenBounds.getWidth() - healthBar.getWidth());
   	 	healthBar.setY(primaryScreenBounds.getHeight() * .04472);
   	 	healthBar.setOpacity(.5);
   	 	healthBar.setStroke(Color.BLACK);
	}	
	
	public void shoot()
	{
		proj = new Projectile(this.getX() + this.getWidth(), this.getY() + (this.getHeight() / 2), this.direction);
		game.getGamePane().getChildren().add(proj);
		shooting = true;
	}
	
	public boolean isShooting()
	{
		if (shooting == false)
			return false;
		
		return true;
	}
	
	@Override
	public void handleMovement(ArrayList input) 
	{	
		if (!this.isAlive())
		{
			game.getGamePane().getChildren().remove(proj);
			this.setFill(archerStillRightP);
			
			if (((NPC)(this.getEnemy())).getBossLevel() == 1)
				game.initGame();
			
			if (((NPC)(this.getEnemy())).getBossLevel() == 2)
				game.initGameTwo();
			
			if (((NPC)(this.getEnemy())).getBossLevel() == 3)
				game.initGameThree();
			
			game.displayDeath();
			game.pauseGame();
		}
		
		if (this.isAlive())
		{
			if (this.getX() > this.getPrevXPos())
	    	{
	    		this.setDirection("right");
	    		
	    		if (!shooting)
	    			this.setFill(archerRightP);
	    	}
	    	
	    	if (this.getX() < this.getPrevXPos())
	    	{
	    		this.setDirection("left");
	    		
	    		if (!shooting)
	    			this.setFill(archerLeftP);
	    	}
	    	
	        if(input.contains("A"))
	        {
	        	this.setX(this.getX() - MOVEMENTSPEED);
	        }
	
	        if(input.contains(("D")))
	        {
	        	this.setX(this.getX() + MOVEMENTSPEED);
	        }
	        
	        if (input.contains("RIGHT"))
	        {
	        	if (sleepTime == 0)
	        	{ 
	        		this.setDirection("right");
	            	this.setFill(archerAttackRightP);
	            	this.shoot();
	            	sleepTime = 1000;
	        	}
	        }
	        
	        if (input.contains("LEFT"))
	        {
	        	if (sleepTime == 0)
	        	{
	        		this.setDirection("left");
	        		this.setFill(archerAttackLeftP);
	        		this.shoot();
	        		sleepTime = 1000;
	        	}
	        }
	        
	        if (shooting)
	        {
	        	if (proj.getDirection().equalsIgnoreCase("right"))
	        	{
	        		proj.setFill(bulletRightP);
	        		proj.launchRight();
	        	}
	        	
	        	if (proj.getDirection().equalsIgnoreCase("left"))
	        	{
	        		proj.setFill(bulletLeftP);
	        		proj.launchLeft();
	        	}
	        	
	        	if (proj.getDirection().equalsIgnoreCase("RIGHT") && proj.getX() > this.getX() + 300)
	        		this.setFill(archerRightP);
	        	
	        	if (proj.getDirection().equalsIgnoreCase("left") && proj.getX() < this.getX() + this.getWidth() - 100)
	        		this.setFill(archerLeftP);
	        	
	        	
	        	if(getBoundary(proj.getX(), proj.getY(), proj.getWidth(), 
	        			proj.getHeight()).intersects(getBoundary(this.getEnemy().getX(), this.getEnemy().getY(), 
	        					this.getEnemy().getWidth(), this.getEnemy().getHeight())))
	        	{
	        		game.getGamePane().getChildren().remove(proj);
	        		
	        		this.getEnemy().reduceHealth(5);
	        		if (!this.getEnemy().isAlive())
	        			game.getGamePane().getChildren().remove(this.getEnemy());
	        		if (missionLevel == 1 || missionLevel == 2)
	        		{ 
	        			if (healthBar.getWidth() >= 7.5)
		        		{
		        			if (healthBar.getWidth() <= 30)
		        				healthBar.setFill(Color.RED);
		        			
		        			healthBar.setWidth(healthBar.getWidth() - 7.5);
		        			healthBar.setX(healthBar.getX() + 7.5);
		        		}
	        		}
	        		
	        		else if (missionLevel == 3)
	        		{
	        			if (healthBar.getWidth() >= 6.25)
		        		{
		        			if (healthBar.getWidth() <= 25)
		        				healthBar.setFill(Color.RED);
		        			
		        			healthBar.setWidth(healthBar.getWidth() - 6.25);
		        			healthBar.setX(healthBar.getX() + 6.25);
		        		}
	        		}
	        		
	        		if (this.direction.equalsIgnoreCase("right"))
	        			this.setFill(archerRightP);
	        		if (this.direction.equalsIgnoreCase("left"))
	        			this.setFill(archerLeftP);
	        		
	        		sleepTime = 0;	
	        		shooting = false;
	        	}
	        	
	        	if (proj.getX() > primaryScreenBounds.getWidth() - proj.getWidth())
	        	{
	        		game.getGamePane().getChildren().remove(proj);
	        		
	        		if (this.direction.equalsIgnoreCase("right"))
	        			this.setFill(archerRightP);
	        		if (this.direction.equalsIgnoreCase("left"))
	        			this.setFill(archerLeftP);
	        		
	        		sleepTime = 0;
	        		shooting = false;
	        	}
	        	
	        	if (proj.getX() < 0)
	        	{
	        		game.getGamePane().getChildren().remove(proj);
	        		
	        		if (this.direction.equalsIgnoreCase("right"))
	        			this.setFill(archerRightP);
	        		if (this.direction.equalsIgnoreCase("left"))
	        			this.setFill(archerLeftP);
	        		
	        		sleepTime = 0;
	        		shooting = false;
	        	}
	        }
	
	        if(jumping) 
	        {
	        	if (this.direction.equalsIgnoreCase("right"))
            	{
	        		if (!shooting)
	        			this.setFill(archerStillRightP);
            	}
            	
            	if (this.direction.equalsIgnoreCase("left"))
            	{
            		if (!shooting)
            			this.setFill(archerStillLeftP);
            	}
	        	
	            this.setY(this.getY() - 10 + gravity);
	            gravity += GRAVITAIONALFORCE;
	
	            if (this.getY() + this.getHeight() > game.getGround().getY()) 
	            {
	            	if (this.direction.equalsIgnoreCase("left"))
                 	   this.setFill(archerLeftP);
                    
                    if (this.direction.equalsIgnoreCase("right"))
                 	   this.setFill(archerRightP);
                    
	                gravity = 0;
	                jumping = false;
	            }
	        }
	
	        if (((this.getY()) + (this.getHeight())) > (game.getGround().getY() - game.getGround().getHeight()))
	            this.setY(game.getGround().getY() - (this.getHeight()));
	
	        if (this.getY() < 0)
	        {
	            this.setY(0);
	            gravity = 0;
	            jumping = false;
	        }
	
	        if (this.getX() < 0)
	        	this.setX(0);
	        
	        if (this.getX() > maxPosition)
	            this.setX(maxPosition);
		}
	}
	
	public void setMissionLevel(int missionLevel)
	{
		this.missionLevel = missionLevel;
	}
	
}
