package pl.krzysztofskul.smnsh2.importdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.thedeanda.lorem.LoremIpsum;

class EncryptorTest {

	@Test
	void testEncrypt() {
		String string = LoremIpsum.getInstance().getTitle(2);
		Encryptor encryptor = new Encryptor();
		System.out.println(encryptor.encrypt("0123456789ABCDEFGHIJK"));
		System.out.println(encryptor.encrypt("0123456789"));
		System.out.println(encryptor.encrypt("012345678"));
		System.out.println(encryptor.encrypt("01234567"));
		System.out.println(encryptor.encrypt("0123456"));
		System.out.println(encryptor.encrypt("012345"));
		System.out.println(encryptor.encrypt("01234"));
		System.out.println(encryptor.encrypt("0123"));
		System.out.println(encryptor.encrypt("012"));
		System.out.println(encryptor.encrypt("01"));
		System.out.println(encryptor.encrypt("0"));
		System.out.println(encryptor.encrypt(""));
		System.out.println(encryptor.encrypt(null));
	}

}
