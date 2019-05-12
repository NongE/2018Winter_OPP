package orderClient;

import java.io.*;
import java.io.Serializable;
import java.net.*;
import java.util.*;

import model.*;
import orderUI.*;

public class orderClient {
	
	ArrayList<CorderVO> orderlist;
	public orderClient (ArrayList<CorderVO> orderlist) {
		
		this.orderlist = orderlist;
		
		try {
			Socket s = new Socket("172.16.10.229",5001);
		//	Socket s = new Socket("127.0.0.1",5001);
			System.out.println("socket connect…");
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(orderlist);
			System.out.println("전송완료…");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {

		
		
	}

}