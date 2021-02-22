package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

/**
 * CircleFast differs from the parent class in the algorithm used to draw
 * the shape on the Picture object. Instead of the pixel-by-pixel algorithm, a  
 * (horizontal) line-by-line is used by invoking the Picture's 
 * drawLine(int, int, int) method.
 * 
 * <p>Again, a simple main method with some test cases is provided for simple 
 * testing.
 *
 */
public class CircleFast extends Circle {
	
	public CircleFast(double radius) {
		super(radius);		
	}
	
	public CircleFast(Circle original) { 
		super(original);
	}
	
	
	@Override
	public void drawOnPicture(Picture pic) {
		int cY = pic.getHeight() / 2;
		int minY = cY - (int) radius;
		
		for( int y = minY > 0 ? minY : 0; y < Tools.minEq((int)( cY + radius * 2 ), pic.getHeight()); y++ ) {
			double sq = Math.sqrt(Math.pow(radius, 2) - Math.pow(y - pic.getHeight() / 2,  2));
			int x0 = (int) (pic.getWidth()/2 - sq);
			int x1 = (int) (pic.getWidth()/2 + sq);
			pic.drawLine(x0 >= 0 ? x0 : 0, Tools.minEq(x1, pic.getWidth()), y);
		}
	}


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
					.append("), CircleFast(r:")
					.append(test[2])
					.append("):"));
			try {
				Picture p = new Picture(test[0], test[1]);
				Circle c = new CircleFast(test[2]);
				c.drawOnPicture(p);
				p.renderImageToStream(System.err);
			}
			catch(Exception e) { 
				e.printStackTrace();
			}
		}

	}

}
