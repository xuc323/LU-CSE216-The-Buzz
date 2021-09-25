import * as React from 'react'

type PostState = { name: string };

/**
 * The Post has two properties: lambdas for getting and setting the
 * value
 */
type PostProps = {
    setNum: (num: number) => void;
    setDis: (num: number) => void;
}
var dis = 0;
var like = 0;

export class Post extends React.Component<PostProps, PostState> {
    
    increment = (_e: React.MouseEvent<HTMLButtonElement>) => {
        this.props.setNum(like++);
    }
    
    increment_dislike = (_e: React.MouseEvent<HTMLButtonElement>) => {
        this.props.setDis(dis++);
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
                
                <input type="text" onChange={this.handleChange} value={this.state.name} />
                <button onClick={this.increment}> Like
                    <span>{" "+like}</span>
                </button>
                <button onClick={this.increment_dislike}> Dislike
                    <span>{" "+dis}</span>
                </button>
            </div>
            
        );
    }
};

