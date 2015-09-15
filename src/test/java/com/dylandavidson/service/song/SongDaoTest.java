package com.dylandavidson.service.song;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SongDaoTest {

	private final static SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Test
	public void test() throws UnknownHostException, ParseException {

		List<Date> songDates = Arrays.asList(
				FORMATTER.parse("2001-01-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-02-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-03-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-04-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-05-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-06-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-07-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-08-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-09-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-10-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-11-04T12:08:56.235-0700"),
				FORMATTER.parse("2001-12-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-01-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-02-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-03-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-04-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-05-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-06-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-07-04T12:08:56.235-0700"),
				FORMATTER.parse("2002-08-04T12:08:56.235-0700"));

		// Create some test songs
		SongDao songDao = SongDao.newSongDao();
		for (int i = 0; i < 20; i++) {
			boolean original = (i % 4 == 0) ? true : false;
			SongDO songDO = new SongDO("http://song-" + i, songDates.get(i),
					"name-" + i, "artist-" + i, "genre-" + i, original);
			songDao.saveSong(songDO);
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setName("name-2", "name-3", "name-5", "name-10",
					"nameeeee");
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(4, songs.size());
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setArtist("artist-2", "artist-3", "artist-5",
					"artist-10", "artistttttt");
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(4, songs.size());
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setName("name-2", "name-3", "name-5", "name-19",
					"nameeeee");
			songQuery.setArtist("artist-2", "artist-3", "artist-5",
					"artist-10", "artistttttt");
			songQuery.setGenre("genre-2", "genre-5");
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(2, songs.size());
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setArtist("artist-2", "artist-3", "artist-5",
					"artist-10", "artistttttt");
			songQuery.setOriginal(true);
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(0, songs.size());
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setArtist("artist-2", "artist-3", "artist-5",
					"artist-10", "artistttttt");
			songQuery.setOriginal(false);
			songQuery.setYear(FORMATTER.parse("2001-03-04T12:08:56.235-0700"));
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(1, songs.size());
		}

		{
			SongQuery songQuery = new SongQuery();
			songQuery.setArtist("artist-2", "artist-3", "artist-5",
					"artist-10", "artistttttt");
			songQuery.setOriginal(false);
			songQuery.setYear(FORMATTER.parse("2001-03-04T12:08:56.235-0700"),
					FORMATTER.parse("2001-07-04T12:08:56.235-0700"));
			List<SongDO> songs = songDao.findSongs(songQuery);
			Assert.assertEquals(3, songs.size());
		}

	}

}
