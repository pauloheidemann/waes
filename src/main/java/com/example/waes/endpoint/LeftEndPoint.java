package com.example.waes.endpoint;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waes.model.DataObject;
import com.example.waes.service.DataObjectService;

@RestController
@RequestMapping("v1/diff")
public class LeftEndPoint {
	
	@Autowired
	private DataObjectService service;
	
	@PostMapping(path = "{id}/left", produces = "application/json")
	Response dataLeft(@PathVariable Long id, @RequestBody String data) {
		try {
			DataObject dataObject = new DataObject();
			dataObject.getDataObjectPK().setId(id);
			dataObject.getDataObjectPK().setOperation(1);
			dataObject.setData(data);
			service.save(dataObject);
			return Response.ok("ok").build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
