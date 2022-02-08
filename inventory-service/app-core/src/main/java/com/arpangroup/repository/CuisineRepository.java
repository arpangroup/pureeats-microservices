package com.arpangroup.repository;

import com.arpangroup.entity.master.CityEntity;
import com.arpangroup.entity.master.CollectionEntity;
import com.arpangroup.entity.master.CuisineEntity;
import com.arpangroup.entity.master.EstablishmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineRepository extends JpaRepository<CuisineEntity, Integer> {
}
