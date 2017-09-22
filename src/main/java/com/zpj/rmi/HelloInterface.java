package com.zpj.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote{
	 public String say() throws RemoteException;
	 public String sayName() throws RemoteException;
	 
}
