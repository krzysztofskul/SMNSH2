package pl.krzysztofskul.sensit.smnsh.importdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Encryptor {

	/**
	 * Simple encryption string
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
			for (int i = 0; i < string.length(); i++) {
				sbX.append(".x.");
			}
			sbEnc.replace(3, string.length()-2, sbX.toString());
		} else if (string.length() > 3 && string.length() <= 5) {
			sbEnc.replace(1, string.length(), "x@.x...x..%");
		} else if (string.length() > 0 && string.length() <= 3) {
			sbEnc.replace(1, string.length(), "x...x%");
		} else {
			return "";
		}
		
		return sbEnc.toString();
	}
	
}
