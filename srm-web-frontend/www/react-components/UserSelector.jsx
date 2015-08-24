
var React = require('react')
var UserCard = require( './UserCard' );

var UserSelector =  React.createClass( {
  displayName: "UserSelector",
  render: function() {
    var entities = this.props.entities.map( function(){ return <UserCard 
      name="hello" 
      groupName="Entity" 
      description="One or two sentence description that may go to several lines" 
      image="http://gasztro-tv.hu/wp-content/uploads/2014/10/dinnye.jpg"/> 
    })
    return (
      <div className="ui four cards visible">
        {entities}
      </div>
  )
    }
  } 
)

module.exports = UserSelector;