package com.vegan.test.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
	@JsonProperty("Condition") 
    public Condition condition;

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
}
