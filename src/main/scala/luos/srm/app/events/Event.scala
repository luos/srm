package luos.srm.app.events

import luos.srm.app.misc.Id

/**
  * Created by luos on 2015.12.26..
  */
trait Event {
    def id : Id
    def description : String
    def images : List[Image]
}
