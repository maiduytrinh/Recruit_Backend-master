package com.app.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor

public class ListProfileId implements Serializable{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_job_id")
    private ListJobs jobs;
}
