package com.abcd.seleniumwebpagecpature.config;

import com.abcd.seleniumwebpagecpature.props.DriverPathProperties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ChromeDriverConfig implements DisposableBean {
    private final DriverPathProperties driverPathProperties;
    private ChromeDriver chromeDriver;
    private ChromeDriverService chromeDriverService;
    private GeckoDriverService geckoDriverService;
    private FirefoxDriver fireFoxDriver;

    public ChromeDriverConfig(DriverPathProperties driverPathProperties) {
        this.driverPathProperties = driverPathProperties;
    }

    @Bean
    public ChromeDriverService chromeDriverService() {
        chromeDriverService = new ChromeDriverService
                .Builder()
                .withVerbose(true)
                .usingDriverExecutable(new File(driverPathProperties.getChromePath()))
                .build();
        return chromeDriverService;
    }

    @Bean
    public ChromeDriver chromeDriver(ChromeDriverService service) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--start-fullscreen");
        chromeDriver = new ChromeDriver(service, options);

        return chromeDriver;
    }

    @Bean
    public GeckoDriverService gekoDriverService() {
        geckoDriverService = new GeckoDriverService
                .Builder()
                .usingDriverExecutable(new File(driverPathProperties.getFirefoxPath()))
                .build();
        return geckoDriverService;
    }

    @Bean
    public FirefoxDriver firefoxDriver(GeckoDriverService service) {
        FirefoxOptions options = new FirefoxOptions();
        fireFoxDriver = new FirefoxDriver(service, options);

        // this can achieved with thread.sleep but documented not working for capturing as full screen
        Dimension dimension = new Dimension(1920, 10000);

        fireFoxDriver.manage().window().setSize(dimension);
        fireFoxDriver.manage().window().maximize();
        return fireFoxDriver;
    }

    @Override
    public void destroy() throws Exception {
        chromeDriverService.stop();
        chromeDriver.quit();
    }
}
