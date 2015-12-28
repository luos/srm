package luos.srm.app.store

import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.28..
  */
class DirectoryStoreTest extends Specification {

    "Given a directory" should {
        val testDirPath = "./src/test/resources/events/directory-data-source/yaml/auto-event/"
        val s: Either[List[String], DirectoryStore] = DirectoryStore.fromPath( testDirPath )
        val store = s.right.get
        "return an entity id specified in a file" in {
            store.name === "Progress Pics"
            store.entity.id === "me"
            store.tags === List("tag1", "sub/tag2")
        }

    }
}
