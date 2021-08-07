package com.indiacleantool.cleantool.web.companymodules.staticservices;

import com.indiacleantool.cleantool.common.collectionUtils.ListUtils;
import com.indiacleantool.cleantool.web.companymodules.staticservices.model.entity.Services;
import com.indiacleantool.cleantool.exceptions.servicecode.ServiceCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StaticServicesService {

    @Autowired
    private StaticServiceRepository repository;

    public Services saveOrUpdateService(Services service) {
        try {
            service.setServiceCode(service.getServiceCode().toUpperCase());
            return repository.save(service);
        } catch (Exception e) {
            throw new ServiceCodeException("Service code '" + service.getServiceCode() + "' already existed ");
        }
    }

    public Iterable<Services> findAllServices() {
        return repository.findAll();
    }

    public Services findByServiceCode(String serviceCode) {
        Services services = repository.findByServiceCode(serviceCode.toUpperCase());
        if (services == null) {
            throw new ServiceCodeException("No Service available with code '" + serviceCode + "'.");
        }
        return services;
    }

    public Map<String, Services> getAllServiceDataInMap() {

        Map<String, Services> hmServices = new HashMap<>();
        Iterable<Services> iterable = repository.findAll();

        hmServices = ListUtils.convertIterableToStream(iterable)
                .collect(Collectors.toMap(Services::getServiceCode, Function.identity()));

        return hmServices;
    }

    public void deleteService(String serviceCode) {
        repository.delete(findByServiceCode(serviceCode));
    }
}
