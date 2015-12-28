package luos.srm.app

import luos.srm.app.config.Configuration
import luos.srm.app.entities.{DataSource, EntityLike, Entity}
import luos.srm.app.events.{CompositeEventList, EventList}
import luos.srm.app.misc.{Id, Identified}

/**
  * Created by luos on 2015.12.26..
  */
class Srm( configuration : Configuration ) {

    def entities : List[EntityLike] = configuration.entities.map( ec => {
        val dataRepos = ec.dataRepositories
        val sources = dataRepos.map( DataSource.fromConfig )
        val eventLists : List[EventList] = sources.map( a => EventList.fromDataSource( a.right.get ) ).map( _.right.get )
        new Entity(Entity.identified( ec.id ), ec.name, ec.description , new CompositeEventList( eventLists ) )
    })

    def findEntityById( id : Id ) : Option[EntityLike] = {
        None
    }

}
