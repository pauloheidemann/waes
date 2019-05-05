package com.example.waes.endpoint;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waes.service.DataObjectService;

@RestController
@RequestMapping("/v1/diff")
public class DiffEndPoint {
	
	@Autowired
	private DataObjectService service;
	
	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	Response diff(@PathVariable Long id) {
		try {
			String diff = service.diff(id);
			return Response.ok(diff).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping()
	Response healthCheck() {
		return Response.ok().build();
	}

}
