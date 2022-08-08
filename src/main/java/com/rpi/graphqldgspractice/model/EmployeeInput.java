package com.rpi.graphqldgspractice.model;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {
    private String employeeName;
    private String departmentName;
}