package entities;

//import java.awt.image.BufferedImage;

public abstract class Entity {

	protected float x, y;
	protected Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
}