package dk.schioler.secure.entity;

import java.time.LocalDateTime;

public interface SecureEntity {

	public void setId(Integer id);

	public Integer getId();

	public void setStartTS(LocalDateTime ts);

	public LocalDateTime getStartTS();

	public void setEndTS(LocalDateTime ts);

	public LocalDateTime getEndTS();


}
