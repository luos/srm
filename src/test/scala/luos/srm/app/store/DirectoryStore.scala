package luos.srm.app.store
import java.io.File
import luos.srm.app.store.DirectoryStore.FileEntity
import luos.srm.libs.yaml.{YamlObject, YamlReader}

import collection.JavaConverters._


/**
  * Created by luos on 2015.12.28..
  */
class DirectoryStore( val name: String, val entity: FileEntity, val tags: List[String] ) {

}

object DirectoryStore {
    val ERROR_NOT_A_DIRECTORY = "Path should be a directory"
    val ERROR_MAIN_FILE_MISSING = "Main file should be srm-main.yml in the directory"
    val ERROR_ENTITY_ID_IS_EMPTY = "Entity id is empty"
    val ERROR_ENTITY_OBJECT_IS_MISSING = "Entity object is missing"

    def fromPath( path: String ) : Either[List[String],DirectoryStore] = {
        val f = new File( path )
        if ( f.isDirectory ){

            val files = f.listFiles().toList
            val mainFileOpt = files.filter( _.getName == "srm-main.yml" )
            if ( ! mainFileOpt.isEmpty ) {
                val mainFile = mainFileOpt.head
                val yaml = new YamlReader
                val fileObject : YamlObject = yaml.loadPath( mainFile.getAbsolutePath )
                val entityOpt = fileObject.getObject( "entity" )
                if ( ! entityOpt.isEmpty ){
                    val entity : YamlObject = entityOpt.get
                    val entityId = entity.get("id")
                    if ( entityId.isDefined ){
                        val fileEntity = DirectoryStore.FileEntity( entityId.get.mkString )
                        val tags = fileObject.getList("tags")
                        val sourceName = fileObject.get("name").map( _.mkString ).getOrElse("Unnamed from " + f.getName )
                        Right( new DirectoryStore( sourceName, fileEntity, tags ) )

                    } else {
                        Left( List(ERROR_ENTITY_ID_IS_EMPTY ) )
                    }

                } else {
                    Left( List( ERROR_ENTITY_OBJECT_IS_MISSING ) )
                }
            } else {
                Left( List( ERROR_MAIN_FILE_MISSING ) )
            }
        } else {
            Left( List( ERROR_NOT_A_DIRECTORY ) )
        }
    }

    case class FileEntity( val id : String )
}