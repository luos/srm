package luos.srm.app.events

import luos.srm.app.entities.DirectoryDataSource
import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.26..
  */
class EventList$Test extends Specification {

    "EventList$Test" should {
        "fromDataSource" in {
            val source = EventList.fromDataSource( new DirectoryDataSource( List( "abc/def", "hi/jkl" ) ) )
            source.right.get must beAnInstanceOf[DirectoryEventList]
        }

    }
}
