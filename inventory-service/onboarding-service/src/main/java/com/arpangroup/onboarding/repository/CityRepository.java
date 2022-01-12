package com.arpangroup.onboarding.repository;

import com.arpangroup.onboarding.entity.global.CityEntity;
import com.arpangroup.onboarding.entity.global.CollectionEntity;
import com.arpangroup.onboarding.entity.global.CuisineEntity;
import com.arpangroup.onboarding.entity.global.EstablishmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    @Query("select s from city s where lower(s.name) like lower(concat('%', :cityName,'%'))")
    List<CityEntity> findAllCityByCityName(@Param("cityName") String cityName);

    @Query("select s from city s where s.id in :cityIds" )
    List<CityEntity> findAllCityByCityIds(@Param("cityIds") List<Integer> cityIds);

    @Query("select s from city s where s.lat = :lat and s.lng = :lng" )
    List<CityEntity> findAllCityByLatLng(@Param("lat") Float lat, @Param("lng") Float lng);


    /* ############################### Cuisines #############################################*/
    @Query("select c.cuisines from city c where c.id = :cityId" )
    List<CuisineEntity> findAllCuisineByCityId(@Param("cityId") Integer cityId);

    @Query("select c.cuisines from city c where c.lat = :lat and c.lng = :lng" )
    List<CuisineEntity> findAllCuisinesByLatLng(@Param("lat") Float lat, @Param("lng") Float lng);


    /* ############################### Collections #############################################*/
    @Query("select c.collections from city c where c.id = :cityId" )
    List<CollectionEntity> findAllCollectionsByCityId(@Param("cityId") Integer cityId);

    @Query("select c.collections from city c where c.lat = :lat and c.lng = :lng" )
    List<CollectionEntity> findAllCollectionsByLatLng(@Param("lat") Float lat, @Param("lng") Float lng);

    /* ############################### Establishments #############################################*/
    @Query("select c.establishments from city c where c.id = :cityId" )
    List<EstablishmentEntity> findAllEstablishmentsByCityId(@Param("cityId") Integer cityId);

    @Query("select c.establishments from city c where c.lat = :lat and c.lng = :lng" )
    List<EstablishmentEntity> findAllEstablishmentsByLatLng(@Param("lat") Float lat, @Param("lng") Float lng);
}
