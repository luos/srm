/** @jsx React.DOM */

var React = require('react')

var UserSelector = require("./UserSelector")
var entities = [1,2,3];

React.render(<UserSelector entities={entities} />, document.getElementById('view-container'))

