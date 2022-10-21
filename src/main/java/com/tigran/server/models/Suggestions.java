package com.tigran.server.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "suggestions")
public class Suggestions {
    @Id
    String id;
    @Column
    String imageUuid;
}
