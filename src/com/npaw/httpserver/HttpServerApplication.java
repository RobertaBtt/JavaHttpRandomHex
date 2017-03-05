package com.npaw.httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.npaw.utilities.args.Args;
import com.npaw.utilities.args.ArgsException;

public class HttpServerApplication {


	public static void main(String[] args) {
		
		try{
			
			Args arg = new Args(args);
			String portNumber = arg.getPort();
			
			executeApplication(portNumber);
				
		}
		catch(ArgsException e){
			System.out.println("The parameters must be: -port n"+ e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Number format Exception of parameter port");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception during the server request");
			e.printStackTrace();
		}	
		
	}
	
	private static void executeApplication(String portNumber) throws NumberFormatException, IOException{
		
		
		ServerSocket serverSocket = new ServerSocket(Integer.valueOf(portNumber));
		while (true) {
			
			
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			String hexadecimal = getHesadecimalRandom();
			
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(hexadecimal);
			pw.close();
			os.close();
			socket.close();
			
		}
			
	}
	
	private static String getHesadecimalRandom(){
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Date date = new Date();
		String nowDateString = dateFormat.format(date).toString();
		
		
		MessageDigest md;
		StringBuffer sb ;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(nowDateString.getBytes());
	        sb = new StringBuffer();
	        
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        
	        
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return "";
		}

		return sb.toString();
	}

	
	
	
}
