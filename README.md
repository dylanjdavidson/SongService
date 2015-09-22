# SongService
This is an example program I developed to get experience using MongoDB. It demonstrates basic document created and document querying through the following classes: 
* [SongDO.java](https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongDO.java): A data object class (a MongoDB entity) that represents the key properties of a song. SongDO objects are uniquely identified in the song database by their url. This class features a factory method for easy SongDO construction.
* [SongDao.java](https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongDao.java)
* [SongQuery.java](https://github.com/dylanjdavidson/SongService/blob/master/src/main/java/com/dylandavidson/service/song/SongQuery.java)
