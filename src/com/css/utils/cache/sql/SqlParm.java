package com.css.utils.cache.sql;

import java.io.Serializable;

public class SqlParm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String binding;
	
	private String type;
	
	private String defaultValue;
	
	private String formatter;

	private String autoKeyGen;
	
	public String getAutoKeyGen() {
		return autoKeyGen;
	}

	public void setAutoKeyGen(String autoKeyGen) {
		this.autoKeyGen = autoKeyGen;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}
