package com.dylandavidson.service.song;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongQuery {
	private Date fromYear=null;
	private Date toYear=null;
	private List<String> names = null;
	private List<String> artists=null;
	private List<String> genres=null;
	private Boolean original=null;
	
	public SongQuery(){
		
	}
	
	public void setYear(Date year){
		fromYear=year;
		toYear=year;
	}
	
	public void setYear(Date fromYear, Date toYear){
		this.fromYear=fromYear;
		this.toYear=toYear;
	}


	public void setName(String name, String ... moreNames) {
		if(null==name){
			throw new IllegalArgumentException("name cannot be null");
		}
		names = new ArrayList<String>();
		names.add(name);
		for(String nameInArray : moreNames){
			names.add(nameInArray);
		}
		
	}



	public void setArtist(String artist, String ... moreArtists) {
		if(null==artist){
			throw new IllegalArgumentException("artist name cannot be null");
		}
		artists = new ArrayList<String>();
		artists.add(artist);
		for(String artistInArray : moreArtists){
			artists.add(artistInArray);
		}
	}



	public List<String> getNames() {
		return names;
	}

	public List<String> getArtists() {
		return artists;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenre(String genre, String ... moreGenres) {
		if(null==genre){
			throw new IllegalArgumentException("genre cannot be null");
		}
		genres = new ArrayList<String>();
		genres.add(genre);
		for(String genreInArray : moreGenres){
			genres.add(genreInArray);
		}
	}

	public Boolean getOriginal() {
		return original;
	}

	public void setOriginal(Boolean original) {
		this.original = original;
	}
}
