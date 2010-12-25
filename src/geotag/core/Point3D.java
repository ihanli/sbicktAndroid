package geotag.core;

public class Point3D {
	public Double x;
	public double y;
	public double z;
	
	public Point3D(double d, double e, double f){
		x = d;
		y = e;
		z = f;
	}
	
	public Point3D(double coordinates[]){
		x = coordinates[0];
		y = coordinates[1];
		z = coordinates[2];
	}
}