/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	/** @jsx React.DOM *//** @jsx React.DOM */

	var React = __webpack_require__(1)

	var UserSelector = __webpack_require__(2)
	var entities = [1,2,3];

	React.render(React.createElement(UserSelector, {entities: entities}), document.getElementById('view-container'))



/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = React;

/***/ },
/* 2 */
/***/ function(module, exports, __webpack_require__) {

	/** @jsx React.DOM */
	var React = __webpack_require__(1)
	var UserCard = __webpack_require__( 3 );

	var UserSelector =  React.createClass( {
	  displayName: "UserSelector",
	  render: function() {
	    var entities = this.props.entities.map( function(){ return React.createElement(UserCard, {
	      name: "hello", 
	      groupName: "Entity", 
	      description: "One or two sentence description that may go to several lines", 
	      image: "http://gasztro-tv.hu/wp-content/uploads/2014/10/dinnye.jpg"}) 
	    })
	    return (
	      React.createElement("div", {className: "ui four cards visible"}, 
	        entities
	      )
	  )
	    }
	  } 
	)

	module.exports = UserSelector;

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	/** @jsx React.DOM */
	var React = __webpack_require__(1)

	var UserCard =  React.createClass( {
	  displayName: "UserCard",
	  render: function() {
	    console.log(this.props)
	    return (React.createElement("div", {className: "ui card visible"}, 
	      React.createElement("div", {className: "image dimmable dimmed"}, 
	        React.createElement("div", {className: "ui blurring inverted dimmer transition animating fade in"}, 
	          React.createElement("div", {className: "content"}, 
	            React.createElement("div", {className: "center"}, 
	              React.createElement("div", {className: "ui teal button"}, "Hello World")
	            )
	          )
	        ), 
	        React.createElement("img", {src: this.props.image})
	      ), 
	      React.createElement("div", {className: "content"}, 
	        React.createElement("div", {className: "header"}, this.props.name), 
	        React.createElement("div", {className: "meta"}, 
	          React.createElement("a", {className: "group"}, this.props.groupName)
	        ), 
	        React.createElement("div", {className: "description"}, this.props.description)
	      ), 
	      React.createElement("div", {className: "extra content"}, 
	        React.createElement("a", {className: "right floated created"}, "Arbitrary"), 
	        React.createElement("a", {className: "friends"}, 
	          "Arbitrary")
	      )
	    ))
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

/***/ }
/******/ ]);