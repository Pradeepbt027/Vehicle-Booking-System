package com.eikona.tech.dto;

public class SlotDto {
	
	private int id;
	private String time;
	private Long count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public SlotDto() {
		super();
	}
	public SlotDto(String time, Long count) {
		super();
		this.time = time;
		this.count = count;
	}

}
