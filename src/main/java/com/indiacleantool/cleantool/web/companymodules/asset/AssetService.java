package com.indiacleantool.cleantool.web.companymodules.asset;

import com.indiacleantool.cleantool.web.companymodules.asset.model.entity.Asset;
import com.indiacleantool.cleantool.exceptions.asset.AssetCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset saveAsset(Asset asset) {
        try {
            return assetRepository.save(asset);
        } catch (Exception e) {
            throw new AssetCodeException("Asset code : " + asset.getCode() + " , already existed");
        }
    }

    public Iterable<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Iterable<Asset> getAllAssetsByCompanyCode(String companycode) {
        return assetRepository.findByCompanyCode(companycode);
    }

    public Asset getAssetByCode(String code) {
        Asset asset = assetRepository.findByCode(code);
        if (asset == null) {
            throw new AssetCodeException("Asset with code :" + code + ", does not exist");
        }
        return asset;
    }

    public void deleteAsset(String code) {
        assetRepository.delete(getAssetByCode(code));
    }

    public Long getCountByCompanyCode(String companyCode) {
        return assetRepository.countByCompanyCode(companyCode);
    }

}
