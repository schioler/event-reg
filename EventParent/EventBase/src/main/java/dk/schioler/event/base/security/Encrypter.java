package dk.schioler.event.base.security;

public interface Encrypter {
	public String encrypt(String string);

//	public String decrypt(String string);

	public void setSalt(String salt);
}
