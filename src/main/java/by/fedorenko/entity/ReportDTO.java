package by.fedorenko.entity;

import java.util.Objects;

public class ReportDTO {

    private String reportTitle;

    private String reportBody;

    private int laborCost;
    /**
     * Class Report for User from JSON answer from main-java-app
     * That class help simple generate PDF table for report our teacher/
     */

    public ReportDTO(String reportTitle, String reportBody, int laborCost) {
        super();
        this.reportTitle = reportTitle;
        this.reportBody = reportBody;
        this.laborCost = laborCost;
    }

    public ReportDTO() {
        super();
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportBody() {
        return reportBody;
    }

    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    public int getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(int laborCost) {
        this.laborCost = laborCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborCost, reportBody, reportTitle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReportDTO other = (ReportDTO) obj;
        return laborCost == other.laborCost && Objects.equals(reportBody, other.reportBody)
                && Objects.equals(reportTitle, other.reportTitle);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReportDTO [reportTitle=");
        builder.append(reportTitle);
        builder.append(", reportBody=");
        builder.append(reportBody);
        builder.append(", laborCost=");
        builder.append(laborCost);
        builder.append("]");
        return builder.toString();
    }
}
