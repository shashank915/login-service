package com.frostinteractive.loginservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestUri {
    @Id
    private String uriId= UUID.randomUUID().toString();
    private String  uri;
    private boolean preLogin;
    @Column(name = "http_method")
    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

//    public boolean compare(String requestUri, HttpMethod httpMethod) {
//        if(this.httpMethod != httpMethod){
//            return false;
//        }
//
//        String[] uriBits = URI
//    }
}
