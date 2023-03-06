# polyBank
Trabajo de tecnologías de aplicaciones web, consiste en una aplicación web de gestión bancaria


## Seting up docker container with MySql database:

- get image and configure the container:
```
docker pull mysql

docker run --name mysql -e MYSQL_ROOT_PASSWORD=1111 -p 33060:33060 -p 3306:3306 -d mysql
```
- copy your script to the container root directory and log in:
```
docker cp <path/to/your/script.sql> mysql:.
docker exec -it mysql mysql -u tawUser -p
```
where path/to/your/script.sql is the the location of your script. (Use double '\\' if on Windows)

Insert your password when required.
- execute the .sql script inside the container
```
source <./scriptName.sql>
```

