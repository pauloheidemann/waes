package com.example.waes.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.example.waes.model.DataObject;
import com.example.waes.repository.DataObjectRepository;
import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;

/**
 * @author Paulo Henrique Heidemann Class responsible for the business rules for
 *         the entity {@link DataObject}
 */
@Component
public class DataObjectService {

	@Autowired
	private DataObjectRepository repository;

	/**
	 * 
	 * @param dataObject
	 * @throws ValidationException
	 * @throws Exception
	 */
	public void save(DataObject dataObject) throws ValidationException, Exception {
		/**
		 * I'm assuming you can only send the object with the same ID if the operation
		 * is different, so I'll always have two objects for comparison
		 */
		Optional<DataObject> dataObjectFoundById = repository.findById(dataObject.getDataObjectPK());
		if (dataObjectFoundById.isPresent() && dataObjectFoundById.get().equals(dataObject))
			throw new ValidationException("You can't save the same object twice");
		repository.save(dataObject);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	public List<DataObject> findAllById(Long id) throws EntityNotFoundException {
		DataObject dataObject = new DataObject();
		dataObject.getDataObjectPK().setId(id);
		Example<DataObject> example = Example.of(dataObject);
		List<DataObject> dataObjects = repository.findAll(example);
		if (dataObjects.isEmpty())
			throw new EntityNotFoundException();
		return dataObjects;
	}

	/**
	 * Verify if there is a diff between the data of the DataObjects sent
	 * 
	 * @param dataObjects
	 * @throws DiffException
	 */
	public void diff(Long id) throws DiffException {
		List<DataObject> dataObjects = this.findAllById(id);
		Patch<String> diff = null;
		for (int i = 0; i < dataObjects.size(); i++) {
			diff = DiffUtils.diff(Arrays.asList(dataObjects.get(i).getData()),
					Arrays.asList(dataObjects.get(++i).getData()));
		}
	}

}