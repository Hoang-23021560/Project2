package com.javaweb.Converter;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchConverter(Map<String, Object> params, List<String> Code){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params,"floorArea",Double.class))
                .setDistrictId(MapUtil.getObject(params,"districtId",Long.class))
                .setWard(MapUtil.getObject(params,"ward",String.class))
                .setStreet(MapUtil.getObject(params,"street",String.class))
                .setNumberOfBasement(MapUtil.getObject(params,"numberOfBasement",Integer.class))
                .setDirection(MapUtil.getObject(params,"direction",String.class))
                .setLevel(MapUtil.getObject(params,"level",String.class))
                .setRentAreaFrom(MapUtil.getObject(params,"rentAreaFrom",Double.class))
                .setRentAreaTo(MapUtil.getObject(params,"rentAreaTo",Double.class))
                .setRentPriceFrom(MapUtil.getObject(params,"rentPriceFrom",Double.class))
                .setRentPriceTo(MapUtil.getObject(params,"rentPriceTo",Double.class))
                .setManagerName(MapUtil.getObject(params,"managerName",String.class))
                .setManagerPhone(MapUtil.getObject(params,"managerPhone",String.class))
                .setStaffId(MapUtil.getObject(params,"staffId",Long.class))
                .setCode(Code).build();
        return buildingSearchBuilder;
    }

}
