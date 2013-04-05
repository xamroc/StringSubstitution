import java.io.*;
import java.util.*;

public class Main {

	private static String[] f;
	private static String[] r;

	public static void main(String[] args) {

		Main m = new Main();
		String s = null;
		Scanner x = m.openFile(args[0]);
		while(x.hasNext()){
			String line = m.readLine(x);
			s = m.extractString(line);
			m.extractxN(line);

			String finalString = findFinalString(s);
			System.out.println(finalString);

		}
		m.closeFile(x);
	}

	/** Takes String S and searches within it a string that matches FN.
	 *  The matching string is replaced by string RN.
	 *  The modified string is returned.
	 *  
	 * @param s
	 * @return finalString
	 */

	private static String findFinalString(String s) {
		String finalString = "";
		int strLen = s.length();

		//Puts the characters in String S into an array
		String[] strArray = new String[strLen];
		for (int i = 0; i < strLen; i++){
			strArray[i] = "" + s.charAt(i);
		}

		int sizeFN = f.length;

		//Puts the characters of FN into an array
		for (int i = 0; i < sizeFN; i++){
			int fLen = f[i].length();
			String[] fArray = new String[fLen];
			for (int j = 0; j < fLen; j++){
				fArray[j] = "" + f[i].charAt(j);
			}

			//Initializes finalString to blank
			finalString = "";

			int j = 0;
			int k = 0;

			//Variable m serves as the marker where strings start to match
			int m = 0;

			//Finds a portion of S that matches with FN and restarts search at marker if mismatches at any point
			while (j < fLen && k < strLen){
				if(fArray[j].equals(strArray[k])){
					if(j == 0){
						m = k;
					}
					j++;
					k++;
				} else {
					//System.out.println("False");
					if(j == 0){
						m = k;
					}
					j = 0;
					k = m + 1;
				}

				/* Replaces FN for RN by replacing the first array of the matching portion of S with RN and
				 * turning the rest of the portion blank
				 */
				if(j == fLen){
					strArray[m] = "r" + r[i];
					for(k = 1; k < fLen; k++){
						strArray[k + m] = "r";
					}
					j = 0;
					k = m + fLen;
				}
			}
		}
		for(int i = 0; i < strLen; i++){
			strArray[i] = strArray[i].replace("r", "");
			finalString = finalString + strArray[i];
		}
		return finalString;
	}

	private Scanner openFile(String file) {
		Scanner x = null;
		try {
			x = new Scanner(new File(file));
		} catch(Exception e) {
			System.out.println("Error reading file.");
		}
		return x;
	}

	private String readLine(Scanner x) {
		String line = x.next();
		return line;
	}

	private void closeFile(Scanner x) {
		x.close();
	}

	/** Separates String S from the input string by taking the string before the ';'
	 */

	public String extractString(String tc) {
		String[] parts = tc.split(";");
		String line = parts[0];
		return line;
	}

	/** Separates FN and RN from the input string by taking the string after the ';' and 
	 * alternately storing them values split by ',' into their proper arrays
	 */

	public void extractxN(String tc) {
		String[] parts = tc.split(";");
		String[] nlist = parts[1].split(",");
		int nLen = nlist.length;
		int i = 0;
		String[] fn = new String[nLen/2];
		String[] rn = new String[nLen/2];
		for(int j = 0; j < nlist.length; j = j+2) {
			fn[i] = nlist[j];
			rn[i] = nlist[j+1];
			i++;
		}
		f = fn;
		r = rn;
	}

	/** Prints array for checking purposes	 
	 */

	private static void printArray(String[] list) {
		for (int i = 0; i < list.length; i++){
			System.out.print(list[i] + ", ");
		}
		System.out.println();
	}
}