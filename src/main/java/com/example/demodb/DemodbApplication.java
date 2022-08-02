package com.example.demodb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.api.ConnectorInfoManager;
import org.identityconnectors.framework.api.ConnectorInfoManagerFactory;
import org.identityconnectors.framework.api.ConnectorKey;
import org.identityconnectors.framework.api.RemoteFrameworkConnectionInfo;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.Uid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodbApplication.class, args);
		
ConnectorInfoManagerFactory fact = ConnectorInfoManagerFactory.getInstance();	
		
		
		RemoteFrameworkConnectionInfo remoteInfo = new RemoteFrameworkConnectionInfo("localhost", 8759,
				new GuardedString("tiger".toCharArray()));
		
		ConnectorInfoManager manager = fact.getRemoteManager(remoteInfo);
		
		ConnectorKey key = new ConnectorKey("net.tirasa.connid.bundles.db.scriptedsql", "2.2.6", "net.tirasa.connid.bundles.db.scriptedsql.ScriptedSQLConnector");       
	    ConnectorInfo info = manager.findConnectorInfo(key);
	    
	    APIConfiguration apiConfig = info.createDefaultAPIConfiguration();
	    
	    ConfigurationProperties properties = apiConfig.getConfigurationProperties();
	    
	      properties.setPropertyValue("host", "localhost");
	      properties.setPropertyValue("user", "root");
	      properties.setPropertyValue("password", new GuardedString("milan".toCharArray()));
	      properties.setPropertyValue("port", "3306");
	      properties.setPropertyValue("database", "test");
	      properties.setPropertyValue("jdbcDriver", "com.mysql.cj.jdbc.Driver");
	      properties.setPropertyValue("autoCommit", true);
		  properties.setPropertyValue("jdbcUrlTemplate", "jdbc:mysql://localhost:3306/test");
		  
		  properties.setPropertyValue("testScriptFileName", "C:\\Users\\MILAN RAJA\\Desktop\\Internship\\Bunddels\\ConnIdDBBundle-db-2.2.6\\scriptedsql\\src\\main\\resources\\samples\\TestScript.groovy");
		  properties.setPropertyValue("createScriptFileName", "C:\\Users\\MILAN RAJA\\Desktop\\Internship\\Bunddels\\ConnIdDBBundle-db-2.2.6\\scriptedsql\\src\\main\\resources\\samples\\CreateScript.groovy");
		  properties.setPropertyValue("deleteScriptFileName", "C:\\Users\\MILAN RAJA\\Desktop\\Internship\\Bunddels\\ConnIdDBBundle-db-2.2.6\\scriptedsql\\src\\main\\resources\\samples\\DeleteScript.groovy");	
		  properties.setPropertyValue("updateScriptFileName", "C:\\Users\\MILAN RAJA\\Desktop\\Internship\\Bunddels\\ConnIdDBBundle-db-2.2.6\\scriptedsql\\src\\main\\resources\\samples\\UpdateScript.groovy");
	      
		  Set<Attribute> set=new HashSet<>();
		    Map<String,Object> map=new HashMap<>();
			String id=UUID.randomUUID().toString();
		    map.put(Name.NAME,id);
		    map.put("firstname","Milan");
		    map.put("lastname","RAja");
		    map.put("fullname","milan raja");
		    map.put("email","milanraja881@gmail.com");
		    map.put("organization","UNOTECHLTD");

		    for (Entry<String, Object> entry : map.entrySet()) {
				set.add(AttributeBuilder.build(entry.getKey(), entry.getValue()));
			}
		  
		  
	    ConnectorFacade conn = ConnectorFacadeFactory.getInstance().newInstance(apiConfig);

	    conn.validate();
	    conn.test();
	   // conn.create(ObjectClass.ACCOUNT, set,null);
	    //conn.delete(ObjectClass.ACCOUNT, new Uid("e6cddd56-9cb6-4538-bf92-d46b4f9fb6d3"), null);
	    Set<Attribute> Uset=new HashSet<>();
	    Map<String,Object> Umap=new HashMap<>();
	    Umap.put("firstname","Milan");
	    Umap.put("lastname","RAja");
	    Umap.put("fullname","milan raja");
	    Umap.put("email","milanraja881@gmail.com");
	    Umap.put("organization","UNOTECHLTD");

	    for (Entry<String, Object> entry : Umap.entrySet()) {
			Uset.add(AttributeBuilder.build(entry.getKey(), entry.getValue()));
		}
	    
	    conn.update(ObjectClass.ACCOUNT, new Uid("c33fda1f-0f01-41ab-8f4e-46fc5a6d5f0f"), Uset, null);
	}

}
