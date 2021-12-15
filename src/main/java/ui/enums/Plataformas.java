package ui.enums;

import java.net.URL;

public enum Plataformas {
	TOMCAT("Tomcat", "tomcat_icon_22x22.png"), NODE_VUE("Node + Vue", "node_icon_22x22.png"), NODE_VUE_PM2("Node + Vue + PM2", "node_icon_22x22.png");
	
	private String plataforma;
	private String urlIcon;

	Plataformas(String plataforma, String urlIcon) {
		this.plataforma = plataforma;
		this.urlIcon = urlIcon;
	}

	public String getPlataforma() { return plataforma; }

	public URL getUrlIcon() { return getClass().getResource("../assets/"+urlIcon); }
	
	public static String[] toArray() {
		return new String[] { NODE_VUE_PM2.name(), NODE_VUE.name(), TOMCAT.name() };
	}
}
