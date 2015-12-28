package luos.srm.app.config

/**
  * Created by luos on 2015.12.26..
  */
trait EntityConfig {
    def id: String
    def name: String
    def description: String

    def dataRepositories: List[RepositoryConfig]
}

case class SimpleEntityConfig(
                             val id : String,
                             val name : String,
                             val description : String = ""
                             ) extends EntityConfig {

    override def dataRepositories: List[RepositoryConfig] = List()
}
