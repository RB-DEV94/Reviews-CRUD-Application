package com.Project.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldname;

    private long fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldname, long fieldvalue) {
        super(String.format("%s not found with %s : '%s'",resourceName,fieldname,fieldvalue));
        this.resourceName = resourceName;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldname() {
        return fieldname;
    }

    public long getFieldvalue() {
        return fieldvalue;
    }



}
