package luos.srm.app.entities

import luos.srm.app.misc.{Identified, Id}

/**
 * Created by luos on 2015.08.23..
 */
case class Entity( id: Id, name : String, description: String ) extends EntityLike

trait EntityLike extends Identified{
    def id: Id
    def name : String
    def description : String
}

