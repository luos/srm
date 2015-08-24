package luos.srm

import spray.routing._


trait FrontendService extends HttpService {

  val frontendRoute = {
        pathPrefix( "web" ) {
            getFromDirectory("srm-web-frontend/www")
        }
    }
}