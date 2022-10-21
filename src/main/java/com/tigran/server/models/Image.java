package com.tigran.server.models;

import lombok.Data;

@Data
public class Image {
    private String fileName;
    private String uuid;
    private boolean usage = false;
}
