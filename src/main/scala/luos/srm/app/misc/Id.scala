package luos.srm.app.misc

import scala.util.Random

/**
 * Created by luos on 2015.08.23..
 */
case class Id( id : String ) {

}

trait Identified {
    def id : Id
}

object Identified {
    def by( o : Any ): Id = {
        Id( o.getClass.getCanonicalName + "-" + Random.nextInt() )
    }
}