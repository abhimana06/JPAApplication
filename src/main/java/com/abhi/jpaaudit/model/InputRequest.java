package com.abhi.jpaaudit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InputRequest<T>{
    @JsonProperty(value = "username", required = true)
    private String loggedInUser;
    private String timeZone;
    private T employee;

}
