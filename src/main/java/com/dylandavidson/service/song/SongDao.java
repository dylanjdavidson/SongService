package com.dylandavidson.service.song;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * A data access object class which provides all the key operations to insert and
 * retrieve data from the database.
 * 
 * I used a MongoLab sandbox database instance to manage the collection of songs
 * used for testing.
 * 
 * I used a local property file to hold the database name, username, and
 * password to avoid checking them into GitHub.
 * 
 * @author dylan
 * 
 */

public class SongDao {

	private final Datastore datastore;

	public static SongDao newSongDao() throws UnknownHostException {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("/Users/dylan/config.properties");

			// load a properties file
			try {
				prop.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Morphia morphia = new Morphia();
		ServerAddress serverAddress = new ServerAddress(
				"ds029793.mongolab.com", 29793);
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		MongoCredential credentia = MongoCredential.createCredential(prop
				.getProperty("dbuser"), prop.getProperty("database"), prop
				.getProperty("dbpassword").toCharArray());
		credentialsList.add(credentia);
		MongoClient client = new MongoClient(serverAddress, credentialsList);
		Datastore datastore = morphia.createDatastore(client, "song-service");

		return new SongDao(datastore);
	}

	public void saveSong(SongDO songDo) {
		this.datastore.save(songDo);
	}

	public List<SongDO> findSongs(SongQuery songQuery) {
		Query<SongDO> q = this.datastore.createQuery(SongDO.class);
		if (songQuery.getNames() != null && !songQuery.getNames().isEmpty()) {
			q.field("name").hasAnyOf(songQuery.getNames());
		}
		if (songQuery.getArtists() != null && !songQuery.getArtists().isEmpty()) {
			q.field("artist").hasAnyOf(songQuery.getArtists());
		}
		if (songQuery.getGenres() != null && !songQuery.getGenres().isEmpty()) {
			q.field("genre").hasAnyOf(songQuery.getGenres());
		}
		if (songQuery.getOriginal() != null) {
			q.field("original").equal(songQuery.getOriginal());
		}
		if (songQuery.getFrom() != null && songQuery.getTo() != null) {
			q.field("year").greaterThanOrEq(songQuery.getFrom());
			q.field("year").lessThanOrEq(songQuery.getTo());
		}
		List<SongDO> songDOList = q.asList();
		return songDOList;
	}

	private SongDao(Datastore datastore) {
		this.datastore = datastore;
	}
}
