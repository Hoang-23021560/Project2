package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.Builder.BuildingSearchBuilder;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;

@Repository
public class JDBCBuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/databasebuilding";
	static final String USER = "root";
	static final String PASS = "123456";
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();// staffId ở đây là id là 2 và trong csdl là staffId=userId = 2 là nhân viên
		if(staffId != null) {
			//asignmentBuildign:Phân công nhân viên quản lý tòa nhà
			sql.append(" Inner JOIN AssignmentBuilding ab ON b.id = ab.buildingId ");
		}

			sql.append(" INNER Join buildingType bt on b.id = bt.buildingId ");

		// trong map có biến areato = 1 giá trị nào đó
		// các staffId, areato, areafrom khi viết vào postman phải viết đúng là các staffId, areato, areafrom
//		Double rentAreaTo = buildingSearchBuilder.getRentAreaTo(); // lấy ra giá trị đó gán vào biến rentAreaTo
//		Double rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
//		if(rentAreaTo != null || rentAreaFrom != null) {
//			sql.append(" Inner join rentarea ra ON b.id = ra.buildingId ");
//		}
		sql.append(" Inner join rentarea ra ON b.id = ra.buildingId ");
	}
	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder,StringBuilder where){
//		for(Map.Entry<String,Object> item : params.entrySet()) {
//			if(!item.getKey().equals("staffId")&& !item.getKey().equals("Code")&&
//				!item.getKey().startsWith("area") && !item.getKey().startsWith("rentPrice")) {
//				String value = item.getValue().toString();
//				if(StringUtil.checkString(value)) {
//					if(NumberUtil.isNumber(value)) {
//						where.append(" AND b." + item.getKey() + " = " + value);
//					}
//					else {
//						where.append(" AND b." + item.getKey() + " Like '%" + value + "%' ");
//					}
//				}
//
//			}
//		}
		try{
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				// Loại trừ các trường đặc biệt không xử lý bằng logic thông thường này
				if (!fieldName.equals("staffId") && !fieldName.equals("Code")
						&& !fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice")) {

					// Lấy giá trị của thuộc tính từ đối tượng truyền vào
					Object itemValue = item.get(buildingSearchBuilder);





						if (itemValue != null) {
							String value = itemValue.toString();

							// 3. Kiểm tra chuỗi giá trị (bảo đảm không phải chuỗi rỗng "" hoặc khoảng trắng "   ")
							if (StringUtil.checkString(value)) {

								Class<?> type = item.getType(); // Lấy ra kiểu dữ liệu của Field

								// 4. Nếu là kiểu số (Long, Double, Integer) thì dùng toán tử bằng (=)
								if (type.equals(Long.class) || type.equals(Double.class) || type.equals(Integer.class)) {
									where.append(" AND b." + fieldName + " = " + value);
								}
								// 5. Nếu là kiểu chuỗi (hoặc các kiểu khác) thì dùng toán tử tìm kiếm gần đúng (LIKE)
								else {
									where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
								}
							}
						}

					}

				}

		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();// staffId ở đây là id là 2 và trong csdl là staffId=userId = 2 là nhân viên
		if(staffId != null) {
			where.append(" AND ab.userId =" + staffId);
		}
		Double rentAreaTo = buildingSearchBuilder.getRentAreaTo(); // lấy ra giá trị đó gán vào biến rentAreaTo
		Double rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		if(rentAreaTo != null || rentAreaFrom != null) {
			if(rentAreaFrom != null) {
			where.append(" AND ra.value >= " + rentAreaFrom);
			}
			if(rentAreaTo != null) {
			where.append(" AND ra.value <= " + rentAreaTo);
			}
		}
		Double rentPriceTo = buildingSearchBuilder.getRentPriceTo(); // lấy ra giá trị đó gán vào biến rentAreaTo
		Double rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if(rentPriceTo != null || rentPriceFrom != null) {
			if(rentPriceFrom != null) {
			where.append(" AND b.rentPrice >= " + rentPriceFrom);
			}
			if(rentPriceTo != null) {
			where.append(" AND b.rentPrice <= " + rentPriceTo);
			}
		}
		if (buildingSearchBuilder.getCode() != null && !buildingSearchBuilder.getCode().isEmpty()) {
		    // Biến đổi các phần tử từ dạng: VAN_PHONG -> 'VAN_PHONG'
		    String codeJoined = buildingSearchBuilder.getCode().stream()
		            .map(c -> "'" + c + "'")
		            .collect(java.util.stream.Collectors.joining(","));

		    where.append(" AND bt.Code IN (" + codeJoined + ") ");
		}

		
	}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingEntity> result = new ArrayList<>();
		StringBuilder sql = new StringBuilder("Select b.*, d.nameDistrict, GROUP_CONCAT(ra.value SEPARATOR ', ') AS rentAreas from building b INNER JOIN District d ON b.districtId = d.id ");
		joinTable(buildingSearchBuilder,sql);
		StringBuilder where = new StringBuilder("where 1 = 1");
		queryNormal(buildingSearchBuilder,where);
		querySpecial(buildingSearchBuilder,where);
		sql.append(where);
		sql.append(" group by b.id, d.nameDistrict limit 0,1000;");
		System.out.println(sql);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(sql.toString())) {

		        // Vòng lặp duyệt qua từng dòng kết quả trả về từ Database
		        while (rs.next()) {
		            BuildingEntity building = new BuildingEntity();
		            
		            // Ép dữ liệu từ ResultSet vào Object Entity (Đã sửa lỗi bỏ "b." và chuẩn hóa tên biến)
		            building.setId(rs.getLong("id"));
		            building.setName(rs.getString("name"));
		            building.setWard(rs.getString("ward"));
//		            building.setDistrictId(rs.getLong("districtId"));
		            building.setNumberOfBasement(rs.getInt("numberOfBasement"));
		            building.setStreet(rs.getString("street"));
		            building.setFloorArea(rs.getDouble("floorArea"));   
		            building.setRentPrice(rs.getDouble("rentPrice"));   
		            building.setServiceFee(rs.getDouble("serviceFee"));
		            building.setManagerName(rs.getString("managerName"));
		            building.setManagerPhone(rs.getString("managerPhone")); // Tên cột chuẩn trong DB
		            building.getDistrict().setNameDistrict(rs.getString("nameDistrict"));
//		            building.getRentarea().set(rs.getString("rentAreas")); // Nhận xâu diện tích thuê [cite: 1]
		            
		            // Thêm đối tượng vào danh sách
		            result.add(building);
		        }

		    } catch (SQLException e) {
		        // Khối catch này sẽ bắt toàn bộ lỗi liên quan đến SQL (Sai tên cột, sai cú pháp, mất kết nối...)
		        System.err.println("Lỗi xảy ra khi truy vấn dữ liệu tòa nhà từ Database!");
		        e.printStackTrace(); // In chi tiết log lỗi ra Console để Hoàng dễ debug
		    }
		return result;
		
