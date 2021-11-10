import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import Post from "../scripts/Post";

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
        }],
    "mDate": "Oct 17, 2021"
}]

let container = null;
beforeEach((() => {
    // setup a DOM element as a render target
    container = document.createElement("div");
    document.body.appendChild(container);
}));

afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

it("should render posts", () => {
    act(() => {
        render(
            <Post data={POSTS} />, container
        );
    });

    expect(container.querySelector("[id='messages']") == !undefined);
    expect(container.querySelector("[id='newEntryForm']").hidden == true);
    expect(container.querySelector("[id='editEntryForm']").hidden == true);
    expect(container.querySelector("[id='addCommentForm']").hidden == true);
    
});