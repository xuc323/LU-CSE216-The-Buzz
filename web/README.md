# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)


# Phase 4: Web Developer's Manual & Documentation
I started out with Phase 4 with the front end showing the toolbar, time, google sign-in button, and a button to add a message. What didn't seem to be functioning is being able to post a message which is most likely due to a problem with backend. Here's what I did in this phase.

First I refactored the state of the code by adding onto the functionality of file sharing and being able to upload a pdf or image files which was somewhat implemented in the previous phase. I did this by implementing Axios with Heroku to create “pick file” and “upload” buttons on the website. Axios is a Javascript library that serves to create HTTP requests that are present externally and allows you to call backend API interfaces from React front end. Also created a unit test for uploading a file.

You can learn more about Axios here: https://medium.com/geekculture/learn-to-use-axios-with-react-ee92829d8ed6

I also used Axios to try to implement translating from one language to another.
* Installed: npm install --save-dev @testing-library/user-event @testing-library/dom
* For testing `file.test.js`

Next I tried to implement flagging content using checkboxes and a button to do this. The logic behind it is that the user can check the message or messages they would like to report and then theoretically it would send to backend and move that data to another table and the message(s) would not display anymore/delete the message in its entirety. I created a function in Post.js called `handleFlaggingMessages` that passes an argument of the message ID (mId) to specify the message and delete it if checked.

Similarly for attempting to implement blocking a user, I created a function in Post.js called `handleBlockingUser` that passes the user ID (uId) and will delete the user and their information from the database if checked.

Editing and deleting messages was implemented already from a previous phase.

## Important resources
Bitbucket Repository: https://bitbucket.org/chm321/cse216_group25/src/master/

The branches that matter for web frontend are `web` and `backend`.

## The Code
Since backend was not working when completing phase 4, I tested web locally. First run `npm install` and then `npm start` to run the app.