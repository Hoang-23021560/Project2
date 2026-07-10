package com.javaweb.repository.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static com.javaweb.utils.NumberUtil.isNumber;
import static com.javaweb.utils.StringUtil.checkString;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void joinTable(BuildingSearchBuilder builder,StringBuilder jpql){
        if(builder.getDistrictId() != null){
        jpql.append(" Join b.district d ");
        }
        if(builder.getRentAreaFrom() != null || builder.getRentAreaTo() != null){
            jpql.append(" Join b.rentarea ra ");
        }
        if(builder.getCode() != null && builder.getCode().size() != 0){
            jpql.append(" Join b.buildingType bt ");
        }
    }
    public void queryNormal(BuildingSearchBuilder builder,StringBuilder jpql){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                if (!key.equals("staffId") && !key.equals("code") && !key.startsWith("rentArea") && !key.startsWith("rentPrice")) {
                    Object value = field.get(builder);

                    if(value!= null&&checkString(value.toString())){
                        if(isNumber(value.toString())){
                            jpql.append(" AND b.").append(key).append(" = ").append(value).append(" ");
                        }
                        else {
                            jpql.append(" AND b.").append(key).append(" LIKE '%").append(value).append("%' ");

                        }
                    }

                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void querySpecial(BuildingSearchBuilder builder,StringBuilder jpql){
        if(builder.getStaffId() != null){
            jpql.append(" AND b.staffId = " + builder.getStaffId());
        }
        if(builder.getRentAreaFrom() != null){
            jpql.append(" AND ra.value >= " + builder.getRentAreaFrom());
        }
        if(builder.getRentAreaTo() != null){
            jpql.append(" AND ra.value <= " + builder.getRentAreaTo());
        }
        if(builder.getRentPriceFrom() != null){
            jpql.append(" AND ra.rentPrice >=" + builder.getRentPriceFrom());
        }
        if(builder.getRentAreaTo() != null){
            jpql.append(" AND ra.rentPrice <= " + builder.getRentAreaTo());
        }
        if(builder.getCode() != null && builder.getCode().size() != 0){
            String codeJoin = builder.getCode().stream()
                    .map(c -> "'" + c + "'").collect(Collectors.joining(","));
            jpql.append(" AND bt.Code IN (" + codeJoin + ")");
        }

    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
        //JPQL: JPA Query Language
        StringBuilder sql = new StringBuilder("SELECT b FROM BuildingEntity b");
        joinTable(builder,sql);
        String where = " WHERE 1 = 1 ";
        sql.append(where);
        queryNormal(builder,sql);
        querySpecial(builder,sql);
        sql.append(" group by b.id ");
        Query query = entityManager.createQuery(sql.toString(),BuildingEntity.class);
//        String sql = "From BuildingEntity ";
//        Query query = entityManager.createNativeQuery(sql,BuildingEntity.class);
        return query.getResultList();
    }
}
