package luos.srm.app.events.readers

import java.io.File

import org.specs2.mutable.Specification

import scala.io.Source

/**
  * Created by luos on 2015.12.26..
  */
class YamlReaderTest extends Specification {
    val reader = new YamlReader
    "Reading a Yaml file" should {
        "read an event from a sipmple file" in {
            val file = new File("./src/test/resources/events/directory-data-source/yaml/simple/srm-event1.yml" )
            val result = reader.readFile( file ).right.get.head
            result.description === "Hello World"
            result.imagePaths must beEqualTo( List( "image1" , "image2" ) )
        }
    }
}
