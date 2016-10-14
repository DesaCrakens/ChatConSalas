package salaDeChat;

import java.io.*;
import java.util.*;
import java.net.*;

public class ServerThread extends Thread{

	Socket s;
	LinkedList <Socket> listaDifusion;
	
	public ServerThread(Socket sock, LinkedList<Socket> lD){
		super();
		this.s = sock;
		this.listaDifusion = lD;
	}
	
	public void run(){
		String mensaje = null;
		try {
			DataOutputStream out;
			DataInputStream in = new DataInputStream(this.s.getInputStream());
			while(true){
				if(in.available() > 0){
					mensaje = in.readUTF();
					for(Socket x : listaDifusion){
						out = new DataOutputStream(x.getOutputStream());
						out.writeUTF(mensaje);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
