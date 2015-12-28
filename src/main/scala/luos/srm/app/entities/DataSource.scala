package luos.srm.app.entities

import luos.srm.app.config.RepositoryConfig

/**
  * Created by luos on 2015.12.26..
  */
trait DataSource {
    def sourceType : String
}

object DataSource {
    val TYPE_DIRECTORY = "directory"
    val ERROR_INVALID_TYPE = "error-invalid-type"

    def fromConfig( repositoryConfig: RepositoryConfig ): Either[List[String], DataSource ] = {
        if ( repositoryConfig.sourceType == TYPE_DIRECTORY ) {
           Right( new DirectoryDataSource( repositoryConfig.valuesForKey( "paths" ) ) )
        } else {
            Left( List( ERROR_INVALID_TYPE ) )
        }
    }


}

class DirectoryDataSource(
                         val paths : List[String]
                         ) extends DataSource{
    override def sourceType: String = DataSource.TYPE_DIRECTORY
}
