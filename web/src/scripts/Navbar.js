function Navbar() {
    return (
        <nav className="navbar navbar-expand-md navbar-light bg-secondary">
            <div className="container-lg">
                <a className="navbar-brand" href="/">
                    <i className="bi bi-house-fill"></i>
                    Home
                </a>

                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarToggler"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarToggler">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0">
                        <li class="nav-item">
                            <a class="nav-link" href="/about">
                                <i class="bi bi-info-circle-fill"></i>
                                About
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="">
                                <i class="bi bi-inbox-fill"></i>
                                Feedback
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/account">
                                <i class="bi bi-person-circle"></i>
                                Account
                            </a>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <input
                            class="form-control me-2"
                            type="search"
                            placeholder="Search..."
                        />
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;