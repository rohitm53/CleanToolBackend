package com.indiacleantool.cleantool.web.companymodules.asset;

import com.indiacleantool.cleantool.web.companymodules.asset.model.entity.Asset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Long> {

    Asset findByCode(String code);

    Iterable<Asset> findByCompanyCode(String companyCode);

    long countByCompanyCode(String companyCode);
}
