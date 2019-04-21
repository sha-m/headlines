package com.example.headlines.manager.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchGoods implements Serializable{

	private Integer id;
	private String gname;
	private String ginfo;
	private float gprice;
	private String gimg;
	private String categoryone;
}
