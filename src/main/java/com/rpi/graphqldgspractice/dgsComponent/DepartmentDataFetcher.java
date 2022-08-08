package com.rpi.graphqldgspractice.dgsComponent;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.rpi.graphqldgspractice.model.Department;
import com.rpi.graphqldgspractice.model.Employee;
import com.rpi.graphqldgspractice.repository.DepartmentRepository;
import com.rpi.graphqldgspractice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class DepartmentDataFetcher {

    private final DepartmentRepository departmentRepository;

    @DgsQuery
    public List<Department> departments() {
        return departmentRepository.findAll();
    }

    @DgsQuery
    public Department departmentById(@InputArgument Long id){
        return departmentRepository.findById(id).orElse(Department.builder().build());
    }
}
