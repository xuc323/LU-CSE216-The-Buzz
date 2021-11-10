import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import { LoginBtn } from "../scripts/User";

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
    container = document.createElement("div");
    document.body.appendChild(container);
}));

afterEach(() => {
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

it("should render login btn", () => {
    act(() => {
        render(<LoginBtn />, container);
    });

    expect(container.querySelector("[id='loginBtn']") == !undefined);
});