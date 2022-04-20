package com.hashan.nagp.adminservice.services;

import com.hashan.nagp.adminservice.exceptions.AssignOrder;
import com.hashan.nagp.adminservice.exceptions.ValidateParamsException;
import com.hashan.nagp.adminservice.models.OrderAssignmentModel;


public interface AdminService {

    void assignOrder(OrderAssignmentModel orderAssignmentModel) throws AssignOrder, ValidateParamsException;
}
