import React from "react";
import axios from 'axios';
import ReactDOM from 'react-dom';
import "./stylesheets/normalize.css";
import Post from "./scripts/Post.js";
import { LoginBtn } from './scripts/User.js';
import Navbar from "./scripts/Navbar.js";
import Clock from './scripts/Clock';
//import FlagContent from './scripts/FlagContent.js';

// main function to layour each components
class App extends React.Component {
  // Phase 4: implementing translation
  constructor(props){
    super(props);

    this.state = {
      value: '',
      translated: '...'
    }
    this.translate=this.translate.bind(this);
  }

  translate(){
    axios.post('postgres://pkdkdvttlfzyfu:6368fa21b4ffd5891b25a4700c6ee3e85350bec637fd33aabd57879c6b97efe1@ec2-3-225-204-194.compute-1.amazonaws.com:5432/d7oeuj2oslhi4l',{q:this.state.value})
    .then(data => {
      this.setState({translated: data.data.data.translations[0].translatedText})
      console.log(data.data.data.translations[0].translatedText)
    }).catch(err => {
      console.log('error translating text')
    })
  }

  render() {
    return (
      <div>
        <Navbar />
        <div className="container">
          <h1>The Buzz App</h1>
          <Clock />
          <LoginBtn />
          <Post />
          <input 
          value={this.state.value}
          onChange={(e)=>this.setState({value: e.target.value})}
          type="text"/>
        <button onClick={this.translate}>Translate</button>
        <h1>{this.state.translated}</h1>
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

// Phase 4: tried using CORS for connection with backend
/*
var cors = require('cors')
app.use(cors())
*/