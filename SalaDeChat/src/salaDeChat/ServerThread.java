package salaDeChat;

import java.io.*;
import java.util.*;
import java.net.*;

public class ServerThread extends Thread{

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private LinkedList<ObjectOutputStream> listaDifusion;
	private Socket s;
	
	public ServerThread(Socket s, LinkedList<ObjectOutputStream> lD, ObjectInputStream ois, ObjectOutputStream oos){
		super();
		this.oos = oos;
		this.ois = ois;
		this.listaDifusion = lD;
		this.s = s;
	}
	
	public void run(){
		String mensaje = " ";
		while(!(mensaje.split(" ").length == 2 && new String("/quit").equals(mensaje.split(" ")[1]))){
			try {
				mensaje = ois.readUTF();
				for(ObjectOutputStream x : listaDifusion){
					x.writeUTF(mensaje);
					x.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		listaDifusion.remove(oos);
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
