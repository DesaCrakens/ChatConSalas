package salaDeChat;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	
	Socket cliSocket;
	String nombre;
	int enSala;
	
	public Cliente(){
		
	}
	
	public void iniciar(String ip, int puerto){
		Scanner teclado = new Scanner(System.in);
		String mensajeOut = " ";
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		System.out.println("Ingrese su nombre de usuario: ");
		this.nombre = teclado.nextLine();
		try {
			this.cliSocket = new Socket(ip,puerto);
			in = new ObjectInputStream(this.cliSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ClienThread(in).start();					//Este imprime todo lo que se escribe en el chat
		try {
			out = new ObjectOutputStream(this.cliSocket.getOutputStream());
			this.enSala = teclado.nextInt();
			out.writeInt(this.enSala);
			out.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!mensajeOut.equals(new String(this.nombre + ": /quit"))){			//si en el chat se escribe /quit sale
			mensajeOut = this.nombre + ": " + teclado.nextLine();
			try {
				out.writeUTF(mensajeOut);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			this.cliSocket.close();
			teclado.close();			//cierro todo lo de este cliente
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			
		}
	}
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.iniciar("192.168.1.37",4444);
	}
}
