function Post(props) {
    return <div>
        <h5>Title: {props.mTitle}</h5>
        <p>Message: {props.mMessage}</p>
        <button className="btn btn-primary">Click!</button>
    </div>
}

export default Post;