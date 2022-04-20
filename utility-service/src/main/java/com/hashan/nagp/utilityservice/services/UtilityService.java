package com.hashan.nagp.utilityservice.services;

import com.hashan.nagp.utilityservice.models.UtilityModel;

import java.util.List;


public interface UtilityService {

    UtilityModel addUtility(String id, String serviceName);
    UtilityModel getUtilityById(String id);
    UtilityModel updateUtility(String id, String name);
    void deleteUtility(String id);
    List<UtilityModel> getAllUtilities();
}
