import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Dictionary {
	
	private static final String connectionString = "jdbc:derby:dictionaryDB";
	private static Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private static final Pattern pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
	public static final Dictionary INSTANCE = new Dictionary(); 
	private Dictionary() {

	}

	public void initialize() throws DictionaryException{
		try {
			conn = DriverManager.getConnection(connectionString);
			st = conn.createStatement();
		} catch (SQLException e) {
			throw new DictionaryException(e);
		}
	}

	public void getWordAndShow() throws DictionaryException{
			
		try{
			Scanner sc = new Scanner(System.in);
			String word = sc.next();
			Matcher m = pattern.matcher(word);

			while (!word.equalsIgnoreCase("STOP")) {
				if (m.find()) {
					System.out.println("special characters not allowed");
				} else if (!word.equalsIgnoreCase("STOP")) {
					showSynonymAntonym(word);
					word = sc.next();
				}
			}
			
		}catch(Exception e){
			throw new DictionaryException(e);
		}
	}

	private void showSynonymAntonym(String myWord) throws DictionaryException{
		String notAvailable = "Not Available in the dictionary";
		String synonym = null;
		String antonym = null;
		int count = 0;

		try {
			rs = st.executeQuery("SELECT myword,synonym,antonym FROM theasurus where myword like '" + myWord.toLowerCase() + "%'");
			while (rs.next()) {
				count++;
				System.out.println("**************");
				System.out.println("word:" + rs.getString(1));
				synonym = rs.getString(2);

				if (synonym.length() == 0) {
					synonym = notAvailable;
				}
				System.out.println("synonym:" + synonym);
				antonym = rs.getString(3);

				if (antonym.length() == 0) {
					antonym = notAvailable;
				}
				System.out.println("antonym:" + antonym);

			}
			if (count == 0) {
				System.out.println("We're sorry.We could not find " + myWord);
			}
		} catch (SQLException sqle) {
			throw new DictionaryException(sqle);
		}
	}

	

	public void shutDown() throws DictionaryException {
		try {
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se) {
				if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
					System.out.println("Thanks for using the dictionary !!!");
				} else {
					throw new DictionaryException(se);
				}
			}

		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				throw new DictionaryException(sqle);
			}

			try {
				if (st != null) {
					st.close();
					st = null;
				}
			} catch (SQLException sqle) {
				throw new DictionaryException(sqle);
			}

			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
	
				throw new DictionaryException(sqle);
			}
		}
		System.exit(0);
	}

	
}

class DictionaryException extends Exception {
	
	private String message;

	DictionaryException(Exception e) {
		this.message=e.getMessage();
	}
	
	public void printTechnicalDetails(){
		System.out.println(this.message);
	}

}