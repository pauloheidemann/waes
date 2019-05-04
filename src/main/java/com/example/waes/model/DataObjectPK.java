package com.example.waes.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author Paulo Henrique Heidemann Class that represents the composite key from
 *         entity {@link DataObject}
 *
 */
@Embeddable
public class DataObjectPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2017799990548132651L;
	private Long id;
	private Integer operation;

	public DataObjectPK() {
	}

	public DataObjectPK(Long id, Integer operation) {
		this.id = id;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataObjectPK other = (DataObjectPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		return true;
	}

}