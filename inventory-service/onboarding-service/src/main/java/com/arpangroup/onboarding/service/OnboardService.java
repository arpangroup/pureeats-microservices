package com.arpangroup.onboarding.service;

import com.arpangroup.onboarding.entity.StoreEntity;
import com.arpangroup.onboarding.exception.IdNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface OnboardService {
    public List<StoreEntity> findAll();
    public StoreEntity findOnboardDetailsByOutletName(String outletName);
    public StoreEntity finById(Integer id);
    public StoreEntity onboardStore(StoreEntity storeEntity);
}
