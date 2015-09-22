package com.dylandavidson.service.song;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An object that represents all the variable data used to query the database
 * for songs.
 * 
 * It supports a definition of any combination of the following song
 * characteristics:
 * 
 * Date/date range, Song Name(s), Artist(s), Genre(s), Original (this is specific to
 * my dad's youtube channel where some songs are originals while others are
 * covers)
 * 
 * @author dylan
 * 
 */

public class SongQuery {
	private Date from = null;
	private Date to = null;
	private List<String> names = null;
	private List<String> artists = null;
	private List<String> genres = null;
	private Boolean original = null;

	private final static SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public SongQuery() {

	}

	public void setYear(int year) throws ParseException {
		setYear(year, year);
	}

	public void setYear(int fromYear, int toYear) throws ParseException {
		from = FORMATTER.parse(Integer.toString(fromYear)
				+ "-01-01T00:00:00.000-0700");
		to = FORMATTER.parse(Integer.toString(toYear)
				+ "-12-31T23:59:99.999-0700");
	}

	public void setName(String name, String... moreNames) {
		if (null == name) {
			throw new IllegalArgumentException("name cannot be null");
		}
		names = new ArrayList<String>();
		names.add(name);
		for (String nameInArray : moreNames) {
			names.add(nameInArray);
		}

	}

	public void setArtist(String artist, String... moreArtists) {
		if (null == artist) {
			throw new IllegalArgumentException("artist name cannot be null");
		}
		artists = new ArrayList<String>();
		artists.add(artist);
		for (String artistInArray : moreArtists) {
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

	public void setGenre(String genre, String... moreGenres) {
		if (null == genre) {
			throw new IllegalArgumentException("genre cannot be null");
		}
		genres = new ArrayList<String>();
		genres.add(genre);
		for (String genreInArray : moreGenres) {
			genres.add(genreInArray);
		}
	}

	public Boolean getOriginal() {
		return original;
	}

	public void setOriginal(Boolean original) {
		this.original = original;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

}
