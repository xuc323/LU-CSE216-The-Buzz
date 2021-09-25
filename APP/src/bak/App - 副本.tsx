import * as React from "react";
import { HashRouter as Router, Route, Link, Switch } from 'react-router-dom';
import { Hello } from "./Hello";
import {Url} from "./Url";
import {Counter} from "./Counter";
import {GlobalCounter} from "./GlobalCounter";
import {Net} from "./Net";
import {TwoWay} from "./TwoWay";
import {Post} from "./Post";
/** App has one property: a number */
type AppProps = { num: number }
export class App extends React.Component<AppProps> {
    /** The global state for this component is a counter */
    state = { num: 0 };
    
    /**
     * When the component mounts, we need to set the initial value of its
     * counter
     */
    componentDidMount = () => { this.setState({ num: this.props.num }); }

    /** Get the current value of the counter */
    getNum = () => this.state.num;

    /** Set the counter value */
    setNum = (num: number) => this.setState({ num });

    /** render the component */
    render() {
        return (
            <Router>
                <div>
                    <nav>
                        <Link to="/">Hello (1)</Link>
                        &nbsp;|&nbsp;
                        <Link to="/hello">Hello (2)</Link>
                        <Link to="/url/1">Url (1)</Link>
                        &nbsp;|&nbsp;
                        <Link to="/url/2">Url (2)</Link>
                        <Link to="/counter">Counter</Link>
                        <Link to="/globalcounter">Global Counter</Link>
                        <Link to="/net">Network</Link>
                        <Link to="/twoway">Two-Way</Link>
                        <Link to="/post">Post</Link>
                    </nav>
                    <Switch>
                        <Route exact path="/" component={Hello} />
                        <Route exact path="/hello" render={() => <Hello message={"There"} />} />
                        <Route path="/url/:num" component={Url} />
                        <Route exact path="/counter" component={Counter} />
                        <Route exact path="/globalcounter" component={GlobalCounter} />
                        <Route exact path="/net" component={Net} />
                        <Route exact path="/twoway" component={TwoWay} />
                        <Route exact path="/post" render={() => <Post getNum={this.getNum} setNum={this.setNum}
                        getDis={this.getNum} setDis={this.setNum}/>} />
                    </Switch>
                    <div>
                        The global like is {this.state.num}
                        
                    </div>
                    <div>
                        The global dislike is {this.state.num}
                    </div>
                </div>
            </Router>
        );
    }
}

// div before global counter: &copy; 2021 &mdash;

