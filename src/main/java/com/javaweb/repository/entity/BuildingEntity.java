package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Nhiệm vụ cốt lõi của BuildingEntity là đại diện trực tiếp cho cấu trúc dữ liệu của bảng building trong Database dưới dạng một đối tượng Java (Object).
//Nó hoạt động như một chiếc "khuôn đúc" để chứa dữ liệu thô móc từ MySQL lên, trước khi đưa đi xử lý logic.
// java bean
// BuildingEntity thường nên chứa toàn bộ các cột của bảng Building,
// vì nhiệm vụ của Entity là ánh xạ (mapping) giữa Java và bảng trong database.
@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id//khoa chinh
	@GeneratedValue(strategy = GenerationType.IDENTITY )// tu dong tang dan
	private Long id;
	@Column(name = "name")
    private String name;
//	@Column(name = "districtId")
//    private Long districtId;
	@Column(name = "ward")
    private String ward;
	@Column(name = "street")
    private String street;
	@Column(name = "numberOfBasement")
    private Integer numberOfBasement;
	@Column(name = "floorArea")
    private Double floorArea;
	@Column(name = "direction")
    private String direction;
	@Column(name = "level")
    private String level;
	@Column(name = "rentPrice")
    private Double rentPrice;
	@Column(name = "servicFee")
    private Double serviceFee;
	@Column(name = "managerName")
    private String managerName;
	@Column(name = "managerPhone")
    private String managerPhone;
	@Column(name = "brokerageFeePercent")
    private Double brokerageFeePercent;
    
//    // --- CÁC TRƯỜNG BỔ SUNG ĐỂ HỨNG DỮ LIỆU JOIN ---
//    private String nameDistrict; // Lấy từ bảng District
//    private String rentAreas;    // Xâu diện tích lấy từ bảng RentArea

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "districtId")//join khoa ngoai //sinh ra cot districtId
	private DistrictEntity district; //gionng cai mappedBy that ra mappedBy phải giống tên biến
    // Thêm các Constructor, Getter và Setter cho tất cả các trường...
	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentarea = new ArrayList<>();


	@OneToMany(mappedBy = "buildingType",fetch = FetchType.LAZY)
	private List<BuildingTypeEntity> buildingType;

	@ManyToMany(mappedBy = "buildings",fetch = FetchType.LAZY)
	private List<UserEntity> user = new ArrayList<>();

	public List<RentAreaEntity> getRentarea() {
		return rentarea;
	}

	public void setRentarea(List<RentAreaEntity> rentarea) {
		this.rentarea = rentarea;
	}
//
//	public String getNameDistrict() { return nameDistrict; }
//    public void setNameDistrict(String nameDistrict) { this.nameDistrict = nameDistrict; }
//
//    public String getRentAreas() { return rentAreas; }
//    public void setRentAreas(String rentAreas) { this.rentAreas = rentAreas; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Long getDistrictId() {
//		return districtId;
//	}
//	public void setDistrictId(Long districtId) {
//		this.districtId = districtId;
//	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Double getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Double getBrokerageFeePercent() {
		return brokerageFeePercent;
	}
	public void setBrokerageFeePercent(Double brokerageFeePercent) {
		this.brokerageFeePercent = brokerageFeePercent;
	}
    
    
	
}
