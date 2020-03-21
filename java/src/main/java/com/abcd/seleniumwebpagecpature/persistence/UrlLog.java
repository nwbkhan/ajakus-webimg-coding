package com.abcd.seleniumwebpagecpature.persistence;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Table(name = "url_logs")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class UrlLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @CreatedDate
    @Column(name = "created_on")
    Date createdOn;
}
