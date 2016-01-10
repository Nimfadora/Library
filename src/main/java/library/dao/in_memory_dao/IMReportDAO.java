package library.dao.in_memory_dao;

import library.dao.ReportDAO;
import library.dao.storage.InMemoryStorage;
import library.model.entity.Report;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IMReportDAO implements ReportDAO {
    private static ReportDAO dao;
    private IMReportDAO(){}

    public static synchronized ReportDAO getInstance(){
        if(dao == null)
            dao = new IMReportDAO();
        return dao;
    }

    @Override
    public List<Report> getReports() {
       return  new LinkedList<>(InMemoryStorage.reportsStorage.values());
    }

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
