package dk.schioler.event.base.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dk.schioler.event.base.EventBaseException;

public class EncrypterSHA256 implements Encrypter {

	private String salt;

	private final String algorithm = "SHA-256";

	@Override
	public void setSalt(String salt) {
		this.salt = salt;
	}


	private List<String> verifyState() {
		List<String> errors = new ArrayList<String>();

		if (StringUtils.isBlank(salt)) {
			errors.add("salt has not been set.");
		}
		if (StringUtils.isBlank(algorithm)) {
			errors.add("algorithm has not been set.");
		}
		return errors;
	}

	@Override
	public String encrypt(String string) {
		List<String> errors = verifyState();
		if (errors.size()>0) {
			throw new EventBaseException("Either salt or algorithm, or none, has been set: " + errors.toString());
		}
		if (StringUtils.isBlank(string)) {
			throw new EventBaseException("argument can not be null");
		}
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(string.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
	}

//	@Override
//	public String decrypt(String string) {
//		List<String> errors = verifyState();
//		if (errors.size()>0) {
//			throw new EventBaseException("Either salt or algorithm, or none, has been set: " + errors.toString());
//		}
//		if (StringUtils.isBlank(string)) {
//			throw new EventBaseException("argument can not be null");
//		}
//        String generatedPassword = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(salt.getBytes());
//            byte[] bytes = md.digest(string.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < bytes.length; i++) {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
//                        .substring(1));
//            }
//            generatedPassword = sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//	}

}
