package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

public class Demonstration {

	public static void main(String[] args) {
		Picture p = new Picture(100, 50);
		Drawable[] ds = { new Rectangle(50, 40)
				, new RectangleFast(90, 6)
				, new Circle(1)
				, new CircleFast(50)
				, new EquilateralTriangle(100)
				, new EquilateralTriangleFast(100) 
		};
		ds[3] = new Circle((Circle)ds[2]);
		
		for(Drawable d : ds) { 
			d.drawOnPicture(p);
		}
		p.renderImageToStream(System.out);
		
		
		
		
		

	}

}

