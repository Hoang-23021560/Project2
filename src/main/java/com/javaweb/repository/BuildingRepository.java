package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface BuildingRepository {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
	void insert(BuildingEntity buildingEntity);
	void update(BuildingEntity buildingEntity);
	void delete(long id);
	BuildingEntity findById(Long id);
	List<UserEntity> findByIdStaff(List<Long> ids);
	DistrictEntity findByDistrictId(Long id);
//	List<RentAreaEntity> findValue(List<Integer> values);
//	List<BuildingTypeEntity> findCode(L)


}
