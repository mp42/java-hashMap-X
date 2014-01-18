package hashMapX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param null
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		HashMapX newDB = new HashMapX();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean isExit = false;
		while (!isExit){
			String command = reader.readLine();
			if (command.compareTo("END")==0){
				isExit = true;
			}else{
				newDB.parseCommand(command);
			}
		}

	}

}
