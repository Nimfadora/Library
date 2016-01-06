package library.dao.in_memory_dao.impl;

import library.dao.ReportDAO;
import library.dao.storage.InMemoryStorage;
import library.dao.storage.Storage;
import library.helper.PropertiesReader;
import library.model.entity.Report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class IMReportDAOImpl implements ReportDAO {
    private static ReportDAO dao;
    private IMReportDAOImpl(){}

    public static synchronized ReportDAO getInstance(){
        if(dao == null)
            dao = new IMReportDAOImpl();
        return dao;
    }

    @Override
    public List<Report> getReports() {
       return  (List<Report>)InMemoryStorage.reportsStorage.values();
    }
    //TODO: refactor this trash (entities(dtos') must be formed on controller layer)
    @Override
    public boolean createReport(Report report) {
        Random random = new Random();
        report.setId(random.nextLong());
        report.setRent(new Date());
        return InMemoryStorage.reportsStorage.put(report.getId(), report)!=null;
    }

    @Override
    public boolean updateReport(Report report) {
        report.setReturnDate(new Date());
        return InMemoryStorage.reportsStorage.replace(report.getId(), report)!=null;
    }

}
