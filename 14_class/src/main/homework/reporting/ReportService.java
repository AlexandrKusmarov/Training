package main.homework.reporting;

import main.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
