package luos.srm.app.config

import luos.srm.app.entities.DataSource

/**
  * Created by luos on 2015.12.26..
  */
case class RepositoryConfig( sourceType : String, values: Map[String,Object] = Map() ) {
    def valueForKey( key : String ) : Option[String] = values.get( key ).map( v => v.asInstanceOf[String] )
    def valuesForKey( key : String ) : List[String] = values.get( key ).map( v => v.asInstanceOf[List[String]] ).getOrElse(List())
}


object RepositoryConfig {
    def directory( path : String ) = {
        RepositoryConfig( DataSource.TYPE_DIRECTORY , Map( "paths" -> List( path ) ) )
    }
}