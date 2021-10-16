import React from "react";

class Navbar extends React.Component {
    constructor(props) {
        super(props);
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
                                <a className="nav-link" href="/"><i className="bi bi-person-circle"></i> Account</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;