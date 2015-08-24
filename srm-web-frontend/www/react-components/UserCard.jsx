
var React = require('react')

var UserCard =  React.createClass( {
  displayName: "UserCard",
  render: function() {
    console.log(this.props)
    return (<div className="ui card visible">
      <div className="image dimmable dimmed">
        <div className="ui blurring inverted dimmer transition animating fade in" >
          <div className="content">
            <div className="center">
              <div className="ui teal button">Hello World</div>
            </div>
          </div>
        </div>
        <img src={this.props.image} />
      </div>
      <div className="content">
        <div className="header">{this.props.name}</div>
        <div className="meta">
          <a className="group">{this.props.groupName}</a>
        </div>
        <div className="description">{this.props.description}</div>
      </div>
      <div className="extra content">
        <a className="right floated created">Arbitrary</a>
        <a className="friends">
          Arbitrary</a>
      </div>
    </div>)
    }
  } 
)

/* React.render(
  <div className="ui four cards visible">
    <UserCard />
    <UserCard />
    <UserCard />
    <UserCard />
    <UserCard />
    <UserCard />
    <UserCard />
  </div>,
  document.getElementById('entity-selector')
); */

module.exports = UserCard;