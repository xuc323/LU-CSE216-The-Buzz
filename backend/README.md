# **BACKEND**

## REST API

> Add the following keywords to the end of the website url to make request. Request type is specified at each bullet point.

### **GET - /messages**

- Returns a JSON listing of all messages in Database.

#### _Accepts_:

- NONE

#### _Returns_:

```json
{
  "mStatus": "ok",
  "mData": [
    {
      "mId": "MESSAGE_ID",
      "mTitle": "MESSAGE_TITLE",
      "mMessage": "MESSAGE_CONTENT",
      "mLikes": "NUMBER_LIKES",
      "mDislikes": "NUMBER_DISLIKES",
      "mDate": "DATE_CREATED"
    }
  ]
}
```

**Note:** `mData` is an array.

### **GET - /messages/[message_id]**

- Ruturns a JSON about the message at id

#### _Accepts_:

- `message_id` in url

#### _Returns_:

```json
{
  "mStatus": "ok",
  "mData": {
    "mId": "MESSAGE_ID",
    "mTitle": "MESSAGE_TITLE",
    "mMessage": "MESSAGE_CONTENT",
    "mLikes": "NUMBER_LIKES",
    "mDislikes": "NUMBER_DISLIKES",
    "mDate": "DATE_CREATED"
  }
}
```

```json
{
  "mStatus": "error",
  "mMessage": "ID not found"
}
```

### **POST - /messages**

- This should accept a JSON body and create a new message rowdata in the Database.

#### _Accepts_: message info in body

```json
{
  "mTitle": "MESSAGE_TITLE",
  "mMessage": "MESSAGE_CONTENT"
}
```

#### _Returns_:

```json
{
  "mStatus": "STATUS_CODE",
  "mMessage": "DATABASE_MESSAGE"
}
```

### **PUT - /messages[message_id]**

- This should accept a JSON body contains both likes and dislikes and increments the one with 1.

#### _Accepts_:

```json
{
  "mLikes": 1,
  "mDislikes": 0
}
```

#### _Returns_:

```json
{
  "mStatus": "STATUS_CODE",
  "mMessage": "DATABASE_MESSAGE"
}
```

---

## Setup

```zsh
// clean and package the backend code
% cd backend
% DATABASE_URL=`DATABASE_URL` mvn clean package

// run locally
% DATABASE_URL=`DATABASE_URL` mvn exec:java

// deploy to Heroku
% DATABASE_URL=`DATABASE_URL` mvn heroku:deploy
```
