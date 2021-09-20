# backend

## REST API

> Add the following keywords to the end of the website url to make request. Request type is specified at each bullet point.

### **GET - /messages**

- Returns a JSON listing of all messages in Database.

#### _Accepts_:

- NONE

#### _Returns_:

- mStatus
- mData: [mId, mTitle, mMessage, mLikes, mDislikes, mDate]

### **GET - /messages/[message_id]**

- Ruturns a JSON about the message at id

#### _Accepts_:

- message id in url

#### _Returns_:

- mStatus
- mData: [mId, mTitle, mMessage, mLikes, mDislikes, mDate]

### **POST - /messages**

- This should accept a JSON body and create a new message rowdata in the Database.

#### _Accepts_: message info in body

- mTitle
- mMessage

#### _Returns_:

- mStatus
- mMessage

---

## Setup
