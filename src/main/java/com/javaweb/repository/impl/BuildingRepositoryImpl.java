package com.javaweb.repository.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        //JPQL: JPA Query Language
        String sql = "From BuildingEntity ";
        Query query = entityManager.createQuery(sql,BuildingEntity.class);
//        String sql = "From BuildingEntity ";
//        Query query = entityManager.createNativeQuery(sql,BuildingEntity.class);
        return query.getResultList();
    }
}
