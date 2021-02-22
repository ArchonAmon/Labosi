package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

public class Circle implements Drawable {	
	
	/*
	 * Inheriting classes may need access to base values to implement  
	 * different drawing algorithms, as well as possibly more complex 
	 * operations.
	 */
	protected double radius;

	/**
	 * Instantiating with a negative radius is not allowed, the  constructor 
	 * will throw a java.lang.IllegalArgumentException if called with a 
	 * negative constructor.  
	 * 
	 * @param radius The circle's radius
	 */
	public Circle(double radius) { 
		if(radius >=0 ) {
			this.radius = radius;
		}
		else 
			throw new IllegalArgumentException("Tried instantiating a negative radius circle");
	}

	/**
	 * Copy constructor won't perform any checks. The original object is 
	 * assumed to be consistent (the radius should be non-negative).
	 * 
	 * @param original The original Circle object.
	 */
	public Circle(Circle original) { 
		this.radius = original.radius;
	}

	@Override
	public void drawOnPicture(Picture pic) {
		int cY = pic.getHeight() / 2
				, cX = pic.getWidth() / 2;
		for( int y = 0; y < pic.getHeight(); y++ ) {
			for(int x = 0; x < pic.getWidth(); x++) {
				if(Math.pow(x - cX, 2) + Math.pow(y - cY,  2) < Math.pow(radius, 2) ){
					pic.turnPixelOn(x,  y);
				}
			}
		}
	}

	
	/* 
	 * main method used for simple testing
	 */

	public static void main(String[] args) { 
		int[][] tests = { 
				{5, 5, -1}
				, {5, 5, 0}
				, {5, 5, 1}
				, {5, 5, 2}
				, {5, 5, 3} // D > pic.get[Height|Width]()
				, {80, 50, 21}
				, {80, 50, 22}
				, {80, 50, 23}
				, {80, 50, 24}
				, {80, 50, 25} // D >= pic.getWidth()
				, {80, 50, 26}
				, {80, 50, 1}
				, {80, 50, 0}
		};

		for(int[] test : tests) { 
			System.out.println(new StringBuilder("Picture(w:")
					.append(test[0])
					.append(", h:")
					.append(test[1])
					.append("), Circle(r:")
					.append(test[2])
					.append("):"));
			try {
				Picture p = new Picture(test[0], test[1]);
				Circle c = new Circle(test[2]);
				c.drawOnPicture(p);
				p.renderImageToStream(System.out);
			}
			catch(Exception e) { 
				e.printStackTrace();
			}
		}

	}

}
