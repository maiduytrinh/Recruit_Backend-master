package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "job_name")
    private String name;
    @Column(name = "count_job")
    private Integer countJob;
    @OneToMany(mappedBy = "job")
    private List<ListJobs> listJobs;
    @OneToMany(mappedBy = "job")
    private List<Users> users;
}
