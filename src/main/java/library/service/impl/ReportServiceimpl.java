package library.service.impl;

import library.dao.ReportDAO;
import library.dao.in_memory_dao.impl.IMReportDAOImpl;
import library.helper.PropertiesReader;
import library.model.dto.ReportDTO;
import library.model.entity.Report;
import library.service.ReportService;
import library.transform.Transformer;

import java.util.LinkedList;
import java.util.List;

public class ReportServiceimpl implements ReportService {
    private static ReportService service;
    ReportDAO dao = PropertiesReader.getReportDao();
    private ReportServiceimpl(){};

    public static synchronized ReportService getInstance(){
            if (service == null)
                service = new ReportServiceimpl();
            return service;
    }

    @Override
    public List<ReportDTO> getReports() {
        List<ReportDTO> reports = new LinkedList<>();
        dao.getReports().forEach(entity -> {
            reports.add(Transformer.transformReport(entity));
        });
        return reports;
    }

    @Override
    public boolean createReport(ReportDTO reportDTO) {
        return dao.createReport(Transformer.transformReportDTO(reportDTO));
    }

    @Override
    public boolean updateReport(ReportDTO reportDTO){
        return dao.updateReport(Transformer.transformReportDTO(reportDTO));
    }
}
