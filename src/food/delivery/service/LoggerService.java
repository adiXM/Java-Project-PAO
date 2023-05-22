package food.delivery.service;

import food.delivery.model.EventLog;
import food.delivery.repository.CustomerRepository;
import food.delivery.repository.LoggerRepository;

import java.sql.SQLException;

public class LoggerService {
    private final LoggerRepository loggerRepository;

    public LoggerService(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public void createLog(EventLog log) throws SQLException {
        this.loggerRepository.create(log);
    }
}
