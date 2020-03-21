package com.abcd.seleniumwebpagecpature.repo;

import com.abcd.seleniumwebpagecpature.persistence.UrlLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlLogRepo
        extends JpaRepository<UrlLog, Long> {
}
