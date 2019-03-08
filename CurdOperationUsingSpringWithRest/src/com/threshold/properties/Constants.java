package com.threshold.properties;

public class Constants { 

	public Constants(
			String MYSQL_USER,String MYSQL_PASSWORD,int MYSQL_PORT,String MYSQL_HOST, String INSTANCE_DB_NAME) {
	
		Constants.MYSQL_USER=MYSQL_USER;
		Constants.MYSQL_PASSWORD=MYSQL_PASSWORD;
		Constants.MYSQL_PORT=MYSQL_PORT;
		Constants.MYSQL_HOST=MYSQL_HOST;
		Constants.INSTANCE_DB_NAME=INSTANCE_DB_NAME;
	} 

	// Mysql Props
	public static String MYSQL_USER;
	public static String MYSQL_PASSWORD;
	public static int MYSQL_PORT;
	public static String MYSQL_HOST;
	public static String INSTANCE_DB_NAME;
}
