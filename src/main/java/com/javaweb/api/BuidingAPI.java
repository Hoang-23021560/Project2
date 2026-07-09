package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customexception.FieldRequierdException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.BuildingSearchResponse;
import com.javaweb.model.ErrorResponseDTO;
import com.javaweb.service.BuildingService;

@RestController
@PropertySource("classpath:application.properties")
public class BuidingAPI {
	@Autowired
	private BuildingService buildingService;
	@Value("${dev.nguyen}")
	private String data;

	@GetMapping("/api/building/")
	public List<BuildingSearchResponse> getBuilding(@RequestParam Map<String, Object> params,
													@RequestParam(value = "Code", required = false) List<String> Code) {
		List<BuildingSearchResponse> result = buildingService.findAll(params, Code);
		return result;

	}


//	static final String DB_URL = "jdbc:mysql://localhost:3306/databasebuilding1";
//	static final String USER = "root";
//	static final String PASS = "123456";
//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	@ResponseBody
//	public List<BuildingDTO> getBuilding() {
//		String sql = "SELECT * FROM databasebuilding1.Building";
//		List<BuildingDTO> result = new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER,PASS);
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				){
//			while(rs.next()) {
//				BuildingDTO building = new BuildingDTO();
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setNumberOfBasement(rs.getInt("numberOfBasement"));
//				result.add(building);
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//			System.out.println("Connection database failed ..");
//		}
//		return result;
//		
//	public Object getBuilding(@RequestParam(value = "name") String name,
//			@RequestParam(value = "numberOfBasement") Integer numberOfBasement,
//			@RequestParam(value = "ward") String ward) {
//		// xu ly duoi DB xong roi
//		try {
//			System.out.println(5/0);
//		}
//		catch(Exception e) {
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("So nguyen lam sao chia het cho 0 duoc");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//			
//		}
//		
//		List<BuildingDTO> listBuildings = new ArrayList<>();
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("ABC Building");
//		buildingDTO1.setNumberOfBasement(3);
//		buildingDTO1.setWard("Tan Mai");
//		BuildingDTO buildingDTO2 = new BuildingDTO();
//		buildingDTO2.setName("ACM Building");
//		buildingDTO2.setNumberOfBasement(4);
//		buildingDTO2.setWard("Da Cao");
//		listBuildings.add(buildingDTO1);
//		listBuildings.add(buildingDTO2);
//		return listBuildings;
////		
//		System.out.println(5/0);
//		System.out.println("hello");
//		return null;

	// hàm valiDate để ném ra loại lỗi tên là gì
//	public void valiDate(BuildingDTO buildingDTO) {
//		if(buildingDTO.getName() == null || buildingDTO.getName().equals("") || buildingDTO.getNumberOfBasement() == null) {
//			throw new FieldRequierdException("name or numberOfbasement is null");
//		}
//	}
//
	@DeleteMapping("/api/building/{id}")
	public void deleteBuilding(@PathVariable Integer id) {
		System.out.println(data);
	}

}


