package library.dao;

import library.model.entity.Report;

import java.util.List;

public interface ReportDAO {
    public List<Report> getReports();

    public boolean createReport(Report report);

    public boolean updateReport(Report report);
}
