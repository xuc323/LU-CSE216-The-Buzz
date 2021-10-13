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

### **PUT - /messages/[message_id]**

- This should accept a JSON body contains the new message, then change the message at `message_id`.

#### _Accepts_:

```json
{
  "mMessage": "NEW_MESSAGE"
}
```

#### _Returns_:

```json
{
  "mStatus": "STATUS_CODE",
  "mMessage": "DATABASE_MESSAGE"
}
```

### **PUT - /messages/[message_id]/like**

- This will increment the like count at `message_id` by 1.

#### _Accepts_:

- None

#### _Returns_:

```json
{
  "mStatus": "STATUS_CODE",
  "mMessage": "DATABASE_MESSAGE"
}
```

### **PUT - /messages/[message_id]/dislike**

- This will increment the dislike count at `message_id` by 1.

#### _Accepts_:

- None

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


Phase 2 Documentation: 

Requirements: 
[1] Retrieve Credentials from the User through the Client

[2] Authenticate? If already registered in database, then just Authorize 
  [a] Through redirect URIs, reroute pages so that information is not present in the visible URL 

  [b] For example, "[Your App URL]/login" -> "[Your App URL]/auth2/..."

[3] If not registered, you need to retrieve some information, insert as tuple in database relation: 
  [a] Payload(email (Primary key), first_name, last_name, picture_url, email_verified, locale)
      [-] Question to consider -> If emails were to be changed or emails from domains outside of Lehigh were to sign up, then how would the relation schema be changed? 

[4] Create an Access Token via the Authorization Code retrieved from the GET url 
  [a] Create an Access Token using the information from the ID Token? 

  [b] Terminologies: 
      [-] Access token used in token-based authentication to gain access to resources by using them as bearer tokens.
      [-] Refresh token is a long-lived special kind of token used to obtain a renewed access token.
      [-] ID token carries identity information encoded in the token itself, which must be a JWT. It must not contain any authorization information, or any audience information — it is merely an identifier for the user.






[Reference] 

Client ID: 273353928555-jlrr9q6grlf6n1sd07p7mc06l06ft98d.apps.googleusercontent.com

Client Secret: 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

https://accounts.google.com/o/oauth2/v2/auth?
 scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&
 access_type=offline&
 include_granted_scopes=true&
 response_type=code&
 state=state_parameter_passthrough_value&
 redirect_uri=https%3A//oauth2.example.com/code&
 client_id=client_id



 https://accounts.google.com/o/oauth2/v2/auth?
 scope= https://mail.google.com&
 access_type=offline&
 include_granted_scopes=true&
 response_type=code&
 state=state_parameter_passthrough_value&
 redirect_uri=http://localhost&
 client_id=273353928555-jlrr9q6grlf6n1sd07p7mc06l06ft98d.apps.googleusercontent.com


[Returned Code from Authorization]

http://localhost/?state=state_parameter_passthrough_value&code=4/0AX4XfWj95qATxS7J240ppKs1H2UabwxoEcdeQ3uyDXmSTVtESrRaqcrJYup_iNMsdggm8Q&scope=https://mail.google.com/

[Code] 4/0AX4XfWj95qATxS7J240ppKs1H2UabwxoEcdeQ3uyDXmSTVtESrRaqcrJYup_iNMsdggm8Q&



POST /token HTTP/1.1
Host: oauth2.googleapis.com
Content-Type: application/x-www-form-urlencoded

code=4/0AX4XfWj95qATxS7J240ppKs1H2UabwxoEcdeQ3uyDXmSTVtESrRaqcrJYup_iNMsdggm8Q&
client_id=273353928555-jlrr9q6grlf6n1sd07p7mc06l06ft98d.apps.googleusercontent.com&
client_secret=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&
redirect_uri=http://localhost&
grant_type=authorization_code



[Get Access Token from Authorization Code]

curl \
--request POST \
--data "code=[Authentcation code from authorization link]&client_id=[Application Client Id]&client_secret=[Application Client Secret]&redirect_uri=http://localhost&grant_type=authoriza..." \
https://accounts.google.com/o/oauth2/...


[Filled in template] 

curl \
--request POST \
--data "code=4/0AX4XfWj95qATxS7J240ppKs1H2UabwxoEcdeQ3uyDXmSTVtESrRaqcrJYup_iNMsdggm8Q&client_id=273353928555-jlrr9q6grlf6n1sd07p7mc06l06ft98d.apps.googleusercontent.com&client_secret=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&redirect_uri=http://localhost&grant_type=authorization_code" \
https://accounts.google.com/o/oauth2/token
