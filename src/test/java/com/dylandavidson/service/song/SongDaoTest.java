package com.dylandavidson.service.song;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Test;

public class SongDaoTest {

	@Test
	public void test() throws UnknownHostException {
		SongDao songDao = SongDao.newSongDao();
		SongDO songDO = new SongDO("url", new Date(), "name", "artist", "genre", true);
		songDao.saveSong(songDO);
	}

}
