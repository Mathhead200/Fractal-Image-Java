package com.mathhead200.mandelbrot;

import java.awt.image.BufferedImage;

import com.mathhead200.Complex;
import com.mathhead200.Function;

@SuppressWarnings("unused")
public class Mandelbrot
{
	/** The function to iterate over. */
	private Function<Complex, Function<Complex, Complex>> functionFamily;
	
	/** If the sequence (squared) ever grows bigger then this value, it's (probably) unbounded. */
	private double bailoutSq;
	
	/** If the sequence has not exceeded the bailout after this many iterations, it's (probably) bounded. */
	private int depth;
	
	/** Colors to use at each iteration. */
	private Palette palette;
	
	
	public Mandelbrot(Function<Complex, Function<Complex, Complex>> functionFamily, double bailout, int depth, Palette palette) {
		this.functionFamily= functionFamily;
		this.bailoutSq = bailout * bailout;
		this.depth = depth;
		this.palette = palette;
	}
	
	
	public BufferedImage generateImage(Complex min, Complex max, long resolution) {
		Complex delta = max.subtract(min);
		int width = (int)Math.ceil( Math.sqrt(resolution * delta.real / delta.imag) );
		int height = (int)Math.ceil( Math.sqrt(resolution * delta.imag / delta.real) );
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for( int y = 0; y < height; y++ )
			for( int x = 0; x < width; x++ ) {
				
				Complex c = new Complex( min.real + delta.real * x / width, max.imag - delta.imag * y / height );
				Function<Complex, Complex> function = functionFamily.f(c);
				
				int n = 0;
				Complex z = Complex.ZERO;
				while( n < depth && z.normSq() < bailoutSq ) {
					n++;
					z = function.f(z);
				}
				image.setRGB( x, y, palette.getColor(n).getRGB() );
				
			}
		
		return image;
	}
}
