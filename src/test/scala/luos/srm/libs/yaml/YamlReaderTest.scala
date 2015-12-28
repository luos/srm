package luos.srm.libs.yaml

import org.specs2.mutable.Specification

/**
  * Created by luos on 2015.12.26..
  */
class YamlReaderTest extends Specification {

    def reader = new YamlReader

    "YamlReader" should {
        "load a simple object" in {
            val yamlObject = reader.loadString(
                """
                  |name: hello
                  |kakao: makao
                  |bub: 1
                """.stripMargin)
            yamlObject.get( "name" ).get  === StringValue( "hello" )
            yamlObject.get( "kakao" ).get  === StringValue( "makao" )
            yamlObject.get( "bub" ).get  === IntValue( 1 )
            yamlObject.get( "nonexisting" ) === None

        }

        "load two lists object" in {
            val fileContent: String =
                """
                  |name: kakao
                  |list:
                  |  - listelem1
                  |  - listelem2
                  |list2:
                  |  - listelem3
                  |  - listelem4
                  |
                """.stripMargin
            println(fileContent)
            val yamlObject = reader.loadString( fileContent )
            yamlObject.getList( "list" ) must beEqualTo( List( "listelem1", "listelem2") )
            yamlObject.getList( "list2" ) must beEqualTo( List( "listelem3", "listelem4") )
        }

        "load a simple object array" in {
            val fileContent: String =
                """
                  |people:
                  |  - name: Jonathan
                  |    age: 123
                  |  - name: Bill
                  |    age: 200
                  |list2:
                  |  - listelem3
                  |  - listelem4
                  |
                """.stripMargin
            println(fileContent)
            val yamlObject = reader.loadString( fileContent )

            val people: List[YamlObject] = yamlObject.getObjectList( "people" )

            val jonathan: YamlObject = people.head

            jonathan.get("name").get must beEqualTo( StringValue( "Jonathan" ) )
            jonathan.get("age").get must beEqualTo( IntValue( 123 ) )

            val bill: YamlObject = people( 1 )
            bill.get("name").get must beEqualTo( StringValue( "Bill") )
            bill.get("age").get.mkString must beEqualTo(  "200"  )
        }

        "load a simple non root object" in {
            val fileContent : String =
                """
                  |entity:
                  |  name: Jonathan
                  |  age: 200
                  |more: 1
                  |blabla: 2
                """.stripMargin
            val obj = reader.loadString( fileContent )
            val entity = obj.getObject( "entity" ).get
            entity.get("name").get === StringValue("Jonathan" )
            //entity.get("age").get === IntValue( 200 )
        }

    }
}
