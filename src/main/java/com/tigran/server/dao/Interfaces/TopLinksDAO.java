package com.tigran.server.dao.Interfaces;

import com.tigran.server.models.TopLinks;

import java.sql.SQLException;
import java.util.List;

public interface TopLinksDAO {
    public void create(TopLinks topLinks) throws SQLException;
    public TopLinks read(String id) throws SQLException;
    public List<TopLinks> readAll() throws SQLException;
    public void update(String id, TopLinks topLinks) throws SQLException;
    public void delete(TopLinks id) throws SQLException;
}
