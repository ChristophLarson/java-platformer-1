package entities;

//import java.awt.image.BufferedImage;

public abstract class Entity {

	protected float x, y;
	protected int width, height;

	protected Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
