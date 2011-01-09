package geotag.core;

public class Properties {

	public static final String VISIBILITY_0 = "PRIVATE";
	public static final String VISIBILITY_1 = "PROTECTED";
	public static final String VISIBILITY_2 = "PUBLIC";
	
	public static final String URL_PROTOCOL = "http";
	public static final String URL_HOST = "sbickt.heroku.com";
	public static final String URL_FOLDER_INDEX = "/geotags/";
	public static final String URL_FOLDER_LIST = "/geotags/list.kml";
	public static final int URL_PORT = 80;
	
	public static final String getUrl(){
		return URL_PROTOCOL + "://" + URL_HOST + ":" + Integer.toString(URL_PORT);
	}
	
	public static final String getUrlIndex(){
		return getUrl() + URL_FOLDER_INDEX;
	}
}
