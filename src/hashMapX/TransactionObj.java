package hashMapX;

import java.util.Stack;

public class TransactionObj {
	
	private Stack<String> commands_;
	private HashMapX db_;
	
	public TransactionObj(HashMapX db){
		db_ = db;
		commands_ = new Stack<>();
	}
	
	public void addSetCommand(String[] command){
		if (command[2]==null){
			System.out.println("Cannot set null values");
			return;
		}
		String oldValue = db_.set(command[1], command[2]);
		if (oldValue == null){
			commands_.push(command[1]);
		}else {
			commands_.push(command[1]+" "+oldValue);
		}
		
	}
	
	public void addUnsetCommand(String[] command){
		String oldValue = db_.unset(command[1]);
		if (oldValue!=null){
			commands_.push(command[1]+" "+oldValue);
		}
	}
	
	public void rollback(){
		while (!commands_.isEmpty()){
			String command = commands_.pop();
			String[] parsed = command.split(" ");
			if (parsed.length==1){
				db_.unset(parsed[0]);
			}else {
				db_.set(parsed[0],parsed[1]);
			}
		}
	}

}
