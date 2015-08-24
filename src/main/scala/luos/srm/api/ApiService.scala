package luos.srm.api

import akka.actor.Actor
import luos.srm.app.entities.Entity
import luos.srm.app.misc.Id
import spray.json.{RootJsonFormat, JsonWriter, DefaultJsonProtocol, JsonFormat}
import spray.routing.HttpService

case class TestClass( a : Id, b: Entity )
case class EntityList( entities : Seq[Entity] )

object ApiJsonProtocol extends DefaultJsonProtocol {
    implicit val idFormat: JsonFormat[luos.srm.app.misc.Id] = jsonFormat1( luos.srm.app.misc.Id.apply )
    implicit val entityListFormat: RootJsonFormat[EntityList] = jsonFormat1( EntityList )
    implicit val entityFormat: JsonFormat[luos.srm.app.entities.Entity] = jsonFormat3( luos.srm.app.entities.Entity.apply )
    implicit val testClassFormat = jsonFormat2(TestClass)
    implicit def listResult[A :JsonFormat] = jsonFormat1(ListResult.apply[A])
}

class ApiServiceActor extends Actor with ApiService {
    def actorRefFactory = context
    def receive = runRoute(apiRoute)

}

trait Successfull {}

case class Failure()

case class ListResult[T]( results: List[T] )

case class Result[T]( result : T )

trait ApiService extends HttpService{
    import spray.json._
    import ApiJsonProtocol._
    import spray.httpx.SprayJsonSupport._


    val apiRoute = {
        pathPrefix( "api" ) {
            path("entities") {
                complete({
                    val e = luos.srm.app.entities.Entity( Id( "hell o" ), "Entiyy name", "entity descrptipn" )
                    ListResult( List( e, e, e) )
                })
            }

        }
    }

}