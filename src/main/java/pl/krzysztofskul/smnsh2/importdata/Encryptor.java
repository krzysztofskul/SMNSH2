package pl.krzysztofskul.smnsh2.importdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Encryptor {

	/**
	 * Makes a simple encryption of the given string - just changes the interior of the given string to the "...".
	 * @param string to encrypt
	 * @return encrypted string
	 */
	public String encrypt(String string) {
		
		if (string == null) {
			return null;
		}
		
		StringBuilder sbEnc = new StringBuilder(string);
		StringBuilder sbX = new StringBuilder();
		if (string.length() > 5) {
			sbX.append("xx");
			for (int i = 0; i < string.length(); i++) {
				sbX.append(".");
			}
			sbEnc.replace(3, string.length()-2, sbX.toString());
		} else if (string.length() > 3 && string.length() <= 5) {
			sbEnc.replace(1, string.length(), "xx...x");
		} else if (string.length() > 0 && string.length() <= 3) {
			sbEnc.replace(1, string.length(), "x..x");
		} else {
			return "";
		}
		
		return sbEnc.toString();
	}
	
}
