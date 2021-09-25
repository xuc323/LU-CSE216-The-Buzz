import * as React from 'react'

type PostState = { name: string };

/**
 * The Post has two properties: lambdas for getting and setting the
 * value
 */
type PostProps = {
    getNum: () => number;
    setNum: (num: number) => void;
    getDis: () => number;
    setDis: (num: number) => void;
}
var dis = 0;

export class Post extends React.Component<PostProps, PostState> {
    
    increment = (_e: React.MouseEvent<HTMLButtonElement>) => {
        this.props.setNum(1 + this.props.getNum());
    }
    
    increment_dislike () {
        dis++;
    }
    /** State will consist of a single string */
    state = { name: "" }
    
    /**
     * This lambda will run any time its associated input element changes.  Its
     * job is to update the state with the value of the input element.
     */
    handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({ name: e.target.value });
    }

    /** Render the component */
    render() {
        return (
            <div>
                <p>
                    Posting message:  {} {this.state.name} {}
                </p>
                <p>
                    The current like is {this.props.getNum()}.
                </p>
                <p>
                    The current dislike is {dis}.
                </p>
                
                <input type="text" onChange={this.handleChange} value={this.state.name} />
                <button onClick={this.increment}> Like</button>
                <button onClick={this.increment_dislike}> Dislike</button>

                
            </div>
            
        );
    }
};