package hashMapX;

import java.util.HashMap;
import java.util.Stack;

public class HashMapX {
	
	private HashMap<String,String> map_;
	private Stack<TransactionObj> activeXActions_;
	
	public HashMapX(){
		map_ = new HashMap<>();
		activeXActions_ = new Stack<>();
	}
	
	public void parseCommand(String command){
		String[] parsedCommand =  command.split(" ");
		if (parsedCommand[0].compareTo("SET")==0 && parsedCommand.length==3){
			if (activeXActions_.isEmpty()){
				if (parsedCommand[2]==null){
					System.out.println("Cannot set null values");
					return;
				}
				this.set(parsedCommand[1], parsedCommand[2]);
			}else {
				activeXActions_.peek().addSetCommand(parsedCommand);
			}
			return;
		}
		if (parsedCommand[0].compareTo("GET")==0){
			System.out.println(map_.get(parsedCommand[1]));
			return;
		}
		if (parsedCommand[0].compareTo("UNSET")==0){
			if (activeXActions_.isEmpty()){
				this.unset(parsedCommand[1]);
			}else {
				activeXActions_.peek().addUnsetCommand(parsedCommand);
			}
			return;
		}
		if (parsedCommand[0].compareTo("BEGIN")==0){
			this.startXaction();
			return;
		}
		if (parsedCommand[0].compareTo("ROLLBACK")==0){
			this.rollback();
			return;
		}
		if (parsedCommand[0].compareTo("COMMIT")==0){
			this.commit();
			return;
		}
		else {
			System.out.println("Invalid command");
		}
		
	}
	
	
	public String set(String key, String value){
		String old = null;
		if (map_.containsKey(key)){
			old = map_.get(key);
		} 
		map_.put(key, value);
		return old;
	}
	
	public String unset(String key){
		String value = null;
		if (map_.containsKey(key)){
			value = map_.get(key);
			map_.remove(key);
		}
		return value;
	}
	
	public String get(String key){
		return map_.get(key);
	}
	
	public void startXaction(){
		activeXActions_.push(new TransactionObj(this));
	}
	
	public void rollback(){
		if (activeXActions_.isEmpty()){
			System.out.println("NO TRANSACTIONS IN PROGRESS");
			return;
		}
		activeXActions_.pop().rollback();
	}
	
	public void commit(){
		if (activeXActions_.isEmpty()){
			System.out.println("NO TRANSACTIONS IN PROGRESS");
			return;
		}
		activeXActions_.clear();
	}
	
}
