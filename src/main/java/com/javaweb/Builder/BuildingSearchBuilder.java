package com.javaweb.Builder;

import java.util.List;
// su dung design pattern Builder
//Builder Pattern là một trong những Creational Design Patterns (nhóm mẫu thiết kế khởi tạo).
// Bản chất của nó sinh ra để giải quyết một bài toán duy nhất: Hỗ trợ xây dựng một đối tượng phức tạp
// bằng cách chia nhỏ quá trình lắp ráp từng bước một, giúp code tường minh, dễ đọc và an toàn hơn.
public class BuildingSearchBuilder {
    private String name;
    private Double floorArea;
    private Long districtId;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Double rentAreaFrom;
    private Double rentAreaTo;
    private Double rentPriceFrom;
    private Double rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> Code;

    public BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.districtId = builder.districtId;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.staffId = builder.staffId;
        this.Code = builder.Code;
    }

    public String getName() {
        return name;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Double getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Double getRentAreaTo() {
        return rentAreaTo;
    }

    public Double getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Double getRentPriceTo() {
        return rentPriceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getCode() {
        return Code;
    }
    public static class Builder{
        private String name;
        private Double floorArea;
        private Long districtId;
        private String ward;
        private String street;
        private Integer numberOfBasement;
        private String direction;
        private String level;
        private Double rentAreaFrom;
        private Double rentAreaTo;
        private Double rentPriceFrom;
        private Double rentPriceTo;
        private String managerName;
        private String managerPhone;
        private Long staffId;
        private List<String> Code;

        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setFloorArea(Double floorArea){
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrictId(Long districtId) {
            this.districtId = districtId;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setRentAreaFrom(Double rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Double rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setRentPriceFrom(Double rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Double rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setCode(List<String> Code) {
            this.Code = Code;
            return this;
        }
        public  BuildingSearchBuilder build(){
            return new BuildingSearchBuilder(this);
        }
    }
}
