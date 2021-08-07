package com.indiacleantool.cleantool.web.companymodules.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{companyCode}")
    public ResponseEntity<?> getDashboardReport(@PathVariable String companyCode){
        return new ResponseEntity<>(dashboardService.getInitialDashboardReport(companyCode), HttpStatus.OK);
    }

}
