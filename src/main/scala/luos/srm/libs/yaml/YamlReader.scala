package luos.srm.libs.yaml

import java.io.{FileInputStream, InputStream, File}
import java.util
import scala.collection.JavaConverters._

import org.yaml.snakeyaml.Yaml

/**
  * Created by luos on 2015.12.26..
  */
class YamlReader {
    val yaml = new Yaml()

    def loadString( path : String) : YamlObject = {
        val obj = yaml.load( path )
        new YamlObject( obj )
    }

    def loadPath( path: String ): YamlObject = {
        val f = new File(path)
        val obj = yaml.load( new FileInputStream( f ) )
        new YamlObject( obj )
    }


}

class YamlObject( obj : Object ) {
    def getObject( s: String ) = {
        val value: Object = asMap.get( s )
        if ( value != null ) {
            Some( new YamlObject( value ) )
        } else {
            None
        }
    }

    def getObjectList( s: String ) : List[YamlObject] = asMap.get( s ).asInstanceOf[util.List[ util.Map[String,Object]]].asScala.toList.map( new YamlObject(_) )

    if ( obj == null ){
        throw  new NullPointerException
    }

    def get( key : String ) : Option[YamlValue] = {
        try{
            val value = asMap.get( key )
            YamlValue(value)
        }catch {
            case e : Exception => {
                System.err.println( e )
                None
            }
        }

    }

    def getList( key : String ) : List[String] = {
        val valueList: util.List[String] = asMap.get( key ).asInstanceOf[util.List[String]]
        if ( valueList != null )  {
            valueList.asScala.toList.map( a => a.asInstanceOf[String] )
        } else {
            List()
        }
    }

    def asMap = obj.asInstanceOf[java.util.Map[String,Object]]
}

abstract class YamlValue() {
    def mkString : String
}
case class IntValue( value : Int ) extends YamlValue {
    override def mkString: String = value.toString
}
case class StringValue( value : String ) extends YamlValue{
    override def mkString: String = value
}

object YamlValue {
    def apply( o : Object ) : Option[YamlValue] = {
        o match {
            case o : java.lang.Integer => Option( IntValue( o.toInt ) )
            case o : java.lang.String => Option( StringValue(o.asInstanceOf[String ]) )
            case other => System.err.println("Yaml Value Invalud ", other ); None
        }
    }
}

