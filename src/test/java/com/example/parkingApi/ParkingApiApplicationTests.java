package com.example.parkingApi;

import com.example.parkingApi.controller.MyRestController;
import com.example.parkingApi.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ParkingApiApplicationTests {

	private MockMvc mockMvc;
	@Mock
	private ParkingRepository parkingRepository1;
	private ParkingMeter parkingMeter;
	private ParkingDao parkingDao;
	private MyRestController myRestController;

	@Before
	public void setUp() {
		parkingDao = new ParkingDao(parkingRepository1);
		parkingMeter = new ParkingMeter(parkingDao);
		myRestController = new MyRestController(parkingMeter);
		mockMvc = MockMvcBuilders.standaloneSetup(myRestController).build();
	}

	@Test
	public void startParkingForTheFirstTimeAndReturnedTrue() {
		try {
			when(parkingRepository1.findById(anyString())).thenReturn(new ArrayList<Parking>());
			mockMvc.perform(post("/start/post").param("id", "id").param("driverType", "VIP"))
					.andExpect(status().isOk())
					.andExpect(content().string("true"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void startParkingWithTheSameIdAndReturnedFalse() {
		try {
			Driver driver = new Driver();
			driver.setId("id");
			driver.setDriverType("VIP");
			ArrayList<Parking> returned = new ArrayList<>();
			returned.add(new Parking(LocalDateTime.now(), DriverType.VIP, "id"));
			when(parkingRepository1.findById(anyString())).thenReturn(returned);
			mockMvc.perform(post("/start/post").param("id", "id").param("driverType", "VIP"))
					.andExpect(status().isOk())
					.andExpect(content().string("false"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void stopNotRunningParkingAndReturnedFalse() {
		try {
			when(parkingRepository1.findById(anyString())).thenReturn(new ArrayList<Parking>());
			mockMvc.perform(post("/stop/post").param("id", "id").param("driverType", "VIP"))
					.andExpect(status().isOk())
					.andExpect(content().string("false"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void stopRunningParkingAndReturnedTrue() {
		try {
			Driver driver = new Driver();
			driver.setId("id");
			driver.setDriverType("VIP");
			ArrayList<Parking> returned = new ArrayList<>();
			returned.add(new Parking(LocalDateTime.now(), DriverType.VIP, "id"));
			when(parkingRepository1.findById(anyString())).thenReturn(returned);
			mockMvc.perform(post("/stop/post").param("id", "id").param("driverType", "VIP"))
					.andExpect(status().isOk())
					.andExpect(content().string("true"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getCostVipAndReturnedCorrectCost() {
		try {
			ArrayList<Parking> returned = new ArrayList<>();
			Parking parking = new Parking(LocalDateTime.now().minusHours(2).minusSeconds(1), DriverType.VIP, "id");
			returned.add(parking);
			when(parkingRepository1.findById(anyString())).thenReturn(returned);
			mockMvc.perform(get("/cost/id"))
					.andExpect(status().isOk())
					.andExpect(content().json("{\"value\": 4.4, \"currency\": \"PLN\"}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getCostRegularAndReturnedCorrectCost() {
		try {
			ArrayList<Parking> returned = new ArrayList<>();
			Parking parking = new Parking(LocalDateTime.now().minusHours(2).minusSeconds(1), DriverType.REGULAR, "id");
			returned.add(parking);
			when(parkingRepository1.findById(anyString())).thenReturn(returned);
			mockMvc.perform(get("/cost/id"))
					.andExpect(status().isOk())
					.andExpect(content().json("{\"value\": 6.0, \"currency\": \"PLN\"}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ParkingRepository parkingRepository;

	@Test
	public void testFindById() {
		entityManager.persist(new Parking(LocalDateTime.now(), DriverType.REGULAR, "testID"));
		List<Parking> parking = parkingRepository.findById("testID");
		assertEquals("testID", parking.get(0).getId());
	}
}
