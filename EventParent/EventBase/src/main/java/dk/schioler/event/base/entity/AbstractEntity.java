package dk.schioler.event.base.entity;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractEntity {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private Integer id;
	
	public AbstractEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AbstractPersistedType [id=" + id + "]";
	}

	
	
}
