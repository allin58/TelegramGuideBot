package com.demo.telegrambot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "description")
public class Description {

    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer identity;

    @Column(name = "text")
    private  String text;;


    @OneToOne ( cascade=CascadeType.ALL)
    @JoinColumn (name="city_id")
    @EqualsAndHashCode.Exclude
    private City city;

}
