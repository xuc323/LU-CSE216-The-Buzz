import React from "react";

class Avatar extends React.Component {
    constructor(props) {
        super(props);
    }

    // changing the modal based on which one it clicks.
    handleClick() {
        const data = this.props.data;
        document.getElementById("account-profile-label").textContent = data.uId;
        document.getElementById("account-name").textContent = "Name: " + data.uName;
        document.getElementById("account-url").textContent = "Profile URL: " + data.uUrl;
        document.getElementById("account-email").textContent = "Email: " + data.uEmail;
    }

    render() {
        return (
            <img className="img-thumbnail rounded-circle mb-3" src={this.props.data.uUrl} alt="avatar" data-bs-toggle="modal" data-bs-target="#account-profile" type="button" onClick={() => this.handleClick()} />
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

export { Avatar, LoginBtn };