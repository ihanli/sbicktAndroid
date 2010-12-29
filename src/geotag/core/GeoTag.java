package geotag.core;

public class GeoTag {
	private Point3D coordinates;
	private String content;
	private String owner;
	private TagVisibility visibility;
	private Integer id;

	public GeoTag(){
		setOwner(null);
		coordinates = new Point3D(0.0, 0.0, 0.0);
		content = null;
		visibility = TagVisibility.PRIVATE;
		id = null;
	}
	
	public GeoTag(Integer id, Point3D point, String owner, String content, TagVisibility visibility){
		this.id = id;
		coordinates = point;
		this.content = content;
		setOwner(owner);
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
	
	public String coordinatesToString(){
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(this.id == id){
			return;
		}
		
		this.id = id;
	}

	public void prettyPrint(){
		System.out.println(getId());
		System.out.println(getOwner());
		System.out.println(getContent());
		System.out.println(coordinatesToString());
		System.out.println(getVisibility());
		System.out.print("\n");
	}
}
