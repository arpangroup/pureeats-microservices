package com.arpangroup.onboarding.service.impl;

import com.arpangroup.onboarding.entity.StoreEntity;
import com.arpangroup.onboarding.service.OnboardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnboardServiceImpl implements OnboardService {


    @Override
    public List<StoreEntity> findAll() {
        return null;
    }

    @Override
    public StoreEntity findOnboardDetailsByOutletName(String outletName) {
        return null;
    }

    @Override
    public StoreEntity finById(Integer id) {
        return null;
    }

    @Override
    public StoreEntity onboardStore(StoreEntity storeEntity) {
        return null;
    }
}
