package luos.srm.app

import luos.srm.app.config.{RepositoryConfig, SimpleEntityConfig, EntityConfig, Configuration}
import luos.srm.app.entities.DirectoryDataSource
import luos.srm.app.misc.Id
import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.26..
  */
class SrmTest extends Specification {
    "Staring the application".txt

    "Application should take a Configuration class" >> {

        "entities can be specified in the configuration" >> {
            val appConfig = new Configuration {
                override def entities: List[EntityConfig] = List()
            }
            val app = new Srm(appConfig)
            app.entities.size === 0
        }

        "adding an entity to the config, makes it available in the app" >> {
            val appConfig = new Configuration {
                override def entities: List[EntityConfig] = List(
                        new SimpleEntityConfig( name = "Jonathan", id = "thisistheid", description = "desc" )
                )
            }
            val app = new Srm(appConfig)
            app.entities.size === 1
            app.entities.head.name === "Jonathan"
            app.entities.head.id === Id( "entity-thisistheid" )
            app.entities.head.description === "desc"

        }

        "I can specify a folder for an entity where related things can be stored" >> {
            val appConfig = new Configuration {
                override def entities: List[EntityConfig] = List(
                    new SimpleEntityConfig( name = "Jonathan", id = "thisistheid", description = "desc" ) {
                        override def dataRepositories: List[RepositoryConfig] = {
                            List(
                                RepositoryConfig.directory( "./src/test/resources/events/directory-data-source/yaml/simple/srm-event1.yml" )
                            )
                        }
                    }
                )
            }
            val app = new Srm(appConfig)
            val firstEntity = app.entities.head
            ok
            /*val events = firstEntity.events.all
            val event = events.head
            val images = event.images
            images.length === 2
            images must contain( "image1" )
            images must contain( "image2" ) */

        }


    }
}
