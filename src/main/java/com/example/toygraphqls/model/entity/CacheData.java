package com.example.toygraphqls.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tcachedata")
public class CacheData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nSeq")
    private Integer seq;

    @Column(name = "dtDate")
    private String date;

    @Column(name = "sKey")
    private String key;

    @Column(name = "sValue")
    private String value;
}
