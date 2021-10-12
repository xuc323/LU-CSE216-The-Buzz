import React from 'react';
import ReactDOM from 'react-dom';
import "./stylesheets/normalize.css";
import './stylesheets/index.css';
import Post from "./scripts/Post.js";
import Navbar from "./scripts/Navbar.js";


function App() {
  return (
    <div>
      <Navbar />
      <div className="container">
        <h1>Posts</h1>
        <div className="container">
          <Post mTitle="HI" mMessage="Hello" />
          <Post mTitle="HI" mMessage="Hello" />
        </div>
      </div>
    </div>
  );
}

function Avatar(props) {
  return (
    <img className="Avatar" src={props.user.avatarUrl} alt={props.user.name} />
  );
}

function UserInfo(props) {
  return (
    <div className="UserInfo">
      <Avatar user={props.user} />
      <div className="UserInfo-name">
        {props.user.name}
      </div>
    </div>
  )
}

ReactDOM.render(
  <App />,
  document.getElementById('root')
);