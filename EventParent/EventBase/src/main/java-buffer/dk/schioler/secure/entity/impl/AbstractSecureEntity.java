package dk.schioler.secure.entity.impl;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractSecureEntity {

	private Integer id;
	
	private LocalDateTime startTS;
	
	private LocalDateTime endTS;
	
	

	public AbstractSecureEntity(Integer id, LocalDateTime startTS, LocalDateTime endTS) {
		this.id = id;
		this.startTS = startTS;
		this.endTS = endTS;
	}
	
	public LocalDateTime getStartTS() {
		return startTS;
	}

	public void setStartTS(LocalDateTime startTS) {
		this.startTS = startTS;
	}

	public LocalDateTime getEndTS() {
		return endTS;
	}

	public void setEndTS(LocalDateTime endTS) {
		this.endTS = endTS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(endTS, id, startTS);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSecureEntity other = (AbstractSecureEntity) obj;
		return Objects.equals(endTS, other.endTS) && Objects.equals(id, other.id)
				&& Objects.equals(startTS, other.startTS);
	}

	@Override
	public String toString() {
		return "AbstractSecureEntity [id=" + id + ", startTS=" + startTS + ", endTS=" + endTS + "]";
	}
	
	

}
