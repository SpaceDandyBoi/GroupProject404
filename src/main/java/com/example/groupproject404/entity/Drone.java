package com.example.groupproject404.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class Drone {

	private final UUID id;

	private final String Name;
	private final String model;
	private final String DirTable;
	private final int speed;

	public Drone(@JsonProperty("id") UUID id,
				 @JsonProperty("name") String name,
				 @JsonProperty("model") String model,
				 @JsonProperty("dirTable") String dirTable,
				 @JsonProperty("speed") int speed) {
		this.id = id;
		Name = name;
		this.model = model;
		DirTable = dirTable;
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public String getModel() {
		return model;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return Name;
	}


	public String getDirTable() {
		return DirTable;
	}

	
}
