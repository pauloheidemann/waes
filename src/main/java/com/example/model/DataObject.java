package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Paulo Henrique Heidemann
 */
@Entity
public class DataObject {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int operation;
    private String data;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the operation
     * 1 - LEFT
     * 2 - RIGHT
	 */
	public int getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
     * 1 - LEFT
     * 2 - RIGHT
	 */
	public void setOperation(int operation) {
		this.operation = operation;
	}

    @Override
    public String toString() {
        return "DataObject [data=" + data + ", id=" + id + ", operation=" + operation + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + operation;
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
        DataObject other = (DataObject) obj;
       if (id != other.id)
            return false;
        return true;
    }

}