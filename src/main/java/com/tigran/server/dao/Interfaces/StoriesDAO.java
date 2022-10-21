package com.tigran.server.dao.Interfaces;

import com.tigran.server.models.Stories;

import java.sql.SQLException;
import java.util.List;

public interface StoriesDAO {
    public void create(Stories stories) throws SQLException;
    public List<Stories> read() throws SQLException;
    public void update(String id, Stories stories) throws SQLException;
    public void delete(Stories id) throws SQLException;
}
