package com.dinsaren.bbuappserver.models;

import lombok.Data;

import javax.persistence.*;

@Table(name = "sms_category")
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nameKh;
    private String status;
}
