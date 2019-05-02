package com.example.waes;

import com.example.model.DataObject;
import com.example.model.DataObjectRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaesApplicationTests {

	@Autowired
	private DataObjectRepository repository;

	@Test
	public void saveEntity() {
		DataObject dataObject = new DataObject();
		dataObject.setData("data");
		dataObject.setOperation(1);
		repository.save(dataObject);
	}

}
