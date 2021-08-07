package com.indiacleantool.cleantool.web.companymodules.dashboard;

import com.indiacleantool.cleantool.web.companymodules.dashboard.model.dto.DashboardReportResponse;
import com.indiacleantool.cleantool.web.companymodules.asset.AssetService;
import com.indiacleantool.cleantool.web.companymodules.companyservice.CompanyServiceSprService;
import com.indiacleantool.cleantool.web.companymodules.employees.EmployeeSprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private EmployeeSprService employeeSprService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private CompanyServiceSprService companyServiceSprService;

    public DashboardReportResponse getInitialDashboardReport(String compnayCode) {

        Long serviceCount = companyServiceSprService.getCountByCompanyCode(compnayCode);
        Long employeeCount = employeeSprService.getCountByCompanyCode(compnayCode);
        Long assetCount = assetService.getCountByCompanyCode(compnayCode);

        DashboardReportResponse dashboardReportResponse = new DashboardReportResponse(serviceCount, employeeCount,
                assetCount);
        return dashboardReportResponse;
    }

}
