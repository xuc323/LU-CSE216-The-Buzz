import React from 'react';
import { Avatar } from './User';

// on memory database
let POSTS = [{
    "mId": 50,
    "mTitle": "Test Title",
    "mMessage": "Test Message1",
    "mLikes": 48,
    "mDislikes": 10,
    "uId": 29,
    "uName": "Judy",
    "uEmail": "judy@lehigh.edu",
    "uUrl": "https://randomuser.me/api/portraits/women/3.jpg",
    "mComments": [
        {
            "cContent": "Comment1?",
            "cId": 2,
            "uId": 4
        },
        {
            "cContent": "Comment1?",
            "cId": 9,
            "uId": 4
        }
    ],
    "mDate": "Sep 27, 2021"
},
{
    "mId": 111,
    "mTitle": "Test Title1",
    "mMessage": "Test Message1",
    "mLikes": 0,
    "mDislikes": 0,
    "uId": 30,
    "uName": "Andy",
    "uEmail": "andy@lehigh.edu",
    "uUrl": "https://randomuser.me/api/portraits/women/10.jpg",
    "mComments": [
        {
            "cContent": "Comment1?",
            "cId": 2,
            "uId": 4
        },
        {
            "cContent": "Comment1?",
            "cId": 9,
            "uId": 4
        }
    ],
    "mDate": "Oct 17, 2021"
},
{
    "mId": 33,
    "mTitle": "More rocksjQuery36007659862288472796_1632775019944",
    "mMessage": "What a glorious day!!",
    "mLikes": 16,
    "mDislikes": 7,
    "mComments": [],
    "uId": 50,
    "uName": "Lily",
    "uEmail": "lily@lehigh.edu",
    "uUrl": "https://randomuser.me/api/portraits/women/6.jpg",
    "mDate": "Sep 27, 2021"
},
{
    "mId": 80,
    "mTitle": "hello",
    "mMessage": "yeah",
    "mLikes": 3,
    "mDislikes": 9,
    "uId": 38,
    "uName": "Lamda",
    "uEmail": "lamda@lehigh.edu",
    "uUrl": "https://randomuser.me/api/portraits/women/1.jpg",
    "mComments": [
        {
            "cContent": "Comment1?",
            "cId": 2,
            "uId": 4
        },
        {
            "cContent": "Comment1?",
            "cId": 9,
            "uId": 4
        },
        {
            "cContent": "Comment1?",
            "cId": 2,
            "uId": 4
        },
        {
            "cContent": "Comment1?",
            "cId": 9,
            "uId": 4
        }
    ],
    "mDate": "Sep 28, 2021"
}];

// method to generate HTML for all posts. Can be selected by id=messages
class Post extends React.Component {
    constructor(props) {
        super(props);
        this.state = { data: [] };
    }

    // LIKE AND DISLIKE POSTS
    // like button will increment the like count on screen as well as database
    async handleLike(mId) {
        const url = "/messages/" + mId + "/like?session_key=" + localStorage.getItem("sessionKey");
        // const url = "http://localhost:4567/messages" + mId + "/like";
        // console.log(mId);
        try {
            const response = await fetch(url, {
                method: "PUT",
            });
            const json = await response.json();
            console.log(json);
            // if the status from the server is ok
            if (json.mStatus === "ok") {
                const data = this.state.data;
                for (let i = 0; i < data.length; i++) {
                    if (data[i].mId === mId) {
                        console.log(data[i]);
                        data[i].mLikes++;
                        break;
                    }
                }
                // set the data to state so the change will show up without refreshing the page
                this.setState({ data: data });
            } else {
                // status is error, show warning
                window.alert("Liking message failed.");
            }
        } catch (err) {
            console.log(err);
        }
    }

    // dislike button will increment the dislike count on screen as well as database
    async handleDislike(mId) {
        const url = "/messages/" + mId + "/dislike?session_key=" + localStorage.getItem("sessionKey");
        // const url = "http://localhost:4567/messages" + mId + "/dislike";
        console.log(mId);
        try {
            const response = await fetch(url, {
                method: "PUT",
            });
            const json = await response.json();
            console.log(json);
            // if the status from the server is ok
            if (json.mStatus === "ok") {
                const data = this.state.data;
                for (let i = 0; i < data.length; i++) {
                    if (data[i].mId === mId) {
                        console.log(data[i]);
                        data[i].mDislikes++;
                        break;
                    }
                }
                // set the data to state so the change will show up without refreshing the page
                this.setState({ data: data });
            } else {
                // status is error, show warning
                window.alert("Disliking message failed.");
            }
        } catch (err) {
            console.log(err);
        }
    }

    // ADDING MESSAGES
    // add message button, show the form to add message
    handleAddClick() {
        document.getElementById("messages").hidden = true;
        document.getElementById("newEntryForm").hidden = false;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("addCommentForm").hidden = true;
    }

