package checkClient;

import java.io.*;
import java.net.*;
import java.util.*;

import model.*;

public class checkClient {
	
	public ArrayList<CustomerVO> customerlist;
	public checkClient () {
		
		try {
			Socket s = new Socket("172.16.10.229",5000);
			//Socket s = new Socket("127.0.0.1",5000);
			System.out.println("socket connect…");
			
			InputStream is = s.getInputStream();
			//System.out.println(is);
			ObjectInputStream ois = new ObjectInputStream(is);
			
			customerlist = (ArrayList<CustomerVO>)ois.readObject();
			
			System.out.println(customerlist.size());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {

		
		
	}

}