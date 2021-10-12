function Post(props) {
    return (
        <div className="bg-light bg-gradient">
            <h5>Title: {props.mTitle}</h5>
            <p>Message: {props.mMessage}</p>
            <button className="btn btn-primary"><i class="bi bi-hand-thumbs-up"></i>Like</button>
            <button className="btn btn-primary"><i class="bi bi-hand-thumbs-down"></i>Dislike</button>
            <button className="btn btn-primary">Edit</button>
            <button className="btn btn-primary">Delete</button>
        </div>
    )
}

export default Post;