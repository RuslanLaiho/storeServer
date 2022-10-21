package com.tigran.server.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stories")
public class Stories {
    @Id
    String id;
    @Column
    String imageUuid;
    @Column
    String contentUuid;
}
