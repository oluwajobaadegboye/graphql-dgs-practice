package com.rpi.graphqldgspractice.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Department department;
}
