import React from "react";

class Avatar extends React.Component {
    constructor(props) {
        super(props);
    }

    handleClick() {
        const title = document.getElementById("account-profile-label");
        const body = document.getElementById("account-details");

        title.textContent = this.props.data.uId;
        body.textContent = "Profile URL: " + this.props.data.uUrl + "\nEmail: " + this.props.data.uEmail;
    }

    render() {
        return (
            <img className="img-thumbnail rounded-circle mb-3" src={this.props.data.uUrl} alt="avatar" data-bs-toggle="modal" data-bs-target="#account-profile" type="button" onClick={() => this.handleClick()} />
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
            <div id="LoginBtn">
                <div id="g_id_onload"
                    data-client_id="496410238969-mvosj73q4tnp1dumhbpfbucato5ner3k.apps.googleusercontent.com"
                    // data-login_uri="https://xuc-web.herokuapp.com/login"
                    // data-login_url="http://localhost:4567/login"
                    data-callback="handleCredentialResponse">
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
        );
    }
}

export { Avatar, UserInfo, LoginBtn };