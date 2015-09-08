package com.dylandavidson.service.song;

import java.util.Date;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("songs")
public class SongDO {
	private @Id String url;
	private Date year;
	private String name;
	private String artist;
	private String genre;
	private boolean original;
	
	public SongDO(){
		
	}
	
	public SongDO(String url, Date year, String name, String artist, String genre, boolean original){
		this.url=url;
		this.year=year;
		this.name=name;
		this.artist=artist;
		this.genre=genre;
		this.original=original;
	}
	
	public Date getYear() {
		return year;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isOriginal() {
		return original;
	}

	public void setOriginal(boolean origional) {
		this.original = origional;
	}


}
