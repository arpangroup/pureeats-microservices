package com.arpangroup.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class Utils {
    public static Pageable createPage(Integer pageNo, Integer pageSize){
        pageNo = (pageNo == null || pageNo < Constants.DEFAULT_PAGE_NUMBER) ? Constants.DEFAULT_PAGE_NUMBER : pageNo;
        pageSize = (pageSize == null || pageSize < Constants.DEFAULT_PAGE_SIZE) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return pageable;
    }
}
