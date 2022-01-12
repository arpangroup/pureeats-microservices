package com.arpangroup.inventory.mapper;

import com.arpangroup.inventory.common.DeliveryChargeType;
import com.arpangroup.inventory.common.DeliveryType;
import com.arpangroup.inventory.common.StoreChargeType;
import com.arpangroup.inventory.common.StoreType;
import com.arpangroup.inventory.dto.request.DeliveryCharge;
import com.arpangroup.inventory.dto.request.StoreCharge;
import com.arpangroup.inventory.dto.request.StoreRequest;
import com.arpangroup.inventory.dto.response.store.StoreResponse;
import com.arpangroup.inventory.entity.DeliveryChargeEntity;
import com.arpangroup.inventory.entity.StoreChargeEntity;
import com.arpangroup.inventory.entity.StoreEntity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.net.InetAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        config = IgnoreUnmappedMapperConfig.class,
//        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ProductMapper.class}
)
public abstract class StoreMapper {
//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    private static String HOST_PORT = "";

    @Value("${storage.download-url:/api/v1/file/download/}")
    private String downloadPath;

    @Autowired
    ServerProperties serverProperties;


    @Mapping(target = "deliveryTypes", source = "storeEntity.deliveryType", qualifiedByName = "deliveryTypeToList")
    @Mapping(target = "image", source = "storeEntity.image", qualifiedByName = "imageUrl")
    public abstract StoreResponse toDto(StoreEntity storeEntity);

    @Mapping(target = "storeType", source = "request.storeType")
    @Mapping(target = "storeCharge", source = "request.storeCharge", qualifiedByName = "mapToStoreChargeEntity")
    @Mapping(target = "deliveryCharge", source = "request.deliveryCharge", qualifiedByName = "mapToDeliveryChargeEntity")
    public abstract StoreEntity toEntity(StoreRequest request);


    public StoreType map(String storeType) {
        return StoreType.getStoreType(storeType);
    }
    public DeliveryType map(int deliveryType) {
        return DeliveryType.getDeliveryTypeById(deliveryType);
    }

    @Named("deliveryTypeToList")
    public static List<DeliveryType> deliveryTypeToList(DeliveryType deliveryType){
        return deliveryType == DeliveryType.BOTH ? Arrays.asList(DeliveryType.DELIVERY, DeliveryType.SELF_PICKUP) : Arrays.asList(deliveryType);
    }

    @Named("mapToStoreChargeEntity")
    public static StoreChargeEntity mapToStoreCharge(StoreCharge request) {
        StoreChargeType storeChargeType = StoreChargeType.getStoreChargeType(request.getStoreChargeType());
        switch (storeChargeType){
            case FIXED:
                return StoreChargeEntity.fixedStoreCharge(request.getFixedStoreCharge());
            case PERCENTAGE:
                return StoreChargeEntity.percentageCharge(request.getFixedCommissionPercentage());
            case DYNAMIC:
                return StoreChargeEntity.dynamicStoreCharge(
                        request.getBaseCommissionPercentage(),
                        request.getBaseOrderAmount(),
                        request.getExtraCommissionPercentage(),
                        request.getExtraOrderAmount(),
                        request.getBaseWeight(),
                        request.getExtraWeight()
                );
            default:
                return null;
        }
    }


    @Named("mapToDeliveryChargeEntity")
    public static DeliveryChargeEntity mapToDeliveryCharge(DeliveryCharge request) {
        DeliveryChargeType deliveryChargeType = DeliveryChargeType.getDeliveryChargeType(request.getDeliveryChargeType());
        switch (deliveryChargeType){
            case FIXED:
                return DeliveryChargeEntity.fixedDeliveryCharge(request.getFixedDeliveryCharge());
            case DYNAMIC:
                return DeliveryChargeEntity.dynamicDeliveryCharge(
                        request.getBaseDeliveryCharge(),
                        request.getBaseDeliveryDistance(),
                        request.getExtraDeliveryCharge(),
                        request.getExtraDeliveryDistance()
                );
            default:
                return null;
        }
    }

    @Named("imageUrl")
    public String getImageUrl(String image){
        if (StringUtils.isEmpty(image)) return image;
        if (image.contains(" http:") || image.contains(" https:")){// might be stored the URL
            return image;
        }else{
            try{
                if (StringUtils.isEmpty(HOST_PORT)){
                    String hostName = InetAddress.getLocalHost().getHostAddress();
                    Integer port = serverProperties.getPort();
                    String url = HOST_PORT = "http://" + hostName + ":" + port + downloadPath;
                    URI uri = URI.create(url);
                    HOST_PORT = uri.toString();
                }
                return HOST_PORT + image;
            }catch (Exception e){
                e.printStackTrace();
                return image;
            }
        }
    }

}
