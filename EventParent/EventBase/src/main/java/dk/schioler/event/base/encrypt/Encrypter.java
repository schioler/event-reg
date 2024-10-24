package dk.schioler.event.base.encrypt;

public interface Encrypter {
	public String encrypt(String string);

//	public String decrypt(String string);

	public void setSalt(String salt);
}
