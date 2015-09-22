package com.dylandavidson.service.song;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	private final static SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	public SongQuery(){
		
	}
	
	public void setYear(int year) throws ParseException{
		Date d = FORMATTER.parse(Integer.toString(year) + "-01-01T00:00:00.000-0700");
		Date e = FORMATTER.parse(Integer.toString(year) + "-12-31T00:00:00.000-0700");
		fromYear=d;
		toYear=e;
	}
	
	public void setYear(int from, int to) throws ParseException{
		Date d = FORMATTER.parse(Integer.toString(from) + "-01-01T00:00:00.000-0700");
		Date e = FORMATTER.parse(Integer.toString(to) + "-12-31T00:00:00.000-0700");
		fromYear=d;
		toYear=e;
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

	public Date getFromYear() {
		return fromYear;
	}

	public Date getToYear() {
		return toYear;
	}
	
}
