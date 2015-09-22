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
				//e.printStackTrace();
			}
//
//			// get the property value and print it out
//			System.out.println(prop.getProperty("database"));
//			System.out.println(prop.getProperty("dbuser"));
//			System.out.println(prop.getProperty("dbpassword"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		ServerAddress serverAddress = new ServerAddress(
				"ds029793.mongolab.com", 29793);
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		MongoCredential credentia = MongoCredential.createCredential(
		    prop.getProperty("dbuser"), prop.getProperty("database"), prop.getProperty("dbpassword").toCharArray());
		credentialsList.add(credentia);
		MongoClient client = new MongoClient(serverAddress, credentialsList);
		Datastore datastore = morphia.createDatastore(client, "song-service");
		
		return new SongDao(datastore);
	}

	public void saveSong(SongDO songDo) {
		this.datastore.save(songDo);
	}
	
	public List<SongDO> findSongs(SongQuery songQuery){
		Query<SongDO> q = this.datastore.createQuery(SongDO.class);
		if(songQuery.getNames()!=null && !songQuery.getNames().isEmpty()){
			q.field("name").hasAnyOf(songQuery.getNames());
		}
		if(songQuery.getArtists()!=null && !songQuery.getArtists().isEmpty()){
			q.field("artist").hasAnyOf(songQuery.getArtists());
		}
		if(songQuery.getGenres()!=null && !songQuery.getGenres().isEmpty()){
			q.field("genre").hasAnyOf(songQuery.getGenres());
		}
		if(songQuery.getOriginal()!=null){
			q.field("original").equal(songQuery.getOriginal());
		}
		if(songQuery.getFromYear()!=null && songQuery.getToYear()!=null){
			q.field("year").greaterThanOrEq(songQuery.getFromYear());
			q.field("year").lessThanOrEq(songQuery.getToYear());
		}
		List<SongDO> songDOList = q.asList();
		return songDOList;
	}

//	public KeywordEntity getKeyword(String keyword) {
//		return this.datastore.get(KeywordEntity.class, keyword);
//	}
//
//	public void deleteQuestion(String keyword) {
//		this.datastore.delete(KeywordEntity.class, keyword);
//	}
//
//	public Query<KeywordEntity> createQuery() {
//		return this.datastore.createQuery(KeywordEntity.class);
//	}
//
//	public List<QuestionKeywordReference> findReferenceByKeywordList(
//			List<String> keywords) {
//
//		// Query for all of the KeywordQuestion objects associated to the
//		// keywords
//		Query<KeywordEntity> keywordEntityQuery = createQuery().field("_id").hasAnyOf(keywords);
//		List<KeywordEntity> keywordEntites = keywordEntityQuery.asList();
//
//		// Iterate through the keyword entities and build a
//		// QuestionKeywordReference list
//		QuestionKeywordReferenceListBuilder builder = QuestionKeywordReferenceListBuilder
//				.newQuestionKeywordReferenceListBuilder();
//		for (KeywordEntity keywordEntity : keywordEntites) {
//
//			// Get the list of KeywordQuestion objects
//			List<KeywordQuestion> keywordQuestions = keywordEntity
//					.getQuestions();
//
//			// Iterate through the list of KeywordQuestion objects and build the
//			// reference list
//			for (KeywordQuestion keywordQuestion : keywordQuestions) {
//				
//				// Create a reference from the keyword question
//				QuestionKeywordReference questionKeywordReference = QuestionKeywordReference
//						.newQuestionKeywordReference(keywordQuestion);
//				
//				// Add the reference via the builder
//				builder = builder.addQuestionKeywordReference(questionKeywordReference);
//			}
//		}
//
//		// Return the list of references
//		return builder.getQuestionKeywordReferenceList();
//	}
//	
//	public List<String> findKeywordsByQuestionId(int questionId) {
//		
//		
//		// Search for matches on question id using dot notation
//		// Query for all of the KeywordQuestion objects associated to the
//		// keywords
//		Query<KeywordEntity> keywordEntityQuery = createQuery().field("questions.questionId").equal(questionId);
//		List<KeywordEntity> keywordEntites = keywordEntityQuery.asList();
//
//		List<String> keywordList = new ArrayList<String>();
//		for (KeywordEntity keywordEntity : keywordEntites) {
//			keywordList.add(keywordEntity.getKeyword());
//		}
//
//		return keywordList;
//	}

	private SongDao(Datastore datastore) {
		this.datastore = datastore;
	}
}
