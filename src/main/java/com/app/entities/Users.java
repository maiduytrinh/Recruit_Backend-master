package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "url_img")
    private String urlImg;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "email")
    private String email;
    @Column(name = "passsword")
    private String passsword;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Roles roles;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "listProfileId.users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ListProfiles> listJobs;
}
