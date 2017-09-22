package com.zpj.rmi;

import java.rmi.*;
import java.rmi.server.*;

public class Hello extends UnicastRemoteObject implements HelloInterface {
	private String message;
	private String name;
	public Hello(String msg) throws RemoteException {
		message = msg;
	}

	public String say() throws RemoteException {
		System.out.println("Called by HelloClient");
		return message;
	}

	@Override
	public String sayName() throws RemoteException {
		return null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}