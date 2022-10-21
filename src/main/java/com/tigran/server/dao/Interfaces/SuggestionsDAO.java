package com.tigran.server.dao.Interfaces;

import com.tigran.server.models.Suggestions;

import java.sql.SQLException;
import java.util.List;

public interface SuggestionsDAO {
    public void create(Suggestions suggestions) throws SQLException;
    public List<Suggestions> read() throws SQLException;
    public void update(String id, Suggestions suggestions) throws SQLException;
    public void delete(Suggestions id) throws SQLException;
}
