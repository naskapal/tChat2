package client;

import java.io.Serializable;

//this class is used to exchange message between server and clients

public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final int MESSAGE = 1, LOGOUT = 2;
	private int type;
	private String message;
	
	//constructor
	ChatMessage(int type, String message)
	{
		this.type = type;
		this.message = message;
	}
	
	int getType()
	{
		return type;
	}
	
	String getMessage()
	{
		return message;
	}
	
}
