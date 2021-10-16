import React from "react";

class Avatar extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <img className="img-thumbnail rounded-circle mb-3" src={"https://randomuser.me/api/portraits/women/1.jpg"} alt="avatar" />
        );
    }
}

class UserInfo extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="UserInfo" >
                <Avatar user={this.props.user} />
                <div className="UserInfo-name">
                    {this.props.user.name}
                </div>
            </div>
        );
    }
}

export { Avatar, UserInfo };