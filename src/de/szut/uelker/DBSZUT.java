package de.szut.uelker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * 
 * This class contains the database and enough methods to use make them useful easily.
 * 
 * @author Yunus Ülker
 *
 */
public class DBSZUT {
	public static final int NAME = 0;
	public static final int FIRSTNAME = 1;
	public static final int EDUCATIONALPROGRAM = 2;
	public static final int CLASS = 3;
	private String path;
	private String username;
	private String password;
	private Connection con;
	private Statement stmt;
	private String sql;
	private StringConstants strings;

	/**
	 
	 * @param path Path of your database. <b>If not existing</b> the database will be created there.
	 * @param username Username to get access to the database.
	 * @param password Password to get access to the database
	 */
	public DBSZUT(String path, String username, String password) {
		strings = new StringConstants();
		this.path = path;
		this.username = username;
		this.password = password;

		initDB();
		//		String[] s = new String[]{"uelker","yunus","m","dqi","dqi11",};
		//		String[] s2 = new String[]{"buelker","yunus","m","dqi","dqi11",};
		//		String[] s3 = new String[]{"suelker","yunus","m","dqi","dqi11",};
		//		insert(s, new java.sql.Date(1196, 1, 1));
		//		insert(s2, new java.sql.Date(1196, 1, 1));
		//		insert(s3, new java.sql.Date(1196, 1, 1));

	}
	
	@Override
	protected void finalize() throws Throwable {
		commitAndClose();
		super.finalize();
	}

	/**
	 * Initializes the Connection to the Database-file.
	 */
	private void initDB() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", username, password);
			create();

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //independent from source code
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, strings.getMESSAGE_ERROR_DB_IS_BLOCKED());
		}
	}

	/**
	 * Creates the database if it is not already existing.
	 */
	private void create(){
		try {
			stmt = con.createStatement();
			sql = "CREATE TABLE IF NOT EXISTS schueler ( nname VARCHAR(50), "
					+ "vname VARCHAR(20), sex VARCHAR(1), gebdat DATE, "
					+ "bgang VARCHAR(30), klasse VARCHAR(30), PRIMARY KEY (nname, vname) );";
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			//won't happen
		}
	}

	/**
	 * Insert a new entry in the database
	 * @param values all information which should insert in the database, apart from the date <br> nname,vname,sex,bgang,klass
	 * @param date the date which should insert in the database
	 * @exception SQLIntegrityConstraintViolationException if already a entry with the same primary key exist in the database
	 * @exception SQLException if the program can't insert a new entry into the database 
	 */
	public boolean insert(String[] values, java.sql.Date date){
		try {
			stmt = con.createStatement();

			sql = "INSERT INTO schueler (nname, vname, sex, gebdat, bgang, klasse)  "
					+ "VALUES('" + values[0] + "', '" + values[1] + "', '" + values[2] + "', '"+ date + "', '" + values[3] + "', '" + values[4] + "');";
			System.out.println(sql);

			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		}catch(SQLIntegrityConstraintViolationException f){
			JOptionPane.showMessageDialog(null, strings.getMESSAGE_ERROR_UNIQUE_ENTRY());
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, strings.getMESSAGE_Error());
		}
		return false;
	}

	/**
	 * Simple SQL-query <code> SELECT * FROM db; </code>
	 * @return the whole content from the database
	 */
	public ResultSet selectAll(){
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sql = "SELECT * FROM schueler";
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			//happens when used on an empty database-> doesnt matter
		}
		return null;
	}

	/**
	 * This method send a query to the database with one condition.
	 * @param type use a constant from this class to select a column
	 * @param text the value in form of text.
	 * @return The matching entries in the database.
	 */
	public ResultSet select(int type, String text){
		try {
			String column = "";
			if (type ==  NAME){
				column = "nname";
			} else if (type == FIRSTNAME) {
				column = "vname";
			} else if (type == EDUCATIONALPROGRAM) {
				column = "bgang";
			} else if (type == CLASS){
				column = "klasse";
			}

			String sqlCondition = column + " LIKE '%" + text + "%';";
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sql = "SELECT * FROM schueler WHERE " + sqlCondition;
			System.out.println(sql);

			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, strings.getMESSAGE_ENTRY_NOT_FOUND());
		}
		return null;
	}

	/**
	 * Removes one entry. So are both parts of the primary key needed.
	 * @param keyName The last name of the entry.
	 * @param keyFirsttname The first name of the entry.
	 * @return The refreshed database.
	 */
	public ResultSet delete(String keyName, String keyFirsttname) {
		try {
			stmt = con.createStatement();
			sql = "DELETE FROM schueler WHERE nname = '" + keyName + "' AND vname = '" + keyFirsttname + "';";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace(); //never reached
		}
		return selectAll();
	}

	/**
	 * commits the connection to the database
	 */
	public void commit(){
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * commits and closes the connection to the database, used in <code>finalize</code>
	 */
	public void commitAndClose(){
		try {
			con.commit();
			con.close();
		} catch (SQLException e) {
			//reached this point? db should be closed too :D
		}
	}

}
