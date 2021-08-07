package com.indiacleantool.cleantool.web.companymodules.asset;

import com.indiacleantool.cleantool.web.companymodules.asset.model.entity.Asset;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> postAssets(@Valid @RequestBody Asset asset, BindingResult result) {

        int i = 0;
        i = i++;

        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);

        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(assetService.saveAsset(asset), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAssets() {
        return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @GetMapping("/companyasset/{companycode}")
    public ResponseEntity<?> getAllCompanyAssets(@PathVariable String companycode) {
        return new ResponseEntity<>(assetService.getAllAssetsByCompanyCode(companycode), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getAssetByCode(@PathVariable String code) {
        return new ResponseEntity<>(assetService.getAssetByCode(code), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteAssetByCode(@PathVariable String code) {
        assetService.deleteAsset(code);
        return new ResponseEntity<>("Asset wth code : " + code + ", has been deleted", HttpStatus.OK);
    }
}
