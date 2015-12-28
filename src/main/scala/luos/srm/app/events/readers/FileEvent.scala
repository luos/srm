package luos.srm.app.events.readers

/**
  * Created by luos on 2015.12.26..
  */
trait FileEvent {
    def description : String
    def imagePaths : List[String]

}
