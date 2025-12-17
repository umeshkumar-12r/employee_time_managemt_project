package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.TimeOffRequest;
import java.util.List;

public interface TimeOffRequestDAO {


    void addRequest(TimeOffRequest request);


    void addTimeOffRequest(TimeOffRequest request);


    List<TimeOffRequest> getAllRequests();


    List<TimeOffRequest> getRequestsByEmployee(int employeeId);


    void updateRequestStatus(int requestId, String newStatus);
}
