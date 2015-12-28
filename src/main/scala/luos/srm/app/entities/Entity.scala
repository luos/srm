package luos.srm.app.entities

import luos.srm.app.events.EventList
import luos.srm.app.misc.{Identified, Id}

/**
 * Created by luos on 2015.08.23..
 */
case class Entity( id: Id, name: String, description: String, events: EventList = EventList.empty, dataSources: List[DataSource] = List() ) extends EntityLike

trait EntityLike extends Identified{
    def id: Id
    def name : String
    def description : String
    def events : EventList
    def dataSources : List[DataSource]
}

object Entity {
    def identified( ids : String ) : Id = Id( "entity-" + ids )
}
