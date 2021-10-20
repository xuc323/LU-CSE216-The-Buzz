import React from 'react';
import ReactDOM from 'react-dom';
import "./stylesheets/normalize.css";
import Post from "./scripts/Post.js";
import { LoginBtn } from './scripts/User.js';
import Navbar from "./scripts/Navbar.js";

class App extends React.Component {
  render() {
    return (
      <div>
        <Navbar />
        <div className="container">
          <h1>The Buzz App</h1>
          <Clock />
          <LoginBtn />
          <Post />
        </div>
      </div>
    );
  }
}

class Clock extends React.Component {
  constructor(props) {
    super(props);
    this.state = { date: new Date() };
  }

  componentDidMount() {
    this.timerID = setInterval(() => this.tick(), 1000);
  }

  componentWillUnmount() {
    clearInterval(this.timerID);
  }

  tick() {
    this.setState({
      date: new Date()
    });
  }

  render() {
    return (
      <div>
        <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
      </div>
    );
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('root')
);