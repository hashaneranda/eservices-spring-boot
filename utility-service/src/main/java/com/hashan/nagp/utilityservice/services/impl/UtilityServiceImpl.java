package com.hashan.nagp.utilityservice.services.impl;

import com.hashan.nagp.utilityservice.models.UtilityModel;
import com.hashan.nagp.utilityservice.services.UtilityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UtilityServiceImpl implements UtilityService {
    private static final Map<String, UtilityModel> UTILITY_LIST = new HashMap<>();

    static {
        UTILITY_LIST.put("001", new UtilityModel("001", "Cleaning"));
        UTILITY_LIST.put("002", new UtilityModel("002", "Plumber"));
        UTILITY_LIST.put("003", new UtilityModel("003", "Carpenter"));
        UTILITY_LIST.put("004", new UtilityModel("004", "Mechanic"));
    }

    public UtilityModel addUtility(String id, String serviceName) {
        UTILITY_LIST.put(id, new UtilityModel(id, serviceName));
        return UTILITY_LIST.get(id);
    }

    public UtilityModel getUtilityById(String id) {
        return UTILITY_LIST.getOrDefault(id, null);
    }

    public UtilityModel updateUtility(String id, String name) {
        if (UTILITY_LIST.containsKey(id)) {
            UTILITY_LIST.get(id).setName(name);
        }

        return UTILITY_LIST.get(id);
    }

    public void deleteUtility(String id) {
        UTILITY_LIST.remove(id);
    }

    public List<UtilityModel> getAllUtilities() {
        return new ArrayList<>(UTILITY_LIST.values());
    }
}
