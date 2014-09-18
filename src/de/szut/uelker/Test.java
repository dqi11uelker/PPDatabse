package de.szut.uelker;

public class Test {
	/**
	 * Just a small program to test classes.
	 * @param args ignored
	 */
	public static void main(String[] args){
		DBSZUT db = new DBSZUT("database", "sa", "");
		new DBDialog (db);
	}
}
