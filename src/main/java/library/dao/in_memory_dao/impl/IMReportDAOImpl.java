package library.dao.in_memory_dao.impl;

import library.dao.ReportDAO;
import library.dao.storage.Storage;
import library.helper.PropertiesReader;
import library.model.entity.Report;

public class IMReportDAOImpl implements ReportDAO {
    private static Storage reportsStorage = PropertiesReader.isInMemory();
    private static ReportDAO dao;
    private IMReportDAOImpl(){}

    public static synchronized ReportDAO getInstance(){
        if(dao == null)
            dao = new IMReportDAOImpl();
        return dao;
    }

    @Override
    public boolean createReport(Report report) {
        return reportsStorage.createReport(report);
    }

    @Override
    public Report findReport(long id) {
        return reportsStorage.findReport(id);
    }

    @Override
    public boolean updateReport(Report report) {
        return reportsStorage.updateReport(report);
    }

    @Override
    public boolean deleteReport(Report report) {
        return reportsStorage.deleteReport(report);
    }

}
