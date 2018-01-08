package com.mathhead200;

public class Complex
{
	public final double real;
	public final double imag;
	
	
	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}
	
	public Complex(double x) {
		this(x, 0);
	}


	public static final Complex ZERO = new Complex(0, 0);
	public static final Complex ONE = new Complex(1, 0);
	public static final Complex I = new Complex(0, 1);
	
	
	public double normSq() {
		return real * real + imag * imag;
	}
	
	public double norm() {
		return Math.sqrt( normSq() );
	}
	
	public double arg() {
		return Math.atan2(imag, real);
	}
	
	
	public Complex add(Complex z) {
		return new Complex( real + z.real, imag + z.imag );
	}
	
	public Complex subtract(Complex z) {
		return new Complex( real - z.real, imag - z.imag );
	}
	
	public Complex negate() {
		return new Complex( -real, -imag );
	}
	
	public Complex conjugate() {
		return new Complex( real, -imag );
	}
	
	public Complex multiply(double k) {
		return new Complex( k * real, k * imag );
	}
	
	public Complex multiply(Complex z) {
		return new Complex( real * z.real - imag * z.imag, real * z.imag + z.real * imag );
	}
	
	public Complex divide(double k) {
		return new Complex( real / k, imag / k );
	}
	
	public Complex divide(Complex z) {
		return multiply( z.conjugate() ).divide( z.normSq() );
	}
	
	public Complex reciprocal() {
		return conjugate().divide( normSq() );
	}
	
	public Complex pow(int n) {
		Complex base = this;
		if( n < 0 ) {
			base = base.reciprocal();
			n = -n;
		}
		
		Complex ans = ONE;
		while( --n >= 0 )
			ans = ans.multiply(base);
		
		return ans;
	}
	
	
	public String toString() {
		return real + " + " + imag + "i";
	}
}
