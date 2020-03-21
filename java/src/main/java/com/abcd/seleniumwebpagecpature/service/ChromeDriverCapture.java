package com.abcd.seleniumwebpagecpature.service;

import java.io.File;
import java.io.IOException;

public interface ChromeDriverCapture {
    File capture(String url) throws IOException;
}
