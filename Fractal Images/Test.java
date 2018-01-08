import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mathhead200.Complex;
import com.mathhead200.Function;
import com.mathhead200.mandelbrot.Mandelbrot;
import com.mathhead200.mandelbrot.Palette;


public class Test
{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Palette palette = new Palette() {
			public Color getColor(int n) {
				return new Color( Color.HSBtoRGB(n / 360f, 1f, 1f) );
			}
		};
		
		Function<Complex, Function<Complex, Complex>> functionFamily = new Function<Complex, Function<Complex, Complex>>() {
			public Function<Complex, Complex> f(final Complex c) {
				return new Function<Complex, Complex>() {
					public Complex f(Complex z) {
						return z.multiply(z).add(c);
					}
				};
			}	
		};
		
		Mandelbrot mandelbrot = new Mandelbrot(functionFamily, 2.0, 360, palette);
		
		System.out.println("Generating...");
		BufferedImage image = mandelbrot.generateImage( new Complex(-2.5, -1), new Complex(1, 1), 200000000L );
		
		System.out.println("Saving...");
		try( FileOutputStream out = new FileOutputStream("mandelbrot.png") ) {
			ImageIO.write(image, "png", out);
		}
		
		System.out.println("Done.");
		
	}
}