    // handle submit button for adding message form
    async handleAddSubmit() {
        const titleField = document.getElementById("newTitle");
        const messageField = document.getElementById("newMessage");
        const url = "/messages?session_key=" + localStorage.getItem("sessionKey");
        // const url = "http://localhost:4567/messages";
        try {
            const response = await fetch(url, {
                method: "POST",
                body: JSON.stringify({
                    mTitle: titleField.value,
                    mMessage: messageField.value
                })
            });
            const json = await response.json();
            console.log(json)
            if (json.mStatus === "ok") {
                window.location.reload(true);
            } else {
                window.alert("Adding message failed.");
            }
        } catch (err) {
            console.log(err);
        }
    }

    // cancel will return to the main page. TODO: think about clearing the form or not
    handleAddCancel() {
        document.getElementById("messages").hidden = false;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("addCommentForm").hidden = true;
    }

    // EDITING MESSAGE
    // edit message button
    handleEdit(mId) {
        console.log(mId);
        document.getElementById("messages").hidden = true;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = false;
        document.getElementById("addCommentForm").hidden = true;

        // get content from edit message form
        let titleField = document.getElementById("editTitle");
        let messageField = document.getElementById("editMessage");
        const data = this.state.data;
        let orgTitle;
        let orgMessage;
        for (let i = 0; i < data.length; i++) {
            if (data[i].mId === mId) {
                console.log(data[i]);
                orgTitle = data[i].mTitle;
                orgMessage = data[i].mMessage;
                break;
            }
        }
        // fill the forms with original messages
        titleField.value = orgTitle;
        messageField.value = orgMessage;

        // add click function to the submit button
        document.getElementById("editSubmit").onclick = async function () {
            const url = "/messages/" + mId + "?session_key=" + localStorage.getItem("sessionKey");
            // const url = "http://localhost:4567/messages" + mId;
            try {
                const response = await fetch(url, {
                    method: "PUT",
                    body: JSON.stringify({
                        mMessage: messageField.value
                    })
                });
                const json = await response.json();
                console.log(json)
                if (json.mStatus === "ok") {
                    window.location.reload(true);
                } else {
                    window.alert("Editing message failed.");
                }
            } catch (err) {
                console.log(err);
            }
        }
    }

    // cancel editing and return to main page. TODO: clear the form?
    handleEditCancel() {
        document.getElementById("messages").hidden = false;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("addCommentForm").hidden = true;
    }

    // delete the message
    async handleDelete(mId) {
        const url = "/messages/" + mId;
        // const url = "http://localhost:4567/messages" + mId;
        try {
            const response = await fetch(url, {
                method: "DELETE",
            });
            const json = await response.json();
            console.log(json);
            if (json.mStatus === "ok") {
                window.location.reload(true);
            } else {
                window.alert("Deleting message failed");
            }
        } catch (err) {
            console.log(err);
        }
    }

    handleCommentCancel() {
        document.getElementById("messages").hidden = false;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("addCommentForm").hidden = true;
    }

    // when the DOM got added, fetch all messages
    async componentDidMount() {
        const url = "/messages";
        // const url = "http://localhost:4567/messages";
        try {
            const response = await fetch(url, {
                method: "GET"
            });
            const json = await response.json();
            if (json.mStatus === "ok") {
                this.setState({ data: json.mData });
            } else {
                window.alert("Can't fetch new messages.");
            }
        } catch (err) {
            console.log(err);
        }
        // this.setState({ data: POSTS });
    }

    handleAddCommentClick() {
        document.getElementById("messages").hidden = true;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("addCommentForm").hidden = false;
    }

    handleAddComment(mId) {
        console.log(mId);

        let commentField = document.getElementById("commentMessage");
        document.getElementById("commentSubmit").onclick = async function () {
            const url = "/messages/" + mId + "/comments";
            // const url = "http://localhost:4567/messages" + mId + "/comments";
            try {
                const response = await fetch(url, {
                    method: "POST",
                    body: JSON.stringify({
                        mMessage: commentField.value
                    })
                });
                const json = await response.json();
                console.log(json);
                if (json.mStatus === "ok") {
                    window.location.reload(true);
                } else {
                    window.alert("Adding comment failed.");
                }
            } catch (err) {
                console.log(err);
            }
        }
    }
    // Phase 4
    // handling flagging messages
    async handleFlaggingMessages(mId) {
        //const url = "/messages/" + mId;
        const url = "http://localhost:3000/messages" + mId;
        try {
            const response = await fetch(url, {
                method: "DELETE",
            });
            const json = await response.json();
            console.log(json);
            if (json.mStatus === "ok") {
                window.location.reload(true);
            } else {
                window.alert("Flagging message failed");
            }
        } catch (err) {
            console.log(err);
        }
    }

