# SongService
This is an example program I developed to get experience using MongoDB. It demonstrates basic document created and document querying through the following classes: 
* [SongDO.java][SongDO]: A data object class (a MongoDB entity) that represents the key properties of a song. SongDO objects are uniquely identified in the song database by their url. This class features a factory method for easy SongDO construction.
* [SongDao.java][SongDao]: A data access object class which provides all the key operations to insert and retrieve data from the database.

 I used a [MongoLab](https://mongolab.com) sandbox database instance to manage the collection of songs used for testing. 
 
 I used a local property file to hold the database name, username, and password to avoid checking them into GitHub.
* [SongQuery.java][SongQuery]: An object that represents all the variable data used to query the database for songs.

 It supports a definition of any combination of the following song characteristics:
 * Date/date range
 * Song Name(s)
 * Artist(s)
 * Genre(s)
 * Original (this is specific to my dad's youtube channel where some songs are originals while others are covers)

* [SongDaoTest](https://github.com/dylanjdavidson/SongService/blob/master/src/test/java/com/dylandavidson/service/song/SongDaoTest.java): Performed tests to verify the SongDao operations and in turn verified SongDO and SongQuery.

[SongDO]: https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongDO.java
[SongDao]: https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongDao.java
[SongQuery]: https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongQuery.java
