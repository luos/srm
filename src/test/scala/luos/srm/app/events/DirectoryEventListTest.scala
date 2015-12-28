package luos.srm.app.events

import luos.srm.app.entities.DirectoryDataSource
import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.26..
  */
class DirectoryEventListTest extends Specification {

    "DirectoryEventListTest" should {
        val eventList = new DirectoryEventList( new DirectoryDataSource(
            List( "./src/test/resources/events/directory-data-source/yaml/simple/" )
        ) )

        "all" in {
            val event = eventList.all.head
            event.description === "Hello World"
        }

    }

}
