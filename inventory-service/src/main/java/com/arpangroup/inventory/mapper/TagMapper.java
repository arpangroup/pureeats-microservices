package com.arpangroup.inventory.mapper;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.request.TagRequest;
import com.arpangroup.inventory.dto.response.category.CategoryResponseDto;
import com.arpangroup.inventory.dto.response.tag.SimpleTagResponse;
import com.arpangroup.inventory.dto.response.tag.TagResponse;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        config = IgnoreUnmappedMapperConfig.class,
//        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ProductMapper.class}
)
public abstract class TagMapper {
//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public abstract SimpleTagResponse toDtoMinimal(TagEntity tagEntity);

    @Mapping(target = "products", source = "tagEntity.productEntities")
    public abstract TagResponse toDto(TagEntity tagEntity);


    public abstract TagEntity toEntity(TagRequest request);
}