    render() {
        // for labeling the checkbox
        const { label } = 'Flag Message';
        const { isChecked } = this.state;
        return (
            <div>
                {/* adding new entry */}
                <div className="container" id="newEntryForm" hidden>
                    <h3>Add a New Entry</h3>
                    <label>Title</label>
                    <input type="text" id="newTitle" className="form-control" placeholder="Title"></input>
                    <label>Message</label>
                    <textarea id="newMessage" className="form-control" placeholder="Message Content..."></textarea>
                    <button type="button" className="btn btn-primary" id="addSubmit" onClick={() => this.handleAddSubmit()}>Submit</button>
                    <button type="button" className="btn btn-primary" id="addCancel" onClick={() => this.handleAddCancel()}>Cancel</button>
                </div>
                {/* editing an entry */}
                <div className="container" id="editEntryForm" hidden>
                    <h3>Edit an Entry</h3>
                    <label>Title</label>
                    <input type="text" id="editTitle" className="form-control" placeholder="Title" disabled></input>
                    <label>Message</label>
                    <textarea id="editMessage" className="form-control" placeholder="Message Content..."></textarea>
                    <button type="button" className="btn btn-primary" id="editSubmit">Submit</button>
                    <button type="button" className="btn btn-primary" id="editCancel" onClick={() => this.handleEditCancel()}>Cancel</button>
                </div>
                {/* adding a comment */}
                <div className="container" id="addCommentForm" hidden>
                    <h3>Add a Comment</h3>
                    <label>Comment</label>
                    <textarea id="commentMessage" className="form-control" placeholder="Comment Content..."></textarea>
                    <button type="button" className="btn btn-primary" id="commentSubmit" onClick={() => this.handleAddComment()}>Submit</button>
                    <button type="button" className="btn btn-primary" id="commentCancel" onClick={() => this.handleCommentCancel()}>Cancel</button>
                </div>
                {/* showing all messages */}
                <div className="container" id="messages">
                    <button type="button" className="btn btn-primary" onClick={() => this.handleAddClick()}>
                        Add Message
                    </button>
                    <div className="File">
                        <input
                            style={{display: 'none'}}
                            type='file'
                            onFileChange={this.onFileUpload}
                            ref={fileInput => this.fileInput = fileInput}/>
                        <button onClick={() => this.fileInput.click()}>Pick File</button>
                        <button onClick={this.onFileUpload}>Upload</button>
                    </div>
                    <div className="checkbox">
                    <input
                        type="checkbox"
                        value={label}
                        checked={isChecked}
                        onChange={this.toggleCheckboxChange}
                        />
                    {label}
                    <label>Message 1: Hello</label>
                    </div>
                    <div className="checkbox">
                    <input
                        type="checkbox"
                        value={label}
                        checked={isChecked}
                        onChange={this.toggleCheckboxChange}
                        />
                    {label}
                    <label>Message 2: Hi</label>
                    </div>
                    <div className="checkbox">
                    <input
                        type="checkbox"
                        value={label}
                        checked={isChecked}
                        onChange={this.toggleCheckboxChange}
                        />
                    {label}
                    <label>Message 3: Hey</label>
                    </div>
                    <div className="container">
                    <div className="row">
                    <div className="col-sm-12">
                        <button className="btn btn-primary" type="submit">Report Flagged Message</button>
                    </div>
                    </div>
                </div>
                    {this.state.data.map(p => (
                        <div className="bg-light bg-gradient p-3 m-3" id={p.mId}>
                            <div>
                                <Avatar data={p} />
                            </div>
                            <h5 className="text-break">{p.mTitle}</h5>
                            <p className="text-break">{p.mMessage}</p>
                            <p>{"Posted on " + p.mDate}</p>
                            <div className="btn-group" role="group" aria-label="Basic checkbox toggle button group">
                                <button type="button" className="btn btn-primary" data-bs-toggle="button" autoComplete="off" onClick={() => this.handleLike(p.mId)} id={`btnLike` + p.mId}><i className="bi bi-hand-thumbs-up"></i> {p.mLikes}</button>

                                <button type="button" className="btn btn-secondary" data-bs-toggle="button" autoComplete="off" onClick={() => this.handleDislike(p.mId)} id={`btnDislike` + p.mId}><i className="bi bi-hand-thumbs-down"></i> {p.mDislikes}</button>

                                <button type="button" className="btn btn-primary" onClick={() => this.handleEdit(p.mId)}>Edit</button>
                                <button type="button" className="btn btn-secondary" onClick={() => this.handleDelete(p.mId)}>Delete</button>
                                <button type="button" className="btn btn-primary" onClick={() => this.handleAddCommentClick(p.mId)}>Add Comment</button>
                            </div>
                            <div className="bg-light p-4">
                                {p.mComments.map(c => (
                                    <div>
                                        <p className="text-break">{c.cContent}</p>
                                        <button className="btn btn-primary btn-sm">Edit</button>
                                    </div>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
            </div >
        );
    }
}

export default Post;