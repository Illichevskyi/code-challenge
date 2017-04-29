# Code Challenge

I have received this challenge as part of the recruiting process for HSBC. 

## Description

A simple social networking application, similar to Twitter, and
expose it through a web API. The application supports the scenarios
below.

## POSTMAN

All HTTP requests are saved as a POSTMAN collection.
This collection is stored in 'postman' folder in the root of this project.

It is saved in two formats: v-1 and v-2.

The content of these two files is the same. 
The only difference is a format.
 
I decided to include both formats for the sake of continence.

## Scenarios

### Posting new post

A user can post a 140 character message.

#### For a new user

##### URL
http://localhost:8080/post

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"userName": "name1", "text":"1"}

#### For an existing user

##### URL
http://localhost:8080/post

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"userId":1, "userName": "name1", "text":"2"}

### Delete a post


##### URL
http://localhost:8080/post/delete/{postId}

##### HTTP REQUEST METHOD
DELETE

### Wall

A user can see a list of the messages they've posted, in reverse
chronological order.

##### URL
http://localhost:8080/post/wall/{userId}

##### HTTP REQUEST METHOD
GET

### Following

A user can follow another user. Following is not
reciprocal: Alice can follow Bob without Bob having to follow Alice.

##### URL
http://localhost:8080/user/follow

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"followerId": 2, "followeeId": 1}

### UnFollowing

A user can unfollow another user.

##### URL
http://localhost:8080/user/unfollow

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"followerId": 2, "followeeId": 1}

### Timeline

A user can see a list of the messages posted by all the people
they follow, in reverse chronological order. 
Their own posts are not visible here. 
They are visible only in user's wall.

##### URL
http://localhost:8080/post/timeline/{userId}

##### HTTP REQUEST METHOD
GET

### List of followers

A user can see a list of people who follows his posts.

##### URL
http://localhost:8080/user/followers/{userId}

##### HTTP REQUEST METHOD
GET

### List of followees

A user can see a list of people whose posts he follows.

##### URL
http://localhost:8080/user/followees/{userId}

##### HTTP REQUEST METHOD
GET

### Likes

A user can like another user's post.
These users does not necessarily should be followers or followees of each other.

##### URL
http://localhost:8080/post/like

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"likedUserId":2, "postId":1}

### Remove previous like

A user can unlike another user's post which he previously liked

##### URL
http://localhost:8080/post/unlike

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"likedUserId":2, "postId":1}

### Repost

A user can make a repost of another's user previous post .
These users does not necessarily should be followers or followees of each other.

##### URL
http://localhost:8080/post/repost

##### HTTP REQUEST METHOD
POST

##### JSON EXAMPLE
{"userId": 2, "postId":1}

### Delete repost

A user can delete previously made repost of another's user original post.
This is done like an ordinary post removal which is already described above.
During repost removal, the original post is not removed.

##### URL
http://localhost:8080/post/delete/{postId}

##### HTTP REQUEST METHOD
DELETE

## How to run

run 

`mvn clean install`

then

` java -jar target/code-challenge-1.0.0.jar`



## Details

- we don't care about registering users: a user is created as soon as they post
  their first message
- we don't care about user authentication
- we don't care about frontend, only backend
- we don't care about storage: storing everything in memory is fine
