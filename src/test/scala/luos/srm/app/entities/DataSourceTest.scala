package luos.srm.app.entities

import luos.srm.app.config.RepositoryConfig
import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.26..
  */
class DataSourceTest extends Specification {
    "Given a directory repository config" should {
        "return a data source of directory type" in {
            val config = DataSource.fromConfig( RepositoryConfig( "directory" ) )
            config.right.get.sourceType === DataSource.TYPE_DIRECTORY
            config.right.get must beAnInstanceOf[DirectoryDataSource]

        }
        "given invalid type" in {
            val config = DataSource.fromConfig( RepositoryConfig( "asdfsafd" ) )
            config.left.get must contain( DataSource.ERROR_INVALID_TYPE )
        }

        "given a directory source with paths" in {
            val config = DataSource.fromConfig( RepositoryConfig( DataSource.TYPE_DIRECTORY, Map( "paths" -> List( "/a", "bc" ) ) ) )
            config.right.get.asInstanceOf[DirectoryDataSource].paths === List( "/a", "bc" )

        }
    }
}
