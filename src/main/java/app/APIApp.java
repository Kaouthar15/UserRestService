package app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class APIApp extends Application {
	private Set<Object> singletons;
	
	public APIApp() {
		singletons = new HashSet<Object>();
	}
	
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
}