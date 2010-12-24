package geotag.core;

public class GeoTag {
	private Point3D coordinates;
	private String text;
	private TagVisibility visibility;

	public GeoTag(){
		coordinates = new Point3D(0.0, 0.0, 0.0);
		text = null;
		visibility = TagVisibility.PRIVATE;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(text == this.text){
			return;
		}
		
		this.text = text;
	}

	public TagVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(TagVisibility visibility) {
		if(visibility == this.visibility){
			return;
		}
		
		this.visibility = visibility;
	}

	public void setX(Double x){
		if(x == coordinates.x){
			return;
		}
		
		coordinates.x = x;
	}
	
	public Double getX(){
		return coordinates.x;
	}
	
	public void setY(Double y){
		if(y == coordinates.y){
			return;
		}
		
		coordinates.y = y;
	}
	
	public Double getY(){
		return coordinates.y;
	}
	
	public void setZ(Double z){
		if(z == coordinates.z){
			return;
		}
		
		coordinates.z = z;
	}
	
	public Double getZ(){
		return coordinates.z;
	}
}
