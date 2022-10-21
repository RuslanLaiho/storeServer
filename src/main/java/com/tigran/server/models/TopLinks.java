package com.tigran.server.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topLinks")
public class TopLinks {
    @Id
    private String id;
    @Column
    private String imageUuid;
    @Column
    private String title;
}
