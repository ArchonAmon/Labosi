package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

/**
 * RectangleFast differs from the parent class in the algorithm used to draw
 * the shape on the Picture object. Instead of the pixel-by-pixel algorithm, a  
 * (horizontal) line-by-line is used by invoking the Picture's 
 * drawLine(int, int, int) method.
 * 
 * <p>Again, a simple main method with some test cases is provided for simple 
 * testing.
 */
public class RectangleFast extends Rectangle {

	public RectangleFast(int width, int heigth) {
		super(width, heigth);		
	}

	@Override
	public void drawOnPicture(Picture pic) { 
		int emptyHeight = pic.getHeight() / 2 - (height)/ 2
				, emptyWidth = pic.getWidth() / 2 - (width) / 2;		
		for(int y = emptyHeight; y < Tools.minEq(emptyHeight + height, pic.getHeight()); y++ ) {
			pic.drawLine(emptyWidth,  Tools.minEq(emptyWidth + width - 1, pic.getWidth()),  y);
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
					.append("), RectangleFast(w:")
					.append(Integer.toString(test[2]))
					.append(",h:")
					.append(Integer.toString(test[3]))
					.append("):")
					);
			Picture p = new Picture(test[0], test[1]);
			RectangleFast r = new RectangleFast(test[2], test[3]);
			r.drawOnPicture(p);
			p.renderImageToStream(System.out);

		}

	}


}
