package com.npaw.utilities.args;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Args {

	String port;

	public Args(String[] args) throws ArgsException {

		parseParameters(Arrays.asList(args));
	}

	private void parseParameters(List<String> argsList) throws ArgsException{
		ListIterator<String> currentArgument;
		
		try{

			for (currentArgument = argsList.listIterator();currentArgument.hasNext();){
				String argString = currentArgument.next();
				if (argString.startsWith("-")){					
					port = currentArgument.next();
					currentArgument.previous();
					
				}
				
			}
							
		}
		catch (Exception e){
			throw new ArgsException(e.getMessage());
		}
		
		
	}
	
	public String getPort(){
		return port;
	}
	
	
}
