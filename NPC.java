package AoJGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class NPC extends Character
{
	private int actionNum;
	private Projectile proj;
	private int shootNum;
	private int time;
	private int xFinal;
	private boolean moving = false;
	
	//METAMAN TEXTURES
	private Image metaManLeft = new Image("file:Pictures/metaman/metaman_left.gif");
    private ImagePattern metaManLeftP = new ImagePattern(metaManLeft);
    private Image metaManRight = new Image("file:Pictures/metaman/metaman_right.gif");
    private ImagePattern metaManRightP = new ImagePattern(metaManRight);
    private Image metaManAttackLeft = new Image("file:Pictures/metaman/metaman_attack_left.gif");
    private ImagePattern metaManAttackLeftP = new ImagePattern(metaManAttackLeft);
    private Image metaManAttackRight = new Image("file:Pictures/metaman/metaman_attack_right.gif");
    private ImagePattern metaManAttackRightP = new ImagePattern(metaManAttackRight);
    private Image metaManStillLeft = new Image("file:Pictures/metaman/metaman_left_still.png");
    private ImagePattern metaManStillLeftP = new ImagePattern(metaManLeft);
    private Image metaManStillRight = new Image("file:Pictures/metaman/metaman_right_still.png");
    private ImagePattern metaManStillRightP = new ImagePattern(metaManRight);
    
    //GUNSLINGER TEXTURES
    private Image gunslingerLeft = new Image("file:Pictures/gunslinger/gunslinger_left.gif");
    private ImagePattern gunslingerLeftP = new ImagePattern(gunslingerLeft);
    private Image gunslingerRight = new Image("file:Pictures/gunslinger/gunslinger_right.gif");
    private ImagePattern gunslingerRightP = new ImagePattern(gunslingerRight);
    private Image gunslingerStillLeft = new Image("file:Pictures/gunslinger/gunslinger_left_still.png");
    private ImagePattern gunslingerStillLeftP = new ImagePattern(gunslingerStillLeft);
    private Image gunslingerStillRight = new Image("file:Pictures/gunslinger/gunslinger_right_still.png");
    private ImagePattern gunslingerStillRightP = new ImagePattern(gunslingerStillRight);
    
    //POISON IVY TEXTURES
    private Image poisonIvyLeft = new Image("file:Pictures/poisonivy/poison_ivy_left.gif");
    private ImagePattern poisonIvyLeftP = new ImagePattern(poisonIvyLeft);
    private Image poisonIvyRight = new Image("file:Pictures/poisonivy/poison_ivy_right.gif");
    private ImagePattern poisonIvyRightP = new ImagePattern(poisonIvyRight);
    private Image poisonIvyAttackLeft = new Image("file:Pictures/poisonivy/poison_ivy_attack_left.gif");
    private ImagePattern poisonIvyAttackLeftP = new ImagePattern(poisonIvyAttackLeft);
    private Image poisonIvyAttackRight = new Image("file:Pictures/poisonivy/poison_ivy_attack_right.gif");
    private ImagePattern poisonIvyAttackRightP = new ImagePattern(poisonIvyAttackRight);
    private Image poisonIvyStillLeft = new Image("file:Pictures/poisonivy/poison_ivy_left_still.png");
    private ImagePattern poisonIvyStillLeftP = new ImagePattern(poisonIvyStillLeft);
    private Image poisonIvyStillRight = new Image("file:Pictures/poisonivy/poison_ivy_right_still.png");
    private ImagePattern poisonIvyStillRightP = new ImagePattern(poisonIvyStillRight);
    
    //PROJECTILES VVVVVVVVVVV
    private Image bulletRight = new Image("file:Pictures/gunslinger/right_bullet.png");
    private ImagePattern bulletRightP = new ImagePattern(bulletRight);
    private Image bulletLeft = new Image("file:Pictures/gunslinger/left_bullet.png");
    private ImagePattern bulletLeftP = new ImagePattern(bulletLeft);
    
    private Image poisonRight = new Image("file:Pictures/poisonivy/poison_right.gif");
    private ImagePattern poisonRightP = new ImagePattern(poisonRight);
    private Image poisonLeft = new Image("file:Pictures/poisonivy/poison_left.gif");
    private ImagePattern poisonLeftP = new ImagePattern(poisonLeft);
    
    private Image worm_right = new Image("file:Pictures/metaman/worm_right.gif");
    private ImagePattern wormRightP = new ImagePattern(worm_right);
    private Image worm_left = new Image("file:Pictures/metaman/worm_left.gif");
    private ImagePattern wormLeftP = new ImagePattern(worm_left);
    
    private int bossLevel = 0;
    
    public NPC(int bossLevel)
    {
    	this.bossLevel = bossLevel;
    	this.sleepTime = 0;
    	if (bossLevel == 1)
			this.setFill(poisonIvyLeftP);
		if (bossLevel == 2)
			this.setFill(gunslingerLeftP);
		if (bossLevel == 3)
			this.setFill(metaManLeftP);
		
		if (bossLevel == 1 || bossLevel == 2)
			this.setHealth(120);
		else if (bossLevel == 3)
			this.setHealth(240);
		
		this.setWidth((int)(primaryScreenBounds.getWidth() * .2));
		maxPosition = (int)(primaryScreenBounds.getWidth() - this.getWidth());
		while(maxPosition % 5 != 0)
			maxPosition--;
		this.setHeight(primaryScreenBounds.getHeight()*(.3));
		this.setX(maxPosition);
		this.setY(primaryScreenBounds.getHeight()*(.84) - this.getHeight());
		
		healthBar.setWidth(180);
		healthBar.setFill(Color.YELLOWGREEN);
		healthBar.setX(0);
   	 	healthBar.setY(healthBar.getHeight());
   	 	healthBar.setOpacity(.5);
   	 	healthBar.setStroke(Color.BLACK);
   	 	
		xFinal = (int)(Math.random()*((maxPosition  + 1)));
		this.setDirection("left");
    	checkXFinal(this);
    	moving = true;
    }
    
	@Override
	public void handleMovement(ArrayList input)
	{
		if (!this.isAlive())
		{
			game.getGamePane().getChildren().remove(proj);
			
			if (this.bossLevel == 1)
			{
				game.initGameTwo();
			}
			
			if (this.bossLevel == 2)
			{
				game.initGameThree();
			}
			
			if (this.bossLevel == 3)
			{
				game.winningScreen();
				game.pauseGame();
			}
			
			game.displayVictory();
		}
		
		if (this.isAlive())
		{
			shootNum = (int)(Math.random()*(75) + 1);
			if (shootNum == 5 && sleepTime == 0)
			{
				this.shoot();
			}
				
			if (shooting)
		    {
				if (this.getX() < this.getEnemy().getX())
				{
					if (this.bossLevel == 1)
					{
						proj.setFill(poisonRightP);
						this.setFill(poisonIvyAttackRightP);
					}
					if (this.bossLevel == 2)
					{
						proj.setFill(bulletRightP);
						this.setFill(gunslingerRightP);
					}
					if (this.bossLevel == 3)
					{
						proj.setFill(wormRightP);
						this.setFill(metaManAttackRightP);
					}
		        	proj.launchRight();
		        	sleepTime = 1000;
		        }
		        	
				if (this.getX() > this.getEnemy().getX())
		        {
					if (this.bossLevel == 1)
					{
						proj.setFill(poisonLeftP);
						this.setFill(poisonIvyAttackLeftP);
					}
					if (this.bossLevel == 2)
					{
						proj.setFill(bulletLeftP);
						this.setFill(gunslingerLeftP);
					}
					if (this.bossLevel == 3)
					{
						proj.setFill(wormLeftP);
						this.setFill(metaManAttackLeftP);
					}
					
		        	proj.launchLeft();
		        	sleepTime = 1000;
		        }
				
				if (proj.getDirection().equalsIgnoreCase("right") && proj.getX() > this.getX() + 500)
				{	
					if (bossLevel == 1 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(poisonIvyLeftP);
	        		if (bossLevel == 1 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(poisonIvyRightP);
     				if (bossLevel == 2 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(gunslingerLeftP);
     				if (bossLevel == 2 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(gunslingerRightP);
     				if (bossLevel == 3 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(metaManLeftP);
     				if (bossLevel == 3 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(metaManRightP);
				}
	        	
	        	if (proj.getDirection().equalsIgnoreCase("left") && proj.getX() < this.getX() - 50)
	        	{	
	        		if (bossLevel == 1 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(poisonIvyLeftP);
	        		if (bossLevel == 1 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(poisonIvyRightP);
     				if (bossLevel == 2 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(gunslingerLeftP);
     				if (bossLevel == 2 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(gunslingerRightP);
     				if (bossLevel == 3 && this.direction.equalsIgnoreCase("left"))
     					this.setFill(metaManLeftP);
     				if (bossLevel == 3 && this.direction.equalsIgnoreCase("right"))
     					this.setFill(metaManRightP);
	        	}		
		        	
					if(getBoundary(proj.getX(), proj.getY(), proj.getWidth(), 
	        			proj.getHeight()).intersects(getBoundary(this.getEnemy().getX(), this.getEnemy().getY(), 
	        					this.getEnemy().getWidth(), this.getEnemy().getHeight())))
					{
		        		this.getEnemy().reduceHealth(12);
		        		
		        		if (!this.getEnemy().isAlive())
		        		{
		        			game.getGamePane().getChildren().remove(this.getEnemy());
		        		}
		        		
		        		if (healthBar.getWidth() >= 18)
		        		{
		        			if (healthBar.getWidth() <= 72)
		        				healthBar.setFill(Color.RED);
		        			
		        			healthBar.setWidth(healthBar.getWidth() - 18);
		        		}
		        		
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
					}
		        	
		        	if (proj.getX() > primaryScreenBounds.getWidth() - proj.getWidth())
		        	{
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
		        	}
		        	
		        	if (proj.getX() < 0)
		        	{
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
		        	}
		        	
		        }
			
			if (moving)
	     	{
				actionNum = (int)(Math.random()*(200) + 1);
		    	if (actionNum == 5)
		    		jumping = true;
		    	
	     		time = (int)((9.4)/this.getMovementSpeed());
	     		
	     		if (this.getDirection().equalsIgnoreCase("right"))
	     		{
	     			if (bossLevel == 1 && !shooting)
	     				this.setFill(poisonIvyRightP);
	     			if (bossLevel == 2 && !shooting)
	     				this.setFill(gunslingerRightP);
	     			if (bossLevel == 3 && !shooting)
	     				this.setFill(metaManRightP);
	     			
	     			this.setX(this.getX() + this.getMovementSpeed() * time);
	     		}
	     		
	     		if (this.getDirection().equalsIgnoreCase("left"))
	     		{
	     			if (bossLevel == 1 && !shooting)
	     				this.setFill(poisonIvyLeftP);
	     			if (bossLevel == 2 && !shooting)
	     				this.setFill(gunslingerLeftP);
	     			if (bossLevel == 3 && !shooting)
	     				this.setFill(metaManLeftP);
	     			
	     			this.setX(this.getX() - this.getMovementSpeed() * time);
	     		}
	     		
	     		if (xFinal == this.getX())
	     		{	
	     			if (this.getDirection().equalsIgnoreCase("right"))
	         		{
	     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
	     				this.setDirection("left");
	     				checkXFinal(this);
	     				if (bossLevel == 1 && !shooting)
	     					this.setFill(poisonIvyRightP);
	     				if (bossLevel == 2 && !shooting)
	     					this.setFill(gunslingerLeftP);
	     				if (bossLevel == 3 && !shooting)
		     				this.setFill(metaManRightP);
	         		}
	         		
	     			else if (this.getDirection().equalsIgnoreCase("left"))
	         		{
	     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
	         			this.setDirection("right");
	         			checkXFinal(this);
	         			if (bossLevel == 1 && !shooting)
	         				this.setFill(poisonIvyRightP);
	         			if (bossLevel == 2 && !shooting)
	         				this.setFill(gunslingerRightP);
	         			if (bossLevel == 3 && !shooting)
		     				this.setFill(metaManLeftP);
	         		}
	     		}
	     	}
			
			if (jumping) 
	        {
	        	//Simulating gravity so the character falls down after jumping
	            this.setY(this.getY() - 10 + gravity);
	            gravity += GRAVITAIONALFORCE;
	            
	            //If the character's y is equal to the ground's y
	            if( (this.getY() + this.getHeight() ) > game.getGround().getY()) 
	            {
	            	gravity = 0;
	            	jumping = false;
	               
	            	if (this.getDirection().equalsIgnoreCase("left"))
	            	{
	            		if (bossLevel == 1 && !shooting)
		     				this.setFill(poisonIvyStillLeftP);
		     			if (bossLevel == 2 && !shooting)
		     				this.setFill(gunslingerStillLeftP);
		     			if (bossLevel == 3 && !shooting)
		     				this.setFill(metaManStillLeftP);
	            	}
	               
	            	if (this.getDirection().equalsIgnoreCase("right"))
	            	{
	            		if (bossLevel == 1 && !shooting)
	         				this.setFill(poisonIvyStillRightP);
	         			if (bossLevel == 2 && !shooting)
	         				this.setFill(gunslingerStillRightP);
	         			if (bossLevel == 3 && !shooting)
		     				this.setFill(metaManStillRightP);
	            	}
	            }
	        }
			
	    	if (this.getX() < 0)
	    	{
	    		this.setDirection("right");
	    		this.setX(0);    
	    		xFinal = (int)(Math.random()*( (maxPosition + 1) ) );
	    		checkXFinal(this);
	    		if (bossLevel == 1 && !shooting)
     				this.setFill(poisonIvyRightP);
     			if (bossLevel == 2 && !shooting)
     				this.setFill(gunslingerRightP);
     			if (bossLevel == 3 && !shooting)
     				this.setFill(metaManRightP);
	        }
			
			if (this.getX() > maxPosition)
	    	{
	    		this.setDirection("left");
	    		this.setX(maxPosition);
	    		xFinal = (int)(Math.random()*((maxPosition + 1)));
	    		checkXFinal(this);
	    		if (bossLevel == 1 && !shooting)
     				this.setFill(poisonIvyLeftP);
     			if (bossLevel == 2 && !shooting)
     				this.setFill(gunslingerLeftP);
     			if (bossLevel == 3 && !shooting)
     				this.setFill(metaManLeftP);
	    	}
			
			if (this.getY() < 0)
	    		this.setY(0);
			
			if ( (this.getY() + this.getHeight() ) > (game.getGround().getY() - game.getGround().getHeight()))
	        	this.setY(game.getGround().getY() - (this.getHeight()));
		}
	}
	
	private void checkXFinal(Character enemy)
	{
		while ( (enemy.getDirection().equalsIgnoreCase("right") ) && (xFinal < enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while ( (enemy.getDirection().equalsIgnoreCase("left") ) && (xFinal > enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while (xFinal > maxPosition)
    		xFinal = maxPosition;
    	
    	while( (xFinal % 5) != 0)
    		xFinal--;
	}
	
	public void shoot()
	{
		proj = new Projectile(this.getX() + this.getWidth(), this.getY() + (this.getHeight() / 2), this.direction);
		game.getGamePane().getChildren().add(proj);
		shooting = true;
	}
	
	public void setBossLevel(int bossLevel)
	{
		this.bossLevel = bossLevel;
	}
	
	public int getBossLevel()
	{
		return this.bossLevel;
	}
}
