package com.demo.telegrambot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer identity;

    @Column(name = "name")
    private  String name;;



    @OneToOne (mappedBy="city")
    @EqualsAndHashCode.Exclude
    private Description description;

}
