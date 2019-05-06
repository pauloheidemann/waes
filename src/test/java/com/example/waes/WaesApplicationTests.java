package com.example.waes;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.hamcrest.CoreMatchers;
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

	//010101
	private static final String DATA = "MDEwMTAx";

	//010111
	private static final String DATA2 = "MDEwMTEx";

	@Autowired
	private DataObjectService service;

	@Test
	public void saveEntity() {
		try {
			DataObject dataObject = mockDataObject(1, DATA);
			service.save(dataObject);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void diffDiferentSize() {
		try {
			// inserting left
			DataObject dataObject = mockDataObject(1, DATA);
			service.save(dataObject);

			// inserting right
			DataObject dataObject2 = mockDataObject(2, "MDEwMTExMTE=");
			dataObject2.getDataObjectPK().setId(dataObject.getDataObjectPK().getId());
			service.save(dataObject2);

			String diff = service.diff(dataObject.getDataObjectPK().getId());
			Assert.assertEquals(DataObjectService.DIFFERENT_SIZE, diff);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void diffSameObject() {
		try {
			// inserting left
			DataObject dataObject = mockDataObject(1, DATA);
			service.save(dataObject);

			// inserting right
			DataObject dataObject2 = mockDataObject(2, DATA);
			dataObject2.getDataObjectPK().setId(dataObject.getDataObjectPK().getId());
			service.save(dataObject2);

			String diff = service.diff(dataObject.getDataObjectPK().getId());
			Assert.assertEquals(DataObjectService.EQUAL, diff);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void diff() {
		try {
			// inserting left
			DataObject dataObject = mockDataObject(1, DATA);
			service.save(dataObject);

			// inserting right
			DataObject dataObject2 = mockDataObject(2, DATA2);
			dataObject2.getDataObjectPK().setId(dataObject.getDataObjectPK().getId());
			service.save(dataObject2);

			String diff = service.diff(dataObject.getDataObjectPK().getId());
			Assert.assertThat(diff, CoreMatchers.containsString("the difference is "));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ValidationException.class)
	public void saveEntityWithSameIdAndOperation() throws Exception {
		DataObject dataObject = mockDataObject(1, DATA);
		service.save(dataObject);
		service.save(dataObject);
	}

	@Test(expected = EntityNotFoundException.class)
	public void entityNotFound() throws Exception {
		service.findAllById(9999999L);
	}

	private DataObject mockDataObject(int operation, String data) {
		DiffEndpointTest.ID++;
		DataObject dataObject = new DataObject();
		dataObject.getDataObjectPK().setId(DiffEndpointTest.ID);
		dataObject.getDataObjectPK().setOperation(operation);
		dataObject.setData(data);
		return dataObject;
	}

}