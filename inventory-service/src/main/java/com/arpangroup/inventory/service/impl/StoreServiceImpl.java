package com.arpangroup.inventory.service.impl;

import com.arpangroup.inventory.common.*;
import com.arpangroup.inventory.dto.request.StoreRequest;
import com.arpangroup.inventory.entity.*;
import com.arpangroup.inventory.exception.IdNotFoundException;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.mapper.StoreMapper;
import com.arpangroup.inventory.registry.StorageRegistry;
import com.arpangroup.inventory.repository.StoreRepository;
import com.arpangroup.inventory.service.StoreService;
import com.arpangroup.inventory.validator.StoreValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@Transactional
@Slf4j
public class StoreServiceImpl implements StoreService {
    private final StoreRepository repository;
    private final StoreMapper mapper;
    private final StoreValidator validator;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    @Value("${store.default.delivery-time:10}")
    private int defaultDeliveryTime;
    @Value("${store.default.store-type:RESTAURANT}")
    private StoreType defaultStoreType;
    @Value("${store.default.delivery-type:DELIVERY}")
    private DeliveryType defaultDeliveryType;
    @Value("${store.default.rating:3}")
    private float defaultRating;
    @Value("${store.default.delivery-radius:10}")
    private int defaultDeliveryRadius;

    @Value("${storage.provider:FILE}")
    private String storageProvider;

    @Autowired
    StorageRegistry storageRegistry;


    /*
    @Value("${some.key:my default value}")
    private String stringWithDefaultValue;//String Default
    @Value("${some.key:true}")
    private boolean booleanWithDefaultValue;//Boolean Default
    @Value("${some.key:one,two,three}")
    private String[] stringArrayWithDefaults;//Arrays
    @Value("#{'${cp.user.skills}'.split(',')}")
    private List<String> userSkills;//List
    @Value("#{systemProperties['some.key'] ?: 'my default system property value'}")
    private String spelWithDefaultValue;// Using SpEL
    @Value("#{${cp.user.teamMates}}")
    private Map<Integer, String> teamMates;//Map
    */

    public StoreServiceImpl(StoreRepository repository, StoreMapper mapper, StoreValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public StoreEntity saveOrUpdate(StoreEntity request, MultipartFile file) {
        validator.validate(request);
        try{
            String slug = toSlug(request.getStoreName());
            boolean isSlugExist = repository.findBySlugIgnoreCase(slug).isPresent();
            if (isSlugExist) slug += RandomStringUtils.randomAlphabetic(10);
            request.setSlug(slug);

            // set default values
            if (request.getStoreType() == null) request.setStoreType(defaultStoreType);
            if (request.getDeliveryType() == null) request.setDeliveryType(defaultDeliveryType);
            if (request.getDeliveryTimeInMinute() <= 0) request.setDeliveryTimeInMinute(defaultDeliveryTime);
            if (request.getRating() <= 0) request.setRating(defaultRating);

            // set store image
            if (file != null && !file.isEmpty()){
                log.info("Uploading store image.......");
                String image = storageRegistry.getService(storageProvider).uploadFile(file);
                request.setImage(image);
            }

            if (request.getDeliveryRadiusInKm() == null){
                request.setDeliveryRadiusInKm(defaultDeliveryRadius);
            }
            return repository.save(request);
        }catch (IllegalArgumentException e1){
            e1.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public StoreEntity findById(int id) {
        return repository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    @Override
    public StoreEntity findBySlug(String slug) {
            return repository.findBySlugIgnoreCase(slug).orElseThrow(() -> new IdNotFoundException("slug not found"));
    }

    @Override
    public List<StoreEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public StoreEntity updateStore(int id, StoreRequest request) {
        StoreEntity entity = repository.findById(id).orElseThrow(() -> new ValidationException(ValidationException.ValidationError.INVALID_ID, "invalid id"));
        //if (request.getStoreName() != null)entity.setStoreName(request.getStoreName());
        if (request.getDescription() != null)entity.setDescription(request.getDescription());
        if (request.getContactNumber() != null)entity.setContactNumber(Long.valueOf(request.getContactNumber()));
        //image
        if (request.getRating() != null)entity.setRating(request.getRating());

        if (request.getStoreType() != null)entity.setStoreType(StoreType.getStoreType(request.getStoreType()));
        if (request.getDeliveryType() != null)entity.setDeliveryType(DeliveryType.getDeliveryTypeById(request.getDeliveryType()));

        if (request.getPriceRange() != null)entity.setPriceRange(request.getPriceRange());
        if (request.getAverageCostForTwo() != null)entity.setAverageCostForTwo(request.getAverageCostForTwo());
        if (request.getDeliveryTimeInMinute() != null)entity.setDeliveryTimeInMinute(request.getDeliveryTimeInMinute());

//        if (request.isPureVeg() != null)entity.setPureVeg(request.getIsPureVeg());
//        if (request.getIsPopular() != null)entity.setPopular(request.getIsPopular());
//        if (request.getIsRecommended() != null)entity.setRecommended(request.getIsRecommended());
//        if (request.getIsOpenTableSupport() != null)entity.setOpenTableSupport(request.getIsOpenTableSupport());
//        if (request.getIsTableReservationSupport() != null)entity.setTableReservationSupport(request.getIsOpenTableSupport());
//
//        if (request.getIsPermClosed() != null)entity.setPermClosed(request.getIsPermClosed());
//        if (request.getIsTempClosed() != null)entity.setTempClosed(request.getIsTempClosed());
        //if (request.getIsOpeningSoon() != null)entity.setOpeningSoon(request.getIsOpeningSoon());

        if (request.getLicenceNumber() != null)entity.setLicenceNumber(request.getLicenceNumber());
        if (request.getDisclaimer() != null)entity.setDisclaimer(request.getDisclaimer());
        try{
            return repository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteStore(int id) {
        try {
            repository.deleteById(id);
        }catch (IllegalArgumentException e1){
            throw new ValidationException(ValidationException.ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            throw new ValidationException(ValidationException.ValidationError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    private String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }



}
