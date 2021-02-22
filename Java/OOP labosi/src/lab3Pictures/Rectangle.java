package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

/**
 * Simple Drawable implementation that draws a rectangle roughly centered 
 * centered in the given Picture object with sides parallel to the Picture
 * border.
 * <p>This Drawable implementation uses a simple pixel-by-pixel algorithm. To
 * avoid rounding problems this class is implemented using integer arithmetic
 * and should always draw a rectangle of the requested dimensions. Therefore 
 * the basic constructor takes integer arguments.
 * <p>The rectangle is drawn roughly in the middle of the picture - if the 
 * parity of the Picture's (in drawOnPicture method) and Rectangle's instances
 * don't match, the rectangle will be drawn with an extra empty line <b>before
 * </b> the shape itself.
 * <p>The rectangle is defined by two side lengths.No methods to access the 
 * base values are provided, but the class does allow overriding of the 
 * internally kept radius value. Inheriting classes should take care to keep 
 * the radius non-negative at all times (see the copy constructor).
 */
public class Rectangle implements Drawable {

	/*
	 * Inheriting classes may need access to base values to implement  
	 * different drawing algorithms, as well as possibly more complex 
	 * operations.
	 */
	protected int width, height;	

	/**
	 * The basic constructor. If any of the side lengths is negative, an 
	 * IllegalArgumentException will be thrown.
	 * 
	 * @param width length of the horizontally drawn side
	 * @param heigth length of the vertically drawn side
	 */
	public Rectangle(int width, int heigth) {
		if( width >= 0 && height >= 0) {
			this.width = width;
			this.height = heigth;
		} else {
			throw new IllegalArgumentException("Can't instatiate rectangles with a negative side length");
		}
	}

	public Rectangle(Rectangle original) { this(original.width, original.height); }

	@Override
	public void drawOnPicture(Picture pic) {
		int emptyHeight = pic.getHeight() / 2 - (height)/ 2
				, emptyWidth = pic.getWidth() / 2 - (width) / 2;		
		for(int y = 0; y < pic.getHeight(); y++ ) {
			for(int x = 0; x < pic.getWidth(); x++ ) {
				if(y >= emptyHeight 
						&& y < emptyHeight + height 
						&& x >= emptyWidth 
						&& x < emptyWidth + width) {
					pic.turnPixelOn(x, y);					
				}
			}
		}		
	}

	public static void main(String[] args) { 
		int [][] tests = new int[][] { 
			{5, 5, 4, 4}
			, {5, 5, 3, 3}
			, {5, 5, 2, 2}
			, {5, 5, 1, 1}
			, {5, 5, 0, 0}
			, {4, 4, 2, 2}
			, {4, 4, 3, 3}
			, {4, 4, 4, 4}
			, {4, 4, 5, 5}
			, {4, 4, 1, 3}
			, {4, 4, 2, 3}
			, {4, 4, 3, 3}
			, {4, 4, 4, 3}
			, {4, 4, 5, 3}
			, {4, 4, 2, 4}
			, {4, 4, 4, 2}
			, {150, 50, 15, 3}
			, {150, 50, 0, 0}
		};
		for(int[] test : tests) {
			System.out.println(new StringBuilder("Picture(w:")
					.append(Integer.toString(test[0]))
					.append(",h:")
					.append(Integer.toString(test[1]))
					.append("), Rectangle(w:")
					.append(Integer.toString(test[2]))
					.append(",h:")
					.append(Integer.toString(test[3]))
					.append("):")
					);
			Picture p = new Picture(test[0], test[1]);
			Rectangle r = new Rectangle(test[2], test[3]);
			r.drawOnPicture(p);
			p.renderImageToStream(System.out);

		}
	}

}