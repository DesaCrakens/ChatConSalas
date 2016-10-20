package salaDeChat;

import java.io.*;
import java.net.*;

public class SelectorSalaThread extends Thread{		

	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Listener l;
	
	public SelectorSalaThread(Socket s, Listener l){
		super();
		this.s = s;
		this.l = l;
		try {
			this.oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		int nroSala = 0;
		try {
			this.oos.writeUTF("Seleccione sala a ingresar (1, 2 ó 3).");
			this.oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			nroSala = this.ois.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(nroSala == 1){
			l.agregarASala1(this.oos);
			new ServerThread(s,l.getSala1(),ois,oos).start();
		}
		else{
			if(nroSala == 2){
				l.agregarASala2(this.oos);
				new ServerThread(s,l.getSala2(),ois,oos).start();
			}
			else{
				l.agregarASala3(this.oos);
				new ServerThread(s,l.getSala3(),ois,oos).start();
			}
		}
	}
	
}
