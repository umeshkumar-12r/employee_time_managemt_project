package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.WorkEntry;
import java.util.List;

public interface WorkEntryDAO {

    void addWorkEntry(WorkEntry entry);

    List<WorkEntry> getAllWorkEntries();

    // 🔥 REQUIRED BY WorkEntryDAOImpl — must be here!
    List<WorkEntry> getWorkEntriesByEmployee(int empId);
}
