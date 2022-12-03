package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "height")
    private Integer height;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "experence")
    private Integer experence;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "homeID")
    private String homeId;
    @Column(name = "CCCD")
    private String CCCD;
    @Column(name = "hobby")
    private String hobby;
    @Column(name = "personality")
    private String personality;
    @Column(name = "address")
    private String address;
    @Column(name = "education")
    private String education;
    @Column(name = "career")
    private String career;
    @Column(name = "salary")
    private String salary;
    @Column(name = "job_information")
    private String jobInformation;
    @Column(name = "phone")
    private String phone;
    @OneToOne(mappedBy = "profile")
    private Users user;
}
