public class DictionaryManager {
	public static void main(String... args) {
		Dictionary dictionary = Dictionary.INSTANCE;
		showWelcomeBanner();
		try{
			dictionary.initialize();
			dictionary.getWordAndShow();
			dictionary.shutDown();
		}catch(DictionaryException de){
			de.printTechnicalDetails();
			System.err.println("The application could run.Please contact the vendor");
			
		}
		
	}

	static void showWelcomeBanner() {
		System.out.println("***************************************************");
		System.out.println("	Welcome to the interactive dictionary          ");
		System.out.println("											       ");
		System.out.println("---------------------------------------------------");
		System.out.println("Please enter a text to find its synonym and antonym");
		System.out.println("enter 'stop' to quit the program                   ");
		System.out.println("***************************************************");

	}
}
