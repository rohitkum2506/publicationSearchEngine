package team1.searchengine.configuration;

import java.util.Map;

public class EnvironmentMap {

	private static String ElasticPort;

	public static void setEnvVar(String name, String val) {
		Map<String, String> env = System.getenv();
		if(env.get(name) !=null && env.get(name).equals("test")){
			setElasticPort("127.0.0.1");
		}else{
			//public IP of the elasticsearch server
			setElasticPort("54.213.204.149");
		}
	}

	public static void setElasticPort(String ep) {
		ElasticPort = ep;
	}
	
	public static String getElasticPort() {
		return ElasticPort;
	}
}
