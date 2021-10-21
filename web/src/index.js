import React from 'react';
import ReactDOM from 'react-dom';
import "./stylesheets/normalize.css";
import Post from "./scripts/Post.js";
import { LoginBtn } from './scripts/User.js';
import Navbar from "./scripts/Navbar.js";
import Clock from './scripts/Clock';

// main function to layour each components
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

// render the main app and add to id=root
ReactDOM.render(
  <App />,
  document.getElementById('root')
);