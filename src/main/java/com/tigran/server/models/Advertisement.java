package com.tigran.server.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "advertisements")
public class Advertisement {
    @Id
    public String id;
    @Column
    public String imageUuid;
}
