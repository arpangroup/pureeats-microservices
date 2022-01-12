package com.arpangroup.inventory.registry;

import com.arpangroup.inventory.service.StorageService;

public interface StorageRegistry {
    public StorageService getService(String serviceName);
}
