package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractTSEntity extends AbstractEntity {
	
	
	private LocalDateTime startTS;
	
	private LocalDateTime endTS;

	public AbstractTSEntity(Integer id, LocalDateTime startTS, LocalDateTime endTS) {
		super(id);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(endTS, startTS);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTSEntity other = (AbstractTSEntity) obj;
		return Objects.equals(endTS, other.endTS) && Objects.equals(startTS, other.startTS);
	}

	@Override
	public String toString() {
		return "AbstractUserEntity [id="+ getId()+"startTS=" + startTS + ", endTS=" + endTS + "]";
	}

	
}
