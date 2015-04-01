package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import lang.Languages_EN;
import lang.Languages_ID;

public class Client {
	
	Languages_EN lang = new Languages_EN();

//server details, ip address, port, etc.	
	private String hostname,username;
	private int port;
	
//prints an object to the network
	private ObjectOutputStream outputStream;
//read an object from the network
	private ObjectInputStream inputStream;
//socket that is used to connect to the server	
	private Socket socket;
	
	Client(String server, int port)
	{
		this.hostname = server;
		this.port = port;
		if (start() != true)
			System.out.println(lang.CANNOT_CONNECT);
	}
	//attempt to connect to specified hostname and port
	public boolean start(){
		try
		{
			socket = new Socket(InetAddress.getByName(hostname),port);
		}
		catch (UnknownHostException unknownHostException)
		{
			System.out.println(Languages_EN.UNKNOWN_HOST);
			unknownHostException.printStackTrace();
			return false;
		} 
		catch (IOException IOEx)
		{
			System.out.println(Languages_EN.SOCKET_CREATE_ERROR);
			IOEx.printStackTrace();
			return false;
		}
		//Create stream
		try
		{
			inputStream  = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException IOEx)
		{
			System.out.println(Languages_EN.STREAM_ERROR);
			IOEx.printStackTrace();
			return false;
		}
		
		try
		{
			outputStream.writeObject(username);
		}
		catch (IOException IOEx)
		{
			System.out.println(Languages_EN.USERNAME_ERROR);
			IOEx.printStackTrace();
			return false;
		}
		return true;
	}
	void sendMessage(ChatMessage msg)
	{
		try
		{
			outputStream.writeObject(msg);
		}
		catch (IOException IOEx)
		{
			System.out.println(Languages_EN.SEND_MSG_ERROR);
			IOEx.printStackTrace();
		}
	}
	private void disconnect()
	{
		try
		{
			if (inputStream != null) inputStream.close();
			if (outputStream != null) outputStream.close();
			if (socket != null) socket.close();
		}
		catch (IOException IOEx){}
	}
}
