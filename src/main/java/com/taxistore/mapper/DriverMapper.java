package com.taxistore.mapper;

import com.taxistore.pojo.Driver;
import com.taxistore.dto.DriverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * DriverMapper 使用 MapStruct 進行 DriverDTO 和 Driver 的映射轉換。
 */
@Mapper
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    /**
     * 將 DriverDTO 映射為 Driver（DTO ➜ Entity）
     *
     * @param dto DriverDTO 對象
     * @return 對應的 Driver 實體
     */
    @Mappings({
        @Mapping(source = "postcodeId", target = "postcode"),
        // 字串轉時間的 str_createdate、str_birthday 等不映射
        // postcodeCode、regionName、districtName、carno、ownerName 等不屬於原始 Driver 實體，也不映射
        @Mapping(target = "lastPostFineDate", source = "lastPostFineDate") // 保險起見寫一下
    })
    Driver toDriver(DriverDTO dto);
}
