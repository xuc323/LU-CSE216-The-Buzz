import React from 'react';
import { Avatar } from './User';

class Post extends React.Component {
    constructor(props) {
        super(props);
        this.state = { data: [] };
    }

    // like button will increment the like count on screen as well as database
    async handleLike(mId) {
        const url = "/messages/" + mId + "/like";
        console.log(mId);
        try {
            const response = await fetch(url, {
                method: "PUT",
            });
            const json = await response.json();
            console.log(json);
        } catch (err) {
            console.log(err);
        }

        const data = this.state.data;
        for (let i = 0; i < data.length; i++) {
            if (data[i].mId === mId) {
                console.log(data[i]);
                data[i].mLikes++;
                break;
            }
        }
        this.setState({ data: data });
        // let btn = document.getElementById("btnLike" + mId).getAttribute("aria-pressed");
        // console.log(btn);
        // if (btn === "true") {
        //     btn = "false";
        // } else {
        //     btn = "true";
        // }
        // console.log(btn);
    }

    // dislike button will increment the dislike count on screen as well as database
    async handleDislike(mId) {
        const url = "/messages/" + mId + "/dislike";
        console.log(mId);
        try {
            const response = await fetch(url, {
                method: "PUT",
            });
            const json = await response.json();
            console.log(json);
        } catch (err) {
            console.log(err);
        }

        const data = this.state.data;
        for (let i = 0; i < data.length; i++) {
            if (data[i].mId === mId) {
                console.log(data[i]);
                data[i].mDislikes++;
                break;
            }
        }
        this.setState({ data: data });
    }

    // add message button
    handleAddClick() {
        document.getElementById("messages").hidden = true;
        document.getElementById("editEntryForm").hidden = true;
        document.getElementById("newEntryForm").hidden = false;
    }

    async handleAddSubmit() {
        let titleField = document.getElementById("newTitle");
        let messageField = document.getElementById("newMessage");
        try {
            const response = await fetch("/messages", {
                method: "POST",
                body: JSON.stringify({
                    mTitle: titleField.value,
                    mMessage: messageField.value
                })
            });
            const json = await response.json();
            console.log(json)
        } catch (err) {
            console.log(err);
        }
        window.location.reload(true);
    }

    // edit message button
    handleEdit(mId) {
        console.log(mId);
        document.getElementById("messages").hidden = true;
        document.getElementById("newEntryForm").hidden = true;
        document.getElementById("editEntryForm").hidden = false;

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
        titleField.value = orgTitle;
        messageField.value = orgMessage;
        document.getElementById("editSubmit").onclick = async function () {
            const url = "/messages/" + mId;
            try {
                const response = await fetch(url, {
                    method: "PUT",
                    body: JSON.stringify({
                        mMessage: messageField.value
                    })
                });
                const json = await response.json();
                console.log(json)
            } catch (err) {
                console.log(err);
            }
            window.location.reload(true);
        }
    }

    // delete the message
    async handleDelete(mId) {
        const url = "/messages/" + mId;
        try {
            const response = await fetch(url, {
                method: "DELETE",
            });
            const json = await response.json();
            console.log(json);
        } catch (err) {
            console.log(err);
        }
        window.location.reload(true);
    }

    // cancel will return to the main page
    handleCancel() {
        window.location.reload(true);
        // document.getElementById("messages").hidden = false;
        // document.getElementById("editEntryForm").hidden = true;
        // document.getElementById("newEntryForm").hidden = true;
    }

    // when the DOM got added, fetch all messages
    async componentDidMount() {
        try {
            const response = await fetch("/messages", {
                method: "GET"
            });
            const json = await response.json();
            this.setState({ data: json.mData });
        } catch (err) {
            console.log(err);
        }
    }

    render() {
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
                    <button type="button" className="btn btn-primary" id="addCancel" onClick={() => this.handleCancel()}>Cancel</button>
                </div>
                {/* editing an entry */}
                <div className="container" id="editEntryForm" hidden>
                    <h3>Edit an Entry</h3>
                    <label>Title</label>
                    <input type="text" id="editTitle" className="form-control" placeholder="Title" disabled></input>
                    <label>Message</label>
                    <textarea id="editMessage" className="form-control" placeholder="Message Content..."></textarea>
                    <button type="button" className="btn btn-primary" id="editSubmit">Submit</button>
                    <button type="button" className="btn btn-primary" id="editCancel" onClick={() => this.handleCancel()}>Cancel</button>
                </div>
                {/* showing all messages */}
                <div className="container" id="messages">
                    <button type="button" className="btn btn-primary" onClick={() => this.handleAddClick()}>
                        Add Message
                    </button>
                    {this.state.data.map(p => (
                        <div className="bg-light bg-gradient p-3 m-3" id={p.mId}>
                            <div>
                                <Avatar mId={p.mId} />
                            </div>
                            <h5 className="text-break">{p.mTitle}</h5>
                            <p className="text-break">{p.mMessage}</p>
                            <div className="btn-group" role="group" aria-label="Basic checkbox toggle button group">
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onClick={() => this.handleLike(p.mId)} id={`btnLike` + p.mId}><i className="bi bi-hand-thumbs-up"></i> {p.mLikes}</button>

                                <button type="button" class="btn btn-secondary" data-toggle="button" aria-pressed="false" onClick={() => this.handleDislike(p.mId)} id={`btnDislike` + p.mId}><i className="bi bi-hand-thumbs-down"></i> {p.mDislikes}</button>

                                <button type="button" className="btn btn-primary" onClick={() => this.handleEdit(p.mId)}>Edit</button>
                                <button type="button" className="btn btn-secondary" onClick={() => this.handleDelete(p.mId)}>Delete</button>
                            </div>

                        </div>
                    ))}
                </div>
            </div >
        );
    }
}

export default Post;