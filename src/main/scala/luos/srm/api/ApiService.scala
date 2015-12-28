package luos.srm.api

import akka.actor.Actor
import luos.srm.app.entities.Entity
import luos.srm.app.misc.Id
import spray.json.{RootJsonFormat, JsonFormat, DefaultJsonProtocol}
import spray.routing.HttpService




case class TestClass( a : Id, b: Entity )
case class EntityList( entities : Seq[Entity] )

case class SimpleTestClass( i : Int)

object ApiJsonProtocol extends DefaultJsonProtocol {
    implicit val idFormat: JsonFormat[luos.srm.app.misc.Id] = jsonFormat1( luos.srm.app.misc.Id.apply )
   // implicit val entityListFormat: RootJsonFormat[EntityList] = jsonFormat1( EntityList )
    //implicit val entityFormat: JsonFormat[luos.srm.app.entities.Entity] = jsonFormat5( luos.srm.app.entities.Entity.apply )
    //implicit val testClassFormat = jsonFormat2(TestClass)
    implicit  val simpleFormat : RootJsonFormat[SimpleTestClass]= jsonFormat1(SimpleTestClass)
    implicit def listResult[A :JsonFormat] = jsonFormat( (a: List[A], b : Boolean) => ListResult(a) , "results", "success" )
    // implicit def simpleResult[A :JsonFormat] = jsonFormat( (a: A, b: Boolean) => Result.apply(a) , "success" , "result" )

}

class ApiServiceActor extends Actor with ApiService {
    def actorRefFactory = context
    def receive = runRoute(apiRoute)

}

trait Successful {
    val success : Boolean = true
}

trait Failure {
    val success = false
    def errors : List[String]
}

case class ListResult[T]( results: List[T] ) extends Successful {

}

case class Result[T]( result : T ) extends Successful

trait ApiService extends HttpService{
    import ApiJsonProtocol._
    import spray.httpx.SprayJsonSupport._

    val apiRoute = {
        pathPrefix( "api" ) {
            path("entities") {
                get {
                    complete {
                         ListResult( List(1,3,4))
                    }
                }
            }

        }
    }

}