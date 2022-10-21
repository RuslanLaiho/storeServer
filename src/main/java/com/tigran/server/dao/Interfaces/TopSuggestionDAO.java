package com.tigran.server.dao.Interfaces;

import com.tigran.server.models.TopSuggestion;

import java.sql.SQLException;
import java.util.List;

public interface TopSuggestionDAO {
    public void create(TopSuggestion topSuggestions) throws SQLException;
    public List<TopSuggestion> read() throws SQLException;
    public void update(String id, TopSuggestion topSuggestions) throws SQLException;
    public void delete(TopSuggestion id) throws SQLException;
}
