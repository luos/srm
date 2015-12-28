package luos.srm

import akka.actor.Actor
import luos.srm.api.ApiService
import luos.srm.api.ApiJsonProtocol._

/**
 * Created by luos on 2015.08.23..
 */
class WebService extends Actor with WebRoutes {

    def actorRefFactory = context

    def receive = runRoute( route )

    //def a = entityFormat
}

trait WebRoutes extends  FrontendService with ApiService{

    val route = {
        frontendRoute ~ apiRoute
    }
}