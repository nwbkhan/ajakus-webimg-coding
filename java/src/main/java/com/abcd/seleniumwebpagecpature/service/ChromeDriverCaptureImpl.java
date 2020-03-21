package com.abcd.seleniumwebpagecpature.service;

import com.abcd.seleniumwebpagecpature.persistence.UrlLog;
import com.abcd.seleniumwebpagecpature.repo.UrlLogRepo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ChromeDriverCaptureImpl implements ChromeDriverCapture {

    private final static Logger logger = LoggerFactory.getLogger(ChromeDriverCaptureImpl.class);

    private final ChromeDriverService chromeDriverService;
    private final UrlLogRepo urlLogRepo;
    private FirefoxDriver firefoxDriver;
    private final ChromeDriver chromeDriver;

    public ChromeDriverCaptureImpl(ChromeDriverService chromeDriverService,
                                   UrlLogRepo urlLogRepo,
                                   FirefoxDriver firefoxDriver,
                                   ChromeDriver chromeDriver) throws IOException {
        this.chromeDriverService = chromeDriverService;
        this.urlLogRepo = urlLogRepo;
        this.firefoxDriver = firefoxDriver;
        this.chromeDriver = chromeDriver;
    }

    @Override
    public File capture(String url) throws IOException {
        logger.info("Capturing for url {}", url);
        saveUrlLog(url);
        firefoxDriver.get(url);
        final File webCapturedScreen =
                firefoxDriver.getScreenshotAs(OutputType.FILE);
        return webCapturedScreen;
    }

    @Async
    public void saveUrlLog(String url) {
        UrlLog urlLog = new UrlLog();
        urlLog.setUrl(url);

        urlLogRepo.saveAndFlush(urlLog);
    }
}
