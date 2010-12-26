package geotag.core;

public class GeoTag {
	private Point3D coordinates;
	private String content;
	private String owner;
	private TagVisibility visibility;

	public GeoTag(){
		setOwner(null);
		coordinates = new Point3D(0.0, 0.0, 0.0);
		content = null;
		visibility = TagVisibility.PRIVATE;
	}
	
	public GeoTag(Point3D point, String content, TagVisibility visibility){
		coordinates = point;
		this.content = content;
		this.visibility = visibility;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String text) {
		if(text == content){
			return;
		}
		
		content = text;
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
	
	public String getCoordinates(){
		return "X: " + coordinates.x + ", Y: " + coordinates.y + ", Z: " + coordinates.z;
	}

	public void setOwner(String owner) {
		if(owner == this.owner){
			return;
		}
		
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}
}
