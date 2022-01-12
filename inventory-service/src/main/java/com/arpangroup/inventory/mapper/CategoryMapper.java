package com.arpangroup.inventory.mapper;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.response.category.CategoryResponseDto;
import com.arpangroup.inventory.dto.response.product.SimpleProductResponseDto;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.ProductEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        config = IgnoreUnmappedMapperConfig.class,
//        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ProductMapper.class}
)
public abstract class CategoryMapper{
//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "products", source = "categoryEntity.productEntities")
    public abstract CategoryResponseDto toDto(CategoryEntity categoryEntity);


    //@Mapping(target = "categoryName", source = "request.category_name")
    public abstract CategoryEntity toEntity(CategoryRequestDto request);


}
