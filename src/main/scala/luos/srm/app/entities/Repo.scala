package luos.srm.app.entities

import luos.srm.app.misc.Id

/**
 * Created by luos on 2015.08.23..
 */
class EntityRepo {

}

trait EntityStore {
    def save( entity: Entity )
}

trait EntityGateway {
    def get( entityId : Id ): Option[Entity]
    def list() : List[Entity]
}

class InitialEntityGateway extends EntityGateway{

    override def get( entityId : Id ) : Option[Entity] = None

    override def list( ) : List[Entity] = List()
}