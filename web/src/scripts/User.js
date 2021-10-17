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

class LoginBtn extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <div id="g_id_onload"
                    data-client_id="496410238969-mvosj73q4tnp1dumhbpfbucato5ner3k.apps.googleusercontent.com"
                    data-login_uri="https://the-buzz-group25.herokuapp.com/login"
                    data-auto_prompt="false">
                </div>
                <div className="g_id_signin"
                    data-type="standard"
                    data-size="large"
                    data-theme="outline"
                    data-text="sign_in_with"
                    data-shape="rectangular"
                    data-logo_alignment="left">
                </div>
            </div>
        )
    }
}

export { Avatar, UserInfo, LoginBtn };