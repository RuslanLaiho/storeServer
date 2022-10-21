package com.tigran.server.dao.Interfaces;

import com.tigran.server.models.Advertisement;

import java.sql.SQLException;
import java.util.List;

public interface AdvertisementDAO {
    public void create(Advertisement advertisement) throws SQLException;
    public List<Advertisement> read() throws SQLException;
    public void update(String id, Advertisement advertisement) throws SQLException;
    public void delete(Advertisement id) throws SQLException;
}
