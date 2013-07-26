package com.teqlabs.bryan;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.uri.ClassURI;

public class Main {
	public static void main(String[] args) {
		PluginManager pm = PluginManagerFactory.createPluginManager();
		pm.addPluginsFrom(ClassURI.CLASSPATH);
		
//		OmniLinkII om = pm.getPlugin(OmniLinkII.class);
//		om.createConnection();
	}
}
