package me.misleaded.forceWand.util;

public class Equation {
	private double pX;
	private double pZ;
	private double qX;
	private double qZ;
	private double slope;
	private int type; //0: equal, 1: less than, 2: greater than, 3: less than or equal to, 4: greater than or equal to
	
	
	public Equation(double x1, double z1, double x2, double z2, int t) {
		pX = x1;
		pZ = z1;
		qX = x2;
		qZ = z2;
		createSlope();	
		type = t;
	}
	
	public Equation(double x1, double z1, double x2, double z2) {
		this(x1, z1, x2, z2, 0);
	}
	
	public boolean solve(double x, double z) {
		double left =  z - pZ;
		double right = slope * (x - pX);
		
		switch (type) {
			case 0:
				return (left == right);
			case 1:
				return (left < right);
			case 2:
				return (left > right);
			case 3:
				return (left <= right);
			case 4:
				return (left >= right);
			default:
				return false; // if you get here then you idot can't read
					
		}
		
	}
	
	public String toString() {
		return "[" + pX + ", " + pZ + "], " + "[" + qX + ", " + qZ + "], " + type + "\n" + slope + "\n";
	}
	
	private void createSlope() {
		slope = (pZ - qZ) / (pX - qX);
		
	}
	
	public double getSlope() {
		return slope;
	
	}
	
	public double[] getP() {
		return new double[]{pX, pZ};
		
	}
	
	public void setP(double x1, double z1) {
		pX = x1;
		pZ = z1;
		createSlope();
		
	}
	
	public double[] getQ() {
		return new double[] {qX, qZ};
		
	}
	
	public void setQ(double x1, double z1) {
		qX = x1;
		qZ = z1;
		createSlope();
		
	}
	
	public int getType() {
		return type;
		
	}
	
	public void setType(int t) {
		type = t;
		
	}
}