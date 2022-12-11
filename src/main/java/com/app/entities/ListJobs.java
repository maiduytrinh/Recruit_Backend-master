package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "list_job")

public class ListJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Long salary;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "age")
    private String age;
    @Column(name = "certification")
    private String certification;
    @Column(name = "experence")
    private String experence;
    @Column(name = "contact_info")
    private String contactInfo;
    @Column(name = "area")
    private String area;
    @Column(name = "work_address")
    private String workAddress;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "code_address")
    private Integer codeAddress;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "expiration_date")
    private LocalDate dateExpiration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "listProfileId.jobs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ListProfiles> listProfiles;
}
