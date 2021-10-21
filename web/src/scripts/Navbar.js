import React from "react";

// on memory storage
const USER = {
    "uName": "Alice",
    "uUrl": "https://randomuser.me/api/portraits/women/18.jpg",
    "uEmail": "alice@lehigh.edu",
    "uBio": "Hello!"
}

// render navbar at the top of the screen
class Navbar extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { user: USER };
        this.state = { user: undefined };
    }

    // handle click for Account button
    async handleClick() {
        const session_key = localStorage.getItem("sessionKey");
        if (!this.state.user) {
            const url = "/user?session_key=" + session_key;
            try {
                const response = await fetch(url, {
                    method: "GET",
                });
                const data = await response.json();
                // if status is ok
                if (data.mStatus === "ok") {
                    this.setState({ user: data.mMessage });
                } else {
                    window.alert("Error loading profile");
                    return;
                }
            } catch (err) {
                console.log(err);
            }
        }
        const user = this.state.user;

        // changing DOM content
        document.getElementById("main-account-name").textContent = "Name: " + user.uName;
        document.getElementById("main-account-url").textContent = "Profile URL: " + user.uUrl;
        document.getElementById("main-account-email").textContent = "Email: " + user.uEmail;
        document.getElementById("main-account-profile1").src = user.uUrl;
        document.getElementById("bio").textContent = user.uBio;
    }

    render() {
        return (
            <nav className="navbar navbar-expand-md navbar-light bg-light" >
                <div className="container-lg">
                    <a className="navbar-brand" href="/"><i className="bi bi-house-fill"></i> Home</a>
                    <button
                        className="navbar-toggler"
                        typeof="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarToggler"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarToggler">
                        <ul className="navbar-nav me-auto mb-2 mb-md-0">
                            <li className="nav-item">
                                <a className="nav-link" href="/"><i className="bi bi-info-circle-fill"></i> About</a>
                            </li>

                            <li className="nav-item">
                                <a className="nav-link" href="/"><i className="bi bi-inbox-fill"></i> Feedback</a>
                            </li>

                            <li className="nav-item">
                                <a className="nav-link" href="" data-bs-toggle="modal" data-bs-target="#main-account-profile" onClick={() => this.handleClick()}><i className="bi bi-person-circle"></i> Account</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;