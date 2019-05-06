package com.example.waes.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waes.model.DataObject;
import com.example.waes.service.DataObjectService;

@RestController
@RequestMapping("/v1/diff")
@SuppressWarnings("rawtypes")
public class LeftEndPoint {
	
	@Autowired
	private DataObjectService service;
	
	@PostMapping(value = "/{id}/left", produces = "application/json", consumes = "application/json")
	ResponseEntity dataLeft(@PathVariable Long id, @RequestBody String data) {
		try {
			DataObject dataObject = new DataObject();
			dataObject.getDataObjectPK().setId(id);
			dataObject.getDataObjectPK().setOperation(1);
			dataObject.setData(data);
			service.save(dataObject);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/left")
	ResponseEntity healthCheck() {
		return ResponseEntity.ok().build();
	}

}
