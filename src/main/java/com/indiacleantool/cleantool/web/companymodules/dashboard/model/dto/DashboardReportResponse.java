package com.indiacleantool.cleantool.web.companymodules.dashboard.model.dto;

public class DashboardReportResponse {

    private Long serviceCount;
    private Long employeeCount;
    private Long assetCount;

    public DashboardReportResponse() {
    }

    public DashboardReportResponse(Long serviceCount, Long employeeCount, Long assetCount) {
        this.serviceCount = serviceCount;
        this.employeeCount = employeeCount;
        this.assetCount = assetCount;
    }

    public Long getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Long serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Long getAssetCount() {
        return assetCount;
    }

}
