package com.wes.camel.utils;

public class Constants {

	private Constants() {
	}
    public static final String ENCRYPT_ETC = ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    public static final String DIRECT_PUBLISHER = "direct:publisher";
    public static final String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String START_OF_ROUTE = "**** START OF ROUTE *****";
	public static final String UPDATE_QUERY = "sql:update outbox set published = 'true' where id = :#${body.id}";


}
