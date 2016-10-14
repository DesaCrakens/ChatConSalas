package salaDeChat;

import java.io.*;

public class ClienThread extends Thread{

	DataInputStream in;
	
	public ClienThread(DataInputStream in){
		super();
		this.in = in;
	}
	
	public void run(){
		try {
			while(true){		
				if(this.in.available() > 0)		//si hay algo que leer
					System.out.println(in.readUTF());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
