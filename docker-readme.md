# Docker Stuff

## NOTE:

As of July 2023 `docker-compose` has been depreciated.  Now use `Docker compose` instead.

Also the `docker-compose.yml` file is now just `compose.yml` 


Build the image
> docker build -t sb3-java17-mvcapp .

Run the image
> docker run -e "SPRING_PROFILES_ACTIVE=local" -p 8080:8080 sb3-java17-mvcapp
> You can enter `control+C` to stop the container.
> 
> Use `docker compose` with a profile-based yml like [compose-local.yml](compose-local.yml).
> After creating your compose{-profile}.yml file you can run a check on it with this command:
> docker compose -f compose-local.yml config
> and to build your image you can run
> docker compose -f compose-local.yml build
> and to run it use:
> docker compose -f compose-local.yml up
> Note that version `2.2` of docker compose, or higher, is required.

## Useful commands:
> List all images: `docker images`
> List all running containers: `docker container ls`

## Useful Links to get started:

[Spring Official Docs](https://spring.io/guides/topicals/spring-boot-docker)

## Baeldung Usefuls
> [Dockerize Spring Boot](https://www.baeldung.com/dockerizing-spring-boot-application)
>
>[Set Spring Profile](https://www.baeldung.com/spring-boot-docker-start-with-profile)
>
>[Setting Ports](https://www.baeldung.com/ops/docker-compose-expose-vs-ports)


## General Help
>[General Help](https://github.com/docker/labs/blob/master/developer-tools/java/chapters/ch03-build-image.adoc)
