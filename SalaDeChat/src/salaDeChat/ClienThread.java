package salaDeChat;

import java.io.*;

public class ClienThread extends Thread{

	ObjectInputStream ois;
	
	public ClienThread(ObjectInputStream ois){
		super();
		this.ois = ois;
	}
	
	public void run(){
		while(true){
			try {
				System.out.println(ois.readUTF());
				} catch (Exception e) {
						try {
							ois.close();
							break;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("No se pudo cerrar el OIS.");
						}
			}
		}
	}
}
