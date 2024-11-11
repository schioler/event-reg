package dk.schioler.event.base.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.EventBaseException;

@Component
public abstract class AbstractEntity {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Integer id;

	private Integer loginId;

	private String name;

	private AbstractEntity parent;

	private List<AbstractEntity> children = new ArrayList<AbstractEntity>();

	public AbstractEntity(Integer id, Integer loginId, String name) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	/*
	 * All things concerning child && parent
	 */
	/* GETTERS */
	public AbstractEntity getParent() {
		return this.parent;
	}

	public Integer getParentId() {
		Integer retVal = null;
		if (this.parent != null) {
			retVal = this.parent.getId();
		}
		return retVal;
	}

	public List<AbstractEntity> getChildren() {
		return this.children;
	}

	/* SETTERS */

	public void setParentId(Integer id) {
		setParent(id, null);
	}

	public void setParent(AbstractEntity parent) {
		setParent(null, parent);
	}

	public void removeChild(AbstractEntity child) {
		if (child != null) {
			if (this.children.contains(child)) {
				children.remove(child);
			}
			child.setParent(child);
		}
	}

	public void addChild(AbstractEntity child) {
		if (child != null) {
			if (!children.contains(child)) {
				logger.debug("will add child:");
				child.setParent(this);
			}
		}
	}

	public abstract AbstractEntity instantiateParent();

	private void setParent(Integer parentId, AbstractEntity parent) {
		if (parentId == null && parent == null) {
			if (this.parent != null) {
				this.parent.getChildren().remove(this);
				this.parent = null;
			}
		} else if (parentId != null && parent == null) {
			if (this.parent != null) {
				this.parent.getChildren().remove(this);
			}

			this.parent = instantiateParent();
			this.parent.setId(parentId);
			this.parent.getChildren().add(this);

		} else if (parentId == null && parent != null) {
			if (this.parent != null) {
				if (!this.parent.equals(parent)) {
					this.parent.getChildren().remove(this);
				}
				this.parent = parent;
				this.parent.getChildren().add(this);

			} else {
				this.parent = parent;
				this.parent.getChildren().add(this);
			}
		} else if (parentId != null && parent != null) {
			if (!parentId.equals(parent.getId())) {
				throw new EventBaseException("specified id=" + parentId + ", parent.getId()=" + parent.getId());
			}
			if (this.parent != null) {
				if (!this.parent.equals(parent)) {
					this.parent.getChildren().remove(this);
				}
				this.parent = parent;
				this.parent.getChildren().add(this);

			} else {
				this.parent = parent;
				this.parent.getChildren().add(this);
			}

		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(children, id, loginId, parent);
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
		return Objects.equals(children, other.children) && Objects.equals(id, other.id)
				&& Objects.equals(loginId, other.loginId) && Objects.equals(parent, other.parent);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n******************\n");
		builder.append(getClass().getName());
		builder.append("[id=").append(id);
		builder.append(", loginId=").append(loginId);
		builder.append(", name=").append(name);
		builder.append("\n    ->->->->parent=");
		if (this.parent != null) {
			builder.append("[ id="+parent.getId()+", loginId="+parent.getLoginId()+", name="+parent.getName()).append("]");
		} else {
			builder.append("null");
		}
		builder.append("\n    ->->->->children:\n");
		for (AbstractEntity abstractEntity : children) {
			builder.append("      [id=").append(abstractEntity.getId());
			builder.append(", loginId=").append(abstractEntity.getLoginId());
			builder.append(", name=").append(abstractEntity.getName());			
//		builder.append("]");
		}
		builder.append("\n******************");
		return builder.toString();
	}

}
