package salaDeChat;

import java.io.*;
import java.net.*;
import java.util.*;


public class Listener extends Thread{
	
	ServerSocket svSocket;
	LinkedList<ObjectOutputStream> sala1;
	LinkedList<ObjectOutputStream> sala2;
	LinkedList<ObjectOutputStream> sala3;
	
	public Listener(){
		try {
			svSocket = new ServerSocket(4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sala1 = new LinkedList<ObjectOutputStream>();
		sala2 = new LinkedList<ObjectOutputStream>();
		sala3 = new LinkedList<ObjectOutputStream>();
	}
	
	public void run(){
		Socket s = null;
		while(true){
			try {
				s = this.svSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new SelectorSalaThread(s,this).start();
		}
	}
	
	public void agregarASala1(ObjectOutputStream oos){
		this.sala1.add(oos);
	}
	
	public void agregarASala2(ObjectOutputStream oos){
		this.sala2.add(oos);
	}
	
	public void agregarASala3(ObjectOutputStream oos){
		this.sala3.add(oos);
	}

	public LinkedList<ObjectOutputStream> getSala1() {
		return sala1;
	}

	public LinkedList<ObjectOutputStream> getSala2() {
		return sala2;
	}

	public LinkedList<ObjectOutputStream> getSala3() {
		return sala3;
	}
	
	public static void main(String[] args) {
		new Listener().start();
	}
}
