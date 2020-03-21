package com.abcd.seleniumwebpagecpature.api;


import com.abcd.seleniumwebpagecpature.ApiResponse;
import com.abcd.seleniumwebpagecpature.PathConstants;
import com.abcd.seleniumwebpagecpature.exceptions.CaptureException;
import com.abcd.seleniumwebpagecpature.messages.Messages;
import com.abcd.seleniumwebpagecpature.service.ChromeDriverCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(PathConstants.CAPTURE)
public class CaptureApplicationController {

    private final static Logger logger = LoggerFactory.getLogger(CaptureApplicationController.class);

    private final ChromeDriverCapture chromeDriverCapture;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    public CaptureApplicationController(ChromeDriverCapture chromeDriverCapture) {
        this.chromeDriverCapture = chromeDriverCapture;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public FileSystemResource captureScreen(@RequestParam String url) {
        try {
            final File capturedFile =
                    chromeDriverCapture.capture(url);
            return new FileSystemResource(capturedFile);
        } catch (Exception ex) {
            final String error = String.format(Messages.CAUGHT_ERROR_WHILE_CAPTURING_THE_URL, url);
            logger.error(error);
            ex.printStackTrace();
            throw new CaptureException(error);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<String>> appName() {
        return ApiResponse.ok(appName);
    }
}
