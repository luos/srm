package luos.srm.app.events.readers
import scala.collection.JavaConversions._


import java.io.File
import java.util

import luos.srm.app.events.Event
import org.yaml.snakeyaml.Yaml

import scala.io.Source

/**
  * Created by luos on 2015.12.26..
  */
class YamlReader {

    val yaml = new Yaml()

    def readFile( file : File ) : Either[String, List[FileEvent]] = {
        val contents = yaml.load( Source.fromFile(  file ).mkString ).asInstanceOf[java.util.Map[String, Any]]
        val desc = contents.get( "description" ).asInstanceOf[String]
        val imgPaths = contents.get( "images" ).asInstanceOf[util.ArrayList[String]].map(_.trim).toList

        Right( List( new FileEvent {
            override def description: String = desc

            override def imagePaths: List[String] = imgPaths
        }) )
    }

}

