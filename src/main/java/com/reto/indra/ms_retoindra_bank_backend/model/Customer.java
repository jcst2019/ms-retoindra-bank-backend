package com.reto.indra.ms_retoindra_bank_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer {
    private String id;
    private String uniqueCode;
    private String name;
    private String lastName;
    private String dni;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;
    private Date createAt;

}
