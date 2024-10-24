package dk.schioler.secure.entity;

public interface Password extends SecureEntity {

	public Integer getLoginId();

	public void setLoginId(Integer loginId);

	public String getPwd();

	public void setPwd(String pwd);

}