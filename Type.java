package tn.MITProject.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
	
	PROPERTY ("P"),
	CREDIT ("C"),
	AGRICULTURE("A");
	
	private String productType;
	
	private Type(String productType) {
		this.productType = productType;
	}
	
	@JsonValue
	public String getType() {
		 
        return this.productType;
    }

	@JsonCreator
	public static Type decode(final String value) {
		
		for (Type ty : Type.values()) {
			 
            if (ty.getType().equals(value)) {
 
                return ty;
            }
        }
 
        return null;
    }
	
	
	}
	
	




