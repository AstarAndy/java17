# Spring-Boot 3 n Java 17 Record Exercise

This exercise project was created mostly to exercise the (relatively) new Java [Record](https://www.baeldung.com/java-record-keyword).  The goal of this exercise project to create a nested object graph using only the `Record`
and then push json data using `MVC` and `RestTemplate`. 

## Please keep the following in mind:  
1. This is an exercise project and was not intended for production use!
2. You will not find any error handling like you would in a real app (See 1)
3. You will not find much of any `JavaDocs` which you expect in a real code base (See 1 again)
4. The `only` purpose of this exercise is play with the new java `Record`


## Goals of this exercise  
See if `Record` will work in places where a traditional DTO/Swagger POJO would be used.

Test the following:  
1. Are generics fully supported?
2. Can I use Annotations on each of the fields such as validation or jackson?
3. Can I still use [Lombok](https://www.baeldung.com/intro-to-project-lombok) annotations on a given `Record`?
4. Will MVC correctly return all the required JSON using the standard [ResponseEntity<?>](https://www.baeldung.com/spring-response-entity) on controller return values?
5. Does [RestTemplate](https://www.baeldung.com/rest-template), [WebClient](https://www.baeldung.com/spring-5-webclient), and/or [RestClient](https://www.baeldung.com/spring-boot-restclient) deserialize the json responses into my `Record` based object graph?
6. Does basic [Swagger/OpenApi]([here](https://howtodoinjava.com/spring-boot/springdoc-openapi-rest-documentation/)) work ok with records?

All test cases above **passed** with no exceptions.  

## Conclusions  
Assuming you're on java 14 or up, start using `Record` for your DTOs and, as needed continue to use Lombok.

## If you'd like to play with this project feel free to cloae and have some fun with it

## Requirements
* Java 17 or better
* Spring Boot 3.2
* Maven 3.9

## Getting Started
1. Clone this repo to your local HD.
2. Open a terminal window (on Windows I would use GitBASH).
3. Switch to the root folder of the project.
4. Execute `./mvnw clean install`

This _MUST_ work without error.  If there's an error then you probably don't have the required java on the classpath or a version of maven installed that Spring 3.2 requires.

5. Start your IDE and then import the project as a `Maven` project.
6. Create a runconfig and be sure you define the spring profile as `local`

At this point you have everything you need to play with the project

## Dog Api public api  

A public api was used as the backend of this exercise.  Here's a list of URLS for the actual api.  You can simply click on one of these links and you'll see the response right from your browser:

> https://randomuser.me/api
> https://randomuser.me/api?city=Fuenlabrada

Click on this URL in your browser,  https://randomuser.me/api and view the returned JSON.

This project used the java `Record` type and implemented `part of` the entire graph.  Basically the `results` section.  The remaining return data is `ignored`.

Once you have the app running locally, you can then use the localhost URLs as follows  
> http://localhost:8080/users
> http://localhost:8080/users?city=Fuenlabrada

And `SwaggerDocs` is enabled and you can hit it via these URLs locally  
> http://localhost:8080/v3/api-docs  
> http://localhost:8080/swagger-ui.html

Feel free to post comments on the repo if you'd like.


