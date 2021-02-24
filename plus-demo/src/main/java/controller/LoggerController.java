package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    public void test(){
        logger.info("[日志测试]{}","test");
    }
}
