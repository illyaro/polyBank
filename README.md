# polyBank
Trabajo de tecnologías de aplicaciones web, consiste en una aplicación web de gestión bancaria

## Seting up docker container with MySql database:
- Execute the next command at the root of the repository and that's it. It will pull the image from DockerHub and then configure the sql needed.

```
docker compose up -d
```

- When the container is running you will be able to connect to it through port ```3306```. Check it via:
```
docker compose ls
```

- You can stop the pod with:
```
docker compose stop <id>
```
- or re-run it with:
```
docker compose run <name_of_the_service>
```
## Updating the database
- You can rebuild the image by using:
```
docker compose build 
```
- Remove the images you don't uses bye typing:
```
docker compose rm <name_of_the_service> 
```
- You can remove the image you don't uses by typing:
```
docker image rm <image> 
```
- or remove it with all the images you don't use by typing:
```
docker image prune 
```