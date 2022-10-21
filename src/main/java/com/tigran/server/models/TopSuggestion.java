package com.tigran.server.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topSuggestions")
public class TopSuggestion {
    @Id
    String id;
    @Column
    String imageUuid;
    @Column
    String contentUuid;
}