//		List<BuildingEntity> result = new ArrayList<>();
//		StringBuilder sql = new StringBuilder(
//		        "SELECT b.*, d.nameDistrict, GROUP_CONCAT(ra.value SEPARATOR ', ') AS rentAreas " +
//		        "FROM Building b " +
//		        "INNER JOIN District d ON b.districtId = d.id " +
//		        "LEFT JOIN RentArea ra ON b.id = ra.buildingId " +
//		        "LEFT JOIN AssignmentBuilding ab ON b.id = ab.buildingId " +
//		        "WHERE 1=1 "
//		    );
//
//		// Giả sử StringBuilder sql = new StringBuilder("SELECT ... WHERE 1=1 ");
//
//		// 1. Tìm theo Tên tòa nhà (String - LIKE)
//		if (request.getName() != null && !request.getName().trim().isEmpty()) {
//		    sql.append(" AND b.name LIKE '%").append(request.getName().trim()).append("%'");
//		}
//
//		// 2. Tìm theo Diện tích sàn (Double/Integer - Bằng nhau)
//		if (request.getFloorArea() != null) {
//		    sql.append(" AND b.floorArea = ").append(request.getFloorArea());
//		}
//
//		// 3. Tìm theo ID Quận (Long - Bằng nhau) 
//		if (request.getDistrictId() != null) {
//		    sql.append(" AND b.districtId = ").append(request.getDistrictId());
//		}
//
//		// 4. Tìm theo Phường (String - LIKE hoặc Bằng nhau)
//		if (request.getWard() != null && !request.getWard().trim().isEmpty()) {
//		    sql.append(" AND b.ward LIKE '%").append(request.getWard().trim()).append("%'");
//		}
//
//		// 5. Tìm theo Tên đường (String - LIKE)
//		if (request.getStreet() != null && !request.getStreet().trim().isEmpty()) {
//		    sql.append(" AND b.street LIKE '%").append(request.getStreet().trim()).append("%'");
//		}
//
//		// 6. Tìm theo Số tầng hầm (Integer - Bằng nhau)
//		if (request.getNumberOfBasement() != null) {
//		    sql.append(" AND b.numberOfBasement = ").append(request.getNumberOfBasement());
//		}
//
//		// 7. Tìm theo Hướng tòa nhà (String - Bằng nhau)
//		if (request.getDirection() != null && !request.getDirection().trim().isEmpty()) {
//		    sql.append(" AND b.direction = '").append(request.getDirection().trim()).append("'");
//		}
//
//		// 8. Tìm theo Hạng tòa nhà (String - Bằng nhau)
//		if (request.getLevel() != null && !request.getLevel().trim().isEmpty()) {
//		    sql.append(" AND b.level = '").append(request.getLevel().trim()).append("'");
//		}
//
//		// 9. Tìm theo Diện tích thuê TỪ (Rent Area From - Lớn hơn hoặc bằng)
//		if (request.getRentAreaFrom() != null) {
//		    sql.append(" AND EXISTS (SELECT 1 FROM RentArea ra WHERE ra.buildingId = b.id AND ra.value >= ")
//		       .append(request.getRentAreaFrom()).append(")");
//		}
//
//		// 10. Tìm theo Diện tích thuê ĐẾN (Rent Area To - Nhỏ hơn hoặc bằng)
//		if (request.getRentAreaTo() != null) {
//		    sql.append(" AND EXISTS (SELECT 1 FROM RentArea ra WHERE ra.buildingId = b.id AND ra.value <= ")
//		       .append(request.getRentAreaTo()).append(")");
//		}
//
//		// 11. Tìm theo Giá thuê TỪ (Rent Price From - Lớn hơn hoặc bằng)
//		if (request.getRentPriceFrom() != null) {
//		    sql.append(" AND b.rentPrice >= ").append(request.getRentPriceFrom());
//		}
//
//		// 12. Tìm theo Giá thuê ĐẾN (Rent Price To - Nhỏ hơn hoặc bằng)
//		if (request.getRentPriceTo() != null) {
//		    sql.append(" AND b.rentPrice <= ").append(request.getRentPriceTo());
//		}
//
//		// 13. Tìm theo Tên quản lý (String - LIKE) 
//		if (request.getManagerName() != null && !request.getManagerName().trim().isEmpty()) {
//		    sql.append(" AND b.managerName LIKE '%").append(request.getManagerName().trim()).append("%'");
//		}
//
//		// 14. Tìm theo SĐT quản lý (String - Bằng nhau hoặc LIKE) 
//		if (request.getManagerPhone() != null && !request.getManagerPhone().trim().isEmpty()) {
//		    sql.append(" AND b.managerPhone LIKE '%").append(request.getManagerPhone().trim()).append("%'");
//		}
//
//		// 15. Tìm theo ID Nhân viên phụ trách (Long - Bằng nhau qua bảng trung gian) 
//		if (request.getStaffId() != null) {
//		    sql.append(" AND ab.userId = ").append(request.getStaffId());
//		}
//
//		// 16. Tìm theo Loại tòa nhà (List<String> - Sử dụng toán tử IN)
//		if (request.getTypeCode() != null && !request.getTypeCode().isEmpty()) {
//		    sql.append(" AND b.id IN (SELECT bt.buildingId FROM buildingType bt WHERE bt.code IN (");
//		    for (int i = 0; i < request.getTypeCode().size(); i++) {
//		        sql.append("'").append(request.getTypeCode().get(i)).append("'");
//		        if (i < request.getTypeCode().size() - 1) {
//		            sql.append(", ");
//		        }
//		    }
//		    sql.append("))");
//		}
//
//		    sql.append(" GROUP BY b.id, d.nameDistrict");
//
//		    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//		         PreparedStatement stmt = conn.prepareStatement(sql.toString());
//		         ResultSet rs = stmt.executeQuery()) {
//
//		        while (rs.next()) {
//		            BuildingEntity entity = new BuildingEntity();
//		            
//		            // Map các trường chuẩn của Building
//		            entity.setId(rs.getLong("id"));
//		            entity.setName(rs.getString("name"));
//		            entity.setStreet(rs.getString("street"));
//		            entity.setWard(rs.getString("ward"));
//		            entity.setNumberOfBasement(rs.getInt("numberOfBasement"));
//		            entity.setFloorArea(rs.getDouble("floorArea"));
//		            entity.setRentPrice(rs.getDouble("rentPrice"));
//		            entity.setServiceFee(rs.getDouble("serviceFee"));
//		            entity.setManagerName(rs.getString("managerName"));
//		            entity.setManagerPhone(rs.getString("managerPhone"));
//		            entity.setBrokerageFeePercent(rs.getDouble("brokerageFeePercent"));
//		            
//		            // Map các trường mở rộng từ câu lệnh JOIN
//		            entity.setNameDistrict(rs.getString("nameDistrict"));
//		            entity.setRentAreas(rs.getString("rentAreas")); // Nhận xâu diện tích thuê [cite: 1]
//
//		            result.add(entity);
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//		    return result;
//	
//}
}
}
