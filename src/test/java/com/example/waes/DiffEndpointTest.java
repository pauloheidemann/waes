package com.example.waes;

import com.example.waes.service.DataObjectService;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaesApplication.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class DiffEndpointTest {

	@Autowired
	private MockMvc mvc;

	//010101
	private static final String DATA = "MDEwMTAx";

	//010111
	private static final String DATA2 = "MDEwMTEx";
	
	public static long ID = 1;
	
	@Test
	public void saveSameData() {
		try {
			ID++;
			saveRight(DATA);
			saveLeft(DATA);
			mvc.perform(
					MockMvcRequestBuilders.get("/v1/diff/" + ID)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(DataObjectService.EQUAL));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void saveDifferentSize() {
		try {
			ID++;
			saveRight(DATA);
			saveLeft("MDEwMTExMTE=");
			mvc.perform(
					MockMvcRequestBuilders.get("/v1/diff/" + ID)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(DataObjectService.DIFFERENT_SIZE));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void saveDifferentData() {
		try {
			ID++;
			saveRight(DATA);
			saveLeft(DATA2);
			mvc.perform(
					MockMvcRequestBuilders.get("/v1/diff/" + ID)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("the difference is ")));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void checkHealth() {
		try {
			mvc.perform(
					MockMvcRequestBuilders.get("/v1/diff"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	private void saveLeft(String data) throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/v1/diff/" + ID + "/left")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private void saveRight(String data) throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/v1/diff/" + ID + "/right")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}