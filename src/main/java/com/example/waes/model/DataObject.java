package com.example.waes.model;

import java.util.Base64;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author Paulo Henrique Heidemann
 */
@Entity
public class DataObject {

	@EmbeddedId
	private DataObjectPK dataObjectPK;
	private byte[] data;

	public DataObjectPK getDataObjectPK() {
		if(dataObjectPK == null)
			dataObjectPK = new DataObjectPK();
		return dataObjectPK;
	}

	public void setDataObjectPK(DataObjectPK dataObjectPK) {
		this.dataObjectPK = dataObjectPK;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(String data) {
		this.data = Base64.getDecoder().decode(data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataObjectPK == null) ? 0 : dataObjectPK.hashCode());
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
		if (dataObjectPK == null) {
			if (other.dataObjectPK != null)
				return false;
		} else if (!dataObjectPK.equals(other.dataObjectPK))
			return false;
		return true;
	}

}