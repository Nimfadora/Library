package library.service;

import library.model.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    public boolean createReport(ReportDTO report);

    public boolean updateReport(ReportDTO report);

    public List<ReportDTO> getReports();
}
