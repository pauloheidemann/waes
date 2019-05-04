package com.example.waes;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.waes.model.DataObject;
import com.example.waes.service.DataObjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaesApplicationTests {

	private static long ID = 1;

	@Autowired
	private DataObjectService service;

	@Test
	public void saveEntity() {
		try {
			DataObject dataObject = mockDataObject(1, "data");
			service.save(dataObject);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void diff() {
		try {
			// inserting left
			DataObject dataObject = mockDataObject(1, "data");
			service.save(dataObject);

			// inserting right
			DataObject dataObject2 = mockDataObject(2, "data2");
			dataObject2.getDataObjectPK().setId(dataObject.getDataObjectPK().getId());
			service.save(dataObject2);

			service.diff(dataObject.getDataObjectPK().getId());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ValidationException.class)
	public void saveEntityWithSameIdAndOperation() throws Exception {
		DataObject dataObject = mockDataObject(1, "data");
		service.save(dataObject);
		service.save(dataObject);
	}

	@Test(expected = EntityNotFoundException.class)
	public void entityNotFound() throws Exception {
		service.findAllById(9999999L);
	}

	private DataObject mockDataObject(int operation, String data) {
		DataObject dataObject = new DataObject();
		dataObject.getDataObjectPK().setId(ID++);
		dataObject.getDataObjectPK().setOperation(operation);
		dataObject.setData(data);
		return dataObject;
	}

}