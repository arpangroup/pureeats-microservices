package com.arpangroup.inventory.mapper;

import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.dto.response.product.ProductResponseDto;
import com.arpangroup.inventory.dto.response.product.SimpleProductResponseDto;
import com.arpangroup.inventory.entity.ProductEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        config = IgnoreUnmappedMapperConfig.class
)
public abstract class ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    public abstract BaseProductResponse mapToResponse(ProductEntity productEntity);
    public abstract SimpleProductResponseDto toDto(ProductEntity productEntity);

    public abstract List<SimpleProductResponseDto> toDto(List<ProductEntity> productEntities);

    @Mapping(target = "categoryId", source = "productEntity.categoryEntity.categoryId")
    public abstract ProductResponseDto toDtoDetails(ProductEntity productEntity);

    //@Mapping(target = "categoryName", source = "request.category_name")
    public abstract ProductEntity mapToEntity(ProductRequestDto request);

}
