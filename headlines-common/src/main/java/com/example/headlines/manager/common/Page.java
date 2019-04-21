package com.example.headlines.manager.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Page<T>  implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<T> datas;
	private int pageno;
	private int tailno;
	private int count;
}
