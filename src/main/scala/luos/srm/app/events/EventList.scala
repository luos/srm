package luos.srm.app.events

import java.io.File

import luos.srm.app.entities.{DirectoryDataSource, DataSource}
import luos.srm.app.events.readers.{FileEvent, YamlReader}
import luos.srm.app.misc.{Identified, Id}

/**
  * Created by luos on 2015.12.26..
  */
trait EventList {

    def all : List[Event]
}


object EventList {

    def empty = new EventList {
        override def all: List[Event] = List()
    }

    def fromDataSource( dataSource: DataSource ): Either[List[String],EventList] = {
        dataSource match {
            case directory : DirectoryDataSource => Right( new DirectoryEventList(directory) )
            case _ => throw new Exception("Invalid data source type"  + dataSource.sourceType + " : " + dataSource.getClass )
        }
    }
}

class CompositeEventList( eventLists: List[EventList] ) extends EventList{
    override def all: List[Event] = eventLists.flatMap( _.all )
}

class DirectoryEventList( directoryDataSource: DirectoryDataSource ) extends EventList {
    override def all: List[Event] = directoryDataSource.paths.flatMap( path => {
        val reader = new YamlReader
        val dir : java.io.File = new File( path )
        val files : List[File] = if ( dir.isDirectory ) dir.listFiles().filter( f => f.getName.endsWith(".yml" ) ).toList else List(dir)
        val events : List[FileEvent] = files.flatMap( f => reader.readFile( f ).right.get.toList )
        events.map( fileEventToEvent )
    })

    def fileEventToEvent( fileEvent: FileEvent ): Event = {
        new Event {
            override def images: List[Image] = List()

            override def description: String = fileEvent.description

            override def id: Id = Identified.none
        }
    }
}