package com.rpi.graphqldgspractice.dgsComponent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.rpi.graphqldgspractice.model.Department;
import com.rpi.graphqldgspractice.model.Employee;
import com.rpi.graphqldgspractice.model.EmployeeInput;
import com.rpi.graphqldgspractice.repository.EmployeeRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@DgsComponent
@RequiredArgsConstructor
public class EmployeeDataFetcher {

    private final EmployeeRepository employeeRepository;

    @DgsQuery
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @DgsQuery
    public Employee employeeById(@InputArgument Long id){
        return employeeRepository.findById(id).orElse(Employee.builder().build());
    }

    @DgsMutation
    public Employee createEmployee(@InputArgument("employee")  EmployeeInput employeeInput){
//        DataFetchingEnvironment dataFetchingEnvironment
//        Map<String,Object> input = dataFetchingEnvironment.getArgument("employee");
//        EmployeeInput employeeInputt = new ObjectMapper().convertValue(input, EmployeeInput.class);

        return employeeRepository.save(
                Employee.builder()
                        .name(employeeInput.getEmployeeName())
                        .department(Department.builder().name(employeeInput.getDepartmentName()).build())
                        .build()
        );
    }

    @DgsMutation
    public Employee updateEmployeeName(@InputArgument Integer id, @InputArgument String name){
        Employee employee = employeeRepository.findById(Long.valueOf(id)).orElse(null);
        if(employee!=null){
            employee.setName(name);
            return employeeRepository.save(employee);
        }
        return null;
    }

    @DgsMutation
    public Boolean delete(@InputArgument Integer id){
        employeeRepository.deleteById(Long.valueOf(id));
        return true;
    }
}
