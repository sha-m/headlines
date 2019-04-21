package com.example.headlines.manager.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class AJAXResult implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean success;
	private Object data;
}
